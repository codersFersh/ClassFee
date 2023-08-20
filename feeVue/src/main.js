import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import axios from 'axios'
import echarts from 'echarts'
import qs from 'qs'
import VueAxios from 'vue-axios'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)
    // Vue.Config.config.productionTip = false

Vue.use(VueAxios, axios)

Vue.prototype.$echarts = echarts

Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')