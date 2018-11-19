<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-select v-model="queryParams.company" placeholder="公司" clearable style="width: 90px" class="filter-item">
        <el-option v-for="item in companies" :key="item.code" :label="item.name" :value="item.code"/>
      </el-select>
      <el-input v-model="queryParams.deliveryId" placeholder="快递单号" style="width: 200px;" class="filter-item" />
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
import { queryDenturesByCriteria, queryByDeliveryId } from '@/api/comprehensive'
import { isStringNull, isNull } from '@/utils/validate'
import { Message } from 'element-ui'

export default {
  name: 'ComprehensiveWait',
  data() {
    return {
      companies: [
        { code: 'ALL', name: '全部' }, { code: 'SF', name: '顺丰' }, { code: 'EMS', name: '邮政特快' },
        { code: 'YT', name: '圆通' }, { code: 'ST', name: '申通' }, { code: 'ZT', name: '中通' },
        { code: 'TT', name: '天天' }, { code: 'YD', name: '韵达' }, { code: 'AskFor', name: '到店取' }
      ],
      queryParams: {
        company: null,
        deliveryId: null,
        dentureId: null,
        patientName: null,
        status: 'Waiting',
        createdDate: null,
        region: null,
        clinicName: null
      },
      list: null
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
      if (isStringNull(this.queryParams.company)) {
        return Message({
          message: '查询物流公司不能为空',
          type: 'error',
          duration: 1000
        })
      }
      if (this.queryParams.company === 'ALL') {
        this.queryParams.deliveryId = null
        this.fetchData()
        return
      }
      if (isStringNull(this.queryParams.deliveryId)) {
        this.queryParams.deliveryId = null
        return Message({
          message: '快递单号不能为空',
          type: 'error',
          duration: 1000
        })
      }
      queryByDeliveryId(this.queryParams).then(response => {
        var data = response.data
        console.log(data)
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
