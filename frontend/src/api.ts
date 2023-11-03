import axiosStatic, { AxiosInstance, InternalAxiosRequestConfig } from "axios";
import Cookies from "js-cookie";

export default class api {

    protected static axios: AxiosInstance

    static {

        api.axios = axiosStatic.create({
            baseURL: "http://localhost/",
            withCredentials: true,
            headers: {Accept: 'application/json'}
        })

        api.axios.interceptors.request.use((config: InternalAxiosRequestConfig) => {
            config.headers['X-XSRF-TOKEN'] = Cookies.get('XSRF-TOKEN');
            return config;
        });

    }

    static async csrf(): Promise<void> {
        await api.axios.get('sanctum/csrf-cookie')
    }
    
    // https://jakearchibald.com/2017/await-vs-return-vs-return-await/
    static async login(email: string, password: string): Promise<void> {
        await api.axios.post('auth/login', {
            email: email,
            password: password
        })
    }

    static async register(userData:object): Promise<void> {
        await api.axios.post('auth/register', userData)
    }

    static async getUser(): Promise<Object> {
        let response = await api.axios.get('user')
        return response.data
    }

    static async logout(): Promise<void> {
        await api.axios.post('auth/logout')
    }
}