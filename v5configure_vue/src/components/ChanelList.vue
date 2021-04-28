<template>
  <body class="chanellist_body">

  <el-button type="text" @click="handleClick(-1,2)" class="add_btn" align="left">新增</el-button>

  <el-table
      class="chanelist_table"
      :data="chanelist">
    <el-table-column
        prop="name"
        align="center"
        label="项目名称">
    </el-table-column>
    <el-table-column
        align="center"
        prop="hostname"
        label="hostname">
    </el-table-column>
    <el-table-column
        align="center"
        prop="appid"
        label="appid">
    </el-table-column>
    <el-table-column
        align="center"
        prop="secret"
        label="secret">
    </el-table-column>
    <el-table-column
        align="center"
        prop="uuid"
        class="uuid"
        label="uuid"
    >
    </el-table-column>
    <el-table-column
        align="center"
        prop="clientid"
        label="clientid">
    </el-table-column>

    <el-table-column
        align="center"
        prop="sourceid"
        label="sourceid">
    </el-table-column>
    <el-table-column
        align="center"
        fixed="right"
        label="操作"
    >
      <template #default="scope">
        <el-button @click="handleClick(scope.$index,0)" type="text" size="small">查看</el-button>
        <el-button @click="handleClick(scope.$index,1)" type="text" size="small">编辑</el-button>
        <el-button @click="handleClick(scope.$index,3)" type="text" size="small">移除</el-button>
      </template>
    </el-table-column>
  </el-table>


  <el-dialog title="项目详情" width="30%" v-if="cleanDialog" :visible.sync="dialogFormVisible" @close='closeDialog()'>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" :label-width="formLabelWidth" :disabled="inputEnable">
      <el-form-item label="项目名称" prop="name">
        <el-input v-model="ruleForm.name" placeholder="项目名称"></el-input>
      </el-form-item>
      <el-form-item label="HOST_NAME" prop="hostname">
        <el-input v-model="ruleForm.hostname" placeholder="服务器地址"></el-input>
      </el-form-item>
      <el-form-item label="APPID" prop="appid">
        <el-input v-model="ruleForm.appid" placeholder="应用id"></el-input>
      </el-form-item>
      <el-form-item label="SECRET" prop="secret">
        <el-input v-model="ruleForm.secret" placeholder="应用密钥"></el-input>
      </el-form-item>
      <el-form-item label="CLIENDID" prop="clientid">
        <el-input v-model="ruleForm.clientid" placeholder="客户id"></el-input>
      </el-form-item>
      <el-form-item label="SOURCEID" prop="sourceid">
        <el-input v-model="ruleForm.sourceid" placeholder="sourceid"></el-input>
      </el-form-item>
      <el-form-item label="NOTE" prop="note">
        <el-input v-model="ruleForm.note" placeholder="备注"></el-input>
      </el-form-item>
      <el-form-item label="上线" align="left" prop="release">
        <el-switch v-model="ruleForm.release"></el-switch>
      </el-form-item>
      <el-form-item v-show="!inputEnable">
        <el-button type="primary" @click="submitForm()" v-text='btnTxt'>立即创建</el-button>
        <el-button @click="dialogFormVisible = false">取消</el-button>
      </el-form-item>
    </el-form>

  </el-dialog>

  </body>
</template>

<script>
export default {
  name: 'ChannelList',
  data() {
    return {
      btnTxt: '',
      chanelist: [],
      cleanDialog: false,
      dialogFormVisible: false,
      ruleForm: {
        id: '',
        name: '',
        hostname: '',
        appid: '',
        secret: '',
        uuid: '',
        clientid: '',
        sourceid: '',
        note: '',
        release: false,
      },
      formLabelWidth: '120px',
      dialogType: 0,  // 0 > 查看 ; 1 > 编辑 ; 2 > 新增 ; 3> 删除
      inputEnable: false,

      username: '',
      channelinfo: '',
      type: 0,
      date: '',


      rules: {
        name: [
          {required: true, message: '请输入项目名称', trigger: 'blur'},
        ],
        hostname: [
          {required: true, message: '请输入主机地址', trigger: 'blur'}
        ],
        appid: [
          {required: true, message: '请输入APPID', trigger: 'blur'}
        ],
        secret: [
          {required: true, message: '请输入SECRET', trigger: 'blur'}
        ],
        clientid: [
          {required: true, message: '请输入CLIENTID', trigger: 'blur'}
        ],
        sourceid: [
          {required: true, message: '请输入SOURCEID', trigger: 'blur'}
        ]
      }

    }
  },
  mounted() {
    this.queryall()
  },
  methods: {
    queryall() {
      var _this = this
      let url = '/queryall';
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.chanelist = resp.data.data;
        } else {
          _this.chanelist = null;
        }
      })
    },
    handleClick(index, type) {
      var _this = this
      _this.dialogType = type

      switch (type) {
        case 0:  //查看
          _this.cleanDialog = true
          _this.dialogFormVisible = true
          _this.inputEnable = type == 0
          var detailinfo = _this.chanelist[index];

          this.filldata(detailinfo)
          break;
        case 1:  // 编辑
          _this.cleanDialog = true
          _this.dialogFormVisible = true
          _this.inputEnable = type == 0
          var editinfo = _this.chanelist[index];
          _this.filldata(editinfo)
          _this.btnTxt = '提交修改'
          break;
        case 2:  //新增
          _this.cleanDialog = true
          _this.dialogFormVisible = true
          _this.inputEnable = type == 0
          _this.btnTxt = '立即创建'


          break;
        case 3:  //删除

          this.delete(_this.chanelist[index])
          break;
      }


    },
    filldata(info) {
      this.ruleForm = info
    },

    delete(info) {



      this.channelinfo = info.name
      this.$axios
          .delete('/remove/' + info.id).then(resp => {
        if (resp && resp.data.code === 200) {
          this.queryall()
          this.addevent()
        }
      })
    },

    submitForm() {
      var _this = this;
      _this.channelinfo = _this.ruleForm.name
      _this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.dialogFormVisible = false
          if (this.ruleForm.appid == null) {
            this.$axios
                .post('/append', {
                  appid: _this.ruleForm.appid,
                  name: _this.ruleForm.name,
                  hostname: _this.ruleForm.hostname,
                  secret: _this.ruleForm.secret,
                  question: _this.ruleForm.question,
                  clientid: _this.ruleForm.clientid,
                  sourceid: _this.ruleForm.sourceid,
                  note: _this.ruleForm.note,
                  release: true,
                })
                .then(successResponse => {
                  if (successResponse.data.code === 200) {
                    _this.queryall()
                    _this.addevent()

                  }
                })
                // eslint-disable-next-line no-unused-vars
                .catch(failResponse => {
                })
          } else {
            _this.$axios
                .post('/update', {
                  id: _this.ruleForm.id,
                  appid: _this.ruleForm.appid,
                  name: _this.ruleForm.name,
                  hostname: _this.ruleForm.hostname,
                  uuid: _this.ruleForm.uuid,
                  secret: _this.ruleForm.secret,
                  clientid: _this.ruleForm.clientid,
                  sourceid: _this.ruleForm.sourceid,
                  note: _this.ruleForm.note,
                  release: true,
                })
                .then(successResponse => {

                  console.log(successResponse.data)
                  if (successResponse.data.code === 200) {
                    _this.queryall()
                    _this.addevent()
                  }

                })
                // eslint-disable-next-line no-unused-vars
                .catch(failResponse => {
                })
          }
        } else {
          return false;
        }
      });
    },

    closeDialog() {
      this.ruleForm = {
        id: '',
        name: '',
        hostname: '',
        appid: '',
        secret: '',
        uuid: '',
        clientid: '',
        sourceid: '',
        note: '',
        release: false,
      }
    },


    findbyuuid() {
      // var _this = this
      let url = '/findbyuuid?uuid=8d8d7c73-2333-41d2-9994-c2180aa892f7';
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          // _this.chanelist = resp.data.data;
        } else {
          // _this.chanelist = null;
        }
      })
    },

    addevent() {

      this.$axios
          .post('/addevent', {
            username: this.$store.state.user.username,
            channelinfo: this.channelinfo,
            type: this.dialogType,
            date: this.$moment(new Date()).format('YYYY-MM-DD  HH:mm:ss'),
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              console.log('添加事件成功')
            }
          })
          // eslint-disable-next-line no-unused-vars
          .catch(failResponse => {
          })
    }
  },
}
</script>
<style>

.chanellist_body {
  margin-top: 10px;
  padding-left: 10px;
  padding-right: 10px;
}

.add_btn {
  margin-top: 20px;
  margin-bottom: 5px;
  margin-right: 10px;
  float: right;
  width: auto;
  height: auto;
}

.chanelist_table {
  border-radius: 4px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

</style>