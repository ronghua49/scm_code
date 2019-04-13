<template>
	<div>
		<header class="header">
			<h1>商业认可状态申请</h1>
			<div v-if="applySteps.step === '0' && componentParams.flag !== 'details'" class="header-btn-group">
				<el-button @click="saveApply" :loading="saveApplyLoad" size="mini" type="primary">申请暂存</el-button>
				<el-button @click="sendApply" :loading="sendApplyLoad" size="mini" type="primary">发起申请</el-button>
			</div>
			<div class="header-back">
				<el-button @click="showParentComp" size="mini" type="primary">返回</el-button>
			</div>
		</header>
		<transition name="el-fade-in">
			<div v-if="applySteps.step === '0'">
				<div v-if="componentParams.flag !== 'details'" style="margin-top:20px;" class="query-section">
					<div class="header">
						<div class="header-left">
							<i class="el-icon-tickets"></i>基础信息
						</div>
						<div class="header-right"></div>
					</div>
					<el-main>
						<el-form label-position="right" size="small" label-width="120px" class="demo-ruleForm">
							<el-form-item label="年度">
								<el-date-picker @change="setStartDateAndEndDate" :default-value="searchForm.year" v-model="searchForm.year" value-format="yyyy" type="year" placeholder="选择年"></el-date-picker>
							</el-form-item>
							<el-form-item label="季度">
								<el-select @change="setStartDateAndEndDate" clearable v-model="searchForm.quarter" placeholder="请选择">
									<el-option label="1季度" value="1"></el-option>
									<el-option label="2季度" value="2"></el-option>
									<el-option label="3季度" value="3"></el-option>
									<el-option label="4季度" value="4"></el-option>
								</el-select>
							</el-form-item>
						</el-form>
					</el-main>
				</div>
				<!--表格数据-->
				<div class="table-section">
					<div class="header">
						<div class="header-left">
							<i class="el-icon-tickets"></i>数据列表
						</div>

						<div v-if="componentParams.flag !== 'details'" class="header-right">
							<el-button @click="addNewCommerce" size="mini" type="primary"><i class="el-icon-circle-plus-outline">新增商业</i></el-button>
							<el-button @click="deleteSelectedRow" size="mini" type="danger"><i class="el-icon-delete">删除</i></el-button>
						</div>
						<div v-if="componentParams.flag === 'details'" class="header-right"></div>
					</div>
					<!--表格栏-->
					<el-table class="test" :data="pageResult.currentPageList" highlight-current-row v-loading="loading" element-loading-text="加载中" border stripe fit @selection-change="getOuterSelectedList" max-height="500" size="mini" align="left" style="width:100%;">
						<el-table-column type="selection" width="55"></el-table-column>
						<el-table-column show-overflow-tooltip="true" prop="businessDivision" key="businessDivision" label="商务分区" header-align="center" align="center"></el-table-column>
						<el-table-column show-overflow-tooltip="true" prop="province" key="province" label="省区" header-align="center" align="center"></el-table-column>
						<el-table-column show-overflow-tooltip="true" prop="commerceCode" key="commerceCode" label="商业编码" header-align="center" align="center"></el-table-column>
						<el-table-column show-overflow-tooltip="true" prop="commerceName" key="commerceName" label="商业名称" header-align="center" align="center"></el-table-column>
						<el-table-column show-overflow-tooltip="true" prop="conpanyPersion" key="conpanyPersion" label="责任人" header-align="center" align="center"></el-table-column>
						<el-table-column show-overflow-tooltip="true" prop="enterDate" key="enterDate" label="开户日期" header-align="center" align="center"></el-table-column>
						<el-table-column show-overflow-tooltip="true" prop="acceptState" key="acceptState" label="认可状态" header-align="center" align="center">
							<template slot-scope="scope">
								<el-select :disabled="disabledinput" v-model="scope.row.acceptState" placeholder="请选择">
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
						<el-table-column show-overflow-tooltip="true" prop="memo" key="memo" label="备注" header-align="center" align="center">
							<template slot-scope="scope">
								<el-input v-model="scope.row.memo"></el-input>
							</template>
						</el-table-column>
						<!-- <el-table-column label="操作" width="150" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button icon="el-icon-delete" size="small" type="danger"  @click="deleteRow(scope.$index, scope.row)" >删除</el-button>
            </template>
          </el-table-column> -->
					</el-table>

					<!-- <div class="pagination">
			<el-pagination 
                @current-change="refreshPageRequest" 
                @size-change="refreshPageSizeRequest"
				background
				:current-page="pageRequest.page" 
                :page-sizes="[10000]" 
                :page-size="pageRequest.pageSize" 
                layout="sizes, total, prev, pager, next"
				:total="pageResult.total">
			</el-pagination> 
        </div> -->
				</div>
			</div>
		</transition>

		<!-- 新增商业 dialog -->
		<el-dialog custom-class="business-accept-status-apply-addnew-dialog" class="addnew-dialog" title="新增认可商业名单" width="70%" :visible.sync="addNewDialogVisible">
			<header class="header">
				<div class="header-left"><i class="el-icon-search"></i>筛选查询</div>
				<div class="header-right"></div>
				<h1>&nbsp;</h1>
				<div class="header-btn-group">
					<el-button @click="getDialogListData" size="mini" type="primary">查询</el-button>
				</div>
			</header>
			<el-form label-position="right" size="small" label-width="120px" class="demo-ruleForm">
				<el-form-item label="商务分区">
					<el-select clearable v-model="addnewDialog.searchForm.businessDivisionId" placeholder="请选择商务分区" @change="getPrivence">
						<el-option v-for="item in addnewDialog.divisionList" :key="item.id" :label="item.businessDivision" :value="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="省区">
					<el-select clearable v-model="addnewDialog.searchForm.provinceId" placeholder="请选择省区">
						<el-option v-for="item in addnewDialog.provinceList" :key="item.id" :label="item.province" :value="item.id"></el-option>
					</el-select>
				</el-form-item>
			</el-form>
			<header class="header">
				<div class="header-left"><i class="el-icon-tickets"></i>数据列表</div>
				<div class="header-right"></div>
				<h1>&nbsp;</h1>
			</header>
			<el-table @selection-change="getDialogSelectedData" :loading="addnewDialog.loading" element-loading-text="加载中" :data="addnewDialog.pageResult.rows" size="mini" border>
				<el-table-column type="selection" width="55">
				</el-table-column>
				<el-table-column show-overflow-tooltip="ture" v-for="item in addnewDialog.column" :key="item.prop" :prop="item.prop" :label="item.label" :width="item.minwidth" :formatter="item.formatter" :align="item.align">
				</el-table-column>
			</el-table>
			<div class="pagination">
				<el-pagination @current-change="addnewDialogRefreshPageRequest" @size-change="addnewDialogRefreshPageSizeRequest" background :current-page="addnewDialog.pageRequest.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="addnewDialog.pageRequest.pageSize" layout="sizes, total, prev, pager, next" :total="addnewDialog.pageResult.total">
				</el-pagination>
			</div>
			<el-row>
				<el-button @click="dialogCancel">取消</el-button>
				<el-button @click="dialogConfirm" type="primary">确定</el-button>
			</el-row>
			<el-dialog width="30%" title="提示" :visible.sync="addnewInnerVisible" append-to-body>
				<p>您选择了重复的商业，已为您自动过滤！</p>
				<div slot="footer" class="dialog-footer">
					<el-button type="primary" @click="addnewInnerVisible = false">确定</el-button>
				</div>
			</el-dialog>
		</el-dialog>
		<!--发起申请成功-->
		<transition name="el-fade-in">
			<div v-if="applySteps.step === '1'" class="apply-success-section">
				<div class="success">
					<span class="el-icon-check"></span> 您已完成
					<!-- <span style="color: #409EFF;">【{{searchForm.businessDivision}}】</span>
            <span style="color: #409EFF;">【{{searchForm.province}}】</span> -->
					商业认可状态申请，审批号
					<span style="color: #409EFF;">【{{applySteps.completeTitle}}】</span>
				</div>
				<div class="success-bottom">共
					<span style="color: #409EFF;">【{{pageResult.currentPageList.length}}】</span>家商业
				</div>
			</div>
		</transition>
	</div>

</template>

<script>
	import storage from "@/utils/storage";
	export default {
		name: "businessAcceptStatusApply",
		props: ['passparam'],
		data() {
			return {
				saveApplyLoad: false,
				sendApplyLoad: false,
				disabledinput: false,
				componentParams: {
					flag: '', // 是编辑还是查看详情还是申请 与id是配对的
					id: '',
					title: '商业认可状态申请',
					detailsdisabled: false
				},
				addNewDialogVisible: false,
				addnewInnerVisible: false,
				ownerId: "",
				selectedList: [],
				pageResult: {
					rows: [],
					currentPageList: []
				},

				loading: false,
				pageRequest: {
					page: 1,
					pageSize: 10000,
					showTotal: true
				},
				applySteps: {
					step: '0',
					completeTitle: ''
				},

				// 查询参数
				searchForm: {
					year: '', // 年度
					quarter: ''
				},
				addnewDialog: {
					loading: false,
					// 商务分区列表
					divisionList: [],
					// 省区列表
					provinceList: [],

					searchForm: {
						businessDivisionId: '', // 商务分区
						provinceId: '', // 省区
					},
					pageRequest: {
						page: 1,
						pageSize: 10,
						showTotal: true,
					},
					pageResult: {},
					column: [{
							prop: "businessDivision",
							label: "商务分区",
							align: "center"
						},
						{
							prop: "province",
							label: "省区",
							align: "center"
						},
						{
							prop: "commerceCode",
							label: "商业编码",
							align: "center"
						},
						{
							prop: "commerceName",
							label: "商业名称",
							align: "center"
						},
						//{prop:"starLevel",label:"星级",align:"center"},
						{
							prop: "conpanyPersion",
							label: "责任人",
							align: "center"
						},
						{
							prop: "enterDate",
							label: "开户日期",
							align: "center"
						},
						{
							prop: "acceptStateStr",
							label: "认可状态",
							align: "center"
						},
						{
							prop: "memo",
							label: "备注",
							align: "center"
						}

					],
					selectedListData: []

				}
			};
		},
		mounted() {
			this.ownerId = storage.get("ownerId");

		},
		watch: {
			passparam: 'getId'
		},
		methods: {
			getId() {
				let self = this;
				let middleArr = self.passparam.split(',');
				self.componentParams.flag = middleArr[0];
				self.componentParams.id = middleArr[1];
				console.log(self.componentParams.id + self.componentParams.flag);
				if(self.componentParams.flag === 'details') {
					self.componentParams.title = '商业认可状态详情';
					self.componentParams.detailsdisabled = true;
					self.disabledinput = true;

				}
				if(self.componentParams.id && self.componentParams.flag !== 'addNew') {
					console.log('编辑/查看详情');
					// /scm/scmZsjCommerceAcceptState/v1/firstList  传入条件 agtherId 主键id
					self.$api.xsgl
						.post({
							vueInstance: self,
							url: "/scm/scmZsjCommerceAcceptState/v1/firstList",
							data: {
								pageBean: self.pageRequest,
								params: {},
								querys: [{
										hasInitValue: false,
										property: "agtherId",
										value: self.componentParams.id,
										operation: "EQUAL",
										relation: "AND"
									},
									{
										hasInitValue: false,
										property: "ownerId",
										value: self.ownerId,
										operation: "EQUAL",
										relation: "AND"
									}
								],
								sorter: []
							}
						}, function(res) {
							console.log(res);
							// res.commerceAcceptStateList.forEach(element => {
							//     if(self.componentParams.flag === 'details') {
							//         //element.disabledpriceinput = true;
							//     }
							// });
							let workDate = {
								year: '',
								quarter: ''
							};
							if(res.approvalState) {
								self.pageResult.approvalState = res.approvalState;
							}
							if(res.rows) {
								for(let i = 0; i < res.rows.length; i++) {
									self.pageResult.rows.push(res.rows[i]);
									if(i === 0 && res.rows[i].startDate) {
										workDate.year = res.rows[i].startDate.split(' ')[0].split('-')[0];
										switch(parseInt(res.rows[i].startDate.split(' ')[0].split('-')[1])) {
											case 1:
												workDate.quarter = '1';
												break;
											case 4:
												workDate.quarter = '2';
												break;
											case 7:
												workDate.quarter = '3';
												break;
											case 10:
												workDate.quarter = '4';
												break;
											default:
												break;
										}
									}
								}
								self.searchForm.year = workDate.year;
								self.searchForm.quarter = workDate.quarter;
								self.findPage();
							}
						});
				}
			},
			formatStartDate(row, column) {
				return row.startDate.split(' ')[0];
			},
			formatEndDate(row, column) {
				return row.endDate.split(' ')[0];

			},
			addnewDialogRefreshPageRequest: function(page = 1) {
				this.addnewDialog.pageRequest.page = page;
				this.getDialogListData();
			},
			addnewDialogRefreshPageSizeRequest: function(pageSize) {
				this.addnewDialog.pageRequest.pageSize = pageSize;
				this.getDialogListData();
			},
			refreshPageRequest: function(page = 1) {
				this.pageRequest.page = page;
				this.findPage();
			},
			refreshPageSizeRequest: function(pageSize) {
				this.pageRequest.pageSize = pageSize;
				this.findPage();
			},
			// 获取申请主页面的选中的列表数据
			getOuterSelectedList(list) {
				let self = this;
				self.selectedList = [];
				list.forEach(element => {
					self.selectedList.push(element);
				});
			},
			// 删除选中行
			deleteSelectedRow() {
				let self = this;
				// 删除当前页列表数据
				for(let i = self.pageResult.currentPageList.length - 1; i >= 0; i--) {
					for(let j = 0; j < self.selectedList.length; j++) {
						if(self.pageResult.currentPageList[i].id === self.selectedList[j].id) {
							self.pageResult.currentPageList.splice(i, 1);
							break;
						}
					}
				}
				// 删除总列表数据
				for(let i = self.pageResult.rows.length - 1; i >= 0; i--) {
					for(let j = 0; j < self.selectedList.length; j++) {
						if(self.pageResult.rows[i].id === self.selectedList[j].id) {
							self.pageResult.rows.splice(i, 1);
							break;
						}
					}
				}
				self.findPage();
			},
			// 删除单行
			deleteRow(index, item) {
				let self = this;
				// 先删除当前页的
				self.pageResult.currentPageList.splice(index, 1);
				// 再删除总列表的
				self.pageResult.rows.splice(self.pageRequest.pageSize * self.pageRequest.page - self.pageRequest.pageSize + index, 1);

				self.findPage();
			},
			// 选择季度，自动填充生效日期
			setStartDateAndEndDate() {
				let self = this;
				let setQuarter = {
					startDate: '',
					endDate: ''
				};

				if(self.searchForm.year && self.searchForm.quarter) {
					switch(self.searchForm.quarter) {
						case "1":
							setQuarter.startDate = self.searchForm.year + '-01-01 00:00:00';
							setQuarter.endDate = self.searchForm.year + '-03-31 00:00:00';
							break;
						case "2":
							setQuarter.startDate = self.searchForm.year + '-04-01 00:00:00';
							setQuarter.endDate = self.searchForm.year + '-06-30 00:00:00';
							break;
						case "3":
							setQuarter.startDate = self.searchForm.year + '-07-01 00:00:00';
							setQuarter.endDate = self.searchForm.year + '-09-30 00:00:00';
							break;
						case "4":
							setQuarter.startDate = self.searchForm.year + '-10-01 00:00:00';
							setQuarter.endDate = self.searchForm.year + '-12-31 00:00:00';
							break;
						default:
							break;
					}

				}
				for(let i = 0; i < self.pageResult.currentPageList.length; i++) {
					self.$set(self.pageResult.currentPageList[i], 'startDate', setQuarter.startDate);
					self.$set(self.pageResult.currentPageList[i], 'endDate', setQuarter.endDate);
				}
				// 更新原数组
				self.pageResult.rows.splice(self.pageRequest.pageSize * self.pageRequest.page - self.pageRequest.pageSize, self.pageResult.currentPageList.length, ...self.pageResult.currentPageList);

			},
			// 切换申请步骤页面
			toogleApplySteps(flag) {
				let self = this;
				self.applySteps.step = '-1';
				setTimeout(() => {
					self.applySteps.step = flag;
				}, 500);
			},
			// 向父组件传值
			showParentComp() {
				this.$emit("closeapplypage", {});
			},
			addNewCommerce() {
				this.addNewDialogVisible = true;
				this.getDivision();
				this.getDialogListData();
			},
			dialogCancel() {
				let self = this;
				self.addnewDialog.pageResult = {};
				self.addnewDialog.searchForm.provinceId = '';
				self.addnewDialog.searchForm.businessDivisionId = '';
				self.addnewDialog.pageRequest.page = 1;
				self.addnewDialog.pageRequest.pageSize = 10;
				self.addnewDialog.pageRequest.showTotal = true;
				self.addnewDialog.selectedListData = [];
				self.addNewDialogVisible = false;

			},
			dialogConfirm() {
				let repetitionMark = false;
				let self = this;
				if(self.addnewDialog.selectedListData.length > 0) {
					for(let i = self.addnewDialog.selectedListData.length - 1; i >= 0; i--) {
						for(let j = 0; j < self.pageResult.rows.length; j++) {
							// 防止重复添加
							//console.log('selectedListData[' + i+'].id = ' + self.addnewDialog.selectedListData[i].id + '-----------------' + 'self.pageResult.rows[' + j +'].id' + self.pageResult.rows[j].id);
							if(self.addnewDialog.selectedListData[i].commerceCode === self.pageResult.rows[j].commerceCode) {
								self.addnewDialog.selectedListData.splice(i, 1);
								repetitionMark = true;
								break;
							}
						}
					}

					for(let i = 0; i < self.addnewDialog.selectedListData.length; i++) {
						delete self.addnewDialog.selectedListData[i].id;
						if(self.addnewDialog.selectedListData[i].acceptState === '-1') {
							self.addnewDialog.selectedListData[i].acceptState = '0';
						}
						self.pageResult.rows.push(self.addnewDialog.selectedListData[i]);
					}
					// 添加完数据以后重新查询当前页数据
					self.findPage();
				}
				if(repetitionMark) {
					self.addnewInnerVisible = true;
				}
				//self.pageResult.rows = self.addnewDialog.selectedListData;
				setTimeout(function() {
					self.dialogCancel();
				}, 300);

			},
			getDialogSelectedData(val) {
				let self = this;
				self.addnewDialog.selectedListData = [];
				for(let i = 0; i < val.length; i++) {
					self.addnewDialog.selectedListData.push(val[i]);
				}
			},
			// 请求dialog框中的列表数据
			getDialogListData() {
				// /scm/scmZsjCommerce/v1/firstList
				let self = this;
				let search = [];
				self.addnewDialog.loading = true;
				for(let key in self.addnewDialog.searchForm) {
					if(self.addnewDialog.searchForm[key]) {
						search.push({
							property: key,
							value: self.addnewDialog.searchForm[key],
							operation: "LIKE",
							relation: "AND"
						});
					}
				}
				self.$api.xsgl
					.post({
						vueInstance: self,
						url: "/scm/scmZsjCommerce/v1/firstList",
						data: {
							pageBean: self.addnewDialog.pageRequest,
							params: {},
							// approvalState
							querys: [{
									hasInitValue: false,
									property: "ownerId",
									value: self.ownerId,
									operation: "EQUAL",
									relation: "AND"
								},
								{
									hasInitValue: false,
									property: "approvalState",
									value: '2',
									operation: "EQUAL",
									relation: "AND"
								}
							].concat(search),
							sorter: []
						}
					}, function(res) {
						self.addnewDialog.pageResult = res;
						self.addnewDialog.loading = false;
					});
			},
			// 申请暂存 -- /scm/scmZsjCommerceAcceptStateTS/v1/save  scmZsjCommerceAcceptStateTS
			saveApply() {
				let self = this;
				self.saveApplyLoad = true;
				let requestData = {
					"ownerId": self.ownerId,
					"id": '',
					"commerceAcceptStateList": [
						//   agtherId: "",
						//     id:""",
						//     memo:""
					],

				};
				self.loading = true;
				if(self.componentParams.id) {
					requestData.id = self.componentParams.id;
				}
				requestData.commerceAcceptStateList = self.pageResult.currentPageList;
				if(requestData.commerceAcceptStateList.length === 0) {
					self.$message({
						message: '请添加商业！',
						type: 'error',
						duration: 2000
					});
					self.saveApplyLoad = false;
					self.loading = false;
					return;
				} else {
					requestData.commerceAcceptStateList.forEach(function(item, index) {
						delete item.id;
					});
				}
				// 发送请求
				self.$api.xsgl.post({
					vueInstance: self,
					url: '/scm/scmZsjCommerceAcceptStateAgther/v1/save',
					data: requestData
				}, function(res) {
					self.loading = false;
					self.saveApplyLoad = false;
					if(res.value) {
						self.componentParams.id = res.value.id;
					}
					self.$message({
						message: '暂存成功！',
						type: "success",
						duration: 2000
					});
				});
			},
			// 发起申请
			sendApply() {
				let self = this;
				self.sendApplyLoad = true;
				let requestData = {
					"ownerId": self.ownerId,
					"id": '',
					"commerceAcceptStateList": []
				};
				// 验证生效年月
				for(let i = 0; i < self.pageResult.currentPageList.length; i++) {
					if(!self.pageResult.currentPageList[i].startDate || !self.pageResult.currentPageList[i].endDate) {
						self.$message({
							message: '请选择年度和季度！',
							type: 'error',
							duration: 2000
						});
						self.sendApplyLoad = false;
						return;
					}

				}
				// 验证通过
				if(self.componentParams.id) {
					requestData.id = self.componentParams.id;
				}
				requestData.commerceAcceptStateList = self.pageResult.currentPageList;
				if(requestData.commerceAcceptStateList.length === 0) {
					self.$message({
						message: '请添加商业！',
						type: 'error',
						duration: 2000
					});
					return;
				} else {
					requestData.commerceAcceptStateList.forEach(function(item, index) {
						delete item.id;
					});
				}

				// 发送请求
				self.$api.xsgl.post({
					vueInstance: self,
					url: '/scm/scmZsjCommerceAcceptStateAgther/v1/sendApply',
					data: requestData
				}, function(res) {
					// self.applySteps.step = '1';
					self.sendApplyLoad = false;
					self.applySteps.completeTitle = res.value.approvalId;
					self.toogleApplySteps('1');
				});
			},

			// 获取商务分区
			getDivision() {
				let self = this;
				self.$api.xsgl.getZdBusinessDivision({
					vueInstance: self
				}, function(res) {
					self.addnewDialog.divisionList = res;
				});
			},
			// 获取省区
			getPrivence() {
				this.addnewDialog.searchForm.provinceId = ''
				let divisionId = this.addnewDialog.searchForm.businessDivisionId // 商务分区ID
				this.addnewDialog.provinceList = this.addnewDialog.divisionList.reduce((final, item, index, arr) => {
					if(item.id == divisionId) {
						final = item.provinceList;
					}
					return final;
				}, []);
			},

			//列表 -- 这个列表是前端维护，分页也在前端
			findPage() {
				let self = this;
				let listTotalLength = self.pageResult.rows.length;
				// 总页数
				let totalPages = Math.ceil(listTotalLength / self.pageRequest.pageSize);
				let startIndex = self.pageRequest.page * self.pageRequest.pageSize - self.pageRequest.pageSize;
				let endIndex = self.pageRequest.page * self.pageRequest.pageSize - 1;
				self.pageResult.currentPageList = self.pageResult.rows.slice(startIndex, endIndex + 1);
				self.pageResult.total = listTotalLength;
				self.setStartDateAndEndDate();
				console.log(self.pageResult);
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
	}
	
	.header h1 {
		font-size: 28px;
		text-align: center;
	}
	
	.header-btn-group {
		position: absolute;
		height: 100%;
		right: 5px;
		top: 0;
		display: flex;
		align-items: center;
	}
	
	.header-back {
		position: absolute;
		height: 100%;
		left: 5px;
		top: 0;
		display: flex;
		align-items: center;
	}
	
	.query-section {
		margin-bottom: 20px;
	}
	
	.header-left,
	.header-right {
		width: 50%;
		display: flex;
		align-items: center;
		font-size: 16px;
	}
	
	.header-right {
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
			margin: 0;
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
				margin: 0;
			}
		}
	}
	
	.apply-success-section {
		margin: 150px auto auto auto;
		width: 60%;
		border: 1px solid #dddddd;
		padding: 5px;
		border-radius: 3px;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	
	.success,
	.success-bottom {
		width: 100%;
	}
</style>