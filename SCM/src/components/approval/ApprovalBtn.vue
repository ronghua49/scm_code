<template>
  <div>
      <el-row>
        <el-button  icon="el-icon-error" :size="size" type="warning" @click="dialogVisible = true,operation = false">驳回</el-button>
        <el-button  icon="el-icon-success" :size="size" type="primary" @click="dialogVisible = true,operation = true">同意</el-button>
      </el-row>
      <el-dialog :title="operation?'同意':'驳回'" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false">
        <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size">
          <el-form-item label="审批意见" prop="opinion">
            <el-input v-model="dataForm.opinion" auto-complete="off" type="textarea" :autosize="{ minRows: 5, maxRows: 10}"></el-input>
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
import storage from '@/utils/storage'
export default {
  name: 'approvalbtn',
  props:{
      taskId:{
      	type:String,
      	required:true,
      }
  },
  data () {
    return {
      size: 'mini',
      dataForm:{
        opinion:''
      },
      operation: true,
      ownerId:'',
      editLoading: false,
      dialogVisible: false,
      dataFormRules: {
        opinion: [
          { required: true, message: "请输入意见", trigger: "blur" },
        ],
      },
    }
  },
  mounted(){
    this.ownerId = storage.get('ownerId')
  },
  methods:{
    submitForm(){
      this.$refs.dataForm.validate(valid => {
        if(valid){
          this.editLoading = true;
          this.$api.approval
          .post({
            url: "/runtime/task/v1/complete",
            data: {
              actionName:this.operation?'agree':'reject',
              formType: "frame",
              opinion:this.dataForm.opinion,
              taskId: this.taskId,
              account: storage.get('userName'),
            }
          })
          .then(res => {
            if (res.state == false) {
              this.$message({
                message: res.message,
                type: "error",
                duration: 800
              });
              this.editLoading = false;
              return;
            };
            this.$message({
              message:'任务办理成功',
              type: 'success',
              duration: 800
            });
            this.$emit('handleSuccess')
            this.$refs.dataForm.resetFields();
            this.dialogVisible = false;
            this.editLoading = false;
          })
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
    
</style>
