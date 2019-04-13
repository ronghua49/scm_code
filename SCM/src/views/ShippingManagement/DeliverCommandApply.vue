<template>
  <div>
    <!-- 标题 + 步骤条 showParentComp-->
    <div class="head-item">
      <div class="searchHeader">
        <div class="searchheadleft">
          <el-button @click="showParentComp" size="mini" class="el-icon-close" type="primary">关闭</el-button>
        </div>

        <div class="searchheadcenter">{{componentParams.title}}</div>
        <div
          v-if="componentParams.flag !== 'details' && applySteps.active !== '2'"
          class="searchheadright"
        >
          <el-button
            v-if="applySteps.active === '0'"
            size="mini"
            type="primary"
            @click="chooseTask"
          >下一步</el-button>
          <el-button
            v-if="applySteps.active === '1' && componentParams.flag !== 'edit'"
            size="mini"
            type="primary"
            @click="preSteps"
          >上一步</el-button>
          <el-button
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
          >发起申请</el-button>
        </div>
      </div>
      <!-- 步骤条 -->
      <template v-if="componentParams.flag!=='details'">
        <el-row>
          <el-steps
            :space="500"
            :align-center=true
            :active="stepsBar.active"
            finish-status="success"
            style="margin: 35px auto;"
          >
            <el-step title="选择发货任务"></el-step>
            <el-step title="添加发货明细"></el-step>
            <el-step title="完成"></el-step>
          </el-steps>
        </el-row>
      </template>
    </div>

    <!-- 选择分销商 -->
    <transition name="el-fade-in">
      <div class="content-item" v-show="applySteps.active === '0'">
        <div class="header">
          <div class="headleft">
            <span class="el-icon-search"></span>
            <span class="sx">选择发货任务</span>
          </div>
        </div>
        <el-row>
          <el-form
            :model="searchForm"
            :rules="searchFormRules"
            ref="searchForm"
            label-position="right"
            label-width="120px"
            size="small"
            class="step-one-form"
          >
            <el-form-item prop="businessDivisionId" label="商务分区">
              <el-select
                clearable
                v-model="searchForm.businessDivisionId"
                placeholder="请选择商务分区"
                @change="getPrivence2"
              >
                <el-option
                  v-for="item in areaQuery.divisionList"
                  :key="item.id"
                  :label="item.businessDivision"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item prop="provinceId" label="省区">
              <el-select clearable v-model="searchForm.provinceId" placeholder="请选择省区" @change="chooseName">
                <el-option
                  v-for="item in areaQuery.provinceList"
                  :key="item.id"
                  :label="item.province"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item prop="commerceId" label="商业名称">
            	<el-select clearable v-model="searchForm.commerceId" placeholder="请输入内容" @change="setCommerceName">
            		<el-option
            			v-for="item in commerceList"
            			:key="item.commerceId"
            			:label="item.commerceName"
            			:value="item.commerceId"></el-option>
            	</el-select>
            </el-form-item>
            <el-form-item prop="startUsingDate" label="启用日期">
          	<el-date-picker style="width: 100%;"
                v-model="searchForm.startUsingDate"
                type="daterange"
                value-format="yyyy-MM-dd HH:mm:ss"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期">
           </el-date-picker>
          </el-form-item>
            <el-form-item prop="lineName" label="产品">
              <el-select clearable v-model="searchForm.lineName" placeholder="请选择商品系列">
                <el-option
                  v-for="item in proList"
                  :key="item"
                  :label="item"
                  :value="item"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item prop="searchDelivery" label="发货情况">
            	<el-select clearable v-model="searchForm.searchDelivery" placeholder="请选择发货情况">
            		<el-option
            			v-for="item in searchDeliveryList"
            			:key="item.value"
            			:label="item.label"
            			:value="item.value"></el-option>
            	</el-select>
            </el-form-item>
            <el-form-item prop="searchTask" label="任务类型">
              <el-select clearable v-model="searchForm.searchTask" placeholder="请选择任务类型">
                <el-option
                  v-for="item in searchTaskList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item prop="codes" label="合同">
            	<el-select clearable v-model="searchForm.codes" placeholder="请输入内容">
            		<el-option
            			v-for="(item,index) in codeList"
            			:key="item+index"
            			:label="item"
            			:value="item"></el-option>
            	</el-select>
            </el-form-item>
            <el-form-item>
              
            </el-form-item>
          </el-form>
        </el-row>
      </div>
    </transition>

    <!-- 列表数据 -->
    <transition name="el-fade-in">
      <div v-show="applySteps.active === '1'">
        <div class="header">
          <div class="headleft">
            <span class="el-icon-tickets"></span>
            <span class="sx">
              <!--<span style="color: #409EFF;">【{{searchForm.businessDivision}}】</span>
              <span style="color: #409EFF;">【{{searchForm.province ? searchForm.province : '-'}}】</span>-->
              发货任务<span style="color: #409EFF;">【{{searchForm.commerceName}}】</span>
            </span>
          </div>
          <div class="headright">
            <!--<el-button size="mini" type="primary" @click="addRow">新增</el-button>-->
            <!--<el-button size="mini" type="danger" @click="deleteRow">删除</el-button>-->
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
          :show-overflow-tooltip="false"
          max-height="500"
          size="mini"
          align="left"
          style="width:100%;"
          @select="queryId"
          @select-all="queryAll"
        >
          <el-table-column type="selection" :selectable="checkboxT" width="55"></el-table-column>
          <el-table-column
            prop="type"
            key="type"
            label="类型"
            min-width="100"
            header-align="center"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="allocationCode"
            key="allocationCode"
            label="合同编号"
            header-align="center"
            align="center"
            min-width="100"
          ></el-table-column>
          <el-table-column
            prop="taskCreateDate"
            key="taskCreateDate"
            label="任务生成时间"
            header-align="center"
            align="center"
            min-width="150"
          ></el-table-column>
          <el-table-column
            prop="banDeliveryMessage"
            key="banDeliveryMessage"
            label="发货状态"
            header-align="center"
            align="center"
            min-width="150"
          ></el-table-column>
          <el-table-column
            prop="paymentType"
            key="paymentType"
            label="购销方式"
            header-align="center"
            align="center"
            min-width="80"
          ></el-table-column>
          <el-table-column
            prop="focusShipmentsMonth"
            key="focusShipmentsMonth"
            label="集中发货分配月"
            header-align="center"
            align="center"
            min-width="80"
          >
          </el-table-column>

          <el-table-column
            prop="isSms"
            key="isSms"
            label="是否SMS"
            header-align="center"
            align="center"
            min-width="50"
          >
          </el-table-column>

          <el-table-column
            prop="sendProductDate"
            key="sendProductDate"
            label="计划发货时间"
            header-align="center"
            align="center"
            min-width="100"
          >
          </el-table-column>

          <el-table-column
            prop="commerceName"
            key="commerceName"
            label="商业名称"
            header-align="center"
            align="center"
            min-width="150"
          ></el-table-column>
          <el-table-column
            prop="productName"
            key="productName"
            label="商品名称"
            header-align="center"
            align="center"
            min-width="120"
          >
          </el-table-column>

          <el-table-column
            prop="productNum"
            key="productNum"
            label="药品编号"
            header-align="center"
            align="center"
            min-width="80"
          >
          </el-table-column>
          <el-table-column
            prop="MedicineType"
            key="MedicineType"
            label="药品类型"
            header-align="center"
            align="center"
            min-width="80"
          >
          </el-table-column>
          <el-table-column
            prop="productState"
            key="productState"
            label="规格型号"
            header-align="center"
            align="center"
            min-width="100"
          >
          </el-table-column>
          <el-table-column
            prop="aChange"
            key="aChange"
            label="件装量"
            header-align="center"
            align="center"
            min-width="80"
          >
          </el-table-column>
          <el-table-column
          	prop="packageNum"
          	key="packageNum"
          	label="件数"
          	header-align="center"
          	align="center"
          	min-width="80">
          </el-table-column>
          <el-table-column
            prop="commercetype"
            key="commercetype"
            label="客户类型"
            header-align="center"
            align="center"
            min-width="80"
          >
          </el-table-column>
          <el-table-column
            prop="marketType"
            key="marketType"
            label="销售类型"
            header-align="center"
            align="center"
            min-width="80"
          >
          </el-table-column>
          <el-table-column
            prop="price"
            key="price"
            label="单价"
            header-align="center"
            align="center"
            min-width="50"
          >
          </el-table-column>
          <el-table-column
            prop="memo"
            key="memo"
            label="备注"
            header-align="center"
            align="center"
            min-width="100"
          >
          </el-table-column>
          <el-table-column
            prop="planShipmentsSum"
            key="planShipmentsSum"
            label="计划发货量"
            header-align="center"
            align="center"
            min-width="80"
          >
          </el-table-column>
          <el-table-column label="收货人信息" align="center">
	        <el-table-column
	          prop="contactPersion"
	          label="联系人"
	          align="center"
	          width="80">
	        </el-table-column>
	        <el-table-column
	          prop="phone"
	          label="联系电话"
	          align="center"
	          width="100">
	        </el-table-column>
	        <el-table-column
	          prop="address"
	          label="地址"
	          align="center"
	          width="120">
	        </el-table-column>
	       </el-table-column>
          <!-- <el-table-column v-if="componentParams.flag!=='details'" label="操作" width="150" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button icon="el-icon-edit" size="small" type="danger"  @click="deleteRow(scope.$index, scope.row)" >删除</el-button>
            </template>
          </el-table-column>-->
        </el-table>
        
        <!--从表数据-->
        <!--表格数据-->
        <div class="table-section">
          <div class="header">
            <div class="header-left">
              <i class="el-icon-tickets"></i>数据列表
            </div>

            <!--<div class="header-right">
              <el-button @click="deleteSelectedRow" size="mini" type="danger">
                <i class="el-icon-delete">删除</i>
              </el-button>
            </div>-->
          </div>
          <!--表格栏-->
          <el-table
            class="test"
            :data="childTable.pageResult.rows"
            highlight-current-row
            v-loading="childTable.loading"
            element-loading-text="加载中"
            border
            stripe
            fit
            max-height="500"
            size="mini"
            align="left"
            style="width:100%;"
            
          >
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column
              show-overflow-tooltip
              v-for="item in childTable.columns"
              :key="item.prop"
              :prop="item.prop"
              :label="item.label"
              :width="item.minwidth"
              :formatter="item.formatter"
              :align="item.align"
            >
             <template slot-scope="scope">
             	<span v-if="item.prop != 'batchNumber'&&item.prop != 'memo'">{{scope.row[item.prop]}}</span>
              	<el-select v-if="item.prop=='batchNumber'" v-model="scope.row[item.prop]" @change="selectByBN($event,scope.$index)">
              		<el-option
              			v-for="item in batchNumbers"
              			:key="item"
              			:label="item"
              			:value="item">
              		</el-option>
              	</el-select>
              	<el-input v-if="item.prop=='memo'" v-model="scope.row[item.prop]"></el-input>
             </template>
             <el-table-column v-for="childItem in item.children" :key="childItem.prop"
               :prop="childItem.prop"
               :label="childItem.label" 
              >
              <template slot-scope="scope">
              	<el-input v-if="childItem.prop=='planShipmentsSum'" v-model="scope.row[childItem.prop]" :disabled="true" class="input"></el-input>
              	<el-input v-if="childItem.prop=='deliveringAmount'" v-model="scope.row[childItem.prop]" @blur="packageNums"></el-input>
              	<el-input v-if="childItem.prop=='stock'" v-model="scope.row[childItem.prop]"></el-input>
              	<el-input v-if="childItem.prop=='contactPersion'" v-model="scope.row[childItem.prop]" :disabled="true"></el-input>
              	<el-input v-if="childItem.prop=='phone'" v-model="scope.row[childItem.prop]" :disabled="true"></el-input>
              	<el-select v-if="childItem.prop=='shippingAddress'" v-model="scope.row[childItem.prop]" placeholder="请选择地址">
              		<el-option
              			v-for="item in addressList"
              			:key="item.id"
              			:label="item.address"
              			:value="item.id">
              		</el-option>
              	</el-select>
              </template>
             </el-table-column>
            </el-table-column>
            
          </el-table>
        </div>

      </div>
    </transition>
    <!-- 点击发起申请 显示完成提示页面 -->
    <transition name="el-fade-in">
      <div class="content-item" v-show="applySteps.active === '2'">
        <div class="success">
          <span class="el-icon-check"></span> 您已完成
          <span style="color: #409EFF;">【{{searchForm.commerceName}}】</span>
          发货指令申请
          <span style="color: #409EFF;">【{{applySteps.completeTitle}}】</span>
        </div>
        <div class="success-bottom">
          共
          <span style="color: #409EFF;">【{{childTable.pageResult.rows.length}}】</span>条发货明细
        </div>
      </div>
    </transition>
     
  </div>
</template>

<script>
// 省级平台和上游商业下拉框接口 /scm/scmZsjCommerce/v1/firstList 省区id 商务分区id ownerId approvalState=2
import storage from "@/utils/storage";
export default {
  name: "DeliverCommandApply",
  props: ["passparam","searchForm","areaQuery","productQuery"],
  created() {},
  mounted() {
    this.ownerId = storage.get("ownerId");
    this.getDivision2();
    this.getProList();
    this.getCodeList();
  },
  data() {
    return {
      componentParams: {
        flag: "", // 是编辑还是查看详情还是申请 与id是配对的
        id: "",
        title: "发货指令申请",
        detailsdisabled: false
      },
      /*searchForm: {
        businessDivision: "",
        businessDivisionId: "",
        province: "",
        provinceId: "",
        commerceName:"",
        startUsingDate:"",
        lineName:"",
        codes:"",
        commerceId:"",
        productNum:"",
        batchNumber:"",
        wareHorse:"",
        inventoryNum:"",
        deliveringAmount:"",
        shipmentsTaskId:"",
        warehouseCode:"",
        aChange:"",
        packageNum:""
      },*/
      searchFormRules: {
        businessDivisionId: [
          { required: true, message: "请选择商务分区", trigger: "blur" }
        ],
        /*provinceId: [
          {required: true, message: "请选择省区", trigger: "blur"}
        ],*/
        commerceId: [
          {required: true, message: "请选择商业名称", trigger: "blur"}
        ]
      },
      //发货情况
      searchDeliveryList:[
        {label: '发货任务', value: '0'},
        {label: '剩余任务', value: '1'}
      ],
      //任务类型
      searchTaskList:[
      	{label:"集中发货",value:"0"},
      	{label:"分配",value:"1"},
      	{label:"日常合同",value:"2"}
      ],

      ownerId: "",
      // 步骤条
      stepsBar: {
        active: 0
      },
      // 申请步骤
      applySteps: {
        active: "", // 0 -- 显示分区/省区选择框；1 -- 显示列表；2 -- 显示完成页面
        completeTitle: ""
      },
      pageResult: {
        approvalState: "",
        rows: []
      },
      loading: false,
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      /*areaQuery: {
      	divisionList: [],
      	searchProvinceList: []
      },
      productQuery:{
      	seriesList:[]
      },*/
      //商业名称列表
      commerceList:[],
      banDeliveryList:[],
      batchNumbers:[],
      batchNumberss:[],
      addressList:[],
      proList:[],
      codeList:[],
      //表格中当前选中行
      multipleSelection: [],

      // 子表命名空间
      childTable: {
          pageResult: {
          	approvalState: "",
       			rows: []
          },
          columns: [
             { prop: "allocationCode", label: "合同编号", align: "center" },
              { prop: "taskCreateDate", label: "任务生成时间", align: "center" },
              { prop: "paymentType", label: "购销方式", align: "center" },
              { prop: "focusShipmentsMonth", label: "集中发货分配月", align: "center" },
              { prop: "isSms", label: "是否SMS", align: "center" },
              { prop: "sendProductDate", label: "计划发货时间", align: "center" },
              { prop: "commerceName", label: "商业名称", align: "center" },
              { prop: "productName", label: "商品名称", align: "center" },
              { prop: "productNum", label: "药品编号", align: "center" },
              { prop: "MedicineType", label: "药品类型", align: "center" },
              { prop: "productState", label: "规格型号", align: "center" },
              { prop: "aChange", label: "件装量", align: "center" },
              { prop: "packageNum", label: "件数", align: "center" },
              { prop: "commercetype", label: "客户类型", align: "center" },
              { prop: "marketType", label: "销售类型", align: "center" },
              { prop: "batchNumber", label: "批号", align: "center" },
              { prop: "price", label: "单价", align: "center" },
              { prop: "memo", label: "发货备注", align: "center" },
              { prop: "goods", label: "发货", align: "center", children:[
                {prop: "planShipmentsSum", label: "计划量"},
                {prop: "deliveringAmount", label: "实际量"}
              ]},
              { prop: "wareHorse", label: "仓库", align: "center" },
              { prop: "warehouseCode", label: "仓库编号", align: "center"},
              { prop: "stock", label: "当前库存量", align: "center" },
              { prop: "otc202", label: "收货人信息", align: "center", children:[
              	{prop:"contactPersion", label:"联系人"},
              	{prop:"phone", label:"联系电话"},
              	{prop:"shippingAddress", label:"地址"}
              ] }
          ]
      }
    };
  },
  watch: {
    passparam: "getId"
  },
  methods: {
  	// 获取商务分区
    getDivision2 () {
        let self = this;
        self.$api.xsgl.getZdBusinessDivision({vueInstance: self}, function(res) {
            self.areaQuery.divisionList = res;
        });
    },
    // 获取省区
    getPrivence2 () {
        console.log(this.searchForm);
        this.searchForm.provinceId= ''
        let divisionId = this.searchForm.businessDivisionId; // 商务分区ID
        this.areaQuery.provinceList = this.areaQuery.divisionList.reduce((final, item, index, arr)=>{
            if(item.id == divisionId){
                final = item.provinceList;
            }
            return final
        },[]);
        this.getCommerceList();
    },
    //选择商业名称
    chooseName(){
    	let query = [];
    	let searchForm = Object.assign({}, this.searchForm);
    	for(var key in searchForm){
   				if(key == "provinceId"){
    				query.push({
    					hasInitValue: false,
              operation: "EQUAL",
              relation: "AND",
              value: searchForm[key],
              property: key
    				});
    			}
    		}
    	this.getCommerceList(query);
    },
    //商业名称列表
    getCommerceList(search = [],val ){
    	let self = this;
    	self.commerceList.splice(0);
        self.searchForm.commerceId = '';
        self.searchForm.commerceName = '';
        self.areaQuery.provinceList.forEach(function(item,index) {
          if(item.id == val) {
              self.searchForm.province = item.province;
          }
        });
        self.$api.xsgl
        .post({
        	vueInstance: self,
       		url:"/scm/scmFhShipmentsTask/v1/commerceList",
       		data: {
					pageBean: self.pageRequest,
		            params: {},
		            querys: [
		            	{hasInitValue: false,property: "businessDivisionId", value: self.searchForm.businessDivisionId, operation: "EQUAL", relation: "AND"},
		            	/*{hasInitValue: false,property: "provinceId", value: self.searchForm.provinceId, operation: "EQUAL", relation: "AND"},*/
		            	{hasInitValue: false,property: "a.ownerId", value: self.ownerId, operation: "EQUAL", relation: "AND"}
		            ].concat(search),
		            sorter: []
       		}
       }).then(res => {
       	console.log(res);
       	self.commerceList = res;
       })
    },
    //设置商品名称
    setCommerceName(val){
    	let self = this;
    	//self.$forceUpdate();//强制刷新，页面正常选值
    	self.commerceList.map(function(item, index){
    		if(val == item.commerceId){
    			self.searchForm.commerceName = item.commerceName;
    		}
    	});
    },
    // 批号
    getBatchNumbers(){
    	let self = this;
    	console.log(self.searchForm);
    	self.$api.xsgl
    	.post({
    		url:"/scm/scmFhShipmentsDatail/v1/batchNumbers/"+self.searchForm.productNum,
    		data:{}
    	}).then(res => {
    		console.log(res);
    		self.batchNumberss = res.value;
    	  let sum = [];
    		for(let i=0; i<res.value.length; i++){
    			sum.push(res.value[i].batchNumber+" | "+res.value[i].aChange+"支/件 | "+res.value[i].warehouse+"(整件) | "+res.value[i].inventoryNum+" | "+res.value[i].expiryDate+"个月");
    		}
    		self.batchNumbers = sum;
    	})
    },
    selectByBN(val,index){
    	let self = this;
    	let id = self.searchForm.shipmentsTaskId;
    	console.log(val)
    	console.log(index)
    	for(let i=0; i<self.batchNumbers.length; i++){
    		
      		if(self.batchNumbers[i] == val){
      			self.childTable.pageResult.rows[index].wareHorse = self.batchNumberss[i].warehouse;
    		    self.childTable.pageResult.rows[index].stock = self.batchNumberss[i].inventoryNum;
    		    self.childTable.pageResult.rows[index].warehouseCode = self.batchNumberss[i].warehouseCode;
    			/*self.searchForm.batchNumber = self.batchNumberss[i].batchNumber;
    			self.searchForm.wareHorse = self.batchNumberss[i].warehouse;
      			
    			self.searchForm.inventoryNum = self.batchNumberss[i].inventoryNum;
      			
    			*/
      		}
    		
    	}
    },
    // address
    getAddressList(){
    	let self = this;
    	console.log(self.searchForm);
    	self.$api.xsgl
    	.post({
    		url:"/scm/scmZsjCommerceShipAddress/v1/list",
    		data:{
    			pageBean: self.pageRequest,
    			params: {},
    			querys:[{hasInitValue: false,property: "commerceFirstId", operation: "EQUAL", relation: "AND", value: self.searchForm.commerceId}],
	  			sorter: []
	  			}
    	}).then(res => {
    		console.log(res);
    		self.addressList = res.rows;
    	})
    },
    // 产品列表
    getProList(){
    	let self = this;
    	self.$api.xsgl
    	.get({
    		url:"/scm/scmFhShipmentsTask/v1/proList/" + self.ownerId,
    		data:{}
    	}).then(res => {
    		console.log(res);
    		self.proList = res;
    	})
    },
    // 合同编号列表
    getCodeList(){
    	let self = this;
    	self.$api.xsgl
    	.get({
    		url:"/scm/scmFhShipmentsTask/v1/codeList/" + self.ownerId,
    		data:{}
    	}).then(res => {
    		console.log(res);
    		self.codeList = res;
    	})
    },
    resetValue(e) {
      // 小数点后两位，不能小于0
      let self = this;
      let value = e.target.value;
      e.target.value = value.match(/^\d*(\.?\d{0,2})/g)[0] || null;
    },

    getId() {
      let self = this;
      let middleArr = self.passparam.split(",");
      self.componentParams.flag = middleArr[0];
      self.componentParams.id = middleArr[1];

      if (self.componentParams.flag === "addNew") {
        //self.applySteps.active = '0';
        self.toogleStepPage("0");
      }

      if (self.componentParams.flag === "details") {
        self.componentParams.title = "发货指令申请";
        self.componentParams.detailsdisabled = true;
      }
      if (self.componentParams.id && self.componentParams.flag !== "addNew") {
        //self.applySteps.active = '1';
        self.toogleStepPage("1");
        self.$api.xsgl.get(
          {
            vueInstance: self,
            url: "/scm/scmXsDistributorAgreementSummary/v1/get/" + self.componentParams.id,
            data: {}
          },
          function(res) {
            console.log(res);
            // self.pageResult = res;
            if (res.approvalState) {
              self.pageResult.approvalState = res.approvalState;
            }
            self.pageResult.rows = res.scmXsDistributorClauseList;
            self.searchForm.businessDivision = res.businessDivision;
            self.searchForm.businessDivisionId = res.businessDivisionId;
            self.searchForm.province = res.province;
            self.searchForm.provinceId = res.provinceId;
          }
        );
      }
    },
    // 向父组件传值
    showParentComp() {
      this.$emit("closepage", {});
    },
    refreshPageRequest: function(page = 1) {
      this.pageRequest.page = page;
      this.findPage();
    },
    refreshPageSizeRequest: function(pageSize) {
      this.pageRequest.pageSize = pageSize;
      this.findPage();
    },
    // 控制步骤间页面切换的动画流畅
    toogleStepPage(flag) {
      let self = this;
      self.applySteps.active = "-1";
      setTimeout(function() {
        self.applySteps.active = flag;
      }, 500);
    },
    // 上一步按钮
    preSteps() {
      let self = this;
      //self.applySteps.active = '0';
      self.toogleStepPage("0");
      self.stepsBar.active = 0;
    },
    
    //选择发货任务
    chooseTask(){
    	let query = [];
    	let searchForm = Object.assign({}, this.searchForm);
    	console.log(searchForm);
    	for(var key in searchForm){
   				if(key == "provinceId" && searchForm.provinceId != ""){
    				query.push({
    					hasInitValue: false,
              operation: "EQUAL",
              relation: "AND",
              value: searchForm[key],
              property: key
    				});
    			}else if(key == "taskCreateDate"){
    				query.push({
    					hasInitValue: false,
              operation: "BETWEEN",
              relation: "AND",
              value: searchForm[key],
              property: key
    				});
    			}else if(key == "productName"){
    				query.push({
    					hasInitValue: false,
              operation: "EQUAL",
              relation: "AND",
              value: searchForm[key],
              property: key
    				});
    			}else if(key == "type"){
    				query.push({
    					hasInitValue: false,
              operation: "EQUAL",
              relation: "AND",
              value: searchForm[key],
              property: key
    				});
    			}else if(key == "taskType"){
    				query.push({
    					hasInitValue: false,
              operation: "EQUAL",
              relation: "AND",
              value: searchForm[key],
              property: key
    				});
    			}else if(key == "dateCode"){
    				query.push({
    					hasInitValue: false,
              operation: "EQUAL",
              relation: "AND",
              value: searchForm[key],
              property: key
    				});
    			}
    		}
    	this.nextSteps(query);
    	console.log(query);
    },
    // 下一步
    nextSteps(search = []) {
      let self = this;
      self.$refs["searchForm"].validate(valid => {
        if (valid) {
          self.toogleStepPage("1");
          self.stepsBar.active = 1;
          self.$api.xsgl
	  		.post({
	  			vueInstance: self,
	  			url:"/scm/scmFhShipmentsTask/v1/firstList",
	  			data:{
	  				pageBean: self.pageRequest,
		            params: {},
		            querys: [
		            	{hasInitValue: false,property: "businessDivisionId", value: self.searchForm.businessDivisionId, operation: "EQUAL", relation: "AND"},
		            	/*{hasInitValue: false,property: "provinceId", value: self.searchForm.provinceId, operation: "EQUAL", relation: "AND"},*/
		            	{hasInitValue: false,property: "commerceId", operation: "EQUAL", relation: "AND", value: self.searchForm.commerceId},
		            	/*{hasInitValue: false,property: "taskCreateDate", operation: "BETWEEN", relation: "AND", value: self.searchForm.startUsingDate},
		            	{hasInitValue: false,property: "productName", operation: "EQUAL", relation: "AND", value: self.searchForm.lineName},
		            	{hasInitValue: false,property: "type", value: self.searchForm.searchTask, operation: "EQUAL", relation: "AND"},
		            	{hasInitValue: false,property: "taskType", value: self.searchForm.searchDelivery, operation: "EQUAL", relation: "AND"},
		            	{hasInitValue: false,property: "dateCode", value: self.searchForm.codes, operation: "EQUAL", relation: "AND"},*/
		            ].concat(search),
		            sorter: []
	  			}
	  		},function(res){
	  				console.log(res);
	  				let val= res.rows;
	  				for(let i = 0; i < val.length; i++){
	  					//banDelivery发货状态0禁止发货checkbox不可用 1正常发货checkbox可用
	  					self.banDeliveryList.push(val[i].banDelivery);
	  					
	  					//类型
	  					let type = val[i].taskType;
	  					if(type=="0"){
	  						type = "集中发货";
	  					}else if(type == "1"){
	  						type = "分配";
	  					}else if(type == "2"){
	  						type = "日常合同";
	  					}
	  					//购销方式(0预付款,1赊销)
	  					 let paymentType = val[i].paymentType;
	  					 if(paymentType == "0"){
	  					 	  paymentType = "预付款";
	  					 }else if(paymentType == "1"){
	  					 		paymentType = "赊销";
	  					 }
	  					 //是否sms  0 否  1是
	  					 let sms = val[i].isSms;
	  					 if(sms == "0"){
	  					 	sms = "否";
	  					 }else if(sms == "1"){
	  					 	sms = "是";
	  					 }
	  					let list ={
	  						type:type,
		  					allocationCode:val[i].dateCode,
		  					taskCreateDate:val[i].taskCreateDate,
		  					banDeliveryMessage:val[i].banDeliveryMessage,
		  					paymentType:paymentType,
		  					focusShipmentsMonth:val[i].focusShipmentsMonth,
		  					isSms:sms,
		  					sendProductDate:val[i].sendProductDate,
		  					commerceName:val[i].commerceName,
		  					productName:val[i].productName,
		  					productNum:val[i].productNum,
		  					MedicineType:val[i].medicineType,
		  					productState:val[i].productState,
		  					aChange:val[i].aChange,
		  					commercetype:val[i].commercetype,
		  					marketType:val[i].marketType,
		  					price:val[i].price,
		  					memo:val[i].memo,
		  					planShipmentsSum:val[i].planShipmentsSum,
		  					contactPersion:val[i].contactPersion,
		  					phone:val[i].phone,
		  					address:val[i].address,
		  					shipmentsTaskId:val[i].id,
		  					packageNum:val[i].packageNum,
	  				}
	  					self.searchForm.productNum = val[i].productNum;
	  					self.searchForm.aChange = val[i].aChange;
	  					self.searchForm.shipmentsTaskId = val[i].id;	  					
	  			  	self.pageResult.rows.push(list);
	  				}
       });
    		}else{
    			return false;
    		}
     });
      },
checkboxT(row, rowIndex){
	console.log(row, rowIndex);
	if(row.banDeliveryMessage == "可正常发货"){
		return true;
	}else if(row.banDeliveryMessage == "发货控制中禁止发货"){
		return false;
	}
/*	let self = this;
	console.log(self.banDeliveryList);
  self.banDeliveryList.forEach(function(item, index){
  	if(item == "1"){
  		return true;
  	}else if(item == "0"){
  		return false;
  	}
  })*/
},

	//新增发货任务
/*	addRow(){
		let list ={
			clauseCode:'1',
			commerceName :'2',
			commerceLevel :'3',
			scm_zsj_commerceState :'4',
			functionType :'5',
			isSubsidiary :'6',
			starLevel :'7',
			integral:'8'
		}
		this.pageResult.rows.push(list);
	},*/
	//获取这一行的id传递给其他列表
	queryId(selecttion,val){
		let self = this;
		/*self.multipleSelection = val;
		console.log(self.multipleSelection);*/
		let checked = selecttion.length !=0 && selecttion.indexOf(val) != -1;
		if(checked == 0){
			self.childTable.pageResult.rows.splice(self.multipleSelection,1);
		}else if(checked == true){
			let list = {
					allocationCode:val.allocationCode,//合同编号
					taskCreateDate:val.taskCreateDate,//任务生成时间
					paymentType:val.paymentType,//购销方式
					focusShipmentsMonth:val.focusShipmentsMonth,//集中发货分配月
					isSms:val.isSms,//是否SMS
					sendProductDate:val.sendProductDate,//计划发货时间
					commerceName:val.commerceName,//商业名称
					productName:val.productName,//商品名称
					productNum:val.productNum,//药品编号
					MedicineType:val.MedicineType,//药品类型
					productState:val.productState,//规格型号
					aChange:val.aChange,//件装量
					//packageNum:self.searchForm.packageNum,//件数
					commercetype:val.commercetype,//客户类型
					marketType:val.marketType,//销售类型
					batchNumber:self.searchForm.batchNumber,//批号
					price:val.price,//单价
					//memo:val.memo,//备注
					planShipmentsSum:val.planShipmentsSum,//计划发货量
					deliveringAmount:self.searchForm.deliveringAmount,//实际量
					stock:self.searchForm.inventoryNum,//当前库存量
					wareHorse:self.searchForm.wareHorse,//仓库
					contactPersion:val.contactPersion,//联系人
					phone:val.phone,//联系电话
					//address:val.address//地址
					shipmentsTaskId:val.shipmentsTaskId,
				};
				self.childTable.pageResult.rows.push(list);
				/*if(self.childTable.pageResult.rows.length>0){
					for(let i=0; i<self.childTable.pageResult.rows.length; i++){
						if(self.childTable.pageResult.rows[i].shipmentsTaskId != val.shipmentsTaskId){
							self.childTable.pageResult.rows.push(list);
						}
					}
				}else{
					self.childTable.pageResult.rows.push(list);
				}*/
		}
		this.getBatchNumbers();
		this.getAddressList();
	},
	//全选
	queryAll(selecttion){
		let self = this;
		/*self.multipleSelection = val;
		console.log(self.multipleSelection);*/
		let checked = selecttion.length !=0;
		if(checked == false){
			self.childTable.pageResult.rows.splice(self.multipleSelection);
		}else if(checked == true){
			for(let i=0; i<selecttion.length; i++){
				let list = {
					allocationCode:selecttion[i].allocationCode,//合同编号
					taskCreateDate:selecttion[i].taskCreateDate,//任务生成时间
					paymentType:selecttion[i].paymentType,//购销方式
					focusShipmentsMonth:selecttion[i].focusShipmentsMonth,//集中发货分配月
					isSms:selecttion[i].isSms,//是否SMS
					sendProductDate:selecttion[i].sendProductDate,//计划发货时间
					commerceName:selecttion[i].commerceName,//商业名称
					productName:selecttion[i].productName,//商品名称
					productNum:selecttion[i].productNum,//药品编号
					MedicineType:selecttion[i].MedicineType,//药品类型
					productState:selecttion[i].productState,//规格型号
					aChange:selecttion[i].aChange,//件装量
					//packageNum:self.searchForm.packageNum,//件数
					commercetype:selecttion[i].commercetype,//客户类型
					marketType:selecttion[i].marketType,//销售类型
					batchNumber:self.searchForm.batchNumber,//批号
					price:selecttion[i].price,//单价
					//memo:val.memo,//备注
					planShipmentsSum:selecttion[i].planShipmentsSum,//计划发货量
					deliveringAmount:self.searchForm.deliveringAmount,//实际量
					stock:self.searchForm.inventoryNum,//当前库存量
					wareHorse:self.searchForm.wareHorse,//仓库
					contactPersion:selecttion[i].contactPersion,//联系人
					phone:selecttion[i].phone,//联系电话
					//address:val.address//地址
					shipmentsTaskId:selecttion[i].shipmentsTaskId,
				};
				if(self.childTable.pageResult.rows.length>0){
					let rows = [];
					for(let a=0; a<self.childTable.pageResult.rows.length; a++){
						rows.push(self.childTable.pageResult.rows[a].shipmentsTaskId);
					}
					for(let j=0; j<self.childTable.pageResult.rows.length; j++){
						if(self.childTable.pageResult.rows[j].shipmentsTaskId != selecttion[i].shipmentsTaskId
						&& rows.indexOf(selecttion[i].shipmentsTaskId) == -1){
							self.childTable.pageResult.rows.push(list);
							rows.push(selecttion[i].shipmentsTaskId);
						}
					}
				}else{
					self.childTable.pageResult.rows.push(list);
				}
			}
		}
		this.getBatchNumbers();
		this.getAddressList();
	},
	packageNums(){
		let self = this.childTable.pageResult.rows;
		for(let i=0; i<self.length; i++){
			self[i].packageNum = self[i].deliveringAmount/self[i].aChange;
			this.searchForm.packageNum = self[i].packageNum;
		}
	},
    // 删除表格行
    deleteRow() {
    	let self = this;
    	let length = self.multipleSelection.length;
    	for(let i=0; i<length; i++){
    		console.log(self.multipleSelection[i]);
    		self.pageResult.rows.splice(self.multipleSelection[i],1);
    	}
    	
    	/*self.$confirm('确认删除选中任务吗？','提示',{
    		type:'warning'
    	}).then(()=> {
      		self.pageResult.rows.splice(index, 1);
      		self.$message({message: '删除成功', type: 'success',duration: 800});
    	})*/
    },
    //数据列表删除
    deleteSelectedRow(index, item){
    	this.$confirm('确认删除选中任务吗？','提示',{
    		type:'warning'
    	}).then(()=>{
    		let self = this;
    		self.childTable.pageResult.rows.splice(index, 1);
    		self.$message({message:'删除成功', type: 'success', duration:800});
    	})
    },
    // 封装发起申请和申请暂存接口
    // params -- url, callback
    /*sendApplyOrSave(params) {
      let self = this;
      self.$api.xsgl.post(params.url,
        params.callback
      );
    },*/
    // 发起申请
    applySubmit() {
      let self = this;
      let row = self.childTable.pageResult.rows;
      if(row.length == 0){
      	this.$message({
						message: "请添加发货明细 ",
						type: "error",
						duration: 1000
						});
					return false
      }else{
	     for(let i=0; i<row.length; i++){
      	if(row[i].deliveringAmount ==""){
      		this.$message({
						message: "请填写发货实际量 ",
						type: "error",
						duration: 1000
						});
					return false
      	}else if(row[i].deliveringAmount>row[i].planShipmentsSum){
      		this.$message({
						message: "实际量不得大于计划量",
						type: "error",
						duration: 1000
						});
					return false
      	}
      }
	     }
      self.$api.xsgl.post({
      	vueInstance: self,
        url: "/scm/scmFhShipmentsDatailMaster/v1/sendApply",
        data:{
        	"businessDivision":self.searchForm.businessDivision,
        	"businessDivisionId":self.searchForm.businessDivisionId,
        	"commerceId":self.searchForm.commerceId,
        	"commerceName":self.searchForm.commerceName,
        	"ownerId":self.ownerId,
        	"province":self.searchForm.province,
        	"provinceId":self.searchForm.provinceId,
        	"scmFhShipmentsDatailList":row,
        }
       },function(res){
	  				console.log(res);
	  				if (res.state) {
	            // 发送申请成功
	            self.stepsBar.active = 3;
	            //self.applySteps.active = '2';
	            self.toogleStepPage("2");
	            self.applySteps.completeTitle = res.value.shipmentsCode;
	          }
	          self.loading = false;
	  		});
    },
    // 申请暂存
    applyCache() {
      let self = this;
      let row = self.childTable.pageResult.rows;
      if(row.length == 0){
      	this.$message({
						message: "请添加发货明细 ",
						type: "error",
						duration: 1000
						});
					return false
      }else{
      	for(let i=0; i<row.length; i++){
	      	if(row[i].deliveringAmount ==""){
	      		this.$message({
							message: "请填写发货实际量 ",
							type: "error",
							duration: 1000
							});
						return false
	      	}else if(row[i].deliveringAmount>row[i].planShipmentsSum){
      		this.$message({
						message: "实际量不得大于计划量",
						type: "error",
						duration: 1000
						});
					return false
      	}
	      }
      }
      
      self.$api.xsgl.post({
      	vueInstance: self,
        url: "/scm/scmFhShipmentsDatailMaster/v1/save",
        data:{
        	"businessDivision":self.searchForm.businessDivision,
        	"businessDivisionId":self.searchForm.businessDivisionId,
        	"commerceId":self.searchForm.commerceId,
        	"commerceName":self.searchForm.commerceName,
        	"ownerId":self.ownerId,
        	"province":self.searchForm.province,
        	"provinceId":self.searchForm.provinceId,
        	"scmFhShipmentsDatailList":row,
        	
        }
      },function(res){
      	console.log(res);
      	if (res.state) {
            // 暂存成功之后不跳页
            //self.pageResult.rows = res.value.scmXsCreditApplyList;
            //self.componentParams.id = res.value.id;
            self.$message({
              message: "暂存成功！",
              type: "success",
              duration: 2000
            });
          }
          self.loading = false;
      });
    },
    
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
  background: none !important;
}

.tanFoot {
  margin-top: 15px;
  display: flex;
}

.savebox {
  margin-left: 20px;
}
.el-steps {
  justify-content: center;
}

.el-table .cell {
  white-space: nowrap !important;
}

header {
  height: 40px !important;
}

// .el-form-item {
//   margin-top: 10px;
// }
.step-one-form.el-form {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  margin-top: 80px;
  margin-bottom: 300px;
  .el-form-item {
    width: 33.3333%;
    flex: 0 0 auto;
    .el-date-editor.el-input {
      width: 100%;
    }
  }
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

.head-item {
  border: 1px solid #f2f2f2;
}

.content-item {
  border: 1px solid #f2f2f2;
  margin-top: 8px;
}

.main-content {
  padding: 0 !important;
}

.success {
  width: 80%;
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

.el-dialog .el-dialog__body {
  border-top: 1px solid #f2f2f2 !important;
  border-bottom: 1px solid #f2f2f2 !important;
  padding: 0 !important;
}

.el-date-editor.el-input {
  width: auto;
}
.el-select .el-input__inner {
  font-size: 12px !important;
}
.el-select.dropdown-select .el-input__inner {
  font-size: 12px;
}
.el-input.is-disabled .el-input__inner{
	border-color: #FFFFFF;
}
</style>
