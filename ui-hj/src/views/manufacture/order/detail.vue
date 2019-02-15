<template>
  <div class="panel panel-primary">
    <el-row style="background:#fff;margin-bottom:30px;">
      <table style="text-align: right">
        <tbody>
          <tr>
            <td class="td_title_prop">订单编号:</td><td class="td_content_prop">{{ orderInfo.id }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">客户名:</td><td class="td_content_prop">{{ orderInfo.customer_name }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">创建日期:</td><td class="td_content_prop">{{ orderInfo.created_date }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">完成日期:</td><td class="td_content_prop">{{ orderInfo.deadline }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">业务员:</td>
            <td class="td_content_prop">
              {{ orderInfo.salesman }}
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">付款方式:</td><td class="td_content_prop">{{ orderInfo.paid_type }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">支付金额:</td><td class="td_content_prop">{{ orderInfo.paid_amount }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">支付日期:</td><td class="td_content_prop">{{ orderInfo.paid_date }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">欠款金额:</td><td class="td_content_prop">{{ orderInfo.unpaid_amount }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">预付金额:</td><td class="td_content_prop">{{ orderInfo.prepaid_amount }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">预付时间:</td><td class="td_content_prop">{{ orderInfo.prepaid_date }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">来料铜(kg):</td><td class="td_content_prop">{{ orderInfo.recycled_cu }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">发货日期:</td><td class="td_content_prop">{{ orderInfo.delivery_date }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">备注:</td><td class="td_content_prop">{{ orderInfo.comment }}</td>
          </tr>
        </tbody>
      </table>
    </el-row>
    <el-row style="background:#fff;margin-left:10px;">
      <el-button type="primary" @click="dialogAddVisible = true">添加产品明细</el-button>
      <el-table :data="orderInfo.details" style="width: 100%;padding-top: 15px;">
        <el-table-column label="产品名">
          <template slot-scope="scope">
            {{ scope.row.product_name }}
          </template>
        </el-table-column>
        <el-table-column label="规格" align="center">
          <template slot-scope="scope">
            {{ scope.row.specification }}
          </template>
        </el-table-column>
        <el-table-column label="单位" align="center">
          <template slot-scope="scope">
            {{ scope.row.unit }}
          </template>
        </el-table-column>
        <el-table-column label="数量" align="center">
          <template slot-scope="scope">
            {{ scope.row.number }}
          </template>
        </el-table-column>
        <el-table-column label="单价(kg)" align="center">
          <template slot-scope="scope">
            {{ scope.row.unit_price }}
          </template>
        </el-table-column>
        <el-table-column label="加工单价" align="center">
          <template slot-scope="scope">
            {{ scope.row.processing_unit_price }}
          </template>
        </el-table-column>
        <el-table-column label="原料价格" align="center">
          <template slot-scope="scope">
            {{ scope.row.ingredient_amount }}
          </template>
        </el-table-column>
        <el-table-column label="供应商" align="center">
          <template slot-scope="scope">
            {{ scope.row.supplier }}
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center">
          <template slot-scope="scope">
            {{ scope.row.comment }}
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-dialog :visible.sync="dialogAddVisible" title="添加产品明细">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="产品名" prop="title">
          <el-input v-model="product.product_name" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="规格" prop="title">
          <el-input v-model="product.specification" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="单位" prop="title">
          <el-input v-model="product.unit" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="数量" prop="title">
          <el-input v-model="product.number" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="单价(kg)" prop="title">
          <el-input v-model="product.unit_price" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="加工单价" prop="title">
          <el-input v-model="product.processing_unit_price" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="原料价格" prop="title">
          <el-input v-model="product.ingredient_amount" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="供应商" prop="title">
          <el-input v-model="product.supplier" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="备注" prop="title">
          <el-input v-model="product.comment" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click="addOrderDetail">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { findOrderDetail, addOrderDetail } from '@/api/order'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      orderInfo: null,
      dialogAddVisible: false,
      product: {
        order_id: null,
        product_name: null,
        specification: null,
        unit: null,
        number: null,
        unit_price: null,
        processing_unit_price: null,
        ingredient_amount: null,
        supplier: null,
        comment: null
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      const id = this.$route.params && this.$route.params.id
      console.log('order detail ==> ' + id)
      findOrderDetail(id).then(response => {
        var data = response.data
        this.orderInfo = data
      })
    },
    addOrderDetail() {
      if (!this.product.product_name) {
        return Message({
          message: '产品名为空',
          type: 'error',
          duration: 2 * 1000
        })
      }
      this.product.order_id = this.orderInfo.id
      addOrderDetail(this.product).then(resp => {
        findOrderDetail(this.orderInfo.id).then(response => {
          var data = response.data
          this.orderInfo = data
        })
        this.dialogAddVisible = false
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  @import "@/styles/common.scss";
</style>
