<template>
  <div>
    <transition>
      <div v-show="page1">
      	<el-container>
      	  <el-header>
      	  	<div class="header">
              <div class="headleft">
                <span class="el-icon-search"></span>
                <span class="sx">筛选查询</span>
              </div>
              <div class="headright">
                <el-row>
                  <el-button type="primary" class="el-icon-search" size="mini">查询</el-button>
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
              <el-form-item label="核销状态" prop="verifyType">
                <el-select clearable style="width: 100%" v-model="searchForm.verifyType" placeholder="请选择">
                  <el-option v-for="item in options" :key="item.id" :label="item.label" :value="item.id"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="开票日期" prop="remittanceDate">
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
              <div class="headright">
                <el-row>
                  <el-button type="primary" size="mini" @click="collect">采集</el-button>
                  <el-button type="primary" size="mini" @click="adjust">调整</el-button>
                </el-row>
              </div>
            </div>
            <el-table v-loading="loading" :data="tableData" border stripe element-loading-text="加载中" style="width: 100%;" max-height="250">
	            <el-table-column header-align="center" align="center" prop="" label="核销状态" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="发票类型" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="到款日期" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="商务分区" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="所属省区" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="开票商业名称" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="原开票日期" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="调整开票日期" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="发票号码" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="产品名称" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="规格" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="数量" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="产品" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="含税单价（元）" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="价税合计（元）" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="发货日期" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="发货单号" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="合同号" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="回款方式" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="备注" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="回款到期日" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="采集人" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="采集时间" width="120"></el-table-column>
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
    <transition>
    	<div v-show="page2">
    		<div class="searchHeader">
	        <div class="searchheadleft">
	          <el-button @click="fanHui" size="mini" class="el-icon-back" type="primary">返回</el-button>
	        </div>
	        <div class="searchheadcenter">销售发票采集</div>
	      </div>
	      <el-container>
	        <el-main>
            <div class="header">
              <div class="headleft">
                <span class="el-icon-tickets"></span>
                <span>数据列表</span>
              </div>
              <div class="headright">
                <el-row>
                  <el-button type="success" size="mini">确认数据</el-button>
                  <el-button type="danger" size="mini">删除</el-button>
                  <el-button type="warning" size="mini">采集数据</el-button>
                </el-row>
              </div>
            </div>
            <el-table v-loading="loading" @select="handleSelectionChange" :data="tableData" border stripe element-loading-text="加载中" style="width: 100%;" max-height="250">
            	<el-table-column type="selection" width="55"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="发票类型" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="商务分区" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="所属省区" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="开票商业名称" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="开票日期" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="发票号码" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="产品名称" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="规格" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="数量" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="产品" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="含税单价（元）" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="价税合计（元）" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="发货日期" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="发货单号" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="合同号" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="回款方式" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="备注" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="采集人" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="采集时间" width="120"></el-table-column>
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
    <transition>
    	<div v-show="page3">
    		<div class="searchHeader">
	        <div class="searchheadleft">
	          <el-button @click="fanHui" size="mini" class="el-icon-back" type="primary">返回</el-button>
	        </div>
	        <div class="searchheadcenter">销售发票调整</div>
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
	                <el-button type="primary" class="el-icon-search" size="mini">查询</el-button>
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
              <el-form-item label="商业名称" prop="commerceName">
                <el-input :rows="1" placeholder="请输入内容" v-model="searchForm.commerceName"></el-input>
              </el-form-item>
              <el-form-item label="开票日期" prop="remittanceDate">
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
            </el-form>
            <div class="header">
              <div class="headleft">
                <span class="el-icon-tickets"></span>
                <span>数据列表</span>
              </div>
              <div class="headright">
                <el-row>
                  <el-button type="success" size="mini">保存</el-button>
                </el-row>
              </div>
            </div>
            <el-table v-loading="loading" @select="handleSelectionChange" :data="tableData" border stripe element-loading-text="加载中" style="width: 100%;" max-height="250">
            	<el-table-column type="selection" width="55"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="发票类型" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="商务分区" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="所属省区" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="开票商业名称" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="原开票日期" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="调整后开票日期" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="发票号码" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="产品名称" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="规格" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="数量" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="产品" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="含税单价（元）" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="价税合计（元）" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="发货日期" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="发货单号" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="合同号" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="回款方式" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="备注" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="调整人" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="调整时间" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="采集人" width="120"></el-table-column>
	            <el-table-column header-align="center" align="center" prop="" label="采集时间" width="120"></el-table-column>
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
  </div>
</template>

<script>
import storage from "@/utils/storage";

export default {
  name: "InvoiceManagement",

  data() {
    return {
    	page1:true,
    	page2:false,
    	page3:false,
      //查询筛选数据
      searchForm: {
        verifyType:"",//核销状况
        remittanceDate: "", //日期
        businessDivisionId: "", // 商务分区
        provinceId:"",//省区
        commerceName:""//商业名称
      },
      //核销状态数据
      options: [
        {
          id: "0",
          label: "未核销"
        },
        {
          id: "1",
          label: "核销中"
        },
        {
          id: "2",
          label: "已核销"
        }
      ],
      searchProvinceList:[],//省区列表
      divisionList:[],//商务分区列表
      search: [],
      tableData:[],//列表数据
      loading:false,
      pageRequest:{
      	page:1,
      	pageSize:10,
      	showTotal:true
      },
      total:10,
    };
  },
  mounted() {
  	//获取货主id
    this.ownerId = storage.get("ownerId");  
		//获取列表数据
    this.getTableList();
    //获取商务分区数据
    this.getDivision ();
  },
  methods: {
    //获取列表信息
    getTableList(query = this.search) {
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
          url: "/scm/scmCwInvoice/v1/list",
          data: {
            pageBean: _this.pageRequest,
            params: {},
            querys: postData,
            sorter: []
          }
        })
        .then(res => {
          console.log("Lists", res);
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
    //分页逻辑
    handleSizeChange(val){},
    handleCurrentChange(val){},
    fanHui(){
    	setTimeout(()=>{
    		this.page1 = true;
    	},300)
    	this.page2 = false;
    	this.page3 = false;
    },
    collect(){
    	this.page1 = false;
    	setTimeout(()=>{
    		this.page2 = true;
    	},300)
    	this.page3 = false;
    },
    adjust(){
    	this.page1 = false;
    	this.page2 = false;
    	setTimeout(()=>{
    		this.page3 = true;
    	},300)
    },
    //多选按钮
    handleSelectionChange(){}
  }
};
</script>

<style scoped lang="scss">
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
	/*采集模块样式*/
	.searchHeader {
	  position: relative;
	  width: 100%;
	  font-size: 28px;
	  font-weight: bold;
	  background: #f3f3f3;
	  margin-bottom: 7px;
	  .searchheadleft {
		  position: absolute;
		  left: 10px;
		}
		.searchheadcenter {
		  width: 100%;
		  text-align: center;
		  line-height: 1.5em;
		}
	}
</style>
