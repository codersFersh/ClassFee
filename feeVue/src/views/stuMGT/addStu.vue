<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <el-skeleton :rows="1" animated />
      <el-row>
        <el-col :span="8">
          <el-form-item label="姓名：" :label-width="formLabelWidth" prop="s_name">
            <el-input v-model="ruleForm.s_name" placeholder="请输入姓名" clearable style="width: 220px;"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="性别：" prop="s_gender">
            <el-radio-group v-model="ruleForm.s_gender">
              <el-radio-button label="男" border></el-radio-button>
              <el-radio-button label="女" border></el-radio-button>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手机号：" :label-width="formLabelWidth" prop="s_phone">
            <el-input v-model="ruleForm.s_phone" placeholder="请输入手机号" clearable style="width: 220px;"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="密码：" :label-width="formLabelWidth" prop="s_password">
            <el-input type="password" v-model="ruleForm.s_password" placeholder="请输入密码" clearable
              style="width: 220px;"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="确认密码：" :label-width="formLabelWidth" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" clearable style="width: 220px;"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="班级：" style="padding-left: 70px;">
            <el-select v-model="ruleForm.g_id" placeholder="请选择班级" disabled>
              <el-option v-for="item in gradeInfoList" :key="item.g_id" :label="item.g_classname" :value="item.g_id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="角色：" style="padding-left: 70px;">
            <el-select v-model="ruleForm.r_id" placeholder="请选择角色" disabled>
              <el-option v-for="item in roleInfoList" :key="item.r_id" :label="item.r_name" :value="item.r_id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <div>
        <el-form-item id="buttonItem">
          <el-button type="primary" @click="clickAdd">提交</el-button>
          <el-button @click="resetForm('ruleForm')" type="warning">重置</el-button>
        </el-form-item>
      </div>
      <el-skeleton :rows="3" animated />
    </el-form>
  </div>
</template>

<script>
import { getGradeAll } from '@/network/ssmurl'
import { getRoleAll } from '@/network/ssmurl'
import { postStuAdd } from '@/network/ssmurl'
import { getSumFee } from '@/network/ssmurl'
import { getSumMoney } from '@/network/ssmurl'
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.s_password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      roleInfoList: '',
      gradeInfoList: '',
      formLabelWidth: "120px",
      ruleForm: {
        s_name: "",
        s_gender: "",
        s_phone: "",
        s_password: "",
        checkPass: "",
        g_id: "1",
        r_id: "1",
      },
      rules: {

        //表单验证规则
        //姓名
        s_name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          {
            pattern: /^[\u4e00-\u9fa5]{0,}$/,
            min: 2,
            max: 10,
            message: "姓名格式错误请检查，2到10个字符",
            trigger: "blur",
          },
        ],
        //性别
        s_gender: [{ required: true, message: "请填写性别", trigger: "blur" }],
        //手机号
        s_phone: [
          { required: true, message: "请填写手机号", trigger: "blur" },
          {
            pattern: /^1[3456789]\d{9}$/,
            message: "手机号格式不对",
            trigger: "blur",
          },
        ],
        //密码
        s_password: [
          { required: true, message: "请填写密码", trigger: "blur" },
          { min: 6, max: 16, message: '长度在 6 到 16 ', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' }
        ],
        //确认密码
        checkPass: [
          { required: true, message: "请规范密码，长度在 6 到 16", trigger: "blur" },
          { min: 6, max: 16, message: '请规范密码，长度在 6 到 16 ', trigger: 'blur' },
          { validator: validatePass2, trigger: 'blur' }
        ],
        //班级
        g_id: [{ required: true, message: "请选择班级", trigger: "blur" }],
        //角色
        r_id: [{ required: true, message: "请选择角色", trigger: "blur" }],
      },

    };
  },

  mounted() {
    // 获取全部班级信息

    getGradeAll().then(res => {
      this.gradeInfoList = res
      this.ruleForm.g_id = this.gradeInfoList[0].g_id
    })

    //获取全部角色信息

    getRoleAll().then(res => {
      this.roleInfoList = res
      this.ruleForm.r_id = this.roleInfoList[0].r_id
    })


  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    open1() {
      this.$notify({
        title: '用户添加',
        message: '该生学号已自动生成于数据库',
        type: 'success'
      })
    },

    refresh() {
      location.reload();
    },

    // 点击提交按钮触发的方法
    async clickAdd() {
      this.$refs.ruleForm.validate(async (val) => {
        if (val) {

          let SM = await getSumMoney();
          let CF = await getSumFee();

          console.log(SM);
          console.log(CF);

          if (CF === SM) {
            // 调用接口方法，传入表单数据
            postStuAdd(this.ruleForm).then(res => {
              // 处理响应结果
              console.log(res)
              if (res) {
                this.$message({
                  message: "新增学生成功",
                  type: "success",
                });
                this.$refs.ruleForm.resetFields()
                this.open1()
                this.refresh()
              } else {
                this.$message({
                  message: "新增学生失败",
                  type: "error",
                });
              }
            })
          } else {
            this.$message({
              message: "班费未缴费完成，请勿随意添加学生",
              type: "warning",
            });
          }
        }
      })
    },
  },

};
</script>

<style></style>