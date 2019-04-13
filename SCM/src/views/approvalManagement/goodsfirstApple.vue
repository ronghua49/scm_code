<template>
	<!-- <div>{{msg}}</div> -->
	<div>
		<transition name="el-fade-in">

			<!-- 表格和搜索表格 -->
			<div v-show="isIndex">
				<!--表格栏-->
				<el-table :data="pageResult.rows" highlight-current-row v-loading="loading" element-loading-text="加载中" border
				 stripe show-overflow-tooltip=true max-height="500" size="mini" align="left" style="width:100%;">
					<!-- <el-table-column type="selection" width="40"></el-table-column> -->
					<el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
					<el-table-column v-for="column in columns" header-align="center" align="center" :prop="column.prop" :label="column.label"
					 :key="column.prop" :min-width="column.minWidth" :formatter="column.formatter" :sortable="column.sortable==null?true:column.sortable">
					</el-table-column>
					<el-table-column label="操作" width="100" fixed="right" header-align="center" align="center">
						<template slot-scope="scope">
							<el-button icon="el-icon-edit" label="" @click="approval(scope.$index, scope.row)" :size="size" type="primary">审批</el-button>
						</template>
					</el-table-column>
				</el-table>
				<!--分页栏-->
				<div class="toolbar" style="padding:10px;">
					<el-pagination @current-change="refreshPageRequest" @size-change="refreshPageSizeRequest" background :current-page="pageRequest.page"
					 :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest.pageSize" layout="sizes, total, prev, pager, next"
					 :total="pageResult.total" style="float:right;">
					</el-pagination>
				</div>
			</div>
		</transition>
		<div v-show="firstType">
			<el-row class="searchHeader">
				<el-col :span="1">
					<el-button icon="el-icon-back" :size="size" type="primary" @click="fanhui">返回</el-button>
				</el-col>
				<el-col :span="4" :offset="8"><b>商品首营审批</b></el-col>
				<el-col :span="4" :offset="7">
					<approval-btn :taskId="taskId" @handleSuccess="approvalSuccess"></approval-btn>
				</el-col>
			</el-row>
			<el-header>
				<div class="header">
					<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">数据列表</span><span class="el-icon-caret-bottom"></span></div>
					<div class="headright">
					</div>
				</div>
			</el-header>
			<el-form :model="ruleForm" :rules="rules" size="small" ref="ruleForm1" :label-position="labelPosition" label-width="120px"
			 class="demo-ruleForm">
				<el-form-item prop="addcpmcname" label="产品名称">
					<el-row class="demo-autocomplete">
						<el-autocomplete class="inline-input" :disabled="goodsType" v-model="ruleForm.addcpmcname" :fetch-suggestions="querySearch"
						 clearable placeholder="请输入产品名称" @select="handleSelect"></el-autocomplete>
					</el-row>
				</el-form-item>
				<el-form-item prop="addcpxlid" label="产品系列">
					<el-select v-model="ruleForm.addcpxlid" :disabled="goodsType" placeholder="请选择产品系列" @change="getaddcpxiname">
						<el-option v-for="item in addcpxioption" :key="item.id" :label="item.productLineName" :value="item.id">
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
					<el-select v-model="ruleForm.addjxid" :disabled="goodsType" placeholder="请选择剂型" @change="getaddjxname">
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
					<el-select v-model="ruleForm.addotcname" :disabled="goodsType" placeholder="请选择OTC">
						<el-option v-for="item in otcoption" :key="item.inx" :label="item.classification" :value="item.classification">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item prop="addjzlname" label="件装量">
					<el-input v-model="ruleForm.addjzlname" clearable :disabled="goodsType" placeholder="请输入件装量"></el-input>
				</el-form-item>
				<el-form-item prop="addyplxid" label="药品类型">
					<el-select v-model="ruleForm.addyplxid" :disabled="goodsType" placeholder="请选择药品类型" @change="getaddyplxname">
						<el-option v-for="item in addyplxoption" :key="item.id" :label="item.medicineType" :value="item.id">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item prop="addypflid" label="药品分类">
					<el-select v-model="ruleForm.addypflid" :disabled="goodsType" placeholder="请选择药品分类" @change="getaddypflname">
						<el-option v-for="item in addypfloption" :key="item.code" :label="item.medicineClassify" :value="item.id">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item prop="addzctjid" label="贮存条件">
					<el-select v-model="ruleForm.addzctjid" :disabled="goodsType" placeholder="请选择贮存条件" @change="getaddzctjname">
						<el-option v-for="(item, index) in addzctjoption" :key="index" :label="item.keepingCondition" :value="item.id">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item prop="gongid" label="供应商名称">
					<el-select v-model="ruleForm.gongname" :disabled="true" placeholder="请输入供应商名称">
						<el-option v-for="item in addsccsoption" :key="item.id" :label="item.manufacturer" :value="item.manufacturer">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item prop="addsccsname" label="生产厂商">
					<el-select :disabled="goodsType" v-model="ruleForm.addsccsname" placeholder="请选择生产厂商">
						<el-option v-for="item in addsccsoption" :key="item.id" :label="item.manufacturer" :value="item.manufacturer">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item prop="addjykcname" label="建议库存">
					<el-input v-model="ruleForm.addjykcname" :disabled="true" placeholder="请先选择贮存条件 "></el-input>
				</el-form-item>
				<el-form-item>
					<el-checkbox v-model="checked" :disabled="goodsType">追溯码</el-checkbox>
				</el-form-item>
			</el-form>


			<el-row class="searchHeader" style="margin-top:10px;">
				<el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>流程图</el-col>
			</el-row>
			<approval-flow :flowData="flowData"></approval-flow>
			<el-row class="searchHeader" style="margin-top:10px;">
				<el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>审批历史</el-col>
			</el-row>
			<approval-history :instId="instId"></approval-history>
			<div class="tab-title">
				<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">数据列表</span><span class="el-icon-caret-bottom"></span></div>
			</div>
			<el-main>
				<div class="wrap">
					<el-form :model="dataForm" :rules="datarules" ref="dataForm1" size="small" :label-position="labelPosition"
					 label-width="120px" class="demo-ruleForm">
						<el-form-item prop="zczhname" label="批准文号/注册证号">
							<el-input v-model="dataForm.zczhname" :disabled="detailType" clearable placeholder="请输入批准文号/注册证号"></el-input>
						</el-form-item>
						<el-form-item prop="zcwhDate" label="批准文号/注册证号有效期至">
							<el-date-picker v-model="dataForm.zcwhDate" type="date" :disabled="detailType" value-format="yyyy-MM-dd HH:mm:ss"
							 style="width:100%" placeholder="选择日期">
							</el-date-picker>
						</el-form-item>
						<el-form-item prop="jyfwname" label="经营范围">
							<el-select v-model="dataForm.jyfwname" :disabled="detailType" placeholder="请选择经营范围">
								<el-option v-for="item in jyfwoptions" :key="item.id" :label="item.managementScope" :value="item.managementScope">
								</el-option>
							</el-select>
						</el-form-item>
						<el-form-item prop="gmpbook" label="GMP证书">
							<el-input v-model="dataForm.gmpbook" clearable :disabled="detailType" placeholder="请输入GMP证书"></el-input>
						</el-form-item>
						<el-form-item prop="gpmdate" label="GMP证书有效期至">
							<el-date-picker v-model="dataForm.gpmdate" type="date" :disabled="detailType" value-format="yyyy-MM-dd HH:mm:ss"
							 style="width:100%" placeholder="选择日期">
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
			</el-main>
			<div class="tab-title">
				<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">物理特性</span><span class="el-icon-caret-bottom"></span></div>
			</div>
			<el-main>
				<div class="wrap">
					<el-form :model="wrapForm" :rules="wraprules" ref="wrapForm1" size="small" :label-position="labelPosition"
					 label-width="120px" class="demo-ruleForm">
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
			</el-main>
		</div>
	</div>
</template>

<script>
	import approvalBtn from "@/components/approval/ApprovalBtn"
	import approvalFlow from "@/components/approval/ApprovalFlow"
	import approvalHistory from "@/components/approval/ApprovalHistory"
	import storage from '@/utils/storage'

	let timer;
	export default {
		name: "commercefirst",
		components: {
			'approval-btn': approvalBtn,
			'approval-flow': approvalFlow,
			'approval-history': approvalHistory,
		},
		data() {
			return {
				size: "small",
				columns: [{
						prop: "subject",
						label: "任务标题",
						minWidth: 150
					},
					{
						prop: "name",
						label: "任务名称"
					},
					{
						prop: "ownerName",
						label: "所属人",
						minWidth: 150
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
						minWidth: 100
					},
					{
						prop: "",
						label: "到期时间",
						minWidth: 100
					},
				],
				isIndex: true,
				loading: false,
				goodsType: false,
				firstType: false,
				pageResult: {},
				pageRequest: {},
				ownerId: '',
				taskId: '',
				showType: true,
				goodsType: false,
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
				total: 0,
				operation: true,
				dialogTableVisible: false,
				options: [],
				ypoption: [],
				cpoption: [],
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
				instId: "",
				flowData: {},
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
							message: '请输入单位',
							trigger: 'blur'
						},
						{
							max: 20,
							message: '最多输入20个字符',
							trigger: 'blur'
						}
					],
					kname: [{
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
					gname: [{
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
					tjname: [{
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
					mlname: [{
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
					wxdmname: [{
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
					xhdmname: [{
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
					addzctjid: "",
					addcpmcname: "",
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
					addcpmcname: [{
							required: true,
							message: '请输入产品名称',
							trigger: 'change'
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
					addzctjid: [{
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
			"ruleForm.addcpmcname": function(val) {
				if (!this.$route.query.editid) {
					for (var i = 0; i < this.cparray.length; i++) {
						if (String(this.cparray[i].value.split("|")[1]).split(" ")[1] == val) {
							this.typearr.push(this.cparray[i].value)
							this.goodsType = true
						}
					}
					if (this.typearr.length == 0) {
						this.resetForm('ruleForm1')
						this.checked = false
						this.goodsType = false
					} else {
						this.typearr = []
					}
				}
			}
		},
		mounted() {
			this.findPage();
			this.ownerId = storage.get('ownerId')
			if (this.$route.query.editid) {
				this.editid = this.$route.query.editid
				this.goodsType = true
				this.checked = true
				this.$api.zsj.changeState({
					url: "/scm/scmZsjProductFirst/v1/get/" + this.editid,
				}).then(res => {
					if (res) {
						this.ruleForm.gongname = res.value.supplierName; //供应商名称
						this.ruleForm.gongid = res.value.supplierId; //供应商id
						this.dataForm.jyfwname = res.value.businessScope; //经营范围
						this.goodsid = res.value.productId; //商品id
						this.dataForm.zczhname = res.value.approvalCode; //批准文号/注册证号
						this.dataForm.zcwhDate = res.value.approvalValidity; //批准文号/注册证号有效期至
						this.dataForm.gmpbook = res.value.gmp; //GMP证书
						this.dataForm.gpmdate = res.value.gmpValidity; //GMP证书有效期至
						this.dataForm.qyphCode = res.value.startBatch; //启用批号
						this.dataForm.zlbzname = res.value.qualityStandard; //质量标准
						this.wrapForm.longname = res.value.length; //长
						this.wrapForm.kname = res.value.wide; //宽
						this.wrapForm.gname = res.value.high; //高
						this.wrapForm.tjname = res.value.volume; //体积
						this.wrapForm.mlname = res.value.roughWeight; //毛重
						this.wrapForm.wxdmname = res.value.cartonBarcode; //外箱条码
						this.wrapForm.xhdmname = res.value.smallBarcode; //小盒条码
						this.ownerId = res.value.ownerId; //户主id
						this.ruleForm.addcpmcname = res.value.scmZsjProduct.productName; //产品名称
						this.ruleForm.addcpxlid = res.value.scmZsjProduct.productLineId; //产品系列id
						this.addcpxlname = res.value.scmZsjProduct.productLineName; //产品系列名称
						this.ruleForm.addwlbmname = res.value.scmZsjProduct.productCode; //物料编码
						this.ruleForm.addtymcname = res.value.scmZsjProduct.commonName; //通用名称
						this.addjxname = res.value.scmZsjProduct.agentType; //剂型名称
						this.ruleForm.addjxid = res.value.scmZsjProduct.agentTypeId; //剂型id
						this.ruleForm.addggname = res.value.scmZsjProduct.productState; //规格
						this.ruleForm.adddwname = res.value.scmZsjProduct.unit; //单位
						this.ruleForm.addotcname = res.value.scmZsjProduct.otc; //OTC
						this.ruleForm.addjzlname = res.value.scmZsjProduct.achange; //件装量
						this.addyplxname = res.value.scmZsjProduct.medicineType; //药品类型
						this.ruleForm.addyplxid = res.value.scmZsjProduct.medicineTypeId; //药型类型id
						this.ruleForm.addypflid = res.value.scmZsjProduct.medicineClassifyId; //药品分类id
						this.addypflname = res.value.scmZsjProduct.medicineClassify; //药品分类
						this.addzctjname = res.value.scmZsjProduct.keepingCondition; //贮存条件
						this.ruleForm.gongname = res.value.supplierName; //供应商名称
						this.ruleForm.gongid = res.value.scmZsjProduct.supplierId; //供应商id
						this.ruleForm.addsccsname = res.value.scmZsjProduct.manufacturer; //生产厂商
						this.ruleForm.addjykcname = res.value.scmZsjProduct.suggestWarehouse; //建议库存
						this.ruleForm.addchdmname = res.value.scmZsjProduct.inventoryCode; //存货代码
						this.ruleForm.addcpbmname = res.value.scmZsjProduct.productNum; //产品编码
						this.goodsid = res.value.scmZsjProduct.id; //商品id
					} else {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
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
				if (res) {
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
				if (res) {
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
				if (res) {
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
				if (res) {
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
				if (res) {
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
				if (res) {
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
				if (res) {
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
				url: "scm/scmZsjProduct/v1/downBox/" + this.ownerId,
			}).then(res => {
				if (res) {
					this.allArr = res
					for (var i = 0; i < res.length; i++) {
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
		},
		methods: {
			fanhui() {
				this.firstType = false
				this.isIndex = true;
			},
			findPage() {
				this.loading = true;
				this.$api.approval
					.post({
						url: "/runtime/task/v1/list",
						data: {
							pageBean: this.pageRequest,
							params: {},
							querys: [{
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
									value: "spsy"
								}
							],
							sorter: []
						}
					})
					.then(res => {
						if (res.state == false) {
							this.$message({
								message: res.shortMessage,
								type: "error",
								duration: 800
							});
							return;
						}
						this.pageResult = res;
						this.loading = false
					})
			},
			deail() {
				this.goodsType = true
				this.detailType = true
				this.detailType1 = true
				this.showType = false
				this.checked = true
				this.$api.zsj.changeState({
					url: "/scm/scmZsjProductFirst/v1/getProductFirstByApprovalId/" + this.instId,
				}).then(res => {
					if (res) {
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
							this.ruleForm.addcpmcname = res.value.scmZsjProduct.productName //产品名称
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
							this.addzctjname = res.value.scmZsjProduct.keepingCondition, //贮存条件
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
			},
			querySearch(queryString, cb) {
				var restaurants = this.restaurants;
				var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
				// 调用 callback 返回建议列表的数据
				cb(results);
			},
			createFilter(queryString) {
				return (restaurant) => {
					return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
				};
			},
			loadAll() {
				return this.cparray
			},
			handleSelect(sel) {
				var getData = this.allArr.filter((item) => {
					if (item.productNameStr == this.ruleForm.addcpmcname) {
						this.dataForm.gongname = item.supplierName
						this.ruleForm.gongid = item.supplierId
						this.goodsid = item.id
						this.ruleForm.addcpmcname = item.productName;
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
							this.ruleForm.addzctjid = item.keepingCondition; //贮存条件
							this.ruleForm.addsccsname = item.manufacturer; //生产厂商
							this.ruleForm.addjykcname = item.suggestWarehouse; //建议库存
							this.ruleForm.addchdmname = item.inventoryCode; //存货代码
							this.ruleForm.addcpbmname = item.productNum; //产品编码
							this.checked = true //追溯码
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
				if (this.ypoption.length != 0) {
					var data = this.ypoption.filter((item) => {
						if (item.id == this.ypid) {
							this.ypname = item.medicineType
						}
					})
				}
			},
			getcpname() {
				//获取产品系列的name
				if (this.cpoption.length != 0) {
					var data = this.cpoption.filter((item) => {
						if (item.id == this.cpid) {
							this.cpname = item.productLineName
						}
					})
				}
			},
			getaddcpxiname() {
				//获取弹框产品系列的name
				if (this.addcpxioption.length != 0) {
					var data = this.addcpxioption.filter((item) => {
						if (item.id == this.ruleForm.addcpxlid) {
							this.addcpxlname = item.productLineName
						}
					})
				}
			},
			getaddjxname() {
				//获取弹框剂型的name
				if (this.addjxoption.length != 0) {
					var data = this.addjxoption.filter((item) => {
						if (item.id == this.ruleForm.addjxid) {
							this.addjxname = item.agentType
						}
					})
				}
			},
			getaddyplxname() {
				//获取弹框药品类型的name
				if (this.addyplxoption.length != 0) {
					var data = this.addyplxoption.filter((item) => {
						if (item.id == this.ruleForm.addyplxid) {
							this.addyplxname = item.medicineType
						}
					})
				}
			},
			getaddzctjname() {
				//获取贮存条件的name
				if (this.addzctjoption.length != 0) {
					var data = this.addzctjoption.filter((item) => {
						if (item.id == this.ruleForm.addzctjid) {
							this.addzctjname = item.keepingCondition
							this.ruleForm.addjykcname = item.suggestWarehouse
						}
					})
				}
			},
			getaddypflname() {
				//获取弹框药品分类的name
				if (this.addypfloption.length != 0) {
					var data = this.addypfloption.filter((item) => {
						if (item.id == this.ruleForm.addypflid) {
							this.addypflname = item.medicineClassify
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
				this.$refs.wrapForm1.validate(valid => {
					this.valid3 = valid
				})
				if (this.valid1 == true && this.valid2 == true && this.valid3 == true) {
					if (this.checked == "true") {
						this.addzsm = "1"
					} else {
						this.addzsm = "0"
					}
					var information = {
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
							productName: this.ruleForm.addcpmcname,
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
					if (this.$route.query.editid) {
						this.$api.zsj.getZsj({
							url: "/scm/scmZsjProductFirst/v1/updateSendApply",
							data: information
						}).then(res => {
							if (!res.state) {
								this.$message({
									message: "申请成功",
									type: "success",
									duration: 800
								});
								this.resetForm('ruleForm1')
								this.resetForm('dataForm1')
								this.resetForm('wrapForm1')
								this.checked = "false"
								information = {}
								this.dialogTableVisible = false
								this.findPage();
							} else {
								this.$message({
									message: "操作失败, " + res.shortMessage,
									type: "error",
									duration: 800
								});
								return false
							}
						});
					} else {
						this.$api.zsj.getZsj({
							url: "/scm/scmZsjProductFirst/v1/sendApply",
							data: information
						}).then(res => {
							if (!res.state) {
								this.$message({
									message: "申请成功",
									type: "success",
									duration: 800
								});
								this.resetForm('ruleForm1')
								this.resetForm('dataForm1')
								this.resetForm('wrapForm1')
								this.checked = "false"
								information = {}
								this.dialogTableVisible = false
								this.findPage();
							} else {
								this.$message({
									message: "操作失败, " + res.shortMessage,
									type: "error",
									duration: 800
								});
								return false
							}
						});
					}

				}
			},
			sqsave() {
				this.$refs.ruleForm1.validate(valid => {
					this.valid1 = valid
				})
				this.$refs.dataForm1.validate(valid => {
					this.valid2 = valid
				})
				this.$refs.wrapForm1.validate(valid => {
					this.valid3 = valid
				})
				if (this.valid1 == true && this.valid2 == true && this.valid3 == true) {
					if (this.checked == "true") {
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
							productName: this.ruleForm.addcpmcname,
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
						if (!res.state) {
							this.$message({
								message: "暂存成功",
								type: "success",
								duration: 800
							});
							this.resetForm('ruleForm1')
							this.resetForm('dataForm1')
							this.resetForm('wrapForm1')
							this.checked = "false"
							information = {}
							this.dialogTableVisible = false
							this.findPage();
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
			edit(rows) {
				this.dialogTableVisible = true;
				this.operation = false;
				this.ruleForm.addcpmcname = rows.productName
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
				this.ruleForm.addzctjid = rows.keepingCondition
				this.ruleForm.addcpbmname = rows.productNum
			},
			searchquery() {
				if (this.cpbmname) {
					this.search.push({
						property: "productNum",
						value: this.cpbmname,
						operation: "LIKE",
						relation: "AND"
					})
				}
				if (this.cpmcname) {
					this.search.push({
						property: "productName",
						value: this.cpmcname,
						operation: "LIKE",
						relation: "AND"
					})
				}
				if (this.jyname) {
					this.search.push({
						property: "medicineClassify",
						value: this.jyname,
						operation: "LIKE",
						relation: "AND"
					})
				}
				if (this.cpid) {
					this.search.push({
						property: "ProductLineId",
						value: this.cpid,
						operation: "=",
						relation: "AND"
					})
				}
				if (this.ypname) {
					this.search.push({
						property: "ProductLineId",
						value: this.ypname,
						operation: "=",
						relation: "AND"
					})
				}
				if (this.wlname) {
					this.search.push({
						property: "productCode",
						value: this.wlname,
						operation: "LIKE",
						relation: "AND"
					})
				}
				if (this.chdmcname) {
					this.search.push({
						property: "inventoryCode",
						value: this.chdmcname,
						operation: "LIKE",
						relation: "AND"
					})
				}
				this.findPage(this.search)
			},
			refreshPageRequest: function(page = 1) {
				this.pageRequest.page = page;
				this.findPage();
			},
			// size刷新
			refreshPageSizeRequest: function(pageSize) {
				this.pageRequest.pageSize = pageSize;
				this.findPage();
			},
			approval(index, row) {
				this.isIndex = false;
				this.isIndex = false;
				this.instId = row.procInstId;
				this.taskId = row.id;
				this.firstType = true;
				this.flowData = {
					taskId: row.taskId
				}

				this.deail()
			},
			approvalSuccess() {
				this.findPage();
				this.isIndex = true;
				this.firstType = false;
			}
		}
	};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
	.btnAdd {
		padding-bottom: 5px;
	}

	.paginer {
		margin-top: 5px;
	}

	.el-select {
		width: 100%;
	}

	.el-form {
		display: flex;
		flex-direction: row;
		// justify-content: space-around;
		flex-wrap: wrap;

		.el-form-item {
			width: 32%;
		}
	}

	.searchHeader {
		margin-top: 5px;
		margin-bottom: 5px;
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

	.searchBox {
		padding-top: 10px;
		border: 1px solid #f4f4f4;
		margin-bottom: 10px;
	}

	.addContent {
		border: 1px solid #f4f4f4;
		border-radius: 3px;
		margin-bottom: 10px;

		.addHead {
			text-align: center;
			width: 100%;
			height: 38px;
			line-height: 50px;
			font-weight: 600;
		}
	}

	.header {
		width: 100%;
		height: 40px;
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
		height: 38px;
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
		display: flex;
		justify-content: space-between;
		margin-right: 30px;
	}

	.sendProposer {
		margin-left: 10px;
	}

	.sx {
		margin: auto 5px;
	}

	.title {
		display: flex;
		justify-content: flex-start;
		margin-top: 20px;
		margin-left: 35px;
		margin-bottom: 20px;
	}

	.pagination {
		margin-top: 20px;
		display: flex;
		justify-content: flex-end;
	}

	.headcenter {
		font-weight: bold;
		font-size: 18px;
	}

	.wrap {
		margin-top: 15px;
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

	.el-main {
		padding: 0 !important;
	}
</style>
