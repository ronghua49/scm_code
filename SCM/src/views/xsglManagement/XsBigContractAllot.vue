<template>
    <div>
        <transition>
            <div v-show="inIndex">
                <el-container>
                    <el-header>
                        <div class="header">
                            <div class="headleft">
                                <span class="el-icon-search"></span>
                                <span class="sx">筛选查询</span>
                            </div>
                            <div class="headright">
                                <el-row>
                                    <el-button type="primary" class="el-icon-search" size="mini" @click="searchquery">
                                        查询
                                    </el-button>
                                </el-row>
                            </div>
                        </div>
                    </el-header>
                    <el-main>
                        <el-form size="small" label-width="120px" class="-ruleForm" :model="searchForm"
                                 ref="searchForm">
                            <el-form-item label="审批状态">
                                <el-select clearable style="width: 100%" v-model="searchForm.approvalState"
                                           placeholder="请选择">
                                    <el-option v-for="item in spOption" :key="item.label" :label="item.label"
                                               :value="item.value"></el-option>
                                </el-select>
                            </el-form-item>

                            <el-form-item label="合同编号">
                                <el-input clearable v-model="searchForm.htbh" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="经销商">
                                <el-input clearable v-model="searchForm.dealer" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="起止日期">
                                <div class="block">
                                    <el-date-picker v-model="searchForm.dateOfSigning" type="daterange" align="right"
                                                    unlink-panels range-separator="至" start-placeholder="开始日期"
                                                    end-placeholder="结束日期"></el-date-picker>
                                </div>
                            </el-form-item>
                        </el-form>
                        <div class="header">
                            <div class="headleft">
                                <span class="el-icon-tickets"></span>
                                <span>合同数据列表</span>
                            </div>
                            <div class="headright">
                                <el-row>
                                    <el-button type="primary" class="el-icon-circle-plus-outline" size="mini"
                                               @click="showPage">大合同分分配申请
                                    </el-button>
                                </el-row>
                            </div>
                        </div>

                        <el-table v-loading="loading" :data="tableData1.rows" border stripe element-loading-text="加载中"
                                  style="width: 100%">
                            <el-table-column :show-overflow-tooltip='true' sortable v-for="column in columns"
                                             header-align="center" align="center" :prop="column.prop"
                                             :key="column.label" :label="column.label" :formatter="column.formatter"
                                             :min-width="column.width"></el-table-column>

                            <el-table-column label="操作" width="220" fixed="right" header-align="center" align="center">
                                <template slot-scope="scope">
                                    <el-button :disabled="scope.row.approvalState == 1 || scope.row.approvalState == 2"
                                               icon="el-icon-edit" size="mini" type="primary" @click="edit(scope.row)">
                                        编辑
                                    </el-button>
                                    <el-button icon="el-icon-view" size="mini" type="primary"
                                               @click="details(scope.row)">查看详情
                                    </el-button>
                                </template>
                            </el-table-column>
                        </el-table>

                        <!--分页-->
                        <div class="pagination">
                            <el-pagination background @size-change="handleSizeChange"
                                           @current-change="handleCurrentChange" :current-page="pageRequest.page"
                                           :page-sizes="[10, 20, 30, 40,50]" :page-size="pageRequest.pageSize"
                                           layout="total, sizes, prev, pager, next"
                                           :total="resultData.total"></el-pagination>
                        </div>
                    </el-main>
                </el-container>
            </div>
        </transition>


        <transition>
            <div class="secondDiv" v-show="inIndexs">
                <!-- <xs-big-apply
          @closeapplypage="closeapplypage"
          :pageState="pageState"
          :isDetails="isDetails"
          :isFenPei="isFenPei"
        ></xs-big-apply>-->
                <div class="bigcon-apply">
                    <div class="searchHeader">
                        <div class="searchheadleft">
                            <el-button @click="fanHui" size="mini" class="el-icon-back" type="primary">返回</el-button>
                        </div>

                        <div class="searchheadcenter">商业大合同分配管理</div>
                        <div class="searchheadright">
                            <div v-if="!isDetails&&!isApproval">
                                <el-button size="mini" type="primary" @click="applyCache">申请暂存</el-button>
                                <el-button size="mini" type="primary" @click="applySubmit">发起申请</el-button>
                            </div>
                            <div v-if="isApproval">
                                <approval-btn :taskId="taskId" @handleSuccess="approvalSuccess"></approval-btn>
                            </div>
                        </div>
                    </div>

                    <div class="header">
                        <div class="headleft">
                            <span class="el-icon-tickets"></span>
                            <span class="sx">基础信息</span>
                        </div>
                    </div>
                    <!--详情 && 审批 页面 ， 表单 不可选-->
                    <el-form :disabled="isDetails||isApproval"
                             size="small"
                             label-width="140px"
                             class="demo-ruleForm"
                             :model="postData"
                             :rules="dataFormRules"
                             ref="postForm">

                        <el-form-item label="供货商" prop="ghs">
                            <el-select :disabled="editVar" style="width: 100%" placeholder="请选择" v-model="postData.ghs">
                                <el-option v-for="(item,index) in supplierName"
                                           :key="'ownerName'+index"
                                           :label="item.name"
                                           :value="item.name">

                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="减免合同次数理由" prop="reason">
                            <el-select style="width: 100%" v-model="postData.reason" placeholder="选择减免理由">
                                <el-option v-for="item in options"
                                           :key="item.value"
                                           :label="item.label"
                                           :value="item.value">

                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="经销商" prop="bigContractId">
                            <el-select :disabled="editVar" style="width: 100%" @change="shuju" placeholder="请选择"
                                       v-model="postData.bigContractId">
                                <el-option v-for="(item,ins) in jingxiaoR"
                                           :key="'dealer'+ins"
                                           :label="item.commerceName +' [' + item.agreementSummaryCode + ']'"
                                           :value="item.id">

                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="合同编码" prop="htbm">
                            <el-input clearable v-model="postData.htbm" placeholder="请输入内容" :disabled="true"></el-input>
                        </el-form-item>
                        <el-form-item label="签订时间" prop="time">
                            <div class="block">
                                <el-date-picker style="width: 100%" v-model="postData.time"
                                                value-format="yyyy-MM-dd HH:mm:ss"
                                                type="date"
                                                placeholder="选择日期">

                                </el-date-picker>
                            </div>
                        </el-form-item>
                        <el-form-item label="计划发货时间" prop="jhfh">
                            <div class="block">
                                <el-date-picker style="width: 100%"
                                                v-model="postData.jhfh"
                                                value-format="yyyy-MM-dd HH:mm:ss"
                                                type="date"
                                                placeholder="选择日期">

                                </el-date-picker>
                            </div>
                        </el-form-item>

                        <el-form-item label="特求意见" prop="tqyj" style="width: 48%;">
                            <el-input type="textarea" :rows="1" placeholder="请输入内容" v-model="postData.tqyj"></el-input>
                        </el-form-item>
                        <el-form-item label="购销方式" prop="marketingWay">
                            <el-select style="width: 100%" filterable placeholder="请选择" v-model="postData.marketingWay">
                                <el-option v-for="(item,ins) in gouxiao"
                                           :key="'marketingWay1'+ins"
                                           :label="item.name"
                                           :value="item.value">

                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-form>



                   <!-- ******************************************商品列表******************************************************-->
                    <div class="header">
                        <div class="headleft">
                            <span class="el-icon-tickets"></span>
                            <span class="sx">商品信息</span>
                        </div>
                    </div>



                    <el-table v-loading="loading"
                              :data="tableData.rows"
                              border stripe
                              element-loading-text="加载中"
                              style="width: 100%"
                              max-height="500">

                        <el-table-column v-for="column in columns1"
                                         align="center"
                                         :prop="column.prop"
                                         :key="column.label"
                                         :label="column.label"
                                         :formatter="column.formatter"
                                         :show-overflow-tooltip="true"
                                         min-width="120">

                        </el-table-column>

                        <el-table-column
                            prop="参考信息"
                            key="参考信息"
                            label="参考信息"
                            header-align="center"
                            align="center"
                            min-width="100"
                        >
                            <template slot-scope="scope">
                                <el-popover placement="top" title="参考信息" width="1000" align="center" trigger="click">
                                    <el-table :data="consult" border style="width: 100%">
                                        <el-table-column prop="commerceGauge" label="商品名称"
                                                         width="100"></el-table-column>
                                        <el-table-column prop="commerceGauge" label="品规" width="100"></el-table-column>
                                        <el-table-column prop="commerceGauge" label="商业/渠道库存信息" width="100"
                                                         align="center">
                                            <el-table-column prop="commerceGauge" label="安全库存设定" width="120"
                                                             align="center"></el-table-column>
                                            <el-table-column prop="commerceGauge" label="上次周库存" width="100"
                                                             align="center"></el-table-column>
                                            <el-table-column prop="commerceGauge" label="在途" width="100"
                                                             align="center"></el-table-column>
                                        </el-table-column>
                                        <el-table-column prop="commerceGauge" label="本省区上次渠道总库存量K" width="100"
                                                         align="center"></el-table-column>
                                        <el-table-column prop="commerceGauge" label="本省区近效期1年库存量" width="100"
                                                         align="center"></el-table-column>
                                        <el-table-column prop="commerceGauge" label="本省区近3月月均纯销量A" width="100"
                                                         align="center"></el-table-column>
                                        <el-table-column prop="commerceGauge" label="本省区上月总纯销量B" width="100"
                                                         align="center"></el-table-column>
                                        <el-table-column prop="commerceGauge" label="K/A" width="100"
                                                         align="center"></el-table-column>
                                        <el-table-column prop="commerceGauge" label="K/B" width="100"
                                                         align="center"></el-table-column>
                                        <el-table-column prop="commerceGauge" label="近三个月销售出库量" width="100"
                                                         align="center">
                                            <el-table-column prop="commerceGauge" label="第一个月" width="120"
                                                             align="center"></el-table-column>
                                            <el-table-column prop="commerceGauge" label="第二个月" width="100"
                                                             align="center"></el-table-column>
                                            <el-table-column prop="commerceGauge" label="第三个月" width="100"
                                                             align="center"></el-table-column>
                                        </el-table-column>
                                        <el-table-column prop="commerceGauge" label="近效期1年库存量" width="100"
                                                         align="center"></el-table-column>

                                    </el-table>

                                    <el-button size="mini" @click="cankao(scope.row)" slot="reference">点击</el-button>
                                </el-popover>
                            </template>
                        </el-table-column>

                        <el-table-column
                            prop="isSMS"
                            key="SMS"
                            label="SMS"
                            header-align="center"
                            align="center"
                            min-width="100"
                        >
                            <template slot-scope="scope">
                                <el-checkbox true-label="1" false-label="0"
                                             :disabled="isDetails||isApproval"
                                             v-model="scope.row.isSMS">

                                </el-checkbox>
                            </template>
                        </el-table-column>
                        <el-table-column
                            prop="surplusSUm"
                            key="大合同剩余数量"
                            label="大合同剩余数量"
                            header-align="center"
                            align="center"
                            min-width="100">
                        </el-table-column>
                        <el-table-column label="申请" align="center">


                            <el-table-column prop="aChange" label="件装量" width="120" align="center">
                                <template slot-scope="scope">
                                    <el-select :disabled="isDetails||isApproval"
                                               size="small"
                                               v-model="scope.row.aChange" placeholder="请选择"
                                               @change="numberChanng(scope.row,scope.$index)">
                                        <el-option v-for="(items,index) in tabarr[scope.$index]"
                                                   :key="'x-'+index"
                                                   :label="items.achage"
                                                   :value="items.achage">
                                        </el-option>
                                    </el-select>
                                </template>
                            </el-table-column>

                            <el-table-column prop="theNumber" label="数量" width="120" align="center">
                                <template slot-scope="scope">
                                    <el-input placeholder="输入数量"
                                              size="small"
                                              :disabled="isDetails||isApproval"
                                              v-model="scope.row.theNumber"
                                              @input.native="numberChanng(scope.row,scope.$index)">

                                    </el-input>
                                </template>
                            </el-table-column>
                            <el-table-column prop="packageNum" label="件数" width="120" align="center">

                            </el-table-column>
                            <el-table-column prop="unitPrice" label="含税单价" width="120" align="center">

                            </el-table-column>
                            <el-table-column prop="sumPrice" label="货款金额" width="120" align="center">

                            </el-table-column>
                        </el-table-column>
                    </el-table>

                    <!--分页-->
                    <!-- <div class="pagination">
            <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageRequest1.page"
             :page-sizes="[5, 10, 20, 50]" :page-size="pageRequest1.pageSize" layout="total, sizes, prev, pager, next " :total="tableData1.total"></el-pagination>
          </div>-->
                    <!-- 各品规发货滚动表 -->
                    <div class="header">
                        <div class="headleft">
                            <span class="el-icon-tickets"></span>
                            <span class="sx">各品规发货滚动表</span>
                        </div>
                    </div>

                    <el-table v-loading="loading2" :data="tableData3.rows" border element-loading-text="加载中"
                              style="width: 99%" max-height="250">
                        <el-table-column v-for="column in columns2"
                                         header-align="center" align="center"
                                         :prop="column.prop"
                                         :key="column.label"
                                         :label="column.label"
                                         width="120">

                        </el-table-column>
                    </el-table>

                    <div class="pagination">
                        <el-pagination background
                                       @size-change="handleSizeChange2"
                                       @current-change="handleCurrentChange2"
                                       :current-page="pageRequest2.page"
                                       :page-sizes="[5,10, 20, 50]"
                                       :page-size="pageRequest2.pageSize"
                                       layout="total, sizes, prev, pager, next "
                                       :total="tableData3.total">

                        </el-pagination>
                    </div>
                </div>
            </div>
        </transition>
    </div>
</template>

<script>
    import storage from "@/utils/storage";
    import XsBigContractApply from "./XsBigContractApply";
    import {format} from "@/utils/datetime";

    export default {
        name: "XsBigContractAllot",
        components: {
            "xs-big-apply": XsBigContractApply,
        },
        data() {
            return {
                showOverflowTooltip: true,
                editVar: false,
                consult: [],
                tabarr: [],
                dataList: [],
                inIndex: true,
                inIndexs: false,
                value11: "",
                postData: {
                    commerceFirstId: "",
                    bigContractId:"",
                    marketingWay: "",
                    jxs: "", //
                    tqyj: "", //
                    time: "", //
                    htbm: "", //
                    jhfh: "", //
                    reason: "", //减免合同次数理由
                    ghs: "" //
                },
                number: "",
                hsdj: "",
                htbh: "",
                supplierName: [],
                loading2: false,
                tableData3: {
                    rows: [],
                    total: 1
                },

                columns2: [],
                value: "",
                pageRequest2: {
                    page: 1
                },
                jingxiaoR: [],
                loading1: false,
                tableData1: {
                    page: 1,
                    rows: []
                },
                columns1: [],
                rules: {},
                pageRequest1: {
                    page: 1
                },
                isApproval: false,
                ownerId: "",
                isShowPage: "1",
                gouxiao: [{
                    value: '0',
                    name: "预付款",
                }, {
                    value: '1',
                    name: "赊销"
                }],
                isDetails: false,
                pageState: "",
                isFenPei: true, //展示分配页面
                applyKey1: "",
                loading: false,
                searchForm: {
                    approvalState: "", // 审批状况
                    dealer: "", // 经销商
                    dateOfSigning: "", //日期
                    //ownerId: '', // 货主ID -- 必传
                    htbh: "" // 合同编号
                },
                spOption: [{
                    value: "0",
                    label: "未申请"
                },
                    {
                        value: "1",
                        label: "申请中"
                    },
                    {
                        value: '2',
                        label: '申请通过'
                    },
                    {
                        value: '3',
                        label: '驳回'
                    }

                ],
                options: [{
                    value: "无需减免",
                    label: "无需减免"
                },
                    {
                        value: "SMS发货",
                        label: "SMS发货"
                    },
                    {
                        value: "新品发货",
                        label: "新品发货"
                    },
                    {
                        value: "短缺品新来品",
                        label: "短缺品新来品"
                    },
                    {
                        value: "预估不足",
                        label: "预估不足"
                    },
                    {
                        value: "BU特批发货",
                        label: "BU特批发货"
                    },
                    {
                        value: "备货不足",
                        label: "备货不足"
                    }
                ],
                search: [],
                resultData: {},
                dataFormRules: {
                    ghs: [{
                        required: true,
                        message: "请选择供货商",
                        trigger: "change"
                    }],
                    reason: [{
                        required: true,
                        message: "请选择减免合同次数理由",
                        trigger: "blur"
                    }
                        // { max: 20, message: '长度不能超过20', trigger: "blur"}
                    ],
                    marketingWay: [{
                        required: true,
                        message: "请选择购销方式",
                        trigger: "blur"
                    }],
                    htbm: [{
                        required: true,
                        message: "请输入合同编码",
                        trigger: "blur"
                    }],
                    jhfh: [{
                        required: true,
                        message: "请选择计划发货时间",
                        trigger: "blur"
                    }],
                    time: [{
                        required: true,
                        message: "请选择签订时间",
                        trigger: "blur"
                    }],
                    // tqyj: [
                    //   { required: false, message: "请输入备注", trigger: "blur"},

                    // ],
                    bigContractId: [{
                        required: true,
                        message: "请选择经销商",
                        trigger: "blur"
                    }]
                },
                columns: [{
                    prop: "approvalState",
                    label: "审批状态",
                    formatter: function (row, clumn, value) {
                        if (value == 0) {
                            return '未申请';
                        } else if (value == 1) {
                            return '申请中';
                        } else if (value == 2) {
                            return '审核通过';
                        } else if (value == 3) {
                            return '驳回';
                        }
                    }
                }, {
                    prop: 'ownerName',
                    label: '供应商名称'
                },
                    {
                        prop: 'commerceName',
                        label: '经销商名称'
                    },
                    {
                        prop: "allocationCode",
                        label: "合同编号",
                        width: '100px'
                    },
                    {
                        prop: "dateOfSigning",
                        label: "签订时间"
                    },
                    {
                        prop: "reduceContractTime",
                        label: "减免合同次数理由"
                    },
                    {
                        prop: "sendProductDate",
                        label: "计划发货时间"
                    },
                    {
                        prop: "specialOpinion",
                        label: "特求意见"
                    }, {
                        prop: "createPersion",
                        label: "创建人名称"
                    },
                    {
                        prop: 'createDate',
                        label: '创建时间'
                    },
                ],
                columns1: [{
                    prop: "commonName",
                    label: "药品名称"
                },
                    {
                        prop: "medicineType",
                        label: "药品类型"
                    },
                    {
                        prop: "v_productName",
                        label: "商品名"
                    },
                    {
                        prop: "v_productState",
                        label: "规格"
                    },
                    {
                        prop: "unit",
                        label: "单位"
                    },
                    {
                        prop: "suppyType",
                        label: "药品供应状态",
                        formatter: function (row, b, value) {
                            if (value == "1") {
                                return '正常供应';
                            } else {
                                return "禁止销售";
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
                        formatter: function (row, column, value) {
                            if (!value) {
                                return "无"
                            }
                            return format(value);
                        }
                    },
                    {
                        prop: "smscurrentMonthSales",
                        label: "省区本月SMS剩余任务量"
                    },
                ],

                tableData: {
                    rows: []
                },
                pageRequest: {
                    page: 1,
                    pageSize: 10,
                    showTotal: true
                },
                zancun1: ""
            };
        },
        computed: {
            commerceFirstId() {
                return this.postData.bigContractId
            }
        },
        watch: {
            commerceFirstId(val) {
                let _this = this;
                if (val) {
                    _this.jingxiaoR.map(function (v, i) {
                        if (val == v.id) {
                            _this.postData.commerceFirstId = v.commerceFirstId;
                            _this.postData.commerceName = v.commerceName;
                            _this.postData.commerceCode = v.commerceCode;
                        }
                    })
                }
            }
        },
        mounted() {
            this.ownerId = storage.get("ownerId");
            this.jzl();
            this.getsupplierName();
            this.jingXiao();
            this.getTableList();
        },
        methods: {
            cankao(row) {
                this.consult = [];
                this.consult.push(row);
            },
            // 页面之间切换的函数 -- 保证过渡动画流畅
            itemChange(item, index) {
                this.tableData.rows[index].aChange = item[0].itemData;
            },
            showParentComp() {
            },
            handleSizeChange2() {
            },
            handleCurrentChange2() {
            },
            gethtbm() {
                let _this = this;
                this.$api.base
                    .getScm({
                        url: "/scm/scmXsBigContractAllot/v1/getCode"
                    })
                    .then(res => {
                        this.postData.htbm = res.value;
                    });
            },
            shuju() {
                let _this = this;
                this.loading = true;
                this.$api.base
                    .postScm({
                        url: "/scm/scmXsBigContractProductSum/v1/listConProductSumBySummaryID",
                        data: {
                            params: {},
                            querys: [{
                                hasInitValue: false,
                                property: "agreementSummaryId",
                                value: _this.postData.bigContractId,
                                operation: "EQUAL",
                                relation: "AND"
                            },
                                {
                                    hasInitValue: false,
                                    operation: "EQUAL",
                                    property: "ownerId",
                                    relation: "AND",
                                    value: _this.ownerId
                                }
                            ],
                            sorter: [{
                                direction: "ASC",
                                property: "BigContractProductId"
                            }]
                        }
                    })
                    .then(res => {
                        setTimeout(() => {
                            _this.loading = false;
                        }, 800)
                        // console.log("经销商", res);
                        if (res.rows) {
                            /*for (let i = 0; i < res.rows.length; i++) {
                              res.rows[i].theNumber = "";
                              res.rows[i].aChange = "";
                              res.rows[i].packageNum = "";
                              res.rows[i].sumPrice = "";
                            }*/
                            _this.tableData = res;
                            _this.tabarr = [];
                            res.rows.forEach((item, index) => {
                                if (item) {
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
                                        _this.achage = response.rows;
                                        for (let i = 0; i < response.rows.length; i++) {
                                            if (i == 0) {
                                                _this.tableData.rows[index].aChange = response.rows[0].achage;
                                                response.rows[i].itemData = response.rows[0].achage;
                                            } else {
                                                response.rows[i].itemData = '';
                                            }
                                        }
                                        _this.tabarr.push(response.rows)
                                    })
                                }
                            })

                        }
                    });
            },
            numberChanng(row, index) {
                let _this = this;
                    if (row.theNumber&&row.aChange) {
                        console.log(row.aChange, row.theNumber, row.surplusSUm, index);
                        if (row.theNumber > row.surplusSUm) {
                            _this.tableData.rows[index].theNumber = row.surplusSUm;
                        }
                        _this.tableData.rows[index].packageNum = Math.ceil(
                            row.theNumber / row.aChange
                        );
                        _this.tableData.rows[index].sumPrice = row.theNumber * row.unitPrice;

                    } else {
                        _this.tableData.rows[index].packageNum = "";
                        _this.tableData.rows[index].sumPrice = "";
                    }

            },
            getsupplierName() {
                //获取供应商
                let _this = this;
                let url = "/scm/scmZsjTXCompany/v1/getByOwnerId/" + this.ownerId;
                this.$api.base
                    .getScm({
                        url
                    })
                    .then(res => {
                        _this.supplierName = [].concat(res.value);
                        _this.postData.ghs = res.value.name;
                    });
            },
            jingXiao() {
                this.$api.base
                    .postScm({
                        url: "/scm/scmXsDealerClause/v1/list",
                        data: {
                            params: {},
                            querys: [{
                                property: "approvalState",
                                value: 2,
                                operation: "EQUAL",
                                relation: "AND"
                            },
                                {
                                    hasInitValue: false,
                                    operation: "EQUAL",
                                    property: "ownerId",
                                    relation: "AND",
                                    value: this.ownerId
                                }
                            ],
                            sorter: []
                        }
                    })
                    .then(res => {
                        // console.log("经销商", res);
                        if (res.rows) {
                            this.jingxiaoR = res.rows;
                        }
                    });
            },
            fanHui() {
                this.inIndexs = false;
                setTimeout(() => {
                    this.inIndex = true;
                }, 300)
                this.$refs.postForm.resetFields();
                this.tableData.rows = [];
                this.zancun1 = "";
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pageRequest.pageSize = val;
                this.getTableList();
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageRequest.page = val;
                this.getTableList();
            },
            jzl() {
                this.$api.base
                    .postScm({
                        url: "/scm/scmXsDealerClause/v1/list",
                        data: {
                            params: {},
                            querys: [{
                                property: "approvalState",
                                value: 2,
                                operation: "EQUAL",
                                relation: "AND"
                            },
                                {
                                    hasInitValue: false,
                                    operation: "EQUAL",
                                    property: "ownerId",
                                    relation: "AND",
                                    value: this.ownerId
                                }
                            ],
                            sorter: []
                        }
                    })
                    .then(res => {
                        // console.log("经销商", res);
                        if (res.rows) {
                            this.jingxiaoR = res.rows;
                        }
                    });
            },
            //搜索按钮
            searchquery() {
                let _this = this;
                let search = [];
                if (this.searchForm.approvalState !== "") {
                    search.push({
                        hasInitValue: false,
                        operation: "EQUAL",
                        property: "approvalState",
                        relation: "AND",
                        value: _this.searchForm.approvalState
                    });
                }
                if (this.searchForm.htbh !== "") {
                    search.push({
                        hasInitValue: false,
                        operation: "EQUAL",
                        property: "allocationCode",
                        relation: "AND",
                        value: _this.searchForm.htbh
                    });
                }
                if (this.searchForm.dealer !== "") {
                    search.push({
                        hasInitValue: false,
                        operation: "LIKE",
                        property: "commerceName",
                        relation: "AND",
                        value: _this.searchForm.dealer
                    });
                }
                if (this.searchForm.dateOfSigning !== "" && this.searchForm.dateOfSigning != null) {
                    search.push({
                        hasInitValue: false,
                        operation: "BETWEEN",
                        property: "dateOfSigning",
                        relation: "AND",
                        value: _this.searchForm.dateOfSigning
                    });
                }
                _this.search = search;
                this.getTableList();
            },
            edit(row) {
                let self = this;
                this.pageState = "focusSendEdit,";
                this.inIndex = false;
                setTimeout(() => {
                    this.inIndexs = true;
                }, 300)
                this.loading = true;
                this.isDetails = false;
                this.editVar = false;
                this.editDetails(row.id, row.bigContractId);
            },
            details(row) {
                let self = this;
                this.pageState = "focusSendDetails,";
                this.getDetails(row.id);
                this.isDetails = true;
                this.inIndex = false;
                setTimeout(() => {
                    this.inIndexs = true;
                }, 300)
                this.loading = true;
            },
            getDetails(id) {
                var _ = require("lodash");
                this.$api.xsgl.get({
                    url: '/scm/scmXsBigContractAllot/v1/get/' + id
                }).then(res => {
                    this.tabarr = [];
                    for (var key in res) {
                        if (key == "scmXsBigContractAllotProductList") {
                            let list = res.scmXsBigContractAllotProductList;
                            for (var i = 0; i < list.length; i++) {
                                this.tabarr.push([{itemData: ""}]);
                            }
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
                                    for (let i = 0; i < response.rows.length; i++) {
                                        if (i == 0) {
                                            response.rows[i].itemData = item.aChange;
                                        } else {
                                            response.rows[i].itemData = '';
                                        }
                                    }
                                    this.loading = false;
                                    this.tableData.rows = list;
                                    for (var i = 0; i < list.length; i++) {

                                        this.$set(this.tableData.rows[i], 'v_productState', list[i].productState ? list[i].productState : list[i].v_productState);
                                        this.$set(this.tableData.rows[i], 'v_productName', list[i].productName ? list[i].productName : list[i].v_productName);
                                    }
                                    ;
                                    this.$nextTick(() => {
                                        this.$set(this.tabarr, index, _.merge(this.tabarr[index], response.rows));
                                    });
                                })
                            })
                        }
                        if (key == "ownerName") {
                            this.postData.ghs = res[key]
                        }
                        if (key == "reduceContractTime") {
                            this.postData.reason = res[key]
                        }
                        if (key == "bigContractId") {
                            this.postData.bigContractId = res[key];
                        }
                        if (key == "allocationCode") {
                            this.postData.htbm = res[key]
                        }
                        if (key == "dateOfSigning") {
                            this.postData.time = res[key]
                        }
                        if (key == "sendProductDate") {
                            this.postData.jhfh = res[key]
                        }
                        if (key == "specialOpinion") {
                            this.postData.tqyj = res[key]
                        }
                        if (key == "marketingWay") {
                            this.postData.marketingWay = res[key]
                        }
                    }
                });
            },
            editDetails(id, protocalId) {
                let _this = this;
                _this.$api.xsgl.get({
                    url: '/scm/scmXsBigContractAllot/v1/get/' + id
                }).then(res => {
                    _this.postData.id = id;
                    _this.zancun1 = id;
                    console.log("RES",res);
                    for (var key in res) {
                        if (key == "ownerName") {
                            this.postData.ghs = res[key];
                        }
                        if (key == "reduceContractTime") {
                            this.postData.reason = res[key];
                        }
                        if (key == "bigContractId") {
                            this.postData.bigContractId = res[key];
                        }
                        if (key == "allocationCode") {
                            this.postData.htbm = res[key];
                        }
                        if (key == "dateOfSigning") {
                            this.postData.time = res[key];
                        }
                        if (key == "sendProductDate") {
                            this.postData.jhfh = res[key];
                        }
                        if (key == "specialOpinion") {
                            this.postData.tqyj = res[key];
                        }
                        if (key == "marketingWay") {
                            this.postData.marketingWay = res[key];
                        }
                    }
                });
                var _ = require("lodash");
                _this.$api.xsgl.post({
                    url: '/scm/scmXsBigContractProductSum/v1/listConProductSumByUpdate?bigContractAllotId=' + id + '&agreementSummaryId=' + protocalId,
                }).then(res2 => {
                    console.log("RES2",res2);
                    _this.tabarr = [];
                    let list = res2;
                    for (var i = 0; i < list.length; i++) {
                        _this.tabarr.push([{itemData: ""}]);
                    }
                    _this.loading = false;
                    list.forEach((item, index) => {
                        _this.$api.xsgl.post({
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
                            for (let i = 0; i < response.rows.length; i++) {
                                if (i == 0) {
                                    response.rows[i].itemData = item.aChange;
                                } else {
                                    response.rows[i].itemData = '';
                                }
                            }

                            _this.tableData.rows = list;
                            for (var i = 0; i < list.length; i++) {
                                _this.$set(this.tableData.rows[i], 'v_productState', list[i].productState ? list[i].productState : list[i].v_productState);
                                _this.$set(this.tableData.rows[i], 'v_productName', list[i].productName ? list[i].productName : list[i].v_productName);
                            }
                            ;
                            _this.$nextTick(() => {
//									var aa = _.merge(this.tabarr[index],response.rows);
                                this.$set(this.tabarr, index, _.merge(this.tabarr[index], response.rows));
                            });
                        })
                    })
                });
            },
            showPage() {
                this.gethtbm();
                for (var key in this.postData) {
                    this.postData[key] = "";
                }
                this.isDetails = false;
                this.inIndex = false;
                setTimeout(() => {
                    this.inIndexs = true;
                }, 300)
                this.editVar = false;
            },
            //关闭页面
            closeapplypage() {
                let self = this;

                this.isDetails = false; //详情参数复位
                this.pageState = "";
                this.applyKey1 += new Date();
                this.getTableList();
            },
            getTableList(query = this.search) {
                let _this = this;
                let postData = [{
                    hasInitValue: false,
                    operation: "EQUAL",
                    property: "ownerId",
                    relation: "AND",
                    value: _this.ownerId
                }];
                if (query.length !== 0) {
                    postData = postData.concat(query);
                }
                this.loading = true;
                _this.$api.xsgl
                    .post({
                        vueInstance: _this,
                        url: "/scm/scmXsBigContractAllot/v1/list",
                        data: {
                            pageBean: _this.pageRequest,
                            params: {},
                            querys: postData,
                            sorter: []
                        }
                    })
                    .then(res => {
                        this.tableData1.rows = res.rows;
                        this.resultData.total = res.total;
                        this.loading = false;
                        //        let arrlength=res.rows.length;
                    });
            },
            changeTabeData(rows) {
                let isNull = false;
                for (let i = 0; i < rows.length; i++) {
                    if (rows[i].theNumber != "" && rows[i].theNumber != undefined) {
                        isNull = true;
                    }
                    for (let key in rows[i]) {
                        if (key == "v_productName") {
                            rows[i]['productName'] = rows[i][key];
                            // delete rows[i][key];
                        }
                        if (key == "v_productState") {
                            rows[i]['productState'] = rows[i][key];
                            // delete rows[i][key];
                        }
                    }
                }
                if (!isNull) {
                    this.$message({
                        message: "数量不能为空！",
                        type: "error",
                        duration: 800
                    });
                    return 'nulls';
                }
                return rows;
            },
            getCommer(id) {
                const unitCommer = this.jingxiaoR.filter((v, i) => {
                    if (v.id == id) {
                        return v;
                    }
                })
                return unitCommer[0];
            },
            applyCache() {
                let _this = this;
                if (!this.postData.ghs) {
                    this.$message({
                        message: "供货商不能为空",
                        type: "error",
                        duration: 800
                    });
                    return;
                }
                if (!this.postData.bigContractId) {
                    this.$message({
                        message: "经销商不能为空",
                        type: "error",
                        duration: 800
                    });
                    return;
                }
                if (this.tableData.rows.length == 0) {
                    this.$message({
                        message: "没有商品，无法申请暂存",
                        type: "error",
                        duration: 800
                    });
                    return;
                }
                let data = {
                    ownerId: this.ownerId,
                    allocationCode: this.postData.htbm,
                    marketingWay: this.postData.marketingWay,
                    dateOfSigning: this.postData.jhfh,
                    reduceContractTime: this.postData.reason,
                    sendProductDate: this.postData.time,
                    commerceFirstId: this.postData.commerceFirstId,
                    bigContractId: this.postData.bigContractId,
                    ownerName: this.postData.ghs,
                    specialOpinion: this.postData.tqyj,
                    id: this.zancun1,
                    scmXsBigContractAllotProductList: this.changeTabeData(this.tableData.rows),
                    commerceName: this.postData.commerceName,
                    commerceCode: this.postData.commerceCode,
                };
                if (data.scmXsBigContractAllotProductList == 'nulls') {
                    return;
                }
                let url = "/scm/scmXsBigContractAllot/v1/save";
                _this.$api.base
                    .postScm({
                        url,
                        data: data
                    })
                    .then(res => {
                        if (res.state == true) {
                            this.zancun1 = res.value;
                            this.$message({
                                message: res.message,
                                type: "success",
                                duration: 800
                            });
                        }
                        //this.inIndex = true;
                        this.getTableList();
                        /*for(var i in this.postData) {
                            this.postData[i] = "";
                        }
                        this.tableData.rows = [];
                        this.$refs.postForm.resetFields();*/
                    });
            },
            applySubmit() {
                let _this = this;
                this.$refs.postForm.validate(valid => {
                    if (valid) {
                        if (this.tableData.rows.length == 0) {
                            this.$message({
                                message: "没有商品，无法发起申请",
                                type: "error",
                                duration: 800
                            });
                            return;
                        }
                        for (let i = 0; i < this.tableData.rows.length; i++) {
                            if (this.tableData.rows[i].aChange == "") {
                                this.$message({
                                    message: "件装量不能为空",
                                    type: "error",
                                    duration: 800
                                });
                                return;
                            }
                            if (this.tableData.rows[i].theNumber == "") {
                                this.$message({
                                    message: "数量不能为空",
                                    type: "error",
                                    duration: 800
                                });
                                return;
                            }
                        }
                        let data = {
                            ownerId: this.ownerId,
                            allocationCode: this.postData.htbm,
                            marketingWay: this.postData.marketingWay,
                            dateOfSigning: this.postData.jhfh,
                            reduceContractTime: this.postData.reason,
                            sendProductDate: this.postData.time,
                            commerceFirstId: this.postData.commerceFirstId,
                            BigContractId: this.postData.BigContractId,
                            ownerName: this.postData.ghs,
                            specialOpinion: this.postData.tqyj,
                            id: this.zancun1,
                            commerceName: this.postData.commerceName,
                            commerceCode: this.postData.commerceCode,
                            scmXsBigContractAllotProductList: this.changeTabeData(this.tableData.rows)
                        };
                        if (data.scmXsBigContractAllotProductList == 'nulls') {
                            return;
                        }
                        let url = "/scm/scmXsBigContractAllot/v1/sendApply";
                        _this.$api.base
                            .postScm({
                                url,
                                data: data
                            })
                            .then(res => {
                                if (res.state == true) {
                                    this.$message({
                                        message: res.message,
                                        type: "success",
                                        duration: 800
                                    });
                                } else {
                                }
                                this.inIndexs = false;
                                setTimeout(() => {
                                    this.inIndex = true;
                                }, 300);
                                this.getTableList();
                                for (var i in this.postData) {
                                    this.postData[i] = "";
                                }
                                this.tableData.rows = [];
                                this.$refs.postForm.resetFields();
                            });
                    }
                });
            },
        }
    };
</script>

<style scoped lang="scss">
    .bigcon {
        text-align: left;
    }

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

    .headleft {
        margin-left: 10px;
    }

    .headright {
        margin-right: 10px;
    }

    .el-form {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        .el-form-item {
            width: 33%;
        }
    }

    .bigHeTong {
        .el-form-item {
            width: 50%;
        }
    }

    .el-main {
        padding: 0 !important;
    }

    /*下拉菜单样式*/

    .el-dropdown-link {
        cursor: pointer;
        color: #409eff;
    }

    .el-icon-arrow-down {
        font-size: 12px;
    }

    .pagination {
        margin-top: 20px;
        display: flex;
        justify-content: flex-end;
    }

    .block {
        .el-date-editor.el-range-editor {
            width: 100%;
        }
    }

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

    .head-item {
        border: 1px solid #f2f2f2;
        text-align: left;
    }

    .header {
        width: 100%;
        height: 38px;
        font-size: 16px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        background: #f3f3f3;
        margin-bottom: 22px;
    }

    .headleft {
        margin-left: 10px;
    }

    .el-form {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        .el-form-item {
            width: 48%;
        }
    }
    .search-ruleForm{
        .el-form-item {
            width: 33.3%;
        }
    }
    .pagination {
        margin-top: 20px;
        display: flex;
        justify-content: flex-end;
    }
</style>
