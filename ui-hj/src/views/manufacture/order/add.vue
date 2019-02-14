<template>
  <div class="dashboard-container" style="overflow:auto;">
    <el-row style="background:#fff;padding:10px 10px 0;">
      <el-form ref="dataForm" label-position="left" label-width="20%">
        <el-form-item label="下单方" prop="title">
          <el-cascader :options="clinics" v-model="selectedClinic" :props="props" placeholder="诊所/医生" filterable @change="handleChange"/>
        </el-form-item>
        <el-form-item label="数量" prop="title">
          <el-input v-model="order.number" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="种类名称" prop="title">
          <!--<el-input v-model="order.specification" style="width: 70%;"/>-->
          <el-select v-model="order.specification" :filter-method="filterMethod" filterable placeholder="类型" class="filter-item">
            <el-option v-for="item in specificationOptions" :key="item.code" :label="item.name" :value="item.code"/>
          </el-select>
          <router-link :to="'/comprehensive/products'" class="link-type">
            <span>详情</span>
          </router-link>
        </el-form-item>
        <!-- 材质规格 -->
        <el-form-item label="材质规格" prop="title">
          <el-select v-model="order.type" placeholder="类型" clearable class="filter-item">
            <el-option v-for="item in types" :key="item.code" :label="item.name" :value="item.code"/>
          </el-select>
        </el-form-item>
        <el-form-item label="色号" prop="title">
          <el-input v-model="order.colorNo" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="制作要求" prop="title">
          <el-input v-model="order.requirement" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="备注">
          <el-input :autosize="{ minRows: 2, maxRows: 4}" v-model="order.comment" type="textarea" placeholder="请输入" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="缺牙区" prop="title">
          <el-select v-model="order.fieldType" placeholder="类型" clearable style="width: 90px" class="filter-item">
            <el-option v-for="item in fieldTypes" :key="item.code" :label="item.name" :value="item.code"/>
          </el-select>
        </el-form-item>
        <el-form-item label="咬合" prop="title">
          <el-radio-group v-model="order.biteLevel">
            <el-radio label="YaoMi">咬密(接触)</el-radio>
            <el-radio label="QingYao">轻咬合(离开0.2)</el-radio>
            <el-radio label="BuYao">不咬合(离开0.4)</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <!--<el-button @click="dialogAddVisible = false">取消</el-button>-->
      <el-button type="primary" @click="addOrder">保存</el-button>
    </el-row>
  </div>
</template>

<script>
import { addOrder, queryClinics } from '@/api/salesman'
import { findProductTypes } from '@/api/comprehensive'

export default {
  name: 'AddUser',
  data() {
    return {
      order: {
        clinicId: null,
        dentistId: null,
        type: null,
        colorNo: null,
        specification: null,
        positions: null,
        number: 0,
        requirement: null,
        comment: null,
        fieldType: null,
        biteLevel: null,
        borderType: null,
        innerCrownType: null,
        neckType: null,
        outerCrownType: null,
        paddingType: null
      },
      clinics: null,
      selectedClinic: [],
      props: {
        value: 'id',
        label: 'name',
        children: 'users'
      },
      position_group: []
    }
  },
  created() {
    this.fetchData()
    this.loadShortcuts()
  },
  methods: {
    loadShortcuts() {
      document.onkeydown = function(event) {
        var e = event || window.event
        console.log(e.code)
        console.log(e)
        if (e && e.code === 'KeyS' && e.altKey) {
          addOrder()
        }
      }
    },
    fetchData() {
      queryClinics().then(response => {
        var data = response.data
        console.log(data)
        this.clinics = data
      })
      findProductTypes().then(response => {
        var data = response.data
        this.specifications = data
        this.specificationOptions = data
      })
    },
    addOrder() {
      // todo check console.log(this.position_group.join(','))
      this.order.positions = this.position_group.join(',')
      addOrder(this.order).then(response => {
        this.$router.push({ path: '/salesman/order-list' })
      })
    },
    handleChange(value) {
      this.order.clinicId = value[0]
      this.order.dentistId = value[1]
    },
    handleSelect() {
      this.order.number = this.position_group.length
    },
    filterMethod(query) {
      if (query !== '') {
        console.log(query)
        this.specificationOptions = this.specifications.filter(item => {
          return item.code.toLowerCase().indexOf(query.toLowerCase()) > -1 ||
            item.name.indexOf(query) > -1
        })
      } else {
        this.specificationOptions = []
      }
    }
  }
}
</script>
