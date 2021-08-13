<!--
<template>
  &lt;!&ndash;  <el-carousel :interval="5000" arrow="always" height="650px">
      &lt;!&ndash;    <el-carousel-item v-for="item in 4" :key="item">
            <h3>{{ item }}</h3>
          </el-carousel-item>&ndash;&gt;
      <el-carousel-item v-for="item in imgList" :key="item.name">
        <img :src="item.src" alt="图片丢失了" :title="item.title" class="carousel_image_type"/>
      </el-carousel-item>

    </el-carousel>&ndash;&gt;


  &lt;!&ndash;  <div class="swiperBox">
      <div class="directionIcon">
        <div class="imgLeft" @click="clickLeft"></div>
        <div class="imgRight" @click="clickRight"></div>
      </div>
      <div id="swiper">
        <div class="imgBox" @mouseenter="enter" @mouseleave="leave">
          <div class="imgDiv" v-for="(item, index) of imageList" :key="index">
            <img :src="item" />
            <span>{{ item }}</span>
          </div>
        </div>
      </div>
    </div>&ndash;&gt;
  &lt;!&ndash;  <div>
      <el-row style="height: 840px;">
        &lt;!&ndash;<search-bar></search-bar>&ndash;&gt;
        <el-tooltip effect="dark" placement="right"
                    v-for="item in books"
                    :key="item.id">
          <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{item.title}}</p>
          <p slot="content" style="font-size: 13px;margin-bottom: 6px">
            <span>{{item.author}}</span> /
            <span>{{item.date}}</span> /
            <span>{{item.press}}</span>
          </p>
          <p slot="content" style="width: 300px" class="abstract">{{item.abs}}</p>
          <el-card style="width: 135px;margin-bottom: 20px;height: 233px;float: left;margin-right: 15px" class="book"
                   bodyStyle="padding:10px" shadow="hover">
            <div class="cover">
              <img :src="item.cover" alt="封面">
            </div>
            <div class="info">
              <div class="title">
                <a href="">{{item.title}}</a>
              </div>
            </div>
            <div class="author">{{item.author}}</div>
          </el-card>
        </el-tooltip>
      </el-row>
      <el-row>
        <el-pagination
            :current-page="1"
            :page-size="10"
            :total="20">
        </el-pagination>
      </el-row>
    </div>&ndash;&gt;


  <div class="recommand-wrap">
    <div class="title">
      <img class="title-img" src="https://imgs.qunarzz.com/piao/fusion/1711/16/bfbb9874e8f11402.png" alt="本周热门榜单">
      <span class="title-hotrec">本周热门榜单</span>
      <span class="title-allrec">全部榜单</span>
    </div>
    <div ref="wrapper">
      <ul class="cont" ref="cont">
        <li class="cont-item" v-for="item of recommendList" :key="item.id">
          <div class="cont-img">
            <img class="img" :src="item.url" :alt="item.text">
          </div>
          <div class="cont-dest">{{ item.text }}</div>
          <div class="cont-price">
            <span class="price">￥{{ item.price }}</span>
            <span>起</span>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
// export default {
//   name: 'Books',
//
//   props: {
//     speed: Number,
//     direction: String
//   },
//   data() {
//     return {
//       // books: [
//       //   {
//       //     cover: 'https://i.loli.net/2019/04/10/5cada7e73d601.jpg',
//       //     title: '三体',
//       //     author: '刘慈欣',
//       //     date: '2019-05-05',
//       //     press: '重庆出版社',
//       //     abs: '文化大革命如火如荼进行的同时。军方探寻外星文明的绝秘计划“红岸工程”取得了突破性进展。但在按下发射键的那一刻，历经劫难的叶文洁没有意识到，她彻底改变了人类的命运。地球文明向宇宙发出的第一声啼鸣，以太阳为中心，以光速向宇宙深处飞驰……'
//       //   }
//       // ]
//
//
//       imgList: [
//         {
//           name: "aaa",
//           src: require("@/assets/aaa.webp"),
//           title: "aaa"
//         },
//         {
//           name: "bbb",
//           src: require("@/assets/bbb.webp"),
//           title: "bbb"
//         },
//         {
//           name: "ccc",
//           src: require("@/assets/ccc.webp"),
//           title: "ccc"
//         }
//       ],
//
//
//       imageList: [
//         require("@/assets/a1.webp"),
//         require("@/assets/a2.webp"),
//
//         require("@/assets/a3.webp"),
//
//         require("@/assets/a4.webp"),
//         require("@/assets/a5.webp"),
//         require("@/assets/a6.webp"),
//         require("@/assets/a7.webp"),
//         require("@/assets/a8.webp"),
//         require("@/assets/a9.webp"),
//         require("@/assets/a10.webp"),
//
//         require("@/assets/a11.webp"),
//
//
//
//         // require("../../assets/images/banner.jpg"),
//         // require("../../assets/images/banner.jpg"),
//         // require("../../assets/images/banner.jpg"),
//         // require("../../assets/images/banner.jpg")
//       ],
//       timer: null,
//       theSpeed: this.speed,
//       imgWidth: 260,
//       theDirection: this.direction
//
//     }
//   },
//   created() {},
//   mounted() {
//     this.move();
//   },
//   methods: {
//     //   左边
//     clickLeft() {
//       this.theDirection = "left";
//     },
//     // 右边
//     clickRight() {
//       this.theDirection = "right";
//     },
//     // 移入
//     enter() {
//       this.timer = clearInterval(this.timer);
//     },
//     leave() {
//       this.move()
//     },
//     move() {
//       var imgBox = document.getElementsByClassName("imgBox")[0];
//       imgBox.innerHTML += imgBox.innerHTML;
//       var imgDiv = document.getElementsByClassName("imgDiv");
//       imgBox.style.width = imgDiv.length * this.imgWidth + "px"; //设置ul的宽度使图片可以放下
//       let that = this;
//       function autoScroll() {
//         if (imgBox.offsetLeft < -(imgBox.offsetWidth / 2)) {
//           //向左滚动
//           imgBox.style.left = 0;
//         }
//         if (imgBox.offsetLeft > 0) {
//           //向右滚动
//           imgBox.style.left = -(imgBox.offsetWidth / 2) + "px";
//         }
//         if (that.theDirection == "left") {
//           that.theSpeed = -Math.abs(that.theSpeed);
//         } else {
//           that.theSpeed = Math.abs(that.theSpeed);
//         }
//         imgBox.style.left = imgBox.offsetLeft + that.theSpeed + "px";
//       }
//       clearInterval(this.timer)
//       this.timer = setInterval(autoScroll, 30); //全局变量 ，保存返回的定时器
//
//     }
//   },
//
//   beforeDestroy() {
//     clearInterval(this.timer);
//     this.timer = null;
//   }
// }
import BScroll from 'better-scroll'

export default {
  name: 'Books',
  props: {
    // recommendList: {
    //   type: Array,
    //   required: true,
    // }
  },
  components: {},
  data() {
    return {

      /*<li class="cont-item" v-for="item of recommendList" :key="item.id">
      <div class="cont-img">
        <img class="img" :src="item.url" :alt="item.text">
      </div>
      <div class="cont-dest">{{ item.text }}</div>
      <div class="cont-price">
        <span class="price">￥{{ item.price }}</span>
        <span>起</span>
      </div>*/


      recommendList: [{
        id: 1,
        url: require("@/assets/a1.webp"),
        text: '1',
        price: 1,
      }, {
        id: 2,
        url: require("@/assets/a2.webp"),
        text: '2',
        price: 2,
      }, {
        id: 3,
        url: require("@/assets/a3.webp"),
        text: '3',
        price: 3,
      }, {
        id: 4,
        url: require("@/assets/a4.webp"),
        text: '4',
        price: 4,
      }, {
        id: 5,
        url: require("@/assets/a5.webp"),
        text: '5',
        price: 5,
      }, {
        id: 6,
        url: require("@/assets/a6.webp"),
        text: '6',
        price: 6,
      }, {
        id: 7,
        url: require("@/assets/a7.webp"),
        text: '7',
        price: 7,
      }, {
        id: 8,
        url: require("@/assets/a8.webp"),
        text: '8',
        price: 8,
      }, {
        id: 9,
        url: require("@/assets/a9.webp"),
        text: '9',
        price: 9,
      }, {
        id: 10,
        url: require("@/assets/a10.webp"),
        text: '10',
        price: 10,
      }],


    }
  },
  methods: {
    verScroll() {
      let width = this.recommendList.length * 110// 动态计算出滚动区域的大小，前面已经说过了，产生滚动的原因是滚动区域宽度大于父盒子宽度
      this.$refs.cont.style.width = width + 'px'  // 修改滚动区域的宽度
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
  mounted() {
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
</script>

<style lang="scss" scoped>
.recommand-wrap {
  height: 0;
  padding-bottom: 50%;
  margin-top: .2rem;
  background: #fff;
  padding-left: .24rem;
  width: 100%;

  .title {
    position: relative;
    height: 40px;
    display: flex;
    padding: 12px 0;
    box-sizing: border-box;

    .title-img {
      width: 15px;
      height: 15px;
    }

    .title-hotrec {
      font-size: 16px;
      margin-left: 4px;
    }

    .title-allrec {
      position: absolute;
      padding-top: 2px;
      font-size: 13px;
      right: 20px;
      color: gray;
    }
  }

  .cont {
    list-style: none;
    // overflow-x: scroll;
    white-space: nowrap;
    font-size: 12px;
    text-align: center;
    padding-right: /*.24rem*/24px;

    .cont-item {
      position: relative;
      display: inline-block;
      padding: /*.06rem 0 .2rem*/10px 10px;
      width: /*2rem*/200px;
      margin: /*0 .1rem*/5px;

      .cont-img {
        overflow: hidden;
        width: /*2rem*/200px;
        height: 0;
        padding-bottom: 100%;

        .img {
          width: 100%;
        }
      }

      .cont-dest {
        margin: /*.1rem 0*/5px;
      }

      .cont-price {
        .price {
          color: #ff8300;
        }
      }
    }
  }
}
</style>
&lt;!&ndash;<style scoped>
/*.cover {
  width: 115px;
  height: 172px;
  margin-bottom: 7px;
  overflow: hidden;
  cursor: pointer;
}

img {
  width: 115px;
  height: 172px;
  !*margin: 0 auto;*!
}

.title {
  font-size: 14px;
  text-align: left;
}

.author {
  color: #333;
  width: 102px;
  font-size: 13px;
  margin-bottom: 6px;
  text-align: left;
}

.abstract {
  display: block;
  line-height: 17px;
}

a {
  text-decoration: none;
}

a:link, a:visited, a:focus {
  color: #3377aa;
}*/
/*.el-carousel__item h3 {*/
/*  color: #475669;*/
/*  font-size: 18px;*/
/*  opacity: 0.75;*/
/*  margin: 0;*/
/*}*/

/*.el-carousel__item:nth-child(2n) {*/
/*  background-color: #99a9bf;*/
/*}*/

/*.el-carousel__item:nth-child(2n+1) {*/
/*  background-color: #d3dce6;*/
/*}*/

.carousel_image_type {
  width: 80%;
}




.sliding_box{
  display: flex;
  overflow-y: hidden;
  overflow-x: scroll;
}
.sliding_item{
  display: flex;
  margin-right: 8px;
  background-color: #CDCDCD;
}
.sliding_item_img {
  width: 200px;
  height: 100px;
}


.swiperBox {
  height: 365px;
  width: 1200px;
  margin: 20px auto;
  position: relative;
}
.swiperBox .directionIcon {
  position: absolute;
  top: 45%;
  width: 100%;
  left: -10%;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between;
  opacity: 0.5;
  z-index: 9;
  pointer-events: none;
}

.swiperBox .directionIcon .imgLeft {
  position: absolute;
  left: 80px;
  width: 100px;
  height: 100px;
  background-size: 80%;
  background: red;
  cursor: pointer;
}

.swiperBox .directionIcon .imgRight {
  position: absolute;
  right: -160px;
  width: 100px;
  height: 100px;
  background-size: 80%;
  background: red;
  -webkit-transform: rotateY(180deg);
  transform: rotateY(180deg);
  cursor: pointer;
}
.swiperBox #swiper {
  width: 100%;
  height: 100%;
  overflow: hidden;
  position: relative;
}
.swiperBox #swiper .imgBox {
  position: absolute;
  left: 0;
  top: 0;
  overflow: hidden;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
}
.swiperBox #swiper .imgBox .imgDiv {
  width: 240px;
  height: 365px;
  margin-left: 15px;
  cursor: pointer;
}
.swiperBox #swiper .imgBox .imgDiv img {
  height: 100%;
  width: 100%;
}






</style>&ndash;&gt;

-->







<div class="threeImg">
<div class="iconleft" @click="zuohua">
</div>
<div class="Containt">
  <ul :style="{'left':calleft + 'px', width: widthData + 'px'} " v-on:mouseover="stopmove()" v-on:mouseout="move()" class="imgBoxoul">
    <li v-for="(item,index) in dataList" :key="index" @click="gotodetails(item.id)" ref="lis">
      <img :src="item.url" />
      <div class="item-content">
        <p class="item-title">{{item.title}}</p>
        <div class="item-detail line-2">{{item.id}}</div>
        <p class="item-price">￥{{item.price}}</p>
      </div>
    </li>
  </ul>
</div>
<div class="iconright" @click="youhua">
</div>
</div>
    <swiper-slide><img src="../../assets/a1.webp">
    </swiper-slide>
    <swiper-slide><img src="../../assets/a2.webp"></swiper-slide>

    <swiper-slide><img src="../../assets/a3.webp"></swiper-slide>
    <swiper-slide><img src="../../assets/a4.webp"></swiper-slide>
    <swiper-slide><img src="../../assets/a5.webp"></swiper-slide>
    <swiper-slide><img src="../../assets/a6.webp"></swiper-slide>
    <swiper-slide><img src="../../assets/a7.webp"></swiper-slide>
    <swiper-slide><img src="../../assets/a8.webp"></swiper-slide>
    <swiper-slide><img src="../../assets/a9.webp"></swiper-slide>
    <div class="swiper-pagination" slot="pagination"></div>
    <div class="swiper-button-prev" slot="button-prev"></div>
    <div class="swiper-button-next" slot="button-next"></div>


<script>
export default {
  data() {
    return {
      calleft: 0,
      speed:1,
      /*dataList: [{
        id: 1,
        url: require("@/assets/a1.webp"),
        text: '1',
        price: 1,
      }, {
        id: 2,
        url: require("@/assets/a2.webp"),
        text: '2',
        price: 2,
      }, {
        id: 3,
        url: require("@/assets/a3.webp"),
        text: '3',
        price: 3,
      }, {
        id: 4,
        url: require("@/assets/a4.webp"),
        text: '4',
        price: 4,
      }, {
        id: 5,
        url: require("@/assets/a5.webp"),
        text: '5',
        price: 5,
      }, {
        id: 6,
        url: require("@/assets/a6.webp"),
        text: '6',
        price: 6,
      }, {
        id: 7,
        url: require("@/assets/a7.webp"),
        text: '7',
        price: 7,
      }, {
        id: 8,
        url: require("@/assets/a8.webp"),
        text: '8',
        price: 8,
      }, {
        id: 9,
        url: require("@/assets/a9.webp"),
        text: '9',
        price: 9,
      }, {
        id: 10,
        url: require("@/assets/a10.webp"),
        text: '10',
        price: 10,
      }],*/

    };
  },

  created() {
    this.move();
  },
  mounted() {
    var imgBox = document.getElementsByClassName("imgBoxoul")[0];
    imgBox.innerHTML += imgBox.innerHTML;
  },
  props: ["dataList"],
  computed: {
    widthData(){
      return 240 * Number(this.dataList.length*2)
    }
  },
  methods: {
    //移动
    move() {
      this.timer = setInterval(this.starmove, 20);
    },
    //开始移动
    starmove() {
      this.calleft -= 1.2;//*this.speed
      if (this.calleft <= -1150) {
        this.calleft = 0;
      }
    },
    //鼠标悬停时停止移动
    stopmove() {
      clearInterval(this.timer);
    },
    //点击按钮左移
    zuohua() {
      this.calleft -= 240;//this.speed = 1
      if (this.calleft <= -1200) {
        this.calleft = 0;
      }
    },
    //点击按钮右移
    youhua() { //this.speed = -1
      this.calleft += 240;
      if (this.calleft >= 0) {
        this.calleft = -1200;
      }
    },

  }
};
</script>

<style>

.threeImg {
  position: relative;
}

.threeImg .Containt ul {
  margin: 0 auto;
  width: 2400px;
  position: absolute;
  left: 0px;
  cursor: pointer;
  height: 100%;
  z-index: 10;
}

.threeImg .Containt ul li {
  float: left;
  width: 220px;
  height: 350px;
  margin-right: 20px;
  border-radius: 10px;
  overflow: hidden;
  background-color: #ffffff;
}

.threeImg .Containt ul li img {
  width: 100%;
  height: 263px;
}

.Containt {
  position: relative;
  padding: 60px 0;
  overflow-y: auto;
  width: 1200px;
  height: 365px;
  overflow: hidden;
  margin: 0 auto;
}

.iconleft {
  position: absolute;
  width: 20px;
  height: 80px;
  top: 35%;
  left: 15%;
  z-index: 99999;
  background: url("~@/assets/icon.png") no-repeat;
  cursor: pointer;
}

.iconright {
  position: absolute;
  top: 37%;
  right: 15%;
  width: 20px;
  height: 80px;
  z-index: 99999;
  background: url("~@/assets/icon.png") no-repeat;
  -webkit-transform:rotate(180deg);
  cursor: pointer;
}
</style>
