<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-form ref="pwd" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="旧密码" prop="title">
          <el-input v-model="srcPwd" type="password" />
        </el-form-item>
        <el-form-item label="新密码" prop="title">
          <el-input v-model="dstPwd" type="password" />
        </el-form-item>
      </el-form>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button type="primary" @click="modifyPwd">修改</el-button>
    </el-row>
  </div>
</template>

<script>
import { modifyPwd } from '@/api/common'
import { isStringNull } from '@/utils/validate'
import { Message } from 'element-ui'

export default {
  name: 'Setting',
  data() {
    return {
      srcPwd: null,
      dstPwd: null
    }
  },
  methods: {
    modifyPwd() {
      if (isStringNull(this.srcPwd) || isStringNull(this.dstPwd)) {
        return Message({
          message: '新/旧密码都不能为空',
          type: 'error',
          duration: 2 * 1000
        })
      }
      modifyPwd(this.srcPwd, this.dstPwd).then(response => {
        this.$router.push({ path: '/login' })
      })
    }
  }
}
</script>
