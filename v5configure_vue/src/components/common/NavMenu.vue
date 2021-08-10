<template>
  <el-menu
      :default-active="'/index'"
      router
      mode="horizontal"
      background-color="white"
      text-color="#222"
      active-text-color="red"

      style="width: 800px"
      display="table"
  >
    <el-menu-item @select="handleSelect" v-for="(item,i) in navList" :key="i" :index="item.name" class="fontClass">
      <!--      {{ item.navItem  }}-->
      <img :src="item.navItem"/>
    </el-menu-item>
    <!--    <el-input
            placeholder="快速搜索..."
            prefix-icon="el-icon-search"
            size="medium"
            style="width: 300px;position:absolute;margin-top: 12px;right: 5% ;"
            v-model="keywords">
        </el-input>
        <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">大屏推送管理平台</span>-->
  </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  data() {
    return {
      navList: [
        /*        {name: '/index', navItem: '设备列表' },
                {name: '/devicegroups',navItem: '设备分组'},
                {name: '/contents', navItem: '内容列表' },
                {name: '/category', navItem: '分类列表' },
                // {name: '/info', navItem: '推送记录'},
                // {name: '/library', navItem: '测试'}
                {name: '/book', navItem: '测试'}*/
/*        {name: '/index', navItem: 'DeviceList'},
        {name: '/devicegroups', navItem: 'DeviceGroup'},
        {name: '/contents', navItem: 'ContentList'},
        {name: '/category', navItem: 'CategoryList'},
        // {name: '/info', navItem: '推送记录'},
        // {name: '/library', navItem: '测试'}
        {name: '/book', navItem: 'Test'}*/
        {name: '/index', navItem: require("@/assets/icon.png")},
        {name: '/devicegroups', navItem: require("@/assets/icon.png")},
        {name: '/contents', navItem: require("@/assets/icon.png")},
        {name: '/category', navItem: require("@/assets/icon.png")},
        // {name: '/info', navItem: '推送记录'},
        // {name: '/library', navItem: '测试'}
        {name: '/book', navItem: require("@/assets/icon.png")}





      ]
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log("~~" + key + "~~", keyPath);
    }
  }
}
</script>

<style scoped>
.fontClass {
  font-size: 16px;
  font-family: FangSong;
  /*font-family: Times, "Times New Roman", Georgia, serif;*/
  /*font-family: Verdana, Arial, Helvetica, sans-serif;*/
  /*font-family: "Lucida Console", Courier, monospace;*/
}

.nav_menu {
  horiz-align: center;
  min-width: 1300px;
}

.el-menu--horizontal > .el-menu-item {
  /*width: 50%;*/
  border-bottom: none;
  /*height: 1rem;*/
  /*line-height: 0.5rem;*/
  /*text-align: center;*/
}

.el-menu--horizontal > .el-menu-item.is-active {
  border-bottom: none;
}

span {
  pointer-events: none;
}
</style>

