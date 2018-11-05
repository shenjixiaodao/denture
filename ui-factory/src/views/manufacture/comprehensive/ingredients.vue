<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button type="primary" @click="dialogAddVisible = true">新增</el-button>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
        <el-table-column label="名称">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="库存" align="center">
          <template slot-scope="scope">
            {{ scope.row.balance }}
          </template>
        </el-table-column>
        <el-table-column label="详情" align="center">
          <template slot-scope="scope">
            <router-link :to="'ingredient/'+scope.row.id" class="link-type">
              <span>详情</span>
            </router-link>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <el-dialog :visible.sync="dialogAddVisible" title="添加新材料">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="物料名称" prop="title">
          <el-input v-model="name" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click="newIngredient">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { queryIngredients, newIngredient } from '@/api/comprehensive'
import { isStringNull } from '@/utils/validate'
import { Message } from 'element-ui'

export default {
  name: 'Ingredients',
  data() {
    return {
      list: null,
      dialogAddVisible: false,
      name: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      queryIngredients().then(response => {
        var data = response.data
        console.log(data)
        this.list = data
      })
    },
    newIngredient() {
      if (isStringNull(this.name)) {
        return Message({
          message: '物料名称不能为空',
          type: 'error',
          duration: 1000
        })
      }
      newIngredient(this.name).then(response1 => {
        this.dialogAddVisible = false
        Message({
          message: '新增成功',
          type: 'success',
          duration: 1000
        })
        this.fetchData()
      })
    }
  }
}
</script>
