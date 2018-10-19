/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout'

const comprehensiveRouter = {
  path: '/comprehensive',
  component: Layout,
  redirect: '/comprehensive/wait',
  name: 'Comprehensive',
  meta: {
    title: 'Comprehensive',
    icon: 'table'
  },
  children: [
    {
      path: 'wait',
      component: () => import('@/views/manufacture/comprehensive/wait'),
      name: 'Wait',
      meta: { title: 'Wait' }
    },
    {
      path: 'doing',
      component: () => import('@/views/manufacture/comprehensive/doing'),
      name: 'Doing',
      meta: { title: 'Doing' }
    },
    {
      path: 'done',
      component: () => import('@/views/manufacture/comprehensive/done'),
      name: 'Done',
      meta: { title: 'Done' }
    },
    {
      path: 'denture/:id(\\d+)',
      component: () => import('@/views/manufacture/comprehensive/components/DentureDetail'),
      name: 'ComprehensiveDentureDetail',
      meta: { title: 'DentureDetail', noCache: true },
      hidden: true
    },
    {
      path: 'users',
      component: () => import('@/views/manufacture/comprehensive/user'),
      name: 'User',
      meta: { title: '用户管理' }
    },
    {
      path: 'user/:id(\\d+)',
      component: () => import('@/views/manufacture/comprehensive/components/UserDetail'),
      name: 'ComprehensiveUserDetail',
      meta: { title: '用户详情', noCache: true },
      hidden: true
    }
  ]
}
export default comprehensiveRouter