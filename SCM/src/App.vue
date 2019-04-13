<template>
  <div id="app">
    <!-- <img src="./assets/logo.png"> -->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
}
*{
  margin: 0;
  padding: 0;
}

.business-accept-status-apply-addnew-dialog.el-dialog .el-dialog__header{
    padding-top:10px;
}
.business-accept-status-apply-addnew-dialog.el-dialog .el-dialog__headerbtn{
    top: 10px;
}
.business-accept-status-apply-addnew-dialog.el-dialog .el-dialog__body{
    padding: 10px;
}

.txmessage-form{
    width: 50%;
    text-align: left;
    margin: auto;
}
.txmessage-form .el-form-item label{
    padding:0;
    font-weight: bold;
}
</style>
