<template>
  <div style="padding:5px;">
    <div class="panel panel-primary">
      <table class="table table-bordered table-striped text-center">
        <!--
        private DentureType type;
    //规格(specification):
    private SpecType specification;
    //定制方(clinic):
    private Clinic clinic;
    //医生备注
    private String comment;
    //生产方(factory):
    private Factory factory;
    //数量(number):
    private Integer number;
    /**
     * 牙位号(position),
     * 使用","分隔各个牙位号，个数等于 {@param number}；
     * 牙位号格式: 半位[a|b|c|d]-编号[1-8], eg: a-2（表示左上半第2号）
     */
    private String positions;
    //义齿色号(colorNo): Enum
    private String colorNo;
    //创建日期(createdDate):
    private Date createdDate;
    //生产开始日期(startDate):
    private Date startDate;
    //生产截止日期(endDate):
    private Date endDate;
        -->
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
        console.log('denture detail ==> ' + data)
        this.denture = data
      })
    }
  }
}
</script>
