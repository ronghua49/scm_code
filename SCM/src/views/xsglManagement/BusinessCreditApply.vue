<template>
  <div>
    <!-- 标题 + 步骤条 showParentComp-->
    <div class="head-item">
      <div class="searchHeader">
        <div class="searchheadleft">
          <el-button @click="showParentComp" size="mini" class="el-icon-back" type="primary">返回</el-button>
          <!-- <router-link to="/xsgl/BusinessCreditManage">
            <el-button size="mini" class="el-icon-back" type="primary">返回</el-button>
          </router-link> -->
        </div>

        <div class="searchheadcenter">{{componentParams.title}}</div>
        <div v-if="componentParams.flag!=='details'" class="searchheadright">
          <el-button :disabled="!applySteps.select" size="mini" type="primary" @click="applyCache">申请暂存</el-button>
          <el-button :disabled="!applySteps.select" size="mini" type="primary" @click="applySubmit">发起申请</el-button>
        </div>
      </div>
      <!-- 步骤条 -->
      <template v-if="componentParams.flag!=='details'">
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
      </template>
    </div>
    <!-- 列表数据 -->
    <transition name="el-fade-in">
      <div v-loading="loading" v-if="applySteps.select">
        
        <div class="header">
          <div class="headleft">
            <span class="el-icon-tickets"></span>
            <span class="sx">商业名单</span>
          </div>
          <div v-if="componentParams.flag!=='details'" class="headright">
            <el-button type="primary" class="el-icon-circle-plus" size="mini" @click="addNewCommerceDialogShow">新增商业</el-button>
          </div>
        </div>
        <!--表格栏-->
        <el-table :data="pageResult.rows" highlight-current-row element-loading-text="加载中"
          border stripe fit tooltip-effect="dark"
          max-height="500" size="mini" align="left" style="width:100%;overflow:auto;">

          <el-table-column prop="year" key="year" label="年份" min-width="100" header-align="center" align="center" ></el-table-column>
          <el-table-column prop="quarter" key="quarter" label="季度" min-width="100"  header-align="center" align="center"></el-table-column>
          <el-table-column prop="businessDivision" key="businessDivision" label="商务分区" header-align="center" align="center" min-width="100"></el-table-column>
          <el-table-column prop="province" key="province" label="省区" header-align="center" align="center" min-width="100"></el-table-column>
          <el-table-column header-align="center" align="center" label="经销商">
            <el-table-column prop="commerceCode" key="commerceCode" label="编码" header-align="center" align="center" min-width="100"></el-table-column>
            <el-table-column show-overflow-tooltip="ture" prop="commerceName" key="commerceName" label="名称" header-align="center" align="center" min-width="120"></el-table-column>
          </el-table-column>
          <el-table-column prop="marketWay" key="marketWay" label="购销方式" header-align="center" align="center" min-width="120">
                <template slot-scope="scope">
                   <el-select :disabled="componentParams.detailsdisabled" v-model="scope.row.marketWay" @change="changePriceInput(scope.row.marketWay,scope.$index)" placeholder="请选择">
                        <el-option label="预付款" value="0"></el-option>
                        <el-option label="赊销" value="1"></el-option>
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
                    <el-input :disabled="scope.row.disabledpriceinput" v-model="scope.row.price" @input.native="resetMoney"></el-input>
                </template>
            </el-table-column>
            <el-table-column prop="memo" key="memo" label="申请备注" header-align="center" align="center" min-width="180">
                <template slot-scope="scope">
                    <el-input :disabled="componentParams.detailsdisabled" v-model="scope.row.memo"></el-input>
                </template>
            </el-table-column>
          </el-table-column>
          <el-table-column v-if="componentParams.flag!=='details'" label="操作" width="150" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button icon="el-icon-edit" size="small" type="danger"  @click="deleteRow(scope.$index, scope.row)" >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页-->
        <!-- <div class="pagination">
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
        </div> -->
      </div>
    </transition>
    <!-- 点击发起申请 显示完成提示页面 -->
    <transition>
      <div class="content-item" v-if="applySteps.complete">
        <div class="success">
          <span class="el-icon-check"></span> 您已完成商业资信
          <span style="color: #409EFF;">【{{applySteps.completeTitle}}】</span>
        </div>
        <div class="success-bottom">共
          <span style="color: #409EFF;">【{{pageResult.rows.length}}】</span>家经销商
        </div>
      </div>
    </transition>
    <!-- 带表单的Dialog -->
    <transition name="el-fade-in">
        <el-dialog v-loading="loading" class="add-new-form" title="新增商业" :visible.sync="applySteps.addNew">
            <el-dialog width="30%" title="提示" :visible.sync="innerVisible" append-to-body>
                <p>请勿重复添加同一家商业！</p>
                <div slot="footer" class="dialog-footer">
                    
                    <el-button type="primary" @click="innerVisible = false">确定</el-button>
                </div>
            </el-dialog>
            <el-form ref="addNewForm" :model="addNewForm" :rules="addNewFormRules" label-width="80px">
                <el-form-item label="年份" prop="year">
                    <el-date-picker type="year" placeholder="请选择年份" v-model="addNewForm.year" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="季度" prop="quarter">
                    <el-select v-model="addNewForm.quarter" placeholder="请选择季度">
                        <el-option label="1季度" value="1"></el-option>
                        <el-option label="2季度" value="2"></el-option>
                        <el-option label="3季度" value="3"></el-option>
                        <el-option label="4季度" value="4"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="商业名称" prop="commerceId">
                    
                    <el-select v-model="addNewForm.commerceId" placeholder="请选择商业名称">
                        <el-option
                          v-for="item in commerceNameList"
                          :key="item.commerceCode"
                          :label="item.commerceName"
                          :value="item.commerceCode"
                        ></el-option>
                        
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button @click="dialogCancel('addNewForm')">取消</el-button>
                    <el-button type="primary" @click="addNewCommerceSubmit('addNewForm')">确定</el-button> 
                </el-form-item>
            </el-form>
        </el-dialog>
    </transition>
  </div>
</template>

<script>
// /scm/scmXsCreditCollect/v1/save   申请暂存

// /scm/scmXsCreditCollect/v1/sendApply  发起申请
import storage from "@/utils/storage";
export default {
  name: "businessCreditApply",
  props:['passparam'],
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
    // console.log(this.$route);
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
          commerceId: [
            { required: true, message: '请选择商业名称', trigger: 'blur' }
          ]
      },
      disabledpriceinput: false, // 是否禁用资信额度输入框
      // columns: [],
      componentParams: {
          flag: '',// 是编辑还是查看详情还是申请 与id是配对的
          id: '',
          title: '商业资信申请',
          detailsdisabled: false
      },
      
      ownerId: '',
      // 步骤条
      stepsBar: {
        active: 0
      },
      innerVisible: false,
      // 申请步骤
      applySteps: {
        addNew: false,
        select: true,
        complete: false,
        completeTitle: ''
      },
      pageResult: {
          approvalState: '',
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
          commerceId: ''
      },
      // 商业名称列表
      commerceNameList: []
    };
  },
  watch: {
    passparam: 'getId'
  },
  methods: {
    resetMoney (e) { // 小数点后两位，不能小于0
        let self = this;
        let value = e.target.value;
        e.target.value = (value.match(/^\d*(\.?\d{0,2})/g)[0]) || null;
    },
    getId () {
        let self = this;
        console.log(self.passparam + ' 888888888888');
        let middleArr = self.passparam.split(',');
        self.componentParams.flag = middleArr[0];
        self.componentParams.id = middleArr[1];
        console.log(self.componentParams.id + self.componentParams.flag);
        if(self.componentParams.flag === 'details') {
            self.componentParams.title = '商业资信详情';
            self.componentParams.detailsdisabled = true;
            self.disabledpriceinput = true;

        }
        if(self.componentParams.id && self.componentParams.flag !== 'addNew') {
            console.log('编辑/查看详情');
            // /scm/scmXsCreditCollect/v1/get/{id}商业资信申请总表数据详情 商业资信申请表数据详情
            self.$api.xsgl
            .get({
                vueInstance: self,
                url: "/scm/scmXsCreditCollect/v1/get/" + self.componentParams.id,
                data: {}
            },function(res) {
                console.log(res);
                // self.pageResult = res;
                res.scmXsCreditApplyList.forEach(element => {
                    if(self.componentParams.flag === 'details') {
                        element.disabledpriceinput = true;
                    } else {
                        if(element.marketWay === "0") {
                            element.disabledpriceinput = true;
                        } else if(element.marketWay === "1") {
                            element.disabledpriceinput = false;
                        }
                    }
                    
                });

                if(res.approvalState) {
                  self.pageResult.approvalState = res.approvalState;
                }
                self.pageResult.rows = res.scmXsCreditApplyList;
            });

        }
    },
    changePriceInput (value,index) {
        let self = this;
        if(value === "0") {
            self.pageResult.rows[index].disabledpriceinput = true;
            self.pageResult.rows[index].price = 0;
        } else {
            self.pageResult.rows[index].disabledpriceinput = false;
        }
    },
    // 向父组件传值
    showParentComp (){
        this.$emit("closeapplypage",{});
    },
    // refreshPageRequest: function(page = 1) {
    //   this.pageRequest.page = page;
    //   this.findPage();
    // },
    // refreshPageSizeRequest: function(pageSize) {
    //   this.pageRequest.pageSize = pageSize;
    //   this.findPage();
    // },

    // 删除表格行
    deleteRow (index, item) {
        console.log(index);
        console.log(item);
        let self = this;
        self.pageResult.rows.splice(index,1);  
    },
    // 封装发起申请和申请暂存接口
    // params -- url, callback
    sendApplyOrSave (params) {
        let self = this;
        let requestData = {
            "id": "", // 点击编辑按钮进入该页面时才能获取到id
            "ownerId": self.ownerId,
            "scmXsCreditApplyList": []
        };
        console.log(self.componentParams);
        // if(self.componentParams.id && self.componentParams.flag !== 'addNew') {
        //     requestData.id = self.componentParams.id;
        // }
        if(self.componentParams.id) {
            requestData.id = self.componentParams.id;
        }
        if(self.pageResult.rows.length < 1) {
            self.$message({
                message: '请添加商业名单！',
                type: "error",
                duration: 2000
            });
            return;
        }
        console.log(self.componentParams);
        // 发起请求前还要验证购销方式、资信额度、备注
        if(params.flag === "sendApply") {
            for(let i = 0; i < self.pageResult.rows.length; i++) {
                if(!self.pageResult.rows[i].marketWay) {
                    self.$message({
                        message: '第' + (i + 1) + '条数据没有填写购销方式！',
                        type: "error",
                        duration: 2000
                    });
                    return;
                }
                if(self.pageResult.rows[i].marketWay !== '0' && !self.pageResult.rows[i].price) {
                    self.$message({
                        message: '第' + (i + 1) + '条数据没有填写资信额度！',
                        type: "error",
                        duration: 2000
                    });
                    return;
                }
            }
        }
        
        // 验证通过
        requestData.scmXsCreditApplyList = self.pageResult.rows;
        if(params.flag === "sendApply") {
            self.loading = true;
        }
        
        self.$api.xsgl
        .post({
          vueInstance: self,
          url: params.url,
          data: requestData
        }, params.callback);
    },
    // 发起申请 /scm/scmXsCreditCollect/v1/sendApply
    applySubmit() {
        let self = this;
        self.sendApplyOrSave({
            flag: 'sendApply',
            url: '/scm/scmXsCreditCollect/v1/sendApply',
            callback: function(res) {
                // res -- {"state":true,"message":"审批发起成功"}
                if(res.state) { // 发送申请成功
                    self.stepsBar.active = 2;
                    self.applySteps.select = false;
                    self.applySteps.completeTitle = '申请';
                }
                setTimeout(function() {
                    self.loading = false;
                    self.applySteps.complete = true;
                }, 500);
                
            }
        });
    },
    // 申请暂存 /scm/scmXsCreditCollect/v1/save   申请暂存
    applyCache() {
        let self = this;
        self.sendApplyOrSave({
            flag: 'save',
            url: '/scm/scmXsCreditCollect/v1/save',
            callback: function(res) {
                // res -- {"state":true,"message":"添加商业资信申请总表成功"}
                console.log(res);
                if(res.state) { // 暂存成功之后不跳页
                    self.$message({
                      message: '暂存成功！',
                      type: "success",
                      duration: 2000
                    });
                    //self.pageResult.rows = res.value.scmXsCreditApplyList;
                    self.componentParams.id = res.value.id;
                }
                self.loading = false;
            }
        });
    },
    // 获取商业列表的函数 /scm/scmZsjCommerce/v1/downBoxApplySuccess/{ownerId}
    getcCreditApplicationTypeList () {
        let self = this;
        self.$api.xsgl
        .get({
            vueInstance: self,
            url: "/scm/scmZsjCommerce/v1/downBoxApplySuccess/" + self.ownerId,
            data: {}
        },function(res) {
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
        self.$api.xsgl
        .get({
            vueInstance: self,
            url: "/scm/scmXsCreditApply/v1/getStatisticalDate/" + data.commerceId +"/" + data.ownerId,
            data: {}
        },resolveFunc);
    },
    addNewCommerceSubmit (formName) {
        let self = this;
        let selectedCommerce = null;
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
              // 检查之前是否已经选择过同一家公司
              for(let i = 0; i < self.pageResult.rows.length; i++) {
                  if(self.pageResult.rows[i].commerceCode === self.addNewForm.commerceId) {
                      self.innerVisible = true;
                      return;
                  }
              }
              // 遍历出所选择的商业对象
              for(let i = 0; i < self.commerceNameList.length; i++) {
                  if(self.commerceNameList[i].commerceCode === self.addNewForm.commerceId) {
                      selectedCommerce = self.commerceNameList[i];
                  }
              }
              //self.loading = true;
              self.getStatisticalDate({
                  commerceId: self.addNewForm.commerceId,
                  ownerId: self.ownerId
              }, function(res) {
                  self.loading = false;
                  item.year = self.addNewForm.year.getFullYear() + '';
                  item.quarter = self.addNewForm.quarter;
                  item.businessDivision = selectedCommerce.businessDivision;
                  item.businessDivisionId = selectedCommerce.businessDivisionId;
                  item.commerceCode = selectedCommerce.commerceCode;
                  item.commerceName = selectedCommerce.commerceName;
                  item.province = selectedCommerce.province;
                  item.provinceId = selectedCommerce.provinceId;
                  
                  item.beforeYearPrice = res.beforeYearPrice;
                  item.lastTimePrice = res.lastTimePrice;
                  item.lastYearPrice = res.lastYearPrice;
                  item.sysPrice = res.sysPrice;

                  item.disabledpriceinput = false;

                  self.pageResult.rows.push(item);

                  self.applySteps.addNew = false;
                  // 重置表单
                  //self.addNewForm = { year: null, quarter: '', commerceId: '' };
                  self.addNewForm.commerceId = '';
                  //self.$refs[formName].resetFields();

              });
            
          } else {
            console.log('error submit!!');
            return false;
          }
        }); 
    },
    // 关闭dialog，清空表单
    dialogCancel (formName) {
        //this.$refs[formName].resetFields();
        this.applySteps.addNew = false;
        this.addNewForm.commerceId = '';
        //this.addNewForm.year = null;
        //this.addNewForm.quarter = '';
        //this.addNewForm.commerce = null;
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
.el-steps {
    justify-content: center;
}

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
  position: relative;
  width: 100%;
  font-size: 28px;
  font-weight: bold;
  background: #f3f3f3;
}
.searchheadleft{
    position: absolute;
    left: 10px;
}
.searchheadcenter{
    width: 100%;
    text-align: center;
    line-height: 1.5em;
}
.searchheadright{
    position: absolute;
    top:0;
    right: 10px;
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
