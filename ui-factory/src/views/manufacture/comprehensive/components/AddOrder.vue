<template>
  <div class="dashboard-container" style="overflow:auto;">
    <el-row style="background:#fff;padding:10px 10px 0;">
      <el-form ref="dataForm" label-position="left" label-width="20%">
        <el-form-item label="下单方" prop="title">
          <el-cascader :options="clinics" v-model="selectedClinic" :props="props" placeholder="诊所/医生" filterable @change="handleChange"/>
        </el-form-item>
        <el-form-item label="联系电话" prop="title">
          <el-select v-model="contact" disabled class="filter-item" />
        </el-form-item>
        <el-form-item label="患者姓名" prop="title">
          <el-input v-model="order.patientName" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="牙盒编号" prop="title">
          <el-input v-model="order.boxNo" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="业务员" prop="title">
          <el-select v-model="order.salesmanId" filterable placeholder="业务员" class="filter-item">
            <el-option v-for="item in users" :key="item.id" :label="item.name" :value="item.id">
              <span style="float: left">{{ item.name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.no }}</span>
            </el-option>
          </el-select>
          <router-link :to="'/comprehensive/users'" class="link-type">
            <span>详情</span>
          </router-link>
        </el-form-item>
        <el-form-item label="阶段" prop="title">
          <el-select v-model="order.stage" filterable placeholder="阶段" class="filter-item" @change="handleStageChange">
            <el-option v-for="item in stages" :key="item.code" :label="item.name" :value="item.code"/>
          </el-select>
        </el-form-item>
        <el-form-item label="收件时间" prop="title">
          <el-date-picker v-model="order.receivedDate" :placeholder="now" type="date" style="width: 200px;" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="预交日期" prop="title">
          <el-date-picker v-model="estimatedDate" disabled type="date" style="width: 200px;" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="牙位" />
        <section style="background:#fff;padding:0px 5px 10px;">
          <table>
            <tbody>
              <tr>
                <td style="font-size: 13px">上排:</td>
                <td>
                  <input v-model="position_group" type="checkbox" value="a8" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="a7" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="a6" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="a5" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="a4" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="a3" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="a2" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="a1" @change="handleSelect">
                </td>
                <td>
                  <el-divider direction="vertical" />
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="b1" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="b2" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="b3" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="b4" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="b5" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="b6" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="b7" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="b8" @change="handleSelect">
                </td>
              </tr>
              <tr>
                <td />
                <td>8</td><td>7</td><td>6</td><td>5</td>
                <td>4</td><td>3</td><td>2</td><td>1</td>
                <td>
                  <el-divider direction="vertical" />
                </td>
                <td>1</td><td>2</td><td>3</td><td>4</td>
                <td>5</td><td>6</td><td>7</td><td>8</td>
              </tr>
              <tr>
                <td style="font-size: 13px">下排:</td>
                <td>
                  <input v-model="position_group" type="checkbox" value="c8" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="c7" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="c6" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="c5" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="c4" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="c3" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="c2" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="c1" @change="handleSelect">
                </td>
                <td>
                  <el-divider direction="vertical" />
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="d1" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="d2" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="d3" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="d4" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="d5" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="d6" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="d7" @change="handleSelect">
                </td>
                <td>
                  <input v-model="position_group" type="checkbox" value="d8" @change="handleSelect">
                </td>
              </tr>
            </tbody>
          </table>
        </section>
        <el-form-item label="数量" prop="title">
          <el-input v-model="order.number" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="种类" prop="title">
          <!--<el-input v-model="order.specification" style="width: 70%;"/>-->
          <el-select v-model="order.specification" :filter-method="filterMethod" filterable placeholder="类型" class="filter-item" @change="handleTypeChange">
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
            <el-radio label="Normal">正常</el-radio>
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
            <el-radio label="AnJianTai">按肩台</el-radio>
            <el-radio label="Top">龈上边缘</el-radio>
            <el-radio label="Below">龈下边缘</el-radio>
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
        <el-form-item label="备注">
          <el-input :autosize="{ minRows: 2, maxRows: 4}" v-model="order.comment" type="textarea" placeholder="请输入" style="width: 70%;"/>
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
import { findProductTypes, users, customers } from '@/api/comprehensive'

export default {
  name: 'AddOrder',
  data() {
    return {
      now: new Date().Format('yyyy-MM-dd'),
      estimatedDate: new Date().Format('yyyy-MM-dd'),
      contact: null,
      types: [
        { code: 'Fixed', name: '定制式固定义齿' }, { code: 'Mobilizable', name: '定制式活动义齿' }
      ],
      specificationOptions: null,
      specifications: null,
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
        dentist: null,
        patientName: null,
        salesman: null,
        salesmanId: null,
        stage: null,
        receivedDate: new Date().Format('yyyy-MM-dd'),
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
        paddingType: null,
        boxNo: null
      },
      clinics: null,
      selectedClinic: [],
      customers: null,
      props: {
        value: 'id',
        label: 'name',
        children: 'users'
      },
      position_group: [],
      users: null,
      stages: [
        { code: '1', name: '1阶段' },
        { code: '2', name: '2阶段' },
        { code: '3', name: '3阶段' },
        { code: '4', name: '4阶段' },
        { code: '5', name: '5阶段' }
      ]
    }
  },
  created() {
    this.fetchData()
    this.loadShortcuts()
  },
  methods: {
    loadShortcuts() {
      const _this = this
      document.onkeydown = function(event) {
        var e = event || window.event
        console.log(e.code)
        console.log(e)
        if (e && e.code === 'KeyS' && e.altKey) {
          _this.addOrder()
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
      users().then(response => {
        var data = response.data
        this.users = data
      })
      customers({
        page: 1,
        limit: 1000
      }).then(response => {
        this.customers = response.data
      })
    },
    addOrder() {
      // todo check console.log(this.position_group.join(','))
      if (this.order.salesmanId) {
        for (const item of this.users) {
          if (item.id === this.order.salesmanId) {
            this.order.salesman = item.name
            break
          }
        }
      }
      if (this.order.clinicId) {
        console.log(this.clinics)
        for (const c of this.clinics) {
          if (c.id === this.order.clinicId) {
            for (const cu of c.users) {
              if (cu.id === this.order.dentistId) {
                this.order.dentist = cu.name
              }
            }
          }
        }
      }
      this.order.positions = this.position_group.join(',')
      addOrder(this.order).then(response => {
        const data = response.data
        this.$router.push({ path: '/comprehensive/denture/' + data.id })
      })
    },
    handleChange(value) {
      this.order.clinicId = value[0]
      this.order.dentistId = value[1]
      for (const c of this.customers) {
        if (c.clinic.id === this.order.clinicId) {
          this.order.salesmanId = c.salesmanId
          break
        }
      }
      for (const c of this.clinics) {
        if (c.id === this.order.clinicId) {
          for (const cu of c.users) {
            console.log(cu)
            if (cu.id === this.order.dentistId) {
              this.contact = cu.contact
              break
            }
          }
        }
      }
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
    },
    handleStageChange(value) {
      const time = new Date().getTime() + parseInt(value) * 24 * 60 * 60 * 1000
      this.estimatedDate = new Date(time).Format('yyyy-MM-dd')
    },
    handleTypeChange(value) {
      for (const s of this.specifications) {
        if (s.code === value) {
          this.order.type = s.type
          break
        }
      }
    }
  }
}
</script>
