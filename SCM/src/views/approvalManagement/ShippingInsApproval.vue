<template>
    <div>
        <!--商业大合同审批-->
        <transition name="el-fade-in">
            <div v-show="!isShowPage">
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
                                    :data="listData.rows"
                                    border stripe fit
                                    element-loading-text="加载中"
                                    style="width: 100%"
                                    max-height="500">
                                    <el-table-column type="index" width="50" label="序号"
                                                     align="center"></el-table-column>
                                    <el-table-column sortable
                                                     show-overflow-tooltip
                                                     v-for="column in columns" header-align="center" align="center"
                                                     :prop="column.prop"
                                                     :key="column.label"
                                                     :label="column.label"
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
            <div v-show='isShowPage' :key="refresh">


                <div>
                    <div class="searchHeader">
                        <div class="searchheadleft">
                            <el-button @click="showParentComp" size="mini" class="el-icon-back" type="primary">返回</el-button>
                        </div>

                        <div class="searchheadcenter">发货指令审批</div>
                        <div class="searchheadright">
                            <div>
                                <approval-btn :taskId="taskId" @handleSuccess="approvalSuccess"></approval-btn>
                            </div>
                        </div>
                    </div>


                    <div class="header" style="margin-bottom: 24px;">
                        <div class="headleft">
                            <span class="el-icon-tickets"></span>
                            <span class="sx">基础数据</span>
                        </div>
                    </div>


                    <el-form ref="form" disabled size="small" :model="form" label-width="80px">
                        <el-form-item label="商务分区">
                            <el-input v-model="form.businessDivision"></el-input>
                        </el-form-item>
                        <el-form-item label="省区">
                            <el-input v-model="form.province"></el-input>
                        </el-form-item>
                        <el-form-item label="商业名称">
                            <el-input v-model="form.commerceName"></el-input>
                        </el-form-item>
                    </el-form>

                    <div class="header" style="margin: 0">
                        <div class="headleft">
                            <span class="el-icon-tickets"></span>
                            <span class="sx">数据列表</span>
                        </div>
                    </div>
                    <el-table
                        :data="tableData.rows"
                        border
                        style="width: 100%">
                        <el-table-column
                            fixed
                            align="center"
                            type="index"
                            label="序号"
                            width="60">
                        </el-table-column>
                        <el-table-column
                            v-for="(item,index) in columns2"
                            :prop="item.prop"
                            :label="item.label"
                            :key="'shippingIns' + index"
                            :min-width="item.width"
                            :formatter="item.formatter"
                            align="center"
                            >
                            <el-table-column
                                v-if="item.children"
                                v-for="(int,ins) in item.children"
                                :prop="int.prop"
                                :label="int.label"
                                :key=" 'shippingInsChild'+ins"
                                :min-width="int.width"
                                :formatter="int.formatter"
                                align="center"
                            >

                            </el-table-column>
                        </el-table-column>


                    </el-table>

                </div>
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
    import storage from "@/utils/storage";
    import xsBigContractApply from "../xsglManagement/FocusSendApply.vue"
    export default {
        name: "FocusSendApproval",
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
                isShowPage: false,
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
                listData: {
                    rows:[]
                },
                columns: [
                    {prop: "subject", label: "任务标题", minWidth: 150},
                    {prop: "name", label: "任务名称", minWidth: 150},
                    {prop: "ownerName", label: "所属人", minWidth: 100},
                    {prop: "assigneeName", label: "执行人", minWidth: 100},
                    {
                        prop: "status", label: "类型", minWidth: 110,
                        formatter: (row, column, cellValue, index) => {
                            return cellValue == 'NORMAL' ? '普通' : cellValue
                        }
                    },
                    {prop: "createTime", label: "创建时间", minWidth: 150},
                    {prop: "", label: "到期时间", minWidth: 100},
                ],
            //    详情页状态
                form:{
                    businessDivision:"",
                    province:"",
                    commerceName:""
                },
                tableData:{
                    rows:[]
                },
                columns2:[
                    { prop: "dateCode", label: "合同编号", width:"120" },
                    { prop: "taskCreateDate", label: "任务生成时间", width:"120" },
                    { prop: "paymentType", label: "购销方式", width:"120",
                    formatter:(row, column, cellValue, index)=>{
                return cellValue?'赊销':'预付款'}
                    },
                    { prop: "focusShipmentsMonth", label: "集中发货分配月", width:"120" },
                    { prop: "isSms", label: "是否SMS", width:"120" ,
                        formatter:(row, column, cellValue, index)=>{
                        return cellValue?'是':'否'}},
                    { prop: "shipmentsAffirmDate", label: "计划发货时间", width:"120" },
                    { prop: "commerceName", label: "商业名称", width:"120" },
                    { prop: "productName", label: "商品名称", width:"120" },
                    { prop: "productNum", label: "药品编号", width:"120" },
                    { prop: "medicineType", label: "药品类型", width:"120" },
                    { prop: "productState", label: "规格型号", width:"120" },
                    { prop: "achange", label: "件装量", width:"120" },
                    { prop: "packageSum", label: "件数", width:"120" },
                    { prop: "commercetype", label: "客户类型", width:"120" },
                    { prop: "marketType", label: "销售类型", width:"120" },
                    { prop: "batchNumber", label: "批号", width:"120" },
                    { prop: "price", label: "单价", width:"120" },
                    { prop: "memo", label: "发货备注", width:"120" },
                    { prop: "goods", label: "发货", children:[
                            {prop: "planShipmentsSum", label: "计划量", width:"120" },
                            {prop: "deliveringAmount", label: "实际量", width:"120" }
                        ]},
                    { prop: "warehouse", label: "仓库", width:"120" },
                    { prop: "stock", label: "当前库存量", width:"120" },
                    { prop: "otc202", label: "收货人信息",  children:[
                            {prop:"contactPersion", label:"联系人", width:"120" },
                            {prop:"phone", label:"联系电话", width:"120" },
                            {prop:"shippingAddress", label:"地址", width:"120" }
                        ] }

                ]



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
                let _this = this;
                setTimeout(()=>{
                    _this.closeapplypage();
                },300)
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
            showParentComp() {
                this.refresh += new Date();
                this.isShowPage = false;
            },
            approval(row) {
                this.isShowPage = true;
                this.pageState = 'focusSendApproval,'+row.procInstId;
                console.log(row);
                this.instId = row.procInstId;
                this.taskId = row.taskId;
                this.row = row;
                this.flowData={
                    taskId:row.taskId
                }
                this.getDetailsApproval(row.procInstId);
            },
            closeapplypage(){
                this.isShowPage = false;
                this.pageState = '';
                this.getTableList();
            },
            getDetailsApproval(approvalID){
                let _this = this;
              this.$api.base.getScm({
                  url:"/scm/scmFhShipmentsDatailMaster/v1/getByApplyId/"+approvalID
              }).then(res=>{
                  if (res) {
                      _this.form = res.value;
                      _this.tableData.rows = res.value.scmFhShipmentsDatailList;
                  }
              })
            },
            //主页面表格获取
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
                                value: "fhzlsp"
                            }
                        ],
                        "sorter": []
                    }
                }).then(res => {
                    self.loading = false;
                    self.listData = res;

                })
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
            width: 33.3%;
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
