<template>
  <div class="app-container">
    <el-row style="background:#fff;margin-bottom:30px;">
      <table style="text-align: right">
        <tbody>
          <tr>
            <td class="td_title_prop">名称:</td><td class="td_content_prop">{{ customer.clinic.name }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">地址:</td><td class="td_content_prop">{{ customer.clinic.address }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">联系方式:</td><td class="td_content_prop">{{ customer.clinic.contact }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">保修:</td>
            <td class="td_content_prop">
              <label style="font-size: 10px;">固定类:</label><el-input v-model="customer.fixedGuarantee" style="width: 40px;"/>年&nbsp;&nbsp;
              <label style="font-size: 10px;">活动类:</label><el-input v-model="customer.mobilizableGuarantee" style="width: 40px;"/>年
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">跟单员:</td><td class="td_content_prop">{{ salesman.name }}</td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <el-row style="background:#fff;margin-bottom:20px;">
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="成员列表" name="StaffList">
          <el-table :data="customer.clinic.users" style="width: 100%;padding-top: 15px;">
            <el-table-column label="姓名">
              <template slot-scope="scope">
                {{ scope.row.name }}
              </template>
            </el-table-column>
            <el-table-column label="职称" align="center">
              <template slot-scope="scope">
                {{ scope.row.role }}
              </template>
            </el-table-column>
            <el-table-column label="联系方式" align="center">
              <template slot-scope="scope">
                {{ scope.row.contact }}
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="业务统计" name="AggregateBusiness">
          <el-table :data="dentures" style="width: 100%;padding-top: 15px;">
            <el-table-column label="订单日期">
              <template slot-scope="scope">
                {{ scope.row.createdDate.split(' ',2)[0] }}
              </template>
            </el-table-column>
            <el-table-column label="类型">
              <template slot-scope="scope">
                {{ scope.row.type }}
              </template>
            </el-table-column>
            <el-table-column label="规格">
              <template slot-scope="scope">
                {{ scope.row.specification }}
              </template>
            </el-table-column>
            <el-table-column label="义齿详情" align="center">
              <template slot-scope="scope">
                <router-link :to="'/comprehensive/denture/'+scope.row.id" class="link-type">
                  <span>详情</span>
                </router-link>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
      <el-tab-pane label="价目表" name="PriceList">
        价目表
      </el-tab-pane>
    </el-row>

  </div>
</template>

<script>
import { customer } from '@/api/common'
import { user, findDenturesByCustomer } from '@/api/comprehensive'

export default {
  data() {
    return {
      salesman: null,
      customer: null,
      activeName: 'StaffList',
      dentures: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      const id = this.$route.params && this.$route.params.id
      console.log('customer detail ==> ' + id)
      customer(id).then(response => {
        var data = response.data
        this.customer = data
        user(this.customer.salesmanId).then(response1 => {
          var data = response1.data
          this.salesman = data
        })
      })
    },
    handleClick(tab, event) {
      if (this.activeName === 'AggregateBusiness') {
        findDenturesByCustomer({
          clinicId: this.customer.id
        }).then(response => {
          this.dentures = response.data
        })
      }
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
</style>
