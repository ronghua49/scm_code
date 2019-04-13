<template>
  <div>
    <el-container>
      
        <el-aside :width="sidewidth">
          <el-scrollbar class="elside" :width="sidewidth">
          <div class="appname">
            <p  v-if="sidewidth=='200px'">SCM管理系统</p>
            <p  v-else>SCM</p>
            <!-- <img v-else  src="../../assets/img/scm.png" alt=""> -->
          </div>
          <side-bar ></side-bar>
          </el-scrollbar>
        </el-aside>
      
        
        <el-container>
          <el-header>
            <header-bar></header-bar>  
          </el-header>
          <el-scrollbar>
            <el-main>
                <!-- <transition name="fade" mode="out-in">
                  <router-view/>
                </transition> -->
                <main-content></main-content>
            </el-main>
          </el-scrollbar>
          <!-- <el-footer>Footer</el-footer> -->
        </el-container>
    </el-container>
  </div>
</template>

<script>
import sidebar from "@/views/layout/Sidebar";
import headerbar from "@/views/layout/Headerbar";
import maincontent from "@/views/layout/MainContent";
export default {
  name: "layout",
  components: {
    "side-bar": sidebar,
    "header-bar": headerbar,
    "main-content": maincontent
  },
  data() {
    return {};
  },
  computed: {
    sidewidth() {
      return this.$store.state.app.isCollapse ? "64px" : "200px";
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.el-container {
  height: 100vh;
}
.el-aside {
  overflow-x: hidden;
  transition: 0.3s all linear;
  height: 100%;
  background: #2f4050;
}
.elside {
  background: #2f4050;
}
.el-scrollbar {
  height: 100%;
  // background: #2f4050;
}
.el-header,
.el-main {
  padding: 0;
}
.el-header {
  height: 55px !important;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
.appname {
  margin-top: 20px;
  color: #fff;
  font-weight: 600;
  font-size: 20px;
  height: 30px;
  p {
    white-space: nowrap;
    overflow: hidden;
  }
  img {
    width: 50px;
    height: 20px;
  }
}
</style>
