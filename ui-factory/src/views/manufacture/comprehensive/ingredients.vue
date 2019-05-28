<template>
  <div class="app-container">

    <div style="margin:0 0 5px 20px">供应商列表</div>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button type="primary" @click="dialogAddSupplierVisible = true">新增</el-button>
      <el-table :data="suppliers" style="width: 100%;padding-top: 15px;">
        <el-table-column label="名称">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="联系人" align="center">
          <template slot-scope="scope">
            {{ scope.row.contacter }}
          </template>
        </el-table-column>
        <el-table-column label="联系方式" align="center">
          <template slot-scope="scope">
            {{ scope.row.contact }}
          </template>
        </el-table-column>
        <el-table-column label="手机" align="center">
          <template slot-scope="scope">
            {{ scope.row.phone }}
          </template>
        </el-table-column>
        <el-table-column label="地址" align="center">
          <template slot-scope="scope">
            {{ scope.row.address }}
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <TotalStatPanel />
    <div style="margin:0 0 5px 20px">物料列表</div>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button type="primary" @click="dialogAddVisible = true">新增</el-button>
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
        <el-table-column label="名称">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="型号">
          <template slot-scope="scope">
            {{ scope.row.type }}
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
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="primary" @click="buyIngredient(scope.row.id)">物料入库</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

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
        <el-form-item label="联系人" prop="title">
          <el-input v-model="supplier.contacter" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="手机" prop="title">
          <el-input v-model="supplier.phone" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddSupplierVisible = false">取消</el-button>
        <el-button type="primary" @click="newSupplier">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogAddVisible" title="添加新材料">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="名称" prop="title">
          <el-input v-model="ingredient.name" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="类型" prop="title">
          <el-input v-model="ingredient.type" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="代号" prop="title">
          <el-input v-model="ingredient.code" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="单位" prop="title">
          <el-input v-model="ingredient.unit" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="规格" prop="title">
          <el-input v-model="ingredient.spec" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="次规格" prop="title">
          <el-input v-model="ingredient.subSpec" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="平衡偏差范围" prop="title">
          <el-input v-model="ingredient.equalityRateRange" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click="newIngredient">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogAddIngredientVisible" title="入库新采购物料">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="供应商" prop="title">
          <el-select v-model="boughtIngredient.supplierId" placeholder="请选择" clearable style="width: 90px" class="filter-item">
            <el-option v-for="item in suppliers" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="采购单号" prop="title">
          <el-input v-model="boughtIngredient.billNo" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="购入数量" prop="title">
          <el-input v-model="boughtIngredient.number" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="购入单价" prop="title">
          <el-input v-model="boughtIngredient.price" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddIngredientVisible = false">取消</el-button>
        <el-button type="primary" @click="addIngredient">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { queryIngredients, newIngredient, addIngredient, newSupplier, querySuppliers } from '@/api/comprehensive'
import { isStringNull, isNull } from '@/utils/validate'
import { Message } from 'element-ui'
import TotalStatPanel from './components/TotalStatPanel'

export default {
  name: 'Ingredients',
  components: {
    TotalStatPanel
  },
  data() {
    return {
      list: null,
      dialogAddSupplierVisible: false,
      dialogAddVisible: false,
      dialogAddIngredientVisible: false,
      ingredient: {
        name: null,
        type: null,
        equalityRateRange: null,
        code: null,
        unit: null,
        spec: null,
        subSpec: null
      },
      supplier: {
        name: null,
        address: null,
        contact: null,
        contacter: null,
        phone: null
      },
      suppliers: null,
      boughtIngredient: {
        ingredientId: null,
        supplierId: null,
        billNo: null,
        number: null,
        price: null
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      queryIngredients().then(response => {
        const data = response.data
        console.log(data)
        this.list = data
      })
      querySuppliers().then(response => {
        var data = response.data
        this.suppliers = data
      })
    },
    newIngredient() {
      if (isStringNull(this.ingredient.name)) {
        return Message({
          message: '物料名称不能为空',
          type: 'error',
          duration: 1000
        })
      }
      newIngredient(this.ingredient).then(response1 => {
        this.dialogAddVisible = false
        Message({
          message: '新增成功',
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
    },
    buyIngredient(id) {
      this.boughtIngredient.ingredientId = id
      this.dialogAddIngredientVisible = true
    },
    addIngredient() {
      if (isNull(this.suppliers)) {
        return Message({
          message: '请先添加供应商',
          type: 'error',
          duration: 1000
        })
      }
      addIngredient(this.boughtIngredient).then(response => {
        this.dialogAddIngredientVisible = false
        Message({
          message: '录入成功',
          type: 'success',
          duration: 1000
        })
        this.fetchData()
      })
    }
  }
}
</script>
