<template>
    <!-- <div>{{msg}}</div> -->
    <div>
      <transition name="el-fade-in">
      
      <!-- 表格和搜索表格 -->
      <div v-show="isIndex">
       <!--表格栏-->
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
            <el-col :span="4" :offset="8"><b>商业首营审批</b></el-col>
            <el-col :span="5" :offset="6">
              <approval-btn :taskId="taskId" @handleSuccess="approvalSuccess"></approval-btn>
            </el-col>
          </el-row>
          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>基础信息</el-col>
          </el-row>
          <el-row class="searchBox">
           <el-form :model="saveData.scmZsjCommerce" label-width="130px" :rules="dataFormRules" ref="baseForm" size="small">
              <el-form-item label="ID" prop="id" v-if="false">
                <el-input v-model="saveData.scmZsjCommerce.id" :disabled="true" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="商业名称" prop="commerceName1">
                <!-- <el-input v-model="saveData.scmZsjCommerce.commerceName" auto-complete="off"></el-input> -->
                <el-autocomplete style="width: 100%"
                  class="inline-input"
                  v-model="saveData.scmZsjCommerce.commerceName"
                  :fetch-suggestions="querySearch"
                  placeholder="请选择或输入商业名称"
                  @select="handleSelect"
                ></el-autocomplete>
              </el-form-item>
              <el-form-item label="商业编号" prop="commerceCode">
                <el-input :disabled="baseEdit"  v-model="saveData.scmZsjCommerce.commerceCode" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="企业类别" prop="commerceState">
                <el-select :disabled="baseEdit" v-model="saveData.scmZsjCommerce.commerceState" placeholder="请选择企业类别">
                    <el-option value='0' label="药品经营企业"></el-option>
                    <el-option value="1" label="医疗机构"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="企业负责人" prop="conpanyPersion">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjCommerce.conpanyPersion" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="联系人" prop="contactPersion">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjCommerce.contactPersion" auto-complete="off" ></el-input>
              </el-form-item>
              <el-form-item label="联系电话" prop="phone">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjCommerce.phone" auto-complete="off"></el-input>
              </el-form-item>
              
              <el-form-item label="开户行" prop="openBank">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjCommerce.openBank" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="账号" prop="bankCode">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjCommerce.bankCode" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="税号" prop="dutyParagraph">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjCommerce.dutyParagraph" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="注册地址" prop="registerAddress">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjCommerce.registerAddress" auto-complete="off"></el-input>
              </el-form-item>
              
              <el-form-item label="商务分区" prop="businessDivisionId">
                <el-select :disabled="baseEdit" @change="changeDivisionFirst" v-model="saveData.scmZsjCommerce.businessDivisionId" placeholder="请选择商品分区">
                    <el-option 
                    v-for="divisionOption in divisionList"
                    :label="divisionOption.businessDivision" 
                    :value="divisionOption.id" 
                    :key="divisionOption.id">
                    </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="省区" prop="provinceId">
                <el-select :disabled="baseEdit" v-model="saveData.scmZsjCommerce.provinceId" placeholder="请选择省区">
                    <el-option 
                    v-for="provinceOption in provinceList"
                    :label="provinceOption.province" 
                    :value="provinceOption.id" 
                    :key="provinceOption.id">
                    </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="商业级别" prop="commerceLevel">
                <el-select :disabled="baseEdit" @change="changeLevel" v-model="saveData.scmZsjCommerce.commerceLevel" placeholder="请选择商业级别">
                    <el-option 
                    v-for="commercelevelOption in commercelevellist"
                    :label="commercelevelOption.commerceLevel" 
                    :value="commercelevelOption.commerceLevel" 
                    :key="commercelevelOption.id">
                    </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="职能类型" prop="functionType">
                <el-select :disabled="baseEdit" v-model="saveData.scmZsjCommerce.functionType" placeholder="请选择职能类型">
                    <el-option 
                    v-for="functionTypeOption in functionTypeList"
                    :label="functionTypeOption.functionType" 
                    :value="functionTypeOption.functionType" 
                    :key="functionTypeOption.id">
                    </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="药品质量保证协议有效期至" prop="qualityValidity">
                <el-date-picker :disabled="baseEdit" v-model="saveData.scmZsjCommerce.qualityValidity" type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
              </el-form-item>
              <!-- <el-form-item label="" prop="">
                <el-date-picker v-model="dataForm.qualityValidity" type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
              </el-form-item> -->
              <el-form-item label="" prop="sealStyle">
                <el-checkbox-group v-model="saveData.scmZsjCommerce.sealStyle" style='float: left'>
                  <el-checkbox :disabled="baseEdit" label="相关印章印模样式" name="sealStyle"></el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="" prop="billsStyle">
                <el-checkbox-group v-model="saveData.scmZsjCommerce.billsStyle" style='float: left'>
                  <el-checkbox :disabled="baseEdit" label="随货同行单(票)样式" name="billsStyle"></el-checkbox>            
                </el-checkbox-group>
              </el-form-item>
            </el-form>    
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>许可证信息</el-col>
          </el-row>
          <el-row class="searchBox">
            <div>
              <el-form :model="saveData.scmZsjCommerceLicenceList[0]" label-width="130px" :rules="dataFormRules" ref="dataForm" size="small">
              <el-form-item label="许可证号" prop="licenseKey">
                <el-input v-model="saveData.scmZsjCommerceLicenceList[0].licenseKey" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="有效期至" prop="validity">
                <el-date-picker v-model="saveData.scmZsjCommerceLicenceList[0].validity" type="date" style="width: 100%;" auto-complete="off"></el-date-picker>
              </el-form-item>
              <el-form-item label="生产/经营范围" prop="managementScope">
                <el-select  v-model="saveData.scmZsjCommerceLicenceList[0].managementScope" multiple placeholder="请选择职能类型">
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

          <el-row v-if="saveData.scmZsjCommerce.commerceState == '1'" class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>医疗许可证信息</el-col>
          </el-row>
          <el-row v-if="saveData.scmZsjCommerce.commerceState == '1'" class="searchBox">
           <el-form :model="saveData.scmZsjTreatLicenceList[0]" label-width="130px" :rules="dataFormRules" ref="dataForm" size="small">
              <el-form-item label="医疗许可证号" prop="treatLicence">
                <el-input v-model="saveData.scmZsjTreatLicenceList[0].treatLicence" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="有效期至" prop="validity">
                <el-date-picker v-model="saveData.scmZsjTreatLicenceList[0].validity" style="width: 100%;" type="date" auto-complete="off"></el-date-picker>
              </el-form-item>
            </el-form>    
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>营业执照信息</el-col>
          </el-row>
          <el-row class="searchBox">
           <el-form :model="saveData.scmZsjCommerceBusinessList[0]" label-width="130px" :rules="dataFormRules" ref="dataForm" size="small">
              <el-form-item label="注册号" prop="register">
                <el-input v-model="saveData.scmZsjCommerceBusinessList[0].register" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="营业执照有效期至" prop="licenseValidity">
                <el-date-picker v-model="saveData.scmZsjCommerceBusinessList[0].licenseValidity" style="width: 100%;" auto-complete="off"></el-date-picker>
              </el-form-item>
              <el-form-item label="法定代表人" prop="legalPersion">
                <el-input v-model="saveData.scmZsjCommerceBusinessList[0].legalPersion" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="注册资本" prop="registerPrice">
                <el-input v-model="saveData.scmZsjCommerceBusinessList[0].registerPrice" auto-complete="off">
                  <template slot="append">万元</template>
                </el-input>
              </el-form-item>
              <el-form-item label="企业类型" prop="commerceName">
                <el-input v-model="saveData.scmZsjCommerceBusinessList[0].commerceType" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="年度报表有效期至" prop="commerceCode">
                <el-date-picker v-model="saveData.scmZsjCommerceBusinessList[0].yearReportValidity" style="width: 100%;" auto-complete="off"></el-date-picker>
              </el-form-item>
            </el-form>    
          </el-row>

          <el-row v-if="saveData.scmZsjCommerce.commerceState == '0'" class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>GSP证书信息</el-col>
          </el-row>
          <el-row v-if="saveData.scmZsjCommerce.commerceState == '0'" class="searchBox">
           <el-form :model="saveData.scmZsjGSPList[0]" label-width="130px" :rules="dataFormRules" ref="dataForm" size="small">
              <el-form-item label="GSP证书" prop="gsp">
                <el-input v-model="saveData.scmZsjGSPList[0].gsp" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="GSP证书有效期至" prop="validity">
                <el-date-picker v-model="saveData.scmZsjGSPList[0].validity" auto-complete="off"></el-date-picker>
              </el-form-item>
            </el-form>    
          </el-row>


          <!-- <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>GMP证书信息</el-col>
            <el-col :span="1" :offset="20">
              <el-button  :size="size" type="primary"><i class="el-icon-plus"></i></el-button>
            </el-col>
          </el-row>
          <el-row class="searchBox" style="padding-left:10px;padding-right:10px;">
            <div class="addContent">
              <p class="addHead">GMP证书1</p>
              <el-form :model="dataForm" label-width="130px" :rules="dataFormRules" ref="dataForm" size="small">
                <el-form-item label="GMP证书" prop="commerceName">
                  <el-input v-model="dataForm.commerceName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="GMP证书有效期至" prop="commerceCode">
                  <el-input v-model="dataForm.commerceCode" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="GMP证书认证范围" prop="commerceCode">
                  <el-input v-model="dataForm.commerceCode" auto-complete="off"></el-input>
                </el-form-item>
              </el-form> 
            </div>
          </el-row> -->


          <el-row  class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>委托书信息</el-col>
            <el-col :span="1" :offset="19">
              <el-button  :size="size" type="danger" @click="delEntruseBookList"><i class="el-icon-minus"></i></el-button>
            </el-col>
            <el-col :span="1" >
              <el-button  :size="size" type="primary" @click="addEntruseBookList"><i class="el-icon-plus"></i></el-button>
            </el-col>
          </el-row>
          <el-row  class="searchBox" style="padding-left:10px;padding-right:10px;">
            <div class="addContent" 
            v-for="(item, index) in saveData.scmZsjCommerceEntruseBookList" :key="item.id">
              <p class="addHead">委托书{{index+1}}</p>
              <el-form :model="item" label-width="130px" :rules="dataFormRules" ref="dataForm" size="small">
                <el-form-item label="委托书编号" prop="entrustCode">
                  <el-input v-model="item.entrustCode" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="委托书姓名" prop="entrustName">
                  <el-input v-model="item.entrustName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="委托书有效期至" prop="entrustValidity">
                  <el-date-picker v-model="item.entrustValidity" auto-complete="off" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="身份证号" prop="idnumber">
                  <el-input v-model="item.idnumber" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="身份证有效期至" prop="idvalidity">
                  <el-date-picker v-model="item.idvalidity" auto-complete="off" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <!-- <el-form-item label="授权销售品种" prop="commerceCode">
                  <el-input v-model="item.commerceCode" auto-complete="off"></el-input>
                </el-form-item> -->
              </el-form> 
            </div>
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>收货地址</el-col>
          </el-row>
          <el-row class="searchBox">
              <!-- <ship-address @getShipAddress="getShipAddress"></ship-address> -->
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>附件信息</el-col>
          </el-row>
          <el-row class="searchBox">
            <el-row style="margin-bottom:10px;">
              <el-col :span="1.5" style="margin-right:5px;">
                <el-button  :size="size" type="primary">上传附件</el-button>
              </el-col>
            </el-row>
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
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>承诺书列表</el-col>
          </el-row>
          <el-row class="searchBox" style="padding-top: 0px;">
              <el-table :data="promiseBookList" ref="promiseTable"  highlight-current-row  
                  v-loading="loading"   element-loading-text="加载中" border stripe  
                  :show-overflow-tooltip="true" max-height="500" size="mini" align="left" style="width:100%;" >
              <el-table-column type="selection" width="40"></el-table-column>
              <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
              <el-table-column prop="promiseBook" label="承诺书名称" align="center">
              </el-table-column>
              <el-table-column label="有效日期" prop="validity" fixed="right" header-align="center" align="center">
                <template slot-scope="scope">
                    <el-date-picker type="date" v-model="promiseBookList[scope.$index].validity" placeholder="选择有效日期" style="width: 100%;"></el-date-picker>
                </template>
              </el-table-column>
            </el-table>
          </el-row>
      </div>
      </transition>
    </div>
</template>

<script>
import approvalBtn  from "@/components/approval/ApprovalBtn"
import storage from '@/utils/storage'

let timer;
export default {
  name: "commercefirst",
  components: {
    'approval-btn':approvalBtn
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
      dataFormRules: {
        // commerceName: [
        //   { required: true, message: "请输入客户名称", trigger: "blur" },
        //   { max: 20, message: "长度不能超过20", trigger: "blur" }
        // ],
        // commerceState: [
        //   { required: true, message: "请选择企业类别", trigger: "change" },
        // ],
        // conpanyPersion: [
        //   { required: true, message: "请输入企业负责人", trigger: "blur" },
        //   { max: 20, message: "长度不能超过20", trigger: "blur" }
        // ],
        // contactPersion: [
        //   {
        //     required: true,
        //     message: "请输入联系人",
        //     trigger: "blur"
        //   },
        //   { max: 20, message: "长度不能超过20", trigger: "blur" }
        // ],
        // phone: [
        //   { required: true, message: "请输入联系电话", trigger: "blur" },
        //   { max: 11, message: "长度不能超过11", trigger: "blur" }
        // ],
        // openBank: [
        //   { required: true, message: "请输入开户行", trigger: "blur" },
        //   { max: 20, message: "长度不能超过11", trigger: "blur" }
        // ],
        // bankCode: [
        //   { required: true, message: "请输入账号", trigger: "change" },
        //   { max: 30, message: "长度不能超过30", trigger: "blur" }
        // ],
        // dutyParagraph: [
        //   { required: true, message: "请输入税号", trigger: "blur" },
        //   { max: 100, message: "长度不能超过100", trigger: "blur" }
        // ],
        // registerAddress: [
        //   { required: true, message: "请输入注册地址", trigger: "blur" },
        //   { max: 100, message: "长度不能超过100", trigger: "blur" }
        // ],
        // qualityValidity: [
        //   { required: true, message: "请选择日期", trigger: "blur" },
        // ],
        // businessDivisionId: [
        //   { required: true, message: "请选择商务分区", trigger: "change" },
        // ],
        // provinceId: [
        //   { required: true, message: "请选择省区", trigger: "change" },
        // ],
        
      },
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
      baseEdit: false,
      provinceList: [], //省区下拉框
      divisionList: [], //商务分区下拉框
      commercelevellist: [],//商业级别
      functionTypeList: [],//职能类型
      agentTypeList: [],//剂型数据列表
      promiseBookList:[],//承诺书表格数据
      approvalList:[],//审批返回数据列表
      taskId: '',//任务id
      // promiseSelect:'',
      saveData:{//上传的数据
        id:"",  
        approvalId: "",
        approvalState: "",
        commerceId: "",
        ownerId: "", 
        scmZsjCommerce: {
          acceptState: "",
          acceptStateStr: "",
          bankCode: "",
          billsStyle: "",
          businessDivision: "",
          businessDivisionId: "",
          commerceCode: "",
          commerceLevel: "",
          commerceName: "",
          commerceNameStr: "",
          commerceState: "",
          commerceStateStr: "",
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
          functionType: "",
          id: "",
          legalPersion: "",
          licenseKey: "",
          licenseValidity: "",
          managementScope: "",
          openBank: "",
          phone: "",
          province: "",
          provinceId: "",
          qualityValidity: "",
          register: "",
          registerAddress: "",
          registerPrice: "",
          sealStyle: "",
          updateBy: "",
          updateName: "",
          updateTime: "",
          validity: ""
        },
        scmZsjCommerceBusinessList: [//营业执照信息
          {
            commerceType: "",
            id: "",
            legalPersion: "",
            licenseValidity: "",
            register: "",
            registerPrice: "",
            yearReportValidity: ""
          }
        ],
        scmZsjCommerceEntruseBookList: [//委托书
          {
            entrustCode: "",
            entrustName: "",
            entrustValidity: "",
            id: "",
            idnumber: "",
            idvalidity: "",
            // scmZsjCommerceAccreditList: [//产品名称
            //   {
            //     commonName: "",
            //     entrustId: "",//委托书id
            //     id: "",
            //     productCode: "",
            //     productName: "",
            //   }
            // ],
          }
        ],
        scmZsjCommerceLicenceList: [//许可证生产经营范围
          {
            id: "",
            licenseKey: "",
            managementScope: "",
            validity: ""
          }
        ],
        scmZsjCommercePromiseBookList: [//承诺书
          {
            promiseBookId: "",
            id:"",
            scmZdPromiseBook: {
              id: "",
              promiseBook: "",
            },
            validity: ""
          }
        ],
        scmZsjCommerceShipAddressList: [//收货地址
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
        scmZsjCommerceWarehouseList: [//生产/仓库地址
          {
            address: "",
            id: "",
          }
        ],
        scmZsjGSPList: [//GSP证书
          {
            id:"",
            gsp: "",
            validity: ""
          }
        ],
        scmZsjTreatLicenceList: [//医疗许可证信息
          {
            treatLicence: "",
            validity: ""
          }
        ],
      }
    };
  },
  mounted() {
    this.findPage();
    // this.getDivision();
    // this.getcommercelevel();
    // this.getCommerList();
    // this.getAgentType();
    // this.getPromiseBook();
    this.saveData.ownerId = storage.get('ownerId')
  },
  watch:{
    'saveData.scmZsjCommerce.commerceName' : 'watchCommerceName'
  },
  methods: {
    watchCommerceName(){//监听商业名称的变化对输入框状态做出改变
      clearTimeout(timer);
      timer = setTimeout(()=>{
        let getStateList = this.resultList.reduce((final, item, index, arr) => {
          if(item.commerceName == this.saveData.scmZsjCommerce.commerceName){
            final.push(item)
          }
          return final
        },[])
        if(getStateList.length>0){
          this.baseEdit = true;
        }else{
          this.baseEdit = false;
          this.$refs.baseForm.resetFields();
        }
      },1000);
    },
    querySearch: function(queryString, cb){
      var result = this.resultList;
      cb(result);
    },
    // 显示编辑界面
    handleEdit: function(index,params) {
      this.dialogVisible = true;
      this.operation = false;
      this.searchForm = Object.assign(
        {},
        {
        id: params.id,
        // fileCode: params.fileCode,
        commerceName: params.commerceName,
        commerceCode: params.commerceCode,
        conpanyPersion: params.conpanyPersion,
        contactPersion: params.contactPersion,
        phone: params.phone,
        openBank: params.openBank,
        bankCode: params.bankCode,
        dutyParagraph: params.dutyParagraph,
        registerAddress:params.registerAddress,
        commerceType:params.commerceType,
        businessDivisionId:params.businessDivisionId,
        provinceId:params.provinceId,
        commerceLevel:params.commerceLevel,
        functionType:params.functionType,
        qualityValidity:params.qualityValidity,
        sealStyle:params.sealStyle == "1"?true: false,
        billsStyle:params.billsStyle == "1"? true: false,
        }
      );
    },
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
                    value: "cght"
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
    getAgentType(){
        this.$api.common
        .getAgentType()
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.agentTypeList = res.rows
        });
    },
    getCommerList(){//获取首营下拉列表
      this.$api.common
        .getcommercefirstList()
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.resultList = res.reduce((final, item, index, arr)=>{
            item.value = item.commerceNameStr;
            final.push(item);
            return final;
          },[]);
        });
    },
    addEntruseBookList(){//添加委託書
      this.saveData.scmZsjCommerceEntruseBookList.push({
            entrustCode: "",
            entrustName: "",
            entrustValidity: "",
            id: "",
            idnumber: "",
            idvalidity: "",
      })
    },
    delEntruseBookList(){//刪除委託書
      if(this.saveData.scmZsjCommerceEntruseBookList.length>1){
        this.saveData.scmZsjCommerceEntruseBookList.pop();
      }else{
        return;
      }
      
    },
    handleSelect(data){//带输入建议表单的select事件
      this.saveData.scmZsjCommerce = Object.assign({},data)
      // this.saveData.scmZsjCommerce.commerceName = this.saveData.scmZsjCommerce.commerceName.split('|')[1]
      this.saveData.scmZsjCommerce.sealStyle = this.saveData.scmZsjCommerce.sealStyle? true:false
      this.saveData.scmZsjCommerce.billsStyle = this.saveData.scmZsjCommerce.billsStyle? true:false
      this.saveData.commerceId = data.id;
      this.baseEdit = true;
    },
    getDivision(){//获取商务分区
      this.$api.common
        .getDivision()
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.divisionList = res;
        });
    },
    getPromiseBook(){//获取承诺书
      this.$api.common
        .getPromiseBook()
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          for(let i of res.rows){
            i.validity = ''
          }
          this.promiseBookList = res.rows;
        });
    },
    getcommercelevel() {
      this.$api.common
        .getcommercelevellist()
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.commercelevellist = res.value;
        });
    },
    getAddress(data){//从列表中去取得数据保存
      this.saveData.scmZsjCommerceWarehouseList = data.allData
    },
    getShipAddress(data){//从列表中获取收货地址
      this.saveData.scmZsjCommerceShipAddressList = data.allData
    },
    changeLevel(){
      this.saveData.scmZsjCommerce.functionType = ''
      let level = this.saveData.scmZsjCommerce.commerceLevel;
      this.functionTypeList = this.commercelevellist.reduce((final, item, index, arr)=>{
        if(item.commerceLevel == level){
          final = item.scmZdFunctionTypeList
        }
        return final
      },[])
    },
    changeDivision(){
      this.searchForm.provinceId= '';
      let divisionId = this.searchForm.businessDivisionId;
      this.provinceList = this.divisionList.reduce((final, item, index, arr)=>{
        if(item.id == divisionId){
          final = item.provinceList
        }
        return final
      },[])
    },
    changeDivisionFirst(){
      this.saveData.scmZsjCommerce.provinceId = '';
      let divisionIdFirst = this.saveData.scmZsjCommerce.businessDivisionId;
      this.provinceList = this.divisionList.reduce((final, item, index, arr)=>{
        if(item.id == divisionIdFirst){
          final = item.provinceList
        }
        return final
      },[])
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
    approval(index, row){
      this.taskId = row.id;
      this.$router.push({path: '/approval/ContractProductAppleSp',query: {htid: row.procInstId, id: row.id,taskid: row.taskId}});
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
.searchHeader {
	
}
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
