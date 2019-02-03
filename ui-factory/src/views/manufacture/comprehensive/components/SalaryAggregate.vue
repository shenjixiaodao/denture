<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <span>指定月份: </span>
      <el-date-picker v-model="queryParams.month" type="month" style="width: 200px;" placeholder="月份" value-format="yyyy-MM" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search()" >搜索</el-button>
      <el-button :loading="exportLoading" type="primary" icon="document" @click="handleExport">导出数据</el-button>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
        <el-table-column label="员工编号">
          <template slot-scope="scope">
            {{ scope.row.user.id }}
          </template>
        </el-table-column>
        <el-table-column label="员工姓名">
          <template slot-scope="scope">
            {{ scope.row.user.name }}
          </template>
        </el-table-column>
        <el-table-column label="应发工资" align="center">
          <template slot-scope="scope">
            {{ scope.row.nominalSalary }}
          </template>
        </el-table-column>
        <el-table-column label="实发工资" align="center">
          <template slot-scope="scope">
            {{ scope.row.actualSalary }}
          </template>
        </el-table-column>
        <el-table-column label="提成" align="center">
          <template slot-scope="scope">
            {{ scope.row.commission }}
          </template>
        </el-table-column>
        <el-table-column label="奖罚" align="center">
          <template slot-scope="scope">
            {{ scope.row.rewardPunishment }}
          </template>
        </el-table-column>
        <el-table-column label="月份">
          <template slot-scope="scope">
            {{ scope.row.month }}
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
import { salaryList } from '@/api/comprehensive'

export default {
  name: 'SalaryAggregate',
  data() {
    return {
      list: null,
      queryParams: {
        month: null
      },
      exportLoading: false
    }
  },
  methods: {
    search() {
      salaryList(this.queryParams).then(response => {
        var data = response.data
        this.list = data
      })
    },
    handleExport() {
      this.exportLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['员工编号', '员工姓名', '应发工资', '实发工资', '提成', '奖罚', '月份']
        const filterVal = ['user.id', 'user.name', 'nominalSalary', 'actualSalary', 'commission', 'rewardPunishment', 'month']
        const data = this.formatJson(filterVal, this.list)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: this.filename ? this.filename : '导出数据',
          autoWidth: true,
          bookType: 'xlsx'
        })
        this.exportLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j.indexOf('user.') > -1) {
          var userAttrs = j.split('.')
          return v[userAttrs[0]][userAttrs[1]]
        } else {
          return v[j]
        }
      }))
    }
  }
}
</script>
