<template>
    <!-- <div>{{msg}}</div> -->
    <div>
      <transition name="el-fade-in">
      
      <!-- 表格和搜索表格 -->
      <div v-show="isIndex">
        <el-row class="searchHeader">
          <el-col :span="2"><div>筛选查询</div></el-col>
          <el-col :span="22" style="text-align: right" >
            <el-button icon="el-icon-search" :size="size" type="primary" @click="inquire">查询</el-button>
          </el-col>
        </el-row>
        <el-row class="searchBox">
          <el-form :model="searchForm" label-width="140px"  ref="searchForm" size="small"><el-form-item label="状态" prop="acceptState">
              <el-select v-model="searchForm.acceptState" placeholder="请选择审批状态" clearable>
                  <el-option value='0' label="认可"></el-option>
                  <el-option value="1" label="考察中"></el-option>
                  <el-option value="2" label="终止"></el-option>
                  <el-option value="-1" label="未认可"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="企业类别" prop="commerceType">
              <el-select v-model="searchForm.commerceState" placeholder="请选择企业类别" clearable>
                  <el-option value='0' label="药品经营企业"></el-option>
                  <el-option value="1" label="医疗机构"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="客户编号" prop="commerceCode">
              <el-input v-model="searchForm.commerceCode" auto-complete="off" clearable></el-input>
            </el-form-item>
            <el-form-item label="商务分区" prop="businessDivisionIdIndex">
              <el-select @change="changeDivision" clearable v-model="searchForm.businessDivision" placeholder="请选择商品分区">
                  <el-option 
                  v-for="divisionOption in divisionList"
                  :label="divisionOption.businessDivision" 
                  :value="divisionOption.businessDivision" 
                  :key="divisionOption.id">
                  </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="省区" prop="provinceIdIndex">
              <el-select v-model="searchForm.province" clearable placeholder="请选择省区">
                  <el-option 
                  v-for="provinceOption in provinceList"
                  :label="provinceOption.province" 
                  :value="provinceOption.province" 
                  :key="provinceOption.id">
                  </el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </el-row>
        <!-- <el-row class="btnAdd">
          <el-col :xs="2" :sm="2" :md="2" :lg="2">
            <el-button icon="el-icon-circle-plus-outline" type="primary" size="mini" @click="handleAdd">首营申请</el-button>
          </el-col>
        </el-row> -->
       <!--表格栏-->
        <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-tickets"></i>数据列表</el-col>
            <el-col :span="21" style="text-align:right;">
              <el-button icon="el-icon-circle-plus-outline" type="primary" size="mini" @click="handleAdd">首营申请</el-button>
            </el-col>
          </el-row>
       
        <el-table :data="pageResult.rows" highlight-current-row  :cell-style="style" 
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
              <el-button icon="el-icon-star-off" label=""  :size="size" type="primary" @click="see(scope.row)">修改记录</el-button>
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
    </transition>
    
    <!-- 新增首营 -->
    <transition name="el-fade-in">

      <div v-show="!isIndex">
          <el-row class="searchHeader">
            <el-col :span="1">
              <el-button icon="el-icon-back" :size="size" type="primary" @click="back">返回</el-button>
            </el-col>
            <el-col :span="4" :offset="8"><b>商业首营申请</b></el-col>
            <el-col :span="5" :offset="6" style="text-align: right">
              <el-button  :size="size" type="primary" @click="saveApply">申请暂存</el-button>
              <el-button  :size="size" type="primary" @click="sendApply">发起申请</el-button>
            </el-col>
          </el-row>
          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>基础信息</el-col>
          </el-row>
          <el-row class="searchBox">
           <el-form :model="saveData.scmZsjCommerce" label-width="140px" :rules="dataFormRules" ref="baseForm" size="small">
              <el-form-item label="ID" prop="id" v-if="false">
                <el-input v-model="saveData.scmZsjCommerce.id" :disabled="true" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="商业名称"  prop="commerceName" >
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
                <el-date-picker  :disabled="baseEdit" value-format="yyyy-MM-dd HH:mm:ss"  v-model="saveData.scmZsjCommerce.qualityValidity" type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
              </el-form-item>
              <!-- <el-form-item label="" prop="">
                <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="dataForm.qualityValidity" type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
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
              <el-form :model="saveData.scmZsjCommerceLicenceList[0]" label-width="140px" :rules="dataFormRules" ref="dataForm1" size="small">
              <el-form-item label="许可证号" prop="licenseKey">
                <el-input v-model="saveData.scmZsjCommerceLicenceList[0].licenseKey" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="有效期至" prop="validity">
                <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjCommerceLicenceList[0].validity" type="date" style="width: 100%;" auto-complete="off"></el-date-picker>
              </el-form-item>
              <el-form-item label="" prop="">
                <!-- <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjCommerceLicenceList[0].validity" type="date" style="width: 100%;" auto-complete="off"></el-date-picker> -->
              </el-form-item>
              <el-form-item label="生产/经营范围" prop="managementScope" style="width:64%">
                <el-select  v-model="saveData.scmZsjCommerceLicenceList[0].managementScope" multiple  placeholder="请选择生产/经营范围">
                    <el-option 
                    v-for="agentTypeOption in agentTypeList"
                    :label="agentTypeOption.managementScope" 
                    :value="agentTypeOption.managementScope" 
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
           <el-form :model="saveData.scmZsjTreatLicenceList[0]" label-width="140px"  ref="dataForm2" size="small">
              <el-form-item label="医疗许可证号" prop="treatLicence" :rules='[
                  { required: true, message: "请输入医疗许可证号", trigger: "blur" },
                  { max: 100, message: "长度不能超过100", trigger: "blur" }
                ]'>
                <el-input v-model="saveData.scmZsjTreatLicenceList[0].treatLicence" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="有效期至" prop="validity" :rules='[
                  { required: true, message: "请选择有效期", trigger: "blur" },
                  { max: 100, message: "长度不能超过100", trigger: "blur" }
                ]'>
                <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjTreatLicenceList[0].validity" style="width: 100%;" type="date" auto-complete="off"></el-date-picker>
              </el-form-item>
            </el-form>    
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>营业执照信息</el-col>
          </el-row>
          <el-row class="searchBox">
           <el-form :model="saveData.scmZsjCommerceBusinessList[0]" label-width="140px" :rules="dataFormRules" ref="dataForm3" size="small">
              <el-form-item label="注册号" prop="register">
                <el-input v-model="saveData.scmZsjCommerceBusinessList[0].register" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="营业执照有效期至" prop="licenseValidity">
                <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjCommerceBusinessList[0].licenseValidity" style="width: 100%;" auto-complete="off"></el-date-picker>
              </el-form-item>
              <el-form-item label="法定代表人" prop="legalPersion">
                <el-input v-model="saveData.scmZsjCommerceBusinessList[0].legalPersion" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="注册资本" prop="registerPrice">
                <el-input v-model="saveData.scmZsjCommerceBusinessList[0].registerPrice" auto-complete="off">
                  <template slot="append">万元</template>
                </el-input>
              </el-form-item>
              <el-form-item label="企业类型" prop="commerceType">
                <el-input v-model="saveData.scmZsjCommerceBusinessList[0].commerceType" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="年度报表有效期至" prop="yearReportValidity">
                <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjCommerceBusinessList[0].yearReportValidity" style="width: 100%;" auto-complete="off"></el-date-picker>
              </el-form-item>
            </el-form>    
          </el-row>

          <el-row v-if="saveData.scmZsjCommerce.commerceState == '0'" class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>GSP证书信息</el-col>
          </el-row>
          <el-row v-if="saveData.scmZsjCommerce.commerceState == '0'" class="searchBox">
           <el-form :model="saveData.scmZsjGSPList[0]" label-width="140px" :rules="dataFormRules" ref="dataForm4" size="small">
              <el-form-item label="GSP证书" prop="gsp" :rules='[
                { required: true, message: "请输入GSP证书", trigger: "blur" },
                { max: 20, message: "长度不能超过20", trigger: "blur" }
              ]'>
                <el-input v-model="saveData.scmZsjGSPList[0].gsp" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="GSP证书有效期至" prop="validity" :rules='[
                  { required: true, message: "请选择GSP证书有效期", trigger: "blur" },
                ]'>
                <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" v-model="saveData.scmZsjGSPList[0].validity" auto-complete="off" style="width: 100%;"></el-date-picker>
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
              <el-form :model="dataForm" label-width="140px" :rules="dataFormRules" ref="dataForm" size="small">
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
            v-for="(item, index) in saveData.scmZsjCommerceEntruseBookList" :key="index">
              <p class="addHead">委托书{{index+1}}</p>
              <el-form :model="item" label-width="140px" :rules="dataFormRules" :ref="`dataFormWt${index}`" size="small">
                <el-form-item label="委托书编号" prop="entrustCode" :rules='[
                  { required: true, message: "请输入委托书编号", trigger: "blur" },
                  { max: 20, message: "长度不能超过20", trigger: "blur" }
                ]'>
                  <el-input v-model="item.entrustCode" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="委托书名称" prop="entrustName" :rules='[
                    { required: true, message: "请输入委托书名称", trigger: "blur" },
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
                <!-- <el-form-item label="授权销售品种" prop="commerceCode">
                  <el-input v-model="item.commerceCode" auto-complete="off"></el-input>
                </el-form-item> -->
              </el-form> 
            </div>
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>收货信息</el-col>
          </el-row>
          <el-row class="searchBox">
              <ship-address :inputList="shipAddressList" :isEdit="true" @getShipAddress="getShipAddress" ></ship-address>
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
                <template>
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
                  v-loading="loading"   element-loading-text="加载中" border stripe @selection-change="handleSelectPromise" @select="handleSelectPromiseOne" @select-all="handleSelectAll"
                  :show-overflow-tooltip="true" max-height="500" size="mini" align="left" style="width:100%;" >
              <el-table-column type="selection" width="40"></el-table-column>
              <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
              <el-table-column prop="promiseBook" label="承诺书名称" align="center">
              </el-table-column>
              <el-table-column label="有效日期" prop="validity" fixed="right" header-align="center" align="center">
                <template slot-scope="scope">
                    <el-date-picker  value-format="yyyy-MM-dd HH:mm:ss" type="date" v-model="promiseBookList[scope.$index].validity" placeholder="选择有效日期" style="width: 100%;"></el-date-picker>
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
  name: "commercefirst",
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
        { prop: "acceptStateStr", label: "状态" },
        { prop: "fileCode", label: "档案编号" ,minWidth: 150},
        { prop: "commerceType", label: "企业类别",minWidth: 100,formatter:(row, column, cellValue, index)=>{return cellValue=='1'?'医疗机构':'药品经营企业'}},
        { prop: "commerceCode", label: "客户编号",minWidth: 100 },
        { prop: "conpanyPersion", label: "企业负责人",minWidth: 110 },
        { prop: "commerceName", label: "商业名称",minWidth: 100 },
        { prop: "businessDivision", label: "商务分区",minWidth: 100 },
        { prop: "province", label: "省区" },
        { prop: "legalPersion", label: "法人代表",minWidth: 100 },
        { prop: "registerPrice", label: "注册资金",minWidth: 100 },
        { prop: "licenseKey", label: "许可证号",minWidth: 100 },
        { prop: "managementScope", label: "许可范围",minWidth: 100 },
        { prop: "validity", label: "许可证有效期",minWidth: 120 },
        { prop: "register", label: "营业执照注册证号",minWidth: 150 },
        { prop: "licenseValidity", label: "营业执照有效期", minWidth: 130 },
        { prop: "operatorName", label: "申请人",minWidth: 100 },
        { prop: "operatorDate", label: "申请日期",minWidth: 100 },
      ],
      dataFormRules: {
        commerceName: [
          {  required: true, message: "请选择或输入商业名称", trigger: "change" },
            { max: 20, message: "长度不能超过20", trigger: "blur" }
        ],
        commerceState: [
          { required: true, message: "请选择企业类别", trigger: "change" },
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
        commerceCode: [
          { required: true, message: "请输入商业编号", trigger: "change" },
          { max: 100, message: "长度不能超过100", trigger: "change" }
        ],
        registerAddress: [
          { required: true, message: "请输入注册地址", trigger: "change" },
          { max: 100, message: "长度不能超过100", trigger: "change" }
        ],
        qualityValidity: [
          { required: true, message: "请选择日期", trigger: "change" },
        ],
        businessDivisionId: [
          { required: true, message: "请选择商务分区", trigger: "change" },
        ],
        commerceLevel: [
          { required: true, message: "请选择商业级别", trigger: "change" },
        ],
        //许可证信息
        licenseKey: [
          { required: true, message: "请输入许可证号", trigger: "blur" },
        ],
        validity: [
          { required: true, message: "请选择有效期", trigger: "blur" },
        ],
        managementScope: [
          { required: true, message: "请选择生产/经营范围", trigger: "change" },
        ],

        //营业执照信息
        register: [
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
        commerceCode:'',
        commerceState:'',
        acceptState:'',
        province:'',
        businessDivision:''
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
      shipAddressList:[],//编辑 收货地址回显数据列表
      wareHouseList:[],//编辑 生产/仓库地址
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
            idNumber: "",
            IDValidity: "",
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
        scmZsjCommerceShipAddressList: [//收货信息
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
    this.saveData.ownerId = storage.get('ownerId')
    this.getDivision();
    this.getcommercelevel();
    this.getCommerList();
    this.getAgentType();
    this.getPromiseBook();
    this.findPage();
  },
  watch:{
    'saveData.scmZsjCommerce.commerceName' : 'watchCommerceName'
  },
  methods: {
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
    back(){
      this.isIndex = true;
      this.clearForm();
    },
    watchCommerceName(){//监听商业名称的变化对输入框状态做出改变
      clearTimeout(timer);
      let newData;
      timer = setTimeout(()=>{
        let getStateList = this.resultList.reduce((final, item, index, arr) => {
          if(item.commerceName === this.saveData.scmZsjCommerce.commerceName){
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
    handleAdd: function() {
      this.isIndex = false
    },
    edit(index, row){//编辑
      this.isIndex = false;
      this.$api.zsj
        .get({
          url: "/scm/scmZsjCommerceFirst/v1/get/"+row.commerceFirstId,
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
          this.shipAddressList = this.saveData.scmZsjCommerceShipAddressList.reduce((final, item, index, arr)=>{
            item.index = index;
            final.push(item)
            return final;
          },[]);//收货信息
          this.wareHouseList = this.saveData.scmZsjCommerceWarehouseList.reduce((final, item, index, arr)=>{
            item.index = index;
            final.push(item)
            return final;
          },[]);//仓库地址
          this.saveData.scmZsjCommerceLicenceList[0].managementScope = String(res.scmZsjCommerceLicenceList[0].managementScope).split('/');
          this.saveData.scmZsjCommercePromiseBookList = [].concat(res.scmZsjCommercePromiseBookList);
          this.promiseBookList.reduce((final, item, index, arr)=>{
            this.saveData.scmZsjCommercePromiseBookList.reduce((final1, item1, index1, arr1)=>{
              if(item.id === item1.promiseBookId){
                this.promiseBookList[index].validity = item1.validity
                this.$refs.promiseTable.toggleRowSelection(this.promiseBookList[index])
              }
            },[])
          },[])
        })
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
    findPage(query=[]) {
      this.loading = true;
      this.$api.zsj
        .getZsj({
          url: "/scm/scmZsjCommerce/v1/firstList",
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
    getAgentType(){
        this.$api.common
        .getManageMentScope()
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
      this.saveData.scmZsjCommerce.sealStyle = this.saveData.scmZsjCommerce.sealStyle == '1'? true:false
      this.saveData.scmZsjCommerce.billsStyle = this.saveData.scmZsjCommerce.billsStyle == '1'? true:false
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
      this.searchForm.province= '';
      let divisionId = this.searchForm.businessDivision;
      this.provinceList = this.divisionList.reduce((final, item, index, arr)=>{
        if(item.businessDivision == divisionId){
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
    handleSelectAll(){
      this.$refs.promiseTable.clearSelection();
    },
    handleSelectPromiseOne(selection, row){//選擇承諾書限制必須選有效日期
        if(row.validity === ''){
          this.$message({message: '请选择有效日期', type: 'warning',duration: 800})
          this.$refs.promiseTable.toggleRowSelection(row, false);
        }
    },
    handleSelectPromise(selection){//承诺书选择事件
        console.log(selection)
        let newData = selection.reduce((final, item, index, arr)=>{
          final.push({
            isExist: "1",
            promiseBookId: item.id,
            validity: item.validity
          })
          return final;
        },[]);
        this.saveData.scmZsjCommercePromiseBookList = newData
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
      for(let i=0;i<this.saveData.scmZsjCommerceEntruseBookList.length; i++){
        validateForm.push(`dataFormWt${i}`)
      };
      console.log(validateForm)
      // return validateForm;
      let newArr = validateForm.reduce((final, item, index, arr)=>{
        let result = new Promise((resolve, reject)=>{
          if(item.includes('dataFormWt')){
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
      Promise.all(newArr).then(()=>{
        result = true
      }).catch(()=>{
        result = false
      })
    },
    clearForm(){//清空表单数据
      this.saveData={
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
            idNumber: "",
            IDValidity: "",
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
        scmZsjCommerceShipAddressList: [//收货信息
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
      this.wareHouseList = [];
      this.shipAddressList = [];
      this.$refs.promiseTable.clearSelection();
      let promiseList = this.promiseBookList
      for(let i of promiseList){
        i.validity = ""
      }
    },
    sendApply(){//发起申请
      // if(this.getValidateForm()){//表单验证通过
      let self = this;
      let newArr = this.getValidateForm();
      Promise.all(newArr).then(()=>{
        if(self.saveData.scmZsjCommerceShipAddressList[0]['address'] === ''){
          self.$message({
            message: '请添加收货地址', type: 'warning',duration: 800
          })
          return;
        }else if(self.saveData.scmZsjCommerceWarehouseList[0]['address'] === ''){
          self.$message({
            message: '请添加生产/仓库地址', type: 'warning',duration: 800
          })
          return;
        }else{
          let sendData = self.saveData;
          if(sendData.scmZsjCommerce.commerceState == '1'){
            delete sendData.scmZsjGSPList;
          }else if(sendData.scmZsjCommerce.commerceState == '0'){
            delete sendData.scmZsjTreatLicenceList;
          }
          if(sendData.scmZsjCommerceLicenceList[0].managementScope instanceof Array){
              sendData.scmZsjCommerceLicenceList[0].managementScope = sendData.scmZsjCommerceLicenceList[0].managementScope.join('/')
          }
          sendData.scmZsjCommerce.billsStyle?sendData.scmZsjCommerce.billsStyle = '1': sendData.scmZsjCommerce.billsStyle = '0'
            sendData.scmZsjCommerce.sealStyle?sendData.scmZsjCommerce.sealStyle = '1': sendData.scmZsjCommerce.sealStyle = '0'
          this.$api.zsj.saveZsj({
              url: "/scm/scmZsjCommerceFirst/v1/sendApply",
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
              this.getCommerList();
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
      if(sendData.scmZsjCommerce.commerceState == '1'){
        delete sendData.scmZsjGSPList;
      }else if(sendData.scmZsjCommerce.commerceState == '0'){
        delete sendData.scmZsjTreatLicenceList;
      }
      if(sendData.scmZsjCommerceLicenceList[0].managementScope instanceof Array){
          sendData.scmZsjCommerceLicenceList[0].managementScope = sendData.scmZsjCommerceLicenceList[0].managementScope.join('/')
      }
      sendData.scmZsjCommerce.billsStyle?sendData.scmZsjCommerce.billsStyle = '1': sendData.scmZsjCommerce.billsStyle = '0'
      sendData.scmZsjCommerce.sealStyle?sendData.scmZsjCommerce.sealStyle = '1': sendData.scmZsjCommerce.sealStyle = '0'
      this.$api.zsj.saveZsj({
          url: "/scm/scmZsjCommerceFirst/v1/save",
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
          this.saveData.id = res.value;
          this.findPage();
          this.$message({message: res.message, type: 'success',duration: 800})
        })
    },
    inquire(){
      let query = [];
      let searchForm = Object.assign({},this.searchForm);
      for(var key in searchForm){
        if(searchForm[key] !== '' && key !== 'commerceCode'){
          query.push({
            hasInitValue: false,
            operation: "EQUAL",
            property: key,
            relation: "AND",
            value: searchForm[key]
          })
        }else if(searchForm[key] !== '' && key === 'commerceCode'){
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
    		{operation: "EQUAL",property: "categoryId",relation: "AND", value: val.commerceFirstId },
    		{operation: "EQUAL",property: "category",relation: "AND", value: "syxg" }
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
