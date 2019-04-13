<template>
	<div>
		<el-container>
			<div class="searchHeader">
				<div class="headleft">
					<router-link to="ContractProductApple">
						<el-button size="mini" class="el-icon-back" type="primary">返回</el-button>
					</router-link>
				</div>
				<div class="headcenter">{{title}}</div>
				<div class="headright">
					<approval-btn :taskId="taskId" @handleSuccess="approvalSuccess"></approval-btn>
				</div>
			</div>
			<el-header>
				<div class="header">
					<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">基础信息</span>
					</div>
					<div class="headright">
					</div>
				</div>
			</el-header>
			<el-main>
				<el-form :model="wrapForm" :rules="wraprules" ref="wrapForm1" :label-position="labelPosition" size="small" label-width="210px" class="demo-ruleForm">
					<el-form-item prop="jfdwname" label="供应商名称">
						<el-input v-model="wrapForm.jfdwname" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item prop="addressName" label="合同编号">
						<el-input v-model="wrapForm.addressName" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item prop="khhname" label="金额">
						<el-input v-model="wrapForm.khhname" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item prop="zhname" label="币种">
						<el-input v-model="wrapForm.zhname" :disabled="btnType"></el-input>
					</el-form-item>
					<el-form-item prop="phone" label="申请日期">
						<el-date-picker v-model="wrapForm.phone" type="date" clearable :disabled="true" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" placeholder="选择日期">
						</el-date-picker>
					</el-form-item>
				</el-form>
			</el-main>
			<div class="header">
				<div class="headleft">
					<span class="el-icon-tickets"></span>
					<span class="sx">数据列表</span>
				</div>
				<div class="headright">

				</div>
			</div>
			<!--table-->
			<el-table v-loading="loading" element-loading-text="加载中" :data="tableData" size="mini" border style="width: 100%">
				<el-table-column v-for="item in column1" :key="item.prop" :prop="item.prop" :label="item.label" :formatter="item.formatter" :align="item.align">
				</el-table-column>
			</el-table>
			<div class="pagination">
				<el-pagination @current-change="refreshPageRequest1" @size-change="refreshPageSizeRequest1" background :current-page="pageRequest1.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest1.pageSize" layout="sizes, total, prev, pager, next" :total="total1">
				</el-pagination>
			</div>

			<el-row class="searchHeader lishi" style="margin-top:10px;">
				<el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>流程图</el-col>
			</el-row>
			<el-row class="searchBox">
				<approval-flow :flowData="flowData"></approval-flow>
			</el-row>

			<el-row class="searchHeader lct" style="margin-top:10px;">
				<el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>审批历史</el-col>
			</el-row>
			<el-row class="searchBox">
				<approval-history :instId="instId"></approval-history>
			</el-row>
		</el-container>
	</div>
</template>

<script>
	import storage from '@/utils/storage'
	import approvalBtn from "@/components/approval/ApprovalBtn"
	import approvalFlow from "@/components/approval/ApprovalFlow"
	import approvalHistory from "@/components/approval/ApprovalHistory"
	export default {
		name: "goods",
		components: {
			'approval-btn': approvalBtn,
			'approval-flow': approvalFlow,
			'approval-history': approvalHistory,
		},
		data() {
			var checkAge = (rule, value, callback) => {
				if(!value) {
					return callback(new Error('请输入单价'));
				}
				if(value <= 0) {
					return callback(new Error('输入单价不能小于1'));
				}
				if(value.length > 10) {
					return callback(new Error('输入单价长度不能大于10位'));
				}
				if(!Number(value)) {
					callback(new Error('单价只能是数字值'));
				}
			};
			var checknum = (rule, value, callback) => {
				if(!value) {
					return callback(new Error('请输入数量'));
				}
				if(value <= 0) {
					return callback(new Error('输入数量不能小于1'));
				}
				if(value.length > 10) {
					return callback(new Error('输入数量长度不能大于10位'));
				}
				if(!Number(value)) {
					callback(new Error('数量只能是数字值'));
				}
			};
			var cpmsg = (rule, value, callback) => {
				if(!value) {
					return callback(new Error('请选择采购商品'));
				}
			};
			return {
				column1: [{
						prop: "productName",
						label: "产品名称",
						minwidth: "200",
						align: "center"
					},
					{
						prop: "productCode",
						label: "产品编码",
						minwidth: "150",
						align: "center"
					},
					{
						prop: "productCname",
						label: "产品通用名",
						minwidth: "200",
						align: "center"
					},
					{
						prop: "productGauge",
						label: "规格",
						minwidth: "150",
						align: "center"
					},
					{
						prop: "medicineType",
						label: "药品类型",
						minwidth: "100",
						align: "center"
					},
					{
						prop: "unit",
						label: "单位",
						minwidth: "100",
						align: "center"
					},
					{
						prop: "sumPrice",
						label: "金额",
						minwidth: "100",
						align: "center"
					},
					{
						prop: "currency",
						label: "币种",
						minwidth: "100",
						align: "center"
					},
				],
				btnType: false,
				title: "采购合同审批详情",
				restaurants: [],
				taskId: '',
				instId: '',
				flowData: '',
				labelPosition: "right",
				loading2: true,
				ownerId: "",
				total1: 0,
				dialogTableVisible: false,
				gongid: "",
				btnShow: false,
				options: [{
						name: "待发起",
						index: 1
					},
					{
						name: "审核中",
						index: 2
					},
					{
						name: "未通过",
						index: 3
					},
					{
						name: "已通过",
						index: 4
					},
				],
				gongoption: [],
				tableData: [],
				checked: false,
				loading: false,
				cparray: [],
				supplierId: "",
				search: [],
				pageRequest: {
					page: 1,
					pageSize: 10,
					showTotal: true,
				},
				pageRequest1: {
					page: 1,
					pageSize: 10,
					showTotal: true,
				},
				wrapForm: {
					jfdwname: '',
					addressName: '',
					khhname: '',
					zhname: '',
					shname: '',
					phone: '',
					yzbmname: '',
				},
				wraprules: {
					jfdwname: [{
						required: true
					}, ],
					addressName: [{
						required: true
					}, ],
					khhname: [{
						required: true
					}, ],
				}
			}
		},
		methods: {
			refreshPageRequest1: function(page = 1) {
				//分页刷新
				this.pageRequest1.page = page;
			},
			refreshPageSizeRequest1: function(pageSize) {
				//分页刷新
				this.tableData.pageSize = pageSize;
			},
			approvalSuccess() {
				this.$router.go(-1);
			}
		},
		mounted() {
			this.ownerId = storage.get('ownerId');
			this.taskId = this.$route.query.id;
			this.instId = this.$route.query.htid;
			this.flowData = {taskId: this.$route.query.taskid};
			//采购合同数据
			if(this.$route.query.htid) {
				this.title = "采购合同审批详情"
				this.btnShow = true
				this.$api.zsj.changeState({
					url: "/scm/scmCgProcurementContract/v1/getContractFirstByApprovalId/" + this.$route.query.htid,
				}).then(res => {
					if(res.state) {
						res = res.value;
						this.tableData = res.scmCgContractProductList;
						this.btnType = true
						if(res.supplier) {
							this.wrapForm.jfdwname = res.supplier;
						} else {
							this.wrapForm.jfdwname = "无"
						}
						if(res.contractCode) {
							this.wrapForm.addressName = res.contractCode
						} else {
							this.wrapForm.addressName = "无"
						}
						if(res.sumPrice) {
							this.wrapForm.khhname = res.sumPrice
						} else {
							this.wrapForm.khhname = "无"
						}
						if(res.currency) {
							this.wrapForm.zhname = res.currency
						} else {
							this.wrapForm.zhname = "无"
						}
						if(res.filingDate) {
							this.wrapForm.phone = res.filingDate
						} else {
							this.wrapForm.phone = '无'
						}

					} else {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
					}
				});
			}
		},
	}
</script>
<style scoped="scoped" lang="scss">
	.header {
		width: 100%;
		height: 50px;
		font-size: 14px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		background: #F3F3F3;
	}
	
	.el-header {
		padding: 0 !important;
	}
	
	.tab-title {
		width: 100%;
		height: 50px;
		font-size: 14px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		background: #F3F3F3;
	}
	
	.headleft {
		margin-left: 10px;
	}
	
	.headright {
		margin-right: 10px;
	}
	
	.headcenter {
		font-size: 24px;
	}
	
	.sx {
		margin: auto 5px;
	}
	
	.heng {
		display: flex;
		width: 100%;
		font-size: 14px;
		align-items: center;
	}
	
	.box span {
		display: inline-block;
		width: 120px;
		font-size: 14px;
	}
	
	.content {
		display: flex;
		justify-content: space-between;
	}
	
	.content1 {
		display: flex;
		justify-content: space-between;
		margin-top: 15px;
	}
	
	.content3 {
		display: flex;
		margin-top: 15px;
	}
	
	.content1 span {
		font-size: 14px;
	}
	
	.el-main {
		border-bottom: 1px solid #CCCCCC;
		padding-bottom: 28px;
	}
	
	.title {
		display: flex;
		justify-content: flex-start;
		margin-top: 20px;
		margin-bottom: 20px;
	}
	
	.pagination {
		margin-top: 20px;
		display: flex;
		justify-content: flex-end;
	}
	
	.el-select {
		width: 100%;
	}
	
	.box {
		display: flex;
		align-items: center;
	}
	
	.color {
		color: red;
		font-size: 20px;
		margin-left: 3px;
	}
	
	.box1 {
		display: flex;
		align-items: center;
		margin-left: 8.9%;
	}
	
	.box2 {
		display: flex;
		align-items: center;
		margin-left: 8.3%;
	}
	
	.box1 span {
		display: inline-block;
		width: 120px;
		font-size: 14px;
	}
	
	.head {
		background: none !important;
	}
	
	.tanFoot {
		margin-top: 15px;
		display: flex;
	}
	
	.savebox {
		margin-left: 20px;
	}
	
	.el-form {
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		.el-form-item {
			width: 33%;
		}
	}
	
	.el-table .cell {
		white-space: nowrap !important;
	}
	
	.el-main {
		padding: 0 !important;
	}
	
	header {
		height: 72px !important;
	}
	
	.searchHeader {
		width: 100%;
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: 28px;
		font-weight: bold;
		padding-bottom: 7px;
		background: #f3f3f3;
		margin-bottom: 8px;
	}
	.lct,.lishi{
		font-size: 14px;
		height: 50px;
		line-height: 50px;
		justify-content: flex-start!important;
		font-weight: normal;
		i{
			margin-left: 8px;
			margin-right: 8px;
		}
	} 
</style>