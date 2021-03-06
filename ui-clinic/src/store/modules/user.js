import { setToken } from 'src/utils/auth'
import { loginByUsername, register } from 'src/api/user'

const user = {
  state: {
    code: '',
    token: '',
    clinic: null,
    user: null
  },

  mutations: {
    SET_CODE: (state, code) => {
      state.code = code
    },
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_USER: (state, user) => {
      state.user = user
    },
    SET_CLINIC: (state, clinic) => {
      state.clinic = clinic
    }
  },

  actions: {
    // 用户名登录
    LoginByUsername({ commit }, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        loginByUsername(username, userInfo.password).then(response => {
          const data = response.data
          console.log(response)
          commit('SET_TOKEN', data.token)
          setToken(data.token)
          commit('SET_USER', data)
          commit("SET_CLINIC", data.clinic)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 用户名注册
    Register({ commit }, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        register(userInfo.username, userInfo.name, userInfo.password, userInfo.clinic, userInfo.role,
          userInfo.code).then(response => {
          const data = response.data
          console.log(response)
          commit('SET_TOKEN', data.token)
          setToken(data.token)
          commit('SET_USER', data)
          commit("SET_CLINIC", data.clinic)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default user
