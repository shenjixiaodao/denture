import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'
const NameKey = 'Admin-Name'

export function getToken() {
  return Cookies.get(TokenKey)
}
export function getName() {
  return Cookies.get(NameKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}
export function setName(name) {
  return Cookies.set(NameKey, name)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
