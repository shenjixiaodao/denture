<template>
  <div class="restContainer">
    <head-top head-title="注册账户" goBack="true"></head-top>
    <form class="restForm">
      <section class="input_container phone_number">
          <input type="text" placeholder="手机号" maxlength="11" v-model="user.username" @input="inputPhone">
          <!-- <button @click.prevent="getVerifyCode" :class="{right_phone_number:rightPhoneNumber}" v-show="!computedTime">获取验证码</button>
          <button  @click.prevent v-show="computedTime">已发送({{computedTime}}s)</button> -->
      </section>
      <section class="input_container">
        <input type="text" placeholder="请输入姓名" v-model="user.name">
      </section>
      <section class="input_container">
          <input type="password" placeholder="请输入密码" v-model="user.password">
      </section>
      <section class="input_container">
        <input v-if="!showClinic" type="text" placeholder="门诊名"  v-model="user.clinic.name">
        <input v-else type="text" placeholder="已有门诊编号"  v-model="user.clinic.id">
        <div class="button_switch" :class="{change_to_text: showClinic}">
          <div class="circle_button" :class="{trans_to_right: showClinic}" @click=" showClinic=!showClinic "></div>
          <span>已有</span>
          <span>...</span>
        </div>
      </section>
      <section class="input_container" v-if="!showClinic">
        <input type="text" placeholder="门诊地址" v-model="user.clinic.address">
      </section>
      <section class="input_container captcha_code_container">
        <input type="text" placeholder="验证码" name="mobileCode" maxlength="6" v-model="user.code">
        <div class="img_change_img">
            <img v-show="captchaCodeImg" :src="captchaCodeImg">
            <div class="change_img" @click="getCaptchaCode">
                <p>看不清</p>
                <p>换一张</p>
            </div>
        </div>
      </section>
      <section class="order_detail_style">
        <section class="item_style">
          <p class="guide_item_row">
            <input type="radio" v-model="user.role" value="Dentist" id="Dentist"/>
            <label for="Dentist">医生</label>
          </p>
          <p class="guide_item_row">
            <input type="radio" v-model="user.role" value="Nurse" id="Nurse"/>
            <label for="Nurse">护士</label>
          </p>
          <p class="guide_item_row">
            <input type="radio" v-model="user.role" value="Other" id="Other"/>
            <label for="Other">其它</label>
          </p>
        </section>
      </section>
    </form>
      <div class="login_container" @click="register">注册</div>
      <alert-tip v-if="showAlert" :showHide="showAlert" @closeTip="closeTip" :alertText="alertText"></alert-tip>
  </div>
</template>

<script>
import headTop from 'src/components/header/head'
import alertTip from 'src/components/common/alertTip'
import {mobileCode, checkExsis, sendMobile, getcaptchas, changePassword} from 'src/service/getData'

export default {
    data(){
        return {
          user: {
            username: null,
            name: null,
            password: null,
            clinic: {
              id: null,
              name: null,
              address: null
            },
            role: 'Nurse',
            code: null
          },
          captchaCodeImg: null,
          showAlert: false,
          alertText: null,
          showClinic: false, // 是否已有门诊
        }
    },
    components: {
        headTop,
        alertTip,
    },
    created(){
        this.getCaptchaCode()
    },
    methods: {
        //判断输入的电话号码
        inputPhone(){
            if(/.+/gi.test(this.phoneNumber)){
                this.rightPhoneNumber = true;
            }else{
                this.rightPhoneNumber = false;
            }
        },
        //获取验证吗
        async getVerifyCode(){
            if (this.rightPhoneNumber) {
                this.computedTime = 30;
                //倒计时
                this.timer = setInterval(() => {
                    this.computedTime --;
                    if (this.computedTime == 0) {
                        clearInterval(this.timer)
                    }
                }, 1000)
                //判断用户是否存在
                let res = await checkExsis(this.phoneNumber, this.accountType);
                //判断返回的信息是否正确，用户是否注册
                if (res.message) {
                    this.showAlert = true;
                    this.alertText = res.message;
                    return
                }else if(!res.is_exists) {
                    this.showAlert = true;
                    this.alertText = '您输入的手机号尚未绑定';
                    return
                }
                //获取验证信息
                let getCode = await mobileCode(this.phoneNumber);
                if (getCode.message) {
                    this.showAlert = true;
                    this.alertText = getCode.message;
                    return
                }
                this.validate_token = getCode.validate_token;
            }
        },
         async getCaptchaCode(){
            let res = await getcaptchas();
            this.captchaCodeImg = res.code;
        },
        //重置密码
        async register(){
            if (!this.user.username) {
                this.showAlert = true;
                this.alertText = '请输入正确的账号';
                return
            } else if(!this.user.password){
                this.showAlert = true;
                this.alertText = '请输入密码';
                return
            } else if(!this.user.code){
                this.showAlert = true;
                this.alertText = '请输验证码';
                return
            }

            this.$store.dispatch('Register', this.user).then(() => {
              // 登录成功后转至用户管理界面
              this.$router.push('/profile')
            }).catch(() => {
              this.getCaptchaCode()
            })
        },
        closeTip(){
            this.showAlert = false;
        }
    }
}

</script>

<style lang="scss" scoped>
    @import 'src/style/mixin';

    .restContainer{
        padding-top: 1.95rem;
    }
    .restForm{
        background-color: #fff;
        margin-top: .6rem;
        .input_container{
            display: flex;
            justify-content: space-between;
            padding: .6rem .8rem;
            border-bottom: 1px solid #f1f1f1;
            input{
                @include sc(.7rem, #666);
            }
            button{
                @include sc(.65rem, #fff);
                font-family: Helvetica Neue,Tahoma,Arial;
                padding: .28rem .4rem;
                border: 1px;
                border-radius: 0.15rem;
            }
            .right_phone_number{
                background-color: #4cd964;
            }
        }
        .phone_number{
            padding: .3rem .8rem;
        }
        .captcha_code_container{
            height: 2.2rem;
            .img_change_img{
                display: flex;
                align-items: center;
                img{
                    @include wh(3.5rem, 1.5rem);
                    margin-right: .2rem;
                }
                .change_img{
                    display: flex;
                    flex-direction: 'column';
                    flex-wrap: wrap;
                    width: 2rem;
                    justify-content: center;
                    p{
                        @include sc(.55rem, #666);
                    }
                    p:nth-of-type(2){
                        color: #3b95e9;
                        margin-top: .2rem;
                    }
                }
            }
        }
    }
    .captcha_code_container{
        height: 2.2rem;
        .img_change_img{
            display: flex;
            align-items: center;
            img{
                @include wh(3.5rem, 1.5rem);
                margin-right: .2rem;
            }
            .change_img{
                display: flex;
                flex-direction: 'column';
                flex-wrap: wrap;
                width: 2rem;
                justify-content: center;
                p{
                    @include sc(.55rem, #666);
                }
                p:nth-of-type(2){
                    color: #3b95e9;
                    margin-top: .2rem;
                }
            }
        }
    }
    .login_container{
        margin: 1rem .5rem;
        @include sc(.7rem, #fff);
        background-color: #4cd964;
        padding: .5rem 0;
        border: 1px;
        border-radius: 0.15rem;
        text-align: center;
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

    .guide_item_row{
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

    .button_switch{
      background-color: #ccc;
      display: flex;
      justify-content: center;
    @include wh(2rem, .7rem);
      padding: 0 .2rem;
      border: 1px;
      border-radius: 0.5rem;
      position: relative;
    .circle_button{
      transition: all .3s;
      position: absolute;
      top: -0.2rem;
      z-index: 1;
      left: -0.3rem;
    @include wh(1.2rem, 1.2rem);
      box-shadow: 0 0.026667rem 0.053333rem 0 rgba(0,0,0,.1);
      background-color: #f1f1f1;
      border-radius: 50%;
    }
    .trans_to_right{
      transform: translateX(1.3rem);
    }
    span{
    @include sc(.45rem, #fff);
      transform: translateY(.05rem);
      line-height: .6rem;
    }
    span:nth-of-type(2){
      transform: translateY(-.08rem);
    }
    }

    .change_to_text{
      background-color: #4cd964;
    }

</style>
