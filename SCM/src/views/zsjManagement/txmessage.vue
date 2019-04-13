<template>
	<div>
		<div class="content"> 
			<el-form class="txmessage-form" :model="wrapForm" :rules="wraprules" ref="wrapForm1" :label-position="labelPosition" size="small">
			  <el-form-item prop="jfdwname" label="甲方单位(销货方)">
			    <el-input v-model="wrapForm.jfdwname"></el-input>
			  </el-form-item>
			  <el-form-item prop="addressName" label="地址">
			     <el-input type="textarea" v-model="wrapForm.addressName"></el-input>
			  </el-form-item>
			  <el-form-item prop="khhname" label="开户行">
			    <el-input v-model="wrapForm.khhname"></el-input>
			  </el-form-item>
			  <el-form-item prop="zhname" label="账号">
			    <el-input v-model="wrapForm.zhname"></el-input>
			  </el-form-item>
			  <el-form-item prop="shname" label="税号">
			    <el-input v-model="wrapForm.shname"></el-input>
			  </el-form-item>
			  <el-form-item prop="phone" label="电话">
			    <el-input v-model="wrapForm.phone"></el-input>
			  </el-form-item>
			  <el-form-item prop="yzbmname" label="邮政编码">
			    <el-input v-model="wrapForm.yzbmname"></el-input>
			  </el-form-item>
			  <el-form-item style="text-align:center;">
				    <el-button type="primary" @click="save">保存</el-button>
			  </el-form-item>
			</el-form>
		</div>
	</div>
</template>
<script>
	import storage from '@/utils/storage'
	export default{
		name:'txmessage',
		data(){
			return{
				labelPosition: 'top',
				ownerId:'',
		        wrapForm: {
		        jfdwname: '',
		        addressName: '',
		        khhname: '',
		        zhname:'',
		        shname:'',
		        phone:'',
		        yzbmname:'',
		        },
		        wraprules:{
			        jfdwname: [
			        	{required: true, message: '请输入甲方单位(销货方)', trigger: 'blur' },
			            {max: 20, message: '最多输入20个字符', trigger: 'blur' }
		        	],
			        addressName: [
			        	{required: true, message: '请输入地址', trigger: 'blur' },
			            {max: 50, message: '最多输入20个字符', trigger: 'blur' }
		        	],
			        khhname: [
			        	{required: true, message: '请输入开户行', trigger: 'blur' },
			            {max: 20, message: '最多输入20个字符', trigger: 'blur' }
		        	],
			        zhname:[
			        	{required: true, message: '请输入账号', trigger: 'blur' },
			            {max: 20, message: '最多输入20个字符', trigger: 'blur' }
		        	],
			        shname:[
			        	{required: true, message: '请输入税号', trigger: 'blur' },
			            {max: 20, message: '最多输入20个字符', trigger: 'blur' }
		        	],
			        phone:[
			        	{required: true, message: '请输入电话', trigger: 'blur' },
			            {max: 20, message: '最多输入20个字符', trigger: 'blur' }
		        	],
			        yzbmname:[
			        	{required: true, message: '请输入邮政编码', trigger: 'blur' },
			            {max: 20, message: '最多输入20个字符', trigger: 'blur' }
		        	],

		        }
		        
			}
		},
		mounted(){
			 this.ownerId = storage.get('ownerId')
			 this.$api.zsj.changeState({
					            url: "/scm/scmZsjTXCompany/v1/getByOwnerId/"+this.ownerId,
				        }).then(res => {
				            if (res) {
				            	this.wrapForm.addressName=res.value.address
								this.wrapForm.zhname=res.value.bankCode
								this.wrapForm.jfdwname=res.value.name
								this.wrapForm.khhname=res.value.openBank
								this.wrapForm.shname=res.value.dutyParagraph
								this.wrapForm.phone=res.value.phone
								this.wrapForm.yzbmname=res.value.postcode
				              } else {
				              this.$message({
				                  message: "操作失败, " + res.shortMessage,
				                  type: "error",
				                  duration: 800
				              });
				            }
				    });
		},
		methods:{
			resetForm(formName) {
		        this.$refs[formName].resetFields();
		     },
			save(){
				var searchData={
					"address":this.wrapForm.addressName,
					"bankCode":this.wrapForm.zhname,
					"name":this.wrapForm.jfdwname,
					"openBank":this.wrapForm.khhname,
					"dutyParagraph":this.wrapForm.shname,
					"phone":this.wrapForm.phone,
					"postcode":this.wrapForm.yzbmname,
					"ownerId":this.ownerId,
				}
				this.$refs.wrapForm1.validate(valid => {
		     		if(valid){
		     			 this.$api.zsj.getZsj({
					            url: "/scm/scmZsjTXCompany/v1/save",
					            data:searchData,
					        }).then(res => {
					            if (res) {
					            	this.$message({
					                  message: "保存成功！",
					                  type: "success",
					                  duration: 800
					              });
					        		this.searchData={}
					              } else {
					              this.$message({
					                  message: "操作失败, " + res.shortMessage,
					                  type: "error",
					                  duration: 800
					              });
					            }
					    });
		     		}
		     	})
			}
		},
	}
</script>

<style scoped="scoped">
	.content{
		width: 60%;
		margin: 0 auto;
		margin-top: 30px;
	}
</style>