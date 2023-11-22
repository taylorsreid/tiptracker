export default interface UserData {
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