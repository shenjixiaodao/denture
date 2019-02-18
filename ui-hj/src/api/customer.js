import request from '@/utils/request'

export function storeCustomer(data) {
  return request({
    url: '/hj/storeCustomer',
    method: 'post',
    data
  })
}

export function modifyCustomer(data) {
  return request({
    url: '/hj/modifyCustomer',
    method: 'post',
    data
  })
}

export function findCustomers() {
  return request({
    url: '/hj/findCustomers',
    method: 'get'
  })
}

export function findCustomer(id) {
  return request({
    url: '/hj/findCustomer',
    method: 'get',
    params: { id: id }
  })
}
