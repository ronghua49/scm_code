<template>
    <!-- <div>{{msg}}</div> -->
    <div>
      <el-row class="btnAdd">
        <el-col :xs="2" :sm="2" :md="2" :lg="2" style="text-align: left;margin-left: 5px;">
          <el-button icon="el-icon-circle-plus-outline" type="primary" size="mini" @click="handleAdd">新增</el-button>
        </el-col>
      </el-row>
    <el-table ref="scmTable" :data="pageResult.rows" :highlight-current-row="true"  @select="handleSelect" @select-all="handleSelectAll"
         v-loading="loading"   element-loading-text="加载中" :border="true" :stripe="true"
          :show-overflow-tooltip="true" max-height="500" :size="size" align="center" style="width:100%;" >
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
      <el-table-column v-for="column in columns" header-align="center" align="center"
        :prop="column.prop" :label="column.label" :width="column.width" :min-width="column.minWidth" 
        :fixed="column.fixed" :key="column.prop" :type="column.type" :formatter="column.formatter"
        :sortable="column.sortable==null?true:column.sortable">
      </el-table-column>
      <el-table-column label="操作" width="200" fixed="right" header-align="center" align="center">
        <template slot-scope="scope">
          <el-button icon="el-icon-edit" label="编辑"  :size="size" type="primary"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button icon="el-icon-delete" label="删除"  :size="size" type="danger"  @click="handleDelete(scope.$index, scope.row)" >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
      <!--新增编辑界面-->
    <el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="110px" :rules="dataFormRules" ref="dataForm" :size="size">
        <el-form-item label="ID" prop="id" v-if="false">
          <el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="生产/仓库地址" prop="address">
          <el-input v-model="dataForm.address" auto-complete="off"></el-input>
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
import storage from '@/utils/storage'
export default {
  name: "WarehouseList", 
  components: {
    "scm-table": ScmTable
  },
  props:{
    inputList:Array,//
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  watch:{
    'inputList':'watchList'
  },
  data() {
    return {
      size: "small",
      columns: [
        { prop: "address", label: "生产/仓库地址", minWidth: 120 },
      ],
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      loading: false,
      pageResult: {},
      operation: false, // true:新增, false:编辑
      dialogVisible: false, // 新增编辑界面是否显示
      editLoading: false,
      dataFormRules: {
        address: [
          { required: true, message: "请输入生产/仓库地址", trigger: "blur" },
          { max: 20, message: '长度不能超过20', trigger: "blur"}
        ],
      },
      // 新增编辑界面数据
      dataForm: {
        address: "",
      },
      menuData: [],
      menuSelections: [],
      menuLoading: false,
      authLoading: false,
      checkAll: false,
      ownerId:'',
    };
  },
  mounted() {
      this.findPage();
  },
  methods: {
    watchList(){
      if(this.inputList.length>0){
        if(this.inputList[0].address === ''){
          this.pageResult.rows = [];
        }else{
          this.pageResult.rows = this.inputList;
        }
      }else{
        this.pageResult.rows = [];
      }
    },
    handleAdd: function() {
      this.dialogVisible = true;
      this.operation = true;
      if (this.$refs["dataForm"]!==undefined) {
          this.$refs["dataForm"].resetFields();
      }
      this.dataForm = {
        address: "",
        id: "",
        isDefault: 0,
      };
    },
    // 显示编辑界面
    handleEdit: function(index, row) {
      console.log(row)
      this.dialogVisible = true;
      this.operation = false;
      this.dataForm = Object.assign({}, {
        address: row.address,
        id: row.id?row.id:'',
        index: index
      });
    },
    //勾选
    //删除
    handleDelete (indexdel, row){
      console.log(indexdel)
      this.$confirm('确认删除选中记录吗？', '提示', {
				type: 'warning'
			}).then(() => {
          let res = this.pageResult;
				  res.rows = res.rows.reduce((final, item, index, arr)=>{
            if(index == indexdel){
              arr.splice(index, 1)
            }
            final = arr;
            return final;
          },[])
          this.pageResult = res;
          this.$message({message: '删除成功', type: 'success',duration: 800})
          this.$emit('getAddress',{allData: res.rows})
			}).catch(() => {
			})
    },
    handleSelectAll(){
      this.$refs.scmTable.clearSelection();
    },
    handleSelect(selection, row){
        this.$refs.scmTable.clearSelection();
        this.$refs.scmTable.toggleRowSelection(row, true);
        let res = this.pageResult;
        for(let i of res.rows){
          i.isDefault = 0
        };
        row.isDefault = 1;
        this.$emit('getAddress',{allData: res.rows})
    },
    // 编辑
    submitForm: function() {
      this.$refs.dataForm.validate(valid => {
        if (valid) {
          this.editLoading = true;
          let params = Object.assign({}, this.dataForm);
          let addWareHouse = new Promise((resolve, reject)=>{
            let res = this.pageResult;
            if(!this.operation){
              res.rows = res.rows.reduce((final, item, index, arr)=>{
                if(index == params.index){
                  arr[index].address = params.address
                }
                final = arr;
                return final;
              },[])
              res.state = true;
              resolve(res)
            }else{
              res.rows.push(params);
              res.state = true;
              resolve(res)
            }
          })
            addWareHouse.then(res => {
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
              this.pageResult = res;
              this.$emit('getAddress',{allData: res.rows})
            });
        }
      });
    },
    findPage(data) {
      if(data != null){
        this.pageRequest = data.pageRequest;  
      }
      let findWareHouse = new Promise((resolve, reject)=>{
        let res = {
          page: 1,
          pageSize: 10,
          rows: [],
          total: 0
        }
        resolve(res)
      })
      findWareHouse.then(res => {
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
    }
  }
};
// clearSelection
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.btnAdd {
  padding-bottom: 5px;
}
.paginer {
  margin-top: 5px;
}
</style>
