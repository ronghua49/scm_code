<template>
  <div>
    <transition name="el-fade-in">
    <div class="content-item" v-show="showApplyPage === '1'">
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
          <el-form-item label="认可状态">
            <el-select clearable v-model="searchForm.acceptState" placeholder="请选择">
                <el-option label="认可" value="0"></el-option>
                <el-option label="观察中" value="1"></el-option>
                <el-option label="终止" value="2"></el-option>
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
            <el-button @click="showApplyPageFunc" type="primary" icon="el-icon-circle-plus-outline" size="mini">新增</el-button>
          </el-row>
        </div>
      </div>
      <!--表格栏-->
        <el-table class="test" :data="pageResult.rows" highlight-current-row v-loading="loading" element-loading-text="加载中"
          border stripe fit
          max-height="500" size="mini" align="left" style="width:100%;">
          <el-table-column show-overflow-tooltip="true" prop="approvalCode" key="approvalCode" label="审批单号" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="approvalStateStr" key="approvalStateStr" label="申请状态" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="businessDivision" key="businessDivision" label="商务分区" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="province" key="province" label="省区" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="commerceCode" key="commerceCode" label="商业编码" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="commerceName" key="commerceName" label="商业名称" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="dutyOfficer" key="dutyOfficer" label="责任人" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="enterDate" key="enterDate" label="开户日期" header-align="center" align="center"></el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="acceptStateStr" key="acceptStateStr" label="认可状态" header-align="center" align="center"></el-table-column>
          <el-table-column align="center" label="生效年月">
                <el-table-column show-overflow-tooltip="true" :formatter="resetStartDate" prop="startDate" key="startDate" label="起始日期" header-align="center" align="center"></el-table-column>
                <el-table-column show-overflow-tooltip="true" :formatter="resetEndDate" prop="endDate" key="endDate" label="结束日期" header-align="center" align="center"></el-table-column>
          </el-table-column>
          <el-table-column show-overflow-tooltip="true" prop="memo" key="memo" label="备注" header-align="center" align="center"></el-table-column>
          <el-table-column label="操作" width="250" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button :disabled="scope.row.approvalState !== '0'" icon="el-icon-edit" size="mini" type="primary"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button icon="el-icon-edit" size="mini" type="primary"  @click="handleDetails(scope.$index, scope.row)">查看详情</el-button>
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
    
    <!-- 协议签订 -->
    <transition name="el-fade-in">
      <div class="content-item" v-show="showApplyPage === '2'">
        <business-accept-apply :key="forceRefresh" v-on:closeapplypage="closeapplypage" :passparam="editItemId"></business-accept-apply>
      </div>
    </transition>

  </div>
</template>
<script>
// 筛选功能 // 商务分区 省区 认可状态-- 0，1，2
import storage from "@/utils/storage";
import BusinessAcceptStatusApply from "./BusinessAcceptStatusApply"; // 引入协议签订组件
export default {
  name: "businessAcceptStatusManage",
  components: {
    "business-accept-apply": BusinessAcceptStatusApply
  },
  data() {
    return {
      forceRefresh: 0, // 强制重置子组件
      ownerId: "",
      pageResult: {},
      loading: false,
      // 是否显示签订协议组件 1 -- 显示manage页面，2 -- 显示申请页面
      showApplyPage: '1',
      editItemId: '', // 传给子组件的参数 -- 怎么区分是编辑还是查看详情呢 标识+id 逗号分隔
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
          AgreementStartDate: '', // 年度
          businessDivisionId: '', // 商务分区
          provinceId: '', // 省区
          commerceName: '' // 商业名称
      }
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
    toogleShowApplyPage (flag) {
        let self = this;
        self.showApplyPage = '-1';
        setTimeout(function() {
            self.showApplyPage = flag;
        },500);
    },
    // 查看详情
    handleDetails (index, item) {
        let self = this;
        // self.showApplyPage = true;
        self.toogleShowApplyPage('2');
        self.editItemId = 'details,' + item.agtherId;

    },
    // 点击编辑按钮
    handleEdit (index, item) {
        let self = this;
        // self.showApplyPage = true;
        self.toogleShowApplyPage('2');
        self.editItemId = 'edit,' + item.agtherId;
    },
    // 点击新增按钮
    showApplyPageFunc () {
        let self = this;
        self.editItemId = 'addNew,';
        // self.showApplyPage = true;
        self.toogleShowApplyPage('2');
        
    },
    // v-on 绑定不支持驼峰命名
    closeapplypage () {
        let self = this;
        // self.showApplyPage = false;
        self.toogleShowApplyPage('1');
        self.editItemId = '';
        self.forceRefresh = + new Date();
        self.searchquery();
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
          url: "/scm/scmZsjCommerceAcceptState/v1/firstList",
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