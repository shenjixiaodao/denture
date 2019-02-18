<template>
  <div class="app-container">
    <el-row style="background:#fff;margin-bottom:20px;">
      <el-button type="primary" @click="dialogAddVisible = true">新增客户</el-button>
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

    <el-dialog :visible.sync="dialogAddVisible" title="新增客户">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="公司名" prop="title">
          <el-input v-model="customer.name" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click="addCustomer">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { findCustomers, storeCustomer } from '@/api/customer'
import { cities } from '@/utils/allCities'

export default {
  data() {
    return {
      list: null,
      dialogAddVisible: false,
      customer: {
        name: null
      },
      cities: cities,
      props: {
        value: 'name',
        label: 'name',
        children: 'sub'
      },
      selectedCity: []
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
    addCustomer() {
      // this.customer.region = this.selectedCity.join('/')
      storeCustomer(this.customer).then(resp => {
        findCustomers().then(response => {
          var data = response.data
          this.list = data
        })
        this.dialogAddVisible = false
      })
    }
  }
}
</script>
