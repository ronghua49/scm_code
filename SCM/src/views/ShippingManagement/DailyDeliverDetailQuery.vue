<template>
  <div>
    <transition name="el-fade-in">
    <div class="content-item">
    <el-container>
      <el-header>
        <div class="header">
          <div class="headleft">
            <span class="el-icon-search"></span>
            <span class="sx">筛选查询</span>
          </div>
          <div class="headright">
            <el-row>
              <el-button type="primary" class="el-icon-search" size="mini" @click="searchquery">查询</el-button>
            </el-row>
          </div>
        </div>
      </el-header>
      <el-main>
        <el-form label-position="right" size="small" label-width="120px" class="demo-ruleForm" :model="searchForm" ref="searchForm">
          <el-form-item label="年度">
            <el-select clearable v-model="searchForm.year" placeholder="请选择年度">
              <el-option v-for="(item,index) in yearList" :key="index" :label="item.YEAR" :value="item.YEAR"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="商务分区">
            <el-select clearable v-model="searchForm.businessDivisionId" placeholder="请选择商务分区" @change="getPrivence">
              <el-option v-for="item in divisionList" :key="item.id" :label="item.businessDivision" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="省区">
            <el-select clearable v-model="searchForm.provinceId" placeholder="请选择省区">
              <el-option v-for="item in provinceList" :key="item.id" :label="item.province" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          
          <el-form-item label="日期" prop="remittanceDate">
            <div class="block">
              <el-date-picker
                v-model="searchForm.shipmentsAffirmDate"
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
          <el-form-item label="产品系列">
            <el-select clearable v-model="searchForm.ProductLineId" placeholder="请选择商品系列" @change="getProductName">
              <el-option v-for="item in productQuery.seriesList" :key="item.id" :label="item.lineName" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="药品规格">
            <el-select clearable v-model="searchForm.productId" placeholder="请选择商品名称">
              <el-option v-for="item in productQuery.standardList" :key="item.id" :label="item.productState" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="商业名称">
              <el-input placeholder="请输入内容" v-model="searchForm.commerceName" clearable></el-input>
          </el-form-item>
          
          <el-form-item label="合同编号">
            <el-input placeholder="请输入内容" v-model="searchForm.dateCode" clearable></el-input>
          </el-form-item>
          <el-form-item label="批号">
            <el-input placeholder="请输入内容" v-model="searchForm.batchNumber" clearable></el-input>
          </el-form-item>
          
        </el-form>
      </el-main>

      <div class="header">
        <div class="headleft">
          <span class="el-icon-tickets"></span>
          <span class="sx">数据列表</span>
        </div>
        <div class="headright">
          <!-- <el-row>
            <el-button @click="showAddNewPageFunc" type="primary" icon="el-icon-circle-plus-outline" size="mini">新增</el-button>
          </el-row> -->
        </div>
      </div>
      <!--表格栏-->
        <el-table class="test" :data="pageResult.rows" highlight-current-row v-loading="loading" element-loading-text="加载中" border stripe fit max-height="500" size="mini" align="left" style="width:100%;">
          <el-table-column 
          	v-for="(item,index) in columns" 
          	:key="index" 
          	:prop="item.prop" 
          	:label="item.label" 
          	:width="item.minwidth" 
          	:formatter="item.formatter" :align="item.align">
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
				  :total="pageResult.total">
			  </el-pagination>
        </div>
    </el-container>

    </div>
    </transition>

  </div>
</template>
<script>
// 筛选功能
import storage from "@/utils/storage";
export default {
  name: "DailyDeliverDetailQuery",
  components: {},
  data() {
    return {
      ownerId: "",
      pageResult: {},
      loading: false,
      editItemId: '', // 传给子组件的参数 -- 怎么区分是编辑还是查看详情呢 标识+id 逗号分隔
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      // 年度下拉框列表
      yearList: [],
      // 商务分区/省区筛选条件
      divisionList: [], // 商务分区
      provinceList: [],  // 省区列表
      // 商品筛选条件
      productQuery: {
          seriesList: [], // 商品系列
          standardList: [] // 商品名称列表
      },
      // 经销商列表
      angecyList: [],
      // 查询参数
      searchForm: {
          filingDate: '',
          businessDivisionId: '', // 商务分区
          provinceId: '', // 省区
      },
      // 表格表头
      columns: [
          {prop:"businessDivision",label:"商务分区",align:"center"},
	      	{prop:"province",label:"省区",align:"center"},
		  		{prop:"month",label:"月度",align:"center"},
		  		{prop:"shipmentsAffirmDate",label:"发货日期",align:"center"},
		  		{prop:"actualDeliveryDate",label:"仓库实际发货日期",align:"center"},
		  		{prop:"",label:"退货标识",align:"center"},
          {prop:"marketType",label:"销售类型",align:"center"},
          {prop:"focusShipmentsMonth",label:"集中发货分配月",align:"center"},
          {prop:"dateCode",label:"合同编号",align:"center"},
          {prop:"commercetype",label:"客户类型",align:"center"},
          {prop:"commerceName",label:"客户名称",align:"center"},
          {prop:"productName",label:"产品名称",align:"center"},
          {prop:"shipmentsCode",label:"发货单号",align:"center"},
          {prop:"contactPersion",label:"联系人",align:"center"},
          {prop:"phone",label:"联系电话",align:"center"},
          {prop:"InventoryCode",label:"代码",align:"center"},
          {prop:"productState",label:"规格",align:"center"},
          {prop:"packageSum",label:"发货件数",align:"center"},
          {prop:"deliveringAmount",label:"发货数量",align:"center"},
          {prop:"price",label:"发货单价",align:"center"},
          {prop:"price",label:"发货金额",align:"center"},
          {prop:"batchNumber",label:"批号",align:"center"},
          {prop:"dateOfManufacture",label:"生产日期",align:"center"},
          {prop:"approvalCode",label:"药品注册号",align:"center"},
          {prop:"memo",label:"备注",align:"center"},
          {prop:"agreementDays",label:"协议到货天数",align:"center"},
          {prop:"expectedArrivalDate",label:"预计到货日期",align:"center"},
          {prop:"actualDateOfArrival",label:"实际到货日期",align:"center"},
          {prop:"dalayDays",label:"延误天数",align:"center"},
          {prop:"officialReceiptsSum",label:"实际件数",align:"center"},
          {prop:"createPersion",label:"制单人",align:"center"}, 
      ]
    };
  },
  methods: {
    resetStartDate (value) {
        if(value.startDate) {
            return value.startDate.split(' ')[0];
        }
        
    },
    resetEndDate (value) {
        if(value.endDate) {
            return value.endDate.split(' ')[0];
        }
        
    },
    handleClose(done) {
      this.dialogTableVisible = false;
    },
    refreshPageRequest(page = 1) {
      this.pageRequest.page = page;
      this.findPage();
    },
    refreshPageSizeRequest(pageSize) {
      this.pageRequest.pageSize = pageSize;
      this.findPage();
    },
    toogleShowAddNewPage (flag) {
        let self = this;
        self.showAddNewPage = '-1';
        setTimeout(function() {
            self.showAddNewPage = flag;
        },500);
    },
    // 点击新增显示
    showAddNewPageFunc () {
        let self = this;
        self.toogleShowAddNewPage('2'); 
    },
    // v-on 绑定不支持驼峰命名
    closeaddnewpage () {
        let self = this;
        self.toogleShowAddNewPage('1');
        self.editItemId = '';
        self.forceRefresh = + new Date();
        console.log(this.pageResult);
        self.searchquery();
    },
    //获取年度下拉列表
    getYear(){
    	let _this = this
    	this.$api.fhgl
        .get({
            url: "/scm/scmFhShipmentsDatail/v1/getYears/" + this.ownerId,
            data: {}
        }).then(res => {
        	_this.yearList = res.value.filter(res => {return res});
        })
    },
    // 获取商务分区
    getDivision () {
        let self = this;
        self.$api.xsgl.getZdBusinessDivision({vueInstance: self}, function(res) {
            self.divisionList = res;
        });
    },
    // 获取省区
    getPrivence () {
        console.log(this.searchForm);
        this.searchForm.provinceId= ''
        let divisionId = this.searchForm.businessDivisionId // 商务分区ID
        this.provinceList = this.divisionList.reduce((final, item, index, arr)=>{
            if(item.id == divisionId){
                final = item.provinceList;
            }
            return final
        },[]);
    },
    //获取产品系列
    getProduct(){
    	let _this = this;
      _this.$api.xsgl
      .post({
        vueInstance: _this,
        url: "/scm/scmZsjProduct/v1/LineAndProductDownBox",
        //data: {}
      }, function(res) {
          _this.productQuery.seriesList = res.value;
          
      })
    },
    //获取药品规格
    getProductName (val) {
        let _this = this;
        _this.productQuery.seriesList.forEach(function(item,index) {
            if(item.id === val) {
                _this.productQuery.standardList = item.productList;
            }
        });
    },
    // 筛选查询
    searchquery() {
        let query = [];
        let searchForm = Object.assign({},this.searchForm);
        for(var key in searchForm) {
          if(searchForm[key]) {
            if(key === "year") { //年度
              query.push({
                hasInitValue: false,
                operation: "EQUAL",
                relation: "AND",
                value: searchForm[key],
                property: key
              }); 
            } else if(key === "businessDivisionId"){ //商务分区
              query.push({
                hasInitValue: false,
                operation: "EQUAL",
                relation: "AND",
                value: searchForm[key],
                property: key
              }); 
            }else if(key === "provinceId"){ //省区
              query.push({
                hasInitValue: false,
                operation: "EQUAL",
                relation: "AND",
                value: searchForm[key],
                property: key
              }); 
            }else if(key === "shipmentsAffirmDate"){ //日期
              query.push({
                hasInitValue: false,
                operation: "BETWEEN",
                relation: "AND",
                value: searchForm[key],
                property: key
              }); 
            }else if(key === "ProductLineId"){ //产品系列
              query.push({
                hasInitValue: false,
                operation: "EQUAL",
                relation: "AND",
                value: searchForm[key],
                property: key
              }); 
            }else if(key === "productId"){ //药品规格
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
            }else if(key === "dateCode"){ //合同编号
              query.push({
                hasInitValue: false,
                operation: "EQUAL",
                relation: "AND",
                value: searchForm[key],
                property: key
              }); 
            }else if(key === "batchNumber"){ //批号
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
        this.findPage(query);
    },
    //列表
    findPage(search = []) {
      let self = this;
      self.loading = true;
      self.$api.xsgl
        .post({
          vueInstance: self,
          url: "/scm/scmFhShipmentsDatailMaster/v1/allShipmentMessage",
          data: {
            pageBean: self.pageRequest,
            params: {},
            querys:[{operation: "EQUAL",property: "ownerId",relation: "AND", value: self.ownerId }].concat(search),
            sorter: []
          }
        })
        .then(res => {
        	self.loading = false;
        	self.pageResult = res;
        	console.log(res)
        });
    }
    
  },
  mounted() {
    this.ownerId = storage.get("ownerId");
    // 获取商务分区
    this.getDivision();
    //获取列表信息
    this.findPage();
    //获取年度
    this.getYear();
    //获取产品系列
    this.getProduct();
  }
};
</script>
<style scoped="scoped" lang="scss">
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
.el-select {
  width: 100%;
}
.head {
  background: none !important ;
}
.el-form {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  
  .el-form-item {
    width: 33.3333%;
    .el-date-editor.el-input{
        width:100%;
    }

  }
}
.el-main {
  padding: 0 !important;
}
header {
  padding: 0 !important;
  height: 50px !important;
  margin-top: 3px !important;
}
.block {
  .el-date-editor.el-range-editor {
    width: 100%;
  }
}
</style>