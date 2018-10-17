import request from '@/utils/request'

export function queryDenturesByStatus(status) {
  return request({
    url: '/manufacture/queryDenturesByStatus',
    method: 'get',
    params: { status: status }
  })
}

export function queryByDeliveryId(params) {
  return request({
    url: '/manufacture/queryByDeliveryId',
    method: 'get',
    params: params
  })
}
