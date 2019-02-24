<template>
  <div class="app-container">
    <el-row style="background:#fff;margin-bottom:20px;">
      <el-button type="primary" @click="jump">新增客户</el-button>
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
        <el-table-column label="编号">
          <template slot-scope="scope">
            {{ scope.row.id }}
          </template>
        </el-table-column>
        <el-table-column label="名称">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="累计欠款">
          <template slot-scope="scope">
            {{ scope.row.total_unpaid_amount }}
          </template>
        </el-table-column>
        <el-table-column label="客户详情" align="center">
          <template slot-scope="scope">
            <router-link :to="'customer/'+scope.row.id" class="link-type">
              <span>详情</span>
            </router-link>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
import { findCustomers } from '@/api/customer'

export default {
  data() {
    return {
      list: null,
      customer: {
        name: null
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      findCustomers().then(response => {
        var data = response.data
        console.log(data)
        this.list = data
      })
    },
    jump() {
      this.$router.push({ path: '/customer/add' })
    }
  }
}
</script>
