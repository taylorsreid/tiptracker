import { defineStore } from "pinia";

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
    }
})