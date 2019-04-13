<template>
    <!-- <div>{{msg}}</div> -->
    <div>
      <div>
          <el-row class="searchHeader" >
            <!-- <el-col :span="1">
              <el-button icon="el-icon-back"  :size="size" type="primary">返回</el-button>
            </el-col> -->
            <el-col><b>经销商协议合作条款详情</b></el-col>
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

                <el-table-column 
                prop="unloadPayer" 
                label="卸载费支付方" 
                align="center" 
                min-width="120px"
                :formatter="function(row, column, cellValue, index){
                    if(cellValue == 0){
                      return '甲方'
                    }else if(cellValue == 1){
                      return '乙方'
                    }else{
                      return ''
                    }
                }">
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
                <el-table-column prop="commerceGauge" label="药品品规" align="center" :show-overflow-tooltip="true"></el-table-column>
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
                <el-table-column 
                prop="effectiveDate" 
                label="生效日期" 
                align="center"
                min-width="120"
                :formatter="function(row, column, cellValue, index){
                  return cellValue.slice(0, 10)
                }"
                ></el-table-column>
                <el-table-column prop="delivery" :label="labelYear" align="center"></el-table-column>
                <el-table-column 
                prop="gaugeType" 
                label="品规类型" 
                align="center"
                :formatter="function(row, column, cellValue, index) {
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
      </div>
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
    this.getData(this.$route.query.id);
    this.getLabelYear();
  },
  methods: {
    getData(id) {
        this.$api.base
        .getScm({
          url: "/scm/scmXsAgreementSummary/v1/get/" + id
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
