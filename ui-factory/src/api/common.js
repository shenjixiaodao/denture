import request from '@/utils/request'
import { encryptPwd } from '@/utils/encrypt'

export function queryByDentureId(dentureId) {
  return request({
    url: '/manufacture/queryByDentureId',
    method: 'get',
    params: { dentureId: dentureId }
  })
}

export function modifyPwd(srcPwd, dstPwd) {
  return request({
    url: '/factoryUser/modifyPwd',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data: {
      srcPwd: encryptPwd(srcPwd),
      dstPwd: encryptPwd(dstPwd)
    }
  })
}

export function statTotalIngredient() {
  return request({
    url: '/info/statTotalIngredient',
    method: 'get'
  })
}

export function statIngredient(id) {
  return request({
    url: '/info/statIngredient',
    method: 'get',
    params: { id: id }
  })
}

export function customer(id) {
  return request({
    url: '/factoryUser/customer',
    method: 'get',
    params: { id: id }
  })
}

export function findAppliedUsedIngredient(id) {
  return request({
    url: '/info/findAppliedUsedIngredient',
    method: 'get',
    params: { dentureId: id }
  })
}

export function findSettlementTypes() {
  return request({
    url: '/management/findSettlementTypes',
    method: 'get'
  })
}

export function findCertifications() {
  return request({
    url: '/management/findCertifications',
    method: 'get'
  })
}
