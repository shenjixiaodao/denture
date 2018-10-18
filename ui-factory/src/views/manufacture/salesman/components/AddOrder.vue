<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-form ref="dataForm" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="诊所" prop="title">
          <el-input v-model="order.clinincId"/>
        </el-form-item>
        <el-form-item label="医生" prop="title">
          <el-input v-model="order.clinicId"/>
        </el-form-item>
        <el-form-item label="牙位" prop="title">
          <el-input v-model="order.positions"/>
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
import { addOrder } from '@/api/salesman'

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
      }
    }
  },
  methods: {
    addOrder() {
      addOrder(this.order).then(response => {
        this.$router.push({ path: '/salesman/order-list' })
      })
    }
  }
}
</script>
