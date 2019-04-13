<template>
	<div>
		<div v-show="startShow">
			<div class="header">
				<div class="headleft"><span class="el-icon-search"></span><span class="sx">筛选查询</span></div>
				<div class="headright">
					<el-row>
						<el-button type="primary" class="el-icon-search" size="mini" @click="searchquery">查询</el-button>
					</el-row>
				</div>
			</div>
			<el-form :label-position="labelPosition" size="small" label-width="120px" class="demo-ruleForm">
				<el-form-item label="供应商名称">
					<el-select v-model="gongname" clearable placeholder="请选择供应商名称" @change="getgongname">
						<el-option v-for="item in gongoption" :key="item.id" :label="item.supplierName" :value="item.supplierName">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="申请日期">
					<el-date-picker v-model="applyDate" value-format="yyyy-MM-dd HH:mm:ss" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期">
					</el-date-picker>
				</el-form-item>
			</el-form>
			<div class="header">
				<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">数据列表</span></div>
				<div class="headright">
					<el-button type="primary" class="el-icon-circle-plus" size="mini" @click="buy">采购合同申请</el-button>
				</div>
			</div>
			<!--table-->
			<el-table v-loading="loading2" element-loading-text="加载中" :data="tableData3" size="mini" border style="width: 100%">
				<el-table-column type="index" label="序号" width="100" align="center">
				</el-table-column>
				<el-table-column v-for="item in column" :key="item.prop" :prop="item.prop" :label="item.label" :formatter="item.formatter" :minwidth="item.minwidth" :align="item.align">
				</el-table-column>
				<el-table-column fixed="right" label="操作" align="center" width="200">
					<template slot-scope="scope">
							<el-button class="el-icon-view" type="primary" size="mini" @click="deial(scope.row)">查看详情</el-button>
						<el-button type="primary" :disabled="scope.row.approvalState==0?false:true" class="el-icon-edit" size="mini" @click="edit(scope.row)">编辑</el-button>
					</template>
				</el-table-column>
			</el-table>
			<div class="pagination">
				<el-pagination @current-change="refreshPageRequest" @size-change="refreshPageSizeRequest" background :current-page="pageRequest.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest.pageSize" layout="sizes, total, prev, pager, next" :total="total">
				</el-pagination>
			</div>
		</div>
		<!--第一步-->
		<div class="head-item" v-show="one">
			<div class="searchHeader">
				<div class="headleft">
					<el-button size="mini" class="el-icon-back" type="primary" @click="goback">返回</el-button>
				</div>

				<div class="headcenter">供货单位合同签订申请</div>
				<div class="headright">
					<el-button size="mini" type="primary" v-show="oneShow" @click="nextStep()">下一步</el-button>
					<el-button size="mini" type="primary" v-show="twoShow" @click="prevStep">上一步</el-button>
					<el-button size="mini" type="primary" v-show="fourShow" @click="sendApply">发起申请</el-button>
				</div>

			</div>
            <!-- 步骤条 -->
			<el-row>
				<el-steps :space="500" :align-center="true" :active="active" finish-status="success" style="margin: 35px auto;">
					<el-step title="选择供应商"></el-step>
					<el-step title="添加供货商品信息"></el-step>
					<el-step title="完成"></el-step>
				</el-steps>
			</el-row>
		</div>
		<transition name="el-fade-in">
			<div v-show="two">
				<div class="content-item">
					<div class="header">
						<div class="headleft"><span class="el-icon-search"></span><span class="sx">选择供应商</span></div>
					</div>
					<el-row>
						<el-form :model="wrapForm" :rules="wraprules" ref="wrapForm1" :label-position="labelPosition" label-width="120px" size="small" class="demo-ruleForm" style="display: flex;justify-content: center;margin-top: 80px;margin-bottom: 300px;">
							<el-form-item label="供应商名称" prop="applyname">
								<el-select v-model="wrapForm.applyname" clearable placeholder="请选择供应商名称" @change="getapplyname">
									<el-option v-for="item in applyoption" :key="item.id" :label="item.supplierName" :value="item.supplierName">
									</el-option>
								</el-select>
							</el-form-item>
							<el-form-item label="币种" prop="bzname">
								<el-select v-model="wrapForm.bzname" clearable placeholder="请选择币种">
									<el-option v-for="item in bzoption" :key="item.inx" :label="item.name" :value="item.name">
									</el-option>
								</el-select>
							</el-form-item>
						</el-form>
					</el-row>
				</div>
			</div>
		</transition>
		<!--新增产品对话框-->
		<el-dialog title="新增商品" :visible.sync="dialogVisible" width="65%">
			<el-form :model="dataForm" :rules="datarules" ref="dataForm1" :label-position="labelPosition" size="small" label-width="130px" class="demo-ruleForm">
				<el-form-item label="产品名称" style="width: 100%;" prop="cpmcname" class="demo-autocomplete" >
					<el-autocomplete style="width: 100%;" class="inline-input" v-model="dataForm.cpmcname" :fetch-suggestions="querySearch" placeholder="请输入产品名称" @select="handleSelect"></el-autocomplete>
				</el-form-item>
				<el-form-item label="产品编码" style="width: 50%;">
					<el-input v-model="cpbmName" :disabled="true"></el-input>
				</el-form-item>
				<el-form-item label="产品通用名称" style="width: 50%;">
					<el-input v-model="cptyName" :disabled="true"></el-input>
				</el-form-item>
				<el-form-item label="规格" style="width: 50%;">
					<el-input v-model="pgName" :disabled="true"></el-input>
				</el-form-item>
				<el-form-item label="药品类型" style="width: 50%;">
					<el-input v-model="yplxName" :disabled="true"></el-input>
				</el-form-item>
				<el-form-item label="单位" style="width: 50%;">
					<el-input v-model="dwName" :disabled="true"></el-input>
				</el-form-item>
				<el-form-item prop="djName" label="单价" style="width: 50%;">
					<el-input v-model="dataForm.djName" placeholder="请输入单价"></el-input>
				</el-form-item>
				<el-form-item label="往年纯销量" style="width: 50%;">
					<el-input v-model="wncxlName" :disabled="true"></el-input>
				</el-form-item>
				<el-form-item prop="slName" label="数量" style="width: 50%;">
					<el-input v-model="dataForm.slName" placeholder="请输入数量"></el-input>
				</el-form-item>
				<el-form-item label="金额" style="width: 50%;">
					<el-input v-model="totalPrice" :disabled="true"></el-input>
				</el-form-item>
				<el-form-item label="币种" style="width: 50%;">
					<el-input v-model="biName" :disabled="true"></el-input>
				</el-form-item>
				<el-form-item label="批准文号/启用批号" style="width: 50%;">
					<el-input v-model="pzwhName" :disabled="true"></el-input>
				</el-form-item>
				<el-form-item label="件装量" style="width: 50%;">
					<el-input v-model="jzlName" :disabled="true"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
			    <el-button @click="dialogVisible = false">取 消</el-button>
			    <el-button type="primary" @click="save">确 定</el-button>
			  </span>
		</el-dialog>

		<!--第二步-->
		<transition name="el-fade-in">
			<div class="content-item" v-show="three">
				<div class="header">
					<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">添加采购</span><span style="color:#409EFF">【{{supplier}}】</span>商品信息,合同号为<span style="color:#409EFF">【{{contractCode}}】</span>。已选择<span style="color:#409EFF">【{{speciesSum}}】</span>种产品,采购总金额为<span style="color:#409EFF">【{{sumPrice}}】</span></div>
					<div class="headright">
						<el-button type="primary" class="el-icon-circle-plus" size="mini" @click="addGoods">新增产品</el-button>
					</div>
				</div>
				<el-table v-loading="loading" element-loading-text="加载中" :data="tableData" size="mini" border style="width: 100%">
					<el-table-column type="index" label="序号" width="100" align="center">
					</el-table-column>
					<el-table-column v-for="item in column1" :key="item.prop" :prop="item.prop" :label="item.label" :formatter="item.formatter" :width="item.minwidth" :align="item.align">
					</el-table-column>
					
					<el-table-column fixed="right" label="单价" align="center" minwidth="100">
						<template slot-scope="scope">
							<span v-show="scope.row.propstate == 0">{{scope.row.unitPrice}}</span>
							<el-input v-show="scope.row.propstate == 1" v-model="scope.row.unitPrice" ></el-input>
						</template>
					</el-table-column>
					<el-table-column fixed="right" label="数量" align="center" minwidth="100">
						<template slot-scope="scope">
							<!--{{scope.row.productSum}}-->
							<span v-show="scope.row.propstate == 0">{{scope.row.productSum}}</span>
							<el-input v-model="scope.row.productSum" v-show="scope.row.propstate == 1"></el-input>
						</template>
					</el-table-column>
					<el-table-column fixed="right" label="操作" align="center" minwidth="100">
						<template slot-scope="scope">
							<el-button type="primary" class="el-icon-edit" size="mini" @click="commmon(scope.$index,scope.row)">{{scope.row.propstate==0?'编辑':'保存'}}</el-button>
						</template>
					</el-table-column>
				</el-table>
				<div class="pagination">
					<el-pagination @current-change="refreshPageRequest1" @size-change="refreshPageSizeRequest1" background :current-page="pageRequest1.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest1.pageSize" layout="sizes, total, prev, pager, next" :total="total1">
					</el-pagination>
				</div>
			</div>
		</transition>
		<!--第三步-->
		
		
		<transition name="el-fade-in">
			<div class="content-item" v-show="four">
				<div class="success">
					<span class="el-icon-check"></span> 您已完成
					<span style="color: #409EFF;">【{{supplier}}】</span>供货合同签订
				</div>
				<div class="success-bottom">
					合同已申请<span style="color: #409EFF;">【{{speciesSum}}】</span>种供货产品,总金额为<span style="color:#409EFF;">【{{sumPrice}}】</span>{{biName}}
				</div>
			</div>
		</transition>
	</div>
</template>
<script>
	import storage from '@/utils/storage'
	export default {
		name: "goods",
		data() {
			var checkAge = (rule, value, callback) => {
				if(!value) {
					return callback(new Error('请输入单价'));
				}
				if(value<=0) {
					return callback(new Error('输入单价不能小于1'));
				}
				if(value.length > 10) {
					return callback(new Error('输入单价长度不能大于10位'));
				}
				if(!Number(value)) {
					callback(new Error('单价只能是数字值'));
				}
			};
			var checknum=(rule, value, callback) => {
				if(!value) {
					return callback(new Error('请输入数量'));
				}
				if(value<=0) {
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
				column: [{
						prop: "approvalState",
						label: "审批状态",
						minwidth: "150",
						align: "center",
						formatter: function(row, column, cellValue, index) {
							if(row.approvalState == 0) {
								return "未提交"
							}
							if(row.approvalState == 1) {
								return "审批中"
							}
							if(row.approvalState == 2) {
								return "已通过"
							}
						}
					},
					{
						prop: "contractCode",
						label: "合同编号",
						minwidth: "200",
						align: "center"
					},
					{
						prop: "supplier",
						label: "供应商名称",
						minwidth: "150",
						align: "center"
					},
					{
						prop: "speciesSum",
						label: "供货产品种数品种数",
						minwidth: "200",
						align: "center"
					},
					{
						prop: "productSum",
						label: "数量",
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
					{
						prop: "filingDate",
						label: "申请日期",
						minwidth: "200",
						align: "center"
					},
				],
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
				contractCode:'',
				bjbtn:true,
				bcbtn:false,
				djText:"",
				slText:'',
				slSpan:true,
				djSpan:true,
				editBtnType:false,
				id: "",
				cpmcId: '',
				cpbmName: "",
				cptyName: '',
				pgName: '',
				yplxName: '',
				yplxNameid: '',
				jzlName:"",
				dwName: '',
				wncxlName: '10000',
				jeName: '',
				biName: '',
				pzwhName: '',
				btnState: false,
				startShow: true,
				one: false,
				two: false,
				three: false,
				four: false,
				active: 0,
				oneShow: true,
				speciesSum: '',
				twoShow: false,
				fourShow: false,
				restaurants: [],
				cparray: [],
				typearr: [],
				allArr: [],
				applyDate: "",
				supplier: '',
				sumPrice: '',
				labelPosition: "right",
				loading2: true,
				loading: true,
				ownerId: "",
				total: 0,
				total1: 0,
				operation: true,
				dialogVisible: false,
				gongid: "",
				deialId:"",
				applyid: "",
				gongname:'',
				gongoption: [],
				applyoption: [],
				tableData3: [],
				tableData: [],
				bzoption: [{
					"name": "CNY",
					"inx": 1
				}],
				checked: false,
				loading: false,
				search: [],
				totalPrice: 0,
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
				dataForm: {
					djName: 0,
					slName: 1,
					cpmcname:"",
				},
				wrapForm: {
					applyname: '',
					bzname: '',
				},
				datarules: {
					djName: [{
						required: true,
						validator: checkAge,
						trigger: 'blur'
					}, ],
					slName: [{
						required: true,
						validator: checknum,
						trigger: 'blur'
					}, ],
					cpmcname: [{
						required: true,
						validator: cpmsg,
						trigger: 'change'
					}, ],
				},
				wraprules: {
					applyname: [{
							required: true,
							message: '请选择供应商',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
					bzname: [{
							required: true,
							message: '请选择币种',
							trigger: 'blur'
						},
						{
							max: 50,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
				}
			}
		},
		methods: {
			querySearch(queryString, cb) {
				var restaurants = this.restaurants;
				var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
				// 调用 callback 返回建议列表的数据
				cb(results);
			},
			createFilter(queryString) {
				return(restaurant) => {
					return(restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
				};
			},
			loadAll() {
				return this.cparray
			},
			handleSelect(item) {
				var getData = this.allArr.filter((item) => {
					if(item.productName == this.dataForm.cpmcname) {
						this.cpmcId = item.id
						this.dataForm.cpmcname = item.productName //产品名称
						this.cpbmName = item.productCode //产品编码
						this.cptyName = item.commonName //通用名称
						this.pgName = item.productState //规格
						this.dwName = item.unit //单位
						this.yplxName = item.medicineType //药品类型
						this.yplxNameid = item.medicineTypeId //药型类型id
						this.pzwhName = item.approvalCode //批准文号
						this.jzlName = item.achange//件装量
						console.log(item)
					}
				})
			},
			addGoods() {
				//新增产品显示
				this.dialogVisible = true
			},
			refreshPageRequest: function(page = 1) {
				//分页刷新
				this.pageRequest.page = page;
				this.findPage();
			},
			refreshPageRequest1: function(page = 1) {
				//分页刷新
				this.pageRequest1.page = page;
				this.findPage1();
			},
			refreshPageSizeRequest: function(pageSize) {
				//分页刷新
				this.tableData3.pageSize = pageSize;
				this.findPage();
			},
			refreshPageSizeRequest1: function(pageSize) {
				//分页刷新
				this.tableData.pageSize = pageSize;
				this.findPage1();
			},
			getgongname() {
				//获取供应商id
				if(this.gongoption.length != 0) {
					var data = this.gongoption.filter((item) => {
						if(item.supplierName == this.gongname) {
							this.gongid = item.id
							console.log(this.gongid)
						}
					})
				}
			},
			getapplyname() {
				//获取供应商id
				if(this.applyoption.length != 0) {
					var data = this.applyoption.filter((item) => {
						if(item.supplierName == this.wrapForm.applyname) {
							this.applyid = item.id
							console.log(this.applyid)
						}
					})
				}
			},
			resetForm(formName) {
				//清空表单
				this.$refs[formName].resetFields();
			},
			deial(rows){
				this.deialId = rows.id
				this.$router.push({
						path: '/cgdd/cgContractDeial',
						query: {
							id: this.deialId
						}
				})
				this.$store.state.cghtDeialId=this.deialId
			},
			edit(rows) {
				//合同列表编辑
				if(this.btnState == false) {
					this.productFirstId = rows.id
					this.$router.push({
						path: '/cgdd/cgContractDeial',
						query: {
							htid: this.productFirstId
						}
					})
				}
				this.$store.state.cght=this.productFirstId
				console.log(this.$store.state)
				
			},
			btnedit(rows,index){
				
				console.log(rows)
				//采购商品列表编辑
				if(rows.btnstate==0){
						this.bjbtn=false//编辑按钮隐藏
						this.bcbtn=true //保存按钮显示
						this.djSpan=false//单价文字隐藏
						this.slSpan=false//数量文字隐藏
						this.djType=true//单价输入框显示
						this.editBtnType=true//数量输入框显示
				}
			},
			searchquery() {
				//合同列表查询
				if(this.applyDate) {
					this.search.push({
						property: "filingDate",
						value: this.applyDate,
						operation: "BETWEEN",
						relation: "AND"
					})
				}
				if(this.gongid) {
					this.search.push({
						property: "supplierId",
						value: this.gongid,
						operation: "EQUAL",
						relation: "AND"
					})
				}
				console.log(this.search)
				this.findPage(this.search)
			},
			//合同列表
			findPage(search) {
				this.loading = true;
				this.$api.zsj.getZsj({
					url: "/scm/scmCgProcurementContract/v1/list",
					data: {
						pageBean: this.pageRequest,
						params: {},
						querys: search,
						sorter: []
					},
				}).then(res => {
					if(res) {
						this.tableData3 = res.rows;
						this.total = res.total;
						this.loading2 = false;
						this.search = []
					} else {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
					}
				});
			},
			//商品列表
			findPage1() {
				this.loading = true;
				this.$api.zsj.getZsj({
					url: "/scm/scmCgContractProduct/v1/list",
					data: {
						pageBean: this.pageRequest,
						params: {},
						querys: [{
							hasInitValue: false,
							operation: "EQUAL",
							relation: "AND",
							value: this.id,
							property: "contractId"
						}],
						sorter: []
					},
				}).then(res => {
					if(res) {
						let newData = res.rows;
						for(var i=0;i<newData.length;i++){
							newData[i].propstate=0
						}
						this.tableData = newData;
						this.total1 = res.total;
						this.loading = false;
					} else {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
					}
				});
			},
			buy() {
				this.startShow = false;
				this.one = true;
				this.two = true;
			},
			nextStep() {
				//下一步
				this.$refs.wrapForm1.validate(valid => {
					if(valid) {
						var data = {
							supplier: this.wrapForm.applyname,
							supplierId: this.applyid,
							currency: this.wrapForm.bzname,
							ownerId: this.ownerId,
							id: this.id
						}
						this.$api.zsj.getZsj({
							url: "/scm/scmCgProcurementContract/v1/save",
							data: data,
						}).then(res => {
							if(res.state == false) {
								this.$message({
									message: "操作失败, " + res.shortMessage,
									type: "error",
									duration: 800
								});
							} else {
								this.id = res.value
								this.active = 1;
								this.one = true;
								this.two = false;
								this.goodsMsg();
								this.findPage1();
							}
						});
					}
				})
			},
			product() {
				//商品信息
				this.cparray.length=0;
				this.$api.zsj.changeState({
					url: "/scm/scmZsjProduct/v1/downBoxApplySuccess/" + this.ownerId + "/" + this.applyid
				}).then(res => {
					if(res) {
						this.allArr = res.value
						for(var i = 0; i < res.value.length; i++) {
							let valueMemo = {
								value: res.value[i].productName
							}
							this.cparray.push(valueMemo)
						}
						console.log(this.cparray)
					} else {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
					}
				});
			},
			goodsMsg() {
				//产品信息
				this.$api.zsj.changeState({
					url: "/scm/scmCgProcurementContract/v1/get/" + this.id,
				}).then(res => {
					if(res.state == false) {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
					} else {
						this.supplier = res.supplier //供应商名称
						this.sumPrice = res.sumPrice //总金额
						this.contractCode = res.contractCode //合同编号
						this.speciesSum = res.speciesSum //种数
						//币种
						this.biName = res.currency
						console.log(res)
						setTimeout(() => {
							this.three = true;
							this.oneShow = false;
							this.twoShow = true;
							this.fourShow = true;
						}, 220)
						this.product()
					}
				});
			},
			prevStep() {
				//上一步
				this.three = false;
				this.active = 0;
				setTimeout(() => {
					this.one = true;
					this.two = true;
					this.oneShow = true;
					this.twoShow = false;
					this.fourShow = false;
				}, 220)
			},
			goback() {
				//返回
				this.startShow = true;
				this.findPage()
				this.one = false;
				this.two = false;
				this.oneShow = true;
				this.twoShow = false;
				this.fourShow = false;
				this.three = false;
				this.resetForm('wrapForm1');
				this.id = "";
				this.four=false;
				this.active=0
			},
			save() {
					if(!this.dataForm.cpmcname){
					this.$message({
							message: "请选择产品名称",
							type: "error",
							duration: 1000
					});
					return false
				}
				if(!this.dataForm.djName){
					this.$message({
							message: "请输入单价",
							type: "error",
							duration: 1000
					});
					return false
				}
				if(!this.dataForm.slName){
					this.$message({
							message: "请输入数量",
							type: "error",
							duration: 1000
					});
					return false
				}
						this.dialogVisible = false
						var search = {
							"approvalNumber": this.pzwhName, //批准文号
							"currency": this.biName, //币种
							"sumPrice": this.totalPrice, //总金额
							"unit": this.dwName, //单位
							"productName": this.dataForm.cpmcname, //产品名称
							"productCname": this.cptyName, //通用名称
							"contractCode": this.contractCode, //合同编号
							"contractId": this.id, //合同id
							"productGauge": this.pgName, //规格
							"medicineType": this.yplxName, //药品类型
							"medicineTypeId": this.yplxNameid, //药品类型id
							"unitPrice": this.dataForm.djName, //单价
							"productSum": this.dataForm.slName, //数量
							"productId": this.cpmcId, //商品id
							"productCode": this.cpbmName,//产品编码
							"achange":this.jzlName//件装量
						}
						this.$api.zsj.getZsj({
							url: "/scm/scmCgContractProduct/v1/save",
							data: search
						}).then(res => {
							if(res.state == false) {
								this.$message({
									message: "操作失败, " + res.shortMessage,
									type: "error",
									duration: 1000
								});
							} else {
								this.$message({
									message: "保存成功 ",
									type: "success",
									duration: 1000
								});
								this.resetForm('dataForm1');
							    this.pzwhName="" //批准文号
							    this.totalPrice="" //总金额
							    this.dwName="" //单位
							    this.dataForm.cpmcname='' //产品名称
							    this.cptyName="" //通用名称
							    this.pgName="" //规格
							    this.yplxName="" //药品类型
							    this.yplxNameid="", //药品类型id
							    this.dataForm.djName=0 //单价
							    this.dataForm.slName=1 //数量
							    this.cpmcId='' //商品id
							    this.cpbmName=""//产品编码
								this.jzlName=""
								this.tableData3 = res.rows;
								this.total = res.total;
								this.loading2 = false;
								search = {}
								this.goodsMsg()
								this.findPage1()
							}
						});
			},
			sendApply() {
				if(!this.id) {
					this.$message({
						message: "操作失败 ",
						type: "error",
						duration: 1000
					});
					return false
				} else {
					this.$api.zsj.getZsj({
						url: "/scm/scmCgProcurementContract/v1/sendApply",
						data: {
							"id": this.id
						}
					}).then(res => {
						if(res.state == false) {
							this.$message({
								message: "操作失败, " + res.shortMessage,
								type: "error",
								duration: 800
							});
						} else {
							this.oneShow=false
							this.three = false;
							this.fourShow=false;
							this.twoShow=false;
							this.active=2;
							setTimeout(() => {
								this.four = true
							}, 220)
							this.$message({
								message: "申请成功",
								type: "success",
								duration: 800
							});
							this.$api.zsj.changeState({
									url: "/scm/scmCgProcurementContract/v1/get/" + this.id,
								}).then(res => {
									if(res.state == false) {
										this.$message({
											message: "操作失败, " + res.shortMessage,
											type: "error",
											duration: 800
										});
									} else {
										this.supplier = res.supplier //供应商名称
										this.sumPrice = res.sumPrice //总金额
										this.contractCode = res.contractCode //合同编号
										this.speciesSum = res.speciesSum //种数
										//币种
										this.biName = res.currency
									}
								});
						}
					});
				}
			},
			commmon(index, row){
				if(row.propstate == 0){
					this.tableData[index]['propstate'] = 1;
				}else if(row.propstate == 1){
					var search = {
					"contractId": this.id, //合同id
					"unitPrice": row.unitPrice, //单价
					"productSum":row.productSum, //数量
					"productId": row.productId, //商品id
					"id":row.id
				}
				this.$api.zsj.getZsj({
					url: "/scm/scmCgContractProduct/v1/save",
					data: search
				}).then(res => {
					if(res.state == false) {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
					} else {
						this.$message({
							message: "编辑成功",
							type: "success",
							duration: 800
						});
						search = {}
						this.goodsMsg()
						this.findPage1()
					}
				});
				}
			}

		},
		watch: {
			"cpmcname": function(val) {
				for(var i = 0; i < this.cparray.length; i++) {
					if(String(this.cparray[i].value.split("|")[1]).split(" ")[1] == val) {
						this.typearr.push(this.cparray[i].value)
						this.goodsType = true
					}
				}
				if(this.typearr.length == 0) {
					this.resetForm('ruleForm1')
					this.checked = false
					this.goodsType = false
				} else {
					this.typearr = []
				}
			},
			'dataForm.slName': function(val) {
				this.totalPrice = val * this.dataForm.djName
			},
			'dataForm.djName': function(val) {
				this.totalPrice = val * this.dataForm.slName
			},
			
		},
		mounted() {
			this.restaurants = this.loadAll();
			this.ownerId = storage.get('ownerId')
			this.findPage();
			//供应商
			this.$api.zsj.changeState({
				url: "/scm/scmZsjSupplier/v1/downBoxApplySuccess/" + this.ownerId,
			}).then(res => {
				if(res) {
					this.gongoption = res
					this.applyoption = res
				} else {
					this.$message({
						message: "操作失败, " + res.shortMessage,
						type: "error",
						duration: 800
					});
				}
			});

		},
	}
</script>
<style scoped="scoped" lang="scss">
	.header {
		width: 100%;
		height: 38px;
		font-size: 16px;
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
	
	.sx {
		margin: auto 5px;
	}
	
	.pagination {
		margin-top: 20px;
		display: flex;
		justify-content: flex-end;
	}
	
	.el-select {
		width: 100%;
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
	
	header {
		height: 40px !important;
	}
	
	.el-form-item {
		margin-top: 10px;
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
	}
	
	.head-item {
		border: 1px solid #f2f2f2;
	}
	
	.content-item {
		border: 1px solid #f2f2f2;
		margin-top: 8px;
	}
	
	.main-content {
		padding: 0 !important;
	}
	
	.success {
		width: 350px;
		height: 50px;
		border: 1px solid #f2f2f2;
		margin: 0 auto;
		margin-top: 150px;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.success-bottom {
		margin-top: 50px;
		margin-bottom: 250px;
	}
	
	.el-dialog .el-dialog__body {
		border-top: 1px solid #f2f2f2 !important;
		border-bottom: 1px solid #f2f2f2 !important;
		padding: 0 !important;
	}
</style>