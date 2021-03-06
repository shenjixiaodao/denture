import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/views/layout/Layout'

/* Router Modules */
import comprehensiveRouter from './modules/comprehensive'
import salesmanRouter from './modules/salesman'

/** note: Submenu only appear when children.length>=1
 *  detail see  https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 **/

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    roles: ['admin','editor']     will control the page roles (you can set multiple roles)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
**/
export const constantRouterMap = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    hidden: true,
    redirect: 'welcome',
    children: [
      {
        path: 'welcome',
        component: () => import('@/views/welcome/index'),
        name: 'Welcome',
        meta: { title: '首页', icon: 'dashboard', noCache: true }
      }
    ]
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/authredirect'),
    hidden: true
  }
]

export default new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  salesmanRouter,
  comprehensiveRouter,
  {
    path: '/worker',
    hidden: true,
    component: Layout,
    children: [
      {
        path: 'complete-procedure',
        component: () => import('@/views/manufacture/worker/CompleteProcedure'),
        name: 'Complete-Procedure',
        meta: { title: '生产管理', icon: 'international' }
      }
    ]
  },

  {
    path: '/setting',
    component: Layout,
    redirect: '/setting/modify-pwd',
    children: [
      {
        path: 'modify-pwd',
        component: () => import('@/views/setting/index'),
        name: 'ModifyPwd',
        meta: { title: '设置', icon: 'user', noCache: true }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]
