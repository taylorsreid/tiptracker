import axiosStatic, { AxiosInstance } from "axios";
import { ShiftData, UserData } from "./types";

export default class api {

    private static axios: AxiosInstance

    static {

        api.axios = axiosStatic.create({
            baseURL: "http://localhost/",
            withCredentials: true,
            headers: {Accept: 'application/json'}
        })

    }
    
    // https://jakearchibald.com/2017/await-vs-return-vs-return-await/
    static async login(email: string, password: string): Promise<void> {
        await api.axios.post('auth/login', {
            email: email,
            password: password
        })
    }

    static async register(userData:UserData): Promise<void> {
        await api.axios.post('auth/register', userData)
    }

    static async getUser(): Promise<UserData> {
        return (await api.axios.get('user')).data
    }

    static async logout(): Promise<void> {
        await api.axios.post('auth/logout')
    }

    static async postShift(formData:ShiftData): Promise<void> {
        await api.axios.post('shift', formData)
    }
}