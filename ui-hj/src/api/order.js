import request from '@/utils/request'

export function newOrder(data) {
  return request({
    url: '/hj/newOrder',
    method: 'post',
    data
  })
}

export function addOrderDetail(data) {
  return request({
    url: '/hj/addOrderDetail',
    method: 'post',
    data
  })
}

export function findOrders() {
  return request({
    url: '/hj/findOrders',
    method: 'get'
  })
}

export function findOrderDetail(id) {
  return request({
    url: '/hj/findOrderDetail',
    method: 'get',
    params: { id: id }
  })
}
