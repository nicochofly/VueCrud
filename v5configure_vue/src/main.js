import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router/index'
import store from './store'
Vue.config.productionTip = false
import moment from 'moment'
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8443/api'
Vue.prototype.$axios = axios
Vue.prototype.$moment = moment
Vue.use(ElementUI)
router.beforeEach((to, from, next) => {
        if (to.meta.requireAuth) {
            if (store.state.user.username) {
                next()
            } else {
                next({
                    path: 'login',
                    query: {redirect: to.fullPath}
                })
            }
        } else {
            next()
        }
    }
)
new Vue({
  el: '#app',
  render: h => h(App),
  router,
    store,
  components: { App },
  template: '<App/>'
}).$mount('#app');




