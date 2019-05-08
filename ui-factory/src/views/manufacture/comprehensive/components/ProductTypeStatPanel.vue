<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-select v-model="queryParams.status" filterable clearable placeholder="入检状态" class="filter-item">
        <el-option v-for="item in status" :key="item.code" :label="item.name" :value="item.code"/>
      </el-select>
      <span>月份:</span>
      <el-date-picker v-model="queryParams.month" type="month" style="width: 200px;" value-format="yyyy-MM" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search()" >搜索</el-button>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-input v-model="filename" style="width: 200px;" placeholder="导出数据文件名"/>
      <el-button :loading="exportLoading" type="primary" icon="document" @click="handleExport">导出数据</el-button>
      <el-table :data="list" style="width: 100%;padding-top: 5px;">
        <el-table-column label="品类代码">
          <template slot-scope="scope">
            {{ scope.row.code }}
          </template>
        </el-table-column>
        <el-table-column label="品类名称">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="合计">
          <template slot-scope="scope">
            {{ scope.row.total }}
          </template>
        </el-table-column>
        <el-table-column v-for="day of list[0].days" :key="day" :label="day+''">
          <template slot-scope="scope">
            {{ scope.row[day] }}
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
import { productTypeStatistic } from '@/api/comprehensive'

export default {
  name: 'ProductTypeStatPanel',
  data() {
    return {
      now: new Date().Format('yyyy-MM-dd'),
      list: null,
      queryParams: {
        month: new Date().Format('yyyy-MM'),
        status: null
      },
      status: [
        { code: null, name: '全部' },
        { code: 'Invalid', name: '无效' },
        { code: 'Created', name: '新入检' },
        { code: 'Delivered', name: '已出货' },
        { code: 'Remaking', name: '返厂' }
      ],
      exportLoading: false,
      filename: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.search()
    },
    search() {
      productTypeStatistic(this.queryParams).then(response => {
        var data = response.data
        this.list = data
      })
    },
    handleExport() {
      this.exportLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['品类代码', '品类名称', '合计']
        const filterVal = ['code', 'name', 'total']
        for (var day = 1; day <= this.list[0].days; day++) {
          tHeader.push(day)
          filterVal.push(day)
        }
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
        return v[j]
      }))
    }
  }
}
</script>
