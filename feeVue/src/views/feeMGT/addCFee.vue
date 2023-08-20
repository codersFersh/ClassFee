<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <el-skeleton :rows="1" animated />
      <el-row>
        <el-col :span="8">
          <el-form-item label="备注标签：" :label-width="formLabelWidth" prop="f_title" >
            <el-input v-model="ruleForm.f_title" placeholder="请输入收费目的" clearable style="width: 220px;"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="班级：" id="selectItem">
            <el-select v-model="ruleForm.g_id" placeholder="请选择班级" disabled >
              <el-option v-for="item in gradeInfoList" :key="item.g_id" :label="item.g_classname" :value="item.g_id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="总金额：" :label-width="formLabelWidth" prop="f_gross">
            <el-input v-model="ruleForm.f_gross" placeholder="请输入收取班费总额" clearable style="width: 220px;"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="状态：" :label-width="formLabelWidth" prop="f_status">
            <el-select  v-model="ruleForm.f_status" disabled >
              <el-option v-for="(item, index) in list" :key="index" :label="item.label" :value="item.value"></el-option>
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
import { PostCfAdd, getGradeAll } from '@/network/ssmurl'
export default {
  data() {
    return {
      gradeInfoList: '',
      formLabelWidth: "120px",
        list: [
        // { label: '未开始', value: '未开始' },
        { label: '进行中', value: '进行中' },
        { label: '已完成', value: '已完成' }
      ],
      ruleForm: {
        f_title: "",
        g_id: "1",
        f_gross: "",
        f_status:"进行中"
      },
      rules: {

        //表单验证规则
        //性别
        f_title: [{ required: true, message: "请填写此次班级收取班费的目的", trigger: "blur" },
        {
            pattern: /^[\u4e00-\u9fa5]{0,}$/,
            min: 2,
            max: 10,
            message: "格式错误请检查，2到10个中文字符",
            trigger: "blur",
          },
      ],
        //班级
        g_id: [{ required: true, message: "请选择班级", trigger: "blur" }],
        //班费
        f_gross: [{
          required: true,
          pattern: /^(([1-9]{1}\d*)|(0{1}))(\.\d{1,2})?$/,
          message: "请输入合法的金额，输入整数",
          trigger: "change"
        }],
        f_status: [{ required: true, message: "请选择状态", trigger: "blur" }],
      },

    };
  },

  mounted() {

    // 获取全部班级信息
    getGradeAll().then(res => {
      this.gradeInfoList = res
      this.ruleForm.g_id = this.gradeInfoList[0].g_id
    })


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
      // console.log(this.ruleForm.f_status);
      this.$refs.ruleForm.validate((val) => {
        if (val) {
          // 调用接口方法，传入表单数据
          PostCfAdd(this.ruleForm).then(res => {
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

#buttonItem {
  /* padding-left: 20px;
 padding-top: 20px;
 padding-bottom: 20px; */


}
</style>