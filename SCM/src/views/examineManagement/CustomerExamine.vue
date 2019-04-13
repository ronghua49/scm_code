<template>
    <div class="exam">{{msg}}</div>
</template>

<script>
export default {
  name: 'customerExamine',
  data () {
    return {
      msg: '客户首营审批'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
    .exam{
      height: 1000px;
    }
</style>
