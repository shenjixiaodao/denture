/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout'

const comprehensiveRouter = {
  path: '/comprehensive',
  component: Layout,
  redirect: '/comprehensive/wait',
  name: 'Comprehensive',
  meta: {
    title: '综合人员管理',
    icon: 'table'
  },
  children: [
    {
      path: 'wait',
      component: () => import('@/views/manufacture/comprehensive/wait'),
      name: 'Wait',
      meta: { title: '待审核义齿' }
    },
    {
      path: 'doing',
      component: () => import('@/views/manufacture/comprehensive/doing'),
      name: 'Doing',
      meta: { title: '在加工义齿' }
    },
    {
      path: 'done',
      component: () => import('@/views/manufacture/comprehensive/done'),
      name: 'Done',
      meta: { title: '已完成义齿' }
    },
    {
      path: 'denture/:id(\\d+)',
      component: () => import('@/views/manufacture/comprehensive/components/DentureDetail'),
      name: 'ComprehensiveDentureDetail',
      meta: { title: '义齿详情', noCache: true },
      hidden: true
    },
    {
      path: 'users',
      component: () => import('@/views/manufacture/comprehensive/users'),
      name: 'User',
      meta: { title: '用户管理' }
    },
    {
      path: 'user/:id(\\d+)',
      component: () => import('@/views/manufacture/comprehensive/components/UserDetail'),
      name: 'ComprehensiveUserDetail',
      meta: { title: '用户详情', noCache: true },
      hidden: true
    },
    {
      path: 'ingredients',
      component: () => import('@/views/manufacture/comprehensive/ingredients'),
      name: 'Ingredients',
      meta: { title: '物料管理' }
    },
    {
      path: 'ingredient/:id(\\d+)',
      component: () => import('@/views/manufacture/comprehensive/components/IngredientDetail'),
      name: 'ComprehensiveIngredientDetail',
      meta: { title: '物料详情', noCache: true },
      hidden: true
    }
  ]
}
export default comprehensiveRouter
