<template>
  <div>
      <div class="login-container">
          <div class="login">
            <div class="login-left">
                <img src="../../assets/img/download.png" alt="">
            </div>
            <div class="login-right">
                <div class="login-right-hd">
                    <img height="48" src="../../assets/img/logo-ww.png" alt="">
                </div>
                <p class="login-title">SCM登陆</p>
                <div class="login-right-bd">
                    <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2"  class="demo-ruleForm">
                        <el-form-item  prop="account">
                            <el-input type="text" placeholder="账号" v-model="ruleForm2.account" @keyup.native.enter="submitForm('ruleForm2')"  autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item  prop="pass">
                            <el-input type="password" placeholder="密码" v-model="ruleForm2.pass" @keyup.native.enter="submitForm('ruleForm2')" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" round @click="submitForm('ruleForm2')">登录</el-button>
                        </el-form-item>
                    </el-form>
                </div>
                <div class="login-right-ft">
                    Copyright©2017深圳万威科技版权所有
                </div>
            </div>
          </div>
      </div>
  </div>
</template>

<script>
import axios from 'axios';
import global from '@/utils/global'
import storage from '@/utils/storage'
export default {
  name: "login",
  data() {
    var checkAccount = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("账号不能为空"));
      }else {
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };
    return {
        ruleForm2: {
          pass: '',
          account: '',
        },
        rules2: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          account: [
            { validator: checkAccount, trigger: 'blur' }
          ]
        }
    };
  },
  methods: {
        submitForm (formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                this.$api.login.login({
                    username: this.ruleForm2.account,
                    password: this.ruleForm2.pass
                }).then((res) => {
                    if(res.code == 500){
                        this.$message({
                            message: res.message,
                            type: 'error',
                            duration: 500
                        });
                        return 
                    };
                    
                    sessionStorage.setItem('token', res.token)
                    // sessionStorage.setItem('token', res.token)
                    sessionStorage.setItem('userName', res.username)
                    sessionStorage.setItem('userId', res.userId)
                    this.$store.commit('setToken', res.token)
                    this.getMenu(res.token);
                    this.getOwnerId();
                })
            } else {
                return false;
            }
            });
        },
        getOwnerId(){
            this.$api.common.getLoginId().then(
                res => {
                    storage.set('ownerId', res.compId)
                }
            );
        },
        getMenu(token){
            axios({
                method:'get', 
                url: global.menuUrl+'/sys/sysMenu/v1/getCurrentUserMenu',
                headers: {
                "Authorization": "Bearer " + token,
                },
                params: {
                menuType: 1
                }
            }).then((res)=>{
                let data = res.data;
                // 从服务端请求左侧菜单栏，包括父菜单和子菜单
                this.menuData = data.value.reduce((finalMenu, item, index, arr)=>{
                    if(item.alias.includes('SCMcdgl')){
                    finalMenu = item.children
                    sessionStorage.setItem('menulist', JSON.stringify(finalMenu))
                    }
                    return finalMenu
                },[])
                this.$router.push('/');
            });
        }
    }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.login-container {
  height: 100%;
  width: 100%;
  .login {
    display: flex;
    width: 1000px;
    height: 540px;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(192, 192, 192, 0.5);
    margin: 7% auto;
    .login-left {
      width: 677px;
      height: 540px;
    }
    .login-right {
      width: 300px;
      position: relative;
      border-left: 1px solid #f8f8f8;
      .login-title {
        font-family: PingFang-SC-Medium;
        font-size: 18px;
        color: #7e7c7c;
        text-align: center;
        line-height: 20px;
        text-align: center;
        margin-bottom: 20px;
      }
      .login-right-hd {
        padding: 76px 59px;
      }
      .login-right-ft {
            position: absolute;
            bottom: 10px;
            width: 100%;
            text-align: center;
            color: #CECED0;
            font-size: 13px;
        }
        .el-form-item{
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .el-button{
            width: 190px;
        }
    }
  }
}
</style>
