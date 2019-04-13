<template>
  <div>
  	<div class="memo">
  		<span class="el-icon-tickets"></span>
  		备注
  	</div>
  	<div class="memoDescript">
  		药品默认都可发货，添加时如果商务分区，省区，经销商，全部为空，表示所有商业都不可以发选中的药品，
	  	如果选了商务分区，代表该商务分区下不可发选中的药品，如果选了商务分区和省区，表示该省区下不可以发选中的药品，
	  	如果选了商务分区、省区以及经销商，代表该经销商下不可以发选中的药品。
  	</div>
  	<el-dialog
  		:title="title"
  		:visible.sync="dialogVisible"
  		width="30%"
  		:before-close="handleClose">
  		<el-form :model="sendValidateForm" :rules="rules" ref="sendValidateForm" label-width="100px" >
  			<el-form-item
  				label="商务分区"
  				prop="businessDivision"
  				>
  				<el-row type="flex" justify="start">
	  				<el-select style="width: 400px" clearable @change="changeDivision(sendValidateForm.swfqValue)"  size="medium" v-model="sendValidateForm.swfqValue" placeholder="请选择商务分区">
	  				<el-option 
	  					v-for="(item,index) in swdqOption"
	  					:key="'swdq'+index"
	  					:value="item.businessDivision">
	  				  </el-option>
	  			  </el-select>
  			  </el-row>
  			</el-form-item>
  			<el-form-item
  				label="省区"
  				prop="province">
  				<el-row type="flex" justify="start">
	  				<el-select style="width: 400px" clearable @change="changeSq(sendValidateForm.sqValue)"  size="medium" v-model="sendValidateForm.sqValue" placeholder="请选择商务大区">
	  				<el-option 
	  					v-for="(item,index) in sqOption.provinceList"
	  					:key="'sq'+index"
	  					:value="item.province">
	  				  </el-option>
	  			  </el-select>
  			  </el-row>
  			</el-form-item>
  			<el-form-item
  				label="经销商"
  				prop="commerce">
  				<el-row type="flex" justify="start">
	  				<el-select style="width: 400px" clearable @change="jxsChange(sendValidateForm.jxsValue)"  size="medium" v-model="sendValidateForm.jxsValue" placeholder="请选择商务大区">
	  				<el-option 
	  					v-for="(item,index) in jxsOption"
	  					:key="'jxs'+index"
	  					:value="item.commerceName">
	  				  </el-option>
	  			  </el-select>
	  			 </el-row>
  			</el-form-item>
  		  <el-form-item	label="药品系列"	prop="xlValue">
  				<el-row type="flex" justify="start">
  					<el-select style="width: 400px" clearable @change="ypChange(sendValidateForm.xlValue)"  size="medium" v-model="sendValidateForm.xlValue" placeholder="请选择商务大区">
	  					<el-option 
		  					v-for="(item,index) in xlOption"
		  					:key="'xl'+index"
		  					:value="item.lineName">
	  				  </el-option>
	  			  </el-select>
  				</el-row>
  			</el-form-item>
  			<el-form-item label="药品名称" prop="spValue">
  				<el-row type="flex" justify="start">
  					<el-select style="width: 400px" clearable @change="ymChange(sendValidateForm.spValue)"  size="medium" v-model="sendValidateForm.spValue" placeholder="请选择禁止发货的药品名称">
	  					<el-option 
		  					v-for="(item,index) in spOption.productList"
		  					:key="'xl'+index"
		  					:value="item.productName">
	  				  </el-option>
	  			  </el-select>
  				</el-row>
  			</el-form-item>
  		</el-form>
  		  <span slot="footer" class="dialog-footer">
			    <el-button size="mini" @click="dialogVisible = false">取 消</el-button>
			    <el-button size="mini" type="primary" @click="sendControl">确 定</el-button>
			  </span>
  	</el-dialog>
    <transition name="el-fade-in">
      <div class="content-item">
        <el-row class="addnew-btn-row">
          <el-button type="primary" size="mini" @click="dialogVisible = true">新增</el-button>
        </el-row>
        <div class="header">
          <div class="headleft">
            <span class="el-icon-tickets"></span>
            <span class="sx">数据列表</span>
          </div>
          <div class="headright">
            <!-- <el-row>
            <el-button @click="showAddNewPageFunc" type="primary" icon="el-icon-circle-plus-outline" size="mini">新增</el-button>
            </el-row>-->
          </div>
        </div>
        <!--表格栏-->
        <el-table
          class="test"
          :data="pageResult.rows"
          highlight-current-row
          v-loading="loading"
          element-loading-text="加载中"
          border
          stripe
          fit
          max-height="500"
          size="mini"
          align="left"
          style="width:100%;"
        >
          <el-table-column
            v-for="item in columns"
            :key="item.prop"
            :prop="item.prop"
            :label="item.label"
            :width="item.minwidth"
            :formatter="item.formatter"
            :align="item.align"
          ></el-table-column>
          <el-table-column label="操作" fixed="right" header-align="center" align="center">
          	<template slot-scope="scope">
          		<el-button size="mini"
                  type="primary" @click="edit(scope.row)">编辑
              </el-button>
              <el-button size="mini"
                  type="danger" @click="deleteTr(scope.row)">删除
              </el-button>
          	</template>
          </el-table-column>
        </el-table>
        <div class="pagination">
          <el-pagination
            @current-change="refreshPageRequest"
            @size-change="refreshPageSizeRequest"
            background
            :current-page="pageRequest.page"
            :page-sizes="[10, 20, 30, 40, 50]"
            :page-size="pageRequest.pageSize"
            layout="sizes, total, prev, pager, next"
            :total="pageResult.total"
          ></el-pagination>
        </div>
        <div class="LOG">
          <div class="header">
            <div class="headleft">
              <span class="el-icon-tickets"></span>
              <span class="sx">日志列表</span>
            </div>
            <div class="headright"></div>
          </div>
          <!--表格栏-->
          <el-table
            class="test"
            :data="LOG.pageResult.rows"
            highlight-current-row
            v-loading="LOG.loading"
            element-loading-text="加载中"
            border
            stripe
            fit
            max-height="500"
            size="mini"
            align="left"
            style="width:100%;"
          >
            <el-table-column
              show-overflow-tooltip
              v-for="item in LOG.columns"
              :key="item.prop"
              :prop="item.prop"
              :label="item.label"
              :width="item.minwidth"
              :formatter="item.formatter"
              :align="item.align"
            ></el-table-column>
          </el-table>
          <div class="pagination">
            <el-pagination
              @current-change="LOGrefreshPageRequest"
              @size-change="LOGrefreshPageSizeRequest"
              background
              :current-page="LOG.pageRequest.page"
              :page-sizes="[10, 20, 30, 40, 50]"
              :page-size="LOG.pageRequest.pageSize"
              layout="sizes, total, prev, pager, next"
              :total="LOG.pageResult.total"
            ></el-pagination>
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>
<script>
import storage from "@/utils/storage";
import {format} from "@/utils/datetime";
export default {
  name: "DeliverControlManage",
  components: {},
  data() {
    return {
    	dialogVisible: false,
    	title: '新增',
    	sendValidateForm: {
    		swfqValue: '',
    		swfqId: "",
    		sqValue: "",
    		sqId: "",
    		jxsValue: "",
    		jxsId: "",
    		xlValue: "",
    		xlId: "",
    		spValue: "",
    		spId: "",
    		id: ""
    	},
    	swdqOption: [], // 商务分区
    	sqOption: [], // 省区
    	jxsOption: [], // 经销商
    	xlOption: [], // 药品系列 
    	spOption: [],// 商品系列
    	rules:{
    		xlValue: [
    			{required: true,message: '请选择药品系列',trigger: 'blur'}
    		],
    		spValue: [
    			{required: true,message: '请选择药品名称',trigger: 'blur'}
    		]
    	},
      ownerId: "",
      pageResult: {},
      loading: false,
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      LOG: {
        pageResult: {},
        loading: false,
        pageRequest: {
          page: 1,
          pageSize: 10,
          showTotal: true
        },
        columns: [
          { prop: "operatorName", label: "操作人", align: "center",formatter:function(row, column, value){if (!value) {return '无'} else {return value} }},
          { prop: "operatormemo", label: "操作内容", align: "center",formatter:function(row, column, value){if (!value) {return '无'} else {return value} }  },
          { prop: "operatorDate", label: "时间", align: "center",formatter: function(row,clumn,value){
          	if (!value) {
          		return '无'
          	}
          	return format(value);
          } },
        ]
      },
      // 表格表头
      columns: [
      	{ prop: "province", label: "省区", align: "center",formatter:function(row, column, value){if (!value) {return '无'} else {return value} } },
        { prop: "businessDivision", label: "商务分区", align: "center",formatter:function(row, column, value){if (!value) {return '无'} else {return value} } },
        { prop: "commerce", label: "经销商", align: "center",formatter:function(row, column, value){if (!value) {return '无'} else {return value} } },
        { prop: "productLineName", label: "药品系列", align: "center" },
        { prop: "productNum", label: "药品编号", align: "center" },
        { prop: "productState", label: "药品品规", align: "center" },
        { prop: "productName", label: "禁止发货药品名称", align: "center" },
        { prop: "createDate", label: "添加时间", align: "center" },
      ]
    };
  },
  methods: {
    handleClose(done) {
      this.dialogVisible = false;
      this.sendValidateForm.xlValue = "";
      this.sendValidateForm.spValue = ""
      for ( var key in this.sendValidateForm ) {
      	this.sendValidateForm[key] = "";
      }
      this.spOption = [];
      this.$refs.sendValidateForm.resetFields();
    },
    refreshPageRequest: function(page = 1) {
      this.pageRequest.page = page;
      this.getDataTableList();
      this.getLog();
    },
    refreshPageSizeRequest: function(pageSize) {
      this.pageRequest.pageSize = pageSize;
      this.getDataTableList();
      this.getLog();
    },
    LOGrefreshPageRequest: function(page = 1) {
      this.LOG.pageRequest.page = page;
      this.getDataTableList(page);
      this.getLog(page);
    },
    LOGrefreshPageSizeRequest: function() {
      this.LOG.pageRequest.pageSize = pageSize;
      this.getDataTableList();
      this.getLog();
    },
    getDivision(){//获取商务分区
      this.$api.common
        .getDivision()
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.swdqOption = res;
        });
    },
    changeDivision (item) { // 商务分chang事件
    	this.sendValidateForm.sqValue = ""; // 省区名称
    	this.sendValidateForm.swfqId = ""; // 商务分区id
    	this.sendValidateForm.sqId = ""; // 药品id
    	this.sendValidateForm.jxsValue = "";//经销商名字
    	this.sendValidateForm.jxsId = ""; // 经销商的id
    	this.sqOption = ""; // 药品数组
    	this.jxsOption = ""; // 经销商数组
    	this.sqOption = this.swdqOption.filter((res) => {
    		if ( res.businessDivision == item ) {
    			return res;
    		}
    	})[0];
    	this.sendValidateForm.swfqId = this.sqOption.id;
    	this.sendValidateForm.swfqValue = this.sqOption.businessDivision;
    	let search = [{
					      "hasInitValue": false,
					      "operation": "EQUAL",
					      "property": "businessDivisionId",
					      "relation": "AND",
					      "value": this.sendValidateForm.swfqId
					    }];
    	this.getJxs(search);
    },
    changeSq(item) { // 省区change事件
    	this.sendValidateForm.jxsValue = "";//经销商名字
    	this.sendValidateForm.jxsId = ""; // 经销商的id
    	this.jxsOption = ""; // 经销商数组
    	let idArr = this.sqOption.provinceList.filter(res => {
    		if ( res.province == item ) {
    			return res;
    		}
    	})[0];
    	this.sendValidateForm.sqId = idArr.id;
    	let search = [
					    {
					      "hasInitValue": false,
					      "operation": "EQUAL",
					      "property": "businessDivisionId",
					      "relation": "AND",
					      "value": this.sendValidateForm.swfqId
					    },
					    {
					    	 "hasInitValue": false,
					      "operation": "EQUAL",
					      "property": "provinceId",
					      "relation": "AND",
					      "value": this.sendValidateForm.sqId
					    }
					  ]
    	this.getJxs(search);
    },
    getJxs (search) {
    	this.$api.xsgl.post({
    		url: '/scm/scmZsjCommerce/v1/list',
    		data:{
					  "pageBean": {
					    "page": 1,
					    "pageSize": 1000000,
					    "showTotal": true
					  },
					  "params": {},
					  "querys": search,
					  "sorter": []
					}
    	}).then(res => {
    		if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
    		this.jxsOption = res.rows;
    	})
    },
    jxsChange (item) {
    	let jxsArr = this.jxsOption.filter(res => {
    		if ( res.commerceName == item ) {
    			return res;
    		}
    	})[0]
    	this.sendValidateForm.jxsId = jxsArr.id; // 经销商的id
    },
    getYp(){
    	this.$api.xsgl.post({
    		url: '/scm/scmZsjProduct/v1/LineAndProductDownBox',
    	}).then(res => {
    		if ( res.state == false ) {
    			this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
    		}
    		this.xlOption = res.value;
    	})
    },
    ypChange (item) {
    	this.sendValidateForm.spValue = "";
    	this.sendValidateForm.spId = "";
    	this.productCode = "";// 后台字段里让传的
    	this.productState = ""; // 后台字段里让传的
    	this.spOption = "";
    	this.spOption = this.xlOption.filter((res) => {
    		if ( res.lineName == item ) {
    			return res;
    		}
    	})[0];
    	this.sendValidateForm.xlId = this.spOption.id;
    },
    ymChange (item) {
    	let ym = this.spOption.productList.filter((res) => {
    		if ( res.productName == item ) {
    			return res;
    		}
    	})[0];
    	//this.sendValidateForm.xlId = this.spOption.id;
    	this.productCode = ym.productCode;
    	this.productState = ym.productState;
    },
     sendControl () {
    	this.$refs.sendValidateForm.validate(valid => {
    		if (!valid) {
    			return;
    		}
    		this.$api.xsgl.post({
	    		url: '/scm/scmFhShipmentsControl/v1/save',
	    		data: {
	    			businessDivisionId: this.sendValidateForm.swfqId,
	    			businessDivision: this.sendValidateForm.swfqValue,
	    			commerce: this.sendValidateForm.jxsValue,
	    			commerceId: this.sendValidateForm.jxsId,
	    			ownerId: this.ownerId,
	    			productLineId: this.sendValidateForm.xlId,
	    			productLineName: this.sendValidateForm.xlValue,
	    			productName: this.sendValidateForm.spValue,
                    productNum: this.productCode,
	    			productState: this.productState,
	    			provinceId: this.sendValidateForm.sqId,
	    			province: this.sendValidateForm.sqValue,
	    			id: this.sendValidateForm.id
	    		}
	    	}).then(res => {
	    		if ( res.state == false ) {
	    			this.$message({
	    				message: '系统错误',
    				  type: "error",
	    			});
	    			return;
	    		}
	    		this.$message({
	    				message: '操作成功',
    				  type: "success",
	    			});
	    			this.handleClose();
	    			this.refreshPageRequest();
	    	})
    	})
    },
    edit(row){
    	this.sendValidateForm.swfqId = row.businessDivisionId;
    	this.sendValidateForm.swfqValue = row.businessDivision;
    	this.sendValidateForm.jxsValue = row.commerce;
    	this.sendValidateForm.jxsId = row.commerceId;
    	this.sendValidateForm.xlId = row.productLineId;
    	this.sendValidateForm.xlValue = row.productLineName;
    	this.sendValidateForm.spValue = row.productName;
    	this.productCode = row.productCode;
    	this.productState = row.productState;
    	this.sendValidateForm.sqValue = row.province;
    	this.sendValidateForm.sqId = row.provinceId;
    	this.sendValidateForm.id = row.id;
    	this.dialogVisible = true;
    	this.sqOption = this.swdqOption.filter((res) => {
    		if ( res.businessDivision == row.businessDivision ) {
    			return res;
    		}
    	})[0];
    	this.spOption = this.xlOption.filter((res) => {
    		if ( res.lineName == row.productLineName ) {
    			return res;
    		}
    	})[0];
    },
    deleteTr(row){
    	this.$confirm("确认删除此项数据？",'确认信息',{
    		 distinguishCancelAndClose: true,
         confirmButtonText: '确定',
         cancelButtonText: '取消'
    	}).then(()=> {
    		this.$api.xsgl.deletes({
	    		url: '/scm/scmFhShipmentsControl/v1/remove/'+row.id
	    	}).then(res => {
	    		if ( res.state == false ) {
	    			this.$message({
	    				message: '系统错误',
	    				type: 'error'
	    			})
	    			return;
	    		}
	    		this.$message({
	    				message: '操作成功',
	    				type: 'success'
					});
					this.refreshPageRequest();
	    	});
    	})
    },
    // 获取数据列表函数
    getDataTableList (pageSize = 1) {
        this.$api.xsgl.post({
            vueInstance: self,
            url: "/scm/scmFhShipmentsControl/v1/list",
            data: {
                pageBean: {
                    page: pageSize,
                    pageSize: 10,
                    showTotal: true
                },
                querys:[
                    {property: "ownerId", value: this.ownerId, operation: "EQUAL", relation: "AND"}
                ]
            }
          }, (res) => {
          		this.pageResult = {};
              this.pageResult.rows = res.rows;
              this.pageResult.total = res.total;
          });
    },
    getLog (pageSize = 1) {
    	this.$api.xsgl.post({
    		url : '/scm/scmFhShipmentsControlLog/v1/list',
    		data: {
            pageBean: {
                page: pageSize,
                pageSize: 10,
                showTotal: true
            },
            querys:[
                {property: "ownerId", value: this.ownerId, operation: "EQUAL", relation: "AND"}
            ]
        }
    	}).then(res => {
    		if ( res.state == false ) {
    			this.$message({
    				message: '系统错误',
    				type: 'warn'
    			});
    			return;
    		}
    		this.LOG.pageResult.rows = res.rows;
    		this.LOG.pageResult.total = res.total;
    	})
    	
    }
  },
  mounted() {
    this.ownerId = storage.get("ownerId");
    this.getDataTableList();
    this.getDivision(); // 获取所有经销商
    this.getYp(); // 获取所有药品系列
    this.getLog(); //日志列表
  }
};
</script>
<style scoped="scoped" lang="scss">
.memo{
	background: #f3f3f3;
	text-align: left;
	padding: 10px;
}
.memoDescript{
	text-indent: 2em;
	text-align: left;
	margin-bottom: 10px;
	padding: 10px;
	border: 1px solid #F3F3F3;
}
.header {
  width: 100%;
  height: 38px;
  font-size: 14px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f3f3f3;
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
.title {
  display: flex;
  justify-content: flex-start;
}
.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.head {
  background: none !important ;
}

header {
  padding: 0 !important;
  height: 50px !important;
  margin-top: 3px !important;
}
.addnew-btn-row {
  margin-bottom: 10px;
  text-align: left;
}
.LOG{
    margin-top: 20px;
}
</style>
