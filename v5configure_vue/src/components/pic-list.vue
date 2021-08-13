<!--@click.stop="clickItem(item)"-->
<template>
  <div class="main">
    <img :src="picItem"/>
  </div>
</template>

<script type="text/ecmascript-6">
/**
 * @description 图片列表切换子组件，传递单个item即可
 */

export default {
  name: 'picList',
  props: ['picItem'],
  // props: {
  //   picItem:String,
  //   key:String,
  //   item: {
  //     type: Object,
  //     default:
  //         () => ({})
  //   }
  // },
  methods: {
    /**
     * @description 点击图片列表事件
     */
    clickItem(item) {
      this.$emit('showPicture', item);
    }
  }
}
</script>

<style scoped lang="less">
@base-line-height: .4rem;
@item-background-color: #303438;

.main {
  margin: 2% .1rem;
  width: 25%;
  min-width: 1rem;
  height: 2rem;
  color: @item-background-color;

  & > div {
    width: 100%;
    height: 100%;
    background-size: 100%;
    background-repeat: no-repeat;
  }
}
</style>
