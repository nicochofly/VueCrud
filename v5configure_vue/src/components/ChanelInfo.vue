<template>
  <div class="block">
   <el-timeline class="time-line">
        <el-timeline-item
           v-for="(info, index) in events"
           :key="index"
           :timestamp="info.date">
         <el-card>
<!--            0 > 查看 ; 1 > 编辑 ; 2 > 新增 ; 3> 删除-->
           <h4>{{info.type==1?"修改了":info.type==2?'添加了':info.type==3?'删除了':''}} {{info.channelinfo}}</h4>
           <p>操作人 : {{info.username}} </p>
         </el-card>

       </el-timeline-item>

    </el-timeline>
  </div>

</template>
<script>


export default {
  mounted() {
    this.queryall()
  },
  data() {
    return {
      events: [],
      type :0,
      username: '',
      channelinfo: '',
      date:''
    }
  },
  methods: {

    queryall(){
      var _this = this
      let url = '/events';
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.events = resp.data.data;
        } else {
          _this.events = null;
        }
      })
    },
    onSubmit() {
      console.log('submit!');
    }
  }
}
</script>
<style>
.time-line {

  float: left;
  margin-left: 100px;
  margin-right: 100px;
  margin-top: 50px;


}

</style>