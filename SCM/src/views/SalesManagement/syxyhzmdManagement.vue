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
				<el-form-item label="年度">
					<el-select v-model="ndname" clearable placeholder="请选择年度">
						<el-option v-for="item in ndoption" :key="item.year" :label="item.year" :value="item.year">
						</el-option>
					</el-select>
				</el-form-item>
					<el-form-item label="商务分区">
						<el-select v-model="swfqname" clearable placeholder="请选择商务分区" @change="getswfqname">
							<el-option v-for="item in swfqoption" :key="item.id" :label="item.businessDivision" :value="item.businessDivision" >
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="商业名称">
						<el-input v-model="syName" placeholder="请输入商务分区" clearable></el-input>
					</el-form-item>
			</el-form>
			<div class="header">
				<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">数据列表</span></div>
				<div class="headright">
					<el-button type="primary" class="el-icon-circle-plus" size="mini" @click="buy">商业协议合作名单申请</el-button>
				</div>
			</div>
			<!--table-->
			<el-table v-loading="loading2" element-loading-text="加载中" :data="tableData3" size="mini"  border style="width:100%;">
				<el-table-column type="index" label="序号" width="100" align="center">
				</el-table-column>
				<el-table-column  prop="isSign" label="签约状态"  width="100" align="center">
					<template slot-scope="scope">
					  	  <span v-show="scope.row.isSign==0" class='el-icon-close' style="font-size: 20px;"></span>
					  	  <span v-show="scope.row.isSign==1" class='el-icon-check' style="font-size: 20px;"></span>
		     		</template>
				</el-table-column>
				<el-table-column v-for="item in column" :key="item.prop" :prop="item.prop" :label="item.label" :formatter="item.formatter" :width="item.minwidth" :align="item.align">
				</el-table-column>
				<el-table-column  fixed="right" label="操作" align="center" min-width="200">
					<template slot-scope="scope">
						<el-button class="el-icon-view" type="primary" @click="toDetail(scope.row)" size="mini">查看详情</el-button>
						<el-button class="el-icon-edit" type="primary" @click="btnedit(scope.row)" :disabled="scope.row.approvalState!=0" size="mini">编辑</el-button>
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
				<div class="headcenter" :class="{isLeft:isleft}">商业协议合作名单申请</div>
				<div class="headright">
					<el-button size="mini" type="primary" v-show="oneShow" @click="nextStep()">下一步</el-button>
					<el-button size="mini" type="primary" v-show="twoShow" @click="prevStep">上一步</el-button>
					<el-button size="mini" type="primary" v-show="threeShow" @click="saveApply">申请暂存</el-button>
					<el-button size="mini" type="primary" v-show="fourShow" @click="sendApply">发起申请</el-button>
				</div>

			</div>
			<div>
				<el-steps :align-center = "true" :active="active" finish-status="success" style="margin: 35px auto;width:100%">
					<el-step title="选择经销商"></el-step>
					<el-step title="修改商业信息"></el-step>
					<el-step title="完成"></el-step>
				</el-steps>
			</div>	
		</div>
		<transition name="el-fade-in">
			<div v-show="two">
				<div class="content-item">
					<div class="header">
						<div class="headleft"><span class="el-icon-search"></span><span class="sx">选择经销商</span></div>
					</div>
					<el-row>
						<el-form :model="wrapForm" :rules="wraprules" ref="wrapForm1" :label-position="labelPosition" label-width="120px" size="small" class="demo-ruleForm" style="display: flex;justify-content: center;margin-top: 80px;margin-bottom: 300px;">
							<el-form-item label="商务大区" prop="swdqname">
								<el-select v-model="wrapForm.swdqname" clearable placeholder="请选择商务大区" @change="getswdqid">
									<el-option v-for="item in swdqoption" :key="item.id" :label="item.businessDivision" :value="item.businessDivision">
									</el-option>
								</el-select>
							</el-form-item>
						</el-form>
					</el-row>
				</div>
			</div>
		</transition>
		<!--第二步-->
		<transition name="el-fade-in">
			<div class="content-item" v-show="three">
				<div class="header">
					<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">选择</span><span style="color:#409EFF">【{{wrapForm.swdqname}}】</span>的经销商,共<span style="color:#409EFF">【{{total1}}】</span>家</div>
					<div class="headright">
					</div>
				</div>
				<el-table v-loading="loading" element-loading-text="加载中" :data="tableData" size="mini" border style="width: 100%">
					<el-table-column type="index" label="序号" min-width="80"  align="center">
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
							  	  <span v-show="scope.row.lastYear==0" class='el-icon-close' style="font-size:20px"></span>
							  	  <span v-show="scope.row.lastYear==1" class='el-icon-check' style="font-size:20px"></span>
				     		</template>
						</el-table-column>
						<el-table-column prop="isSign" label="本年度" width="100" align="center">
							  <template slot-scope="scope">
							  	<el-checkbox :checked="tableData[scope.$index].isSign == 1" @change="changeBox(scope.$index)"></el-checkbox>
				     		   </template>
						</el-table-column>
					</el-table-column>
				    <el-table-column
				      fixed="right"
				      label="选择分类"
				      align="center"
				      width="180">
				      <template slot-scope="scope">
				     		<el-select v-model="tableData[scope.$index].type1" clearable placeholder="请选择" @change="getswdqid">
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
							<el-input v-model="tableData[scope.$index].memo" ></el-input>
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
					<span style="color: #409EFF;">【{{quName}}】</span>经销商协议合作名单申请<span style="color: #409EFF;">【{{orderCode}}】</span>
				</div>
				<div class="success-bottom">
					共<span style="color: #409EFF;">【{{totNum}}】</span>家经销商
				</div>
			</div>
		</transition>
	</div>
</template>
<script>
	import storage from '@/utils/storage'
	export default {
		name: "syxyhzmdMananement",
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
						label: "流程状态",
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
						prop: "year",
						label: "年度",
						minwidth: "200",
						align: "center"
					},
					{
						prop: "commerceCode",
						label: "商业编码",
						minwidth: "200",
						align: "center"
					},
					{
						prop: "commerceName",
						label: "商业名称",
						minwidth: "200",
						align: "center"
					},
					{
						prop: "acceptState",
						label: "商业状态",
						minwidth: "100",
						align: "center"
					},
					{
						prop: "type",
						label: "商业分类",
						minwidth: "100",
						align: "center"
					},
					{
						prop: "memo",
						label: "申请备注",
						minwidth: "200",
						align: "center"
					},
				],
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
				quName:'',
				orderCode:'',
				totNum:'',
				ltime:"",
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
				twoShow: false,
				fourShow: false,
				threeShow:false,
				restaurants: [],
				cparray: [],
				typearr: [],
				allArr: [],
				supplier: '',
				sumPrice: '',
				labelPosition: "right",
				loading2: true,
				loading: true,
				ownerId: "",
				saveid:"",
				total: 0,
				total1: 0,
				operation: true,
				swdqid: "",
				swfqid:'',
				ndoption: [],
				swfqoption: [],
				swdqoption: [],
				tableData3: [],
				sendData:[],
				isleft: false,
				floption:[{"index":"0","name":"经销商"},
						  {"index":"1","name":"两票制新增经销商"},
						  {"index":"2","name":"终止认可"},
						  {"index":'3',"name":"转分销商"}],
				tableData: [],
				bzoption: [{
					"name": "CNY",
					"inx": 1
				}],
				checked: false,
				speciesSum: '',
				contractCode: '',
				ndname: '',
				swfqname:'',
				syName:'',
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
				wrapForm: {
					swdqname: '',
				},
				wraprules: {
					swdqname: [{
							required: true,
							message: '请选择商务大区',
							trigger: 'chanage'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'change'
						}
					],
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
			getswfqname() {
				//获取分区id
				if(this.swfqoption.length != 0) {
					var data = this.swfqoption.filter((item) => {
						if(item.businessDivision== this.swfqname) {
							this.swfqid = item.id
						}
					})
				}
			},
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
			getswdqid() {
				//获取分类下标
				if(this.swdqoption.length != 0) {
					var data = this.swdqoption.filter((item) => {
						if(item.businessDivision == this.wrapForm.swdqname) {
							this.swdqid = item.id
						}
					})
				}
			},
			resetForm(formName) {
				//清空表单
				this.$refs[formName].resetFields();
			},
			toDetail: function(row){
				this.$router.push({ path:'/fhgl/syxydetails',query:{lookid:row.masterId,businessDivisionId:row.businessDivisionId,businessDivision:row.businessDivision} })
			},
			btnedit(row){
				this.$router.push({ path:'/fhgl/syxydetails',query:{editid:row.masterId,businessDivisionId:row.businessDivisionId,businessDivision:row.businessDivision,approvalState:row.approvalState,masterCode:row.masterCode,isSign:row.isSign} })
			},
			searchquery() {
				//列表查询
				if(this.ndname) {
					this.search.push({
						property: "year",
						value: this.ndname,
						operation: "EQUAL",
						relation: "AND"
					})
				}
				if(this.swfqid) {
					this.search.push({
						property: "businessDivisionId",
						value: this.swfqid,
						operation: "EQUAL",
						relation: "AND"
					})
				}
				if(this.syName) {
					this.search.push({
						property: "commerceName",
						value: this.syName,
						operation: "LIKE",
						relation: "AND"
					})
				}
				this.findPage(this.search)
			},
			//商业协议名单申请列表展示
			findPage(search) {
				this.loading = true;
				this.$api.zsj.getZsj({
					url: "/scm/scmXsAgreementList/v1/masterList",
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
			//商务大区下的经销商
			findPage1() {
				this.loading = true;
				this.$api.zsj.getZsj({
					url: "/scm/scmXsAgreementList/v1/addAgreementlist",
					data: {
						pageBean: this.pageRequest,
						params: {},
						querys: [{
							hasInitValue: false,
							operation: "EQUAL",
							relation: "AND",
							value: this.swdqid,
							property: "businessDivisionId"
						},
						],
						sorter: [{
							"direction": "ASC",
							"property": "commerceId",
						}]
					},
				}).then(res => {
					if(res) {
						let newData = res.rows;
						for(var i=0;i<newData.length;i++){
//							newData[i].commerceFirstId="";
							newData[i].memo="";
							newData[i].type="";
							newData[i].isSign= 0
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
						this.active = 1;
						this.one = true;
						this.two = false;
						this.three=true;
						this.loading=false;
						this.oneShow=false;
						this.twoShow=true;
						this.threeShow = true;
						this.fourShow=true;
						this.findPage1();
						this.sytimer();
						this.isleft=true;
					}
				})
			},
			prevStep() {
				//上一步
				this.three = false;
				this.isleft = false;
				this.active = 0;
				setTimeout(() => {
					this.one = true;
					this.two = true;
					this.oneShow = true;
					this.twoShow = false;
					this.fourShow = false;
					this.threeShow = false;
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
				this.threeShow = false;
				this.three = false;
				this.resetForm('wrapForm1');
				this.id = "";
				this.saveid="";
				this.four=false;
				this.active=0;
				this.isleft=false;
			},
			sendApply() {
					let sendData=this.tableData
					for(var i=0;i<sendData.length;i++ ){
						if(sendData[i].type==""){
							this.$message({
								message: "请把列表分类选择完毕后在发起申请",
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
				if(!this.swdqid) {
					this.$message({
						message: "请选择商务大区 ",
						type: "error",
						duration: 1000
					});
					return false
				} else {
					if(sendData.length==0){
						this.$message({
							message: "没有经销商无法发起申请 ",
							type: "error",
							duration: 1000
						});
						return false
					}
					this.$api.zsj.getZsj({
						url: "/scm/scmXsAgreementListMaster/v1/sendApply",
						data: {
							"businessDivisionId": this.swdqid,
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
							this.oneShow=false;
							this.three = false;
							this.fourShow=false;
							this.twoShow=false;
							this.threeShow=false;
							this.active=3;
							sendData=[];
							this.quName=res.value.businessDivision;
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
						}
					});
				}
			},
			saveApply(){
				let saveData=this.tableData
				for(var i=0;i<saveData.length;i++ ){
					if(saveData[i].type1=="经销商"){
						saveData[i].type="0"
					}else if(saveData[i].type1=="两票制新增经销商"){
						saveData[i].type="1"
					}else if(saveData[i].type1=="终止认可"){
						saveData[i].type="2"
					} else if(saveData[i].type1=="转分销商"){
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
							"businessDivisionId": this.swdqid,
							"ownerId":this.ownerId,
							'businessDivision':this.wrapForm.swdqname,
							"scmXsAgreementListList":saveData,
							"id":this.saveid,
						}
					}).then(res => {
						if(res.state == false) {
							this.$message({
								message: "操作失败, " + res.message,
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
			sytimer(){
				let myDate = new Date();
				let newTime= myDate.getFullYear();
				this.ltime= (Number(newTime)-2) +".11至"+(Number(newTime)-1) +".7";
			},
		},
		mounted() {
			this.restaurants = this.loadAll();
			this.ownerId = storage.get('ownerId')
			this.findPage();
			//年度
			this.$api.zsj.changeState({
				url: "/scm/scmXsAgreementList/v1/getYears/" + this.ownerId,
			}).then(res => {
				if(res) {
					this.ndoption = res.value
				} else {
					this.$message({
						message: "操作失败, " + res.shortMessage,
						type: "error",
						duration: 800
					});
				}
			});
			//商务分区
			this.$api.common.getDivision({
					url: "/scm/scmZdBusinessDivision/v1/list",
					data: {}
			}).then(res => {
				if(res.state == false) {
					this.$message({
						message: "操作失败, " + res.shortMessage,
						type: "error",
						duration: 800
					});
				} else {
					this.swfqoption=res
				}
			});
			//商务大区
			this.$api.common.getDivision({
					url: "/scm/scmZdBusinessDivision/v1/list",
					data: {}
			}).then(res => {
				if(res.state == false) {
					this.$message({
						message: "操作失败, " + res.shortMessage,
						type: "error",
						duration: 800
					});
				} else {
					this.swdqoption=res
				}
			});

		},
	}
</script>
<style scoped="scoped" lang="scss">
	.isLeft{
		padding-left: 207px !important;
		box-sizing: border-box;
	}
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
		font-size: 24px;
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
		width: 490px;
		height: 50px;
		border: 1px solid #f2f2f2;
		margin: 0 auto;
		margin-top: 150px;
		display: flex;
		justify-content: center;
		align-items: center;
		white-space: nowrap;
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
	th{
		background: none !important;
	}
</style>
