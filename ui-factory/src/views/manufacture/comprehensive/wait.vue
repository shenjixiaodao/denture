<template>
  <div class="app-container">
    <el-table :data="list" style="width: 100%;padding-top: 15px;">
      <el-table-column label="订单日期" min-width="200">
        <template slot-scope="scope">
          {{ scope.row.createdDate }}
        </template>
      </el-table-column>
      <el-table-column label="类型" min-width="200">
        <template slot-scope="scope">
          {{ scope.row.type }}
        </template>
      </el-table-column>
      <el-table-column label="规格" min-width="200">
        <template slot-scope="scope">
          {{ scope.row.specification }}
        </template>
      </el-table-column>
      <el-table-column label="义齿详情" width="100" align="center">
        <template slot-scope="scope">
          <router-link :to="'denture/'+scope.row.id" class="link-type">
            <span>详情</span>
          </router-link>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { queryDenturesByStatus } from '@/api/comprehensive'

export default {
  name: 'ComprehensiveWait',
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
      queryDenturesByStatus('Waiting').then(response => {
        var data = response.data
        console.log(data)
        this.list = data
      })
    }
  }
}
</script>
