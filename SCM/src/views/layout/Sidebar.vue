<template>
  <div>
    <!-- <el-col> -->
    <el-menu
      default-active=""
      background-color="#2f4050"
      text-color="#a7b1c2"
      router
      unique-opened
      :collapse="isCollapse"
      :class="isCollapse?'menu-bar-collapse-width':'menu-bar-width'"
      active-text-color="#fff">
      <menu-list :menulist="menuData"></menu-list>
    </el-menu>
  <!-- </el-col> -->
  </div>
</template>

<script>
import { mapState } from 'vuex'
import Menulist from './Menulist'
// import axios from 'axios'
// import global from '@/utils/global'
export default {
  name: "sidebar",
  components: {
    "menu-list": Menulist,
  },
  data() {
    return {
      menuData: [],
    };
  },
  mounted () {
      var menulist = JSON.parse(sessionStorage.getItem('menulist'))
      if(menulist){
        this.menuData = menulist
        return ;
      }
  },
  computed:  {
      ...mapState({
        isCollapse: state => state.app.isCollapse
      }),
      mainTabs: {
        get () { return this.$store.state.tab.mainTabs },
        set (val) { this.$store.commit('updateMainTabs', val) }
      },
      mainTabsActiveName: {
        get () { return this.$store.state.tab.mainTabsActiveName },
        set (val) { this.$store.commit('updateMainTabsActiveName', val) }
      }
  },
  watch: {
    $route: 'handleRoute'
  },
  created () {
    this.handleRoute(this.$route)            
  },
  methods: {
    handleRoute (route) {
      // tab标签页选中, 如果不存在则先添加
      var tab = this.mainTabs.filter(item => item.name === route.name)[0]
      if (!tab) {
        tab = {
          name: route.name,
          title: route.name,
          icon: route.meta.icon,
          params:route.query,
        }
        this.mainTabs = this.mainTabs.concat(tab)
      }else {
      	let tab = {
          name: route.name,
          title: route.name,
          icon: route.meta.icon,
          params:route.query,
        }
      	let newMaintabs = this.mainTabs
      	for(let i = 0;i<newMaintabs.length;i++){
      		if(tab.name == newMaintabs[i].name){
      			newMaintabs.splice(i, 1, tab)
      		}
      	}
      	this.mainTabs = newMaintabs;
      }
      this.mainTabsActiveName = tab.name
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
  .el-menu{
    border-right: 0;
    text-align: left;
    transition: .3s all linear;
  }
  .menu-bar-width {
    width: 200px;
  }
  .menu-bar-collapse-width {
    width: 64px;
  }
  
</style>
