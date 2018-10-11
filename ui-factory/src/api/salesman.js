import request from '@/utils/request'

export function queryOrders() {
  return request({
    url: '/manufacture/salesmanQueryOrders',
    method: 'get'
  })
}

export function queryOrder(dentureId) {
  return request({
    url: '/manufacture/salesmanQueryOrder',
    method: 'get',
    params: { dentureId: dentureId }
  })
}
