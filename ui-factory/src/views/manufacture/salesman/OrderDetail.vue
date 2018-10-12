<template>
  <div style="padding:5px;">
    <div class="panel panel-primary">
      <table class="table table-bordered table-striped text-center">
        <!--<thead>
          <tr>
            <th>序号</th>
            <th>用户名</th>
            <th>年龄</th>
            <th>毕业学校</th>
            <th>操作</th>
          </tr>
        </thead>-->
        <tbody>
          <tr>
            <td>订单号</td><td>{{ orderInfo.id }}</td>
          </tr>
          <tr>
            <td>订单状态</td><td>{{ orderInfo.status }}</td>
          </tr>
          <tr>
            <td>定制方</td><td>{{ orderInfo.clinic.name }}</td>
          </tr>
          <tr>
            <td>联系方式</td><td>{{ orderInfo.clinic.contact }}</td>
          </tr>
          <tr>
            <td>下单时间</td><td>{{ orderInfo.createdDate }}</td>
          </tr>
          <tr>
            <td>支付定金</td><td>{{ orderInfo.payAmount }}</td>
          </tr>
          <!--<tr>
            <td>医生</td><td>{{orderInfo.dentist}}</td>
          </tr>
          <tr>
            <td>患者</td><td>{{orderInfo.name}}</td>
          </tr>-->
        </tbody>
      </table>
    </div>
  </div>

</template>

<script>
import { queryOrderByDentureId } from '@/api/salesman'

export default {
  data() {
    return {
      orderInfo: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      const id = this.$route.params && this.$route.params.id
      console.log('order detail ==> ' + id)
      queryOrderByDentureId(id).then(response => {
        var data = response.data
        console.log('order detail ==> ' + data)
        this.orderInfo = data
      })
    }
  }
}
</script>
