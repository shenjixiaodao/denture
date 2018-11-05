<template>
  <div style="padding:5px;">
    <div class="panel panel-primary">
      <table style="text-align: right">
        <tbody>
          <tr>
            <td class="td_title_prop">姓名:</td><td class="td_content_prop">{{ user.name }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">联系方式:</td><td class="td_content_prop">{{ user.contact }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">所在组:</td><td class="td_content_prop">{{ user.groupType }}</td>
          </tr>
          <tr>
            <td class="td_title_prop">入职时间:</td><td class="td_content_prop">{{ user.joinDate }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>

</template>

<script>
import { user } from '@/api/comprehensive'

export default {
  data() {
    return {
      user: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      const id = this.$route.params && this.$route.params.id
      console.log('user detail ==> ' + id)
      user(id).then(response => {
        var data = response.data
        this.user = data
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  @import "@/styles/common.scss";
</style>
