import request from '@/utils/request'

export function storeCustomer(data) {
  return request({
    url: '/hj/storeCustomer',
    method: 'post',
    data
  })
}
