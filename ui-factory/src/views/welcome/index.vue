<template>
  <div class="dashboard-container">
    <div align="center" class="title-container">
      <h3 class="title">欢迎您, {{ username }}</h3>
    </div>
  </div>
</template>

<script>
import store from '@/store'

export default {
  name: 'Welcome',
  data() {
    return {
      username: store.getters.name
    }
  }
}

</script>
