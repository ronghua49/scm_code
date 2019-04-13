<template>
    <!-- <div>{{msg}}</div> -->
    <div>
      <transition name="el-fade-in">
      
      <!-- 表格和搜索表格 -->
      <div v-show="isIndex">
        <el-row class="searchHeader">
          <el-col :span="2"><div>筛选查询</div></el-col>
          <el-col :span="22" style="text-align: right">
            <el-button icon="el-icon-search" :size="size" type="primary" @click="inquire">查询</el-button>
          </el-col>
        </el-row>
        <el-row class="searchBox">
          <el-form :model="searchForm" label-width="140px"  ref="searchForm" size="small">
            <el-form-item label="状态" prop="acceptState">
              <el-select v-model="searchForm.approvalState" clearable placeholder="请选择审批状态">
                  <el-option value='0' label="未提交"></el-option>
                  <el-option value="1" label="待审批"></el-option>
                  <el-option value="2" label="已经通过"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="供应商编号" prop="supplierCode">
              <el-input v-model="searchForm.supplierCode" clearable auto-complete="off"></el-input>
            </el-form-item>
          </el-form>
        </el-row>
        <!-- <el-row class="btnAdd">
          <el-col :xs="2" :sm="2" :md="2" :lg="2">
            
          </el-col>
        </el-row> -->
       <!--表格栏-->
       <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-tickets"></i>数据列表</el-col>
            <el-col :span="21" style="text-align:right;">
              <el-button icon="el-icon-circle-plus-outline" type="primary" size="mini" @click="handleAdd">首营申请</el-button>
            </el-col>
          </el-row>
        <el-table :data="pageResult.rows" highlight-current-row :cell-style="style"  
              v-loading="loading"   element-loading-text="加载中" border stripe
               max-height="500" size="mini" align="left" style="width:100%;" >
          <!-- <el-table-column type="selection" width="40"></el-table-column> -->
          <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
          <el-table-column v-for="column in columns" header-align="center" align="center"
            :prop="column.prop" :label="column.label" :show-overflow-tooltip="true"
            :key="column.prop"  :min-width="column.minWidth" :formatter="column.formatter"
            :sortable="column.sortable==null?true:column.sortable">
          </el-table-column>
          <el-table-column label="操作" width="340" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button icon="el-icon-edit" label=""  :size="size" type="primary"   @click="edit(scope.$index, scope.row)">修改信息</el-button>
              <el-button icon="el-icon-view" label=""  :size="size" type="primary"  >查看详情</el-button>
              <el-button icon="el-icon-star-off" label=""  :size="size" type="primary"  @click="see(scope.row)" >修改记录</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-dialog title="修改记录详情" :visible.sync="dialogVisibles" width="70%">
				  <el-table :data="amendmentRecord" highlight-current-row  
              v-loading="loading"   element-loading-text="加载中" border stripe
               max-height="500" size="mini" style="width:100%;" >
		        <el-table-column 
		        	v-for="(item,index) in amendmentRecordList" 
		        	:key="index" :prop="item.prop" 
		        	:show-overflow-tooltip="true" 
		        	:label="item.label" 
		        	:min-width="item.minWidth" 
		        	header-align="center"
		        	align="center">
		        </el-table-column>
		      </el-table>
		      <div class="toolbar" style="padding:10px;">
		      	<el-pagination  @current-change="refreshPageRequest1" @size-change="refreshPageSizeRequest1" background
	            :current-page="pageRequest1.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest1.pageSize" layout="sizes, total, prev, pager, next" :total="pageResult.total1" style="float:right;">
	          </el-pagination>
		      </div>
				</el-dialog>
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
              <el-button icon="el-icon-back" :size="size" type="primary" @click="back">返回</el-button>
            </el-col>
            <el-col :span="4" :offset="8"><b>供应商首营申请</b></el-col>
            <el-col :span="5" :offset="6" style="text-align: right">
              <el-button  :size="size" type="primary" @click="saveApply">申请暂存</el-button>
              <el-button  :size="size" type="primary" @click="sendApply">发起申请</el-button>
            </el-col>
          </el-row>
          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>基础信息</el-col>
          </el-row>
          <el-row class="searchBox">
           <el-form :model="saveData.scmZsjSupplier" label-width="140px" :rules="dataFormRules" ref="baseForm" size="small">
              <el-form-item label="ID" prop="id" v-if="false">
                <el-input v-model="saveData.scmZsjSupplier.id" :disabled="true" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="供应商名称"  prop="supplierName1" >
                <!-- <el-input v-model="saveData.scmZsjSupplier.supplierName" auto-complete="off"></el-input> -->
                <el-autocomplete style="width: 100%"
                  class="inline-input"
                  v-model="saveData.scmZsjSupplier.supplierName"
                  :fetch-suggestions="querySearch"
                  placeholder="请选择或输入供应商名称"
                  @select="handleSelect"
                ></el-autocomplete>
              </el-form-item>
              <el-form-item label="供应商编号" prop="supplierCode">
                <el-input :disabled="baseEdit"  v-model="saveData.scmZsjSupplier.supplierCode" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="企业负责人" prop="conpanyPersion">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjSupplier.conpanyPersion" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="联系人" prop="contactPersion">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjSupplier.contactPersion" auto-complete="off" ></el-input>
              </el-form-item>
              <el-form-item label="联系电话" prop="phone">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjSupplier.phone" auto-complete="off"></el-input>
              </el-form-item>
              
              <el-form-item label="开户行" prop="openBank">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjSupplier.openBank" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="账号" prop="bankCode">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjSupplier.bankCode" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="税号" prop="dutyParagraph">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjSupplier.dutyParagraph" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="注册地址" prop="registerAddress">
                <el-input :disabled="baseEdit" v-model="saveData.scmZsjSupplier.registerAddress" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="药品质量保证协议有效期至" prop="qualityValidity">
                <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" :disabled="baseEdit" v-model="saveData.scmZsjSupplier.qualityValidity" type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
              </el-form-item>
              <!-- <el-form-item label="" prop="">
                <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="dataForm.qualityValidity" type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
              </el-form-item> -->
              <el-form-item label="" prop="sealStyle">
                <el-checkbox-group v-model="saveData.scmZsjSupplier.sealStyle" style='float: left'>
                  <el-checkbox :disabled="baseEdit" label="相关印章印模样式" name="sealStyle"></el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="" prop="billsStyle">
                <el-checkbox-group v-model="saveData.scmZsjSupplier.billsStyle" style='float: left'>
                  <el-checkbox :disabled="baseEdit" label="随货同行单(票)样式" name="billsStyle"></el-checkbox>            
                </el-checkbox-group>
              </el-form-item>
            </el-form>    
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>生产/仓库地址</el-col>
          </el-row>
          <el-row class="searchBox">
            <ware-house :inputList="wareHouseList" :isEdit="true" @getAddress="getAddress"></ware-house>
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>许可证信息</el-col>
          </el-row>
          <el-row class="searchBox">
            <div>
              <el-form :model="saveData.scmZsjSupplierLicenceList[0]" label-width="140px" :rules="dataFormRules" ref="dataForm1" size="small">
              <el-form-item label="许可证号" prop="licenseKey">
                <el-input v-model="saveData.scmZsjSupplierLicenceList[0].licenseKey" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="有效期至" prop="validity">
                <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjSupplierLicenceList[0].validity" type="date" style="width: 100%;" auto-complete="off"></el-date-picker>
              </el-form-item>
              <el-form-item label="" prop="">
                <!-- <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjSupplierLicenceList[0].validity" type="date" style="width: 100%;" auto-complete="off"></el-date-picker> -->
              </el-form-item>
              <el-form-item label="生产/经营范围" prop="managementScope" style="width:64%">
                <el-select  v-model="saveData.scmZsjSupplierLicenceList[0].managementScope" multiple  placeholder="请选择生产/经营范围">
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
           <el-form :model="saveData.scmZsjSupplierBusinessList[0]" label-width="140px" :rules="dataFormRules" ref="dataForm3" size="small">
              <el-form-item label="注册号" prop="registerCode">
                <el-input v-model="saveData.scmZsjSupplierBusinessList[0].registerCode" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="营业执照有效期至" prop="licenseValidity">
                <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjSupplierBusinessList[0].licenseValidity" style="width: 100%;" auto-complete="off"></el-date-picker>
              </el-form-item>
              <el-form-item label="法定代表人" prop="legalPersion">
                <el-input v-model="saveData.scmZsjSupplierBusinessList[0].legalPersion" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="注册资本" prop="registerPrice">
                <el-input v-model="saveData.scmZsjSupplierBusinessList[0].registerPrice" auto-complete="off">
                  <template slot="append">万元</template>
                </el-input>
              </el-form-item>
              <el-form-item label="企业类型" prop="commerceType">
                <el-input v-model="saveData.scmZsjSupplierBusinessList[0].commerceType" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="年度报表有效期至" prop="yearReportValidity">
                <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjSupplierBusinessList[0].yearReportValidity" style="width: 100%;" auto-complete="off"></el-date-picker>
              </el-form-item>
            </el-form>    
          </el-row>


          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>GMP证书信息</el-col>
            <el-col :span="1" :offset="19">
              <el-button  :size="size" type="danger" @click="delgmpList"><i class="el-icon-minus"></i></el-button>
            </el-col>
            <el-col :span="1" >
              <el-button  :size="size" type="primary" @click="addgmpList"><i class="el-icon-plus"></i></el-button>
            </el-col>
          </el-row>
          <el-row class="searchBox" style="padding-left:10px;padding-right:10px;">
            <div class="addContent" 
            v-for="(item, index) in saveData.scmZsjGMPList" :key="index">
              <p class="addHead">GMP证书{{index+1}}</p>
              <el-form :model="item" label-width="140px" :rules="dataFormRules" :ref="`dataFormGMP${index}`" size="small">
                <el-form-item label="GMP证书" prop="gmpcertificate" :rules='[
                  { required: true, message: "请输入GMP证书", trigger: "blur" },
                  { max: 20, message: "长度不能超过20", trigger: "blur" }
                ]'>
                  <el-input v-model="item.gmpcertificate" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="GMP证书有效期至" prop="validity" :rules=' [
                    { required: true, message: "请选择GMP证书有效期", trigger: "blur" },
                  ]'>
                  <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="item.validity" auto-complete="off" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="GMP证书认证范围" prop="certificationScope" :rules='[
                  { required: true, message: "请输入GMP证书", trigger: "blur" },
                  { max: 50, message: "长度不能超过50", trigger: "blur" }
                ]'>
                  <el-input v-model="item.certificationScope" auto-complete="off"></el-input>
                </el-form-item>
              </el-form> 
            </div>
          </el-row>


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
            v-for="(item, index) in saveData.scmZsjSupplierEntruseBookList" :key="index">
              <p class="addHead">委托书{{index+1}}</p>
              <el-form :model="item" label-width="140px" :rules="dataFormRules" :ref="`dataFormWt${index}`" size="small">
                <el-form-item label="委托书编号" prop="entrustCode" :rules='[
                  { required: true, message: "请输入委托书编号", trigger: "blur" },
                  { max: 20, message: "长度不能超过20", trigger: "blur" }
                ]'>
                  <el-input v-model="item.entrustCode" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="委托人姓名" prop="entrustName" :rules='[
                    { required: true, message: "请输入委托人姓名", trigger: "blur" },
                    { max: 20, message: "长度不能超过20", trigger: "blur" }
                  ]'>
                  <el-input v-model="item.entrustName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="委托书有效期至" prop="entrustValidity" :rules=' [
                    { required: true, message: "请选择委托书有效期", trigger: "blur" },
                  ]'>
                  <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="item.entrustValidity" auto-complete="off" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="身份证号" prop="idNumber" :rules='[
                    { required: true, message: "请输入身份证号", trigger: "blur" },
                    { max: 20, message: "长度不能超过20", trigger: "blur" }
                  ]'>
                  <el-input v-model="item.idNumber" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="身份证有效期至" prop="IDValidity" :rules='[
                    { required: true, message: "请选择身份证有效期", trigger: "blur" },
                  ]'>
                  <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="item.IDValidity" auto-complete="off" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-form> 
            </div>
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>收货地址</el-col>
          </el-row>
          <el-row class="searchBox">
              <ship-address  :inputList="shipAddressList" :isEdit="true" @getShipAddress="getShipAddress"></ship-address>
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
                <template >
                  <el-button icon="el-icon-view" label=""  :size="size" type="primary"  >查看</el-button>
                  <el-button icon="el-icon-edit" label=""  :size="size" type="danger"  >删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-row>
      </div>
      </transition>
    </div>
</template>

<script>
import ShipAddress from './ShipAddress'
import Warehouse from './Warehouse'
import storage from '@/utils/storage'

let timer;
export default {
  name: "supplierfirst",
  components: {
    'ship-address':ShipAddress,
    'ware-house': Warehouse
  },
  data() {
    return {
    	dialogVisibles:false,
    	amendmentRecord:[],//修改记录
      size: "small",
      amendmentRecordList:[
      	{ prop: "filedName", label: "修改内容",minWidth: 150 },
      	{ prop: "modifier", label: "修改人",minWidth: 150 },
      	{ prop: "modifiedTime", label: "修改时间",minWidth: 150 },
      ],
      columns: [
        { prop: "approvalStateStr", label: "申请状态",minWidth: 150 },
        { prop: "fileCode", label: "档案编号" ,minWidth: 150},
        { prop: "conpanyPersion", label: "企业负责人",minWidth: 110 },
        { prop: "supplierName", label: "供应商名称",minWidth: 120 },
        { prop: "supplierCode", label: "供应商编号",minWidth: 120 },
        { prop: "legalPersion", label: "法人代表",minWidth: 100 },
        { prop: "registerPrice", label: "注册资金",minWidth: 100 },
        { prop: "licenseKey", label: "许可证号",minWidth: 100 },
        { prop: "managementScope", label: "许可范围",minWidth: 100 },
        { prop: "validity", label: "许可证有效期",minWidth: 120 },
        { prop: "registerCode", label: "营业执照注册证号",minWidth: 150 },
        { prop: "licenseValidity", label: "营业执照有效期", minWidth: 130 },
        { prop: "operatorName", label: "申请人",minWidth: 100 },
        { prop: "operatorDate", label: "申请日期",minWidth: 100 },
      ],
      dataFormRules: {
        supplierName: [
          {  required: true, message: "请选择或输入供应商名称", trigger: "change" },
            { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        conpanyPersion: [
          { required: true, message: "请输入企业负责人", trigger: "change" },
          { max: 20, message: "长度不能超过20", trigger: "change" }
        ],
        contactPersion: [
          {
            required: true,
            message: "请输入联系人",
            trigger: "change"
          },
          { max: 20, message: "长度不能超过20", trigger: "change" }
        ],
        phone: [
          { required: true, message: "请输入联系电话", trigger: "change" },
          { max: 11, message: "长度不能超过11", trigger: "change" }
        ],
        openBank: [
          { required: true, message: "请输入开户行", trigger: "change" },
          { max: 20, message: "长度不能超过11", trigger: "change" }
        ],
        bankCode: [
          { required: true, message: "请输入账号", trigger: "change" },
          { max: 30, message: "长度不能超过30", trigger: "change" }
        ],
        dutyParagraph: [
          { required: true, message: "请输入税号", trigger: "change" },
          { max: 100, message: "长度不能超过100", trigger: "change" }
        ],
        supplierCode: [
          { required: true, message: "请输入供应商编号", trigger: "change" },
          { max: 100, message: "长度不能超过100", trigger: "change" }
        ],
        registerAddress: [
          { required: true, message: "请输入注册地址", trigger: "change" },
          { max: 100, message: "长度不能超过100", trigger: "change" }
        ],
        qualityValidity: [
          { required: true, message: "请选择日期", trigger: "change" },
        ],

        //许可证信息
        licenseKey: [
          { required: true, message: "请输入许可证号", trigger: "blur" },
          // { max: 100, message: "长度不能超过100", trigger: "blur" }
        ],
        validity: [
          { required: true, message: "请选择有效期", trigger: "blur" },
          // { max: 100, message: "长度不能超过100", trigger: "blur" }
        ],
        managementScope: [
          { required: true, message: "请选择生产/经营范围", trigger: "change" },
        ],

        //营业执照信息
        registerCode: [
          { required: true, message: "请输入注册号", trigger: "blur" },
          { max: 100, message: "长度不能超过100", trigger: "blur" }
        ],
        licenseValidity: [
          { required: true, message: "请选择营业执照有效期", trigger: "blur" },
          // { max: 100, message: "长度不能超过100", trigger: "blur" }
        ],
        legalPersion: [
          { required: true, message: "请输入法定代表人", trigger: "blur" },
          { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        registerPrice: [
          { required: true, message: "请输入注册资本", trigger: "blur" },
          { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        commerceType: [
          { required: true, message: "请输入企业类型", trigger: "blur" },
          { max: 100, message: "长度不能超过100", trigger: "blur" }
        ],
        yearReportValidity: [
          { required: true, message: "请选择年度报表有效期", trigger: "blur" },
          // { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],

        //委托书
        entrustCode: [
          { required: true, message: "请输入委托书编号", trigger: "blur" },
          { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        entrustName: [
          { required: true, message: "请输入委托书名称", trigger: "blur" },
          { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        entrustValidity: [
          { required: true, message: "请选择委托书有效期", trigger: "blur" },
          // { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        idnumber: [
          { required: true, message: "请输入身份证号", trigger: "blur" },
          { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        idvalidity: [
          { required: true, message: "请选择身份证有效期", trigger: "blur" },
          // { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
      },
      // 新增编辑界面数据
      dataForm: {},
      resultList: [],//首营下拉列表数据
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      pageRequest1: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      pageResult: {},
      searchForm: {
        supplierCode:'',
        commerceType:'',
        approvalState:'',
      },
      size: "mini",
      isIndex: true,
      loading: false,
      baseEdit: false,
      provinceList: [], //省区下拉框
      divisionList: [], //商务分区下拉框
      commercelevellist: [],//供应商级别
      functionTypeList: [],//职能类型
      agentTypeList: [],//剂型数据列表
      shipAddressList:[],//编辑 收货地址回显数据列表
      wareHouseList:[],//编辑 生产/仓库地址
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
    this.saveData.ownerId = storage.get('ownerId')
    this.getSupplierList();
    this.getAgentType();
    this.findPage();
  },
  watch:{
    'saveData.scmZsjSupplier.supplierName' : 'watchsupplierName'
  },
  methods: {
    back(){
      this.isIndex = true;
      this.clearForm();
    },
    style(rows){
    	let {row,column,rowIndex,columnIndex} = rows;
    	//营业执照
    	let flag = this.getPassFormatDate(row.licenseValidity);
    	//许可证
    	let data = this.getPassFormatDate(row.validity);
    	if((flag == 2 && column.label == "营业执照有效期") || (data == 2 && column.label == "许可证有效期")){
    		return 'background:red'
    	}else if((flag == 3 && column.label == "营业执照有效期") || (data == 3 && column.label == "许可证有效期")){
    			return 'background:rgba(204, 204, 204)'
    	}
    },
    //判断是否到期
    getPassFormatDate(timer) {
  		//转换时间格式时间戳
    	function tranDate (time) {
        return new Date(time.replace(/-/g, '/')).getTime();
      }    	
	    let date = new Date(timer);
	    date.setMonth(date.getMonth()-1);
	    let year = date.getFullYear();
	    let month = date.getMonth() + 1;
	    let strDate = date.getDate();
	    let hours = date.getHours()<10?'0'+date.getHours():date.getHours();
	    let min = date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes();
	    let sec = date.getSeconds()<10?'0'+date.getSeconds():date.getSeconds();
	    if (month >= 1 && month <= 9) { month = "0" + month;}
	    if (strDate >= 0 && strDate <= 9) {strDate = "0" + strDate;}
	    let currentdate = year + '-' + month + '-' + strDate + ' ' + hours + ':' + min + ':' + sec;
			//到期前一个月时间
	    let endTime = tranDate(currentdate);
	    //到期时间
	    let etime = tranDate(timer);
	    //获取当前时间
	    let nowDate = new Date();
	    let currentTime = nowDate.getFullYear() + '-' + (nowDate.getMonth() + 1) + '-' + nowDate.getDate() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes()+ ':' + nowDate.getSeconds();
	    //将当前时间转为时间戳
	    let nowTime = tranDate(currentTime);
	    if(nowTime < endTime){return 1}
	    else if(nowTime >= endTime && nowTime < etime){return 2}
	    else {return 3}
		},
    clearForm(){
      this.saveData = {//上传的数据
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
      this.wareHouseList = [];
      this.shipAddressList = [];
    },
    watchsupplierName(){//监听供应商名称的变化对输入框状态做出改变
      clearTimeout(timer);
      let newData;
      timer = setTimeout(()=>{
        let getStateList = this.resultList.reduce((final, item, index, arr) => {
          if(item.supplierName === this.saveData.scmZsjSupplier.supplierName){
            final.push(item)
          }
          return final
        },[])
        if(getStateList.length>0){
          this.handleSelect(getStateList[0]);
          this.baseEdit = true;
        }else{
          this.baseEdit = false;
          this.$refs.baseForm.resetFields();
        }
      },1000);
    },
    handleAdd() {
      this.isIndex = false
    },
    edit(index, row){//编辑
      this.isIndex = false;
      this.$api.zsj
        .get({
          url: "/scm/scmZsjSupplierFirst/v1/get/"+row.supplierFirstId,
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
          this.saveData = Object.assign({},res);
          this.shipAddressList = this.saveData.scmZsjSupplierShipAddressList.reduce((final, item, index, arr)=>{
            item.index = index;
            final.push(item)
            return final;
          },[]);//收货地址
          this.wareHouseList = this.saveData.scmZsjSupplierWarehouseList.reduce((final, item, index, arr)=>{
            item.index = index;
            final.push(item)
            return final;
          },[]);//仓库地址
          this.saveData.scmZsjSupplierLicenceList[0].managementScope = String(res.scmZsjSupplierLicenceList[0].managementScope).split('/');
        })
    },
    querySearch(queryString, cb){
      var result = this.resultList;
      cb(result);
    },
    findPage(query=[]) {
      this.loading = true;
      this.$api.zsj
        .getZsj({
          url: "/scm/scmZsjSupplier/v1/firstList",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys: [{
              property: "ownerId",
              value: this.saveData.ownerId,
              operation: "EQUAL",
              relation: "AND"
            }].concat(query),
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
    getSupplierList(){//获取首营下拉列表
      this.$api.common
        .getsupplierfirstList()
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
            item.value = item.supplierNameStr;
            final.push(item);
            return final;
          },[]);
        });
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
    addEntruseBookList(){//添加委託書
      this.saveData.scmZsjSupplierEntruseBookList.push({
            entrustCode: "",
            entrustName: "",
            entrustValidity: "",
            id: "",
            idnumber: "",
            idvalidity: "",
      })
    },
    delEntruseBookList(){//刪除委託書
      if(this.saveData.scmZsjSupplierEntruseBookList.length>1){
        this.saveData.scmZsjSupplierEntruseBookList.pop();
      }else{
        return;
      }
    },
    addgmpList(){//添加GMP
      this.saveData.scmZsjGMPList.push({
            certificationScope: "",
            validity: "",
            id: "",
            gmpcertificate: "",
      })
    },
    delgmpList(){//刪除委託書
      if(this.saveData.scmZsjGMPList.length>1){
        this.saveData.scmZsjGMPList.pop();
      }else{
        return;
      }
    },
    handleSelect(data){//带输入建议表单的select事件
      this.saveData.scmZsjSupplier = Object.assign({},data)
      // this.saveData.scmZsjSupplier.supplierName = this.saveData.scmZsjSupplier.supplierName.split('|')[1]
      this.saveData.scmZsjSupplier.sealStyle = this.saveData.scmZsjSupplier.sealStyle == '1'? true:false
      this.saveData.scmZsjSupplier.billsStyle = this.saveData.scmZsjSupplier.billsStyle == '1'? true:false
      this.saveData.supplierId = data.id;
      this.baseEdit = true;
    },
    getAddress(data){//从列表中去取得数据保存
      this.saveData.scmZsjSupplierWarehouseList = data.allData
    },
    getShipAddress(data){//从列表中获取收货地址
      this.saveData.scmZsjSupplierShipAddressList = data.allData
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
    refreshPageRequest1: function(page = 1) {
      this.pageRequest1.page = page;
      this.getAmendmentRecord();
    },
    // size刷新
    refreshPageSizeRequest1: function(pageSize) {
      this.pageRequest1.pageSize = pageSize;
      this.getAmendmentRecord();
    },
    getValidateForm(){//添加要校验的表单
      let validateForm = ['baseForm','dataForm1','dataForm3'];
      // console.log(validateForm)
      if(this.$refs.dataForm2){
        validateForm.push('dataForm2')
      };
      if(this.$refs.dataForm4){
        validateForm.push('dataForm4')
      };
      for(let i=0;i<this.saveData.scmZsjSupplierEntruseBookList.length; i++){
        validateForm.push(`dataFormWt${i}`)
      };
      for(let i=0;i<this.saveData.scmZsjGMPList.length; i++){
        validateForm.push(`dataFormGMP${i}`)
      };
      console.log(validateForm)
      // return validateForm;
      let newArr = validateForm.reduce((final, item, index, arr)=>{
        let result = new Promise((resolve, reject)=>{
          if(item.includes('dataFormWt') || item.includes('dataFormGMP')){
            this.$refs[item][0].validate( valid => {
              if(valid){
                resolve()
              }else{
                reject()
              }
            })
          }else{
            this.$refs[item].validate( valid => {
              if(valid){
                resolve()
              }else{
                reject()
              }
            })
          }
        });
        final.push(result)
        console.log(result)
        return final;
      },[]);
      return newArr
    },
    sendApply(){//发起申请
      // if(this.getValidateForm()){//表单验证通过
      let self = this;
      let newArr = this.getValidateForm();
      Promise.all(newArr).then(()=>{
        if(self.saveData.scmZsjSupplierShipAddressList[0]['address'] === ''){
          self.$message({
            message: '请添加收货地址', type: 'warning',duration: 800
          })
          return;
        }else if(self.saveData.scmZsjSupplierWarehouseList[0]['address'] === ''){
          self.$message({
            message: '请添加生产/仓库地址', type: 'warning',duration: 800
          })
          return;
        }else{
            let sendData = self.saveData;
          if(sendData.scmZsjSupplierLicenceList[0].managementScope instanceof Array){
              sendData.scmZsjSupplierLicenceList[0].managementScope = sendData.scmZsjSupplierLicenceList[0].managementScope.join('/')
          }
          sendData.scmZsjSupplier.billsStyle?sendData.scmZsjSupplier.billsStyle = '1': sendData.scmZsjSupplier.billsStyle = '0'
          sendData.scmZsjSupplier.sealStyle?sendData.scmZsjSupplier.sealStyle = '1': sendData.scmZsjSupplier.sealStyle = '0'
          this.$api.zsj.saveZsj({
              url: "/scm/scmZsjSupplierFirst/v1/sendApply",
              data: sendData
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
              this.findPage();
              this.$message({message: res.message, type: 'success',duration: 800})
              this.isIndex = true;
              this.clearForm();
              this.getSupplierList();
            })
        }
      }).catch(()=>{
        this.$message({
          message: '请将表单信息填写完整！', type: 'warning',duration: 800
        })
      })
    },
    saveApply(){//发起暂存申请

        let self = this;
        let sendData = self.saveData;
        if(sendData.scmZsjSupplierLicenceList[0].managementScope instanceof Array){
            let sendManageScope = sendData.scmZsjSupplierLicenceList[0].managementScope;
            sendData.scmZsjSupplierLicenceList[0].managementScope = sendManageScope.join('/')
        }
        sendData.scmZsjSupplier.billsStyle?sendData.scmZsjSupplier.billsStyle = '1': sendData.scmZsjSupplier.billsStyle = '0'
        sendData.scmZsjSupplier.sealStyle?sendData.scmZsjSupplier.sealStyle = '1': sendData.scmZsjSupplier.sealStyle = '0'
        this.$api.zsj.saveZsj({
            url: "/scm/scmZsjSupplierFirst/v1/save",
            data: sendData
          })
          .then(res => {
            self.saveData.scmZsjSupplierLicenceList[0].managementScope = self.saveData.scmZsjSupplierLicenceList[0].managementScope.split('/')
            if (res.state == false) {
              this.$message({
                message: res.shortMessage,
                type: "error",
                duration: 800
              });
              return;
            }
            this.saveData.id = res.value;
            this.findPage();
            this.$message({message: '申请成功', type: 'success',duration: 800})
          })
        },
        inquire(){
          let query = [];
          let searchForm = Object.assign({},this.searchForm);
            for(var key in searchForm){
            if(searchForm[key] !== '' && key !== 'supplierCode'){
              query.push({
                hasInitValue: false,
                operation: "EQUAL",
                property: key,
                relation: "AND",
                value: searchForm[key]
              })
            }else if(searchForm[key] !== '' && key === 'supplierCode'){
              query.push({
                hasInitValue: false,
                operation: "LIKE",
                property: key,
                relation: "AND",
                value: searchForm[key]
              })
            }
          }
          this.findPage(query)
        },
        see(val){
		    	console.log(val)
		    	this.dialogVisibles = true;
		    	let search = [
		    		{operation: "EQUAL",property: "categoryId",relation: "AND", value: val.supplierFirstId },
		    		{operation: "EQUAL",property: "category",relation: "AND", value: "gysxg" }
		    	]
		    	this.getAmendmentRecord(search)
		    },
		    //获取修改记录
		    getAmendmentRecord(search = []){
		    	this.$api.zsj
		        .getZsj({
		          url: "/scm/scmLogModifyLog/v1/list",
		          data: {
		          	pageBean: this.pageRequest1,
		            params: {},
		            querys: search,
		            sorter: []
		          }
		        })
		        .then(res => {
		          console.log(res)
		          this.pageResult.total1 = res.total;
		          this.amendmentRecord = res.rows
		        });
		    },
    },
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
.red{background-color: red;}
.gray{background-color: gray;}
</style>
