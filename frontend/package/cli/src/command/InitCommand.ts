import { BaseCommand } from "./BaseCommand.js";
import log from "../util/log.js";
import inquirer from "inquirer";

export class InitCommand extends BaseCommand {
  static TEMPLATE = [
    {
      name: "示例1",
      value: "demo1",
      version: "v1.0.0"
    },
    {
      name: "示例2",
      value: "demo2",
      version: "v1.0.0"
    }
  ];
  static TYPE = [
    {
      name: "页面",
      value: "page"
    },
    {
      name: "项目",
      value: "project"
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
    const type = await inquirer
      .prompt({
        name,
        choices: InitCommand.TYPE,
        message: "请选择初始化类型",
        default: "project",
        type: "list"
      })
      .then(answer => answer[name]);
    log.verbose("type == ", type);
    if (type == "project") {
      const pName = await inquirer
        .prompt({
          name,
          message: "请输入项目名称",
          type: "input"
        })
        .then(answer => answer[name]);
      log.verbose("pName == ", pName);
      const tempalteValue = await inquirer
        .prompt({
          name,
          choices: InitCommand.TEMPLATE,
          message: "请选择项目模板",
          type: "list"
        })
        .then(answer => answer[name]);
      log.verbose("tempalte == ", tempalteValue);
      const selectTemplate = InitCommand.TEMPLATE.find(
        t => t.value === tempalteValue
      );
      log.verbose("selectTemplate == ", selectTemplate as unknown as string);
    }
  }
}
