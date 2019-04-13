<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <h3>{{ name21 }}</h3>
  </div>
</template>

<script>
// import api from '../../api';
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      name21: ''
    }
  },
  mounted () {
      api.getMock().then((res)=>{
          console.log(res)
          this.name21 = res.name21
      });
      api.getState().then((res) => {
          console.log(res)
      })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
