<template>
  <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="入检统计" name="ProductTypeStatPanel"><ProductTypeStatPanel /></el-tab-pane>
    <el-tab-pane label="出货统计" name="OrderAggregate"><order-aggregate /></el-tab-pane>
    <el-tab-pane label="物料领用统计" name="IngredientAggregate"><IngredientAggregate /></el-tab-pane>
    <el-tab-pane label="工资统计" name="SalaryAggregate"><salary-aggregate /></el-tab-pane>
  </el-tabs>
</template>

<script>
import OrderAggregate from './components/OrderAggregate'
import IngredientAggregate from './components/IngredientAggregate'
import SalaryAggregate from './components/SalaryAggregate'
import ProductTypeStatPanel from './components/ProductTypeStatPanel'

export default {
  name: 'Dashboard',
  components: {
    SalaryAggregate,
    OrderAggregate,
    IngredientAggregate,
    ProductTypeStatPanel
  },
  data() {
    return {
      activeName: 'OrderAggregate'
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>
