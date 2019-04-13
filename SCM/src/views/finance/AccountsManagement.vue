<template>
	<div>
		<transition name="el-fade-in">
			<div v-show="false">
				<div class="searchHeader">
		      <div class="searchheadcenter">应付账款记账管理</div>
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
		          <el-form-item label="供应商名称" prop="businessDivisionId">
		            <el-select clearable style="width: 100%" v-model="searchForm.businessDivisionId" placeholder="请选择">
		              <el-option v-for="item in divisionList" :key="item.id" :label="item.businessDivision" :value="item.id"></el-option>
		            </el-select>
		          </el-form-item>
		          <el-form-item label="合同号" prop="provinceId">
		            <el-input :rows="1" placeholder="请输入内容" v-model="searchForm.commerceName"></el-input>
		          </el-form-item>
		          <el-form-item label="发票号码" prop="remittanceType">
		            <el-input :rows="1" placeholder="请输入内容" v-model="searchForm.commerceName"></el-input>
		          </el-form-item>
		          <el-form-item label="记账日期" prop="remittanceDate">
		            <div class="block">
		              <el-date-picker
		                v-model="searchForm.remittanceDate"
		                type="daterange"
		                align="right"
		                value-format="yyyy-MM-dd"
		              ></el-date-picker>
		            </div>
		          </el-form-item>
		        </el-form>
		        <div class="header">
		          <div class="headleft">
		            <span class="el-icon-tickets"></span>
		            <span>数据列表</span>
		          </div>
		          <div class="headright">
			          <el-row>
			            <el-button type="primary" size="mini">新增</el-button>
			          </el-row>
			        </div>
		        </div>
		        <el-table v-loading="loading" :data="tableData" border stripe element-loading-text="加载中" style="width: 100%;" max-height="250">
		          <el-table-column v-for="(item,index) in columns" header-align="center" align="center" :key="index" :prop="item.prop" :label="item.label" width="120"></el-table-column>
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
		      </el-main>
		    </el-container>
			</div>
		</transition>
		<!--填写付款信息-->
		<transition name="el-fade-in">
			<div v-show="true">
				<el-row class="searchHeader1">
	        <el-col :span="1" style="text-align: right;">
	          <el-button size="mini" class="el-icon-back" type="primary">返回</el-button>
	        </el-col>
	        <el-col :span="6" :offset="8">
	          <b>付款申请</b>
	        </el-col>
	        <el-col :span="9" style="text-align: right;">
	          <el-button size="mini" type="primary" v-show="true">下一步</el-button>
	        </el-col>
	     	</el-row>
	     	<el-row>
        	<el-steps :align-center="true" :active="1" finish-status="success" style="margin: 35px auto;">
	          <el-step title="填写付款信息"></el-step>
	          <el-step title="添加付款明细"></el-step>
	          <el-step title="完成"></el-step>
	        </el-steps>
	      </el-row>
	      <transition>
	      	<payMsg></payMsg>
	      </transition>
			</div>
		</transition>
		<!--添加付款明细-->
		<transition>
			
		</transition>
		<!--完成-->
		<transition>
			
		</transition>
	</div>
</template>
<script>
	import storage from "@/utils/storage";
	import payMsg from "./payMsg"
	export default{
		name:'',
		components:{payMsg:payMsg},
		data(){
			return {
				total:0,
				tableData:[],
				columns:[
					{prop:'',label:'合同号'},
					{prop:'',label:'发票号码'},
					{prop:'',label:'供应商编码'},
					{prop:'',label:'供应商名称'},
					{prop:'',label:'联系人'},
					{prop:'',label:'应付金额'},
					{prop:'',label:'税额差额'},
					{prop:'',label:'无税差额'},
					{prop:'',label:'税额'},
					{prop:'',label:'记账日期'},
					{prop:'',label:'采购员'},
				],
				searchForm: {
	        businessDivisionId: "", // 商务分区
	      },
				pageRequest:{
	      	page:1,
	      	pageSize:10,
	      	showTotal:true
	      },
	      divisionList:[],
	      loading:false,
			}
		},
		mounted(){},
		methods:{
			handleSizeChange(val){},
			handleCurrentChange(val){},
			searchs(){},
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
	  .headright {
		  margin-right: 10px;
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
	.searchHeader1 {
	  padding: 5px;
	  background: #f3f3f3;
	  div:first-child {
	    height: 28px;
	    line-height: 1.5em;
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