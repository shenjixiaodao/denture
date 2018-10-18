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

export function addOrder(data) {
  return request({
    url: '/manufacture/recordOrder',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}
