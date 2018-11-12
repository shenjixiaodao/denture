<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-input v-model="queryParams.dentureId" placeholder="产品编号" style="width: 200px;" class="filter-item" />
      <el-input v-model="queryParams.region" placeholder="区域" style="width: 200px;" class="filter-item" />
      <!--<el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" >扫描</el-button>-->
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search()" >搜索</el-button>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
        <el-table-column label="订单日期">
          <template slot-scope="scope">
            {{ scope.row.createdDate.split(' ',2)[0] }}
          </template>
        </el-table-column>
        <el-table-column label="类型">
          <template slot-scope="scope">
            {{ scope.row.type }}
          </template>
        </el-table-column>
        <el-table-column label="规格">
          <template slot-scope="scope">
            {{ scope.row.specification }}
          </template>
        </el-table-column>
        <el-table-column label="义齿详情" align="center">
          <template slot-scope="scope">
            <router-link :to="'denture/'+scope.row.id" class="link-type">
              <span>详情</span>
            </router-link>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
import { queryDenturesByCriteria } from '@/api/comprehensive'
import { isvalidDentureId } from '@/utils/validate'
import { Message } from 'element-ui'

export default {
  name: 'ComprehensiveDone',
  data() {
    return {
      list: null,
      dentureId: null,
      queryParams: {
        dentureId: null,
        patientName: null,
        status: 'Done',
        createdDate: null,
        region: null,
        clinicName: null
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      queryDenturesByCriteria(this.queryParams).then(response => {
        var data = response.data
        console.log(data)
        this.list = data
      })
    },
    search() {
      if (!isvalidDentureId(this.queryParams.dentureId)) {
        return Message({
          message: '不是合法产品ID',
          type: 'error',
          duration: 2 * 1000
        })
      }
      queryDenturesByCriteria(this.queryParams).then(response => {
        var data = response.data
        this.list = data
      })
    }
  }
}
</script>
