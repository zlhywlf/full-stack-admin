import { BaseCommand } from "./BaseCommand.js";
import log from "../util/log.js";
import { useInput, useList } from "../util/inquirer.js";
import { handleError } from "../util/exception.js";
import { $ } from "execa";
import { homedir } from "os";
import { resolve } from "path";
import { existsSync, readdirSync } from "fs";
import ora from "ora";
import ejs from "ejs";
import { glob } from "glob";
import fse from "fs-extra";
const { ensureDirSync, emptyDirSync, copySync, writeFileSync } = fse;

export class InitCommand extends BaseCommand {
  static TEMPLATE = [
    {
      name: "vue-dev",
      value: "dev",
      project: "vue-template"
    },
    {
      name: "vue-master",
      value: "",
      project: "vue-template"
    }
  ];

  get options(): Array<[string, string, boolean]> {
    return [["-f, --force", "是否强制更新", false]];
  }

  get name(): string {
    return "init [name]";
  }

  get description(): string {
    return "初始化项目";
  }

  action([name, opts]: [string, { force: string }]): void | Promise<void> {
    log.success("init command!", name, opts);
    this.createTemplate(name, opts.force);
  }

  private async createTemplate(name: string, force: string) {
    let pName: string;
    if (name) {
      pName = name;
    } else {
      pName = await useInput(
        "请输入项目名称",
        v => (v.length > 0 ? true : "必填"),
        "project"
      );
    }
    log.verbose("pName == ", pName);
    const tempalteValue = await useList("请选择项目模板", InitCommand.TEMPLATE);
    const selectTemplate = InitCommand.TEMPLATE.find(
      t => t.value === tempalteValue
    );
    if (selectTemplate) {
      const { value, project } = selectTemplate;
      const cwd = resolve(`${homedir()}`, ".th-cli");
      if (!existsSync(cwd)) {
        ensureDirSync(cwd);
      }
      const spinner = ora("正在下载模板...").start();
      try {
        const templateDir = resolve(cwd, project);
        if (existsSync(templateDir)) {
          emptyDirSync(templateDir);
        }
        await $({
          cwd
        })`git clone -b ${value} --depth=1 https://github.com/zlhywlf/${project}.git`;
        const installDir = resolve(process.cwd(), pName);
        if (existsSync(installDir)) {
          if (!force) {
            spinner.stop();
            log.error("当前目录已存在", installDir);
            return;
          }
          emptyDirSync(installDir);
        }
        ensureDirSync(installDir);
        const fileList = readdirSync(templateDir);
        fileList.map(f => {
          if (f == ".git") {
            return;
          }
          copySync(resolve(templateDir, f), resolve(installDir, f));
        });

        const results = await glob("**", {
          cwd: installDir,
          withFileTypes: true,
          ignore: ["**/public/*", "**/assets/*"]
        });
        results.map(f => {
          if (f.isFile()) {
            log.verbose("f", f.fullpath());
            ejs.renderFile(f.fullpath(), { p: { name: pName } }, (err, str) => {
              if (!err) {
                writeFileSync(f.fullpath(), str);
              } else {
                handleError(err);
              }
            });
          }
        });
        spinner.stop();
        log.success("模板下载成功");
      } catch (e) {
        spinner.stop();
        handleError(e as Error);
      }
    } else {
      throw new Error("Template does not exist");
    }
  }
}
