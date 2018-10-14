import request from '@/utils/request'

export function queryWaitingDentures() {
  return request({
    url: '/manufacture/queryWaitingDentures',
    method: 'get'
  })
}
