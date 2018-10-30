<template>
    <section id='foot_guide'>
    	<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="position:absolute;width:0;height:0">
	    	<defs>

				<symbol xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 38 38" id="order"><defs><rect id="order-regular.41c17f8_a" width="38" height="38" rx="2"></rect><mask id="order-regular.41c17f8_b" width="38" height="38" x="0" y="0" fill="#fff"><use xlink:href="#order-regular.41c17f8_a"></use></mask></defs><g fill="none" fill-rule="evenodd"><use stroke="#666" stroke-width="4" mask="url(#order-regular.41c17f8_b)" xlink:href="#order-regular.41c17f8_a"></use><rect width="24" height="2" x="7" y="8" fill="#666" rx="1"></rect><rect width="20" height="2" x="7" y="17" fill="#666" rx="1"></rect><rect width="8" height="2" x="7" y="26" fill="#666" rx="1"></rect></g></symbol>
				<symbol viewBox="0 0 38 38" id="orderActive"><defs><linearGradient id="order.070ae2a_a" x1="50%" x2="50%" y1="100%" y2="0%"><stop offset="0%" stop-color="#2BAEFF"></stop><stop offset="100%" stop-color="#0095FF"></stop></linearGradient></defs><g fill="none" fill-rule="evenodd"><rect width="38" height="38" fill="url(#order.070ae2a_a)" rx="2"></rect><rect width="24" height="2" x="7" y="8" fill="#FFF" rx="1"></rect><rect width="20" height="2" x="7" y="17" fill="#FFF" rx="1"></rect><rect width="8" height="2" x="7" y="26" fill="#FFF" rx="1"></rect></g></symbol>

				<symbol xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 38 38" id="profile"><defs><path id="profile-regular.c151d62_a" d="M10 11.833V8.999A8.999 8.999 0 0 1 19 0c4.97 0 9 4.04 9 8.999v2.834l-.013.191C27.657 16.981 23.367 21 19 21c-4.616 0-8.64-4.02-8.987-8.976L10 11.833z"></path><mask id="profile-regular.c151d62_c" width="18" height="21" x="0" y="0" fill="#fff"><use xlink:href="#profile-regular.c151d62_a"></use></mask><path id="profile-regular.c151d62_b" d="M0 32.675C0 26.763 10.139 22 19.027 22 27.916 22 38 26.763 38 32.757v3.312C38 37.136 37.098 38 35.997 38H2.003C.897 38 0 37.137 0 36.037v-3.362z"></path><mask id="profile-regular.c151d62_d" width="38" height="16" x="0" y="0" fill="#fff"><use xlink:href="#profile-regular.c151d62_b"></use></mask></defs><g fill="none" fill-rule="evenodd" stroke="#666" stroke-width="4"><use mask="url(#profile-regular.c151d62_c)" xlink:href="#profile-regular.c151d62_a"></use><use mask="url(#profile-regular.c151d62_d)" xlink:href="#profile-regular.c151d62_b"></use></g></symbol>
				<symbol viewBox="0 0 38 38" id="profileActive"><defs><linearGradient id="profile.dbc5ebf_a" x1="50%" x2="50%" y1="100%" y2="0%"><stop offset="0%" stop-color="#2BAEFF"></stop><stop offset="100%" stop-color="#0095FF"></stop></linearGradient></defs><path fill="url(#profile.dbc5ebf_a)" fill-rule="evenodd" d="M10 11.833V8.999A8.999 8.999 0 0 1 19 0c4.97 0 9 4.04 9 8.999v2.834l-.013.191C27.657 16.981 23.367 21 19 21c-4.616 0-8.64-4.02-8.987-8.976L10 11.833zM0 32.675C0 26.763 10.139 22 19.027 22 27.916 22 38 26.763 38 32.757v3.312C38 37.136 37.098 38 35.997 38H2.003C.897 38 0 37.137 0 36.037v-3.362z"></path></symbol>

	    	</defs>
    	</svg>
        <section @click = "gotoAddress('/orders')" class="guide_item">
        	<svg class="icon_style">
                <use xmlns:xlink="http://www.w3.org/1999/xlink" :xlink:href="$route.path.indexOf('orders') !== -1? '#orderActive' : '#order'"></use>
            </svg>
            <span>订单</span>
        </section>
        <section @click = "gotoAddress('/profile')" class="guide_item">
        	<svg class="icon_style">
                <use xmlns:xlink="http://www.w3.org/1999/xlink" :xlink:href="$route.path.indexOf('profile') !== -1? '#profileActive' : '#profile'"></use>
            </svg>
            <span>我的</span>
        </section>
    </section>
</template>

<script>
import {mapState} from 'vuex'
export default {
  data(){
        return{

        }
    },
    created(){

    },
    mounted(){

    },
    computed: {
        ...mapState([
            'geohash'
        ]),
    },
    methods: {
      gotoAddress(path){
        this.$router.push(path)
      }
    },

}
</script>

<style lang="scss" scoped>
    @import '../../style/mixin';

    #foot_guide{
        background-color: #fff;
        position: fixed;
        z-index: 100;
        left: 0;
        right: 0;
        bottom: 0;
        @include wh(100%, 1.95rem);
        display: flex;
        box-shadow: 0 -0.026667rem 0.053333rem rgba(0,0,0,.1);
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

</style>
