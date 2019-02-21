<template>
  <div class="panel panel-primary">
    <el-row style="background:#fff;margin-left:10px;">
      <table style="text-align: right">
        <tbody>
          <tr>
            <td class="td_title_prop">订单编号:</td><td class="td_content_prop">{{ orderInfo.id }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">客户名:</td>
            <td class="td_content_prop">
              <el-input v-model="orderInfo.customer_name"/>
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">创建日期:</td>
            <td class="td_content_prop">
              <el-date-picker v-model="orderInfo.created_date" type="date" placeholder="创建日期" value-format="yyyy-MM-dd" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">交货日期:</td>
            <td class="td_content_prop">
              <el-input v-model="orderInfo.deadline" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">业务员:</td>
            <td class="td_content_prop">
              <el-input v-model="orderInfo.salesman" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">付款方式:</td>
            <td class="td_content_prop">
              <el-input v-model="orderInfo.paid_type" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">支付金额:</td>
            <td class="td_content_prop">
              <el-input v-model="orderInfo.paid_amount" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">支付日期:</td>
            <td class="td_content_prop">
              <el-date-picker v-model="orderInfo.paid_date" type="date" placeholder="支付日期" value-format="yyyy-MM-dd" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">欠款金额:</td>
            <td class="td_content_prop">
              <el-input v-model="orderInfo.unpaid_amount" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">预付金额:</td>
            <td class="td_content_prop">
              <el-input v-model="orderInfo.prepaid_amount" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">预付时间:</td>
            <td class="td_content_prop">
              <el-date-picker v-model="orderInfo.prepaid_date" type="date" placeholder="预付时间" value-format="yyyy-MM-dd" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">来料铜(kg):</td>
            <td class="td_content_prop">
              <el-input v-model="orderInfo.recycled_cu" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">发货日期:</td>
            <td class="td_content_prop">
              <el-date-picker v-model="orderInfo.delivery_date" type="date" placeholder="预付时间" value-format="yyyy-MM-dd" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">备注:</td>
            <td class="td_content_prop">
              <el-input :autosize="{ minRows: 2, maxRows: 4}" v-model="orderInfo.comment" type="textarea" placeholder="请输入"/>
            </td>
          </tr>
        </tbody>
      </table>
      <el-button type="primary" @click="modifyOrder">修改</el-button>
    </el-row>
    <el-row style="background:#fff;margin-left:10px;margin-top: 30px">
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
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="handleModify(scope.row)">修改</el-button>
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
        <el-button v-if="isCreated" type="primary" @click="addOrderDetail">提交</el-button>
        <el-button v-else type="primary" @click="modifyOrderDetail">修改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { findOrderDetail, addOrderDetail, modifyOrder, modifyOrderDetail } from '@/api/order'
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
      },
      isCreated: true
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
    modifyOrder() {
      modifyOrder(this.orderInfo).then(res => {
        Message({
          message: '修改成功',
          type: 'success',
          duration: 2 * 1000
        })
      })
    },
    handleModify(row) {
      this.product = Object.assign({}, row)
      this.dialogAddVisible = true
      this.isCreated = false
    },
    modifyOrderDetail() {
      modifyOrderDetail(this.product).then(response => {
        Message({
          message: '修改成功',
          type: 'success',
          duration: 2 * 1000
        })
        var data = response.data
        this.orderInfo = data
        this.dialogAddVisible = false
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
