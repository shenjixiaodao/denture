<template>
  <div class="order_page">
    <!--<head-top head-title="订单列表" go-back='false'></head-top>-->
    <header id='head_top'>
      <slot name='logo'></slot>
      <slot name='search'></slot>
      <section class="head_goback" @click="addOrder">
        <svg width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" version="1.1">
          <polyline points="6,6 6,18" style="fill:none;stroke:rgb(255,255,255);stroke-width:2"/>
          <polyline points="0,12 12,12" style="fill:none;stroke:rgb(255,255,255);stroke-width:2"/>
        </svg>
      </section>
      <router-link :to="userInfo? '/profile':'/login'" v-if='false' class="head_login">
        <svg class="user_avatar" v-if="userInfo">
          <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#user"></use>
        </svg>
        <span class="login_span" v-else>登录|注册</span>
      </router-link>
      <section class="title_head ellipsis">
        <span class="title_text">订单列表</span>
      </section>
      <slot name="edit"></slot>
      <slot name="msite-title"></slot>
      <slot name="changecity"></slot>
      <slot name="changeLogin"></slot>
    </header>

    <ul class="order_list_ul" v-load-more="loaderMore">
      <li class="order_list_li" v-for="item in orderList" :key="item.id">
        <img src="http://img.jituwang.com/uploads/allimg/130614/260169-1306140J93521.jpg" class="restaurant_image">
        <section class="order_item_right">
          <section @click="showDetail(item)">
            <header class="order_item_right_header">
              <section class="order_header">
                <h4 >
                  <span class="ellipsis">{{item.factory.id}} </span>
                  <svg fill="#333" class="arrow_right">
                    <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#arrow-right"></use>
                  </svg>
                </h4>
                <p class="order_time">{{item.createdDate}}</p>
              </section>
              <p class="order_status">
                {{item.status}}
              </p>
            </header>
            <section class="order_basket">
              <p class="order_name ellipsis">{{item.comment}}</p>
              <p class="order_amount">¥{{item.payAmount.toFixed(2)}}</p>
            </section>
          </section>
        </section>
      </li>
    </ul>
    <foot-guide></foot-guide>
    <transition name="loading">
      <loading v-show="showLoading"></loading>
    </transition>
    <transition name="router-slid" mode="out-in">
      <router-view></router-view>
    </transition>

  </div>
</template>

<script>
import {mapState, mapMutations} from 'vuex'
import headTop from 'src/components/header/head'
import computeTime from 'src/components/common/computeTime'
import loading from 'src/components/common/loading'
import footGuide from 'src/components/footer/footGuide'
import {getOrderList} from 'src/service/getData'
import {loadMore} from 'src/components/common/mixin'
import {imgBaseUrl} from 'src/config/env'
import { queryOrders } from 'src/api/order'

export default {
  data(){
    return{
      orderList: null, //订单列表
      offset: 0,
      preventRepeat: false,  //防止重复获取
      showLoading: true, //显示加载动画
      imgBaseUrl
    }
  },
  mounted(){
    this.initData();
  },
  mixins: [loadMore],
  components: {
    headTop,
    footGuide,
    loading,
    computeTime,
  },
  computed: {
    ...mapState([
      'userInfo', 'geohash'
    ]),
  },
  methods: {
    ...mapMutations([
      'SAVE_ORDER'
    ]),
    //初始化获取信息
    initData(){
      queryOrders().then(response => {
        var data = response.data
        this.orderList = data
        this.hideLoading();
      })
    },
    //加载更多
    async loaderMore(){
      if (this.preventRepeat) {
        return
      }
      /*this.preventRepeat = true;
      this.showLoading = true;
      this.offset += 10;
      //获取信息
      let res = await getOrderList(this.userInfo.user_id, this.offset);
      this.orderList = [...this.orderList, ...res];
      this.hideLoading();
      if (res.length < 10) {
        return
      }*/
      this.preventRepeat = false;
    },
    //显示详情页
    showDetail(item){
      this.SAVE_ORDER(item);
      this.preventRepeat = false;
      this.$router.push('/orders/orderDetail/'+item.id)
    },
    //下单
    addOrder(){
      this.preventRepeat = false;
      this.$router.push('/orders/addOrder')
    },
    //生产环境与发布环境隐藏loading方式不同
    hideLoading(){
      this.showLoading = false;
    },
  },
  watch: {
    userInfo: function (value) {
      if (value && value.user_id && !this.orderList) {
        this.initData();
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import 'src/style/mixin';

.order_page{
  background-color: #f1f1f1;
  margin-bottom: 1.95rem;
p, span, h4{
  font-family: Helvetica Neue,Tahoma,Arial;
}
}
.order_list_ul{
  padding-top: 1.95rem;
.order_list_li{
  background-color: #fff;
  display: flex;
  margin-bottom: 0.5rem;
  padding: .6rem .6rem 0;
.restaurant_image{
@include wh(2rem, 2rem);
  margin-right: 0.4rem;
}
.order_item_right{
  flex: 5;
.order_item_right_header{
  border-bottom: 0.025rem solid #f5f5f5;
  padding-bottom: .3rem;
@include fj;
.order_header{
h4{
  display: flex;
  align-items: center;
  justify-content: flex-start;
@include sc(.75rem, #333);
  line-height: 1rem;
  width: 9rem;

.arrow_right{
@include wh(.4rem, .4rem);
  fill: #ccc;
  margin-right: .2rem;
}
}
.order_time{
@include sc(.55rem, #999);
  line-height: .8rem;
}
}
.order_status{
@include sc(.6rem, #333);
}
}
.order_basket{
@include fj;
  line-height: 2rem;
  border-bottom: 0.025rem solid #f5f5f5;
.order_name{
@include sc(.6rem, #666);
  width: 10rem;
}
.order_amount{
@include sc(.6rem, #f60);
  font-weight: bold;
}
}
.order_again{
  text-align: right;
  line-height: 1.6rem;
.buy_again{
@include sc(.55rem, #3190e8);
  border: 0.025rem solid #3190e8;
  padding: .1rem .2rem;
  border-radius: .15rem;
}
}
}
}
}
.loading-enter-active, .loading-leave-active {
  transition: opacity .7s
}
.loading-enter, .loading-leave-active {
  opacity: 0
}
.router-slid-enter-active, .router-slid-leave-active {
  transition: all .4s;
}
.router-slid-enter, .router-slid-leave-active {
  transform: translate3d(2rem, 0, 0);
  opacity: 0;
}

#head_top{
  background-color: $blue;
  position: fixed;
  z-index: 100;
  left: 0;
  top: 0;
@include wh(100%, 1.95rem);
}
.head_goback{
  left: 0.4rem;
@include wh(0.6rem, 1rem);
  line-height: 2.2rem;
  margin-left: .4rem;
}
.head_login{
  right: 0.55rem;
@include sc(0.65rem, #fff);
@include ct;
.login_span{
  color: #fff;
}
.user_avatar{
  fill: #fff;
@include wh(.8rem, .8rem);
}
}
.title_head{
@include center;
  width: 50%;
  color: #fff;
  text-align: center;
.title_text{
@include sc(0.8rem, #fff);
  text-align: center;
  font-weight: bold;
}
}
</style>
