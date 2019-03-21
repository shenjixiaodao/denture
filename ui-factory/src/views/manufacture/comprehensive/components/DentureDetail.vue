<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:5px;">
      <table style="text-align: right">
        <tbody>
          <tr>
            <td class="td_title_prop">编号:</td><td class="td_content_prop">{{ denture.id }}</td>
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
            <td class="td_title_prop">阶段:</td><td class="td_content_prop">{{ denture.stage }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">数量:</td><td class="td_content_prop">{{ denture.number }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">加工时长:</td>
            <td class="td_content_prop">
              <el-input v-model="denture.estimatedDuration" class="filter-item" />
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
            <td class="td_title_prop">业务员:</td><td class="td_content_prop">{{ denture.deliveryPerson }}</td>
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
      <el-button v-if="!isShow&&!denture.deliveryId" type="primary" @click="dialogAddDeliveryVisible=true">出货</el-button>
      <el-button v-if="!isShow&&denture.deliveryId" type="primary" @click="printDeliveryInfo">打印出货单</el-button>
    </el-row>

    <el-row v-if="isShow" style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button @click="review('Reject')">废弃订单</el-button>
      <el-button type="primary" @click="review('Accept')">确认生产</el-button>
    </el-row>
    <div style="visibility: hidden">
      <table id="PrintingTable" style="text-align: right">
        <tbody>
          <tr>
            <td class="td_title_prop">编号:</td><td class="td_content_prop">{{ denture.id }}</td>
          </tr>
        </tbody>
      </table>
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

export default {
  data() {
    return {
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
        deliveryDate: null,
        deliveryPerson: null
      }
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
      })
    },
    review(result) {
      this.denture['reviewResult'] = result
      review(this.denture)
      this.fetchData()
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
      var printingTable = document.getElementById('PrintingTable').innerHTML
      var oldPage = document.body.innerHTML
      document.body.innerHTML = printingTable
      window.print()
      document.body.innerHTML = oldPage
      return false
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
</style>
