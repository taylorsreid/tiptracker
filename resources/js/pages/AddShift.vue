<template>
    <Layout>
        <div class="centerContainer">
            <BForm v-if="user.jobs.length" @submit="submit">
                <div>
                    <div>
                        <label for="job_id">Job:</label>
                    </div>
                    <div>
                        <select v-model="currentShift.job_id" id="job_id" @change="jobUpdated" required>
                            <option v-for="(job) in user?.jobs" :value="job.id">{{ job.title }}</option>
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
                        <input v-model="currentShift.hours_worked_regular" id="hours_worked_regular" type="number" step=".01" min="0" required />
                    </div>
                </div>

                <div>
                    <div>
                        <label for="hourly_rate_regular">Hourly Rate (regular):</label>
                    </div>
                    <div>
                        <input v-model="currentShift.hourly_rate_regular" id="hourly_rate_regular" type="number" step=".01" min="0" required />
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
                            <input v-model="currentShift.hours_worked_overtime" id="hours_worked_overtime" type="number" step=".01" min="0" />
                        </div>
                    </div>

                    <div>
                        <div>
                            <label for="hourly_rate_overtime">Hourly Rate (overtime):</label>
                        </div>
                        <div>
                            <input v-model="currentShift.hourly_rate_overtime" id="hourly_rate_overtime" type="number" step=".01" min="0" />
                            <BButton variant="warning" @click="currentShift.hourly_rate_overtime = currentJob.default_overtime_rate">Reset</BButton>
                        </div>
                    </div>
                </div>

                <div>
                    <div>
                        <label for="tips_cash">Cash Tips:</label>
                    </div>
                    <div>
                        <input v-model="currentShift.tips_cash" type="number" id="tips_cash" step=".01" min="0">
                    </div>
                </div>

                <div>
                    <div>
                        <label for="tips_charge">Charge Tips:</label>
                    </div>
                    <div>
                        <input v-model="currentShift.tips_charge" type="number" id="tips_charge" step=".01" min="0">
                    </div>
                </div>
                <br>
                <BButton variant="success" type="submit">Submit</BButton>
                <BButton variant="danger" @click="router.push('/')">Cancel</BButton>
            </BForm>
            <div v-else>
                You need to add at least one job to your <RouterLink to="/profile">profile</RouterLink> before you can add a shift.
            </div>
        </div>

    </Layout>
</template>

<script setup lang="ts">
import Layout from '../components/Layout.vue';
import { Ref, ref } from 'vue';
import { Job, Shift, User } from '../types';
import router from '../router';
import api from '../api';
import { AxiosResponse, AxiosError } from 'axios';
import { useModalStore } from '../stores';

const modal = useModalStore()
const user: User = JSON.parse(sessionStorage.getItem('userData') ?? '');
let currentJob: Ref<Job> = ref(new Job());
const currentShift: Ref<Shift> = ref(new Shift());
const hasOvertime: Ref<boolean> = ref(false);

function jobUpdated(e: Event) {
    // get job from user job array based on key
    if (user.jobs !== undefined) {
        currentJob.value = user.jobs.find(j => parseInt((e.target as HTMLInputElement).value) === j.id) ?? new Job()
    }
    
    // set shift data to equal job defaults
    currentShift.value.job_id = currentJob.value.id
    currentShift.value.hourly_rate_regular = currentJob.value.default_hourly_rate
    currentShift.value.hourly_rate_overtime = currentJob.value.default_overtime_rate
}

async function submit() {
    if (!hasOvertime.value) {
        delete currentShift.value.hours_worked_overtime
        delete currentShift.value.hourly_rate_overtime
    }

    try {
        const response:AxiosResponse = await api.shift.post(currentShift.value)
        if (response) {
            modal.set('Shift added successfully.', 'green')
            currentShift.value = new Shift() // reset form
        }
        else {
            throw new Error('Unknown Error')
        }
    } catch (error:any) {
        if (error instanceof AxiosError) {
            modal.set(error.response?.data.message)
        }
        else if (error instanceof Error) {
            modal.set(error.message)
        }
        else {
            modal.set(error)
        }
    }
}

</script>