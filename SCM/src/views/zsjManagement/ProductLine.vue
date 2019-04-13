<template>
  <!-- <div>{{msg}}</div> -->
  <div>
    <!-- 添加筛选查询 productLineState productLineName -->
    <el-row class="searchHeader">
      <el-col :span="2">
        <div>筛选查询</div>
      </el-col>
      <el-col :span="22" style="text-align: right">
        <el-button icon="el-icon-search" :size="size" type="primary" @click="inquire">查询</el-button>
      </el-col>
    </el-row>
    <el-row class="searchBox">
      <el-form :model="searchForm" label-width="140px" ref="searchForm" size="small">
        <el-form-item label="状态">
          <el-select v-model="searchForm.productLineState" placeholder="请选择状态">
            <el-option value="0" label="全部"></el-option>
            <el-option value="1" label="启用"></el-option>
            <el-option value="2" label="停用"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品系列名称">
          <el-input v-model="searchForm.productLineName" auto-complete="off" clearable placeholder="请输入商品系列名称"></el-input>
        </el-form-item>
      </el-form>
    </el-row>


    <el-row class="btnAdd">
      <el-col :xs="2" :sm="2" :md="2" :lg="2">
        <el-button
          icon="el-icon-circle-plus-outline"
          type="primary"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
    </el-row>

    <!--表格栏-->
    <el-table
      :data="pageResult.rows"
      highlight-current-row
      v-loading="loading"
      element-loading-text="加载中"
      border
      stripe
      :show-overflow-tooltip="true"
      max-height="500"
      size="mini"
      align="left"
      style="width:100%;"
    >
      <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
      <el-table-column
        v-for="column in columns"
        header-align="center"
        align="center"
        :prop="column.prop"
        :label="column.label"
        :key="column.prop"
        :min-width="column.minWidth"
        :sortable="column.sortable==null?true:column.sortable"
      ></el-table-column>
      <el-table-column
        prop="productLineState"
        label="状态"
        align="center"
        :formatter="(row, column, cellValue, index)=>{return cellValue=='1'?'启用':'停用'}"
      ></el-table-column>
      <el-table-column label="操作" width="200" fixed="right" header-align="center" align="center">
        <template slot-scope="scope">
          <el-button
            icon="el-icon-edit"
            label
            :size="size"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)"
          >编辑</el-button>
          <el-button
            :icon="scope.row.productLineState == '1'?'el-icon-circle-close-outline':'el-icon-circle-check-outline'"
            label
            :size="size"
            :type="scope.row.productLineState == '1'?'warning':'primary'"
            @click="handleChange(scope.$index, scope.row)"
          >{{scope.row.productLineState == '1'?'停用':'启用'}}</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页栏-->
    <div class="toolbar" style="padding:10px;">
      <el-pagination
        @current-change="refreshPageRequest"
        @size-change="refreshPageSizeRequest"
        background
        :current-page="pageRequest.page"
        :page-sizes="[10, 20, 30, 40, 50]"
        :page-size="pageRequest.pageSize"
        layout="sizes, total, prev, pager, next"
        :total="pageResult.total"
        style="float:right;"
      ></el-pagination>
    </div>

    <!--新增编辑界面-->
    <el-dialog
      :title="operation?'新增':'编辑'"
      width="40%"
      :visible.sync="dialogVisible"
      :close-on-click-modal="false"
    >
      <el-form
        :model="dataForm"
        label-width="120px"
        :rules="dataFormRules"
        ref="dataForm"
        size="small"
      >
        <el-form-item label="ID" prop="id" v-if="false">
          <el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="产品系列名称" prop="productLineName">
          <el-input v-model="dataForm.productLineName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="产品系列缩写" prop="productLineAcronym">
          <el-input v-model="dataForm.productLineAcronym" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="编码" prop="productLineCode">
          <el-input v-model="dataForm.productLineCode" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="产品通用名称" prop="productLineCommon">
          <el-input v-model="dataForm.productLineCommon" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="产品系列英文名" prop="productLineEname">
          <el-input v-model="dataForm.productLineEname" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="剂型" prop="agentTypeId">
          <el-select v-model="dataForm.agentTypeId" placeholder="请选择剂型">
            <el-option
              v-for="agentTypeOption in agentTypeList"
              :label="agentTypeOption.agentType"
              :value="agentTypeOption.id"
              :key="agentTypeOption.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="药品分类" prop="medicineTypeId">
          <el-select v-model="dataForm.medicineTypeId" placeholder="请选择药品分类">
            <el-option
              v-for="medicineClassifyOption in medicineClassifyList"
              :label="medicineClassifyOption.medicineClassify"
              :value="medicineClassifyOption.id"
              :key="medicineClassifyOption.id"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :size="size" @click.native="dialogVisible = false">取消</el-button>
        <el-button :size="size" type="primary" @click.native="submitForm" :loading="editLoading">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "productline",
  components: {},
  data() {
    return {
      size: "small",
      columns: [
        { prop: "productLineCode", label: "编码" },
        { prop: "productLineName", label: "产品系列名称", minWidth: 100 },
        { prop: "productLineAcronym", label: "产品系列缩写", minWidth: 100 },
        { prop: "productLineCommon", label: "系列通用名称", minWidth: 100 },
        { prop: "productLineEname", label: "产品系列英文名", minWidth: 110 },
        { prop: "agentType", label: "剂型" },
        { prop: "medicineType", label: "药品分类" },
        { prop: "createPersion", label: "创建人" },
        { prop: "CreateDate", label: "创建时间", minWidth: 120 }
        // { prop: "productLineState", label: "状态" }
      ],
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      pageResult: {},
      operation: false, // true:新增, false:编辑
      dialogVisible: false, // 新增编辑界面是否显示
      editLoading: false,
      dataFormRules: {
        productLineName: [
          { required: true, message: "请输入系列名称", trigger: "blur" },
          { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        productLineAcronym: [
          { required: true, message: "请输入产品系列缩写", trigger: "blur" },
          { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        productLineCode: [
          { required: true, message: "请输入编码", trigger: "blur" },
          { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        productLineCommon: [
          {
            required: true,
            message: "请输入产品系列通用名称",
            trigger: "blur"
          },
          { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        agentType: [
          { required: true, message: "请选择剂型", trigger: "change" }
        ],
        medicineType: [
          { required: true, message: "请选择药品分类", trigger: "change" }
        ]
      },
      // 新增编辑界面数据
      dataForm: {
        productLineName: "",
        productLineAcronym: "",
        productLineCommon: "",
        productLineEname: "",
        agentType: "",
        medicineType: "",
        agentTypeId: "",
        medicineTypeId: "",
        productLineCode: ""
      },
      menuData: [],
      menuSelections: [],
      menuLoading: false,
      authLoading: false,
      checkAll: false,
      loading: false,
      size: "mini",
      agentTypeList: [], //剂型下拉框
      medicineClassifyList: [], //药品类型下拉框
      // 新增筛选查询
      searchForm: {
        productLineState: '',
        productLineName: ''
      }
    };
  },
  mounted() {
    this.findPage();
    this.getAgentType();
    this.getMedicineClassify();
    this.searchForm.productLineState = "0"; // 筛选查询默认选中全部
  },
  methods: {
    handleAdd: function() {
      this.dialogVisible = true;
      this.operation = true;
      if (this.$refs["dataForm"] !== undefined) {
        this.$refs["dataForm"].resetFields();
      }
      this.dataForm.productLineState = 1;
    },
    handleChange(index, params) {
      this.$api.zsj
        .changeState({
          url: "/scm/scmZsjProductLine/v1/changeState/" + params.id
        })
        .then(res => {
          if (res.state) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 800
            });
          } else {
            this.$message({
              message: "操作失败, " + res.shortMessage,
              type: "error",
              duration: 800
            });
          }
          this.findPage();
        });
    },
    // 显示编辑界面
    handleEdit: function(index, params) {
      this.dialogVisible = true;
      this.operation = false;
      this.dataForm = Object.assign(
        {},
        {
          productLineName: params.productLineName,
          productLineAcronym: params.productLineAcronym,
          id: params.id,
          productLineCommon: params.productLineCommon,
          productLineEname: params.productLineEname,
          agentType: params.agentType,
          medicineType: params.medicineType,
          agentTypeId: params.agentTypeId,
          medicineTypeId: params.medicineTypeId,
          productLineCode: params.productLineCode,
          productLineState: params.productLineState
        }
      );
    },

    // 编辑
    submitForm: function() {
      this.$refs.dataForm.validate(valid => {
        this.dataForm.medicineType = this.medicineClassifyList.reduce(
          (final, item, index, arr) => {
            if (item.id == this.dataForm.medicineTypeId) {
              final = item.medicineClassify;
            }
            return final;
          },
          ""
        );
        this.dataForm.agentType = this.agentTypeList.reduce(
          (final, item, index, arr) => {
            if (item.id == this.dataForm.agentTypeId) {
              final = item.agentType;
            }
            return final;
          },
          ""
        );
        if (valid) {
          this.editLoading = true;
          let params = Object.assign({}, this.dataForm);
          this.$api.zsj
            .addZsj({
              url: "/scm/scmZsjProductLine/v1/save",
              data: params
            })
            .then(res => {
              this.editLoading = false;
              if (res.state) {
                this.$message({
                  message: "操作成功",
                  type: "success",
                  duration: 800
                });
                this.dialogVisible = false;
                this.$refs["dataForm"].resetFields();
              } else {
                this.$message({
                  message: "操作失败, " + res.shortMessage,
                  type: "error",
                  duration: 800
                });
              }
              this.findPage();
            });
        }
      });
    },
    findPage() {
      this.loading = true;
      this.$api.zsj
        .getZsj({
          url: "/scm/scmZsjProductLine/v1/list",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys: [],
            sorter: []
          }
        })
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.pageResult = res;
          this.loading = false;
        });
    },
    getAgentType() {
      this.$api.dict
        .getDict({
          url: "/scm/scmZdAgenttype/v1/list",
          data: {
            // pageBean: {},
            params: {},
            querys: [],
            sorter: [
              {
                direction: "DESC",
                property: "createDate"
              }
            ]
          }
        })
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.agentTypeList = res.rows;
        });
    },
    getMedicineClassify() {
      this.$api.dict
        .getDict({
          url: "/scm/scmZdMedicineClassify/v1/list",
          data: {
            // pageBean: {
            //   page: 1,
            //   pageSize: 9999,
            //   showTotal: true
            // },
            params: {},
            querys: [],
            sorter: [
              {
                direction: "DESC",
                property: "createDate"
              }
            ]
          }
        })
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }

          this.medicineClassifyList = res.rows;
        });
    },
    refreshPageRequest: function(page = 1) {
      this.pageRequest.page = page;
      this.findPage();
    },
    // size刷新
    refreshPageSizeRequest: function(pageSize) {
      this.pageRequest.pageSize = pageSize;
      this.findPage();
    },
    inquire() {
      console.log(this.searchForm);
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.btnAdd {
  padding-bottom: 5px;
}
.paginer {
  margin-top: 5px;
}
.el-select {
  width: 100%;
}

.searchHeader {
  padding: 5px;
  background: #f3f3f3;
  div:first-child {
    height: 28px;
    line-height: 28px;
  }
  b {
    font-size: 24px;
  }
  i {
    margin-right: 5px;
    margin-left: 5px;
  }
}
.searchBox {
  padding-top: 10px;
  border: 1px solid #f4f4f4;
  margin-bottom: 10px;
  .el-form {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    .el-form-item {
      width: 32%;
    }
  }
}
</style>
