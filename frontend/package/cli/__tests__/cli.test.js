import { test, expect } from "@jest/globals";
import { execa } from "execa";
import { resolve } from "path";

const run = args =>
  execa(resolve(process.cwd(), ".", "bin") + "/th-cli.js", args);

test("未知命令", async () => {
  const { stderr } = await run(["any"]);
  expect(stderr).toContain("命令不存在");
});

test("help 命令", async () => {
  let err = null;
  try {
    await run(["-h"]);
  } catch (e) {
    err = e;
  }
  expect(err).toBe(null);
});

test("版本号", async () => {
  const { stdout } = await run(["-V"]);
  expect(stdout).toContain((await import("../package.json")).default.version);
});
