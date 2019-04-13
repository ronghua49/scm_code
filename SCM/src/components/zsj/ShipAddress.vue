<template>
    <!-- <div>{{msg}}</div> -->
    <div>
      <el-row v-if="showOperation" class="btnAdd">
        <el-col :xs="2" :sm="2" :md="2" :lg="2">
          <el-button icon="el-icon-circle-plus-outline" type="primary" size="small" @click="handleAdd">新增</el-button>
        </el-col>
      </el-row>
      
      <el-table ref="scmTable" :data="showOperation?pageResult.rows:list" :highlight-current-row="true"  @select="handleSelect" @select-all="handleSelectAll"
         v-loading="loading"   element-loading-text="加载中" :border="true" :stripe="true"
          show-overflow-tooltip=true max-height="500" :size="size" align="center" style="width:100%;" >
      <el-table-column v-if="showOperation" type="selection" width="40"></el-table-column>
      <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
      <el-table-column v-for="column in columns" header-align="center" align="center"
        :prop="column.prop" :label="column.label" :width="column.width" :min-width="column.minWidth" 
        :fixed="column.fixed" :key="column.prop" :type="column.type" :formatter="column.formatter"
        :sortable="column.sortable==null?true:column.sortable">
      </el-table-column>
      <el-table-column v-if="showOperation" label="操作" width="200" fixed="right" header-align="center" align="center">
        <template slot-scope="scope">
          <el-button icon="el-icon-edit" label="编辑"  :size="size" type="primary"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button icon="el-icon-delete" label="删除"  :size="size" type="danger"  @click="handleDelete(scope.$index, scope.row)" >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

      <!--新增编辑界面-->
    <el-dialog :title="operation?'新增':'编辑'" width="60%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="70px" :rules="dataFormRules" ref="dataForm" :size="size">
        <el-form-item label="ID" prop="id" v-if="false">
          <el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系人" prop="contactPersion">
          <el-input v-model="dataForm.contactPersion" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="dataForm.phone" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="headPhone">
          <el-input v-model="dataForm.headPhone" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="省" prop="province">
          <el-input v-model="dataForm.province" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="市" prop="city">
          <el-input v-model="dataForm.city" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="县/区" prop="district">
          <el-input v-model="dataForm.district" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="街道" prop="street">
          <el-input v-model="dataForm.street" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮编" prop="postcode">
          <el-input v-model="dataForm.postcode" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
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
  name: "shipaddress", 
  components: {
    "scm-table": ScmTable
  },
  props:{
    list:Array,
    showOperation:{
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      size: "small",
      columns: [
        { prop: "contactPersion", label: "联系人", minWidth: 120 },
        { prop: "phone", label: "电话", minWidth: 120 },
        { prop: "headPhone", label: "手机", minWidth: 120 },
        { prop: "province", label: "省", minWidth: 120 },
        { prop: "city", label: "市", minWidth: 120 },
        { prop: "district", label: "县/区", minWidth: 120 },
        { prop: "street", label: "街道", minWidth: 120 },
        { prop: "postcode", label: "邮编", minWidth: 120 },
        { prop: "address", label: "地址", minWidth: 120 },
      ],
      pageRequest: {
        page: 1,
        pageSize: 10,
        showTotal: true
      },
      pageResult: {},
      operation: false, // true:新增, false:编辑
      dialogVisible: false, // 新增编辑界面是否显示
      loading: false,
      editLoading: false,
      dataFormRules: {
        contactPersion: [
          { required: true, message: "请输入联系人", trigger: "blur" },
          { max: 10, message: '长度不能超过10', trigger: "blur"}
        ],
        phone: [
          { required: true, message: "请输入电话号码", trigger: "blur" },
          { max: 10, message: '长度不能超过10', trigger: "blur"}
        ],
        headPhone: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          { max: 11, message: '长度不能超过11', trigger: "blur"}
        ],
        province: [
          { required: true, message: "请输入省", trigger: "blur" },
          { max: 10, message: '长度不能超过10', trigger: "blur"}
        ],
        city: [
          { required: true, message: "请输入市", trigger: "blur" },
          { max: 10, message: '长度不能超过10', trigger: "blur"}
        ],
        district: [
          { required: true, message: "请输入县/区", trigger: "blur" },
          { max: 10, message: '长度不能超过10', trigger: "blur"}
        ],
        street: [
          { required: true, message: "请输入街道", trigger: "blur" },
          { max: 10, message: '长度不能超过10', trigger: "blur"}
        ],
        postcode: [
          { required: true, message: "请输入邮编", trigger: "blur" },
          { max: 6, message: '长度不能超过6', trigger: "blur"}
        ],
        address: [
          { required: true, message: "请输入地址", trigger: "blur" },
          { max: 50, message: '长度不能超过50', trigger: "blur"}
        ],
      },
      // 新增编辑界面数据
      dataForm: {
        address: "",
        city: "",
        district: "",
        headPhone: "",
        // isDufault: "",
        phone: "",
        postcode: "",
        province: "",
        street: "",
        contactPersion: "",
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
    handleAdd: function() {
      this.dialogVisible = true;
      this.operation = true;
      if (this.$refs["dataForm"]!==undefined) {
          this.$refs["dataForm"].resetFields();
      }
      this.dataForm = {
        address: "",
        city: "",
        district: "",
        headPhone: "",
        isDufault: 0,
        phone: "",
        postcode: "",
        province: "",
        street: "",
        contactPersion: "",
        id: ''
      };
    },
    // 显示编辑界面
    handleEdit: function(index, row) {
      this.dialogVisible = true;
      this.operation = false;
      this.dataForm = Object.assign({}, {
        address: row.address,
        id: row.id?row.id:'',
        city: row.city,
        district: row.district,
        headPhone: row.headPhone,
        phone: row.phone,
        postcode: row.postcode,
        province: row.province,
        street: row.street,
        contactPersion: row.contactPersion,
        index: index
      });
    },
    //删除
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
          this.$emit('getShipAddress',{allData: res.rows})
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
          i.isDufault = 0;
        };
        row.isDufault = 1;
        this.$emit('getShipAddress',{allData: res.rows})
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
              this.$emit('getShipAddress',{allData: res.rows})
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
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.btnAdd {
  padding-bottom: 5px;
}
.paginer {
  margin-top: 5px;
}
.el-form{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  .el-form-item{
    width: 33%;
  }
}
</style>
