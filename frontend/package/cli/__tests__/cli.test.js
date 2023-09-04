import { test, expect } from "@jest/globals";
import { execa } from "execa";
import { resolve } from "path";

const CLI = resolve(process.cwd(), ".", "bin") + "/th-cli.js";

test("未知命令", async () => {
  const { stderr } = await execa(CLI, ["any"]);
  expect(stderr).toContain("命令不存在");
});
