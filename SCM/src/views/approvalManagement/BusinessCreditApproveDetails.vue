<template>
<div>
    <header class="header">
        <h1>商业资信审批</h1>
        <div class="header-btn-group">
          <!-- <el-button icon="el-icon-error" @click="saveApply" size="mini" type="warning">驳回</el-button>
          <el-button icon="el-icon-success" @click="sendApply" size="mini" type="primary">同意</el-button> -->
		  <approval-btn :taskId="passparamObj.id" @handleSuccess="approvalSuccess"></approval-btn>
        </div>
        <div class="header-back">
            <el-button @click="showParentComp" size="mini" type="primary">返回</el-button>
        </div>
    </header>
    <transition name="el-fade-in">
    <div>
    <!--表格数据-->
    <div class="table-section">
        <div class="header">
            <div class="header-left">
                <i class="el-icon-tickets"></i>&nbsp;
                <span style="color: #409EFF;">【{{nameListTitle.businessDivision}}】</span>商业名单
            </div>
            <div class="header-right"></div>
        </div>
        <!--表格栏-->
        <el-table class="test" :data="pageResult.rows" highlight-current-row v-loading="loading" element-loading-text="加载中"
          border stripe fit
          max-height="500" size="mini" align="left" style="width:100%;">
          <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
          <el-table-column prop="year" key="year" label="年份" min-width="100" header-align="center" align="center" ></el-table-column>
          <el-table-column prop="quarter" key="quarter" label="季度" min-width="100"  header-align="center" align="center"></el-table-column>
          <el-table-column prop="businessDivision" key="businessDivision" label="商务分区" header-align="center" align="center" min-width="100"></el-table-column>
          <el-table-column prop="province" key="province" label="省区" header-align="center" align="center" min-width="100"></el-table-column>
          <el-table-column header-align="center" align="center" label="经销商">
            <el-table-column prop="commerceCode" key="commerceCode" label="编码" header-align="center" align="center" min-width="100"></el-table-column>
            <el-table-column show-overflow-tooltip="ture" prop="commerceName" key="commerceName" label="名称" header-align="center" align="center" min-width="120"></el-table-column>
          </el-table-column>
          <el-table-column :formatter="resetMarketWay" prop="marketWay" key="marketWay" label="购销方式" header-align="center" align="center" min-width="120"></el-table-column>
          <el-table-column header-align="center" align="center" label="月均发货额(万)">
            <el-table-column prop="beforeYearPrice" key="beforeYearPrice" label="前年" header-align="center" align="center" min-width="100"></el-table-column>
            <el-table-column prop="lastYearPrice" key="lastYearPrice" label="去年" header-align="center" align="center" min-width="100"></el-table-column>
          </el-table-column>
          <el-table-column header-align="center" align="center" label="资信额(万)">
            <el-table-column prop="lastTimePrice" key="lastTimePrice" label="上次批准" min-width="100" header-align="center" align="center"></el-table-column>
            <el-table-column prop="sysPrice" key="sysPrice" label="系统预测" header-align="center" align="center" min-width="100"></el-table-column>
          </el-table-column>
          <el-table-column header-align="center" align="center" label="申请">
            <el-table-column prop="price" key="price" label="资信额度(万)" header-align="center" align="center" min-width="100"></el-table-column>
            <el-table-column prop="memo" key="memo" label="申请备注" header-align="center" align="center" min-width="180"></el-table-column>
          </el-table-column>
        </el-table>
    </div>
    <!--审批流程图-->
     <div class="header">
        <div class="header-left">
            <i class="el-icon-tickets"></i>&nbsp;流程图
        </div>      
        <div class="header-right"></div>
    </div>
    <approval-flow :flowData="passparamObj"></approval-flow>
    <!--审批历史-->
    <div class="header">
        <div class="header-left">
            <i class="el-icon-tickets"></i>&nbsp;审批历史
        </div>      
        <div class="header-right"></div>
    </div>
    <approval-history :instId="passparamObj.instId"></approval-history>
    </div>
    </transition>
</div>
</template>

<script>
import approvalBtn from "@/components/approval/ApprovalBtn"
import approvalFlow from "@/components/approval/ApprovalFlow"
import approvalHistory from "@/components/approval/ApprovalHistory"
import storage from "@/utils/storage";
export default {
    name: "businessCreditApproveDetails",
    components: {
        'approval-btn': approvalBtn,
		'approval-flow': approvalFlow,
		'approval-history': approvalHistory,
    },
    props: ['passparam'],
    data () {
        return {
            nameListTitle: {
                businessDivision: '-'
            },
            // 保存父组件传过来的数据
            passparamObj: {},
            ownerId: "",
            pageResult: { // 商业名单列表
                rows:[]
            },

            loading: false,
            pageRequest: {
                page: 1,
                pageSize: 10,
                showTotal: true
            }
        };
    },
    watch: {
        passparam: 'getPassParam'
    },
    mounted() {
      this.ownerId = storage.get("ownerId");
    },
    methods: {
      resetMarketWay (row) {
          let val = '';
          switch(row.marketWay) {
              case '0':
                  val = '预付款';
                  break;
              case '1':
                  val = '赊销';
                  break;
              default:
                  break;
          }
          return val;
      },
      getPassParam () {
          // 将父组件传过来的参数解析成对象保存在另外一个变量里
          let self = this;
          self.passparamObj = JSON.parse(self.passparam);
          console.log(self.passparamObj);
          this.getNameList();
      },
      refreshPageRequest: function(page = 1) {
        this.pageRequest.page = page;
        this.findPage();
      },
      refreshPageSizeRequest: function(pageSize) {
        this.pageRequest.pageSize = pageSize;
        this.findPage();
      },
      // 向父组件传值
      showParentComp () {
        this.$emit("closedetailspage",{});
      },

      approvalSuccess() { // 点击同意按钮后通知主页面刷新
          let self = this;
          self.showParentComp();
	  },
      // 获取商业名单
      getNameList () {
          let self = this;
		  self.loading = true;
		  self.$api.xsgl.get({
            vueInstance: self,
            // /scm/scmXsCreditCollect1/getCreditApplyByApprovalId/{id}  传审批id
			url: "/scm/scmXsCreditCollect/v1/getCreditApplyByApprovalId/" + self.passparamObj.instId,
			data: {}
		  }, function(res) {
            console.log(res);
            self.loading = false;
            self.pageResult.rows = res.scmXsCreditApplyList;
            if(res.scmXsCreditApplyList.length > 0) {
                self.nameListTitle.businessDivision = res.scmXsCreditApplyList[0].businessDivision;
            }
          });
      }
    }
}
</script>
<style scoped lang="scss">
.header {
  position: relative;
  font-size: 28px;
  padding: 5px;
  background: #f3f3f3;
  display: flex;
  justify-content: center;
  line-height: 1em;
  margin-top:10px;
}
.header h1{
  font-size: 28px;
  text-align: center;
}
.header-btn-group{
  position: absolute;
  
  right: 5px;
  top:0;
  display: flex;
  align-items: center;
}
.header-back{
  position: absolute;
  height: 100%;
  left: 5px;
  top:0;
  display: flex;
  align-items: center;
}
.query-section{
    margin-bottom: 20px;
}
.header-left, .header-right{
    width:50%;
    display: flex;
    align-items: center;
    font-size: 16px;
}
.header-right{
    justify-content: flex-end;
}
.el-form {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  .el-form-item {
    text-align: left;
    width: 33%;
    margin:0;
  }
}
.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.addnew-dialog {
  .el-form {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items: center;
    margin: 10px 0;
    .el-form-item {
      width: 40%;
      margin:0;
    }
  }
}
.apply-success-section{
    margin: 150px auto auto auto;
    width:60%;
    border: 1px solid #dddddd;
    padding: 5px;
    border-radius: 3px;
    display:flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.success, .success-bottom{
    width: 100%;
}
</style>

