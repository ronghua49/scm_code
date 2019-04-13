<template>
  <div>
    <!-- 标题 + 步骤条 showParentComp-->
    <div class="head-item">
      <div class="searchHeader">
        <div class="searchheadleft">
          <el-button @click="showParentComp" size="mini" class="el-icon-close" type="primary">关闭</el-button>
        </div>

        <div class="searchheadcenter">{{componentParams.title}}</div>
        <div v-if="componentParams.flag !== 'details' && applySteps.active !== '2'" class="searchheadright">
          <el-button v-if="applySteps.active === '0'" size="mini" type="primary" @click="nextSteps">下一步</el-button>
          <el-button v-if="applySteps.active === '1' && componentParams.flag !== 'edit'" size="mini" type="primary" @click="preSteps">上一步</el-button>
          <el-button v-if="applySteps.active === '1'" :disabled="applySteps.active !== '1'" size="mini" type="primary" @click="applyCache">申请暂存</el-button>
          <el-button v-if="applySteps.active === '1'" :disabled="applySteps.active !== '1'" size="mini" type="primary" @click="applySubmit">发起申请</el-button>
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
            <el-step title="选择分销商"></el-step>
            <el-step title="协议合作条款"></el-step>
            <el-step title="完成"></el-step>
          </el-steps>
        </el-row>
      </template>
    </div>
    
    <!-- 选择分销商 -->
    <transition name="el-fade-in">
        <div class="content-item" v-show="applySteps.active === '0'">
            <div class="header">
              <div class="headleft">
                <span class="el-icon-search"></span>
                <span class="sx">选择分销商</span>
              </div>
            </div>
            <el-row>
				<el-form 
                  :model="searchForm" 
                  :rules="searchFormRules" 
                  ref="searchForm" 
                  label-position="right" 
                  label-width="120px" 
                  size="small" class="demo-ruleForm" 
                  style="display: flex;justify-content: center;margin-top: 80px;margin-bottom: 300px;">
					<el-form-item prop="businessDivisionId" label="商务分区">
                      <el-select clearable v-model="searchForm.businessDivisionId" placeholder="请选择商务分区" @change="getPrivence">
                        <el-option
                          v-for="item in divisionList"
                          :key="item.id"
                          :label="item.businessDivision"
                          :value="item.id"
                        ></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="provinceId" label="省区">
                      <el-select clearable v-model="searchForm.provinceId" placeholder="请选择省区">
                        <el-option
                          v-for="item in searchProvinceList"
                          :key="item.id"
                          :label="item.province"
                          :value="item.id"
                        ></el-option>
                      </el-select>
                    </el-form-item>
				</el-form>
		    </el-row>
        </div>
    </transition>

    <!-- 列表数据 -->
    <transition name="el-fade-in">
      <div v-show="applySteps.active === '1'">
        <div class="header">
          <div class="headleft">
            <span class="el-icon-tickets"></span>
            <span class="sx">
                <span style="color: #409EFF;">【{{searchForm.businessDivision}}】</span>
                <span style="color: #409EFF;">【{{searchForm.province ? searchForm.province : '-'}}】</span>
                的分销商协议合作条款
            </span>
          </div>
        </div>
        <!--表格栏-->
        <el-table class="test" :data="pageResult.rows" highlight-current-row v-loading="loading" element-loading-text="加载中"
          border stripe fit :show-overflow-tooltip="false"
          max-height="500" size="mini" align="left" style="width:100%;">
          <el-table-column prop="clauseCode" key="clauseCode" label="编码" min-width="100" header-align="center" align="center" ></el-table-column>
          <el-table-column header-align="center" align="center" label="分商业">
            <el-table-column prop="commerceName" key="commerceName" label="名称" header-align="center" align="center" min-width="100"></el-table-column>
            <el-table-column prop="commerceLevel" key="commerceLevel" label="行政级别" header-align="center" align="center" min-width="100"></el-table-column>
            <el-table-column prop="scm_zsj_commerceState" key="scm_zsj_commerceState" label="商业认可状态" header-align="center" align="center" min-width="100"></el-table-column>
            <!-- 市场类型是从后台获取的 -->
            <el-table-column prop="functionType" key="functionType" label="市场类型" header-align="center" align="center" min-width="180">
                <template slot-scope="scope">
                    <el-select :disabled="componentParams.detailsdisabled" class="dropdown-select" v-model="scope.row.functionType" placeholder="请选择">
                        <el-option
                          v-for="item in functionTypeList"
                          :key="item.id"
                          :label="item.functionType"
                          :value="item.id"
                        ></el-option>
                    </el-select>
                </template>
            </el-table-column>
            
            <el-table-column prop="isSubsidiary" key="isSubsidiary" label="是否子分公司" header-align="center" align="center" min-width="100">
                <template slot-scope="scope">
                    <el-checkbox :disabled="componentParams.detailsdisabled" true-label="1" false-label="0" v-model="scope.row.isSubsidiary"></el-checkbox>
                </template>
            </el-table-column>
            
            <el-table-column prop="starLevel" key="starLevel" label="星级" header-align="center" align="center" min-width="120">
                <template slot-scope="scope">
                    <el-select :disabled="componentParams.detailsdisabled" v-model="scope.row.starLevel" placeholder="请选择">
                        <el-option label="一星" value="0"></el-option>
                        <el-option label="二星" value="1"></el-option>
                        <el-option label="三星" value="2"></el-option>
                        <el-option label="四星" value="3"></el-option>
                        <el-option label="五星" value="4"></el-option>
                    </el-select>
                </template>
            </el-table-column>

            <el-table-column prop="integral" key="integral" label="上次协议纯销积分" header-align="center" align="center" min-width="100"></el-table-column>
          </el-table-column>

          <el-table-column prop="messageCost" key="messageCost" label="信息管理费(元/年)" header-align="center" align="center" min-width="120">
                <template slot-scope="scope">
                   <el-input :disabled="componentParams.detailsdisabled" @input.native="resetValue" type="number" v-model="scope.row.messageCost"></el-input>
                </template>
          </el-table-column>
          <el-table-column prop="isPromotional" key="isPromotional" label="是否签订促销折让" header-align="center" align="center" min-width="120">
                <template slot-scope="scope">
                    <el-checkbox :disabled="componentParams.detailsdisabled" true-label="1" false-label="0" v-model="scope.row.isPromotional"></el-checkbox>
                </template>
          </el-table-column>
          <el-table-column prop="startDate"  label="起始日期" header-align="center" align="center" min-width="180">
                <template slot-scope="scope">
                    <el-date-picker
                      :disabled="componentParams.detailsdisabled"
                      v-model="scope.row.startDate"
                      type="date"
                      
                      value-format="yyyy-MM-dd HH:mm:ss"
                      placeholder="选择日期">
                    </el-date-picker>
                </template>
            </el-table-column>
          
          <el-table-column header-align="center" align="center" label="协议生效日期">
            
            <el-table-column prop="endDate" label="截止日期" header-align="center" align="center" min-width="180">
                <template slot-scope="scope">
                    <el-date-picker
                      :disabled="componentParams.detailsdisabled"
                      v-model="scope.row.endDate"
                      type="date"
                      
                      value-format="yyyy-MM-dd HH:mm:ss"
                      placeholder="选择日期">
                    </el-date-picker>
                </template>
            </el-table-column>
          </el-table-column>
          <el-table-column header-align="center" align="center" label="购货渠道">
            <el-table-column prop="provincePlatformId" key="provincePlatformId" label="省级平台" header-align="center" align="center" min-width="120">
                <template slot-scope="scope">
                   <el-select :disabled="componentParams.detailsdisabled" v-model="scope.row.provincePlatformId" placeholder="请选择">
                        <el-option
                          v-for="item in chargeChannelList"
                          :key="item.id"
                          :label="item.commerceName"
                          :value="item.id"
                        ></el-option>
                    </el-select>
                </template>
            </el-table-column>
            <el-table-column prop="upCommerceId" key="upCommerceId" label="上游商业" header-align="center" align="center" min-width="120">
                <template slot-scope="scope">
                   <el-select :disabled="componentParams.detailsdisabled" v-model="scope.row.upCommerceId" placeholder="请选择">
                        <el-option
                          v-for="item in chargeChannelList"
                          :key="item.id"
                          :label="item.commerceName"
                          :value="item.id"
                        ></el-option>
                    </el-select>
                </template>
            </el-table-column>
          </el-table-column>
          <el-table-column prop="memo" key="memo" label="备注" header-align="center" align="center" min-width="120">
                <template slot-scope="scope">
                   <el-input :disabled="componentParams.detailsdisabled" type="text" v-model="scope.row.memo"></el-input>
                </template>
          </el-table-column>
          <!-- <el-table-column v-if="componentParams.flag!=='details'" label="操作" width="150" fixed="right" header-align="center" align="center">
            <template slot-scope="scope">
              <el-button icon="el-icon-edit" size="small" type="danger"  @click="deleteRow(scope.$index, scope.row)" >删除</el-button>
            </template>
          </el-table-column> -->
        </el-table>
      </div>
    </transition>
    <!-- 点击发起申请 显示完成提示页面 -->
    <transition name="el-fade-in">
      <div class="content-item" v-show="applySteps.active === '2'">
        <div class="success">
          <span class="el-icon-check"></span> 您已完成
          <span style="color: #409EFF;">【{{searchForm.businessDivision}}】</span>
          <span style="color: #409EFF;">【{{searchForm.province}}】</span>
          分销商协议合作条款申请
          <span style="color: #409EFF;">【{{applySteps.completeTitle}}】</span>
        </div>
        <div class="success-bottom">共
          <span style="color: #409EFF;">【{{pageResult.rows.length}}】</span>家经销商
        </div>
      </div>
    </transition>
    
  </div>
</template>

<script>
// 省级平台和上游商业下拉框接口 /scm/scmZsjCommerce/v1/firstList 省区id 商务分区id ownerId approvalState=2
import storage from "@/utils/storage";
export default {
  name: "distributorAgreementApply",
  props:['passparam'],
  created() {
  },
  mounted() {
    this.ownerId = storage.get("ownerId");
    this.getDivision();
    this.getFunctionType();
  },
  data() {
    return {  
      componentParams: {
          flag: '',// 是编辑还是查看详情还是申请 与id是配对的
          id: '',
          title: '分销商协议合作条款申请',
          detailsdisabled: false
      },
      searchForm: {
          businessDivision: '',
          businessDivisionId: '',
          province: '',
          provinceId: ''
      },
      searchFormRules: {
          businessDivisionId: [{required: true, message: '请选择商务分区', trigger: 'change'}],
          //provinceId: [{required: true, message: '请选择省区', trigger: 'change'}]
      },
      // 购销渠道数据列表
      chargeChannelList: [],
      
      ownerId: '',
      // 步骤条
      stepsBar: {
        active: 0
      },
      // 申请步骤
      applySteps: {
        active: '', // 0 -- 显示分区/省区选择框；1 -- 显示列表；2 -- 显示完成页面
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
      divisionList: [],
      searchProvinceList: [],
      // 表格中的下拉选择框
      functionTypeList: []
    };
  },
  watch: {
    passparam: 'getId'
  },
  methods: {
    resetValue (e) { // 小数点后两位，不能小于0
        let self = this;
        let value = e.target.value;
        e.target.value = (value.match(/^\d*(\.?\d{0,2})/g)[0]) || null;
    },

    getId () {
        let self = this;
        let middleArr = self.passparam.split(',');
        self.componentParams.flag = middleArr[0];
        self.componentParams.id = middleArr[1];

        if(self.componentParams.flag === 'addNew') {
            //self.applySteps.active = '0';
            self.toogleStepPage('0');
        }
        
        if(self.componentParams.flag === 'details') {
            self.componentParams.title = '分销商协议合作条款申请';
            self.componentParams.detailsdisabled = true;
            
        }
        if(self.componentParams.id && self.componentParams.flag !== 'addNew') {       
            //self.applySteps.active = '1';
            self.toogleStepPage('1');
            self.$api.xsgl
            .get({
                vueInstance: self,
                url: "/scm/scmXsDistributorAgreementSummary/v1/get/" + self.componentParams.id,
                data: {}
            },function(res) {
                console.log(res);
                // self.pageResult = res;
                if(res.approvalState) {
                  self.pageResult.approvalState = res.approvalState;
                }
                self.pageResult.rows = res.scmXsDistributorClauseList;
                self.searchForm.businessDivision = res.businessDivision;
                self.searchForm.businessDivisionId = res.businessDivisionId;
                self.searchForm.province = res.province;
                self.searchForm.provinceId = res.provinceId;
            });
        }
    },
    // 向父组件传值
    showParentComp (){
        this.$emit("closeapplypage",{});
    },
    refreshPageRequest: function(page = 1) {
      this.pageRequest.page = page;
      this.findPage();
    },
    refreshPageSizeRequest: function(pageSize) {
      this.pageRequest.pageSize = pageSize;
      this.findPage();
    },
    // 控制步骤间页面切换的动画流畅
    toogleStepPage (flag) {
        let self = this;
        self.applySteps.active = '-1';
        setTimeout(function() {
            self.applySteps.active = flag;
        },500);

    },
    // 上一步按钮
    preSteps () {
      let self = this;
      //self.applySteps.active = '0';
      self.toogleStepPage('0');
      self.stepsBar.active = 0;
    },
    // 下一步
    nextSteps () {
      let self = this;
      self.$refs['searchForm'].validate((valid) => {
            if (valid) {
                self.getDistributorClauseList();
                self.getCommerce();
            } else {
                return false;
            }
       }); 
    },
    // 省级平台和上游商业下拉框接口 /scm/scmZsjCommerce/v1/firstList 省区id 商务分区id ownerId approvalState=2
    getCommerce () {
        let self = this;
        let querysData = [
            {
                hasInitValue: false,
                property: "ownerId", 
                value: self.ownerId, 
                operation: "EQUAL", 
                relation: "AND"
            },
            {
                hasInitValue: false,
                property: "businessDivisionId", 
                value: self.searchForm.businessDivisionId, 
                operation: "EQUAL", 
                relation: "AND"
            },
            {
                hasInitValue: false,
                property: "provinceId", 
                value: self.searchForm.provinceId, 
                operation: "EQUAL", 
                relation: "AND"
            },
            {
                hasInitValue: false,
                property: "approvalState", 
                value: "2", 
                operation: "EQUAL", 
                relation: "AND"
            }
        ];
        self.$api.xsgl.post({
          vueInstance: self,
          url: "/scm/scmZsjCommerce/v1/firstList",
          data: {
            pageBean: {
              page: 1,
              pageSize: 10,
              showTotal: true
            },
            params: {},
            querys: querysData,
            sorter: []
          }
        }, function(res) {
            self.chargeChannelList = res.rows;
        });
    },

    getDistributorClauseList () {
        let self = this;
        let query = [];
        let searchForm = Object.assign({},self.searchForm);
        self.searchProvinceList.forEach(function(item, index) {
            if(item.id === self.searchForm.provinceId) {
                self.searchForm.province = item.province;
            }
        });
        self.loading = true;
        for(var key in searchForm) {
          if(searchForm[key] !== '' && key.indexOf('Id') > -1) {
            query.push({
                hasInitValue: false,
                operation: "EQUAL",
                relation: "AND",
                value: searchForm[key],
                property: key
            }); 
          }
        }
        self.$api.xsgl.post({
          vueInstance: self,
          url: "/scm/scmXsDistributorClause/v1/commerceAndAcceptState",
          data: {
            pageBean: self.pageRequest,
            params: {},
            // querys: [{hasInitValue: false,property: "ownerId", value: self.ownerId, operation: "EQUAL", relation: "AND"}].concat(query),
            querys: [].concat(query), // 测试代码
            sorter: [
                {
                  "direction": "ASC",
                  "property": "commerceId"
                }
            ]
          }
        }, function(res) {
            res.rows.forEach(function(item, index) {
                Object.assign(item,{
                    "isSubsidiary": "0", //是否分子公司
                    "starLevel": "", // 星级
                    "messageCost": "", // 信息管理费
                    "isPromotional": "0", //是否促销折让
                    "startDate": "", //协议起始日期
                    "endDate": "", //协议截止日期
                    "memo": "",// 备注
                    "provincePlatformId": "",// 省级平台
                    "upCommerceId": ""// 上游商业
                });
            });
            self.pageResult = res;
            self.loading = false;
            //self.applySteps.active = '1';
            self.toogleStepPage('1');
            self.stepsBar.active = '1';
        });
    },

    // 删除表格行
    deleteRow (index, item) {
        let self = this;
        self.pageResult.rows.splice(index,1);  
    },
    // 封装发起申请和申请暂存接口
    // params -- url, callback
    sendApplyOrSave (params) {
        let self = this;
        let validateArr = [
            // {
            //     key: "isSubsidiary", // 不需验证
            //     value: "是否分子公司"
            // },
            {
                key: "starLevel",
                value: "星级"
            },
            {
                key: "messageCost",
                value: "信息管理费"
            },
            // {
            //     key: "isPromotional", // 不需验证
            //     value: "是否促销折让"
            // },
            {
                key: "startDate",
                value: "协议起始日期"
            },
            {
                key: "endDate",
                value: "协议截止日期"
            },
            // {
            //     key: "memo", // 不需要验证
            //     value: "备注"
            // },
            /* 暂时没有数据
            {
                key: "provincePlatformId",
                value: "省级平台"
            },
            {
                key: "upCommerceId",
                value: "上游商业"
            }*/
        ];
        let requestDataArray = [];
        let requestData = {
            "id": "", // 点击编辑按钮进入该页面或者发起一次暂存才能获取到id
            "ownerId": self.ownerId,
            "province": self.searchForm.province,
            "provinceId": self.searchForm.provinceId,
            "businessDivision": self.searchForm.businessDivision,
            "businessDivisionId": self.searchForm.businessDivisionId,
            "scmXsDistributorClauseList": []
        };
         if(self.componentParams.id) {
            requestData.id = self.componentParams.id;
        }
        if(self.pageResult.rows.length < 1) {
            self.$message({
                message: '请选择分销商协议合作条款！',
                type: "error",
                duration: 2000
            });
            return;
        }
        if(params.flag === 'sendApply') {
            // 验证
            for(let i = 0; i < self.pageResult.rows.length; i++) {
                for(let j = 0; j < validateArr.length; j++) {
                    if(!self.pageResult.rows[i][validateArr[j].key]) {
                        self.$message({
                            message: '第' + (i + 1) + '条数据没有填写' + validateArr[j].value + '！',
                            type: "error",
                            duration: 2000
                        });
                        return;
                    }
                }
            }
        }
        
        // 验证通过
        for(let i = 0; i < self.pageResult.rows.length; i++) {
            requestDataArray.push({
                "commerceId": self.pageResult.rows[i].commerceId, // 商业（分销商
                "commerceName": self.pageResult.rows[i].commerceName, // 商业名称
                "commerceLevel": self.pageResult.rows[i].commerceLevel, // 商业级别
                "functionType": self.pageResult.rows[i].functionType, // 职能类型 
                "isSubsidiary": self.pageResult.rows[i].isSubsidiary, //是否分子公司
                "starLevel": self.pageResult.rows[i].starLevel, // 星级
                "messageCost": self.pageResult.rows[i].messageCost, // 信息管理费
                "isPromotional": self.pageResult.rows[i].isPromotional, //是否促销折让
                "startDate": self.pageResult.rows[i].startDate, //协议起始日期
                "endDate": self.pageResult.rows[i].endDate, //协议截止日期
                "memo": self.pageResult.rows[i].memo,// 备注
                "provincePlatformId": self.pageResult.rows[i].provincePlatformId,// 省级平台
                "upCommerceId": self.pageResult.rows[i].upCommerceId// 上游商业
            });
        }
        requestData.scmXsDistributorClauseList = self.pageResult.rows;
        //self.loading = true;
        self.$api.xsgl
        .post({
          vueInstance: self,
          url: params.url,
          data: requestData
        }, params.callback);
    },
    // 发起申请 
    applySubmit() {
        let self = this;
        self.sendApplyOrSave({
            flag: 'sendApply',
            url: '/scm/scmXsDistributorAgreementSummary/v1/sendApply',
            callback: function(res) {
                console.log(res);
                if(res.state) { // 发送申请成功
                    self.stepsBar.active = 3;
                    //self.applySteps.active = '2';
                    self.toogleStepPage('2');
                    self.applySteps.completeTitle = res.value.agreementSummaryCode;
                }
                self.loading = false;
            }
        });
    },
    // 申请暂存 
    applyCache() {
        let self = this;
        self.sendApplyOrSave({
            flag: 'save',
            url: '/scm/scmXsDistributorAgreementSummary/v1/save',
            callback: function(res) {
                // res -- {"state":true,"message":"添加商业资信申请总表成功"}
                if(res.state) { // 暂存成功之后不跳页
                    //self.pageResult.rows = res.value.scmXsCreditApplyList;
                    self.componentParams.id = res.value.id;
                    self.$message({
                        message: "暂存成功！",
                        type: "success",
                        duration: 2000
                    });
                }
                self.loading = false;
            }
        });
    },
    // 获取商务分区
    getDivision () {
        let self = this;
        self.$api.xsgl.getZdBusinessDivision({vueInstance: self}, function(res) {
            self.divisionList = res;
        });
    },
    // 获取省区
    getPrivence () {
        let self = this;
        self.searchForm.provinceId= ''
        let divisionId = self.searchForm.businessDivisionId // 商务分区ID
        self.searchProvinceList = self.divisionList.reduce((final, item, index, arr)=>{
            if(item.id == divisionId){
                self.searchForm.businessDivision = item.businessDivision;
                final = item.provinceList;
            }
            return final
        },[]);
    },
    // 获取市场类型
    getFunctionType () {
        let self = this;
        self.$api.xsgl.post({
          vueInstance: self,
          url:'/scm/scmZdFunctionType/v1/list',
          data:{
            pageBean: self.pageRequest,
            params: {},
            querys: [{
              hasInitValue: false,
              operation: "EQUAL",
              property: "commerceLevelId",
              relation: "AND",
              value: "6271002" // 商业级别 --- 这里传分销商这个级别的ID
            }],
            sorter: [{direction: "DESC",property: "createDate"}]
          }
        }, function(res) {
            self.functionTypeList = res.rows;
        });
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
  width: 80%;
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

.el-date-editor.el-input{
    width:auto;
}
.el-select .el-input__inner{
  font-size: 12px !important;
}
.el-select.dropdown-select .el-input__inner{
  font-size: 12px;
}
</style>
