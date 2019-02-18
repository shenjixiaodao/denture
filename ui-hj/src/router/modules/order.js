/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout'

const orderRouter = {
  path: '/order',
  component: Layout,
  redirect: '/order/list',
  name: 'Order',
  meta: {
    title: '订单管理',
    icon: 'dashboard'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/manufacture/order/list'),
      name: 'OrderList',
      meta: { title: '订单列表', noCache: true }
    },
    {
      path: 'order/:id(\\d+)',
      component: () => import('@/views/manufacture/order/detail'),
      name: 'OrderDetail',
      meta: { title: '订单详情', noCache: true },
      hidden: true
    },
    {
      path: 'add',
      component: () => import('@/views/manufacture/order/add'),
      name: 'AddOrder',
      meta: { title: '录入订单' },
      hidden: true
    }
  ]
}
export default orderRouter
