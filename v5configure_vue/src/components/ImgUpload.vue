<template>
  <el-upload
      class="img-upload"
      ref="upload"
      action="/api/uploadfile"
      :show-file-list="true"
      with-credentials
      multiple
      :limit="15"
      :file-list="fileList"
      :on-preview="handlePreview"
      :on-success="handleSuccess">
    <el-button size="small" type="primary">上传文件</el-button>
    <!--    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
  </el-upload>
</template>
<!--:before-remove="beforeRemove"-->
<!--:on-remove="handleRemove"-->
<!--:file-list="fileList"-->
<!--:on-exceed="handleExceed"-->
<!--multiple-->
<!--:limit="1"-->
<script>
export default {
  // name: 'ImgUpload',
  data() {
    return {
      fileList: [],
      url: '',
    }
  },
  methods: {
    handleRemove(file, fileList) {
      console.log( file, fileList)
    },
    handlePreview(file) {
      console.log(file)
      // 此处的 file 是整个文件
      // console.log(file.response)
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    // beforeRemove (file) {
    //   return this.$confirm(`确定移除 ${file.name}？`)
    // },
    handleSuccess(response) {

      this.url = response
      console.log("handleSuccess >> "+response)

      this.$emit('onUpload')
      this.$message.warning('上传成功')
    },
    clear() {
      this.$refs.upload.clearFiles()
    }
  }
}
</script>

<style scoped>
.img-upload {
  /*height: 200px;*/
}
</style>
