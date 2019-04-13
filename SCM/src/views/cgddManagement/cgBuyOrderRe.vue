<template>
	<div>
    <transition name="el-fade-in-linear">
			<div v-show="firstStep">
		  	<div class="header">
				<div class="headleft"><span class="el-icon-search"></span><span class="sx">筛选查询</span></div>
				<div class="headright">
					<el-row>
					  <el-button type="primary" class="el-icon-search" size="mini" @click="searchquery">查询</el-button>
					</el-row>
				</div>
			</div>
		  	<el-form :label-position="labelPosition" :model="searchForm" label-width="120px" size="small" class="demo-ruleForm">
				<el-form-item  label="采购补单单号" prop="replacementOrderCode">
					<el-input  placeholder="请输入采购补单单号" v-model="searchForm.replacementOrderCode" clearable></el-input>
				</el-form-item>
				<el-form-item  label="审批状态" prop="approvalState">
					<el-select v-model="searchForm.approvalState" clearable placeholder="请选择审批状态">
					    <el-option value='0' label="未提交"></el-option>
              <el-option value="1" label="待审批"></el-option>
              <el-option value="2" label="已经通过"></el-option>
					 </el-select>
				</el-form-item>
        <el-form-item  label="合同编号" prop="contractCode">
          <el-select v-model="searchForm.contractCode" clearable placeholder="请选择合同编号">
              <el-option
                v-for="item in contractList"
                :key="item.id"
                :label="item.contractCode"
                :value="item.contractCode">
              </el-option>
            </el-select>
        </el-form-item>
				<el-form-item  label="供应商名称" prop="supplier"  clearable>
					<el-select v-model="searchForm.supplier" clearable placeholder="请选择供应商名称">
					    <el-option
					      v-for="item in gongoption"
					      :key="item.id"
					      :label="item.supplierName"
					      :value="item.supplierName">
					    </el-option>
					 </el-select>
				</el-form-item>
        
			</el-form>
		  <div class="header">
				<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">数据列表</span></div>
				<div class="headright">
					 <el-button type="primary" class="el-icon-circle-plus" size="mini" @click="buy">采购补单申请</el-button>
				</div>
		  </div>
		  <!--table-->
		  	 <el-table
		  	 	 v-loading="loading2"
		  	 	 element-loading-text="加载中"
			    :data="tableData3"
			    size="mini"
			    border
			    style="width: 100%">
			    <el-table-column v-for="item in column"
						:key="item.prop"
			      :prop="item.prop"
			      :label="item.label"
			      :formatter="item.formatter"
			      :width="item.minwidth"
			      :align="item.align">
			    </el-table-column>
			    <el-table-column
			      fixed="right"
			      label="操作"
			      align="center"
			      width="200">
			      <template slot-scope="scope">
			      	<el-button class="el-icon-view" type="primary" size="mini">查看详情</el-button>
			        <el-button type="primary" :disabled="scope.row.approvalState==0?false:true" class="el-icon-edit" size="mini" @click="edit(scope.row)">编辑</el-button>
			      </template>
			    </el-table-column>
			  </el-table>
			  <div class="pagination">
			  	<el-pagination @current-change="refreshPageRequest" @size-change="refreshPageSizeRequest"
				  background
				  :current-page="pageRequest.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest.pageSize" layout="sizes, total, prev, pager, next"
				  :total="total">
			  	</el-pagination>
			  </div>
			</div>
    </transition>

      <transition name="el-fade-in-linear">
      <div v-show="secondStep">
        <el-row class="searchHeader">
            <el-col :span="1" style="text-align: right;">
            <el-button  size="mini" class="el-icon-back" type="primary" @click="goback">返回</el-button>
          </el-col>
          <el-col :span="6" :offset="8"><b>采购补单申请</b></el-col>
          <el-col :span="9" style="text-align: right;">
            <!-- <el-button  size="mini" type="primary" v-show="oneShow" @click="nextStep()">下一步</el-button> -->
            <!-- <el-button  size="mini" type="primary" v-show="twoShow" @click="prevStep">上一步</el-button> -->
            <!-- <el-button  size="mini" type="primary" v-show="threeShow" @click="applySave">申请暂存</el-button> -->
            <el-button v-show="secondTableData.length>0"  size="mini" type="primary"  @click="sendApply">发起申请</el-button>
          </el-col>
        </el-row>
				<div class="header"  style="margin-top:10px;">
					<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">基础信息</span>
					</div>
					<div class="headright">
					</div>
				</div>
				<el-form :model="wrapForm" ref="wrapForm1" :label-position="labelPosition" size="small" label-width="120px" class="demo-ruleForm">
					<el-form-item prop="supplier" label="供应商名称">
						<el-input v-model="wrapForm.supplier" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item prop="contractCode" label="合同编号">
						<el-input v-model="wrapForm.contractCode" :disabled="true"></el-input>
					</el-form-item>
                    <el-form-item prop="deliveryDate" label="交货日期">
						<el-date-picker v-model="wrapForm.deliveryDate" type="date" clearable :disabled="true" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" placeholder="选择日期">
						</el-date-picker>
					</el-form-item>
					<el-form-item prop="warehouse" label="收货仓库">
						<el-input v-model="wrapForm.warehouse" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item prop="getTicket" label="收票方">
						<el-input v-model="wrapForm.getTicket" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item prop="orderDate" label="下单日期">
						<el-date-picker v-model="wrapForm.orderDate" type="date" clearable :disabled="true" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" placeholder="选择日期">
						</el-date-picker>
					</el-form-item>
					<!-- <el-form-item prop="sumPrice" label="补单编号">
						<el-input v-model="wrapForm.scmCgProReplacementOrderList.replacementOrderCode" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item prop="orderDate" label="补单日期">
						<el-date-picker v-model="wrapForm.scmCgProReplacementOrderList.orderDate" type="date" clearable :disabled="true" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" placeholder="选择日期">
						</el-date-picker>
					</el-form-item> -->
				</el-form>
        <div class="content-item">
				    <div class="header">
						<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">添加采购</span><span style="color:#409eff">{{detailInfo.supplier}}</span>商品信息,合同号为<span style="color:#409eff">【{{detailInfo.contractCode}}】</span>。已选择<span style="color:#409eff">【{{detailInfo.speciesSum}}】</span>种产品,采购总金额为<span style="color:#409eff">【{{detailInfo.sumPrice}}】</span></div>
						<div class="headright">
							 <el-button type="primary" class="el-icon-circle-plus" size="mini" @click="addProduct">新增产品</el-button>
						</div>
				    </div>
					<el-table
				  	 	 v-loading="loading"
				  	 	 element-loading-text="加载中"
					    :data="secondTableData"
					    size="mini"
					    border
					    style="width: 100%">
              <el-table-column type="index" label="序号" align="center"></el-table-column>
              <el-table-column prop="achange" label="件装量" align="center"></el-table-column>
              <el-table-column prop="count" label="盒数" align="center" minWidth="150">
                <template slot-scope="scope">
					        <el-input  v-model="secondTableData[scope.$index].productSum" @input="oninput(scope.$index, scope.row)"  placeholder="请输入盒数"></el-input>
					      </template>
              </el-table-column>
					    <el-table-column v-for="item in columnSecond"
								:key="item.prop"
					      :prop="item.prop"
					      :label="item.label"
					      :formatter="item.formatter"
					      :minWidth="item.minWidth"
					      :align="item.align">
					    </el-table-column>
					    <el-table-column
					      fixed="right"
					      label="操作"
					      align="center"
					      minWidth="100">
					      <template slot-scope="scope">
					        <el-button type="danger" class="el-icon-delete" size="mini" @click="del(scope.$index,scope.row)">删除</el-button>
					      </template>
					    </el-table-column>
					  </el-table>
					  <div class="pagination">
					  	<el-pagination @current-change="refreshPageRequest" @size-change="refreshPageSizeRequest"
						  background
						  :current-page="pageRequest.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest.pageSize" layout="sizes, total, prev, pager, next"
						  :total="total">
					  </el-pagination>
					  </div>	
				</div>
      </div>
      </transition>
			<!--第三步-->
			<transition name="el-fade-in-linear">
				<div class="content-item" v-show="false">
					<div class="success">
						<span class="el-icon-check"></span>
						您已完成<span style="color: #409eff;">【{{detailInfo.supplier}}】</span>供货合同<span style="color: #409eff;">【{{detailInfo.contractCode}}】</span>签订
					</div>
					<div class="success-bottom">
						合同已申请<span style="color: #409eff;">【{{detailInfo.speciesSum}}】</span>种供货产品,总金额为<span style="color:#409eff;">[{{detailInfo.sumPrice}}]</span>{{detailInfo.currency}}
					</div>
				</div>
			</transition>	
      <el-dialog title="新增产品" width="50%"  :visible.sync="dialogVisible" :close-on-click-modal="false">
        <el-form :model="addForm" label-width="120px" :rules="addRules"  ref="addForm" size="small">
          <el-form-item style="width: 98%;" label="产品名称" prop="productName">
            <el-autocomplete style="width: 100%"
                  class="inline-input"
                  v-model="addForm.productName"
                  :fetch-suggestions="querySearch"
                  placeholder="请输入商品名称"
                  @select="handleSelect"
                  clearable
                ></el-autocomplete>
          </el-form-item>
          <el-form-item style="width: 49%;" label="产品编码" prop="productCode">
            <el-input :disabled="true" v-model="addForm.productCode" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item style="width: 49%;" label="产品通用名称" prop="productCname">
            <el-input :disabled="true" v-model="addForm.productCname" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item style="width: 49%;" label="品规" prop="productGauge">
            <el-input :disabled="true" v-model="addForm.productGauge" auto-complete="off" ></el-input>
          </el-form-item>
          <el-form-item style="width: 49%;" label="药品类型" prop="medicineType">
            <el-input :disabled="true" v-model="addForm.medicineType" auto-complete="off"></el-input>
          </el-form-item>
          
          <el-form-item style="width: 49%;" label="单位" prop="unit">
            <el-input :disabled="true" v-model="addForm.unit" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item style="width: 49%;" label="单价" prop="unitPrice">
            <el-input :disabled="true" v-model="addForm.unitPrice" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item style="width: 49%;" label="合同剩余数" prop="surplusAmount">
            <el-input :disabled="true" v-model="addForm.surplusAmount" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item style="width: 49%;" label="采购数量" prop="productSum">
            <el-input :disabled="true" v-model="addForm.productSum" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item style="width: 49%;" label="件装量" prop="achange">
            <el-input :disabled="true" v-model="addForm.achange" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item style="width: 49%;" label="盒数" prop="count">
            <el-input :disabled="countEdit" v-model="addForm.count" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item style="width: 49%;" label="总金额" prop="totalPrice">
            <el-input :disabled="true" v-model="totalPrice" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item style="width: 49%;" label="币种" prop="currency">
            <el-input :disabled="true" v-model="addForm.currency" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button :size="size" @click.native="dialogVisible = false">取消</el-button>
          <el-button :size="size" type="primary" @click="submitForm" >确定</el-button>
        </div>
      </el-dialog>	
	</div>
</template>
<script>
import storage from "@/utils/storage";
let timer;
export default {
  name: "goods",
  data() {
    return {
      size:'mini',
      dialogVisible: false,
      column: [
        {
          prop: "replacementOrderCode",
          label: "采购补单单号",
          minWidth: "200",
          align: "center"
        },
        {
          prop: "supplier",
          label: "供应商名称",
          minWidth: "150",
          align: "center"
        },
        {
          prop: "contractCode",
          label: "合同编号",
          minWidth: "150",
          align: "center"
        },
        {
          prop: "speciesSum",
          label: "供货品种数",
          minWidth: "200",
          align: "center"
        },
        { prop: "productSum", label: "数量", minWidth: "100", align: "center" },
        { prop: "sumPrice", label: "金额", minWidth: "100", align: "center" },
        { prop: "currency", label: "币种", minWidth: "100", align: "center" },
        {
          prop: "deliveryDate",
          label: "到货日期",
          minWidth: "200",
          align: "center"
        },
        {
          prop: "approvalState",
          label: "审批状态",
          minWidth: "150",
          align: "center",
          formatter: function(row, column, cellValue, index) {
            if (row.approvalState == 0) {
              return "未提交";
            }
            if (row.approvalState == 1) {
              return "审批中";
            }
            if (row.approvalState == 2) {
              return "已通过";
            }
          }
        }
      ],
      columnSecond:[
        { prop: "surplusAmount", label: "合同剩余数", minWidth: "100", align: "center" },
        { prop: "productName", label: "产品名称", minWidth: "100", align: "center" },
        { prop: "productCode", label: "产品编码", minWidth: "100", align: "center" },
        { prop: "productCname", label: "产品通用名", minWidth: "100", align: "center" },
        { prop: "productGauge", label: "品规", minWidth: "100", align: "center" },
        { prop: "medicineType", label: "药品类型", minWidth: "100", align: "center" },
        { prop: "unit", label: "单位", minWidth: "100", align: "center" },
        { prop: "unitPrice", label: "单价", minWidth: "100", align: "center" },
        { prop: "sumPrice", label: "金额", minWidth: "100", align: "center" },
        { prop: "currency", label: "币种", minWidth: "100", align: "center" },
        
      ],
      searchForm:{
        replacementOrderCode:'',
        approvalState:'',
        contractCode:'',
        supplier:''
      },
      btnState: false,
      firstStep: true,
      secondStep: false,
      restaurants: [],
      applyDate: "",
      labelPosition: "right",
      loading2: false,
      ownerId: "",
      total: 0,
      operation: true,
      dialogTableVisible: false,
      gongid: "",
      applyid: "",
      gongoption: [],//供应商下拉列表
      ticklist:[],//收票方下拉列表
      wareHouseList:[],//收货仓库下拉列表
      contractList:[],//合同编号
      tableData3: [],
      secondTableData:[],
      fisrtStepId:'',//第一步保存生成的id
      resultList:[],// 步骤二新增产品下拉列表
      countEdit: true,//盒数是否能够输入
      detailInfo:{
        speciesSum:'',
        supplier:'',
        contractCode:'',
        sumPrice:''
      },//第二部 头部信息
      bzoption: [{ name: "CNY", inx: 1 }],
      checked: false,
      loading: false,
      search: [],
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      addForm:{
        productName:'',
        productCode:'',
        productCname:'',
        productGauge:'',
        medicineType:'',
        unit:'',
        unitPrice:'',
        sumPrice:'',
        productSum:'',
        achange:'',
        surplusAmount:'',
        count:'',
      },
      wrapForm: {
        
      },
      addRules: {
        productName: [
          {required: true, message: '请选择产品名称',}
        ],
        count:[
          {required: true, message: '请输入盒数',},
          {tirgger:'blur', validator: (rule, value, callback)=>{
            if(value.length<=0){
              callback(new Error('请输入盒数'))
            }else if(!Number(value) && value.length>0){
              callback(new Error('盒数必须为数字'))
            }else if(value<= 0){
              callback(new Error('盒数必须大于0'))
            }else if(value>=99999999){
              callback(new Error('输入数量超过限制'))
            }else{
              callback();
            }
          }},
        ]
      },
    };
  },
  watch: {
    'addForm.productName':'productNameChange'
  },
  computed: {
    totalPrice(){
      return Number(this.addForm.count) * Number(this.addForm.unitPrice)
    }
  },
  methods: {
    oninput(index, row){//表格中直接修改盒数调用后台方法
      clearTimeout(timer);
      timer = setTimeout(()=>{
        let newid = this.fisrtStepId;
        if(row.productSum.length<=0 || !Number(row.productSum) || Number(row.productSum)<0 || row.productSum.startsWith('0') || row.productSum.includes('.')){
          this.secondTableData[index].productSum = '0';          
        }
        this.secondTableData[index].sumPrice = Number(this.secondTableData[index].productSum) * Number(this.secondTableData[index].unitPrice)
        let data = {
          id: row.id,
          productSum: row.productSum,
          contractProductId: row.contractProductId,
          proOrderId: this.fisrtStepId
        }
        this.$api.approval
        .postscm({
          url: "/scm/scmCgProReplacementOrder/v1/save",
          data: data
        })
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: "操作失败, " + res.message,
              type: "error",
              duration: 800
            });
            return;
          } 
          this.$message({
            message:res.message,
            type: 'success',
            duration: 800
          })
          this.getDetail(newid);
        })
      },1000)
    },
    del(index, row){//第二步 删除商品
      this.$confirm('确认删除选中记录吗？', '提示', {
				type: 'warning'
			}).then(() => {
				this.$api.approval.delscm({
          url: '/scm/scmCgProRepProduct/v1/remove/'+row.id
        })
        .then(res => {
              if (res.state == false) {
                this.$message({
                  message: "操作失败, " + res.message,
                  type: "error",
                  duration: 800
                });
                return;
              } 
              this.$message({
                message:res.message,
                type: 'success',
                duration: 800
              })
              this.getSecondTable();
        })
			}).catch(() => {
			})
    },
    submitForm(){//第二部新增商品
      let newid = this.fisrtStepId;
      this.$refs['addForm'].validate( valid => {
        if(valid){
          let data = {
              replacementOrderId:this.fisrtStepId,
              achange: this.addForm.achange,
            currency: this.addForm.currency,
            contractProductId: this.addForm.id,
            medicineType: this.addForm.medicineType,
            medicineTypeId: this.addForm.medicineTypeId,
            proOrderId: this.fisrtStepId,
            replacementOrderCode: this.addForm.replacementOrderCode,
            productCname: this.addForm.productCname,
            productCode: this.addForm.productCode,
            productGauge: this.addForm.productGauge,
            productId: this.addForm.productId,
            productName: this.addForm.productName,
            productSum: this.addForm.count,
            sumPrice: this.totalPrice,
            unit: this.addForm.unit,
            unitPrice: this.addForm.unitPrice,
          };
          this.$api.approval
          .postscm({
            url: "/scm/scmCgProRepProduct/v1/save",
            data: data
          })
          .then(res => {
            if (res.state == false) {
              this.$message({
                message: "操作失败, " + res.message,
                type: "error",
                duration: 800
              });
              return;
            } 
            this.$message({
              message:res.message,
              type: 'success',
              duration: 800
            })
            this.dialogVisible = false;
            this.getSecondTable();
            this.getDetail(newid);
          });
        }
      })
    },
    productNameChange(val){
      if(val == ''){
        this.$refs.addForm.resetFields();
        this.countEdit = true;
      }else{
        this.countEdit = false;
      }
    },
    refreshPageRequest: function(page = 1) {
      this.pageRequest.page = page;
      this.findPage();
    },
    refreshPageSizeRequest: function(pageSize) {
      this.pageRequest.pageSize = pageSize;
      this.findPage();
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    edit(rows) {
      if (this.btnState == false) {
        this.$router.push({
          path: "/cgdd/cgBuyOrderDetail",
          query: {
            id: rows.id,
            isEdit: true,
            contractId: rows.contractId
          }
        });
      }
    },
    searchquery() {
      if (this.applyDate) {
        this.search.push({
          property: "productNum",
          value: this.applyDate,
          operation: "BETWEEN",
          relation: "AND"
        });
      }
      if (this.gongid) {
        this.search.push({
          property: "supplierId",
          value: this.gongid,
          operation: "EQUAL",
          relation: "AND"
        });
      }
      this.findPage(this.search);
    },
    //列表
    findPage(search) {
      this.loading = true;
      this.$api.zsj
        .getZsj({
          url: "/scm/scmCgProReplacementOrder/v1/list",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys: search,
            sorter: []
          }
        })
        .then(res => {
          if (res) {
            this.tableData3 = res.rows;
            this.total = res.total;
            this.loading = false;
            this.search = [];
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
      this.firstStep = false;
      setTimeout(() => {
        this.secondStep = true;
      },200)
        this.getReplacementOrder();
    },
    goback() {
      this.secondStep = false;
      setTimeout(() => {
        this.firstStep = true;
      },200)
    },
    getDetail(id){
      this.$api.approval.getscm({
        url:'/scm/scmCgProcurementOrder/v1/get/'+id
      })
      .then(res => {
        if (res.state === false) {
          this.$message({
            message: "操作失败, " + res.shortMessage,
            type: "error",
            duration: 800
          });
          return;
        }
        this.detailInfo = Object.assign({},res)
      })
    },
    sendApply() {//发起申请发起申请

      return;

      this.$api.approval
      .postscm({
         url: "/scm/scmCgProcurementOrder/v1/sendApply",
         data:{
           id: this.fisrtStepId
         }
      })
      .then(res => {
        if (res.state === false) {
          this.$message({
            message: "操作失败, " + res.shortMessage,
            type: "error",
            duration: 800
          });
          return;
        }
        this.$message({
            message:  res.shortMessage,
            type: "success",
            duration: 800
          });
      })
    },
    getSecondTable(){
      this.loading2 = true;
      this.$api.approval
      .postscm({
          url: "/scm/scmCgProRepProduct/v1/list",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys:[
              {property: "proOrderId",value:this.fisrtStepId, operation: "EQUAL", relation: "AND"},
            ],
            sorter: []
          }
        })
        .then(res => {
          if (res) {
            this.secondTableData = res.rows;
            this.total = res.total;
            this.loading2 = false;
            this.search = [];
          } else {
            this.$message({
              message: "操作失败, " + res.shortMessage,
              type: "error",
              duration: 800
            });
          }
        });
    },
    getDownBox(){
      this.$api.approval
      .postscm({
          url: "/scm/scmCgContractProduct/v1/list",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys:[
              {property: "contractId",value:this.$route.query.contractId, operation: "EQUAL", relation: "AND"},
            ],
            sorter: []
          }
        })
        .then(res => {
          if (res.state == false) {
            this.$message({
              message:  res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          };
          this.resultList = res.rows.reduce((final, item, index, arr)=>{
            item.value = item.productName+"|"+item.productCode+"|"+item.productCname+"|"+item.productGauge+"|"+item.medicineType;
            final.push(item);
            return final;
          },[]);
        });
    },
    querySearch(queryString, cb){
      var result = this.resultList;
      var results = queryString ? result.filter(this.createFilter(queryString)) : result
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item){
      this.addForm = Object.assign(this.addForm,item)
    },
    addProduct(){
      if (this.$refs["addForm"]!==undefined) {
          this.$refs["addForm"].resetFields();
      }
      this.dialogVisible = true;
    },
    getData() {
        this.$api.zsj
        .changeState({
          url: "/scm/scmCgProcurementOrder/v1/get/" + this.$route.query.id
        })
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: "操作失败, " + res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.wrapForm = Object.assign({}, res);
          this.tableData = res.scmCgProOrderProductList;
        });
    },
    getReplacementOrder() {
      this.$api.base.postScm({
        url:"/scm/scmCgProReplacementOrder/v1/save",
        data: {
          ownerId: this.ownerId+'',
          orderId: this.$route.query.id+''
        }
      }).then(res => {
        if(res.state == false){
          this.$message({
            message: "操作失败," + res.shortMessage,
            type: "error",
            duration: 800
          })
        }else{
            this.fisrtStepId = res.value.id;
        }
      })
    }
  },
  mounted() {
    this.ownerId = storage.get("ownerId");
    this.findPage();
    this.getData();
    this.getDownBox();


  }
};
</script>
<style scoped="scoped" lang="scss">
.header {
  width: 100%;
  height: 38px;
  font-size: 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f3f3f3;
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
  background: none !important ;
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
.head-item {
  border: 1px solid #f2f2f2;
}
.content-item {
  // border: 1px solid #f2f2f2;
  margin-top: 8px;
}
.main-content {
  padding: 0 !important;
}
.success {
  // width: 350px;
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
</style>
