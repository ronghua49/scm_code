<template>
  <div>
    <template v-for="list in this.menulist" > 
        <el-submenu  v-if="list.children.length>0" :key="list.id" :index="list.id"  > 
          <template slot="title">
            <i :class="list.menuIcon"></i>
            <span slot="title">{{ list.name}}</span>
          </template>
          <menu-list  :menulist="list.children"></menu-list>
        </el-submenu>
        <el-menu-item v-else :index="list.menuUrl?list.menuUrl:''"  :key="list.id" >
          <span>{{list.name}}</span>
        </el-menu-item>
      </template>
  </div>
</template>

<script>
export default {
  name: 'MenuList',
  props: ['menulist'],
  data () {
    return {
      
    }
  },
  methods:{
    go(){
      console.log()
    },
    handleRoute(list){

    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .el-menu--collapse  span,
  .el-menu--collapse  i.el-submenu__icon-arrow
  {
    height: 0;
    width: 0;
    overflow: hidden;
    visibility: hidden;
    display: inline-block;
  }
  
</style>
