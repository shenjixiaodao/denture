<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-input v-model="queryParams.dentureId" placeholder="产品编号" style="width: 200px;" class="filter-item" />
      <!--<el-input v-model="queryParams.region" placeholder="区域" style="width: 200px;" class="filter-item" />-->
      <el-cascader :options="cities" :props="props" placeholder="省/市" style="width: 200px;" @change="handleChange"/>
      <el-date-picker v-model="queryParams.createdDate" type="date" style="width: 200px;" placeholder="订单创建日" value-format="yyyy-MM-dd" />
      <!--<el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" >扫描</el-button>-->
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search()" >搜索</el-button>
      <el-button type="primary" @click="jump">新增</el-button>
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
import { queryDentures } from '@/api/comprehensive'
// import { isvalidDentureId } from '@/utils/validate'
// import { Message } from 'element-ui'
import { cities } from '@/utils/allCities'

export default {
  name: 'Dentures',
  data() {
    return {
      list: null,
      cities: cities,
      props: {
        value: 'name',
        label: 'name',
        children: 'sub'
      },
      queryParams: {
        dentureId: null,
        patientName: null,
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
      queryDentures(this.queryParams).then(response => {
        var data = response.data
        console.log(data)
        this.list = data
      })
    },
    search() {
      /* if (!isvalidDentureId(this.queryParams.dentureId)) {
        return Message({
          message: '不是合法产品ID',
          type: 'error',
          duration: 2 * 1000
        })
      } */
      queryDentures(this.queryParams).then(response => {
        var data = response.data
        this.list = data
      })
    },
    handleChange(value) {
      this.queryParams.region = value.join('/')
    },
    jump() {
      this.$router.push({ path: '/comprehensive/denture/add' })
    }
  }
}
</script>
