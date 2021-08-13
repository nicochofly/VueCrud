<template>
  <div>
    <p style="width: 100%;height: 60px ;background-color: #2c3e50; margin-top: 10px;margin-bottom: 10px ;font-family: Patrick">
      Helloworld</p>
    <span class="titleClass">Melemo-Kids</span>
    <el-row type="flex" justify="space-around" :class="isFocus?'inputDeepFocus':'inputDeep'">
      <el-col :span="3" :offset="4" style="display: flex;
            justify-content: center;
            align-items: center;">
        <el-input
            type="text"
            placeholder="Search..."
            v-model="text"
            clearable="true"
            prefix-icon="el-icon-search"
            @focus="localFocus"
            @blur="localBlur"/>
      </el-col>
      <el-col :span="8" :pull="1">
        <nav-menu class="nav"/>
      </el-col>
      <el-col :span="4" :pull="4" style="display: flex;
            justify-content: center;
            align-items: center;">
        <el-link icon="el-icon-user-solid" :underline="false" class="rightitleClass">Log in</el-link>
        <el-link icon="el-icon-s-goods" :underline="false" class="rightitleClass">0</el-link>
        <!--        <div class="grid-content">bbb</div>-->
      </el-col>
    </el-row>

    <router-view/>
  </div>
</template>

<script>
import NavMenu from "@/components/common/NavMenu";

export default {
  name: "Home",
  components: {NavMenu},


  data() {
    return {
      isFocus: false,

      focusStyle: 'inputDeepFocus',
      blurStyle: 'inputDeep',
      // bottom_width: this.isFocus ? 1 : 0,

    }
  },
  methods: {
    localFocus() {
      this.isFocus = true;
    },
    localBlur() {
      this.isFocus = false;
    }
  }

}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.nav {
  margin-left: auto;
  margin-right: auto;
}


.titleClass {
  font-size: 50px;
  font-family: Patrick Hand;
  /*//@font-face { font-family: "Patrick Hand"; src: url("https://fonts.gstatic.com/s/patrickhand/v14/LDI1apSQOAYtSuYWp8ZhfYe8XsLL.woff2") format("woff2"); font-style: normal; font-weight: 400; font-display: swap; unicode-range: U+0-FF, U+131, U+152-153, U+2BB-2BC, U+2C6, U+2DA, U+2DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD; }*/

  /*font-family: Times, "Times New Roman", Georgia, serif;*/
  /*font-family: Verdana, Arial, Helvetica, sans-serif;*/
  /*font-family: "Lucida Console", Courier, monospace;*/
}


.rightitleClass {
  font-size: 16px;
  font-family: Patrick Hand;
  margin-left: 20px;
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

.inputDeep >>> .el-input__inner {
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
  border-top-width: 0px;
  border-left-width: 0px;
  border-right-width: 0px;
  border-bottom-width: 0px;
  border-bottom-color: #333333;
}


.inputDeepFocus >>> .el-input__inner {
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
  border-top-width: 0px;
  border-left-width: 0px;
  border-right-width: 0px;
  border-bottom-width: 1px;
  border-bottom-color: #333333;
}


</style>
