<template>
<div>
    <header class="header">
        <h1>商业认可状态审批</h1>
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
                <i class="el-icon-tickets"></i>&nbsp;商业认可名单
            </div>
            <div class="header-right"></div>
        </div>
        <!--表格栏-->
        <el-table class="test" :data="pageResult.rows" highlight-current-row v-loading="loading" element-loading-text="加载中"
          border stripe fit
          max-height="500" size="mini" align="left" style="width:100%;">
          <el-table-column width="55"><el-checkbox disabled="true" checked="true"></el-checkbox></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="businessDivision" key="businessDivision" label="商务分区" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="province" key="province" label="省区" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="commerceCode" key="commerceCode" label="商业编码" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="commerceName" key="commerceName" label="商业名称" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="conpanyPersion" key="conpanyPersion" label="责任人" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="enterDate" key="enterDate" label="开户日期" header-align="center" align="center"></el-table-column>
          <el-table-column :formatter="formatAcceptState" show-overflow-tooltip="true" prop="acceptState" key="acceptState" label="认可状态" header-align="center" align="center">
                <template slot-scope="scope">
                    <el-select disabled v-model="scope.row.acceptState" placeholder="请选择">
                        <el-option label="认可" value="0"></el-option>
                        <el-option label="考察中" value="1"></el-option>
                        <el-option label="终止" value="2"></el-option>
                    </el-select>
                </template>
          </el-table-column>
          <el-table-column align="center" label="生效年月">
                <el-table-column :formatter="formatStartDate" show-overflow-tooltip="true" prop="startDate" key="startDate" label="起始日期" header-align="center" align="center"></el-table-column>
                <el-table-column :formatter="formatEndDate" show-overflow-tooltip="true" prop="endDate" key="endDate" label="结束日期" header-align="center" align="center"></el-table-column>
          </el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="memo" key="memo" label="备注" header-align="center" align="center"></el-table-column>
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
            // 保存父组件传过来的数据
            passparamObj: {},
            ownerId: "",
            pageResult: {
                rows:[],
                currentPageList:[]
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
      formatStartDate (row, column) {
          return row.startDate.split(' ')[0];
      },
      formatEndDate (row, column) {
          return row.endDate.split(' ')[0];

      },
      formatAcceptState (row, column) {
          let val = '';
          switch(row.marketWay) {
              case '0':
                  val = '认可';
                  break;
              case '1':
                  val = '考察中';
                  break;
              case '2':
                  val = '终止';
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
          self.getNameList();
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
      // 获取商业认可名单
      getNameList () {
          let self = this;
		  self.loading = true;
		
          self.$api.xsgl
            .post({
                vueInstance: self,
                url: "/scm/scmZsjCommerceAcceptState/v1/firstList",
                data: {
                    pageBean: self.pageRequest,
                    params: {},
                    querys: [
                        {hasInitValue:false,property:"approvalId", value: self.passparamObj.instId, operation: "EQUAL", relation: "AND"},
                        {hasInitValue:false,property:"ownerId", value: self.ownerId, operation: "EQUAL", relation: "AND"}   
                    ],
                    sorter: []
                }
            }, function(res) {
                console.log(res);
                self.loading = false;
                self.pageResult.rows = res.rows;
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

