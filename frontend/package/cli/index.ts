import { program } from "commander";
import log from "./src/util/log.js";
import "./src/util/exception.js";
import { InitCommand } from "./src/command/InitCommand.js";
import semver from "semver";

const LOWEST_NODE_VERSION = "18.0.0";

export default (pkg: { bin: object; version: string }) => {
  const binName = Object.keys(pkg.bin)[0];
  program
    .name(binName)
    .usage("<command> [options]")
    .version(pkg.version)
    .option("-d, --debug", "是否开启调试模式 ", false)
    .hook("preAction", () => {
      log.info(`${binName} version`, pkg.version);
      log.info("node version", process.version);
      if (!semver.gte(process.version, LOWEST_NODE_VERSION)) {
        throw new Error(`最低版本要求${LOWEST_NODE_VERSION}`);
      }
    });

  new InitCommand().register(program);

  program.parse(process.argv);
};
