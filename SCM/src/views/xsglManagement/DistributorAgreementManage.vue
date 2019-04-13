<template>
    <div>
        <transition name="el-fade-in">
            <div class="content-item" v-show="showManagePage">
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
                        <el-form label-position="right" size="small" label-width="120px" class="demo-ruleForm"
                                 :model="searchForm" ref="searchForm">
                            <el-form-item label="年度">
                                <!--<el-select clearable v-model="searchForm.year" placeholder="请选择年度">
                                    <el-option v-for="item in searchYearsList" :key="item.year" :label="item.year" :value="item.year"></el-option>
                                </el-select>-->
                            </el-form-item>

                            <el-form-item label="商务分区">
                                <el-select clearable v-model="searchForm.businessDivisionId" placeholder="请选择商务分区"
                                           @change="getPrivence">
                                    <el-option v-for="item in divisionList" :key="item.id"
                                               :label="item.businessDivision" :value="item.id"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="省区">
                                <el-select clearable v-model="searchForm.provinceId" placeholder="请选择省区">
                                    <el-option v-for="item in searchProvinceList" :key="item.id" :label="item.province"
                                               :value="item.id"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="商业名称">
                                <el-input v-model="searchForm.commerceName" clearable placeholder="请输入商业名称"></el-input>
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
                                <el-button @click="showApplyPageFunc" type="primary" icon="el-icon-circle-plus-outline"
                                           size="mini">协议签订
                                </el-button>
                            </el-row>
                        </div>
                    </div>
                    <!--表格栏-->
                    <el-table :data="pageResult.rows" highlight-current-row v-loading="loading"
                              element-loading-text="加载中" border stripe fit max-height="500" size="mini" align="left"
                              style="width:100%;">
                        <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
                        <el-table-column :show-overflow-tooltip="showOverflowTooltip" prop="year" key="year" label="年度"
                                         min-width="80" header-align="center" align="center"></el-table-column>
                        <el-table-column :show-overflow-tooltip="showOverflowTooltip" prop="clauseCode" key="clauseCode"
                                         label="协议号" header-align="center" align="center"></el-table-column>
                        <el-table-column :show-overflow-tooltip="showOverflowTooltip" prop="approvalStateStr"
                                         key="approvalStateStr" label="审批状态" header-align="center"
                                         align="center"></el-table-column>
                        <el-table-column :show-overflow-tooltip="showOverflowTooltip" prop="businessDivision"
                                         key="businessDivision" label="商务分区" header-align="center"
                                         align="center"></el-table-column>
                        <el-table-column :show-overflow-tooltip="showOverflowTooltip" prop="province" key="province"
                                         label="省区" header-align="center" align="center"></el-table-column>
                        <el-table-column :show-overflow-tooltip="showOverflowTooltip" prop="commerceId" key="commerceId"
                                         label="商业编码" header-align="center" align="center"></el-table-column>
                        <el-table-column :show-overflow-tooltip="showOverflowTooltip" prop="commerceName"
                                         key="commerceName" label="商业名称" header-align="center"
                                         align="center"></el-table-column>
                        <el-table-column :show-overflow-tooltip="showOverflowTooltip" prop="functionType"
                                         key="functionType" label="市场类型" header-align="center"
                                         align="center"></el-table-column>
                        <el-table-column :show-overflow-tooltip="showOverflowTooltip" prop="scm_zsj_commerceState"
                                         key="scm_zsj_commerceState" label="商业认可状态" header-align="center"
                                         align="center"></el-table-column>

                        <el-table-column header-align="center" align="center" label="购进渠道">
                            <el-table-column :show-overflow-tooltip="showOverflowTooltip" prop="provincePlatformId"
                                             key="provincePlatformId" label="省级平台名称" header-align="center"
                                             align="center"></el-table-column>
                            <el-table-column :show-overflow-tooltip="showOverflowTooltip" prop="upCommerceId"
                                             key="upCommerceId" label="上游商业名称" header-align="center"
                                             align="center"></el-table-column>
                        </el-table-column>
                        <el-table-column :show-overflow-tooltip="showOverflowTooltip" prop="isSubsidiary"
                                         key="isSubsidiary" :formatter="isSubsidiaryFormat" label="是否分公司"
                                         header-align="center" align="center">
                            <template slot-scope="scope">
                                <span style="font-size:20px;">{{scope.row.isSubsidiary === '0' ? '×' : '√'}}</span>
                            </template>
                        </el-table-column>

                        <el-table-column label="操作" width="220" fixed="right" header-align="center" align="center">
                            <template slot-scope="scope">
                                <el-button :disabled="scope.row.approvalState !== '0'" icon="el-icon-edit" size="mini"
                                           type="primary" @click="handleEdit(scope.$index, scope.row)">编辑
                                </el-button>
                                <el-button icon="el-icon-view" size="mini" type="primary"
                                           @click="handleDetails(scope.$index, scope.row)">查看详情
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="pagination">
                        <el-pagination @current-change="refreshPageRequest" @size-change="refreshPageSizeRequest"
                                       background :current-page="pageRequest.page" :page-sizes="[10, 20, 30, 40, 50]"
                                       :page-size="pageRequest.pageSize" layout="sizes, total, prev, pager, next"
                                       :total="pageResult.total">
                        </el-pagination>
                    </div>
                </el-container>
            </div>
        </transition>

        <!-- 协议签订 -->
        <transition name="el-fade-in">
            <div class="content-item" v-show="showApplyPage">
                <distributor-agreement-apply :key="forceRefresh" v-on:closeapplypage="closeapplypage"
                                             :passparam="editItemId"></distributor-agreement-apply>
            </div>
        </transition>

    </div>
</template>
<script>
    import storage from "@/utils/storage";
    import DistributorAgreementApply from "./DistributorAgreementApply"; // 引入协议签订组件
    export default {
        name: "distributorAgreementManage",
        components: {
            "distributor-agreement-apply": DistributorAgreementApply
        },
        data() {
            return {
                alignCenter: true,
                showOverflowTooltip: true,
                forceRefresh: 0, // 强制重置子组件
                ownerId: "",
                pageResult: {},
                loading: false,
                // 是否显示签订协议组件
                showApplyPage: false,
                // 显示管理页面
                showManagePage: true,
                editItemId: '', // 传给子组件的参数 -- 怎么区分是编辑还是查看详情呢 标识+id 逗号分隔
                pageRequest: {
                    page: 1,
                    pageSize: 10,
                    showTotal: true
                },
                // 商务分区列表
                divisionList: [],
                // 省区列表
                searchProvinceList: [],
                // 年度列表
                searchYearsList: [],
                // 查询参数
                searchForm: {
                    year: '', // 年度
                    businessDivisionId: '', // 商务分区
                    provinceId: '', // 省区
                    commerceName: '' // 商业名称
                }
            };
        },
        methods: {
            isSubsidiaryFormat(row, column, value, index) {
                let returnVal = "未知";
                switch (row.isSubsidiary) {
                    case "0":
                        returnVal = "×";
                        break;
                    case "1":
                        returnVal = "√";
                        break;
                    default:
                        break;
                }

                return returnVal;
            },
            handleClose(done) {
                this.dialogTableVisible = false;
            },
            refreshPageRequest: function (page = 1) {
                this.pageRequest.page = page;
                this.searchquery();
            },
            refreshPageSizeRequest: function (pageSize) {
                this.pageRequest.pageSize = pageSize;
                this.searchquery();
            },
            toogleApplyPage(flag) {
                let self = this;
                if (flag === 1) {
                    self.showManagePage = !self.showManagePage;
                    setTimeout(function () {
                        self.showApplyPage = !self.showApplyPage;
                    }, 300);
                } else {
                    self.showApplyPage = !self.showApplyPage;
                    setTimeout(function () {
                        self.showManagePage = !self.showManagePage;

                    }, 300);
                }
            },
            // 查看详情
            handleDetails(index, item) {
                let self = this;
                //self.showApplyPage = true;
                self.toogleApplyPage(1);
                self.editItemId = 'details,' + item.distributorAgreementId;

            },
            // 点击编辑按钮
            handleEdit(index, item) {
                let self = this;
                // self.showApplyPage = true;
                self.toogleApplyPage(1);
                self.editItemId = 'edit,' + item.distributorAgreementId;
            },
            // 点击 协议签订 按钮
            showApplyPageFunc() {
                let self = this;
                self.editItemId = 'addNew,';
                // self.showApplyPage = true;
                self.toogleApplyPage(1);
            },
            // v-on 绑定不支持驼峰命名
            closeapplypage() {
                let self = this;
                // self.showApplyPage = false;
                self.toogleApplyPage(2);
                setTimeout(function () {
                    self.forceRefresh = +new Date();
                    self.editItemId = '';
                }, 500);
                self.searchquery();
            },
            // 获取年度
            getYears() {
                let self = this;
                self.$api.xsgl
                    .get({
                        vueInstance: self,
                        url: "/scm/scmXsDistributorClause/v1/getYears/" + self.ownerId,
                        data: {}
                    }, function (res) {
                        console.log(res);
                        self.searchYearsList = res.value;

                    });
            },
            // 获取商务分区
            getDivision() {
                let self = this;
                self.$api.xsgl.getZdBusinessDivision({
                    vueInstance: self
                }, function (res) {
                    self.divisionList = res;
                });
            },
            // 获取省区
            getPrivence() {
                console.log(this.searchForm);
                this.searchForm.provinceId = ''
                let divisionId = this.searchForm.businessDivisionId // 商务分区ID
                this.searchProvinceList = this.divisionList.reduce((final, item, index, arr) => {
                    if (item.id == divisionId) {
                        final = item.provinceList;
                    }
                    return final
                }, []);
            },
            // 筛选查询
            searchquery() {
                let query = [];
                let searchForm = Object.assign({}, this.searchForm);
                for (var key in searchForm) {
                    if (searchForm[key] !== '') {
                        if (key === "AgreementStartDate") {
                            query.push({
                                hasInitValue: false,
                                operation: "BETWEEN",
                                relation: "AND",
                                value: [searchForm[key] + "-01-01", searchForm[key] + "-12-31"],
                                property: key
                            });
                        } else {
                            query.push({
                                hasInitValue: false,
                                operation: "EQUAL",
                                relation: "AND",
                                value: searchForm[key],
                                property: key
                            });
                        }
                    }
                }
                this.findPage(query);
            },
            //列表
            findPage(search = []) {
                let self = this;
                self.loading = true;
                self.$api.xsgl
                    .post({
                        vueInstance: self,
                        url: "/scm/scmXsDistributorClause/v1/firstList",
                        data: {
                            pageBean: self.pageRequest,
                            params: {},
                            //querys: [{hasInitValue: false,property: "ownerId", value: self.ownerId, operation: "EQUAL", relation: "AND"}].concat(search),
                            querys: [].concat(search), // 测试代码
                            sorter: []
                        }
                    }, function (res) {
                        self.pageResult = res;
                        self.loading = false;
                    });
            }

        },
        mounted() {
            this.ownerId = storage.get("ownerId");
            // 获取年份
            this.getYears();
            // 获取商务分区
            this.getDivision();
            // 获取列表
            this.findPage();
        }
    };
</script>
<style scoped="scoped" lang="scss">
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

    .tab-title {
        width: 100%;
        height: 38px;
        font-size: 14px;
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

    .title {
        display: flex;
        justify-content: flex-start;
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

    header {
        padding: 0 !important;
        height: 50px !important;
        margin-top: 3px !important;
    }

    .el-table .test-column {
        font-size: 28px;
    }
</style>
