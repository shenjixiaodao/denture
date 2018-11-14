<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <table>
        <tbody>
          <tr>
            <td class="td_title_prop">物料名:</td><td class="td_content_prop">{{ ingredientDetail.name }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">库存:</td><td class="td_content_prop">{{ ingredientDetail.balance }}</td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:10px;">
      <el-button type="primary" @click="dialogAddIngredientVisible = true">添加物料</el-button>
      <el-button type="primary" @click="dialogAddSupplierVisible = true">添加供应商</el-button>
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-table :data="purchaseRecords" style="width: 100%;padding-top: 15px;">
        <el-table-column label="采购时间">
          <template slot-scope="scope">
            {{ scope.row.gmtCreated.split(' ',2)[0] }}
          </template>
        </el-table-column>
        <el-table-column label="购入单价" align="center">
          <template slot-scope="scope">
            {{ scope.row.price }}
          </template>
        </el-table-column>
        <el-table-column label="购入量" align="center">
          <template slot-scope="scope">
            {{ scope.row.number }}
          </template>
        </el-table-column>
        <el-table-column label="供应商" align="center">
          <template slot-scope="scope">
            {{ scope.row.supplierId }}
          </template>
        </el-table-column>
        <el-table-column label="采购单号" align="center">
          <template slot-scope="scope">
            {{ scope.row.billNo }}
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-table :data="appliedRecords" style="width: 100%;padding-top: 15px;">
        <el-table-column label="领用时间" align="center">
          <template slot-scope="scope">
            {{ scope.row.appliedDate.split(' ',2)[0] }}
          </template>
        </el-table-column>
        <el-table-column label="领用数量" align="center">
          <template slot-scope="scope">
            {{ scope.row.appliedNumber }}
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center">
          <template slot-scope="scope">
            {{ scope.row.comment }}
          </template>
        </el-table-column>
        <el-table-column label="加工义齿" align="center">
          <template slot-scope="scope">
            <router-link :to="'/comprehensive/denture/'+scope.row.dentureId" class="link-type">
              <span>详情</span>
            </router-link>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <el-dialog :visible.sync="dialogAddIngredientVisible" title="添加新材料">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="供应商" prop="title">
          <el-select v-model="ingredient.supplierId" placeholder="请选择" clearable style="width: 90px" class="filter-item">
            <el-option v-for="item in suppliers" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="采购单号" prop="title">
          <el-input v-model="ingredient.billNo" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="购入数量" prop="title">
          <el-input v-model="ingredient.number" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="购入单价" prop="title">
          <el-input v-model="ingredient.price" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddIngredientVisible = false">取消</el-button>
        <el-button type="primary" @click="addIngredient">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogAddSupplierVisible" title="添加新供应商">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="名称" prop="title">
          <el-input v-model="supplier.name" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="地址" prop="title">
          <el-input v-model="supplier.address" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="联系方式" prop="title">
          <el-input v-model="supplier.contact" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddSupplierVisible = false">取消</el-button>
        <el-button type="primary" @click="newSupplier">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { queryIngredientById, addIngredient, newSupplier, querySuppliers } from '@/api/comprehensive'
import { Message } from 'element-ui'
import { isNull } from '@/utils/validate'

export default {
  data() {
    return {
      ingredientDetail: null,
      purchaseRecords: null,
      appliedRecords: null,
      dialogAddIngredientVisible: false,
      dialogAddSupplierVisible: false,
      ingredient: {
        ingredientId: null,
        supplierId: null,
        billNo: null,
        number: null,
        price: null
      },
      supplier: {
        name: null,
        address: null,
        contact: null
      },
      suppliers: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      const id = this.$route.params && this.$route.params.id
      console.log('user detail ==> ' + id)
      queryIngredientById(id).then(response => {
        var data = response.data
        this.ingredientDetail = data
        this.purchaseRecords = this.ingredientDetail.records
        this.appliedRecords = this.ingredientDetail.appliedIngredients
        this.ingredient.ingredientId = data.id
      })
      querySuppliers().then(response => {
        var data = response.data
        this.suppliers = data
      })
    },
    addIngredient() {
      if (isNull(this.suppliers)) {
        return Message({
          message: '请先添加供应商',
          type: 'error',
          duration: 1000
        })
      }
      addIngredient(this.ingredient).then(response => {
        this.dialogAddIngredientVisible = false
        Message({
          message: '更新成功',
          type: 'success',
          duration: 1000
        })
        this.fetchData()
      })
    },
    newSupplier() {
      newSupplier(this.supplier).then(response => {
        this.dialogAddSupplierVisible = false
        Message({
          message: '添加成功',
          type: 'success',
          duration: 1000
        })
        querySuppliers().then(response => {
          var data = response.data
          this.suppliers = data
        })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
</style>
