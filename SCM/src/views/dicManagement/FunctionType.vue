<template>
    <!-- <div>{{msg}}</div> -->
    <div>
      <el-row class="searchHeader">
        <el-col :span="2"><div>筛选查询</div></el-col>
        <!-- <el-col :span="22" style="text-align: right" >
          <el-button icon="el-icon-search" :size="size" type="primary" @click="inquire">查询</el-button>
        </el-col> -->
      </el-row>
      <el-row class="searchBox">
        <el-form label-width="140px" size="small">
          <el-form-item label="商业级别">
            <el-select v-model="devision" placeholder="请选择商业级别" clearable>
              <el-option v-for="(item, index) in dropdownList" :key="index" :label="item.commerceLevel" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </el-row>

      <el-row class="btnAdd">
        <el-col :xs="2" :sm="2" :md="2" :lg="2">
          <el-button icon="el-icon-circle-plus-outline" type="primary" size="small" @click="handleAdd">新增</el-button>
        </el-col>
        <!-- <el-dropdown @command="handleCommand">
          <el-button type="primary">
            {{devisionName}}<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-for="(item, index) in dropdownList" :key="index" :command="index">{{item.commerceLevel}}</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown> -->
      </el-row>
      
      <scm-table   :highlightCurrentRow="true" :stripe="true" :border="true"
        :data="pageResult" :columns="columns" :showBatchDelete="false" 
        @findPage="findPage" @handleEdit="handleEdit"  @handleDelete="handleDelete">
      </scm-table>

      <!--新增编辑界面-->
    <el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form class="addnew" :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size">
        <el-form-item label="ID" prop="id" v-if="false">
          <el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="职能类型" prop="functionType">
          <el-input v-model="dataForm.functionType" auto-complete="off"></el-input>
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
</template>

<script>
import ScmTable from "@/components/table/Scmtable";
export default {
  name: "functiontype",
  components: {
    "scm-table": ScmTable
  },
  //props:['devision'],
  data() {
    return {
      size: "small",
      columns: [
        { prop: "functionType", label: "职能类型", minWidth: 120 },
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
        functionType: [
          { required: true, message: "请输入职能类型", trigger: "blur" },
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
        functionType: "",
        memo: "",
        id:''
      },
      menuData: [],
      menuSelections: [],
      menuLoading: false,
      authLoading: false,
      checkAll: false,
      // 保存请求来的商业级别数据
      dropdownList: [],
      devisionName: '',
      devision: '',
    };
  },
  // 请求商业级别
  created() {
    // `this` 指向 vm 实例
    // console.log('a is: ' + this.a)
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
      console.log(res);
      this.dropdownList = res.rows;
      this.devisionName = res.rows.length > 0 ? res.rows[0].commerceLevel : '';
      this.devision = res.rows.length > 0 ? res.rows[0]['id'] : '';
    });//.then(data!=null?data.callback:'')
  },
  watch: {
    devision: 'findPage',
  },
  mounted() {
  },
  methods: {
    // handleCommand(command) {
    //   //this.$message('click on item ' + command);
    //   this.devision = this.dropdownList[parseInt(command)]['id'];
    //   this.devisionName = this.dropdownList[parseInt(command)]['commerceLevel'];
    // },
    handleAdd: function() {
      this.dialogVisible = true;
      this.operation = true;
      if (this.$refs["dataForm"]!==undefined) {
          this.$refs["dataForm"].resetFields();
      }
      this.dataForm = {
        functionType: "",
        memo: "",
      };
      this.dataForm.commerceLevelId = this.devision;
    },
    // 显示编辑界面
    handleEdit: function(params) {
      console.log(params)
      this.dialogVisible = true;
      this.operation = false;
      this.dataForm = Object.assign({}, {
        code: params.row.code,
        functionType: params.row.functionType,
        memo: params.row.memo,
        id: params.row.id,
        commerceLevelId: params.row.commerceLevelId
      });
    },
    //删除
    handleDelete (data){
      if(data.params.length == 1 &&  data.params[0] != ""){
        this.$api.dict.delDict({
          url: '/scm/scmZdFunctionType/v1/remove/'+ data.params[0]
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
            url: '/scm/scmZdFunctionType/v1/removes?'+ids,
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
              url: '/scm/scmZdFunctionType/v1/save',
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
      if(this.devision == ''){
        let handleFirst = new Promise((resolve, reject)=>{
          let res = {
            state: true
          }
          resolve(res)
        });
        handleFirst.then(data!=null?data.callback:'')
        return;
      };
      if(data != null && data.pageRequest){
        this.pageRequest = data.pageRequest;
      }
      this.$api.dict.getDict({
          url:'/scm/scmZdFunctionType/v1/list',
          data:{
            pageBean: this.pageRequest,
            params: {},
            querys: [{
              hasInitValue: false,
              operation: "EQUAL",
              property: "commerceLevelId",
              relation: "AND",
              value: this.devision
            }],
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
          
        }).then(data!=null?data.callback:'')
    },
    inquire(){
      //let searchForm = Object.assign({},this.searchForm);
      console.log(this.searchForm);
      //this.findPage(searchForm)
    },
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
.el-select {
  width: 100%;
}

.el-form{
  display: flex;
  flex-direction: row;
  // justify-content: space-around;
  flex-wrap: wrap;
  .el-form-item{
    width: 32%;
  }
}
.addnew{
  .el-form-item{
    width: 100%;
  }
}
.searchHeader{
  padding: 5px;
  background: #f3f3f3;
  div:first-child{
    height: 28px;
    line-height: 28px;
  }
  b{
    font-size: 24px;
  }
  i{
    margin-right: 5px;
    margin-left: 5px;
  }
}
.searchBox{
  padding-top: 10px;
  border: 1px solid #f4f4f4;
  margin-bottom: 10px;
}
.addContent{
  border: 1px solid #f4f4f4;
  border-radius: 3px;
  margin-bottom: 10px;
  .addHead{
    text-align: center;
    width: 100%;
    height: 50px;
    line-height: 50px;
    font-weight: 600;
  }
}
</style>
