<template>
	<div>
		<div class="searchHeader">
      <div class="searchheadcenter">回款勾兑申请</div>
    </div>
    <el-container>
  	  <el-header>
  	  	<div class="header">
          <div class="headleft">
            <span class="el-icon-search"></span>
            <span class="sx">筛选查询</span>
          </div>
          <div class="headright">
            <el-row>
              <el-button type="primary" class="el-icon-search" size="mini" @click="searchs">查询</el-button>
            </el-row>
          </div>
        </div>
  	  </el-header>
  	  <el-main>
  	  	<el-form size="small" label-width="120px" class="demo-ruleForm" :model="searchForm" ref="searchForm">
          <el-form-item label="商务分区" prop="businessDivisionId">
            <el-select clearable style="width: 100%" v-model="searchForm.businessDivisionId" placeholder="请选择" @change="getPrivence">
              <el-option v-for="item in divisionList" :key="item.id" :label="item.businessDivision" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="省区" prop="provinceId">
            <el-select clearable style="width: 100%" v-model="searchForm.provinceId" placeholder="请选择">
              <el-option v-for="item in searchProvinceList" :key="item.id" :label="item.province" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="回款方式" prop="remittanceType">
            <el-select clearable style="width: 100%" v-model="searchForm.remittanceType" placeholder="请选择">
              <el-option v-for="item in options" :key="item.id" :label="item.label" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="到款日期" prop="remittanceDate">
            <div class="block">
              <el-date-picker
                v-model="searchForm.remittanceDate"
                type="daterange"
                align="right"
                unlink-panels
                range-separator="起"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"
              ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="商业名称" prop="commerceName">
            <el-input :rows="1" placeholder="请输入内容" v-model="searchForm.commerceName"></el-input>
          </el-form-item>
        </el-form>
        <div class="header">
          <div class="headleft">
            <span class="el-icon-tickets"></span>
            <span>数据列表</span>
          </div>
        </div>
        <el-table v-loading="loading" :data="tableData" border stripe element-loading-text="加载中" style="width: 100%;" max-height="250">
        	<el-table-column type="index" width="50"></el-table-column>
          <el-table-column v-for="(item,index) in columns" header-align="center" align="center" :key="index" :prop="item.prop" :label="item.label" width="120"></el-table-column>
          <el-table-column label="操作" width="200" fixed="right" header-align="center" align="center">
          	<template slot-scope="scope">
	            <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">核销</el-button>
	            <el-button size="mini" type="primary" @click="detail(scope.$index, scope.row)">查看详情</el-button>
	          </template>
	        </el-table-column>
        </el-table>
        <!--分页-->
        <div class="pagination">
          <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageRequest.page"
            :page-sizes="[10, 20, 30, 40,50]"
            :page-size="pageRequest.pageSize"
            layout="total, sizes, prev, pager, next"
            :total="total"
          ></el-pagination>
        </div>
        <!--查看详情-->
        <div v-show="page">
        	<div class="header detail">
	          <div class="headleft">
	            <span class="el-icon-tickets"></span>
	            <span>数据列表</span>
	          </div>
	        </div>
	        <el-table v-loading="loading" :data="tableData2" border stripe element-loading-text="加载中" style="width: 100%;" max-height="250">
	          <el-table-column v-for="(item,index) in columns2" header-align="center" align="center" :key="index" :prop="item.label" :label="item.label" width="120">
	          	<template slot-scope="scope">
	          		<el-checkbox v-if="item.value" :checked="scope.row[item.prop] == '1'" center></el-checkbox>
	          		<span v-if="!item.value">{{ scope.row[item.prop] }}</span>
	          	</template>
	          </el-table-column>
	        </el-table>
	        <!--分页-->
	        <div class="pagination">
	          <el-pagination
	            background
	            @size-change="handleSizeChange2"
	            @current-change="handleCurrentChange2"
	            :current-page="pageRequest2.page"
	            :page-sizes="[10, 20, 30, 40,50]"
	            :page-size="pageRequest.pageSize"
	            layout="total, sizes, prev, pager, next"
	            :total="total2"
	          ></el-pagination>
	        </div>
        </div>
        <!--核销弹出层-->
        <el-dialog title="发票明细" :visible.sync="dialogTableVisible" width="80%" center>
				  <el-table v-loading="loading" :data="tableData3" border stripe element-loading-text="加载中" style="width: 100%;" max-height="250">
	          <el-table-column v-for="(item,index) in columns3" header-align="center" align="center" :key="index" :prop="item.prop" :label="item.label" width="120">
	          	<template slot-scope="scope">
	          		<el-checkbox v-if="item.prop == 'verifyType'" v-model="scope.row.isChecked"></el-checkbox>
	          		<span v-if="item.prop !== 'verifyType' && item.prop !== 'blendprice' && item.prop !== 'memo'">{{ scope.row[item.prop] }}</span>
	          		<el-input v-if="item.prop == 'blendprice' || item.prop == 'memo'" v-model="scope.row[item.prop]" placeholder="请输入"></el-input>
	          	</template>
	          </el-table-column>
	        </el-table>
	        <div class="pagination">
          <el-pagination
	            background
	            @size-change="handleSizeChange1"
	            @current-change="handleCurrentChange1"
	            :current-page="pageRequest1.page"
	            :page-sizes="[10, 20, 30, 40,50]"
	            :page-size="pageRequest1.pageSize"
	            layout="total, sizes, prev, pager, next"
	            :total="total1"
	          ></el-pagination>
	        </div>
	        <span slot="footer" class="dialog-footer">
				    <el-button type="primary" @click="dialogTableVisible = false">取消核销</el-button>
				    <el-button type="primary" @click="verify">确认核销</el-button>
				    <el-button type="primary" @click="dialogTableVisible = false">关闭</el-button>
				  </span>
				</el-dialog>
        
  	  </el-main>
  	</el-container>
	</div>
</template>
<script>
	import storage from "@/utils/storage";
	export default{
		name:'remittanceApply',
		data(){
			return{
				searchProvinceList:[],//省区列表
      	divisionList:[],//商务分区列表
      	tableData:[],//列表数据
      	tableData2:[],//详情列表
      	tableData3:[],//核销列表
      	search:[],
      	page:false,
      	dialogTableVisible:false,
      	//回款方式数据
	      options: [
	        {
	          id: "电汇",
	          label: "电汇"
	        },
	        {
	          id: "银承",
	          label: "银承"
	        },
	        {
	          id: "折让",
	          label: "折让"
	        },
	        {
	          id: "特求承兑",
	          label: "特求承兑"
	        }
	      ],
				total: 0,
				total1: 0,
				total2:0,
				pageRequest:{
	      	page:1,
	      	pageSize:10,
	      	showTotal:true
	      },
	      pageRequest1:{
	      	page:1,
	      	pageSize:10,
	      	showTotal:true
	      },
	      pageRequest2:{
	      	page:1,
	      	pageSize:10,
	      	showTotal:true
	      },
	      verifyData:{},
	      detailData:{},
	      loading:false,
	      //查询筛选数据
	      searchForm: {
	        verifyType:"",//核销状况
	        remittanceDate: "", //日期
	        businessDivisionId: "", // 商务分区
	        provinceId:"",//省区
	        commerceName:""//商业名称
	      },
	      //勾兑申请
	      columns:[
	      	{prop:'getMoth',label:'月度'},
	      	{prop:'acceptExpire',label:'到款日期'},
	      	{prop:'commerceName',label:'回款商业名称'},
	      	{prop:'businessDivision',label:'商务分区'},
	      	{prop:'province',label:'省区'},
	      	{prop:'remittanceType',label:'回款方式'},
	      	{prop:'price',label:'回款金额'},
	      	{prop:'verifyType',label:'核销'},
	      	{prop:'balancePrice',label:'剩余'},
	      	{prop:'memo',label:'备注'}
	      ],
	      //详情
	      columns2:[
	      	{prop:'invoiceDate',label:'开票日期'},
	      	{prop:'',label:'参照'},
	      	{prop:'',label:'SAP匹配'},
	      	{prop:'',label:'金税发票'},
	      	{prop:'commonName',label:'产品名称'},
	      	{prop:'productState',label:'规格'},
	      	{prop:'unitPrice',label:'单价'},
	      	{prop:'productSum',label:'数量'},
	      	{prop:'priceTaxSum',label:'应收金额'},
	      	{prop:'shipmentDate',label:'发货日期'},
	      	{prop:'contractCode',label:'合同编号'},
	      	{prop:'verifyType',label:'核销',value:'hx'},
	      	{prop:'blendprice',label:'回款核销金额'},
	      	{prop:'balancePrice',label:'剩余'},
	      	{prop:'memo',label:'核销备注'},
	      	{prop:'blendPersion',label:'核销人'},
	      	{prop:'blendDate',label:'核销时间'},
	      	{prop:'affirmPersion',label:'确认人'},
	      	{prop:'affirmDate',label:'确认时间'}
	      ],
	      //核销
	      columns3:[
	      	{prop:'invoiceDate',label:'开票日期'},
	      	{prop:'',label:'参照'},
	      	{prop:'',label:'SAP匹配'},
	      	{prop:'',label:'金税发票'},
	      	{prop:'commonName',label:'产品名称'},
	      	{prop:'productState',label:'规格'},
	      	{prop:'unitPrice',label:'单价'},
	      	{prop:'productSum',label:'数量'},
	      	{prop:'priceTaxSum',label:'应收金额'},
	      	{prop:'shipmentDate',label:'发货日期'},
	      	{prop:'contractCode',label:'合同编号'},
	      	{prop:'verifyType',label:'核销'},
	      	{prop:'blendprice',label:'回款核销金额'},
	      	{prop:'balancePrice',label:'剩余'},
	      	{prop:'memo',label:'核销备注'}
	      ],
			}
		},
		mounted() {
	  	//获取货主id
	    this.ownerId = storage.get("ownerId");  
			//获取列表数据
	    this.getTableList();
	    //获取商务分区数据
	    this.getDivision ();
	  },
		methods:{
			handleSizeChange(val){
				this.pageRequest.pageSize = val;
				this.getTableList();
			},
			handleCurrentChange(val){
				this.pageRequest.page = val;
				this.getTableList();
			},
			handleSizeChange1(val){
				this.pageRequest1.pageSize = val;
				this.getVerify(this.verifyData);
			},
			handleCurrentChange1(val){
				this.pageRequest1.page = val;
				this.getVerify(this.verifyData);
			},
			handleSizeChange2(val){
				this.pageRequest2.pageSize = val;
				this.getDetail(this.detailData);
			},
			handleCurrentChange2(val){
				this.pageRequest2.page = val;
				this.getDetail(this.detailData);
			},
			//查询
			searchs(){
				let query = [];
      	let searchForm = Object.assign({},this.searchForm);
      	for(var key in searchForm){
      		if(searchForm[key]){
      			if(key === "remittanceDate"){ //日期
      				query.push({
	              hasInitValue: false,
	              operation: "BETWEEN",
	              relation: "AND",
	              value: searchForm[key],
	              property: key
	            }); 
      			} else if(key === "businessDivisionId") { //商务分区
      				query.push({ 
	              hasInitValue: false,
	              operation: "EQUAL",
	              relation: "AND",
	              value: searchForm[key],
	              property: key
	            }); 
      			} else if(key === "provinceId"){ //省区
      				query.push({ 
	              hasInitValue: false,
	              operation: "EQUAL",
	              relation: "AND",
	              value: searchForm[key],
	              property: key
	            });
      			}else if(key === "commerceName"){ //商业名称
      				query.push({ 
	              hasInitValue: false,
	              operation: "EQUAL",
	              relation: "AND",
	              value: searchForm[key],
	              property: key
	            });
      			}else if(key === "remittanceType"){ //回款方式
      				query.push({ 
	              hasInitValue: false,
	              operation: "EQUAL",
	              relation: "AND",
	              value: searchForm[key],
	              property: key
	            });
      			}
      		}
      	}
      	this.getTableList(query);
			},
			//获取列表信息
	    getTableList(query = []) {
	      let _this = this;
	      let postData = [
	        {
	          operation: "EQUAL",
	          property: "ownerId",
	          relation: "AND",
	          value: _this.ownerId
	        }
	      ];
	      if (query.length !== 0) {
	        postData = postData.concat(query);
	      }
	      _this.$api.xsgl
	        .post({
	          vueInstance: _this,
	          url: "/scm/scmCwReturnMoney/v1/list",
	          data: {
	            pageBean: _this.pageRequest,
	            params: {},
	            querys: postData,
	            sorter: []
	          }
	        })
	        .then(res => {
	          console.log("Lists", res);
	          _this.total = res.total;
	          res.rows.map((v,i)=>{
	          	//时间戳转换
	          	v.acceptExpire = _this.timer(v.acceptExpire);
	          	//核销状态转换
	          	if(v.verifyType == 0){v.verifyType = '未核销';}
	          	else if(v.verifyType == 1){v.verifyType = '核销中';}
	          	else if(v.verifyType == 2){v.verifyType = '已核销';}
	          });
	          _this.tableData = res.rows;
	        });
	    },
	    //时间戳转日期
	    timer(val){
	    	var date = new Date(val);
				let Y = date.getFullYear() + '-';
				let M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
				let D = date.getDate();
				let time = Y+M+D;
				return time;
	    },
			//获取商务分区
	    getDivision () {
	        let self = this;
	        self.$api.xsgl.getZdBusinessDivision({vueInstance: self}, function(res) {
	            self.divisionList = res;
	        });
	    },
			// 获取省区
	    getPrivence () {
	        this.searchForm.provinceId= ''
	        let divisionId = this.searchForm.businessDivisionId // 商务分区ID
	        this.searchProvinceList = this.divisionList.reduce((final, item, index, arr)=>{
	            if(item.id == divisionId){
	                final = item.provinceList;
	            }
	            return final
	        },[]);
	    },
	    //核销
	    handleEdit(index,row){
	    	console.log(row)
	    	this.dialogTableVisible = true;
	    	this.verifyData = row;
	    	this.getVerify(this.verifyData);
	    },
	    //获取核销数据
	    getVerify(row){
	    	let _this = this;
	    	_this.$api.xsgl
	        .post({
	          vueInstance: _this,
	          url: "/scm/scmCwInvoice/v1/list",
	          data: {
	            pageBean: _this.pageRequest1,
	            params: {},
	            querys: [
									{property: "commerceId", value: row.commerceId, operation: "EQUAL", relation: "AND"},
									{property: "verifyType", value: "0", operation: "EQUAL", relation: "AND"},
									{property: "ownerId", value: _this.ownerId, operation: "EQUAL", relation: "AND"}
	            ],
	            sorter: []
	          }
	        })
	        .then(res => {
	          console.log("Lists1", res);
	          _this.total1 = res.total;
	          _this.tableData3 = res.rows.map(v => {
	          	//开票日期
	          	v.invoiceDate = _this.timer(v.invoiceDate);
	          	//发货日期
	          	v.shipmentDate = _this.timer(v.shipmentDate);
	          	v.isChecked = false;
	          	v.returnMoneyId = row.id;
	          	return v
	          })
	        });
	    },
	    //查看详情
	    detail(index,row){
	    	this.page = true;
	    	this.detailData = row;
	    	this.getDetail(this.detailData);
	    },
	    getDetail(row){
	    	let _this = this;
	    	_this.$api.xsgl
	        .post({
	          vueInstance: _this,
	          url: "/scm/scmCwInvoice/v1/history",
	          data: {
	            pageBean: _this.pageRequest2,
	            params: {},
	            querys: [{property: "returnMoneyId", value: row.id, operation: "NOT_EQUAL", relation: "AND"}],
	            sorter: []
	          }
	        })
	        .then(res => {
	          console.log("Lists1", res);
	          _this.total2 = res.total;
	          res.rows.map(v => {
	          	//开票日期
	          	v.invoiceDate = _this.timer(v.invoiceDate);
	          	//发货日期
	          	v.shipmentDate = _this.timer(v.shipmentDate);
	          })
	          _this.tableData2 = res.rows;

	        });
	    },
	    //确认核销
	    verify(){
	    	let _this = this;
				let params = this.tableData3.filter(v => v.isChecked)
				.map(v => {
					if(v.blendprice){
						let obj = {};
						obj.memo = v.memo;
						obj.invoiceId = v.id;
						obj.returnMoneyId = v.returnMoneyId;
						obj.blendprice = v.blendprice;
						return obj
					}else{
						//错误提示
			  		this.$alert('请输入回款核销金额', '错误提醒', {
		          confirmButtonText: '确定',
		          callback: action => {}
		        });
					}
				})
	    	if(params.length !== 0){
	    		if(params.indexOf(undefined) == -1){
	    			this.$api.xsgl
			        .post({
			          vueInstance: _this,
			          url: "/scm/scmCwBlend/v1/charge",
			          data: params
			        })
			        .then(res => {
								if(res.state){
									_this.dialogTableVisible = false;
									this.$message({
					          message: res.message,
					          type: 'success'
					        });
								}else{
									this.$message({
					          message: res.message,
					          type: 'success'
					        });
								}
			        });
	    		}
	    	}else {
	    		//错误提示
		  		this.$alert('请勾选需要核销的数据', '错误提醒', {
	          confirmButtonText: '确定',
	          callback: action => {}
	        });
	    	}
	    },
		}
	}
</script>
<style scoped lang="scss">
	.detail{margin-top: 20px;}
	.header {
	  width: 100%;
	  height: 38px;
	  font-size: 14px;
	  display: flex;
	  justify-content: space-between;
	  align-items: center;
	  background: #f3f3f3;
	 .headleft {
			margin-left: 10px;
	  }
	}
	.el-header {
	  padding: 0 !important;
	}
	.el-form {
	  display: flex;
	  flex-direction: row;
	  flex-wrap: wrap;
	  .el-form-item {
	    width: 33%;
	  }
	}
	.el-main {
	  padding: 0 !important;
	}
	/*下拉菜单样式*/
	.el-dropdown-link {
	  cursor: pointer;
	  color: #409eff;
	}
	.el-icon-arrow-down {
	  font-size: 12px;
	}
	.pagination {
	  margin-top: 20px;
	  display: flex;
	  justify-content: flex-end;
	}
	.block {
	  .el-date-editor.el-range-editor {
	    width: 100%;
	  }
	}
	.searchHeader {
	  position: relative;
	  width: 100%;
	  font-size: 28px;
	  font-weight: bold;
	  background: #f3f3f3;
	  margin-bottom: 7px;
		.searchheadcenter {
		  width: 100%;
		  text-align: center;
		  line-height: 1.5em;
		}
	}
</style>