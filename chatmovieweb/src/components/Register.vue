<template>
  <div class="register_container">
    <div class="register_box">
      <!--注册表单-->
      <!--ref 表单对象的引用
          class css 样式
          :model 表单绑定的对象
          :rules 表单的验证规则-->
      <el-form ref="registerFormRef" class="register_form" :model="registerForm" :rules="registerFormRules" label-width="80px" status-icon>
        <!--昵称-->
        <el-form-item label="昵称" prop="name">
          <el-input v-model="registerForm.name" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <!--密码-->
        <el-form-item label="密码" prop="password">
          <el-input v-model="registerForm.password" prefix-icon="el-icon-lock" type="password"></el-input>
        </el-form-item>
        <!--确认密码-->
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input v-model="registerForm.confirmPassword" prefix-icon="el-icon-lock" type="password"></el-input>
        </el-form-item>
        <!--手机号码-->
        <el-form-item label="手机号码" prop="mobilePhone">
          <el-input v-model="registerForm.mobilePhone" prefix-icon="el-icon-user"></el-input>
        </el-form-item>

        <!--验证码-->
        <el-row :gutter="15">
          <el-col :span="30" >
            <el-form-item label="验证码" prop="code" >
              <el-input v-model="registerForm.code" prefix-icon="el-icon-lock"></el-input>
            </el-form-item>
          </el-col>
          <el-col  :span="4">
            <el-button type="primary" @click="sendMsg" :disabled="isDisabled">{{buttonName}}</el-button>
          </el-col>
        </el-row>
        <!--按钮-->
        <el-form-item class="buttons">
          <el-button type="primary" @click="register">注册</el-button>
          <el-button type="info" @click="resetRegisterForm">重置</el-button>
          <el-button type="info" @click="cancel" >取消</el-button>
        </el-form-item>
      </el-form>
      <!--注册表单-->
    </div>
  </div>

</template>

<script>
  export default {
    data() {
      var checkPhoneNumber = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('电话号码不能为空'));
        }
        setTimeout(() => {
          const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
          if (reg.test(value)) {
            callback()
          } else {
            return callback(new Error('请输入正确的电话'))
          }
        }, 1000);
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.registerForm.confirmPassword !== '') {
            this.$refs.registerFormRef.validateField('confirmPassword');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.registerForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        registerForm: {
          code: '',
          mobilePhone: '',
          password: '',
          name: '',
          confirmPassword: ''
        },
        registerFormRules: {
          mobilePhone:[  { validator: checkPhoneNumber, trigger: 'blur' }
          ],
          password:[  { validator: validatePass, trigger: 'blur' },
            { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
          ],
          confirmPassword:[  { validator: validatePass2, trigger: 'blur' },
            { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
          ],
          code:[
            {  max: 6, message: '验证码格式错误', trigger: 'blur' }
          ],
          name:[
            {  max: 10, message: '用户名太长了', trigger: 'blur'},
            { pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+$/, message: '不允许输入空格等特殊符号' }
          ],

        },
        buttonName:'发送验证码',
        isDisabled:false,
        totalTime: 61,

      }
    },
    methods: {
      sendMsg() {

        this.$refs.registerFormRef.validate(async(valid)=>{
          console.log("表单验证结果:"+valid)
          if(!valid)return;
          const {data:res} = await this.$http.post('sendmsg',this.registerForm.mobilePhone)

          if(res.code!=20000) return this.$message.error("发送短信失败！")
          this.$message.success("发送短信成功！")

          this.isDisabled=true;
          let interval = window.setInterval(()=>{
            this.buttonName = this.totalTime-1+'s 后重新发送'
            --this.totalTime
            if(this.totalTime<=0)
            {
              this.buttonName ="重新发送";
              this.totalTime=61;
              this.isDisabled = false;
              window.clearInterval(interval);
            }
          },1000)
        })

      },
      register() {
        this.$refs.registerFormRef.validate(async (valid)=>{
          console.log("表单验证结果:"+valid)
          if(!valid)return;
          const {data:res} = await this.$http.post('register',this.registerForm)
          if(res.code!=20000)return this.$message.error(res.message)
          this.$message.success("注册成功！")
          this.$router.push('/login')
        })


      },
      resetRegisterForm() {
        this.$refs.registerFormRef.resetFields();
      },
      cancel()
      {
        this.$router.push('/login')
      }


    }
  }
</script>

<style lang="less" scoped>
  .register_container {
    background-color: #25606b;
    height: 100%;
  }

  .register_box {
    width: 650px;
    height: 400px;
    background-color: #fff;
    border-radius: 5px;
    position: absolute;
    left: 50%;
    top: 50%;
    /*相对于左上角的位置，要减去自身的面积*/
    transform: translate(-50%, -50%);
    }

  .register_form{
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0px 100px;
    box-sizing: border-box;

  }
  .buttons{
  }
</style>
