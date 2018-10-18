<template>
  <div style="padding:5px;">
    <div class="panel panel-primary">
      <table class="table table-bordered table-striped text-center">
        <tbody>
          <tr>
            <td>姓名</td><td>{{ user.name }}</td>
          </tr>
          <tr>
            <td>联系方式</td><td>{{ user.contact }}</td>
          </tr>
          <tr>
            <td>所在组</td><td>{{ user.groupType }}</td>
          </tr>
          <tr>
            <td>入职时间</td><td>{{ user.joinDate }}</td>
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
