import request from '@/utils/request'

export function queryOrders() {
  return request({
    url: '/manufacture/salesmanQueryOrders',
    method: 'get'
  })
}

export function queryOrderByDentureId(dentureId) {
  return request({
    url: '/manufacture/queryOrderByDentureId',
    method: 'get',
    params: { dentureId: dentureId }
  })
}
