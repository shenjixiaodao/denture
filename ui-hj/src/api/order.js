import request from '@/utils/request'

export function addCustomer(data) {
  return request({
    url: '/hj/addCustomer',
    method: 'post',
    data
  })
}

export function queryClinic(id) {
  return request({
    url: '/info/queryClinic',
    method: 'get',
    params: { id: id }
  })
}
