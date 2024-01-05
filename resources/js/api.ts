import axiosStatic, { AxiosInstance } from "axios";
import { Shift, User } from "./types";

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

    static async register(user:User): Promise<void> {
        await api.axios.post('auth/register', user)
    }

    static async getUser(): Promise<User> {
        return (await api.axios.get('user')).data
    }

    static async logout(): Promise<any> {
        return api.axios.post('auth/logout')
    }

    static shift = {
        async post(shift:Shift): Promise<any> {
            return api.axios.post('shift', shift)
        }
    }

    // static async postShift(formData:Shift): Promise<void> {
    //     return api.axios.post('shift', formData)
    // }

}