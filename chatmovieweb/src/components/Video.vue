<template>
  <div class="video_container">
    <!--面包屑-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>视频管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!--搜素与添加区域-->
    <div style="margin-top: 15px;">
      <el-row :gutter="20">
        <el-col :span="10">
          <el-input placeholder="请输入内容" v-model="queryName" clearable @clear="getVideoList" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="getVideoListByName"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible=true"> 添加视频信息</el-button>
        </el-col>
      </el-row>
    </div>
    <!--视频列表-->
    <el-table :data="videoList" border stripe>
      <el-table-column label="名称" prop="name"> </el-table-column>
      <el-table-column label="创建者" prop="c_username"> </el-table-column>
      <el-table-column label="详情" prop="description"> </el-table-column>
      <el-table-column label="状态" >已上传 </el-table-column>
      <el-table-column label="操作" prop="">
        <template slot-scope = "scope">
          <el-button type="primary" icon="el-icon-edit"></el-button>
          <!--连接-->
          <el-button type="info" icon="el-icon-share" @click="openVideo(scope.row.path)"></el-button>
          <!--删除-->
          <el-button type="danger" icon="el-icon-delete" @click="deleteById(scope.row.id)"></el-button>
        </template>

      </el-table-column>
    </el-table>

    <!-- 添加视频信息对话框-->
    <el-dialog
      title="添加视频信息"
      :visible.sync="addDialogVisible"
      width="50%"
      @close="addDialogClosed"
      >
      <!--内容主体区域-->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="80px" >
        <el-form-item label="视频名称" prop="name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="视频描述" prop="description">
          <el-input v-model="addForm.description"></el-input>
        </el-form-item>
        <el-form-item label="视频URL" prop="path">
          <el-input v-model="addForm.path"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addVideo">确 定</el-button>
       </span>
    </el-dialog>
  </div>


</template>

<script>
  export default {
    data () {
      return {
        options: {
          // https://github.com/simple-uploader/Uploader/tree/develop/samples/Node.js
          target: 'localhost:8080',

          testChunks: false
        },
        attrs: {
          accept: 'image/*'
        },
        videoList:[],
        total:0,

        //对话框显示与隐藏
        addDialogVisible:false,
        queryName:'',

        //添加用户的表单数据
        addForm:{
          c_user:window.sessionStorage.getItem("id"),
          c_username:window.sessionStorage.getItem("name"),
          description:'',
          name:'',
          path:''
        },
        //添加表单验证规则
        addFormRules:{
          name:[{ required: true, message: '请输入视频名称', trigger: 'blur' }
            ,{ min: 1, max: 50, message: '长度在 6 到 50 个字符', trigger: 'blur' }]
          ,
          description:[{ required: true, message: '请输入视频描述', trigger: 'blur' }
            ,{ min: 1, max: 50, message: '长度在 6 到 50 个字符', trigger: 'blur' }]
            ,
          path:[{ required: true, message: '请输入视频URL', trigger: 'blur' }]
        },

      }
    },
    created(){
      this.getVideoList()
    },
    methods:{
      async getVideoList(){
        const {data:res} = await this.$http.get('/videos')
        console.log(res)
        this.videoList = res
      },
      async getVideoListByName(){
        const {data:res} = await this.$http.get('/videosByName',{params:{name:this.queryName}})
        console.log(res)
        this.videoList = res
      },

      addDialogClosed(){
        this.$refs.addFormRef.resetFields()
      },
      addVideo(){
        this.$refs.addFormRef.validate(async valid => {
          if (!valid) return
          //可以调用添加API
          const {data: res} = await this.$http.post('/video', this.addForm)
          if(res.code!=20000) return this.$message.error("添加失败！")
          this.$message.success("添加成功！")
          //隐藏对话框
          this.addDialogVisible=false
          this.getVideoList()
        })
      },
      async deleteById(id){

        const confirmResult= await this.$confirm('此操作将永久删除该视频, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).catch(err => err)
        //若 用户取消删除，返回值为 'cancel' ，如果用户确定，返回值为 'confirm'
        if(confirmResult!= 'confirm'){
          return this.$message.info("取消删除！")
        }

        const {data:res} = await this.$http.delete('video',{params:{id:id}})
        if(res.code!=20000) return this.$message.error("删除失败！")
        this.$message.success("删除成功！")
        this.getVideoList()

      },
      openVideo(path){

        window.open(path)
      }




    }

  }

</script>

<style lang="less" scoped>
  .video_container{

    height: 100%;
  }

  /*.chat_form{*/
  /*position: absolute;*/
  /*bottom: 0;*/
  /*width: 100%;*/
  /*right: 0;*/
  /*padding: 0 100px;*/
  /*box-sizing: border-box;*/

  /*}*/

  /*.textarea_form{*/
  /*position: absolute;*/
  /*bottom:10px;*/
  /*width: 50%;*/
  /*right: 0;*/
  /*padding: 0 100px;*/
  /*box-sizing: border-box;*/

  /*}*/
</style>
