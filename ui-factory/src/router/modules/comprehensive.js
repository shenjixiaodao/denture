/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout'

const comprehensiveRouter = {
  path: '/comprehensive',
  component: Layout,
  redirect: '/comprehensive/dentures',
  name: 'Comprehensive',
  meta: {
    title: '综合管理',
    icon: 'table'
  },
  children: [
    {
      path: 'denture/status',
      hidden: true,
      component: () => import('@/views/manufacture/comprehensive/wait'),
      name: 'Dentures',
      meta: { title: '义齿管理' },
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
        }
      ]
    },
    /* {
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
    },*/
    {
      path: 'dentures',
      component: () => import('@/views/manufacture/comprehensive/dentures'),
      name: 'Dentures',
      meta: { title: '义齿列印', noCache: true }
    },
    {
      path: 'denture/add',
      component: () => import('@/views/manufacture/comprehensive/components/AddOrder'),
      name: 'AddOrder',
      meta: { title: '义齿建档', noCache: true }
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
      meta: { title: '员工列印' }
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
      meta: { title: '物料明细列印' }
    },
    {
      path: 'ingredient/:id(\\d+)',
      component: () => import('@/views/manufacture/comprehensive/components/IngredientDetail'),
      name: 'ComprehensiveIngredientDetail',
      meta: { title: '物料详情', noCache: true },
      hidden: true
    },
    {
      path: 'customers',
      component: () => import('@/views/manufacture/comprehensive/customers'),
      name: 'Customers',
      meta: { title: '客户档案', noCache: true }
    },
    {
      path: 'customer/:id(\\d+)',
      component: () => import('@/views/manufacture/comprehensive/components/CustomerDetail'),
      name: 'CustomerDetail',
      meta: { title: '客户详情' },
      hidden: true
    },
    {
      path: 'dashboard',
      component: () => import('@/views/manufacture/comprehensive/dashboard'),
      name: 'Dashboard',
      meta: { title: '统计表列印', noCache: true }
    },
    {
      path: 'procedure/:id(\\d+)',
      component: () => import('@/views/manufacture/comprehensive/components/ProcedureDetail'),
      name: 'ProcedureDetail',
      meta: { title: '工序详情' },
      hidden: true
    },
    {
      path: 'products',
      component: () => import('@/views/manufacture/comprehensive/products'),
      name: 'Products',
      meta: { title: '品类档案', noCache: true }
    }
  ]
}
export default comprehensiveRouter
