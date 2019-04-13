<template>
    <div>
        <transition name="el-fade-in">
            <div class="content-item" v-show="!showPage">
                <el-container>

                    <el-header>
                        <div class="header">
                            <div class="headleft">
                                <span class="el-icon-search"></span>
                                <span class="sx">筛选查询</span>

                            </div>
                            <div class="headright">
                                <el-row>
                                    <el-button type="primary" class="el-icon-search" size="mini" @click="searchquery">查询
                                    </el-button>
                                </el-row>
                            </div>
                        </div>
                    </el-header>

                    <el-main>
                        <el-form
                            size="small"
                            label-width="120px"
                            class="demo-ruleForm"
                            :model="searchForm"
                            ref="searchForm"
                        >

                            <el-form-item label="审批状态">
                                <el-select clearable style="width: 100%" v-model="searchForm.approvalState"
                                           placeholder="请选择">
                                    <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="合同编码">
                                <el-input clearable v-model="searchForm.contractCode" placeholder="请输入内容"></el-input>
                            </el-form-item>
                            <el-form-item label="经销商">
                                <el-input clearable v-model="searchForm.dealer" placeholder="请输入内容"></el-input>
                            </el-form-item>

                            <el-form-item label="起止日期">
                                <div class="block">
                                    <el-date-picker
                                        v-model="searchForm.dateOfSigning"
                                        type="daterange"
                                        align="right"
                                        unlink-panels
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                    >
                                    </el-date-picker>
                                </div>
                            </el-form-item>
                        </el-form>

                        <div class="header">
                            <div class="headleft">
                                <span class="el-icon-tickets"></span>
                                <span>数据列表</span>

                            </div>
                            <div class="headright">
                                <el-row>
                                    <el-button type="primary" class="el-icon-setting" size="mini" @click="showPage2">
                                        合同分配
                                    </el-button>
                                </el-row>
                            </div>
                        </div>

                        <!--表格-->


                        <el-table
                            v-loading="loading"
                            :data="tableData"
                            border stripe
                            element-loading-text="加载中"
                            style="width: 100%"
                            max-height="250">

                            <el-table-column sortable
                                             v-for="column in columns"
                                             header-align="center"
                                             align="center"
                                             :prop="column.prop"
                                             :key="column.label"
                                             :label="column.label"
                                             width="120">
                            </el-table-column>


                            <el-table-column label="操作" width="220" fixed="right" header-align="center" align="center">
                                <template slot-scope="scope">
                                    <el-button icon="el-icon-edit" size="mini"
                                               type="primary" @click="edit(scope.row)">编辑
                                    </el-button>
                                    <el-button icon="el-icon-view" size="mini" type="primary"
                                               @click="handleClick(scope.row)">
                                        查看详情
                                    </el-button>
                                </template>
                            </el-table-column>
                        </el-table>


                        <!--分页-->
                        <div class="pagination">
                            <el-pagination
                                background
                                @size-change="handleSizeChange"
                                @current-change="handleCurrentChange"
                                :current-page="pageRequest.page"
                                :page-sizes="[10, 20, 30, 40,50]"
                                :page-size="pageRequest.pageSize"
                                layout="total, sizes, prev, pager, next"
                                :total="resultData.total">
                            </el-pagination>
                        </div>


                    </el-main>

                </el-container>
            </div>
        </transition>


        <transition name="el-fade-in">
            <div class="content-item" v-show="showPage">
                <he-tong :key="applyKey1" @closeapplypage="closeapplypage"
                         :pageState="pageState" :isFenPei="isFenPei" :isDetails="isDetails"
                >

                </he-tong>
            </div>
        </transition>

        <!--<transition name="el-fade-in">-->
        <!--<div class="content-item" v-show="showPage==2">-->
        <!--<fen-pei :key="applyKey2" @closeallotpage="closeallotpage"-->
        <!--&gt;</fen-pei>-->
        <!--</div>-->
        <!--</transition>-->
    </div>
</template>

<script>
    import storage from "@/utils/storage";
    import hetongshenqing from "./XsBigContractApply"
    import hetongfenpeishenqing from "./XsBigContractAllotProduct"

    export default {
        name: "xsBigContract",
        components: {
            "he-tong": hetongshenqing,
            "fen-pei": hetongfenpeishenqing
        },
        beforeRouteEnter(to, from, next) {
            next(vm => {
                // 通过 `vm` 访问组件实例
                if (from.name === "商业大合同管理") {
                    // 向主页广播事件
                    vm.$root.eventHub.$emit('closexsBigContract', {});
                }
            });
        },
        mounted() {
            this.ownerId = storage.get("ownerId");
            this.getTableList()
        },
        data() {
            return {
                ownerId: '',
                pageState: '',
                isFenPei: false,
                isDetails: false,
                //两个组件key
                applyKey1: '',
                applyKey2: '',
                options: [{
                    value: '未提交',
                    label: '未提交'
                }, {
                    value: '已通过',
                    label: '已通过'
                }, {
                    value: '未通过',
                    label: '未通过'
                }],
                //主页列表切换
                pageList: "大合同数据列表",
                isApproveList: false,
                searchForm: {
                    approvalState: '', // 审批状况
                    dealer: '', // 经销商
                    dateOfSigning: '', // 起止日期
                    //ownerId: '', // 货主ID -- 必传
                    contractCode: '' // 合同编号
                },
                loading: false,
                resultData: {},
                search: [],
                columns: [{
                    prop: 'contractCode',
                    label: '合同编号',

                }, {
                    prop: 'dateOfSigning',
                    label: '签订时间',

                }, {
                    prop: 'province',
                    label: '有效期',

                }, {
                    prop: 'approvalState',
                    label: '生效情况',

                }, {
                    prop: 'address',
                    label: '确认情况',

                }, {
                    prop: 'zip',
                    label: '确认时间',

                }, {
                    prop: 'zip',
                    label: '确认操作者',

                }, {
                    prop: 'zip',
                    label: '合同类型',

                }, {
                    prop: 'marketingWay',
                    label: '购销方式',

                }, {
                    prop: 'ownerName',
                    label: '供应商',

                }, {
                    prop: 'dealer',
                    label: '经销商',

                }, {
                    prop: 'consignee',
                    label: '收货联系人',

                }, {
                    prop: 'zip',
                    label: '录单人',

                }],
                tableData: [{
                    date: '2016-05-03',
                    name: '王小虎',
                    province: '上海',
                    city: '普陀区',
                    address: '上海市',
                    zip: 200333,
                    id: 312321
                }, {
                    date: '2016-05-02',
                    name: '王小虎',
                    province: '上海',
                    city: '普陀区',
                    address: '上海市',
                    zip: 200333,
                    id: 324243
                }],
                pageRequest: {
                    page: 1,
                    pageSize: 10,
                    showTotal: true
                },
                // tableData: [],
                //是否显示大合同申请页面
                showPage: false,
            }
        },
        methods: {
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pageRequest.pageSize = val;
                this.getTableList()
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageRequest.page = val;
                this.getTableList();
            },
            getTableList(query = this.search) {
                let _this = this;
                let postData = [{
                    "hasInitValue": false,
                    "operation": "EQUAL",
                    "property": "ownerId",
                    "relation": "AND",
                    "value": _this.ownerId
                }];
                if (query.length !== 0) {
                    postData = postData.concat(query);
                }

                _this.$api.base.postScm({
                    url: "/scm/scmXsBigContractAllotProduct/v1/list",
                    data: {
                        "pageBean": _this.pageRequest,
                        "params": {},
                        "querys": postData,
                        "sorter": []
                    }
                }).then(res => {
                    console.log('List', res);
                    self.resultData = res;
                    self.loading = false;
                })
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {
                    });
            },
            searchquery() {
                /* approvalState: '', // 审批状况
                     dealer: '', // 经销商
                     dateOfSigning: '',//日期
                     //ownerId: '', // 货主ID -- 必传
                     contractCode: '', // 合同编号*/
                let _this = this;
                let search = [];
                if (this.searchForm.approvalState !== '') {
                    search.push({
                        "hasInitValue": false,
                        "operation": "EQUAL",
                        "property": "approvalState",
                        "relation": "AND",
                        "value": _this.searchForm.approvalState
                    })
                }
                if (this.searchForm.contractCode !== '') {
                    search.push({
                        "hasInitValue": false,
                        "operation": "EQUAL",
                        "property": "contractCode",
                        "relation": "AND",
                        "value": _this.searchForm.contractCode
                    })
                }
                if (this.searchForm.dealer !== '') {
                    search.push({
                        "hasInitValue": false,
                        "operation": "EQUAL",
                        "property": "dealer",
                        "relation": "AND",
                        "value": _this.searchForm.dealer
                    })
                }
                if (this.searchForm.dateOfSigning !== '') {
                    search.push({
                        "hasInitValue": false,
                        "operation": "EQUAL",
                        "property": "dateOfSigning",
                        "relation": "AND",
                        "value": _this.searchForm.dateOfSigning
                    })
                }
                _this.search = search;
                this.getTableList();
            },

            //大合同分配
            showPage2() {
                this.showPage = 1;
                this.pageState = 'assignApply,';
                this.isFenPei = true;//改为分配状态
            },

            //分配详情
            handleClick(row) {
                console.log(row);
                this.pageState = 'assignDetails,' + row.id;
                this.showPage = 1;
                this.isFenPei = true;//改为分配状态
                this.isDetails = true;//打开详情开关
            },

            //分配编辑
            edit(row) {
                this.pageState = 'assignEdit,' + row.id;
                this.showPage = 1;
                this.isFenPei = true;//改为分配状态
            },

            closeapplypage() {
                this.showPage = 0;
                this.pageState = '';
                this.isFenPei = false;//初始化分配状态
                this.isDetails = false;//分配初始化
                this.applyKey1 += new Date();
                this.getTableList();
            },

        }
    }
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
        color: #409EFF;
    }

    .el-icon-arrow-down {
        font-size: 12px;
    }

    .pagination {
        margin-top: 20px;
        display: flex;
        justify-content: flex-end;
    }
</style>
