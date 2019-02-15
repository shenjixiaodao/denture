<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <el-table-column label="订单编号">
      <template slot-scope="scope">
        {{ scope.row.id }}
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
</template>

<script>
import { findOrders } from '@/api/order'

export default {
  data() {
    return {
      list: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      findOrders().then(response => {
        var data = response.data
        console.log(data)
        this.list = data
      })
    }
  }
}
</script>
