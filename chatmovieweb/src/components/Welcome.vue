<template>
  <div>
    <h3>
      {{'欢迎登录！尊敬的 ' + name }}
    </h3>
    <el-row :gutter="50">
      <el-col :span="5">
        <el-card :body-style="{ padding: '0px' }">
          <img src="http://172.19.240.176/videos/BlackMirror.png"
               class="image">
          <div style="padding: 14px;">
            <span>黑镜</span>
            <div class="bottom clearfix">
              <time> 新上映的视频资源！</time>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card :body-style="{ padding: '0px' }">
          <img src="http://172.19.240.176/videos/RickAndMorty.png"
               class="image">
          <div style="padding: 14px;">
            <span>瑞克与莫蒂第三季第四集</span>
            <div class="bottom clearfix">
              <time> 新上映的视频资源！</time>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card :body-style="{ padding: '0px' }">
          <img src="http://172.19.240.176/videos/RickAndMorty.png"
               class="image">
          <div style="padding: 14px;">
            <span>瑞克与莫蒂第三季第五集</span>
            <div class="bottom clearfix">
              <time> 新上映的视频资源！</time>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>

</template>

<script>

export default {
  data () {
    return {
      name: window.sessionStorage.getItem('name'),
      currentDate: new Date()
    }
  }
}
</script>

<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
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
