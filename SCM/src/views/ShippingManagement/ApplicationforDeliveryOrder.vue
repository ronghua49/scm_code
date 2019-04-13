<template>
	<div>
		<!--头部-->
		<div class="head">
			<el-container>
				<el-header>发货指令申请</el-header>
				<el-main>
					<el-steps :active="2" align-center>
						<el-step title="" description="选择发货任务" process-status="wait"></el-step>
						<el-step title="" description="添加发货明细"></el-step>
						<el-step title="" description="完成"></el-step>
					</el-steps>
				</el-main>
			</el-container>
		</div>
		<!--主题部分-->
		<div class="zt">
			<el-container>
				<el-header>
					<i class="el-icon-search"></i>选择发货任务
				</el-header>
				<el-main>
					<!--<div class="left">
						<el-select v-model="value4" clearable placeholder="请选择">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
							</el-option>
						</el-select>
					</div>-->
				</el-main>
			</el-container>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				list: []
			}
		},
		mounted() {
//			var that = this;
			this.$axios.post('/scm/scmFhFocusShipmentsSet/v1/saves').then(function(res) {
				console.log(res); 			
//				that.list = res.data.group;
			})
	}, methods: {

	}, components: {

	}
	}
</script>

<style scoped lang="scss">
	.el-step__icon {
		width: 50%;
	}
	.main-content {
		padding: 0;
		height: 100px;
	}
	.el-step {
		margin-top: 20px;
	}
	.el-header,
	.el-footer {
		background-color: #F3F3F3;
		color: #333;
		text-align: center;
		line-height: 60px;
	}
	.el-step__main {
		margin-top: 20px;
	}
	.main-content {
		padding: 0px;
	}
	.el-main {
		height: 200px;
	}
	.el-container {
		margin-top: -9px;
		;
		border: 1px #ccc solid;
	}
	.el-steps {
		margin-top: 60px;
	}
	
	.el-step {
		margin-top: 20px;
	}
	
	.zt .el-header {
		text-align: left;
	}
	
	.zt {
		margin-top: 20px;
	}
	.el-header {
		border: 1px #CCCCCC solid;
	}
</style>