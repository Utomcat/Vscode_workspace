// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import '../node_modules/element-ui/lib/theme-chalk/index.css'
import VueRouter from '../node_modules/vue-router'
import routerConfig from './router-config'
import store from './store.js'

Vue.use(ElementUI)
Vue.use(VueRouter)

const router = new VueRouter({routes: routerConfig})

/* eslint-disable no-new */
new Vue({
  router,
  store,
  el: '#app',
  render: h => h(App)
})
