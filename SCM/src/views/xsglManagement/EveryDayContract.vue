<template>
    <div>
        <transition>
            <div v-show="!isShowPage">
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
                                        :key="item.label"
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
                                <div class="block" >
                                    <el-date-picker style="width: 100%;"
                                        v-model="searchForm.dateOfSigning"
                                        type="daterange"
                                        align="right"
                                        unlink-panels
                                        range-separator="至"
                                        value-format="yyyy-MM-dd"
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
                                <span>合同数据列表</span>
                            </div>
                            <div class="headright">
                                <el-row>
                                    <el-button type="primary" class="el-icon-circle-plus-outline" size="mini"
                                               @click="showPage">
                                        商业日常合同申请
                                    </el-button>
                                </el-row>
                            </div>
                        </div>
                        <el-table
                            v-loading="loading"
                            :data="tableData"
                            border stripe
                            element-loading-text="加载中"
                            style="width: 100%"
                            max-height="500">
                            <el-table-column sortable
                            				 show-overflow-tooltip
                                             v-for="column in columns" header-align="center"
                                             align="center"
                                             :prop="column.prop"
                                             :key="column.label"
                                             :label="column.label"
                                             :formatter="column.formatter"
                                             width="120">
                            </el-table-column>
                            <el-table-column label="操作" width="220" fixed="right" header-align="center" align="center">
                                <template slot-scope="scope">
                                    <el-button :disabled="scope.row.approvalState != '0'" icon="el-icon-edit" size="mini"
                                               type="primary" @click="edit(scope.row)">编辑
                                    </el-button>
                                    <el-button icon="el-icon-view" size="mini" type="primary"
                                               @click="details(scope.row)">
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
        <transition>
            <div v-show="isShowPages" :key="applyKey1">
                <xs-big-apply @closeapplypage="closeapplypage" :pageState="pageState" :row="unitRowData" :isDetails="isDetails">
                </xs-big-apply>
            </div>
        </transition>
    </div>
</template>

<script>
    import storage from "@/utils/storage";
    import XsBigContractApply from "./EveryDayApply"

    export default {
        name: "EveryDayContract",
        components: {
            'xs-big-apply': XsBigContractApply
        },
        data() {
            return {
                ownerId: '',
                isShowPage: false,
                isShowPages: false,
                isDetails:false,
                pageState:"",
                applyKey1: "",
                loading: false,
                searchForm: {
                    approvalState: '', // 审批状况
                    dealer: '', // 经销商
                    dateOfSigning:'',//起止日期
                    //ownerId: '', // 货主ID -- 必传
                    contractCode: '' // 合同编号
                },
                search:[],
                pageRequest: {
                    page: 1,
                    pageSize: 10,
                    showTotal: true
                },
                resultData: {},
                options: [{
                    value: '0',
                    label: '未提交'
                }, {
                    value: '1',
                    label: '审批中'
                }, {
                    value: '2',
                    label: '已通过'
                },{
                    value: '3',
                    label: '已驳回'
                }],
                columns: [
                 {
                    prop: 'approvalState',
                    label: '审批状态',
                    formatter: function(row, column, cellValue, index){
                        if(cellValue == '0'){
                            return '未提交'
                        }else if(cellValue == '1'){
                            return '审批中'
                        }else if(cellValue == '2'){
                            return '已通过'
                        }else if(cellValue == '3'){
                            return '已驳回'
                        }
                    }
                },{
                    prop: 'contractCode',
                    label: '合同编号',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'dateOfSigning',
                    label: '签订时间',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'dealer',
                    label: '经销商',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'jaddress',
                    label: '甲方地址',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'jbankCode',
                    label: '甲方账户',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'jdutyCode',
                    label: '甲方税号',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'jopenBank',
                    label: '甲方开户行',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'jphone',
                    label: '甲方电话',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'jpostcode',
                    label: '甲方邮政编号',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'junit',
                    label: '甲方单位',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'ownerName',
                    label: '供货商',
                    formatter: (a,b,c)=>{return c}
                }, {
                	prop: 'marketingWay',
                	label: '购销方式',
                	formatter: function(row,clumn,value){
                		if ( value == 0 ) {
                			return "预付款";
                		} else {
                			return "赊账";
                		}
                	}
                }, {
                	prop: 'payType',
                	label: '付款方式',
                	formatter: (a,b,c)=>{return c}
                }, {
                	prop: 'phone',
                	label: '联系电话',
                	formatter: (a,b,c)=>{return c}
                }, {
                	prop: 'reduceContractTime',
                	label: '减免合同理由',
                	formatter: (a,b,c)=>{return c}
                }, {
                	prop: 'signTheAddress',
                	label: '签订地址',
                	formatter: (a,b,c)=>{return c}
                }, {
                	prop: 'specialOpinion',
                	label: '特求意见',
                	formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'yaddress',
                    label: '乙方地址',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'ybankCode',
                    label: '乙方账户',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'ydutyCode',
                    label: '乙方税号',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'yopenBank',
                    label: '乙方开户行',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'yphone',
                    label: '乙方电话',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'ypostcode',
                    label: '乙方邮政编号',
                    formatter: (a,b,c)=>{return c}
                }, {
                    prop: 'yunit',
                    label: '乙方单位',
                    formatter: (a,b,c)=>{return c}
                }, {
                	prop: 'createName',
                	label: '创建人名称',
                	formatter: (a,b,c)=>{return c}
                },{
                	prop: 'createTime',
                	label: '创建时间',
                	formatter: (a,b,c)=>{return c}
                },],
                tableData: [],
                unitRowData: {
			        ownerId: "",
			        ownerName: "", //供应商
			        contractCode: "", //合同编号
			        dealer: "", //经销商
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
            }
        },
        mounted() {
            this.ownerId = storage.get("ownerId");
            this.getTableList();
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
            //搜索按钮
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
                        "operation": "LIKE",
                        "property": "yunit",
                        "relation": "AND",
                        "value": _this.searchForm.dealer
                    })
                }
                if (this.searchForm.dateOfSigning !== '') {
                    search.push({
                        "hasInitValue": false,
                        "operation": "BETWEEN",
                        "property": "dateOfSigning",
                        "relation": "AND",
                        "value": _this.searchForm.dateOfSigning
                    })
                }
                _this.search = search;
                this.getTableList();
            },
            edit(row){
                this.isShowPage = true;
                this.pageState = 'everyDayEdit,'+row.id+","+row.agreementSummaryId;
            	this.unitRowData = Object.assign({},this.unitRowData,row);
                setTimeout(()=>{
                	this.isShowPages = true;
                },300);
            },
            details(row){
                this.isShowPage = true;
                this.pageState = 'everyDayDetails,'+row.id;
                this.isDetails = true;
                setTimeout(()=>{
                	this.isShowPages = true;
                },300);
            },
            showPage() {
                this.isShowPage = true;
                setTimeout(()=>{
                	this.isShowPages = true;
                },300);
                this.pageState = 'everyDayApply,';
            },
            //关闭页面
            closeapplypage() {
            	this.isShowPages = false;
            	setTimeout(()=>{
                	this.isShowPage = false;
                },300);
                this.isDetails = false;//详情参数复位
                this.pageState = '';
                this.unitRowData = "";
                this.applyKey1 += new Date();
                this.getTableList();
            },
            getTableList(query = this.search) {
                let _this = this;
                this.loading = true;
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
                    url:"/scm/scmXsEveryDayContract/v1/list",
                    data: {
                        "pageBean": _this.pageRequest,
                        "params": {},
                        "querys": postData,
                        "sorter": []
                    }
                }).then(res => {
                	this.loading = false;
                    this.tableData = res.rows;
                    this.resultData.total = res.total;
                })
            }
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
