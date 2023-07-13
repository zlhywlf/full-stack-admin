<template>
  <div>
    <h1>admin</h1>
    <p>question: a is null?</p>
    <p>answer: {{ isNull(a) }}</p>
  </div>
</template>

<script setup lang="ts">
import { isNull } from "@fs/util";
let a: string;
</script>

<style scoped lang="less">
div {
  width: auto;
  user-select: all;
  h1 {
    color: blue;
  }
}
</style>
