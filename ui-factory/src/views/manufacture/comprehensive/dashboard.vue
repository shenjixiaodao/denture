<template>
  <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="订单记录" name="OrderAggregate"><order-aggregate /></el-tab-pane>
    <el-tab-pane label="物料领用记录" name="IngredientAggregate"><IngredientAggregate /></el-tab-pane>
  </el-tabs>
</template>

<script>
import OrderAggregate from './components/OrderAggregate'
import IngredientAggregate from './components/IngredientAggregate'

export default {
  name: 'Dashboard',
  components: {
    OrderAggregate,
    IngredientAggregate
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
