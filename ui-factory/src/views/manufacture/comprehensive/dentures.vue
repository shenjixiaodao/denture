<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-input v-model="queryParams.dentureId" placeholder="产品编号" style="width: 200px;" class="filter-item" />
      <el-select v-model="queryParams.status" filterable clearable placeholder="入检状态" class="filter-item">
        <el-option v-for="item in status" :key="item.code" :label="item.name" :value="item.code"/>
      </el-select>
      <el-cascader :options="cities" :props="props" filterable clearable placeholder="省/市" style="width: 200px;" @change="handleChange"/>
      <el-date-picker v-model="queryParams.createdDate" type="date" style="width: 200px;" placeholder="订单创建日" value-format="yyyy-MM-dd" />
      <!--<el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" >扫描</el-button>-->
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search()" >搜索</el-button>
      <el-button type="primary" @click="jump">新增</el-button>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-table :data="list" style="width: 100%;padding-top: 15px;">
        <el-table-column label="义齿编号">
          <template slot-scope="scope">
            {{ scope.row.id | id2Short }}
          </template>
        </el-table-column>
        <el-table-column label="类型">
          <template slot-scope="scope">
            {{ scope.row.type }}
          </template>
        </el-table-column>
        <el-table-column label="品类">
          <template slot-scope="scope">
            {{ scope.row.specification }}
          </template>
        </el-table-column>
        <el-table-column label="定制方">
          <template slot-scope="scope">
            {{ scope.row.clinic.name }}
          </template>
        </el-table-column>
        <el-table-column label="入检日期">
          <template slot-scope="scope">
            {{ scope.row.createdDate | time2DateStr }}
          </template>
        </el-table-column>
        <el-table-column label="入检状态">
          <template slot-scope="scope">
            {{ scope.row.status }}
          </template>
        </el-table-column>
        <el-table-column label="阶段">
          <template slot-scope="scope">
            {{ scope.row.stage }}
          </template>
        </el-table-column>
        <el-table-column label="医生">
          <template slot-scope="scope">
            {{ scope.row.dentist }}
          </template>
        </el-table-column>
        <el-table-column label="患者">
          <template slot-scope="scope">
            {{ scope.row.patientName }}
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
      <pagination :total="total" :page.sync="queryParams.page" :limit.sync="queryParams.limit" @pagination="fetchData" />
    </el-row>
  </div>
</template>

<script>
import { queryDentures } from '@/api/comprehensive'
// import { isvalidDentureId } from '@/utils/validate'
// import { Message } from 'element-ui'
import { cities } from '@/utils/allCities'
import Pagination from '@/components/Pagination'

export default {
  name: 'Dentures',
  components: { Pagination },
  data() {
    return {
      list: null,
      cities: cities,
      props: {
        value: 'name',
        label: 'name',
        children: 'sub'
      },
      status: [
        { code: 'Invalid', name: '无效' },
        { code: 'Created', name: '新入检' },
        { code: 'Producing', name: '待出货' },
        { code: 'Delivered', name: '已出货' },
        { code: 'Remaking', name: '返厂' }
      ],
      queryParams: {
        page: 1,
        limit: 20,
        dentureId: null,
        patientName: null,
        createdDate: null,
        region: null,
        clinicName: null,
        status: null
      },
      total: 0
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      queryDentures(this.queryParams).then(response => {
        this.total = response.totalSize
        this.list = response.data
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
