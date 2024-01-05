import axiosStatic, { AxiosInstance } from "axios";
import { User, Job, Shift } from "./types";

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

    static auth = {
        async login(email: string, password: string): Promise<void> {
            await api.axios.post('auth/login', {
                email: email,
                password: password
            })
        },

        async logout(): Promise<any> {
            return api.axios.post('auth/logout')
        },

        async register(user:User): Promise<void> {
            await api.axios.post('auth/register', user)
        }
    }
    
    static user = {
        async get(): Promise<User> {
            return (await api.axios.get('user')).data
        }
    }

    static job = {
        async get(): Promise<Job> {
            return (await api.axios.get('job')).data
        }
    }
    
    static shift = {
        async post(shift:Shift): Promise<any> {
            return api.axios.post('shift', shift)
        }
    }

}