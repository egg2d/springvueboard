

// Vuex 기본 Setup
import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex)

export default new Vuex.Store({

    state: {
        login : false
    },
    getters: {
        loginStatus(state) {
            return state.login;
        }
    },
    mutations : {
        LOGIN(state) {
            //store에 토큰 저장
            state.login = true
        },
        LOGOUT(state) {
            state.login = false;
        }
    }

});




