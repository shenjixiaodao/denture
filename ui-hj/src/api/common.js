import request from '@/utils/request'
import { encryptPwd } from '@/utils/encrypt'

export function loginByUsername(username, password) {
  const data = {
    phone: username,
    password: encryptPwd(password)
  }
  return request({
    url: '/factoryUser/login',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function logout() {
  return request({
    url: '/login/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
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

