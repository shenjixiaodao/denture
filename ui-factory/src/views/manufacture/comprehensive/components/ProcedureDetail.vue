<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <table style="text-align: right">
        <tbody>
          <tr>
            <td class="td_title_prop">类型:</td><td class="td_content_prop">{{ denture.type }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">规格:</td><td class="td_content_prop">{{ denture.specification }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">定制方:</td><td class="td_content_prop">{{ denture.clinic.name }}</td>
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
            <td class="td_title_prop">牙位:</td><td class="td_content_prop">{{ denture.positions }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">色号:</td><td class="td_content_prop">{{ denture.colorNo }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">制作要求:</td>
            <td class="td_content_prop">
              {{ denture.requirement }}
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
            <td class="td_title_prop">创建日期:</td><td class="td_content_prop">{{ denture.createdDate | time2DateStr }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">生产开始日期:</td><td class="td_content_prop">{{ denture.startDate | time2DateStr }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">生产结束日期:</td><td class="td_content_prop">{{ denture.endDate | time2DateStr }}</td>
          </tr>
        </tbody>
      </table>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <div style="font-size: 15px;padding-top: 10px;font-weight: bold;">物料平衡记录表:</div>
      <el-table :data="appliedUsedIngredient" style="width: 100%;">
        <el-table-column label="物料名">
          <template slot-scope="scope">
            {{ scope.row.ingredientName }}
          </template>
        </el-table-column>
        <el-table-column label="型号">
          <template slot-scope="scope">
            {{ scope.row.ingredientType }}
          </template>
        </el-table-column>
        <el-table-column label="偏差范围">
          <template slot-scope="scope">
            {{ scope.row.equalityRateRange }}
          </template>
        </el-table-column>
        <el-table-column label="领取数量">
          <template slot-scope="scope">
            {{ scope.row.appliedNumber }}
          </template>
        </el-table-column>
        <el-table-column label="实际使用量">
          <template slot-scope="scope">
            {{ scope.row.usedNumber }}
          </template>
        </el-table-column>
        <el-table-column label="剩余量">
          <template slot-scope="scope">
            {{ scope.row.balance }}
          </template>
        </el-table-column>
        <el-table-column label="废料量">
          <template slot-scope="scope">
            {{ scope.row.wastedNumber }}
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="dialogSubmitVisible=true">
        提交工序
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="useIngredient">
        使用物料
      </el-button>
      <el-button v-if="!denture.inspection" class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="dialogInspectVisible=true">
        生成检验报告
      </el-button>
    </el-row>
    <el-row v-for="group in procedureGroups" :key="group.id" style="background:#fff;margin-bottom:25px;">
      <hr style="filter: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="100%" color="#987cb9" SIZE="1">
      <table cellspacing="15" style="text-align: left">
        <tr>
          <td class="td_title_prop">工序组:</td>
          <td class="td_content_prop">{{ group.type }}</td>
          <td class="td_title_prop">开始时间:</td>
          <td class="td_content_prop">
            {{ group.startDate | time2DateStr }}
          </td>
        </tr>
      </table>
      <el-tabs active-name="procedures" type="card">
        <el-tab-pane label="工序列表" name="procedures">
          <el-table :data="group.procedures" style="width: 100%;">
            <el-table-column label="工序名">
              <template slot-scope="scope">
                {{ scope.row.name }}
              </template>
            </el-table-column>
            <el-table-column label="完成时间">
              <template slot-scope="scope">
                {{ scope.row.completedDate | time2DateStr }}
              </template>
            </el-table-column>
            <el-table-column label="备注">
              <template slot-scope="scope">
                {{ scope.row.comment }}
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="物料使用列表" name="ingredients">
          <el-table :data="group.usedIngredients" style="width: 100%;">
            <el-table-column label="物料名">
              <template slot-scope="scope">
                {{ scope.row.ingredient.name }}
              </template>
            </el-table-column>
            <el-table-column label="使用设备">
              <template slot-scope="scope">
                {{ scope.row.equipment }}
              </template>
            </el-table-column>
            <el-table-column label="使用量">
              <template slot-scope="scope">
                {{ scope.row.usedNumber }}
              </template>
            </el-table-column>
            <el-table-column label="使用时间">
              <template slot-scope="scope">
                {{ scope.row.usedTime | time2DateStr }}
              </template>
            </el-table-column>
            <el-table-column label="备注">
              <template slot-scope="scope">
                {{ scope.row.comment }}
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-row>

    <el-dialog :visible.sync="dialogInspectVisible" title="新建检验结论">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="取样时间" prop="title">
          <el-date-picker v-model="newInspectionReport.sampleDate" type="date" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="开始时间" prop="title">
          <el-date-picker v-model="newInspectionReport.startDate" type="date" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="结束时间" prop="title">
          <el-date-picker v-model="newInspectionReport.endDate" type="date" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="检验结论">
          <el-input :autosize="{ minRows: 2, maxs: 4}" v-model="newInspectionReport.conclusion" type="textarea" placeholder="请输入" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="备注">
          <el-input :autosize="{ minRows: 2, maxs: 4}" v-model="newInspectionReport.comment" type="textarea" placeholder="请输入" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogInspectVisible = false">取消</el-button>
        <el-button type="primary" @click="addInspectionReport">提交</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="dialogSubmitVisible" title="提交工序">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="工序组" prop="title">
          <el-select v-model="procedure.pgId" placeholder="请选择" clearable style="width: 90px" class="filter-item">
            <el-option v-for="group in procedureGroups" :key="group.id" :label="group.type" :value="group.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="工序名" prop="title">
          <el-input v-model="procedure.name" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="备注">
          <el-input :autosize="{ minRows: 2, maxs: 4}" v-model="procedure.comment" type="textarea" placeholder="请输入" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogSubmitVisible = false">取消</el-button>
        <el-button type="primary" @click="submitProcedure">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogUseIngredient" title="物料使用">
      <el-form ref="dataForm" label-position="left" label-width="20%" style="width: 100%;">
        <el-form-item label="工序组" prop="title">
          <el-select v-model="usedIngredient.pgId" placeholder="请选择" clearable style="width: 90px" class="filter-item">
            <el-option v-for="group in procedureGroups" :key="group.id" :label="group.type" :value="group.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="物料名" prop="title">
          <el-select v-model="usedIngredient.ingredient.id" placeholder="请选择" style="width: 90px" class="filter-item" @change="usedIngredientChange">
            <el-option v-for="group in appliedUsedIngredient" :key="group.ingredientId" :label="group.ingredientName" :value="group.ingredientId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="设备名" prop="title">
          <el-input v-model="usedIngredient.equipment" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="使用量" prop="title">
          <el-input v-model="usedIngredient.usedNumber" :placeholder="'剩余可用:'+selectedBalance" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="废料量" prop="title">
          <el-input v-model="usedIngredient.wastedNumber" style="width: 70%;"/>
        </el-form-item>
        <el-form-item label="备注">
          <el-input :autosize="{ minRows: 2, maxRows: 4}" v-model="usedIngredient.comment" type="textarea" placeholder="请输入" style="width: 70%;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogUseIngredient = false">取消</el-button>
        <el-button type="primary" @click="submitUsedIngredient">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { queryByDentureId, findAppliedUsedIngredient } from '@/api/common'
import { completeProcedure, useIngredient, addInspectionReport } from '@/api/worker'
import { isvalidDentureId, isNotNull, isNull } from '@/utils/validate'
import { Message } from 'element-ui'

export default {
  name: 'ProcedureDetail',
  data() {
    return {
      dialogSubmitVisible: false,
      dialogUseIngredient: false,
      dialogInspectVisible: false,
      dentureId: null,
      denture: {},
      procedureGroups: null,
      selectedBalance: 0,
      appliedUsedIngredient: null,
      pgId: null,
      procedure: {
        pgId: null,
        name: null,
        comment: null
      },
      usedIngredient: {
        dentureId: null,
        equipment: null,
        pgId: null,
        ingredient: {
          id: null
        },
        usedNumber: null,
        wastedNumber: null,
        comment: null
      },
      newInspectionReport: {
        dentureId: null,
        sampleDate: new Date().Format('yyyy-MM-dd'),
        startDate: new Date().Format('yyyy-MM-dd'),
        endDate: null,
        conclusion: null,
        comment: null
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.dentureId = this.$route.params && this.$route.params.id
      if (this.dentureId) {
        this.search()
      }
    },
    search() {
      if (!isvalidDentureId(this.dentureId)) {
        return Message({
          message: '不是合法产品ID',
          type: 'error',
          duration: 2 * 1000
        })
      }
      queryByDentureId(this.dentureId).then(response => {
        var data = response.data
        console.log(data)
        if (isNull(data)) {
          return
        }
        this.denture = data
        if (isNotNull(this.denture.procedureGroups)) {
          console.log(this.denture.procedureGroups)
          this.procedureGroups = this.denture.procedureGroups
          this.pgId = this.denture.procedureGroups[0].id
          this.procedure.pgId = this.pgId
        }
      })
    },
    submitProcedure() {
      completeProcedure(this.procedure).then(response1 => {
        // 刷新工序列表
        this.search()
        this.dialogSubmitVisible = false
      })
    },
    useIngredient() {
      if (!this.appliedUsedIngredient) {
        findAppliedUsedIngredient(this.dentureId).then(response => {
          var data = response.data
          console.log(data)
          this.appliedUsedIngredient = data
        })
      }
      this.dialogUseIngredient = true
    },
    submitUsedIngredient() {
      this.usedIngredient.dentureId = this.dentureId
      useIngredient(this.usedIngredient).then(response => {
        this.dialogUseIngredient = false
      })
    },
    usedIngredientChange(value) {
      if (this.appliedUsedIngredient) {
        for (var item of this.appliedUsedIngredient) {
          if (item.ingredientId === value) {
            this.selectedBalance = item.balance
          }
        }
      }
    },
    addInspectionReport() {
      this.newInspectionReport.dentureId = this.dentureId
      addInspectionReport(this.newInspectionReport).then(response => {
        this.search()
        this.dialogUseIngredient = false
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  @import "@/styles/common.scss";
</style>
