<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <pan-thumb :image="avatar"/>

      <el-button type="primary" icon="upload" style="position: absolute;bottom: 15px;margin-left: 40px;" @click="imagecropperShow=true">更换头像</el-button>
      <image-cropper
        v-show="imagecropperShow"
        :width="300"
        :height="300"
        :key="imagecropperKey"
        :url="uploadAvatar"
        lang-type="zh"
        @close="close"
        @crop-upload-success="cropSuccess"/>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-form ref="pwd" label-position="left" label-width="70px" style="width: 400px;">
        <el-form-item label="旧密码" prop="title">
          <!--<el-input v-model="srcPwd" type="password" />-->
          <el-input v-model="srcPwd" style="width: 70%;" class="filter-item" />
        </el-form-item>
        <el-form-item label="新密码" prop="title">
          <!--<el-input v-model="dstPwd" type="password" />-->
          <el-input v-model="dstPwd" style="width: 70%;" class="filter-item" />
        </el-form-item>
      </el-form>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button type="primary" @click="modifyPwd">修改</el-button>
    </el-row>
  </div>
</template>

<script>
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'
import { modifyPwd } from '@/api/common'
import { isStringNull } from '@/utils/validate'
import { Message } from 'element-ui'
import store from '@/store'

export default {
  name: 'Setting',
  components: { ImageCropper, PanThumb },
  data() {
    return {
      srcPwd: null,
      dstPwd: null,
      imagecropperShow: false,
      imagecropperKey: 0,
      uploadAvatar: '/factoryUser/changeAvatar',
      avatar: store.getters.avatar
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
    },
    cropSuccess(resData) {
      this.imagecropperShow = false
      this.imagecropperKey = this.imagecropperKey + 1
      const tmp = resData.split('.', 2)
      this.avatar = process.env.BASE_API + '/info/avatar/' + tmp[0] + '/' + tmp[1]
      console.info(this.avatar)
    },
    close() {
      this.imagecropperShow = false
    }
  }
}
</script>

<style scoped>
  .avatar{
    width: 200px;
    height: 200px;
    border-radius: 50%;
  }
</style>
