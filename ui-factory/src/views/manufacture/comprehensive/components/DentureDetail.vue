<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:5px;">
      <table style="text-align: right">
        <tbody>
          <tr>
            <td class="td_title_prop">编号:</td><td class="td_content_prop">{{ denture.id | id2Short }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">牙盒编号:</td><td class="td_content_prop">{{ denture.boxNo }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">类型:</td><td class="td_content_prop">{{ denture.type }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">规格:</td><td class="td_content_prop">{{ denture.specification }}</td>
          </tr>
          <tr>
            <td class="td_title_prop" >定制方:</td><td class="td_content_prop">{{ denture.clinic.name }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">联系方式:</td><td class="td_content_prop">{{ denture.clinic.contact }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">医生备注:</td><td class="td_content_prop">{{ denture.comment }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">患者姓名:</td><td class="td_content_prop">{{ denture.patientName }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">业务员编号:</td><td class="td_content_prop">{{ denture.salesmanId }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">业务员:</td><td class="td_content_prop">{{ denture.salesman }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">数量:</td><td class="td_content_prop">{{ denture.number }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">阶段:</td><td class="td_content_prop">{{ denture.stage }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">收件时间:</td>
            <td class="td_content_prop">
              <el-date-picker v-model="denture.receivedDate" type="date" style="width: 200px;" placeholder="收件日" value-format="yyyy-MM-dd" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">预交时间:</td>
            <td class="td_content_prop">
              <el-date-picker v-model="denture.estimatedDuration" type="date" style="width: 200px;" placeholder="预计交货日" value-format="yyyy-MM-dd" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">应收单价:</td>
            <td class="td_content_prop">
              <el-input v-model="denture.basePrice" class="filter-item" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">出货单价:</td>
            <td class="td_content_prop">
              <el-input v-model="denture.factoryPrice" class="filter-item" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">牙位</td><td class="td_content_prop">{{ denture.positions }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">色号:</td><td class="td_content_prop">{{ denture.colorNo }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">制作要求:</td>
            <td class="td_content_prop">
              <el-input v-model="denture.requirement" class="filter-item" />
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">咬合:</td><td class="td_content_prop">{{ denture.biteLevel }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">领接:</td><td class="td_content_prop">{{ denture.borderType }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">缺牙区:</td><td class="td_content_prop">{{ denture.fieldType }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">内冠:</td><td class="td_content_prop">{{ denture.innerCrownType }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">颈缘:</td><td class="td_content_prop">{{ denture.neckType }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">牙冠:</td><td class="td_content_prop">{{ denture.outerCrownType }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">如空间不够:</td><td class="td_content_prop">{{ denture.paddingType }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">创建日期:</td><td class="td_content_prop"><span v-if="denture.createdDate">{{ denture.createdDate.split(' ',2)[0] }}</span></td>
          </tr>
          <tr>
            <td class="td_title_prop">工序详情:</td>
            <td class="td_content_prop">
              <router-link :to="'/comprehensive/procedure/'+denture.id" class="link-type">
                <span>详情</span>
              </router-link>
            </td>
          </tr>
          <!--<tr>
            <td class="td_title_prop">生产开始日期:</td><td class="td_content_prop"><span v-if="denture.startDate">{{ denture.startDate.split(' ',2)[0] }}</span></td>
          </tr>
          <tr>
            <td class="td_title_prop">生产结束日期:</td><td class="td_content_prop"><span v-if="denture.endDate">{{ denture.endDate.split(' ',2)[0] }}</span></td>
          </tr>-->
          <tr v-if="denture.deliveryId">
            <td class="td_title_prop">出货单号:</td><td class="td_content_prop">{{ denture.deliveryId }}</td>
          </tr>
          <tr v-if="denture.deliveryId">
            <td class="td_title_prop">出货日期:</td><td class="td_content_prop">{{ denture.deliveryDate }}</td>
          </tr>
          <tr v-if="denture.deliveryId">
            <td class="td_title_prop">收送员:</td><td class="td_content_prop">{{ denture.deliveryPerson }}</td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:15px;">
      <div style="font-size: 15px;font-weight: bold;">物料申请列表:</div>
      <el-table :data="appliedIngredients" style="width: 100%;">
        <el-table-column label="物料名">
          <template slot-scope="scope">
            {{ scope.row.ingredient.name }}
          </template>
        </el-table-column>
        <el-table-column label="型号">
          <template slot-scope="scope">
            {{ scope.row.ingredient.type }}
          </template>
        </el-table-column>
        <el-table-column label="物料名">
          <template slot-scope="scope">
            {{ scope.row.appliedDate | time2DateStr }}
          </template>
        </el-table-column>
        <el-table-column label="申请数量" align="center">
          <template slot-scope="scope">
            {{ scope.row.appliedNumber }}
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center">
          <template slot-scope="scope">
            {{ scope.row.comment }}
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:15px;">
      <el-button type="primary" @click="applyIngredient">申请用料</el-button>
      <el-button type="primary" @click="printDentureInfo">打印入检单</el-button>
      <el-button v-if="!isShow&&!denture.deliveryId" type="primary" @click="dialogAddDeliveryVisible=true">出货</el-button>
      <el-button v-if="!isShow&&denture.deliveryId" type="primary" @click="printDeliveryInfo">打印出货单</el-button>
    </el-row>

    <el-row v-if="isShow" style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button @click="review('Reject')">废弃订单</el-button>
      <el-button type="primary" @click="review('Accept')">入检确认</el-button>
    </el-row>
    <div style="visibility: hidden">
      <div id="DeliveryTable">
        <div style="text-align:center;font-size: 20px;font-weight: bold;">{{ denture.factory.name }}</div>
        <table align="center">
          <tbody>
            <tr>
              <td class="td_title_prop">NO:</td><td class="td_content_prop">{{ denture.deliveryId }}</td>
              <td class="td_title_prop">地区:</td><td class="td_content_prop">{{ denture.clinic.region }}</td>
              <td class="td_title_prop">付款方式:</td><td class="td_content_prop">{{ '      ' }}</td>
              <td class="td_title_prop">出货日期:</td><td class="td_content_prop">{{ denture.deliveryDate }}</td>
            </tr>
          </tbody>
        </table>
        <table border="1" bordercolor="#000000" style="border-collapse:collapse;" class="print_table" align="center">
          <tbody>
            <tr>
              <td class="td_title_prop">客户名称</td><td class="td_content_prop">{{ denture.clinic.name }}</td>
              <td class="td_title_prop">医生</td><td class="td_content_prop">{{ denture.clinic.name }}</td>
              <td class="td_title_prop">业务员</td><td class="td_content_prop">{{ denture.salesman }}</td>
              <td class="td_title_prop">患者</td><td class="td_content_prop">{{ denture.patientName }}</td>
            </tr>
            <tr>
              <td style="text-align: center" colspan="8"><barcode :value="denture.id | id2Short" height="35px" font-size="15px"/></td>
            </tr>
            <tr >
              <td class="td_title_print_table">品名</td>
              <td class="td_title_print_table" colspan="2">牙位</td>
              <td class="td_title_print_table">数量</td>
              <td class="td_title_print_table">单价</td>
              <td class="td_title_print_table">金额</td>
              <td class="td_title_print_table" colspan="2">客户签收</td>
            </tr>
            <tr style="border-bottom-style: hidden; ">
              <td style="border-right-style: hidden" class="td_content_print_table">{{ denture.specification }}</td>
              <td style="border-right-style: hidden" colspan="2">
                <table border="1" bordercolor="darkgrey" style="border-collapse:collapse;margin: 15px;" align="center" class="denture_position_table_mini">
                  <tr style="border-top-color: #ffffff;">
                    <td style="border-left-style: hidden;">{{ positions[0] }}</td><td>{{ positions[1] }}</td><td>{{ positions[2] }}</td><td>{{ positions[3] }}</td><td>{{ positions[4] }}</td>
                    <td>{{ positions[5] }}</td><td>{{ positions[6] }}</td><td style="border-right-color: #000000;border-right-width: 5px">{{ positions[7] }}</td>
                    <td style="border-left-color: #000000;border-left-width: 5px">{{ positions[8] }}</td><td>{{ positions[9] }}</td><td>{{ positions[10] }}</td><td>{{ positions[11] }}</td>
                    <td>{{ positions[12] }}</td><td>{{ positions[13] }}</td><td>{{ positions[14] }}</td><td style="border-right-style: hidden;">{{ positions[15] }}</td>
                  </tr>
                  <tr style="border-bottom-color: #ffffff;">
                    <td style="border-left-style: hidden">{{ positions[16] }}</td><td>{{ positions[17] }}</td><td>{{ positions[18] }}</td><td>{{ positions[19] }}</td>
                    <td>{{ positions[20] }}</td><td>{{ positions[21] }}</td><td>{{ positions[22] }}</td><td style="border-right-color: #000000;border-right-width: 5px">{{ positions[23] }}</td>
                    <td style="border-left-color: #000000;border-left-width: 5px">{{ positions[24] }}</td><td>{{ positions[25] }}</td><td>{{ positions[26] }}</td><td>{{ positions[27] }}</td>
                    <td>{{ positions[28] }}</td><td>{{ positions[29] }}</td><td>{{ positions[30] }}</td><td style="border-right-style: hidden">{{ positions[31] }}</td>
                  </tr>
                </table>
              </td>
              <td style="border-right-style: hidden" class="td_content_print_table">{{ denture.number }}</td>
              <td style="border-right-style: hidden" class="td_content_print_table">{{ '' }}</td>
              <td class="td_content_prop">{{ '' }}</td>
              <td colspan="2" rowspan="2" />
            </tr>
            <tr>
              <td style="border-top-style: hidden" colspan="6" />
            </tr>
            <tr>
              <td class="td_title_prop">备注</td>
              <td class="td_content_prop" colspan="7" />
            </tr>
            <tr>
              <td style="text-align: left" colspan="8">
                合计:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                大写:
              </td>
            </tr>
            <tr>
              <td class="td_title_prop">地址</td><td class="td_content_prop" colspan="3"> {{ denture.clinic.region + ' ' + denture.clinic.address }} </td>
              <td class="td_title_prop">联系方式</td><td class="td_content_prop" colspan="3">{{ denture.clinic.contact }}</td>
            </tr>
          </tbody>
        </table>
        <table align="center">
          <tbody>
            <tr>
              <td class="td_title_prop">注册证号:</td><td class="td_content_prop">{{ denture.factory.certification }}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <div id="DentureTable">
        <div style="text-align:center;font-size: 20px;font-weight: bold;">{{ denture.factory.name }}</div>
        <table border="1" bordercolor="#000000" style="border-collapse:collapse;" class="print_table" align="center">
          <tbody>
            <tr>
              <td class="td_title_prop">模号:</td><td class="td_content_prop">{{ denture.id | id2Short }}</td>
              <td class="td_title_prop">收件:</td><td class="td_content_prop">{{ denture.receivedDate }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">客户:</td><td class="td_content_prop">{{ '('+denture.clinic.id+')'+denture.clinic.name }}</td>
              <td class="td_title_prop">预定:</td><td class="td_content_prop">{{ denture.estimatedDuration }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">业务员:</td><td class="td_content_prop">{{ '('+denture.salesmanId+')'+denture.salesman }}</td>
              <td class="td_title_prop">患者:</td><td class="td_content_prop">{{ denture.patientName }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">阶段:</td><td class="td_content_prop">{{ denture.stage }}</td>
              <td class="td_title_prop">色号:</td><td class="td_content_prop">{{ denture.colorNo }}</td>
            </tr>
            <tr>
              <td style="text-align: center" colspan="4"><barcode :value="denture.id | id2Short" height="35px" font-size="15px"/></td>
            </tr>
            <tr>
              <td class="td_title_prop">牙位:</td>
              <td colspan="3">
                <table border="1" bordercolor="darkgrey" style="border-collapse:collapse;margin: 15px;" align="center" class="denture_position_table">
                  <tr style="border-top-color: #ffffff;">
                    <td style="border-left-style: hidden;">{{ positions[0] }}</td><td>{{ positions[1] }}</td><td>{{ positions[2] }}</td><td>{{ positions[3] }}</td><td>{{ positions[4] }}</td>
                    <td>{{ positions[5] }}</td><td>{{ positions[6] }}</td><td style="border-right-color: #000000;border-right-width: 5px">{{ positions[7] }}</td>
                    <td style="border-left-color: #000000;border-left-width: 5px">{{ positions[8] }}</td><td>{{ positions[9] }}</td><td>{{ positions[10] }}</td><td>{{ positions[11] }}</td>
                    <td>{{ positions[12] }}</td><td>{{ positions[13] }}</td><td>{{ positions[14] }}</td><td style="border-right-style: hidden;">{{ positions[15] }}</td>
                  </tr>
                  <tr style="border-bottom-color: #ffffff;">
                    <td style="border-left-style: hidden">{{ positions[16] }}</td><td>{{ positions[17] }}</td><td>{{ positions[18] }}</td><td>{{ positions[19] }}</td>
                    <td>{{ positions[20] }}</td><td>{{ positions[21] }}</td><td>{{ positions[22] }}</td><td style="border-right-color: #000000;border-right-width: 5px">{{ positions[23] }}</td>
                    <td style="border-left-color: #000000;border-left-width: 5px">{{ positions[24] }}</td><td>{{ positions[25] }}</td><td>{{ positions[26] }}</td><td>{{ positions[27] }}</td>
                    <td>{{ positions[28] }}</td><td>{{ positions[29] }}</td><td>{{ positions[30] }}</td><td style="border-right-style: hidden">{{ positions[31] }}</td>
                  </tr>
                </table>
              </td>
            </tr>
            <tr>
              <td class="td_title_prop">种类:</td><td class="td_content_prop">{{ denture.specification }}</td>
              <td class="td_title_prop">数量:</td><td class="td_content_prop">{{ denture.number }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">制造要求</td>
              <td class="td_content_prop" colspan="3">{{ denture.requirement }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">咬合</td><td class="td_content_prop">{{ denture.biteLevel }}</td>
              <td class="td_title_prop">领接</td><td class="td_content_prop">{{ denture.borderType }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">内冠</td><td class="td_content_prop">{{ denture.innerCrownType }}</td>
              <td class="td_title_prop">牙冠</td><td class="td_content_prop">{{ denture.outerCrownType }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">颈缘</td><td class="td_content_prop">{{ denture.neckType }}</td>
              <td class="td_title_prop">缺牙区</td><td class="td_content_prop">{{ denture.fieldType }}</td>
            </tr>
            <tr>
              <td class="td_title_prop">如空间不够</td>
              <td class="td_content_prop" colspan="3">{{ denture.paddingType }}</td>
            </tr>
            <tr>
              <td style="color: #999;font-size: 14px;font-weight: bold;text-align: center">备注</td>
              <td style="height:200px;" colspan="3" />
            </tr>
          </tbody>
        </table>
        <table align="center">
          <tbody>
            <tr>
              <td class="td_title_prop">入检员:</td><td class="td_content_prop">{{ denture.creator }}</td>
              <td class="td_title_prop">印表日期:</td><td class="td_content_prop">{{ new Date().Format("yyyy/MM/dd hh:mm") }}</td>
              <td class="td_title_prop">确认:</td><td class="td_content_prop">{{ '      ' }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <el-dialog :visible.sync="dialogAddVisible" title="申请生产用料">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="物料" prop="title">
          <el-select v-model="ingredient.ingredientId" placeholder="物料" clearable style="width: 70%;" class="filter-item">
            <el-option v-for="item in ingredients" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="领用数量" prop="title">
          <el-input v-model="ingredient.number" style="width: 70%;"/> <!-- 可用数量 -->
        </el-form-item>
        <el-form-item label="使用数量" prop="title">
          <el-input v-model="ingredient.usedNumber" style="width: 70%;"/> <!-- 可用数量 -->
        </el-form-item>
        <el-form-item label="应用设备" prop="title">
          <el-input v-model="ingredient.equipment" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="备注" prop="title">
          <el-input v-model="ingredient.comment" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click="addIngredient">添加</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogAddDeliveryVisible" title="记录出货信息">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="出货日期" prop="title">
          <el-date-picker v-model="deliveryInfo.deliveryDate" type="date" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="收送员" prop="title">
          <el-input v-model="deliveryInfo.deliveryPerson" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddDeliveryVisible = false">取消</el-button>
        <el-button type="primary" @click="delivery">添加</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>
import { queryByDentureId } from '@/api/common'
import { queryIngredients, applyIngredient, review, delivery } from '@/api/comprehensive'
import barcode from 'vue-barcode'

export default {
  components: {
    'barcode': barcode
  },
  data() {
    return {
      now: new Date().Format('yyyy-MM-dd'),
      ingredient: {
        dentureId: null,
        ingredientId: null,
        usedNumber: null,
        number: null,
        equipment: null,
        comment: null
      },
      ingredients: null,
      appliedIngredients: [],
      isShow: false,
      dialogAddVisible: false,
      dialogAddDeliveryVisible: false,
      denture: null,
      deliveryInfo: {
        dentureId: null,
        deliveryDate: new Date().Format('yyyy-MM-dd'),
        deliveryPerson: null
      },
      positions: [
        '', '', '', '', '', '', '', '',
        '', '', '', '', '', '', '', '',
        '', '', '', '', '', '', '', '',
        '', '', '', '', '', '', '', ''
      ]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      const id = this.$route.params && this.$route.params.id
      console.log('denture detail ==> ' + id)
      queryByDentureId(id).then(response => {
        var data = response.data
        this.denture = data
        this.isShow = !data.startDate
        this.appliedIngredients = this.denture.appliedIngredients
        for (const p of this.denture.positions.split(',')) {
          const index = (p.charCodeAt(0) - 'a'.charCodeAt()) * 8 + (p.charCodeAt(1) - '1'.charCodeAt())
          console.log(index)
          this.positions[index] = p
        }
      })
    },
    review(result) {
      this.denture['reviewResult'] = result
      review(this.denture).then(() => {
        this.fetchData()
      })
    },
    delivery() {
      this.deliveryInfo.dentureId = this.denture.id
      delivery(this.deliveryInfo).then(() => {
        this.dialogAddDeliveryVisible = false
      })
      this.fetchData()
    },
    applyIngredient() {
      this.dialogAddVisible = true
      queryIngredients().then(response => {
        var data = response.data
        this.ingredients = data
      })
    },
    addIngredient() {
      this.ingredient.dentureId = this.denture.id
      applyIngredient(this.ingredient).then(response => {
        var data = response.data
        this.ingredients = data
        this.fetchData()
        this.dialogAddVisible = false
      })
    },
    printDeliveryInfo() {
      const deliveryTable = document.getElementById('DeliveryTable').innerHTML
      const oldPage = document.body.innerHTML
      document.body.innerHTML = deliveryTable
      window.print()
      document.body.innerHTML = oldPage
      window.location.reload()
    },
    printDentureInfo() {
      const dentureTable = document.getElementById('DentureTable').innerHTML
      const oldPage = document.body.innerHTML
      document.body.innerHTML = dentureTable
      window.print()
      document.body.innerHTML = oldPage
      window.location.reload()
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
</style>
