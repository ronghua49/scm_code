<template>
	<div>
		<el-container>

			<div class="searchHeader">
				<div class="headleft">
					<!-- <router-link to="/cgdd/cgBuyOrder"> -->
						<el-button size="mini" class="el-icon-back" type="primary" @click="goBack">返回</el-button>
					<!-- </router-link> -->
				</div>

				<div class="headcenter">{{title}}</div>
				<div class="headright" >
					<el-button size="mini" v-if="isEdit" type="primary" @click="sendApply">发起申请</el-button>
				</div>

			</div>

			<el-header>
				<div class="header">
					<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">基础信息</span>
					</div>
					<div class="headright">
					</div>
				</div>
			</el-header>
			<el-main>
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
			</el-main>
			<div class="header">
				<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">数据列表</span>
				</div>
				<div class="headright" v-if="isEdit">
				    <el-button type="primary" class="el-icon-circle-plus" size="mini" @click="addProduct">新增产品</el-button>                    
				</div>
			</div>
			<!--table-->
			<el-table v-loading="loading" element-loading-text="加载中" :data="tableData" size="mini" border style="width: 100%">
                <el-table-column type="index" label="序号" align="center"></el-table-column>
                <el-table-column prop="achange" label="件装量" align="center"></el-table-column>
                <el-table-column prop="count" label="盒数" align="center" minWidth="150">
                <template slot-scope="scope">
                    <span v-show="!isEdit">{{tableData[scope.$index].productSum}}</span>
                    <el-input v-show="isEdit" v-model="tableData[scope.$index].productSum" @input="oninput(scope.$index, scope.row)"  placeholder="请输入盒数"></el-input>
                </template>
                </el-table-column>
				<el-table-column v-for="item in column1" :key="item.prop" :prop="item.prop" :label="item.label" :formatter="item.formatter" :align="item.align">
				</el-table-column>
                <el-table-column v-if="isEdit"
                    fixed="right"
                    label="操作"
                    align="center"
                    minWidth="100">
                    <template slot-scope="scope">
                    <el-button type="danger" class="el-icon-delete" size="mini" @click="del(scope.$index,scope.row)">删除</el-button>
                    </template>
                </el-table-column>
			</el-table>
			<!-- <div class="pagination">
				<el-pagination @current-change="refreshPageRequest1" @size-change="refreshPageSizeRequest1" background :current-page="pageRequest1.page" :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest1.pageSize" layout="sizes, total, prev, pager, next" :total="total1">
				</el-pagination>
			</div> -->

            <el-dialog title="新增产品" width="50%"  :visible.sync="dialogVisible" :close-on-click-modal="false">
                <el-form :model="addForm" label-width="120px" :rules="addRules"  ref="addForm" size="small">
                <el-form-item style="width: 98%;" label="产品名称" prop="productName">
                    <el-autocomplete style="width: 100%"
                        class="inline-input"
                        v-model="addForm.productName"
                        :fetch-suggestions="querySearch"
                        placeholder="请输入商品名称"
                        @select="handleSelect"
                        clearable
                        ></el-autocomplete>
                </el-form-item>
                <el-form-item style="width: 49%;" label="产品编码" prop="productCode">
                    <el-input :disabled="true" v-model="addForm.productCode" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item style="width: 49%;" label="产品通用名称" prop="productCname">
                    <el-input :disabled="true" v-model="addForm.productCname" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item style="width: 49%;" label="品规" prop="productGauge">
                    <el-input :disabled="true" v-model="addForm.productGauge" auto-complete="off" ></el-input>
                </el-form-item>
                <el-form-item style="width: 49%;" label="药品类型" prop="medicineType">
                    <el-input :disabled="true" v-model="addForm.medicineType" auto-complete="off"></el-input>
                </el-form-item>
                
                <el-form-item style="width: 49%;" label="单位" prop="unit">
                    <el-input :disabled="true" v-model="addForm.unit" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item style="width: 49%;" label="单价" prop="unitPrice">
                    <el-input :disabled="true" v-model="addForm.unitPrice" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item style="width: 49%;" label="合同剩余数" prop="surplusAmount">
                    <el-input :disabled="true" v-model="addForm.surplusAmount" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item style="width: 49%;" label="采购数量" prop="productSum">
                    <el-input :disabled="true" v-model="addForm.productSum" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item style="width: 49%;" label="件装量" prop="achange">
                    <el-input :disabled="true" v-model="addForm.achange" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item style="width: 49%;" label="盒数" prop="count">
                    <el-input :disabled="countEdit" v-model="addForm.count" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item style="width: 49%;" label="总金额" prop="totalPrice">
                    <el-input :disabled="true" v-model="totalPrice" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item style="width: 49%;" label="币种" prop="currency">
                    <el-input :disabled="true" v-model="addForm.currency" auto-complete="off"></el-input>
                </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                <el-button :size="size" @click.native="dialogVisible = false">取消</el-button>
                <el-button :size="size" type="primary" @click="submitForm" >确定</el-button>
                </div>
            </el-dialog>	
		</el-container>
	</div>
</template>

<script>
import storage from "@/utils/storage";
let timer;
export default {
  name: "cgbuyorderdetail",
  data() {
    return {
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
      title: "",
      size:'mini',
      restaurants: [],
      labelPosition: "right",
      loading2: true,
      ownerId: "",
      total1: 0,
      dialogVisible: false,
      gongid: "",
      isEdit: false,
      countEdit: true,//盒数是否能够输入
      addForm:{
        productName:'',
        productCode:'',
        productCname:'',
        productGauge:'',
        medicineType:'',
        unit:'',
        unitPrice:'',
        sumPrice:'',
        productSum:'',
        achange:'',
        surplusAmount:'',
        count:'',
      },
      addRules: {
        productName: [
          {required: true, message: '请选择产品名称',}
        ],
        count:[
          {required: true, message: '请输入盒数',},
          {tirgger:'blur', validator: (rule, value, callback)=>{
            if(value.length<=0){
              callback(new Error('请输入盒数'))
            }else if(!Number(value) && value.length>0){
              callback(new Error('盒数必须为数字'))
            }else if(value<= 0){
              callback(new Error('盒数必须大于0'))
            }else if(value>=99999999){
              callback(new Error('输入数量超过限制'))
            }else{
              callback();
            }
          }},
        ]
      },
      options: [
        {
          name: "待发起",
          index: 1
        },
        {
          name: "审核中",
          index: 2
        },
        {
          name: "未通过",
          index: 3
        },
        {
          name: "已通过",
          index: 4
        }
      ],
      gongoption: [],
      tableData: [],
      checked: false,
      loading: false,
      search: [],
      pageRequest1: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      wrapForm: {}
    };
  },
  watch: {
    'addForm.productName':'productNameChange'
  },
  computed: {
    totalPrice(){
      return Number(this.addForm.count) * Number(this.addForm.unitPrice)
    },
    mainTabs: {
      get () { return this.$store.state.tab.mainTabs },
      set (val) { this.$store.commit('updateMainTabs', val) }
    },
    mainTabsActiveName: {
      get () { return this.$store.state.tab.mainTabsActiveName },
      set (val) { this.$store.commit('updateMainTabsActiveName', val) }
    }
  },
  methods: {
    // tabs, 删除tab
    removeTabHandle (tabName) {
      this.mainTabs = this.mainTabs.filter(item => item.name !== tabName)
      if (this.mainTabs.length >= 1) {
        // 当前选中tab被删除
        if (tabName === this.mainTabsActiveName) {
          this.$router.push({ name: this.mainTabs[this.mainTabs.length - 1].name }, () => {
            this.mainTabsActiveName = this.$route.name
          })
        }
      } else {
        this.$router.push("/")
      }
    },
    goBack() {
      let name = this.$route.name;
      this.removeTabHandle(name);
      this.$router.push({
        path:'/cgdd/cgBuyOrder',
      })
    },
    productNameChange(val){
      if(val == ''){
        this.$refs.addForm.resetFields();
        this.countEdit = true;
      }else{
        this.countEdit = false;
      }
    },
    getDownBox(id){
      this.$api.approval
      .postscm({
          url: "/scm/scmCgContractProduct/v1/list",
          data: {
            pageBean: this.pageRequest,
            params: {},
            querys:[
              {property: "contractId",value:id, operation: "EQUAL", relation: "AND"},
            ],
            sorter: []
          }
        })
        .then(res => {
          if (res.state == false) {
            this.$message({
              message:  res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          };
          this.resultList = res.rows.reduce((final, item, index, arr)=>{
            item.value = item.productName+"|"+item.productCode+"|"+item.productCname+"|"+item.productGauge+"|"+item.medicineType;
            final.push(item);
            return final;
          },[]);
        });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    addProduct(){
      if (this.$refs["addForm"]!==undefined) {
          this.$refs["addForm"].resetFields();
      }
      this.dialogVisible = true;
    },
    oninput(index, row){//表格中直接修改盒数调用后台方法
      clearTimeout(timer);
      timer = setTimeout(()=>{
        // let newid = this.fisrtStepId
        if(row.productSum.length<=0 || !Number(row.productSum) || Number(row.productSum)<0 || row.productSum.startsWith('0') || row.productSum.includes('.')){
          this.tableData[index].productSum = '0';          
        }
        this.tableData[index].sumPrice = Number(this.tableData[index].productSum) * Number(this.tableData[index].unitPrice)
        let data = {
          id: row.id,
          productSum: row.productSum,
          contractProductId: row.contractProductId,
          proOrderId: this.fisrtStepId
        }
        this.$api.approval
        .postscm({
          url: "/scm/scmCgProOrderProduct/v1/save",
          data: data
        })
        .then(res => {
          if (res.state == false) {
            this.$message({
              message: "操作失败, " + res.message,
              type: "error",
              duration: 800
            });
            return;
          } 
          this.$message({
            message:res.message,
            type: 'success',
            duration: 800
          })
          
        //   this.getDetail(newid);
        })
      },1000)
    },
    del(index, row){//第二步 删除商品
      this.$confirm('确认删除选中记录吗？', '提示', {
				type: 'warning'
			}).then(() => {
				this.$api.approval.delscm({
          url: '/scm/scmCgProOrderProduct/v1/remove/'+row.id
        })
        .then(res => {
              if (res.state == false) {
                this.$message({
                  message: "操作失败, " + res.message,
                  type: "error",
                  duration: 800
                });
                return;
              } 
              this.$message({
                message:res.message,
                type: 'success',
                duration: 800
              })
              this.getData();
        })
			}).catch(() => {
			})
    },
    submitForm(){//第二部新增商品
    //   let newid = this.fisrtStepId;
      this.$refs['addForm'].validate( valid => {
        if(valid){
          let data = {
            achange: this.addForm.achange,
            currency: this.addForm.currency,
            contractProductId: this.addForm.id,
            medicineType: this.addForm.medicineType,
            medicineTypeId: this.addForm.medicineTypeId,
            proOrderId: this.fisrtStepId,
            procurementCode: this.addForm.procurementCode,
            productCname: this.addForm.productCname,
            productCode: this.addForm.productCode,
            productGauge: this.addForm.productGauge,
            productId: this.addForm.productId,
            productName: this.addForm.productName,
            productSum: this.addForm.count,
            sumPrice: this.totalPrice,
            unit: this.addForm.unit,
            unitPrice: this.addForm.unitPrice,
          };
          this.$api.approval
          .postscm({
            url: "/scm/scmCgProOrderProduct/v1/save",
            data: data
          })
          .then(res => {
            if (res.state == false) {
              this.$message({
                message: "操作失败, " + res.message,
                type: "error",
                duration: 800
              });
              return;
            } 
            this.$message({
              message:res.message,
              type: 'success',
              duration: 800
            })
            this.dialogVisible = false;
            this.getData();
            // this.getDetail(newid);
          });
        }
      })
    },
    sendApply() {//发起申请发起申请
      this.$api.approval
      .postscm({
         url: "/scm/scmCgProcurementOrder/v1/sendApply",
         data:{
           id: this.fisrtStepId
         }
      })
      .then(res => {
        if (res.state === false) {
          this.$message({
            message: "操作失败, " + res.shortMessage,
            type: "error",
            duration: 800
          });
          return;
        }
        this.$message({
            message:  res.shortMessage,
            type: "success",
            duration: 800
          });
      })
    },
    querySearch(queryString, cb){
      var result = this.resultList;
      var results = queryString ? result.filter(this.createFilter(queryString)) : result
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item){
      this.addForm = Object.assign(this.addForm,item)
    },
    changeState(){
        if(this.$route.query.isEdit){
            this.title = '采购订单编辑';
            this.isEdit = true;
            this.getDownBox(this.$route.query.contractId);
        }else{
            this.title = '采购订单详情';
            this.isEdit = false
        }
    },
    getData() {
        this.$api.zsj
        .changeState({
          url: "/scm/scmCgProcurementOrder/v1/get/" + this.$route.query.id
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
          this.wrapForm = Object.assign({}, res);
          this.tableData = res.scmCgProOrderProductList;
        });
    }
  },
  mounted() {
    this.ownerId = storage.get("ownerId");
    this.getData();
    this.changeState();
    this.fisrtStepId = this.$route.query.id;
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

.el-header {
  padding: 0 !important;
}

.tab-title {
  width: 100%;
  height: 50px;
  font-size: 14px;
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

.headcenter {
  font-size: 24px;
}

.sx {
  margin: auto 5px;
}

.heng {
  display: flex;
  width: 100%;
  font-size: 14px;
  align-items: center;
}

.box span {
  display: inline-block;
  width: 120px;
  font-size: 14px;
}

.content {
  display: flex;
  justify-content: space-between;
}

.content1 {
  display: flex;
  justify-content: space-between;
  margin-top: 15px;
}

.content3 {
  display: flex;
  margin-top: 15px;
}

.content1 span {
  font-size: 14px;
}

.el-main {
  // border-bottom: 1px solid #cccccc;
  padding-bottom: 28px;
}

.title {
  display: flex;
  justify-content: flex-start;
  margin-top: 20px;
  margin-bottom: 20px;
}

.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.el-select {
  width: 100%;
}

.box {
  display: flex;
  align-items: center;
}

.color {
  color: red;
  font-size: 20px;
  margin-left: 3px;
}

.box1 {
  display: flex;
  align-items: center;
  margin-left: 8.9%;
}

.box2 {
  display: flex;
  align-items: center;
  margin-left: 8.3%;
}

.box1 span {
  display: inline-block;
  width: 120px;
  font-size: 14px;
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

.el-form {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  .el-form-item {
    width: 33%;
  }
}

.el-table .cell {
  white-space: nowrap !important;
}

.el-main {
  padding: 0 !important;
}

header {
  height: 72px !important;
}

.searchHeader {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 28px;
  font-weight: bold;
  padding-bottom: 7px;
  background: #f3f3f3;
  margin-bottom: 8px;
}
</style>