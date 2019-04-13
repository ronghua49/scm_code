<template>
  <div>
      <el-row>
        <img :src="flowImg" alt="">
      </el-row>
  </div>
</template>

<script>
import storage from '@/utils/storage'
export default {
  name: 'approvalflow',
  props:{
      flowData:{}
  },
  data () {
    return {
      flowImg:''
    }
  },
  watch:{
    'flowData.taskId':'getFlowImg'
  },
  mounted(){
    
  },
  methods:{
    getFlowImg(){
          this.$api.approval
          .get({
            url: "/runtime/instance/v1/getBpmImage",
            data: Object.assign({},this.flowData)})
          .then(res => {
            if (res.state == false) {
              this.$message({
                message: res.message,
                type: "error",
                duration: 800
              });
              return;
          }
          this.flowImg = res;
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>
