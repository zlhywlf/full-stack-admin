import inquirer from "inquirer";
import type {
  QuestionCollection,
  AsyncDynamicQuestionProperty,
  DistinctChoice,
  Answers,
  ListChoiceMap
} from "inquirer";

const use = async (addOpt: () => QuestionCollection) => {
  return inquirer.prompt(addOpt()).then(answer => answer.name);
};

export const useList = async (
  message: string,
  choices:
    | AsyncDynamicQuestionProperty<
        readonly DistinctChoice<Answers, ListChoiceMap<Answers>>[],
        Answers
      >
    | undefined
) => {
  return use(() => {
    return {
      name: "name",
      type: "list",
      message,
      choices
    };
  });
};

export const useInput = async (message: string, defaultValue?: string) => {
  return use(() => {
    return {
      name: "name",
      type: "input",
      message,
      default: defaultValue
    };
  });
};
