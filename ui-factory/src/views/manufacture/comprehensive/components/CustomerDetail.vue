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
        <el-tab-pane label="价目表" name="PriceSheet">
          <el-button type="primary" @click="dialogAddVisible = true">新增价目</el-button>
          <el-table :data="customer.priceSheet" style="width: 100%;padding-top: 15px;">
            <el-table-column label="品类代码">
              <template slot-scope="scope">
                {{ scope.row.productType.code }}
              </template>
            </el-table-column>
            <el-table-column label="品类名称" align="center">
              <template slot-scope="scope">
                {{ scope.row.productType.name }}
              </template>
            </el-table-column>
            <el-table-column label="应收价" align="center">
              <template slot-scope="scope">
                {{ scope.row.basePrice }}
              </template>
            </el-table-column>
            <el-table-column label="出货价" align="center">
              <template slot-scope="scope">
                {{ scope.row.factoryPrice }}
              </template>
            </el-table-column>
            <el-table-column label="代工价" align="center">
              <template slot-scope="scope">
                {{ scope.row.OEMPrice }}
              </template>
            </el-table-column>
            <el-table-column label="备注" align="center">
              <template slot-scope="scope">
                {{ scope.row.comment }}
              </template>
            </el-table-column>
            <el-table-column label="要求" align="center">
              <template slot-scope="scope">
                {{ scope.row.requirement }}
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
    </el-row>

    <el-dialog :visible.sync="dialogAddVisible" title="增加价目">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="品类" prop="title">
          <el-select v-model="priceSheet.productType.id" placeholder="请选择" clearable style="width: 90px" class="filter-item">
            <el-option v-for="type in productType" :key="type.id" :label="type.name" :value="type.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="应收价" prop="title">
          <el-input v-model="priceSheet.basePrice" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="出货价" prop="title">
          <el-input v-model="priceSheet.factoryPrice" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="代工价" prop="title">
          <el-input v-model="priceSheet.OEMPrice" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="备注">
          <el-input :autosize="{ minRows: 2, maxs: 4}" v-model="priceSheet.comment" type="textarea" placeholder="请输入" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="要求">
          <el-input :autosize="{ minRows: 2, maxs: 4}" v-model="priceSheet.requirement" type="textarea" placeholder="请输入" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click="submitPriceSheet">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { customer } from '@/api/common'
import { Message } from 'element-ui'
import { user, findDenturesByCustomer, addPriceSheet, findProductTypes } from '@/api/comprehensive'

export default {
  data() {
    return {
      salesman: null,
      customer: null,
      activeName: 'StaffList',
      dentures: null,
      dialogAddVisible: false,
      priceSheet: {
        productType: {
          id: null
        },
        customerId: null,
        basePrice: null,
        factoryPrice: null,
        OEMPrice: null,
        comment: null,
        requirement: null
      },
      productType: null
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
      findProductTypes().then(response => {
        var data = response.data
        this.productType = data
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
    },
    submitPriceSheet() {
      if (!this.priceSheet.productType.id) {
        return Message({
          message: '未选择产品类别',
          type: 'error',
          duration: 2 * 1000
        })
      }
      this.priceSheet.customerId = this.customer.id
      addPriceSheet(this.priceSheet).then(resp => {
        customer(this.customer.id).then(response => {
          var data = response.data
          this.customer = data
          user(this.customer.salesmanId).then(response1 => {
            var data = response1.data
            this.salesman = data
          })
          this.dialogAddVisible = false
        })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
</style>
