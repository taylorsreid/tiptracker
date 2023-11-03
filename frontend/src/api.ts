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
    
    // https://jakearchibald.com/2017/await-vs-return-vs-return-await/
    static async login(email: string, password: string): Promise<void> {
        await api.axios.get('sanctum/csrf-cookie')
        await api.axios.post('auth/login', {
            email: email,
            password: password
        })
        let response = await api.axios.get('user')
        sessionStorage.setItem('userData', JSON.stringify(response.data));
    }

    static async logout(): Promise<void> {
        api.axios.post('auth/logout')
            .then(() => {
                sessionStorage.clear();
            })
    }
}