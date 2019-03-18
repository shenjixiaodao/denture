<template>
  <div>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:5px;">
      <span style="color: #999;font-size: 14px;">企业名: </span>
      <el-input v-model="queryParam.name" style="width: 200px;"/>
      <span style="color: #999;font-size: 14px;">类型: </span>
      <el-select v-model="queryParam.type" placeholder="产品类型" clearable class="filter-item">
        <el-option v-for="item in types" :key="item" :label="item" :value="item"/>
      </el-select>
      <span style="color: #999;font-size: 14px;">所在省: </span>
      <el-input v-model="queryParam.province" style="width: 200px;"/>
      <span style="color: #999;font-size: 14px;">所在县(区): </span>
      <el-input v-model="queryParam.country" style="width: 200px;"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search" >搜索</el-button>
    </el-row>
    <el-row>
      <el-table :data="list">
        <el-table-column type="index" />
        <el-table-column label="公司名">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="产品类型">
          <template slot-scope="scope">
            {{ scope.row.type }}
          </template>
        </el-table-column>
        <el-table-column label="注册资本">
          <template slot-scope="scope">
            {{ scope.row.capital }}
          </template>
        </el-table-column>
        <el-table-column label="所在省">
          <template slot-scope="scope">
            {{ scope.row.province }}
          </template>
        </el-table-column>
        <el-table-column label="所在市">
          <template slot-scope="scope">
            {{ scope.row.city }}
          </template>
        </el-table-column>
        <el-table-column label="所在县(区)">
          <template slot-scope="scope">
            {{ scope.row.country }}
          </template>
        </el-table-column>
        <el-table-column label="公开地址">
          <template slot-scope="scope">
            {{ scope.row.public_address }}
          </template>
        </el-table-column>
        <el-table-column label="公开手机">
          <template slot-scope="scope">
            {{ scope.row.public_phone }}
          </template>
        </el-table-column>
        <el-table-column label="公开座机">
          <template slot-scope="scope">
            {{ scope.row.public_telephone }}
          </template>
        </el-table-column>
        <el-table-column label="营业内容">
          <template slot-scope="scope">
            {{ scope.row.business }}
          </template>
        </el-table-column>
        <el-table-column label="客户详情" align="center">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="handleDetail(scope.row)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination :total="total" :page.sync="queryParam.page" :limit.sync="queryParam.limit" @pagination="fetchData" />
    </el-row>
  </div>
</template>

<script>
import { findPublicCustomers, checkNameCustomer } from '@/api/customer'
import Pagination from '@/components/Pagination'

export default {
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      queryParam: {
        name: null,
        province: null,
        country: null,
        page: 1,
        limit: 20,
        sort: '+id'
      },
      types: ['铁合金', '黄磷', '工业硅', '硅铁']
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      findPublicCustomers(this.queryParam).then(response => {
        this.total = response.totalSize
        this.list = response.data
      })
    },
    search() {
      this.queryParam.page = 1
      this.queryParam.limit = 20
      findPublicCustomers(this.queryParam).then(response => {
        this.total = response.totalSize
        this.list = response.data
      })
    },
    handleDetail(row) {
      checkNameCustomer({ name: row.name }).then(response => {
        var data = response.data
        this.$router.push({ path: 'customer/' + data.id })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
</style>
