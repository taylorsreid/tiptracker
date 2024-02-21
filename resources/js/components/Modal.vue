<template>
    <div v-show="visible" id="container">
        <div id="hideButton" @click="visible = !visible">X</div>
        <div id="message">{{ message }}</div>
    </div>
</template>

<script setup lang="ts">
import { useModalStore } from '../stores';
import { storeToRefs } from 'pinia'

const { message, color, visible } = storeToRefs(useModalStore())
</script>

<style scoped>
#container {
    bottom: 5%;
    right: 5%;
    height: fit-content;
    width: fit-content;
    position: fixed;
    background-color: v-bind(color);
    border-radius: .5rem;
    padding-top: 1%;
    padding-bottom: 2.5%;
    padding-left: 2.5%;
    padding-right: 1%;
    color: black;

}

#hideButton {
    text-align: right;
}

a {
    color: black;
}

a:hover {
    color: white;
}
</style>