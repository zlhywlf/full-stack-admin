import { BaseCommand } from "./BaseCommand.js";
import log from "../util/log.js";

export class InitCommand extends BaseCommand {
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
  }
}
