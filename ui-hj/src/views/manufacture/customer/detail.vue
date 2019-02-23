<template>
  <div class="panel panel-primary">
    <el-row style="background:#fff;margin-left:10px;">
      <table style="text-align: right">
        <tbody>
          <tr>
            <td class="td_title_prop">编号:</td>
            <td class="td_content_prop">
              {{ customer.id }}
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">名称:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.name" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">累计欠款:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.total_unpaid_amount" />
            </td>
          </tr>
        </tbody>
      </table>
      <el-button type="primary" @click="modifyCustomer">修改</el-button>
    </el-row>
    <el-row style="background:#fff;margin-left:10px;">
      <el-table :data="customer.orders" style="width: 100%;padding-top: 15px;">
        <el-table-column type="index" />
        <el-table-column label="订单编号">
          <template slot-scope="scope">
            <span style="font-weight: bold">{{ scope.row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column label="创建日期">
          <template slot-scope="scope">
            {{ scope.row.created_date }}
          </template>
        </el-table-column>
        <el-table-column label="完成日期">
          <template slot-scope="scope">
            {{ scope.row.deadline }}
          </template>
        </el-table-column>
        <el-table-column label="业务员">
          <template slot-scope="scope">
            {{ scope.row.salesman }}
          </template>
        </el-table-column>
        <el-table-column label="付款方式">
          <template slot-scope="scope">
            {{ scope.row.paid_type }}
          </template>
        </el-table-column>
        <el-table-column label="支付金额">
          <template slot-scope="scope">
            {{ scope.row.paid_amount }}
          </template>
        </el-table-column>
        <el-table-column label="支付日期">
          <template slot-scope="scope">
            {{ scope.row.paid_date }}
          </template>
        </el-table-column>
        <el-table-column label="欠款金额">
          <template slot-scope="scope">
            {{ scope.row.unpaid_amount }}
          </template>
        </el-table-column>
        <el-table-column label="预付金额">
          <template slot-scope="scope">
            {{ scope.row.prepaid_amount }}
          </template>
        </el-table-column>
        <el-table-column label="预付时间">
          <template slot-scope="scope">
            {{ scope.row.prepaid_date }}
          </template>
        </el-table-column>
        <el-table-column label="来料铜(kg)">
          <template slot-scope="scope">
            {{ scope.row.recycled_cu }}
          </template>
        </el-table-column>
        <el-table-column label="发货日期">
          <template slot-scope="scope">
            {{ scope.row.delivery_date }}
          </template>
        </el-table-column>
        <el-table-column label="备注">
          <template slot-scope="scope">
            {{ scope.row.comment }}
          </template>
        </el-table-column>
        <el-table-column label="订单详情" align="center">
          <template slot-scope="scope">
            <router-link :to="'/order/order/'+scope.row.id" class="link-type">
              <span>详情</span>
            </router-link>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
import { findCustomer, modifyCustomer } from '@/api/customer'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      customer: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      const id = this.$route.params && this.$route.params.id
      console.log('customer detail ==> ' + id)
      findCustomer(id).then(response => {
        var data = response.data
        this.customer = data
      })
    },
    modifyCustomer() {
      modifyCustomer(this.customer).then(response => {
        Message({
          message: '修改成功',
          type: 'success',
          duration: 2 * 1000
        })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
</style>
