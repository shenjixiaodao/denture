/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout'

const customerRouter = {
  path: '/customer',
  component: Layout,
  redirect: '/customer/list',
  name: 'Customer',
  meta: {
    title: '客户管理',
    icon: 'table'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/manufacture/customer/list'),
      name: 'CustomerList',
      meta: { title: '客户列表', noCache: true }
    },
    {
      path: 'customer/:id(\\d+)',
      component: () => import('@/views/manufacture/customer/detail'),
      name: 'CustomerDetail',
      meta: { title: '客户详情' },
      hidden: true
    },
    {
      path: 'add',
      component: () => import('@/views/manufacture/customer/add'),
      name: 'AddOCustomer',
      meta: { title: '新增客户' },
      hidden: true
    }
  ]
}
export default customerRouter
