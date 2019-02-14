import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import * as QS from 'qs'
import router from '@/router'

// create an axios instance
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  withCredentials: true
})

// request interceptor
service.interceptors.request.use(
  config => {
    // Do something before request is sent
    if (store.getters.token) {
      config.headers['X-Token'] = getToken()
    }
    if (config.headers['Content-Type'] === 'application/x-www-form-urlencoded') {
      config.data = QS.stringify(config.data)
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code === '1' || res.code === '3' || res.code === '4') {
      Message({
        message: '请求错误: ' + res.message,
        type: 'error',
        duration: 2 * 1000
      })
      return Promise.reject('error')
    }
    if (res.code === '2') {
      store.dispatch('LogOut').then(() => {
        Message({
          message: '登录过期，请重新登录',
          type: 'error',
          duration: 3 * 1000
        })
        console.log(window.location.hash)
        router.push(`/login?redirect=` + window.location.hash.substring(1))
      })
      return
    }
    return res
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
