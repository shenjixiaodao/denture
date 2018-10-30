<template>
  <div class="dashboard-container" style="overflow:auto;">
    <el-row style="background:#fff;padding:16px 16px 0;">
      <el-form ref="dataForm" label-position="left" label-width="20%">
        <el-form-item label="下单方" prop="title">
          <el-cascader :options="clinics" v-model="selectedClinic" :props="props" placeholder="诊所/医生" @change="handleChange"/>
        </el-form-item>
        <!--<el-form-item label="医生" prop="title">
          <el-input v-model="order.dentistId"/>
        </el-form-item>-->
        <el-form-item label="牙位" prop="title">
          <el-checkbox-group v-model="position_group">
            <table class="answer-table">
              <tbody>
                <tr>
                  <td><el-checkbox-button label="a8"/></td><td><el-checkbox-button label="a7" /></td><td><el-checkbox-button label="a6"/></td><td><el-checkbox-button label="a5"/></td><td><el-checkbox-button label="a4"/></td><td><el-checkbox-button label="a3"/></td><td><el-checkbox-button label="a2"/></td><td><el-checkbox-button label="a1"/></td>
                  <td><el-checkbox-button label="b1"/></td><td><el-checkbox-button label="b2"/></td><td><el-checkbox-button label="b3"/></td><td><el-checkbox-button label="b4"/></td><td><el-checkbox-button label="b5"/></td><td><el-checkbox-button label="b6"/></td><td><el-checkbox-button label="b7"/></td><td><el-checkbox-button label="b8"/></td>
                </tr>
                <tr>
                  <td><el-checkbox-button label="c8"/></td><td><el-checkbox-button label="c7"/></td><td><el-checkbox-button label="c6"/></td><td><el-checkbox-button label="c5"/></td><td><el-checkbox-button label="c4"/></td><td><el-checkbox-button label="c3"/></td><td><el-checkbox-button label="c2"/></td><td><el-checkbox-button label="c1"/></td>
                  <td><el-checkbox-button label="d1"/></td><td><el-checkbox-button label="d2"/></td><td><el-checkbox-button label="d3"/></td><td><el-checkbox-button label="d4"/></td><td><el-checkbox-button label="d5"/></td><td><el-checkbox-button label="d6"/></td><td><el-checkbox-button label="d7"/></td><td><el-checkbox-button label="d8"/></td>
                </tr>
              </tbody>
            </table>
          </el-checkbox-group>
          <!--<el-input v-model="order.positions"/>-->
        </el-form-item>
        <el-form-item label="种类名称" prop="title">
          <el-select v-model="order.type" placeholder="类型" clearable style="width: 90px" class="filter-item">
            <el-option v-for="item in types" :key="item.code" :label="item.name" :value="item.code"/>
          </el-select>
        </el-form-item>
        <el-form-item label="色号" prop="title">
          <el-input v-model="order.colorNo"/>
        </el-form-item>
        <el-form-item label="材质规格" prop="title">
          <el-select v-model="order.specification" placeholder="材质" clearable style="width: 90px" class="filter-item">
            <el-option v-for="item in specifications" :key="item.code" :label="item.name" :value="item.code"/>
          </el-select>
        </el-form-item>
        <el-form-item label="备注">
          <el-input :autosize="{ minRows: 2, maxRows: 4}" v-model="order.comment" type="textarea" placeholder="请输入"/>
        </el-form-item>
      </el-form>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <!--<el-button @click="dialogAddVisible = false">取消</el-button>-->
      <el-button type="primary" @click="addOrder">添加</el-button>
    </el-row>
  </div>
</template>

<script>
import { addOrder, queryClinics } from '@/api/salesman'

export default {
  name: 'AddUser',
  data() {
    return {
      types: [
        { code: 'Fixed', name: '定制式固定义齿' }, { code: 'Mobilizable', name: '定制式活动义齿' }
      ],
      specifications: [
        { code: 'GuGe', name: '钴铬合金' },
        { code: 'GuiJinShuDanGuan', name: '贵金属单冠' },
        { code: 'LianGuan', name: '连冠（桥、嵌体、贴面）' },
        { code: 'ErYangHuaGao', name: '二氧化锆' },
        { code: 'YangHuaGao', name: '氧化锆' },
        { code: 'ErYangHuaGuiGuan', name: '二氧化硅冠（桥、嵌体、贴面)' },
        { code: 'NieGeHeJinGuan', name: '镍铬合金冠' },
        { code: 'NieGeHeJinQiao', name: '镍铬合金桥' },
        { code: 'WanZhiZhiJiaKeZhai', name: '弯制支架可摘局部义齿' },
        { code: 'ShuZhiJiTuoQuanKou', name: '树脂基托全口义齿' },
        { code: 'Other', name: '其他' }
      ],
      order: {
        clinicId: null,
        dentistId: null,
        type: null,
        colorNo: null,
        specification: null,
        positions: null,
        comment: null
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
  },
  methods: {
    fetchData() {
      queryClinics().then(response => {
        var data = response.data
        console.log(data)
        this.clinics = data
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
    }
  }
}
</script>
