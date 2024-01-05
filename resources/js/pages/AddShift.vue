<template>
    <Layout>
        <div class="centerContainer">
            <BForm @submit="submit">
                <div>
                    <div>
                        <label for="job_id">Job:</label>
                    </div>
                    <div>
                        <select v-model="currentShift.job_id" id="job_id" @change="jobUpdated" required>
                            <option v-for="(job, index) in user?.jobs" :value="job.id">{{ job.title }}</option>
                        </select>
                    </div>
                </div>

                <div>
                    <div>
                        <label for="date">Date:</label>
                    </div>
                    <div>
                        <input v-model="currentShift.date" id="date" type="date" required />
                    </div>
                </div>

                <div>
                    <div>
                        <label for="hours_worked_regular">Hours Worked (regular):</label>
                    </div>
                    <div>
                        <input v-model="currentShift.hours_worked_regular" id="hours_worked_regular" type="number" step=".01" required />
                    </div>
                </div>

                <div>
                    <div>
                        <label for="hourly_rate_regular">Hourly Rate (regular):</label>
                    </div>
                    <div>
                        <input v-model="currentShift.hourly_rate_regular" id="hourly_rate_regular" type="number" step=".01" required />
                        <BButton variant="warning" @click="currentShift.hourly_rate_regular = currentJob.default_hourly_rate">Reset</BButton>
                    </div>
                </div>

                <div>
                    <BFormCheckbox v-model="hasOvertime">I worked overtime on this shift.</BFormCheckbox>
                </div>

                <div v-show="hasOvertime">
                    <div>
                        <div>
                            <label for="hours_worked_overtime">Hours Worked (overtime):</label>
                        </div>
                        <div>
                            <input v-model="currentShift.hours_worked_overtime" id="hours_worked_overtime" type="number" step=".01" />
                        </div>
                    </div>

                    <div>
                        <div>
                            <label for="hourly_rate_overtime">Hourly Rate (overtime):</label>
                        </div>
                        <div>
                            <input v-model="currentShift.hourly_rate_overtime" id="hourly_rate_overtime" type="number" step=".01" />
                            <BButton variant="warning" @click="currentShift.hourly_rate_overtime = currentJob.default_overtime_rate">Reset</BButton>
                        </div>
                    </div>
                </div>

                <div>
                    <div>
                        <label for="tips_cash">Cash Tips:</label>
                    </div>
                    <div>
                        <input v-model="currentShift.tips_cash" type="number" id="tips_cash" step=".01">
                    </div>
                </div>

                <div>
                    <div>
                        <label for="tips_charge">Charge Tips:</label>
                    </div>
                    <div>
                        <input v-model="currentShift.tips_charge" type="number" id="tips_charge" step=".01">
                    </div>
                </div>

                <BButton variant="success" type="submit">Submit</BButton>
                <BButton variant="danger" @click="router.push('/')">Cancel</BButton>
            </BForm>
        </div>

    </Layout>
</template>

<script setup lang="ts">
import Layout from '../components/Layout.vue';
import { Ref, ref } from 'vue';
import { Job, Shift, User } from '../types';
import router from '../router';
import api from '../api';
import { AxiosResponse } from 'axios';

const user: User = JSON.parse(sessionStorage.getItem('userData'));
let currentJob: Ref<Job> = ref(new Job());
const currentShift: Ref<Shift> = ref(new Shift());
const hasOvertime: Ref<boolean> = ref(false);

function jobUpdated(e: Event) {
    // get job from user job array based on key
    currentJob.value = user.jobs.find(j => parseInt((e.target as HTMLInputElement).value) === j.id)

    // set shift data to equal job defaults
    currentShift.value.job_id = currentJob.value.id
    currentShift.value.hourly_rate_regular = currentJob.value.default_hourly_rate
    currentShift.value.hourly_rate_overtime = currentJob.value.default_overtime_rate
}

async function submit() {
    if (!hasOvertime.value) {
        currentShift.value.hours_worked_overtime = null
        currentShift.value.hourly_rate_overtime = null
    }

    const response:AxiosResponse = await api.shift.post(currentShift.value)
    alert(response.status)
}

</script>