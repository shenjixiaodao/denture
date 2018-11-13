import request from '@/utils/request'

export function queryDenturesByCriteria(criteria) {
  return request({
    url: '/manufacture/queryDenturesByCriteria',
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

// ingredient
export function newIngredient(ingredientName) {
  return request({
    url: '/manufacture/newIngredient',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data: { name: ingredientName }
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
