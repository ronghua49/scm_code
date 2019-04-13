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
                <el-form-item label="年度" prop>
                    <el-select v-model="searchForm.approvalState" clearable placeholder="请选择年度">
                        <el-option value="0" label="2018"></el-option>
                        <el-option value="1" label="2017"></el-option>
                        <el-option value="2" label="2016"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="商务分区" prop="contractCode">
                    <el-select
                        @change="changeSearchDivision"
                        v-model="searchForm.businessDivisionId"
                        clearable
                        placeholder="请选择商务分区"
                    >
                        <el-option
                            v-for="item in divisionList"
                            :label="item.businessDivision"
                            :value="item.id"
                            :key="item.id"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="省区" prop="provinceIdIndex">
                    <el-select v-model="searchForm.provinceId" clearable placeholder="请选择省区">
                        <el-option
                            v-for="provinceOption in searchProvinceList"
                            :label="provinceOption.province"
                            :value="provinceOption.id"
                            :key="provinceOption.id"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="商业名称" prop="procurementCode">
                    <el-input placeholder="请输入商业名称" v-model="searchForm.procurementCode" clearable></el-input>
                </el-form-item>
            </el-form>
            <div class="header">
                <div class="headleft">
                    <span class="el-icon-tickets"></span>
                    <span class="sx">数据列表</span>
                </div>
                <div class="headright">
                    <el-button type="primary" class="el-icon-circle-plus" size="mini" @click="buy">签订协议</el-button>
                </div>
            </div>
            <!--table-->
            <el-table
                element-loading-text="加载中"
                :data="firstTableData"
                size="mini"
                border
                style="width: 100%"
            >
                <el-table-column type="index" label="序号" align="center" minWidth="400"></el-table-column>
                <el-table-column
                    v-for="item in column.slice(0,6) "
                    :key="item.prop"
                    :prop="item.prop"
                    :label="item.label"
                    :formatter="item.formatter"
                    :width="item.minwidth"
                    :align="item.align"
                    :show-overflow-tooltip="true"
                ></el-table-column>
                <el-table-column label="商业级别" align="center" minWidth="400">
                    <el-table-column label="实际级别" align="center" minWidth="130" prop="actualLevel"></el-table-column>
                    <el-table-column
                        label="签订协议类型"
                        align="center"
                        minWidth="130"
                        prop="agreementType"
                        :formatter="function(row, column, cellValue, index){
                if(cellValue == 0){
                  return '经销商'
                }else if (cellValue == 1){
                  return '两票制经销商'
                }else if (cellValue == 2){
                  return '终止认可'
                }else if (cellValue == 3){
                  return '转分销商'
                }
          }"
                    ></el-table-column>
                </el-table-column>
                <el-table-column
                    v-for="item in column.slice(6,column.length) "
                    :key="item.prop"
                    :prop="item.prop"
                    :label="item.label"
                    :formatter="item.formatter"
                    :width="item.minwidth"
                    :align="item.align"
                ></el-table-column>
                <el-table-column fixed="right" label="操作" align="center" width="270">
                    <template slot-scope="scope">
                        <el-button class="el-icon-view" type="primary" size="mini"
                                   @click="toViewDetail(scope.row.agreementSummaryId)">查看详情
                        </el-button>
                        <el-button type="primary" class="el-icon-edit" size="mini"
                                   :disabled="['1','2'].includes(scope.row.approvalState)"
                                   @click="toEdit(scope.row.agreementSummaryId)">编辑
                        </el-button>
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
                    :total="totalFirst"
                ></el-pagination>
            </div>
        </div>
        <!--第一步-->
        <div class="head-item" v-show="[1,2,3,4,5].includes(active)">
            <el-row class="searchHeader">
                <el-col :span="1" style="text-align: right;">
                    <el-button size="mini" class="el-icon-back" type="primary" @click="goback">返回</el-button>
                </el-col>
                <el-col :span="6" :offset="8">
                    <b>经销商协议合作条款申请</b>
                </el-col>
                <el-col :span="9" style="text-align: right;">
                    <el-button
                        size="mini"
                        type="primary"
                        v-show="[2,3].includes(active)"
                        @click="prevStep"
                    >上一步
                    </el-button>
                    <el-button
                        size="mini"
                        type="primary"
                        v-show="[1,2].includes(active)"
                        @click="nextStep"
                    >下一步
                    </el-button>
                    <el-button size="mini" type="primary" v-show="[2,3].includes(active)" @click="saveApply">申请暂存
                    </el-button>
                    <el-button size="mini" type="primary" v-show="active == 3" @click="sendApply">发起申请</el-button>
                </el-col>
            </el-row>
            <el-row>
                <el-steps
                    :space="500"
                    :align-center="true"
                    :active="active-1"
                    finish-status="success"
                    style="margin: 35px auto;"
                >
                    <el-step title="选择经销商"></el-step>
                    <el-step title="协议折扣条款"></el-step>
                    <el-step title="协议品规明细"></el-step>
                    <el-step title="完成"></el-step>
                </el-steps>
            </el-row>
        </div>
        <transition name="el-fade-in">
            <div v-show="active == 1">
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
                            <el-form-item label="商务分区" prop="businessDivisionId">
                                <el-select
                                    @change="changeDivision"
                                    v-model="wrapForm.businessDivisionId"
                                    placeholder="请选择商务分区"
                                >
                                    <el-option
                                        v-for="item in divisionList"
                                        :label="item.businessDivision"
                                        :value="item.id"
                                        :key="item.id"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="省区" prop="provinceId">
                                <el-select
                                    @change="changeProvince"
                                    v-model="wrapForm.provinceId"
                                    clearable
                                    placeholder="请选择省区"
                                >
                                    <el-option
                                        v-for="provinceOption in provinceList"
                                        :label="provinceOption.province"
                                        :value="provinceOption.id"
                                        :key="provinceOption.id"
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
            <div class="content-item" v-show="active == 2">
                <div class="header">
                    <div class="headleft">
                        <span class="el-icon-tickets"></span>
                        <span style="color:#409eff">【{{wrapForm.businessDivistionName}}】</span>
                        <span
                            style="color:#409eff"
                        >【{{wrapForm.provinceName != ''? wrapForm.provinceName : '-'}}】</span>经销商协议合作折扣条款
                    </div>
                    <div class="headright">
                        <el-button
                            type="primary"
                            class="el-icon-circle-plus"
                            size="mini"
                            @click="commerceAdd"
                        >新增
                        </el-button>
                    </div>
                </div>
                <el-table
                    v-loading="loading"
                    element-loading-text="加载中"
                    :data="secondTableData.rows"
                    size="mini"
                    border
                    style="width: 100%"
                >
                    <el-table-column type="index" label="序号" align="center"></el-table-column>
                    <el-table-column prop="commerceCode" label="商业编码" align="center"></el-table-column>
                    <el-table-column prop="commerceName" label="名称" align="center"
                                     :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column label="商业" align="center">
                        <el-table-column prop="functionType" label="职能类型" align="center"></el-table-column>
                        <el-table-column
                            prop="agreementType"
                            label="选择分类"
                            align="center"
                            :formatter="function(row, column, cellValue, index){
                  if(cellValue == 0){
                    return '经销商'
                  }else if (cellValue == 1){
                    return '两票制经销商'
                  }else if (cellValue == 2){
                    return '终止认可'
                  }else if (cellValue == 3){
                    return '转分销商'
                  }
            }"
                        ></el-table-column>
                    </el-table-column>
                    <el-table-column label="协议生效日期" align="center">
                        <el-table-column prop="AgreementStartDate" label="起始" align="center" min-width="150px">
                            <template slot-scope="scope">
                                <el-date-picker
                                    :size="size"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    type="date"
                                    placeholder="请选择日期"
                                    style="width: 100%;"
                                    v-model="secondTableData.rows[scope.$index].AgreementStartDate"
                                ></el-date-picker>
                            </template>
                        </el-table-column>
                        <el-table-column prop="AgreementChangeDate" label="变更" align="center" min-width="150px">
                            <template slot-scope="scope">
                                <el-date-picker
                                    :size="size"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    type="date"
                                    placeholder="请选择日期"
                                    style="width: 100%;"
                                    v-model="secondTableData.rows[scope.$index].AgreementChangeDate"
                                ></el-date-picker>
                            </template>
                        </el-table-column>
                        <el-table-column prop="AgreementEndDate" label="截止" align="center" min-width="150px">
                            <template slot-scope="scope">
                                <el-date-picker
                                    :size="size"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    type="date"
                                    placeholder="请选择日期"
                                    style="width: 100%;"
                                    v-model="secondTableData.rows[scope.$index].AgreementEndDate"
                                ></el-date-picker>
                            </template>
                        </el-table-column>
                    </el-table-column>
                    <el-table-column label="付款方式" align="center">
                        <el-table-column
                            prop="lastPaymentType"
                            label="上次"
                            align="center"
                            :formatter="function(row, column, cellValue, index){
                return cellValue == '1' ? '赊销' : '预付款'
              }"
                        ></el-table-column>
                        <el-table-column prop="paymentType" label="本次" align="center" min-width="130px">
                            <template slot-scope="scope">
                                <el-select @change="typeChange(scope.$index, scope.row)" :size="size"
                                           v-model="secondTableData.rows[scope.$index].paymentType" placeholder="请选择">
                                    <el-option value="0" label="预付款"></el-option>
                                    <el-option value="1" label="赊销"></el-option>
                                </el-select>
                            </template>
                        </el-table-column>
                    </el-table-column>
                    <el-table-column label="本商业" align="center">
                        <el-table-column prop="achange" label="设定区间" align="center" min-width="150px">
                            <template slot-scope="scope">
                                <span v-show="secondTableData.rows[scope.$index].paymentType == '0'">预付款</span>
                                <el-select @change="changeDays(scope.$index)"
                                           v-model="secondTableData.rows[scope.$index].changeDay"
                                           v-show="secondTableData.rows[scope.$index].paymentType == '1'" :size="size"
                                           placeholder="请选择">
                                    <el-option value="0" label="1-30"></el-option>
                                    <el-option value="1" label="31-60"></el-option>
                                </el-select>
                            </template>
                        </el-table-column>
                        <el-table-column prop="stardDay" label="起始天数" align="center">
                            <template slot-scope="scope">
                                <span>{{scope.row.stardDay}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="endDay" label="截止天数" align="center">
                            <template slot-scope="scope">
                                <span>{{scope.row.endDay}}</span>
                            </template>
                        </el-table-column>
                    </el-table-column>
                    <el-table-column label="电汇付款" align="center">
                        <el-table-column prop="discount" label="标准折扣" align="center"></el-table-column>
                        <el-table-column prop="lastTheApplication" label="上次申请" align="center"></el-table-column>
                        <el-table-column prop="theApplication" label="本次申请" align="center" min-width="150px">
                            <template slot-scope="scope">
                                <el-input :size="size" v-model="scope.row.theApplication"
                                          placeholder="请输入本次申请"></el-input>
                            </template>
                        </el-table-column>
                    </el-table-column>
                    <el-table-column label="回款天数" align="center">
                        <el-table-column prop="lastReturnMoneyDay" label="上次" align="center"></el-table-column>
                        <el-table-column prop="returnMoneyDay" label="本次" align="center"></el-table-column>
                        <el-table-column prop="quarterPaymentPercent" label="季度预付款占比（%）"
                                         align="center"></el-table-column>
                    </el-table-column>
                    <el-table-column prop="promiseProvince" label="承诺省份" align="center"></el-table-column>
                    <el-table-column prop="messageStandard" label="信息直连标准" align="center"></el-table-column>
                    <el-table-column prop="flowStandard" label="流向折扣标准" align="center"></el-table-column>
                    <el-table-column prop="transportationDiscount" label="储运管理折扣率" align="center"></el-table-column>
                    <el-table-column prop="unloadPayer" label="卸载费支付方" align="center" min-width="120px">
                        <template slot-scope="scope">
                            <el-select v-model="secondTableData.rows[scope.$index].unloadPayer" :size="size"
                                       placeholder="请选择">
                                <el-option value="0" label="甲方"></el-option>
                                <el-option value="1" label="乙方"></el-option>
                            </el-select>
                        </template>
                    </el-table-column>
                    <el-table-column label="分子公司" align="center">
                        <el-table-column label="设定" align="center" min-width="120px;">
                            <template slot-scope="scope">
                                <el-button type="default" :size="size" @click.native="getBuiness(scope.$index)">点击设置
                                </el-button>
                            </template>
                        </el-table-column>
                        <el-table-column
                            prop="scmXsAgreementSubsidiaryList"
                            label="商业"
                            align="center"
                            :show-overflow-tooltip="true"
                            :formatter="function(row, column, cellValue, index) {
              let strList = []
              cellValue.forEach((item) => {
                strList.push(item.subsidiaryName)
              })
              return strList.join(' / ')
            }"
                        ></el-table-column>
                        <el-table-column
                            prop="scmXsAgreementSubsidiaryList"
                            label="家数"
                            align="center"
                            :formatter="function(row, column, cellValue, index){
              return cellValue.length
            }"
                        ></el-table-column>
                    </el-table-column>
                    <el-table-column label="商业产品特殊设置" align="center">
                        <el-table-column prop="isSpecial" label="是否特殊" align="center">
                            <template slot-scope="scope">
                                <el-checkbox
                                    v-model="scope.row.isSpecial"
                                    :checked="scope.row.isSpecial == '1'"
                                    true-label="1"
                                    false-label="0"
                                    @change="changeIsSpecial(scope.$index, scope.row)"
                                ></el-checkbox>
                            </template>
                        </el-table-column>
                        <el-table-column label="设置" align="center" min-width="120px;">
                            <template slot-scope="scope">
                                <el-button type="default" :size="size" :disabled="scope.row.isSpecial == '0'"
                                           @click.native="getProduct(scope.$index)">点击设置
                                </el-button>
                            </template>
                        </el-table-column>
                        <el-table-column
                            prop="scmXsAgreementSpecialProductList"
                            label="商品"
                            align="center"
                            min-width="150px"
                            :show-overflow-tooltip="true"
                            :formatter="function(row, column, cellValue, index) {
              let strList = []
              cellValue.forEach((item) => {
                strList.push(item.productName)
              })
              return strList.join(' / ')
            }"
                        >
                        </el-table-column>
                    </el-table-column>
                    <el-table-column prop="memo" label="申请备注" align="center" min-width="150px">
                        <template slot-scope="scope">
                            <el-input v-model="secondTableData.rows[scope.$index].memo" placeholder="请输入备注"
                                      :size="size"></el-input>
                        </template>
                    </el-table-column>
                    <el-table-column prop="isOverfulfil" label="是否可以超额发货" align="center">
                        <template slot-scope="scope">
                            <el-checkbox
                                v-model="secondTableData.rows[scope.$index].isOverfulfil"
                                :checked="scope.row.isOverfulfil == 1"
                                true-label="1"
                                false-label="0"
                            ></el-checkbox>
                        </template>
                    </el-table-column>
                    <el-table-column fixed="right" label="操作" align="center" minWidth="100">
                        <template slot-scope="scope">
                            <el-button
                                type="danger"
                                class="el-icon-delete"
                                size="mini"
                                @click="del(scope.$index,scope.row)"
                            >删除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>

                <el-dialog
                    title="新增"
                    width="60%"
                    :visible.sync="dialog.dialogVisible"
                    :close-on-click-modal="false"
                >
                    <el-table
                        :data="dialog.pageResult.rows"
                        highlight-current-row
                        ref="commerceList"
                        v-loading="dialog.loading"
                        element-loading-text="加载中"
                        border
                        stripe
                        max-height="500"
                        size="mini"
                        align="left"
                        style="width:100%;"
                        @selection-change="handleSelectionChange"
                    >
                        <!-- <el-table-column type="selection" width="40"></el-table-column> -->
                        <el-table-column type="selection" width="50" align="center"></el-table-column>
                        <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
                        <el-table-column
                            v-for="column in dialog.commerceList"
                            header-align="center"
                            align="center"
                            :prop="column.prop"
                            :label="column.label"
                            :show-overflow-tooltip="true"
                            :key="column.prop"
                            :min-width="column.minWidth"
                            :formatter="column.formatter"
                            :sortable="column.sortable==null?true:column.sortable"
                        ></el-table-column>
                    </el-table>
                    <!--分页栏-->
                    <div class="toolbar" style="padding:10px;">
                        <el-pagination
                            @current-change="refreshPageRequest"
                            @size-change="refreshPageSizeRequest"
                            background
                            :current-page="dialog.pageRequest.page"
                            :page-sizes="[10, 20, 30, 40, 50]"
                            :page-size="dialog.pageRequest.pageSize"
                            layout="sizes, total, prev, pager, next"
                            :total="dialog.pageResult.total"
                            style="float:right;"
                        ></el-pagination>
                    </div>
                    <div slot="footer" class="dialog-footer">
                        <el-button :size="size" @click.native="dialog.dialogVisible = false">取消</el-button>
                        <el-button :size="size" type="primary" @click.native="commerceAddConfirm"
                                   :disabled="dialog.addList.length <= 0">确定
                        </el-button>
                    </div>
                </el-dialog>
                <!-- 选择商业 -->
                <el-dialog
                    title="选择商业"
                    width="40%"
                    :visible.sync="transferDialog.dialogVisible"
                    :close-on-click-modal="false"
                    @close="clearBusiness"
                >
                    <div>
                        <el-transfer style="text-align: left; display: inline-block" v-model="transferDialog.value"
                                     :data="transferDialog.data"></el-transfer>
                    </div>
                    <div slot="footer" class="dialog-footer">
                        <el-button :size="size" @click.native="transferDialog.dialogVisible = false">取消</el-button>
                        <el-button :size="size" type="primary" @click.native="addBusiness"
                                   :disabled="transferDialog.value.length <= 0">确定
                        </el-button>
                    </div>
                </el-dialog>

                <el-dialog
                    title="选择商品"
                    width="40%"
                    :visible.sync="transferProductDialog.dialogVisible"
                    :close-on-click-modal="false"
                >
                    <div>
                        <el-transfer style="text-align: left; display: inline-block"
                                     v-model="transferProductDialog.value"
                                     :data="transferProductDialog.data"></el-transfer>
                    </div>
                    <div slot="footer" class="dialog-footer">
                        <el-button :size="size" @click.native="transferProductDialog.dialogVisible = false">取消
                        </el-button>
                        <el-button :size="size" type="primary" @click.native="addProduct"
                                   :disabled="transferProductDialog.value.length <= 0">确定
                        </el-button>
                    </div>
                </el-dialog>
            </div>
        </transition>
        <!--第三步-->
        <transition name="el-fade-in">
            <div class="content-item" v-show="active == 3">
                <div class="header">
                    <div class="headleft">
                        <span class="el-icon-tickets"></span>
                        <span style="color:#409eff">【{{wrapForm.businessDivistionName}}】</span>
                        <span
                            style="color:#409eff">【{{wrapForm.provinceName != ''? wrapForm.provinceName : '-'}}】</span>协议品规明细
                    </div>
                </div>
                <el-table
                    v-loading="loading"
                    element-loading-text="加载中"
                    :data="thirdTableData"
                    size="mini"
                    border
                    style="width: 100%"
                >
                    <el-table-column type="index" label="序号" align="center"></el-table-column>
                    <el-table-column prop="commerceGauge" label="药品品规" align="center"></el-table-column>
                    <el-table-column label="是否签约" align="center">
                        <el-table-column prop="lastIsSign" label="上年度" align="center">
                            <template slot-scope="scope">
                                <span v-if="scope.row.lastIsSign == 0" class="el-icon-close"></span>
                                <span v-else class="el-icon-check"></span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="isSign" label="本年度" align="center">
                            <template slot-scope="scope">
                                <el-checkbox
                                    v-model="scope.row.isSign"
                                    true-label="1"
                                    false-label="0"
                                ></el-checkbox>
                            </template>
                        </el-table-column>
                    </el-table-column>
                    <el-table-column prop="effectiveDate" label="生效日期" align="center"></el-table-column>
                    <el-table-column prop="delivery" :label="labelYear" align="center"></el-table-column>
                    <el-table-column
                        prop="gaugeType"
                        label="品规类型"
                        align="center"
                        :formatter="function(row, column, cellValue, index) {
            if(cellValue == 0){
              return '非OTC'
            }else if(cellValue == 1){
              return '甲类'
            }else {
              return '乙类'
            }
          }"
                    ></el-table-column>
                    <el-table-column label="本省中标情况" align="center">
                        <el-table-column prop="isBidding" label="是否中标" align="center">
                            <template slot-scope="scope">
                                <span v-if="scope.row.isBidding == 0" class="el-icon-close"></span>
                                <span v-else class="el-icon-check"></span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="isImplement" label="是否实施" align="center">
                            <template slot-scope="scope">
                                <span v-if="scope.row.isImplement == 0" class="el-icon-close"></span>
                                <span v-else class="el-icon-check"></span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="biddingPrice" label="实际中标价格" align="center"></el-table-column>
                    </el-table-column>
                    <el-table-column prop="achange" label="商业供货单价" align="center">
                        <el-table-column prop="lastSupplyPrice" label="上次" align="center"></el-table-column>
                        <el-table-column prop="supplyPrice" label="本次" align="center"></el-table-column>
                    </el-table-column>
                    <el-table-column prop="achange" label="毛利分析（%）" align="center">
                        <el-table-column prop="lastAllotGross" label="上次调拨毛利" align="center"></el-table-column>
                        <el-table-column prop="allotGross" label="本次调拨毛利" align="center" min-width="120px">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.allotGross" placeholder="请输入"
                                          @input="handleInput(scope.$index, scope.row)" :size="size"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="lastAcceptGross" label="上次承兑毛利" align="center"></el-table-column>
                        <el-table-column prop="acceptGross" label="本次承兑毛利" align="center" min-width="120px">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.acceptGross" placeholder="请输入"
                                          @input="handleInput(scope.$index, scope.row)" :size="size"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="lastPurePinGross" label="上次纯销毛利" align="center"></el-table-column>
                        <el-table-column prop="purePinGross" label="本次纯销毛利" align="center" min-width="120px">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.purePinGross" placeholder="请输入" :size="size"></el-input>
                            </template>
                        </el-table-column>
                    </el-table-column>
                    <el-table-column label="本次申请商业价格" align="center">
                        <el-table-column prop="wireTransferPrice" label="电汇调拨供货价格" align="center" min-width="120px">
                            <template slot-scope="scope">
                                <el-input :disabled="true" v-model="scope.row.wireTransferPrice" placeholder="请输入"
                                          :size="size"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="acceptPrice" label="承兑调拨供货价" align="center" min-width="120px">
                            <template slot-scope="scope">
                                <el-input :disabled="true" v-model="scope.row.acceptPrice" placeholder="请输入"
                                          :size="size"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="purePinPrice" label="纯销供货价" align="center" min-width="120px">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.purePinPrice" placeholder="请输入"
                                          @input="handleInput(scope.$index, scope.row)" :size="size"></el-input>
                            </template>
                        </el-table-column>
                    </el-table-column>
                    <el-table-column prop="memo" label="申请备注" align="center" min-width="150px">
                        <template slot-scope="scope">
                            <el-input v-model="scope.row.memo" placeholder="请输入" :size="size"></el-input>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </transition>
        <!--第四步-->
        <transition name="el-fade-in">
            <div class="content-item" v-show="active == 5">
                <div class="success">
                    <span class="el-icon-check"></span>
                    您已完成
                    <span style="color:#409eff">【{{wrapForm.businessDivistionName}}】</span>
                    <span style="color:#409eff">【{{wrapForm.provinceName != ''? wrapForm.provinceName : '-'}}】</span>经销商协议合作条款申请
                    <span style="color:#409eff">【{{firstStepData.agreementSummaryCode}}】</span>
                </div>
                <div class="success-bottom">
                    共
                    <span style="color: #409eff;">【{{totals?totals:0}}】</span>家经销商
                </div>
            </div>
        </transition>
    </div>
</template>
<script>
    import storage from "@/utils/storage";

    let timer;
    export default {
        name: "xsDealerClause",
        data() {
            return {
                size: "mini",
                totals:0,
                column: [
                    {
                        prop: "clauseCode",
                        label: "协议号",
                        minWidth: "200",
                        align: "center"
                    },
                    {
                        prop: "businessDivision",
                        label: "商务分区",
                        minWidth: "150",
                        align: "center"
                    },
                    {
                        prop: "province",
                        label: "省区",
                        minWidth: "150",
                        align: "center"
                    },
                    {
                        prop: "commerceCode",
                        label: "商业编码",
                        minWidth: "200",
                        align: "center"
                    },
                    {
                        prop: "commerceName",
                        label: "商业名称",
                        minWidth: "100",
                        align: "center"
                    },
                    {
                        prop: "functionType",
                        label: "商业类型",
                        minWidth: "100",
                        align: "center"
                    },
                    {
                        prop: "AgreementStartDate",
                        label: "协议生效日期起",
                        minWidth: "200",
                        align: "center"
                    },
                    {
                        prop: "AgreementChangeDate",
                        label: "协议最新变更日期",
                        minWidth: "150",
                        align: "center"
                    },
                    {
                        prop: "AgreementEndDate",
                        label: "协议生效日期止",
                        minWidth: "150",
                        align: "center"
                    },
                    {
                        prop: "paymentType",
                        label: "付款类型",
                        minWidth: "150",
                        align: "center",
                        formatter: function (row, column, cellValue, index) {
                            if (cellValue == 0) {
                                return '预付款'
                            } else if (cellValue == 1) {
                                return '赊销'
                            }
                        }
                    },
                    {
                        prop: "approvalState",
                        label: "流程状态",
                        minWidth: "150",
                        align: "center",
                        formatter: function (row, column, cellValue, index) {
                            if (cellValue == 0) {
                                return '未提交'
                            } else if (cellValue == 1) {
                                return '审批中'
                            } else if (cellValue == 2) {
                                return '已审批'
                            }
                        }
                    }
                ],
                searchForm: {
                    businessDivisionId: "",
                    provinceId: "",
                    businessDivision:"",
                    province:""
                },
                startShow: true,
                isEdit: false,
                active: 0,
                applyDate: "",
                labelPosition: "right",
                ownerId: "",
                total: 0,
                totalFirst: 0, //首页列表数据总数
                dialog: {
                    //新增弹框数据
                    pageRequest: {
                        page: 1,
                        pageSize: 10,
                        showTotal: true
                    },
                    pageResult: {}, //
                    loading: false,
                    dialogVisible: false,
                    commerceList: [
                        {prop: "approvalStateStr", label: "申请状态", minWidth: 150},
                        {prop: "acceptStateStr", label: "状态"},
                        {prop: "fileCode", label: "档案编号", minWidth: 150},
                        {
                            prop: "commerceType",
                            label: "企业类别",
                            minWidth: 100,
                            formatter: (row, column, cellValue, index) => {
                                return cellValue == "1" ? "医疗机构" : "药品经营企业";
                            }
                        },
                        {prop: "commerceCode", label: "客户编号", minWidth: 100},
                        {prop: "conpanyPersion", label: "企业负责人", minWidth: 110},
                        {prop: "commerceName", label: "商业名称", minWidth: 100},
                        {prop: "businessDivision", label: "商务分区", minWidth: 100},
                        {prop: "province", label: "省区"}
                    ],
                    addList: []//勾选的数据
                },
                transferDialog: {//商业勾选弹框
                    data: [],
                    value: [],
                    index: '',
                    dialogVisible: false
                },
                transferProductDialog: {//商品勾选弹框
                    data: [],
                    value: [],
                    index: '',
                    dialogVisible: false
                },
                labelYear: '',
                firstStepData: {},
                provinceList: [], //第一步
                searchProvinceList: [], //搜索省区下拉列表
                divisionList: [], //商务分区下拉列表
                firstTableData: [], //首页列表
                secondTableData: {},
                thirdTableData: [],
                // changeDay:'',//设定天数区间的值
                loading: false,
                search: [],
                pageRequest: {
                    page: 1,
                    pageSize: 10,
                    showTotal: true
                },
                wrapForm: {
                    businessDivisionId: "",
                    businessDivistionName: "",
                    provinceId: "",
                    provinceName: ""
                },
                wraprules: {
                    businessDivisionId: [
                        {required: true, message: "请选择商务分区", trigger: "change"}
                    ]
                    // provinceId: [{ required: true, message: "请选择省区", trigger: "change" }]
                }
            };
        },
        computed:{
          shangwu(){
              return this.searchForm.businessDivisionId
          },
          shengqu(){
              return this.searchForm.provinceId
          }
        },
        watch:{
            shangwu(val){
                let _this = this;
                if (val) {
                    _this.divisionList.map(function (v,i) {
                        if (v.id == val){
                            _this.searchForm.businessDivision = v.businessDivision
                        }
                    })
                }
            },
            shengqu(val){
                let _this = this;
                if (val) {
                    _this.searchProvinceList.map((v,i)=>{
                        if (v.id == val) {
                            _this.searchForm.province = v.province
                        }
                    })
                }
            }
        },
        methods: {
            del(index, row) {
                //第二步 删除商品
                this.$confirm("确认删除选中记录吗？", "提示", {
                    type: "warning"
                })
                    .then(() => {
                        this.$api.approval
                            .delscm({
                                url: "/scm/scmXsDealerClause/v1/remove/" + row.id
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
                    .catch(() => {
                    });
            },
            refreshPageRequest: function (page = 1) {
                this.pageRequest.page = page;
                this.findPage();
            },
            refreshPageSizeRequest: function (pageSize) {
                this.pageRequest.pageSize = pageSize;
                this.findPage();
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
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
                if (this.searchForm.businessDivisionId) {
                    this.search.push({
                        property: "businessDivision",
                        value: this.searchForm.businessDivision,
                        operation: "LIKE",
                        relation: "AND"
                    });
                }
                if (this.searchForm.provinceId) {
                    this.search.push({
                        property: "province",
                        value: this.searchForm.province,
                        operation: "LIKE",
                        relation: "AND"
                    });
                }
                if (this.searchForm.procurementCode) {
                    this.search.push({
                        property: "commerceName",
                        value: this.searchForm.procurementCode,
                        operation: "LIKE",
                        relation: "AND"
                    });
                }
                this.findPage(this.search);
            },
            //列表
            findPage(search = []) {
                this.loading = true;
                this.$api.base
                    .postScm({
                        url: "/scm/scmXsDealerClause/v1/firstList",
                        data: {
                            pageBean: this.pageRequest,
                            params: {},
                            querys: [
                                {
                                    property: "ownerId",
                                    value: this.ownerId,
                                    operation: "EQUAL",
                                    relation: "AND"
                                }
                            ].concat(search),
                            sorter: []
                        }
                    })
                    .then(res => {
                        if (res.state == false) {
                            this.$message({
                                message: "操作失败, " + res.shortMessage,
                                type: "error",
                                duration: 800
                            });
                        } else {
                            this.firstTableData = res.rows;
                            this.totalFirst = res.total;
                            this.loading = false;
                            this.search = [];
                        }
                    });
            },
            getSecondTable(search = []) {
                //第二步的列表
                this.loading = true;
                this.$api.base
                    .postScm({
                        url: "/scm/scmXsDealerClause/v1/firstList",
                        data: {
                            pageBean: {},
                            params: {},
                            querys: [
                                {
                                    property: "AgreementSummaryId",
                                    value: this.firstStepData.id,
                                    operation: "EQUAL",
                                    relation: "AND"
                                }
                            ].concat(search),
                            sorter: []
                        }
                    })
                    .then(res => {
                        if (res.state == false) {
                            this.$message({
                                message: "操作失败, " + res.shortMessage,
                                type: "error",
                                duration: 800
                            });
                            return;
                        }
                        let resData = res;
                        resData.rows.forEach((item) => {
                            let section = '';
                            if (item.stardDay == 31) {
                                section = '1'
                            } else if (item.stardDay == 1) {
                                section = '0';
                            }
                            ;
                            Object.assign(item, {
                                changeDay: section,
                            })
                        })
                        this.secondTableData = resData;
                        this.totals = this.secondTableData.rows.length;
                        /*for ( let i = 0,data = this.dialog.pageResult.rows; i < data.length; i++ ) {
                            this.secondTableData.rows[i].commerceFirstId = data[i].commerceFirstId
                        }*/
                        this.loading = false;
                    });
            },
            getThirdTable() {//获取第三步列表的方法
                this.$api.base.getScm({
                    url: '/scm/scmXsAgreementProductDetail/v1/getAgreementProduct/' + this.wrapForm.businessDivisionId + '/' + (this.wrapForm.provinceId ? this.wrapForm.provinceId : '-1') + '/' + this.ownerId + '/' + this.firstStepData.id
                }).then(res => {
                    if (res.state == false) {
                        this.$message({
                            message: "操作失败, " + res.shortMessage,
                            type: "error",
                            duration: 800
                        });
                        return;
                    }
                    if (!res.value[0].hasOwnProperty('acceptGross')) {
                        res.value.forEach((item, index) => {
                            Object.assign(item, {
                                supplyPrice: '',
                                allotGross: '',
                                acceptGross: '',
                                purePinGross: '',
                                acceptPrice: '',
                                wireTransferPrice: '',
                                purePinPrice: '',
                                agreementSummaryId: this.firstStepData.id
                            })
                        })
                    }

                    this.thirdTableData = res.value;
                })
            },
            buy() {
                this.startShow = false;
                this.active = 1;
            },
            nextStep() {
                if (this.active === 1) {
                    this.$refs.wrapForm1.validate(valid => {
                        if (valid) {
                            this.$api.base
                                .postScm({
                                    url: "/scm/scmXsAgreementSummary/v1/save",
                                    data: {
                                        businessDivision: this.wrapForm.businessDivistionName,
                                        businessDivisionId: this.wrapForm.businessDivisionId,
                                        ownerId: this.ownerId,
                                        province: this.wrapForm.provinceName,
                                        provinceId: this.wrapForm.provinceId
                                    }
                                })
                                .then(res => {
                                    if (res.state == false) {
                                        this.$message({
                                            message: "操作失败, " + res.shortMessage,
                                            type: "error",
                                            duration: 800
                                        });
                                        return;
                                    }
                                    this.firstStepData = res.value;
                                    this.active += 1;
                                });
                        }
                    });
                } else if (this.active === 2) {//
                    this.saveApply().then(res => {
                        this.active += 1;
                        this.getThirdTable();
                    });
                } else if (this.active === 3) {

                }
            },
            prevStep() {
                if (this.isEdit && this.active == 2) {
                    this.goback();
                    return;
                }
                this.active -= 1;
            },
            goback() {
                this.startShow = true;
                this.findPage();
                this.resetForm("wrapForm1");
                this.firstStepData = {};
                this.secondTableData = {};
                this.active = 0;
                this.isEdit = false;
            },
            saveApply() {//第二步申请暂存
                return new Promise((resolve, reject) => {
                    this.firstStepData.scmXsDealerClauseList = this.secondTableData.rows ? this.secondTableData.rows : [];
                    this.firstStepData.scmXsAgreementProductDetailList = this.thirdTableData.filter((item) => {
                        return item.isSign == '1'
                    })
                    this.$api.base
                        .postScm({
                            url: "/scm/scmXsAgreementSummary/v1/" + (this.active == 2 ? 'saveByDealerClause' : 'saveByAgreement'),
                            data: this.firstStepData
                        })
                        .then(res => {
                            if (res.state == false) {
                                this.$message({
                                    message: "操作失败, " + res.shortMessage,
                                    type: "error",
                                    duration: 800
                                });
                                reject('error');
                                return
                            } else {
                                this.$message({
                                    message: "暂存成功",
                                    type: "success",
                                    duration: 800
                                })
                            }
                            resolve('success')
                        });
                })

            },
            sendApply() {
                // 发起申请发起申请
                this.firstStepData.scmXsAgreementProductDetailList = this.thirdTableData;
                this.$api.base
                    .postScm({
                        url: "/scm/scmXsAgreementSummary/v1/sendApply",
                        data: this.firstStepData
                    })
                    .then(res => {
                        if (res.state === false) {
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
                        setTimeout(() => {
                            this.active = 5;
                            this.isEdit = false;
                            this.secondTableData = {};
                        }, 220);
                    });

            },
            getDivision() {
                //获取商务分区
                this.$api.common.getDivision().then(res => {
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
            changeDivision() {
                this.wrapForm.provinceId = "";
                this.wrapForm.provinceName = "";
                let divisionId = this.wrapForm.businessDivisionId; // 商务分区ID
                this.provinceList = this.divisionList.reduce(
                    (final, item, index, arr) => {
                        if (item.id == divisionId) {
                            final = item.provinceList;
                            this.wrapForm.businessDivistionName = item.businessDivision;
                        }
                        return final;
                    },
                    []
                );
            },
            changeProvince() {
                try {
                    this.wrapForm.provinceName = this.provinceList.filter((item, index) => {
                        return item.id === this.wrapForm.provinceId;
                    })[0]["province"];
                } catch (e) {
                    this.wrapForm.provinceName = "";
                }
            },
            changeSearchDivision() {
                this.searchForm.provinceId = "";
                let divisionId = this.searchForm.businessDivisionId; // 商务分区ID
                this.searchProvinceList = this.divisionList.reduce(
                    (final, item, index, arr) => {
                        if (item.id == divisionId) {
                            final = item.provinceList;
                        }
                        return final;
                    },
                    []
                );
            },
            getBuiness(index) {
                this.transferDialog.index = index;

                this.$api.zsj
                    .getZsj({
                        url: "/scm/scmZsjCommerce/v1/list",
                        data: {
                            pageBean: {},
                            params: {},
                            querys: [{
                                property: "commerceLevel",
                                value: '经销商',
                                operation: "EQUAL",
                                relation: "AND"
                            }],
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
                        ;
                        this.transferDialog.data = res.rows.reduce((final, item, index, arr) => {
                            final.push({
                                label: item.commerceName,
                                key: JSON.stringify({
                                    subsidiaryName: item.commerceName,
                                    subsidiaryId: item.id
                                })
                            });
                            return final;
                        }, []);
                        this.secondTableData.rows[this.transferDialog.index].scmXsAgreementSubsidiaryList.forEach((item) => {
                            this.transferDialog.value.push(JSON.stringify(item));
                        });
                        this.transferDialog.dialogVisible = true;
                    })
            },
            getProduct(index) {
                this.transferProductDialog.index = index;
                this.transferProductDialog.dialogVisible = true;
                this.$api.zsj
                    .getZsj({
                        url: "scm/scmZsjProduct/v1/firstList",
                        data: {
                            pageBean: {},
                            params: {},
                            querys: [{
                                property: "approvalState",
                                value: '2',
                                operation: "EQUAL",
                                relation: "AND"
                            }, {
                                property: "ownerId",
                                value: this.ownerId,
                                operation: "EQUAL",
                                relation: "AND"
                            }],
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
                        ;
                        this.transferProductDialog.data = res.rows.reduce((final, item, index, arr) => {
                            final.push({
                                label: item.productLineName,
                                key: JSON.stringify({
                                    productName: item.productName,
                                    productFirstId: item.id
                                })
                            });
                            return final;
                        }, [])
                    })
            },
            addBusiness() {
                this.secondTableData.rows[this.transferDialog.index].scmXsAgreementSubsidiaryList = [];
                this.transferDialog.value.forEach((item, index) => {
                    this.secondTableData.rows[this.transferDialog.index].scmXsAgreementSubsidiaryList.push(JSON.parse(item))
                });
                this.transferDialog.dialogVisible = false;
            },
            clearBusiness() {
                this.transferDialog.value = [];
                this.transferProductDialog.value = [];
            },
            addProduct() {
                this.secondTableData.rows[this.transferProductDialog.index].scmXsAgreementSpecialProductList = [];
                this.transferProductDialog.value.forEach((item, index) => {
                    this.secondTableData.rows[this.transferProductDialog.index].scmXsAgreementSpecialProductList.push(JSON.parse(item))
                });
                this.transferProductDialog.dialogVisible = false;
            },
            commerceAdd() {
                //第二步新增按钮
                //this.saveApply();
                this.dialog.dialogVisible = true;
                this.dialog.loading = true;
                this.$api.zsj
                    .getZsj({
                        url: "/scm/scmZsjCommerce/v1/agreementSendApplySuccessList",
                        data: {
                            pageBean: this.dialog.pageRequest,
                            params: {},
                            querys: [
                                {
                                    property: "approvalState",
                                    value: "2",
                                    operation: "EQUAL",
                                    relation: "AND"
                                },
                                {
                                    property: "businessDivisionId",
                                    value: this.wrapForm.businessDivisionId,
                                    operation: "EQUAL",
                                    relation: "AND"
                                }
                            ].concat(this.wrapForm.provinceId != '' ? {
                                property: "provinceId",
                                value: this.wrapForm.provinceId,
                                operation: "EQUAL",
                                relation: "AND"
                            } : []),
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
                        this.dialog.pageResult = res;
                        this.dialog.loading = false;
                    });
            },
            commerceAddConfirm() {
                this.firstStepData.scmXsDealerClauseList = this.dialog.addList.map((item, index) => {
                    item.agreementType = item.type;
                    delete item.id;
                    delete item.type;
                    return item;
                })
                this.$api.base.postScm({
                    url: '/scm/scmXsAgreementSummary/v1/saveByDealerClause',
                    data: this.firstStepData
                }).then(res => {
                    if (res.state == false) {
                        this.$message({
                            message: res.shortMessage,
                            type: "error",
                            duration: 800
                        });
                        return;
                    }
                    this.getSecondTable();
                    this.dialog.dialogVisible = false;
                })
            },
            handleSelectionChange(val) {
                this.dialog.addList = val
            },
            typeChange(index, row) {
                if (row.paymentType == '0') {
                    this.secondTableData.rows[index].stardDay = 0;
                    this.secondTableData.rows[index].endDay = 0;
                } else if (row.paymentType == '1') {
                    this.secondTableData.rows[index].stardDay = '';
                    this.secondTableData.rows[index].endDay = '';
                }
            },
            changeDays(index) {
                if (this.secondTableData.rows[index].changeDay == '0') {
                    this.secondTableData.rows[index].stardDay = 1;
                    this.secondTableData.rows[index].endDay = 30;
                } else if (this.secondTableData.rows[index].changeDay == '1') {
                    this.secondTableData.rows[index].stardDay = 31;
                    this.secondTableData.rows[index].endDay = 60;
                }
            },
            changeIsSpecial(index, row) {
                if (row.isSpecial == '0') {
                    this.secondTableData.rows[index].scmXsAgreementSpecialProductList = []
                }
            },
            getLabelYear() {
                var year = new Date().getFullYear();
                var str = `${year - 2}11-${year - 1}10发货额(万)`;
                this.labelYear = str;
            },
            handleInput(index, row) {
                if (Number(row.allotGross) < 0) {
                    this.thirdTableData[index].allotGross = ''
                } else if (Number(row.acceptGross) < 0) {
                    this.thirdTableData[index].acceptGross = ''
                } else if (row.purePinPrice == '' || Number(row.purePinPrice) < 0) {
                    this.thirdTableData[index].wireTransferPrice = '';
                    this.thirdTableData[index].acceptPrice = '';
                }

                this.thirdTableData[index].wireTransferPrice = row.purePinPrice * (Number(row.allotGross) + 1)
                this.thirdTableData[index].acceptPrice = row.wireTransferPrice * (Number(row.acceptGross) + 1)
            },
            toViewDetail(id) {
                this.$router.push({
                    path: '/xsgl/xsDealerClauseDetail',
                    query: {id: id}
                })
            },
            toEdit(id) {
                this.isEdit = true;
                this.$api.base
                    .getScm({
                        url: "/scm/scmXsAgreementSummary/v1/get/" + id
                    })
                    .then(res => {
                        if (res.state == false) {
                            this.$message({
                                message: "操作失败, " + res.shortMessage,
                                type: "error",
                                duration: 800
                            });
                            return;
                        }
                        res.scmXsDealerClauseList.forEach((item) => {
                            let section = '';
                            if (item.stardDay == 31) {
                                section = '1'
                            } else if (item.stardDay == 1) {
                                section = '0';
                            }
                            ;
                            Object.assign(item, {
                                changeDay: section,
                            })
                        })
                        this.secondTableData.rows = res.scmXsDealerClauseList;
                        this.thirdTableData = res.scmXsAgreementProductDetailList;
                        this.firstStepData = Object.assign({}, res);
                        this.wrapForm = {
                            businessDivisionId: res.businessDivisionId,
                            businessDivistionName: res.businessDivision,
                            provinceId: res.provinceId,
                            provinceName: res.province
                        }
                        this.startShow = false;
                        this.active = 2;
                    });
            }
        },
        mounted() {
            this.ownerId = storage.get("ownerId");
            this.findPage();
            this.getDivision();
            this.getLabelYear();
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
        background: none !important;
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
