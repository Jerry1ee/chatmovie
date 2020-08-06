import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Register from '../components/Register'
import Home from '../components/Home'
import Welcome from '../components/Welcome'
import Chat from '../components/Chat'
import Video from '../components/Video'
import Myself from '../components/Myself'
import Roomlist from '../components/Roomlist'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  { path: '/register', component: Register },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path: '/welcome', component: Welcome },
      { path: '/chat', component: Chat },
      { path: '/video', component: Video },
      { path: '/myself', component: Myself },
      { path: '/roomlist', component: Roomlist }
    ]
  }

]

// 挂载路由导航守卫
const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  // to 将要访问的路径
  // from 从哪个路径跳转而来
  // next 表示放行 ,next()直接放行,next('/login') 强制跳转
  if (to.path === '/login') return next()
  if (to.path === '/register') return next()
  // 获取token
  const tokenStr = window.sessionStorage.getItem('token')
  if (!tokenStr) return next('/login')
  next()
})

export default router
