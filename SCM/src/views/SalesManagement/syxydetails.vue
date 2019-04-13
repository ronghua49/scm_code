<template>
	<div>
		<el-container>
			<div class="searchHeader">
				<div class="headleft">
					<router-link to="syxyhzmdManagement">
						<el-button size="mini" class="el-icon-back" type="primary">返回</el-button>
					</router-link>
				</div>
				<div class="headcenter">{{title}}</div>
				<div class="headright">
					<el-button size="mini" type="primary" @click="saveApply" v-show="isShow">申请暂存</el-button>
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
					<el-form-item prop="jfdwname" label="商务大区">
						<el-input v-model="wrapForm.swdq" :disabled="true"></el-input>
					</el-form-item>
				</el-form>
			</el-main>
			<div class="header">
				<div class="headleft">
					<span class="sx"><span class="el-icon-tickets"></span><span class="sx">选择</span><span style="color:#409EFF">【{{wrapForm.swdq}}】</span>的经销商,共<span style="color:#409EFF">【{{total1}}】</span>家</span>
				</div>
				<div class="headright">
				</div>
			</div>
			<!--table-->
<el-table v-loading="loading" element-loading-text="加载中" :data="tableData" size="mini" border style="width: 100%">
					<el-table-column type="index" label="序号" width="100" align="center">
				    </el-table-column>
					<el-table-column v-for="item in column1" :key="item.prop" :prop="item.prop" :label="item.label" :formatter="item.formatter" :min-width="item.minwidth" :align="item.align">
					</el-table-column>
					<el-table-column  :label="ltime" width="300" align="center">
						<el-table-column prop="shipments" label="发货量" width="100" align="center">
						</el-table-column>
						<el-table-column  prop="allocating" label="调拨量" width="100" align="center">
						</el-table-column>
						<el-table-column prop="sales" label="纯销量" width="100" align="center">
						</el-table-column>
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
							  	<el-checkbox :checked="tableData[scope.$index].isSign == 1" :disabled="isTrue" @change="changeBox(scope.$index)"></el-checkbox>
				     		   </template>
						</el-table-column>
					</el-table-column>
				    <el-table-column
				      fixed="right"
				      label="选择分类"
				      align="center"
				      width="180">
				      <template slot-scope="scope">
				     		<el-select v-model="tableData[scope.$index].type" clearable placeholder="请选择" @change="getswdqid" :disabled="isTrue" style="width: 100%;">
									<el-option v-for="item in floption" :key="item.index" :label="item.name" :value="item.name">
									</el-option>
							</el-select>
				      </template>
				    </el-table-column>
				    <el-table-column
				      fixed="right"
				      label="申请备注"
				      align="center"
				      width="180">
				      <template slot-scope="scope">
							<el-input v-model="tableData[scope.$index].memo" :disabled="isTrue"></el-input>
				      </template>
				     </el-table-column>
				</el-table>
		</el-container>
	</div>
</template>

<script>
	import storage from '@/utils/storage'
	export default {
		name: "syxydetails",
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
				btnType: false,
				title: "商业协议合作名单管理详情",
				restaurants: [],
				labelPosition: "right",
				loading2: true,
				isShow:true,
				ownerId: "",
				total1: 0,
				dialogTableVisible: false,
				gongid: "",
				btnShow: false,
				floption:[{"index":"0","name":"经销商"},
						  {"index":"1","name":"两票制新增经销商"},
						  {"index":"2","name":"终止认可"},
						  {"index":'3',"name":"转分销商"}],
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
				isTrue:true,
				checked: false,
				loading: false,
				sumPrice: "",
				speciesSum: "",
				contractCode: "",
				saveid:'',
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
				cparray: [],
				supplierId: "",
				swdqid:'',
				ltime:"",
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
					swdq: '',
				},
				wraprules: {
					swdq: [{
						required: true
					}, ],
				}
			}
		},
		methods: {
			changeBox(index){
				if(this.tableData[index].isSign == 0){
					this.tableData[index].isSign = 1;
				} else {
					this.tableData[index].isSign = 0;
				}
			},
			getswdqid() {
				//获取分类下标
				if(this.swdqoption.length !== 0) {
					var data = this.swdqoption.filter((item) => {
						if(item.businessDivision == this.wrapForm.swdqname) {
							this.swdqid = item.id
						}
					})
				}
			},
			sytimer(){
				let myDate = new Date();
				let newTime= myDate.getFullYear();
				this.ltime= (Number(newTime)-2) +".11至"+(Number(newTime)-1) +".7";
			},
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
			saveApply(){
				let saveData=this.tableData
				for(var i=0;i<saveData.length;i++ ){
					delete saveData[i].id
					if(saveData[i].type=="经销商"){
						saveData[i].type="0"
					}else if(saveData[i].type=="两票制新增经销商"){
						saveData[i].type="1"
					}else if(saveData[i].type=="终止认可"){
						saveData[i].type="2"
					} else if(saveData[i].type=="转分销商"){
						saveData[i].type="3"
					}
				}
				if(!this.swdqid) {
					this.$message({
						message: "请选择商务大区 ",
						type: "error",
						duration: 1000
					});
					return false
				} else {
					if(saveData.length==0){
						this.$message({
						message: "没有经销商无法发起申请 ",
						type: "error",
						duration: 1000
						});
					return false
					}
					this.$api.zsj.getZsj({
						url: "/scm/scmXsAgreementListMaster/v1/save",
						data: {
							"businessDivisionId": this.$route.query.businessDivisionId,
							"ownerId":this.ownerId,
							'businessDivision':this.$route.query.businessDivision,
							"scmXsAgreementListList":saveData,
							"id":this.saveid,
							"approvalState":this.$route.query.approvalState,
							"masterCode":this.$route.query.masterCode,
							"isSign":this.$route.query.isSign,
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
								message: "暂存成功",
								type: "success",
								duration: 800
							});
							this.saveid=res.value.id
						}
					});
				}
			},
			goodsMsg() {
				//详情
			if(this.$route.query.lookid) {
				this.isShow=false
				this.$api.zsj.changeState({
					url: "/scm/scmXsAgreementListMaster/v1/get/" + this.$route.query.lookid,
				}).then(res => {
					if(res) {
						this.btnType = true;
						this.wrapForm.swdq=this.$route.query.businessDivision;
						this.swdqid=this.$route.query.businessDivisionId;
						this.total1=res.scmXsAgreementListList.length;
						let newData=res.scmXsAgreementListList;
						for (var i=0;i<newData.length;i++){
							newData[i].isSign="0"
						}
						this.tableData =newData;
					} else {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
					}
				});
			}
			//编辑
			if(this.$route.query.editid) {
				this.isTrue=false
				this.isShow=true
				this.title = "商业协议合作名单管理编辑"
				this.btnShow = true
				this.loading = true;
					this.$api.zsj.changeState({
					url: "/scm/scmXsAgreementList/v1/updateAgreementlist/"+this.$route.query.editid+"/"+this.ownerId+"/"+this.$route.query.businessDivisionId,
				}).then(res => {
					if(res) {
						this.btnType = true;
						this.wrapForm.swdq=this.$route.query.businessDivision;
						this.swdqid=this.$route.query.businessDivisionId;
						this.total1=res.value.length;
						let newData=res.value;
						for(var i=0;i<newData.length;i++){
							if(!newData[i].memo){
								newData[i].memo="";
							}
							newData[i].memo=newData[i].memo;
							newData[i].type="";
							newData[i].type=newData[i].commerceLevel;
							newData[i].isSign= 0
						}
						this.loading = false;
						this.tableData=newData;
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
			sendApply() {
				if(!this.$route.query.lookid) {
					let sendData=this.tableData
					for(var i=0;i<sendData.length;i++ ){
						delete sendData[i].id
						if(sendData[i].type==""){
							this.$message({
								message: "请把分类选择完毕之后申请 ",
								type: "error",
								duration: 1000
							});
							return false
						}
						if(sendData[i].type=="经销商"){
							sendData[i].type="0"
						}else if(sendData[i].type=="两票制新增经销商"){
							sendData[i].type="1"
						}else if(sendData[i].type=="终止认可"){
							sendData[i].type="2"
						} else if(sendData[i].type=="转分销商"){
							sendData[i].type="3"
						}
					}
					if(sendData.length==0){
						this.$message({
							message: "没有经销商无法发起申请",
							type: "error",
							duration: 1000
						});
						return false
					}
					this.$api.zsj.getZsj({
						url: "/scm/scmXsAgreementListMaster/v1/sendApply",
						data: {
							"businessDivisionId": this.$route.query.businessDivisionId,
							"ownerId":this.ownerId,
							'businessDivision':this.$route.query.businessDivision,
							"scmXsAgreementListList":sendData,
							"id":this.$route.query.editid,
						}
					}).then(res => {
						if(res.state == false) {
							this.$message({
								message: "操作失败, " + res.message,
								type: "error",
								duration: 800
							});
						} else {
							this.oneShow=false
							this.three = false;
							this.fourShow=false;
							this.twoShow=false;
							this.threeShow=false;
							this.active=3;
							sendData=[];
							this.quName=res.value.businessDivision
							this.orderCode=res.value.masterCode;
							this.totNum=res.value.scmXsAgreementListList.length;
							setTimeout(() => {
								this.four = true
							}, 220)
							this.$message({
								message: "申请成功",
								type: "success",
								duration: 800
							});
							this.$router.push({ path:'/fhgl/syxyhzmdManagement'})
						}
					});
				} else if(!this.$route.query.editid){
					let sendData=this.tableData.filter((item)=>item.isSign=1)
					for(var i=0;i<sendData.length;i++ ){
						delete sendData[i].id
						if(sendData[i].type==""){
							this.$message({
								message: "请把分类选择完毕之后申请 ",
								type: "error",
								duration: 1000
							});
							return false
						}
						if(sendData[i].type=="经销商"){
							sendData[i].type="0"
						}else if(sendData[i].type=="两票制新增经销商"){
							sendData[i].type="1"
						}else if(sendData[i].type=="终止认可"){
							sendData[i].type="2"
						} else if(sendData[i].type=="转分销商"){
							sendData[i].type="3"
						}
					}
					if(sendData.length==0){
						this.$message({
							message: "请选择年度之后暂存 ",
							type: "error",
							duration: 1000
						});
						return false
					}
					this.$api.zsj.getZsj({
						url: "/scm/scmXsAgreementListMaster/v1/sendApply",
						data: {
							"businessDivisionId": this.$route.query.editid,
							"ownerId":this.ownerId,
							'businessDivision':this.wrapForm.swdqname,
							"scmXsAgreementListList":sendData,
							"id":this.saveid,
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
							this.$router.push({ path:'/fhgl/syxyhzmdManagement'})
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
			this.sytimer();
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
