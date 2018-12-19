<template>
  <div class="app-container">
    <el-row style="background:#fff;margin-bottom:20px;">
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
        <el-table-column label="编号">
          <template slot-scope="scope">
            {{ scope.row.clinic.id }}
          </template>
        </el-table-column>
        <el-table-column label="名称">
          <template slot-scope="scope">
            {{ scope.row.clinic.name }}
          </template>
        </el-table-column>
        <el-table-column label="地址" align="center">
          <template slot-scope="scope">
            {{ scope.row.clinic.address }}
          </template>
        </el-table-column>
        <el-table-column label="联系方式" align="center">
          <template slot-scope="scope">
            {{ scope.row.clinic.contact }}
          </template>
        </el-table-column>
        <el-table-column label="客户详情" align="center">
          <template slot-scope="scope">
            <router-link :to="'/comprehensive/customer/'+scope.row.id" class="link-type">
              <span>详情</span>
            </router-link>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
import { customers } from '@/api/comprehensive'

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
      customers().then(response => {
        var data = response.data
        console.log(data)
        this.list = data
      })
    }
  }
}
</script>
