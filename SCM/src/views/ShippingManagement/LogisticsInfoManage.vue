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
          <el-form-item label="仓库">
            <el-select clearable v-model="searchForm.warehouse" placeholder="请选择仓库">
              <el-option v-for="item in houseList" :key="item.id" :label="item.getMoneyWarehouse" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="销售类型">
            <el-select clearable v-model="searchForm.taskType" placeholder="请选择销售类型">
              <el-option v-for="item in taskTypeList" :key="item.value" :label="item.type" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="核销状态">
            <el-select clearable v-model="searchForm.verifyType" placeholder="请选择核销状态">
              <el-option v-for="item in options" :key="item.id" :label="item.label" :value="item.id"></el-option>
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

          <el-form-item label="日期" prop="remittanceDate">
            <div class="block">
              <el-date-picker
                v-model="searchForm.shipmentsAffirmDate"
                type="daterange"
                align="right"
                unlink-panels
                clearable
                range-separator="起"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"
              ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="药品系列">
            <el-select clearable v-model="searchForm.ProductLineId" placeholder="请选择商品系列" @change="getProductName">
              <el-option v-for="item in productQuery.seriesList" :key="item.id" :label="item.lineName" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="药品规格">
            <el-select clearable v-model="searchForm.productId" placeholder="请选择商品名称">
              <el-option v-for="item in productQuery.standardList" :key="item.id" :label="item.productState" :value="item.id"></el-option>
            </el-select>
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
        <el-table-column v-for="(item,index) in columns" :key="index" :prop="item.prop" :show-overflow-tooltip="true" :label="item.label" :min-width="item.minWidth" :formatter="item.formatter" :align="item.align">
        	<template slot-scope="scope">
        		<el-select clearable v-model="scope.row[item.prop]" v-if="item.value == '3'">
              <el-option v-for="item in logisticsName" :key="item.id" :label="item.commonCarrierName" :value="item.commonCarrierName"></el-option>
           </el-select>
				    <span v-if="item.value !== '1' && item.value !== '2' && item.value !== '3'">{{ scope.row[item.prop] }}</span>
        		<el-input placeholder="请输入内容" v-show="item.value == '1'" v-model="scope.row[item.prop]"></el-input>
        		<div class="block">
        			<el-date-picker v-if="item.value == '2'" v-model="scope.row[item.prop]" type="date" placeholder="选择日期" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
        		</div>
        	</template>
        </el-table-column>
        <el-table-column label="操作" width="150" fixed="right" header-align="center" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">保存</el-button>
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
      logisticsName:[],
      loading: false,
      editItemId: '', // 传给子组件的参数 -- 怎么区分是编辑还是查看详情呢 标识+id 逗号分隔
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      // 仓库下拉框列表
      houseList: [],
      // 商务分区/省区筛选条件
      areaQuery: {
          divisionList: [], // 商务分区
          provinceList: []  // 省区列表
      },
      // 商品筛选条件
      productQuery: {
          seriesList: [], // 商品系列
          standardList: [] // 商品名称列表
      },
      // 查询参数
      searchForm: {
          filingDate: '',
          businessDivisionId: '', // 商务分区
          provinceId: '', // 省区
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
      //销售类型数据
      taskTypeList: [
        {
          value: "0",
          type: "集中发货"
        },
        {
          value: "1",
          type: "分配"
        },
        {
          value: "2",
          type: "日常合同"
        }
      ],
      // 表格表头
      columns: [
        {prop:"approvalStateStr",label:"状态",align:"center",minWidth:100},
	      {prop:"directiveCode",label:"发货指令单",align:"center",minWidth:100},
		    {prop:"warehouse",label:"仓库名",align:"center",minWidth:100},
		    {prop:"shipmentsAffirmDate",label:"发货时间",align:"center",minWidth:100},
		    {prop:"marketType",label:"销售类型",align:"center",minWidth:100},
		    {prop:"commerceName",label:"商业名称",align:"center",minWidth:150},
        {prop:"",label:"发/退货单号",align:"center",minWidth:100},
        {prop:"",label:"退货标识",align:"center",minWidth:100},
        {prop:"dateCode",label:"合同编号",align:"center",minWidth:100},
        {prop:"shippingAddress",label:"收货地址",align:"center",minWidth:100},
        {prop:"contactPersion",label:"联系人",align:"center",minWidth:100},
        {prop:"phone",label:"联系电话",align:"center",minWidth:100},
        {prop:"createPersion",label:"制单人",align:"center",minWidth:100},
        {prop:"InventoryCode",label:"存货代码",align:"center",minWidth:100},
        {prop:"productName",label:"存货名称",align:"center",minWidth:100},
        {prop:"deliveringAmount",label:"发货数量",align:"center",minWidth:100},
        {prop:"aChange",label:"件装量",align:"center",minWidth:100},
        {prop:"packageSum",label:"发货件数",align:"center",minWidth:100},
        {prop:"batchNumber",label:"批号",align:"center",minWidth:100},
        {prop:"dateOfManufacture",label:"生产日期",align:"center",minWidth:100},
        {prop:"approvalCode",label:"医药注册号",align:"center",minWidth:100},
        {prop:"unitPrice",label:"价税合计",align:"center",minWidth:100},
        {prop:"memo",label:"备注",align:"center",minWidth:100},
        {prop:"commercetype",label:"客户类别",align:"center",minWidth:100},
        {prop:"logisticsName",label:"物流承运商",align:"center",value:'3',minWidth:150},
        {prop:"logisticsCode",label:"物流单号",align:"center",value:'1',minWidth:150},
        {prop:"actualDeliveryDate",label:"仓库实际发货日期",align:"center",value:'2',minWidth:150},
        {prop:"expectedArrivalDate",label:"预计到货日期",align:"center",value:'2',minWidth:150},
        {prop:"actualDateOfArrival",label:"实际到货日期",align:"center",value:'2',minWidth:150},
        {prop:"confirmArrivalDate",label:"配送确认|到货日期",align:"center",value:'2',minWidth:150},
        {prop:"confirmMemo",label:"配送确认|备注",align:"center",value:'1',minWidth:150},
        {prop:"officialReceiptsSum",label:"实收件数",align:"center",value:'1',minWidth:150},
        {prop:"exceptionCase",label:"异常情况",align:"center",value:'1',minWidth:150},
        {prop:"explain",label:"说明",align:"center",value:'1',minWidth:150},
        {prop:"agreementDays",label:"协议到货天数",align:"center",value:'1',minWidth:150},
        {prop:"dalayDays",label:"延误天数",align:"center",value:'1',minWidth:150},
        {prop:"transportationMethod",label:"运输方式",align:"center",value:'1',minWidth:150},
        {prop:"entrustPersion",label:"委托经办人",align:"center",value:'1',minWidth:150},
        {prop:"driver",label:"驾驶员",align:"center",value:'1',minWidth:150},
        {prop:"licencePlate",label:"车牌号",align:"center",value:'1',minWidth:150},
        {prop:"toWmsDate",label:"传入WMS时间",align:"center",minWidth:100}
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
    //获取仓库下拉列表
    getHouse(){
    	let _this = this;
    	this.$api.approval
        .postscm({
          url: "/scm/scmZdGetMoneyWarehouse/v1/list",
          data: {
          	pageBean: {
			        page: 1,
			        pageSize: 100,
			        showTotal: true
			      },
            params: {},
            querys: [{operation: "EQUAL",property: "ownerId",relation: "AND", value: _this.ownerId }],
            sorter: []
          }
        })
        .then(res => {
          console.log(res)
          this.houseList = res.rows
        });
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
          console.log(res);
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
      this.searchProvinceList = this.divisionList.reduce((final, item, index, arr)=>{
          if(item.id == divisionId){
              final = item.provinceList;
          }
          return final
      },[]);
    },
    // 筛选查询
    searchquery() {
      let query = [];
      let searchForm = Object.assign({},this.searchForm);
      for(var key in searchForm) {
        if(searchForm[key]) {
          if(key === "shipmentsAffirmDate") { //日期
            query.push({
              hasInitValue: false,
              operation: "BETWEEN",
              relation: "AND",
              value: searchForm[key],
              property: key
            }); 
          } else if(key === "commerceName"){
            query.push({ //商业名称
              hasInitValue: false,
              operation: "EQUAL",
              relation: "AND",
              value: searchForm[key],
              property: key
            }); 
          }else if(key === "taskType"){
            query.push({ //销售类型
              hasInitValue: false,
              operation: "EQUAL",
              relation: "AND",
              value: searchForm[key],
              property: key
            }); 
          }else if(key === "warehouse"){
            query.push({ //仓库
              hasInitValue: false,
              operation: "EQUAL",
              relation: "AND",
              value: searchForm[key],
              property: key
            }); 
          }else if(key === "dateCode"){
            query.push({ //合同编号
              hasInitValue: false,
              operation: "EQUAL",
              relation: "AND",
              value: searchForm[key],
              property: key
            }); 
          }else if(key === "batchNumber"){
            query.push({ //批号
              hasInitValue: false,
              operation: "EQUAL",
              relation: "AND",
              value: searchForm[key],
              property: key
            }); 
          }else if(key === "productId"){
            query.push({ //产品
              hasInitValue: false,
              operation: "EQUAL",
              relation: "AND",
              value: searchForm[key],
              property: key
            }); 
          }else if(key === "ProductLineId"){
            query.push({ //产品系列
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
            querys: [{operation: "EQUAL",property: "ownerId",relation: "AND", value: self.ownerId }].concat(search),
            sorter: []
          }
        })
        .then(res => {
        	self.loading = false;
        	self.pageResult = res;
        	console.log(res)
        });
    },
    //列表保存
    handleEdit(val,row){
    	let obj = {}
    	obj.actualDateOfArrival = row.actualDateOfArrival
    	obj.actualDeliveryDate = row.actualDeliveryDate
    	obj.agreementDays = row.agreementDays
    	obj.confirmArrivalDate = row.confirmArrivalDate
    	obj.confirmMemo = row.confirmMemo
    	obj.dalayDays = row.dalayDays
    	obj.driver = row.driver
    	obj.entrustPersion = row.entrustPersion
    	obj.exceptionCase = row.exceptionCase
    	obj.expectedArrivalDate = row.expectedArrivalDate
    	obj.explain = row.explain
    	obj.id = row.shipmentsmessageId
    	obj.licencePlate = row.licencePlate
    	obj.logisticsCode = row.logisticsCode
    	obj.logisticsName = row.logisticsName //物流承运商
    	obj.officialReceiptsSum = row.officialReceiptsSum
    	obj.transportationMethod = row.transportationMethod
    	obj.shipmentsDatailId = row.id
			console.log(obj)
    	this.$api.xsgl
        .post({
          vueInstance: self,
          url: "/scm/scmFhShipmentsMessage/v1/save",
          data:obj
        })
        .then(res => {
        	console.log(res)
        	if(res.state == true){
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
    },
    //获取物流承运商
    getlogisticsName(){
    	this.$api.xsgl
        .post({
          vueInstance: self,
          url: "/scm/scmFhCommonCarrier/v1/list",
          data:{}
        })
        .then(res => {
        	this.logisticsName = res.rows;
        });
    },
  },
  mounted() {
    this.ownerId = storage.get("ownerId");
    // 获取商务分区
    this.getDivision();
    this.findPage();
    //获取产品系列
    this.getProduct();
    //获取仓库下拉列表
    this.getHouse();
    this.getlogisticsName();
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