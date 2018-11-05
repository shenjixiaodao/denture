<template>
  <div style="padding:5px;">
    <div class="panel panel-primary">
      <table style="text-align: right">
        <tbody>
          <tr>
            <td class="td_title_prop">订单号:</td><td class="td_content_prop">{{ orderInfo.id }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">订单状态:</td><td class="td_content_prop">{{ orderInfo.status }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">定制方:</td><td class="td_content_prop">{{ orderInfo.clinic.name }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">联系方式:</td><td class="td_content_prop">{{ orderInfo.clinic.contact }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">下单时间:</td>
            <td class="td_content_prop">
              <span v-if="orderInfo.createdDate">{{ orderInfo.createdDate.split(' ',2)[0] }}</span>
            </td>
          </tr>
          <tr>
            <td class="td_title_prop">支付定金:</td><td class="td_content_prop">{{ orderInfo.payAmount }}</td>
          </tr>
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
<style lang="scss" scoped>
  @import "@/styles/common.scss";
</style>
