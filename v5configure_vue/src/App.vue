<template>
  <div id="app">
    <router-view></router-view>
    <!--<img alt="Vue logo" src="./assets/logo.png">-->
<!--    <Login></Login>-->
  </div>
</template>

<script>

// import Login from './components/home/AppIndex.vue'

// import Login from "@/components/Login";

export default {
  name: 'App',


  // components: {
  //   // eslint-disable-next-line vue/no-unused-components
  //   Login
  // }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}
</style>
