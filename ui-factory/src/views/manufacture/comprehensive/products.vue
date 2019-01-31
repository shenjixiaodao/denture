<template>
  <div class="app-container">
    <el-row style="background:#fff;margin-bottom:20px;">
      <el-button type="primary" @click="dialogAddProductVisible = true">新增</el-button>
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
        <el-table-column label="名称">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="代码">
          <template slot-scope="scope">
            {{ scope.row.code }}
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center">
          <template slot-scope="scope">
            {{ scope.row.gmtCreated | time2DateStr }}
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="deleteProductType(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <el-dialog :visible.sync="dialogAddProductVisible" title="添加产品类别">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="名称" prop="title">
          <el-input v-model="productType.name" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="代码" prop="title">
          <el-input v-model="productType.code" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddProductVisible = false">取消</el-button>
        <el-button type="primary" @click="addProductType">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { findProductTypes, addProductType, deleteProductType } from '@/api/comprehensive'

export default {
  data() {
    return {
      list: null,
      dialogAddProductVisible: false,
      productType: {
        name: null,
        code: null
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      findProductTypes().then(response => {
        var data = response.data
        console.log(data)
        this.list = data
      })
    },
    addProductType() {
      addProductType(this.productType).then(response => {
        // 刷新页面
        findProductTypes().then(response => {
          var data = response.data
          console.log(data)
          this.list = data
        })
        this.dialogAddProductVisible = false
      })
    },
    deleteProductType(id) {
      this.$confirm('此操作将永久删除记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteProductType({ id: id }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        })
        // 刷新页面
        findProductTypes().then(response => {
          var data = response.data
          console.log(data)
          this.list = data
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>
