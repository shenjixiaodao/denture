<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <table class="table table-bordered table-striped text-center">
        <tbody>
          <tr>
            <td>类型</td><td>{{ denture.type }}</td>
          </tr>
          <tr>
            <td>规格</td><td>{{ denture.specification }}</td>
          </tr>
          <tr>
            <td>定制方</td><td>{{ denture.clinic.name }}</td>
          </tr>
          <tr>
            <td>联系方式</td><td>{{ denture.clinic.contact }}</td>
          </tr>
          <tr>
            <td>医生备注</td><td>{{ denture.comment }}</td>
          </tr>
          <tr>
            <td>数量</td><td>{{ denture.number }}</td>
          </tr>
          <tr>
            <td>牙位</td><td>{{ denture.positions }}</td>
          </tr>
          <tr>
            <td>色号</td><td>{{ denture.colorNo }}</td>
          </tr>
          <tr>
            <td>创建日期</td><td>{{ denture.createdDate }}</td>
          </tr>
          <tr>
            <td>生产开始日期</td><td>{{ denture.startDate }}</td>
          </tr>
          <tr>
            <td>生产结束日期</td><td>{{ denture.endDate }}</td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button type="primary" @click="applyIngredient">申请用料</el-button>
      <el-table :data="appliedIngredients" style="width: 100%;padding-top: 15px;">
        <el-table-column label="物料名">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="申请数量" align="center">
          <template slot-scope="scope">
            {{ scope.row.number }}
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
      <el-form ref="dataForm" label-position="left" style="width: 400px; margin-left:50px;">
        <el-select v-model="ingredient.ingredientId" placeholder="物料" clearable style="width: 90px" class="filter-item">
          <el-option v-for="item in ingredients" :key="item.id" :label="item.name" :value="item.id"/>
        </el-select>
        <el-form-item label="数量" prop="title">
          <el-input v-model="ingredient.number"/> <!-- 可用数量 -->
        </el-form-item>
        <el-form-item label="备注" prop="title">
          <el-input v-model="ingredient.comment"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取消</el-button>
        <el-button type="primary" @click="submitProcedure">添加</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>
import { queryByDentureId } from '@/api/common'
import { queryIngredients } from '@/api/comprehensive'

export default {
  data() {
    return {
      ingredient: {
        ingredient: null,
        number: null,
        comment: null
      },
      ingredients: null,
      appliedIngredients: [],
      isShow: false,
      dialogAddVisible: false
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
      })
    },
    review(result) {
      console.log(result)
    },
    applyIngredient() {
      this.dialogAddVisible = true
      queryIngredients().then(response => {
        var data = response.data
        this.ingredients = data
      })
    }
  }
}
</script>
