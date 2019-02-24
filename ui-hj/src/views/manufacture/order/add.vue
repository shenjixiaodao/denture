<template>
  <div class="dashboard-container">
    <el-row style="background:#fff;padding:10px 10px 0;">
      <el-form ref="dataForm" label-position="left" label-width="20%">
        <el-form-item label="客户名" prop="title">
          <el-select v-model="order.customer_id" placeholder="类型" filterable class="filter-item">
            <el-option v-for="item in customers" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
          <router-link :to="'/customer/list'" class="link-type">
            <span>详情</span>
          </router-link>
        </el-form-item>
        <el-form-item label="创建日期" prop="title">
          <el-date-picker v-model="order.created_date" type="date" placeholder="创建日期" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="交货日期" prop="title">
          <el-input v-model="order.deadline" />
        </el-form-item>
        <el-form-item label="业务员" prop="title">
          <el-input v-model="order.salesman" />
        </el-form-item>
        <el-form-item label="付款方式" prop="title">
          <el-input v-model="order.paid_type" />
        </el-form-item>
        <el-form-item label="支付金额" prop="title">
          <el-input v-model="order.paid_amount" />
        </el-form-item>
        <el-form-item label="支付日期" prop="title">
          <el-date-picker v-model="order.paid_date" type="date" placeholder="支付日期" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="欠款金额" prop="title">
          <el-input v-model="order.unpaid_amount"/>
        </el-form-item>
        <el-form-item label="预付金额" prop="title">
          <el-input v-model="order.prepaid_amount" />
        </el-form-item>
        <el-form-item label="预付时间" prop="title">
          <el-date-picker v-model="order.prepaid_date" type="date" placeholder="发货日期" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="来料铜(kg)" prop="title">
          <el-input v-model="order.recycled_cu" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="发货日期" prop="title">
          <el-date-picker v-model="order.delivery_date" type="date" placeholder="发货日期" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input :autosize="{ minRows: 2, maxRows: 4}" v-model="order.comment" type="textarea" placeholder="请输入" style="width: 70%;"/>
        </el-form-item>
      </el-form>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <!--<el-button @click="dialogAddVisible = false">取消</el-button>-->
      <el-button type="primary" @click="addOrder">保存</el-button>
    </el-row>
  </div>
</template>

<script>
import { newOrder } from '@/api/order'
import { findCustomers } from '@/api/customer'
import { Message } from 'element-ui'

export default {
  name: 'AddOrder',
  data() {
    return {
      order: {
        customer_id: null,
        customer_name: null,
        created_date: null,
        deadline: null,
        salesman: null,
        paid_type: null,
        paid_amount: null,
        paid_date: null,
        unpaid_amount: null,
        prepaid_amount: null,
        prepaid_date: null,
        recycled_cu: null,
        delivery_date: null,
        comment: null
      },
      customers: null
    }
  },
  created() {
    this.fetchData()
    this.loadShortcuts()
  },
  methods: {
    loadShortcuts() {
      document.onkeydown = function(event) {
        var e = event || window.event
        if (e && e.code === 'KeyS' && e.altKey) {
          newOrder()
        }
      }
    },
    fetchData() {
      findCustomers().then(response => {
        var data = response.data
        this.customers = data
      })
    },
    addOrder() {
      if (!this.order.customer_id) {
        return Message({
          message: '客户为空',
          type: 'error',
          duration: 2 * 1000
        })
      }
      newOrder(this.order).then(response => {
        var data = response.data
        this.$router.push({ path: '/order/' + data.id })
      })
    }
  }
}
</script>
