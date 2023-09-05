import { isDebug } from "./util.js";
import log from "./log.js";
import chalk from "chalk";

export const handleError = (e: Error, type = "uncaughtException") =>
  log.error(
    type,
    chalk.red(isDebug ? (e.stack ? e.stack : e.message) : e.message)
  );

process.on("uncaughtException", e => handleError(e));

process.on("unhandledRejection", e =>
  handleError(e as Error, "unhandledRejection")
);
