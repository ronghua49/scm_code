<template>
  <div>
    <header class="header">
      <h1>集中发货设置</h1>
      <div class="header-btn-group">
        <el-button @click="saveFunc" size="mini" type="primary">保存</el-button>
        <!-- <el-button @click="sendApply" size="mini" type="primary">发起申请</el-button> -->
      </div>
      <div class="header-back">
        <el-button @click="showParentComp" size="mini" type="primary">返回</el-button>
      </div>
    </header>
    <transition name="el-fade-in">
      <div>
        <div style="margin-top:20px;" class="query-section">
          <div class="header">
            <div class="header-left">
              <i class="el-icon-tickets"></i>基础信息
            </div>
            <div class="header-right"></div>
          </div>
          <el-main>
            <el-form
              :model="searchForm"
              ref="searchForm"
              label-position="right"
              size="small"
              label-width="120px"
              class="demo-ruleForm"
            >
              <el-form-item label="商务分区">
                <el-select
                  clearable
                  v-model="searchForm.businessDivisionId"
                  placeholder="请选择商务分区"
                  @change="getPrivence"
                >
                  <el-option
                    v-for="item in areaQuery.divisionList"
                    :key="item.id"
                    :label="item.businessDivision"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="省区">
                <el-select
                  clearable
                  v-model="searchForm.provinceId"
                  placeholder="请选择省区"
                  @change="getAngecyList"
                >
                  <el-option
                    v-for="item in areaQuery.provinceList"
                    :key="item.id"
                    :label="item.province"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="经销商">
                <el-select clearable v-model="searchForm.commerceId" 
                  @change="setAngecyName"
                  placeholder="请选择经销商">
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
        </div>
        <!--表格数据-->
        <div class="table-section">
          <div class="header">
            <div class="header-left">
              <i class="el-icon-tickets"></i>数据列表
            </div>

            <div class="header-right">
              <el-button @click="addNewProduct" size="mini" type="primary">
                <i class="el-icon-circle-plus-outline">新增商品</i>
              </el-button>
              <el-button @click="deleteSelectedRow" size="mini" type="danger">
                <i class="el-icon-delete">删除</i>
              </el-button>
            </div>
          </div>
          <!--表格栏-->
          <el-table
            class="test"
            :data="pageResult.currentPageList"
            highlight-current-row
            v-loading="loading"
            element-loading-text="加载中"
            border
            stripe
            fit
            @selection-change="getOuterSelectedList"
            max-height="500"
            size="mini"
            align="left"
            style="width:100%;"
          >
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column
              show-overflow-tooltip
              v-for="item in columns"
              :key="item.prop"
              :prop="item.prop"
              :label="item.label"
              :width="item.minwidth"
              :formatter="item.formatter"
              :align="item.align"
            ></el-table-column>
            
          </el-table>
        </div>
      </div>
    </transition>

    <!-- 新增商业 dialog -->
    <el-dialog
      custom-class="business-accept-status-apply-addnew-dialog"
      class="addnew-dialog"
      title="新增商品"
      width="70%"
      :visible.sync="addNewDialogVisible"
    >
      <header class="header">
        <div class="header-left">
          <i class="el-icon-tickets"></i>数据列表
        </div>
        <div class="header-right"></div>
        <h1>&nbsp;</h1>
      </header>
      <el-table
        @selection-change="getDialogSelectedData"
        v-loading="addnewDialog.loading"
        element-loading-text="加载中"
        :data="addnewDialog.pageResult.rows"
        size="mini"
        border
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column
          show-overflow-tooltip="ture"
          v-for="item in addnewDialog.column"
          :key="item.prop"
          :prop="item.prop"
          :label="item.label"
          :width="item.minwidth"
          :formatter="item.formatter"
          :align="item.align"
        ></el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          @current-change="addnewDialogRefreshPageRequest"
          @size-change="addnewDialogRefreshPageSizeRequest"
          background
          :current-page="addnewDialog.pageRequest.page"
          :page-sizes="[10, 20, 30, 40, 50]"
          :page-size="addnewDialog.pageRequest.pageSize"
          layout="sizes, total, prev, pager, next"
          :total="addnewDialog.pageResult.total"
        ></el-pagination>
      </div>
      <el-row>
        <el-button @click="dialogCancel">取消</el-button>
        <el-button @click="dialogConfirm" type="primary">确定</el-button>
      </el-row>
      <el-dialog width="30%" title="提示" :visible.sync="addnewInnerVisible" append-to-body>
        <p>您选择了重复的商业，已为您自动过滤！</p>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="addnewInnerVisible = false">确定</el-button>
        </div>
      </el-dialog>
    </el-dialog>
  </div>
</template>

<script>

import storage from "@/utils/storage";
export default {
  name: "CentralizedDeliverAdd",
  props: ["passparam"],
  data() {
    return {
      addNewDialogVisible: false,
      addnewInnerVisible: false,
      ownerId: "",
      selectedList: [],
      pageResult: {
        rows: [],
        currentPageList: []
      },

      loading: false,
      pageRequest: {
        page: 1,
        pageSize: 10000,
        showTotal: true
      },
      // 商务分区/省区筛选条件
      areaQuery: {
        divisionList: [], // 商务分区
        provinceList: [] // 省区列表
      },
      angecyList: [],
      // 查询参数
      searchForm: {
        businessDivisionId: "", // 商务分区
        provinceId: "", // 省区
        commerceId: "" // 经销商
      },
      columns: [
          { 
              prop: "isStartUsing", 
              label: "是否启用", 
              align: "center", 
              formatter: (r,c,v,i) => {
                  if(v === '1') {
                      return '是';
                  } else {
                      return '否';
                  }
              }
          },
          { prop: "businessDivision", label: "商务分区", align: "center" },
          { prop: "province", label: "省区", align: "center" },
          { prop: "commerceName", label: "经销商", align: "center" },
          { prop: "productLineName", label: "商品系列", align: "center" },
          { prop: "productName", label: "商品名字", align: "center" },
          { prop: "agentType", label: "商品类型", align: "center" },
          { prop: "keepingCondition", label: "保存方式", align: "center" },
          { prop: "supplierName", label: "供应商", align: "center" },
          { prop: "manufacturer", label: "制造商", align: "center" },
          { prop: "otc", label: "OTC", align: "center" }
      ],

      addnewDialog: {
        loading: false,
        pageRequest: {
          page: 1,
          pageSize: 10,
          showTotal: true
        },
        pageResult: {},
        column: [
          { prop: "productLineName", label: "商品系列", align: "center" },
          { prop: "productName", label: "商品名字", align: "center" },
          { prop: "agentType", label: "商品类型", align: "center" },
          { prop: "keepingCondition", label: "保存方式", align: "center" },
          { prop: "supplierName", label: "供应商", align: "center" },
          { prop: "manufacturer", label: "制造商", align: "center" },
          { prop: "otc", label: "OTC", align: "center" }
        ],
        selectedListData: []
      }
    };
  },
  mounted() {
    this.ownerId = storage.get("ownerId");
    this.getDivision();
  },
  watch: {
    passparam: "getId"
  },
  methods: {
    getId() {
      let self = this;
      let middleArr = self.passparam.split(",");
    },
    formatStartDate(row, column) {
      return row.startDate.split(" ")[0];
    },
    formatEndDate(row, column) {
      return row.endDate.split(" ")[0];
    },
    addnewDialogRefreshPageRequest: function(page = 1) {
      this.addnewDialog.pageRequest.page = page;
      this.getDialogListData();
    },
    addnewDialogRefreshPageSizeRequest: function(pageSize) {
      this.addnewDialog.pageRequest.pageSize = pageSize;
      this.getDialogListData();
    },
    refreshPageRequest: function(page = 1) {
      this.pageRequest.page = page;
      this.findPage();
    },
    refreshPageSizeRequest: function(pageSize) {
      this.pageRequest.pageSize = pageSize;
      this.findPage();
    },
    // 获取申请主页面的选中的列表数据
    getOuterSelectedList(list) {
      let self = this;
      self.selectedList = [];
      list.forEach(element => {
        self.selectedList.push(element);
      });
      console.log(self.selectedList);
    },
    // 删除选中行
    deleteSelectedRow() {
      let self = this;
      // 删除当前页列表数据
      for (let i = self.pageResult.currentPageList.length - 1; i >= 0; i--) {
        for (let j = 0; j < self.selectedList.length; j++) {
          if (
            self.pageResult.currentPageList[i].productCode === self.selectedList[j].productCode
          ) {
            self.pageResult.currentPageList.splice(i, 1);
            break;
          }
        }
      }
      // 删除总列表数据
      for (let i = self.pageResult.rows.length - 1; i >= 0; i--) {
        for (let j = 0; j < self.selectedList.length; j++) {
          if (self.pageResult.rows[i].productCode === self.selectedList[j].productCode) {
            self.pageResult.rows.splice(i, 1);
            break;
          }
        }
      }
      self.findPage();
    },
    // 删除单行
    deleteRow(index, item) {
      let self = this;
      // 先删除当前页的
      self.pageResult.currentPageList.splice(index, 1);
      // 再删除总列表的
      self.pageResult.rows.splice(
        self.pageRequest.pageSize * self.pageRequest.page -
          self.pageRequest.pageSize +
          index,
        1
      );

      self.findPage();
    },
    // 选择分区/省区/经销商，自动填
    setStartDateAndEndDate() {
      let self = this;
      for (let i = 0; i < self.pageResult.currentPageList.length; i++) {
          for(let key in self.searchForm) {
              self.$set( self.pageResult.currentPageList[i],key,self.searchForm[key]);
          }
      }
      // 更新原数组
      self.pageResult.rows.splice(
        self.pageRequest.pageSize * self.pageRequest.page -
          self.pageRequest.pageSize,
        self.pageResult.currentPageList.length,
        ...self.pageResult.currentPageList
      );
    },
    // 切换申请步骤页面
    toogleApplySteps(flag) {
      let self = this;
      self.applySteps.step = "-1";
      setTimeout(() => {
        self.applySteps.step = flag;
      }, 500);
    },
    // 向父组件传值
    showParentComp() {
      this.$emit("closeaddnewpage", {});
    },
    addNewProduct() {
      let self = this;
      self.addNewDialogVisible = true;
      self.$nextTick(() => {
          self.addnewDialog.loading = true;
          self.getDialogListData();
      });
      
    },
    dialogCancel() {
      let self = this;
      self.addnewDialog.pageRequest.page = 1;
      self.addnewDialog.pageRequest.pageSize = 10;
      self.addnewDialog.pageRequest.showTotal = true;
      self.addnewDialog.selectedListData = [];
      self.addNewDialogVisible = false;
      setTimeout(function() {
          self.addnewDialog.pageResult = {};
      },300);
    },
    dialogConfirm() {
      let repetitionMark = false;
      let self = this;
      if (self.addnewDialog.selectedListData.length > 0) {
        for (
          let i = self.addnewDialog.selectedListData.length - 1;
          i >= 0;
          i--
        ) {
          for (let j = 0; j < self.pageResult.rows.length; j++) {
            // 防止重复添加
            //console.log('selectedListData[' + i+'].id = ' + self.addnewDialog.selectedListData[i].id + '-----------------' + 'self.pageResult.rows[' + j +'].id' + self.pageResult.rows[j].id);
            if (
              self.addnewDialog.selectedListData[i].productCode ===
              self.pageResult.rows[j].productCode
            ) {
              self.addnewDialog.selectedListData.splice(i, 1);
              repetitionMark = true;
              break;
            }
          }
        }

        for (let i = 0; i < self.addnewDialog.selectedListData.length; i++) {
          self.addnewDialog.selectedListData[i].productId = self.addnewDialog.selectedListData[i].id;
          delete self.addnewDialog.selectedListData[i].id;
          delete self.addnewDialog.selectedListData[i].scmZsjAchageList;
          self.addnewDialog.selectedListData[i].isStartUsing = '1';
          if (self.addnewDialog.selectedListData[i].acceptState === "-1") {
            self.addnewDialog.selectedListData[i].acceptState = "0";
          }
          self.pageResult.rows.push(self.addnewDialog.selectedListData[i]);
        }
        // 添加完数据以后重新查询当前页数据
        self.findPage();
      }
      if (repetitionMark) {
        self.addnewInnerVisible = true;
      }
      //self.pageResult.rows = self.addnewDialog.selectedListData;
      setTimeout(function() {
        self.dialogCancel();
      }, 300);
    },
    getDialogSelectedData(val) {
      let self = this;
      self.addnewDialog.selectedListData = [];
      for (let i = 0; i < val.length; i++) {
        self.addnewDialog.selectedListData.push(val[i]);
      }
    },
    // 请求dialog框中的列表数据 // dialog -- 列表查询接口 /scm/scmZsjProduct/v1/list
    getDialogListData() {
      let self = this;
      let search = [];
      self.addnewDialog.loading = true;
      self.$api.xsgl.post(
        {
          vueInstance: self,
          url: "/scm/scmZsjProduct/v1/list",
          data: {
            pageBean: self.addnewDialog.pageRequest,
            params: {},
            querys: [].concat(search),
            sorter: []
          }
        },
        function(res) {
          console.log(res);
          self.addnewDialog.pageResult = res;
          self.addnewDialog.loading = false;
        }
      );
    },
    // 发起申请
    saveFunc () {
      let self = this;
      let requestData = [];
      // 验证生效年月
      for (let i = 0; i < self.pageResult.currentPageList.length; i++) {
        if (!self.pageResult.currentPageList[i].commerceId) {
          self.$message({
            message: "请选择商务分区/省区/经销商！",
            type: "error",
            duration: 2000
          });
          return;
        }
      }
      requestData = self.pageResult.currentPageList;
      if (requestData.length === 0) {
        self.$message({
          message: "请添加商品！",
          type: "error",
          duration: 2000
        });
        return;
      } else {
        requestData.forEach(function(item, index) {
          item.ownerId = self.ownerId;
          if(item.id) {
             delete item.id;
          }
        });
      }

      // 发送请求
      self.$api.xsgl.post(
        {
          vueInstance: self,
          url: "/scm/scmFhFocusShipmentsSet/v1/saves",
          data: requestData
        },
        function(res) {
          console.log(res);
          self.$message({
              message: '保存成功！',
              type: 'success',
              duration: 2000
          });
          self.showParentComp();
        }
      );
    },

    // 获取商务分区
    getDivision() {
      let self = this;
      self.$api.xsgl.getZdBusinessDivision({ vueInstance: self }, function(
        res
      ) {
        self.areaQuery.divisionList = res;
      });
    },
    // 获取省区
    getPrivence() {
      let self = this;
      this.getAngecyList();
      self.searchForm.provinceId = "";
      self.searchForm.province = "";
      self.angecyList.splice(0);
      self.searchForm.commerceId = '';
      self.searchForm.commerceName = "";
      let divisionId = self.searchForm.businessDivisionId; // 商务分区ID
      self.areaQuery.provinceList = self.areaQuery.divisionList.reduce(
        (final, item, index, arr) => {
          if (item.id == divisionId) {
            final = item.provinceList;
            self.searchForm.businessDivision = item.businessDivision;
          }
          return final;
        },
        []
      );
      console.log(this.searchForm);
    },
    // 获取商业下拉框数据
    getAngecyList(val) {
      let self = this;
      self.angecyList.splice(0);
      self.searchForm.commerceId = '';
      self.searchForm.commerceName = '';
      self.areaQuery.provinceList.forEach(function(item,index) {
          if(item.id == val) {
              self.searchForm.province = item.province;
          }
      });

      self.$api.xsgl.post(
        {
          vueInstance: self,
          url: "/scm/scmZsjCommerce/v1/firstList",
          data: {
            pageBean: {},
            params: {},
            sorter: [],
            querys: [
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
        },
        function(res) {
          self.angecyList = res.rows;
        }
      );
      console.log(self.searchForm);
    },
    // 设置经销商名字
    setAngecyName (val) {
        let self = this;
        self.angecyList.forEach(function(item, index) {
            if(val === item.id) {
                self.searchForm.commerceName = item.commerceName;
            }
        });
        self.findPage();

    },

    //列表 -- 这个列表是前端维护，分页也在前端
    findPage() {
      let self = this;
      let listTotalLength = self.pageResult.rows.length;
      // 总页数
      let totalPages = Math.ceil(listTotalLength / self.pageRequest.pageSize);
      let startIndex =
        self.pageRequest.page * self.pageRequest.pageSize -
        self.pageRequest.pageSize;
      let endIndex = self.pageRequest.page * self.pageRequest.pageSize - 1;
      self.pageResult.currentPageList = self.pageResult.rows.slice(
        startIndex,
        endIndex + 1
      );
      self.pageResult.total = listTotalLength;
      self.setStartDateAndEndDate();
      console.log(self.pageResult);
    }
  }
};

</script>
<style scoped lang="scss">
.header {
  position: relative;
  font-size: 28px;
  padding: 5px;
  background: #f3f3f3;
  display: flex;
  justify-content: center;
  line-height: 1em;
}
.header h1 {
  font-size: 28px;
  text-align: center;
}
.header-btn-group {
  position: absolute;
  height: 100%;
  right: 5px;
  top: 0;
  display: flex;
  align-items: center;
}
.header-back {
  position: absolute;
  height: 100%;
  left: 5px;
  top: 0;
  display: flex;
  align-items: center;
}
.query-section {
  margin-bottom: 20px;
}
.header-left,
.header-right {
  width: 50%;
  display: flex;
  align-items: center;
  font-size: 16px;
}
.header-right {
  justify-content: flex-end;
}
.el-form {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  .el-form-item {
    text-align: left;
    width: 33.3333%;
    margin: 0;
  }
}
.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.addnew-dialog {
  .el-form {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items: center;
    margin: 10px 0;
    .el-form-item {
      width: 33.3333%;
      margin: 0;
    }
  }
}
.apply-success-section {
  margin: 150px auto auto auto;
  width: 60%;
  border: 1px solid #dddddd;
  padding: 5px;
  border-radius: 3px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.success,
.success-bottom {
  width: 100%;
}
</style>

