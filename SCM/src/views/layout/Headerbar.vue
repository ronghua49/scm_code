<template>
  <div class="header">
      <div class="left" @click="changeIsCo">
          <img src="../../assets/img/menu.png" alt="">
      </div>
      <div class="right" >
        <img class="headimg" src="../../assets/img/profile_small.jpg" alt="">
        <b>{{username}}</b>
        <p @click="logout">
          <img class="exit" src="../../assets/img/exit.png" alt="">
          <b >退出</b>
        </p>
      </div>
  </div>
</template>

<script>
export default {
  name: 'headerbar',
  data () {
    return {
      headImg:'',
      username: sessionStorage.getItem('userName')
    }
  },
  methods: {
    changeIsCo () {
      this.$store.commit('changeIsCollapse')
    },
    logout() {
      sessionStorage.removeItem('token');
      sessionStorage.removeItem('ownerId');
      sessionStorage.removeItem('userName');
      sessionStorage.removeItem('menulist')
      this.$store.commit('changeAddRouterState')
      this.$router.push('/login');
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
    .header{
        width: 100%;
        height: 55px;
        background: #f3f3f4;
        display: flex;
        align-items: center;
        justify-content: space-between;
        .left {
          padding-left: 10px;
          cursor: pointer;
          img{
            width: 30px;
            height: 30px;
          }
        }
        
        .right{
          
          margin-right: 10px;
          vertical-align: middle;
          display: flex;
          align-items: center;
          .exit{
            width: 25px;
            height: 25px;
            margin-left: 5px;
            margin-right: 5px;
          }
          b{
            font-size: 14px;
            vertical-align: middle;
            color: #515151;
          }
          p{
            cursor: pointer;
            display: flex;
            align-items: center;
          }
        }
    }
    .headimg{
      border-radius: 50%;
      width: 40px;
      height: 40px;
      margin-right: 6px;
    }
</style>
