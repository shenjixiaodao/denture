<template>
  <div>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:5px;">
      <span style="color: #999;font-size: 14px;">客户名: </span>
      <el-input v-model="queryParam.customer_name" style="width: 200px;"/>
      <span style="color: #999;font-size: 14px;">订单月份: </span>
      <el-date-picker v-model="queryParam.order_month" type="month" style="width: 200px;" placeholder="月份" value-format="yyyy-MM" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search" >搜索</el-button>
    </el-row>
    <el-row>
      <el-table :data="list">
        <el-table-column type="index" />
        <el-table-column label="订单编号">
          <template slot-scope="scope">
            <span style="font-weight: bold">{{ scope.row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column label="客户名">
          <template slot-scope="scope">
            {{ scope.row.customer_name }}
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
            {{ scope.row.paid_amount | formatMoney }}
          </template>
        </el-table-column>
        <el-table-column label="支付日期">
          <template slot-scope="scope">
            {{ scope.row.paid_date }}
          </template>
        </el-table-column>
        <el-table-column label="欠款金额">
          <template slot-scope="scope">
            {{ scope.row.unpaid_amount | formatMoney }}
          </template>
        </el-table-column>
        <el-table-column label="预付金额">
          <template slot-scope="scope">
            {{ scope.row.prepaid_amount | formatMoney }}
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
            <router-link :to="'order/'+scope.row.id" class="link-type">
              <span>详情</span>
            </router-link>
          </template>
        </el-table-column>
      </el-table>
      <pagination :total="total" :page.sync="queryParam.page" :limit.sync="queryParam.limit" @pagination="fetchData" />
    </el-row>
  </div>
</template>

<script>
import { findOrders } from '@/api/order'
import Pagination from '@/components/Pagination'

export default {
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      queryParam: {
        customer_name: null,
        order_month: null,
        page: 1,
        limit: 20,
        sort: '+id'
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      findOrders(this.queryParam).then(response => {
        this.total = response.totalSize
        this.list = response.data
      })
    },
    search() {
      this.queryParam.page = 1
      this.queryParam.limit = 20
      findOrders(this.queryParam).then(response => {
        this.total = response.totalSize
        this.list = response.data
      })
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
</style>
