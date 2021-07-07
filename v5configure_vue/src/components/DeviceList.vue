<template>
  <body class="devicelist_body">

  <el-button type="text" @click="handleClick(-1,2)" class="add_btn" align="left">新增</el-button>

  <el-table
      class="devices_table"
      :data="devicelist">
    <el-table-column
        prop="name"
        align="center"
        label="设备名称">
    </el-table-column>
    <el-table-column
        align="center"
        prop="alias"
        label="设备别名">
    </el-table-column>
    <el-table-column
        align="center"
        prop="device_id"
        label="设备号">
    </el-table-column>
    <el-table-column
        align="center"
        prop="device_group"
        label="设备分组">
    </el-table-column>

    <el-table-column
        align="center"
        prop="operator_name"
        label="操作人">
    </el-table-column>

    <el-table-column
        align="center"
        prop="time"
        label="操作时间">
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


  <el-dialog title="设备详情" width="30%" v-if="cleanDialog" :visible.sync="dialogFormVisible" @close='closeDialog()'>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" :label-width="formLabelWidth" :disabled="inputEnable">
      <el-form-item label="设备名称" prop="name">
        <el-input v-model="ruleForm.name" placeholder="设备名称"></el-input>
      </el-form-item>
      <el-form-item label="别名" prop="alias">
        <el-input v-model="ruleForm.alias" placeholder="别名"></el-input>
      </el-form-item>
      <el-form-item label="设备号" prop="device_id">
        <el-input v-model="ruleForm.device_id" placeholder="设备号"></el-input>
      </el-form-item>
      <el-form-item label="设备分组" prop="device_group" >
        <el-select v-model="ruleForm.device_group" placeholder="请选择" style="width:100%">
          <el-option
              v-for="item in grouplist"
              :key="item.name"
              :label="item.name"
              :value="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="图标" :label-width="formLabelWidth" prop="cover" style="align-items: start">
        <el-col :span="14">
          <el-input v-model="ruleForm.image" autocomplete="off" placeholder="图片 URL" clearable></el-input>
        </el-col>
        <el-col :span="10">
          <img-upload @onUpload="uploadImg" ref="imgUpload"></img-upload>
        </el-col>
      </el-form-item>

      <el-form-item label="备注" prop="note">
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
import ImgUpload from './ImgUpload'

export default {

  name: 'DeviceList',
  components: {ImgUpload},

  data() {
    return {
      btnTxt: '',
      devicelist: [],
      grouplist: [],
      cleanDialog: false,
      dialogFormVisible: false,
      fileList: [],
      ruleForm: {
        id: '',
        name: '',
        alias: '',
        device_id: '',
        device_group: '',
        operator_name: '',
        note: '',
        image: ''
      },
      formLabelWidth: '120px',
      dialogType: 0,  // 0 > 查看 ; 1 > 编辑 ; 2 > 新增 ; 3> 删除
      inputEnable: false,
      username: '',
      device: '',
      type: 0,
      date: '',
      rules: {
        name: [
          {required: true, message: '请输入设备名称', trigger: 'blur'},
        ],
        alias: [
          {required: true, message: '请输入设备别名', trigger: 'blur'}
        ],
        device_id: [
          {required: true, message: '请输入设备编码', trigger: 'blur'}
        ],
        device_group: [
          {required: true, message: '请选择设备分组', trigger: 'blur'}
        ],
        note: [
          {required: false, message: '备注', trigger: 'blur'}
        ]
      }

    }
  },
  mounted() {
    this.queryall(),
        this.queryAllGroup()
  },
  methods: {
    handleRemove() {
    },
    handlePreview() {
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove(file) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    handleSuccess(response) {
      // this.url = response
      this.ruleForm.image = response
      this.$emit('onUpload')
      this.$message.warning('上传成功')
    },
    clear() {
      this.$refs.upload.clearFiles()
    },
    uploadImg() {
      this.ruleForm.image = this.$refs.imgUpload.url
    },
    queryall() {
      var _this = this
      let url = '/queryalldevice';
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.devicelist = resp.data.data;
        } else {
          _this.devicelist = null;
        }
      })
    },
    queryAllGroup() {
      var _this = this
      let url = '/queryalldevicegroup';
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.grouplist = resp.data.data;
        } else {
          _this.grouplist = null;
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
          var detailinfo = _this.devicelist[index];
          this.filldata(detailinfo)
          break;
        case 1:  // 编辑
          _this.cleanDialog = true
          _this.dialogFormVisible = true
          _this.inputEnable = type == 0
          var editinfo = _this.devicelist[index];
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
          this.delete(_this.devicelist[index])
          break;
      }


    },
    filldata(info) {
      this.ruleForm = info
    },

    delete(info) {


      this.device = info.name
      this.$axios
          .delete('/remove/' + info.id).then(resp => {
        if (resp && resp.data.code === 200) {
          this.queryall()
          // this.addevent()
        }
      })
    },

    submitForm() {


      var _this = this;
      _this.device = _this.ruleForm.name
      _this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.dialogFormVisible = false
          if (this.ruleForm.id == null) {

            this.$axios
                .post('/appenddevice', {
                  name: _this.ruleForm.name,
                  alias: _this.ruleForm.alias,
                  device_id: _this.ruleForm.device_id,
                  device_group: _this.ruleForm.device_group,
                  time: this.$moment(new Date()).format('YYYY-MM-DD  HH:mm:ss'),
                  operator_name: this.$store.state.user.username,
                  note: _this.ruleForm.note,
                  image: _this.ruleForm.image
                })
                .then(successResponse => {
                  if (successResponse.data.code === 200) {
                    _this.queryall()
                    // _this.addevent()
                  }
                })
                // eslint-disable-next-line no-unused-vars
                .catch(failResponse => {
                })
          } else {
            _this.$axios
                .post('/updatedevice', {
                  id: _this.ruleForm.id,
                  name: _this.ruleForm.name,
                  alias: _this.ruleForm.alias,
                  device_id: _this.ruleForm.device_id,
                  device_group: _this.ruleForm.device_group,
                  time: this.$moment(new Date()).format('YYYY-MM-DD  HH:mm:ss'),
                  operator_name: this.$store.state.user.username,
                  note: _this.ruleForm.note,
                  image: _this.ruleForm.image
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
        alias: '',
        device_id: '',
        device_group: '',
        time: '',
        operator_name: '',
        note: '',
        image: '',
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

      // this.$axios
      //     .post('/addevent', {
      //       username: this.$store.state.user.username,
      //       channelinfo: this.device,
      //       type: this.dialogType,
      //       date: this.$moment(new Date()).format('YYYY-MM-DD  HH:mm:ss'),
      //     })
      //     .then(successResponse => {
      //       if (successResponse.data.code === 200) {
      //         console.log('添加事件成功')
      //       }
      //     })
      //     // eslint-disable-next-line no-unused-vars
      //     .catch(failResponse => {
      //     })
    }
  },
}
</script>
<style>

.devicelist_body {
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

.devices_table {
  border-radius: 4px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

</style>
