<template>
    <div class="bigcon-apply">
        <div class="searchHeader">
            <div class="searchheadleft">
                <el-button @click="showParentComp" size="mini" class="el-icon-back" type="primary">返回</el-button>
            </div>

            <div class="searchheadcenter">{{componentParams.title}}</div>
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
                <span class="sx">基本信息</span>
            </div>
        </div>
        <!--详情 && 审批 页面 ， 表单 不可选-->
        <el-form v-show="!isFenPei" :disabled="isDetails||isApproval" :rules="rules" size="small" label-width="120px"
                 class="demo-ruleForm" :model="postData" ref="postForm">
            <el-form-item label="供货商" prop="ownerName">
                <el-select style="width: 100%" v-model="postData.ownerName" placeholder="请选择">
                    <el-option v-for="(item,index) in supplierName"
                               :key="'ownerName'+index"
                               :label="item.name"
                               :value="item.name">

                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="合同编号" prop="contractCode">
                <el-input disabled
                          v-model="postData.contractCode"
                          placeholder="请输入内容">

                </el-input>
            </el-form-item>

            <el-form-item label="经销商" prop="dealerClauseId">
                <el-select style="width: 100%" v-model="postData.dealerClauseId"
                           @change="jingxiaoChange(postData.dealerClauseId)"
                           filterable placeholder="请选择">
                    <el-option v-for="(item,ins) in jingxiaoR"
                               :key="'dealer'+ins"
                               :label="item.commerceName+ ' [' +item.agreementSummaryCode+']'"
                               :value="item.id">

                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="收货人" style="width: 24%" prop="consignee">
                <el-input v-model="postData.consignee" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" style="width: 24%" prop="phone">
                <el-input v-model="postData.phone" maxlength="13" placeholder="请输入联系电话"></el-input>
            </el-form-item>

            <el-form-item label="购销方式" prop="marketingWay">
                <el-select style="width: 100%" v-model="postData.marketingWay" filterable placeholder="请选择">
                    <el-option key="marketingWay1" label="预付款" value="0"></el-option>
                    <el-option key="marketingWay2" label="赊销" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="到货地址">
                <el-input v-model="postData.shipAddress" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="付款方式" prop="payType">
                <el-input v-model="postData.payType" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="签订地址" prop="signTheAddress">
                <el-input v-model="postData.signTheAddress" placeholder="请输入内容"></el-input>
            </el-form-item>

            <el-form-item label="签订时间" prop="dateOfSigning">
                <div class="block">
                    <el-date-picker style="width: 100%" v-model="postData.dateOfSigning"
                                    value-format="yyyy-MM-dd HH:mm:ss" type="date" placeholder="选择日期"></el-date-picker>
                </div>
            </el-form-item>
            <el-form-item label="特求意见" style="width: 96%;">
                <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="postData.specialOpinion"></el-input>
            </el-form-item>
        </el-form>


        <!--商品列表-->
        <div class="header">
            <div class="headleft">
                <span class="el-icon-tickets"></span>
                <span class="sx">商品列表</span>
            </div>
        </div>
        <el-table v-loading="loading1"
                  :data="tableData1.rows"
                  border stripe
                  element-loading-text="加载中"
                  style="width: 99%"
                  max-height="470">
            <el-table-column v-for="(column) in columns1"
                             header-align="center"
                             align="center"
                             :prop="column.prop"
                             :key="column.label"
                             :label="column.label"
                             :formatter="column.formatter"
                             width="120">

            </el-table-column>

            <el-table-column prop="参考信息" key="参考信息" label="参考信息" header-align="center" align="center" min-width="100">
                <template slot-scope="scope">
                    <el-popover placement="top" title="参考信息" width="1000" align="center" trigger="click">
                        <el-table :data="consult" border style="width: 100%">
                            <el-table-column prop="commerceGauge" label="商品名称" width="100"></el-table-column>
                            <el-table-column prop="commerceGauge" label="品规" width="100"></el-table-column>
                            <el-table-column prop="commerceGauge" label="商业/渠道库存信息" width="100" align="center">
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
                            <el-table-column prop="commerceGauge" label="近三个月销售出库量" width="100" align="center">
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
            <el-table-column prop="isSMS" key="SMS" label="SMS" header-align="center" align="center" min-width="100">
                <template slot-scope="scope">
                    <el-checkbox true-label="1" :disabled="!isEveryDayJzl" false-label="0"
                                 v-model="scope.row.isSMS"></el-checkbox>
                </template>
            </el-table-column>

            <el-table-column prop="申请" key="申请" label="申请"
                             header-align="center" align="center"
                             min-width="100">
                <el-table-column prop="achange"
                                 v-show="jzlClumn" label="件装量" width="120" align="center">
                    <template slot-scope="scope">
                        <!--tabarr[scope.$index][0].itemData-->
                        <el-select v-if="isEveryDayJzl"
                                   size="small"
                                   :disabled="isDetails||isApproval"
                                   v-model="scope.row.achange"
                                   placeholder="请选择"
                                   @change="numberChanng(scope.row,scope.$index)">

                            <el-option v-for="(items,index) in tabarr[scope.$index]"
                                       :key="'x-'+index"
                                       :label="items.achage"
                                       :value="items.achage">
                            </el-option>
                        </el-select>
                        <el-input v-if="!isEveryDayJzl"
                                  size="small"
                                  disabled
                                  v-model="scope.row.achange"
                                  placeholder="请输入数量">

                        </el-input>
                    </template>
                </el-table-column>
                <el-table-column prop="theNumber" key="数量"
                                 label="数量"
                                 header-align="center"
                                 align="center"
                                 min-width="100">
                    <template slot-scope="scope">
                        <el-input @input.native="numberChanng(scope.row,scope.$index)"
                                  :disabled="isDetails||isApproval"
                                  size="small"
                                  v-model="scope.row.theNumber"
                                  placeholder="请输入数量">

                        </el-input>
                    </template>
                </el-table-column>

                <el-table-column prop="packageNum" key="件数" label="件数" header-align="center" align="center"
                                 min-width="100" v-if="dhtJs"></el-table-column>

                <el-table-column prop="unitPrice" key="含税单价" label="含税单价" header-align="center" align="center"
                                 min-width="100"></el-table-column>

                <el-table-column prop="sumPrice" key="贷款金额" label="货款金额" header-align="center" align="center"
                                 min-width="100">

                </el-table-column>
            </el-table-column>
        </el-table>


        <!--账号信息-->
        <div class="header zhanghaoInfo">
            <div class="headleft">
                <span class="el-icon-tickets"></span>
                <span class="sx">账号信息</span>
            </div>
        </div>

        <el-form disabled ref="idForm" :model="tableData2" size="small" class="zhanghao" label-width="80px">
            <el-form-item label="甲方单位（销货方）" label-width="160px">
                <el-input v-model="planA.junit"></el-input>
            </el-form-item>
            <el-form-item label="乙方单位（购货方）" label-width="160px">
                <el-input v-model="planB.yunit"></el-input>
            </el-form-item>
            <el-form-item label="地址">
                <el-input v-model="planA.jaddress" label-width="120px"></el-input>
            </el-form-item>
            <el-form-item label="地址">
                <el-input v-model="planB.yaddress"></el-input>
            </el-form-item>
            <el-form-item label="开户行">
                <el-input v-model="planA.jopenBank"></el-input>
            </el-form-item>
            <el-form-item label="开户行">
                <el-input v-model="planB.yopenBank"></el-input>
            </el-form-item>
            <el-form-item label="账号">
                <el-input v-model="planA.jbankCode"></el-input>
            </el-form-item>
            <el-form-item label="账号">
                <el-input v-model="planB.ybankCode"></el-input>
            </el-form-item>
            <el-form-item label="税号">
                <el-input v-model="planA.jdutyCode"></el-input>
            </el-form-item>
            <el-form-item label="税号">
                <el-input v-model="planB.ydutyCode"></el-input>
            </el-form-item>
            <el-form-item label="电话">
                <el-input v-model="planA.jphone"></el-input>
            </el-form-item>
            <el-form-item label="电话">
                <el-input v-model="planB.yphone"></el-input>
            </el-form-item>
            <el-form-item label="邮政编号">
                <el-input v-model="planA.jpostcode"></el-input>
            </el-form-item>
            <el-form-item label="邮政编号">
                <el-input v-model="planB.ypostcode"></el-input>
            </el-form-item>
        </el-form>

        <!--各品规发货滚动表-->
        <div class="header">
            <div class="headleft">
                <span class="el-icon-tickets"></span>
                <span class="sx">各品规发货滚动表</span>
            </div>
        </div>

        <el-table v-loading="loading2" :data="tableData3" border element-loading-text="加载中" style="width: 99%"
                  max-height="250">
            <el-table-column v-for="column in columns2" header-align="center" align="center" :prop="column.prop"
                             :key="column.label" :label="column.label" width="120"></el-table-column>
        </el-table>

        <!--分页-->
        <div class="pagination">
            <el-pagination background @size-change="handleSizeChange2" @current-change="handleCurrentChange2"
                           :current-page="pageRequest2.page" :page-sizes="[5,10, 20, 50]"
                           :page-size="pageRequest2.pageSize" layout="total, sizes, prev, pager, next "
                           :total="tableData3.total"></el-pagination>
        </div>
    </div>
</template>

<script>
    import approvalBtn from "@/components/approval/ApprovalBtn";
    import storage from "@/utils/storage";

    export default {
        name: "XsBigContractApply",
        components: {
            "approval-btn": approvalBtn
        },
        data() {
            return {
                isEveryDayJzl: true,
                jzlClumn: false,
                tabarr: [],
                ownerId: "",
                taskId: "",
                dhtJs: true, // 控制商业大合同页面商品列表当中的申请件数
                componentParams: {
                    flag: "apply", // 是编辑还是查看详情还是申请 与id是配对的
                    id: "",
                    title: "商业日常合同页面",
                    agreementSummaryId: "",
                    detailsdisabled: false
                },
                pageRequest1: {
                    page: 1,
                    pageSize: 5,
                    showTotal: true
                },
                pageRequest2: {
                    page: 1,
                    pageSize: 5,
                    showTotal: true
                },
                checked: [],
                visible: false,
                //表格loading状态
                loading1: false,
                loading2: false,
                //商品列表
                tableData1: {
                    rows: []
                },
                //账号信息
                tableData2: {
                    planA: ""
                },
                //发货列表
                tableData3: [{
                    date: "21321",
                    name: "呵呵",
                    province: "河南省",
                    city: "郑州市",
                    address: "地址",
                    lang: "有效期"
                }],
                //参考信息
                consult: [],
                reasons: [{
                    label: "SMS发货",
                    value: "SMS发货"
                },
                    {
                        label: "新品发货",
                        value: "新品发货"
                    },
                    {
                        label: "短缺品新品来",
                        value: "短缺品新品来"
                    },
                    {
                        label: "预估不足",
                        value: "预估不足"
                    },
                    {
                        label: "BU特批发货",
                        value: "BU特批发货"
                    },
                    {
                        label: "备货不足",
                        value: "备货不足"
                    }
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
                        prop: "productName",
                        label: "商品名"
                    },
                    {
                        prop: "productGauge",
                        label: "规格"
                    },
                    {
                        prop: "unit",
                        label: "单位"
                    },
                    // { prop: "commerceGauge", label: "标准包装" },
                    {
                        prop: "suppyType",
                        label: "药品供应状态"
                    },
                    {
                        prop: "suggestApplyNum",
                        label: "建议本次申请量",
                        formatter: function () {
                            return 0
                        }
                    },
                    {
                        prop: "lastShipmentDate",
                        label: "上次发货时间"
                    },
                    {
                        prop: "surplusSUm",
                        label: "省区本月SMS剩余任务量"
                    }
                ],
                columns2: [{
                    prop: "date",
                    label: "产品"
                },
                    {
                        prop: "name",
                        label: "年度"
                    },
                    {
                        prop: "province",
                        label: "一月"
                    },
                    {
                        prop: "city",
                        label: "二月"
                    },
                    {
                        prop: "address",
                        label: "三月"
                    },
                    {
                        prop: "lang",
                        label: "四月"
                    },
                    {
                        prop: "lang",
                        label: "五月"
                    },
                    {
                        prop: "lang",
                        label: "六月"
                    },
                    {
                        prop: "lang",
                        label: "七月"
                    },
                    {
                        prop: "lang",
                        label: "八月"
                    },
                    {
                        prop: "lang",
                        label: "九月"
                    },
                    {
                        prop: "lang",
                        label: "十月"
                    },
                    {
                        prop: "lang",
                        label: "十一月"
                    },
                    {
                        prop: "lang",
                        label: "十二月"
                    },
                    {
                        prop: "lang",
                        label: "合计"
                    }
                ],
                postData: {
                    id:'',
                    ownerId: "",
                    ownerName: "", //供应商
                    contractCode: "", //合同编号
                    dealer: "", //经销商
                    dealerClauseId: "",
                    commerceFirstId: "", //经销商ID
                    commerceFirst: "", //经销商名称 王伟东添加
                    sendProductDate: "", //计划发货时间
                    consignee: "", //收货人
                    phone: "", //联系电话
                    marketingWay: "", //购销方式0/1
                    shipAddress: "", //到货地址
                    payType: "", //付款方式
                    signTheAddress: "", //签订地址
                    dateOfSigning: "", //签订时间
                    specialOpinion: "", //特求意见
                    agreementSummaryId: "", // 协议id
                    jaddress: "", //甲方
                    jbankCode: "",
                    jdutyCode: "",
                    jopenBank: "",
                    jphone: "",
                    jpostcode: "",
                    junit: "",
                    yaddress: "", //乙方
                    ybankCode: "",
                    ydutyCode: "",
                    yopenBank: "",
                    yphone: "",
                    ypostcode: "",
                },

                //甲方&&乙方
                planA: {
                    jaddress: "", //甲方
                    jbankCode: "",
                    jdutyCode: "",
                    jopenBank: "",
                    jphone: "",
                    jpostcode: "",
                    junit: "",
                },
                planB: {
                    yunit: '',
                    yaddress: '',
                    yopenBank: '',
                    ybankCode: '',
                    ydutyCode: '',
                    yphone: '',
                    ypostcode: '',
                },
                //经销商下拉框返回数据
                jingxiaoR: [],
                //供货商
                supplierName: [],
                rules: {
                    ownerName: [{
                        required: true,
                        message: "请选择供货商",
                        trigger: "change"
                    }],
                    // contractCode: [
                    //   { required: true, message: "请输入合同编号", trigger: "change" }
                    // ],
                    dealerClauseId: [{
                        required: true,
                        message: "请选择经销商",
                        trigger: "change"
                    }],
                    // sendProductDate: [
                    //   { required: true, message: "请选择计划发货时间", trigger: "change" }
                    // ],
                    consignee: [{
                        required: true,
                        message: "请输入收货人",
                        trigger: "change"
                    }],
                    phone: [{
                        required: true,
                        message: "请输入联系电话",
                        trigger: "change"
                    }],
                    marketingWay: [{
                        required: true,
                        message: "请选择购销方式",
                        trigger: "change"
                    }],
                    payType: [{
                        required: true,
                        message: "请输入付款方式",
                        trigger: "change"
                    }],
                    // signTheAddress: [
                    //   { required: true, message: "请输入签订地址", trigger: "change" }
                    // ],
                    dateOfSigning: [{
                        required: true,
                        message: "请选择签订时间",
                        trigger: "change"
                    }]
                },
                rules2: {
                    ownerName: [{
                        required: true,
                        message: "请选择供货商",
                        trigger: "change"
                    }],
                    commerceFirstId: [{
                        required: true,
                        message: "请选择经销商",
                        trigger: "change"
                    }],
                    reasons: [{
                        required: true,
                        message: "请选择减免合同次数理由",
                        trigger: "change"
                    }],
                    dateOfSigning: [{
                        required: true,
                        message: "请选择签订时间",
                        trigger: "change"
                    }],
                    marketingWay: [{
                        required: true,
                        message: "请选择购销方式",
                        trigger: "change"
                    }]
                }
            };
        },
        props: ["pageState", "isFenPei", "isDetails", "isApproval", "row"],
        mounted() {
            this.ownerId = storage.get("ownerId");
            this.postData.ownerId = this.ownerId;
            this.getSelestGHS();
            this.getJXS();
        },
        computed: {
            gonghuoshang() {
                return this.postData.ownerName;
            },
            jingxiaoshang() {
                return this.postData.dealerClauseId;
            },
        },
        watch: {
            pageState: "getId",
            /*row() {
                this.$nextTick(() => {
                    /!*if(this.componentParams.flag === "everyDayEdit") {
                        this.getSelestGHS();
                        this.getJXS();
                        delete this.row.scmXsEveryDayContractProductList;
                        this.postData = Object.assign({}, this.postData, this.row);
                        this.getEveryDayDetails(this.row.id);
                    } else {
                        this.postData = Object.assign({}, this.postData, this.row);
                    }*!/
                });
                for(let key in this.row) {
                    if(this.planA.hasOwnProperty(key)) {
                        this.planA[key] = this.row[key];
                    }
                    if(this.planB.hasOwnProperty(key)) {
                        if(key == "dealer") {
                            this.planB['yunit'] = this.row[key];
                        } else {
                            this.planB[key] = this.row[key];
                        }
                    }
                }
            },*/
            //监听  ##供应商  数据变化
            gonghuoshang(value) {
                let _this = this;
                _this.supplierName.map(function (v, i) {
                    if (v.name == value) {
                        _this.postData.jaddress = v.address;
                        _this.postData.jbankCode = v.bankCode;
                        _this.postData.jdutyCode = v.dutyParagraph;
                        _this.postData.jopenBank = v.openBank;
                        _this.postData.jphone = v.phone;
                        _this.postData.jpostcode = v.postcode;
                        _this.postData.junit = v.unit;

                        _this.planA.jaddress = v.address;
                        _this.planA.jbankCode = v.bankCode;
                        _this.planA.jdutyCode = v.dutyParagraph;
                        _this.planA.jopenBank = v.openBank;
                        _this.planA.jphone = v.phone;
                        _this.planA.jpostcode = v.postcode;
                        _this.planA.junit = v.name;
                        // _this.postData = Object.assign(_this.postData, v);
                        console.log("主表", _this.postData);
                    }
                });
            },

            //监听 ## 经销商  变化
            jingxiaoshang(value) {
                let _this = this;

                _this.jingxiaoR.map(function (v, i) {
                    if (v.id == value) {
                        _this.postData.commerceFirstId = v.commerceFirstId;
                        _this.postData.dealer = v.commerceName;
                        _this.postData.agreementSummaryId = v.agreementSummaryId;
                    }
                });

            },

        },
        methods: {
            jingxiaoChange(id) {
                let _this = this;
                _this.jingxiaoR.map(function (v, i) {
                    if (id == v.id) {
                        _this.getGoodsList(v.agreementSummaryId);
                        _this.getPlanB(_this.postData.dealerClauseId);
                    }
                });

            },

            numberChanng(row, index) {
                let _this = this;


                if (row.theNumber) {

                    console.log(row.achange, row.theNumber, row.surplusSUm, index);

                    if (row.theNumber > row.surplusSUm) {
                        _this.tableData1.rows[index].theNumber = row.surplusSUm;
                    }
                    _this.tableData1.rows[index].packageNum = Math.ceil(
                        row.theNumber / row.achange
                    );
                    _this.tableData1.rows[index].sumPrice = row.theNumber * row.unitPrice;

                } else {
                    _this.tableData1.rows[index].packageNum = "";
                    _this.tableData1.rows[index].sumPrice = "";
                }


            },
            cankao(row) {
                this.consult.length = 0;
                this.consult.push(row);
            },
            //商品信息
            handleSizeChange(val) {
                this.pageRequest1.pageSize = val;
                this.getGoodsList();
            },
            handleCurrentChange(val) {
                this.pageRequest1.page = val;
                this.getGoodsList();
            },
            //滚动表
            handleSizeChange2(val) {
                this.pageRequest2.pageSize = val;
            },
            handleCurrentChange2(val) {
                this.pageRequest2.page = val;
            },
            getId() {
                let self = this;
                if (this.isApproval) {
                    this.taskId = this.row.id;
                }
                let middleArr = self.pageState.split(",");
                self.componentParams.flag = middleArr[0];
                self.componentParams.id = middleArr[1];
                if (middleArr.length > 2) {
                    self.componentParams.agreementSummaryId = middleArr[2];
                }

                if (self.componentParams.flag === "everyDayApply") {
                    self.componentParams.title = "商业日常合同申请";
                    this.jzlClumn = true;
                    this.getContractCode();
                    this.tabarr = [];
                }
                if (self.componentParams.flag === "everyDayEdit") {
                    self.componentParams.title = "商业日常合同更新";
                    self.getEveryDayDetailsById(self.componentParams.id);
                    this.getEveryDayDetails(self.componentParams.id, self.componentParams.agreementSummaryId);
                }
                if (self.componentParams.flag === "everyDayDetails") {
                    self.componentParams.title = "商业日常合同详情";
                    this.isEveryDayJzl = false;
                    self.getEveryDayDetailsById(self.componentParams.id);
                }
                if (self.componentParams.flag === "everyDayApproval") {
                    self.componentParams.title = "商业日常合同审批";
                    this.isEveryDayJzl = false;
                    self.getEveryDayDetailsApproval(self.componentParams.id);
                }

            },

            getEveryDayDetailsById(id) {
                this.$api.base
                    .getScm({
                        url: '/scm/scmXsEveryDayContract/v1/get/' + id
                    }).then((res) => {
                    if (res.state == false) {
                        this.$message({
                            message: '系统错误',
                            type: 'error'
                        });
                        return;
                    }
                    this.postData = res;
                    for (var i in this.postData) {
                        this.postData[i] = res[i]
                        if (this.planA.hasOwnProperty(i)) {
                            this.planA[i] = res[i];
                        }
                    }
                    // this.
                    for (var j in this.planB) {
                        this.planB[j] = res[j]
                    }
                    var _ = require("lodash");

                    let list = res.scmXsEveryDayContractProductList;
                    for (var i = 0; i < list.length; i++) {
                        this.tabarr.push([{
                            itemData: ""
                        }]);
                    }
                    if (this.componentParams.flag == "everyDayDetails") {


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

                                this.tableData1.rows = list;
                                for (var i = 0; i < list.length; i++) {
                                    this.$set(this.tableData1.rows[i], 'v_productState', list[i].productState ? list[i].productState : list[i].v_productState);
                                    this.$set(this.tableData1.rows[i], 'v_productName', list[i].productName ? list[i].productName : list[i].v_productName);
                                }

                                this.$nextTick(() => {
                                    this.$set(this.tabarr, index, _.merge(this.tabarr[index], response.rows));
                                });

                            })
                        })
                    }
                })
            },
            getEveryDayDetails(id, agreementSummaryId) {
                this.$api.base
                    .postScm({
                        url: '/scm/scmXsEveryDayContract/v1/listAgrProductByEveryDayUpdate/' + id + '/' + agreementSummaryId
                    }).then((res) => {
                    if (res.state == false) {
                        this.$message({
                            message: '系统错误',
                            type: 'error'
                        });
                        return;
                    }
                    var _ = require("lodash");
                    let list = res;
                    for (var i = 0; i < list.length; i++) {
                        this.tabarr.push([{
                            itemData: ""
                        }]);
                    }
                    if (this.componentParams.flag === "everyDayEdit") {

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

                                this.tableData1.rows = list;
                                for (var i = 0; i < list.length; i++) {
                                    this.$set(this.tableData1.rows[i], 'v_productState', list[i].productState ? list[i].productState : list[i].v_productState);
                                    this.$set(this.tableData1.rows[i], 'v_productName', list[i].productName ? list[i].productName : list[i].v_productName);
                                }

                                this.$nextTick(() => {
                                    this.$set(this.tabarr, index, _.merge(this.tabarr[index], response.rows));
                                });


                            })
                        })
                    }
                })
            },
            getEveryDayDetailsApproval(id) {
                this.$api.base.getScm({
                    url: "/scm/scmXsEveryDayContract/v1/getByApplyId/" + id,
                }).then(res => {
                    this.postData = res;
                    for (var i in this.postData) {
                        this.postData[i] = res[i]
                        if (this.planA.hasOwnProperty(i)) {
                            this.planA[i] = res[i];
                        }
                    }
                    // this.
                    for (var j in this.planB) {
                        this.planB[j] = res[j]
                    }
                    this.tableData1.rows = res.scmXsEveryDayContractProductList
                })
            },


            getAgreementDetails(id) {
                this.$api.base
                    .getScm({
                        url: '/scm/scmXsAgreementProductDetail/v1/listAgrProductByBigUpdate/' + id + '/' + this.postData.agreementSummaryId
                    }).then((res) => {
                    if (res.state == false) {
                        this.$message({
                            message: '系统错误',
                            type: 'error'
                        });
                        return;
                    }
                    // this.tableData1.rows = [];
                    this.tableData1.rows = res.value;
                })
            },

            doubleClick(row) {
                console.log(row);
            },
            //申请暂存
            applyCache() {
                let _this = this;
                let data;
                _this.postData.scmXsEveryDayContractProductList = JSON.parse(JSON.stringify(_this.tableData1.rows));
                _this.postData.scmXsEveryDayContractProductList.forEach((v, i) => {
                    if (v.suppyType == '正常供应') {
                        v.suppyType = '1'
                    } else {
                        v.suppyType = '0'
                    }
                })
                _this.postData = Object.assign(_this.postData, _this.planB);
                _this.postData.junit = _this.postData.ownerName;
                var jxsId = _this.jingxiaoR.filter((v) => {
                    if (v.commerceName == _this.postData.dealer) {
                        return v;
                    }
                });
                //_this.postData.commerceFirstId = jxsId[0].commerceCode;
                // _this.postData.dealerClauseId = jxsId[0].id;
                data = _this.postData;


                _this.$api.base
                    .postScm({
                        url: "/scm/scmXsEveryDayContract/v1/save",
                        data: data
                    })
                    .then(res => {
                        if (res.state == true) {
                            this.postData.id = res.value
                            _this.open2(res.message);
                        } else {
                            _this.open4("发起暂存失败");
                        }
                        console.log("暂存返回", res);
                    });
            },
            //发起申请
            applySubmit() {
                this.submitForm("postForm");
            },
            //表单验证
            submitForm(formName) {
                let _this = this;
                _this.$refs[formName].validate(valid => {
                    if (valid) {
                        //请求参数
                        _this.postData.scmXsEveryDayContractProductList = _this.tableData1.rows;
                        _this.postData.scmXsEveryDayContractProductList.forEach((v, i) => {
                            if (v.suppyType == '正常供应') {
                                v.suppyType = '1'
                            } else {
                                v.suppyType = '0'
                            }
                        })
                        _this.postData = Object.assign({}, _this.postData, _this.planA, _this.planB);
                        /*var jxsId = _this.jingxiaoR.filter((v) => {
                            if (v.commerceName == _this.postData.dealer) {
                                return v;
                            }
                        });*/
                        //_this.postData.commerceFirstId = jxsId[0].commerceCode;
                        // _this.postData.dealerClauseId = jxsId[0].id;
                        _this.sendApplyFunc({
                            data: _this.postData,
                            url: "/scm/scmXsEveryDayContract/v1/sendApply"
                        })


                    } else {
                        return false;
                    }
                });
            },
            //申请函数
            sendApplyFunc(params) {
                let _this = this;

                _this.$api.base
                    .postScm({
                        url: params.url,
                        data: params.data
                    })
                    .then(res => {
                        console.log("申请返回", res);
                        if (res.state == true) {
                            _this.showParentComp();
                            _this.open2("发起申请成功");
                        } else {
                            _this.open4("发起申请失败");
                        }
                    });
            },
            open4(msg) {
                this.$message.error(msg);
            },
            open2(msg) {
                this.$message({
                    message: msg,
                    type: "success"
                });
            },
            //获取供货商
            getSelestGHS() {
                let self = this;
                let url = "";

                //分配页面

                url = "/scm/scmZsjTXCompany/v1/getByOwnerId/" + self.ownerId;

                self.$api.base
                    .getScm({
                        url
                    })
                    .then(res => {
                        console.log("》》供货商", res);
                        self.supplierName = [].concat(res.value);
                        self.postData.ownerName = res.value.name;
                    });
            },
            //获取乙方信息
            getPlanB(planB) {
                let _this = this;
                _this.$api.base
                    .postScm({
                        url: "/scm/scmXsBigContract/v1/bigcontractGetYDetails",
                        data: {
                            querys: [{
                                hasInitValue: false,
                                operation: "EQUAL",
                                property: "dealerClauseId",
                                relation: "AND",
                                value: planB
                            }],
                            sorter: []
                        }
                    })
                    .then(res => {
                        this.postData.consignee = res.rows[0]['consignee']
                        this.postData.phone = res.rows[0]['phone']
                        this.postData.shipAddress = res.rows[0]['shipAddress']
                        delete res.rows[0].scmXsEveryDayContractProductList;
                        for (let key in this.planB) {
                            if (key == "yunit") {
                                this.planB['yunit'] = res.rows[0]['dealer'];
                            } else {
                                this.planB[key] = res.rows[0][key];
                            }
                        }
                        //this.planB = Object.assign({}, res.rows[0]);
                    });
            },
            //获取经销商
            getJXS() {
                let self = this;
                return new Promise((resolve, rej) => {
                    self.$api.base
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
                                        value: self.ownerId
                                    }
                                ],
                                sorter: []
                            }
                        })
                        .then(res => {
                            if (res.state == false) {
                                rej();
                            }

                            console.log("经销商", res);
                            if (res.rows) {
                                self.jingxiaoR = res.rows;
                                resolve();
                                /*let arr = self.jingxiaoR.filter(function(v){
                                    if ( v.id ==  self.postData.dealerClauseId ) {

                                    }
                                })
                                 self.postData.commerceFirst = arr[0];*/
                            }
                        });
                });
            },
            //获取商品列表
            getGoodsList(agreementSummaryId) {
                let self = this;
                self.$api.base
                    .postScm({
                        url: "/scm/scmXsBigContractProduct/v1/bigcontractProductList",
                        data: {
                            pageBean: self.pageRequest1,
                            params: {},
                            querys: [{
                                property: "agreementSummaryId",
                                value: agreementSummaryId,
                                operation: "EQUAL",
                                relation: "AND"
                            }]
                        }
                    })
                    .then(res => {
                        console.log("商品列表", res);
                        if (res.rows.length !== 0) {
                            this.tabarr = [];
                            self.tableData1 = res;
                            res.rows.forEach((item, index) => {
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
                                    this.achage = response.rows;
                                    for (let i = 0; i < response.rows.length; i++) {
                                        if (i == 0) {
                                            this.tableData1.rows[index].achange = response.rows[0].achage;
                                            response.rows[i].itemData = response.rows[0].achage;
                                        } else {
                                            response.rows[i].itemData = '';
                                        }
                                    }
                                    this.tabarr.push(response.rows);
                                })
                            })
                        }
                    });
            },
            //审批成功
            approvalSuccess() {
                let _this = this;
                setTimeout(function () {
                    _this.showParentComp();
                }, 300);
            },
            getBigCode() {
                let _this = this;
                _this.$api.base
                    .getScm({
                        url: "/scm/scmXsBigContract/v1/getCode"
                    })
                    .then(res => {
                        this.postData.contractCode = res.value;
                    });
            },
            //日常合同（获取合同ID）
            getContractCode() {
                let _this = this;
                _this.$api.base
                    .getScm({
                        url: "/scm/scmXsEveryDayContract/v1/getContracteCode"
                    })
                    .then(res => {
                        this.postData.contractCode = res;
                    });
            },
            //返回父页面
            showParentComp() {
                delete this.postData.id;
                for (var key in this.postData) {
                    this.$set(this.postData, key, "");
                }
                this.$emit("closeapplypage", {});
            }
        }
    };
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

    .zhanghaoInfo {
        margin-top: 10px;
    }

    .headleft {
        margin-left: 10px;
    }

    .el-form {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
    }

    .el-form .el-form-item {
        width: 48%;
    }

    .pagination {
        margin-top: 20px;
        display: flex;
        justify-content: flex-end;
    }
</style>
