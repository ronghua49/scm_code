<template>
  <div>
    <!-- 标题 + 步骤条 -->
    <div class="head-item">
      <div class="searchHeader">
        <div class="headleft">
          <router-link to="/fhgl/BusinessCreditManage">
            <el-button size="mini" class="el-icon-back" type="primary">返回</el-button>
          </router-link>
        </div>

        <div class="headcenter">商业资信申请</div>
        <div class="headright">
          <el-button size="mini" type="primary" @click="applyCache">申请暂存</el-button>
          <el-button size="mini" type="primary" @click="applySubmit">发起申请</el-button>
        </div>
      </div>
      <!-- 步骤条 -->
      <el-row>
        <el-steps
          :space="500"
          align-center="true"
          :active="stepsBar.active"
          finish-status="success"
          style="margin: 35px auto;"
        >
          <el-step title="选择商业名单/填写资信额度"></el-step>
          <el-step title="完成"></el-step>
        </el-steps>
      </el-row>
    </div>
    <!-- 列表数据 -->
    <transition name="el-fade-in">
      <div v-show="applySteps.select">
        
        <div class="header">
          <div class="headleft">
            <span class="el-icon-tickets"></span>
            <span class="sx">商业名单</span>
            <span class="el-icon-caret-bottom"></span>
          </div>
          <div class="headright">
            <el-button type="primary" class="el-icon-circle-plus" size="mini" @click="addNewCommerceDialogShow">新增123</el-button>
          </div>
        </div>
        <!--表格栏-->
        <el-table :data="pageResult.rows" highlight-current-row v-loading="loading" element-loading-text="加载中"
          border stripe fit :show-overflow-tooltip="false"
          max-height="500" size="mini" align="left" style="width:100%;">
          <el-table-column prop="year" key="year" label="年份" min-width="100" header-align="center" align="center" ></el-table-column>
          <el-table-column prop="quarter" key="quarter" label="季度" min-width="100"  header-align="center" align="center"></el-table-column>
          <el-table-column prop="businessDivision" key="businessDivision" label="商务分区" header-align="center" align="center" min-width="100"></el-table-column>
          <el-table-column prop="province" key="province" label="省区" header-align="center" align="center" min-width="100"></el-table-column>
          <el-table-column header-align="center" align="center" label="经销商">
            <el-table-column prop="commerceCode" key="commerceCode" label="编码" header-align="center" align="center" min-width="100"></el-table-column>
            <el-table-column prop="commerceName" key="commerceName" label="名称" header-align="center" align="center" min-width="100"></el-table-column>
          </el-table-column>
          <el-table-column prop="marketWay" key="marketWay" label="购销方式" header-align="center" align="center" min-width="120">
                <template slot-scope="scope">
                   <el-select v-model="scope.row.marketWay" placeholder="请选择">
                        <el-option label="预付款" value="1"></el-option>
                        <el-option label="赊销" value="2"></el-option>
                    </el-select>
                </template>
          </el-table-column>
          <el-table-column header-align="center" align="center" label="月均发货额(万)">
            <el-table-column prop="beforeYearPrice" key="beforeYearPrice" label="前年" header-align="center" align="center" min-width="100"></el-table-column>
            <el-table-column prop="lastYearPrice" key="lastYearPrice" label="去年" header-align="center" align="center" min-width="100"></el-table-column>
          </el-table-column>
          <el-table-column header-align="center" align="center" label="资信额(万)">
            <el-table-column prop="lastTimePrice" key="lastTimePrice" label="上次批准" min-width="100" header-align="center" align="center"></el-table-column>
            <el-table-column prop="sysPrice" key="sysPrice" label="系统预测" header-align="center" align="center" min-width="100"></el-table-column>
          </el-table-column>
          <el-table-column header-align="center" align="center" label="申请">
            <el-table-column prop="price" key="price" label="资信额度(万)" header-align="center" align="center" min-width="100">
                <template slot-scope="scope">
                    <el-input v-model="scope.row.price"></el-input>
                </template>
            </el-table-column>
            <el-table-column prop="memo" key="memo" label="申请备注" header-align="center" align="center" min-width="180">
                <template slot-scope="scope">
                    <el-input v-model="scope.row.memo"></el-input>
                </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="操作" width="150" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button icon="el-icon-edit" size="small" type="danger"  @click="handleEdit(scope.$index, scope.row)" >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页-->
        <div class="pagination">
          <el-pagination
            @current-change="refreshPageRequest"
            @size-change="refreshPageSizeRequest"
            background
            :current-page="pageRequest.page"
            :page-sizes="[10, 20, 30, 40, 50]"
            :page-size="pageRequest.pageSize"
            layout="sizes, total, prev, pager, next"
            :total="pageResult.total"
          ></el-pagination>
        </div>
      </div>
    </transition>
    <!-- 点击发起申请 显示完成提示页面 -->
    <transition name="el-fade-in">
      <div class="content-item" v-show="applySteps.complete">
        <div class="success">
          <span class="el-icon-check"></span> 您已完成
          <span style="color: #409EFF;">【{{supplier}}】</span>商业资信申请
        </div>
        <div class="success-bottom">共
          <span style="color: #409EFF;">【{{speciesSum}}】</span>经销商
        </div>
      </div>
    </transition>
    <!-- 带表单的Dialog -->
    <transition name="el-fade-in">
        <el-dialog v-loading="loading" :rules="addNewFormRules" class="add-new-form" title="新增商业" :visible.sync="applySteps.addNew">
            <el-form ref="addNewForm" :model="addNewForm" label-width="80px">
                <el-form-item label="年份">
                    <el-date-picker type="year"  placeholder="请选择年份" v-model="addNewForm.year" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="季度">
                    <el-select v-model="addNewForm.quarter" placeholder="请选择季度">
                        <el-option label="1季度" value="1"></el-option>
                        <el-option label="2季度" value="2"></el-option>
                        <el-option label="3季度" value="3"></el-option>
                        <el-option label="4季度" value="4"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="商业名称">
                    
                    <el-select v-model="addNewForm.commerce" placeholder="请选择商业名称">
                        <el-option
                          v-for="item in commerceNameList"
                          :key="item.id"
                          :label="item.commerceName"
                          :value="item"
                        ></el-option>
                        
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button @click="dialogCancel('addNewForm')">取消</el-button>
                    <el-button type="primary" @click="addNewCommerceSubmit('addNewForm')">确定</el-button> 
                </el-form-item>
            </el-form>
            <!-- <div slot="footer" class="dialog-footer">
               <el-button @click="dialogCancel">取 消</el-button>
               <el-button type="primary" @click="addNewCommerceSubmit">确 定</el-button>
            </div> -->
        </el-dialog>
    </transition>
  </div>
</template>

<script>
// /scm/scmXsCreditCollect/v1/save   暂存

// /scm/scmXsCreditCollect/v1/sendApply  申请
import storage from "@/utils/storage";
export default {
  name: "businessCreditApply",
  beforeRouteEnter(to, from, next) {
    next(vm => {
      // 通过 `vm` 访问组件实例
      if (from.name === "商业资信管理") {
        // 向主页广播事件
        vm.$root.eventHub.$emit("closeBusinessCreditManage", {});
      }
    });
  },
  created() {
    //获取传入的参数
    //var param = this.$route.query;
    // var param = this.$route.params;
    //this.params = param;
    //如果使用query方式传入的参数使用this.$route.query 接收
    //如果使用params方式传入的参数使用this.$router.params接收
    console.log(this.$route);
  },
  mounted() {
    this.ownerId = storage.get("ownerId");
    // 获取商业列表
    this.getcCreditApplicationTypeList();
  },
  data() {
    return {
      addNewFormRules: {
          year: [
            { required: true, message: '请选择年份', trigger: 'blur' }
          ],
          quarter: [
            { required: true, message: '请选择季度', trigger: 'blur' }
          ],
          commerce: [
            { required: true, message: '请选择商业名称', trigger: 'blur' }
          ]
      },
      // columns: [],
      ownerId: '',
      // 步骤条
      stepsBar: {
        active: 0
      },
      // 申请步骤
      applySteps: {
        addNew: false,
        select: true,
        complete: false
      },
      pageResult: {
          rows: []
      },
      loading: false,
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      }, 
      // Dialog表单
      addNewForm: {
          year: null, // 日历控件返回的是一个Date对象
          quarter: '',
          commerce: ''
      },
      // 商业名称列表
      commerceNameList: []
    };
  },
  methods: {
    refreshPageRequest: function(page = 1) {
      this.pageRequest.page = page;
      this.findPage();
    },
    refreshPageSizeRequest: function(pageSize) {
      this.tableData3.pageSize = pageSize;
      this.findPage();
    },
    // 发起申请 /scm/scmXsCreditCollect/v1/sendApply
    applySubmit() {
        console.log(this.pageResult);
        return;
    //   this.applySteps.select = false;
    //   this.applySteps.complete = true;
    //   this.stepsBar.active = 2;
        let self = this;
        this.$api.fhgl
        .saveFhgl({
          url: "/scm/scmXsCreditCollect/v1/sendApply",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys: [].concat(search),
            sorter: []
          }
        })
        .then(res => {
          console.log(res);
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
        });

    },
    // 申请暂存
    applyCache() {
      this.applySteps.select = false;
      this.applySteps.complete = true;
      this.stepsBar.active = 2;
    },
    // 获取商业列表的函数 /scm/scmZsjCommerce/v1/downBoxApplySuccess/{ownerId}
    getcCreditApplicationTypeList () {
        // this.loading = true;
        let self = this;
        self.$api.fhgl
        .get({
            url: "/scm/scmZsjCommerce/v1/downBoxApplySuccess/" + self.ownerId,
            data: {}
        }).then(res => {
            console.log(res);
            if(res.length) {
               self.commerceNameList = res;
            }
        });
        
    },
    // 显示Dialog表单
    addNewCommerceDialogShow () {
        this.applySteps.addNew = true;
    },

    // 提交表单数据，关闭dialog /scm/scmXsCreditApply/v1/getStatisticalDate/{commerceId}/{ownerId}
    getStatisticalDate (data, resolveFunc) {
        let self = this;
        self.$api.fhgl
        .get({
            url: "/scm/scmXsCreditApply/v1/getStatisticalDate/" + data.commerceId +"/" + data.ownerId,
            data: {}
        }).then(res => {
            console.log(res);
            resolveFunc(res);
        }).catch(error => {
            self.loading = false;
            self.$message({
              message: '系统错误',
              type: "error",
              duration: 800
            });
        });
    },
    addNewCommerceSubmit (formName) {
        let self = this;
        let item = {
            "beforeYearPrice": 0,//前年月均销售额(万),
            "businessDivision": '',//"商务分区",
            "businessDivisionId": '',//"商务分区ID",
            "commerceCode": '',//"商业编码",
            "commerceName": '',//"商业名称",
            "lastTimePrice": 0,//上次批准资信额(万),
            "lastYearPrice": 0,//去年月均销售额(万),
            "province": '',//"省区",
            "provinceId": '',//"省区ID",
            "sysPrice": 0,//系统预测资信额(万),
            "year": '',//"年度"
            "quarter": '',//"季度",
            "marketWay": '',//"string",
            "memo": '',//备注,
            "price": 0,//资信额(万),
        };
        self.$refs[formName].validate((valid) => {
          if (valid) { // 验证通过
              self.loading = true;
              self.getStatisticalDate({
                  commerceId: self.addNewForm.commerce.id,
                  ownerId: self.ownerId
              }, function(res) {
                  self.loading = false;
                  item.year = self.addNewForm.year.getFullYear();
                  item.quarter = self.addNewForm.quarter;
                  item.businessDivision = self.addNewForm.commerce.businessDivision;
                  item.businessDivisionId = self.addNewForm.commerce.businessDivisionId;
                  item.commerceCode = self.addNewForm.commerce.commerceCode;
                  item.commerceName = self.addNewForm.commerce.commerceName;
                  item.province = self.addNewForm.commerce.province;
                  item.provinceId = self.addNewForm.commerce.provinceId;
                  
                  item.beforeYearPrice = res.beforeYearPrice;
                  item.lastTimePrice = res.lastTimePrice;
                  item.lastYearPrice = res.lastYearPrice;
                  item.sysPrice = res.sysPrice;

                  self.pageResult.rows.push(item);
                  self.applySteps.addNew = false;

              });
            
          } else {
            console.log('error submit!!');
            return false;
          }
        }); 
    },
    // 关闭dialog，清空表单
    dialogCancel () {
        this.applySteps.addNew = false;
        this.addNewForm.year = '';
        this.addNewForm.quarter = '';
        this.addNewForm.commerceName = '';
    }
  }
  
};
</script>
<style scoped="scoped" lang="scss">
.header {
  width: 100%;
  height: 38px;
  font-size: 16px;
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

.tanFoot {
  margin-top: 15px;
  display: flex;
}

.savebox {
  margin-left: 20px;
}

// .el-form {
//   display: flex;
//   flex-direction: row;
//   flex-wrap: wrap;
//   .el-form-item {
//     width: 33%;
//   }
// }

.el-table .cell {
  white-space: nowrap !important;
}

header {
  height: 40px !important;
}

// .el-form-item {
//   margin-top: 10px;
// }

.searchHeader {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 28px;
  font-weight: bold;
  padding-bottom: 7px;
  background: #f3f3f3;
}

.head-item {
  border: 1px solid #f2f2f2;
}

.content-item {
  border: 1px solid #f2f2f2;
  margin-top: 8px;
}

.main-content {
  padding: 0 !important;
}

.success {
  width: 350px;
  height: 50px;
  border: 1px solid #f2f2f2;
  margin: 0 auto;
  margin-top: 150px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.success-bottom {
  margin-top: 50px;
  margin-bottom: 250px;
}

.el-dialog .el-dialog__body {
  border-top: 1px solid #f2f2f2 !important;
  border-bottom: 1px solid #f2f2f2 !important;
  padding: 0 !important;
}

</style>
