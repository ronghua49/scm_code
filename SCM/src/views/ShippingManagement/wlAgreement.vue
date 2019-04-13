<template>
	<div>
		<el-container>
			<el-header>
				<div class="header">
					<div class="headleft"><span class="el-icon-search"></span><span class="sx">筛选查询</span><span class="el-icon-caret-bottom"></span></div>
					<div class="headright">
						<el-row>
							<el-button type="primary" class="el-icon-search" size="mini" @click="searchquery">查询</el-button>
						</el-row>
					</div>
				</div>
			</el-header>
			<el-main>
				<el-form :label-position="labelPosition" size="small" label-width="120px" class="demo-ruleForm">
					<el-form-item label="药品类型">
						<el-select v-model="ypid" placeholder="请选择药品类型" @change="getypname">
							<el-option v-for="item in ypoption" :key="item.id" :label="item.medicineType" :value="item.id">
							</el-option>
						</el-select>
					</el-form-item>
				</el-form>
			</el-main>
			<div class="header">
				<div class="headleft"><span class="el-icon-tickets"></span><span class="sx">数据列表</span><span class="el-icon-caret-bottom"></span></div>
				<div class="headright">
					<el-row>
						<el-button type="primary" icon="el-icon-circle-plus-outline" size="mini" @click="add">新增</el-button>
					</el-row>
				</div>
			</div>
			<!--table-->
			<el-table v-loading="loading2" element-loading-text="加载中" :data="tableData3" size="mini" border>
				<el-table-column v-for="item in column" :key="item.prop" :prop="item.prop" :label="item.label"
				 :align="item.align">
				</el-table-column>
				<el-table-column fixed="right" label="操作" align="center" width="180">
					<template slot-scope="scope">
						<el-button type="primary" size="small" class="el-icon-edit" @click="edit(scope.row)">编辑</el-button>
						<el-button type="primary" size="small" class="el-icon-delete" @click="del(scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
			<div class="pagination">
				<el-pagination @current-change="refreshPageRequest" @size-change="refreshPageSizeRequest" background :current-page="pageRequest.page"
				 :page-sizes="[10, 20, 30, 40, 50]" :page-size="pageRequest.pageSize" layout="sizes, total, prev, pager, next"
				 :total="total">
				</el-pagination>
			</div>

		</el-container>
		<!--弹框-->
		<el-dialog :title="this.operation?'物流协议新增':'物流协议编辑'" width="50%" :visible.sync="dialogTableVisible">
			<div>
				<el-container>
					<el-main>
						<el-form :model="ruleForm" :rules="rules" size="small" ref="ruleForm1" :label-position="labelPosition"
						 label-width="120px" class="demo-ruleForm">
						 <el-form-item prop="fhname" label="发货地" style="width:100%">
							<el-cascader
							  :options="fhoptions"
							  :show-all-levels="false"
							 placeholder="请选择发货地"
							 style="width:100%"
							 v-model="ruleForm.fhname"
							 clearable
							></el-cascader>
						 </el-form-item>
						  <el-form-item label="目的地" prop="mdname" style="width:100%">
							<el-cascader
							  :options="fhoptions"
							  :show-all-levels="false"
							 placeholder="请选择目的地"
							 v-model="ruleForm.mdname"
							 style="width:100%"
							  clearable
							></el-cascader>
						 </el-form-item>
						 <el-form-item label="最长到达天数" prop="dayNum" style="width:100%;">
							 <el-input placeholder="请输入天数" v-model="ruleForm.dayNum">
							    <template slot="append">天</template>
							 </el-input>
						 </el-form-item>
						 	 <el-form-item label="备注" prop="memo" style="width:100%;">
							 <el-input placeholder="请输入内容" v-model="ruleForm.memo" type="textarea">
							 </el-input>
						 </el-form-item>
						</el-form>
					</el-main>
					<el-header>
						<div class="header head">
							<div class="headleft"></div>
							<div class="headright tanFoot">
								<el-row>
									<el-button @click="qx" size="mini">取消</el-button>
								</el-row>
								<el-row class="savebox">
									<el-button type="primary" size="mini" @click="save">保存</el-button>
								</el-row>
							</div>
						</div>
					</el-header>
				</el-container>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	import storage from '@/utils/storage'
	export default {
		name: "wlAgreement",
		data() {
			return {
				column: [{
						prop: "shippedTo",
						label: "发货地",
						minwidth: "250",
						align: "center"
					},
					{
						prop: "destination",
						label: "目的地",
						minwidth: "250",
						align: "center"
					},
					{
						prop: "maxArrivalTime",
						label: "最长到达天数",
						minwidth: "100",
						align: "center"
					},
					{
						prop: "memo",
						label: "备注",
						minwidth: "100",
						align: "center"
					},
					{
						prop: "createPersion",
						label: "创建人",
						minwidth: "180",
						align: "center"
					},
					{
						prop: "createTime",
						label: "创建时间",
						minwidth: "150",
						align: "center"
					},
				],
				restaurants: [],
				labelPosition: "right",
				loading2: true,
				ownerId: '',
				total: 0,
				operation: true,
				dialogTableVisible: false,
				options: [],
				fhoptions:[{
		          value: 'zhinan',
		          label: '指南',
		          children: [{
		            value: 'shejiyuanze',
		            label: '设计原则',
		            children: [{
		              value: 'yizhi',
		              label: '一致'
		            }, {
		              value: 'fankui',
		              label: '反馈'
		            }, {
		              value: 'xiaolv',
		              label: '效率'
		            }, {
		              value: 'kekong',
		              label: '可控'
		            }]
		        }]}],
		        mdoptions:[{
		          value: 'zhinan',
		          label: '目的地',
		          children: [{
		            value: 'shejiyuanze',
		            label: '设计原则',
		            children: [{
		              value: 'yizhi',
		              label: '一致'
		            }, {
		              value: 'fankui',
		              label: '反馈'
		            }, {
		              value: 'xiaolv',
		              label: '效率'
		            }, {
		              value: 'kekong',
		              label: '可控'
		            }]
		        }]}],
				tableData3: [],
				checked: false,
				loading: false,
				addcpxlname: "",
				addjxname: "",
				addyplxname: "",
				addzctjname: "",
				addzsm: "0",
				editid: "",
				search: [],
				pageRequest: {
					page: 1,
					pageSize: 10,
					showTotal: true,
				},
				ruleForm: {
					fhname:'',
					mdname:'',
					dayNum:'',
					memo:'',
				},
				rules: {
					fhname: [{ required: true, message: '发货地不能为空', trigger: 'change' }],
					mdname: [{ required: true, message: '目的地不能为空', trigger: 'change' }],
					dayNum: [{ required: true, message: '请输入天数', trigger: 'blur' }],
					  memo: [{ required: true, message: '请输入备注', trigger: 'blur' },{max:100,message:"内容不能超过100个字符"}],
				}

			}
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
			getypname() {
				//获取药品类型的name
				if (this.ypoption.length != 0) {
					var data = this.ypoption.filter((item) => {
						if (item.id == this.ypid) {
							this.ypname = item.medicineType
							console.log(this.ypname)
						}
					})
				}
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			qx() {
				this.dialogTableVisible = false
				this.resetForm('ruleForm1')
			},
			add() {
				this.dialogTableVisible = true;
				this.operation = true;
			},
			save() {
				this.$refs.ruleForm1.validate(valid => {
					if (valid) {
						
						var information = {
							"shippedTo": this.ruleForm.fhname.join("-"), //发货地
							"destination": this.ruleForm.mdname.join("-"), //目的地
							"maxArrivalTime": this.ruleForm.dayNum, //
							"memo": this.ruleForm.memo, //备注
							'ownerId':this.ownerId,
							"id": this.editid,
						}
						this.$api.zsj.getZsj({
							url: "/scm/scmFhLogisticsAgreement/v1/save",
							data: information
						}).then(res => {
							console.log(res)
							if (res.state == false) {
								this.$message({
									message: "操作失败, " + res.shortMessage,
									type: "error",
									duration: 800
								});

							} else {
								this.$message({
									message: "保存成功",
									type: "success",
									duration: 800
								});
								this.resetForm('ruleForm1')
								information = {}
								this.dialogTableVisible = false
								this.findPage();
							}
						});
					}
				});
			},
			edit(rows) {
				this.dialogTableVisible = true;
				this.operation = false;
				this.ruleForm.fhname=rows.shippedTo //发货地
				this.ruleForm.mdname=rows.destination//目的地
				this.ruleForm.dayNum=rows.maxArrivalTime //
				this.ruleForm.memo=rows.memo //备注
				this.editid=rows.id
			},
			del(rows) {
				this.$confirm('确认删除选中记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.$api.approval.delscm({
							url: '/scm/scmFhLogisticsAgreement/v1/remove/' + rows.id
						})
						.then(res => {
							if(res.state == false) {
								this.$message({
									message: "操作失败, " + res.message,
									type: "error",
									duration: 800
								});
								return;
							}
							this.$message({
								message: res.message,
								type: 'success',
								duration: 800
							})
							this.findPage();
						}).catch( error => {
							console.log(error)
						})
				}).catch(() => {})
			},
			searchquery() {
				if (this.jyname) {
					this.search.push({
						property: "medicineClassify",
						value: this.jyname,
						operation: "LIKE",
						relation: "AND"
					})
				}
				if (this.ypname) {
					this.search.push({
						property: "ProductLineId",
						value: this.ypname,
						operation: "=",
						relation: "AND"
					})
				}
				if (this.wlname) {
					this.search.push({
						property: "productCode",
						value: this.wlname,
						operation: "LIKE",
						relation: "AND"
					})
				}
				this.findPage(this.search)
			},
			//商品列表
			findPage(search) {
				this.loading = true;
				this.$api.zsj.getZsj({
					url: "/scm/scmFhLogisticsAgreement/v1/list",
					data: {
						pageBean: this.pageRequest,
						params: {},
						querys: search,
						sorter: []
					},
				}).then(res => {
					if (res) {
						this.tableData3 = res.rows
						this.total = res.total
						this.loading2 = false
						this.search = []
					} else {
						this.$message({
							message: "操作失败, " + res.shortMessage,
							type: "error",
							duration: 800
						});
					}
				});
			}
		},
		mounted() {
			this.ownerId = storage.get('ownerId')
			this.findPage();
			//药品分类
			this.$api.zsj.getZsj({
				url: "/scm/scmZdMedicineClassify/v1/list",
				data: {},
			}).then(res => {
				if (res) {
					this.addypfloption = res.rows
					this.options = res.rows
				} else {
					this.$message({
						message: "操作失败, " + res.shortMessage,
						type: "error",
						duration: 800
					});
				}
			})
		},
	}
</script>
<style scoped="scoped" lang="scss">
	.header {
		width: 100%;
		height: 38px;
		font-size: 14px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		background: #F3F3F3;
	}

	.el-header {
		padding: 0 !important;
	}

	.tab-title {
		width: 100%;
		height: 38px;
		font-size: 14px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		background: #F3F3F3;
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

	.el-form {
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;

		.el-form-item {
			width: 33.3%;
		}
	}

	.el-table .cell {
		white-space: nowrap !important;
	}

	.el-main {
		padding: 0 !important;
	}

	header {
		height: 58px !important;
	}
</style>
