<template>
    <div>
      <!-- <div>{{msg}}</div> -->
      <transition name="el-fade-in">
      <div>
        <el-row class="btnAdd">
          <el-col :xs="2" :sm="2" :md="2" :lg="2">
            <el-button icon="el-icon-circle-plus-outline" type="primary" size="small" @click="handleAdd">新增</el-button>
          </el-col>
          <!-- <el-col :span="21" style="text-align:right;">
              <el-button type="primary" size="mini" @click="showFunctionType">职能类型字典列表</el-button>
          </el-col> -->
        </el-row>
        <!-- useClickRow属性改为false 取消点击行事件 -->
        <!-- 删除handleClickRow事件处理函数 @handleClickRow="handleClickRow" -->
        <scm-table :highlightCurrentRow="true" :stripe="true" :border="true" :useClickRow="false"
          :data="pageResult" :columns="columns" :showBatchDelete="false" 
          @findPage="findPage" @handleEdit="handleEdit"  @handleDelete="handleDelete">
        </scm-table>
        <!-- <div class="forSplit">职能类型字典列表</div> 
        <function-type :devision="devision"></function-type> -->
        <!--新增编辑界面-->
        <el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false">
          <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size">
            <el-form-item label="ID" prop="id" v-if="false">
              <el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="商业级别" prop="commerceLevel">
              <el-input v-model="dataForm.commerceLevel" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="编码" prop="code">
              <el-input v-model="dataForm.code" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="备注 " prop="memo">
              <el-input v-model="dataForm.memo" auto-complete="off" type="textarea"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button :size="size" @click.native="dialogVisible = false">取消</el-button>
            <el-button :size="size" type="primary" @click.native="submitForm" :loading="editLoading">确定</el-button>
          </div>
        </el-dialog>
      </div>
      </transition>
      <!-- 职能类型字典列表 -->
      <!-- <transition name="el-fade-in">
      
        <div v-show="!isIndex">
            <el-row class="function-type-btn-group">
              <el-col :xs="2" :sm="2" :md="2" :lg="2">
                <el-button icon="el-icon-back" :size="size" type="primary" @click="back">返回</el-button>
              </el-col>
              <el-dropdown @command="handleCommand">
                <el-button type="primary">
                  {{devisionName}}<i class="el-icon-arrow-down el-icon--right"></i>
                </el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item v-for="(item, index) in dropdownList" :key="index" :command="index">{{item.commerceLevel}}</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-row>
            
            
            <function-type :devision="devision"></function-type>
        </div>
      </transition> -->

    </div>
</template>

<script>
import ScmTable from "@/components/table/Scmtable";
import FunctionType from "./FunctionType";
export default {
  name: "commercelevel",
  components: {
    "scm-table": ScmTable,
    "function-type": FunctionType
  },
  data() {
    return {
      size: "small",
      columns: [
        { prop: "commerceLevel", label: "商业级别", minWidth: 120 },
        { prop: "code", label: "编码", minWidth: 120 },
        { prop: "createPersion", label: "创建人", minWidth: 120 },
        { prop: "createDate", label: "创建时间", minWidth: 120, },
        { prop: "memo", label: "备注", minWidth: 120 },
      ],
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      pageResult: {},
      operation: false, // true:新增, false:编辑
      dialogVisible: false, // 新增编辑界面是否显示
      editLoading: false,
      dataFormRules: {
        commerceLevel: [
          { required: true, message: "请输入商业级别", trigger: "blur" },
          { max: 20, message: '长度不能超过20', trigger: "blur"}
        ],
        code: [
          { required: true, message: "请输入编码", trigger: "blur"},
          { max: 20, message: '长度不能超过20', trigger: "blur"}
        ],
        memo: [
          { required: false, message: "请输入备注", trigger: "blur"},
          { max: 200, message: '长度不能超过200', trigger: "blur"}
        ]
      },
      // 新增编辑界面数据
      dataForm: {
        commerceLevel: "",
        memo: "",
        id:''
      },
      menuData: [],
      menuSelections: [],
      menuLoading: false,
      authLoading: false,
      checkAll: false,
      // devision:'',
      // devisionName: '',
      // isIndex: true,
      // dropdownList: []
    };
  },
  mounted() {
  },
  methods: {
    // handleCommand(command) {
    //   //this.$message('click on item ' + command);
    //   this.devision = this.dropdownList[parseInt(command)]['id'];
    //   this.devisionName = this.dropdownList[parseInt(command)]['commerceLevel'];
    // },
    // back(){
    //   this.isIndex = true;
    //   // this.clearForm();
    // },
    handleAdd: function() {
      this.dialogVisible = true;
      this.operation = true;
      if (this.$refs["dataForm"]!==undefined) {
          this.$refs["dataForm"].resetFields();
      }
      this.dataForm = {
        commerceLevel: "",
        memo: ""
      };
    },
    //显示functionType
    // showFunctionType: function() {
    //   this.isIndex = false;
    //   this.dropdownList = this.pageResult.rows;
    //   this.devision = this.dropdownList[0]['id'];
    //   this.devisionName = this.dropdownList[0]['commerceLevel'];
    //   console.log(this.dropdownList);
    // },
    // 取消handleClickRow事件处理函数
    // handleClickRow(params){
    //   this.devision = params.division
    // },
    // 显示编辑界面
    handleEdit: function(params) {
      console.log(params)
      this.dialogVisible = true;
      this.operation = false;
      this.dataForm = Object.assign({}, {
        code: params.row.code,
        commerceLevel: params.row.commerceLevel,
        memo: params.row.memo,
        id: params.row.id
      });
    },
    //删除
    handleDelete (data){
      console.log(data);
      if(data.params.length == 1 &&  data.params[0] != ""){
        this.$api.dict.delDict({
          url: '/scm/scmZdCommerceLevel/v1/remove/'+ data.params[0]
        }).then(data.callback)
      }else{
        if(data.params.length>1){
          let ids = ''
          for(let i= 0; i < data.params.length ; i++){
            if(i == data.params.length-1){
              ids += 'ids='+data.params[i]
            }else{
              ids += 'ids='+data.params[i]+'&'
            }
          }
          this.$api.dict.delDictPl({
            url: '/scm/scmZdCommerceLevel/v1/removes?'+ids,
          }).then(data.callback)
        }else{
          return;
        }
      }
    },
    // 编辑
    submitForm: function() {
      this.$refs.dataForm.validate(valid => {
        if (valid) {
            this.editLoading = true;
            let params = Object.assign({}, this.dataForm);
            this.$api.dict.addDict({
              url: '/scm/scmZdCommerceLevel/v1/save',
              data: params
            }).then(res => {
              this.editLoading = false;
              if (res.state) {
                this.$message({ message: "操作成功", type: "success" ,duration: 800});
                this.dialogVisible = false;
                this.$refs["dataForm"].resetFields();
              } else {
                this.$message({
                  message: "操作失败, " + res.shortMessage,
                  type: "error",
                  duration: 800
                });
              }
               this.findPage(null);
            });
        }
      });
    },
    findPage(data) {
      if(data != null){
        this.pageRequest = data.pageRequest;  
      }
      this.$api.dict.getDict({
          url:'/scm/scmZdCommerceLevel/v1/list',
          data:{
            pageBean: this.pageRequest,
            params: {},
            querys: [],
            sorter: [
              {
                direction: "DESC",
                property: "createDate"
              }
            ]
          }
          
        }).then(res => {
          if (res.state == false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
            return;
          }
          this.pageResult = res;
          console.log(this.pageResult);
          // this.devision = res.rows.length>0?res.rows[0]['id']:''
        }).then(data!=null?data.callback:'')
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
.forSplit{
  margin-top: 25px;
  margin-bottom: 20px;
  height: 30px;
  line-height: 30px;
  text-align: left;
  font-size: 13px;
  padding-left: 5px;
  background: #f5f5f5;
}
// .function-type-btn-group{
//   text-align: center;
//   margin-bottom: 20px;
// }
</style>
