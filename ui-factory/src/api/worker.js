import request from '@/utils/request'

export function queryProcedures(pgId) {
  return request({
    url: '/manufacture/queryProcedures',
    method: 'get',
    params: { pgId: pgId }
  })
}

export function completeProcedure(data) {
  return request({
    url: '/manufacture/completeProcedure',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function useIngredient(data) {
  return request({
    url: '/manufacture/useIngredient',
    method: 'post',
    data
  })
}
