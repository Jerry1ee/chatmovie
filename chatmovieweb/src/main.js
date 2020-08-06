import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import axios from 'axios'
import Video from 'video.js'
import 'video.js/dist/video-js.css'
// 导入全局样式表
import './assets/css/global.css'
// import uploader from 'vue-simple-uploader'
// //视频上传组件
// Vue.use(uploader)

Vue.prototype.$http = axios
Vue.prototype.$video = Video

// 默认请求路径
axios.defaults.baseURL = 'http://172.19.240.176:8080'
// axios.defaults.baseURL = 'http://localhost:8080'

// 为axios请求带上token
axios.interceptors.request.use(config => {
  console.log(config)
  config.headers.Authorization = window.sessionStorage.getItem('token')
  return config
})
// 修改axios 的content type
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
