import type { Command } from "commander";

export abstract class BaseCommand {
  register(program: Command) {
    if (!program) {
      throw new Error("program must not be null");
    }
    const cmd = program.command(this.name);
    // 'preSubcommand' | 'preAction' | 'postAction';
    cmd.hook("preAction", () => this.preAction());
    cmd.hook("postAction", () => this.postAction());
    this.options?.forEach(o => cmd.option(...o));
    cmd.description(this.description).action((...args) => this.action(args));
  }

  get options(): Array<[string, string, boolean]> | null {
    return null;
  }

  abstract get name(): string;
  abstract get description(): string;

  preAction() {
    // empty
  }

  postAction() {
    // empty
  }

  abstract action(args: unknown): void | Promise<void>;
}
