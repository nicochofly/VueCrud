<template>
  <!--  <div>
    <h1 style="font-family: Patrick Hand;align-items: center;align-content: center;horiz-align: center">Melemo-Kids</h1>-->



    <el-menu
        :default-active="'/index'"
        router
        mode="horizontal"
        background-color="white"
        text-color="#222"
        active-text-color="red"
        style="width: auto"
        display="table"
    >
      <el-menu-item @select="handleSelect" v-for="(item,i) in navList" :key="i" :index="item.name" class="fontClass">
        {{ item.navItem }}
        <!--      <img :src="item.navItem"/>-->
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
        {name: '/index', navItem: 'DeviceList'},
        {name: '/devicegroups', navItem: 'DeviceGroup'},
        {name: '/contents', navItem: 'ContentList'},
        {name: '/category', navItem: 'CategoryList'},
        // {name: '/info', navItem: '推送记录'},
        // {name: '/library', navItem: '测试'}
        {name: '/melemohome', navItem: 'Test'}
        // {name: '/index', navItem: require("@/assets/icon.png")},
        // {name: '/devicegroups', navItem: require("@/assets/icon.png")},
        // {name: '/contents', navItem: require("@/assets/icon.png")},
        // {name: '/category', navItem: require("@/assets/icon.png")},
        // // {name: '/info', navItem: '推送记录'},
        // // {name: '/library', navItem: '测试'}
        // {name: '/melemohome', navItem: require("@/assets/icon.png")}


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
  font-size: 20px;
  font-family: Patrick Hand;
  /*//@font-face { font-family: "Patrick Hand"; src: url("https://fonts.gstatic.com/s/patrickhand/v14/LDI1apSQOAYtSuYWp8ZhfYe8XsLL.woff2") format("woff2"); font-style: normal; font-weight: 400; font-display: swap; unicode-range: U+0-FF, U+131, U+152-153, U+2BB-2BC, U+2C6, U+2DA, U+2DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD; }*/

  /*font-family: Times, "Times New Roman", Georgia, serif;*/
  /*font-family: Verdana, Arial, Helvetica, sans-serif;*/
  /*font-family: "Lucida Console", Courier, monospace;*/
}

.titleClass {
  font-size: 40px;
  font-family: Patrick Hand;
  /*//@font-face { font-family: "Patrick Hand"; src: url("https://fonts.gstatic.com/s/patrickhand/v14/LDI1apSQOAYtSuYWp8ZhfYe8XsLL.woff2") format("woff2"); font-style: normal; font-weight: 400; font-display: swap; unicode-range: U+0-FF, U+131, U+152-153, U+2BB-2BC, U+2C6, U+2DA, U+2DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD; }*/

  /*font-family: Times, "Times New Roman", Georgia, serif;*/
  /*font-family: Verdana, Arial, Helvetica, sans-serif;*/
  /*font-family: "Lucida Console", Courier, monospace;*/
}


@font-face {
  font-family: Patrick Hand;
  src: url("https://fonts.gstatic.com/s/patrickhand/v14/LDI1apSQOAYtSuYWp8ZhfYe8XsLL.woff2") format("woff2");
  font-style: normal;
  font-weight: 400;
  font-display: swap;
  /* src: url-loader("@/assets/iozzkhwu.ttf");  这种方式是错误的*/
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

