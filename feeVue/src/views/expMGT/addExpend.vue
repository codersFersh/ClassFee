<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <el-skeleton :rows="1" animated />
      <el-row>
        <el-col :span="8">
          <el-form-item label="备注标签：" :label-width="formLabelWidth" prop="e_title">
            <el-input v-model="ruleForm.e_title" placeholder="请输入收费目的" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="总金额：" :label-width="formLabelWidth" prop="e_gross">
            <el-input v-model="ruleForm.e_gross" placeholder="请输入收取班费总额" clearable></el-input>
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
import { PostExpAdd } from '@/network/ssmurl'
import { getSumGross } from '@/network/ssmurl'
import { getSumMoney } from '@/network/ssmurl'
export default {
  data() {
    return {
      formLabelWidth: "120px",
      ruleForm: {
        e_title: "",
        e_gross: "",
      },
      rules: {

        //表单验证规则
        //备注
        e_title: [
          { required: true, message: "请填写此次班级收取班费的目的", trigger: "blur" },
          {
            pattern: /^[\u4e00-\u9fa5]{0,}$/,
            min: 2,
            max: 10,
            message: "姓名格式错误请检查，2到10个中文字符",
            trigger: "blur",
          },
        ],
        //班费
        e_gross: [{
          required: true,
          pattern: /^(([1-9]{1}\d*)|(0{1}))(\.\d{1,20})?$/,
          message: "请输入合法的金额，输入整数",
          trigger: "change"
        }],
      },

    };
  },

  mounted() {

    // // 获取全部班级信息
    // getGradeAll().then(res => {
    //   this.gradeInfoList = res     
    //   this.ruleForm.g_id = this.gradeInfoList[0].g_id
    // })


  },
  created() {

  },
  methods: {
    // 重置内容
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    // 添加成功@click事件
    open1() {
      this.$notify({
        title: '添加成果',
        message: '编号与时间已自动生成于数据库',
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
          //支出总金额
          let Zmoney = await getSumGross()
          //收入总金额
          let Smoney = await getSumMoney()

          let Ymoney = Smoney - Zmoney
          
          console.log(Zmoney);
          console.log(Smoney);
          console.log(Ymoney);

          if (Smoney > Zmoney && this.ruleForm.e_gross<Ymoney) {

            // 调用接口方法，传入表单数据
            PostExpAdd(this.ruleForm).then(res => {
              // 处理响应结果
              console.log(res)
              if (res) {
                this.$message({
                  message: "新增班费信息成功",
                  type: "success",
                });
                this.$refs.ruleForm.resetFields()
                this.open1()
                this.refresh()
              } else {
                this.$message({
                  message: "新增班费信息失败",
                  type: "error",
                });
              }
            })
          } else {
            this.$message({
              message: "班费余额不足，不能新增",
              type: "warning",
            });
          }

        }
      })
    },

  },

};
</script>

<style>
#selectItem {
  padding-left: 80px;
}
</style>