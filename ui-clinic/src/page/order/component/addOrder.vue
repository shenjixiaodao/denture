<template xmlns="http://www.w3.org/1999/html">
  <div class="order_detail_page">
    <head-top head-title="下单" goBack="true"></head-top>
    <section v-if="!showLoading" id="scroll_section" class="scroll_container">
      <section class="scroll_insert">
        <section class="pay_way container_style">
          <header class="header_style">
            <span>合作工厂</span>
            <div class="more_type" @click="showFactoryWay = true">
              <span>{{ selectedFactory.name }}</span>
              <svg class="address_empty_right">
                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#arrow-right"></use>
              </svg>
            </div>
          </header>
        </section>

        <section class="pay_way container_style">
          <header class="header_style">
            <span>牙位</span>
          </header>
          <section class="hongbo">
            <span>上排:</span>
            <span>
              <input type="checkbox" v-model="position_array" value="a1"/>
              <input type="checkbox" v-model="position_array" value="a2"/>
              <input type="checkbox" v-model="position_array" value="a3"/>
              <input type="checkbox" v-model="position_array" value="a4"/>
              <input type="checkbox" v-model="position_array" value="a5"/>
              <input type="checkbox" v-model="position_array" value="a6"/>
              <input type="checkbox" v-model="position_array" value="a7"/>
              <input type="checkbox" v-model="position_array" value="a8"/>
              <b>|</b>
              <input type="checkbox" v-model="position_array" value="b8"/>
              <input type="checkbox" v-model="position_array" value="b7"/>
              <input type="checkbox" v-model="position_array" value="b6"/>
              <input type="checkbox" v-model="position_array" value="b5"/>
              <input type="checkbox" v-model="position_array" value="b4"/>
              <input type="checkbox" v-model="position_array" value="b3"/>
              <input type="checkbox" v-model="position_array" value="b2"/>
              <input type="checkbox" v-model="position_array" value="b1"/>
            </span>
          </section>
          <section class="hongbo">
            <span>下排:</span>
            <span>
              <input type="checkbox" v-model="position_array" value="c1"/>
              <input type="checkbox" v-model="position_array" value="c2"/>
              <input type="checkbox" v-model="position_array" value="c3"/>
              <input type="checkbox" v-model="position_array" value="c4"/>
              <input type="checkbox" v-model="position_array" value="c5"/>
              <input type="checkbox" v-model="position_array" value="c6"/>
              <input type="checkbox" v-model="position_array" value="c7"/>
              <input type="checkbox" v-model="position_array" value="c8"/>
              <b>|</b>
              <input type="checkbox" v-model="position_array" value="d8"/>
              <input type="checkbox" v-model="position_array" value="d7"/>
              <input type="checkbox" v-model="position_array" value="d6"/>
              <input type="checkbox" v-model="position_array" value="d5"/>
              <input type="checkbox" v-model="position_array" value="d4"/>
              <input type="checkbox" v-model="position_array" value="d3"/>
              <input type="checkbox" v-model="position_array" value="d2"/>
              <input type="checkbox" v-model="position_array" value="d1"/>
            </span>
          </section>
        </section>

        <section class="pay_way container_style">
          <header class="header_style">
            <span>义齿类型</span>
            <div class="more_type" @click="showTypeWay = true">
              <span>{{ selectedType.name }}</span>
              <svg class="address_empty_right">
                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#arrow-right"></use>
              </svg>
            </div>
          </header>
        </section>

        <section class="pay_way container_style">
          <header class="header_style">
            <span>材质规格</span>
            <div class="more_type" @click="showSpecWay = true">
              <span>{{ selectedSpec.name }}</span>
              <svg class="address_empty_right">
                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#arrow-right"></use>
              </svg>
            </div>
          </header>
        </section>

        <section class="pay_way container_style section_list">
          <span class="section_left">色号</span>
          <section class="section_right">
            <input type="text" v-model="order.colorNo" class="input_style">
          </section>
        </section>

        <section class="pay_way container_style section_list">
          <span class="section_left">备注</span>
          <section class="section_right">
            <input type="text" v-model="order.comment" class="input_style">
          </section>
        </section>

        <section class="pay_way container_style">
          <header class="header_style">
            <span>缺牙区</span>
            <div class="more_type" @click="showFieldType = true">
              <span>{{ selectedFieldType.name }}</span>
              <svg class="address_empty_right">
                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#arrow-right"></use>
              </svg>
            </div>
          </header>
        </section>

        <section class="order_detail_style">
          <header>咬合</header>
          <section class="item_style">
            <p class="guide_item">
              <input type="radio" v-model="order.biteLevel" value="YaoMi" id="YaoMi"/>
              <label for="YaoMi">咬密(接触)</label>
            </p>
            <p class="guide_item">
              <input type="radio" v-model="order.biteLevel" value="QingYao" id="QingYao"/>
              <label for="QingYao">轻咬合(离开0.2)</label>
            </p>
            <p class="guide_item">
              <input type="radio" v-model="order.biteLevel" value="BuYao" id="BuYao"/>
              <label for="BuYao">不咬到(离开0.4)</label>
            </p>
          </section>
        </section>

        <section class="order_detail_style">
          <header>邻接</header>
          <section class="item_style">
            <p class="guide_item_row">
              <input type="radio" v-model="order.borderType" value="Plan" id="Plan"/>
              <label for="Plan">面接触</label>
            </p>
            <p class="guide_item_row">
              <input type="radio" v-model="order.borderType" value="Point" id="Point"/>
              <label for="Point">点接触</label>
            </p>
            <p class="guide_item_row">
              <input type="radio" v-model="order.borderType" value="Normal" id="Normal"/>
              <label for="Normal">正常</label>
            </p>
          </section>
        </section>

        <section class="order_detail_style">
          <header>颈缘</header>
          <section class="item_style">
            <p class="guide_item_row">
              <input type="radio" v-model="order.neckType" value="AnJianTai" id="AnJianTai"/>
              <label for="AnJianTai">按肩台</label>
            </p>
            <p class="guide_item_row">
              <input type="radio" v-model="order.neckType" value="Top" id="Top"/>
              <label for="Top">龈上边缘</label>
            </p>
            <p class="guide_item_row">
              <input type="radio" v-model="order.neckType" value="Below" id="Below"/>
              <label for="Below">龈下边缘</label>
            </p>
          </section>
        </section>

        <section class="order_detail_style">
          <header>内冠</header>
          <section class="item_style">
            <p class="guide_item_row">
              <input type="radio" v-model="order.innerCrownType" value="Normal" id="Normal"/>
              <label for="Normal">正常</label>
            </p>
            <p class="guide_item_row">
              <input type="radio" v-model="order.innerCrownType" value="Tight" id="Tight"/>
              <label for="Tight">紧</label>
            </p>
            <p class="guide_item_row">
              <input type="radio" v-model="order.innerCrownType" value="Loose" id="Loose"/>
              <label for="Loose">松</label>
            </p>
          </section>
        </section>

        <section class="pay_way container_style">
          <header class="header_style">
            <span>牙冠</span>
            <div class="more_type" @click="showOuterCrownType = true">
              <span>{{ selectedOuterCrownType.name }}</span>
              <svg class="address_empty_right">
                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#arrow-right"></use>
              </svg>
            </div>
          </header>
        </section>

      </section>
    </section>

    <!-- bottom popup -->
    <transition name="fade">
      <div class="cover" v-if="showFactoryWay||showTypeWay||showSpecWay||showFieldType||showOuterCrownType"
           @click="showFieldType=showOuterCrownType=showSpecWay=showTypeWay=showFactoryWay = false"></div>
    </transition>

    <transition name="slid_up">
      <div class="choose_type_Container" v-if="showFactoryWay">
        <header>工厂列表</header>
        <ul>
          <li v-for="item in factories" :key="item.id" :class="{choose: selectedFactory.id == item.id}">
            <span>{{item.name}}</span>
            <svg class="address_empty_right" @click="choosePayWay(item)">
              <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#select"></use>
            </svg>
          </li>
        </ul>
      </div>
    </transition>

    <transition name="slid_up">
      <div class="choose_type_Container" v-if="showTypeWay">
        <header>义齿类型</header>
        <ul>
          <li v-for="item in types" :key="item.code" :class="{choose: selectedType.code == item.code}">
            <span>{{item.name}}</span>
            <svg class="address_empty_right" @click="chooseType(item)">
              <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#select"></use>
            </svg>
          </li>
        </ul>
      </div>
    </transition>

    <transition name="slid_up">
      <div class="choose_type_Container" v-if="showSpecWay">
        <header>义齿规格</header>
        <ul>
          <li v-for="item in specifications" :key="item.code" :class="{choose: selectedSpec.code == item.code}">
            <span>{{item.name}}</span>
            <svg class="address_empty_right" @click="chooseSpec(item)">
              <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#select"></use>
            </svg>
          </li>
        </ul>
      </div>
    </transition>

    <transition name="slid_up">
      <div class="choose_type_Container" v-if="showFieldType">
        <header>缺牙区</header>
        <ul>
          <li v-for="item in fieldTypes" :key="item.code" :class="{choose: selectedFieldType.code == item.code}">
            <span>{{item.name}}</span>
            <svg class="address_empty_right" @click="chooseFieldType(item)">
              <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#select"></use>
            </svg>
          </li>
        </ul>
      </div>
    </transition>

    <transition name="slid_up">
      <div class="choose_type_Container" v-if="showOuterCrownType">
        <header>缺牙区</header>
        <ul>
          <li v-for="item in outerCrownTypes" :key="item.code" :class="{choose: selectedOuterCrownType.code == item.code}">
            <span>{{item.name}}</span>
            <svg class="address_empty_right" @click="chooseOuterCrownType(item)">
              <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#select"></use>
            </svg>
          </li>
        </ul>
      </div>
    </transition>
    <transition name="loading">
      <loading v-if="showLoading"></loading>
    </transition>
  </div>
</template>

<script>
import {mapState, mapMutations} from 'vuex'
import headTop from 'src/components/header/head'
import {getImgPath} from 'src/components/common/mixin'
import loading from 'src/components/common/loading'
import BScroll from 'better-scroll'
import {imgBaseUrl} from 'src/config/env'
import { addOrder } from 'src//api/order'
import { queryFactories } from 'src//api/info'


  export default {

    data(){
      return{
        order: {
          factorId: null,
          type: null,
          colorNo: null,
          specification: null,
          positions: null,
          comment: null,
          fieldType: null,
          biteLevel: null,
          borderType: null,
          innerCrownType: null,
          neckType: null,
          outerCrownType: null,
          paddingType: null
        },
        types: [
          { code: 'Fixed', name: '定制式固定义齿' }, { code: 'Mobilizable', name: '定制式活动义齿' }
        ],
        specifications: [
          { code: 'GuGe', name: '钴铬合金' },
          { code: 'GuiJinShuDanGuan', name: '贵金属单冠' },
          { code: 'LianGuan', name: '连冠（桥、嵌体、贴面）' },
          { code: 'ErYangHuaGao', name: '二氧化锆' },
          { code: 'YangHuaGao', name: '氧化锆' },
          { code: 'ErYangHuaGuiGuan', name: '二氧化硅冠（桥、嵌体、贴面)' },
          { code: 'NieGeHeJinGuan', name: '镍铬合金冠' },
          { code: 'NieGeHeJinQiao', name: '镍铬合金桥' },
          { code: 'WanZhiZhiJiaKeZhai', name: '弯制支架可摘局部义齿' },
          { code: 'ShuZhiJiTuoQuanKou', name: '树脂基托全口义齿' },
          { code: 'Other', name: '其他' }
        ],
        fieldTypes: [
          { code: 'DaMaAn', name: '大马鞍' },
          { code: 'PianCe', name: '偏侧型' },
          { code: 'ZiDan', name: '子弹型' },
          { code: 'XiaoMaAn', name: '小马鞍' },
          { code: 'WeiSheng', name: '卫生型' },
          { code: 'ShenRu', name: '深入型' }
        ],
        outerCrownTypes: [
          { code: 'SheCe', name: '舌侧' },
          { code: 'JinTop', name: '全金属边' },
          { code: 'ThreeQuarter', name: '松' },
          { code: 'SC_TQ', name: '舌侧金属3/4颌侧' },
          { code: 'JinBelow', name: '金属颌侧' },
          { code: 'AllCi', name: '舌侧全瓷边' }
        ],
        showLoading: true, //显示加载动画
        orderData: null,
        imgBaseUrl: null,
        factories: null,
        showFactoryWay: false, //显示选择合作工厂
        showTypeWay: false,
        showSpecWay: false,
        showFieldType: false,
        showOuterCrownType: false,
        selectedFactory: {
          id: -1,
          name: '请选择'
        },
        selectedType: {
          code: '',
          name: '请选择'
        },
        selectedFieldType: {
          code: '',
          name: '请选择'
        },
        selectedOuterCrownType: {
          code: '',
          name: '请选择'
        },
        selectedSpec: {
          code: '',
          name: '请选择'
        },
        position_array: []
      }
    },
    mounted(){
      this.initData();
    },
    components: {
      headTop,
      loading,
    },
    methods: {
      async initData(){
        const factoryId = this.$route.params && this.$route.params.id
        var _this = this
        queryFactories(1).then(response => {
          var data = response.data
          console.log(data)
          this.factories = data
          if (factoryId) {
            this.factories.forEach(function (value,i) {
              console.log(value)
              if(value.id==factoryId) {
                console.log(factoryId)
                _this.selectedFactory = value
                return
              }
            })
          }
          this.showLoading = false
          this.$nextTick(() => {
            new BScroll('#scroll_section', {
              deceleration: 0.001,
              bounce: true,
              swipeTime: 1800,
              click: true,
            });
          })
        })
      },
      addOrder() {
        // todo check console.log(this.position_group.join(','))
        this.order.positions = this.position_array.join(',')
      },
      //
      choosePayWay(factory){
        this.showFactoryWay = !this.showFactoryWay
        this.selectedFactory = factory
      },
      //
      chooseType(type){
        this.showTypeWay = !this.showTypeWay
        this.selectedType = type
      },
      //选择
      chooseSpec(spec){
        this.showSpecWay = !this.showSpecWay
        this.selectedSpec = spec
      },
      chooseFieldType(type){
        this.showFieldType = !this.showFieldType
        this.selectedFieldType = type
      },
      chooseOuterCrownType(type){
        this.showOuterCrownType = !this.showOuterCrownType
        this.selectedOuterCrownType = type
      }
    }
  }
</script>

<style lang="scss" scoped>
  @import 'src/style/mixin';

  .order_detail_page{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: #f1f1f1;
    z-index: 202;
    padding-top: 1.95rem;
  p, span{
    font-family: Helvetica Neue,Tahoma,Arial;
  }
  }
  .scroll_container{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    padding-top: 1.95rem;
  }
  .scroll_insert{
    padding-bottom: 3rem;
  }

  .container_style{
    background-color: #fff;
    margin-top: .4rem;
    padding: 0 .7rem;
  }

  .address_empty_right{
  @include wh(.6rem, .6rem);
    fill: #999;
  }
  .delivery_model{
    border-left: .2rem solid $blue;
    min-height: 4rem;
  @include fj;
    align-items: center;
  .deliver_text{
  @include sc(.8rem, #333);
    font-weight: bold;
    padding-left: .3rem;
  }
  .deliver_time{
    display: flex;
    flex-direction: column;
    align-items: flex-end;
  p:nth-of-type(1){
  @include sc(.7rem, $blue);
  }
  p:nth-of-type(2){
  @include sc(.5rem, #fff);
    background-color: $blue;
    width: 2.4rem;
    margin-top: .5rem;
    text-align: center;
    border-radius: 0.12rem;
    padding: .1rem;
  }
  }
  }
  .pay_way{
  .header_style{
  @include fj;
    line-height: 2rem;
  span:nth-of-type(1){
  @include sc(.7rem, #666);
  }
  .more_type{
  span:nth-of-type(1){
  @include sc(.6rem, #aaa);
    width: 10rem;
    display: inline-block;
    text-align: right;
    vertical-align: middle;
  }
  svg{
  @include wh(.5rem, .5rem);
    fill: #ccc;
  }
  }
  }
  .hongbo{
  @include fj;
    border-top: 0.025rem solid #f5f5f5;
  span{
  @include sc(.6rem, #aaa);
    line-height: 2rem;
  svg{
  @include wh(.5rem, .5rem);
    vertical-align: middle;
    fill: #ccc;
  }
  }
  span:nth-of-type(2){
    color: #aaa;
  }
  }
  .support_is_available{
  span{
    color: #666;
  }
  }
  }

  .cover{
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(0,0,0,.3);
    z-index: 203;
  }
  .choose_type_Container{
    min-height: 10rem;
    background-color: #fff;
    position: fixed;
    bottom: 0;
    width: 100%;
    z-index: 204;
  header{
    background-color: #fafafa;
  @include sc(.7rem, #333);
    text-align: center;
    line-height: 2rem;
  }
  ul{
  li{
  @include fj;
    padding: 0 .7rem;
    line-height: 2.5rem;
    align-items: center;
  span{
  @include sc(.7rem, #ccc);
  }
  svg{
  @include wh(.8rem, .8rem);
    fill: #eee;
  }
  }
  .choose{
  span{
    color: #333;
  }
  svg{
    fill: #4cd964;
  }
  }
  }
  }
  .fade-enter-active, .fade-leave-active {
    transition: opacity .3s;
  }
  .fade-enter, .fade-leave-active {
    opacity: 0;
  }
  .slid_up-enter-active, .slid_up-leave-active {
    transition: all .3s;
  }
  .slid_up-enter, .slid_up-leave-active {
    transform: translate3d(0,10rem,0)
  }
  .router-slid-enter-active, .router-slid-leave-active {
    transition: all .4s;
  }
  .router-slid-enter, .router-slid-leave-active {
    transform: translate3d(2rem, 0, 0);
    opacity: 0;
  }

  .section_list{
    display: flex;
    border-bottom: 0.025rem solid #f5f5f5;
  .section_left{
  @include sc(.7rem, #333);
    flex: 2;
    line-height: 2.5rem;
  }
  .section_right{
    flex: 5;
  .input_style{
    width: 100%;
    height: 2.5rem;
  @include sc(.7rem, #999);
  }
  .phone_bk{
    border-top: 0.025rem solid #f5f5f5;
  }
  .phone_add{
  @include fj;
    align-items: center;
  }
  .choose_sex{
    display: flex;
    line-height: 2.5rem;
    border-top: 0.025rem solid #f5f5f5;
  .choose_option{
  @include sc(.7rem, #333);
    display: flex;
    align-items: center;
    margin-right: .8rem;
  svg{
    margin-right: .3rem;
  @include wh(.8rem, .8rem);
    fill: #ccc;
  }
  .choosed{
    fill: #4cd964;
  }
  }
  }
  .choose_address{
  @include sc(.7rem, #999);
    line-height: 2.5rem;
    border-bottom: 0.025rem solid #f5f5f5;
  }
  }
  }

  .order_detail_style{
    background-color: #fff;
    margin-top: 0.5rem;
  header{
  @include sc(.75rem, #333);
    padding: .5rem;
    border-bottom: 1px solid #f5f5f5;
  }
  .item_style{
    display: flex;
    padding: .5rem;
  p{
  @include sc(.65rem, #666);
    line-height: 1rem;
  }
  }
  }

  .guide_item{
    flex: 1;
    display: flex;
    text-align: center;
    flex-direction: column;
    align-items: center;
  .icon_style{
  @include wh(.8rem, .8rem);
    margin-top: .3rem;
    fill: #ccc;
  }
  span{
  @include sc(.45rem, #666);
  }
  }

  .guide_item_row {
    flex: 1;
    display: flex;
    text-align: center;
    flex-direction: row;
    align-items: center;
  .icon_style{
  @include wh(.8rem, .8rem);
    margin-top: .3rem;
    fill: #ccc;
  }
  span{
  @include sc(.45rem, #666);
  }
  }

</style>

