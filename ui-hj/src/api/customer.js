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

export function findPublicCustomers(criteria) {
  return request({
    url: '/hj/findPublicCustomers',
    method: 'post',
    data: criteria
  })
}

export function checkNameCustomer(customer) {
  return request({
    url: '/hj/checkNameCustomer',
    method: 'post',
    data: customer
  })
}
