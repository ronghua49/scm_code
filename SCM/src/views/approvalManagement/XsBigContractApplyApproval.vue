<template>
    <div>
        <!--商业大合同审批-->
        <transition name="el-fade-in">
            <div v-show="isShowPage1">
                <el-container>
                    <el-main>
                        <el-row>
                            <div class="header" style="margin: 0">
                                <div class="headleft">
                                    <span class="el-icon-tickets"></span>
                                    <span class="sx">数据列表</span>
                                </div>
                            </div>

                            <el-row>
                                <el-table
                                    v-loading="loadingIndex"
                                    :data="listData"
                                    border stripe fit
                                    element-loading-text="加载中"
                                    style="width: 100%"
                                    size="mini"
                                    max-height="500">
                                    <el-table-column  type="index" width="50" label="序号"
                                                     align="center"></el-table-column>
                                    <el-table-column sortable
                                                     v-for="column in columns" header-align="center" align="center"
                                                     :prop="column.prop"
                                                     :key="column.label"
                                                     :label="column.label"
                                                     :show-overflow-tooltip="true"
                                                     >
                                    </el-table-column>


                                    <el-table-column label="操作" width="120" fixed="right" header-align="center"
                                                     align="center">
                                        <template slot-scope="scope">
                                            <el-button size="mini"
                                                       type="primary" @click="approval(scope.row)">审批
                                            </el-button>

                                        </template>
                                    </el-table-column>
                                </el-table>
                            </el-row>


                            <!--分页-->
                            <div class="pagination">

                                <el-pagination
                                    background
                                    @size-change="handleSizeChange"
                                    @current-change="handleCurrentChange"
                                    :current-page="pageRequest.currentPage4"
                                    :page-sizes="[10, 20, 30, 40,50]"
                                    :page-size="pageRequest.pageSize"
                                    layout="total, sizes, prev, pager, next "
                                    :total="listData.total">
                                </el-pagination>
                            </div>


                        </el-row>
                    </el-main>
                </el-container>
            </div>
        </transition>


        <!--审批详情页面-->
        <transition name="el-fade-in">
            <div v-show='isShowPage2' :key="refresh">
                <big-contract-apply :pageState="pageState" @closeapplypage="closeapplypage" :isApproval="isApproval" :row="row">

                </big-contract-apply>
                <el-row>
                    <el-row>
                        <div class="header" style="margin: 0">
                            <div class="headleft">
                                <span class="el-icon-tickets"></span>
                                <span class="sx">流程图</span>
                            </div>
                        </div>
                        <el-row class="liu-cheng-tu">
                            <approval-flow :flowData="flowData"></approval-flow>
                        </el-row>
                    </el-row>
                    <el-row>
                        <div class="header" style="margin: 0">
                            <div class="headleft">
                                <span class="el-icon-tickets"></span>
                                <span class="sx">审批历史</span>
                            </div>
                        </div>
                        <el-row>
                            <approval-history :instId="instId"></approval-history>
                        </el-row>
                    </el-row>
                </el-row>
            </div>
        </transition>

    </div>
</template>

<script>
    import approvalBtn from "@/components/approval/ApprovalBtn"
    import approvalFlow from "@/components/approval/ApprovalFlow"
    import approvalHistory from "@/components/approval/ApprovalHistory"
    import wareHouse from "@/components/zsj/Warehouse"
    import shipAddress from "@/components/zsj/ShipAddress"
    import storage from "@/utils/storage"
    import xsBigContractApply from "../xsglManagement/XsBigContractApply.vue"
    export default {
        name: "XsBigContractApplyApproval",
        components: {
            'approval-btn': approvalBtn,
            'approval-flow': approvalFlow,
            'approval-history': approvalHistory,
            'ware-house': wareHouse,
            'ship-address': shipAddress,
            'big-contract-apply':xsBigContractApply
        },
        mounted() {
            this.ownerId = storage.get("ownerId");
            this.getTableList();
        },
        data() {
            return {
                ownerId: '',
                taskId: '',
                isShowPage1: true,
                isShowPage2: false,
                pageState:'',
                isApproval:true,
                refresh: '',
                row:'',
                flowData:{},
                instId:"",
                pageRequest: {
                    page: 1,
                    pageSize: 10,
                    showTotal: true
                },
                componentParams: {
                    flag: '',// 是编辑还是查看详情还是申请 与id是配对的
                    id: '',
                    title: '商业大合同审批',
                    detailsdisabled: false
                },
                loadingIndex: false,
                //数据主表
                listData: [],
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
        watch: {
            refresh(value) {
                this.getTableList();
            }
        },
        methods: {
            //审批成功
            approvalSuccess() {

            },
            handleSizeChange(val) {
                this.pageRequest.pageSize = val;
                this.getTableList();
            },
            handleCurrentChange(val) {
                this.pageRequest.page = val;
                this.getTableList();
            },
            showParentComp() {
                this.refresh += new Date();
                this.isShowPage1 = false;
                setTimeout(()=>{
                	this.isShowPage2 = true;
                },)
            },
            //主页面表格获取
            getTableList() {
                this.$api.approval.post({
                	url: '/runtime/task/v1/list',
                	data: {
					  "pageBean": {
					    "page": this.pageRequest.page,
					    "pageSize": this.pageRequest.pageSize,
					    "showTotal": true
					  },
					  "params": {},
					  "querys": [{
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
		                    value: "dhtsq"
	                   }],
					  "sorter": [
					    {
					      "direction": "DESC",
					      "property": "entryDate"
					    }
					  ]}
				}).then(res => {
					if ( res.hasOwnProperty('message') ) {
						this.$message.error('系统错误');
						return;
					}
					this.listData = res.rows;
					this.listData.total = res.total;
				})
            },
            approval(row) {
                this.isShowPage1 = false;
                setTimeout(()=>{
                	this.isShowPage2 = true;
                },)
                if ( this.$route.fullPath == '/approval/XsBigContractApplyApproval') { 
                	this.pageState = 'approval,'+row.procInstId;
                } else {
                	this.pageState = 'approval,'+row.id;
                }
                this.instId = row.approvalId?row.approvalId:row.procInstId;
                this.row = row;
                this.flowData={
                    taskId:row.taskId
                }
            },
            closeapplypage(){
                this.isShowPage2 = false;
                setTimeout(()=>{
                	this.isShowPage1 = true;
                },)
                this.pageState = '';
                this.getTableList();
            }
        }
    }
</script>

<style scoped lang="scss">
    .el-header{
        padding: 0!important;
        height: auto!important;
    }
    .el-main{
        padding: 0!important;
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
            width: 50%;
        }
    }
    .liu-cheng-tu{
        padding: 24px 0;
    }
    .pagination {
        margin-top: 20px;
        display: flex;
        justify-content: flex-end;
    }

</style>
