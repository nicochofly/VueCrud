<template>


  <el-row type="flex" justify="space-around" :class="isFocus?'inputDeepFocus':'inputDeep'">


    <el-col :span="4" style="height: 100%">
      <h2 class="el_menu_filter_title">Filter By</h2>
      <el-menu style="height: 100%;"
               default-active="2"
               @open="handleOpen"
               @close="handleClose"
      >
        <el-submenu index="1">
          <template slot="title">
            <!--            <i class="el-icon-location"></i>-->
            <span>Collection</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1">All</el-menu-item>
            <el-menu-item index="1-2">T-Shirt</el-menu-item>
            <el-menu-item index="1-3">BodySuit</el-menu-item>
          </el-menu-item-group>
          <!--          <el-menu-item-group title="分组2">
                      <el-menu-item index="1-3">选项3</el-menu-item>
                    </el-menu-item-group>
                    <el-submenu index="1-4">
                      <template slot="title">选项4</template>
                      <el-menu-item index="1-4-1">选项1</el-menu-item>
                    </el-submenu>-->
        </el-submenu>


        <el-submenu index="2">
          <template slot="title">
            <!--            <i class="el-icon-location"></i>-->
            <span>Price</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="2-1">
              <el-slider
                  v-model="value"
                  range
                  step="0.5"
                  show-stops
                  :max="50.0"
                  style="color: #2c3e50;">
              </el-slider>
            </el-menu-item>
          </el-menu-item-group>

        </el-submenu>


        <el-submenu index="3">
          <template slot="title">
            <!--            <i class="el-icon-location"></i>-->
            <span>Color</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="3-1">

              <el-row>

                <el-button circle v-for="item in colorList" v-bind:key="item.name"
                           :style="{backgroundColor:item.color_value,marginLeft:'10px'}  "/>
              </el-row>


            </el-menu-item>
          </el-menu-item-group>

        </el-submenu>

        <el-submenu index="4">
          <template slot="title">
            <!--            <i class="el-icon-location"></i>-->
            <span>Size</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="4-1">
              <el-row v-for="item in sizeList" v-bind:key="item.name">
                <el-checkbox style="margin: 0px">{{ item.name }}</el-checkbox>
              </el-row>
            </el-menu-item>
          </el-menu-item-group>

        </el-submenu>

      </el-menu>
    </el-col>


    <el-col :span="20" style="height: 100%">
      hahahaha
    </el-col>
  </el-row>


</template>

<script>
export default {
  name: "melemo_all",
  data() {
    return {
      value: [10.0, 50.0],
      colorList: [{name: 'Blue', color_value: '#f00'}, {name: 'Cream', color_value: '#0f0'}, {
        name: 'Green',
        color_value: ''
      }, {name: 'Light Pink', color_value: '#00f'}, {name: 'Peach', color_value: '#000'}, {
        name: 'Yellow',
        color_value: '#ff0'
      }, {name: 'White', color_value: '#fff'}],
      sizeList: [{name: "0-3 Months", value: 0}, {name: "3-6 Months", value: 0}, {
        name: "6-12 Months",
        value: 0
      }, {name: "12-18 Months", value: 0}, {name: "18-24 Months", value: 0}, {
        name: "2 years",
        value: 0
      }, {name: "3 years", value: 0}]
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style>

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
  /*background-image: "el-icon-plus";*/
}


.el_menu_filter_title {
  font-family: Patrick Hand;
  color: #2c3e50;
}

/*
.el-submenu__title >
.el-submenu__icon-arrow {
  right: 220px;
  margin-top: -5px;
}

.el-icon-arrow-down {
  right: 220px;
  margin-top: -5px;
}

.el-icon-arrow-down:before {
  content: '\e791';
}
.el-icon-arrow-down:after{

}*/


@font-face {
  font-family: Patrick Hand;
  src: url("https://fonts.gstatic.com/s/patrickhand/v14/LDI1apSQOAYtSuYWp8ZhfYe8XsLL.woff2") format("woff2");
  font-style: normal;
  font-display: swap;
}


/*菜单关闭*/
.el-submenu > .el-submenu__title .el-submenu__icon-arrow {
  -webkit-transform: rotateZ(0deg);
  -ms-transform: rotate(0deg);
  transform: rotateZ(0deg);
}

/*菜单展开*/
.el-submenu.is-opened > .el-submenu__title .el-submenu__icon-arrow {
  -webkit-transform: rotateZ(90deg);
  -ms-transform: rotate(90deg);
  transform: rotateZ(90deg);
}
</style>
