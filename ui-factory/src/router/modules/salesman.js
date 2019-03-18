/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout'

const salesmanRouter = {
  path: '/salesman',
  component: Layout,
  hidden: true,
  redirect: '/salesman/order-list',
  name: 'Salesman',
  meta: {
    title: '业务人员管理',
    icon: 'dashboard'
  },
  children: [
    {
      path: 'order-list',
      component: () => import('@/views/manufacture/salesman/index'),
      name: 'Order-List',
      meta: { title: '订单列表', noCache: true }
    },
    {
      path: 'denture/:id(\\d+)',
      component: () => import('@/views/manufacture/salesman/DentureDetail'),
      name: 'SalesmanDentureDetail',
      meta: { title: '义齿详情', noCache: true },
      hidden: true
    },
    {
      path: 'order/:id(\\d+)',
      component: () => import('@/views/manufacture/salesman/OrderDetail'),
      name: 'OrderDetail',
      meta: { title: '订单详情', noCache: true },
      hidden: true
    },
    {
      path: 'add-order',
      component: () => import('@/views/manufacture/salesman/components/AddOrder'),
      name: 'AddOrder',
      meta: { title: '录入订单' },
      hidden: true
    },
    {
      path: 'customers',
      component: () => import('@/views/manufacture/salesman/customers'),
      name: 'Customers',
      meta: { title: '客户列表', noCache: true }
    },
    {
      path: 'customer/:id(\\d+)',
      component: () => import('@/views/manufacture/salesman/components/CustomerDetail'),
      name: 'CustomerDetail',
      meta: { title: '客户详情' },
      hidden: true
    }
  ]
}
export default salesmanRouter
