export interface UserData {
    id: number,
    first_name: string,
    last_name: string,
    phone_number?: number,
    email: string,
    created_at: string,
    job_id?: number,
    jobs?: [
        {
            id: number,
            title: string,
            default_hourly_rate?: number,
            default_overtime_rate?: number,
            created_at: string,
            updated_at: string
        }
    ]
}

export class ShiftData {
    date: string = '';
    job_id: number = 0;
    hours_worked_regular?: number;
    hour_worked_overtime?: number;
    hourly_rate_regular?: number;
    hourly_rate_overtime?: number;
    tips_cash?: number;
    tips_charge?: number;
}