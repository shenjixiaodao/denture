<template>
  <div class="app-container">
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="品类建档" name="ProductList">
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
            <el-table-column label="材质规格">
              <template slot-scope="scope">
                {{ scope.row.type | code2Name(types) }}
              </template>
            </el-table-column>
            <el-table-column label="创建时间" align="center">
              <template slot-scope="scope">
                {{ scope.row.gmtCreated | time2DateStr }}
              </template>
            </el-table-column>
            <!--<el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button size="mini" type="danger" @click="deleteProductType(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>-->
          </el-table>
        </el-row>
      </el-tab-pane>
      <el-tab-pane label="付款方式建档" name="SettlementTypeList">
        <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
          <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 40%;">
            <el-form-item label="代号" prop="title">
              <el-input v-model="settlementType.code"/>
            </el-form-item>
            <el-form-item label="名称" prop="title">
              <el-input v-model="settlementType.name"/>
            </el-form-item>
            <el-button class="filter-item" type="primary" icon="el-icon-search" @click="addSettlementType()" >新建</el-button>
          </el-form>
        </el-row>
        <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
          <el-table :data="settlementTypes" style="width: 100%;padding-top: 15px;">
            <el-table-column label="代号">
              <template slot-scope="scope">
                {{ scope.row.code }}
              </template>
            </el-table-column>
            <el-table-column label="名称" >
              <template slot-scope="scope">
                {{ scope.row.name }}
              </template>
            </el-table-column>
          </el-table>
        </el-row>
      </el-tab-pane>
      <el-tab-pane label="注册证号建档" name="CertificateList">
        <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
          <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 40%;">
            <el-form-item label="代号" prop="title">
              <el-input v-model="certification.code"/>
            </el-form-item>
            <el-form-item label="名称" prop="title">
              <el-input v-model="certification.name"/>
            </el-form-item>
            <el-button class="filter-item" type="primary" icon="el-icon-search" @click="addCertification()" >新建</el-button>
          </el-form>
        </el-row>
        <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
          <el-table :data="certifications" style="width: 100%;padding-top: 15px;">
            <el-table-column label="代号">
              <template slot-scope="scope">
                {{ scope.row.code }}
              </template>
            </el-table-column>
            <el-table-column label="名称" >
              <template slot-scope="scope">
                {{ scope.row.name }}
              </template>
            </el-table-column>
          </el-table>
        </el-row>
      </el-tab-pane>
    </el-tabs>

    <el-dialog :visible.sync="dialogAddProductVisible" title="添加产品类别">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="名称" prop="title">
          <el-input v-model="productType.name" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="代码" prop="title">
          <el-input v-model="productType.code" style="width: 70%;"/>
        </el-form-item>
        <el-select v-model="productType.type" placeholder="材质规格" clearable class="filter-item">
          <el-option v-for="item in types" :key="item.code" :label="item.name" :value="item.code"/>
        </el-select>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddProductVisible = false">取消</el-button>
        <el-button type="primary" @click="addProductType">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { findProductTypes, addProductType, deleteProductType, addSettlement, addCertification } from '@/api/comprehensive'
import { findSettlementTypes, findCertifications } from '@/api/common'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      list: null,
      activeName: 'ProductList',
      dialogAddProductVisible: false,
      productType: {
        name: null,
        code: null,
        type: null
      },
      types: [
        { code: 'Fixed', name: '定制式固定义齿' }, { code: 'Mobilizable', name: '定制式活动义齿' }
      ],
      settlementTypes: null,
      settlementType: {
        code: null,
        name: null
      },
      certifications: null,
      certification: {
        code: null,
        name: null
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      findProductTypes().then(response => {
        const data = response.data
        this.list = data
      })
      findSettlementTypes().then(response => {
        const data = response.data
        this.settlementTypes = data
      })
      findCertifications().then(response => {
        const data = response.data
        this.certifications = data
      })
    },
    addCertification() {
      if (!this.certification.code) {
        return Message({
          message: '证件代号不能为空',
          type: 'error',
          duration: 2 * 1000
        })
      }
      if (!this.certification.name) {
        return Message({
          message: '证件名称不能为空',
          type: 'error',
          duration: 2 * 1000
        })
      }
      addCertification(this.certification).then(() => {
        // 刷新页面
        findCertifications().then(response => {
          const data = response.data
          this.certifications = data
        })
      })
    },
    addSettlementType() {
      if (!this.settlementType.code) {
        return Message({
          message: '付款代号不能为空',
          type: 'error',
          duration: 2 * 1000
        })
      }
      if (!this.settlementType.name) {
        return Message({
          message: '付款名称不能为空',
          type: 'error',
          duration: 2 * 1000
        })
      }
      addSettlement(this.settlementType).then(() => {
        // 刷新页面
        findSettlementTypes().then(response => {
          const data = response.data
          this.settlementTypes = data
        })
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
    },
    handleClick(tab, event) {
      //
    }
  }
}
</script>
