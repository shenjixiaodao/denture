<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <span>开始时段: </span>
      <el-date-picker v-model="queryParams.startTime" type="date" style="width: 200px;" placeholder="开始时间" value-format="yyyy-MM-dd" />
      <span style="font-size: 12px; color: #999">至</span>
      <el-date-picker v-model="queryParams.endTime" type="date" style="width: 200px;" placeholder="结束时间" value-format="yyyy-MM-dd" />
      <span>客户名: </span><el-input v-model="queryParams.customer" style="width: 200px;" placeholder="客户名称"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search()" >搜索</el-button>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-input v-model="filename" style="width: 200px;" placeholder="导出数据文件名"/>
      <el-button :loading="exportLoading" type="primary" icon="document" @click="handleExport">导出数据</el-button>
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
        <el-table-column label="业务员">
          <template slot-scope="scope">
            {{ scope.row.salesman }}
          </template>
        </el-table-column>
        <el-table-column label="客户名称">
          <template slot-scope="scope">
            {{ scope.row.customer }}
          </template>
        </el-table-column>
        <el-table-column label="义齿编号">
          <template slot-scope="scope">
            <router-link :to="'denture/'+scope.row.dentureId" class="link-type">
              <span>{{ scope.row.dentureId }}</span>
            </router-link>
          </template>
        </el-table-column>
        <el-table-column label="出货日期" align="center">
          <template slot-scope="scope">
            {{ scope.row.endDate }}
          </template>
        </el-table-column>
        <el-table-column label="品名" align="center">
          <template slot-scope="scope">
            {{ scope.row.specification }}
          </template>
        </el-table-column>
        <el-table-column label="订单号" align="center">
          <template slot-scope="scope">
            {{ scope.row.orderId }}
          </template>
        </el-table-column>
        <el-table-column label="数量" align="center">
          <template slot-scope="scope">
            {{ scope.row.number }}
          </template>
        </el-table-column>
        <el-table-column label="单价" align="center">
          <template slot-scope="scope">
            {{ scope.row.basePrice }}
          </template>
        </el-table-column>
        <el-table-column label="应收金额" align="center">
          <template slot-scope="scope">
            {{ scope.row.basePrice * scope.row.number }}
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
import { aggregateOrders } from '@/api/comprehensive'

export default {
  name: 'OrderAggregate',
  data() {
    return {
      list: null,
      queryParams: {
        startTime: null,
        endTime: null,
        customer: null
      },
      exportLoading: false,
      filename: null
    }
  },
  methods: {
    search() {
      aggregateOrders(this.queryParams).then(response => {
        var data = response.data
        this.list = data
      })
    },
    handleExport() {
      this.exportLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['业务员', '客户名称', '义齿编号', '出货日期', '品名', '订单号', '数量', '单价', '应收金额']
        const filterVal = ['salesman', 'customer', 'dentureId', 'endDate', 'specification', 'orderId', 'number', 'basePrice', 'receivable']
        const data = this.formatJson(filterVal, this.list)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: this.filename ? this.filename : '导出数据',
          autoWidth: true,
          bookType: 'xlsx'
        })
        this.exportLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'receivable') {
          return v['number'] * v['basePrice']
        } else {
          return v[j]
        }
      }))
    }
  }
}
</script>
