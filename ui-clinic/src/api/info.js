import request from 'src/utils/request'

export function queryFactories() {
  return request({
    url: '/info/queryFactories',
    method: 'get'
  })
}

export function statistic() {
  return request({
    url: '/info/statistic',
    method: 'get'
  })
}
