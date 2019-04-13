// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router' // 默认导入该文件夹下的index.js模块
// import Mock from './mock/index' // 引入mock，模拟数据
import api from './http'
import axios from 'axios'
import global from './utils/global.js'
// import Mock from './mock/index.js'
import qs from 'qs'
import ElementUI from 'element-ui';
import store from "./store";
import 'element-ui/lib/theme-chalk/index.css';
import './assets/css/elscrollbar.css';

// Mock.mockData();

Vue.config.productionTip = true;
Vue.use(ElementUI);
Vue.use(api);
Vue.prototype.$qs = qs
Vue.prototype.$global = global
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  data: {
    eventHub: new Vue() // 创建一个vue实例，用于事件的广播和接收
  },
  template: '<App/>'
})
