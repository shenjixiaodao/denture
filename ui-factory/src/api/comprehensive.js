import request from '@/utils/request'

export function queryDenturesByCriteria(criteria) {
  return request({
    url: '/manufacture/queryDenturesByCriteria',
    method: 'post',
    data: criteria
  })
}

export function queryDentures(criteria) {
  return request({
    url: '/manufacture/queryDentures',
    method: 'post',
    data: criteria
  })
}

export function queryByDeliveryId(params) {
  return request({
    url: '/manufacture/queryByDeliveryId',
    method: 'get',
    params: params
  })
}

export function users() {
  return request({
    url: '/factoryUser/users',
    method: 'get'
  })
}

export function user(uid) {
  return request({
    url: '/factoryUser/user',
    method: 'get',
    params: { uid: uid }
  })
}

export function review(data) {
  return request({
    url: '/manufacture/review',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function addUser(data) {
  return request({
    url: '/factoryUser/add',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function modifyUser(data) {
  return request({
    url: '/factoryUser/modify',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

// ingredient
export function newIngredient(data) {
  return request({
    url: '/manufacture/newIngredient',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function addIngredient(data) {
  return request({
    url: '/manufacture/recordIngredientPurchase',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function applyIngredient(data) {
  return request({
    url: '/manufacture/applyIngredient',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function queryIngredients() {
  return request({
    url: '/manufacture/queryIngredients',
    method: 'get'
  })
}

export function queryIngredientById(id) {
  return request({
    url: '/manufacture/queryIngredientById',
    method: 'get',
    params: { id: id }
  })
}

export function newSupplier(data) {
  return request({
    url: '/manufacture/newSupplier',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function querySuppliers() {
  return request({
    url: '/manufacture/querySuppliers',
    method: 'get'
  })
}

export function customers(criteria) {
  return request({
    url: '/manufacture/customers',
    method: 'post',
    data: criteria
  })
}

export function aggregateOrders(data) {
  return request({
    url: '/aggregate/aggregateOrders',
    method: 'post',
    data
  })
}

export function aggregateAppliedUsedIngredient(data) {
  return request({
    url: '/aggregate/aggregateAppliedUsedIngredient',
    method: 'post',
    data
  })
}

export function findDenturesByCustomer(data) {
  return request({
    url: '/info/findDenturesByCustomer',
    method: 'post',
    data
  })
}

export function findProductTypes() {
  return request({
    url: '/info/findProductTypes',
    method: 'get'
  })
}

export function addProductType(data) {
  return request({
    url: '/manufacture/addProductType',
    method: 'post',
    data
  })
}

export function deleteProductType(data) {
  return request({
    url: '/manufacture/deleteProductType',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function salaryList(data) {
  return request({
    url: '/aggregate/salaryList',
    method: 'post',
    data
  })
}

export function addPriceSheet(data) {
  return request({
    url: '/manufacture/addPriceSheet',
    method: 'post',
    data
  })
}

export function delivery(data) {
  return request({
    url: '/manufacture/delivery',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function modifyCustomer(data) {
  return request({
    url: '/manufacture/modifyCustomer',
    method: 'post',
    data
  })
}

export function addSettlement(data) {
  return request({
    url: '/management/addSettlement',
    method: 'post',
    data
  })
}

export function addCertification(data) {
  return request({
    url: '/management/addCertification',
    method: 'post',
    data
  })
}

export function productTypeStatistic(data) {
  return request({
    url: '/info/productTypeStatistic',
    method: 'post',
    data
  })
}

export function modifyFactory(data) {
  return request({
    url: '/management/modifyFactory',
    method: 'post',
    data
  })
}

export function findFactory() {
  return request({
    url: '/management/findFactory',
    method: 'get'
  })
}
