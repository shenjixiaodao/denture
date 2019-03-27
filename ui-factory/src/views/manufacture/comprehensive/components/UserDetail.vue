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
              <td class="td_title_prop">身份证号:</td><td class="td_content_prop"><el-input v-model="user.cardId" class="filter-item" /></td>
            </tr>
            <tr>
              <td class="td_title_prop">职位:</td><td class="td_content_prop"><el-input v-model="user.position" class="filter-item" /></td>
            </tr>
            <tr>
              <td class="td_title_prop">联系方式:</td><td class="td_content_prop">{{ user.contact }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">所在组:</td><td class="td_content_prop">{{ user.groupType }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">入职时间:</td><td class="td_content_prop">
                <el-date-picker v-model="user.joinDate" type="date" value-format="yyyy-MM-dd" placeholder="选择日期时间" style="width: 70%;"/>
              </td>
            </tr>
            <tr>
              <td class="td_title_prop">年龄:</td><td class="td_content_prop">{{ user.age }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">学历:</td><td class="td_content_prop"><el-input v-model="user.educational" class="filter-item" /></td>
            </tr>
            <tr>
              <td class="td_title_prop">婚姻:</td><td class="td_content_prop">
                <!--{{ user.marital ? '已婚': '未婚' }}-->
                <input id="radio_1" v-model="user.marital" type="radio" value="false">
                <label for="radio_1">未婚</label>
                <input id="radio_2" v-model="user.marital" type="radio" value="true" >
                <label for="radio_2">已婚</label>
              </td>
            </tr>
            <tr>
              <td class="td_title_prop">住址:</td><td class="td_content_prop">
                <el-input v-model="user.address" class="filter-item" />
              </td>
            </tr>
            <tr>
              <td class="td_title_prop">基本工资:</td><td class="td_content_prop">
                <el-input v-model="user.baseSalary" class="filter-item" />
              </td>
            </tr>
            <tr>
              <td class="td_title_prop">餐补:</td><td class="td_content_prop">
                <el-input v-model="user.mealSubsidy" class="filter-item" />
              </td>
            </tr>
            <tr>
              <td class="td_title_prop">交通补:</td><td class="td_content_prop">
                <el-input v-model="user.trafficSubsidy" class="filter-item" />
              </td>
            </tr>
            <tr>
              <td class="td_title_prop">住宿补:</td><td class="td_content_prop">
                <el-input v-model="user.accommodationSubsidy" class="filter-item" />
              </td>
            </tr>
            <tr>
              <td class="td_title_prop">提成率:</td><td class="td_content_prop">
                <el-input v-model="user.commissionRate" class="filter-item" />
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </el-row>
    <!--<el-row style="background:#fff;padding:5px 5px 0;margin-bottom:32px;">
      <span>权限:</span>
      <table cellspacing="15">
        <tr>
          <th style="text-align: center;font-size: 10px">生产人员角色</th>
          <th style="text-align: center;font-size: 10px">管理人员角色</th>
          <th style="text-align: center;font-size: 10px">业务人员角色</th>
        </tr>
        <tr>
          <td style="vertical-align: top">
            <el-checkbox v-model="user.roles" :label="roles[0].code">{{ roles[0].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[1].code">{{ roles[1].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[2].code">{{ roles[2].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[3].code">{{ roles[3].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[4].code">{{ roles[4].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[5].code">{{ roles[5].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[6].code">{{ roles[6].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[7].code">{{ roles[7].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[8].code">{{ roles[8].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[9].code">{{ roles[9].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[10].code">{{ roles[10].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[11].code">{{ roles[11].name }}</el-checkbox>
          </td>
          <td style="vertical-align: top">
            <el-checkbox v-model="user.roles" :label="roles[12].code">{{ roles[12].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[13].code">{{ roles[13].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[14].code">{{ roles[14].name }}</el-checkbox><br>
            <el-checkbox v-model="user.roles" :label="roles[15].code">{{ roles[15].name }}</el-checkbox>
          </td>
          <td style="vertical-align: top">
            <el-checkbox v-model="user.roles" :label="roles[16].code">{{ roles[16].name }}</el-checkbox>
          </td>
        </tr>
      </table>
    </el-row>-->
    <el-row style="background:#fff;padding:5px 5px 0;margin-bottom:32px;">
      <el-button :loading="loading" type="primary" @click="modifyUser">修改</el-button>
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
        { code: 'CheCi', name: '车瓷技师' },
        { code: 'CheCiLeader', name: '车瓷组长' },
        { code: 'Comprehensive', name: '综合部人员' },
        { code: 'ComprehensiveLeader', name: '综合部主管' },
        { code: 'Management', name: '管理层' },
        { code: 'Market', name: '市场人员' },
        { code: 'MarketLeader', name: '市场主管' }
      ],
      loading: false
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
      this.loading = true
      modifyUser({
        uid: this.user.id,
        roles: this.user.roles.join(','),
        marital: this.user.marital,
        address: this.user.address,
        educational: this.user.educational,
        cardId: this.user.cardId,
        joinDate: this.user.joinDate
      }).then(response => {
        this.loading = false
        var data = response.data
        this.user = data
      }).catch(() => {
        this.loading = false
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  @import "@/styles/common.scss";
  .div-inline{ display:inline}
</style>
