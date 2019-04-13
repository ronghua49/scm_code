<template>
  <div>
    <transition name="el-fade-in">
    <div v-show="!isShowPage">
    <div class="content-item" v-show="showPage === '1'">
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
        <el-form
          label-position="right"
          size="small"
          label-width="120px"
          class="demo-ruleForm"
          :model="searchForm"
          ref="searchForm"
        >
          <el-form-item label="年度">
            <el-select clearable v-model="searchForm.year" placeholder="请选择年度" @change="getYear">
              <el-option
                v-for="item in yearList"
                :key="item"
                :label="item"
                :value="item"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="商务分区">
            <el-select clearable v-model="searchForm.businessDivisionId" placeholder="请选择商务分区" @change="getPrivence">
              <el-option
                v-for="item in areaQuery.divisionList"
                :key="item.id"
                :label="item.businessDivision"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="省区">
            <el-select clearable v-model="searchForm.provinceId" placeholder="请选择省区">
              <el-option
                v-for="item in areaQuery.provinceList"
                :key="item.id"
                :label="item.province"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="启用日期">
          	<el-date-picker
          		style="width: 100%;"
                v-model="searchForm.startUsingDate"
                type="daterange"
                value-format="yyyy-MM-dd HH:mm:ss"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期">
           </el-date-picker>
          </el-form-item>
          <el-form-item label="查询来源">
            <el-select clearable 
              v-model="searchForm.label" 
              placeholder="请选择查询来源">
              <el-option
                v-for="item in querySourceList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="产品系列">
            <el-select clearable 
              v-model="searchForm.productSeries" placeholder="请选择商品系列" 
              @change="getProductNameList">
              <el-option
                v-for="(item,index) in productQuery.seriesList"
                :key="item+index"
                :label="item.lineName"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="药品规格">
            <el-select clearable v-model="searchForm.standard" placeholder="请选择商品名称">
              <el-option
                v-for="item in productQuery.standardList"
                :key="item.id"
                :label="item.productName"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="商业名称">
              <el-input
                placeholder="请输入内容"
                v-model="searchForm.centername"
                clearable>
              </el-input>
          </el-form-item>
          
          <el-form-item label="合同编号">
            <el-input
                placeholder="请输入内容"
                v-model="searchForm.contractcode"
                clearable>
            </el-input>
          </el-form-item>
          <el-form-item label="批号">
            <el-input
                placeholder="请输入内容"
                v-model="searchForm.batchnumber"
                clearable>
            </el-input>
          </el-form-item>
          
        </el-form>
      </el-main>
      
      <el-row style="text-align:left;margin: 10px 0;">
          <el-button @click="showPageFunc" type="primary">发货分配</el-button>
      </el-row>

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
        <el-table class="test" 
          :data="pageResult.rows" 
          highlight-current-row 
          v-loading="loading" 
          element-loading-text="加载中"
          border stripe fit
          max-height="500" size="mini" align="left" style="width:100%;">
          <el-table-column 
                show-overflow-tooltip 
                v-for="(item,index) in columns"
				:key="item+index"
			    :prop="item.prop"
			    :label="item.label"
			    :width="item.minwidth"
			    :formatter="item.formatter"
			    align="center">
			    
		  </el-table-column>
		  <el-table-column label="操作" width="220" fixed="right" header-align="center" align="center">
        <template slot-scope="scope">
          <el-button :disabled="scope.row.approvalState != '0'" icon="el-icon-edit" size="mini"
              type="primary" @click="edit(scope.row)">编辑
          </el-button>
          <el-button icon="el-icon-view" size="mini" type="primary"
            @click="details(scope.row)">
                        查看详情
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
				:total="pageResult.total">
			</el-pagination>
        </div>
    </el-container>
    </div>
    </div>
    </transition>
    
    <!--显示申请页面-->
    <transition name="el-fade-in">
        <div v-show="showPage === '2'">
            <!--貌似非要用v-on-->
            <deliver-command-apply 
            	:areaQuery="areaQuery" 
            	:productQuery="productQuery" 
            	:searchForm="searchForm" 
            	:passparam="editItemId" 
            	v-on:closepage="closepage"></deliver-command-apply>
        </div>
    </transition>
    
    <!--显示编辑详情页面-->
    <transition name="el-fade-in">
    	<div v-show="isShowPage" :key="editKey">
    		<div class="searchHeader">
        <div class="searchheadleft">
          <el-button @click="showParentComp" size="mini" class="el-icon-close" type="primary">返回</el-button>
        </div>
        <div class="searchheadcenter">发货任务更新</div>
        <div class="searchheadright">
        	<!--<el-button
            v-if="applySteps.active === '1'"
            :disabled="applySteps.active !== '1'"
            size="mini"
            type="primary"
            @click="applyCache"
          >申请暂存</el-button>
          <el-button
            v-if="applySteps.active === '1'"
            :disabled="applySteps.active !== '1'"
            size="mini"
            type="primary"
            @click="applySubmit"
          >发起申请</el-button>-->
        </div>
        
    	</div>
    	<div class="header upheader">
        <div class="headleft">
          <span class="el-icon-tickets"></span>
          <span class="sx">基础数据</span>
        </div>
      </div>
      <div class="center">
          <el-form ref="form" disabled size="small" :model="form" label-width="80px">
            <el-form-item label="商务分区">
              <el-input v-model="form.businessDivision"></el-input>
            </el-form-item>
            <el-form-item label="省区">
              <el-input v-model="form.province"></el-input>
            </el-form-item>
            <el-form-item label="商业名称">
              <el-input v-model="form.commerceName"></el-input>
            </el-form-item>
          </el-form>
      </div>
      
      
      <div class="header" style="margin: 0">
        <div class="headleft">
          <span class="el-icon-tickets"></span>
          <span class="sx">发货任务 </span>
        </div>
      </div>
      
      <el-table
        :data="tableData.rows"
        border
        style="width: 100%">
        <el-table-column
        fixed
        align="center"
        type="index"
        label="序号"
        width="60">
        </el-table-column>
        <el-table-column
          v-for="(item,index) in columns2"
          :prop="item.prop"
          :label="item.label"
          :key="'shippingIns' + index"
          :min-width="item.width"
          :formatter="item.formatter"
          align="center"
          >
          <el-table-column
          v-if="item.children"
          v-for="(int,ins) in item.children"
          :prop="int.prop"
          :label="int.label"
          :key=" 'shippingInsChild'+ins"
          :min-width="int.width"
          :formatter="int.formatter"
          align="center"
         ></el-table-column>
         </el-table-column>
        </el-table>
      </div>
    </transition>
  </div>
</template>
<script>
// 筛选功能
import storage from "@/utils/storage";
import DeliverCommandApply from "./DeliverCommandApply"
export default {
  name: "DeliverGoodsManage",
  components: {
      "deliver-command-apply": DeliverCommandApply
  },
  computed:{
  	businessDivisionId(){
  		return this.searchForm.businessDivisionId
  	},
  	provinceId(){
  		return this.searchForm.provinceId;
  	},
  	productSeries(){
  		return this.searchForm.productSeries;
  	},
  },
  watch:{
  	businessDivisionId(val){
  		let _this=this; 
  		_this.areaQuery.divisionList.map(function(v,i){
  			if(v.id == val){
  				_this.searchForm.businessDivision = v.businessDivision//通过ID获取商务分区值
  			}
  		})
  	},
  	provinceId(val){
  		let _this = this;
  		_this.areaQuery.provinceList.map(function(v,i){
  			if(v.id == val){
  				_this.searchForm.province = v.province;//通过ID获取省区值
  			}
  		})
  	},
  	productSeries(val){
  		let _this = this;
  		_this.productQuery.seriesList.map(function(v,i){
  			if(v.id == val){
  				_this.searchForm.lineName = v.lineName;
  			}
  		})
  	},
  },
  data() {
    return {
      showPage: '1',
      ownerId: "",
      pageResult: {},
      loading: false,
      isShowPage: false,
      pageState:"",
      editKey: "",
      row:"",
      editItemId: '', // 传给子组件的参数 -- 怎么区分是编辑还是查看详情呢 标识+id 逗号分隔
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      // 查询来源列表
      querySourceList: [
          {label: '发货明细', value: '1'},
          {label: '按月滚动', value: '2'}
      ],
      // 年度下拉框列表
      yearList: [],
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
      // 经销商列表
      angecyList: [],
      // 查询参数
      searchForm: {
          filingDate: '',
          businessDivisionId: '', // 商务分区
          businessDivision: '',
          provinceId: '', // 省区
          province: '',
          commerceId: '',//商业名称
          commerceName:'',
          startUsingDate: '',//日期
          productSeries: '',//产品ID
          lineName: '',//产品名称
          codes:'',//合同
          centername:'',
          productNum:'',
          batchNumber:"",
          wareHorse:"",
          inventoryNum:"",
          deliveringAmount:"",
          shipmentsTaskId:"",
          warehouseCode:"",
          aChange:"",
          packageNum:""
      },

      // 表格表头
      columns: [
          {prop:"businessDivision",label:"商务分区",align:"center"},
	      {prop:"province",label:"省区",align:"center"},
		  {prop:"",label:"月度",align:"center"},
		  {prop:"",label:"发货日期",align:"center"},
		  {prop:"actualDeliveryDate",label:"仓库实际发货日期",align:"center"},
		  {prop:"",label:"退货标识",align:"center"},
          {prop:"marketType",label:"销售类型",align:"center"},
          {prop:"focusShipmentsMonth",label:"集中发货分配月",align:"center"},
          {prop:"dateCode",label:"合同编号",align:"center"},
          {prop:"commercetype",label:"客户类型",align:"center"},
          {prop:"",label:"客户编码",align:"center"},
          {prop:"commerceName",label:"客户名称",align:"center"},
          {prop:"productName",label:"产品名称",align:"center"},
          {prop:"shipmentsCode",label:"发货单号",align:"center"},
          {prop:"directiveCode",label:"指令编号",align:"center"},
          {prop:"contactPersion",label:"联系人",align:"center"},
          {prop:"phone",label:"联系电话",align:"center"},
          {prop:"productNum",label:"代码",align:"center"},
          {prop:"productState",label:"规格",align:"center"},
          {prop:"packageNum",label:"发货件数",align:"center"},
          {prop:"deliveringAmount",label:"发货数量",align:"center"},
          {prop:"price",label:"发货单价",align:"center"},
          {prop:"",label:"发货金额",align:"center"},
          {prop:"batchNumber",label:"批号",align:"center"},
          //{prop:"dateOfManufacture	",label:"生产日期",align:"center"},
          {prop:"",label:"有效期至",align:"center"},
          {prop:"",label:"药品注册号",align:"center"},
          {prop:"",label:"电子监管码",align:"center"},
          {prop:"",label:"总箱数",align:"center"},
          {prop:"memo",label:"备注",align:"center"},
          {prop:"",label:"协议到货天数",align:"center"},
          {prop:"expectedArrivalDate",label:"预计到货日期",align:"center"},
          {prop:"actualDateOfArrival",label:"实际到货日期",align:"center"},
          {prop:"dalayDays",label:"延误天数",align:"center"},
          {prop:"",label:"收货人",align:"center"},
          {prop:"",label:"收货电话",align:"center"},
          {prop:"",label:"实际件数",align:"center"},
          {prop:"",label:"制单人",align:"center"},
          //{prop:"",label:"审核人",align:"center"},
      ],
            //    详情页状态
      form:{
        businessDivision:"",
        province:"",
        commerceName:""
      },
      tableData:{
        rows:[]
      },
      columns2:[
        { prop: "dateCode", label: "合同编号", width:"120" },
        { prop: "shipmentsAffirmDate", label: "计划发货时间", width:"120" },
        { prop: "commerceName", label: "商业名称", width:"120" },
        { prop: "productName", label: "商品名称", width:"120" },
        { prop: "productNum", label: "药品编号", width:"120" },
        { prop: "medicineType", label: "药品类型", width:"120" },
        { prop: "productState", label: "规格型号", width:"120" },
        { prop: "achange", label: "件装量", width:"120" },
        { prop: "packageSum", label: "件数", width:"120" },
        { prop: "commercetype", label: "客户类型", width:"120" },
        { prop: "marketType", label: "销售类型", width:"120" },
        { prop: "batchNumber", label: "批号", width:"120" },
        { prop: "price", label: "单价", width:"120" },
        { prop: "memo", label: "发货备注", width:"120" },
        { prop: "goods", label: "发货", children:[
          {prop: "planShipmentsSum", label: "计划量", width:"120" },
          {prop: "deliveringAmount", label: "实际量", width:"120" }
        ]},
        { prop: "warehouse", label: "仓库", width:"120" },
        { prop: "stock", label: "当前库存量", width:"120" },
        { prop: "otc202", label: "收货人信息",  children:[
          {prop:"contactPersion", label:"联系人", width:"120" },
          {prop:"phone", label:"联系电话", width:"120" },
          {prop:"shippingAddress", label:"地址", width:"120" }
        ] }
      ],
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
    refreshPageRequest: function(page = 1) {
      this.pageRequest.page = page;
      this.findPage();
    },
    refreshPageSizeRequest: function(pageSize) {
      this.pageRequest.pageSize = pageSize;
      this.findPage();
    },
    toogleShowPage (flag) {
        let self = this;
        self.showPage = '-1';
        setTimeout(function() {
            self.showPage = flag;
        },500);
    },
    // 点击发货分配显示
    showPageFunc () {
        let self = this;
        self.editItemId = "addNew,";
        self.toogleShowPage('2'); 
    },
    //点击关闭编辑页面
    showParentComp() {
      //this.refresh += new Date();
      this.isShowPage = false;
    },
    //点击显示编辑页面
    edit(row){
      this.isShowPage = true;
      this.pageState = 'DeliverGoodsManage,'+row.id+','+row.shipmentsTaskId;
      console.log(row);
      this.row = row;
      /*this.flowData={
        taskId:row.taskId
      }*/
      this.getDetailsApproval(row.shipmentsTaskId);
    },
    //点击显示详情页面
    details(row){
      this.isShowPage = true;
      this.pageState = 'DeliverGoodsManage,'+row.id;
      console.log(row);
      this.row = row;
      /*this.flowData={
        taskId:row.taskId
      }*/
      this.getDetailsApproval(row.shipmentsTaskId);
    },
    getDetailsApproval(approvalID){
      let _this = this;
      this.$api.base.getScm({
        url:"/scm/scmFhShipmentsDatailMaster/v1/get/"+approvalID
      }).then(res=>{
       if (res) {
        //  _this.form = res.value;
      //    _this.tableData.rows = res.value.scmFhShipmentsDatailList;
        }
      })
    },
    // v-on 绑定不支持驼峰命名
    closepage () {
        let self = this;
        self.toogleShowPage('1');
        self.editItemId = '';
        self.forceRefresh = + new Date();
        console.log(this.pageResult);
        self.searchquery();
    },
    
    // 获取年度
    getYear () {
        /*let self = this;
        self.$api.xsgl
        .get({
          vueInstance: self,
          url: "/scm/scmFhShipmentsDatail/v1/getYears/" + self.ownerId,
          //data: {}
        }, function(res) {
            console.log(res);
            //self.productQuery.seriesList = res.value;
        });*/
       this.$api.xsgl
       .get({
       	url:"/scm/scmFhShipmentsDatail/v1/getYears/" + this.ownerId,
       	data: {}
       }).then(res => {
       	console.log(res);
       	/*for(key in this.searchForm){
       		this.searchForm[key] = res.value[key];
       	}*/
       	for(let i=0; i<res.value.length; i++){
       		if(res.value[i] == null){
       			
       		}else{
       			this.yearList = res.value[i];
       		}
       	}
       })

    },
    
    // 获取商务分区
    getDivision () {
        let self = this;
        self.$api.xsgl.getZdBusinessDivision({vueInstance: self}, function(res) {
            self.areaQuery.divisionList = res;
        });
    },
    // 获取省区
    getPrivence () {
        console.log(this.searchForm);
        this.searchForm.provinceId= ''
        let divisionId = this.searchForm.businessDivisionId // 商务分区ID
        this.areaQuery.provinceList = this.areaQuery.divisionList.reduce((final, item, index, arr)=>{
            if(item.id == divisionId){
                final = item.provinceList;
            }
            return final
        },[]);
    },
    // 获取产品系列
    getProductLine () {
        let self = this;
        self.$api.xsgl
        .post({
          vueInstance: self,
          url: "/scm/scmZsjProduct/v1/LineAndProductDownBox",
          //data: {}
        }, function(res) {
            console.log(res);
            self.productQuery.seriesList = res.value;
        });
    },
    getProductNameList (val) {
        console.log(val);
        let self = this;
        self.productQuery.seriesList.forEach(function(item,index) {
            if(item.id === val) {
                self.productQuery.standardList = item.productList;
            }
        });
    },
    // 筛选查询
    searchquery() {
        let query = [];
        let searchForm = Object.assign({},this.searchForm);
        for(var key in searchForm) {
          if(searchForm[key] !== '') {
            if(key === "AgreementStartDate") {
              query.push({
                hasInitValue: false,
                operation: "BETWEEN",
                relation: "AND",
                value: [searchForm[key] + "-01-01", searchForm[key] + "-12-31"],
                property: key
              }); 
            } else {
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
          url: "/scm/scmFhShipmentsDatail/v1/firstList",

          data: {
            pageBean: self.pageRequest,
            params: {},
            querys: [{hasInitValue: false,property: "ownerId", value: self.ownerId, operation: "EQUAL", relation: "AND"}].concat(search),
            sorter: []
          }
        }, function(res) {
            self.pageResult = res;
            self.loading = false;
        });
    },
    
  },
  
  mounted() {
    this.ownerId = storage.get("ownerId");
    // 获取商务分区
    this.getDivision();
    this.getProductLine();
    this.getYear();
    this.findPage();
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
.upheader{
	margin-top: 10px;
}
.center{
	margin: 25px 0;
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
.el-open-date{
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
.searchHeader {
  position: relative;
  width: 100%;
  font-size: 28px;
  font-weight: bold;
  background: #f3f3f3;
}
.searchheadleft {
  position: absolute;
  left: 10px;
}
.searchheadcenter {
  width: 100%;
  text-align: center;
  line-height: 1.5em;
}
.searchheadright {
  position: absolute;
  top: 0;
  right: 10px;
}
</style>