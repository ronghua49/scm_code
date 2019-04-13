<template>
    <!-- <div>{{msg}}</div> -->
    <div>
      <transition name="el-fade-in">
      
      <!-- 表格和搜索表格 -->
      <div v-show="isIndex">
       <!--表格栏-->
       <el-row class="searchHeader" >
            <el-col :span="3" style="text-align:left;"><i class="el-icon-tickets"></i>数据列表</el-col>
          </el-row>
        <el-table :data="pageResult.rows" highlight-current-row  
              v-loading="loading"   element-loading-text="加载中" border stripe
              :show-overflow-tooltip="true" max-height="500" size="mini" align="left" style="width:100%;" >
          <!-- <el-table-column type="selection" width="40"></el-table-column> -->
          <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
          <el-table-column v-for="column in columns" header-align="center" align="center"
            :prop="column.prop" :label="column.label" 
            :key="column.prop"  :min-width="column.minWidth" :formatter="column.formatter"
            :sortable="column.sortable==null?true:column.sortable">
          </el-table-column>
          <el-table-column label="操作" width="100" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button icon="el-icon-edit" label=""  @click="approval(scope.$index, scope.row)" :size="size" type="primary">审批</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页栏-->
        <div class="toolbar" style="padding:10px;">
          <el-pagination  @current-change="refreshPageRequest" @size-change="refreshPageSizeRequest" background
            :current-page="pageRequest.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest.pageSize" layout="sizes, total, prev, pager, next" :total="pageResult.total" style="float:right;">
          </el-pagination>
        </div>
      </div>











      <!-- 新增首营 -->
    </transition>
    <transition name="el-fade-in">

      <div v-show="!isIndex">
          <el-row class="searchHeader">
            <el-col :span="1">
              <el-button icon="el-icon-back"  :size="size" type="primary" @click="isIndex=!isIndex">返回</el-button>
            </el-col>
            <el-col :span="4" :offset="8"><b>供应商首营审批</b></el-col>
            <el-col :span="4" :offset="7" style="padding-left:10px">
              <approval-btn :taskId="taskId" @handleSuccess="approvalSuccess"></approval-btn>
            </el-col>
          </el-row>
          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>基础信息</el-col>
          </el-row>
          <el-row class="searchBox">
           <el-form :model="saveData.scmZsjSupplier" label-width="140px"  ref="baseForm" size="small">
              <el-form-item label="ID" prop="id" v-if="false">
                <el-input :disabled="baseEdit"  v-model="saveData.scmZsjSupplier.id"  auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="供应商名称"  prop="supplierName1" >
                <!-- <el-input :disabled="baseEdit"  v-model="saveData.scmZsjSupplier.supplierName" auto-complete="off"></el-input> -->
                <el-autocomplete :disabled="baseEdit" style="width: 100%"
                  class="inline-input"
                  v-model="saveData.scmZsjSupplier.supplierName"
                  placeholder="请选择或输入供应商名称"
                ></el-autocomplete>
              </el-form-item>
              <el-form-item label="供应商编号" prop="supplierCode">
                <el-input :disabled="baseEdit"    v-model="saveData.scmZsjSupplier.supplierCode" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="企业负责人" prop="conpanyPersion">
                <el-input :disabled="baseEdit"   v-model="saveData.scmZsjSupplier.conpanyPersion" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="联系人" prop="contactPersion">
                <el-input :disabled="baseEdit"   v-model="saveData.scmZsjSupplier.contactPersion" auto-complete="off" ></el-input>
              </el-form-item>
              <el-form-item label="联系电话" prop="phone">
                <el-input :disabled="baseEdit"   v-model="saveData.scmZsjSupplier.phone" auto-complete="off"></el-input>
              </el-form-item>
              
              <el-form-item label="开户行" prop="openBank">
                <el-input :disabled="baseEdit"   v-model="saveData.scmZsjSupplier.openBank" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="账号" prop="bankCode">
                <el-input :disabled="baseEdit"   v-model="saveData.scmZsjSupplier.bankCode" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="税号" prop="dutyParagraph">
                <el-input :disabled="baseEdit"   v-model="saveData.scmZsjSupplier.dutyParagraph" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="注册地址" prop="registerAddress">
                <el-input :disabled="baseEdit"   v-model="saveData.scmZsjSupplier.registerAddress" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="药品质量保证协议有效期至" prop="qualityValidity">
                <el-date-picker :disabled="baseEdit"  value-format="yyyy-MM-dd HH:mm:ss"  v-model="saveData.scmZsjSupplier.qualityValidity" type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
              </el-form-item>
              <!-- <el-form-item label="" prop="">
                <el-date-picker :disabled="baseEdit"  value-format="yyyy-MM-dd HH:mm:ss" v-model="dataForm.qualityValidity" type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
              </el-form-item> -->
              <el-form-item label="" prop="sealStyle">
                <el-checkbox-group v-model="saveData.scmZsjSupplier.sealStyle" style='float: left'>
                  <el-checkbox  label="相关印章印模样式" name="sealStyle"></el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="" prop="billsStyle">
                <el-checkbox-group v-model="saveData.scmZsjSupplier.billsStyle" style='float: left'>
                  <el-checkbox  label="随货同行单(票)样式" name="billsStyle"></el-checkbox>            
                </el-checkbox-group>
              </el-form-item>
            </el-form>    
          </el-row>


          


          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>生产/仓库地址</el-col>
          </el-row>
          <el-row class="searchBox">
            <ware-house :showOperation="false" :list="saveData.scmZsjSupplierWarehouseList"></ware-house>
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>许可证信息</el-col>
          </el-row>
          <el-row class="searchBox">
            <div>
              <el-form :model="saveData.scmZsjSupplierLicenceList[0]" label-width="140px"  ref="dataForm1" size="small">
              <el-form-item label="许可证号" prop="licenseKey">
                <el-input :disabled="baseEdit"  v-model="saveData.scmZsjSupplierLicenceList[0].licenseKey" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="有效期至" prop="validity">
                <el-date-picker :disabled="baseEdit"  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjSupplierLicenceList[0].validity" type="date" style="width: 100%;" auto-complete="off"></el-date-picker>
              </el-form-item>
              <el-form-item label="" prop="">
                <!-- <el-date-picker :disabled="baseEdit"  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjSupplierLicenceList[0].validity" type="date" style="width: 100%;" auto-complete="off"></el-date-picker> -->
              </el-form-item>
              <el-form-item label="生产/经营范围" prop="managementScope" style="width:64%">
                <el-select :disabled="baseEdit"  v-model="saveData.scmZsjSupplierLicenceList[0].managementScope" multiple  placeholder="请选择生产/经营范围">
                    <el-option 
                    v-for="agentTypeOption in agentTypeList"
                    :label="agentTypeOption.agentType" 
                    :value="agentTypeOption.agentType" 
                    :key="agentTypeOption.id">
                    </el-option>
                </el-select>
              </el-form-item>
            </el-form>
            </div>
               
          </el-row>


          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>营业执照信息</el-col>
          </el-row>
          <el-row class="searchBox">
           <el-form :model="saveData.scmZsjSupplierBusinessList[0]" label-width="140px"  ref="dataForm3" size="small">
              <el-form-item label="注册号" prop="registerCode">
                <el-input :disabled="baseEdit"  v-model="saveData.scmZsjSupplierBusinessList[0].registerCode" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="营业执照有效期至" prop="licenseValidity">
                <el-date-picker :disabled="baseEdit"  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjSupplierBusinessList[0].licenseValidity" style="width: 100%;" auto-complete="off"></el-date-picker>
              </el-form-item>
              <el-form-item label="法定代表人" prop="legalPersion">
                <el-input :disabled="baseEdit"  v-model="saveData.scmZsjSupplierBusinessList[0].legalPersion" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="注册资本" prop="registerPrice">
                <el-input :disabled="baseEdit"  v-model="saveData.scmZsjSupplierBusinessList[0].registerPrice" auto-complete="off">
                  <template slot="append">万元</template>
                </el-input>
              </el-form-item>
              <el-form-item label="企业类型" prop="commerceType">
                <el-input :disabled="baseEdit"  v-model="saveData.scmZsjSupplierBusinessList[0].commerceType" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="年度报表有效期至" prop="yearReportValidity">
                <el-date-picker :disabled="baseEdit"  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjSupplierBusinessList[0].yearReportValidity" style="width: 100%;" auto-complete="off"></el-date-picker>
              </el-form-item>
            </el-form>    
          </el-row>


          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>GMP证书信息</el-col>
          </el-row>
          <el-row class="searchBox" style="padding-left:10px;padding-right:10px;">
            <div class="addContent" 
            v-for="(item, index) in saveData.scmZsjGMPList" :key="index">
              <p class="addHead">GMP证书{{index+1}}</p>
              <el-form :model="item" label-width="140px"  :ref="`dataFormGMP${index}`" size="small">
                <el-form-item label="GMP证书" prop="gmpcertificate" :rules='[
                  { required: true, message: "请输入GMP证书", trigger: "blur" },
                  { max: 20, message: "长度不能超过20", trigger: "blur" }
                ]'>
                  <el-input :disabled="baseEdit"  v-model="item.gmpcertificate" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="GMP证书有效期至" prop="validity" :rules=' [
                    { required: true, message: "请选择GMP证书有效期", trigger: "blur" },
                  ]'>
                  <el-date-picker :disabled="baseEdit"  value-format="yyyy-MM-dd HH:mm:ss" v-model="item.validity" auto-complete="off" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="GMP证书认证范围" prop="certificationScope" :rules='[
                  { required: true, message: "请输入GMP证书", trigger: "blur" },
                  { max: 50, message: "长度不能超过50", trigger: "blur" }
                ]'>
                  <el-input :disabled="baseEdit"  v-model="item.certificationScope" auto-complete="off"></el-input>
                </el-form-item>
              </el-form> 
            </div>
          </el-row>


          <el-row  class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>委托书信息</el-col>
          </el-row>
          <el-row  class="searchBox" style="padding-left:10px;padding-right:10px;">
            <div class="addContent" 
            v-for="(item, index) in saveData.scmZsjSupplierEntruseBookList" :key="index">
              <p class="addHead">委托书{{index+1}}</p>
              <el-form :model="item" label-width="140px"  :ref="`dataFormWt${index}`" size="small">
                <el-form-item label="委托书编号" prop="entrustCode" :rules='[
                  { required: true, message: "请输入委托书编号", trigger: "blur" },
                  { max: 20, message: "长度不能超过20", trigger: "blur" }
                ]'>
                  <el-input :disabled="baseEdit"  v-model="item.entrustCode" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="委托书名称" prop="entrustName" :rules='[
                    { required: true, message: "请输入委托书名称", trigger: "blur" },
                    { max: 20, message: "长度不能超过20", trigger: "blur" }
                  ]'>
                  <el-input :disabled="baseEdit"  v-model="item.entrustName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="委托书有效期至" prop="entrustValidity" :rules=' [
                    { required: true, message: "请选择委托书有效期", trigger: "blur" },
                  ]'>
                  <el-date-picker :disabled="baseEdit"  value-format="yyyy-MM-dd HH:mm:ss" v-model="item.entrustValidity" auto-complete="off" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="身份证号" prop="idNumber" :rules='[
                    { required: true, message: "请输入身份证号", trigger: "blur" },
                    { max: 20, message: "长度不能超过20", trigger: "blur" }
                  ]'>
                  <el-input :disabled="baseEdit"  v-model="item.idNumber" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="身份证有效期至" prop="IDValidity" :rules='[
                    { required: true, message: "请选择身份证有效期", trigger: "blur" },
                  ]'>
                  <el-date-picker :disabled="baseEdit"  value-format="yyyy-MM-dd HH:mm:ss" v-model="item.IDValidity" auto-complete="off" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-form> 
            </div>
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>收货地址</el-col>
          </el-row>
          <el-row class="searchBox">
              <ship-address  :showOperation="false" :list="saveData.scmZsjSupplierShipAddressList"></ship-address>
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>附件信息</el-col>
          </el-row>
          <el-row class="searchBox">
              <el-table  highlight-current-row  
                  v-loading="loading"   element-loading-text="加载中" border stripe
                  :show-overflow-tooltip="true" max-height="500" size="mini" align="left" style="width:100%;" >
              <el-table-column type="selection" width="40"></el-table-column>
              <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
              <el-table-column   label="附件名称" align="center"></el-table-column>
              <el-table-column   label="上传时间" align="center"></el-table-column>
              <el-table-column label="操作" width="340" fixed="right" header-align="center" align="center">
                <template slot-scope="scope">
                  <el-button icon="el-icon-view" label=""  :size="size" type="primary"  >查看</el-button>
                  <el-button icon="el-icon-edit" label=""  :size="size" type="danger"  >删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>流程图</el-col>
          </el-row>
          <el-row class="searchBox">
              <approval-flow :flowData="flowData"></approval-flow>
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>审批历史</el-col>
          </el-row>
          <el-row class="searchBox">
              <approval-history :instId="instId"></approval-history>
          </el-row>
      </div>
      </transition>
    </div>
</template>

<script>
import approvalBtn  from "@/components/approval/ApprovalBtn"
import approvalFlow  from "@/components/approval/ApprovalFlow"
import approvalHistory  from "@/components/approval/ApprovalHistory"
import wareHouse from "@/components/zsj/Warehouse"
import shipAddress from "@/components/zsj/ShipAddress"
import storage from '@/utils/storage'

let timer;
export default {
  name: "commercefirst",
  components: {
    'approval-btn':approvalBtn,
    'approval-flow':approvalFlow,
    'approval-history':approvalHistory,
    'ware-house': wareHouse,
    'ship-address':shipAddress
  },
  data() {
    return {
      size: "small",
      columns: [
        { prop: "subject", label: "任务标题",minWidth: 150 },
        { prop: "name", label: "任务名称" },
        { prop: "ownerName", label: "所属人" ,minWidth: 150},
        { prop: "assigneeName", label: "执行人",minWidth: 100},
        { prop: "status", label: "类型",minWidth: 110, formatter:(row, column, cellValue, index)=>{return cellValue=='NORMAL'?'普通':cellValue}},
        { prop: "createTime", label: "创建时间",minWidth: 100 },
        { prop: "", label: "到期时间",minWidth: 100 },
      ],
      // 新增编辑界面数据
      dataForm: {},
      resultList: [],//首营下拉列表数据
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      pageResult: {},
      searchForm: {
        commerceCode:'',
        commerceType:'',
        acceptState:'',
        provinceId:'',
        businessDivisionId:''
      },
      size: "mini",
      isIndex: true,
      loading: false,
      baseEdit: true,
      provinceList: [], //省区下拉框
      divisionList: [], //商务分区下拉框
      commercelevellist: [],//商业级别
      functionTypeList: [],//职能类型
      agentTypeList: [],//剂型数据列表
      promiseBookList:[],//承诺书表格数据
      approvalList:[],//审批返回数据列表
      taskId: '',//任务id
      instId:'',
      flowData: {},//流程图数据
      // promiseSelect:'',
      saveData:{//上传的数据
        id:"",  
        approvalId: "",
        approvalState: "",
        supplierId: "",
        ownerId: "", 
        scmZsjSupplier: {
            approvalStateStr: "",
            bankCode: "",
            billsStyle: "",
            company: "",
            conpanyPersion: "",
            contactPersion: "",
            createBy: "",
            createName: "",
            createOrgId: "",
            createOrgName: "",
            createTime: "",
            dutyParagraph: "",
            enterDate: "",
            enterPersion: "",
            fileCode: "",
            id: "",
            legalPersion: "",
            licenseKey: "",
            licenseValidity: "",
            managementScope: "",
            openBank: "",
            operatorDate: "",
            operatorName: "",
            phone: "",
            qualityValidity: "",
            register: "",
            registerAddress: "",
            registerPrice: "",
            sealStyle: "",
            supplierCode: "",
            supplierName: "",
            supplierNameStr: "",
            updateBy: "",
            updateName: "",
            updateTime: "",
            validity: ""
        },
        scmZsjSupplierBusinessList: [//营业执照信息
          {
            commerceType: "",
            id: "",
            legalPersion: "",
            licenseValidity: "",
            registerCode: "",
            registerPrice: "",
            yearReportValidity: ""
          }
        ],
        scmZsjSupplierEntruseBookList: [//委托书
          {
            entrustCode: "",
            entrustName: "",
            entrustValidity: "",
            id: "",
            idNumber: "",
            IDValidity: "",
          }
        ],
        scmZsjGMPList: [//GMP证书
          {
            certificationScope: "",
            validity: "",
            id: "",
            gmpcertificate: "",
          }
        ],
        scmZsjSupplierLicenceList: [//许可证生产经营范围
          {
            id: "",
            licenseKey: "",
            managementScope: "",
            validity: ""
          }
        ],
        scmZsjSupplierShipAddressList: [//收货地址
          {
            address: "",
            city: "",
            district: "",
            headPhone: "",
            // isDufault: "",
            phone: "",
            postcode: "",
            province: "",
            street: "",
            contactPersion: ""
          }
        ],
        scmZsjSupplierWarehouseList: [//生产/仓库地址
          {
            address: "",
            id: "",
          }
        ],
      }
    };
  },
  mounted() {
    this.findPage();
    this.saveData.ownerId = storage.get('ownerId')
  },
  methods: {

  
    findPage() {
      this.loading = true;
      this.$api.approval
        .post({
          url: "/runtime/task/v1/list",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys: [
                {
                    hasInitValue: false,
                    operation: "EQUAL",
                    property: "assigneeId",//登录人 id
                    relation: "AND",
                    value: storage.get('userId')
                },
                {
                    hasInitValue: false,
                    operation: "EQUAL",
                    property: "procDefKey",
                    relation: "AND",
                    value: "gyssy"
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
    getDetail(id){
      this.$api.zsj
        .get({
          url: "/scm/scmZsjSupplierFirst/v1/getSupplierFirstByApprovalId/"+id,
        })
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: res.message,
              type: "error",
              duration: 800
            });
            return;
          };
          this.saveData = Object.assign({},res.value);
        })
    },
    approval(index, row){
      this.isIndex = false;
      this.taskId = row.id;
      this.instId = row.procInstId;
      this.getDetail(row.procInstId);
      this.flowData = {
        taskId: row.taskId
      }
    },
    approvalSuccess(){
      this.findPage();
      this.isIndex = true;
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
  // justify-content: space-around;
  flex-wrap: wrap;
  .el-form-item{
    width: 32%;
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
