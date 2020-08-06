<template>
  <div>
    <el-row>
      <el-col :span="5" v-for="(room, index) in roomlist" :key="index" :offset="index > 0 ? 2 : 0">
        <el-card :body-style="{ padding: '0px' }">
          <img v-bind:src=room.picpath
               @click=toroom(room.id)
               class="image">
          <div style="padding: 14px;">
            <span class="text center-pill">{{room.name}}</span>
            <br/>
            <span>正在播放：{{room.videoname}}</span>
            <div class="bottom clearfix">
              <time class="time">{{ currentDate }}</time>
              <el-button type="text" class="button" @click=toroom(room.id)>进入房间</el-button>
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
      roomlist: [
        // {
        //   id: 1,
        //   name: 'room1',
        //   detail: 'detail',
        //   picpth: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
        //   userid: '1',
        //   videoname: 'video1'
        // },
        // {
        //   id: 2,
        //   name: 'room2',
        //   detail: 'detail2',
        //   picpth: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
        //   userid: '1',
        //   videoname: 'video2'
        // }
      ]
    }
  },
  mounted () {
    this.InitRoomlist()
  },
  methods: {
    toroom (roomid) {
      this.$router.push('/chat?roomid=' + roomid)
    },
    InitRoomlist () {
      var that = this
      // 获取房间信息
      this.$http.get('/listRooms').then(function (response) {
        console.log(response.data.data)
        that.roomlist = response.data.data
      }, function (err) {
        console.log('getRoombyID faile' + err)
      })
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
    width: 250px;
    height: 250px;
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
