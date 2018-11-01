import App from '../App'

const orders = r => require.ensure([], () => r(require('../page/order/orders')), 'orders')
const orderDetail = r => require.ensure([], () => r(require('../page/order/component/orderDetail')), 'orderDetail')
const addOrder = r => require.ensure([], () => r(require('../page/order/component/addOrder')), 'addOrder')
const login = r => require.ensure([], () => r(require('../page/login/login')), 'login')
const profile = r => require.ensure([], () => r(require('../page/profile/profile')), 'profile')
const register = r => require.ensure([], () => r(require('../page/profile/register')), 'register')
const forget = r => require.ensure([], () => r(require('../page/forget/forget')), 'forget')
const info = r => require.ensure([], () => r(require('../page/profile/children/info')), 'info')
const setusername = r => require.ensure([], () => r(require('../page/profile/children/children/setusername')), 'setusername')
const address = r => require.ensure([], () => r(require('../page/profile/children/children/address')), 'address')
const add = r => require.ensure([], () => r(require('../page/profile/children/children/children/add')), 'add')
const addDetail = r => require.ensure([], () => r(require('../page/profile/children/children/children/children/addDetail')), 'addDetail')
const balance = r => require.ensure([], () => r(require('../page/balance/balance')), 'balance')
const balanceDetail = r => require.ensure([], () => r(require('../page/balance/children/detail')), 'balanceDetail')
const partners = r => require.ensure([], () => r(require('../page/factory/partners')), 'partners')
const points = r => require.ensure([], () => r(require('../page/points/points')), 'points')
const pointsDetail = r => require.ensure([], () => r(require('../page/points/children/detail')), 'pointsDetail')
const service = r => require.ensure([], () => r(require('../page/service/service')), 'service')
const questionDetail = r => require.ensure([], () => r(require('../page/service/children/questionDetail')), 'questionDetail')
const find = r => require.ensure([], () => r(require('../page/find/find')), 'find')




export default [{
    path: '/',
    component: App, //顶层路由，对应index.html
    children: [ //二级路由。对应App.vue
      //地址为空时跳转home页面
      {
          path: '',
          redirect: '/orders'
      },
      //订单列表页
      {
          path: '/orders',
          component: orders,
          children: [
            {
              path: 'orderDetail/:id(\\d+)', //订单详情页
              component: orderDetail,
            },
            {
              path: 'addOrder', //下单
              component: addOrder,
            }
          ]
      },
      //登录注册页
      {
          path: '/login',
          component: login
      },
      //个人信息页
      {
          path: '/profile',
          component: profile,
          children: [
            {
              path: 'info', //个人信息详情页
              component: info,
              children: [
                {
                  path: 'setusername',
                  component: setusername,
                },
                {
                    path: 'address',
                    component: address,     //编辑地址
                    children:[{
                        path:'add',
                        component:add,
                        children:[{
                            path:'addDetail',
                            component:addDetail
                        }]
                    }]
                }
              ]
            },
            {
              path: 'service', //服务中心
              component: service,
            }
          ]
      },
      {
        path: 'register', //注册
        component: register,
      },
      //修改密码页
      {
          path: '/forget',
          component: forget
      },
      //发现页
      {
          path: '/find',
          component: find
      },
      //服务中心
      {
          path: '/service',
          component: service,
           children: [{
              path: 'questionDetail',
              component: questionDetail,
          }, ]
      },
      //余额
      {
          path: 'balance',
          component: balance,
          children: [{
              path: 'detail', //余额说明
              component: balanceDetail,
          }, ]
      },
      //我的工厂列
      {
          path: 'partners',
          component: partners
      },
      //我的积分页
      {
          path: 'points',
          component: points,
          children: [{
              path: 'detail', //积分说明
              component: pointsDetail,
          }, ]
      },
    ]
}]
