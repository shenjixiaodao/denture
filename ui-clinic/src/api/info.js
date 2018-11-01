import request from 'src/utils/request'

export function queryFactories(type) {
  return request({
    url: '/info/queryFactories',
    method: 'get',
    params: { type: type }
  })
}

export function statistic() {
  return request({
    url: '/info/statistic',
    method: 'get'
  })
}
