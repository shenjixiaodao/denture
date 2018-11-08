<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <el-table-column label="订单日期">
      <template slot-scope="scope">
        {{ scope.row.createdDate }}
      </template>
    </el-table-column>
    <el-table-column label="定制方">
      <template slot-scope="scope">
        {{ scope.row.clinic.name }}
      </template>
    </el-table-column>
    <el-table-column label="订单详情" align="center">
      <template slot-scope="scope">
        <router-link :to="'order/'+scope.row.dentureId" class="link-type">
          <span>详情</span>
        </router-link>
      </template>
    </el-table-column>
    <el-table-column label="义齿详情" align="center">
      <template slot-scope="scope">
        <router-link :to="'denture/'+scope.row.dentureId" class="link-type">
          <span>详情</span>
        </router-link>
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
        var data = response.data
        console.log(data)
        this.list = data
      })
    }
  }
}
</script>
