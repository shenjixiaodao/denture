<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <el-table-column label="订单日期" min-width="200">
      <template slot-scope="scope">
        {{ scope.row.createdDate }}
      </template>
    </el-table-column>
    <el-table-column label="订单详情" width="195" align="center">
      <template slot-scope="scope">
        {{ scope.row.id }}
      </template>
    </el-table-column>
    <el-table-column label="生产详情" width="100" align="center">
      <template slot-scope="scope">
        {{ scope.row.dentureId }}
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { queryOrders } from '@/api/salesman'

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
      queryOrders().then(response => {
        var data = response.data.data
        console.log(data)
        this.list = data
      })
    }
  }
}
</script>
