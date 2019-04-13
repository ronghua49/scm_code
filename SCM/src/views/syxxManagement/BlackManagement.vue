<template>
    <div>
        <transition>
            <div class="content-item" v-show="!isShowApply">
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

                            <el-form-item label="商务分区">
                                <el-select clearable style="width: 100%" v-model="searchForm.businessDivision" placeholder="请选择"
                                           @change="selectchange">
                                    <el-option
                                        v-for="(item,index) in shangwufenqu"
                                        :key="index"
                                        :label="item.businessDivision"
                                        :value="item.id">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item  label="省区">
                                <el-select clearable style="width: 100%" v-model="searchForm.province" placeholder="请选择">
                                    <el-option
                                        v-for="(item,index) in provinceList"
                                        :key="index"
                                        :label="item.province"
                                        :value="item.id">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="商业名称">
                                <el-input clearable v-model="searchForm.commerceName" placeholder="请输入内容"></el-input>
                            </el-form-item>

                        </el-form>
                    </el-main>

                    <div class="header">
                        <div class="headleft">
                            <span class="el-icon-tickets"></span>
                            <span class="sx">数据列表</span>

                        </div>
                        <div class="headright">
                            <el-row>
                                <el-button type="primary" class="el-icon-circle-plus-outline" size="mini"
                                           @click="addBlackList">
                                    新增
                                </el-button>

                            </el-row>
                        </div>
                    </div>

                    <!--表格栏-->
                    <el-table
                        :data="tableList.rows"
                        highlight-current-row
                        v-loading="loading"
                        element-loading-text="加载中" border stripe fit
                        :show-overflow-tooltip="false"
                        max-height="410"
                        size="mini"
                        align="left"
                        style="width:100%;">

                        <el-table-column type="index" width="50" label="序号" align="center">

                        </el-table-column>
                        <el-table-column v-for="column in columns" header-align="center"
                                         align="center"
                                         :prop="column.prop" :label="column.label"
                                         :key="column.prop" :min-width="column.minWidth"
                                         :formatter="column.formatter"
                                         :sortable="column.sortable==null?true:column.sortable">
                        </el-table-column>


                        <el-table-column label="操作" width="220" fixed="right" header-align="center" align="center">
                            <template slot-scope="scope">
                                <el-button :disabled="scope.row.approvalState !== '0'" icon="el-icon-edit" size="mini" type="primary"  @click="edit(scope.row)" >
                                    编辑
                                </el-button>
                                <el-button icon="el-icon-view" size="mini" type="primary"  @click="handleClick(scope.row)" >
                                    查看详情
                                </el-button>
                            </template>
                        </el-table-column>

                    </el-table>


                    <!--分页-->
                    <div class="pagination">
                        <div class="block">
                            <el-pagination
                                @size-change="handleSizeChange"
                                @current-change="handleCurrentChange"
                                background
                                :current-page="pageRequest.page"
                                :page-sizes="[10, 20, 30, 40, 50]"
                                :page-size="pageRequest.pageSize"
                                layout="sizes, total, prev, pager, next"
                                :total="tableList.total">
                            </el-pagination>
                        </div>
                    </div>
                </el-container>
            </div>
        </transition>


        <transition name="el-fade-in">
            <div v-show="isShowApply">
                <black-apply @closeApply="closeApply"
                             :shangwufenqu="shangwufenqu"
                             :key="isShowKey"
                             :pageState="pageState">

                </black-apply>
            </div>
        </transition>

    </div>
</template>

<script>
    import storage from "@/utils/storage";
    import BlackManagementApply from "./BlackManagementApply";


    export default {
        name: "BlackManagement",
        components: {
            "black-apply": BlackManagementApply
        },
        data() {
            return {
                ownerId: '',
                componentParams: {
                    flag: '',// 是编辑还是查看详情还是申请 与id是配对的
                    id: '',
                    title: '黑名单申请',
                    detailsdisabled: false
                },
                searchForm: {
                    businessDivision: '',
                    province: '',
                    commerceName: ''
                },
                search:[],
                //新增/编辑/详情组件key值
                isShowKey:'',
                loading: true,
                //页面状态
                pageState:'',
                select1: [],
                select2: [],
                tableList: [],
                //商务分区
                shangwufenqu: [],
                provinceList: '',
                pageRequest: {
                    page: 1,
                    pageSize: 10,
                    showTotal: true
                },
                isShowApply: false,
                columns: [
                    {prop: "approvalDate", label: "年度", minWidth: 100,formatter: (row, column, cellValue, index) => {
                            return cellValue.slice(0,4)
                        }},
                    {prop: "businessDivision", label: "商务分区", minWidth: 100},
                    {prop: "province", label: "省区", minWidth: 110},
                    // { prop: "createOrgName", label: "创建组织名称", minWidth: 100 },
                    {prop: "commerceCode", label: "商业编码", minWidth: 100},
                    {prop: "commerceName", label: "商业名称", minWidth: 150},
                    {
                        prop: "classify", label: "分类", minWidth: 150, formatter: (row, column, cellValue, index) => {
                            if (cellValue == '0') {
                                return '窜货'
                            }else if (cellValue == '1'){
                                return '虚假流向'
                            }
                        }
                    },
                    {prop: "penaltyReason", label: "处罚原因", minWidth: 120,},
                    {
                        prop: "approvalState",
                        label: "流程状态",
                        minWidth: 120,
                        formatter: (row, column, cellValue, index) => {
                            if (cellValue == 0) {
                                return "未提交"
                            } else if (cellValue == 1) {
                                return "审核中"
                            } else if (cellValue == 2) {
                                return "已通过"
                            }
                        }
                    }
                ],
            }
        },

        methods: {

            handleSizeChange(val) {
                // console.log(`每页 ${val} 条`);
                this.pageRequest.pageSize = val;
                this.getTableList();
            },
            handleCurrentChange(val) {
                // console.log(`当前页: ${val}`);
                this.pageRequest.page = val;
                this.getTableList();
            },
            //select改变修改状态
            selectchange(value) {
                var self = this;

                this.shangwufenqu.map(function (v, i) {
                    if (value == v.id) {
                        self.provinceList = v.provinceList
                    }
                })
            },
            searchquery() {

                let search = []
                if (this.searchForm.businessDivision) {
                    search.push({
                        "hasInitValue": false,
                        "operation": "EQUAL",
                        "property": "businessDivisionId",
                        "relation": "AND",
                        "value": this.searchForm.businessDivision
                    })
                }
                if (this.searchForm.province) {
                    search.push({
                        "hasInitValue": false,
                        "operation": "EQUAL",
                        "property": "provinceId",
                        "relation": "AND",
                        "value": this.searchForm.province
                    })
                }
                if (this.searchForm.commerceName) {
                    search.push({
                        "hasInitValue": false,
                        "operation": "EQUAL",
                        "property": "commerceName",
                        "relation": "AND",
                        "value": this.searchForm.commerceName
                    })
                }
                this.search = search;
                this.getTableList()
            },
            //添加黑名单
            addBlackList() {
                this.isShowApply = true;
                this.pageState = 'apply,';
            },
            //详情页面
            handleClick(row) {
                this.isShowApply = true;
                this.pageState = 'details,' + row.id;
            },
            edit(row){
                this.isShowApply = true;
                this.pageState = 'edit,' + row.id;
            },
            closeApply() {
                this.isShowApply = false;
                this.pageState = '';
                this.isShowKey = + new Date();
                this.getTableList();
            },
            getTableList(search = this.search) {
                var postData = [
                    {
                        "hasInitValue": false,
                        "operation": "EQUAL",
                        "property": "ownerId",
                        "relation": "AND",
                        "value": this.ownerId
                    }
                ];
                if (search) {
                    postData = postData.concat(search)
                }
                this.$api.base.postScm({
                    url: "/scm/scmZsjCommerceBlackList/v1/list",
                    data: {
                        "pageBean": this.pageRequest,
                        "params": {},
                        "querys": postData,
                        "sorter": []
                    }
                }).then(res => {
                    this.loading = false;
                    this.tableList = res
                })
            },
            getSelestGHS() {

                // let self = this;
                this.$api.common
                    .getDivision({
                        url: "/api/scmuc/v1/getBusinessDivisionInfo",
                    }).then(res => {
                    this.shangwufenqu = res;

                })

            },
        },

        mounted() {
            this.ownerId = storage.get("ownerId");
            this.getTableList();
            this.getSelestGHS();
        },
    }
</script>

<style scoped lang="scss">
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

    .el-main {
        padding: 0 !important;
    }

    .pagination {
        margin-top: 20px;
        display: flex;
        justify-content: flex-end;
    }
</style>
