#!/usr/bin/env node

import entry from "../dist/index.js";
import importLocal from "import-local";
import log from "../dist/src/util/log.js";
import * as pkg from "./pkj.cjs";

if (importLocal(import.meta.url)) {
  log.verbose("importLocal", "Using local version of this package");
} else {
  log.verbose("importLocal", "Code for both global and local version here…");
  entry(pkg.default);
}
