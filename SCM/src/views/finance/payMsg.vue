<template>
	<div>
		<div class="header detail">
      <div class="headleft">
        <span class="el-icon-tickets"></span>
        <span>数据列表</span>
      </div>
    </div>
    <el-row>
      <el-form
        :model="wrapForm"
        :rules="wraprules"
        ref="wrapForm1"
        :label-position="labelPosition"
        label-width="120px"
        size="small"
        class="demo-ruleForm"
        style="display: flex;justify-content: center;margin-top: 80px;"
      >
        <el-form-item label="供应商名称" prop="supplier">
          <el-select v-model="wrapForm.supplier" clearable placeholder="请选择供应商名称">
            <el-option v-for="item in gongoption" :key="item.id" :label="item.supplierName" :value="item.supplierName"></el-option>
          </el-select>
        </el-form-item>
        
        <el-form-item label="开户行" prop="deposit">
			    <el-input v-model="wrapForm.deposit"></el-input>
			  </el-form-item>
        
        <el-form-item label="账号" prop="account">
          <el-input v-model="wrapForm.account" ></el-input>
        </el-form-item>
        
        <el-form-item label="税号" prop="duty">
          <el-input v-model="wrapForm.duty" ></el-input>
        </el-form-item>
        
        <el-form-item label="付款方式" prop="payType">
          <el-input v-model="wrapForm.payType" ></el-input>
        </el-form-item>
        
        <el-form-item label="扣款金额" prop="deductMoney">
          <el-input v-model="wrapForm.deductMoney" ></el-input>
        </el-form-item>
        
        <el-form-item label="付款金额" prop="payMoney">
          <el-input v-model="wrapForm.payMoney" ></el-input>
        </el-form-item>
        
        <el-form-item label="应付款金额" prop="actualPayMoney">
          <el-input v-model="wrapForm.actualPayMoney" ></el-input>
        </el-form-item>
        
        <el-form-item label="申请付款日期" prop="deliveryDate">
          <el-date-picker
            v-model="wrapForm.deliveryDate"
            value-format="yyyy-MM-dd HH:mm:ss"
            style="width:100%"
            placeholder="请选择交货日期"
            clearable
          ></el-date-picker>
        </el-form-item> 
        
        <el-form-item label="外币付款金额" prop="foreignCurrency">
          <el-input v-model="wrapForm.foreignCurrency" ></el-input>
        </el-form-item>
        
        <el-form-item label="外币应付款金额" prop="actualForeignCurrency">
          <el-input v-model="wrapForm.actualForeignCurrency" ></el-input>
        </el-form-item>
        
        <el-form-item label="采购员" prop="buyer">
          <el-input v-model="wrapForm.buyer" ></el-input>
        </el-form-item>
        
        <el-form-item label="备注" prop="memo">
          <el-input v-model="wrapForm.memo" ></el-input>
        </el-form-item>
      </el-form>
    </el-row>
	</div>
</template>
<script>
	export default{
		name:'payMsg',
		data(){
			return{
				wrapForm:{
					currency: "",
	        warehouse: "",
	        getTicket: "",
	        contractCode: "", //合同编号
	        contractId: "", //合同id
	        supplier: "",
	        deliveryDate: "",
	        supplierId: ""
				},
				wraprules: {
	        supplier: [
	          { required: true, message: "请选择供应商", trigger: "change" }
	        ],
	        deposit: [
	          { required: true, message: "请选择收票方", trigger: "change" }
	        ],
	        account: [
	          { required: true, message: "请选择合同编号", trigger: "change" }
	        ],
	        duty: [
	          { required: true, message: "请选择交货日期", trigger: "change" }
	        ],
	        payType: [
	          { required: true, message: "请选择收货仓库", trigger: "change" }
	        ],
	        deductMoney: [
	        	{ required: true, message: "请选择收货仓库", trigger: "change" }
	        ],
	        payMoney: [
	        	{ required: true, message: "请选择收货仓库", trigger: "change" }
	        ],
	        actualPayMoney: [
	        	{ required: true, message: "请选择收货仓库", trigger: "change" }
	        ],
	        deliveryDate: [
	        	{ required: true, message: "请选择收货仓库", trigger: "change" }
	        ],
	        buyer: [
	        	{ required: true, message: "请选择收货仓库", trigger: "change" }
	        ],
      	},
      	labelPosition: "right",
      	gongoption: [], //供应商下拉列表
			}
		}
	}
</script>
<style scoped lang="scss">
	.header {
	  width: 100%;
	  height: 38px;
	  font-size: 14px;
	  display: flex;
	  justify-content: space-between;
	  align-items: center;
	  background: #f3f3f3;
	 .headleft {
			margin-left: 10px;
	  }
	}
	.el-form {
	  display: flex;
	  flex-direction: row;
	  flex-wrap: wrap;
	  .el-form-item {
	    width: 33%;
	  }
	}
	.el-select {
	  width: 100%;
	}
</style>