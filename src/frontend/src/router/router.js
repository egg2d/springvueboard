import Vue from 'vue'
import Router from 'vue-router'

const Login = () => import("@/components/login/Login")
const Join = () => import("@/components/join/Join")
Vue.use(Router)

export const router = new Router({
    mode: 'history',
    routes: [
        {
            path:'/login',
            name: 'Login',
            component: Login
        },
        {
            path:'/join',
            name: 'Join',
            component: Join

        }
    ]

})