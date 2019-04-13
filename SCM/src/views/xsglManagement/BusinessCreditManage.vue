<template>
  <div>
    <transition name="el-fade-in">
    <div class="content-item" v-show="showManagePage">
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
          :label-position="labelPosition"
          size="small"
          label-width="120px"
          class="demo-ruleForm"
          :model="searchForm"
          ref="searchForm"
        >
          <el-form-item label="年度">
            <el-select clearable v-model="searchForm.year" placeholder="请选择年度">
              <el-option
                v-for="item in searchYearsList"
                :key="item.year"
                :label="item.year"
                :value="item.year"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="商务分区">
            <el-select clearable v-model="searchForm.businessDivisionId" placeholder="请选择商务分区" @change="getPrivence">
              <el-option
                v-for="item in divisionList"
                :key="item.id"
                :label="item.businessDivision"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="省区">
            <el-select clearable v-model="searchForm.provinceId" placeholder="请选择省区">
              <el-option
                v-for="item in searchProvinceList"
                :key="item.id"
                :label="item.province"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="商业名称">
            <el-input v-model="searchForm.commerceName" clearable placeholder="请输入商业名称"></el-input>
          </el-form-item>
        </el-form>
      </el-main>

      <div class="header">
        <div class="headleft">
          <span class="el-icon-tickets"></span>
          <span class="sx">数据列表</span>
        </div>
        <div class="headright">
          <el-row>
            <el-button @click="showApplyPageFunc" type="primary" icon="el-icon-circle-plus-outline" size="mini">资信申请</el-button>

            <!-- <router-link to="/xsgl/BusinessCreditApply/addNew">
              <el-button type="primary" icon="el-icon-circle-plus-outline" size="mini">新增</el-button>
            </router-link> -->
          </el-row>
        </div>
      </div>
      <!--表格栏-->
      <el-table 
          :data="pageResult.rows" 
          highlight-current-row  
          v-loading="loading" 
          element-loading-text="加载中" border stripe fit
          
          tooltip-effect="dark"
          max-height="500" 
          size="mini" 
          align="left" 
          style="width:100%;">
          <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
          <el-table-column v-for="column in columns" header-align="center" align="center"
            :prop="column.prop" :label="column.label" 
            show-overflow-tooltip="ture" 
            :key="column.prop" :min-width="column.minWidth" :formatter="column.formatter"
            :sortable="column.sortable==null?true:column.sortable">
          </el-table-column>
          <el-table-column label="操作" width="230" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button :disabled="scope.row.approvalState !== '0'" icon="el-icon-edit" size="mini" type="primary"  @click="handleEdit(scope.$index, scope.row)" >编辑</el-button>
              <el-button icon="el-icon-view" size="mini" type="primary"  @click="handleDetails(scope.$index, scope.row)" >查看详情</el-button>
            </template>
          </el-table-column>
      </el-table>
      <!--分页-->
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
    
    <!-- 商业资信申请 -->
    <transition name="el-fade-in">
      <div class="content-item" v-show="showApplyPage">
        <business-credit-apply :key="forceRefresh" v-on:closeapplypage="closeapplypage" :passparam="editItemId"></business-credit-apply>
      </div>
    </transition>
    

  </div>
</template>
<script>
import storage from "@/utils/storage";
import BusinessCreditApply from "./BusinessCreditApply"; // 引入商业资信申请组件
export default {
  name: "businessCreditManage",
  components: {
    "business-credit-apply": BusinessCreditApply
  },
  beforeRouteEnter (to, from, next) {  
    next(vm => {
        // 通过 `vm` 访问组件实例
        if(from.name === "商业资信申请") {
            // 向主页广播事件
            vm.$root.eventHub.$emit('closeBusinessCreditApply', {});
        }
    });
  },
  data() {
    return {
      forceRefresh: 0, // 强制重置子组件
      ownerId: "",
      columns: [
        { prop: "approvalStateStr", label: "流程状态", minWidth: 120, },
        { prop: "year", label: "年度" ,minWidth: 100},
        { 
            prop: "quarter", label: "月度",minWidth: 100,
            // formatter:(row, column, cellValue, index)=>{return cellValue=='1'?'医疗机构':'药品经营企业'}
        },
        { prop: "businessDivision", label: "商务分区",minWidth: 100 },
        { prop: "province", label: "省区", minWidth: 110 },
        // { prop: "createOrgName", label: "创建组织名称", minWidth: 100 },
        { prop: "commerceCode", label: "商业编码", minWidth: 100 },
        { prop: "commerceName", label: "商业名称", minWidth: 150 },
        { prop: "marketWay", label: "购销方式",minWidth: 100, formatter:(row, column, cellValue, index)=>{return cellValue=='0'?'预付款':'赊销'} },
        { prop: "price", label: "资信额(万)", minWidth: 120, },
        
      ],

      labelPosition: "right",
      
      operation: true,

      // tableData3: [], -- 列表数据 改为pageResult
      pageResult: {},

      loading: false,

      // 是否显示资信申请页面
      showApplyPage: false,
      // 显示管理页面
      showManagePage: true,
      
      editItemId: '', // 传给子组件的参数 -- 怎么区分是编辑还是查看详情呢 标识+id 逗号分隔

      // search: [], // 筛选查询要传的参数
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      // 商务分区列表
      divisionList: [],
      // 省区列表
      searchProvinceList: [],
      // 年度列表
      searchYearsList: [],
      // 查询参数
      searchForm: {
          year: '', // 年度
          businessDivisionId: '', // 商务分区
          provinceId: '', // 省区
          //ownerId: '', // 货主ID -- 必传
          commerceName: '' // 商业名称
      }
    };
  },
  methods: {
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
    toogleApplyPage (flag) {
        let self = this;
        if(flag === 1) {
            self.showManagePage = !self.showManagePage;
            setTimeout(function() {
                self.showApplyPage = !self.showApplyPage;
            },300);
        } else {
            self.showApplyPage = !self.showApplyPage;
            setTimeout(function() {
                self.showManagePage = !self.showManagePage;
            },300);
        }
        
    },
    // 查看详情
    handleDetails (index, item) {
        let self = this;
        //self.forceRefresh = + new Date();
        //self.showApplyPage = true;
        self.toogleApplyPage(1);
        self.editItemId = 'details,' + item.masterId;

    },
    // 点击编辑按钮
    handleEdit (index, item) {
        let self = this;
        //self.forceRefresh = + new Date();
        //self.showApplyPage = true;
        self.toogleApplyPage(1);
        self.editItemId = 'edit,' + item.masterId;
    },
    // 点击新增显示 business-credit-apply 组件
    showApplyPageFunc () {
        let self = this;
        self.editItemId = 'addNew,';
        //self.showApplyPage = true;
        self.toogleApplyPage(1);
        
    },
    // v-on 绑定不支持驼峰命名
    closeapplypage () {
        let self = this;
        //self.showApplyPage = false;
        self.toogleApplyPage(2);
        self.editItemId = '';
        self.forceRefresh = + new Date();
        console.log(this.pageResult);
        self.searchquery();
    },
    // 获取年度
    getYears () {
        let self = this;
        self.$api.xsgl
        .get({
            vueInstance: self,
            url: "/scm/scmXsCreditApply/v1/getYears/" + self.ownerId,
            data: {}
        }, function(res) {
            console.log(res);
            self.searchYearsList = res.value;

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
      let queryItem = {
          hasInitValue: false,
          operation: "EQUAL",
          relation: "AND",
          value: [],
          property: ""
      };
      let searchForm = Object.assign({},this.searchForm);
        for(var key in searchForm) {
          if(searchForm[key] !== '') {
            queryItem.property = key;
            // if(key === "AgreementStartDate") {
            //   queryItem.operation = "BETWEEN";
            //   queryItem.value.push(searchForm[key] + "-01-01");
            //   queryItem.value.push(searchForm[key] + "-12-31");
            // } else {
              queryItem.value = searchForm[key];
            //}
            query.push(queryItem); 
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
          url: "/scm/scmXsCreditApply/v1/creditApplicationTypeList",
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
    }
    
  },
  mounted() {
    this.ownerId = storage.get("ownerId");
    // 获取年份
    this.getYears();
    // 获取商务分区
    this.getDivision();
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
    width: 33%;
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
</style>