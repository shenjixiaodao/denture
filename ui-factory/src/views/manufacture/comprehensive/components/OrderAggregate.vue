<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-form-item label="业务员" prop="title">
        <el-select v-model="queryParams.salesmanId" filterable placeholder="业务员" class="filter-item">
          <el-option v-for="item in users" :key="item.id" :label="item.name" :value="item.id"/>
        </el-select>
      </el-form-item>
      <span>出货时段: </span>
      <el-date-picker v-model="queryParams.startTime" type="date" style="width: 200px;" placeholder="开始时间" value-format="yyyy-MM-dd" />
      <span style="font-size: 12px; color: #999">至</span>
      <el-date-picker v-model="queryParams.endTime" type="date" style="width: 200px;" placeholder="结束时间" value-format="yyyy-MM-dd" />
      <span>客户名: </span><el-input v-model="queryParams.customer" style="width: 200px;" placeholder="客户名称"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search()" >搜索</el-button>
    </el-row>
    <el-row v-for="item in data" :key="item.month" style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <!--<el-input v-model="filename" style="width: 200px;" placeholder="导出数据文件名"/>
      <el-button :loading="exportLoading" type="primary" icon="document" @click="handleExport">导出数据</el-button>-->
      <table cellspacing="15" style="text-align: left">
        <tr>
          <td class="td_title_prop">月份:</td><td class="td_content_prop">{{ item.month }}</td>
          <td class="td_title_prop">总数:</td><td class="td_content_prop">{{ item.size }}</td>
          <td class="td_title_prop">总价:</td><td class="td_content_prop">{{ item.amount }}</td>
        </tr>
      </table>
      <el-table :data="item.list" style="width: 100%;padding-top: 15px;">
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
        <el-table-column label="出货单号">
          <template slot-scope="scope">
            {{ scope.row.deliveryId }}
          </template>
        </el-table-column>
        <el-table-column label="出货日期" align="center">
          <template slot-scope="scope">
            {{ scope.row.deliveryDate }}
          </template>
        </el-table-column>
        <el-table-column label="收件日期" align="center">
          <template slot-scope="scope">
            {{ scope.row.receivedDate | time2DateStr }}
          </template>
        </el-table-column>
        <el-table-column label="义齿编号">
          <template slot-scope="scope">
            <router-link :to="'denture/'+scope.row.dentureId" class="link-type">
              <span>{{ scope.row.dentureId | id2Short }}</span>
            </router-link>
          </template>
        </el-table-column>
        <el-table-column label="品类" align="center">
          <template slot-scope="scope">
            {{ scope.row.specification }}
          </template>
        </el-table-column>
        <el-table-column label="数量" align="center">
          <template slot-scope="scope">
            {{ scope.row.number }}
          </template>
        </el-table-column>
        <el-table-column label="阶段" align="center">
          <template slot-scope="scope">
            {{ scope.row.stage }}
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
      <hr style="filter: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="100%" color="#987cb9" SIZE="1">
    </el-row>
  </div>
</template>

<script>
import { aggregateOrders, users } from '@/api/comprehensive'

export default {
  name: 'OrderAggregate',
  data() {
    return {
      now: new Date().Format('yyyy-MM-dd'),
      users: null,
      data: null,
      queryParams: {
        salesmanId: null,
        startTime: new Date().Format('yyyy-MM-dd'),
        endTime: new Date().Format('yyyy-MM-dd'),
        customer: null
      },
      exportLoading: false,
      filename: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.search()
      users().then(response => {
        const data = response.data
        this.users = data
      })
    },
    search() {
      aggregateOrders(this.queryParams).then(response => {
        var data = response.data
        this.data = data
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
