<template>
  <div class="app-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-input v-model="dentureId" placeholder="产品编号" style="width: 200px;" class="filter-item" />
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" >扫描</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search()" >搜索</el-button>
    </el-row>
    <el-row v-if="isShow" style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
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
          <tr>
            <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="dialogSubmitVisible=true">
              提交工序
            </el-button>
          </tr>
        </tbody>
      </table>
    </el-row>

    <el-row v-if="isShow" style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-table :data="procedures" style="width: 100%;padding-top: 15px;">
        <el-table-column label="工序名" min-width="200">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="完成时间" min-width="200">
          <template slot-scope="scope">
            {{ scope.row.completedDate }}
          </template>
        </el-table-column>
        <el-table-column label="备注" min-width="200">
          <template slot-scope="scope">
            {{ scope.row.comment }}
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <el-dialog :visible.sync="dialogSubmitVisible" title="提交工序">
      <el-form ref="dataForm" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="工序名" prop="title">
          <el-input v-model="procedure.name"/>
        </el-form-item>
        <el-form-item label="备注">
          <el-input :autosize="{ minRows: 2, maxRows: 4}" v-model="procedure.comment" type="textarea" placeholder="请输入"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogSubmitVisible = false">取消</el-button>
        <el-button type="primary" @click="submitProcedure">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { queryByDentureId } from '@/api/common'
import { queryProcedures, completeProcedure } from '@/api/worker'
import { isvalidDentureId, isNotNull, isNull } from '@/utils/validate'
import { Message } from 'element-ui'

export default {
  name: 'CompleteProcedure',
  data() {
    return {
      isShow: false,
      dialogSubmitVisible: false,
      dentureId: null,
      denture: {},
      procedures: null,
      pgId: null,
      procedure: {
        pgId: null,
        name: null,
        comment: null
      }
    }
  },
  methods: {
    search() {
      if (!isvalidDentureId(this.dentureId)) {
        this.isShow = false
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
          this.isShow = false
          return
        }
        this.denture = data
        this.isShow = true
        // fetch procedure data
        if (isNotNull(this.denture.procedureGroups)) {
          console.log(this.denture.procedureGroups)
          this.pgId = this.denture.procedureGroups[0].id
          this.procedure.pgId = this.pgId
          queryProcedures(this.pgId).then(response => {
            var data = response.data
            console.log(data)
            this.procedures = data
          })
        }
      })
    },
    submitProcedure() {
      completeProcedure(this.procedure).then(response1 => {
        // 刷新工序列表
        queryProcedures(this.pgId).then(response2 => {
          var data = response2.data
          console.log(data)
          this.procedures = data
          this.dialogSubmitVisible = false
        })
      })
    }
  }
}
</script>
