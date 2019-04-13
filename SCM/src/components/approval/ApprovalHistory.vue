<template>
    <!-- <div>{{msg}}</div> -->
    <div>
    <el-table ref="scmTable" :data="pageResult" :highlight-current-row="true"  
         v-loading="loading"   element-loading-text="加载中" :border="true" :stripe="true"
          show-overflow-tooltip='showOverflowTooltip' max-height="500" :size="size" align="center" style="width:100%;" >
      <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
      <el-table-column v-for="column in columns" header-align="center" align="center"
        :prop="column.prop" :label="column.label" :width="column.width" :min-width="column.minWidth" 
        :fixed="column.fixed" :key="column.prop" :type="column.type" :formatter="column.formatter"
        :sortable="column.sortable==null?true:column.sortable">
      </el-table-column>
    </el-table>
    </div>
</template>

<script>
import storage from '@/utils/storage'
export default {
  name: "approvalhistory", 
  components: {

  },
  props:{
      instId:{
          type: String,
          required: true
      }
  },
  watch:{
      'instId':'findPage'
  },
  data() {
    return {
	  showOverflowTooltip: true,
      size: "small",
      columns: [
        { prop: "taskName", label: "任务名称", minWidth: 120 },
        { prop: "createTime", label: "任务创建/更新时间", minWidth: 120 },
        { prop: "completeTime", label: "审批时间", minWidth: 120 },
        { prop: "qualfiedNames", label: "待执行人", minWidth: 120 },
        { prop: "auditorName", label: "执行人", minWidth: 120 },
        { prop: "statusVal", label: "审批状态", minWidth: 120 },
        { prop: "opinion", label: "审批意见", minWidth: 200 },
      ],
      loading: false,
      pageResult: [],
    };
  },
  mounted() {
      
  },
  methods: {
      findPage() {
      this.loading = true;
      this.$api.approval
          .get({
          url: "/runtime/instance/v1/instanceFlowOpinions",
          data: {
            instId:this.instId
          }
        })
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.pageResult = res;
          this.loading = false
        })
    },
  }
};
// clearSelection
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">

</style>
