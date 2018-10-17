<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-input v-model="dentureId" placeholder="产品编号" style="width: 200px;" class="filter-item" />
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" >扫描</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search()" >搜索</el-button>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
        <el-table-column label="订单日期" min-width="200">
          <template slot-scope="scope">
            {{ scope.row.createdDate }}
          </template>
        </el-table-column>
        <el-table-column label="类型" min-width="200">
          <template slot-scope="scope">
            {{ scope.row.type }}
          </template>
        </el-table-column>
        <el-table-column label="规格" min-width="200">
          <template slot-scope="scope">
            {{ scope.row.specification }}
          </template>
        </el-table-column>
        <el-table-column label="义齿详情" width="100" align="center">
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
import { queryDenturesByStatus } from '@/api/comprehensive'
import { queryByDentureId } from '@/api/common'
import { isvalidDentureId, isNull } from '@/utils/validate'
import { Message } from 'element-ui'

export default {
  name: 'ComprehensiveDone',
  data() {
    return {
      list: null,
      dentureId: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      queryDenturesByStatus('Done').then(response => {
        var data = response.data
        console.log(data)
        this.list = data
      })
    },
    search() {
      if (!isvalidDentureId(this.dentureId)) {
        return Message({
          message: '不是合法产品ID',
          type: 'error',
          duration: 2 * 1000
        })
      }
      queryByDentureId(this.dentureId).then(response => {
        var data = response.data
        if (isNull(data)) {
          this.list = []
        } else {
          this.list = [data]
        }
      })
    }
  }
}
</script>
