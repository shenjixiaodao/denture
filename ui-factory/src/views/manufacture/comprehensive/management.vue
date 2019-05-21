<template>
  <div class="app-container">
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="品类档案" name="ProductList">
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
      <el-tab-pane label="付款方式档案" name="SettlementTypeList">
        <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
          <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 40%;">
            <el-form-item label="代号" prop="title">
              <el-input v-model="settlementType.code"/>
            </el-form-item>
            <el-form-item label="名称" prop="title">
              <el-input v-model="settlementType.name"/>
            </el-form-item>
            <el-button class="filter-item" type="primary" @click="addSettlementType()" >新建</el-button>
          </el-form>
        </el-row>
        <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
          <el-table ref="settlementTypeTable" :data="settlementTypes" highlight-current-row style="width: 100%;padding-top: 15px;" @current-change="handleSelectedSettlement">
            <el-table-column label="选中项">
              <template slot-scope="scope">
                <input v-model="selectedSettlement" :value="scope.row.code" name="settlement" type="radio">
              </template>
            </el-table-column>
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
      <el-tab-pane label="公司档案" name="CertificateList">
        <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
          <table style="text-align: right">
            <tbody>
              <tr>
                <td class="td_title_prop">公司名称:</td>
                <td class="td_content_prop"><el-input v-model="factory.name" class="filter-item" /></td>
              </tr>
              <tr>
                <td class="td_title_prop">联系方式:</td>
                <td class="td_content_prop"><el-input v-model="factory.contact" class="filter-item" /></td>
              </tr>
              <tr>
                <td class="td_title_prop">地址:</td>
                <td class="td_content_prop"><el-input v-model="factory.address" class="filter-item" /></td>
              </tr>
              <tr>
                <td class="td_title_prop">注册证号:</td>
                <td class="td_content_prop">{{ factory.certification }}</td>
              </tr>
              <tr><td colspan="2"><el-button class="filter-item" type="primary" @click="modifyFactory" >修改</el-button></td></tr>
            </tbody>
          </table>
        </el-row>
        <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
          <el-button class="filter-item" type="primary" @click="dialogAddCertificationVisible=true" >新建注册证</el-button>
          <el-table ref="certificationTable" :data="certifications" highlight-current-row style="width: 100%;padding-top: 15px;" @current-change="handleSelectedCertification">
            <el-table-column label="选中项">
              <template slot-scope="scope">
                <input v-model="factory.certification" :value="scope.row.name" name="certification" type="radio">
              </template>
            </el-table-column>
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

    <el-dialog :visible.sync="dialogAddCertificationVisible" title="注册证建档">
      <el-form label-position="left" label-width="20%" style="width: 40%;">
        <el-form-item label="注册证代号" prop="title">
          <el-input v-model="certification.code"/>
        </el-form-item>
        <el-form-item label="注册证名称" prop="title">
          <el-input v-model="certification.name"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddCertificationVisible = false">取消</el-button>
        <el-button class="filter-item" type="primary" @click="addCertification()" >新建</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="dialogAddProductVisible" title="添加产品类别">
      <el-form label-position="left" label-width="20%" style="width: 100%;">
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
import { findProductTypes, addProductType, deleteProductType, addSettlement, addCertification, modifyFactory, findFactory } from '@/api/comprehensive'
import { findSettlementTypes, findCertifications } from '@/api/common'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      list: null,
      factory: null,
      activeName: 'ProductList',
      dialogAddProductVisible: false,
      dialogAddCertificationVisible: false,
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
      selectedSettlement: null,
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
      findFactory().then(response => {
        const data = response.data
        this.factory = data
      })
    },
    modifyFactory() {
      this.$confirm('确定修改?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        modifyFactory(this.factory).then(() => {
          // const data = response.data
          return Message({
            message: '修改成功',
            type: 'success',
            duration: 2 * 1000
          })
        })
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
        this.certification.code = null
        this.certifications.name = null
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
        this.settlementType.code = null
        this.settlementType.name = null
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
          const data = response.data
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
    },
    handleSelectedCertification(row) {
      if (!row) {
        return
      }
      this.$confirm('确定切换注册证?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.factory.certification = row.name
        modifyFactory(this.factory).then(() => {
          // const data = response.data
          return Message({
            message: '修改成功',
            type: 'success',
            duration: 2 * 1000
          })
        })
      }).catch(() => {
        this.$refs.certificationTable.setCurrentRow()
      })
    },
    handleSelectedSettlement(row) {
      if (!row) {
        return
      }
      this.$confirm('确定切换付款方式?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.factory.settlement = row.name
        this.factory.settlementCode = row.code
        modifyFactory(this.factory).then(() => {
          return Message({
            message: '修改成功',
            type: 'success',
            duration: 2 * 1000
          })
        })
      }).catch(() => {
        this.$refs.settlementTypeTable.setCurrentRow()
      })
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
</style>
