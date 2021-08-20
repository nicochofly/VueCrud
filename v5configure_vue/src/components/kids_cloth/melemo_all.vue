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
      <!--     <div class="grid_layout" v-for="item in dataList" :key="item.name">
             <img :src="item.src"/>
           </div>-->
      <!--      <el-row gutter="10px" >
              <el-col span="51985041" v-for="item in dataList" :key="item.name">
                <img :src="item.src">
              </el-col>
            </el-row>-->
      <div>
        <ul class="grid">
          <li v-for="item in dataList" :key="item.name">
            <a target="_blank"><img v-bind:src="item.src"/></a>
          </li>
        </ul>
      </div>
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
      }, {name: "3 years", value: 0}],
      dataList: [
        {
          name: "蓝底方格包屁服",
          src: require("@/assets/a1.webp"),
          price: 29.90
        },
        {
          name: "白底蓝点包屁服",
          src: require("@/assets/a2.webp"),
          price: 29.90
        },
        {
          name: "褐色点童装",
          src: require("@/assets/a3.webp"),
          price: 18.90
        },
        {
          name: "白底蓝点T恤",
          src: require("@/assets/a4.webp"),
          price: 19.90
        },
        {
          name: "白底蓝块T恤",
          src: require("@/assets/a5.webp"),
          price: 19.90
        },
        {
          name: "白底橙条童装",
          src: require("@/assets/a6.webp"),
          price: 29.90
        },
        {
          name: "白底黑卡童装",
          src: require("@/assets/a7.webp"),
          price: 18.90
        },
        {
          name: "白底花点T恤",
          src: require("@/assets/a8.webp"),
          price: 19.90
        },
        {
          name: "白底黑点包屁服",
          src: require("@/assets/a9.webp"),
          price: 19.90
        },
        {
          name: "白底褐圈包屁服",
          src: require("@/assets/a10.webp"),
          price: 19.90
        },
        {
          name: "白底花圈包屁服",
          src: require("@/assets/a11.webp"),
          price: 39.90
        },
        {
          name: "白底蓝圈包屁服",
          src: require("@/assets/a12.webp"),
          price: 39.90
        }
      ],
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


.wrapper ul {
  overflow: hidden;
  margin: 0 -10px;
  padding: 0;
  list-style: none;
}

.wrapper li {
  float: left;
  width: calc(33.333333% - 20px);
  margin: 0 10px 20px 10px;
}


ul.grid {
  list-style: none;
  width: 1500px;
  margin: 5px;
  text-align: left;
}

ul.grid li {
  padding: 10px;
  float: left;
  cursor: pointer;
  border: 2px solid #e8e8e8;
  box-sizing: border-box;
}

ul.grid li img {
  width: 460px;
  height: 460px ;
  object-fit: cover;
  display: block;
  border: none;
  padding: 20px;
  box-sizing: border-box;
}
</style>
