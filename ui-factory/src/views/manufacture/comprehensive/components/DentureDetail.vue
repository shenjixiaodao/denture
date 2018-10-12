<template>
  <div style="padding:5px;">
    <div class="panel panel-primary">
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
    </div>
  </div>

</template>

<script>
import { queryByDentureId } from '@/api/common'

export default {
  data() {
    return {
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
      })
    }
  }
}
</script>
