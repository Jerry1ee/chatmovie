<template>
  <div class="myself_container">
    <!--面包屑-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>我的资料</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">
    <el-col :span="5">
      <el-upload
        class="avatar-uploader"
        action="https://jsonplaceholder.typicode.com/posts/"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-col>
      <el-col :span="9">
        <div class="text item">
          {{'用户名： ' + name }}
        </div>
        <div class="text item">
          {{'电话号码： ' + mobilePhone }}
        </div>
        <div class="text item">
          {{'交友宣言： ' + "快乐电影 快乐聊天！" }}
        </div>
      </el-col>
    </el-card>

  </div>

</template>

<script>
  export default {
    data() {
      return {
        //查询参数
        queryInfo:{
          phoneNumber:window.sessionStorage.getItem('phoneNumber')
        },
        imageUrl: 'http://172.19.240.176/videos/avatar.png',
        name: window.sessionStorage.getItem('name'),
        mobilePhone:window.sessionStorage.getItem('phoneNumber'),
        avatarURL:''
      };
    },
    created(){
      // this.getInformation()
    },
    methods: {

      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);

        this.$message.success("修改成功！")
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      // async getInformation(){
      //   const{data:res} =await this.$http.get('/userByPhone',{params:this.queryInfo})
      //   this.name = res.name;
      //   this.mobilePhone = res.mobilePhone
      // }
    }
  }
</script>

<style lang="less" scoped>
  .myself_container{

    height: 100%;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }

  .text {
    font-size: 20px;
  }

  .item {
    margin-bottom: 25px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

</style>
