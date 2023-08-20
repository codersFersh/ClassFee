<template>
  <div class="login_container">
    <!-- 这里是用户登录 -->
    <div class="login_box">
      <!--头像区域-->
      <div class="avatar_box">
        <img src="@/assets/logo.png" alt="">
      </div>
      <!--表单-->
      <el-form ref="loginForms" :model="loginForm" :rules="loginFormRules" label-width="0px" class="login_form"
        @keyup.enter.native="loginn">
        <el-switch v-model="value1" class="switchStyle" active-text="管理" inactive-text="用户" active-color="#409eff"
          @click.native="open1" inactive-color="#DCDFE6">
        </el-switch>
        <!--用户名-->
        <el-form-item prop="s_phone">
          <el-input v-model="loginForm.s_phone" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <!--密码-->
        <el-form-item prop="s_password">
          <el-input type="password" show-password v-model="loginForm.s_password" prefix-icon="el-icon-lock"
            placeholder="请输入密码"></el-input>
        </el-form-item>
        <!--按钮-->
        <el-form-item>
          <el-row>
            <el-col :span="15">
              <el-button type="text" style="font-size:10px;">修改密码</el-button>
            </el-col>
            <el-col :span="9">
              <el-button type="primary" @click="loginn" :disabled="!canSubmit">登录</el-button>
              <el-button type="info" @click="resetLoginForm">重置</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
import { PostUserLogin } from '@/network/ssmurl';
export default {

  data() {
    return { // 表单数据
      loginForm: {
        s_phone: '',
        s_password: '',
      },
      value1: true,
      // 表单验证
      loginFormRules: {
        s_phone: [
          { required: true, message: '请输入登录名称', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ], // 验证用户名

        s_password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { min: 6, max: 18, message: '长度在6 到 18 个字符', trigger: 'blur' }
        ]// 验证密码
      },
    }
  },
  computed: {
    canSubmit() {
      const { s_phone, s_password } = this.loginForm
      return Boolean(s_phone && s_password)
    }
  },
  methods: {
    // loginn() {
    //   this.$router.push({ path: "/Home/studentContent" })
    // },
    // signin() {
    //   this.$router.push({ path: "/signin" })
    // },
    //重置按钮
    resetLoginForm() {
      this.$refs.loginForms.resetFields()
      this.$message({
        type: 'success',
        message: "内容已重置"
      });
    },
    open1() {
      const h = this.$createElement;
      this.$notify({
        title: '注意：选择登录身份',
        message: h('i', { style: 'color: teal' }, '请选择管理员登录 或者 普通用户登录')
      });
    },
    loginn() {
      this.$refs.loginForms.validate((valid) => {
        if (valid) {
          const student = {
            s_phone: this.loginForm.s_phone,
            s_password: this.loginForm.s_password
          };
          PostUserLogin(student).then((res) => {          
              if (res != null) {
                const token = res.token;
                window.localStorage.setItem("token", token);
                this.$message({
                  message: "登录成功",
                  type: "success",
                });
                this.$router.push("/Home/countMoney");
              } else {
                this.$message({
                  message: "登录失败，请检查用户名和密码是否正确",
                  type: "error",
                });
              }
            })
            .catch((error) => {
              console.log(error);
              this.$message({
                message: "登录请求失败：" + error,
                type: "error",
              });
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

  },
}



</script>

<style lang="less" scoped>
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}

.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

  .avatar_box {
    height: 130px;
    width: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
      box-shadow: 0 0 10px #eee;
    }
  }
}

// .tsgu{
//   // 弹性布局
//   display:flex;
//   justify-content:flex-start;
// }
// .btns{
//   // 弹性布局
//     display: flex;
//     justify-content: space-between;
// }
.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;

  input.el-input__inner {
    border-radius: 30px;
    border-color: transparent;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }

}



::v-deep .switchStyle {

  //开关小盒子
  .el-switch__core {
    width: 70px !important;
    height: 25px !important;
    border-radius: 60px;
    background: #ffffff; //圆球在左时的开关背景色 只改变内部色，不改变边框色
    //圆球在右时的开关背景色在html结构中active-color="#67c23a"设置即可
  }

  //开关内区域
  .el-switch__label {
    position: absolute;
    padding-top: 1px;
    display: none;
    color: #fff;
    font-size: 10px !important;

    //圆球在左的 文字设置
    &--left {
      color: #9a9a9a !important;
      z-index: 1;
      right: 1px;
    }

    //圆球在右的 文字设置
    &--right {
      color: #ffffff !important;
      z-index: 1;
      left: 1px;
      font-size: 10px;
    }

    &.is-active {
      display: block;
    }
  }

  //圆球靠左的 圆球样式
  .el-switch__core:after {
    top: 15%;
    left: 4%;
    background-color: #d2d2d2;
  }

  //圆球靠右的 圆球样式
  &.el-switch.is-checked .el-switch__core::after {
    top: 15%;
    left: 97%;
    margin-left: -1.063rem;
    background-color: #fff;
  }
}
</style>
