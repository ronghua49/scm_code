<template>
  <div>
    <transition>
      <div v-show="pageNum1">
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
                <span>商业名称</span>
              </div>
              <div class="headright">
                <el-row>
                  <el-button type="primary" size="mini" @click="showPage">导入</el-button>
                  <el-button type="primary" size="mini" @click="showPage2">调整</el-button>
                </el-row>
              </div>
            </div>

            <el-table v-loading="loading" :data="tableData" border stripe element-loading-text="加载中" style="width: 100%;" max-height="250">
              <el-table-column header-align="center" align="center" prop="verifyType" label="核销状态" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="getMoth" label="回款月度" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="acceptExpire" label="到款日期" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="matchCommerceName" label="回款商业名称" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="businessDivision" label="商务分区" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="province" label="所属省区" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="remittanceType" label="回款方式" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="acceptIsTrue" label="承兑汇票是否验真伪" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="acceptExpire" label="承兑汇票到期日" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="paymentType" label="是否预付款" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="price" label="回款金额" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="updatePrice" label="调整后回款金额" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="memo" label="备注" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="toLeadPersion" label="导入人" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="toLeadDate" label="导入时间" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="affirmPersion" label="调整人" width="120"></el-table-column>
              <el-table-column header-align="center" align="center" prop="affirmDate" label="调整时间" width="120"></el-table-column>
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
                :total="resultData.total"
              ></el-pagination>
            </div>
          </el-main>
        </el-container>
      </div>
    </transition>
    <transition>
      <div v-show="pageNum2">
        <div class="bigcon-apply">
          <div class="searchHeader">
            <div class="searchheadleft">
              <el-button @click="fanHui" size="mini" class="el-icon-back" type="primary">返回</el-button>
            </div>

            <div class="searchheadcenter">商业回款导入</div>
          </div>

          <!--详情 && 审批 页面 ， 表单 不可选-->
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
              </el-form>
              <div class="header">
                <div class="headleft">
                  <span class="el-icon-tickets"></span>
                  <span>商业名称</span>
                  <a href="javascript:(0);">下载模板</a>
                </div>
                <div class="headright">
                  <el-row>
                    <el-button type="primary" size="mini" @click="sure">确认数据</el-button>
                    <el-button type="danger" size="mini" @click="del">删除</el-button>
                    <el-upload
                    	class="upload"
										  action="https://jsonplaceholder.typicode.com/posts/"
										  multiple
										  :limit="3"
										  :http-request="myUpload"
										  :file-list="fileList"
										  :show-file-list="false">
										  <el-button type="primary" size="mini">导入</el-button>
										</el-upload>
                  </el-row>
                </div>
              </div>

              <el-table v-loading="loading" @selection-change="handleSelectionChange" :data="tableData" border stripe element-loading-text="加载中" style="width: 100%" max-height="250">
              	<el-table-column type="selection" width="55"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="getMoth" label="月度" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="acceptExpire" label="到款日期" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="commerceName" label="商业名称（导入）" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="matchCommerceName" label="商业名称（匹配）" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="businessDivision" label="商务分区" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="province" label="省区" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="remittanceType" label="回款方式" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="acceptIsTrue" label="承兑汇票是否验真伪" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="acceptExpire" label="承兑汇票到款日" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="price" label="回款金额" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="memo" label="备注" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="matchResult" label="匹配结果" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="toLeadPersion" label="导入人" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="toLeadDate" label="导入时间" width="120"></el-table-column>
              </el-table>
            </el-main>
          </el-container>
           <!--分页-->       
          <div class="pagination">
	          <el-pagination
	            background
	            @size-change="handleSizeChange"
	            @current-change="handleCurrentChange"
	            :current-page="pageRequest.page"
	            :page-sizes="[5,10, 20, 50]"
	            :page-size="pageRequest.pageSize"
	            layout="total, sizes, prev, pager, next"
	            :total="resultData.total"
	          ></el-pagination>
	        </div>
        </div>
      </div>
    </transition>
    <transition>
      <div v-show="pageNum3">
        <div class="bigcon-apply">
          <div class="searchHeader">
            <div class="searchheadleft">
              <el-button @click="fanHui" size="mini" class="el-icon-back" type="primary">返回</el-button>
            </div>

            <div class="searchheadcenter">商业回款调整</div>
          </div>

          <!--详情 && 审批 页面 ， 表单 不可选-->
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
              <el-form size="small" label-width="120px" class="demo-ruleForm" :model="searchForm" ref="searchForm">
                <el-form-item label="商务分区" prop="businessDivisionId">
                  <el-select clearable style="width: 100%" v-model="searchForm.businessDivisionId" placeholder="请选择">
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
              </el-form>
              <div class="header">
                <div class="headleft">
                  <span class="el-icon-tickets"></span>
                  <span>商业名称</span>
                </div>
                <!--<div class="headright">
                  <el-row>
                    <el-button type="success" size="mini" @click="save">保存</el-button>
                  </el-row>
                </div>-->
              </div>

              <el-table v-loading="loading" :data="tableData" border stripe element-loading-text="加载中" style="width: 100%" max-height="250">
	              <el-table-column header-align="center" align="center" prop="getMoth" label="月度" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="acceptExpire" label="到款日期" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="commerceName" label="商业名称" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="businessDivision" label="商务分区" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="province" label="省区" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="remittanceType" label="回款方式" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="acceptIsTrue" label="承兑汇票是否验真伪" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="acceptExpire" label="承兑汇票到款日" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="price" label="回款金额" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="updatePrice" label="调整后回款金额" width="120">
	              	<template slot-scope="scope">
	              		<input class="tzPrice" v-model="scope.row.updatePrice"></input>
	              		<i class="el-icon-edit-outline"></i>
	              	</template>
	              </el-table-column>
	              <el-table-column header-align="center" align="center" prop="memo" label="备注" width="120">
	              	<template slot-scope="scope">
	              		<input class="tzPrice" v-model="scope.row.memo"></input>
	              	</template>
	              </el-table-column>
	              <el-table-column header-align="center" align="center" prop="affirmPersion" label="调整人" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="affirmDate" label="调整时间" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="toLeadPersion" label="导入人" width="120"></el-table-column>
	              <el-table-column header-align="center" align="center" prop="toLeadDate" label="导入时间" width="120"></el-table-column>
	              <el-table-column label="操作" width="150" fixed="right" header-align="center" align="center">
			            <template slot-scope="scope">
			              <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">保存</el-button>
			            </template>
			          </el-table-column>
              </el-table>
            </el-main>
          </el-container>
					<!--分页-->
          <div class="pagination">
            <el-pagination
              background
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageRequest.page"
              :page-sizes="[5,10, 20, 50]"
              :page-size="pageRequest.pageSize"
              layout="total, sizes, prev, pager, next "
              :total="resultData.total"
            ></el-pagination>
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
import storage from "@/utils/storage";

export default {
  name: "EveryDayContract",

  data() {
    return {
    	fileList:[],
    	multipleSelection:[],
      pageNum1: true,
      pageNum2: false,
      pageNum3: false,
      jxs: "",
      value: "",
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      postData: {},
      ownerId: "",
      loading: false,
      searchForm: {
        verifyType:"",//核销状况
        remittanceDate: "", //日期
        businessDivisionId: "", // 商务分区
        provinceId:"",//省区
        commerceName:""//商业名称
      },
      searchProvinceList:[],//省区列表
      divisionList:[],//商务分区列表
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
      search: [],
      resultData: {},
			isAffirm:{
    		operation: "EQUAL",
	      property: "isAffirm",
	      relation: "AND"
    	},
      tableData: [],//所有数据
      memo:''//调整后备注
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
    // 页面之间切换的函数 -- 保证过渡动画流畅
  	fanHui() {
  		this.pageNum2 = false;
  		this.pageNum3 = false;
  		setTimeout(()=>{
  			this.pageNum1 = true;
  		},300)
      this.$refs.searchForm.resetFields()
      this.searchForm.verifyType = ""//核销状况
      this.pageRequest.page= 1
      this.pageRequest.pageSize=10
      this.pageRequest.showTotal=true
      this.getTableList()
    },
    handleSizeChange(val) {
      this.pageRequest.pageSize = val;
      if(this.pageNum2){
	    	this.isAffirm.value = "0"
	    	this.getTableList([this.isAffirm])
      }else if(this.pageNum3){
	    	this.isAffirm.value = "1"
	    	this.getTableList([this.isAffirm])
      }else{this.getTableList();}
    },
    handleCurrentChange(val) {
      this.pageRequest.page = val;
      if(this.pageNum2){
	    	this.isAffirm.value = "0"
	    	this.getTableList([this.isAffirm])
      }else if(this.pageNum3){
	    	this.isAffirm.value = "1"
	    	this.getTableList([this.isAffirm])
      }else{this.getTableList();}
    },
    //搜索按钮
    searchquery() {
      let _this = this;
      let search = [];
      //商务分区
      if (this.searchForm.businessDivisionId !== "") {
        search.push({
          operation: "EQUAL",
          property: "businessDivisionId",
          relation: "AND",
          value: _this.searchForm.businessDivisionId
        });
      }
      //省区
      if (this.searchForm.provinceId !== "") {
        search.push({
          operation: "EQUAL",
          property: "provinceId",
          relation: "AND",
          value: _this.searchForm.provinceId
        });
      }
      //核销状态
      if (this.searchForm.verifyType !== "") {
        search.push({
          operation: "EQUAL",
          property: "verifyType",
          relation: "AND",
          value: _this.searchForm.verifyType
        });
      }
      //日期
      if (this.searchForm.remittanceDate !== "" && this.searchForm.remittanceDate !== undefined) {
        search.push({
          operation: "BETWEEN",
          property: "remittanceDate",
          relation: "AND",
          value: _this.searchForm.remittanceDate
        });
      }
      //商务名称
        if (this.searchForm.commerceName !== "") {
        search.push({
          operation: "LIKE",
          property: "commerceName",
          relation: "AND",
          value: _this.searchForm.commerceName
        });
        }
      _this.search = search;
      this.getTableList();
   },
    showPage() {//导入
    	this.pageNum1 = false;
    	this.pageNum3 = false;
    	setTimeout(()=>{
    		this.pageNum2 = true;
    	},300)
    	this.pageRequest.page= 1;
      this.pageRequest.pageSize=10;
      this.pageRequest.showTotal=true;
    	this.isAffirm.value = "0";
    	this.getTableList([this.isAffirm]);
      this.searchForm.verifyType = "";//核销状况
			this.$refs.searchForm.resetFields();
    },
    showPage2(){//调整
    	this.pageNum1 = false
    	this.pageNum2 = false
    	setTimeout(()=>{
    		this.pageNum3 = true
    	},300)
    	this.pageRequest.page= 1
      this.pageRequest.pageSize=10
      this.pageRequest.showTotal=true
    	this.isAffirm.value = "1"
    	this.getTableList([this.isAffirm])
    	this.searchForm.verifyType = ""//核销状况
			this.$refs.searchForm.resetFields()
    },
    //关闭页面
    closeapplypage() {
      let self = this;
      this.getTableList();
    },
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
          _this.resultData.total = res.total
          let ary = [] //回款管理数据
          let drAry = [] //导入表格数据
          let tzAry = []//调整表格数据
          res.rows.map( v=> {
          	ary.push(v)
          })
          ary.map((v,i)=>{
          	//时间戳转换
          	v.remittanceDate = _this.timer(v.remittanceDate)
          	v.toLeadDate = _this.timer(v.toLeadDate)
          	v.acceptExpire = _this.timer(v.acceptExpire)
          	if(v.affirmDate){v.affirmDate = _this.timer(v.affirmDate)}
          	//核销状态转换
          	if(v.verifyType == 0){v.verifyType = '未核销'}
          	else if(v.verifyType == 1){v.verifyType = '核销中'}
          	else if(v.verifyType == 2){v.verifyType = '已核销'}
          	//是否预付款
          	if(v.paymentType == 0){v.paymentType = '预付款'}
          	else if(v.paymentType == 1){v.paymentType = '赊销'}
          })
          _this.tableData = ary
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
    //导入
	  myUpload(file){
	  	let _this = this
	  	let fd = new FormData()
	  	fd.append('file',file.file)
	  	this.$api.hkgl
        .post({
          vueInstance: _this,
          url: "/scm/scmCwReturnMoney/v1/leadExcel?id="+_this.ownerId,
          data:fd
        })
        .then(res => {
        	if(res.state == true){
        		this.$message({
		          message: res.message,
		          type: 'success'
		        });
        		this.isAffirm.value = "0";
    				this.getTableList([this.isAffirm]);
        	}
        });
	  },
	  handleSelectionChange(selection) {	
	  	this.multipleSelection = selection;
	  },
	  //导入模块删除
	  del(){
	  	let ary = []
	  	this.multipleSelection.map(v => {
	  		ary.push(v.id)
	  	})
	  	if(ary.length == 0){
	  		//错误提示
	  		this.$alert('请勾选需要删除的数据', '错误提醒', {
          confirmButtonText: '确定',
          callback: action => {}
        });
	  	}else{
	  		let _this = this
		  	this.$api.hkgl
	        .delDict({
	          url: "/scm/scmCwReturnMoney/v1/removes?ids="+ary,
	        })
	        .then(res => {
	        	if(res.state == true){ 
	        		this.$message({
			          message: res.message,
			          type: 'success'
			        });
	        		this.isAffirm.value = "0";
	    				this.getTableList([this.isAffirm]);
	        	}
	        });
	  	}
	  },
	  //导入模块确认
	  sure(){
	  	let ary = []
	  	this.multipleSelection.forEach(v => {
	  		ary.push(v.id)
	  	})
	  	if(ary.length == 0){
	  		//错误提示
	  		this.$alert('请勾选需要确认的数据', '错误提醒', {
          confirmButtonText: '确定',
          callback: action => {}
        });
	  	}else{
	  		let _this = this
		  	this.$api.hkgl
	        .post({
	          url: "/scm/scmCwReturnMoney/v1/affirmData?ids="+ary,
	        })
	        .then(res => {
	        	if(res.state == true){ 
	        		this.$message({
			          message: res.message,
			          type: 'success'
			        });
	        		this.isAffirm.value = "0";
	    				this.getTableList([this.isAffirm]);
	        	}
	        });
	  	} 	
	  },
	  //回款调整保存
	  handleEdit(index,row){
	  	let _this = this
	  	if(!row.updatePrice){
	  		//错误提示
	  		this.$alert('请输入调整后回款金额', '错误提醒', {
          confirmButtonText: '确定',
          callback: action => {}
        });
	  	}else{
	  		let url = "?id="+row.id+'&updatePrice='+row.updatePrice+'&memo='+row.memo;
	  		this.$api.hkgl
	        .post({
	          url: "/scm/scmCwReturnMoney/v1/updatePrice"+url,
	        })
	        .then(res => {
	        	if(res.state == true){ 
	        		this.$message({
			          message: res.message,
			          type: 'success'
			        });
	        		this.isAffirm.value = "1";
	    				this.getTableList([this.isAffirm]);
	        	}
	        });
	  	}
	  },
  }
};
</script>

<style scoped lang="scss">
.bigcon {
  text-align: left;
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

.headleft {
  margin-left: 10px;
}

.headright {
  margin-right: 10px;
}

.el-form {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  .el-form-item {
    width: 33%;
  }
}

.bigHeTong {
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
  text-align: left;
}

.header {
  width: 100%;
  height: 38px;
  font-size: 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f3f3f3;
  margin-bottom: 22px;
}

.headleft {
  margin-left: 10px;
}

.el-form {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  .el-form-item {
    width: 33%;
  }
}

.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
.upload{display: inline-block;}
.tzPrice{
	display: inline-block;
	width: 80px;
  border: none;
	height: 20px;
	background-color: transparent;
	text-align: center;
}
</style>
