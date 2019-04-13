<template>
    <div>

        <transition>
            <div v-show="!isShowPage">
            <el-container>
                <el-main>
                        <div class="header" style='margin-bottom: 0;'>
                            <div class="headleft">
                        <span class="el-icon-tickets
"></span>
                                <span class="sx">数据列表</span>
                            </div>
                        </div>

                        <!--表格栏-->
                        <el-table
                            :data="tableList.rows"
                            highlight-current-row
                            v-loading="loading"
                            element-loading-text="加载中" border stripe fit
                            :show-overflow-tooltip="true"
                            max-height="500"
                            size="mini"
                            align="left"
                            style="width:100%;">
                            <el-table-column type="index" width="50" label="序号" align="center">

                            </el-table-column>
                            <el-table-column v-for="column in columns"
                                             header-align="center" align="center"
                                             :prop="column.prop"
                                             :label="column.label"
                                             :key="column.prop"
                                             :min-width="column.minWidth"
                                             :formatter="column.formatter" sortable >
                            </el-table-column>


                            <el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
                                <template slot-scope="scope">
                                    <el-button  size="mini"
                                               type="primary" @click="approval(scope.row)">审批
                                    </el-button>

                                </template>
                            </el-table-column>

                        </el-table>


                        <div class="pagination">
                            <div class="block">
                                <el-pagination
                                    @size-change="handleSizeChange"
                                    @current-change="handleCurrentChange"
                                    background
                                    :current-page="pageRequest.page"
                                    :page-sizes="[10, 20, 30, 40, 50]"
                                    :page-size="pageRequest.pageSize"
                                    layout="sizes, total, prev, pager, next "
                                    :total="tableList.total">
                                </el-pagination>
                            </div>
                        </div>

                </el-main>
            </el-container>
            </div>
        </transition>




        <!--审批页面-->
        <transition>
            <div v-show="isShowPage" :approvalKey="approvalKey">
               <el-container>
                   <el-header>
                       <div class="searchHeader">
                           <div class="searchheadleft">
                               <el-button @click="showParentComp" size="mini" class="el-icon-back" type="primary">返回</el-button>

                           </div>

                           <div class="searchheadcenter">{{componentParams.title}}</div>
                           <div class="searchheadright" v-show="componentParams.flag !== 'details' ">
                               <approval-btn :taskId="taskId" @handleSuccess="approvalSuccess"></approval-btn>
                           </div>
                       </div>
                   </el-header>
                   <el-main style="padding-top: 0">


                       <div class="header">
                           <div class="headleft">
                        <span class="el-icon-tickets"></span>
                               <span class="sx">基本信息</span>
                           </div>
                       </div>
                       <el-form ref="form" :model="details" size="small" disabled label-width="120px">
                           <el-form-item label="申请单号">
                               <el-input v-model="details.approvalCode"></el-input>
                           </el-form-item>
                           <el-form-item label="申请人">
                               <el-input v-model="details.name"></el-input>
                           </el-form-item>
                           <el-form-item label="申请日期">
                               <el-input v-model="details.approvalDate"></el-input>
                           </el-form-item>

                           <div class="header">
                               <div class="headleft">
                                   <span class="el-icon-edit-outline"></span>
                                   <span class="sx">商业信息</span>
                               </div>
                           </div>

                           <el-form-item label="商务分区">
                               <el-input v-model="details.businessDivision"></el-input>
                           </el-form-item>
                           <el-form-item label="省区">
                               <el-input v-model="details.province"></el-input>
                           </el-form-item>

                           <el-form-item label="商业编号">
                               <el-input v-model="details.commerceCode"></el-input>
                           </el-form-item>
                           <el-form-item label="商业名称">
                               <el-input v-model="details.commerceName"></el-input>
                           </el-form-item>
                           <el-form-item label="分类">
                               <el-input  v-model="details.classify"></el-input>
                           </el-form-item>
                           <el-form-item label="处罚原因">
                               <el-input v-model="details.penaltyReason"></el-input>
                           </el-form-item>

                           <el-form-item label="禁销品种">
                               <el-input v-model="details.bannedBreed"></el-input>
                           </el-form-item>
                           <div class="header">
                               <div class="headleft">
                                   <span class="el-icon-edit-outline"></span>
                                   <span class="sx">备注信息</span>
                               </div>
                           </div>
                           <el-form-item label="备注信息" style="width: 100%">
                               <el-input
                                   disabled
                                   type="textarea"
                                   v-model="details.approvalMemo">
                               </el-input>
                           </el-form-item>

                       </el-form>

                       <div class="header">
                           <div class="headleft">
                        <span class="el-icon-tickets
"></span>
                               <span class="sx">流程图</span>
                           </div>
                       </div>
                       <el-row class="searchBox">
                           <approval-flow :flowData="flowData"></approval-flow>
                       </el-row>


                       <div class="header">
                           <div class="headleft">
                        <span class="el-icon-tickets
"></span>
                               <span class="sx">审批历史</span>
                           </div>
                       </div>

                       <el-row class="searchBox" style="width: 100%">
                           <approval-history :instId="instId"></approval-history>
                       </el-row>

                   </el-main>
               </el-container>
            </div>
        </transition>
    </div>
</template>

<script>
    import approvalBtn  from "@/components/approval/ApprovalBtn"
    import approvalFlow  from "@/components/approval/ApprovalFlow"
    import approvalHistory  from "@/components/approval/ApprovalHistory"
    import wareHouse from "@/components/zsj/Warehouse"
    import shipAddress from "@/components/zsj/ShipAddress"
    import storage from "@/utils/storage";

    export default {
        name: "BlackManagementApple",
        components: {
            'approval-btn':approvalBtn,
            'approval-flow':approvalFlow,
            'approval-history':approvalHistory,
            'ware-house': wareHouse,
            'ship-address':shipAddress
        },
        mounted() {
            this.ownerId = storage.get("ownerId");
            this.getTableList()
        },
        data() {
            return {
                ownerId: '',
                taskId:'',
                instId:'',
                flowData:{

                },
                isShowPage:false,
                componentParams: {
                    flag: '',// 是编辑还是查看详情还是申请 与id是配对的
                    id: '',
                    title: '商业黑名单审批',
                    detailsdisabled: false
                },
                pageRequest: {
                    page: 1,
                    pageSize: 10,
                    showTotal: true
                },
                loading: true,
                approvalKey:'',
                //表格信息
                tableList: {
                    page: 1,
                    pageSize: 10,
                    rows: [],
                    total: 3
                },
                details:{

                },
                //表格
                columns: [
                    {prop: "subject", label: "任务标题", minWidth: 150},
                    {prop: "name", label: "任务名称", minWidth: 150},
                    {prop: "ownerName", label: "所属人", minWidth: 100},
                    {prop: "assigneeName", label: "执行人", minWidth: 100},
                    {prop: "status", label: "类型", minWidth: 110, formatter:(row, column, cellValue, index)=>{return cellValue=='NORMAL'?'普通':cellValue}},
                    {prop: "createTime", label: "创建时间", minWidth: 150},
                    { prop: "", label: "到期时间",minWidth: 100 },
                ],

            }
        },
        methods: {
            handleSizeChange(val) {
                this.pageRequest.pageSize = val;
                this.getTableList();
            },
            handleCurrentChange(val) {
                this.pageRequest.page = val;
                this.getTableList();
            },
            showParentComp(){
                this.isShowPage = false;
                this.approvalKey = new Date();
                this.getTableList();
            },

            //审批成功
            approvalSuccess(){
                let self = this;
                setTimeout(function () {
                    self.showParentComp();
                },300)
            },
            //审批按钮
            approval(row){
                this.taskId = row.id;
                this.instId = row.procInstId;
                this.getDetails(row.procInstId);
                this.flowData = {
                    taskId: row.taskId
                };
                this.isShowPage = true;


            },
            //获取详情
            getDetails(id){
                let self = this;
                self.$api.base.getScm({
                    url: "/scm/scmZsjCommerceBlackList/v1/getByApplyId/"+id,
                }).then(res=>{
                    this.details = res.value;
                })
            },

            getTableList() {
                let self = this;
                self.$api.approval.post({
                    url: "/runtime/task/v1/list",
                    data: {
                        "pageBean": self.pageRequest,
                        "params": {},
                        querys: [
                            {
                                hasInitValue: false,
                                operation: "EQUAL",
                                property: "assigneeId",//登录人 id
                                relation: "AND",
                                value: storage.get('userId')
                            },
                            {
                                hasInitValue: false,
                                operation: "EQUAL",
                                property: "procDefKey",
                                relation: "AND",
                                value: "syhmdsp"
                            }
                        ],
                        "sorter": []
                    }
                }).then(res => {
                    self.loading = false;
                    self.tableList = res;

                })
            }

        }
    }
</script>

<style scoped lang="scss">
    .el-header{
        padding: 0 !important;
        height: auto!important;
    }
    .el-main{
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
    .pagination {
        margin-top: 20px;
        display: flex;
        justify-content: flex-end;
    }
</style>
