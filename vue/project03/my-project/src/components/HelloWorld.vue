<template>
  <div class="hello">
        <h1>{{ msg }}</h1>
        <el-progress type="circle" :percentage="0"/>
        <el-progress type="circle" :percentage="25" />
        <el-progress type="circle" :percentage="100" status="success" />
        <el-progress type="circle" :percentage="25" status="exception" />
    </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
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
