<template>
  <div id="main-container" class="main-container">
    <!-- 标签页 -->
    <div class="tab-container">
      <el-tabs class="tabs" 
        v-model="mainTabsActiveName" :closable="true" type="card"
        @tab-click="selectedTabHandle" @tab-remove="removeTabHandle">
        <el-tab-pane v-for="item in mainTabs"
          :key="item.name" :label="item.title" :name="item.name">
          <span slot="label"><i :class="item.icon"></i> {{item.title}} </span>
        </el-tab-pane>
      </el-tabs>
      <el-dropdown class="tabs-tools" :show-timeout="0" trigger="hover">
          <div style="font-size:20px;width:50px;"><i class="el-icon-arrow-down"></i></div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="tabsCloseCurrentHandle">关闭当前标签</el-dropdown-item>
            <el-dropdown-item @click.native="tabsCloseOtherHandle">关闭其它标签</el-dropdown-item>
            <el-dropdown-item @click.native="tabsCloseAllHandle">关闭全部标签</el-dropdown-item>
            <el-dropdown-item @click.native="tabsRefreshCurrentHandle">刷新当前标签</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
    </div>
    <!-- 主内容区域 -->
        <div class="main-content">
          <keep-alive>
            <transition name="fade" mode="out-in">
                <router-view></router-view>
            </transition>
          </keep-alive>
        </div>
  </div>
</template>

<script>
export default {
  name:'maincontent',
  data () {
    return {
    }
  },
  created() {
    let self = this;
    this.$root.eventHub.$on('closeBusinessCreditApply',(target) => {
        console.log('接收到事件closeBusinessCreditApply');
        console.log(self.mainTabs);
        //self.tabsCloseOtherHandle();
        self.removeTabHandle ('商业资信申请');
    });
    this.$root.eventHub.$on('closeBusinessCreditManage',(target) => {
        console.log('接收到事件closeBusinessCreditManage');
        console.log(self.mainTabs);
        //self.tabsCloseOtherHandle();
        self.removeTabHandle ('商业资信管理');
    });
  },
  computed: {
    mainTabs: {
      get () { return this.$store.state.tab.mainTabs },
      set (val) { this.$store.commit('updateMainTabs', val) }
    },
    mainTabsActiveName: {
      get () { return this.$store.state.tab.mainTabsActiveName },
      set (val) { this.$store.commit('updateMainTabsActiveName', val) }
    }
  },
  methods: {
    // tabs, 选中tab
    selectedTabHandle (tab) {
      tab = this.mainTabs.filter(item => item.name === tab.name)
      if (tab.length >= 1) {
      	if(tab[0].name=="采购合同管理详情"){
      		if(this.$store.state.cght){
      			this.$router.push({
							path: '/cgdd/cgContractDeial',
							query: {
								htid: this.$store.state.cght
							}
						})
      		}
      		if(this.$store.state.cghtDeialId){
      			this.$router.push({
							path: '/cgdd/cgContractDeial',
							query: {
								id: this.$store.state.cghtDeialId
							}
						})
      		}
      		console.log(this.$store.state)
      	}else{
        	this.$router.push({ name: tab[0].name,query:tab[0].params })
      	}
      }
    },
    // tabs, 删除tab
    removeTabHandle (tabName) {
        console.log(tabName);
      this.mainTabs = this.mainTabs.filter(item => item.name !== tabName)
      if (this.mainTabs.length >= 1) {
        // 当前选中tab被删除
        if (tabName === this.mainTabsActiveName) {
          this.$router.push({ name: this.mainTabs[this.mainTabs.length - 1].name,query: this.mainTabs[this.mainTabs.length - 1].params }, () => {
            this.mainTabsActiveName = this.$route.name
          })
        }
      } else {
        this.$router.push("/")
      }
    },
    // tabs, 关闭当前
    tabsCloseCurrentHandle () {
      this.removeTabHandle(this.mainTabsActiveName)
    },
    // tabs, 关闭其它
    tabsCloseOtherHandle () {
      this.mainTabs = this.mainTabs.filter(item => item.name === this.mainTabsActiveName)
    },
    // tabs, 关闭全部
    tabsCloseAllHandle () {
      this.mainTabs = []
      this.$router.push("/")
    },
    // tabs, 刷新当前
    tabsRefreshCurrentHandle () {
    	var routeNow = this.$route;
      var tempTabName = this.mainTabsActiveName
      this.removeTabHandle(tempTabName)
      this.$nextTick(() => {
      	var obj ={name: tempTabName}
      	if(routeNow.query != ''){
      		obj.query = routeNow.query
      	}
        this.$router.push(obj)
      })
    }
  }
}
</script>

<style scoped lang="scss">
.main-container {
  padding: 0;
  margin-top: 40px;
  .tabs {
    height: 40px;
    line-height: 40px;
    font-size: 14px;
    background: rgb(255, 253, 255);
    border-color: rgba(200, 206, 206, 0.5);
    border-bottom-width: 1px;
    border-bottom-style: solid;
    z-index: 1020;
  }

}
.fade-enter-active, .fade-leave-active {
  transition: opacity .2s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
.tab-container{
  position: fixed;
  top: 55px;
  width: 100%;
  z-index: 1020;
}
.el-scrollbar, .main-content {
  height: 100%;
}
.main-content{
  padding:10px;
}
.tabs-tools {
    position: fixed;
    top: 55px;
    right: 0;
    z-index: 1020;
    height: 40px;
    // padding: 0 10px;
    font-size: 14px;
    line-height: 40px;
    cursor: pointer;
    border-color: rgba(200, 206, 206, 0.5);
    border-left-width: 1px;
    border-left-style: solid;
    border-bottom-width: 1px;
    border-bottom-style: solid;
    background: rgba(255, 255, 255, 1);
  }
</style>