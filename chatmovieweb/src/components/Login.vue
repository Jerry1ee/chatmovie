<template>
  <div class = 'login_container'>
    <div class="login_box">
      <!--头像区域-->
      <div class="avatar_box">
        <img src="../assets/movie.png" alt="">
      </div>
      <!--头像区域-->

      <!--登录表单-->
        <!--手机号码-->
      <!--ref 表单对象的引用
          class css 样式
          :model 表单绑定的对象
          :rules 表单的验证规则-->
      <el-form ref="loginFormRef" class="login_form" :model="loginForm" :rules="loginFormRules" status-icon>
        <el-form-item prop="mobilePhone">
          <el-input v-model.number="loginForm.mobilePhone" prefix-icon="el-icon-user" ></el-input>
        </el-form-item>
        <!--密码-->
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" prefix-icon="el-icon-lock" type="password"></el-input>
        </el-form-item>
        <!--按钮-->
        <el-form-item class="buttons">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
          <el-button  @click="register">注册</el-button>
        </el-form-item>
      </el-form>
      <!--登录表单-->

    </div>
  </div>
</template>
<script>

export default {
  data() {
    var checkPhoneNumber = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('电话号码不能为空'))
      }
      setTimeout(() => {
        const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
        if (reg.test(value)) {
          callback()
        } else {
          return callback(new Error('请输入正确的电话'))
        }
      }, 1000)
    }

    return {
      // 登录表单的数据绑定对象
      loginForm:
        {
          mobilePhone: '',
          password: '',
          name: '',
          avatarURL: ''
        },
      loginFormRules:
        {
          // 验证电话号是否合法
          mobilePhone: [{validator: checkPhoneNumber, trigger: 'blur'}
          ],

          // 验证密码是否合法
          password: [{required: true, message: '请输入登录密码', trigger: 'blur'},
            {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}]
        }
    }
  },
  methods:
    {
      resetLoginForm() {
        this.$refs.loginFormRef.resetFields()
      },
      login() {
        this.$refs.loginFormRef.validate(async (valid) => {
          console.log('表单验证结果：' + valid)
          if (!valid) return
          const {data: res} = await this.$http.post('login', this.loginForm)

          if (res.code != 20000) return this.$message.error('登录失败！')
          this.$message.success('登录成功！')

          // 登录之后的token，保存到客户端的 sessionStorage 中
          window.sessionStorage.setItem('token', res.data.token)
          window.sessionStorage.setItem('phoneNumber', res.data.phoneNumber)
          window.sessionStorage.setItem('id', res.data.id)
          window.sessionStorage.setItem('name', res.data.name)
          // 编程式导航跳转到后台主页
          this.$router.push('/home')
        })
      },
      register() {
        this.$router.push('/register')
      }

    }

}

</script>

<style lang="less" scoped>
  .login_container{
    background-color: #25606b;
    height: 100%;
  }

  .login_box{
    width: 650px;
    height: 350px;
    background-color: #fff;
    border-radius: 5px;
    position: absolute;
    left: 50%;
    top:50%;
    /*相对于左上角的位置，要减去自身的面积*/
    transform: translate(-50%,-50%);
    .avatar_box{
      height: 150px;
      width: 150px;
      border: 1px solid #fff;
      border-radius: 50%;
      padding: 10px;
      box-shadow: 0 0 10px #ddd;
      position: absolute;
      left: 50%;
      transform: translate(-50%,-50%);
      background-color: #fff;
      img{
        width: 100%;
        height: 100%;
        border-radius: 50%;
        background-color:#eee;

      }
    }
  .buttons{
    display: flex;
    justify-content: center;

  }
  .login_form{
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;

  }
  }

</style>
