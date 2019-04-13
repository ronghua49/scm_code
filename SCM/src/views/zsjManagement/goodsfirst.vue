<template>
	<div>
		<el-container>
		  <el-header>
		  	<div class="header">
				<div class="headleft"><span class="el-icon-search"></span><span class="sx">筛选查询</span></div>
				<div class="headright">
					<el-row>
					  <el-button type="primary" class="el-icon-search" size="mini" @click="searchquery">查询</el-button>
					</el-row>
				</div>
			</div>
		  </el-header>
		  <el-main>
		  	<el-form :label-position="labelPosition" size="small" label-width="120px" class="demo-ruleForm">
		  		<el-form-item label="产品编码">
						<el-input v-model="cpbmname" clearable  placeholder="请输入产品编码"></el-input>
				</el-form-item>
		  		<el-form-item label="产品名称">
						<el-input v-model="cpmcname" clearable  placeholder="请输入产品名称"></el-input>
				</el-form-item>	
				<el-form-item  label="存货代码">
						<el-input v-model="chdmcname" clearable  placeholder="请输入存货代码"></el-input>
				</el-form-item>	
		  		<el-form-item  label="药品分类">
		  			<el-select v-model="addypflid" placeholder="请输入药品分类" @change="getaddypflname">
						    <el-option
						      v-for="item in addypfloption"
						      :key="item.id"
						      :label="item.medicineClassify"
						      :value="item.id">
						    </el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="药品类型">
					<el-select v-model="ypid" placeholder="请选择药品类型" @change="getypname">
					    <el-option
					      v-for="item in ypoption"
					      :key="item.id"
					      :label="item.medicineType"
					      :value="item.id">
					    </el-option>
					 </el-select>
				</el-form-item> 
				<el-form-item label="产品系列">
					<el-select v-model="cpid" placeholder="请选择产品系列" @change="getcpname">
						    <el-option
						      v-for="item in cpoption"
						      :key="item.id"
						      :label="item.productLineName"
						      :value="item.id">
						    </el-option>
					</el-select>
				</el-form-item>
					<el-form-item label="经营范围">
						<el-select v-model="jyid" placeholder="请选择经营范围" @change="getname">
							    <el-option
							      v-for="item in jyoption"
							      :key="item.id"
							      :label="item.managementScope"
							      :value="item.id">
							    </el-option>
						</el-select>
					</el-form-item>
				</el-form>
		  </el-main>
		  
		  <div class="header">
				<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">数据列表</span></div>
				<div class="headright">
					<el-row>
					  <router-link to="/zsj/goodsfirstAdd"><el-button type="primary" icon="el-icon-circle-plus-outline" size="mini">首营申请</el-button></router-link>
					</el-row>
				</div>
			</div>
		  <!--table-->
		  	 <el-table
		  	 	 v-loading="loading2"
		  	 	 element-loading-text="加载中"
			    :data="tableData3"
			    size="mini"
			    border
			    :cell-style="style" 
			    style="width: 100%">
			    <el-table-column type="index" label="序号" width="100" align="center">
				</el-table-column>
			    <el-table-column v-for="item in column"
				  :key="item.prop"
			      :prop="item.prop"
			      :label="item.label"
			      :width="item.minwidth"
			      :formatter="item.formatter"
			      :align="item.align">
			    </el-table-column>
			    <el-table-column
			      align="center"
			      header-align="center"
			      fixed="right"
			      label="操作"
			      width="320">
			      <template slot-scope="scope">
			      	  <el-button type="primary" :disabled="scope.row.approvalState==1?true:false"  class="el-icon-edit" size="small" @click="edit(scope.row)">信息修改</el-button>
			      	  <router-link :to="{path:'/zsj/goodsfirstAdd',query:{editid:scope.row.productFirstId,detailType:0}}"><el-button class="el-icon-view" type="primary" size="small">查看详情</el-button></router-link> 
			         <el-button type="primary" class="el-icon-star-off" size="small" @click="see(scope.row)">修改记录</el-button>
			      </template>
			    </el-table-column>
			  </el-table>
			  <el-dialog title="修改记录详情" :visible.sync="dialogVisibles" width="70%">
				  <el-table :data="amendmentRecord" highlight-current-row  
                element-loading-text="加载中" border stripe
               max-height="500" size="mini" style="width:100%;" >
		        <el-table-column 
		        	v-for="(item,index) in amendmentRecordList" 
		        	:key="index" :prop="item.prop" 
		        	:show-overflow-tooltip="true" 
		        	:label="item.label" 
		        	:min-width="item.minWidth" 
		        	header-align="center"
		        	align="center">
		        </el-table-column>
		      </el-table>
		      <div class="toolbar" style="padding:10px;">
		      	<el-pagination  @current-change="refreshPageRequest1" @size-change="refreshPageSizeRequest1" background
	            :current-page="pageRequest1.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest1.pageSize" layout="sizes, total, prev, pager, next" :total="pageResult.total1" style="float:right;">
	          </el-pagination>
		      </div>
				</el-dialog>
			  <div class="pagination">
			  	<el-pagination @current-change="refreshPageRequest" @size-change="refreshPageSizeRequest"
				  background
				  :current-page="pageRequest.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest.pageSize" layout="sizes, total, prev, pager, next"
				  :total="total">
			  	</el-pagination>
			  </div>
			 
		</el-container>
	</div>
</template>
<script>
import storage from '@/utils/storage'	
export default {
	name:"goods",
    data() {
      return {
      	dialogVisibles:false,
    		amendmentRecord:[],//修改记录
    		pageResult:{},
    		amendmentRecordList:[
	      	{ prop: "filedName", label: "修改内容",minWidth: 150 },
	      	{ prop: "modifier", label: "修改人",minWidth: 150 },
	      	{ prop: "modifiedTime", label: "修改时间",minWidth: 150 },
	      ],
      	column:[
      	{prop:"approvalState",label:"审批状态",minwidth:"150",align:"center",formatter:function(row, column, cellValue, index){
			if(row.approvalState==0){
				return "未提交"
			}
			if(row.approvalState==1){
				return "审批中"
			}
			if(row.approvalState==2){
				return "已通过"
			}
		}},
      	{prop:"productName",label:"产品名称",minwidth:"180",align:"center"},
	    {prop:"productNum",label:"产品编码",minwidth:"100",align:"center"},
		{prop:"productLineName",label:"产品系列",minwidth:"120",align:"center"},
		{prop:"commonName",label:"通用名称",minwidth:"180",align:"center"},
		{prop:"agentType",label:"剂型",minwidth:"150",align:"center"},
		{prop:"productState",label:"规格",minwidth:"150",align:"center"},
		{prop:"unit",label:"单位",minwidth:"",align:"center"},
		{prop:"medicineType",label:"药品类型",minwidth:"150",align:"center"},
		{prop:"supplier",label:"供应商名称",minwidth:"200",align:"center"},
		{prop:"gmp",label:"GMP证号",minwidth:"150",align:"center"},
		{prop:"GMPValidity",label:"GMP有效期至",minwidth:"200",align:"center"},
		{prop:"businessScope",label:"经营范围",minwidth:"150",align:"center"},
	    ],
	    btnState:false,
	    ownerId:"",
        restaurants: [],
        labelPosition:"right",
        loading2:true,
        jyid:'',
        ypid:'',
        cpid:"",
        addypflid:"",
        jyname:'',
        ypname:'',
        cpname:'',
        wlbmname:"",
        chdmcname:"",
        total:0,
        operation:true,
	    options: [],
	    ypoption:[],
	    cpoption:[],
	    addcpxioption:[],
	    addjxoption:[],
	    addyplxoption:[],
	    addypfloption:[],
	    addzctjoption:[],
        addsccsoption:[],
        jyoption:[],
	    tableData3: [],
	    otcoption:[{"classification":"甲类","inx":"1"},{"classification":"乙类","inx":"2"},{"classification":"无","inx":"3"},],
        checked:false,
        loading: false,
        cpbmname:'',
        cpmcname:'',
        addcpxlname:"",
        addjxname:"",
        addyplxname:"",
        addypflname:"",
        addzctjname:"",
        addzsm:"0",
        editid:"",
        search:[],
        productFirstId:"",
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
      }
    },
    methods: {
      handleClose(done) {
      	this.dialogTableVisible=false
      },	
      refreshPageRequest(page = 1) {
	      this.pageRequest.page = page;
	      this.findPage();
    	},
      refreshPageSizeRequest(pageSize) {
	      this.tableData3.pageSize = pageSize;
	      this.findPage();
	    },
		   refreshPageRequest1(page = 1) {
	      this.pageRequest1.page = page;
	      this.getAmendmentRecord();
	    },
	    // size刷新
	    refreshPageSizeRequest1(pageSize) {
	      this.pageRequest1.pageSize = pageSize;
	      this.getAmendmentRecord();
	    },
	    style(rows){
	    	let {row,column,rowIndex,columnIndex} = rows;
	    	//GMP
	    	let flag = this.getPassFormatDate(row.GMPValidity);
	    	if(flag == 2 && column.label == "GMP有效期至"){
	    		return 'background:red'
	    	}else if(flag == 3 && column.label == "GMP有效期至"){
	    			return 'background:rgba(204, 204, 204)'
	    	}
	    },
	    //判断是否到期
	    getPassFormatDate(timer) {
	  		//转换时间格式时间戳
	    	function tranDate (time) {
	        return new Date(time.replace(/-/g, '/')).getTime();
	      }    	
		    let date = new Date(timer);
		    date.setMonth(date.getMonth()-1);
		    let year = date.getFullYear();
		    let month = date.getMonth() + 1;
		    let strDate = date.getDate();
		    let hours = date.getHours()<10?'0'+date.getHours():date.getHours();
		    let min = date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes();
		    let sec = date.getSeconds()<10?'0'+date.getSeconds():date.getSeconds();
		    if (month >= 1 && month <= 9) { month = "0" + month;}
		    if (strDate >= 0 && strDate <= 9) {strDate = "0" + strDate;}
		    let currentdate = year + '-' + month + '-' + strDate + ' ' + hours + ':' + min + ':' + sec;
				//到期前一个月时间
		    let endTime = tranDate(currentdate);
		    //到期时间
		    let etime = tranDate(timer);
		    //获取当前时间
		    let nowDate = new Date();
		    let currentTime = nowDate.getFullYear() + '-' + (nowDate.getMonth() + 1) + '-' + nowDate.getDate() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes()+ ':' + nowDate.getSeconds();
		    //将当前时间转为时间戳
		    let nowTime = tranDate(currentTime);
		    if(nowTime < endTime){return 1}
		    else if(nowTime >= endTime && nowTime < etime){return 2}
		    else {return 3}
			},
      getname(){
      	//获取经营范围的name
      	if(this.jyoption.length!=0){
      		var data= this.jyoption.filter((item)=>{
     		if(item.id == this.jyid){
     			this.jyname=item.managementScope
     		}
      	})
		}
      },
      getypname(){
      	//获取药品类型的name
      	if(this.ypoption.length!=0){
      		var data= this.ypoption.filter((item)=>{
     		if(item.id == this.ypid){
     			this.ypname=item.medicineType
     			console.log(this.ypname)
     		}
      	})
		}
      },
      getcpname(){
      	//获取产品系列的name
      	if(this.cpoption.length!=0){
      		var data= this.cpoption.filter((item)=>{
     		if(item.id == this.cpid){
     			this.cpname=item.productLineName
     			console.log(this.cpname)
     		}
      	})
		}
      },
      getaddcpxiname(){
      	//获取弹框产品系列的name
      	if(this.addcpxioption.length!=0){
      		var data= this.addcpxioption.filter((item)=>{
     		if(item.id == this.ruleForm.addcpxlid){
     			this.addcpxlname=item.productLineName
     			console.log(this.addcpxlname)
     		}
      	})
		}
      },
       getaddjxname(){
      	//获取弹框剂型的name
      	if(this.addjxoption.length!=0){
      		var data= this.addjxoption.filter((item)=>{
     		if(item.id == this.ruleForm.addjxid){
     			this.addjxname=item.agentType
     			console.log(this.addjxname)
     		}
      	})
		}
      },
       getaddyplxname(){
      	//获取弹框药品类型的name
      	if(this.addyplxoption.length!=0){
      		var data= this.addyplxoption.filter((item)=>{
     		if(item.id == this.ruleForm.addyplxid){
     			this.addyplxname=item.medicineType
     			console.log(this.addyplxname)
     		}
      	})
		}
      },
       getaddzctjname(){
      	//获取贮存条件的name
      	if(this.addzctjoption.length!=0){
      		var data= this.addzctjoption.filter((item)=>{
     		if(item.id == this.ruleForm.addzctjid){
     			this.addzctjname=item.keepingCondition
     			this.ruleForm.addjykcname=item.suggestWarehouse
     			console.log(this.ruleForm.addjykcname)
     		}
      	})
		}
      },
      getaddypflname(){
      	//获取弹框药品分类的name
      	if(this.addypfloption.length!=0){
      		var data= this.addypfloption.filter((item)=>{
     		if(item.id == this.addypflid){
     			this.addypflname=item.medicineClassify
     			console.log(this.addypflname)
     		}
      	})
		}
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      qx(){
      	this.dialogTableVisible = false
      	this.resetForm('ruleForm1')
      },
      edit(rows){
      	if(this.btnState==false){
      		this.productFirstId=rows.productFirstId
      		this.$router.push({
      			path: '/zsj/goodsfirstAdd?',
		        query: {
		            editid: this.productFirstId
		        }
      		})
      	}
      },
      searchquery(){
      	if(this.cpbmname){
      		this.search.push({property:"productNum",value:this.cpbmname, "hasInitValue": false, "operation": "EQUAL",relation:"AND"})
      	}
      	if(this.cpmcname){
      		this.search.push({property:"productName",value:this.cpmcname,"hasInitValue": false, "operation": "EQUAL",relation:"AND"})
      	}
      	if(this.jyname){
      		this.search.push({property:"medicineClassify",value:this.jyname,"hasInitValue": false, "operation": "EQUAL",relation:"AND"})
      	}
      	if(this.cpid){
      		this.search.push({property:"ProductLineId",value:this.cpid,"hasInitValue": false, "operation": "EQUAL",relation:"AND"})
      	}
      	if(this.ypname){
      		this.search.push({property:"ProductLineId",value:this.ypname,"hasInitValue": false, "operation": "EQUAL",relation:"AND"})
      	}
      	if(this.wlname){
      		this.search.push({property:"productCode",value:this.wlname,"hasInitValue": false, "operation": "EQUAL",relation:"AND"})
      	}
      	if(this.chdmcname){
      		this.search.push({property:"inventoryCode",value:this.chdmcname,"hasInitValue": false, "operation": "EQUAL",relation:"AND"})
      	}
      	this.search.push({property:"ownerId",value:this.ownerId,"hasInitValue": false, "operation": "EQUAL",relation:"AND"})
      	this.findPage(this.search)
      },
         //商品列表
      findPage(search){
		      this.loading = true;
		        this.$api.zsj.getZsj({
		            url: "/scm/scmZsjProduct/v1/firstList",
		            data:{ 
		            	pageBean: this.pageRequest,
			            params: {},
			            querys: search,
			            sorter: []
			            },
		        }).then(res => {
		            if (res) {
		        		this.tableData3=res.rows
		        		this.total=res.total
		        		this.loading2 = false
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
		 see(val){
	    	console.log(val)
	    	this.dialogVisibles = true;
	    	let search = [
	    		{operation: "EQUAL",property: "categoryId",relation: "AND", value: val.productFirstId },
	    		{operation: "EQUAL",property: "category",relation: "AND", value: "spxg" }
	    	]
	    	this.getAmendmentRecord(search)
	    },
	    //获取修改记录
	    getAmendmentRecord(search = []){
	    	this.$api.zsj
	        .getZsj({
	          url: "/scm/scmLogModifyLog/v1/list",
	          data: {
	            pageBean: this.pageRequest1,
	            params: {},
	            querys: search,
	            sorter: []
	          }
	        })
	        .then(res => {
	          console.log(res)
	          this.pageResult.total1 = res.total;
	          this.amendmentRecord = res.rows
	        });
	    },
    },
    mounted() {
      this.ownerId = storage.get('ownerId')

      this.findPage();
      //经营范围
      this.$api.zsj.getZsj({
            url: "/scm/scmZdManageMentScope/v1/list",
            data:{},
        }).then(res => {
            if (res) {
        		this.jyoption=res.rows
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
            data:{},
        }).then(res => {
            if (res) {
        		this.ypoption=res.rows
        		this.addyplxoption=res.rows
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
            data:{},
        }).then(res => {
            if (res) {
        		this.cpoption=res.rows
        		this.addcpxioption=res.rows
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
            data:{},
        }).then(res => {
            if (res) {
        		this.addypfloption=res.rows
              } else {
              this.$message({
                  message: "操作失败, " + res.shortMessage,
                  type: "error",
                  duration: 800
              });
            }
        })
	    },
	  }
</script>
<style scoped="scoped" lang="scss">
	.header{
		width: 100%;
		height: 38px;
		font-size: 14px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		background: #F3F3F3;
	}
	.el-header{
		padding: 0 !important;
	}
	.tab-title{
		width: 100%;
		height: 38px;
		font-size: 14px;
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
	.title{
		display: flex;
		justify-content: flex-start;
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
	.el-form{
	  display: flex;
	  flex-direction: row;
	  flex-wrap: wrap;
	  .el-form-item{
	    width: 33%;
	  }
	}
	.el-main{
		padding: 0 !important;
	}
	header{
		padding:0 !important;
		height: 50px !important;
		margin-top: 3px !important;
		 }
</style>