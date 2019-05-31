import Cookies from 'js-cookie'

const UserKey = 'User-Key'

export function getAvatar() {
  const user = getUser()
  console.info(user.avatar)
  return user ? user.avatar : null
}
export function getUser() {
  return Cookies.get(UserKey) ? JSON.parse(Cookies.get(UserKey)) : null
}
export function getToken() {
  const user = getUser()
  return user ? user.token : null
}
export function getName() {
  const user = getUser()
  return user ? user.name : null
}
export function getRoles() {
  const user = getUser()
  return user ? user.roles : null
}

export function setUser(user) {
  return Cookies.set(UserKey, user)
}

export function removeToken() {
  return Cookies.remove(UserKey)
}
