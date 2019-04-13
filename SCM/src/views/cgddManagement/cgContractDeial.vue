<template>
	<div>
		<el-container>
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
			<div class="searchHeader">
				<div class="headleft">
					<router-link to="/cgdd/cgContract">
						<el-button size="mini" class="el-icon-back" type="primary">返回</el-button>
					</router-link>
				</div>
				<div class="headcenter">{{title}}</div>
				<div class="headright">
					<el-button size="mini" type="primary" @click="sendApply" v-show="isShow">发起申请</el-button>
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
					<span class="sx">添加采购</span><span style="color:#409EFF">【{{supplier}}】</span>商品信息,合同号为<span style="color:#409EFF">【{{contractCode}}】</span>。已选择<span style="color:#409EFF">【{{speciesSum}}】</span>种产品,采购总金额为<span style="color:#409EFF">【{{sumPrice}}】</span>
				</div>
				<div class="headright">
					<el-button type="primary" class="el-icon-circle-plus" size="mini" @click="addGoods" v-show="isShow">新增产品</el-button>
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
		</el-container>
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
				title: "采购合同详情",
				restaurants: [],
				labelPosition: "right",
				loading2: true,
				ownerId: "",
				total1: 0,
				dialogTableVisible: false,
				gongid: "",
				isShow:true,
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
				sumPrice: "",
				speciesSum: "",
				contractCode: "",
				supplier: "",
				jzlName: "",
				pzwhName: "",
				biName: "",
				totalPrice: "",
				wncxlName: "",
				dwName: "",
				yplxName: "",
				pgName: "",
				cptyName: "",
				cpbmName: "",
				dialogVisible: false,
				cparray: [],
				supplierId: "",
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
				dataForm: {
					djName: 0,
					slName: 1,
					cpmcname:"",
				},
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
					zhname: [{
							required: true,
							message: '请输入币种',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
					shname: [{
						required: true
					}, ],
					phone: [{
						required: true
					}, ],
					yzbmname: [{
						required: true
					}, ],

				}
			}
		},
		watch: {
			"dataForm.cpmcname": function(val) {
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
		methods: {
			refreshPageRequest1: function(page = 1) {
				//分页刷新
				this.pageRequest1.page = page;
				this.findPage1();
			},
			refreshPageSizeRequest1: function(pageSize) {
				//分页刷新
				this.tableData.pageSize = pageSize;
				this.findPage1();
			},
			handleSelect (item) {
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
					}
				})
			},
			querySearch(queryString, cb) {
				var restaurants = this.restaurants;
				var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
				// 调用 callback 返回建议列表的数据
				cb(results);
			},
			getgongname() {
				//获取药品类型的name
				if(this.gongoption.length != 0) {
					var data = this.gongoption.filter((item) => {
						if(item.id == this.gongid) {
							this.gongname = item.supplierName
							console.log(this.gongname)
						}
					})
				}
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			edit(rows) {},
			//商品列表
			findPage1() {
				this.loading = true;
				this.$api.zsj.get({
					url: "/scm/scmCgProcurementContract/v1/get/" + (this.$route.query.htid?this.$route.query.htid:this.$route.query.id),
				}).then(res => {
					if ( res.state ) {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
						return;
					}
					let newData = res.scmCgContractProductList;
					for(var i = 0; i < newData.length; i++) {
						newData[i].propstate = 0
					}
					this.tableData = newData;
					this.total1 = res.total;
					this.loading = false;
				});
			},
			commmon(index, row) {
				if(row.propstate == 0) {
					this.tableData[index]['propstate'] = 1;
				} else if(row.propstate == 1) {
					var search = {
						"contractId": this.$route.query.htid, //合同id
						"unitPrice": row.unitPrice, //单价
						"productSum": row.productSum, //数量
						"productId": row.productId, //商品id
						"id": row.id
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
							
						}
					});
				}
			},
			goodsMsg() {
				//产品信息
				this.$api.zsj.get({
					url: "/scm/scmCgProcurementContract/v1/get/" + (this.$route.query.htid?this.$route.query.htid:this.$route.query.id),
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
						this.biName = res.currency;
						this.supplierId = res.supplierId;
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
			addGoods() { // 添加商品
				this.cparray=[];
				this.dialogVisible = true
			},
			product() {
				//商品信息
				this.$api.zsj.changeState({
					url: "/scm/scmZsjProduct/v1/downBoxApplySuccess/" + this.ownerId + "/" + this.supplierId
				}).then(res => {
					if(res) {
						this.allArr = res.value
						for(var i = 0; i < res.value.length; i++) {
							let valueMemo = {
								value: res.value[i].productName
							}
							this.cparray.push(valueMemo)
						}
					} else {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
					}
				});
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
							"contractId": (this.$route.query.htid?this.$route.query.htid:this.$route.query.id), //合同id
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
								//this.goodsMsg()
								this.findPage1()
							}
						});
			},
			sendApply() {
				if(!this.$route.query.htid) {
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
							"id": this.$route.query.htid
						}
					}).then(res => {
						if(res.state == false) {
							this.$message({
								message: "操作失败, " + res.shortMessage,
								type: "error",
								duration: 800
							});
						} else {
							this.$message({
								message: "申请成功",
								type: "success",
								duration: 800
							});
							this.$router.push({
									path: '/cgdd/cgContract',
							})
						}
					});
				}
			},
			loadAll(){
				return this.cparray;
			}
		},
		mounted() {
			this.restaurants = this.loadAll();
			this.ownerId = storage.get('ownerId');
			this.goodsMsg();
			this.findPage1();
			//采购合同数据详情
			if(this.$route.query.id) {
				this.isShow=false;
				this.btnType = true;
				this.$api.zsj.changeState({
					url: "/scm/scmCgProcurementContract/v1/get/" + this.$route.query.id,
				}).then(res => {
					if(res) {
						if(res.supplier) {
							this.wrapForm.jfdwname = res.supplier
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
						this.tableData = res.scmCgContractProductList;
					} else {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
					}
				});
			}

			//采购合同数据编辑
			if(this.$route.query.htid) {
				this.isShow=true;
				this.title = "采购合同编辑"
				this.btnShow = true
				this.$api.zsj.changeState({
					url: "/scm/scmCgProcurementContract/v1/get/" + this.$route.query.htid,
				}).then(res => {
					if(res) {
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
		height: 42px;
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
	
	.headcenter{
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
</style>