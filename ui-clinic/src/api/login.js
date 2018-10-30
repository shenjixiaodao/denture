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

