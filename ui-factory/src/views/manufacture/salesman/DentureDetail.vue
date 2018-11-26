<template>
  <div style="padding:5px;">
    <div class="panel panel-primary">
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
            <td class="td_title_prop">定制方:</td><td class="td_content_prop">{{ denture.clinic.name }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">联系方式:</td><td class="td_content_prop">{{ denture.clinic.contact }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">备注:</td><td class="td_content_prop">{{ denture.comment }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">数量:</td><td class="td_content_prop">{{ denture.number }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">应收单价:</td><td class="td_content_prop">{{ denture.basePrice }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">出货单价:</td><td class="td_content_prop">{{ denture.factoryPrice }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">牙位:</td><td class="td_content_prop">{{ denture.positions }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">色号:</td><td class="td_content_prop">{{ denture.colorNo }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">制作要求:</td><td class="td_content_prop">{{ denture.requirement }}</td>
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
            <td class="td_title_prop">生产开始日期:</td><td class="td_content_prop"><span v-if="denture.startDate">{{ denture.startDate.split(' ',2)[0] }}</span></td>
          </tr>
          <tr>
            <td class="td_title_prop">生产结束日期:</td><td class="td_content_prop"><span v-if="denture.endDate">{{ denture.endDate.split(' ',2)[0] }}</span></td>
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
        console.log('denture detail ==> ' + data)
        this.denture = data
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  @import "@/styles/common.scss";
</style>
