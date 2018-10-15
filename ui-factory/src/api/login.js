import request from '@/utils/request'
import { JSEncrypt } from 'jsencrypt'

const PublicKey = 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtJ9qG8bnsWKCpeGHZwjey74C93KAHMMHF6MwJdv2cv0GXbRjLq212ZbnA5sgqBb85lZy+5OokHVj96YEEPIInIw9Kk5qwwzX4j5wZcQEKelnt+/wKEQwg1FbJFjUUp6FCdYCVme9RUfHo1G7sNTq9MfwVTHqK7J49fQ1FTJEq9/DXi0ajdHCRduOYtNUrfbYAG7p8ngTJnbBfdpfSAcT2tNNbKQy27cjYcEspUn8j/WKtE8ALwebluu1En5VQA69C4cQvhVaRDjbyObnaW/8eWb37A+jhv7wEDnAzsiFXM1c1SmfRc7eoHHPNAKBnhw1kt0hLNMV2LYuCgkgRm/V3QIDAQAB'
const encryptor = new JSEncrypt()
encryptor.setPublicKey(PublicKey)

export function loginByUsername(username, password) {
  const data = {
    phone: username,
    password: encryptor.encrypt(password)
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
/*
export function loginByUsername(username, password) {
  const data = {
    username: username,
    password: encryptor.encrypt(password)
  }
  return request({
    url: '/login/login',
    method: 'post',
    data
  })
}*/

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

