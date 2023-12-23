<template>
    <Layout>
        <div class="centerContainer">
            <h2>Good {{timeOfDay}}, what would you like to do?</h2>
            <div style="padding-top: 10%;"></div>
            <table class="centerTable">
                <tr>
                    <td>Add a shift</td>
                    <td>View shifts</td>
                </tr>
                <tr>
                    <td><img src="../assets/plus.png" alt="Add a shift" @click="router.push('/addShift')"></td>
                    <td><img src="../assets/calendar.png" alt="View shifts" @click=""></td>
                </tr>
            </table>
        </div>
    </Layout>    
</template>

<script setup lang="ts">
    import Layout from '../components/Layout.vue';
    import router from '../router';

    let hour:number = new Date().getHours();
    let timeOfDay:string;
    if (hour > 4 && hour < 12) {
        timeOfDay = 'morning'
    }
    else if (hour >= 12 && hour < 17) {
        timeOfDay = 'afternoon'
    }
    else {
        timeOfDay = 'evening'
    }
</script>

<style>

</style>