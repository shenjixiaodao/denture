import request from 'src/utils/request'
import { encryptPwd } from 'src/utils/encrypt'

export function loginByUsername(username, password) {
  const data = {
    phone: username,
    password: encryptPwd(password)
  }
  return request({
    url: '/user/login',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function register(username, name, password, clinic, role, code) {
  const data = {
    phone: username,
    name: name,
    password: encryptPwd(password),
    clinicId: clinic.id,
    clinicName: clinic.name,
    clinicAddress: clinic.address,
    role: role,
    code: code
  }
  return request({
    url: '/user/register',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function applicants() {
  return request({
    url: '/user/applicants',
    method: 'get'
  })
}
