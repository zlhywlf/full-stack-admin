import { BaseCommand } from "./BaseCommand.js";
import log from "../util/log.js";
import { useInput, useList } from "../util/inquirer.js";
import { $ } from "execa";

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

  action([name, opts]: [string, object]): void | Promise<void> {
    log.success("init command!", name, opts);
    this.createTemplate(name);
  }

  private async createTemplate(name: string) {
    const pName = await useInput("请输入项目名称", name);
    log.verbose("pName == ", pName);
    const tempalteValue = await useList("请选择项目模板", InitCommand.TEMPLATE);
    const selectTemplate = InitCommand.TEMPLATE.find(
      t => t.value === tempalteValue
    );
    if (selectTemplate) {
      const { value, project } = selectTemplate;
      const url = `git clone -b ${value} --depth=1 https://github.com/zlhywlf/${project}.git`;
      log.verbose("url", url);
      await $`${url}`;
    } else {
      throw new Error("Template does not exist");
    }
  }
}
