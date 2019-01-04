<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <span>开始时段: </span>
      <el-date-picker v-model="queryParams.startTime" type="date" style="width: 200px;" placeholder="开始时间" value-format="yyyy-MM-dd" />
      <span style="font-size: 12px; color: #999">至</span>
      <el-date-picker v-model="queryParams.endTime" type="date" style="width: 200px;" placeholder="结束时间" value-format="yyyy-MM-dd" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search()" >搜索</el-button>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
        <el-table-column label="物料名">
          <template slot-scope="scope">
            {{ scope.row.ingredientName }}
          </template>
        </el-table-column>
        <el-table-column label="类型">
          <template slot-scope="scope">
            {{ scope.row.ingredientType }}
          </template>
        </el-table-column>
        <el-table-column label="领用数量" align="center">
          <template slot-scope="scope">
            {{ scope.row.appliedNumber }}
          </template>
        </el-table-column>
        <el-table-column label="使用数量" align="center">
          <template slot-scope="scope">
            {{ scope.row.usedNumber }}
          </template>
        </el-table-column>
        <el-table-column label="余量" align="center">
          <template slot-scope="scope">
            {{ scope.row.balance }}
          </template>
        </el-table-column>
        <el-table-column label="物料详情">
          <template slot-scope="scope">
            <router-link :to="'ingredient/'+scope.row.ingredientId" class="link-type">
              <span>详情</span>
            </router-link>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
import { aggregateAppliedUsedIngredient } from '@/api/comprehensive'

export default {
  name: 'IngredientAggregate',
  data() {
    return {
      list: null,
      queryParams: {
        startTime: null,
        endTime: null
      }
    }
  },
  methods: {
    search() {
      aggregateAppliedUsedIngredient(this.queryParams).then(response => {
        var data = response.data
        this.list = data
      })
    }
  }
}
</script>
