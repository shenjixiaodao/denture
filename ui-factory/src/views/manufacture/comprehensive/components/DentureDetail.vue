<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
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
            <td class="td_title_prop">生产记录:</td>
            <td class="td_content_prop">
              <router-link :to="'/worker/complete-procedure/'+denture.id" class="link-type">
                <span>详情</span>
              </router-link>
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">生产开始日期:</td><td class="td_content_prop"><span v-if="denture.startDate">{{ denture.startDate.split(' ',2)[0] }}</span></td>
          </tr>
          <tr>
            <td class="td_title_prop">生产结束日期:</td><td class="td_content_prop"><span v-if="denture.endDate">{{ denture.endDate.split(' ',2)[0] }}</span></td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button type="primary" @click="applyIngredient">申请用料</el-button>
      <el-table :data="appliedIngredients" style="width: 100%;padding-top: 15px;">
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

    <el-row v-if="isShow" style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button @click="review('Reject')">拒绝生产</el-button>
      <el-button type="primary" @click="review('Accept')">同意生产</el-button>
    </el-row>

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

  </div>

</template>

<script>
import { queryByDentureId } from '@/api/common'
import { queryIngredients, applyIngredient, review } from '@/api/comprehensive'

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
      denture: null
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
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
</style>
