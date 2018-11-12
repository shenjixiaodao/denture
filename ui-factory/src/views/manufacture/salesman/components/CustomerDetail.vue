<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <table style="text-align: right">
        <tbody>
          <tr>
            <td class="td_title_prop">名称:</td><td class="td_content_prop">{{ customer.clinic.name }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">地址:</td><td class="td_content_prop">{{ customer.clinic.address }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">联系方式:</td><td class="td_content_prop">{{ customer.clinic.contact }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">保修:</td>
            <td class="td_content_prop">
              <label style="font-size: 10px;">固定类:</label><el-input v-model="customer.fixedGuarantee" style="width: 40px;"/>年&nbsp;&nbsp;
              <label style="font-size: 10px;">活动类:</label><el-input v-model="customer.mobilizableGuarantee" style="width: 40px;"/>年
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button type="primary" @click="dialogAddVisible = true">新增</el-button>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <!--<el-button type="primary" @click="dialogAddVisible = true">添加成员</el-button>-->
      <el-table :data="customer.clinic.users" style="width: 100%;padding-top: 15px;">
        <el-table-column label="姓名">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="职称" align="center">
          <template slot-scope="scope">
            {{ scope.row.role }}
          </template>
        </el-table-column>
        <el-table-column label="联系方式" align="center">
          <template slot-scope="scope">
            {{ scope.row.contact }}
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <el-dialog :visible.sync="dialogAddVisible" title="添加客户">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="姓名" prop="title">
          <el-input v-model="member.name" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="职称" prop="title">
          <el-input v-model="member.role" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="联系方式" prop="title">
          <el-input v-model="member.contact" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click="addCustomer">提交</el-button>
      </div>
    </el-dialog>

    <!--<el-dialog :visible.sync="dialogAddVisible" title="添加成员">
      <el-form ref="dataForm" label-position="left" style="width: 400px; margin-left:50px;">
        <el-form-item label="姓名" prop="title">
          <el-input v-model="member.name"/>
        </el-form-item>
        <el-form-item label="职称" prop="title">
          <el-input v-model="member.role"/>
        </el-form-item>
        <el-form-item label="联系方式" prop="title">
          <el-input v-model="member.contact"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click="addMember">添加</el-button>
      </div>
    </el-dialog>-->

  </div>

</template>

<script>
import { customer } from '@/api/salesman'

export default {
  data() {
    return {
      customer: null,
      member: {
        name: null,
        role: null,
        contact: null
      },
      appliedIngredients: [],
      dialogAddVisible: false
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      const id = this.$route.params && this.$route.params.id
      console.log('customer detail ==> ' + id)
      customer(id).then(response => {
        var data = response.data
        this.customer = data
        console.log(this.customer)
      })
    },
    addMember() {
      this.dialogAddVisible = false
      /* queryIngredients().then(response => {
        var data = response.data
        this.ingredients = data
      }) */
    }
  }
}
</script>
<style lang="scss" scoped>
  @import "@/styles/common.scss";
</style>
