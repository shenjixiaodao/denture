<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:5px 5px 0;margin-bottom:20px;">
      <div class="panel panel-primary">
        <table>
          <tbody>
            <tr>
              <td class="td_title_prop">姓名:</td><td class="td_content_prop">{{ user.name }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">联系方式:</td><td class="td_content_prop">{{ user.contact }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">所在组:</td><td class="td_content_prop">{{ user.groupType }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">入职时间:</td><td class="td_content_prop">{{ user.joinDate }}</td>
            </tr>
          </tbody>
        </table>
        <div>
          <span>权限:</span>
          <el-checkbox-group v-model="user.roles">
            <el-checkbox :label="roles[0].code">{{ roles[0].name }}</el-checkbox>
            <el-checkbox :label="roles[1].code">{{ roles[1].name }}</el-checkbox>
            <el-checkbox :label="roles[2].code">{{ roles[2].name }}</el-checkbox>
            <br>
            <el-checkbox :label="roles[3].code">{{ roles[3].name }}</el-checkbox>
            <el-checkbox :label="roles[4].code">{{ roles[4].name }}</el-checkbox>
            <el-checkbox :label="roles[5].code">{{ roles[5].name }}</el-checkbox>
            <br>
            <el-checkbox :label="roles[6].code">{{ roles[6].name }}</el-checkbox>
            <el-checkbox :label="roles[7].code">{{ roles[7].name }}</el-checkbox>
            <el-checkbox :label="roles[8].code">{{ roles[8].name }}</el-checkbox>
            <br>
            <el-checkbox :label="roles[9].code">{{ roles[9].name }}</el-checkbox>
            <el-checkbox :label="roles[10].code">{{ roles[10].name }}</el-checkbox>
            <el-checkbox :label="roles[11].code">{{ roles[11].name }}</el-checkbox>
            <br>
            <el-checkbox :label="roles[12].code">{{ roles[12].name }}</el-checkbox>
            <el-checkbox :label="roles[13].code">{{ roles[13].name }}</el-checkbox>
            <el-checkbox :label="roles[14].code">{{ roles[14].name }}</el-checkbox>
            <br>
            <el-checkbox :label="roles[15].code">{{ roles[15].name }}</el-checkbox>
            <el-checkbox :label="roles[16].code">{{ roles[16].name }}</el-checkbox>
          </el-checkbox-group>
        </div>
      </div>
    </el-row>
    <el-row style="background:#fff;padding:5px 5px 0;margin-bottom:32px;">
      <el-button type="primary" @click="modifyUser">修改</el-button>
    </el-row>
  </div>
</template>

<script>
import { user, modifyUser } from '@/api/comprehensive'

export default {
  data() {
    return {
      user: null,
      roles: [
        { code: 'ShiGao', name: '石膏技师' },
        { code: 'ShiGaoLeader', name: '石膏组长' },
        { code: 'LaXing', name: '蜡型技师' },
        { code: 'LaXingLeader', name: '蜡型组长' },
        { code: 'CheJin', name: '车金技师' },
        { code: 'ChejinLeader', name: '车金组长' },
        { code: 'ChongJiao', name: '充胶技师' },
        { code: 'ChongjiaoLeader', name: '充胶组长' },
        { code: 'ShangCi', name: '上瓷技师' },
        { code: 'ShangCiLeader', name: '上瓷组长' },
        { code: 'CheCi', name: '车瓷' },
        { code: 'CheCiLeader', name: '车瓷组长' },
        { code: 'Comprehensive', name: '综合部人员' },
        { code: 'ComprehensiveLeader', name: '综合部主管' },
        { code: 'Market', name: '市场人员' },
        { code: 'MarketLeader', name: '市场主管' },
        { code: 'Management', name: '管理层' }
      ]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      const id = this.$route.params && this.$route.params.id
      console.log('user detail ==> ' + id)
      user(id).then(response => {
        var data = response.data
        this.user = data
      })
    },
    modifyUser() {
      modifyUser({
        uid: this.user.id,
        roles: this.user.roles.join(',')
      }).then(response => {
        var data = response.data
        this.user = data
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  @import "@/styles/common.scss";
</style>
