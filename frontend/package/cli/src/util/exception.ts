import { isDebug } from "./util.js";
import log from "./log.js";
import chalk from "chalk";

const handleError = (type: string, e: Error) =>
  log.error(
    type,
    chalk.red(isDebug ? (e.stack ? e.stack : e.message) : e.message)
  );

process.on("uncaughtException", e => handleError("uncaughtException", e));

process.on("unhandledRejection", e =>
  handleError("unhandledRejection", e as Error)
);
