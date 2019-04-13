<template>
  <div>
    <el-container>
      <el-header>
        <div class="header">
          <div class="headleft">
            <span class="el-icon-search"></span>
            <span class="sx">筛选查询</span>
            <span class="el-icon-caret-bottom"></span>
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
            <el-select v-model="searchForm.AgreementStartDate" placeholder="请选择年度">
              <el-option
                v-for="item in searchYearsList"
                :key="item.id"
                :label="item.id"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="商务分区">
            <el-select v-model="searchForm.businessDivisionId" placeholder="请选择商务分区" @change="getPrivence">
              <el-option
                v-for="item in divisionList"
                :key="item.id"
                :label="item.businessDivision"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="省区">
            <el-select v-model="searchForm.provinceId" placeholder="请选择省区">
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
          <span class="el-icon-caret-bottom"></span>
        </div>
        <div class="headright">
          <el-row>
            <router-link to="/fhgl/BusinessCreditApply/addS">
              <el-button type="primary" icon="el-icon-circle-plus-outline" size="mini">新增</el-button>
            </router-link>
          </el-row>
        </div>
      </div>
      <!--表格栏-->
      <el-table 
          :data="pageResult.rows" 
          highlight-current-row  
          v-loading="loading" 
          element-loading-text="加载中" border stripe fit
          :show-overflow-tooltip="false" 
          max-height="500" 
          size="mini" 
          align="left" 
          style="width:100%;">
          <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
          <el-table-column v-for="column in columns" header-align="center" align="center"
            :prop="column.prop" :label="column.label" 
            :key="column.prop" :min-width="column.minWidth" :formatter="column.formatter"
            :sortable="column.sortable==null?true:column.sortable">
          </el-table-column>
          <el-table-column label="操作" width="210" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button icon="el-icon-edit" :size="size" type="primary"  @click="handleEdit(scope.$index, scope.row)" >编辑</el-button>
              <el-button icon="el-icon-view" :size="size" type="primary"  @click="handleDetails(scope.$index, scope.row)" >查看详情</el-button>
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
</template>
<script>
import storage from "@/utils/storage";
export default {
  name: "businessCreditManage",
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
      ownerId: "",
      columns: [
        { prop: "year", label: "年度" ,minWidth: 150},
        { prop: "quarter", label: "月度",minWidth: 100,formatter:(row, column, cellValue, index)=>{return cellValue=='1'?'医疗机构':'药品经营企业'}},
        { prop: "businessDivision", label: "商务分区",minWidth: 100 },
        { prop: "province", label: "省区", minWidth: 110 },
        { prop: "createOrgName", label: "创建组织名称", minWidth: 100 },
        { prop: "commerceCode", label: "商业编码", minWidth: 100 },
        { prop: "commerceName", label: "商业名称", minWidth: 150 },
        { prop: "marketWay", label: "购销方式",minWidth: 100 },
        { prop: "price", label: "资信额(万)" },
        { prop: "id", label: "流程状态" }
      ],
      btnState: false,

      restaurants: [],
      labelPosition: "right",
      
      operation: true,
      options: [],
 
      // tableData3: [], -- 列表数据 改为pageResult
      pageResult: {},
      
      checked: false,
      loading: false,
      editid: "",



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
          AgreementStartDate: '', // 年度
          businessDivisionId: '', // 商务分区
          provinceId: '', // 省区
          ownerId: '', // 货主ID -- 必传
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
      this.tableData3.pageSize = pageSize;
      this.findPage();
    },
    // 获取年度
    getYears () {
        this.$api.fhgl
        .get({
            url: "/scm/scmXsCreditApply/v1/getYears/" + this.ownerId,
            data: {}
        }).then(res => {
            console.log(res);
        })
    },
    // 获取商务分区
    getDivision () {
        this.$api.fhgl
        .getZdBusinessDivision()
        .then(res => {
            console.log(res);
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.divisionList = res.rows;
        });
    },
    // 获取省区
    getPrivence () {
        console.log(this.searchForm);
        this.searchForm.provinceId= ''
        let divisionId = this.searchForm.businessDivisionId // 商务分区ID
        this.searchProvinceList = this.divisionList.reduce((final, item, index, arr)=>{
            if(item.id == divisionId){
                final = item.scmZdProvinceList;
            }
            return final
        },[])
    },
    // 筛选查询
    searchquery() {
      let query = [];
      let queryItem = {
          hasInitValue: false,
          operation: "EQUAL",
          relation: "AND",
          value: null,
          property: ""
      };
      let searchForm = Object.assign({},this.searchForm);
        for(var key in searchForm) {
          if(searchForm[key] !== '') {
            queryItem.property = key;
            if(key === "AgreementStartDate") {
              queryItem.value = "[" + searchForm[key] + "-01-01," + searchForm[key] + "-12-31" + "]";
            } else {
              queryItem.value = searchForm[key];
            }
            query.push(queryItem); 
          }
        }
        this.findPage(query);
    },
    //商品列表
    findPage(search = []) {
      this.loading = true;
      this.$api.fhgl
        .getFhgl({
          url: "/scm/scmXsCreditApply/v1/list",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys: [].concat(search),
            sorter: []
          }
        })
        .then(res => {
          console.log(res);
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.pageResult = res;
          this.loading = false
        });
    },
    // 编辑
    handleEdit () {
        
    },
    // 查看详情
    handleDetails () {

    }
  },
  mounted() {
    this.ownerId = storage.get("ownerId");
    this.searchForm.ownerId = this.ownerId;
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