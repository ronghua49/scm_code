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
            <el-col :span="4" :offset="8"><b>采购订单审批</b></el-col>
            <el-col :span="4" :offset="7" style="padding-left:10px">
              <approval-btn :taskId="taskId" @handleSuccess="approvalSuccess"></approval-btn>
            </el-col>
          </el-row>
          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>基础信息</el-col>
          </el-row>
          <el-row class="searchBox" style="padding-top:10px;">
          <el-form :model="wrapForm" ref="wrapForm1" :label-position="labelPosition" size="small" label-width="120px" class="demo-ruleForm">
					<el-form-item prop="supplier" label="供应商名称">
						<el-input v-model="wrapForm.supplier" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item prop="contractCode" label="合同编号">
						<el-input v-model="wrapForm.contractCode" :disabled="true"></el-input>
					</el-form-item>
                    <el-form-item prop="deliveryDate" label="交货日期">
						<el-date-picker v-model="wrapForm.deliveryDate" type="date" clearable :disabled="true" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" placeholder="选择日期">
						</el-date-picker>
					</el-form-item>
					<el-form-item prop="warehouse" label="收货仓库">
						<el-input v-model="wrapForm.warehouse" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item prop="getTicket" label="收票方">
						<el-input v-model="wrapForm.getTicket" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item prop="orderDate" label="下单日期">
						<el-date-picker v-model="wrapForm.orderDate" type="date" clearable :disabled="true" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" placeholder="选择日期">
						</el-date-picker>
					</el-form-item>
					<!-- <el-form-item prop="sumPrice" label="补单编号">
						<el-input v-model="wrapForm.scmCgProReplacementOrderList.replacementOrderCode" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item prop="orderDate" label="补单日期">
						<el-date-picker v-model="wrapForm.scmCgProReplacementOrderList.orderDate" type="date" clearable :disabled="true" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" placeholder="选择日期">
						</el-date-picker>
					</el-form-item> -->
				</el-form>    
          </el-row>

          <el-row class="searchHeader"  style="margin-top:10px;">
            <el-col :span="3" style="text-align:left;"><i class="el-icon-edit-outline"></i>数据列表</el-col>
          </el-row>
          <el-row class="searchBox" style="padding-top: 0;">
            <el-table v-loading="loading" element-loading-text="加载中" :data="tableData" size="mini" border style="width: 100%">
                <el-table-column type="index" label="序号" align="center"></el-table-column>
                <el-table-column prop="achange" label="件装量" align="center"></el-table-column>
                <el-table-column prop="count" label="盒数" align="center" minWidth="150">
                  <template slot-scope="scope">
                      <span >{{tableData[scope.$index].productSum}}</span>
                  </template>
                </el-table-column>
                <el-table-column v-for="item in column1" :key="item.prop" :prop="item.prop" :label="item.label" :formatter="item.formatter" :align="item.align">
                </el-table-column>
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
      isIndex: true,
      loading: false,
      baseEdit: false,
      labelPosition: "right",
      taskId: '',//任务id
      instId:'',
      tableData:[],
      flowData: {},//流程图数据
      // promiseSelect:'',
     
    };
  },
  mounted() {
    this.findPage();
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
                    value: "cgdd"
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
        this.$api.zsj
        .changeState({
          url: "/scm/scmCgProcurementOrder/v1/getOrderFirstByApprovalId/" + id
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
          this.wrapForm = Object.assign({}, res.value);
          this.tableData = res.value.scmCgProOrderProductList;
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
    }
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
