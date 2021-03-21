import Vue from 'vue'
import App from './App.vue'
import { router } from './router/router'
import store from "./store/index"
import vuetify from './plugins/vuetify';
import VueConfirmDialog from 'vue-confirm-dialog'
import VueSession from "vue-session";

var sessionOptions = {
  persist: true
}
Vue.config.productionTip = false
Vue.prototype.$EventBus = new Vue();


Vue.use(VueConfirmDialog)
Vue.use(VueSession, sessionOptions);
Vue.component('vue-confirm-dialog', VueConfirmDialog.default)
new Vue({
  vuetify,
  render: h => h(App),
  router,
  store,
  component: {
    App
  }
}).$mount('#app')
