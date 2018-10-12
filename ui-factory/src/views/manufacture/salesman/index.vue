<template>
  <div class="dashboard-container">
    <salesman-orders-table />
  </div>
</template>

<script>
import SalesmanOrdersTable from './components/SalesmanOrdersTable'

export default {
  name: 'Salesman',
  components: {
    SalesmanOrdersTable
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
