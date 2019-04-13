<template>
  <div>
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
        >
          <el-form-item label="产品编码">
            <el-input v-model="cpbmname" clearable placeholder="请输入产品编码"></el-input>
          </el-form-item>
          <el-form-item label="产品名称">
            <el-input v-model="cpmcname" clearable placeholder="请输入产品名称"></el-input>
          </el-form-item>
          <el-form-item label="物料编码">
            <el-input v-model="wlbmname" clearable placeholder="请输入物料编码"></el-input>
          </el-form-item>
          <el-form-item label="存货代码">
            <el-input v-model="chdmcname" clearable placeholder="请输入存货代码"></el-input>
          </el-form-item>
          <el-form-item label="药品类型">
            <el-select v-model="ypid" placeholder="请选择药品类型" @change="getypname">
              <el-option
                v-for="item in ypoption"
                :key="item.id"
                :label="item.medicineType"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="产品系列">
            <el-select v-model="cpid" placeholder="请选择产品系列" @change="getcpname">
              <el-option
                v-for="item in cpoption"
                :key="item.productLineName"
                :label="item.productLineName"
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
            <el-button type="primary" icon="el-icon-circle-plus-outline" size="mini" @click="add">新增</el-button>
          </el-row>
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
        <el-table-column type="index" label="序号" width="100" align="center"></el-table-column>
        <el-table-column
          v-for="item in column"
          :key="item.prop"
          :prop="item.prop"
          :label="item.label"
          :width="item.minwidth"
          :align="item.align"
        ></el-table-column>
        <el-table-column fixed="right" align="center" label="操作" width="220">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              class="el-icon-circle-plus-outline"
              @click="addNewAcChange(scope.row.id)"
            >新增件装量</el-button>
            <el-button type="primary" size="mini" class="el-icon-edit" @click="edit(scope.row)">编辑</el-button>
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
    </el-container>
    <!--弹框-->
    <el-dialog :title="this.operation?'商品新增':'商品编辑'" width="80%" :visible.sync="dialogTableVisible">
      <div>
        <el-container>
          <el-main>
            <el-form
              :model="ruleForm"
              :rules="rules"
              size="small"
              ref="ruleForm1"
              :label-position="labelPosition"
              label-width="120px"
              class="demo-ruleForm"
            >
              <el-form-item prop="addcpmcname" label="产品名称">
                <el-input v-model="ruleForm.addcpmcname" clearable placeholder="请输入产品名称"></el-input>
              </el-form-item>
              <el-form-item prop="addcpxlid" label="产品系列">
                <el-select
                  v-model="ruleForm.addcpxlid"
                  placeholder="请选择产品系列"
                  @change="getaddcpxiname"
                >
                  <el-option
                    v-for="item in addcpxioption"
                    :key="item.id"
                    :label="item.productLineName"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="addcpbmname" label="产品编码">
                <el-input v-model="ruleForm.addcpbmname" clearable placeholder="请输入产品编码"></el-input>
              </el-form-item>
              <el-form-item prop="addwlbmname" label="物料编码">
                <el-input v-model="ruleForm.addwlbmname" clearable placeholder="请输入物料编码"></el-input>
              </el-form-item>
              <el-form-item prop="addtymcname" label="通用名称">
                <el-input v-model="ruleForm.addtymcname" clearable placeholder="请输入通用名称"></el-input>
              </el-form-item>
              <el-form-item prop="addchdmname" label="存货代码">
                <el-input v-model="ruleForm.addchdmname" clearable placeholder="请输入存货代码"></el-input>
              </el-form-item>

              <el-form-item prop="addjxid" label="剂型">
                <el-select v-model="ruleForm.addjxid" placeholder="请选择剂型" @change="getaddjxname">
                  <el-option
                    v-for="item in addjxoption"
                    :key="item.id"
                    :label="item.agentType"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="addggname" label="规格">
                <el-input v-model="ruleForm.addggname" clearable placeholder="请输入规格"></el-input>
              </el-form-item>
              <el-form-item prop="adddwname" label="单位">
                <el-input v-model="ruleForm.adddwname" clearable placeholder="请输入单位"></el-input>
              </el-form-item>
              <el-form-item prop="addotcname" label="OTC">
                <el-select v-model="ruleForm.addotcname" placeholder="请选择OTC">
                  <el-option
                    v-for="item in otcoption"
                    :key="item.inx"
                    :label="item.classification"
                    :value="item.classification"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="addyplxid" label="药品类型">
                <el-select
                  v-model="ruleForm.addyplxid"
                  placeholder="请选择药品类型"
                  @change="getaddyplxname"
                >
                  <el-option
                    v-for="item in addyplxoption"
                    :key="item.id"
                    :label="item.medicineType"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="addypflid" label="药品分类">
                <el-select
                  v-model="ruleForm.addypflid"
                  placeholder="请选择药品分类"
                  @change="getaddypflname"
                >
                  <el-option
                    v-for="item in addypfloption"
                    :key="item.code"
                    :label="item.medicineClassify"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="addzctjid" label="贮存条件">
                <el-select
                  v-model="ruleForm.addzctjid"
                  placeholder="请选择贮存条件"
                  @change="getaddzctjname"
                >
                  <el-option
                    v-for="item in addzctjoption"
                    :key="item.suggestWarehouse"
                    :label="item.keepingCondition"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="供应商名称" prop="gongname">
                <el-select v-model="ruleForm.gongname" placeholder="请选择供应商名称" @change="getgongname">
                  <el-option
                    v-for="item in gongoption"
                    :key="item.id"
                    :label="item.supplierName"
                    :value="item.supplierName"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="addsccsname" label="生产厂商">
                <el-select v-model="ruleForm.addsccsname" placeholder="请选择生产厂商">
                  <el-option
                    v-for="item in addsccsoption"
                    :key="item.id"
                    :label="item.manufacturer"
                    :value="item.manufacturer"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="addjykcname" label="建议库存">
                <el-input v-model="ruleForm.addjykcname" :disabled="true" placeholder="请先选择贮存条件 "></el-input>
              </el-form-item>
              <el-form-item>
                <el-checkbox v-model="checked">追溯码</el-checkbox>
              </el-form-item>
            </el-form>
          </el-main>
          <el-header>
            <div class="header head">
              <div class="headleft"></div>
              <div class="headright tanFoot">
                <el-row>
                  <el-button @click="qx" size="mini">取消</el-button>
                </el-row>
                <el-row class="savebox">
                  <el-button type="primary" size="mini" @click="save">保存</el-button>
                </el-row>
              </div>
            </div>
          </el-header>
        </el-container>
      </div>
    </el-dialog>
    <el-dialog title="件装量" width="40%" :visible.sync="acChangeDialog.dialogAcChangeVisible">
      <div class="acchange">
        <el-tag
          :key="tag"
          v-for="tag in acChangeDialog.dynamicTags"
          closable
          :disable-transitions="false"
          @close="handleClose(tag)"
        >{{tag}} 盒/件</el-tag>
        <el-input
          class="input-new-tag"
          v-if="acChangeDialog.inputVisible"
          v-model="acChangeDialog.inputValue"
          ref="saveTagInput"
          size="small"
          @keyup.enter.native="handleInputConfirm"
          @blur="handleInputConfirm"
        ></el-input>
        <el-button v-else class="button-new-tag" size="small" @click="showInput">+ 新增件装量</el-button>
      </div>
      <el-row style="margin-top:20px;text-align:right">
        <el-button @click.native="acChangeDialog.dialogAcChangeVisible = false" size="mini">取消</el-button>
        <el-button
          type="primary"
          size="mini"
          @click="acChangeDialog.dialogAcChangeVisible = false"
        >确定</el-button>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import storage from "@/utils/storage";
export default {
  name: "goods",
  data() {
    return {
      column: [
        {
          prop: "productName",
          label: "产品名称",
          minwidth: "180",
          align: "center"
        },
        {
          prop: "productNum",
          label: "产品编码",
          minwidth: "100",
          align: "center"
        },
        {
          prop: "productLineName",
          label: "产品系列",
          minwidth: "150",
          align: "center"
        },
        {
          prop: "productCode",
          label: "物料编码",
          minwidth: "100",
          align: "center"
        },
        {
          prop: "commonName",
          label: "通用名称",
          minwidth: "180",
          align: "center"
        },
        { prop: "agentType", label: "剂型", minwidth: "150", align: "center" },
        {
          prop: "productState",
          label: "规格",
          minwidth: "180",
          align: "center"
        },
        { prop: "unit", label: "单位", minwidth: "100", align: "center" },
        { prop: "otc", label: "OTC", minwidth: "100", align: "center" },
        {
          prop: "medicineType",
          label: "药品类型",
          minwidth: "150",
          align: "center"
        },
        {
          prop: "supplierName",
          label: "供应商名称",
          minwidth: "180",
          align: "center"
        },
        {
          prop: "manufacturer",
          label: "生产厂商",
          minwidth: "280",
          align: "center"
        },
        {
          prop: "suggestWarehouse",
          label: "建议库存",
          minwidth: "150",
          align: "center"
        },
        {
          prop: "inventoryCode",
          label: "存货代码",
          minwidth: "150",
          align: "center"
        },
        {
          prop: "keepingCondition",
          label: "贮存条件",
          minwidth: "150",
          align: "center"
        }
      ],
      restaurants: [],
      labelPosition: "right",
      loading2: true,
      jyid: "",
      ypid: "",
      cpid: "",
      jyname: "",
      ypname: "",
      cpname: "",
      wlbmname: "",
      gongid: "",
      ownerId: "",
      chdmcname: "",
      total: 0,
      operation: true,
      dialogTableVisible: false,
      acChangeDialog: {
        dialogAcChangeVisible: false,
				dynamicTags: [],
				list:[],
        id: "",
        inputVisible: false,
        inputValue: ""
      },
      options: [],
      ypoption: [],
      cpoption: [],
      addcpxioption: [],
      addjxoption: [],
      addyplxoption: [],
      addypfloption: [],
      addzctjoption: [],
      addsccsoption: [],
      gongoption: [],
      tableData3: [],
      otcoption: [
        { classification: "甲类", inx: "1" },
        { classification: "乙类", inx: "2" },
        { classification: "无", inx: "3" }
      ],
      checked: false,
      loading: false,
      cpbmname: "",
      cpmcname: "",
      addcpxlname: "",
      addjxname: "",
      addyplxname: "",
      addypflname: "",
      addzctjname: "",
      addzsm: "0",
      editid: "",
      search: [],
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      ruleForm: {
        cpbmname: "",
        cpmcname: "",
        addwlbmname: "",
        addtymcname: "",
        addggname: "",
        adddwname: "",
        addjzlname: "",
        addjykcname: "",
        addchdmname: "",
        addcpxlid: "",
        addjxid: "",
        addotcname: "",
        addyplxid: "",
        addypflid: "",
        addzctjid: "",
        addcpmcname: "",
        addsccsname: "",
        addcpbmname: "",
        gongname: ""
      },
      rules: {
        addcpbmname: [
          { required: true, message: "请输入产品编码", trigger: "blur" },
          { max: 20, message: "最多输入20个字符", trigger: "blur" }
        ],
        addcpmcname: [
          { required: true, message: "请输入产品名称", trigger: "blur" },
          { max: 20, message: "最多输入20个字符", trigger: "blur" }
        ],
        addwlbmname: [
          { required: true, message: "请输入物料编码", trigger: "blur" },
          { max: 20, message: "最多输入20个字符", trigger: "blur" }
        ],
        addtymcname: [
          { required: true, message: "请输入通用名称", trigger: "blur" },
          { max: 20, message: "最多输入20个字符", trigger: "blur" }
        ],
        addggname: [
          { required: true, message: "请输入规格", trigger: "blur" },
          { max: 20, message: "最多输入20个字符", trigger: "blur" }
        ],
        adddwname: [
          { required: true, message: "请输入单位", trigger: "blur" },
          { max: 20, message: "最多输入20个字符", trigger: "blur" }
        ],
        addchdmname: [
          { required: true, message: "请输入存货代码", trigger: "blur" },
          { max: 50, message: "最多输入50个字符", trigger: "blur" }
        ],
        addcpxlid: [
          { required: true, message: "请选择产品系列", trigger: "change" }
        ],
        addotcname: [
          { required: true, message: "请选择otc", trigger: "change" }
        ],
        addyplxid: [
          { required: true, message: "请选择药品类型", trigger: "change" }
        ],
        addypflid: [
          { required: true, message: "请选择药品分类", trigger: "change" }
        ],
        addzctjid: [
          { required: true, message: "请选择贮存条件", trigger: "change" }
        ],
        gongname: [
          { required: true, message: "请选择供应商", trigger: "change" }
        ],
        addsccsname: [
          { required: true, message: "请选择生产厂商", trigger: "change" }
        ],
        addjxid: [{ required: true, message: "请选择剂型", trigger: "change" }],
        addjykcname: [
          {
            required: true,
            message: "请选择贮存条件，自动匹配建议库存",
            trigger: "change"
          }
        ]
      }
    };
  },
  methods: {
    handleClose(tag) {
			let  item = this.acChangeDialog.list.filter((item, index) => {
				return item.achage === tag;
			});
			this.$api.zsj.deleteA({
				url:'/scm/ScmZsjAchage/v1/remove/'+item[0].id
			}).then(res => {
				if(res.state == false){
					this.$message({
						message: res.message,
						type: "error",
						duration: 800
					});
					return;
				};
      	this.acChangeDialog.dynamicTags.splice(this.acChangeDialog.dynamicTags.indexOf(tag), 1);
			})
    },

    showInput() {
      this.acChangeDialog.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      let inputValue = this.acChangeDialog.inputValue;
      if (inputValue) {
        this.$api.zsj
          .post({
            url: "/scm/ScmZsjAchage/v1/save",
            data: {
              productId: this.acChangeDialog.id,
              achage: inputValue
            }
          })
          .then(res => {
            if (res.state == false) {
              this.$message({
                message: res.message,
                type: "error",
                duration: 800
							});
							return;
            }
            this.acChangeDialog.list.push({
              achage: inputValue,
              id: res.value.id,
              productId: res.value.productId
            })
						this.acChangeDialog.dynamicTags.push(inputValue)
          });
      }
      this.acChangeDialog.inputVisible = false;
      this.acChangeDialog.inputValue = "";
    },
    addNewAcChange(id) {
      //新增件装量方法
      this.acChangeDialog.id = id;
      this.$api.zsj
        .getZsj({
          url: "/scm/ScmZsjAchage/v1/list",
          data: {
            pageBean: {},
            params: {},
            querys: [
              {
                property: "productId",
                value: id,
                operation: "EQUAL",
                relation: "AND"
              }
            ],
            sorter: []
          }
        })
        .then(res => {
          if (res.state == false) {
            return;
          }
          let arr = [];
          res.rows.forEach((item, index) => {
            arr.push(item.achage);
					});
					this.acChangeDialog.list = res.rows;
          this.acChangeDialog.dynamicTags = arr;
          this.acChangeDialog.dialogAcChangeVisible = true;
        });
    },
    refreshPageRequest: function(page = 1) {
      this.pageRequest.page = page;
      this.findPage();
    },
    refreshPageSizeRequest: function(pageSize) {
      this.tableData3.pageSize = pageSize;
      this.findPage();
    },
    getypname() {
      //获取药品类型的name
      if (this.ypoption.length != 0) {
        var data = this.ypoption.filter(item => {
          if (item.id == this.ypid) {
            this.ypname = item.medicineType;
            console.log(this.ypname);
          }
        });
      }
    },
    getname() {
      //获取药品分类的name
      if (this.options.length != 0) {
        var data = this.options.filter(item => {
          if (item.id == this.ypid) {
            this.ypname = item.medicineType;
            console.log(this.ypname);
          }
        });
      }
    },
    getcpname() {
      //获取产品系列的name
      if (this.cpoption.length != 0) {
        var data = this.cpoption.filter(item => {
          if (item.id == this.cpid) {
            this.cpname = item.productLineName;
            console.log(this.cpname);
          }
        });
      }
    },
    getaddcpxiname() {
      //获取弹框产品系列的name
      if (this.addcpxioption.length != 0) {
        var data = this.addcpxioption.filter(item => {
          if (item.id == this.ruleForm.addcpxlid) {
            this.addcpxlname = item.productLineName;
            console.log(this.addcpxlname);
          }
        });
      }
    },
    getaddjxname() {
      //获取弹框剂型的name
      if (this.addjxoption.length != 0) {
        var data = this.addjxoption.filter(item => {
          if (item.id == this.ruleForm.addjxid) {
            this.addjxname = item.agentType;
            console.log(this.addjxname);
          }
        });
      }
    },
    getaddyplxname() {
      //获取弹框药品类型的name
      if (this.addyplxoption.length != 0) {
        var data = this.addyplxoption.filter(item => {
          if (item.id == this.ruleForm.addyplxid) {
            this.addyplxname = item.medicineType;
            console.log(this.addyplxname);
          }
        });
      }
    },
    getaddzctjname() {
      //获取贮存条件的name
      if (this.addzctjoption.length != 0) {
        var data = this.addzctjoption.filter(item => {
          if (item.id == this.ruleForm.addzctjid) {
            this.addzctjname = item.keepingCondition;
            this.ruleForm.addjykcname = item.suggestWarehouse;
            console.log(this.ruleForm.addjykcname);
          }
        });
      }
    },
    getaddypflname() {
      //获取弹框药品分类的name
      if (this.addypfloption.length != 0) {
        var data = this.addypfloption.filter(item => {
          if (item.id == this.ruleForm.addypflid) {
            this.addypflname = item.medicineClassify;
            console.log(this.addypflname);
          }
        });
      }
    },
    getgongname() {
      //获取供应商id
      if (this.gongoption.length != 0) {
        var data = this.gongoption.filter(item => {
          if (item.supplierName == this.ruleForm.gongname) {
            this.gongid = item.id;
            console.log(this.gongid);
          }
        });
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    qx() {
      this.dialogTableVisible = false;
      this.resetForm("ruleForm1");
    },
    add() {
      this.dialogTableVisible = true;
      this.operation = true;
    },
    save() {
      this.$refs.ruleForm1.validate(valid => {
        if (valid) {
          if (this.checked == "true") {
            this.addzsm = "1";
          } else {
            this.addzsm = "0";
          }
          var information = {
            productName: this.ruleForm.addcpmcname,
            productLineId: this.ruleForm.addcpxlid, //产品系列id
            productLineName: this.addcpxlname, //产品系列名称
            productCode: this.ruleForm.addwlbmname, //物料编码
            commonName: this.ruleForm.addtymcname, //通用名称
            agentType: this.addjxname, //剂型名称
            agentTypeId: this.ruleForm.addjxid, //剂型id
            productState: this.ruleForm.addggname, //规格
            unit: this.ruleForm.adddwname, //单位
            otc: this.ruleForm.addotcname, //OTC
            medicineType: this.addyplxname, //药品类型
            medicineTypeId: this.ruleForm.addyplxid, //药型类型id
            medicineClassifyId: this.ruleForm.addypflid, //药品分类id
            medicineClassify: this.addypflname, //药品分类
            keepingCondition: this.addzctjname, //贮存条件
            supplierName: this.ruleForm.gongname, //供应商名称
            supplierId: this.gongid, //供应商id
            manufacturer: this.ruleForm.addsccsname, //生产厂商
            suggestWarehouse: this.ruleForm.addjykcname, //建议库存
            inventoryCode: this.ruleForm.addchdmname, //存货代码
            productNum: this.ruleForm.addcpbmname, //产品编码
            traceCode: this.addzsm, //追溯码
            id: this.editid
          };
          this.$api.zsj
            .getZsj({
              url: "/scm/scmZsjProduct/v1/save",
              data: information
            })
            .then(res => {
              console.log(res);
              if (res) {
                this.$message({
                  message: "保存成功",
                  type: "success",
                  duration: 800
                });
                this.resetForm("ruleForm1");
                this.checked = "false";
                information = {};
                this.dialogTableVisible = false;
                this.findPage();
              } else {
                this.$message({
                  message: "操作失败, " + res.shortMessage,
                  type: "error",
                  duration: 800
                });
              }
            });
        }
      });
    },
    edit(rows) {
      this.dialogTableVisible = true;
      this.operation = false;
      this.ruleForm.addcpmcname = rows.productName;
      this.ruleForm.addcpxlid = rows.productLineId;
      this.addcpxlname = rows.productLineName;
      this.ruleForm.addwlbmname = rows.productCode;
      this.ruleForm.addtymcname = rows.commonName;
      this.addjxname = rows.agentType;
      this.ruleForm.addjxid = rows.agentTypeId;
      this.ruleForm.addggname = rows.productState;
      this.ruleForm.adddwname = rows.unit;
      this.ruleForm.addotcname = rows.otc;
      this.ruleForm.addjzlname = rows.achange;
      this.addyplxname = rows.medicineType;
      this.ruleForm.addyplxid = rows.medicineTypeId;
      this.ruleForm.addypflid = rows.medicineClassifyId;
      this.ruleForm.addsccsname = rows.manufacturer;
      this.ruleForm.addjykcname = rows.suggestWarehouse;
      this.ruleForm.addchdmname = rows.inventoryCode;
      this.checked = true;
      this.ruleForm.addzctjid = rows.keepingCondition;
      this.editid = rows.id;
      this.ruleForm.addcpbmname = rows.productNum;
      this.ruleForm.gongname = rows.supplierName;
      this.gongid = rows.supplierId;
    },
    searchquery() {
      if (this.cpbmname) {
        this.search.push({
          property: "productNum",
          value: this.cpbmname,
          operation: "LIKE",
          relation: "AND"
        });
      }
      if (this.cpmcname) {
        this.search.push({
          property: "productName",
          value: this.cpmcname,
          operation: "LIKE",
          relation: "AND"
        });
      }
      if (this.jyname) {
        this.search.push({
          property: "medicineClassify",
          value: this.jyname,
          operation: "LIKE",
          relation: "AND"
        });
      }
      if (this.cpid) {
        this.search.push({
          property: "ProductLineId",
          value: this.cpid,
          operation: "=",
          relation: "AND"
        });
      }
      if (this.ypname) {
        this.search.push({
          property: "ProductLineId",
          value: this.ypname,
          operation: "=",
          relation: "AND"
        });
      }
      if (this.wlname) {
        this.search.push({
          property: "productCode",
          value: this.wlname,
          operation: "LIKE",
          relation: "AND"
        });
      }
      if (this.chdmcname) {
        this.search.push({
          property: "inventoryCode",
          value: this.chdmcname,
          operation: "LIKE",
          relation: "AND"
        });
      }
      this.findPage(this.search);
    },
    //商品列表
    findPage(search) {
      this.loading = true;
      this.$api.zsj
        .getZsj({
          url: "/scm/scmZsjProduct/v1/list",
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
    }
  },
  mounted() {
    this.ownerId = storage.get("ownerId");
    this.findPage();
    //药品类型
    this.$api.zsj
      .getZsj({
        url: "/scm/scmZdMedicineType/v1/list",
        data: {}
      })
      .then(res => {
        if (res) {
          this.ypoption = res.rows;
          this.addyplxoption = res.rows;
        } else {
          this.$message({
            message: "操作失败, " + res.shortMessage,
            type: "error",
            duration: 800
          });
        }
      });
    //产品系列
    this.$api.zsj
      .getZsj({
        url: "/scm/scmZsjProductLine/v1/list",
        data: {}
      })
      .then(res => {
        if (res) {
          this.cpoption = res.rows;
          this.addcpxioption = res.rows;
        } else {
          this.$message({
            message: "操作失败, " + res.shortMessage,
            type: "error",
            duration: 800
          });
        }
      });
    //剂型
    this.$api.zsj
      .getZsj({
        url: "/scm/scmZdAgenttype/v1/list",
        data: {}
      })
      .then(res => {
        if (res) {
          this.addjxoption = res.rows;
        } else {
          this.$message({
            message: "操作失败, " + res.shortMessage,
            type: "error",
            duration: 800
          });
        }
      });
    //药品分类
    this.$api.zsj
      .getZsj({
        url: "/scm/scmZdMedicineClassify/v1/list",
        data: {}
      })
      .then(res => {
        if (res) {
          this.addypfloption = res.rows;
          this.options = res.rows;
        } else {
          this.$message({
            message: "操作失败, " + res.shortMessage,
            type: "error",
            duration: 800
          });
        }
      });
    //贮存条件
    this.$api.zsj
      .getZsj({
        url: "/scm/scmZdKeepingCondition/v1/list",
        data: {}
      })
      .then(res => {
        if (res) {
          this.addzctjoption = res.rows;
        } else {
          this.$message({
            message: "操作失败, " + res.shortMessage,
            type: "error",
            duration: 800
          });
        }
      });
    //生产厂商
    this.$api.zsj
      .getZsj({
        url: "/scm/scmZdManufacturer/v1/list",
        data: {}
      })
      .then(res => {
        console.log(res);
        if (res) {
          this.addsccsoption = res.rows;
        } else {
          this.$message({
            message: "操作失败, " + res.shortMessage,
            type: "error",
            duration: 800
          });
        }
      });
    //供应商
    this.$api.zsj
      .changeState({
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
.el-main {
  padding: 0 !important;
}
header {
  height: 58px !important;
}

.el-tag {
  margin-left: 10px;
  margin-top: 10px;
}
.button-new-tag {
  margin-left: 10px;
  margin-top: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  margin-top: 10px;
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
.acchange {
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;
}
</style>