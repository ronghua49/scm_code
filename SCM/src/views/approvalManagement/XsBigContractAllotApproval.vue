<template>
	<div>
		<!--商业大合同审批-->
		<transition name="el-fade-in">
			<div v-show="!isShowPage">
				<el-container>
					<el-main>
						<el-row>
							<div class="header" style="margin: 0">
								<div class="headleft">
									<span class="el-icon-tickets"></span>
									<span class="sx">数据列表</span>
								</div>
							</div>

							<el-row>
								<el-table v-loading="loadingIndex" :data="listData" border stripe fit element-loading-text="加载中" style="width: 100%" max-height="500">
									<el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
									<el-table-column sortable :show-overflow-tooltip="showOverflowTooltip" v-for="column in columns" header-align="center" align="center" :prop="column.prop" :key="column.label" :label="column.label">
									</el-table-column>

									<el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
										<template slot-scope="scope">
											<el-button size="mini" type="primary" @click="approval(scope.row)">审批
											</el-button>

										</template>
									</el-table-column>
								</el-table>
							</el-row>

							<!--分页-->
							<div class="pagination">

								<el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageRequest.currentPage4" :page-sizes="[10, 20, 30, 40,50]" :page-size="pageRequest.pageSize" layout="total, sizes, prev, pager, next " :total="listData.total">
								</el-pagination>
							</div>

						</el-row>
					</el-main>
				</el-container>
			</div>
		</transition>

		<!--审批详情页面-->
		<transition name="el-fade-in">
			<div class="secondDiv" v-show="isShowPage" :key="refresh">
				<div class="bigcon-apply">
					<div class="searchHeader">
						<div class="searchheadleft">
							<el-button @click="fanHui" size="mini" class="el-icon-back" type="primary">返回</el-button>
						</div>
						<div class="searchheadcenter">商业大合同分配审批</div>
						<div class="searchheadright">
							<div v-if="!isDetails&&!isApproval">
								<el-button size="mini" type="primary" @click="applyCache">申请暂存</el-button>
								<el-button size="mini" type="primary" @click="applySubmit">发起申请</el-button>
							</div>
							<div v-if="isApproval">
								<approval-btn :taskId="taskId" @handleSuccess="approvalSuccess"></approval-btn>
							</div>
						</div>
					</div>

					<div class="header">
						<div class="headleft">
							<span class="el-icon-tickets"></span>
							<span class="sx">基础信息</span>
						</div>
					</div>
					<!--详情 && 审批 页面 ， 表单 不可选-->
					<el-form :disabled="isDetails||isApproval" size="small" label-width="140px" class="demo-ruleForm" :model="postData"  ref="postForm">
						<el-form-item label="供货商" prop="ghs">
							<el-select :disabled="editVar" style="width: 100%" placeholder="请选择" v-model="postData.ghs">
								<el-option v-for="(item,index) in supplierName" :key="'ownerName'+index" :label="item.name" :value="item.name"></el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="减免合同次数理由" prop="reason">
							<el-select style="width: 100%" v-model="postData.reason" placeholder="选择减免理由">
								<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="经销商" prop="jxs">
							<el-select :disabled="editVar" style="width: 100%" placeholder="请选择" v-model="postData.jxs">
								<el-option v-for="(item,ins) in jingxiaoR" :key="'dealer'+ins" :label="item.commerceName +' [' + item.agreementSummaryCode + ']'" :value="item.id"></el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="合同编码" prop="htbm">
							<el-input clearable v-model="postData.htbm" placeholder="请输入内容" :disabled="true"></el-input>
						</el-form-item>
						<el-form-item label="签订时间" prop="time">
							<div class="block">
								<el-date-picker style="width: 100%" v-model="postData.time" value-format="yyyy-MM-dd HH:mm:ss" type="date" placeholder="选择日期"></el-date-picker>
							</div>
						</el-form-item>
						<el-form-item label="计划发货时间" prop="jhfh">
							<div class="block">
								<el-date-picker style="width: 100%" v-model="postData.jhfh" value-format="yyyy-MM-dd HH:mm:ss" type="date" placeholder="选择日期"></el-date-picker>
							</div>
						</el-form-item>

						<el-form-item label="特求意见" prop="tqyj" style="width: 48%;">
							<el-input type="textarea" :rows="1" placeholder="请输入内容" v-model="postData.tqyj"></el-input>
						</el-form-item>
						<el-form-item label="购销方式" prop="marketingWay">
							<el-select style="width: 100%" filterable placeholder="请选择" v-model="postData.marketingWay">
								<el-option v-for="(item,ins) in gouxiao" :key="'marketingWay1'+ins" :label="item.name" :value="item.value"></el-option>
							</el-select>
						</el-form-item>
					</el-form>

					<div class="header">
						<div class="headleft">
							<span class="el-icon-tickets"></span>
							<span class="sx">商品信息</span>
						</div>
					</div>
					<el-table v-loading="loading" :data="tableDataRows" border stripe element-loading-text="加载中" style="width: 100%" max-height="500">
						<el-table-column v-for="column in columns1" header-align="center" align="center" :prop="column.prop" :key="column.label" :label="column.label" :formatter="column.formatter" :show-overflow-tooltip="showOverflowTooltip" min-width="120"></el-table-column>
						<el-table-column prop="参考信息" key="参考信息" label="参考信息" header-align="center" align="center" min-width="100">
							<template slot-scope="scope">
								<el-popover placement="top" title="参考信息" width="1000" align="center" trigger="click">
									<el-table :data="consult" border style="width: 100%">
										<el-table-column prop="commerceGauge" label="商品名称" width="100"></el-table-column>
										<el-table-column prop="commerceGauge" label="品规" width="100"></el-table-column>
										<el-table-column prop="commerceGauge" label="商业/渠道库存信息" width="100" align="center">
											<el-table-column prop="commerceGauge" label="安全库存设定" width="120" align="center"></el-table-column>
											<el-table-column prop="commerceGauge" label="上次周库存" width="100" align="center"></el-table-column>
											<el-table-column prop="commerceGauge" label="在途" width="100" align="center"></el-table-column>
										</el-table-column>
										<el-table-column prop="commerceGauge" label="本省区上次渠道总库存量K" width="100" align="center"></el-table-column>
										<el-table-column prop="commerceGauge" label="本省区近效期1年库存量" width="100" align="center"></el-table-column>
										<el-table-column prop="commerceGauge" label="本省区近3月月均纯销量A" width="100" align="center"></el-table-column>
										<el-table-column prop="commerceGauge" label="本省区上月总纯销量B" width="100" align="center"></el-table-column>
										<el-table-column prop="commerceGauge" label="K/A" width="100" align="center"></el-table-column>
										<el-table-column prop="commerceGauge" label="K/B" width="100" align="center"></el-table-column>
										<el-table-column prop="commerceGauge" label="近三个月销售出库量" width="100" align="center">
											<el-table-column prop="commerceGauge" label="第一个月" width="120" align="center"></el-table-column>
											<el-table-column prop="commerceGauge" label="第二个月" width="100" align="center"></el-table-column>
											<el-table-column prop="commerceGauge" label="第三个月" width="100" align="center"></el-table-column>
										</el-table-column>
										<el-table-column prop="commerceGauge" label="近效期1年库存量" width="100" align="center"></el-table-column>

									</el-table>

									<el-button size="mini" @click="cankao(scope.row)" :disabled="isDetails||isApproval" slot="reference">点击</el-button>
								</el-popover>
							</template>
						</el-table-column>
						<el-table-column prop="isSMS"   key="SMS" label="SMS" header-align="center" align="center" min-width="100">
							<template slot-scope="scope">
								<el-checkbox true-label="1" false-label="0" :disabled="isDetails||isApproval" v-model="scope.row.isSMS"></el-checkbox>
							</template>
						</el-table-column>
						<el-table-column prop="surplusSUm" key="大合同剩余数量" label="大合同剩余数量" header-align="center" align="center" min-width="100">
						</el-table-column>
						<el-table-column label="申请" align="center">
							<el-table-column prop="achange" label="件装量" width="120" align="center">
								<template slot-scope="scope">
									<el-input placeholder="输入数量" :disabled="isDetails||isApproval" v-model="scope.row.aChange"></el-input>
								</template>
							</el-table-column>
							<el-table-column prop="theNumber" label="数量" width="120" align="center">
								<template slot-scope="scope">
									<el-input placeholder="输入数量" :disabled="isDetails||isApproval" v-model="scope.row.theNumber" @input="numberChanng(scope.row,scope.$index)"></el-input>
								</template>
							</el-table-column>
							<el-table-column prop="packageNum" label="件数" width="120" align="center"></el-table-column>
							<el-table-column prop="unitPrice" label="含税单价" width="120" align="center"></el-table-column>
							<el-table-column prop="sumPrice" label="货款金额" width="120" align="center"></el-table-column>
						</el-table-column>
					</el-table>

					<!--分页-->
					<!-- <div class="pagination">
			<el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageRequest1.page"
			 :page-sizes="[5, 10, 20, 50]" :page-size="pageRequest1.pageSize" layout="total, sizes, prev, pager, next " :total="tableData1.total"></el-pagination>
          </div>-->
					<!-- 各品规发货滚动表 -->
					<div class="header">
						<div class="headleft">
							<span class="el-icon-tickets"></span>
							<span class="sx">各品规发货滚动表</span>
						</div>
					</div>

					<el-table v-loading="loading2" :data="tableData3.rows" border element-loading-text="加载中" style="width: 99%" max-height="250">
						<el-table-column v-for="column in columns2" header-align="center" align="center" :prop="column.prop" :key="column.label" :label="column.label" width="120"></el-table-column>
					</el-table>

					<div class="pagination">
						<el-pagination background @size-change="handleSizeChange2" @current-change="handleCurrentChange2" :current-page="pageRequest2.page" :page-sizes="[5,10, 20, 50]" :page-size="pageRequest2.pageSize" layout="total, sizes, prev, pager, next " :total="tableData3.total"></el-pagination>
					</div>


                    <el-row>
                        <el-row>
                            <div class="header" style="margin: 0">
                                <div class="headleft">
                                    <span class="el-icon-tickets"></span>
                                    <span class="sx">流程图</span>
                                </div>
                            </div>
                            <el-row class="liu-cheng-tu">
                                <approval-flow :flowData="flowData"></approval-flow>
                            </el-row>
                        </el-row>
                        <el-row>
                            <div class="header" style="margin: 0">
                                <div class="headleft">
                                    <span class="el-icon-tickets"></span>
                                    <span class="sx">审批历史</span>
                                </div>
                            </div>
                            <el-row>
                                <approval-history :instId="instId"></approval-history>
                            </el-row>
                        </el-row>
                    </el-row>


				</div>





			</div>
		</transition>

	</div>
</template>

<script>
	import approvalBtn from "@/components/approval/ApprovalBtn"
	import approvalFlow from "@/components/approval/ApprovalFlow"
	import approvalHistory from "@/components/approval/ApprovalHistory"
	import wareHouse from "@/components/zsj/Warehouse"
	import shipAddress from "@/components/zsj/ShipAddress"
	import storage from "@/utils/storage";
	import xsBigContractApply from "../xsglManagement/XsBigContractApply.vue"
	import { format } from "@/utils/datetime";
	export default {
		name: "XsBigContractAllotApproval",
		components: {
			'approval-btn': approvalBtn,
			'approval-flow': approvalFlow,
			'approval-history': approvalHistory,
			'ware-house': wareHouse,
			'ship-address': shipAddress,
			'big-contract-apply': xsBigContractApply
		},
		mounted() {
			this.ownerId = storage.get("ownerId");
			this.getTableList();
		},
		data() {
			return {
				showOverflowTooltip: true,
				ownerId: '',
				taskId: '',
				isShowPage: false,
				pageState: '',
				isApproval: true,
				refresh: '',
				row: '',
				flowData: {},
				instId: "",
				postData: {
					marketingWay: "",
					jxs: "", //
					tqyj: "", //
					time: "", //
					htbm: "", //
					jhfh: "", //
					reason: "", //减免合同次数理由
					ghs: "" //
				},
				tableDataRows: [],
				pageRequest: {
					page: 1,
					pageSize: 10,
					showTotal: true
				},
				componentParams: {
					flag: '', // 是编辑还是查看详情还是申请 与id是配对的
					id: '',
					title: '商业大合同审批',
					detailsdisabled: false
				},
				loadingIndex: false,
				//数据主表
				listData: [],
				columns: [{
						prop: "subject",
						label: "任务标题",
						minWidth: 150
					},
					{
						prop: "name",
						label: "任务名称",
						minWidth: 150
					},
					{
						prop: "ownerName",
						label: "所属人",
						minWidth: 100
					},
					{
						prop: "assigneeName",
						label: "执行人",
						minWidth: 100
					},
					{
						prop: "status",
						label: "类型",
						minWidth: 110,
						formatter: (row, column, cellValue, index) => {
							return cellValue == 'NORMAL' ? '普通' : cellValue
						}
					},
					{
						prop: "createTime",
						label: "创建时间",
						minWidth: 150
					},
					{
						prop: "",
						label: "到期时间",
						minWidth: 100
					},
				],
				columns1: [{
						prop: "commonName",
						label: "药品名称"
					},
					{
						prop: "medicineType",
						label: "药品类型"
					},
					{
						prop: "productName",
						label: "商品名"
					},
					{
						prop: "productState",
						label: "规格"
					},
					{
						prop: "unit",
						label: "单位"
					},
					{
						prop: "suppyType",
						label: "药品供应状态",
						formatter: function(row,b,value){
							if ( value == "0" ) {
								return '正常供应';
							} else{
								return "禁止销售";
							}
						}
					},
					{
						prop: "suggestApplyNum",
						label: "建议本次申请量"
					},
					{
						prop: "lastShipmentDate",
						label: "上次发货时间",
						formatter: function(row, column, value) {
							if(!value) {
								return "无"
							}
							return format(value);
						}
					},
					{
						prop: "smscurrentMonthSales",
						label: "省区本月SMS剩余任务量"
					},
				],
				tableData: {},
				pageRequest: {
					page: 1,
					pageSize: 10,
					showTotal: true
				},
				loading: false,
				gouxiao: [{
					value: '0',
					name:"预付款",
				},{
					value: '1',
					name: "赊销"
				}],
				jingxiaoR: [],
				editVar: true,
				options: [{
						value: "无需减免",
						label: "无需减免"
					},
					{
						value: "SMS发货",
						label: "SMS发货"
					},
					{
						value: "新品发货",
						label: "新品发货"
					},
					{
						value: "短缺品新来品",
						label: "短缺品新来品"
					},
					{
						value: "预估不足",
						label: "预估不足"
					},
					{
						value: "BU特批发货",
						label: "BU特批发货"
					},
					{
						value: "备货不足",
						label: "备货不足"
					}
				],
				supplierName: [],
				tableData3: {
					rows: [],
					total: 1
				},
				loading2: false,
				isDetails: true,
				pageRequest2:{
					page: 1,
				},
				columns2: [],
				consult: [],
				tabarr: [],
			}
		},
		watch: {
			refresh(value) {
				this.getTableList();
			}
		},
		methods: {
			//审批成功
			approvalSuccess() {
                let _this = this;
                setTimeout(function () {
                    _this.fanHui();
                    _this.getTableList();
                })
			},
			cankao(row) {
		      console.log(row);
		      this.consult.length = 0;
		      this.consult.push(row);
		    },
			fanHui() {
				this.isShowPage = false;
				setTimeout(()=>{
					this.isShowPages = false;
				},300)
				this.$refs.postForm.resetFields();
				this.tableData.rows = [];
				this.zancun1 = "";
			},
			handleSizeChange2() {},
			handleCurrentChange2() {},
			handleSizeChange(val) {
				this.pageRequest.pageSize = val;
				this.getTableList();
			},
			handleCurrentChange(val) {
				this.pageRequest.page = val;
				this.getTableList();
			},
			showParentComp() {
				this.refresh += new Date();
				this.isShowPage = false;
			},
			//主页面表格获取
			getTableList() {
				console.log(8799079079080);
			},
			approval(row) {
				this.isShowPage = true;
				//this.pageState = 'assignApproval,' + row.procInstId;
				this.assignApplylistDetails(row.procInstId);
				this.instId = row.procInstId;
				this.taskId = row.taskId;
				this.row = row;
				this.flowData = {
					taskId: row.taskId
				}
			},
			assignApplylistDetails(id){
				this.$api.base
			        .getScm({
			        	url: '/scm/scmXsBigContractAllot/v1/getByApplyId/'+ id
			        }).then((res) => {
			        	if ( res.state == false ) {
			        		this.$message({
			        			message: '系统错误',
			        			type: 'error'
			        		});
			        		return;
			        	}
			        	this.$nextTick().then(()=>{
				          this.postData.id = res.id;
				          this.tableDataRows = {};
				          this.tableDataRows = res.scmXsBigContractAllotProductList;
				          for(var key in res){
				            if ( key == "ownerName" ) {
								this.postData.ghs = res[key]
							}
							if ( key == "reduceContractTime" ) {
								this.postData.reason = res[key]
							}
							if ( key == "commerceFirstId" ) {
								 this.postData.jxs = res[key];
							}
							if ( key == "allocationCode" ) {
								this.postData.htbm = res[key]
							}
							if ( key == "dateOfSigning" ) {
								this.postData.time = res[key]
							}
							if ( key == "sendProductDate" ) {
								this.postData.jhfh = res[key]
							}
							if ( key == "specialOpinion" ) {
								this.postData.tqyj = res[key]
							}
							if ( key == "marketingWay" ) {
								this.postData.marketingWay = res[key]
							}
				          }
			            })
			        })
			},
			closeapplypage() {
				this.isShowPage = false;
				this.pageState = ''
			},
			//主页面表格获取
			getTableList() {
				this.$api.approval.post({
					url: '/runtime/task/v1/list',
					data: {
						"pageBean": {
							"page": this.pageRequest.page,
							"pageSize": this.pageRequest.pageSize,
							"showTotal": true
						},
						"params": {},
						"querys": [{
								hasInitValue: false,
								operation: "EQUAL",
								property: "assigneeId", //登录人 id
								relation: "AND",
								value: storage.get('userId')
							},
							{
								hasInitValue: false,
								operation: "EQUAL",
								property: "procDefKey",
								relation: "AND",
								value: "dhtfpsp"
							}
						],
						"sorter": [{
							"direction": "DESC",
							"property": "entryDate"
						}]
					}
				}).then(res => {
					if(res.hasOwnProperty('message')) {
						this.$message.error('系统错误');
						return;
					}
					this.listData = res.rows;
					this.listData.total = res.total;
				})
			},
		}
	}
</script>

<style scoped lang="scss">
	.el-header {
		padding: 0!important;
		height: auto!important;
	}
	
	.el-main {
		padding: 0!important;
	}
	
	.searchHeader {
		position: relative;
		width: 100%;
		font-size: 28px;
		font-weight: bold;
		background: #f3f3f3;
		margin-bottom: 7px;
	}
	
	.searchheadleft {
		position: absolute;
		left: 10px;
	}
	
	.searchheadcenter {
		width: 100%;
		text-align: center;
		line-height: 1.5em;
	}
	
	.searchheadright {
		position: absolute;
		top: 0;
		right: 10px;
	}
	
	.head-item {
		border: 1px solid #f2f2f2;
		text-align: left;
	}
	
	.header {
		width: 100%;
		height: 38px;
		font-size: 16px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		background: #f3f3f3;
		margin-bottom: 22px;
	}
	
	.headleft {
		margin-left: 10px;
	}
	
	.el-form {
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		.el-form-item {
			width: 50%;
		}
	}
    .liu-cheng-tu{
        padding: 24px 0;
    }
	.pagination {
		margin-top: 20px;
		display: flex;
		justify-content: flex-end;
	}
</style>
