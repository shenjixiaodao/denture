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
    <el-row style="background:#fff;margin-left:10px;margin-top: 10px">
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="订单记录" name="orders">
          <el-table :data="customer.orders">
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
        </el-tab-pane>
        <el-tab-pane label="回访记录" name="visit">
          <el-button type="primary" @click="dialogAddVisible=true">添加回访记录</el-button>
          <el-table :data="customer.visit">
            <el-table-column type="index" />
            <el-table-column label="回访时间">
              <template slot-scope="scope">
                <span style="font-weight: bold">{{ scope.row.visit_date }}</span>
              </template>
            </el-table-column>
            <el-table-column label="接待人员">
              <template slot-scope="scope">
                {{ scope.row.interviewe }}
              </template>
            </el-table-column>
            <el-table-column label="扩能计划">
              <template slot-scope="scope">
                {{ scope.row.expansion_plan }}
              </template>
            </el-table-column>
            <el-table-column label="短网改造计划">
              <template slot-scope="scope">
                {{ scope.row.rebuild_plan }}
              </template>
            </el-table-column>
            <el-table-column label="采购计划及意向">
              <template slot-scope="scope">
                {{ scope.row.puchase_intended }}
              </template>
            </el-table-column>
            <el-table-column label="产品使用情况反馈">
              <template slot-scope="scope">
                {{ scope.row.feedback }}
              </template>
            </el-table-column>
            <el-table-column label="对我司的态度">
              <template slot-scope="scope">
                {{ scope.row.atitude }}
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-row>
    <el-dialog :visible.sync="dialogAddVisible" title="新增回访记录">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="回访时间" prop="title">
          <el-date-picker v-model="customer.visit_date" type="date" placeholder="回访时间" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="接待人员" prop="title">
          <el-input v-model="customer.interviewe"/>
        </el-form-item>
        <el-form-item label="扩能计划" prop="title">
          <el-input v-model="customer.expansion_plan"/>
        </el-form-item>
        <el-form-item label="短网改造计划" prop="title">
          <el-input v-model="customer.rebuild_plan"/>
        </el-form-item>
        <el-form-item label="采购计划及意向" prop="title">
          <el-input v-model="customer.puchase_intended"/>
        </el-form-item>
        <el-form-item label="产品使用情况反馈" prop="title">
          <el-input v-model="customer.feedback"/>
        </el-form-item>
        <el-form-item label="对我司的态度" prop="title">
          <el-input v-model="customer.atitude"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click="addCustomer">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { findCustomer, modifyCustomer } from '@/api/customer'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      customer: null,
      activeName: 'orders',
      dialogAddVisible: false
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
    },
    handleClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
</style>
