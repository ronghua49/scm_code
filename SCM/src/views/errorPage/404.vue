<template>
    <div class="main">
        <b class="err">{{msg}}</b>
    </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: '404 NOT FOUND'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
    .main{
        width: 100%;
        text-align: center;
        line-height: 100vh;
        .err{
            font-size: 100px;
        }
    }
</style>
