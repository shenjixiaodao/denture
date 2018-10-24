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
      </el-table>
    </el-row>

    <el-dialog :visible.sync="dialogAddVisible" title="添加客户">
      <el-form ref="dataForm" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="客户编号" prop="title">
          <el-input v-model="clinicId"/>
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
import { customers, addCustomer } from '@/api/salesman'

export default {
  data() {
    return {
      list: null,
      dialogAddVisible: false,
      customer: {
        clinicId: null
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
      addCustomer(this.customer).then(resp => {
        customers().then(response => {
          var data = response.data
          this.list = data
        })
      })
    }
  }
}
</script>
