<template>
<div>
    <header class="header">
        <h1>分销商协议合作条款审批</h1>
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
                <span style="color: #409EFF;">【{{nameListTitle.businessDivision}}】</span>
                <span style="color: #409EFF;">【{{nameListTitle.province}}】</span>
                的分销商协议合作条款
            </div>
            <div class="header-right"></div>
        </div>
        <!--表格栏-->
        <el-table class="test" :data="pageResult.rows" highlight-current-row v-loading="loading" element-loading-text="加载中"
          border stripe fit
          max-height="500" size="mini" align="left" style="width:100%;">
          <el-table-column prop="clauseCode" key="clauseCode" label="编码" min-width="100" header-align="center" align="center" ></el-table-column>
          <el-table-column header-align="center" align="center" label="分商业">
            <el-table-column prop="commerceName" key="commerceName" label="名称" header-align="center" align="center" min-width="100"></el-table-column>
            <el-table-column prop="commerceLevel" key="commerceLevel" label="行政级别" header-align="center" align="center" min-width="100"></el-table-column>
            <el-table-column prop="scm_zsj_commerceState" key="scm_zsj_commerceState" label="商业认可状态" header-align="center" align="center" min-width="100"></el-table-column>
            <!-- 市场类型是从后台获取的 -->
            <el-table-column prop="functionType" key="functionType" label="市场类型" header-align="center" align="center" min-width="180">
                <template slot-scope="scope">
                    <el-select disabled class="dropdown-select" v-model="scope.row.functionType" placeholder="请选择">
                        <el-option
                          v-for="item in functionTypeList"
                          :key="item.id"
                          :label="item.functionType"
                          :value="item.id"
                        ></el-option>
                    </el-select>
                </template>
            </el-table-column>
            
            <el-table-column prop="isSubsidiary" key="isSubsidiary" label="是否子分公司" header-align="center" align="center" min-width="100">
                <template slot-scope="scope">
                    <el-checkbox disabled true-label="1" false-label="0" v-model="scope.row.isSubsidiary"></el-checkbox>
                </template>
            </el-table-column>
            
            <el-table-column prop="starLevel" key="starLevel" label="星级" header-align="center" align="center" min-width="120">
                <template slot-scope="scope">
                    <el-select disabled v-model="scope.row.starLevel" placeholder="请选择">
                        <el-option label="一星" value="0"></el-option>
                        <el-option label="二星" value="1"></el-option>
                        <el-option label="三星" value="2"></el-option>
                        <el-option label="四星" value="3"></el-option>
                        <el-option label="五星" value="4"></el-option>
                    </el-select>
                </template>
            </el-table-column>

            <el-table-column prop="integral" key="integral" label="上次协议纯销积分" header-align="center" align="center" min-width="100"></el-table-column>
          </el-table-column>

          <el-table-column prop="messageCost" key="messageCost" label="信息管理费(元/年)" header-align="center" align="center" min-width="120">
                
          </el-table-column>
          <el-table-column prop="isPromotional" key="isPromotional" label="是否签订促销折让" header-align="center" align="center" min-width="120">
                <template slot-scope="scope">
                    <el-checkbox disabled true-label="1" false-label="0" v-model="scope.row.isPromotional"></el-checkbox>
                </template>
          </el-table-column>
          <el-table-column prop="startDate"  label="起始日期" header-align="center" align="center" min-width="180">
                
            </el-table-column>
          
          <el-table-column header-align="center" align="center" label="协议生效日期">
            
            <el-table-column prop="endDate" label="截止日期" header-align="center" align="center" min-width="180">
                
            </el-table-column>
          </el-table-column>
          <el-table-column header-align="center" align="center" label="购货渠道">
            <el-table-column prop="provincePlatformId" key="provincePlatformId" label="省级平台" header-align="center" align="center" min-width="120">
                <template slot-scope="scope">
                   <el-select disabled v-model="scope.row.provincePlatformId" placeholder="请选择">
                        <el-option
                          v-for="item in chargeChannelList"
                          :key="item.id"
                          :label="item.commerceName"
                          :value="item.id"
                        ></el-option>
                    </el-select>
                </template>
            </el-table-column>
            <el-table-column prop="upCommerceId" key="upCommerceId" label="上游商业" header-align="center" align="center" min-width="120">
                <template slot-scope="scope">
                   <el-select disabled v-model="scope.row.upCommerceId" placeholder="请选择">
                        <el-option
                          v-for="item in chargeChannelList"
                          :key="item.id"
                          :label="item.commerceName"
                          :value="item.id"
                        ></el-option>
                    </el-select>
                </template>
            </el-table-column>
          </el-table-column>
          <el-table-column prop="memo" key="memo" label="备注" header-align="center" align="center" min-width="120">
                
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
                province: '-',
                businessDivision: '-'
            },
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
      // 获取商业名单
      getNameList () {
          let self = this;
          self.loading = true;
          self.$api.xsgl
            .post({
                vueInstance: self,
                url: "/scm/scmXsDistributorClause/v1/firstList",
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
                if(res.rows.length > 0) {
                    self.nameListTitle.businessDivision = res.rows[0].businessDivision;
                    self.nameListTitle.province = res.rows[0].province;
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

