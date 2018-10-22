<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <div class="panel panel-primary">
        <table class="table table-bordered table-striped text-center">
          <tbody>
            <tr>
              <td>物料名</td><td>{{ ingredientDetail.name }}</td>
            </tr>
            <tr>
              <td>库存</td><td>{{ ingredientDetail.balance }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button type="primary" @click="dialogAddIngredientVisible = true">添加物料</el-button>
      <el-button type="primary" @click="dialogAddVisible = true">添加供应商</el-button>
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-table :data="purchaseRecords" style="width: 100%;padding-top: 15px;">
        <el-table-column label="入库时间">
          <template slot-scope="scope">
            {{ scope.row.gmtCreated }}
          </template>
        </el-table-column>
        <el-table-column label="库存" align="center">
          <template slot-scope="scope">
            {{ scope.row.number }}
          </template>
        </el-table-column>
        <el-table-column label="供应商" align="center">
          <template slot-scope="scope">
            {{ scope.row.supplierId }}
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <el-dialog :visible.sync="dialogAddIngredientVisible" title="添加新材料">
      <el-form ref="dataForm" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="供应商" prop="title">
          <el-select v-model="ingredient.supplierId" placeholder="类型" clearable style="width: 90px" class="filter-item">
            <el-option v-for="item in suppliers" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="购入数量" prop="title">
          <el-input v-model="ingredient.number"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddIngredientVisible = false">取消</el-button>
        <el-button type="primary" @click="addIngredient">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogAddIngredientVisible" title="添加新供应商">
      <el-form ref="dataForm" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="名称" prop="title">
          <el-input v-model="supplier.name"/>
        </el-form-item>
        <el-form-item label="地址" prop="title">
          <el-input v-model="supplier.address"/>
        </el-form-item>
        <el-form-item label="联系方式" prop="title">
          <el-input v-model="supplier.contact"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddSupplierVisible = false">取消</el-button>
        <el-button type="primary" @click="addIngredient">提交</el-button>
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
      dialogAddIngredientVisible: false,
      dialogAddSupplierVisible: false,
      ingredient: {
        ingredientId: null,
        supplierId: null,
        number: null
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
      })
    },
    addIngredient() {
      querySuppliers().then(response => {
        var data = response.data
        this.suppliers = data
      })
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
      })
    }
  }
}
</script>
