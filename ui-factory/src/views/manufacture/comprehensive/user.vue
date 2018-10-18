<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
        <el-table-column label="姓名" min-width="200">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="联系方式" min-width="200">
          <template slot-scope="scope">
            {{ scope.row.contact }}
          </template>
        </el-table-column>
        <el-table-column label="详情" min-width="200">
          <template slot-scope="scope">
            <router-link :to="'user/'+scope.row.id" class="link-type">
              <span>详情</span>
            </router-link>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="dialogAddVisible=true">
        添加员工
      </el-button>
    </el-row>

    <el-dialog :visible.sync="dialogAddVisible" title="添加员工">
      <el-form ref="dataForm" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="名字" prop="title">
          <el-input v-model="user.name"/>
        </el-form-item>
        <el-form-item label="手机" prop="title">
          <el-input v-model="user.contact"/>
        </el-form-item>
        <el-form-item label="名字" prop="title">
          <el-select v-model="user.role" placeholder="角色" clearable style="width: 90px" class="filter-item">
            <el-option v-for="item in roles" :key="item.code" :label="item.name" :value="item.code"/>
          </el-select>
        </el-form-item>
        <el-form-item label-width="80px" label="入职时间" class="postInfo-container-item">
          <el-date-picker v-model="user.joinDate" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click="addUser">添加</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { users, addUser } from '@/api/comprehensive'

export default {
  name: 'ComprehensiveUser',
  data() {
    return {
      list: null,
      dialogAddVisible: false,
      user: {
        name: null,
        contact: null,
        role: null,
        joinDate: null
      },
      roles: [
        { code: 'ShiGao', name: '石膏技师' },
        { code: 'ShiGaoLeader', name: '石膏组长' },
        { code: 'LaXing', name: '蜡型技师' },
        { code: 'LaXingLeader', name: '蜡型组长' },
        { code: 'CheJin', name: '车金技师' },
        { code: 'ChejinLeader', name: '车金组长' },
        { code: 'ChongJiao', name: '充胶技师' },
        { code: 'ChongjiaoLeader', name: '充胶组长' },
        { code: 'ShangCi', name: '上瓷技师' },
        { code: 'ShangCiLeader', name: '上瓷组长' },
        { code: 'CheCi', name: '车瓷' },
        { code: 'CheCiLeader', name: '车瓷组长' },
        { code: 'Comprehensive', name: '综合管理人员' },
        { code: 'ComprehensiveLeader', name: '综合部主管' },
        { code: 'Market', name: '市场人员' },
        { code: 'MarketLeader', name: '市场主管' },
        { code: 'Management', name: '管理层' }
      ]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      users().then(response => {
        var data = response.data
        console.log(data)
        this.list = data
      })
    },
    addUser() {
      addUser(this.user).then(response1 => {
        // 刷新用户列表
        users().then(response2 => {
          var data = response2.data
          console.log(data)
          this.list = data
          this.dialogAddVisible = false
        })
      })
    }
  }
}
</script>
