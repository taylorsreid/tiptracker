export class Job {
    id: number | undefined
    title: string | undefined
    default_hourly_rate?: number
    default_overtime_rate?: number
    created_at: string | undefined
    updated_at: string | undefined
}

export interface User {
    id: number,
    first_name: string,
    last_name: string,
    phone_number?: number,
    email: string,
    created_at: string,
    default_job_id?: number,
    jobs: [Job]
}

export class Shift {
    date: string | undefined
    job_id: number | undefined
    hours_worked_regular?: number
    hours_worked_overtime?: number
    hourly_rate_regular?: number
    hourly_rate_overtime?: number
    tips_cash?: number
    tips_charge?: number
}