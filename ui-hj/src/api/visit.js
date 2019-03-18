import request from '@/utils/request'

export function newVisit(data) {
  return request({
    url: '/hj/storeVisit',
    method: 'post',
    data
  })
}
