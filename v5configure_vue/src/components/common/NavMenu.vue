<template>
  <el-menu
      :default-active="'/index'"
      router
      mode="horizontal"
      background-color="white"
      text-color="#222"
      active-text-color="red"
      style="min-width: 1300px"
      display="table"
  >
    <el-menu-item @select="handleSelect" v-for="(item,i) in navList" :key="i" :index="item.name">
      {{ item.navItem  }}

    </el-menu-item>
    <el-input
        placeholder="快速搜索..."
        prefix-icon="el-icon-search"
        size="medium"

        style="width: 300px;position:absolute;margin-top: 12px;right: 5% ;"
        v-model="keywords">
    </el-input>
    <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">V5 Configure For Mobile Platform</span>
  </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  data () {
    return {
      navList: [
        {name: '/index', navItem: '首页' },
        // {name: '/list', navItem: '列表' },
        {name: '/info', navItem: '详情'},
        {name: '/library', navItem: '测试'}
      ]
    }
  },
  methods:{
    handleSelect(key, keyPath) {
      console.log("~~"+key+"~~", keyPath);
    }
  }
}
</script>

<style scoped>


span {
  pointer-events: none;
}
</style>

