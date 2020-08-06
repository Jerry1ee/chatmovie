<template>
  <el-container class="home-container">
    <!-- 头部区域 -->
    <el-header>
      <div>
        <img src="../assets/movie.png" alt="" height="60px">
        <span>在线放映室</span>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <!-- 主题部分 -->
    <el-container>
      <!-- 侧边栏 -->

      <el-aside :width="isCollapse ? '64px':'200px'">
        <!--折叠按钮-->
        <div class="toggle-button" @click="toggleCollapse">|||</div>
        <!-- 侧边栏菜单区域 -->
        <el-menu
          background-color="#323b3d"
          text-color="#fff"
          active-text-color="#ffd04b"
          unique-opened
          :collapse=isCollapse
          :collapse-transition="false"
          router
          default-active="">
          <!--一级菜单-->
          <!--聊天室测试-->
          <el-menu-item index="home">
            <!--一级菜单的模板区域-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-location"></i>
              <!--文本-->
              <span>主页</span>
            </template>

          </el-menu-item>

          <!--一级菜单-->
          <el-menu-item index="roomlist">
            <!--一级菜单的模板区域-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-location"></i>
              <!--文本-->
              <span>浏览房间</span>
            </template>
          </el-menu-item>

          <!--一级菜单-->
          <el-submenu index="2">
            <!--一级菜单的模板区域-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-location"></i>
              <!--文本-->
              <span>资源管理</span>
            </template>
            <!--二级菜单-->
            <el-menu-item index="video">
              <!--图标-->
              <i class="el-icon-location"></i>
              <!--文本-->
              <span>视频管理</span>
            </el-menu-item>
          </el-submenu>

          <!--一级菜单-->
          <el-submenu index="3">
            <!--一级菜单的模板区域-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-location"></i>
              <!--文本-->
              <span>我的</span>
            </template>
            <!--二级菜单-->
            <el-menu-item index="myself">
              <!--图标-->
              <i class="el-icon-location"></i>
              <!--文本-->
              <span>我的资料</span>
            </el-menu-item>
          </el-submenu>

          <!--          &lt;!&ndash;聊天室测试&ndash;&gt;-->
          <!--          <el-menu-item index="chat">-->
          <!--            &lt;!&ndash;一级菜单的模板区域&ndash;&gt;-->
          <!--            <template slot="title">-->
          <!--              &lt;!&ndash;图标&ndash;&gt;-->
          <!--              <i class="el-icon-location"></i>-->
          <!--              &lt;!&ndash;文本&ndash;&gt;-->
          <!--              <span>聊天室测试</span>-->
          <!--            </template>-->

          <!--          </el-menu-item>-->

        </el-menu>

      </el-aside>
      <!-- 右侧内容主题体 -->
      <el-main>
        <!--路由占位符-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
export default {
  data () {
    return {

      isCollapse: false
    }
  },

  methods: {
    logout () {
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    toggleCollapse () {
      // 点击按钮,切换菜单折叠与展开
      this.isCollapse = !this.isCollapse
    }

  }
}
</script>

<style lang="less" scoped>
  .home-container {
    height: 100%;
  }

  .el-header {
    background-color: #4a5355;
    display: flex;
    justify-content: space-between;
    padding-left: 0;
    align-items: center;
    color: #fff;
    font-size: 30px;

    > div {
      display: flex;
      align-items: center;

      span {
        margin-left: 20px;
      }
    }
  }

  .el-aside {
    background-color: #323b3d;

    .el-menu {
      border-right: none;
    }
  }

  .el-main {
    background-color: #f7f6f5;
  }

  .toggle-button {
    background-color: #788f91;
    font-size: 10px;
    color: #fff;
    line-height: 24px;
    text-align: center;
    letter-spacing: 0.2em;
    cursor: pointer;
  }
</style>
