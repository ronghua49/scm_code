<template>
  <div>
    <transition name="el-fade-in">
    <div class="content-item" v-show="showAddNewPage === '1'">
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
          <el-form-item label="商品系列">
            <el-select clearable v-model="searchForm.ProductLineId" 
              placeholder="请选择商品系列" @change="getProductName">
              <el-option
                v-for="item in goodsQuery.seriesList"
                :key="item.id"
                :label="item.lineName"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="商品名称">
            <el-select clearable v-model="searchForm.productName" placeholder="请选择商品名称">
              <el-option
                v-for="item in goodsQuery.nameList"
                :key="item.id"
                :label="item.productName"
                :value="item.productName"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="启用日期">
            <el-date-picker
                v-model="searchForm.startUsingDate"
                type="daterange"
                value-format="yyyy-MM-dd HH:mm:ss"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="商务分区">
            <el-select clearable v-model="searchForm.businessDivisionId" 
              placeholder="请选择商务分区" @change="getPrivence">
              <el-option
                v-for="item in areaQuery.divisionList"
                :key="item.id"
                :label="item.businessDivision"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="省区">
            <el-select clearable v-model="searchForm.provinceId" 
              placeholder="请选择省区" @change="getAngecyList">
              <el-option
                v-for="item in areaQuery.provinceList"
                :key="item.id"
                :label="item.province"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          
          <el-form-item label="经销商">
            <el-select clearable v-model="searchForm.commerceId" placeholder="请选择经销商">
                <el-option
                  v-for="item in angecyList"
                  :key="item.id"
                  :label="item.commerceName"
                  :value="item.id"
              ></el-option>
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
          <el-row>
            <el-button @click="showAddNewPageFunc" type="primary" icon="el-icon-circle-plus-outline" size="mini">新增</el-button>
          </el-row>
        </div>
      </div>
      <!--表格栏-->
        <el-table class="test" :data="pageResult.rows" 
          highlight-current-row v-loading="loading" element-loading-text="加载中"
          border stripe fit
          max-height="500" size="mini" align="left" style="width:100%;">
          <el-table-column label="序号" type="index" width="50"></el-table-column>
          <el-table-column show-overflow-tooltip prop="id" key="id" label="记录编号" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip prop="businessDivision" key="businessDivision" label="商业分区" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip prop="province" key="province" label="省区" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip prop="commerceName" key="commerceName" label="经销商名称" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip prop="productLineName" key="productLineName" label="商品系列" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip prop="productName" key="productName" label="商品名称" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip prop="isStartUsing" key="isStartUsing" label="是否启用" :formatter="setStartUsing" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip prop="startUsingDate" key="startUsingDate" :formatter="resetDate" label="启用日期" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip prop="createPersion" key="createPersion" label="录入人" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip prop="createDate" key="createDate" :formatter="resetDate" label="录入日期" header-align="center" align="center"></el-table-column>
          <el-table-column label="操作" width="250" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button v-if="scope.row.isStartUsing === '1'" icon="el-icon-circle-check" size="mini" type="primary"  @click="closeOrOpen(scope.$index, scope.row)">停用</el-button>
              <el-button v-if="scope.row.isStartUsing === '0'" icon="el-icon-circle-close" size="mini" type="danger"  @click="closeOrOpen(scope.$index, scope.row)">启用</el-button>
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
    <!-- 新增页面 -->
    <transition name="el-fade-in">
      <div class="content-item" v-show="showAddNewPage === '2'">
        <centralized-deliver-add :key="forceRefresh" v-on:closeaddnewpage="closeaddnewpage" :passparam="editItemId"></centralized-deliver-add>
      </div>
    </transition>

  </div>
</template>
<script>
// 筛选功能
import storage from "@/utils/storage";
import CentralizedDeliverAdd from "./CentralizedDeliverAdd";
export default {
  name: "CentralizedDeliverSetting",
  components: {
      "centralized-deliver-add": CentralizedDeliverAdd
  },
  data() {
    return {
      forceRefresh: 0, // 强制重置子组件
      ownerId: "",
      pageResult: {},
      loading: false,
      // 是否显示新增组件 1 -- 显示manage页面，2 -- 显示申请页面
      showAddNewPage: '1',
      editItemId: '', // 传给子组件的参数 -- 怎么区分是编辑还是查看详情呢 标识+id 逗号分隔
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      // 商务分区/省区筛选条件
      areaQuery: {
          divisionList: [], // 商务分区
          provinceList: []  // 省区列表
      },
      // 商品筛选条件
      goodsQuery: {
          seriesList: [], // 商品系列
          nameList: [] // 商品名称列表
      },
      // 经销商列表
      angecyList: [],
      // 查询参数
      searchForm: {
          startUsingDate: '',
          commerceId: '',
          businessDivisionId: '', // 商务分区
          provinceId: '', // 省区
          productName: '',
          ProductLineId: '',

      }
    };
  },
  methods: {
    setStartUsing (item, c, val) {
        return val === '1' ? '是' : '否';

    },

    resetDate (row,clum,val) {
        if(val) {
            return val.split(' ')[0];
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
        this.angecyList.splice(0);
        this.searchForm.commerceId = '';
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
    getProductSerise () {
        let self = this;
        self.$api.xsgl
        .post({
          vueInstance: self,
          url: "/scm/scmZsjProduct/v1/LineAndProductDownBox",
          //data: {}
        }, function(res) {
            console.log(res);
            self.goodsQuery.seriesList = res.value;
            
        });
    },
    getProductName (val) {
        let self = this;
        console.log(val);
        console.log(self.searchForm);
        self.goodsQuery.seriesList.forEach(function(item,index) {
            if(item.id === val) {
                self.goodsQuery.nameList = item.productList;
            }
        });
    },
    // 获取商业下拉框数据
    getAngecyList () {
        let self = this;
        self.angecyList.splice(0);
        self.searchForm.commerceId = '';
        self.$api.xsgl
        .post({
          vueInstance: self,
          url: "/scm/scmZsjCommerce/v1/firstList",
          data: {
              pageBean:{},
              params: {},
              sorter: [],
              querys:[
                {
                    property: "businessDivisionId", 
                    value: self.searchForm.businessDivisionId, 
                    operation: "EQUAL", 
                    relation: "AND"
                },
                {
                    property: "provinceId", 
                    value: self.searchForm.provinceId, 
                    operation: "EQUAL", 
                    relation: "AND"
                },
                {
                    property: "ownerId",
                    value: self.ownerId, 
                    operation: "EQUAL", 
                    relation: "AND"
                },
                {
                    property: "approvalState", 
                    value: 2, 
                    operation: "EQUAL", 
                    relation: "AND"
                }
              ]
          }
        }, function(res) {
            console.log(res);
            self.angecyList = res.rows;
            
        });

    },
    // 停用启用按钮 /scm/scmFhFocusShipmentsSet/v1/startAndStop/{id}
    closeOrOpen (index, item) {
        let self = this;
        self.$api.xsgl
        .get({
          vueInstance: self,
          url: "/scm/scmFhFocusShipmentsSet/v1/startAndStop/" + item.id,
        }, function(res) {
            item.isStartUsing = res.value;
            self.pageResult.rows.splice(index,1,item);
            
        });


    },
    // 筛选查询
    searchquery() {
        let query = [];
        let searchForm = Object.assign({},this.searchForm);
        for(var key in searchForm) {
          if(searchForm[key] !== '') {
            if(key === "startUsingDate") {
              query.push({
                hasInitValue: false,
                operation: "BETWEEN",
                relation: "AND",
                value: searchForm[key],
                property: key
              }); 
            } else if(key === "productName") {
              query.push({
                hasInitValue: false,
                operation: "LIKE",
                relation: "AND",
                value: searchForm[key],
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
          url: "/scm/scmFhFocusShipmentsSet/v1/list",
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
    // 获取商务分区
    this.getDivision();
    this.findPage();
    this.getProductSerise();
    
  }
};
/****
 * 
 * /scm/scmZsjCommerce/v1/firstList
query条件传
{property: "businessDivisionId", value: "商务大区id", operation: "=", relation: "AND"},
{property: "provinceId", value: "省区id", operation: "=", relation: "AND"},
{property: "ownerId", value: "货主ID", operation: "=", relation: "AND"}
{property: "approvalState", value: "审批状态(传2)", operation: "=", relation: "AND"}
  启用日期调整为两个输入框
列表
请求方式:post
调用URL:/scm/scmFhFocusShipmentsSet/v1/list
query:[
{property: "businessDivisionId", value: "商务大区id", operation: "=", relation: "AND"},
{property: "provinceId", value: "省区id", operation: "=", relation: "AND"},
{property: "commerceId",, operation: "=", relation: "AND", value: 商业ID},
{property: "ProductLineId", value: "商品系列ID", operation: "=", relation: "AND"},
{property: "productName", value: "商品名称", operation: "like", relation: "AND"}
property: "startUsingDate",, operation: "BETWEEN", relation: "AND",
value: ["yyyy-mm-dd hh:mi:ss","yyyy-mm-dd hh:mi:ss"]}(日期)
{property: "ownerId", value: "货主ID", operation: "=", relation: "AND"}(必填)
]
 **/
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
    .el-date-editor.el-date-editor--daterange.el-input__inner{
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
</style>