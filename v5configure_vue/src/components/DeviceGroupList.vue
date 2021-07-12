<template>
  <body class="devicegroups_body">

  <el-button type="text" @click="handleClick(-1,2)" class="add_btn" align="left">新增</el-button>

  <el-table
      class="groups_table"
      :data="devicegroups">
    <el-table-column
        prop="name"
        align="center"
        label="设备分组名称">
    </el-table-column>
    <el-table-column
        align="center"
        prop="alias"
        label="别名">

    </el-table-column>
    <el-table-column
        align="center"
        prop="note"
        class="note"
        label="备注"
    >
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


  <el-dialog title="分组详情" width="30%" v-if="cleanDialog" :visible.sync="dialogFormVisible" @close='closeDialog()'>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" :label-width="formLabelWidth" :disabled="inputEnable">
      <el-form-item label="分组名称" prop="name">
        <el-input v-model="ruleForm.name" placeholder="分组名称"></el-input>
      </el-form-item>

      <el-form-item label="分组别名" prop="alias">
        <el-input v-model="ruleForm.alias" placeholder="分组别名"></el-input>
      </el-form-item>
      <el-form-item label="备注" prop="note">
        <el-input v-model="ruleForm.note" placeholder="备注"></el-input>
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


  data() {
    return {
      btnTxt: '',
      devicegroups: [],
      cleanDialog: false,
      dialogFormVisible: false,
      url: '',
      ruleForm: {
        id: -1,
        name: '',
        alias: '',
        time: '',
        parentGroupId: 0,
        operator_name: '',
        note: '',
      },
      formLabelWidth: '120px',
      dialogType: 0,  // 0 > 查看 ; 1 > 编辑 ; 2 > 新增 ; 3> 删除
      inputEnable: false,
      username: '',
      type: 0,
      date: '',


      rules: {
        name: [
          {required: true, message: '请输入分组名称', trigger: 'blur'},
        ],
        alias: [
          {required: true, message: '请输入分组别名', trigger: 'blur'},
        ],
        note: [
          {required: false, message: '备注', trigger: 'blur'}
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
      let url = '/queryalldevicegroup';
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.devicegroups = resp.data.data;
        } else {
          _this.devicegroups = null;
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
          var detailinfo = _this.devicegroups[index];

          this.filldata(detailinfo)
          break;
        case 1:  // 编辑
          _this.cleanDialog = true
          _this.dialogFormVisible = true
          _this.inputEnable = type == 0
          var editinfo = _this.devicegroups[index];
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

          this.delete(_this.devicegroups[index])
          break;
      }


    },
    filldata(info) {
      this.ruleForm = info
    },

    delete(info) {
      this.devicegroup = info.name
      this.$axios
          .delete('/removedevicegroup/' + info.id).then(resp => {
        if (resp && resp.data.code === 200) {
          this.queryall()
        }
      })
    },


    // id: '',
    // name: '',
    // parentGroupId: '',
    // operator_id: '',
    // operator_name: '',
    // note: '',
    submitForm() {
      var _this = this;
      _this.devicegroup = _this.ruleForm.name
      _this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.dialogFormVisible = false
          if (this.ruleForm.id == -1) {
            this.$axios
                .post('/appenddevicegroup', {
                  name: _this.ruleForm.name,
                  alias: _this.ruleForm.alias,
                  device_group_id: _this.ruleForm.parentGroupId,
                  operator_name: _this.$store.state.user.username,
                  time: this.$moment(new Date()).format('YYYY-MM-DD  HH:mm:ss'),
                  note: _this.ruleForm.note,
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
                .post('/updatedevicegroup', {
                  id: _this.ruleForm.id,
                  name: _this.ruleForm.name,
                  alias: _this.ruleForm.alias,
                  operator_name: _this.$store.state.user.username,
                  time: this.$moment(new Date()).format('YYYY-MM-DD  HH:mm:ss'),
                  note: _this.ruleForm.note,
                })
                .then(successResponse => {

                  console.log(successResponse.data)
                  if (successResponse.data.code === 200) {
                    _this.queryall()
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
        parentGroupId: 0,
        operator_id: '',
        operator_name: '',
        note: '',
      }
    },


    findbyuuid() {
      // var _this = this
      let url = '/findbyuuid?uuid=8d8d7c73-2333-41d2-9994-c2180aa892f7';
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          // _this.devicegroups = resp.data.data;
        } else {
          // _this.devicegroups = null;
        }
      })
    },

    // addevent() {
    //   this.$axios
    //       .post('/addevent', {
    //         username: this.$store.state.user.username,
    //         devicegroup: this.devicegroup,
    //         type: this.dialogType,
    //         date: this.$moment(new Date()).format('YYYY-MM-DD  HH:mm:ss'),
    //       })
    //       .then(successResponse => {
    //         if (successResponse.data.code === 200) {
    //           console.log('添加事件成功')
    //         }
    //       })
    //       // eslint-disable-next-line no-unused-vars
    //       .catch(failResponse => {
    //       })
    // }
  },
}
</script>
<style>

.devicegroups_body {
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

.groups_table {
  border-radius: 4px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

</style>
