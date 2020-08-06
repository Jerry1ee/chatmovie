<template>
  <div class="chat_container">
    <!--面包屑-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>{{"聊天室："+room.roomname}}</el-breadcrumb-item>
    </el-breadcrumb>

    <!--卡片视图区域-->
    <el-card class="box-card">
      <h3>正在播放：{{video.videoname}}</h3>
      <el-col span="18">
        <el-row :gutter="10">
          <video
            id="myVideo"
            class="video-js"
            width='510'
          >
            <source
              src=''
              type="video/mp4"
            >
          </video>
        </el-row>

      </el-col>

      <!--聊天表单-->
      <el-form ref="chatFormRef" :model="chatForm">
        <!--消息框-->
        <el-col :span="6">
          <el-form-item prop="chatArea">
            <el-input type="textarea" v-model="chatForm.chatArea" :rows="25"></el-input>
          </el-form-item>
        </el-col>

        <el-row :gutter="20">
          <!--          <el-col :span="2">-->
          <!--            &lt;!&ndash;按钮&ndash;&gt;-->
          <!--            <el-form-item class="buttons">-->
          <!--              <el-button type="primary" @click="beginVideo">播放</el-button>-->
          <!--            </el-form-item>-->
          <!--          </el-col>-->
          <el-col :span="3">
            <!--按钮-->
            <el-form-item class="buttons">
              <el-button id="pause" type="primary" @click="pauseVideo">播放/暂停</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="3">
            <!--按钮-->
            <el-form-item class="buttons">
              <el-button type="primary" @click="sendTimeStamp">设置进度</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="3">
            <!--按钮-->
            <el-form-item class="buttons">
              <el-button type="primary" @click="synVideo">同步播放</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <!--输入框-->
            <el-form-item prop="message">
              <el-input v-model="chatForm.message" clearable @keyup.enter.native=send></el-input>
            </el-form-item>

          </el-col>
          <el-col :span="3">
            <!--按钮-->
            <el-form-item class="buttons">
              <el-button type="primary" @click="send">发送消息</el-button>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
    </el-card>

  </div>

</template>

<script>
  import Video from 'video.js'

  export default {
    data() {
      return {
        chatForm: {
          chatArea: '',
          message: ''
        },
        path:"ws://172.19.240.176:8088/ws",
        // path:"ws://localhost:8088/ws",
        socket: '',
        myPlayer: '',
        videoCurrentTime: 0,
        room: {
          roomdetail: 'detail',
          roomid: this.$route.query.roomid,
          roomname: 'roomname',
          timestamp: 0
        },
        video: {
          picpath: '',
          videopath: '',
          videoname: ''
        },
        play: '播放'
      }
    },
    mounted() {
      this.init()
      if (this.$route.query.roomid != null) {
        this.initRoom(this.$route.query.roomid)
      }
      this.initVideo()
    },
    methods:
      {
        initVideo() {
          // 初始化视频方法
          this.myPlayer = this.$video(myVideo, {
            // 确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。
            // true/false,
            // controls: true,
            // 自动播放属性,muted:静音播放 false/muted/true
            autoplay: true,
            // 建议浏览器是否应在<video>加载元素后立即开始下载视频数据。auto / nano
            preload: 'nano',
            // 设置视频播放器的显示宽度（以像素为单位）
            // width: "800px",
            // //设置视频播放器的显示高度（以像素为单位）
            // height: "400px",
            fluid: true,
            // src: 'http://172.19.240.176/videos/RickandMortyS03E04.mp4'
            loop: true
            // poster: this.video.picpath
          })
        },
        initRoom (id) {
          var that = this
          // 获取房间信息
          this.$http.get('/getRoombyID/' + id).then(function (response) {
            console.log(response.data.data)
            let room = response.data.data
            that.room.roomname = room.name
            that.room.roomdetail = room.detail
            that.video.videopath = room.videopath
            that.video.videoname = room.videoname
            that.video.picpath = room.picpath
            // 更换视频src
            that.myPlayer.src(room.videopath)
            that.myPlayer.poster(room.picpath)
          }, function (err) {
            console.log('getRoombyID faile' + err)
          })
        },
        init: function () {
          if (typeof (WebSocket) === 'undefined') {
            this.$message.warning('您的浏览器不支持socket')
          } else {
            // 实例化socket
            this.socket = new WebSocket(this.path)
            // 监听socket连接
            this.socket.onopen = this.open
            // 监听socket消息
            this.socket.onmessage = this.onmessage
            // 监听socket关闭
            this.socket.onclose = this.onclose
          }
        },
        // 控制聊天
        open: function (event) {
          this.chatForm.chatArea = '连接开启！'
          // 构建聊天消息
          this.socket.send(window.sessionStorage.getItem('name'))
        },
        onmessage: function (msg) {
          this.chatForm.chatArea = this.chatForm.chatArea + '\n' + msg.data
        },
        onclose: function () {
          this.chatForm.chatArea = this.chatForm.chatArea + '\n连接关闭'
        },
        send: function () {
          if (!window.WebSocket) {
            return
          }
          if (this.socket.readyState == WebSocket.OPEN) {
            this.socket.send(this.chatForm.message)
            this.chatForm.message = ''
          } else {
            alert('连接没有开启！')
          }
        },
        sendTimeStamp: function () {
          let timestamp = Math.floor(this.myPlayer.currentTime())
          if (!window.WebSocket) {
            return
          }
          if (this.socket.readyState == WebSocket.OPEN) {
            this.$http.get('/updateVideotime/' + this.room.roomid + '/' + timestamp).then(function (response) {
              console.log(response.data.message)
            }, function (err) {
              console.log('updateVideotime faile' + err)
            })
            this.socket.send('我更新了进度：' + Video.formatTime(timestamp))
            this.chatForm.message = ''
          } else {
            alert('连接没有开启！')
          }
        },

        // 控制视频
        // 开始
        beginVideo: function () {
          this.myPlayer.play()
          // that.play = '暂停'
        },
        // 暂停
        pauseVideo: function () {
          if (this.myPlayer.paused()) {
            this.myPlayer.requestFullscreen()
            this.myPlayer.play()
            // this.play = '暂停'
          } else {
            this.myPlayer.pause()
            // this.play = '播放'
          }
        },
        // 同步
        synVideo: function () {
          var that = this
          this.$http.get('/synVideo/' + this.room.roomid).then(function (response) {
            let timestamp = response.data.data
            console.log(timestamp)
            that.videoCurrentTime = that.myPlayer.currentTime()
            that.myPlayer.currentTime(timestamp)
          }, function (err) {
            console.log('synVideo faile')
          })
          this.myPlayer.play()
          this.play = '暂停'
        }

      }
}

</script>

<style lang="less" scoped>
  .chat_container {
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
