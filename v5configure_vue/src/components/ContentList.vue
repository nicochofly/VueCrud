<template>
  <body class="contents_body">

  <el-button type="text" @click="handleClick(-1,2)" class="add_btn" align="left">新增</el-button>

  <el-table
      class="contents_table"
      :data="contentlist">
    <el-table-column
        prop="name"
        align="center"
        label="名称">
    </el-table-column>
    <el-table-column
        align="center"
        prop="type_name"
        label="类型">
    </el-table-column>
    <el-table-column
        align="center"
        prop="category"
        label="类别">
    </el-table-column>
    <el-table-column
        align="center"
        prop="date"
        label="操作时间">
    </el-table-column>
    <el-table-column
        align="center"
        prop="operator_name"
        label="操作人"
    >
    </el-table-column>
    <el-table-column
        align="center"
        prop="content"
        label="content">
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
        <el-button @click="handleClick(scope.$index,4)" type="text" size="small">推送</el-button>

      </template>
    </el-table-column>
  </el-table>


  <el-dialog title="推送详情" width="30%" v-if="cleanDialog" :visible.sync="dialogFormVisible" @close='closeDialog()'>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" :label-width="formLabelWidth" :disabled="inputEnable">
      <el-form-item label="名称" prop="name">
        <el-input v-model="ruleForm.name" placeholder="名称"></el-input>
      </el-form-item>
      <el-form-item label="内容" prop="content">
        <el-input v-model="ruleForm.content" placeholder="内容"></el-input>
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="ruleForm.type" placeholder="请选择" style="width:100%">
          <el-option
              v-for="item in types"
              :key="item.name"
              :label="item.name"
              :value="item">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="类别" prop="category">
        <el-select v-model="ruleForm.category" placeholder="请选择" style="width:100%" value-key="id">
          <el-option
              v-for="item in categorys"
              :key="item.id"
              :label="item.name"
              :value="item">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="有效期" prop="expirationdate"
                    v-if="this.dialogType!=0 || (ruleForm.expirationdate[0]>0&&ruleForm.expirationdate[1]>0)">

        <el-date-picker
            v-model="ruleForm.expirationdate"
            type="datetimerange"
            value-format="timestamp"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            align="right">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="备注" prop="note">
        <el-input v-model="ruleForm.note" placeholder="备注"></el-input>
      </el-form-item>
      <el-form-item label="资源文件" :label-width="formLabelWidth" prop="cover" style="align-items: start"
                    v-if="ruleForm.type.value>0">
        <el-col :span="17">
          <el-input v-model="ruleForm.resource_str" autocomplete="off" placeholder="资源路径 URL" clearable></el-input>
        </el-col>
        <el-col :span="7">
          <img-upload @onUpload="uploadImg" ref="imgUpload"></img-upload>
        </el-col>
      </el-form-item>
      <el-form-item v-show="!inputEnable">
        <el-button type="primary" @click="submitForm()" v-text='btnTxt'>立即创建</el-button>
        <el-button @click="dialogFormVisible = false">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>


  <el-dialog title="推送设备" width="30%" v-if="push_dialog_show" :close-on-click-modal='false'
             :visible.sync="push_dialog_form_visible"
             @close='closeSelectDialog()'>
    <el-cascader
        ref="cascader"
        :options="push_dialog_data"
        :props="push_dialog_props"
        collapse-tags
        clearable
        @change="handleChange"></el-cascader>

    <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="doPushRequest()" v-text='pushTxt'>立即创建</el-button>
        <el-button @click="push_dialog_form_visible = false">取消</el-button>
      </span>
  </el-dialog>
  </body>
</template>

<script>
import ImgUpload from './ImgUpload'

export default {
  name: 'ContentList',
  components: {ImgUpload},
  data() {
    return {
      push_dialog_show: false,
      currentpushcontent: {},
      push_dialog_form_visible: false,
      push_dialog_data: [],
      push_dialog_props: {
        multiple: true,
        checkStrictly: false,
        lazy: true, // 此处必须为true
        lazyLoad: (node, resolve) => {
          this.getSubData(node.data, resolve)
        }
      },
      btnTxt: '',
      pushTxt: '立即推送',
      contentlist: [],
      devicegroups: [],
      categorys: [],
      types: [{name: '文字', value: 0}, {name: '图片', value: 1}, {name: '视频', value: 2}],
      cleanDialog: false,
      dialogFormVisible: false,
      ruleForm: {
        id: '',
        name: '',
        content: '',
        type: {name: '文字', value: 0},
        category: {},
        date: '',
        operator_name: '',
        operator_id: 0,
        resource_path: [],
        resource_str: '',
        release: false,
        expirationdate: [],
      },
      formLabelWidth: '120px',
      dialogType: 0,  // 0 > 查看 ; 1 > 编辑 ; 2 > 新增 ; 3> 删除
      inputEnable: false,
      username: '',
      date: '',


      rules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'},
        ],
        content: [
          {required: true, message: '请输入推送内容', trigger: 'blur'}
        ],
        type: [
          {required: true, message: '请选择类型', trigger: 'blur'}
        ],
        category: [
          {required: true, message: '请选择类别', trigger: 'blur'}
        ],
      },
    }
  },

  mounted() {
    this.queryall();
    this.queryCategoryGroup();
    this.queryDeviceGroup()
  },
  methods: {
    //=============================级联选择器相关请求===========================================//
    handleChange(value) {

      console.log("handleChange value >> " + value);
      // console.log("value.length >> " + (value.length))
      if (value.length > 0) {
        // 获取当前选中节点
        let checkNodes = this.$refs['cascader'].getCheckedNodes()
        // let checkNode = checkNodes[0];
        checkNodes.forEach((cn, index) => {
          console.log(">>>>" + index + " label " + cn.label + " isLeaf " + cn.isLeaf + "  cn.value " + cn.value);
        });
        // 将当前选中节点数据和当前路径数组返回给父组件
        // console.log("checkNode >> " + (checkNode[0].data) + "   " + checkNode[0].pathLabels)
        // this.$emit('getData', checkNode[0].data, checkNode[0].pathLabels)
      } else {
        // this.$emit('getData', {}, [])
      }
    },
    // 获取第一层数据
    queryDeviceGroup() {
      let _this = this
      let url = '/queryalldevicegroup';
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.push_dialog_data = resp.data.data.map(item => ({
            value: item.alias,
            label: item.name,
            leaf: false
          }))
        } else {
          _this.push_dialog_data = null;
        }
      })
    },
// 获取子层数据
    getSubData(data, resolve) {
      let url = '/finddevicebygroupname?group_name=' + data.label;
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          const nodes = resp.data.data.map(item => ({
            value: item.id,
            label: item.name,
            leaf: true,
          }))
          resolve(nodes)
        }
      })
    },
    //=============================资源操作相关请求===========================================//

    uploadImg() {
      this.ruleForm.resource_path.push(this.$refs.imgUpload.url)
      this.ruleForm.resource_str = this.processStringArray()
    },

    //=============================资源操作相关请求===========================================//
    queryall() {
      var _this = this
      let url = '/queryallcontent';
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.contentlist = resp.data.data;
        } else {
          _this.contentlist = null;
        }
      })
    },
    handleClick(index, type) {
      let _this = this
      _this.dialogType = type
      switch (type) {
        case 0:  //查看
          _this.cleanDialog = true
          _this.dialogFormVisible = true
          _this.inputEnable = type == 0
          var detailinfo = _this.contentlist[index];
          this.filldata(detailinfo)
          break;
        case 1:  // 编辑
          _this.cleanDialog = true
          _this.dialogFormVisible = true
          _this.inputEnable = type == 0
          var editinfo = _this.contentlist[index];
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
          this.delete(_this.contentlist[index])
          break;
        case 4:
          _this.push_dialog_show = true;
          _this.push_dialog_form_visible = true;
          _this.currentpushcontent = _this.contentlist[index];
          _this.queryDeviceGroup();
          break;
      }
    },
    filldata(info) {
      this.ruleForm.id = info.id,
          this.ruleForm.name = info.name,
          this.ruleForm.content = info.content,
          this.ruleForm.type = {name: info.type_name, value: info.type_id},
          this.ruleForm.category_id = info.category_id,
          this.ruleForm.category = {name: info.category, id: info.category_id},
          this.ruleForm.operator_name = info.operator_name,
          this.ruleForm.operator_id = info.operator_id,
          this.ruleForm.date = info.date,
          this.ruleForm.resource_path = info.resource_path.split(";"),
          this.ruleForm.resource_str = info.resource_path,

          this.ruleForm.expirationdate.push(info.expirationdate_start),
          this.ruleForm.expirationdate.push(info.expirationdate_end);

    },

    delete(info) {
      this.channelinfo = info.name
      this.$axios
          .delete('/removecontent/' + info.id).then(resp => {
        if (resp && resp.data.code === 200) {
          this.queryall()
          this.addevent()
        }
      })
    },

    // test() {
    //   console.log(this.ruleForm.category.id)
    //   // console.log(this.ruleForm.expirationdate[0]);
    //   // console.log(this.ruleForm.expirationdate[1]);
    // },

    submitForm() {
      var _this = this;
      _this.channelinfo = _this.ruleForm.name
      _this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.dialogFormVisible = false
          if (this.ruleForm.appid == null) {
            this.$axios
                .post('/appendcontent', {
                  id: _this.ruleForm.id,
                  name: _this.ruleForm.name,
                  type_name: _this.ruleForm.type.name,
                  type_id: _this.ruleForm.type.value,
                  category: _this.ruleForm.category.name,
                  category_id: _this.ruleForm.category.id,
                  date: _this.$moment(new Date()).format('YYYY-MM-DD  HH:mm:ss'),
                  operator_name: _this.$store.state.user.username,
                  resource_path: _this.processStringArray(),
                  content: _this.ruleForm.content,
                  release: true,
                  expirationdate_start: _this.ruleForm.expirationdate == null ? "" : _this.ruleForm.expirationdate[0],
                  expirationdate_end: _this.ruleForm.expirationdate == null ? "" : _this.ruleForm.expirationdate[1],

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
                .post('/updatecontent', {
                  id: _this.ruleForm.id,
                  name: _this.ruleForm.name,
                  type_name: _this.ruleForm.type.name,
                  type_id: _this.ruleForm.type.value,
                  category: _this.ruleForm.category.name,
                  category_id: _this.ruleForm.category.category_id,
                  date: _this.$moment(new Date()).format('YYYY-MM-DD  HH:mm:ss'),
                  operator_name: _this.$store.state.user.username,
                  resource_path: _this.processStringArray(),
                  content: _this.ruleForm.content,
                  release: true,
                  expirationdate_start: _this.ruleForm.expirationdate == null ? "" : _this.ruleForm.expirationdate[0],
                  expirationdate_end: _this.ruleForm.expirationdate == null ? "" : _this.ruleForm.expirationdate[1],
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
        content: '',
        type: {name: '文字', value: 0},
        category: {},
        category_id: -1,
        date: '',
        operator_name: '',
        operator_id: 0,
        resource_path: [],
        resource_str: '',
        release: false,
      }
    },
    closeSelectDialog() {
      this.push_dialog_data = [];
      this.ruleForm.push_dialog_show = false;
      this.ruleForm.push_dialog_form_visible = false;
      ++this.isResourceShow;
    },


    //搜索分组
    queryCategoryGroup() {
      var _this = this
      let url = '/queryallcategory';
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.categorys = resp.data.data;
          // _this.ruleForm.category = _this.categorys[0];
          _this.ruleForm.category = {name: _this.categorys[0].name, id: _this.categorys[0].id}
        } else {
          _this.categorys = null;
        }
      })
    },
    doPushRequest() {
      this.push_dialog_form_visible = false;
      this.push_dialog_show = false;
      let checkNodes = this.$refs['cascader'].getCheckedNodes();
      var local_alias = []
      var local_registerid = []
      checkNodes.forEach((cn) => {

        if (cn.children.length > 0) {
          local_alias.push(cn.value)
        } else {
          local_registerid.push(cn.value)
        }
        // console.log("nodes children >>> " + cn.children)
        // console.log("result >>>>" + index + " label " + cn.label + " isLeaf " + cn.leaf + "  cn.value " + cn.value + "   children  " + cn.children.length);
      });

      // console.log("nodes expirationdate_start >>> " + this.currentpushcontent.expirationdate_start)
      // console.log("nodes expirationdate_end >>> " + this.currentpushcontent.expirationdate_end)

      this.$axios
          .post('/pushrequest', {
            alias: local_alias,
            platform: ['all'],
            registerid: local_registerid,
            contentType: this.currentpushcontent.type_id,
            messageContent: this.currentpushcontent.content,
            title: "推送",
            extras: {
              "path": this.currentpushcontent.resource_path.split(";").filter(item => item != ''),
              "time": [this.currentpushcontent.expirationdate_start, this.currentpushcontent.expirationdate_end],
            }
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {

              console.log("successResponse")
            }
          })
          // eslint-disable-next-line no-unused-vars
          .catch(failResponse => {
            console.log("failResponse")
          })
    },
    processStringArray() {
      var result = ''
      this.ruleForm.resource_path.forEach(item => {
        console.log("item > " + item)
        result = result + item + ";"
        console.log("result > " + result)
      })
      return result;
    }
  },


}
</script>
<style>

.contents_body {
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

.contents_table {
  border-radius: 4px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

</style>
