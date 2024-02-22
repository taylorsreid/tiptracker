import { defineStore } from "pinia";
import { User, LoginOrRegistrationData } from "./types";
import Cookies from "js-cookie";
import api from "./api";

export const useModalStore = defineStore('modal', {
    state: () => ({
        message: '',
        color: 'red',
        visible: false
    }),
    actions: {
        set(message:string, color:string = 'red', visible:boolean = true): void {
            this.message = message
            this.color = color
            this.visible = visible
        },
        hide(): void {
            this.visible = false
            this.color = 'red'
            this.message = ''
        }
    },
    persist: false
})

export const useAuthStore = defineStore('auth', {
    state: () => ({
        user: {} as User
    }),
    getters: {
        isLoggedIn(): boolean {
            try {
                return Object.keys(this.user).length > 0 && (Cookies.get('XSRF-TOKEN') !== undefined)
            } catch (error) {
                return false
            }
        }
    },
    actions: {
        setCurrentUser(user:User): void {
            this.user = user
        },
        async login(loginData:LoginOrRegistrationData): Promise<[boolean, string]> {
            if (loginData.email !== '' && loginData.password !== '') {
                try {
                    await api.post('auth/login', {
                        email: loginData.email,
                        password: loginData.password
                    })
                    this.setCurrentUser((await api.get('user')).data)
                    return [true, '']
                } catch (error:any) {
                    return [false, error.response.data.message]
                }
            }
            else {
                return [false, 'Email and password are required.']
            }
        },
        async register(registrationData:LoginOrRegistrationData): Promise<[boolean, string]> {
            try {
                await api.post('auth/register', registrationData)
                this.setCurrentUser((await api.get('user')).data)
                return [true, '']
            } catch (error:any) {
                return [false, error.response.data.message]
            }
        },
        logout(): void {
            api.post('auth/logout')
            this.user = {} as User
            localStorage.clear()
        },
        async updateUser(): Promise<void> {

        }
    },
    persist: true
})