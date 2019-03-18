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
          <tr v-if="customer.is_customer===0">
            <td class="td_title_prop">是否客户:</td>
            <td class="td_content_prop">
              否
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">名称:</td>
            <td class="td_content_prop">
              <!--<el-input v-model="customer.name" />-->
              {{ customer.name }}
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">企业性质:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.property" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">开户行及账号:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.bank_account" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">所在城市:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.cities" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">详细地址:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.address" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">发货路线、方式、费用:</td>
            <td class="td_content_prop">
              <el-input :autosize="{ minRows: 2, maxRows: 4}" v-model="customer.delivery" type="textarea" placeholder="请输入"/>
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">炉子功率、种类、数量:</td>
            <td class="td_content_prop">
              <el-input :autosize="{ minRows: 2, maxRows: 4}" v-model="customer.equipment" type="textarea" placeholder="请输入"/>
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">生产产品:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.product" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">产量情况:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.production" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">目前开工情况:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.work_status" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">采购负责人:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.purchase_manager" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">负责人联系方式:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.purchase_contact" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">负责人是否为老板亲信:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.is_trusted_manager" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">采购方式:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.purchase_style" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">是否长期客户:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.is_long_term" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">是否参观过我单位:</td>
            <td class="td_content_prop">
              <el-input v-model="customer.is_visit_company" />
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
      <el-button v-if="customer.is_customer===0" type="primary" @click="convert2Customer">转为客户</el-button>
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
          <el-table :data="customer.visits">
            <el-table-column type="index" />
            <el-table-column label="回访时间">
              <template slot-scope="scope">
                <span style="font-weight: bold">{{ scope.row.visit_date }}</span>
              </template>
            </el-table-column>
            <el-table-column label="接待人员">
              <template slot-scope="scope">
                {{ scope.row.interview }}
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
                {{ scope.row.purchase_intended }}
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
          <el-date-picker v-model="visit.visit_date" type="date" placeholder="回访时间" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="接待人员" prop="title">
          <el-input v-model="visit.interview"/>
        </el-form-item>
        <el-form-item label="扩能计划" prop="title">
          <el-input v-model="visit.expansion_plan"/>
        </el-form-item>
        <el-form-item label="短网改造计划" prop="title">
          <el-input v-model="visit.rebuild_plan"/>
        </el-form-item>
        <el-form-item label="采购计划及意向" prop="title">
          <el-input v-model="visit.purchase_intended"/>
        </el-form-item>
        <el-form-item label="产品使用情况反馈" prop="title">
          <el-input v-model="visit.feedback"/>
        </el-form-item>
        <el-form-item label="对我司的态度" prop="title">
          <el-input v-model="visit.atitude"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click="addVisit">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { findCustomer, modifyCustomer } from '@/api/customer'
import { newVisit } from '@/api/visit'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      customer: null,
      visit: {
        visit_date: null,
        interview: null,
        expansion_plan: null,
        rebuild_plan: null,
        purchase_intended: null,
        feedback: null,
        atitude: null,
        customer_id: null
      },
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
    },
    addVisit() {
      this.visit.customer_id = this.customer.id
      newVisit(this.visit).then(resp => {
        findCustomer(this.customer.id).then(response => {
          var data = response.data
          this.customer = data
        })
        this.dialogAddVisible = false
      })
    },
    convert2Customer() {
      modifyCustomer({
        id: this.customer.id,
        is_customer: 1
      }).then(response => {
        this.customer.is_customer = 1
        Message({
          message: '加入成功',
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
