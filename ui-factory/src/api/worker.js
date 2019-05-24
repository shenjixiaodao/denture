import request from '@/utils/request'

export function queryProcedures(pgId) {
  return request({
    url: '/denture/queryProcedures',
    method: 'get',
    params: { pgId: pgId }
  })
}

export function completeProcedure(data) {
  return request({
    url: '/denture/completeProcedure',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function useIngredient(data) {
  return request({
    url: '/ingredient/useIngredient',
    method: 'post',
    data
  })
}

export function addInspectionReport(data) {
  return request({
    url: '/denture/newInspectionReport',
    method: 'post',
    data
  })
}

export function addInspectionItem(data) {
  return request({
    url: '/denture/newInspectionItem',
    method: 'post',
    data
  })
}

