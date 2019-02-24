<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:10px 10px 0;">
      <el-form ref="dataForm" label-position="left" label-width="20%">
        <el-form-item label="公司名称" prop="title">
          <el-input v-model="customer.name" />
        </el-form-item>
        <el-form-item label="企业性质" prop="title">
          <el-input v-model="customer.property" />
        </el-form-item>
        <el-form-item label="开户行及账号" prop="title">
          <el-input v-model="customer.bank_account" />
        </el-form-item>
        <el-form-item label="所在城市" prop="title">
          <el-cascader :options="cities" :props="props" placeholder="省/市/县" @change="handleChange"/>
        </el-form-item>
        <el-form-item label="详细地址" prop="title">
          <el-input v-model="customer.address" />
        </el-form-item>
        <el-form-item label="发货路线、方式、费用" prop="title">
          <el-input v-model="customer.delivery" />
        </el-form-item>
        <el-form-item label="炉子功率、种类、数量" prop="title">
          <el-input v-model="customer.equipment" />
        </el-form-item>
        <el-form-item label="生产产品" prop="title">
          <el-input v-model="customer.product" />
        </el-form-item>
        <el-form-item label="产量情况" prop="title">
          <el-input v-model="customer.production" />
        </el-form-item>
        <el-form-item label="目前开工情况" prop="title">
          <el-input v-model="customer.work_status" />
        </el-form-item>
        <el-form-item label="采购负责人" prop="title">
          <el-input v-model="customer.purchase_manager" />
        </el-form-item>
        <el-form-item label="负责人联系方式" prop="title">
          <el-input v-model="customer.purchase_contact" />
        </el-form-item>
        <el-form-item label="负责人是否为老板亲信" prop="title">
          <el-input v-model="customer.is_trusted_manager" />
        </el-form-item>
        <el-form-item label="采购方式" prop="title">
          <el-input v-model="customer.purchase_style" />
        </el-form-item>
        <el-form-item label="是否长期客户" prop="title">
          <el-input v-model="customer.is_long_term" />
        </el-form-item>
        <el-form-item label="是否参观过我单位" prop="title">
          <el-input v-model="customer.is_visit_company" />
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
import { storeCustomer } from '@/api/customer'
import { cities } from '@/utils/allCities'

export default {
  data() {
    return {
      customer: {
        name: null,
        property: null,
        bank_account: null,
        region: null,
        address: null,
        delivery: null,
        equipment: null,
        product: null,
        production: null,
        work_status: null,
        purchase_manager: null,
        purchase_contact: null,
        is_trusted_manager: null,
        purchase_style: null,
        is_long_term: null,
        is_visit_company: null
      },
      cities: cities,
      props: {
        value: 'name',
        label: 'name',
        children: 'sub'
      },
      selectedCity: []
    }
  },
  methods: {
    addCustomer() {
      // this.customer.region = this.selectedCity.join('/')
      storeCustomer(this.customer).then(resp => {
        //
      })
    },
    handleChange(value) {
      this.selectedCity = value
    }
  }
}
</script>
