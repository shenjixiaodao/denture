<template>
  <el-row style="background:#fff">
    <table cellspacing="15" style="text-align: left">
      <tr>
        <td class="td_title_prop">年度总计:</td>
        <td class="td_content_prop">{{ year?year:'--' }}</td>
        <td class="td_title_prop">月度总计:</td>
        <td class="td_content_prop">{{ month?month:'--' }}</td>
        <td class="td_title_prop">当日总计:</td>
        <td class="td_content_prop">{{ day?day:'--' }}</td>
      </tr>
    </table>
  </el-row>
</template>

<script>
import CountTo from 'vue-count-to'
import { statIngredient } from '@/api/common'

export default {
  components: {
    CountTo
  },
  props: {
    id: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      year: null,
      month: null,
      day: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      const { id } = this
      console.log(id)
      statIngredient(id).then(response => {
        var data = response.data
        console.log(data)
        this.year = data.year
        this.month = data.month
        this.day = data.day
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
@import "@/styles/common.scss";
.panel-group {
  margin-top: 18px;
  .card-panel-col{
    margin-bottom: 32px;
  }
  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);
    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }
      .icon-people {
         background: #40c9c6;
      }
      .icon-message {
        background: #36a3f7;
      }
      .icon-money {
        background: #f4516c;
      }
      .icon-shopping {
        background: #34bfa3
      }
    }
    .icon-people {
      color: #40c9c6;
    }
    .icon-message {
      color: #36a3f7;
    }
    .icon-money {
      color: #f4516c;
    }
    .icon-shopping {
      color: #34bfa3
    }
    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }
    .card-panel-icon {
      float: left;
      font-size: 48px;
    }
    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;
      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }
      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}
</style>
