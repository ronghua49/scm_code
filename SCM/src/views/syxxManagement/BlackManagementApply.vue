<template>
    <div>
        <el-container>
            <el-header>
                <div class="searchHeader">
                    <div class="searchheadleft">
                        <el-button @click="showParentComp" size="mini" class="el-icon-back" type="primary">返回</el-button>

                    </div>

                    <div class="searchheadcenter">{{componentParams.title}}</div>
                    <div class="searchheadright" v-show="componentParams.flag !== 'details' ">
                        <el-button size="mini" type="primary" @click="applyCache">申请暂存</el-button>
                        <el-button size="mini" type="primary" @click="submitForm('tableData')">发起申请</el-button>
                    </div>
                </div>
            </el-header>
            <el-main>

                <div class="header">
                    <div class="headleft">
                        <span class="el-icon-menu"></span>
                        <span class="sx">基本信息</span>
                    </div>
                </div>

                <el-form
                    :rules="rules"
                    size="small"
                    label-width="120px"
                    class="demo-ruleForm"
                    :model="tableData"
                    ref="tableData"
                >

                    <el-form-item label="商务分区" prop="businessDivision">
                        <el-select @change="changeBus" style="width: 100%" v-model="tableData.businessDivision"
                                   placeholder="请选择" :disabled="componentParams.flag=='details'">
                            <el-option
                                v-for="(item,index) in shangwufenqu"
                                :key="index"
                                :label="item.businessDivision"
                                :value="item.businessDivision">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="省区" prop="province">
                        <el-select @change="changeProvice" style="width: 100%" v-model="tableData.province" placeholder="请选择"
                                    :disabled="componentParams.flag=='details'">
                            <el-option
                                v-for="(item,index) in provinceList"
                                :key="index"
                                :label="item.province"
                                :value="item.province">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="商业编号" prop="commerceCode">
                        <el-input disabled v-model="tableData.commerceCode" placeholder="请输入内容"></el-input>

                    </el-form-item>

                    <el-form-item label="商业名称" prop="commerceName" style="width: 66%">
                        <el-select @change="changecommerceName" style="width: 100%" v-model="tableData.commerceName" filterable
                                   placeholder="请选择" :disabled="componentParams.flag=='details'">
                            <el-option
                                v-for="(item,ins) in commerce"
                                :key="ins"
                                :label="item.commerceName"
                                :value="item.commerceName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="分类" prop="classify">
                        <el-select style="width: 100%" v-model="tableData.classify" filterable placeholder="请选择"
                                   :disabled="componentParams.flag=='details'">
                            <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="处罚原因" style="width: 66%" prop="penaltyReason">
                        <el-input clearable v-model="tableData.penaltyReason" placeholder="请输入内容"
                                  :disabled="componentParams.flag=='details'"></el-input>
                    </el-form-item>
                    <el-form-item label="禁销品种" prop="bannedBreed">
                        <el-input clearable v-model="tableData.bannedBreed" placeholder="请输入内容"
                                  :disabled="componentParams.flag=='details'"></el-input>
                    </el-form-item>


                </el-form>

                <div class="header">
                    <div class="headleft">
                        <span class="el-icon-menu"></span>
                        <span class="sx">备注信息</span>
                    </div>
                </div>

                <el-form
                    :rules="rules"
                    size="small"
                    label-width="120px"
                    class="demo-ruleForm"
                    :model="tableData"
                    ref="searchForm"
                >
                    <el-form-item label="申请备注" style="width: 100%;" prop="company">
                        <el-input
                            clearable
                            :disabled="componentParams.flag=='details'"
                            type="textarea"
                            :rows="2"
                            placeholder="请输入内容"
                            v-model="tableData.approvalMemo">
                        </el-input>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import storage from "@/utils/storage";


    export default {
        name: "XsBigContractApply",
        data() {
            return {
                ownerId: "",
                componentParams: {
                    flag: '',// 是编辑还是查看详情还是申请 与id是配对的
                    id: '',
                    title: '黑名单申请',
                    detailsdisabled: false
                },
                tableData: {
                    businessDivisionId: '',
                    businessDivision: '',
                    provinceId: '',
                    province: '',
                    commerceCode: '',
                    commerceName: '',
                    //分类
                    classify: '',
                    //处罚原因
                    penaltyReason: '',
                    bannedBreed: '',
                    approvalMemo: '',
                    ownerId: '',

                },
                provinceList: [],
                commerce: '',
                options: [{
                    value: 0,
                    label: '窜货'
                }, {
                    value: 1,
                    label: '虚假流向'
                }],
                index: 0,
                ins: '',
                rules: {
                    businessDivision: [
                        {required: true, message: '请选择商务分区', trigger: 'change'}
                    ],
                    commerceCode: [
                        {required: true, message: '请输入商业编号', trigger: 'blur'},
                    ],
                    commerceName: [
                        {required: true, message: '商业名称', trigger: 'change'}
                    ],
                    classify: [
                        {required: true, message: '请选择分类', trigger: 'blur'}
                    ],
                    penaltyReason: [
                        {required: true, message: '请输入处罚原因', trigger: 'blur'}
                    ]
                }
            }
        },
        props: ['shangwufenqu', 'pageState'],
        mounted() {
            this.ownerId = storage.get("ownerId");
            this.tableData.ownerId = this.ownerId;
            this.getBuinessName();
        },
        computed: {
            businessDivision() {
                return this.tableData.businessDivision;
            },
            province() {
                return this.tableData.province;
            }
        },
        watch: {
            pageState: 'getId',
            businessDivision() {
                let self = this;
                self.shangwufenqu.map(function (v, i) {
                    if (self.tableData.businessDivision == v.businessDivision) {
                        self.tableData.businessDivisionId = v.id;
                        self.provinceList = v.provinceList
                    }
                });
                self.getBuinessName();
            },
            province(value) {
                var self = this;
                self.provinceList.map(function (v, i) {
                    if (value == v.province) {
                        self.tableData.provinceId = v.id
                    }
                });
                self.getBuinessName();
            }
        },
        methods: {
            getId() {

                let self = this;
                let middleArr = self.pageState.split(',');
                self.componentParams.flag = middleArr[0];
                self.componentParams.id = middleArr[1];

                if (self.componentParams.flag === 'apply') {
                    self.componentParams.title = '商业黑名单申请';
                }

                if (self.componentParams.flag === 'details') {
                    self.componentParams.title = '商业黑名单详情';
                    this.getBlackListDetails()

                }
                if (self.componentParams.flag === 'edit') {
                    self.componentParams.title = '商业黑名单修改'
                    this.getBlackListDetails()
                }
            },
            //改变商业名称
            changecommerceName(value) {
                var self = this;
                this.commerce.map(function (v, i) {
                    if (v.commerceName == value) {
                        self.tableData.commerceCode = v.commerceCode;
                    }
                })

            },
            //申请暂存
            applyCache() {
                let self = this;
                self.$api.base.postScm({
                    url: "/scm/scmZsjCommerceBlackList/v1/save",
                    data: self.tableData,

                }).then(res => {
                    if (res.state == false) {
                        this.open1('添加暂存失败')
                    } else {
                        this.open2("添加暂存成功")
                    }
                })
            },
            // 发起申请
            applySubmit() {
                this.$api.base.postScm({
                    url: "/scm/scmZsjCommerceBlackList/v1/sendApply",
                    data: this.tableData
                }).then(res => {
                    if (res.state == false) {
                        this.open1('黑名单申请失败')
                    } else {
                        this.open2('黑名单申请成功');
                        var self = this;
                        setTimeout(function(){
                                self.showParentComp();
                        },300)
                    }
                })
            },
            //提交表单
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.applySubmit();

                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            //获取详情数据
            getBlackListDetails() {
                let self = this;
                self.$api.base.getScm({
                    url: '/scm/scmZsjCommerceBlackList/v1/get/' + self.componentParams.id
                }).then(res => {
                    self.tableData = res;
                    //转换类型，使select还原
                    if (res.classify) {
                        self.tableData.classify = parseInt(res.classify);
                    }
                })
            },
            open1(res) {
                this.$message({
                    message: res,
                    type: 'error'
                });
            },
            open2(res) {
                this.$message({
                    message: res,
                    type: 'success'
                });
            },
            changeBus(){
                this.tableData.provinceId = '';
                this.tableData.province = '';
                this.tableData.commerceName = '';
                this.tableData.commerceCode = '';
            },
            changeProvice(){
                this.tableData.commerceName = '';
                this.tableData.commerceCode = '';
            },
            showParentComp() {
                let self = this;
                self.$emit("closeApply", {});

            },

            //    获取商业名称
            getBuinessName(filter) {
                var query = [
                    {
                        property: "ownerId",
                        value: this.ownerId,
                        operation: "EQUAL",
                        relation: "AND"
                    }
                ];
                if (this.tableData.businessDivisionId) {
                    query.push({
                        property: 'businessDivisionId',
                        value: this.tableData.businessDivisionId,
                        operation: "EQUAL",
                        relation: "AND"
                    })
                }
                if (this.tableData.provinceId) {
                    query.push({
                        property: 'provinceId',
                        value: this.tableData.provinceId,
                        operation: "EQUAL",
                        relation: "AND"
                    })
                }
                this.$api.base.postScm({
                    url: "/scm/scmZsjCommerce/v1/firstList",
                    data: {
                        pageBean: {
                            "page": 0,
                            "pageSize": 0,
                            "showTotal": true
                        },
                        params: {},
                        querys: query,
                        sorter: []
                    }
                }).then(res => {
                    // console.log("商业名称", res);
                    this.commerce = res.rows;
                })
            }
        }
    }
</script>

<style scoped lang="scss">
    .el-header,.el-main {
        padding: 0 !important;
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

    .el-form {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        .el-form-item {
            width: 33%;
        }
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
</style>
