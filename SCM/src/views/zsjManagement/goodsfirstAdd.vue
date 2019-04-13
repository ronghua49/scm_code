<template>
	<div>
		<el-row class="searchHeader">
			<el-col :span="1">
				<router-link to="/zsj/goodsfirst">
					<el-button icon="el-icon-back" size="mini" type="primary" @click="isIndex=!isIndex">返回</el-button>
				</router-link>
			</el-col>
			<el-col :span="4" :offset="8"><b>商品首营申请</b></el-col>
			<el-col :span="11" style="padding-left:10px;text-align: right">
				<el-button size="mini" type="primary" @click="sqsave">申请暂存</el-button>
				<el-button size="mini" type="primary" @click="sendapply">发起申请</el-button>
			</el-col>
		</el-row>
		<el-row class="searchHeader" style="margin-top:10px;margin-bottom: 10px;">
			<el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>基础信息</el-col>
		</el-row>
		<el-form :model="ruleForm" :rules="rules" ref="ruleForm1" :label-position="labelPosition" size="small" label-width="120px" class="demo-ruleForm">
			<el-form-item label="产品名称">
				<el-row class="demo-autocomplete">
					<el-autocomplete class="inline-input" :disabled="goodsType1" v-model="addcpmcname" clearable :fetch-suggestions="querySearch1" placeholder="请输入产品名称" @select="handleSelect"></el-autocomplete>
				</el-row>
			</el-form-item>
			<el-form-item prop="addcpxlid" label="产品系列">
				<el-select v-model="ruleForm.addcpxlid" :disabled="goodsType" placeholder="请选择产品系列" @change="getaddcpxiname">
					<el-option v-for="(item,index) in addcpxioption" :key="index" :label="item.productLineName" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item prop="addcpbmname" label="产品编码">
				<el-input v-model="ruleForm.addcpbmname" :disabled="goodsType" clearable placeholder="请输入产品编码"></el-input>
			</el-form-item>
			<el-form-item prop="addwlbmname" label="物料编码">
				<el-input v-model="ruleForm.addwlbmname" :disabled="goodsType" clearable placeholder="请输入物料编码"></el-input>
			</el-form-item>
			<el-form-item prop="addtymcname" label="通用名称">
				<el-input v-model="ruleForm.addtymcname" :disabled="goodsType" clearable placeholder="请输入通用名称"></el-input>
			</el-form-item>
			<el-form-item prop="addchdmname" label="存货代码">
				<el-input v-model="ruleForm.addchdmname" :disabled="goodsType" clearable placeholder="请输入存货代码"></el-input>
			</el-form-item>
			<el-form-item prop="addjxid" label="剂型">
				<el-select v-model="ruleForm.addjxid" :disabled="goodsType" clearable placeholder="请选择剂型" @change="getaddjxname">
					<el-option v-for="item in addjxoption" :key="item.id" :label="item.agentType" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item prop="addggname" label="规格">
				<el-input v-model="ruleForm.addggname" :disabled="goodsType" clearable placeholder="请输入规格"></el-input>
			</el-form-item>
			<el-form-item prop="adddwname" label="单位">
				<el-input v-model="ruleForm.adddwname" :disabled="goodsType" clearable placeholder="请输入单位"></el-input>
			</el-form-item>
			<el-form-item prop="addotcname" label="OTC">
				<el-select v-model="ruleForm.addotcname" clearable :disabled="goodsType" placeholder="请选择OTC">
					<el-option v-for="item in otcoption" :key="item.inx" :label="item.classification" :value="item.classification">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item prop="addyplxid" label="药品类型">
				<el-select v-model="ruleForm.addyplxid" clearable :disabled="goodsType" placeholder="请选择药品类型" @change="getaddyplxname">
					<el-option v-for="item in addyplxoption" :key="item.id" :label="item.medicineType" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item prop="addypflid" label="药品分类">
				<el-select v-model="ruleForm.addypflid" clearable :disabled="goodsType" placeholder="请选择药品分类" @change="getaddypflname">
					<el-option v-for="item in addypfloption" :key="item.code" :label="item.medicineClassify" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item prop="addzctjname" label="贮存条件">
				<el-select v-model="ruleForm.addzctjname" clearable :disabled="goodsType" placeholder="请选择贮存条件" @change="getaddzctjname">
					<el-option v-for="item in addzctjoption" :key="item.suggestWarehouse" :label="item.keepingCondition" :value="item.keepingCondition">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item prop="gongname" label="供应商名称">
				<el-select v-model="ruleForm.gongname" clearable placeholder="请选择供应商名称" @change="gysChange(ruleForm.gongname)">
					<el-option v-for="item in gongoption" :key="item.id" :label="item.supplierName" :value="item.supplierName">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item prop="addsccsname" label="生产厂商">
				<el-select :disabled="goodsType" clearable v-model="ruleForm.addsccsname" placeholder="请选择生产厂商">
					<el-option v-for="item in addsccsoption" :key="item.id" :label="item.manufacturer" :value="item.manufacturer">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item prop="addjykcname" label="建议库存">
				<el-input v-model="ruleForm.addjykcname" clearable :disabled="true" placeholder="请先选择贮存条件 "></el-input>
			</el-form-item>
			<el-form-item>
				<el-checkbox v-model="checked" :disabled="goodsType">追溯码</el-checkbox>
			</el-form-item>
			<el-form-item label="件装量" style="width:100%;text-align:left">
				<el-tag
				style="margin-right:10px;"
				:key="tag"
				type="info"
				v-for="tag in taglist"
				>{{tag}} 盒/件</el-tag>
			</el-form-item>
		</el-form>
		<el-row class="searchHeader" style="margin-top:10px;margin-bottom: 10px;">
			<el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>基础信息</el-col>
		</el-row>
		<div class="wrap">
			<el-form :model="dataForm" :rules="datarules" ref="dataForm1" size="small" :label-position="labelPosition" label-width="120px" class="demo-ruleForm">
				<el-form-item prop="zczhname" label="批准文号/注册证号">
					<el-input v-model="dataForm.zczhname" :disabled="detailType" clearable placeholder="请输入批准文号/注册证号"></el-input>
				</el-form-item>
				<el-form-item prop="zcwhDate" label="批准文号/注册证号有效期至">
					<el-date-picker v-model="dataForm.zcwhDate" type="date" :disabled="detailType" clearable value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" placeholder="选择日期">
					</el-date-picker>
				</el-form-item>
				<el-form-item prop="jyfwname" label="经营范围">
					<el-select v-model="dataForm.jyfwname" :disabled="detailType" placeholder="请选择经营范围">
						<el-option v-for="item in jyfwoptions" :key="item.id" :label="item.managementScope" clearable :value="item.managementScope">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item prop="gmpbook" label="GMP证书">
					<el-input v-model="dataForm.gmpbook" clearable :disabled="detailType" placeholder="请输入GMP证书"></el-input>
				</el-form-item>
				<el-form-item prop="gpmdate" label="GMP证书有效期至">
					<el-date-picker v-model="dataForm.gpmdate" type="date" :disabled="detailType" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" clearable placeholder="选择日期">
					</el-date-picker>
				</el-form-item>
				<el-form-item prop="qyphCode" label="启用批号">
					<el-input v-model="dataForm.qyphCode" :disabled="detailType" clearable placeholder="请输入启用批号"></el-input>
				</el-form-item>
				<el-form-item prop="zlbzname" label="质量标准">
					<el-input v-model="dataForm.zlbzname" :disabled="detailType" clearable placeholder="请输入质量标准"></el-input>
				</el-form-item>
			</el-form>
		</div>
		<el-row class="searchHeader" style="margin-top:10px;margin-bottom:10px;">
			<el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>物理特性</el-col>
		</el-row>
		<div class="wrap">
			<el-form :model="wrapForm" :rules="wraprules" size="small" ref="wrapForm1" :label-position="labelPosition" label-width="120px" class="demo-ruleForm">
				<el-form-item prop="longname" label="长(厘米)">
					<el-input v-model="wrapForm.longname" :disabled="detailType" clearable placeholder="请输入内容"></el-input>
				</el-form-item>
				<el-form-item prop="kname" label="宽(厘米)">
					<el-input v-model="wrapForm.kname" :disabled="detailType" clearable placeholder="请输入内容"></el-input>
				</el-form-item>
				<el-form-item prop="gname" label="高(厘米)">
					<el-input v-model="wrapForm.gname" :disabled="detailType" clearable placeholder="请输入内容"></el-input>
				</el-form-item>
				<el-form-item prop="tjname" label="体积(立方米)">
					<el-input v-model="wrapForm.tjname" :disabled="detailType" clearable placeholder="请输入内容"></el-input>
				</el-form-item>
				<el-form-item prop="mlname" label="毛重(kg)">
					<el-input v-model="wrapForm.mlname" :disabled="detailType" clearable placeholder="请输入内容"></el-input>
				</el-form-item>
				<el-form-item prop="wxdmname" label="外箱条码">
					<el-input v-model="wrapForm.wxdmname" :disabled="detailType" clearable placeholder="请输入内容"></el-input>
				</el-form-item>
				<el-form-item prop="xhdmname" label="小盒条码">
					<el-input v-model="wrapForm.xhdmname" :disabled="detailType" clearable placeholder="请输入内容"></el-input>
				</el-form-item>
			</el-form>
		</div>
		<el-row class="searchHeader" style="margin-top:10px;">
			<el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>基础信息</el-col>
			<el-col :span="21" style="text-align:right;">
				<el-button type="primary" size="mini">上传附件</el-button>
			</el-col>
		</el-row>
		<!--table-->
		<el-table :data="tableData3" size="mini" border style="width: 100%">
			<!--<el-table-column
			      prop="productName"
			      label="产品名称">
			      <template slot-scope="scope">
			      	 <el-popover
					    placement="top-start"
					    title="产品名称"
					    width="200"
					    trigger="hover"
					    :content="scope.row.productName">
					    <div slot="reference">{{scope.row.productName.length>3?scope.row.productName.slice(0,3)+"...":scope.row.productName}}</div>
					  </el-popover>
			      </template>
			    </el-table-column>-->
			<!--<el-table-column v-for="item in column"
						:key="item.prop"
			      :prop="item.prop"
			      :label="item.label"
			      :width="item.minwidth"
			      :align="item.align">
			    </el-table-column>-->
			<!--<el-table-column
			      fixed="right"
			      label="操作"
			      width="100">
			      <template slot-scope="scope">
			        <el-button type="text" size="small" @click="edit(scope.row)">编辑</el-button>
			      </template>
			    </el-table-column>-->
		</el-table>
		<div class="pagination">
			<el-pagination @current-change="refreshPageRequest" @size-change="refreshPageSizeRequest" background :current-page="pageRequest.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest.pageSize" layout="sizes, total, prev, pager, next" :total="total">
			</el-pagination>
		</div>
	</div>
</template>

<script>
	import storage from '@/utils/storage'
	export default {
		name: "goods",
		data() {
			var checkAge = (rule, value, callback) => {
				if(!value) {
					return callback(new Error('请输入参数'));
				}
				if(value.length > 10) {
					return callback(new Error('输入参数长度不能大于10位'));
				}
				setTimeout(() => {
					if(!Number(value)) {
						callback(new Error('请输入数字值'));
					}
				}, 1000);
			};

			return {
				column: [],
				taglist:[],//件装量
				showType: true,
				goodsType: false,
				goodsType1: false,
				detailType: false,
				restaurants: [],
				labelPosition: "right",
				jyid: '',
				ypid: '',
				cpid: "",
				jyname: '',
				ypname: '',
				cpname: '',
				wlbmname: "",
				chdmcname: "",
				addcpmcname: "",
				total: 0,
				operation: true,
				dialogTableVisible: false,
				options: [],
				ypoption: [],
				cpoption: [],
				gongoption: [],
				goodsid: '',
				addcpxioption: [],
				addjxoption: [],
				addyplxoption: [],
				addypfloption: [],
				addzctjoption: [],
				addsccsoption: [],
				jyfwoptions: [],
				tableData3: [],
				otcoption: [{
					"classification": "甲类",
					"inx": "1"
				}, {
					"classification": "乙类",
					"inx": "2"
				}, {
					"classification": "无",
					"inx": "3"
				}, ],
				checked: false,
				loading: false,
				cpbmname: '',
				cpmcname: '',
				addcpxlname: "",
				addjxname: "",
				addyplxname: "",
				addypflname: "",
				addzctjname: "",
				addzsm: "0",
				editid: "",
				search: [],
				valid1: false,
				valid2: false,
				valid3: false,
				ownerId: '',
				cparray: [],
				allArr: [],
				typearr: [],
				pageRequest: {
					page: 1,
					pageSize: 10,
					showTotal: true,
				},

				dataForm: {
					zczhname: '',
					zcwhDate: '',
					jyfwname: '',
					gmpbook: '',
					gpmdate: '',
					qyphCode: '',
					zlbzname: '',
				},
				wrapForm: {
					longname: '',
					kname: '',
					gname: '',
					tjname: '',
					mlname: '',
					wxdmname: '',
					xhdmname: '',
				},
				wraprules: {
					longname: [{
						required: true,
						validator: checkAge,
						trigger: 'blur'
					}, ],
					kname: [{
						required: true,
						validator: checkAge,
						trigger: 'blur'
					}, ],
					gname: [{
						required: true,
						validator: checkAge,
						trigger: 'blur'
					}, ],
					tjname: [{
						required: true,
						validator: checkAge,
						trigger: 'blur'
					}, ],
					mlname: [{
						required: true,
						validator: checkAge,
						trigger: 'blur'
					}, ],
					wxdmname: [{
							required: true,
							message: '请输入外箱条码',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
					xhdmname: [{
							required: true,
							message: '请输入小盒条码',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
				},
				datarules: {
					zczhname: [{
							required: true,
							message: '请输入批准文号/注册证号',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
					zcwhDate: [{
						required: true,
						message: '请选择日期',
						trigger: 'change'
					}, ],
					jyfwname: [{
						required: true,
						message: '请选择经营范围',
						trigger: 'change'
					}, ],
					gmpbook: [{
							required: true,
							message: '请输入gmp证书',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
					gpmdate: [{
						required: true,
						message: '请选择GMP证书有效期至',
						trigger: 'change'
					}, ],
					qyphCode: [{
						required: true,
						message: '请输入启用批号',
						trigger: 'blur'
					}, ],
					zlbzname: [{
						required: true,
						message: '请输入质量标准',
						trigger: 'blur'
					}, ],
				},
				ruleForm: {
					cpbmname: '',
					cpmcname: '',
					addwlbmname: "",
					addtymcname: "",
					addggname: "",
					adddwname: "",
					addjzlname: "",
					addjykcname: "",
					addchdmname: "",
					addcpxlid: "",
					addjxid: "",
					addotcname: "",
					addyplxid: "",
					addypflid: "",
					addzctjname: "",
					addsccsname: "",
					addcpbmname: "",
					gyssname: '',
					gongid: "",
					gongname: "",
				},
				rules: {
					addcpbmname: [{
							required: true,
							message: '请输入产品编码',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
					addwlbmname: [{
							required: true,
							message: '请输入物料编码',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
					addtymcname: [{
							required: true,
							message: '请输入通用名称',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
					addggname: [{
							required: true,
							message: '请输入规格',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
					adddwname: [{
							required: true,
							message: '请输入单位',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
					addjzlname: [{
							required: true,
							message: '请输入件装量',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
					addchdmname: [{
							required: true,
							message: '请输入存货代码',
							trigger: 'blur'
						},
						{
							max: 50,
							message: '最多输入50个字符',
							trigger: 'blur'
						}
					],
					addcpxlid: [{
						required: true,
						message: '请选择产品系列',
						trigger: 'change'
					}, ],
					addotcname: [{
						required: true,
						message: '请选择otc',
						trigger: 'change'
					}, ],
					addyplxid: [{
						required: true,
						message: '请选择药品类型',
						trigger: 'change'
					}, ],
					gongname: [{
						required: true,
						message: '请选择供应商',
						trigger: 'change'
					}, ],
					addypflid: [{
						required: true,
						message: '请选择药品分类',
						trigger: 'change'
					}, ],
					addzctjname: [{
						required: true,
						message: '请选择贮存条件',
						trigger: 'change'
					}, ],
					addsccsname: [{
						required: true,
						message: '请选择生产厂商',
						trigger: 'change'
					}, ],
					addjxid: [{
						required: true,
						message: '请选择剂型',
						trigger: 'change'
					}, ],
					addjykcname: [{
						required: true,
						message: '请选择贮存条件，自动匹配建议库存',
						trigger: 'change'
					}, ],
				}

			}
		},
		watch: {
			"addcpmcname": function(val) {
				if(!this.$route.query.editid) {
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
				}
			},
		},
		methods: {
			querySearch1(queryString, cb) {
				var restaurants = this.restaurants;
				//      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
				// 调用 callback 返回建议列表的数据
				cb(restaurants);
			},
			querySearch(queryString, cb) {
				var restaurants = this.restaurants;
				//      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
				// 调用 callback 返回建议列表的数据
				cb(restaurants);
			},
			createFilter(queryString) {
				return(restaurant) => {
					return(restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
				};
			},
			loadAll() {
				return this.cparray
			},
			handleSelect(sel) {
				this.editid = "";
				var getData = this.allArr.filter((item) => {
					if(item.productNameStr == this.addcpmcname) {
						/*this.ruleForm.gongname=item.supplierName
						this.ruleForm.gongid=item.supplierId*/
						this.goodsid = item.id
						this.addcpmcname = item.productName;
						this.ruleForm.addcpxlid = item.productLineId; //产品系列id
						this.addcpxlname = item.productLineName; //产品系列名称
						this.ruleForm.addwlbmname = item.productCode; //物料编码
						this.ruleForm.addtymcname = item.commonName; //通用名称
						this.addjxname = item.agentType; //剂型名称
						this.ruleForm.addjxid = item.agentTypeId; //剂型id
						this.ruleForm.addggname = item.productState; //规格
						this.ruleForm.adddwname = item.unit; //单位
						this.ruleForm.addotcname = item.otc; //OTC
						this.ruleForm.addjzlname = item.achange; //件装量
						this.addyplxname = item.medicineType; //药品类型
						this.ruleForm.addyplxid = item.medicineTypeId; //药型类型id
						this.ruleForm.addypflid = item.medicineClassifyId; //药品分类id
						this.addypflname = item.medicineClassify; //药品分类
						this.ruleForm.addzctjname = item.keepingCondition; //贮存条件
						this.ruleForm.addsccsname = item.manufacturer; //生产厂商
						this.ruleForm.addjykcname = item.suggestWarehouse; //建议库存
						this.ruleForm.addchdmname = item.inventoryCode; //存货代码
						this.ruleForm.addcpbmname = item.productNum; //产品编码
						this.checked = true; //追溯码
						this.taglist = [];
						item.scmZsjAchageList.forEach((i, index) => {
							console.log(i)
							this.taglist.push(i.achage)
						});
						console.log(this.taglist)
					}
				})
			},
			gysChange: function(value){
				this.gongoption.filter(res=>{
					if (res.supplierName == value) {
						this.ruleForm.gongid = res.id;
					}
				})
			},
			refreshPageRequest: function(page = 1) {
				this.pageRequest.page = page;
				this.findPage();
			},
			refreshPageSizeRequest: function(pageSize) {
				this.tableData3.pageSize = pageSize;
				this.findPage();
			},
			getypname() {
				//获取药品类型的name
				if(this.ypoption.length != 0) {
					var data = this.ypoption.filter((item) => {
						if(item.id == this.ypid) {
							this.ypname = item.medicineType
							console.log(this.ypname)
						}
					})
				}
			},
			getcpname() {
				//获取产品系列的name
				if(this.cpoption.length != 0) {
					var data = this.cpoption.filter((item) => {
						if(item.id == this.cpid) {
							this.cpname = item.productLineName
							console.log(this.cpname)
						}
					})
				}
			},
			getgongname() {
				//获取供应商的id
				if(this.gongoption.length != 0) {
					var data = this.gongoption.filter((item) => {
						if(item.supplierName == this.ruleForm.gongname) {
							this.ruleForm.gongid = item.id
							console.log(this.ruleForm.gongid)
						}
					})
				}
			},
			getaddcpxiname() {
				//获取弹框产品系列的name
				if(this.addcpxioption.length != 0) {
					var data = this.addcpxioption.filter((item) => {
						if(item.id == this.ruleForm.addcpxlid) {
							this.addcpxlname = item.productLineName
							console.log(this.addcpxlname)
						}
					})
				}
			},
			getaddjxname() {
				//获取弹框剂型的name
				if(this.addjxoption.length != 0) {
					var data = this.addjxoption.filter((item) => {
						if(item.id == this.ruleForm.addjxid) {
							this.addjxname = item.agentType
							console.log(this.addjxname)
						}
					})
				}
			},
			getaddyplxname() {
				//获取弹框药品类型的name
				if(this.addyplxoption.length != 0) {
					var data = this.addyplxoption.filter((item) => {
						if(item.id == this.ruleForm.addyplxid) {
							this.addyplxname = item.medicineType
							console.log(this.addyplxname)
						}
					})
				}
			},
			getaddzctjname() {

			},
			getaddypflname() {
				//获取弹框药品分类的name
				if(this.addypfloption.length != 0) {
					var data = this.addypfloption.filter((item) => {
						if(item.id == this.ruleForm.addypflid) {
							this.addypflname = item.medicineClassify
							console.log(this.addypflname)
						}
					})
				}
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			qx() {
				this.dialogTableVisible = false
				this.resetForm('ruleForm1')
			},
			sendapply() {
				this.$refs.ruleForm1.validate(valid => {
					this.valid1 = valid
				})
				this.$refs.dataForm1.validate(valid => {
					this.valid2 = valid
				})
				if(!this.ruleForm.gongname) {
					this.$message({
						message: "请输入供应商名称",
						type: "error",
						duration: 800
					});
					return false;
				}
				if(!this.wrapForm.longname) {
					this.$message({
						message: "请输入长",
						type: "error",
						duration: 800
					});
					return false
				}
				if(!this.wrapForm.kname) {
					this.$message({
						message: "请输入宽",
						type: "error",
						duration: 800
					});
					return false
				}
				if(!this.wrapForm.gname) {
					this.$message({
						message: "请输入高",
						type: "error",
						duration: 800
					});
					return false
				}
				if(!this.wrapForm.tjname) {
					this.$message({
						message: "请输入体积",
						type: "error",
						duration: 800
					});
					return false
				}
				if(!this.wrapForm.mlname) {
					this.$message({
						message: "请输入毛重",
						type: "error",
						duration: 800
					});
					return false
				}
				if(!this.wrapForm.wxdmname) {
					this.$message({
						message: "请输入外形条码",
						type: "error",
						duration: 800
					});
					return false
				}
				if(!this.wrapForm.xhdmname) {
					this.$message({
						message: "请输入小盒条码",
						type: "error",
						duration: 800
					});
					return false
				}

				if(this.valid1 == true && this.valid2 == true) {
					if(this.checked == "true") {
						this.addzsm = "1"
					} else {
						this.addzsm = "0"
					}
					var information = {
						productName: this.addcpmcname, //产品名称
						supplier: this.ruleForm.gongname, //供应商名称
						supplierId: this.ruleForm.gongid, //供应商id
						id: this.editid, //编辑列表id
						productId: this.goodsid, //商品id
						businessScope: this.dataForm.jyfwname, //经营范围
						approvalCode: this.dataForm.zczhname, //批准文号/注册证号
						approvalValidity: this.dataForm.zcwhDate, //批准文号/注册证号有效期至
						gmp: this.dataForm.gmpbook, //GMP证书
						gmpValidity: this.dataForm.gpmdate, //GMP证书有效期至
						startBatch: this.dataForm.qyphCode, //启用批号
						qualityStandard: this.dataForm.zlbzname, //质量标准
						length: this.wrapForm.longname, //长
						wide: this.wrapForm.kname, //宽
						high: this.wrapForm.gname, //高
						volume: this.wrapForm.tjname, //体积
						roughWeight: this.wrapForm.mlname, //毛重
						cartonBarcode: this.wrapForm.wxdmname, //外箱条码
						smallBarcode: this.wrapForm.xhdmname, //小盒条码
						ownerId: this.ownerId, //户主id
						scmZsjProduct: {
							productName: this.addcpmcname,
							productLineId: this.ruleForm.addcpxlid, //产品系列id
							productLineName: this.addcpxlname, //产品系列名称
							productCode: this.ruleForm.addwlbmname, //物料编码
							commonName: this.ruleForm.addtymcname, //通用名称
							agentType: this.addjxname, //剂型名称
							agentTypeId: this.ruleForm.addjxid, //剂型id
							productState: this.ruleForm.addggname, //规格
							unit: this.ruleForm.adddwname, //单位
							otc: this.ruleForm.addotcname, //OTC
							achange: this.ruleForm.addjzlname, //件装量
							medicineType: this.addyplxname, //药品类型
							medicineTypeId: this.ruleForm.addyplxid, //药型类型id
							medicineClassifyId: this.ruleForm.addypflid, //药品分类id
							medicineClassify: this.addypflname, //药品分类
							keepingCondition: this.addzctjname, //贮存条件
							supplierName: this.ruleForm.gongname, //供应商名称
							supplierId: this.ruleForm.gongid, //供应商id
							manufacturer: this.ruleForm.addsccsname, //生产厂商
							suggestWarehouse: this.ruleForm.addjykcname, //建议库存
							inventoryCode: this.ruleForm.addchdmname, //存货代码
							productNum: this.ruleForm.addcpbmname, //产品编码
							traceCode: this.addzsm, //追溯码
							id: this.goodsid //商品id
						}
					}
					if(this.$route.query.editid) {
						this.$api.zsj.getZsj({
							url: "/scm/scmZsjProductFirst/v1/updateSendApply",
							data: information
						}).then(res => {
							if(res.state == false) {
								this.$message({
									message: "操作失败, " + res.shortMessage,
									type: "error",
									duration: 800
								});
								return false
							} else {
								this.$message({
									message: "申请成功",
									type: "success",
									duration: 800
								});
								this.$router.push({
									path: '/zsj/goodsfirst'
								})
								//		            		this.resetForm('ruleForm1')
								//		            		this.resetForm('dataForm1')
								//		      				this.checked="false"
								information = {}
								this.dialogTableVisible = false
								this.findPage();
							}
						});
					} else {
						this.$api.zsj.getZsj({
							url: "/scm/scmZsjProductFirst/v1/sendApply",
							data: information
						}).then(res => {
							console.log(res)
							if(res.state == false) {
								this.$message({
									message: "操作失败, " + res.shortMessage,
									type: "error",
									duration: 800
								});
								return false
							} else {
								this.$message({
									message: "申请成功",
									type: "success",
									duration: 800
								});
								this.$router.push({
									path: '/zsj/goodsfirst'
								})
								this.resetForm('ruleForm1')
								this.resetForm('dataForm1')
								this.resetForm('wrapForm1')
								this.checked = "false"
								information = {}
								this.dialogTableVisible = false
								this.findPage();
							}
						});
					}

				}
			},
			sqsave() {
				if(this.checked == "true") {
					this.addzsm = "1"
				} else {
					this.addzsm = "0"
				}
				var information = {
					supplier: this.ruleForm.gongname, //供应商名称
					supplierId: this.ruleForm.gongid, //供应商id
					businessScope: this.dataForm.jyfwname, //经营范围
					id: this.editid, //编辑列表id
					productId: this.goodsid, //商品id
					approvalCode: this.dataForm.zczhname, //批准文号/注册证号
					approvalValidity: this.dataForm.zcwhDate, //批准文号/注册证号有效期至
					gmp: this.dataForm.gmpbook, //GMP证书
					gmpValidity: this.dataForm.gpmdate, //GMP证书有效期至
					startBatch: this.dataForm.qyphCode, //启用批号
					qualityStandard: this.dataForm.zlbzname, //质量标准
					length: this.wrapForm.longname, //长
					wide: this.wrapForm.kname, //宽
					high: this.wrapForm.gname, //高
					volume: this.wrapForm.tjname, //体积
					roughWeight: this.wrapForm.mlname, //毛重
					cartonBarcode: this.wrapForm.wxdmname, //外箱条码
					smallBarcode: this.wrapForm.xhdmname, //小盒条码
					ownerId: this.ownerId, //户主id
					scmZsjProduct: {
						productName: this.addcpmcname,
						productLineId: this.ruleForm.addcpxlid, //产品系列id
						productLineName: this.addcpxlname, //产品系列名称
						productCode: this.ruleForm.addwlbmname, //物料编码
						commonName: this.ruleForm.addtymcname, //通用名称
						agentType: this.addjxname, //剂型名称
						agentTypeId: this.ruleForm.addjxid, //剂型id
						productState: this.ruleForm.addggname, //规格
						unit: this.ruleForm.adddwname, //单位
						otc: this.ruleForm.addotcname, //OTC
						achange: this.ruleForm.addjzlname, //件装量
						medicineType: this.addyplxname, //药品类型
						medicineTypeId: this.ruleForm.addyplxid, //药型类型id
						medicineClassifyId: this.ruleForm.addypflid, //药品分类id
						medicineClassify: this.addypflname, //药品分类
						keepingCondition: this.addzctjname, //贮存条件
						supplierName: this.ruleForm.gongname, //供应商名称
						supplierId: this.ruleForm.gongid, //供应商id
						manufacturer: this.ruleForm.addsccsname, //生产厂商
						suggestWarehouse: this.ruleForm.addjykcname, //建议库存
						inventoryCode: this.ruleForm.addchdmname, //存货代码
						productNum: this.ruleForm.addcpbmname, //产品编码
						traceCode: this.addzsm, //追溯码
						id: this.goodsid //商品id

					}
				}
				this.$api.zsj.getZsj({
					url: "/scm/scmZsjProductFirst/v1/save",
					data: information
				}).then(res => {
					if(res.state == false) {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
						//		            		this.resetForm('ruleForm1')
						//		            		this.resetForm('dataForm1')
						//		            		this.resetForm('wrapForm1')
						//		      				this.checked="false"

					} else {
						this.$message({
							message: "暂存成功",
							type: "success",
							duration: 800
						});
						information = {}
						this.dialogTableVisible = false
						this.editid = res.value
					}
				});
			},
			edit(rows) {
				this.dialogTableVisible = true;
				this.operation = false;
				this.addcpmcname = rows.productName
				this.ruleForm.addcpxlid = rows.productLineId
				this.addcpxlname = rows.productLineName
				this.ruleForm.addwlbmname = rows.productCode
				this.ruleForm.addtymcname = rows.commonName
				this.addjxname = rows.agentType
				this.ruleForm.addjxid = rows.agentTypeId
				this.ruleForm.addggname = rows.productState
				this.ruleForm.adddwname = rows.unit
				this.ruleForm.addotcname = rows.otc
				this.ruleForm.addjzlname = rows.achange
				this.addyplxname = rows.medicineType
				this.ruleForm.addyplxid = rows.medicineTypeId
				this.ruleForm.addypflid = rows.medicineClassifyId
				this.ruleForm.addsccsname = rows.manufacturer
				this.ruleForm.addjykcname = rows.suggestWarehouse
				this.ruleForm.addchdmname = rows.inventoryCode
				this.checked = true
				this.ruleForm.addzctjname = rows.keepingCondition
				this.ruleForm.addcpbmname = rows.productNum
			},
			searchquery() {
				if(this.cpbmname) {
					this.search.push({
						property: "productNum",
						value: this.cpbmname,
						operation: "LIKE",
						relation: "AND"
					})
				}
				if(this.cpmcname) {
					this.search.push({
						property: "productName",
						value: this.cpmcname,
						operation: "LIKE",
						relation: "AND"
					})
				}
				if(this.jyname) {
					this.search.push({
						property: "medicineClassify",
						value: this.jyname,
						operation: "LIKE",
						relation: "AND"
					})
				}
				if(this.cpid) {
					this.search.push({
						property: "ProductLineId",
						value: this.cpid,
						operation: "=",
						relation: "AND"
					})
				}
				if(this.ypname) {
					this.search.push({
						property: "ProductLineId",
						value: this.ypname,
						operation: "=",
						relation: "AND"
					})
				}
				if(this.wlname) {
					this.search.push({
						property: "productCode",
						value: this.wlname,
						operation: "LIKE",
						relation: "AND"
					})
				}
				if(this.chdmcname) {
					this.search.push({
						property: "inventoryCode",
						value: this.chdmcname,
						operation: "LIKE",
						relation: "AND"
					})
				}
				this.findPage(this.search)
			},
			//商品列表
			findPage(search) {
				this.loading = true;
				this.$api.zsj.getZsj({
					url: "/scm/scmZsjProduct/v1/list",
					data: {
						pageBean: {},
						params: {},
						querys: search,
						sorter: []
					},
				}).then(res => {
					if(res) {
						this.tableData3 = res.rows
						this.total = res.total
						this.loading = false
						this.search = []
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
		mounted() {
			//编辑获取数据然后回显
			if(this.$route.query.editid) {
				this.editid = this.$route.query.editid
				this.goodsType = true
				this.checked = true
				this.$api.zsj.changeState({
					url: "/scm/scmZsjProductFirst/v1/get/" + this.editid,
				}).then(res => {
					if(res.state) {
						this.ruleForm.gongname = res.value.supplier, //供应商名称
						this.dataForm.jyfwname = res.value.businessScope, //经营范围
						this.goodsid = res.value.productId, //商品id
						this.dataForm.zczhname = res.value.approvalCode, //批准文号/注册证号
						this.dataForm.zcwhDate = res.value.approvalValidity, //批准文号/注册证号有效期至
						this.dataForm.gmpbook = res.value.gmp, //GMP证书
						this.dataForm.gpmdate = res.value.gmpValidity, //GMP证书有效期至
						this.dataForm.qyphCode = res.value.startBatch, //启用批号
						this.dataForm.zlbzname = res.value.qualityStandard, //质量标准
						this.wrapForm.longname = res.value.length, //长
						this.wrapForm.kname = res.value.wide, //宽
						this.wrapForm.gname = res.value.high, //高
						this.wrapForm.tjname = res.value.volume, //体积
						this.wrapForm.mlname = res.value.roughWeight, //毛重
						this.wrapForm.wxdmname = res.value.cartonBarcode, //外箱条码
						this.wrapForm.xhdmname = res.value.smallBarcode, //小盒条码
						this.ownerId = res.value.ownerId, //户主id
						this.addcpmcname = res.value.scmZsjProduct.productName //产品名称
						this.ruleForm.addcpxlid = res.value.scmZsjProduct.productLineId, //产品系列id
						this.addcpxlname = res.value.scmZsjProduct.productLineName, //产品系列名称
						this.ruleForm.addwlbmname = res.value.scmZsjProduct.productCode, //物料编码
						this.ruleForm.addtymcname = res.value.scmZsjProduct.commonName, //通用名称
						this.addjxname = res.value.scmZsjProduct.agentType, //剂型名称
						this.ruleForm.addjxid = res.value.scmZsjProduct.agentTypeId, //剂型id
						this.ruleForm.addggname = res.value.scmZsjProduct.productState, //规格
						this.ruleForm.adddwname = res.value.scmZsjProduct.unit, //单位
						this.ruleForm.addotcname = res.value.scmZsjProduct.otc, //OTC
						this.ruleForm.addjzlname = res.value.scmZsjProduct.achange, //件装量
						this.addyplxname = res.value.scmZsjProduct.medicineType, //药品类型
						this.ruleForm.addyplxid = res.value.scmZsjProduct.medicineTypeId, //药型类型id
						this.ruleForm.addypflid = res.value.scmZsjProduct.medicineClassifyId, //药品分类id
						this.addypflname = res.value.scmZsjProduct.medicineClassify, //药品分类
						this.ruleForm.addzctjname = res.value.scmZsjProduct.keepingCondition, //贮存条件
						this.ruleForm.gongid = res.value.supplierId, //供应商id
						this.ruleForm.addsccsname = res.value.scmZsjProduct.manufacturer, //生产厂商
						this.ruleForm.addjykcname = res.value.scmZsjProduct.suggestWarehouse, //建议库存
						this.ruleForm.addchdmname = res.value.scmZsjProduct.inventoryCode, //存货代码
						this.ruleForm.addcpbmname = res.value.scmZsjProduct.productNum, //产品编码
						this.goodsid = res.value.scmZsjProduct.id //商品id
					} else {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
					}
				});
			}
			//查看详情
			if(this.$route.query.detailType == "0") {
				this.goodsType = true
				this.detailType = true
				this.detailType1 = true
				this.showType = false
				this.checked = true
				this.$api.zsj.changeState({
					url: "/scm/scmZsjProductFirst/v1/get/" + this.editid,
				}).then(res => {
					if(res.state == false) {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
					} else {
						this.ruleForm.gongname = res.value.supplier, //供应商名称
							this.ruleForm.gongid = res.value.supplierId, //供应商id
							this.dataForm.jyfwname = res.value.businessScope, //经营范围
							this.goodsid = res.value.productId, //商品id
							this.dataForm.zczhname = res.value.approvalCode, //批准文号/注册证号
							this.dataForm.zcwhDate = res.value.approvalValidity, //批准文号/注册证号有效期至
							this.dataForm.gmpbook = res.value.gmp, //GMP证书
							this.dataForm.gpmdate = res.value.gmpValidity, //GMP证书有效期至
							this.dataForm.qyphCode = res.value.startBatch, //启用批号
							this.dataForm.zlbzname = res.value.qualityStandard, //质量标准
							this.wrapForm.longname = res.value.length, //长
							this.wrapForm.kname = res.value.wide, //宽
							this.wrapForm.gname = res.value.high, //高
							this.wrapForm.tjname = res.value.volume, //体积
							this.wrapForm.mlname = res.value.roughWeight, //毛重
							this.wrapForm.wxdmname = res.value.cartonBarcode, //外箱条码
							this.wrapForm.xhdmname = res.value.smallBarcode, //小盒条码
							this.ownerId = res.value.ownerId, //户主id
							this.addcpmcname = res.value.scmZsjProduct.productName //产品名称
							this.ruleForm.addcpxlid = res.value.scmZsjProduct.productLineId, //产品系列id
							this.addcpxlname = res.value.scmZsjProduct.productLineName, //产品系列名称
							this.ruleForm.addwlbmname = res.value.scmZsjProduct.productCode, //物料编码
							this.ruleForm.addtymcname = res.value.scmZsjProduct.commonName, //通用名称
							this.addjxname = res.value.scmZsjProduct.agentType, //剂型名称
							this.ruleForm.addjxid = res.value.scmZsjProduct.agentTypeId, //剂型id
							this.ruleForm.addggname = res.value.scmZsjProduct.productState, //规格
							this.ruleForm.adddwname = res.value.scmZsjProduct.unit, //单位
							this.ruleForm.addotcname = res.value.scmZsjProduct.otc, //OTC
							this.ruleForm.addjzlname = res.value.scmZsjProduct.achange, //件装量
							this.addyplxname = res.value.scmZsjProduct.medicineType, //药品类型
							this.ruleForm.addyplxid = res.value.scmZsjProduct.medicineTypeId, //药型类型id
							this.ruleForm.addypflid = res.value.scmZsjProduct.medicineClassifyId, //药品分类id
							this.addypflname = res.value.scmZsjProduct.medicineClassify, //药品分类
							this.ruleForm.addzctjname = res.value.scmZsjProduct.keepingCondition, //贮存条件
							this.ruleForm.gongname = res.value.supplier, //供应商名称
							this.dataForm.gongid = res.value.scmZsjProduct.supplierId, //供应商id
							this.ruleForm.addsccsname = res.value.scmZsjProduct.manufacturer, //生产厂商
							this.ruleForm.addjykcname = res.value.scmZsjProduct.suggestWarehouse, //建议库存
							this.ruleForm.addchdmname = res.value.scmZsjProduct.inventoryCode, //存货代码
							this.ruleForm.addcpbmname = res.value.scmZsjProduct.productNum, //产品编码
							this.goodsid = res.value.scmZsjProduct.id //商品id

					}
				});
			}
			this.ownerId = storage.get('ownerId')
			this.restaurants = this.loadAll();
			this.findPage();
			//经营范围
			this.$api.zsj.getZsj({
				url: "/scm/scmZdManageMentScope/v1/list",
				data: {},
			}).then(res => {
				if(res) {
					this.jyfwoptions = res.rows
				} else {
					this.$message({
						message: "操作失败, " + res.shortMessage,
						type: "error",
						duration: 800
					});
				}
			});
			//药品类型
			this.$api.zsj.getZsj({
				url: "/scm/scmZdMedicineType/v1/list",
				data: {},
			}).then(res => {
				if(res) {
					this.ypoption = res.rows
					this.addyplxoption = res.rows
				} else {
					this.$message({
						message: "操作失败, " + res.shortMessage,
						type: "error",
						duration: 800
					});
				}
			});
			//产品系列
			this.$api.zsj.getZsj({
				url: "/scm/scmZsjProductLine/v1/list",
				data: {},
			}).then(res => {
				if(res) {
					this.cpoption = res.rows
					this.addcpxioption = res.rows
				} else {
					this.$message({
						message: "操作失败, " + res.shortMessage,
						type: "error",
						duration: 800
					});
				}
			})
			//剂型
			this.$api.zsj.getZsj({
				url: "/scm/scmZdAgenttype/v1/list",
				data: {},
			}).then(res => {
				if(res) {
					this.addjxoption = res.rows
				} else {
					this.$message({
						message: "操作失败, " + res.shortMessage,
						type: "error",
						duration: 800
					});
				}
			})
			//药品分类
			this.$api.zsj.getZsj({
				url: "/scm/scmZdMedicineClassify/v1/list",
				data: {},
			}).then(res => {
				if(res) {
					this.addypfloption = res.rows
					this.options = res.rows
				} else {
					this.$message({
						message: "操作失败, " + res.shortMessage,
						type: "error",
						duration: 800
					});
				}
			})
			//贮存条件
			this.$api.zsj.getZsj({
				url: "/scm/scmZdKeepingCondition/v1/list",
				data: {},
			}).then(res => {
				if(res) {
					this.addzctjoption = res.rows
				} else {
					this.$message({
						message: "操作失败, " + res.shortMessage,
						type: "error",
						duration: 800
					});
				}
			})
			//生产厂商
			this.$api.zsj.getZsj({
				url: "/scm/scmZdManufacturer/v1/list",
				data: {},
			}).then(res => {
				//      	console.log(res)
				if(res) {
					this.addsccsoption = res.rows
				} else {
					this.$message({
						message: "操作失败, " + res.shortMessage,
						type: "error",
						duration: 800
					});
				}
			})
			//商品信息
			this.$api.zsj.changeState({
				url: "/scm/scmZsjProduct/v1/downBox/" + this.ownerId,
			}).then(res => {
				if(res) {
					this.allArr = res
					for(var i = 0; i < res.length; i++) {
						let valueMemo = {
							value: res[i].productNameStr
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
			//供应商
			this.$api.zsj.changeState({
				url: "/scm/scmZsjSupplier/v1/downBoxApplySuccess/" + this.ownerId,
			}).then(res => {
				if(res) {
					this.gongoption = res
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
	.sendProposer {
		margin-left: 10px;
	}
	
	.pagination {
		margin-top: 20px;
		display: flex;
		justify-content: flex-end;
	}
	
	.el-form {
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		.el-form-item {
			width: 33%;
		}
	}
	
	.el-select {
		width: 100%;
	}
	
	.el-autocomplete {
		width: 100%;
	}
	
	.searchHeader {
		padding: 5px;
		background: #f3f3f3;
		div:first-child {
			height: 28px;
			line-height: 28px;
		}
		b {
			font-size: 24px;
		}
		i {
			margin-right: 5px;
			margin-left: 5px;
		}
	}
</style>