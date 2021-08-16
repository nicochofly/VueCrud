<!--<template>
  <div class="content-container">
    <div class="content-container-item content">
      &lt;!&ndash; 使用transition加过渡效果 &ndash;&gt;
      <transition-group tag="div" class="content-inner" name="list" mode="out-in">
        &lt;!&ndash; 图片列表封装成组件，以便扩展 &ndash;&gt;
        <pic-list v-for="item in showPicList" :key="item.code" :picItem="item.picList"></pic-list>
      </transition-group>
    </div>
    &lt;!&ndash; 向左点击查看图片按钮 &ndash;&gt;
    <div class="content-container-item icon-container left-icon-container">
      <div @click="prevStation" class="shuffling-btn prev"></div>
    </div>
    &lt;!&ndash; 向右点击查看图片按钮 &ndash;&gt;
    <div class="content-container-item icon-container right-icon-container">
      <div @click="nextStation" class="shuffling-btn next"></div>
    </div>
  </div>


</template>

<script>
import picList from '../pic-list';

export default {
  name: 'pictureContainer',
  data() {
    return {
      showPicList: [{
        id:1,
        code: 1,
        picList: require("@/assets/a1.webp"),
        price:111
      },
        {
          id:1,
          code: 2,
          picList: require("@/assets/a2.webp"),
          price:111

        },
        {
          id:1,
          code: 3,
          picList: require("@/assets/a3.webp"),
          price:111
        },
        {
          id:1,
          code: 4,
          picList: require("@/assets/a4.webp"),
          price:111
        },
        {
          id:1,
          code: 5,
          picList: require("@/assets/a5.webp"),
          price:111
        },
        {
          code: 6,
          picList: require("@/assets/a6.webp"),
          price:111
        },
        {
          code: 7,
          picList: require("@/assets/a7.webp"),

          price:111
        },
        {
          code: 8,
          picList: require("@/assets/a8.webp"),
          price:111
        }],
      watchList: [],    //图片列表list
      showListIndex: 0,    //当前页面显示的第一个图片列表在watchList中的位置
    }
  },
  components: {
    picList
  },
  mounted() {
    //加载图片列表
    this.getWatchList();
  },
  methods: {
    /**
     * @description 获取可视范围图片列表
     */
    getWatchList() {
      this.watchList = this.showPicList;

      if (this.watchList.length <= 4) {
        this.showPicList = this.watchList;
      } else {
        this.showPicList = this.watchList.slice(0, 4);
      }
    },
    /**
     * @description 向右点击事件
     */
    nextStation() {
      if ((this.showListIndex + 4) < this.watchList.length) {
        this.showPicList = this.watchList.slice(++this.showListIndex, this.showListIndex + 4);
      } else {
        // utils.showAlert('右侧已无数据');
      }
    },
    /**
     * @description 向左点击事件
     */
    prevStation() {
      if (this.showListIndex > 0) {
        this.showPicList = this.watchList.slice(&#45;&#45;this.showListIndex, this.showListIndex + 4);
      } else {
        // utils.showAlert('左侧已无数据');
      }
    }
  }
}
</script>

<style scoped lang="less">
@icon-container-width: .3rem;
@title-background: #35393E;

.content-container {
  width: 100%;
  height: 20rem;
  background: @title-background;

  .icon-container {
    width: @icon-container-width;
    max-width: @icon-container-width;
  }

  .left-icon-container {
    margin-left: -100%;
  }

  .right-icon-container {
    margin-left: @icon-container-width;

    &:after {
      display: block;
      content: '';
      clear: both;
      height: 0;
      line-height: 0;
      visibility: hidden;
    }
  }

  .content-container-item {
    float: left;
    height: 100%;
    position: relative;

    .shuffling-btn {
      position: absolute;
      top: 50%;
      left: 50%;
      margin-left: -.25rem;
      margin-top: -.25rem;
      cursor: pointer;
      width: .5rem;
      height: .5rem;
      border-radius: .5rem;
    }

    .prev {
      //icon: el-icon-back no-repeat;
      background: url(../../assets/back.png) no-repeat;
    }

    .next {
      //icon: el-icon-right no-repeat;
      background: url(../../assets/next.png) no-repeat;
    }
  }

  .content {
    width: 100%;
  }

  .content-inner {
    margin: 0 @icon-container-width;
    display: flex;
    flex-wrap: nowrap;
    align-items: center;
    height: 100%;
    justify-content: center;

    .list-enter-active, .list-leave-active {
      transition: opacity 2s ease-in-out;
    }

    .list-leave, .list-enter {
      opacity: 0;
    }
  }
}
</style>-->


<!--<script>
import BScroll from 'better-scroll'

export default {
  name: 'melemo_home',
  props: {
    recommendList: {
      type: Array,
      required: true
    }
  },
  components: {

  },
  data () {
    return {
      showPicList: [{
        id:1,
        code: 1,
        url: require("@/assets/a1.webp"),
        price:111
      },
        {
          id:1,
          code: 2,
          url: require("@/assets/a2.webp"),
          price:111

        },
        {
          id:1,
          code: 3,
          url: require("@/assets/a3.webp"),
          price:111
        },
        {
          id:1,
          code: 4,
          url: require("@/assets/a4.webp"),
          price:111
        },
        {
          id:1,
          code: 5,
          url: require("@/assets/a5.webp"),
          price:111
        },
        {id:1,
          code: 6,
          url: require("@/assets/a6.webp"),
          price:111
        },
        {id:1,
          code: 7,
          url: require("@/assets/a7.webp"),

          price:111
        },
        {id:1,
          code: 8,
          url: require("@/assets/a8.webp"),
          price:111
        }],
    }
  },
  methods: {
    verScroll () {
      let width = this.showPicList.length * 110// 动态计算出滚动区域的大小，前面已经说过了，产生滚动的原因是滚动区域宽度大于父盒子宽度
      this.$refs.cont.style.width = width + 'px'  // 修改滚动区域的宽度

      console.log("this.$refs.cont.style.width  "+this.$refs.cont.style.width);
      this.$nextTick(() => {
        if (!this.scroll) {
          this.scroll = new BScroll(this.$refs.wrapper, {
            startX: 0,  // 配置的详细信息请参考better-scroll的官方文档，这里不再赘述
            click: true,
            scrollX: true,
            scrollY: false,
            eventPassthrough: 'vertical'
          })
        } else {
          this.scroll.refresh() //如果dom结构发生改变调用该方法
        }
      })
    }
  },
  mounted () {
    this.$nextTick(() => {
      let timer = setTimeout(() => { // 其实我也不想写这个定时器的，这相当于又嵌套了一层$nextTick，但是不这么写会失败
        if (timer) {
          clearTimeout(timer)
          this.verScroll()
        }
      }, 0)
    })
  }
}
</script>-->

<!--<style lang="scss" scoped>
//.recommand-wrap {
//  height: 0;
//  padding-bottom: 50%;
//  margin-top: .2rem;
//  background: #fff;
//  padding-left: .24rem;
//  width: 100%;
//  .title {
//    position: relative;
//    height: 40px;
//    display: flex;
//    padding: 12px 0;
//    box-sizing: border-box;
//    .title-img {
//      width: 15px;
//      height: 15px;
//    }
//    .title-hotrec {
//      font-size: 16px;
//      margin-left: 4px;
//    }
//    .title-allrec {
//      position: absolute;
//      padding-top: 2px;
//      font-size: 13px;
//      right: 20px;
//      color: gray;
//    }
//  }
  .cont {
    list-style: none;
    width: 100%;
    height: 2rem;
    // overflow-x: scroll;
    white-space: nowrap;
    font-size: 12px;
    text-align: center;
    padding-right: .24rem;
    //width: 140rem;
    .cont-item {
      position: relative;
      display: inline-block;
      padding: .06rem 0 .2rem;
      width: 30rem;
      margin: 0 .1rem;
      .cont-img {
        overflow: hidden;
        width: 30rem;
        height: 0;
        padding-bottom: 100%;
        .img {
          width: 100%;
        }
      }
      .cont-dest {
        margin: .1rem 0;
      }
      .cont-price {
        .price {
          color: #ff8300;
        }
      }
    }
  }
//}
</style>-->

<template>

  <div>

    <el-carousel :interval="5000" arrow="always" height="500px">
      <el-carousel-item v-for="item in adImgList" :key="item.name">
        <img :src="item.src" alt="图片丢失了" :title="item.title" class="carousel_image_type"/>
      </el-carousel-item>
    </el-carousel>

    <span class="new_font">New Arrivals</span>
    <swiper class="swiper" :options="swiperOption">
      <swiper-slide v-for="item in dataList" :key="item.name">
        <div>
          <img :src="item.src"/>

          <el-col>
            <el-row style="font-family: Patrick Hand;font-size: 14px;">{{item.name}}</el-row>
            <el-row style="font-family: Patrick Hand;font-size: 14px;">$:{{item.price}}</el-row>
            <el-row><el-button plain style="font-family: Patrick Hand;font-size: 16px;">Add to Cart</el-button></el-row>
          </el-col>
<!--          <span>{{item.name}}</span>-->
<!--          <span>{{item.price}}</span>-->

        </div>


        // 园博园
        // 小助手
        // voiceFun

      </swiper-slide>
      <!--      <swiper-slide><img src="../../assets/a1.webp">
            </swiper-slide>
            <swiper-slide><img src="../../assets/a2.webp"></swiper-slide>

            <swiper-slide><img src="../../assets/a3.webp"></swiper-slide>
            <swiper-slide><img src="../../assets/a4.webp"></swiper-slide>
            <swiper-slide><img src="../../assets/a5.webp"></swiper-slide>
            <swiper-slide><img src="../../assets/a6.webp"></swiper-slide>
            <swiper-slide><img src="../../assets/a7.webp"></swiper-slide>
            <swiper-slide><img src="../../assets/a8.webp"></swiper-slide>
            <swiper-slide><img src="../../assets/a9.webp"></swiper-slide>-->
      <div class="swiper-pagination" slot="pagination"></div>
      <div class="swiper-button-prev" slot="button-prev"></div>
      <div class="swiper-button-next" slot="button-next"></div>
    </swiper>
  </div>
</template>

<script>
import {Swiper, SwiperSlide} from 'vue-awesome-swiper'
// import 'swiper/css/swiper.css'
import 'swiper/swiper-bundle.css'
// import { Swiper, SwiperSlide, directive } from 'vue-awesome-swiper'
export default {
  name: 'swiper-example-loop-group',
  title: 'Loop mode with multiple slides per group',
  components: {
    Swiper,
    SwiperSlide
  },
  data() {
    return {
      swiperOption: {
        slidesPerView: 4,
        spaceBetween: 30,
        slidesPerGroup: 1,
        height: 500,
        loop: true,
        loopFillGroupWithBlank: true,
        pagination: {
          el: '.swiper-pagination',
          clickable: true
        },
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev'
        }
      }
      ,
      subOption: {},
      adImgList: [
        {
          name: "aaa",
          src: require("@/assets/aaa.webp"),
          title: "aaa"
        },
        {
          name: "bbb",
          src: require("@/assets/bbb.webp"),
          title: "bbb"
        },
        {
          name: "ccc",
          src: require("@/assets/ccc.webp"),
          title: "ccc"
        }
      ],
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
  }
}
</script>

<style lang="scss" scoped>
//@import './base.scss';
//@import "../base.sd";
.new_font {
  font-family: Patrick Hand;
  font-size: 40px;
  color: #2c3e50;
  margin: 40px;
  padding-top: 40px;
  /*//@font-face { font-family: "Patrick Hand"; src: url("https://fonts.gstatic.com/s/patrickhand/v14/LDI1apSQOAYtSuYWp8ZhfYe8XsLL.woff2") format("woff2"); font-style: normal; font-weight: 400; font-display: swap; unicode-range: U+0-FF, U+131, U+152-153, U+2BB-2BC, U+2C6, U+2DA, U+2DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD; }*/

  /*font-family: Times, "Times New Roman", Georgia, serif;*/
  /*font-family: Verdana, Arial, Helvetica, sans-serif;*/
  /*font-family: "Lucida Console", Courier, monospace;*/
}

@font-face {
  font-family: Patrick Hand;
  font-style: italic;
  src: url("https://fonts.gstatic.com/s/patrickhand/v14/LDI1apSQOAYtSuYWp8ZhfYe8XsLL.woff2") format("woff2");
  /* src: url-loader("@/assets/iozzkhwu.ttf");  这种方式是错误的*/
}
</style>
