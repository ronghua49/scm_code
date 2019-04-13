<template>
<div>
    <header class="header">
        <h1>商业协议合作名单审批</h1>
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
                的经销商，共
                <span style="color: #409EFF;">【{{pageResult.rows.length}}】</span>家
            </div>
            <div class="header-right"></div>
        </div>
        <!--表格栏-->
        <!-- <el-table class="test" :data="pageResult.rows" highlight-current-row v-loading="loading" element-loading-text="加载中"
          border stripe fit
          @selection-change="getOuterSelectedList"
          max-height="500" size="mini" align="left" style="width:100%;">
          <el-table-column show-overflow-tooltip="true" prop="businessDivision" key="businessDivision" label="商务大区" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="commerceCode" key="commerceCode" label="商业编码" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="commerceName" key="commerceName" label="商业名称" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="conpanyPersion" key="conpanyPersion" label="商业级别" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="enterDate" key="enterDate" label="认可状态" header-align="center" align="center"></el-table-column>
          <el-table-column :formatter="formatAcceptState" show-overflow-tooltip="true" prop="acceptState" key="acceptState" label="选择分类" header-align="center" align="center">
                <template slot-scope="scope">
                    <el-select :disabled="disabledinput" v-model="scope.row.acceptState" placeholder="请选择">
                        <el-option label="认可" value="0"></el-option>
                        <el-option label="考察中" value="1"></el-option>
                        <el-option label="终止" value="2"></el-option>
                    </el-select>
                </template>
          </el-table-column>

          <el-table-column align="center" label="-至-">
                <el-table-column :formatter="formatStartDate" show-overflow-tooltip="true" prop="startDate" key="startDate" label="发货量" header-align="center" align="center"></el-table-column>
                <el-table-column :formatter="formatEndDate" show-overflow-tooltip="true" prop="endDate" key="endDate" label="调拨量" header-align="center" align="center"></el-table-column>
                <el-table-column :formatter="formatEndDate" show-overflow-tooltip="true" prop="endDate" key="endDate" label="纯销量" header-align="center" align="center"></el-table-column>
          </el-table-column>
          <el-table-column align="center" label="签约状态">
                <el-table-column :formatter="formatStartDate" show-overflow-tooltip="true" prop="startDate" key="startDate" label="上年度" header-align="center" align="center"></el-table-column>
                <el-table-column :formatter="formatEndDate" show-overflow-tooltip="true" prop="endDate" key="endDate" label="本年度" header-align="center" align="center"></el-table-column>
          </el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="memo" key="memo" label="申请备注" header-align="center" align="center"></el-table-column>
        </el-table> -->
        <el-table 
            v-loading="loading" 
            element-loading-text="加载中" 
            :data="pageResult.rows" size="mini" border style="width: 100%">
			<el-table-column type="index" label="序号" width="100" align="center"></el-table-column>
			<el-table-column 
                v-for="item in column1" 
                :key="item.prop" 
                :prop="item.prop" 
                :label="item.label" 
                :formatter="item.formatter" 
                :minwidth="item.minwidth" 
                :align="item.align"></el-table-column>
			<el-table-column  :label="ltime" width="300" align="center">
				<el-table-column prop="shipments" label="发货量" width="100" align="center"></el-table-column>
				<el-table-column  prop="allocating" label="调拨量" width="100" align="center"></el-table-column>
				<el-table-column prop="sales" label="纯销量" width="100" align="center"></el-table-column>
			</el-table-column>
			<el-table-column  label="签约状态" width="300" align="center">
				<el-table-column  prop="lastYear" label="上年度"  width="100" align="center">
					<template slot-scope="scope">
						<span v-show="scope.row.lastYear==0" class='el-icon-close' style="font-size: 20px;"></span>
						<span v-show="scope.row.lastYear==1" class='el-icon-check' style="font-size: 20px;"></span>
				    </template>
				</el-table-column>
				<el-table-column prop="isSign" label="本年度" width="100" align="center">
					<template slot-scope="scope">
						<el-checkbox :checked="pageResult.rows[scope.$index].isSign == 1" disabled></el-checkbox>
				    </template>
				</el-table-column>
			</el-table-column>
			 <el-table-column
				fixed="right"
				label="选择分类"
				align="center"
				width="180">
				<template slot-scope="scope">
				    <el-select v-model="pageResult.rows[scope.$index].type" clearable placeholder="请选择" @change="getswdqid" disabled style="width: 100%;">
						<el-option v-for="item in floption" :key="item.index" :label="item.name" :value="item.name"></el-option>
					</el-select>
				</template>
			</el-table-column>
			<el-table-column
				fixed="right"
				label="申请备注"
				align="center"
				width="180">
				<template slot-scope="scope">
					<el-input v-model="pageResult.rows[scope.$index].memo" disabled></el-input>
				</template>
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
            column1: [
                {     
                    prop: "businessDivision",
					label: "商务大区",
					minwidth: "100",
					align: "center"
				},
				{
					prop: "commerceCode",
					label: "商业编码",
					minwidth: "100",
					align: "center"
				},
				{
					prop: "commerceName",
					label: "商业名称",
					minwidth: "200",
					align: "center"
				},
				{
					prop: "commerceLevel",
					label: "商业级别",
					minwidth: "100",
					align: "center"
				},
				{
					prop: "acceptState",
					label: "认可状态",
					minwidth: "100",
					align: "center"
				},
			],
            nameListTitle: {
                businessDivision: ''
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
            },
            ltime: '',
            
        };
    },
    watch: {
        passparam: 'getPassParam'
    },
    mounted() {
      this.ownerId = storage.get("ownerId");
      this.sytimer();
    },
    methods: {
      sytimer(){
		let myDate = new Date();
		let newTime= myDate.getFullYear();
		this.ltime= (Number(newTime)-2) +".11至"+(Number(newTime)-1) +".7";
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
      // 获取商业名单
      getNameList () {
          let self = this;
		  self.loading = true;
		  self.$api.xsgl.post({
            vueInstance: self,
			url: "/scm/scmXsAgreementList/v1/masterList",
			data: {
				pageBean: self.pageRequest,
				params: {},
				querys: [{
					hasInitValue: false,
					operation: "EQUAL",
					property: "ownerId", //登录人 id
					relation: "AND",
					value: self.ownerId
				},
				{
					hasInitValue: false,
					operation: "EQUAL",
					property: "approvalId",
					relation: "AND",
					value: self.passparamObj.instId
				}],
			    sorter: []
			}
		  }, function(res) {
            console.log('99999999999999999999999999999999999999999');
            if(res.rows.length > 0) {
                self.nameListTitle.businessDivision = res.rows[0].businessDivision;
                self.getNameListDetails(res.rows[0].masterId);
            }
            
          });
      },
      // 获取详情  /scm/scmXsAgreementListMaster/v1/get/
      getNameListDetails (masterId) {
          let self = this;
          self.$api.xsgl
            .get({
                vueInstance: self,
                url: "/scm/scmXsAgreementListMaster/v1/get/" + masterId,
                data: {}
            },function(res) {
                self.loading = false;
                self.pageResult.rows = res.scmXsAgreementListList;
                
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

