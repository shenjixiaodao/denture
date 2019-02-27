<template>
  <div class="app-container">
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="客户列表" name="Customer">
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
      </el-tab-pane>
      <el-tab-pane label="公海客户库" name="PublicCustomer">
        <public-customer-table />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { findCustomers } from '@/api/customer'
import PublicCustomerTable from './components/PublicCustomerTable'

export default {
  components: { PublicCustomerTable },
  data() {
    return {
      list: null,
      publicCustomers: null,
      activeName: 'Customer'
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
    },
    handleClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>
