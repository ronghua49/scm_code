<template>
  <div>
    <div v-show="startShow">
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
      <el-form
        :label-position="labelPosition"
        :model="searchForm"
        label-width="120px"
        size="small"
        class="demo-ruleForm"
      >
        <el-form-item label="采购订单号" prop="procurementCode">
          <el-input placeholder="请输入采购订单号" v-model="searchForm.procurementCode" clearable></el-input>
        </el-form-item>
        <el-form-item label="审批状态" prop="approvalState">
          <el-select v-model="searchForm.approvalState" clearable placeholder="请选择审批状态">
            <el-option value="0" label="未提交"></el-option>
            <el-option value="1" label="待审批"></el-option>
            <el-option value="2" label="已经通过"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="合同编号" prop="contractCode">
          <el-select v-model="searchForm.contractCode" clearable placeholder="请选择合同编号">
            <el-option
              v-for="item in contractList"
              :key="item.id"
              :label="item.contractCode"
              :value="item.contractCode"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="供应商名称" prop="supplier" clearable>
          <el-select v-model="searchForm.supplier" clearable placeholder="请选择供应商名称">
            <el-option
              v-for="item in gongoption"
              :key="item.id"
              :label="item.supplierName"
              :value="item.supplierName"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div class="header">
        <div class="headleft">
          <span class="el-icon-tickets"></span>
          <span class="sx">数据列表</span>
        </div>
        <div class="headright">
          <el-button type="primary" class="el-icon-circle-plus" size="mini" @click="buy">采购订单申请</el-button>
        </div>
      </div>
      <!--table-->
      <el-table
        v-loading="loading2"
        element-loading-text="加载中"
        :data="tableData3"
        size="mini"
        border
        style="width: 100%"
      >
        <el-table-column
          v-for="item in column"
          :key="item.prop"
          :prop="item.prop"
          :label="item.label"
          :formatter="item.formatter"
          :width="item.minwidth"
          :align="item.align"
        ></el-table-column>
        <el-table-column fixed="right" label="操作" align="center" width="270">
          <template slot-scope="scope">
            <router-link
              :to="{path:'/cgdd/cgBuyOrderDetail',query:{id:scope.row.id,isEdit: false,contractId:scope.row.contractId}}"
            >
              <el-button class="el-icon-view" type="primary" size="mini">查看详情</el-button>
            </router-link>
            <el-button
              type="primary"
              :disabled="scope.row.approvalState==0?false:true"
              class="el-icon-edit"
              size="mini"
              @click="edit(scope.row)"
            >编辑</el-button>
            <el-button
              type="primary"
              :disabled="scope.row.approvalState==2?false:true"
              class="el-icon-circle-plus-outline"
              size="mini"
              @click="reOrder(scope.row)"
              style="margin-left:0px;"
            >补单</el-button>
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
          :total="total"
        ></el-pagination>
      </div>
    </div>
    <!--第一步-->
    <div class="head-item" v-show="one">
      <el-row class="searchHeader">
        <el-col :span="1" style="text-align: right;">
          <el-button size="mini" class="el-icon-back" type="primary" @click="goback">返回</el-button>
        </el-col>
        <el-col :span="6" :offset="8">
          <b>采购订单申请</b>
        </el-col>
        <el-col :span="9" style="text-align: right;">
          <el-button size="mini" type="primary" v-show="oneShow" @click="nextStep()">下一步</el-button>
          <el-button size="mini" type="primary" v-show="twoShow" @click="prevStep">上一步</el-button>
          <!-- <el-button  size="mini" type="primary" v-show="threeShow" @click="applySave">申请暂存</el-button> -->
          <el-button size="mini" type="primary" v-show="fourShow" @click="sendApply">发起申请</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-steps
          
          :align-center="true"
          :active="active"
          finish-status="success"
          style="margin: 35px auto;"
        >
          <el-step title="选择供应商"></el-step>
          <el-step title="添加供货商品信息"></el-step>
          <el-step title="完成"></el-step>
        </el-steps>
      </el-row>
    </div>
    <transition name="el-fade-in">
      <div v-show="two">
        <div class="content-item">
          <div class="header">
            <div class="headleft">
              <span class="el-icon-search"></span>
              <span class="sx">选择供应商</span>
            </div>
          </div>
          <el-row>
            <el-form
              :model="wrapForm"
              :rules="wraprules"
              ref="wrapForm1"
              :label-position="labelPosition"
              label-width="120px"
              size="small"
              class="demo-ruleForm"
              style="display: flex;justify-content: center;margin-top: 80px;margin-bottom: 300px;"
            >
              <el-form-item label="供应商名称" prop="supplier">
                <el-select
                  v-model="wrapForm.supplier"
                  clearable
                  placeholder="请选择供应商名称"
                  @change="changeSupplier"
                >
                  <el-option
                    v-for="item in gongoption"
                    :key="item.id"
                    :label="item.supplierName"
                    :value="item.supplierName"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="合同编号" prop="contractCode">
                <el-select v-model="wrapForm.contractCode" clearable placeholder="请选择合同编号">
                  <el-option
                    v-for="item in contractList"
                    :key="item.id"
                    :label="item.contractCode"
                    :value="item.contractCode"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="币种" prop="currency">
                <el-input v-model="wrapForm.currency" :disabled="true" >
                </el-input>
              </el-form-item>
              <el-form-item label="交货日期" prop="deliveryDate">
                <el-date-picker
                  v-model="wrapForm.deliveryDate"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  style="width:100%"
                  placeholder="请选择交货日期"
                  clearable
                ></el-date-picker>
              </el-form-item>
              <el-form-item label="收货仓库" prop="warehouse">
                <el-select v-model="wrapForm.warehouse" clearable placeholder="请选择收货仓库">
                  <el-option
                    v-for="item in wareHouseList"
                    :key="item.id"
                    :label="item.getMoneyWarehouse"
                    :value="item.getMoneyWarehouse"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="收票方" prop="getTicket">
                <el-select v-model="wrapForm.getTicket" clearable placeholder="请选择收票方">
                  <el-option
                    v-for="item in ticklist"
                    :key="item.id"
                    :label="item.getTicket"
                    :value="item.getTicket"
                  ></el-option>
                </el-select>
              </el-form-item>
              
            </el-form>
          </el-row>
        </div>
      </div>
    </transition>
    <!--第二步-->
    <transition name="el-fade-in">
      <div class="content-item" v-show="three">
        <div class="header">
          <div class="headleft">
            <span class="el-icon-tickets"></span>
            <span class="sx">添加采购</span>
            <span style="color:#409eff">{{detailInfo.supplier}}</span>商品信息,合同号为
            <span style="color:#409eff">【{{detailInfo.contractCode}}】</span>。已选择
            <span style="color:#409eff">【{{detailInfo.speciesSum}}】</span>种产品,采购总金额为
            <span style="color:#409eff">【{{detailInfo.sumPrice}}】</span>
          </div>
          <div class="headright">
            <el-button
              type="primary"
              class="el-icon-circle-plus"
              size="mini"
              @click="addProduct"
            >新增产品</el-button>
          </div>
        </div>
        <el-table
          v-loading="loading"
          element-loading-text="加载中"
          :data="secondTableData"
          size="mini"
          border
          style="width: 100%"
        >
          <el-table-column type="index" label="序号" align="center"></el-table-column>
          <el-table-column prop="achange" label="件装量" align="center"></el-table-column>
          <el-table-column prop="count" label="盒数" align="center" minWidth="150">
            <template slot-scope="scope">
              <el-input
                v-model="secondTableData[scope.$index].productSum"
                @input="oninput(scope.$index, scope.row)"
                placeholder="请输入盒数"
              ></el-input>
            </template>
          </el-table-column>
          <el-table-column
            v-for="item in columnSecond"
            :key="item.prop"
            :prop="item.prop"
            :label="item.label"
            :formatter="item.formatter"
            :minWidth="item.minWidth"
            :align="item.align"
          ></el-table-column>
          <el-table-column fixed="right" label="操作" align="center" minWidth="100">
            <template slot-scope="scope">
              <el-button
                type="danger"
                class="el-icon-delete"
                size="mini"
                @click="del(scope.$index,scope.row)"
              >删除</el-button>
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
            :total="total"
          ></el-pagination>
        </div>
      </div>
    </transition>
    <!--第三步-->
    <transition name="el-fade-in">
      <div class="content-item" v-show="four">
        <div class="success">
          <span class="el-icon-check"></span>
          您已完成
          <span style="color: #409eff;">【{{detailInfo.supplier}}】</span>供货合同
          <span style="color: #409eff;">【{{detailInfo.contractCode}}】</span>签订
        </div>
        <div class="success-bottom">
          合同已申请
          <span style="color: #409eff;">【{{detailInfo.speciesSum}}】</span>种供货产品,总金额为
          <span style="color:#409eff;">[{{detailInfo.sumPrice}}]</span>
          {{detailInfo.currency}}
        </div>
      </div>
    </transition>
    <el-dialog title="新增产品" width="50%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="120px" :rules="addRules" ref="addForm" size="small">
        <el-form-item style="width: 98%;" label="产品名称" prop="productName">
          <el-autocomplete
            style="width: 100%"
            class="inline-input"
            v-model="addForm.productName"
            :fetch-suggestions="querySearch"
            placeholder="请输入商品名称"
            @select="handleSelect"
            clearable
          ></el-autocomplete>
        </el-form-item>
        <el-form-item style="width: 49%;" label="产品编码" prop="productCode">
          <el-input :disabled="true" v-model="addForm.productCode" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item style="width: 49%;" label="产品通用名称" prop="productCname">
          <el-input :disabled="true" v-model="addForm.productCname" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item style="width: 49%;" label="品规" prop="productGauge">
          <el-input :disabled="true" v-model="addForm.productGauge" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item style="width: 49%;" label="药品类型" prop="medicineType">
          <el-input :disabled="true" v-model="addForm.medicineType" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item style="width: 49%;" label="单位" prop="unit">
          <el-input :disabled="true" v-model="addForm.unit" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item style="width: 49%;" label="单价" prop="unitPrice">
          <el-input :disabled="true" v-model="addForm.unitPrice" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item style="width: 49%;" label="合同剩余数" prop="surplusAmount">
          <el-input :disabled="true" v-model="addForm.surplusAmount" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item style="width: 49%;" label="采购数量" prop="productSum">
          <el-input :disabled="true" v-model="addForm.productSum" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item style="width: 49%;" label="件装量" prop="achange">
          <el-input :disabled="true" v-model="addForm.achange" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item style="width: 49%;" label="盒数" prop="count">
          <el-input :disabled="countEdit" v-model="addForm.count" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item style="width: 49%;" label="总金额" prop="totalPrice">
          <el-input :disabled="true" v-model="totalPrice" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item style="width: 49%;" label="币种" prop="currency">
          <el-input :disabled="true" v-model="addForm.currency" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :size="size" @click.native="dialogVisible = false">取消</el-button>
        <el-button :size="size" type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import storage from "@/utils/storage";
let timer;
export default {
  name: "goods",
  data() {
    return {
      size: "mini",
      dialogVisible: false,
      column: [
        {
          prop: "procurementCode",
          label: "采购单号",
          minWidth: "200",
          align: "center"
        },
        {
          prop: "supplier",
          label: "供应商名称",
          minWidth: "150",
          align: "center"
        },
        {
          prop: "contractCode",
          label: "合同编号",
          minWidth: "150",
          align: "center"
        },
        {
          prop: "speciesSum",
          label: "供货品种数",
          minWidth: "200",
          align: "center"
        },
        { prop: "productSum", label: "数量", minWidth: "100", align: "center" },
        { prop: "sumPrice", label: "金额", minWidth: "100", align: "center" },
        { prop: "currency", label: "币种", minWidth: "100", align: "center" },
        {
          prop: "deliveryDate",
          label: "到货日期",
          minWidth: "200",
          align: "center"
        },
        {
          prop: "approvalState",
          label: "审批状态",
          minWidth: "150",
          align: "center",
          formatter: function(row, column, cellValue, index) {
            if (row.approvalState == 0) {
              return "未提交";
            }
            if (row.approvalState == 1) {
              return "审批中";
            }
            if (row.approvalState == 2) {
              return "已通过";
            }
          }
        }
      ],
      columnSecond: [
        {
          prop: "surplusAmount",
          label: "合同剩余数",
          minWidth: "100",
          align: "center"
        },
        {
          prop: "productName",
          label: "产品名称",
          minWidth: "100",
          align: "center"
        },
        {
          prop: "productCode",
          label: "产品编码",
          minWidth: "100",
          align: "center"
        },
        {
          prop: "productCname",
          label: "产品通用名",
          minWidth: "100",
          align: "center"
        },
        {
          prop: "productGauge",
          label: "品规",
          minWidth: "100",
          align: "center"
        },
        {
          prop: "medicineType",
          label: "药品类型",
          minWidth: "100",
          align: "center"
        },
        { prop: "unit", label: "单位", minWidth: "100", align: "center" },
        { prop: "unitPrice", label: "单价", minWidth: "100", align: "center" },
        { prop: "sumPrice", label: "金额", minWidth: "100", align: "center" },
        { prop: "currency", label: "币种", minWidth: "100", align: "center" }
      ],
      searchForm: {
        procurementCode: "",
        approvalState: "",
        contractCode: "",
        supplier: ""
      },
      btnState: false,
      startShow: true,
      one: false,
      two: false,
      three: false,
      four: false,
      active: 0,
      oneShow: true,
      twoShow: false,
      threeShow: false,
      fourShow: false,
      restaurants: [],
      applyDate: "",
      labelPosition: "right",
      loading2: false,
      ownerId: "",
      total: 0,
      operation: true,
      dialogTableVisible: false,
      gongid: "",
      applyid: "",
      gongoption: [], //供应商下拉列表
      ticklist: [], //收票方下拉列表
      wareHouseList: [], //收货仓库下拉列表
      contractList: [], //合同编号
      tableData3: [],
      secondTableData: [],
      fisrtStepId: "", //第一步保存生成的id
      resultList: [], // 步骤二新增产品下拉列表
      countEdit: true, //盒数是否能够输入
      detailInfo: {
        speciesSum: "",
        supplier: "",
        contractCode: "",
        sumPrice: ""
      }, //第二部 头部信息
      bzoption: [{ name: "CNY", inx: 1 }],
      checked: false,
      loading: false,
      search: [],
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      addForm: {
        productName: "",
        productCode: "",
        productCname: "",
        productGauge: "",
        medicineType: "",
        unit: "",
        unitPrice: "",
        sumPrice: "",
        productSum: "",
        achange: "",
        surplusAmount: "",
        count: ""
      },
      wrapForm: {
        currency: "",
        warehouse: "",
        getTicket: "",
        contractCode: "", //合同编号
        contractId: "", //合同id
        supplier: "",
        deliveryDate: "",
        supplierId: ""
      },
      addRules: {
        productName: [{ required: true, message: "请选择产品名称" }],
        count: [
          { required: true, message: "请输入盒数" },
          {
            tirgger: "blur",
            validator: (rule, value, callback) => {
              if (value.length <= 0) {
                callback(new Error("请输入盒数"));
              } else if (!Number(value) && value.length > 0) {
                callback(new Error("盒数必须为数字"));
              } else if (value <= 0) {
                callback(new Error("盒数必须大于0"));
              } else if (value >= 99999999) {
                callback(new Error("输入数量超过限制"));
              } else {
                callback();
              }
            }
          }
        ]
      },
      wraprules: {
        supplier: [
          { required: true, message: "请选择供应商", trigger: "change" }
        ],
        // currency: [
        //   { required: true, message: "请选择币种", trigger: "change" }
        // ],
        getTicket: [
          { required: true, message: "请选择收票方", trigger: "change" }
        ],
        contractCode: [
          { required: true, message: "请选择合同编号", trigger: "change" }
        ],
        deliveryDate: [
          { required: true, message: "请选择交货日期", trigger: "change" }
        ],
        warehouse: [
          { required: true, message: "请选择收货仓库", trigger: "change" }
        ]
      }
    };
  },
  watch: {
    "wrapForm.supplier": "supplierChange",
    "wrapForm.contractCode": "contractChange",
    "addForm.productName": "productNameChange"
  },
  computed: {
    totalPrice() {
      return Number(this.addForm.count) * Number(this.addForm.unitPrice);
    }
  },
  methods: {
    oninput(index, row) {
      //表格中直接修改盒数调用后台方法
      clearTimeout(timer);
      timer = setTimeout(() => {
        let newid = this.fisrtStepId;
        if (
          row.productSum.length <= 0 ||
          !Number(row.productSum) ||
          Number(row.productSum) < 0 ||
          row.productSum.startsWith("0") ||
          row.productSum.includes(".")
        ) {
          this.secondTableData[index].productSum = "0";
        }
        this.secondTableData[index].sumPrice =
          Number(this.secondTableData[index].productSum) *
          Number(this.secondTableData[index].unitPrice);
        let data = {
          id: row.id,
          productSum: row.productSum,
          contractProductId: row.contractProductId,
          proOrderId: this.fisrtStepId
        };
        this.$api.approval
          .postscm({
            url: "/scm/scmCgProOrderProduct/v1/save",
            data: data
          })
          .then(res => {
            if (res.state == false) {
              this.$message({
                message: "操作失败, " + res.message,
                type: "error",
                duration: 800
              });
              return;
            }
            this.$message({
              message: res.message,
              type: "success",
              duration: 800
            });

            this.getDetail(newid);
          });
      }, 1000);
    },
    del(index, row) {
      //第二步 删除商品
      this.$confirm("确认删除选中记录吗？", "提示", {
        type: "warning"
      })
        .then(() => {
          this.$api.approval
            .delscm({
              url: "/scm/scmCgProOrderProduct/v1/remove/" + row.id
            })
            .then(res => {
              if (res.state == false) {
                this.$message({
                  message: "操作失败, " + res.message,
                  type: "error",
                  duration: 800
                });
                return;
              }
              this.$message({
                message: res.message,
                type: "success",
                duration: 800
              });
              this.getSecondTable();
            });
        })
        .catch(() => {});
    },
    submitForm() {
      //第二部新增商品
      let newid = this.fisrtStepId;
      this.$refs["addForm"].validate(valid => {
        if (valid) {
          let data = {
            achange: this.addForm.achange,
            currency: this.addForm.currency,
            contractProductId: this.addForm.id,
            medicineType: this.addForm.medicineType,
            medicineTypeId: this.addForm.medicineTypeId,
            proOrderId: this.fisrtStepId,
            procurementCode: this.addForm.procurementCode,
            productCname: this.addForm.productCname,
            productCode: this.addForm.productCode,
            productGauge: this.addForm.productGauge,
            productId: this.addForm.productId,
            productName: this.addForm.productName,
            productSum: this.addForm.count,
            sumPrice: this.totalPrice,
            unit: this.addForm.unit,
            unitPrice: this.addForm.unitPrice
          };
          this.$api.approval
            .postscm({
              url: "/scm/scmCgProOrderProduct/v1/save",
              data: data
            })
            .then(res => {
              if (res.state == false) {
                this.$message({
                  message: "操作失败, " + res.message,
                  type: "error",
                  duration: 800
                });
                return;
              }
              this.$message({
                message: res.message,
                type: "success",
                duration: 800
              });
              this.dialogVisible = false;
              this.getSecondTable();
              this.getDetail(newid);
            });
        }
      });
    },
    productNameChange(val) {
      if (val == "") {
        this.$refs.addForm.resetFields();
        this.countEdit = true;
      } else {
        this.countEdit = false;
      }
    },
    refreshPageRequest: function(page = 1) {
      this.pageRequest.page = page;
      this.findPage();
    },
    refreshPageSizeRequest: function(pageSize) {
      this.pageRequest.pageSize = pageSize;
      this.findPage();
    },
    supplierChange() {
      //获取供应商id
      if (this.wrapForm.supplier == "") {
        this.wrapForm.supplierId = "";
        return;
      }
      let final = this.gongoption.reduce((final, item, index, arr) => {
        if (this.wrapForm.supplier === item.supplierName) {
          final = {
            sid: item.id
          }
        }
        return final;
      }, {});
      this.wrapForm.supplierId = final.sid;
    },
    contractChange() {
      if (this.wrapForm.contractCode == "") {
        this.wrapForm.contractId = "";
        this.wrapForm.currency = "";
        return;
      }
      let final = this.contractList.reduce((final, item, index, arr) => {
        if (this.wrapForm.contractCode === item.contractCode) {
          final = {
            cid: item.id,
            currency: item.currency
          };
        }
        return final;
      }, {});
      this.wrapForm.contractId = final.cid;
      this.wrapForm.currency = final.currency;
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    edit(rows) {
      if (this.btnState == false) {
        this.$router.push({
          path: "/cgdd/cgBuyOrderDetail",
          query: {
            id: rows.id,
            isEdit: true,
            contractId: rows.contractId
          }
        });
      }
    },
    reOrder(rows) {
      if (this.btnState == false) {
        this.$router.push({
          path: "/cgdd/cgBuyOrderRe",
          query: {
            id: rows.id,
            isEdit: true,
            contractId: rows.contractId
          }
        });
      }
    },
    searchquery() {
      if (this.applyDate) {
        this.search.push({
          property: "productNum",
          value: this.applyDate,
          operation: "BETWEEN",
          relation: "AND"
        });
      }
      if (this.gongid) {
        this.search.push({
          property: "supplierId",
          value: this.gongid,
          operation: "EQUAL",
          relation: "AND"
        });
      }
      console.log(this.search);
      this.findPage(this.search);
    },
    //列表
    findPage(search) {
      this.loading = true;
      this.$api.zsj
        .getZsj({
          url: "/scm/scmCgProcurementOrder/v1/list",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys: search,
            sorter: []
          }
        })
        .then(res => {
          if (res) {
            this.tableData3 = res.rows;
            this.total = res.total;
            this.loading = false;
            this.search = [];
          } else {
            this.$message({
              message: "操作失败, " + res.shortMessage,
              type: "error",
              duration: 800
            });
          }
        });
    },
    buy() {
      this.startShow = false;
      this.one = true;
      this.two = true;
    },
    nextStep() {
      let first = this.fisrtStepId;
      this.$refs.wrapForm1.validate(valid => {
        if (valid) {
          var data = Object.assign({}, this.wrapForm);
          if (first !== "") {
            data.id = first;
          }
          data.ownerId = this.ownerId;
          this.$api.zsj
            .getZsj({
              url: "/scm/scmCgProcurementOrder/v1/save",
              data: data
            })
            .then(res => {
              if (res.state == false) {
                this.$message({
                  message: "操作失败, " + res.shortMessage,
                  type: "error",
                  duration: 800
                });
              } else {
                this.fisrtStepId = res.value;
                this.getSecondTable();
                this.getDownBox();
                this.getDetail(res.value);
                this.active = 1;
                this.one = true;
                this.two = false;
                setTimeout(() => {
                  this.three = true;
                  this.oneShow = false;
                  this.twoShow = true;
                  this.threeShow = true;
                  this.fourShow = true;
                }, 220);
              }
            });
        }
        //  this.fisrtStepId = 7971007;
        //         this.getSecondTable();
        //         this.getDownBox();
        //         this.active = 1;
        //         this.one = true;
        //         this.two = false;
        //         setTimeout(() => {
        //           this.three = true;
        //           this.oneShow = false;
        //           this.twoShow = true;
        //           this.threeShow = true;
        //           this.fourShow = true;
        //         }, 220);
      });
    },
    getDetail(id) {
      this.$api.approval
        .getscm({
          url: "/scm/scmCgProcurementOrder/v1/get/" + id
        })
        .then(res => {
          if (res.state === false) {
            this.$message({
              message: "操作失败, " + res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.detailInfo = Object.assign({}, res);
        });
    },
    prevStep() {
      this.three = false;
      this.active = 0;
      setTimeout(() => {
        this.one = true;
        this.two = true;
        this.oneShow = true;
        this.twoShow = false;
        this.threeShow = false;
        this.fourShow = false;
      }, 220);
    },
    goback() {
      this.findPage();
      this.startShow = true;
      this.one = false;
      this.two = false;
      this.four = false;
      this.oneShow = true;
      this.twoShow = false;
      this.threeShow = false;
      this.fourShow = false;
      this.three = false;
      this.resetForm("wrapForm1");
      this.contractList = [];
      this.active = 0;
      this.firstStepId = "";
    },
    applySave() {},
    sendApply() {
      //发起申请发起申请
      this.$api.approval
        .postscm({
          url: "/scm/scmCgProcurementOrder/v1/sendApply",
          data: {
            id: this.fisrtStepId
          }
        })
        .then(res => {
          if (res.state === false) {
            this.$message({
              message: "操作失败, " + res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.$message({
            message: res.shortMessage,
            type: "success",
            duration: 800
          });
          setTimeout(() => {
            this.three = false;
            this.four = true;
            this.oneShow = false;
            this.twoShow = false;
            this.threeShow = false;
            this.fourShow = false;
            this.active = 3;
          }, 220);
        });
    },
    getSupplier() {
      //供应商
      this.$api.approval
        .getscm({
          url: "/scm/scmZsjSupplier/v1/downBoxApplySuccess/" + this.ownerId
        })
        .then(res => {
          if (res) {
            this.gongoption = res;
          } else {
            this.$message({
              message: "操作失败, " + res.shortMessage,
              type: "error",
              duration: 800
            });
          }
        });
    },
    getTicket() {
      //获取收票方下拉列表
      this.$api.approval
        .postscm({
          url: "/scm/scmZdGetticket/v1/list",
          data: {}
        })
        .then(res => {
          if (res) {
            this.ticklist = res.rows;
          } else {
            this.$message({
              message: "操作失败, " + res.shortMessage,
              type: "error",
              duration: 800
            });
          }
        });
    },
    getWareHouse() {
      //获取收货仓库下拉列表
      this.$api.approval
        .postscm({
          url: "/scm/scmZdGetMoneyWarehouse/v1/list",
          data: {}
        })
        .then(res => {
          if (res) {
            this.wareHouseList = res.rows;
          } else {
            this.$message({
              message: "操作失败, " + res.shortMessage,
              type: "error",
              duration: 800
            });
          }
        });
    },
    getContract(supplierId = "") {
      //获取合同编号下拉列表
      this.$api.approval
        .postscm({
          url: "/scm/scmCgProcurementContract/v1/list",
          data: {
            querys: [
              {
                property: "supplierId",
                value: supplierId,
                operation: "EQUAL",
                relation: "AND"
              },
              {
                property: "ownerId",
                value: this.ownerId,
                operation: "EQUAL",
                relation: "AND"
              },
              {
                property: "approvalState",
                value: "2",
                operation: "EQUAL",
                relation: "AND"
              }
            ]
          }
        })
        .then(res => {
          if (res) {
            this.contractList = res.rows;
          } else {
            this.$message({
              message: "操作失败, " + res.shortMessage,
              type: "error",
              duration: 800
            });
          }
        });
    },
    getSecondTable() {
      this.loading2 = true;
      this.$api.approval
        .postscm({
          url: "/scm/scmCgProOrderProduct/v1/list",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys: [
              {
                property: "proOrderId",
                value: this.fisrtStepId,
                operation: "EQUAL",
                relation: "AND"
              }
            ],
            sorter: []
          }
        })
        .then(res => {
          if (res) {
            this.secondTableData = res.rows;
            this.total = res.total;
            this.loading2 = false;
            this.search = [];
          } else {
            this.$message({
              message: "操作失败, " + res.shortMessage,
              type: "error",
              duration: 800
            });
          }
        });
    },
    getDownBox() {
      this.$api.approval
        .postscm({
          url: "/scm/scmCgContractProduct/v1/list",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys: [
              {
                property: "contractId",
                value: this.wrapForm.contractId,
                operation: "EQUAL",
                relation: "AND"
              }
            ],
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
          this.resultList = res.rows.reduce((final, item, index, arr) => {
            item.value =
              item.productName +
              "|" +
              item.productCode +
              "|" +
              item.productCname +
              "|" +
              item.productGauge +
              "|" +
              item.medicineType;
            final.push(item);
            return final;
          }, []);
        });
    },
    querySearch(queryString, cb) {
      var result = this.resultList;
      var results = queryString
        ? result.filter(this.createFilter(queryString))
        : result;
      cb(results);
    },
    createFilter(queryString) {
      return restaurant => {
        return (
          restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) ===
          0
        );
      };
    },
    handleSelect(item) {
      this.addForm = Object.assign(this.addForm, item);
    },
    changeSupplier() {
      let supplier = this.wrapForm.supplier;
      let list = [].concat(this.gongoption);
      if (supplier === "") {
        this.wrapForm.contractCode = "";
        return;
      }
      for (let i of list) {
        if (supplier == i.supplierName) {
          this.getContract(i.id);
        }
      }
    },
    addProduct() {
      if (this.$refs["addForm"] !== undefined) {
        this.$refs["addForm"].resetFields();
      }
      this.dialogVisible = true;
    }
  },
  mounted() {
    this.ownerId = storage.get("ownerId");
    this.findPage();
    this.getSupplier(); //获取供应商下拉列表
    this.getTicket(); //获取收票方下拉列表
    this.getWareHouse(); //获取收货仓库下拉列表
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
  background: none !important ;
}
.tanFoot {
  margin-top: 15px;
  display: flex;
}
.savebox {
  margin-left: 20px;
}
.el-form {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  .el-form-item {
    width: 33%;
  }
}
.el-table .cell {
  white-space: nowrap !important;
}
header {
  height: 40px !important;
}
.el-form-item {
  margin-top: 10px;
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
.head-item {
  border: 1px solid #f2f2f2;
}
.content-item {
  // border: 1px solid #f2f2f2;
  margin-top: 8px;
}
.main-content {
  padding: 0 !important;
}
.success {
  // width: 350px;
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
</style>