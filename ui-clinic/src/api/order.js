import request from 'src/utils/request'

export function queryOrders() {
  return request({
    url: '/ordering/queryOrders',
    method: 'get'
  })
}

export function queryOrder(id) {
  return request({
    url: '/ordering/queryOrderById',
    method: 'get',
    params: { id: id }
  })
}

export function addOrder(data) {
  return request({
    url: '/ordering/order',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}
