<template>
  <div class="dashboard-container" style="overflow:auto;">
    <el-row style="background:#fff;padding:10px 10px 0;">
      <el-form ref="dataForm" label-position="left" label-width="20%">
        <el-form-item label="下单方" prop="title">
          <el-cascader :options="clinics" v-model="selectedClinic" :props="props" placeholder="诊所/医生" @change="handleChange"/>
        </el-form-item>
        <!--<el-form-item label="医生" prop="title">
          <el-input v-model="order.dentistId"/>
        </el-form-item>-->

        <!--<el-form-item label="牙位" prop="title">
          &lt;!&ndash;<el-checkbox-group v-model="position_group">
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
          </el-checkbox-group>&ndash;&gt;
          &lt;!&ndash;<el-input v-model="order.positions"/>&ndash;&gt;
        </el-form-item>-->
        <el-form-item label="牙位" />
        <section style="background:#fff;padding:0px 5px 10px;">
          <section class="hongbo">
            <span style="font-size: 13px">上排:</span>
            <span>
              <input v-model="position_group" type="checkbox" value="a8">
              <input v-model="position_group" type="checkbox" value="a7">
              <input v-model="position_group" type="checkbox" value="a6">
              <input v-model="position_group" type="checkbox" value="a5">
              <input v-model="position_group" type="checkbox" value="a4">
              <input v-model="position_group" type="checkbox" value="a3">
              <input v-model="position_group" type="checkbox" value="a2">
              <input v-model="position_group" type="checkbox" value="a1">
              <b>|</b>
              <input v-model="position_group" type="checkbox" value="b1">
              <input v-model="position_group" type="checkbox" value="b2">
              <input v-model="position_group" type="checkbox" value="b3">
              <input v-model="position_group" type="checkbox" value="b4">
              <input v-model="position_group" type="checkbox" value="b5">
              <input v-model="position_group" type="checkbox" value="b6">
              <input v-model="position_group" type="checkbox" value="b7">
              <input v-model="position_group" type="checkbox" value="b8">
            </span>
          </section>
          <section class="hongbo" style="padding-left: 30px">
            <b style="padding-left: 6px">8</b>
            <b style="padding-left: 4px">7</b>
            <b style="padding-left: 4px">6</b>
            <b style="padding-left: 4px">5</b>
            <b style="padding-left: 4px">4</b>
            <b style="padding-left: 4px">3</b>
            <b style="padding-left: 4px">2</b>
            <b style="padding-left: 4px">1</b>
            <b style="padding-left: 3px">|</b>
            <b style="padding-left: 2px">1</b>
            <b style="padding-left: 4px">2</b>
            <b style="padding-left: 4px">3</b>
            <b style="padding-left: 4px">4</b>
            <b style="padding-left: 4px">5</b>
            <b style="padding-left: 4px">6</b>
            <b style="padding-left: 4px">7</b>
            <b style="padding-left: 4px">8</b>
          </section>
          <section class="hongbo" style="background:#fff;padding:0px 0px 16px;">
            <span style="font-size: 13px">下排:</span>
            <span>
              <input v-model="position_group" type="checkbox" value="c8">
              <input v-model="position_group" type="checkbox" value="c7">
              <input v-model="position_group" type="checkbox" value="c6">
              <input v-model="position_group" type="checkbox" value="c5">
              <input v-model="position_group" type="checkbox" value="c4">
              <input v-model="position_group" type="checkbox" value="c3">
              <input v-model="position_group" type="checkbox" value="c2">
              <input v-model="position_group" type="checkbox" value="c1">
              <b>|</b>
              <input v-model="position_group" type="checkbox" value="d1">
              <input v-model="position_group" type="checkbox" value="d2">
              <input v-model="position_group" type="checkbox" value="d3">
              <input v-model="position_group" type="checkbox" value="d4">
              <input v-model="position_group" type="checkbox" value="d5">
              <input v-model="position_group" type="checkbox" value="d6">
              <input v-model="position_group" type="checkbox" value="d7">
              <input v-model="position_group" type="checkbox" value="d8">
            </span>
          </section>
        </section>
        <el-form-item label="数量" prop="title">
          <el-input v-model="order.number" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="种类名称" prop="title">
          <el-input v-model="order.specification" style="width: 70%;"/>
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
        <el-form-item label="领接" prop="title">
          <el-radio-group v-model="order.borderType">
            <el-radio label="Plan">面接触</el-radio>
            <el-radio label="Point">点接触</el-radio>
            <el-radio label="Normal">正常</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="颈缘" prop="title">
          <el-radio-group v-model="order.neckType">
            <el-radio label="Plan">按肩台</el-radio>
            <el-radio label="Point">龈上边缘</el-radio>
            <el-radio label="Normal">龈下边缘</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="内冠" prop="title">
          <el-radio-group v-model="order.innerCrownType">
            <el-radio label="Normal">正常</el-radio>
            <el-radio label="Tight">紧</el-radio>
            <el-radio label="Loose">松</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="牙冠" prop="title">
          <el-select v-model="order.outerCrownType" placeholder="类型" clearable style="width: 90px" class="filter-item">
            <el-option v-for="item in outerCrownTypes" :key="item.code" :label="item.name" :value="item.code"/>
          </el-select>
        </el-form-item>
        <el-form-item label="如空间不够" prop="title">
          <el-select v-model="order.paddingType" placeholder="类型" clearable style="width: 90px" class="filter-item">
            <el-option v-for="item in paddingType" :key="item.code" :label="item.name" :value="item.code"/>
          </el-select>
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
      fieldTypes: [
        { code: 'DaMaAn', name: '大马鞍' },
        { code: 'PianCe', name: '偏侧型' },
        { code: 'ZiDan', name: '子弹型' },
        { code: 'XiaoMaAn', name: '小马鞍' },
        { code: 'WeiSheng', name: '卫生型' },
        { code: 'ShenRu', name: '深入型' }
      ],
      outerCrownTypes: [
        { code: 'SheCe', name: '舌侧' },
        { code: 'JinTop', name: '全金属边' },
        { code: 'ThreeQuarter', name: '松' },
        { code: 'SC_TQ', name: '舌侧金属3/4颌侧' },
        { code: 'JinBelow', name: '金属颌侧' },
        { code: 'AllCi', name: '舌侧全瓷边' }
      ],
      paddingType: [
        { code: 'PiDuiYao', name: '批对咬' },
        { code: 'PiJiYa', name: '批基牙' },
        { code: 'ZuoYaoJin', name: '做咬金' },
        { code: 'LouDianJin', name: '露点金' },
        { code: 'Unknown', name: '待问' }
      ],
      order: {
        clinicId: null,
        dentistId: null,
        type: null,
        colorNo: null,
        specification: null,
        positions: null,
        number: null,
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
