<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button type="primary" @click="dialogAddVisible = true">新增</el-button>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
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
            <router-link :to="'customer/'+scope.row.id" class="link-type">
              <span>详情</span>
            </router-link>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-dialog :visible.sync="dialogAddVisible" title="添加客户">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="诊所名" prop="title">
          <el-input v-model="customer.name" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="诊所地址" prop="title">
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

export default {
  data() {
    return {
      list: null,
      dialogAddVisible: false,
      customer: {
        name: null,
        address: null,
        contact: null,
        dentistName: null
      }
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
    },
    addCustomer() {
      this.dialogAddVisible = false
      recordCustomer(this.customer).then(resp => {
        customers().then(response => {
          var data = response.data
          this.list = data
        })
      })
    }
  }
}
</script>
