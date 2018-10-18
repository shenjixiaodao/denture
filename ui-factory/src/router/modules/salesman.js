/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout'

const comprehensiveRouter = {
  path: '/salesman',
  component: Layout,
  redirect: '/salesman/order-list',
  name: 'Salesman',
  meta: {
    title: 'Salesman',
    icon: 'dashboard'
  },
  children: [
    {
      path: 'order-list',
      component: () => import('@/views/manufacture/salesman/index'),
      name: 'Order-List',
      meta: { title: 'Order-List', icon: 'dashboard', noCache: true }
    },
    {
      path: 'denture/:id(\\d+)',
      component: () => import('@/views/manufacture/salesman/DentureDetail'),
      name: 'SalesmanDentureDetail',
      meta: { title: 'DentureDetail', noCache: true },
      hidden: true
    },
    {
      path: 'order/:id(\\d+)',
      component: () => import('@/views/manufacture/salesman/OrderDetail'),
      name: 'OrderDetail',
      meta: { title: 'OrderDetail', noCache: true },
      hidden: true
    },
    {
      path: 'add-order',
      component: () => import('@/views/manufacture/salesman/components/AddOrder'),
      name: 'AddOrder',
      meta: { title: '录入订单' },
      hidden: true
    }
  ]
}
export default comprehensiveRouter
