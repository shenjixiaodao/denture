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

export function customers() {
  return request({
    url: '/factoryUser/customers',
    method: 'get'
  })
}

export function addCustomer(data) {
  return request({
    url: '/factoryUser/addCustomer',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function queryClinics() {
  return request({
    url: '/info/queryClinics',
    method: 'get'
  })
}
