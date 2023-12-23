<template>
    <div id="errorContainer">
        <div id="hideButton"><a href="#" @click="$emit('hideError')">X</a></div>
        <div id="errorMessage">{{ errorMessage }}</div>
    </div>
</template>

<script setup lang="ts">
defineProps({
    errorMessage: String
})
</script>

<style>
#errorContainer {
    bottom: 5%;
    right: 5%;
    height: fit-content;
    width: fit-content;
    position: fixed;
    background-color: red;
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