<template>
	<div>
			<div v-show="startShow">
		  	<div class="header">
				<div class="headleft"><span class="el-icon-search"></span><span class="sx">筛选查询</span><span class="el-icon-caret-bottom"></span></div>
				<div class="headright">
					<el-row>
					  <el-button type="primary" class="el-icon-search" size="mini" @click="searchquery">查询</el-button>
					</el-row>
				</div>
			</div>
		  	<el-form :label-position="labelPosition" label-width="120px" class="demo-ruleForm">
				<el-form-item  label="供应商名称">
					<el-select v-model="gongname" clearable placeholder="请选择供应商名称" @change="getgongname">
					    <el-option
					      v-for="item in gongoption"
					      :key="item.id"
					      :label="item.supplierName"
					      :value="item.supplierName">
					    </el-option>
					 </el-select>
				</el-form-item>
				<el-form-item label="申请日期">
						<el-date-picker
					      v-model="applyDate"
					      value-format="yyyy-MM-dd HH:mm:ss"
					      type="daterange"
					      range-separator="至"
					      start-placeholder="开始日期"
					      end-placeholder="结束日期">
    					</el-date-picker>
				</el-form-item>
			</el-form>
		  <div class="header">
				<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">数据列表</span><span class="el-icon-caret-bottom"></span></div>
				<div class="headright">
					 <el-button type="primary" class="el-icon-circle-plus" size="mini" @click="buy">采购合同申请</el-button>
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
			      	 <router-link :to="{path:'/cgdd/cgContractDeial',query:{id:scope.row.id}}"><el-button class="el-icon-view" type="primary" size="mini">查看详情</el-button></router-link> 
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
			<!--第一步-->
			<div class="head-item" v-show="one">
						 <el-row class="searchHeader">
						 	 <el-col :span="1" style="text-align: right;">
				              <el-button  size="mini" class="el-icon-back" type="primary" @click="goback">返回</el-button>
				            </el-col>
				            <el-col :span="5" :offset="9"><b>供货单位合同签订申请</b></el-col>
				            <el-col :span="9" style="text-align: right;">
				              <el-button  size="mini" type="primary" v-show="oneShow" @click="nextStep()">下一步</el-button>
				              <el-button  size="mini" type="primary" v-show="twoShow" @click="prevStep">上一步</el-button>
				              <el-button  size="mini" type="primary" v-show="threeShow" @click="applySave">申请暂存</el-button>
				              <el-button  size="mini" type="primary" v-show="fourShow" @click="sendApply">发起申请</el-button>
				            </el-col>
				         </el-row>
				         <el-row>
			          			<el-steps :space="500" align-center=true :active="active" finish-status="success" style="margin: 35px auto;">
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
							  	 <el-form-item  label="供应商名称" prop="applyname">
										<el-select v-model="wrapForm.applyname" clearable placeholder="请选择供应商名称" @change="getapplyname">
										    <el-option
										      v-for="item in applyoption"
										      :key="item.id"
										      :label="item.supplierName"
										      :value="item.supplierName">
										    </el-option>
										 </el-select>
								 </el-form-item>
								  <el-form-item  label="币种" prop="bzname">
										<el-select v-model="wrapForm.bzname" clearable placeholder="请选择币种">
										    <el-option
										      v-for="item in bzoption"
										      :key="item.inx"
										      :label="item.name"
										      :value="item.name">
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
						<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">添加采购</span><span style="color:#8400FF">澳美只要长</span>商品信息,合同号为<span style="color:#8400FF">【BF17】</span>。已选择<span style="color:#8400FF">【5】</span>种产品,采购总金额为<span style="color:#8400FF">【200000】</span></div>
						<div class="headright">
							 <el-button type="primary" class="el-icon-circle-plus" size="mini" @click="buy">新增产品</el-button>
						</div>
				    </div>
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
					      minwidth="100">
					      <template slot-scope="scope">
					        <el-button type="primary" class="el-icon-edit" size="mini" @click="edit(scope.row)">编辑</el-button>
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
			<!--第三步-->
			<transition name="el-fade-in">
				<div class="content-item" v-show="four">
					<div class="success">
						<span class="el-icon-check"></span>
						您已完成<span style="color: #8400FF;">【澳美制药厂】</span>供货合同签订
					</div>
					<div class="success-bottom">
						合同已申请<span style="color: #8400FF;">【5】</span>种供货产品,总金额为<span style="color:#8400FF;">[200000]</span>CNY
					</div>
				</div>
			</transition>		
	</div>
</template>
<script>
import storage from '@/utils/storage'
export default {
	name:"goods",
    data() {
      return {
      	column:[
	      	{prop:"contractCode",label:"合同编号",minwidth:"200",align:"center"},
		    {prop:"supplier",label:"供应商名称",minwidth:"150",align:"center"},
			{prop:"speciesSum",label:"供货产品种数品种数",minwidth:"200",align:"center"},
			{prop:"productSum",label:"数量",minwidth:"100",align:"center"},
			{prop:"sumPrice",label:"金额",minwidth:"100",align:"center"},
			{prop:"currency",label:"币种",minwidth:"100",align:"center"},
			{prop:"filingDate",label:"申请日期",minwidth:"200",align:"center"},
			{
				prop:"approvalState",
				label:"审批状态",
				minwidth:"150",
				align:"center",
				formatter:function(row, column, cellValue, index){
					if(row.approvalState==0){
						return "未提交"
					}
					if(row.approvalState==1){
						return "审批中"
					}
					if(row.approvalState==2){
						return "已通过"
					}
				}
			},
	    ],
	    btnState:false,
	    startShow:true,
	    one:false,
	    two:false,
	    three:false,
	    four:false,
	    active:0,
	    oneShow:true,
	    twoShow:false,
	    threeShow:false,
	    fourShow:false,
        restaurants: [],
        applyDate:"",
        labelPosition:"right",
        loading2:true,
        ownerId:"",
        total:0,
        operation:true,
        dialogTableVisible: false,
        gongid:"",
        applyid:"",
	    gongoption:[],
	    applyoption:[],
	    tableData3: [],
	    bzoption:[{"name":"CNY","inx":1}],
        checked:false,
        loading: false,
        search:[],
        pageRequest: {
	        page: 1,
	        pageSize: 10,
	        showTotal: true,
        },
           wrapForm: {
		        applyname: '',
		        bzname: '',
		        },
		        wraprules:{
			        applyname: [
			        	{required: true, message: '请选择供应商', trigger: 'blur' },
			            {max: 20, message: '最多输入20个字符', trigger: 'blur' }
		        	],
			        bzname: [
			        	{required: true, message: '请选择币种', trigger: 'blur' },
			            {max: 50, message: '最多输入20个字符', trigger: 'blur' }
		        	],
		        }
      }
    },
    methods: {
      refreshPageRequest: function(page = 1) {
	      this.pageRequest.page = page;
	      this.findPage();
      },
      refreshPageSizeRequest: function(pageSize) {
	      this.tableData3.pageSize = pageSize;
	      this.findPage();
	   },
      getgongname(){
      	//获取供应商id
      	if(this.gongoption.length!=0){
      		var data= this.gongoption.filter((item)=>{
     		if(item.supplierName == this.gongname){
     			this.gongid=item.id
     			console.log(this.gongid)
     		}
      	})
		}
      },
       getapplyname(){
      	//获取供应商id
      	if(this.applyoption.length!=0){
      		var data= this.applyoption.filter((item)=>{
     		if(item.supplierName == this.wrapForm.applyname){
     			this.applyid=item.id
     			console.log(this.applyid)
     		}
      	})
		}
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      edit(rows){
      	if(this.btnState==false){
      		this.productFirstId=rows.id
      		this.$router.push({
      			path: '/cgdd/cgContractDeial',
		        query: {
		            htid: this.productFirstId
		        }
      		})
      	}
      },
      searchquery(){
      	if(this.applyDate){
      		this.search.push({property:"productNum",value:this.applyDate,operation:"BETWEEN",relation:"AND"})
      	}
      	if(this.gongid){
      		this.search.push({property:"supplierId",value:this.gongid,operation:"EQUAL",relation:"AND"})
      	}
      	console.log(this.search)
      	this.findPage(this.search)
      },
         //列表
      findPage(search){
		      this.loading = true;
		        this.$api.zsj.getZsj({
		            url: "/scm/scmCgProcurementContract/v1/list",
		            data:{ 
		            	pageBean: this.pageRequest,
			            params: {},
			            querys: search,
			            sorter: []
			            },
		        }).then(res => {
		            if (res) {
		        		this.tableData3=res.rows;
		        		this.total=res.total;
		        		this.loading2 = false;
		        		this.search=[]
		              } else {
		              this.$message({
		                  message: "操作失败, " + res.shortMessage,
		                  type: "error",
		                  duration: 800
		              });
		            }
		        });
		 },
      buy(){
      	this.startShow=false;
      	this.one=true;
      	this.two=true;
      },
      nextStep(){
      	this.$refs.wrapForm1.validate(valid => {
     		if(valid){
				      	   var data={supplier:this.wrapForm.applyname,supplierId:this.applyid,currency:this.wrapForm.bzname,ownerId:this.ownerId}
				      	   this.$api.zsj.getZsj({
						            url: "/scm/scmCgProcurementContract/v1/save",
						            data:data,
						        }).then(res => {
						            if (res.state==false) {
						            	this.$message({
						                  message: "操作失败, " + res.shortMessage,
						                  type: "error",
						                  duration: 800
						              	});
						              } else {
						               	this.active=1;
								      	this.one=true;
								      	this.two=false;
								      	setTimeout(()=>{
								      		this.three=true;
									      	this.oneShow=false;
									      	this.twoShow=true;
									      	this.threeShow=true;
									      	this.fourShow=true;
								      	},220)
						            }
						     });
     		}
		 })
      },
      prevStep(){
      	this.three=false;
      	this.active=0;
      	setTimeout(()=>{
  		  	this.one=true;
	      	this.two=true;
	      	this.oneShow=true;
	      	this.twoShow=false;
	      	this.threeShow=false;
	      	this.fourShow=false;
      	},220)
      },
      goback(){
      	this.startShow=true;
      	this.one=false;
      	this.two=false;
      	this.oneShow=true;
      	this.twoShow=false;
      	this.threeShow=false;
      	this.fourShow=false;
      	this.three=false;
      	this.resetForm('wrapForm1')
      },
      applySave(){
      	
      },
      sendApply(){
      	
      },
    },
    mounted() {
      this.ownerId = storage.get('ownerId')
      this.findPage();
         //供应商
         this.$api.zsj.changeState({
			            url: "/scm/scmZsjSupplier/v1/downBoxApplySuccess/"+this.ownerId,
		        }).then(res => {
		            if (res) {
		            	this.gongoption=res
		            	this.applyoption=res
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
	.header{
		width: 100%;
		height: 38px;
		font-size: 16px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		background: #F3F3F3;
	}
	.headleft{
		margin-left: 10px;
	}
	.headright{
		margin-right: 10px;
	}
	.sx{
		margin:auto 5px;
	}
	.pagination{
		margin-top: 20px;
		display: flex;
		justify-content: flex-end;
	}
	.el-select{
		width:100%;
	}
	.head{
		background:none !important ;
	}
	.tanFoot{
		margin-top: 15px;
		display: flex;
	}
	.savebox{
		margin-left: 20px;
	}
	.el-form{
	  display: flex;
	  flex-direction: row;
	  flex-wrap: wrap;
	  .el-form-item{
	    width: 33%;
	  }
	}
	.el-table .cell{
		white-space: nowrap !important;
	}
	header{
		height: 40px !important;
	}
	.el-form-item{
		margin-top:10px;
	}
.searchHeader{
  padding: 5px;
  background: #f3f3f3;
  div:first-child{
    height: 28px;
    line-height: 28px;
  }
  b{
    font-size: 24px;
  }
  i{
    margin-right: 5px;
    margin-left: 5px;
  }
}
.head-item{
	border: 1px solid #f2f2f2;
}
.content-item{
	border: 1px solid #f2f2f2;
	margin-top: 8px;
}
.main-content{
	padding: 0 !important;
}
.success{
	width: 350px;
	height: 50px;
	border: 1px solid #f2f2f2;
	margin:0 auto  ;
	margin-top: 150px;
	display: flex;
	justify-content: center;
	align-items: center;
}
.success-bottom{
	margin-top: 50px;
	margin-bottom: 250px;
}
</style>