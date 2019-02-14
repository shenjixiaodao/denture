<template>
  <div class="app-container">
    <el-row style="background:#fff;margin-bottom:20px;">
      <el-button type="primary" @click="dialogAddVisible = true">新增客户</el-button>
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
            <router-link :to="'/salesman/customer/'+scope.row.id" class="link-type">
              <span>详情</span>
            </router-link>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-dialog :visible.sync="dialogAddVisible" title="新增客户">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="诊所名" prop="title">
          <el-input v-model="customer.name" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="所在城市" prop="title">
          <el-cascader :options="cities" :props="props" placeholder="省/市/县" @change="handleChange"/>
        </el-form-item>
        <el-form-item label="具体地址" prop="title">
          <el-input v-model="customer.address" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="联系方式" prop="title">
          <el-input v-model="customer.contact" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="医生名" prop="title">
          <el-input v-model="customer.dentistName" style="width: 70%;"/>
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
import { customers, recordCustomer } from '@/api/salesman'
import { cities } from '@/utils/allCities'

export default {
  data() {
    return {
      list: null,
      dialogAddVisible: false,
      customer: {
        name: null,
        region: null,
        address: null,
        contact: null,
        dentistName: null
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
    console.log(this.cities)
  },
  methods: {
    fetchData() {
      customers().then(response => {
        var data = response.data
        console.log(data)
        this.list = data
      })
    },
    addCustomer() {
      this.dialogAddVisible = false
      this.customer.region = this.selectedCity.join('/')
      recordCustomer(this.customer).then(resp => {
        customers().then(response => {
          var data = response.data
          this.list = data
        })
      })
    },
    handleChange(value) {
      this.selectedCity = value
    }
  }
}
</script>
