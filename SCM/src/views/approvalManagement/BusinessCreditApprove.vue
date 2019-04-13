<template>
<div>
    <transition name="el-fade-in">
    <div v-show="showDetailsPage === '1'">
    <header class="header">
        <h1>商业资信审批</h1>
        <div class="header-back"></div>
    </header>
    
    <!--表格数据-->
    <div class="table-section">
        <div class="header">
            <div class="header-left">
                <i class="el-icon-tickets"></i>&nbsp;数据列表
            </div>
            <div class=header-right></div>
        </div>
        <!--表格栏-->
        <el-table class="test" :data="pageResult.rows" highlight-current-row v-loading="loading" element-loading-text="加载中"
          border stripe fit max-height="500" size="mini" align="left" style="width:100%;">
          <el-table-column label="序号" type="index" width="50"></el-table-column>
          <el-table-column sortable show-overflow-tooltip="true" prop="subject" key="subject" label="任务标题" header-align="center" align="center"></el-table-column>
          <el-table-column sortable show-overflow-tooltip="true" prop="name" key="name" label="任务名称" header-align="center" align="center"></el-table-column>
          <el-table-column sortable show-overflow-tooltip="true" prop="ownerName" key="ownerName" label="所属人" header-align="center" align="center"></el-table-column>
          <el-table-column sortable show-overflow-tooltip="true" prop="assigneeName" key="assigneeName" label="执行人" header-align="center" align="center"></el-table-column>
          <el-table-column sortable show-overflow-tooltip="true" prop="status" key="status" :formatter="restStatus" label="类型" header-align="center" align="center"></el-table-column>
          <el-table-column sortable show-overflow-tooltip="true" prop="createTime" key="createTime" label="创建时间" header-align="center" align="center"></el-table-column>
          <el-table-column sortable show-overflow-tooltip="true" prop="endDate" key="endDate" label="到期时间" header-align="center" align="center"></el-table-column>
          <el-table-column label="操作" width="150" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button icon="el-icon-edit" size="small" type="primary"  @click="showDetailsPageFunc(scope.$index, scope.row)" >审批</el-button>
            </template>
          </el-table-column>
        </el-table>
      
        <div class="pagination">
			<el-pagination 
                @current-change="refreshPageRequest" 
                @size-change="refreshPageSizeRequest"
				background
				:current-page="pageRequest.page" 
                :page-sizes="[10, 20, 30, 40, 50]" 
                :page-size="pageRequest.pageSize" 
                layout="sizes, total, prev, pager, next"
				:total="pageResult.total">
			</el-pagination> 
        </div>
    </div>
    </div>
    </transition>
    <!-- 审批详情 -->
    <transition name="el-fade-in">
      <div class="content-item" v-show="showDetailsPage === '2'">
        <business-credit-approve-details :key="forceRefresh" v-on:closedetailspage="closedetailspage" :passparam="passIdJSON"></business-credit-approve-details>
      </div>
    </transition>

</div>
    
</template>

<script>
import storage from "@/utils/storage";
import BusinessCreditApproveDetails from "./BusinessCreditApproveDetails"; // 引入协议签订组件
export default {
    name: "businessCreditApprove",
    components: {
    "business-credit-approve-details": BusinessCreditApproveDetails
  },
    data () {
        return {
            forceRefresh: 0, // 强制重置子组件
            showDetailsPage: '1',
            passIdJSON: '', // 传给详情页的数据
            ownerId: "",
            pageResult: {
                rows:[]
            },

            loading: false,
            pageRequest: {
                page: 1,
                pageSize: 10,
                showTotal: true
            }   
        };
    },
    mounted() {
      this.ownerId = storage.get("ownerId");
      this.findPage();
    },
    methods: {
      restStatus (value) {
          return value.status === 'NORMAL' ? '普通' : value.status;
      },
      // 审批按钮的事件处理函数
      showDetailsPageFunc (index,item) {
          let self = this;
          let passIdObj = {
              taskId: item.taskId,
              instId: item.procInstId,
              id: item.id
          };
          
          self.passIdJSON = JSON.stringify(passIdObj);
          self.toogleShowDetailsPage ('2');
          console.log(self.passIdJSON);
      },
      refreshPageRequest: function(page = 1) {
        this.pageRequest.page = page;
        this.findPage();
      },
      refreshPageSizeRequest: function(pageSize) {
        this.pageRequest.pageSize = pageSize;
        this.findPage();
      },
      toogleShowDetailsPage (flag) {
        let self = this;
        self.showDetailsPage = '-1';
        setTimeout(function() {
            self.showDetailsPage = flag;
        },500);
      },
      // v-on 绑定不支持驼峰命名
      closedetailspage () {
        let self = this;
        // self.showApplyPage = false;
        self.toogleShowDetailsPage('1');
        self.passIdJSON = '';
        setTimeout(function() {
            self.forceRefresh = + new Date();
        },0);
        self.findPage();
      },
      //列表
      findPage() {
        let self = this;
		self.loading = true;
		self.$api.xsgl.postApproval({
            vueInstance: self,
			url: "/runtime/task/v1/list",
			data: {
				pageBean: self.pageRequest,
				params: {},
				querys: [{
					hasInitValue: false,
					operation: "EQUAL",
					property: "assigneeId", //登录人 id
					relation: "AND",
					value: storage.get('userId')
				},
				{
					hasInitValue: false,
					operation: "EQUAL",
					property: "procDefKey",
					relation: "AND",
					value: "syzxlc"
				}],
			    sorter: []
			}
		}, function(res) {
            self.loading = false;
            self.pageResult = res;
        });
	  },


    }
}
</script>
<style scoped lang="scss">
.header {
  position: relative;
  font-size: 28px;
  padding: 5px;
  background: #f3f3f3;
  display: flex;
  justify-content: center;
  line-height: 1em;
}
.header h1{
  font-size: 28px;
  text-align: center;
}
.header-btn-group{
  position: absolute;
  height: 100%;
  right: 5px;
  top:0;
  display: flex;
  align-items: center;
}
.header-back{
  position: absolute;
  height: 100%;
  left: 5px;
  top:0;
  display: flex;
  align-items: center;
}
.query-section{
    margin-bottom: 20px;
}
.header-left, .header-right{
    width:50%;
    display: flex;
    align-items: center;
    font-size: 16px;
}
.header-right{
    justify-content: flex-end;
}
.el-form {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  .el-form-item {
    text-align: left;
    width: 33%;
    margin:0;
  }
}
.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
</style>

