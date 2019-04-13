<template>
    <div>
        <transition>
            <div v-show="isShowPage === '1'">
                <el-container>
                    <el-header>
                        <div class="header">
                            <div class="headleft">
                                <span class="el-icon-search"></span>
                                <span class="sx">筛选查询</span>
                            </div>
                            <div class="headright">
                                <el-row>
                                    <el-button
                                        type="primary"
                                        class="el-icon-search"
                                        size="mini"
                                        @click="searchquery"
                                    >查询
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
                            height="500"
                        >
                            <el-form-item label="确认情况">
                                <el-select
                                    clearable
                                    style="width: 100%"
                                    v-model="searchForm.approvalState"
                                    placeholder="请选择"
                                >
                                    <el-option
                                        v-for="item in options"
                                        :key="item.label"
                                        :label="item.value"
                                        :value="item.label"
                                    ></el-option>
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
                                        value-format="yyyy-MM-dd HH:mm:ss"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                    ></el-date-picker>
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
                                    <el-button
                                        type="primary"
                                        class="el-icon-circle-plus-outline"
                                        size="mini"
                                        @click="showPage"
                                    >集中发货合同申请
                                    </el-button>
                                </el-row>
                            </div>
                        </div>

                        <el-table
                            v-loading="loading"
                            :data="tableData.rows"
                            border
                            stripe
                            element-loading-text="加载中"
                            style="width: 100%"
                            max-height="500"
                        >
                            <el-table-column
                                sortable
                                v-for="column in columns"
                                header-align="center"
                                align="center"
                                :prop="column.prop"
                                :key="column.label"
                                :label="column.label"
                                :formatter="column.formatter"
                                :show-overflow-tooltip="true"
                                min-width="120"
                            ></el-table-column>

                            <el-table-column
                                label="操作"
                                width="220"
                                fixed="right"
                                header-align="center"
                                align="center"
                            >
                                <template slot-scope="scope">
                                    <el-button
                                        icon="el-icon-edit"
                                        size="mini"
                                        type="primary"
                                        :disabled="scope.row.approvalState !== '0'"
                                        @click="edit(scope.row)"
                                    >编辑
                                    </el-button>
                                    <el-button
                                        icon="el-icon-view"
                                        size="mini"
                                        type="primary"
                                        @click="details(scope.row)"
                                    >查看详情
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
                                :total="tableData.total"
                            ></el-pagination>
                        </div>
                    </el-main>
                </el-container>
            </div>
        </transition>
        <transition>
            <div v-show="isShowPage === '2'" :key="applyKey1">
                <focus-send-apply
                    @closeapplypage="closeapplypage"
                    :pageState="pageState"
                >

                </focus-send-apply>
            </div>
        </transition>
    </div>
</template>

<script>
    import storage from "@/utils/storage";
    import FocusSendApply from "./FocusSendApply";

    export default {
        name: "FocusSend",
        components: {
            "focus-send-apply": FocusSendApply
        },
        data() {
            return {
                tabarr: [],
                ownerId: "",
                isShowPage: '1',
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
                    contractCode: "" // 合同编号
                },
                search: [],
                resultData: {},
                options: [
                    {
                        value: "未提交",
                        label: "0"
                    },
                    {
                        value: "审批中",
                        label: "1"
                    },
                    {
                        value: "已通过",
                        label: "2"
                    },
                    {
                        value: "未通过",
                        label: "3"
                    }
                ],
                columns: [
                    {
                        prop: "focusSendCode",
                        label: "合同编号"
                    },
                    {
                        prop: "approvalState",
                        label: "审批状态",
                        formatter: function (row, column, cellValue, index) {
                            if (cellValue == '0') {
                                return '未提交'
                            } else if (cellValue == '1') {
                                return '审批中'
                            } else if (cellValue == '2') {
                                return '已通过'
                            } else if (cellValue == '3') {
                                return "已驳回"
                            }
                        }
                    },

                    {
                        prop: "dateOfSigning",
                        label: "签订时间",
                        formatter: function (row, column, cellValue, index) {
                            if (cellValue) {
                                let string = cellValue;
                                string = string.toString().slice(0, 10);
                                return string
                            }
                        }
                    },


                    {
                        prop: "marketingWay",
                        label: "购销方式"
                    },
                    {
                        prop: "ownerName",
                        label: "供应商"
                    },
                    {
                        prop: "commerceName",
                        label: "经销商"
                    },
                    {
                        prop: 'consignee',
                        label: '收货联系人',

                    }, {
                        prop: 'entryPeople',
                        label: '录单人',
                    },
                ],
                tableData: {
                    rows: []
                },
                pageRequest: {
                    page: 1,
                    pageSize: 10,
                    showTotal: true
                }
            };
        },
        mounted() {
            this.ownerId = storage.get("ownerId");
            this.getTableList();
        },
        methods: {

            // 页面之间切换的函数 -- 保证过渡动画流畅
            toggleShowPage(flag) {
                let self = this;
                self.isShowPage = '-1';
                setTimeout(function () {
                    self.isShowPage = flag;
                }, 300);
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
                if (this.searchForm.contractCode !== "") {
                    search.push({
                        hasInitValue: false,
                        operation: "LIKE",
                        property: "focusSendCode",
                        relation: "AND",
                        value: _this.searchForm.contractCode
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
                if (this.searchForm.dateOfSigning !== "") {
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

                this.pageState = "focusSendEdit," + row.id;
                self.toggleShowPage('2');
            },
            details(row) {
                let self = this;
                this.pageState = "focusSendDetails," + row.id;
                this.isDetails = true;
                self.toggleShowPage('2');
            },
            showPage() {
                let self = this;
                self.toggleShowPage('2');
                this.pageState = "focusSendApply,";
            },
            //关闭页面
            closeapplypage() {
                let self = this;
                self.toggleShowPage('1');
                this.isDetails = false; //详情参数复位
                this.pageState = "";
                this.applyKey1 += new Date();
                this.getTableList();
            },
            getTableList(query = this.search) {
                let _this = this;
                let postData = [
                    {
                        hasInitValue: false,
                        operation: "EQUAL",
                        property: "ownerId",
                        relation: "AND",
                        value: _this.ownerId
                    }
                ];
                if (query.length !== 0) {
                    postData = postData.concat(query);
                }

                _this.$api.xsgl
                    .post({
                        vueInstance: _this,
                        url: "/scm/scmXsFocusSend/v1/list",
                        data: {
                            pageBean: _this.pageRequest,
                            params: {},
                            querys: postData,
                            sorter: []
                        }
                    })
                    .then(res => {
                        if (res) {
                            _this.tableData = res;
                        }
                    });
            }
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
</style>
