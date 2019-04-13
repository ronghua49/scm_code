<template>
    <!-- <div>{{msg}}</div> -->
    <div>
      <transition name="el-fade-in">
      
      <!-- 表格和搜索表格 -->
      <div v-show="isIndex">
       <!--表格栏-->
       <el-row class="searchHeader" >
            <el-col :span="3" style="text-align:left;"><i class="el-icon-tickets"></i>数据列表</el-col>
          </el-row>
        <el-table :data="pageResult.rows" highlight-current-row  
              v-loading="loading"   element-loading-text="加载中" border stripe
              :show-overflow-tooltip="true" max-height="500" size="mini" align="left" style="width:100%;" >
          <!-- <el-table-column type="selection" width="40"></el-table-column> -->
          <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
          <el-table-column v-for="column in columns" header-align="center" align="center"
            :prop="column.prop" :label="column.label" 
            :key="column.prop"  :min-width="column.minWidth" :formatter="column.formatter"
            :sortable="column.sortable==null?true:column.sortable">
          </el-table-column>
          <el-table-column label="操作" width="100" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button icon="el-icon-edit" label=""  @click="approval(scope.$index, scope.row)" :size="size" type="primary">审批</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页栏-->
        <div class="toolbar" style="padding:10px;">
          <el-pagination  @current-change="refreshPageRequest" @size-change="refreshPageSizeRequest" background
            :current-page="pageRequest.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest.pageSize" layout="sizes, total, prev, pager, next" :total="pageResult.total" style="float:right;">
          </el-pagination>
        </div>
      </div>











      <!-- 新增首营 -->
    </transition>
    <transition name="el-fade-in">

      <div v-show="!isIndex">
          <el-row class="searchHeader" >
            <el-col :span="1">
              <el-button icon="el-icon-back"  :size="size" type="primary" @click="isIndex=!isIndex">返回</el-button>
            </el-col>
            <el-col :span="5" :offset="8"><b>经销商协议合作条款审批</b></el-col>
            <el-col :span="4" :offset="6" style="text-align:right;padding-left:10px">
              <approval-btn :taskId="taskId" @handleSuccess="approvalSuccess"></approval-btn>
            </el-col>
          </el-row>


          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>
            <!-- <span style="color:#409eff">【{{wrapForm.businessDivistionName}}】</span>
            <span style="color:#409eff">【{{wrapForm.provinceName != ''? wrapForm.provinceName : '-'}}】</span> -->
            经销商协折扣条款</el-col>
          </el-row>
          <el-row class="searchBox" style="padding-top: 0;">
            <el-table v-loading="loading" element-loading-text="加载中" :data="secondTableData" size="mini" border style="width: 100%">
                <el-table-column type="index" label="序号" align="center"></el-table-column>
                <el-table-column prop="commerceName" label="名称" align="center" :show-overflow-tooltip="true"></el-table-column>
                <el-table-column prop="promiseProvince" label="承诺省份" align="center"></el-table-column>
                <el-table-column label="商业" align="center">
                  <el-table-column prop="functionType" label="职能类型" align="center"></el-table-column>
                  <el-table-column 
                    prop="agreementType" 
                    label="选择分类" 
                    align="center"
                    :formatter="function(row, column, cellValue, index){
                        if(cellValue == 0){
                          return '经销商'
                        }else if (cellValue == 1){
                          return '两票制经销商'
                        }else if (cellValue == 2){
                          return '终止认可'
                        }else if (cellValue == 3){
                          return '转分销商'
                        }
                  }"
                  ></el-table-column>
                </el-table-column>
                <el-table-column label="协议生效日期" align="center">
                  <el-table-column prop="AgreementStartDate" label="起始" align="center" min-width="150px">
                  </el-table-column>
                  <el-table-column prop="AgreementChangeDate" label="变更" align="center" min-width="150px">
                  </el-table-column>
                  <el-table-column prop="AgreementEndDate" label="截止" align="center" min-width="150px">
                  </el-table-column>
                </el-table-column>

                <el-table-column prop="unloadPayer" label="卸载费支付方" align="center" min-width="120px">
                    <template slot-scope="scope">
                    <el-select :disabled="true" v-model="scope.row.unloadPayer" :size="size" >
                        <el-option value="0" label="甲方"></el-option>
                        <el-option value="1" label="乙方"></el-option>
                    </el-select>
                    </template>
                </el-table-column>

                <el-table-column label="分子公司" align="center">
                    <el-table-column 
                    prop="scmXsAgreementSubsidiaryList" 
                    label="商业" 
                    align="center"
                    :show-overflow-tooltip="true"
                    :formatter="function(row, column, cellValue, index) {
                    let strList = []
                    cellValue.forEach((item) => {
                        strList.push(item.subsidiaryName)
                    })
                    return strList.join(' / ')
                    }"
                    ></el-table-column>
                    <el-table-column 
                    prop="scmXsAgreementSubsidiaryList" 
                    label="家数" 
                    align="center"
                    :formatter="function(row, column, cellValue, index){
                    return cellValue.length
                    }"
                    ></el-table-column>
                </el-table-column>
                <el-table-column prop="isSpecial" label="是否特殊" align="center">
                    <template slot-scope="scope">
                      <span v-if="scope.row.isSpecial == 0" class="el-icon-close"></span>
                      <span v-else class="el-icon-check"></span>
                    </template>
                </el-table-column>
                <el-table-column 
                    prop="scmXsAgreementSpecialProductList" 
                    label="商品" 
                    align="center" 
                    min-width="150px"
                    :show-overflow-tooltip="true"
                    :formatter="function(row, column, cellValue, index) {
                    let strList = []
                    cellValue.forEach((item) => {
                        strList.push(item.productName)
                    })
                    return strList.join(' / ')
                    }"
                    >
                    </el-table-column>
                <el-table-column label="付款方式" align="center">
                  <el-table-column 
                    prop="lastPaymentType" 
                    label="上次" 
                    align="center"
                    :formatter="function(row, column, cellValue, index){
                      return cellValue == '1' ? '赊销' : '预付款'
                    }"
                  ></el-table-column>
                    <el-table-column 
                    prop="paymentType" 
                    label="本次" 
                    align="center"
                    :formatter="function(row, column, cellValue, index){
                      return cellValue == '1' ? '赊销' : '预付款'
                    }"
                  ></el-table-column>
                </el-table-column>
                <el-table-column label="本商业" align="center">
                  <!-- <el-table-column prop="achange" label="设定区间" align="center" min-width="150px">
                    <template slot-scope="scope">
                      <span v-show="secondTableData.rows[scope.$index].paymentType == '0'">预付款</span>
                      <el-select @change="changeDays(scope.$index)"  v-show="secondTableData.rows[scope.$index].paymentType == '1'" :size="size"  placeholder="请选择">
                        <el-option value="0" label="1-30"></el-option>
                        <el-option value="1" label="31-60"></el-option>
                      </el-select>
                    </template>
                  </el-table-column> -->
                  <el-table-column prop="stardDay" label="起始天数" align="center">
                  </el-table-column>
                  <el-table-column prop="endDay" label="截止天数" align="center">
                  </el-table-column>
                </el-table-column>
                <el-table-column label="电汇付款" align="center">
                  <el-table-column prop="discount" label="标准折扣" align="center"></el-table-column>
                  <el-table-column prop="lastTheApplication" label="上次申请" align="center"></el-table-column>
                  <el-table-column prop="theApplication" label="本次申请" align="center" min-width="150px">
                  </el-table-column>
                </el-table-column>
                 <el-table-column prop="isOverfulfil" label="是否可以超额发货" align="center">
                    <template slot-scope="scope">
                      <span v-if="scope.row.isOverfulfil == 0" class="el-icon-close"></span>
                      <span v-else class="el-icon-check"></span>
                    </template>
                </el-table-column>
                <el-table-column label="回款天数" align="center">
                  <el-table-column prop="lastReturnMoneyDay" label="上次" align="center"></el-table-column>
                  <el-table-column prop="returnMoneyDay" label="本次" align="center"></el-table-column>
                  <el-table-column prop="quarterPaymentPercent" label="季度预付款占比（%）" align="center" min-width="150"></el-table-column>
                </el-table-column>
                <el-table-column prop="memo" label="备注" align="center" min-width="150px"></el-table-column>
            </el-table>
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>
            <!-- <span style="color:#409eff">【{{wrapForm.businessDivistionName}}】</span>
            <span style="color:#409eff">【{{wrapForm.provinceName != ''? wrapForm.provinceName : '-'}}】</span> -->
            协议品规明细</el-col>
          </el-row>
          <el-row class="searchBox" style="padding-top: 0;">
            <el-table v-loading="loading" element-loading-text="加载中" :data="tableDetailData" size="mini" border style="width: 100%">
                <el-table-column type="index" label="序号" align="center"></el-table-column>
                <el-table-column prop="commerceGauge" label="药品品规" align="center"></el-table-column>
                <el-table-column  label="是否签约" align="center">
                  <el-table-column prop="lastIsSign" label="上年度" align="center">
                    <template slot-scope="scope">
                      <span v-if="scope.row.lastIsSign == 0" class="el-icon-close"></span>
                      <span v-else class="el-icon-check"></span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="isSign" label="本年度" align="center">
                    <template slot-scope="scope">
                      <span v-if="scope.row.isSign == 0" class="el-icon-close"></span>
                      <span v-else class="el-icon-check"></span>
                    </template>
                  </el-table-column>
                </el-table-column>
                <el-table-column prop="effectiveDate" label="生效日期" align="center"></el-table-column>
                <el-table-column prop="delivery" :label="labelYear" align="center"></el-table-column>
                <el-table-column 
                prop="gaugeType" 
                label="品规类型" 
                align="center"
                :formatter="function(row, column, cellValue, index) {1
                  if(cellValue == 0){
                    return '非OTC'
                  }else if(cellValue == 1){
                    return '甲类'
                  }else {
                    return '乙类'
                  }
                }"
                ></el-table-column>
                <el-table-column  label="本省中标情况" align="center">
                  <el-table-column prop="isBidding" label="是否中标" align="center">
                    <template slot-scope="scope">
                      <span v-if="scope.row.isBidding == 0" class="el-icon-close"></span>
                      <span v-else class="el-icon-check"></span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="isImplement" label="是否实施" align="center">
                    <template slot-scope="scope">
                      <span v-if="scope.row.isImplement == 0" class="el-icon-close"></span>
                      <span v-else class="el-icon-check"></span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="biddingPrice" label="实际中标价格" align="center"></el-table-column>
                </el-table-column>
                <el-table-column prop="achange" label="商业供货单价" align="center">
                  <el-table-column prop="lastSupplyPrice" label="上次" align="center"></el-table-column>
                  <el-table-column prop="supplyPrice" label="本次" align="center"></el-table-column>
                </el-table-column>
                <el-table-column prop="achange" label="毛利分析（%）" align="center">
                  <el-table-column prop="lastAllotGross" label="上次调拨毛利" align="center"></el-table-column>
                  <el-table-column prop="allotGross" label="本次调拨毛利" align="center" min-width="120px"> </el-table-column>
                  <el-table-column prop="lastAcceptGross" label="上次承兑毛利" align="center"></el-table-column>
                  <el-table-column prop="acceptGross" label="本次承兑毛利" align="center" min-width="120px"></el-table-column>
                  <el-table-column prop="lastPurePinGross" label="上次纯销毛利" align="center"></el-table-column>
                  <el-table-column prop="purePinGross" label="本次纯销毛利" align="center" min-width="120px"></el-table-column>
                </el-table-column>
                <el-table-column  label="本次申请商业价格" align="center">
                  <el-table-column prop="wireTransferPrice" label="电汇调拨供货价格" align="center" min-width="120px"></el-table-column>
                  <el-table-column prop="acceptPrice" label="承兑调拨供货价" align="center" min-width="120px"></el-table-column>
                  <el-table-column prop="purePinPrice" label="纯销供货价" align="center" min-width="120px"></el-table-column>
                </el-table-column>
                <el-table-column prop="memo" label="申请备注" align="center" min-width="150px"></el-table-column>
            </el-table>
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>流程图</el-col>
          </el-row>
          <el-row class="searchBox">
              <approval-flow :flowData="flowData"></approval-flow>
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>审批历史</el-col>
          </el-row>
          <el-row class="searchBox" style="padding-top: 0">
              <approval-history :instId="instId"></approval-history>
          </el-row>






      </div>
      </transition>
    </div>
</template>

<script>
import approvalBtn  from "@/components/approval/ApprovalBtn"
import approvalFlow  from "@/components/approval/ApprovalFlow"
import approvalHistory  from "@/components/approval/ApprovalHistory"
import storage from '@/utils/storage'

let timer;
export default {
  name: "cgproorderproductapple",
  components: {
    'approval-btn':approvalBtn,
    'approval-flow':approvalFlow,
    'approval-history':approvalHistory,
  },
  data() {
    return {
      size: "small",
      columns: [
        { prop: "subject", label: "任务标题",minWidth: 150 },
        { prop: "name", label: "任务名称" },
        { prop: "ownerName", label: "所属人" ,minWidth: 150},
        { prop: "assigneeName", label: "执行人",minWidth: 100},
        { prop: "status", label: "类型",minWidth: 110, formatter:(row, column, cellValue, index)=>{return cellValue=='NORMAL'?'普通':cellValue}},
        { prop: "createTime", label: "创建时间",minWidth: 100 },
        { prop: "", label: "到期时间",minWidth: 100 },
      ],
      column1: [
        {
          prop: "surplusAmount",
          label: "合同剩余数",
          minWidth: "100",
          align: "center"
        },
        {
          prop: "productName",
          label: "产品名称",
          minWidth: "100",
          align: "center"
        },
        {
          prop: "productCode",
          label: "产品编码",
          minWidth: "100",
          align: "center"
        },
        {
          prop: "productCname",
          label: "产品通用名",
          minWidth: "100",
          align: "center"
        },
        {
          prop: "productGauge",
          label: "品规",
          minWidth: "100",
          align: "center"
        },
        {
          prop: "medicineType",
          label: "药品类型",
          minWidth: "100",
          align: "center"
        },
        { prop: "unit", label: "单位", minWidth: "100", align: "center" },
        { prop: "unitPrice", label: "单价", minWidth: "100", align: "center" },
        { prop: "sumPrice", label: "金额", minWidth: "100", align: "center" },
        { prop: "currency", label: "币种", minWidth: "100", align: "center" }
      ],
      // 新增编辑界面数据
      wrapForm: {},
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      pageResult: {},
      size: "mini",
      labelYear:'',
      isIndex: true,
      loading: false,
      baseEdit: false,
      labelPosition: "right",
      taskId: '',//任务id
      instId:'',
      secondTableData:[],
      tableDetailData:[],
      flowData: {},//流程图数据
      // promiseSelect:'',
     
    };
  },
  mounted() {
    this.findPage();
    this.getLabelYear();
  },
  methods: {
    findPage() {
      this.loading = true;
      this.$api.approval
        .post({
          url: "/runtime/task/v1/list",
          data: {
            pageBean: this.pageRequest,
            params: {},
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
                    value: "jxsxyhztksq"
                }
            ],
            sorter: []
          }
        })
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.pageResult = res;
          this.loading = false
        })
    },
    refreshPageRequest: function(page = 1) {
      this.pageRequest.page = page;
      this.findPage();
    },
    // size刷新
    refreshPageSizeRequest: function(pageSize) {
      this.pageRequest.pageSize = pageSize;
      this.findPage();
    },
    getData(id) {
        this.$api.base
        .getScm({
          url: "/scm/scmXsAgreementSummary/v1/getDealerClauseAndProductDetail/" + id
        })
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: "操作失败, " + res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.secondTableData = res.scmXsDealerClauseList;
          this.tableDetailData = res.scmXsAgreementProductDetailList;
        });
    },
    approval(index, row){
      this.isIndex = false;
      this.taskId = row.id;
      this.instId = row.procInstId;
      this.getData(row.procInstId);
      this.flowData = {
        taskId: row.taskId
      }
    },
    approvalSuccess(){
      this.findPage();
      this.isIndex = true;
    },
    getLabelYear() {
      var year = new Date().getFullYear();
      var str = `${year-2}11-${year-1}10发货额(万)`;
      this.labelYear = str;
    },
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.btnAdd {
  padding-bottom: 5px;
}
.paginer {
  margin-top: 5px;
}
.el-select {
  width: 100%;
}
.el-form{
  display: flex;
  flex-direction: row;
  // justify-content: space-around;
  flex-wrap: wrap;
  .el-form-item{
    width: 32%;
  }
}
.searchHeader{
  padding: 5px;
  background: #f3f3f3;
  div:first-child{
    height: 28px;
    line-height: 28px;
  }
  b{
    font-size: 24px;
  }
  i{
    margin-right: 5px;
    margin-left: 5px;
  }
}
.searchBox{
  padding-top: 10px;
  border: 1px solid #f4f4f4;
  margin-bottom: 10px;
}
.addContent{
  border: 1px solid #f4f4f4;
  border-radius: 3px;
  margin-bottom: 10px;
  .addHead{
    text-align: center;
    width: 100%;
    height: 50px;
    line-height: 50px;
    font-weight: 600;
  }
}

</style>
