<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <table>
        <tbody>
          <tr>
            <td class="td_title_prop">物料名:</td><td class="td_content_prop">{{ ingredientDetail.name }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">类型:</td><td class="td_content_prop">{{ ingredientDetail.type }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">代号:</td><td class="td_content_prop">{{ ingredientDetail.code }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">单位:</td><td class="td_content_prop">{{ ingredientDetail.unit }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">规格:</td><td class="td_content_prop">{{ ingredientDetail.spec }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">次规格:</td><td class="td_content_prop">{{ ingredientDetail.subSpec }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">库存:</td><td class="td_content_prop">{{ ingredientDetail.balance }}</td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <div style="margin:0 0 5px 20px">采购记录</div>
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

    <div style="margin:0 0 5px 20px">领用记录</div>
    <IngredientStatPanel :id="id" />
    <el-row style="background:#fff;padding:10px 10px 0;margin-bottom:32px;">
      <el-table :data="appliedRecords" style="width: 100%;padding-top: 5px;">
        <el-table-column label="领用时间" align="center">
          <template slot-scope="scope">
            {{ scope.row.appliedDate | time2DateStr }}
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

  </div>
</template>

<script>
import { queryIngredientById, querySuppliers } from '@/api/comprehensive'
import IngredientStatPanel from './IngredientStatPanel'

export default {
  components: {
    IngredientStatPanel
  },
  data() {
    return {
      id: null,
      ingredientDetail: null,
      purchaseRecords: null,
      appliedRecords: null,
      dialogAddIngredientVisible: false,
      suppliers: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.id = Number(this.$route.params && this.$route.params.id)
      queryIngredientById(this.id).then(response => {
        var data = response.data
        this.ingredientDetail = data
        this.purchaseRecords = this.ingredientDetail.records
        this.appliedRecords = this.ingredientDetail.appliedIngredients
      })
      querySuppliers().then(response => {
        const data = response.data
        this.suppliers = data
      })
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
</style>
