import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import autoprefixer from "autoprefixer";
import { resolve } from "path";

export default defineConfig({
  base: "./",
  resolve: {
    alias: [
      {
        find: /\/@\//,
        replacement: resolve(process.cwd(), ".", "src") + "/"
      }
    ]
  },
  plugins: [vue()],
  server: {
    open: true
  },
  css: {
    postcss: { plugins: [autoprefixer] }
  }
});
