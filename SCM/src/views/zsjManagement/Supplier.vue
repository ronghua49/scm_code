<template>
    <!-- <div>{{msg}}</div> -->
    <div>
      <!-- 添加筛选功能 -->
      <el-row class="searchHeader">
        <el-col :span="2"><div>筛选查询</div></el-col>
        <el-col :span="22" style="text-align: right" >
          <el-button icon="el-icon-search" :size="size" type="primary" @click="inquire">查询</el-button>
        </el-col>
      </el-row>
      <el-row class="searchBox">
        <el-form :model="searchForm" ref="searchForm" label-width="140px" size="small">
          <el-form-item label="档案编号">
            <el-input v-model="searchForm.fileCode" clearable  placeholder="请输入档案编号"></el-input>
          </el-form-item>
          <el-form-item label="商业名称">
						<el-input v-model="searchForm.supplierName" clearable  placeholder="请输入商业名称"></el-input>
				  </el-form-item>
        </el-form>
      </el-row>




      <el-row class="btnAdd">
        <el-col :xs="2" :sm="2" :md="2" :lg="2">
          <el-button icon="el-icon-circle-plus-outline" type="primary" size="mini" @click="handleAdd">新增</el-button>
        </el-col>
      </el-row>
      
       <!--表格栏-->
    <el-table :data="pageResult.rows" highlight-current-row  
           v-loading="loading"   element-loading-text="加载中" border stripe
           max-height="500" size="mini" align="left" style="width:100%;" >
      <!-- <el-table-column type="selection" width="40"></el-table-column> -->
      <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
      <el-table-column v-for="column in columns" header-align="center" align="center"
        :prop="column.prop" :label="column.label" :show-overflow-tooltip="true"
         :key="column.prop"  :min-width="column.minWidth" :formatter="column.formatter"
        :sortable="column.sortable==null?true:column.sortable">
      </el-table-column>
      <el-table-column label="操作" width="210" fixed="right" header-align="center" align="center">
        <template slot-scope="scope">
          <el-button icon="el-icon-edit" label=""  :size="size" type="primary"  @click="handleEdit(scope.$index, scope.row)" >编辑</el-button>
          <el-button icon="el-icon-view" label=""  :size="size" type="primary"  @click="handleChange(scope.$index, scope.row)" >修改记录</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页栏-->
    <div class="toolbar" style="padding:10px;">
      <el-pagination  @current-change="refreshPageRequest" @size-change="refreshPageSizeRequest" background
        :current-page="pageRequest.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest.pageSize" layout="sizes, total, prev, pager, next" :total="pageResult.total" style="float:right;">
      </el-pagination>
    </div>

      <!--新增编辑界面-->
    <el-dialog :title="operation?'新增':'编辑'" width="70%"  :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="120px" :rules="dataFormRules" ref="dataForm" size="small">
        <el-form-item label="ID" prop="id" v-if="false">
          <el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="供应商名称" prop="supplierName">
          <el-input v-model="dataForm.supplierName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="供应商编号" prop="supplierCode">
          <el-input v-model="dataForm.supplierCode" auto-complete="off"></el-input>
        </el-form-item>
        <!-- <el-form-item label="企业类别" prop="commerceState">
          <el-select v-model="dataForm.commerceState" placeholder="请选择企业类别">
              <el-option value='0' label="药品经营企业"></el-option>
              <el-option value="1" label="医疗机构"></el-option>
          </el-select>
        </el-form-item> -->
        <el-form-item label="企业负责人" prop="conpanyPersion">
          <el-input v-model="dataForm.conpanyPersion" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系人" prop="contactPersion">
          <el-input v-model="dataForm.contactPersion" auto-complete="off" ></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="dataForm.phone" auto-complete="off"></el-input>
        </el-form-item>
        
        <el-form-item label="开户行" prop="openBank">
          <el-input v-model="dataForm.openBank" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="bankCode">
          <el-input v-model="dataForm.bankCode" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="税号" prop="dutyParagraph">
          <el-input v-model="dataForm.dutyParagraph" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="注册地址" prop="registerAddress">
          <el-input v-model="dataForm.registerAddress" auto-complete="off"></el-input>
        </el-form-item>
        
        <el-form-item label="药品质量保证协议有效期至" prop="qualityValidity">
          <el-date-picker 
          v-model="dataForm.qualityValidity" 
          type="date" 
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="选择日期" 
          style="width: 100%;"></el-date-picker>
        </el-form-item>
        <!-- <el-form-item label="" prop="">
          <el-date-picker v-model="dataForm.qualityValidity" type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
        </el-form-item> -->
        <el-form-item label="">
          <el-checkbox-group v-model="dataForm.sealStyle" style='float: left'>
            <el-checkbox label="相关印章印模样式" name="sealStyle"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="">
          <el-checkbox-group v-model="dataForm.billsStyle" style='float: left'>
            <el-checkbox label="随货同行单(票)样式" name="billsStyle"></el-checkbox>            
          </el-checkbox-group>
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
  name: "supplier",
  components: {},
  data() {
    return {
      size: "small",
      columns: [
        { prop: "fileCode", label: "档案编号" ,minWidth: 150},
        // { prop: "commerceState", label: "企业类别",minWidth: 100,formatter:(row, column, cellValue, index)=>{return cellValue=='1'?'医疗机构':'药品经营企业'}},
        { prop: "supplierName", label: "商业名称",minWidth: 100 },
        { prop: "conpanyPersion", label: "企业负责人", minWidth: 110 },
        { prop: "contactPersion", label: "联系人", minWidth: 100 },
        { prop: "phone", label: "联系电话", minWidth: 100 },
        { prop: "supplierCode", label: "供应商编号", minWidth: 150 },
        { prop: "openBank", label: "开户行",minWidth: 100 },
        { prop: "bankCode", label: "账号" },
        { prop: "dutyParagraph", label: "税号" },
        { prop: "qualityValidity", label: "药品质量保证协议有效期",minWidth: 190 },
        { prop: "registerAddress", label: "注册地址", minWidth: 120},
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
        supplierName: [
          { required: true, message: "请输入供应商名称", trigger: "blur" },
          { max: 50, message: "长度不能超过50", trigger: "blur" }
        ],
        supplierCode: [
          { required: true, message: "请输入供应商编号", trigger: "blur" },
          { max: 50, message: "长度不能超过50", trigger: "blur" }
        ],
        conpanyPersion: [
          { required: true, message: "请输入企业负责人", trigger: "blur" },
          { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        contactPersion: [
          {
            required: true,
            message: "请输入联系人",
            trigger: "blur"
          },
          { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "请输入联系电话", trigger: "blur" },
          { max: 11, message: "长度不能超过11", trigger: "blur" }
        ],
        openBank: [
          { required: true, message: "请输入开户行", trigger: "blur" },
          { max: 20, message: "长度不能超过11", trigger: "blur" }
        ],
        bankCode: [
          { required: true, message: "请输入账号", trigger: "change" },
          { max: 30, message: "长度不能超过30", trigger: "blur" }
        ],
        dutyParagraph: [
          { required: true, message: "请输入税号", trigger: "blur" },
          { max: 100, message: "长度不能超过100", trigger: "blur" }
        ],
        registerAddress: [
          { required: true, message: "请输入注册地址", trigger: "blur" },
          { max: 100, message: "长度不能超过100", trigger: "blur" }
        ],
        qualityValidity: [
          { required: true, message: "请选择日期", trigger: "blur" },
        ],
        // businessDivisionId: [
        //   { required: true, message: "请选择商务分区", trigger: "change" },
        // ],
        // provinceId: [
        //   { required: true, message: "请选择省区", trigger: "change" },
        // ],
        
      },
      // 新增编辑界面数据
      dataForm: {},
      menuData: [],
      menuSelections: [],
      menuLoading: false,
      authLoading: false,
      checkAll: false,
      loading: false,
      size: "mini",
      // 查询功能
      searchForm: {
        fileCode: '',
        supplierName: ''
      }
    };
  },
  mounted() {
    this.findPage();
  },
  methods: {
    handleAdd: function() {
      this.dialogVisible = true;
      this.operation = true;
      if (this.$refs["dataForm"]!==undefined) {
          this.$refs["dataForm"].resetFields();
      }
      this.dataForm = {
        supplierName: "",
        supplierCode: "",
        conpanyPersion: "",
        contactPersion: "",
        phone: "",
        openBank: "",
        bankCode: "",
        dutyParagraph: "",
        registerAddress:"",
        qualityValidity:"",
        sealStyle:"",
        billsStyle:"",
      }
    },
    // 显示编辑界面
    handleEdit: function(index,params) {
      this.dialogVisible = true;
      this.operation = false;
      this.dataForm = Object.assign(
        {},
        {
        id: params.id,
        supplierName: params.supplierName,
        supplierCode: params.supplierCode,
        conpanyPersion: params.conpanyPersion,
        contactPersion: params.contactPersion,
        phone: params.phone,
        openBank: params.openBank,
        bankCode: params.bankCode,
        dutyParagraph: params.dutyParagraph,
        registerAddress:params.registerAddress,
        // commerceState:params.commerceState,
        qualityValidity:params.qualityValidity,
        sealStyle:params.sealStyle == "1"?true: false,
        billsStyle:params.billsStyle == "1"? true: false,
        }
      );
    },
    // 编辑
    submitForm: function() {
      this.$refs.dataForm.validate(valid => {
        this.dataForm.sealStyle = this.dataForm.sealStyle?'1':'0'
        this.dataForm.billsStyle = this.dataForm.billsStyle?'1':'0'
        if (valid) {
          this.editLoading = true;
          let params = Object.assign({}, this.dataForm);
          this.$api.zsj.addZsj({
              url: "/scm/scmZsjSupplier/v1/save",
              data: params
            }).then(res => {
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
    findPage(query = []) {
      this.loading = true;
      this.$api.zsj
        .getZsj({
          url: "/scm/scmZsjSupplier/v1/list",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys: [].concat(query),
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
          this.loading = false
        })
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
    // 筛选查询函数
    inquire(){
      let query = [];
      let searchForm = Object.assign({},this.searchForm);
        for(var key in searchForm) {
          if(searchForm[key] !== '') {
            query.push({
              hasInitValue: false,
              operation: "LIKE",
              property: key,
              relation: "AND",
              value: searchForm[key]
            });
          }
        }
        this.findPage(query);
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
.el-form{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  .el-form-item{
    width: 33%;
  }
}
.searchHeader{
  padding: 5px;
  background: #f3f3f3;
  div:first-child{
    height: 28px;
    line-height: 28px;
  }
  b{
    font-size: 24px;
  }
  i{
    margin-right: 5px;
    margin-left: 5px;
  }
}
.searchBox{
  padding-top: 10px;
  border: 1px solid #f4f4f4;
  margin-bottom: 10px;
}
.addContent{
  border: 1px solid #f4f4f4;
  border-radius: 3px;
  margin-bottom: 10px;
  .addHead{
    text-align: center;
    width: 100%;
    height: 50px;
    line-height: 50px;
    font-weight: 600;
  }
}
</style>
