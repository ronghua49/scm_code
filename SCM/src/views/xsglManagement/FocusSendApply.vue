<template>
    <div>


        <div class="searchHeader">
            <div class="searchheadleft">
                <el-button @click="showParentComp" size="mini" type="primary">返回</el-button>
            </div>

            <div class="searchheadcenter">{{componentParams.title}}</div>
            <div class="searchheadright">
                <div v-if="componentParams.flag !== 'focusSendDetails'&&componentParams.flag !== 'focusSendApproval'">
                    <el-button @click="saveApply" size="mini" type="primary">申请暂存</el-button>
                    <el-button @click="sendApply" size="mini" type="primary">发起申请</el-button>
                </div>

                <div v-if="componentParams.flag == 'focusSendApproval'">
                    <approval-btn :taskId="taskId" @handleSuccess="approvalSuccess"></approval-btn>
                </div>
            </div>
        </div>

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
                            label-position="right"
                            size="small"
                            :disabled="componentParams.detailsdisabled"
                            label-width="150px"
                            ref="baseInfoForm"
                            :model="baseInfoForm"
                            :rules="rules"
                            class="base-info-form">
                            <el-form-item label="供货商" prop="ownerName">
                                <el-select clearable v-model="baseInfoForm.ownerName" placeholder="请选择">
                                    <el-option v-for="(item,index) in supplyCommerceList"
                                               :key="item.id+index"
                                               :label="item.name"
                                               :value="item.name">

                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="减免合同次数理由" prop="reduceContractTime">
                                <el-select clearable v-model="baseInfoForm.reduceContractTime" placeholder="请选择">
                                    <el-option v-for="(item,index) in reduceReasonList"
                                               :key="item.value+index"
                                               :label="item.label"
                                               :value="item.label">

                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="经销商" prop="angecyId">
                                <el-select clearable @change="getSelectedAngecyCommerce(baseInfoForm.angecyId)"
                                           v-model="baseInfoForm.angecyId" placeholder="请选择">
                                    <el-option v-for="(item,index) in angecyCommerceList"
                                               :key="item.id+index"
                                               :label="item.commerceName+' ['+ item.clauseCode +']'"
                                               :value="item.id">

                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="合同编号" prop="allocationCode">
                                <el-input disabled v-model="baseInfoForm.allocationCode"
                                          placeholder="请输入合同编号"></el-input>
                            </el-form-item>
                            <el-form-item label="特求意见">
                                <el-input v-model="baseInfoForm.specialOpinion" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="购销方式" prop="paymentTypeStr">
                                <el-input disabled v-model="baseInfoForm.paymentTypeStr" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="签订时间" prop="dateOfSigning">
                                <div class="block">
                                    <el-date-picker style="width: 100%;"
                                                    v-model="baseInfoForm.dateOfSigning"
                                                    type="date"
                                                    value-format="yyyy-MM-dd HH:mm:ss"
                                                    placeholder="选择日期">
                                    </el-date-picker>
                                </div>
                            </el-form-item>
                        </el-form>
                    </el-main>
                </div>
                <!--商品信息表格数据-->
                <div class="table-section">
                    <div class="header">
                        <div class="header-left">
                            <i class="el-icon-tickets"></i>数据列表
                        </div>
                        <div class="header-right">
                            <el-button @click="addNewDialogFunc"
                                       :disabled="componentParams.detailsdisabled"
                                       size="mini" type="primary">
                                新增
                            </el-button>
                        </div>
                    </div>

                    <!-- 表格 -->
                    <el-table class="product-info-table"
                              :data="listData.pageResult.rows"
                              highlight-current-row v-loading="loading"
                              element-loading-text="加载中"
                              border stripe fit
                              max-height="500" size="mini"
                              align="left" style="width:100%;">

                        <el-table-column
                            v-for="column in listData.columns1"
                            header-align="center"
                            align="center"
                            :prop="column.prop"
                            :key="column.label"
                            :label="column.label"
                            :formatter="column.formatter"
                            :show-overflow-tooltip="true"
                            min-width="120">

                        </el-table-column>

                        <el-table-column prop="yearAndMonth" label="月份" min-width="140" align="center">
                            <template slot-scope="scope">
                                <el-date-picker style="width: 100%;" size="small" disabled
                                                v-model="scope.row.yearAndMonth"
                                                type="month"
                                                placeholder="选择月">
                                </el-date-picker>
                            </template>
                        </el-table-column>

                        <el-table-column prop="province" label="申请" align="center">
                            <el-table-column prop="achange" label="件装量" width="100" align="center"></el-table-column>
                            <el-table-column prop="theNumber" label="数量" width="100" align="center"></el-table-column>
                            <el-table-column prop="packageNum" label="件数" width="100" align="center"></el-table-column>
                            <el-table-column prop="unitPrice" label="含税单价" width="100" align="center"></el-table-column>
                            <el-table-column prop="sumPrice" label="货款金额" width="100" align="center"></el-table-column>
                        </el-table-column>

                        <el-table-column label="操作" align="center" fixed="right">
                            <template slot-scope="scope">
                                <div>
                                    <el-button size="mini"
                                               @click="deleteProductFunc(scope.$index)"
                                               :disabled="componentParams.detailsdisabled"
                                               type="danger">
                                        删除
                                    </el-button>
                                </div>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="pagination">
                        <el-pagination @current-change="yearInfoTableRefreshPageRequest"
                                       @size-change="yearInfoTableRefreshPageSizeRequest" background
                                       :current-page="yearInfoTable.pageRequest.page" :page-sizes="[10, 20, 30, 40, 50]"
                                       :page-size="yearInfoTable.pageRequest.pageSize"
                                       layout="sizes, total, prev, pager, next" :total="yearInfoTable.pageResult.total">
                        </el-pagination>
                    </div>

                </div>
                <!--账号信息表单-->
                <!--<div style="margin-top:20px;" class="admin-info-section">
                    <div class="header">
                        <div class="header-left">
                            <i class="el-icon-tickets"></i>账号信息
                        </div>
                        <div class="header-right"></div>
                    </div>
                    <el-main>
                        <el-form label-position="right" size="small" label-width="150px" ref="adminInfoForm" :rules="adminInfoFormRules" class="admin-info-form">
                            <el-form-item label="甲方单位(销货方)">
                                <el-input disabled v-model="supplyCommerceSelected.name" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="乙方单位(购货方)">
                                <el-input disabled v-model="partyBDetails.dealer" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="地址">
                                <el-input disabled v-model="supplyCommerceSelected.address" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="地址">
                                <el-input disabled v-model="partyBDetails.yaddress" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="开户行">
                                <el-input disabled v-model="supplyCommerceSelected.openBank" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="开户行">
                                <el-input disabled v-model="partyBDetails.yopenBank" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="账号">
                                <el-input disabled v-model="supplyCommerceSelected.bankCode" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="账号">
                                <el-input disabled v-model="partyBDetails.ybankCode" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="税号">
                                <el-input disabled v-model="supplyCommerceSelected.dutyParagraph" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="税号">
                                <el-input disabled v-model="partyBDetails.ydutyCode" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="电话">
                                <el-input disabled v-model="supplyCommerceSelected.phone" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="电话">
                                <el-input disabled v-model="partyBDetails.yphone" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="邮政编号">
                                <el-input disabled v-model="supplyCommerceSelected.postcode" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="邮政编号">
                                <el-input disabled v-model="partyBDetails.ypostcode" placeholder="请输入内容"></el-input>
                            </el-form-item>
                        </el-form>
                    </el-main>
                </div>
-->
                <!--各品规发货滚动表 -- 暂时没有接口 -->
                <div class="table-section">
                    <div class="header">
                        <div class="header-left">
                            <i class="el-icon-tickets"></i>各品规发货滚动表
                        </div>
                        <div class="header-right">
                            <el-button size="mini" type="primary"><i class="el-icon-arrow-left">上一年</i></el-button>
                            <el-button size="mini" type="primary">下一年<i class="el-icon-arrow-right"></i></el-button>
                        </div>
                    </div>
                    <!--表格栏-->
                    <el-table class="year-info-table"
                              :data="yearInfoTable.pageResult.rows"
                              highlight-current-row
                              v-loading="yearInfoTable.loading"
                              element-loading-text="加载中"
                              border stripe fit
                              max-height="500"
                              size="mini"
                              align="left"
                              style="width:100%;">
                        <el-table-column
                            show-overflow-tooltip
                            v-for="(item,index) in yearInfoTable.columns"
                            :key="item.prop+index"
                            :prop="item.prop"
                            :label="item.label"
                            :width="item.minwidth"
                            :formatter="item.formatter" :align="item.align">
                        </el-table-column>

                    </el-table>

                    <div class="pagination">
                        <el-pagination
                            @current-change="yearInfoTableRefreshPageRequest"
                            @size-change="yearInfoTableRefreshPageSizeRequest"
                            background :current-page="yearInfoTable.pageRequest.page"
                            :page-sizes="[10, 20, 30, 40, 50]"
                            :page-size="yearInfoTable.pageRequest.pageSize"
                            layout="sizes, total, prev, pager, next"
                            :total="yearInfoTable.pageResult.total">
                        </el-pagination>
                    </div>
                </div>
            </div>
        </transition>


        <!-- 添加可用药品 dialog -->
        <el-dialog
            custom-class="business-accept-status-apply-addnew-dialog"
            class="addnew-dialog"
            title="添加商品" width="70%"
            :visible.sync="addNewDialogVisible" v-if="show">
            <el-table @selection-change="getDialogSelectedData"
                      :loading="addnewDialog.loading"
                      element-loading-text="加载中" :data="addnewDialog.pageResult.rows"
                      size="mini" border>


                <el-table-column type="selection" width="55">
                </el-table-column>

                <el-table-column
                    :show-overflow-tooltip="showOverflowTooltip"
                    v-for="(item,index) in addnewDialog.column"
                    :key="item.prop+index"
                    :prop="item.prop"
                    :label="item.label"
                    :width="item.minwidth"
                    :formatter="item.formatter" :align="item.align">
                </el-table-column>

                <el-table-column prop="yearAndMonth" label="月份" min-width="140" align="center">
                    <template slot-scope="scope">
                        <el-date-picker style="width: 100%;" size="small"
                                        v-model="scope.row.yearAndMonth"
                                        type="month"
                                        placeholder="选择月">
                        </el-date-picker>
                    </template>

                </el-table-column>

                <el-table-column prop="achange" label="件装量" width="100" align="center">
                    <template slot-scope="scope">
                        <el-select placeholder="请选择件装量" size="small" style="width: 100%;"
                                   @change="numberChanng(scope.row,scope.$index)"
                                   v-model="scope.row.achange">
                            <el-option v-for="(item,index) in tabarr[scope.$index]"
                                       :label="item.achage"
                                       :key="'ins'+index"
                                       :value="item.achage">

                            </el-option>
                        </el-select>
                    </template>
                </el-table-column>
                <el-table-column prop="surplusSUm" label="剩余数量" width="100" align="center"></el-table-column>
                <el-table-column prop="theNumber" label="数量" width="100" align="center">
                    <template slot-scope="scope">
                        <el-input placeholder="输入数量" @input.native="numberChanng(scope.row,scope.$index)" size="small"
                                  style="width: 100%;" v-model="scope.row.theNumber"></el-input>
                    </template>
                </el-table-column>
                <el-table-column prop="packageNum" label="件数" width="100" align="center"></el-table-column>
                <el-table-column prop="unitPrice" label="含税单价" width="100" align="center"></el-table-column>
                <el-table-column prop="sumPrice" label="货款金额" width="100" align="center"></el-table-column>

            </el-table>
            <div class="pagination">
                <el-pagination @current-change="addnewDialogRefreshPageRequest"
                               @size-change="addnewDialogRefreshPageSizeRequest" background
                               :current-page="addnewDialog.pageRequest.page" :page-sizes="[10, 20, 30, 40, 50]"
                               :page-size="addnewDialog.pageRequest.pageSize" layout="sizes, total, prev, pager, next"
                               :total="addnewDialog.pageResult.total">
                </el-pagination>
            </div>
            <el-row>
                <el-button @click="dialogCancel">取消</el-button>
                <el-button @click="dialogConfirm" type="primary">确定</el-button>
            </el-row>


        </el-dialog>
        <!-- 参考消息 dialog -->
        <el-dialog custom-class="business-accept-status-apply-addnew-dialog" title="参考消息" width="70%"
                   :visible.sync="referenceDialogVisible">
            <el-table :loading="referenceDialog.loading" element-loading-text="加载中"
                      :data="referenceDialog.pageResult.rows" size="mini" border>
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column show-overflow-tooltip v-for="(item,index) in referenceDialog.column"
                                 :key="item.prop+index" :prop="item.prop" :label="item.label" :width="item.minwidth"
                                 :formatter="item.formatter" :align="item.align">
                </el-table-column>
            </el-table>
        </el-dialog>

        <!--发起申请成功-->
        <transition name="el-fade-in">
            <div v-if="applySteps.step === '1'" class="apply-success-section">
                <div class="success">
                    <span class="el-icon-check"></span> 您已完成
                    <!-- <span style="color: #409EFF;">【{{searchForm.businessDivision}}】</span>
            <span style="color: #409EFF;">【{{searchForm.province}}】</span> -->
                    集中发货合同申请，审批号
                    <span style="color: #409EFF;">【{{applySteps.completeTitle}}】</span>
                </div>
                <div class="success-bottom">共
                    <span style="color: #409EFF;">【{{pageResult.currentPageList.length}}】</span>家商业
                </div>
            </div>
        </transition>

        {{msglll}}
    </div>

</template>

<script>
    import storage from "@/utils/storage";
    import {format} from "@/utils/datetime";
    import approvalBtn from "@/components/approval/ApprovalBtn";

    export default {
        name: "FocusSendApply",
        components: {
            "approval-btn": approvalBtn
        },
        props: ['pageState', 'taskId'],
        data() {
            return {
                msglll: "",
                tabarr: [],
                show: true,
                showOverflowTooltip: true,
                disabledinput: false,
                referenceDialogVisible: false,
                componentParams: {
                    flag: '', // 区分新增/编辑/查看详情
                    id: '',
                    title: '集中发货合同申请',
                    detailsdisabled: false
                },
                supplyCommerceList: [], // 供应商列表
                angecyCommerceList: [], // 经销商列表
                angecyCommerceSelected: {}, // 保存下拉框选择的经销商对象
                supplyCommerceSelected: {}, // 保存下拉框选择的供货商对象 -- 甲方
                partyBDetails: {}, // -- 乙方信息
                reduceReasonList: [{
                    label: '无需减免',
                    value: '1'
                },
                    {
                        label: 'SMS发货',
                        value: '1'
                    },
                    {
                        label: '新品发货',
                        value: '1'
                    },
                    {
                        label: '短缺品新来品',
                        value: '1'
                    },
                    {
                        label: '预估不足',
                        value: '1'
                    },
                    {
                        label: 'BU特批发货',
                        value: '1'
                    },
                    {
                        label: '备货不足',
                        value: '1'
                    },
                ],
                rules: {
                    ownerName: [
                        {required: true, message: '请选择供货商', trigger: 'change'}
                    ],
                    reduceContractTime: [
                        {required: true, message: '请选择减免合同次数理由', trigger: 'change'}
                    ],
                    angecyId: [
                        {required: true, message: '请选择经销商', trigger: 'change'}
                    ],
                    allocationCode: [
                        {required: true, message: '请输入合同编号', trigger: 'blur'}
                    ],
                    paymentTypeStr: [
                        {required: true, message: '请选择购销方式', trigger: 'blur'}
                    ],
                    dateOfSigning: [
                        {required: true, message: '请选择签订日期', trigger: 'change'}
                    ],

                },
                // 基础信息表单
                baseInfoForm: {
                    id: "",
                    ownerName: "",
                    reduceContractTime: "",
                    angecyId: '',
                    allocationCode: '',
                    paymentTypeStr: '',
                    dateOfSigning: ''

                },
                // 商品信息表格
                productInfoTable: {},
                // 账号信息表单
                adminInfoForm: {},
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
                applySteps: {
                    step: '0',
                    completeTitle: ''
                },
                addNewDialogVisible: false,
                addnewInnerVisible: false,
                addnewDialog: {
                    loading: false,
                    value4: '',
                    pageRequest: {
                        rows: [],
                        page: 1,
                        pageSize: 10,
                        showTotal: true,
                    },
                    pageResult: {
                        rows: []
                    },
                    column: [{
                        prop: "medicineType",
                        label: "药品类型",
                        align: "center"
                    },
                        {
                            prop: "commonName",
                            label: "药品名称",
                            align: "center"
                        },
                        {
                            prop: "productGauge",
                            label: "规格",
                            align: "center"
                        },
                        {
                            prop: "unit",
                            label: "单位",
                            align: "center"
                        },
                        {
                            prop: "suppyType",
                            label: "药品供应状态",
                            align: "center",
                            formatter: function (a, b, value) {
                                if (value == "1") {
                                    return "正常供应";
                                } else {
                                    return "禁止供应";
                                }
                            }
                        },
                        {
                            prop: "suggestApplyNum",
                            label: "建议本次申请量",
                            align: "center"
                        },
                        {
                            prop: "lastShipmentDate",
                            label: "上次发货时间",
                            align: "center",
                            formatter: function (a, b, value) {
                                return format(value);
                            }
                        },
                        {
                            prop: "smscurrentMonthSales",
                            label: "省区本月SMS剩余任务量",
                            align: "center"
                        },
                    ],
                    selectedListData: []

                },
                // 参考消息 -- dialog
                referenceDialog: {
                    pageResult: {},
                    column: []
                },
                // 各品规发货滚动表
                yearInfoTable: {
                    loading: false,
                    pageRequest: {
                        page: 1,
                        pageSize: 10,
                        showTotal: true,
                    },
                    pageResult: {
                        rows: []
                    },
                    columns: [{
                        prop: "businessDivision",
                        label: "药品",
                        align: "center"
                    },
                        {
                            prop: "province",
                            label: "年度",
                            align: "center"
                        },
                        {
                            prop: "commerceCode",
                            label: "一月",
                            align: "center"
                        },
                        {
                            prop: "commerceName",
                            label: "二月",
                            align: "center"
                        },
                        {
                            prop: "conpanyPersion",
                            label: "三月",
                            align: "center"
                        },
                        {
                            prop: "enterDate",
                            label: "四月",
                            align: "center"
                        },
                        {
                            prop: "acceptStateStr",
                            label: "五月",
                            align: "center"
                        },
                        {
                            prop: "acceptStateStr6",
                            label: "六月",
                            align: "center"
                        },
                        {
                            prop: "acceptStateStr7",
                            label: "七月",
                            align: "center"
                        },
                        {
                            prop: "acceptStateStr8",
                            label: "八月",
                            align: "center"
                        },
                        {
                            prop: "acceptStateStr9",
                            label: "九月",
                            align: "center"
                        },
                        {
                            prop: "acceptStateStr10",
                            label: "十月",
                            align: "center"
                        },
                        {
                            prop: "acceptStateStr11",
                            label: "十一月",
                            align: "center"
                        },
                        {
                            prop: "acceptStateStr12",
                            label: "十二月",
                            align: "center"
                        },
                    ]
                },
                //  数据列表
                listData: {
                    loading: false,
                    pageRequest: {
                        rows: [],
                        page: 1,
                        pageSize: 10,
                        showTotal: true,
                    },
                    pageResult: {
                        rows: []
                    },
                    columns1: [{
                        prop: "medicineType",
                        label: "药品类型"
                    },
                        {
                            prop: "commonName",
                            label: "药品名称"
                        },
                        {
                            prop: "productGauge",
                            label: "规格"
                        },
                        {
                            prop: "unit",
                            label: "单位"
                        },
                        {
                            prop: "suppyType",
                            label: "药品供应状态",
                            formatter: function (a, b, value) {
                                if (value == "0") {
                                    return "正常供应";
                                } else {
                                    return "禁止供应";
                                }
                            }
                        },
                        {
                            prop: "suggestApplyNum",
                            label: "建议本次申请量"
                        },
                        {
                            prop: "lastShipmentDate",
                            label: "上次发货时间",
                            formatter: function (a, b, value) {
                                return format(value);
                            }
                        },
                        {
                            prop: "smscurrentMonthSales",
                            label: "省区本月SMS剩余任务量"
                        },
                    ],
                    selectedListData: []
                },
                // 月份选择
            };
        },
        mounted() {
            this.ownerId = storage.get("ownerId");
            this.getSupplyCommerceList();
            this.getAngecyCommerceList();
        },
        computed: {
            angecyId(){
                return this.baseInfoForm.angecyId
            }
        },
        watch: {
            pageState: "getId",
            angecyId(val) {
                if (val) {
                    let self = this;
                    self.angecyCommerceList.forEach(function (item, index) {
                        if (item.id === val) {
                            self.angecyCommerceSelected = item;
                            self.baseInfoForm.commerceCode = item.commerceCode;
                            self.baseInfoForm.commerceFirstId = item.commerceFirstId;
                        }
                    });
                    self.baseInfoForm.paymentType = self.angecyCommerceSelected.paymentType;
                    self.baseInfoForm.paymentTypeStr = self.angecyCommerceSelected.paymentType === '0' ? '预付款' : '赊销';
                    self.baseInfoForm.BigContractId = self.angecyCommerceSelected.id;
                }
            },
        },
        methods: {
            // 获取选中的经销商
            getSelectedAngecyCommerce(param) {
                let self = this;
                self.angecyCommerceList.forEach(function (item, index) {
                    if (item.id === param) {
                        self.angecyCommerceSelected = item;
                        self.baseInfoForm.commerceCode = item.commerceCode;
                        self.baseInfoForm.commerceFirstId = item.commerceFirstId;
                    }
                });
                self.listData.pageResult.rows.splice(0);
                self.baseInfoForm.paymentType = self.angecyCommerceSelected.paymentType;
                self.baseInfoForm.paymentTypeStr = self.angecyCommerceSelected.paymentType === '0' ? '预付款' : '赊销';
                self.baseInfoForm.BigContractId = self.angecyCommerceSelected.id;
                self.getPartyBDetails(self.angecyCommerceSelected.id);
            },
            getId(val) {
                let self = this;
                if (val) {
                    let middleArr = self.pageState.split(',');
                    self.componentParams.flag = middleArr[0];
                    self.componentParams.id = middleArr[1];
                    if (self.componentParams.flag == 'focusSendApply') {
                        self.componentParams.title = '集中发货合同申请';
                        self.getCode();
                    }
                    console.log(self.componentParams.flag == 'focusSendEdit');
                    if (self.componentParams.flag == 'focusSendEdit') {
                        self.componentParams.title = '集中发货合同申请';
                        self.getDetails(self.componentParams.id)
                    }

                    if (self.componentParams.flag == 'focusSendDetails') {
                        self.componentParams.title = '集中发货合同详情';
                        self.componentParams.detailsdisabled = true;
                        self.disabledinput = true;
                        self.getDetails(self.componentParams.id)
                    }
                    if (self.componentParams.flag == 'focusSendApproval') {
                        self.componentParams.title = '集中发货合同审批';
                        self.componentParams.detailsdisabled = true;
                        self.disabledinput = true;
                        self.getDetailsApproval(self.componentParams.id);
                    }
                }

            },
            formatDate(row, column, val) {
                if (val) {
                    return val.split(' ')[0];
                }
            },
            addnewDialogRefreshPageRequest: function (page = 1) {
                this.addnewDialog.pageRequest.page = page;
                this.getDialogListData();
            },
            addnewDialogRefreshPageSizeRequest: function (pageSize) {
                this.addnewDialog.pageRequest.pageSize = pageSize;
                this.getDialogListData();
            },
            // 各品规发货滚动表分页函数
            yearInfoTableRefreshPageRequest: function (page = 1) {
                this.yearInfoTable.pageRequest.page = page;
                //this.findPage();
            },
            yearInfoTableRefreshPageSizeRequest: function (pageSize) {
                this.yearInfoTable.pageRequest.pageSize = pageSize;
                //this.findPage();
            },
            numberChanng(row, index) {
                let _this = this;

                console.log(row.achange, row.theNumber, row.surplusSUm, index);
                if (row.theNumber&&row.achange) {
                    if (row.theNumber > row.surplusSUm) {
                        _this.addnewDialog.pageResult.rows[index].theNumber = row.surplusSUm;
                    }
                    _this.addnewDialog.pageResult.rows[index].packageNum = Math.ceil(
                        row.theNumber / row.achange
                    );
                    _this.addnewDialog.pageResult.rows[index].sumPrice = row.theNumber * row.unitPrice;

                } else {
                    _this.addnewDialog.pageResult.rows[index].packageNum = "";
                    _this.addnewDialog.pageResult.rows[index].sumPrice = "";
                }


            },
            // 获取供应商列表
            getSupplyCommerceList() {
                let self = this;
                self.$api.xsgl
                    .get({
                        vueInstance: self,
                        url: "/scm/scmZsjTXCompany/v1/getByOwnerId/" + self.ownerId
                    }, function (res) {
                        console.log(res);
                        self.supplyCommerceList.push(res.value);
                        self.baseInfoForm.ownerName = res.value.name;
                        self.supplyCommerceSelected = res.value;

                    });
            },
            // 获取经销商列表
            getAngecyCommerceList() {
                let self = this;
                self.$api.xsgl.post({
                    vueInstance: self,
                    url: "/scm/scmXsDealerClause/v1/list",
                    data: {
                        pageBean: {
                            page: 1,
                            pageSize: 500,
                            showTotal: true
                        },
                        querys: [{
                            property: "ownerId",
                            value: self.ownerId,
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
                }, function (res) {
                    self.angecyCommerceList = res.rows;

                });
            },

            // 获取乙方信息
            getPartyBDetails(id) {
                let self = this;
                self.$api.xsgl.post({
                    vueInstance: self,
                    url: "/scm/scmXsBigContract/v1/bigcontractGetYDetails",
                    data: {
                        pageBean: {
                            page: 1,
                            pageSize: 500,
                            showTotal: true
                        },
                        querys: [{
                            property: "DealerClauseId",
                            value: id,
                            operation: "EQUAL",
                            relation: "AND"
                        }]
                    }
                }, function (res) {
                    if (res.rows.length > 0) {
                        self.partyBDetails = res.rows[0];
                        //console.log(self.partyBDetails);
                    }
                });
            },
            // 获取申请主页面的选中的列表数据
            getOuterSelectedList(list) {
                let self = this;
                self.selectedList = [];
                list.forEach(element => {
                    self.selectedList.push(element);
                });
            },
            // 删除选中行
            deleteSelectedRow() {
                let self = this;
                // 删除当前页列表数据
                for (let i = self.pageResult.currentPageList.length - 1; i >= 0; i--) {
                    for (let j = 0; j < self.selectedList.length; j++) {
                        if (self.pageResult.currentPageList[i].id === self.selectedList[j].id) {
                            self.pageResult.currentPageList.splice(i, 1);
                            break;
                        }
                    }
                }
                // 删除总列表数据
                for (let i = self.pageResult.rows.length - 1; i >= 0; i--) {
                    for (let j = 0; j < self.selectedList.length; j++) {
                        if (self.pageResult.rows[i].id === self.selectedList[j].id) {
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
                self.pageResult.rows.splice(self.pageRequest.pageSize * self.pageRequest.page - self.pageRequest.pageSize + index, 1);

                self.findPage();
            },


            // 向父组件传值
            showParentComp() {
                this.$emit("closeapplypage", {});
            },
            addNewDialogFunc() {
                let self = this;
                self.show = true;

                if (!self.baseInfoForm.angecyId || !self.baseInfoForm.ownerName) {
                    self.$message({
                        message: "请选择供货商和经销商！",
                        type: "error",
                        duration: 2000
                    });
                    return;
                }
                self.getDialogListData();
            },
            //   删除按钮
            deleteProductFunc(index) {

                this.listData.pageResult.rows.splice(index, 1)
            },
            // 显示弹窗时请求弹窗中的数据列表
            getDialogListData() {
                let self = this;
                self.$api.xsgl.get({
                    vueInstance: self,
                    url: '/scm/scmXsFocusSend/v1/getProduct/' + self.angecyCommerceSelected.commerceCode + '/' + self.ownerId + '/' + self.baseInfoForm.BigContractId,
                }).then((res) => {
                    if (res.state == false) {
                        self.$message({
                            message: '系统错误',
                            type: 'error',
                            duration: 2000
                        });
                        return;
                    }
                    self.addNewDialogVisible = true;
                    res.value.forEach((v, i) => {
                        delete res.value[i].theNumber;
                        delete v.sumPrice;
                    });
                    var _ = require("lodash");
                    let list = res.value;
                    list.forEach((item, index) => {
                        this.$api.xsgl.post({
                            url: '/scm/ScmZsjAchage/v1/list',
                            data: {
                                "pageBean": {
                                    "page": 1,
                                    "pageSize": 1000,
                                    "showTotal": true
                                },
                                "params": {},
                                "querys": [{
                                    "hasInitValue": false,
                                    "operation": "EQUAL",
                                    "property": "productCode",
                                    "relation": "AND",
                                    "value": item.productCode //this.data.row.productCode
                                }],
                                "sorter": []
                            }
                        }).then(response => {
                            self.loading = false;
                            self.addnewDialog.pageResult.rows = res.value;
                            self.addnewDialog.pageResult.rows[index].achange = response.rows[0].achage;
                            self.addnewDialog.pageResult.total = res.total;
                            this.$nextTick(() => {
                                this.$set(this.tabarr, index, _.merge(this.tabarr[index], response.rows));
                            });
                        })
                    })
                });
            },
            // 数据列表
            dialogCancel() {
                let self = this;
                self.addnewDialog.pageResult.rows.splice(0);
                self.addnewDialog.pageRequest.page = 1;
                self.addnewDialog.pageRequest.pageSize = 10;
                self.addnewDialog.pageRequest.showTotal = true;
                self.addnewDialog.selectedListData.splice(0);
                self.addNewDialogVisible = false;

            },
            dialogConfirm() {

                let self = this;
                for (let i = 0; i < self.addnewDialog.selectedListData.length; i++) {
                    self.addnewDialog.selectedListData[i].bigContractProductId = self.addnewDialog.selectedListData[i].id;
                    if (!self.addnewDialog.selectedListData[i].yearAndMonth || !self.addnewDialog.selectedListData[i].achange || !self.addnewDialog.selectedListData[i].theNumber) {
                        return this.$message({
                            type: "warning",
                            message: "请选择参数"
                        })
                    }

                    delete self.addnewDialog.selectedListData[i].id;
                    if (self.addnewDialog.selectedListData[i].acceptState === '-1') {
                        self.addnewDialog.selectedListData[i].acceptState = '0';
                    }
                    self.pageResult.rows.push(self.addnewDialog.selectedListData[i]);
                }
                this.show = false
                //把数据带到表格
                let arr = [];
                arr = arr.concat(this.addnewDialog.selectedListData);
                if (arr.length > 0) {
                    arr.map(function (v, index) {
                        self.listData.pageResult.rows.push(v)
                    })
                }


                let repetitionMark = false;

                /*if(self.addnewDialog.selectedListData.length > 0) {

                    for(let i = self.addnewDialog.selectedListData.length - 1; i >= 0; i--) {
                        for(let j = 0; j < self.pageResult.rows.length; j++) {
                            // 防止重复添加
                            //console.log('selectedListData[' + i+'].id = ' + self.addnewDialog.selectedListData[i].id + '-----------------' + 'self.pageResult.rows[' + j +'].id' + self.pageResult.rows[j].id);
                            if(self.addnewDialog.selectedListData[i].commerceCode === self.pageResult.rows[j].commerceCode) {
                                self.addnewDialog.selectedListData.splice(i, 1);
                                repetitionMark = true;
                                break;
                            }
                        }
                    }


                    // 添加完数据以后重新查询当前页数据

                }*/
                if (repetitionMark) {
                    self.addnewInnerVisible = true;
                }
                //self.pageResult.rows = self.addnewDialog.selectedListData;
                setTimeout(function () {
                    self.dialogCancel();
                }, 300);

                console.log(this.listData.pageResult.rows)
            },
            getDialogSelectedData(param) {
                let self = this;
                console.log(123)
                console.log(param);
                self.addnewDialog.selectedListData = param;

            },
            // 申请暂存
            saveApply() {
                let self = this;
                let requestData = {
                    "ownerId": self.ownerId,
                    "ownerName": self.baseInfoForm.ownerName, // 供货商名字
                    "dealerClauseId": self.baseInfoForm.angecyId, // 大合同id
                    "commerceCode": self.angecyCommerceSelected.commerceCode, // 商业编号
                    "commerceName": self.angecyCommerceSelected.commerceName, // 商业名称
                    "reduceContractTime": self.baseInfoForm.reduceContractTime, // 减免合同次数理由
                    "specialOpinion": self.baseInfoForm.specialOpinion, // 特求意见
                    "focusSendCode": self.baseInfoForm.allocationCode,//合同编号
                    "commerceFirstId": self.baseInfoForm.commerceFirstId,//商业首营ID
                    "marketingWay": self.baseInfoForm.paymentTypeStr,//购销方式
                    "dateOfSigning": self.baseInfoForm.dateOfSigning,//签订时间
                    "id": self.baseInfoForm.id,
                    "scmXsFocusSendProductList": ""
                };
                // 要校验的字段
                let checkKey = [{
                    pro: 'ownerId',
                    tips: '货主ID'
                },
                    {
                        pro: 'ownerName',
                        tips: '供货商'
                    },
                    {
                        pro: 'commerceCode',
                        tips: '经销商'
                    },
                    // {
                    // 	pro: 'reduceContractTime',
                    // 	tips: '减免合同次数理由'
                    // },
                ]
                for (let i = 0; i < checkKey.length; i++) {
                    if (!requestData[checkKey[i].pro]) {
                        self.$message({
                            message: '请选择' + checkKey[i].tips,
                            type: 'error',
                            duration: 2000
                        });
                        return;
                    }
                }
                if (self.baseInfoForm.ownerName) { // 供货商名字
                    requestData.ownerName = self.baseInfoForm.ownerName
                }
                if (self.componentParams.id) {
                    requestData.id = self.componentParams.id;
                }

                requestData.scmXsFocusSendProductList = this.listData.pageResult.rows
                // if(requestData.scmXsFocusSendProductList.length == 0) {
                // 	self.$message({
                // 		message: '请添加商品！',
                // 		type: 'error',
                // 		duration: 2000
                // 	});
                // 	return;
                // } else {
                // 	requestData.scmXsFocusSendProductList.forEach(function(item, index) {
                // 		delete item.id;
                // 	});
                // }
                // 发送请求
                self.$api.xsgl.post({
                    vueInstance: self,
                    url: '/scm/scmXsFocusSend/v1/save',
                    data: requestData
                }, function (res) {
                    self.loading = false;
                    if (res.value) {
                        self.baseInfoForm.id = res.value;
                    }

                    self.$message({
                        message: res.message,
                        type: "success",
                        duration: 2000
                    });
                });
            },
            // 发起申请
            sendApply() {
                let self = this;
                let requestData = {
                    "ownerId": self.ownerId,
                    "ownerName": self.baseInfoForm.ownerName, // 供货商名字
                    "dealerClauseId": self.baseInfoForm.angecyId, // 大合同id
                    "commerceCode": self.angecyCommerceSelected.commerceCode, // 商业编号
                    "commerceName": self.angecyCommerceSelected.commerceName, // 商业名称
                    "reduceContractTime": self.baseInfoForm.reduceContractTime, // 减免合同次数理由
                    "specialOpinion": self.baseInfoForm.specialOpinion, // 特求意见
                    "focusSendCode": self.baseInfoForm.allocationCode,//合同编号
                    "commerceFirstId": self.baseInfoForm.commerceFirstId,//商业首营ID
                    "marketingWay": self.baseInfoForm.paymentTypeStr,//购销方式
                    "dateOfSigning": self.baseInfoForm.dateOfSigning,//签订时间
                    "id": '',
                    "scmXsFocusSendProductList": ""
                };
                // 要校验的字段
                let checkKey = [{
                    pro: 'ownerId',
                    tips: '货主ID'
                },
                    {
                        pro: 'ownerName',
                        tips: '供货商'
                    },
                    {
                        pro: 'commerceCode',
                        tips: '经销商'
                    },
                    {
                        pro: 'dateOfSigning',
                        tips: '签订时间'
                    },
                    {
                        pro: 'reduceContractTime',
                        tips: '减免合同次数理由'
                    },
                ];
                for (let i = 0; i < checkKey.length; i++) {
                    if (!requestData[checkKey[i].pro]) {
                        self.$message({
                            message: '请选择' + checkKey[i].tips,
                            type: 'error',
                            duration: 2000
                        });
                        return;
                    }
                }
                // 验证通过
                if (self.componentParams.id) {
                    requestData.id = self.componentParams.id;
                }
                requestData.scmXsFocusSendProductList = self.listData.pageResult.rows
                if (requestData.scmXsFocusSendProductList.length === 0) {
                    self.$message({
                        message: '请添加商业！',
                        type: 'error',
                        duration: 2000
                    });
                    return;
                } else {
                    requestData.scmXsFocusSendProductList.forEach(function (item, index) {
                        delete item.id;
                    });
                }

                // 发送请求
                self.$api.xsgl.post({
                    vueInstance: self,
                    url: '/scm/scmXsFocusSend/v1/sendApply',
                    data: requestData
                }, function (res) {
                    self.showParentComp()
                    // self.applySteps.step = '1';
                    // self.applySteps.completeTitle = res.value.approvalId;
                    self.$message({
                        type: "success",
                        message: "发起申请成功！"
                    })
                });
            },
            // 获取各品规发货滚动表函数 -- 暂时没有接口
            getScrollTableList() {
                let self = this;
            },
            getCode() {
                this.$api.xsgl.get({
                    url: '/scm/scmXsBigContractAllot/v1/getCode'
                }).then(res => {
                    if (res.state == false) {
                        this.$message({
                            message: '系统错误',
                            type: 'error'
                        })
                    }
                    this.baseInfoForm.allocationCode = res.value;
                });
            },
            //  起始日期 formatter
            formatStartDate(row, column, cellValue) {
                console.log(row)
            },
            //  结束日期 formatter
            formatEndDate(row, column, cellValue) {
                console.log(row)
            },
            getDetails(id) {
                let self = this;
                this.$api.xsgl.get({
                    url: "/scm/scmXsFocusSend/v1/get/" + id,
                    data: {}
                }).then(res => {
                    self.ownerId = res.ownerId;
                    self.baseInfoForm.ownerName = res.ownerName;// 供货商名字
                    self.baseInfoForm.angecyId = res.dealerClauseId; // 大合同id
                    self.angecyCommerceSelected.commerceCode = res.commerceCode;// 商业编号
                    self.angecyCommerceSelected.commerceName = res.commerceName;// 商业名称
                    self.baseInfoForm.reduceContractTime = res.reduceContractTime; // 减免合同次数理由
                    self.baseInfoForm.specialOpinion = res.specialOpinion;// 特求意见
                    self.baseInfoForm.allocationCode = res.focusSendCode;//合同编号
                    self.baseInfoForm.commerceFirstId = res.commerceFirstId;//商业首营ID
                    self.baseInfoForm.paymentTypeStr = res.marketingWay;//购销方式
                    self.baseInfoForm.dateOfSigning = res.dateOfSigning;//签订时间
                    self.baseInfoForm.id = res.id;//签订时间
                    let arr = [];
                    self.listData.pageResult.rows = res.scmXsFocusSendProductList;
                })
            },
            getDetailsApproval(applyId) {
                let self = this;
                this.$api.xsgl.get({
                    url: "/scm/scmXsFocusSend/v1/getApplyId/" + applyId,
                }).then(res => {
                    self.ownerId = res.ownerId;
                    self.baseInfoForm.ownerName = res.ownerName;// 供货商名字
                    self.baseInfoForm.angecyId = res.dealerClauseId; // 大合同id
                    self.angecyCommerceSelected.commerceCode = res.commerceCode;// 商业编号
                    self.angecyCommerceSelected.commerceName = res.commerceName;// 商业名称
                    self.baseInfoForm.reduceContractTime = res.reduceContractTime; // 减免合同次数理由
                    self.baseInfoForm.specialOpinion = res.specialOpinion;// 特求意见
                    self.baseInfoForm.allocationCode = res.focusSendCode;//合同编号
                    self.baseInfoForm.commerceFirstId = res.commerceFirstId;//商业首营ID
                    self.baseInfoForm.paymentTypeStr = res.marketingWay;//购销方式
                    self.baseInfoForm.dateOfSigning = res.dateOfSigning;//签订时间
                    self.baseInfoForm.id = res.id;//签订时间
                    let arr = [];
                    self.listData.pageResult.rows = res.scmXsFocusSendProductList;
                })
            },
            approvalSuccess() {
                let _this = this;
                setTimeout(function () {
                    _this.showParentComp();
                }, 300);
            }

        }
    }
</script>
<style scoped lang="scss">

    .searchHeader {
        position: relative;
        width: 100%;
        font-size: 28px;
        font-weight: bold;
        background: #f3f3f3;
        margin-bottom: 7px;
    }

    .searchheadleft {
        position: absolute;
        left: 10px;
    }

    .searchheadcenter {
        width: 100%;
        text-align: center;
        line-height: 1.5em;
    }

    .searchheadright {
        position: absolute;
        top: 0;
        right: 10px;
    }

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

    .admin-info-form.el-form,
    .base-info-form.el-form {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        align-items: center;
        justify-content: space-between;
        .el-form-item {
            text-align: left;
            width: 50%;
            .el-select,
            .el-date-editor.el-input {
                width: 100%;
            }
        }
    }

    .el-form {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        align-items: center;
        .el-form-item {
            text-align: left;
            width: 33.3333%;
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
                width: 40%;
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

    .table-section:nth-last-of-type(1) {
        margin-top: 10px;
    }
</style>
