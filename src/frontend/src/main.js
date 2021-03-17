import Vue from 'vue'
import App from './App.vue'
import { router } from './router/router'
import store from "./store"
import vuetify from './plugins/vuetify';
import VueConfirmDialog from 'vue-confirm-dialog'

Vue.config.productionTip = false
Vue.prototype.$EventBus = new Vue();
Vue.use(VueConfirmDialog)
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
