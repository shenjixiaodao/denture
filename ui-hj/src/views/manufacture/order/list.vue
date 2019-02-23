<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button type="primary" @click="jump">新增</el-button>
    </el-row>
    <el-row style="background:#fff; margin-left:10px;">
      <orders-table />
    </el-row>
  </div>
</template>

<script>
import OrdersTable from './components/OrdersTable'

export default {
  name: 'Order',
  components: {
    OrdersTable
  },
  methods: {
    jump() {
      this.$router.push({ path: '/order/add' })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}
</style>
