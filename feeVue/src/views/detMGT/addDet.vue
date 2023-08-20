<template>
  <div style="padding-left:20px;">
    <h2>新增缴费</h2>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <el-row>
        <el-col :span="9">
          <el-form-item label="班费编号：" :label-width="formLabelWidth">
            <el-input v-model="ruleForm.f_id" disabled style="width: 220px;"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="9">
          <el-form-item label="备注标签：" :label-width="formLabelWidth">
            <el-input v-model="f_title" disabled style="width: 220px;"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="班级：" :label-width="formLabelWidth" prop="g_id">
            <el-select v-model="ruleForm.g_id" placeholder="请选择班级" disabled>
              <el-option v-for="item in gradeInfoList" :key="item.g_id" :label="item.g_classname" :value="item.g_id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>       
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="学号：" :label-width="formLabelWidth" prop="s_id">
            <el-select v-model="ruleForm.s_id" placeholder="请选择学号" @change="handleSelectChange('s_id')">
              <el-option v-for="item in selectData" :key="item.s_id" :label="item.s_id"
                :value="item.s_id"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>        
        <el-col :span="8">
          <el-form-item label="姓名：" :label-width="formLabelWidth" prop="s_name">
            <el-select v-model="ruleForm.s_name" placeholder="请选择姓名" @change="handleSelectChange('s_name')">
              <el-option v-for="item in selectData" :key="item.s_name" :label="item.s_name"
                :value="item.s_name"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="缴费金额：" :label-width="formLabelWidth" prop="d_money">
              <el-input v-model="ruleForm.d_money" placeholder="请输入金额" style="width: 220px;" clearable disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <div>
        <el-form-item id="buttonItem">
          <el-button type="primary" @click="clickAdd">提交</el-button>
          <!-- <el-button @click="resetForm('ruleForm')" type="warning">重置</el-button> -->
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
import { PostDetAdd } from '@/network/ssmurl';
import { getGradeAll } from '@/network/ssmurl';
import { getStuAll } from '@/network/ssmurl';
import { getstuCount } from '@/network/ssmurl';
export default {
  props:{
    tableData:[]
  },
  data() {
    return {
      gradeInfoList: '',
      selectData: [],
      //input宽度
      formLabelWidth: "120px",
      count:"",
      sum:"",
      //from
      ruleForm: {
        g_id: "",
        d_money: "",
        f_id: "",
        s_id: "",
        s_name:""
      },
      //数据校验
      rules: {
        //表单验证规则
  
        //班级
        g_id: [{ required: true, message: "请选择班级", trigger: "blur" }],
          
        //班费单
        f_id: [{ required: true, message: "请选择缴费事项编号", trigger: "blur" }],
          
        //学生
        s_id: [{ required: true, message: "请选着学生", trigger: "blur" }],
         //学生
        s_name: [{ required: true, message: "请选着学生", trigger: "blur" }],
        //班费
        d_money: [{
          required: true,
          pattern: /^(([1-9]{1}\d*)|(0{1}))(\.\d{1,20})?$/,
          message: "请输入合法的金额，输入整数",
          trigger: "change"
        }],
      },

    }
  },
  methods: {

   async clickAdd() { 
  this.$refs.ruleForm.validate(async (valid) => { 
    if (valid) {
      // 从ruleForm中获取f_id和s_id的值
      const { f_id, s_id } = this.ruleForm; 
      // 使用Array.prototype.some()方法判断tableData中是否存在相同的记录
      const isExist = this.tableData.some((item) => item.f_id === f_id && item.s_id === s_id); 
     // 如果存在相同的记录
      if (isExist) { 
        this.$message({ 
          message: '该学生缴费已存在，不允许新增',
          type: 'warning',
        });
        return; // 直接退出方法
      }
      try { 
        const res = await PostDetAdd(this.ruleForm); 
        if (res) { 
          this.$message({ 
            message: '新增缴费信息成功',
            type: 'success',
          });
          // 重置表单
          this.$refs.ruleForm.resetFields(); 
          this.open1(); 
          this.refresh(); 
        } else {
          this.$message({ 
            message: '新增缴费信息失败',
            type: 'error',
          });
        }
        // 如果PostDetAdd()方法执行出错
      } catch (error) { 
        // console.error(error); 
        // this.$message({ 
        //   message: '新增班费信息失败',
        //   type: 'error',
        // });
      }
    }
  });
},



    //下拉列表双向绑定
    handleSelectChange(fieldName) {
      const s_id = this.ruleForm.s_id
      const s_name = this.ruleForm.s_name
      if (fieldName === 's_id' && s_id) {
        // 根据学号获取姓名
        const student = this.selectData.find(item => item.s_id === s_id)
        if (student) {
          this.ruleForm.s_name = student.s_name
        } else {
          this.ruleForm.s_name = ''
        }
      } else if (fieldName === 's_name' && s_name) {
        // 根据姓名获取学号
        const student = this.selectData.find(item => item.s_name === s_name)
        if (student) {
          this.ruleForm.s_id = student.s_id
        } else {
          this.ruleForm.s_id = ''
        }
      }

    },

    calcAvgFee(){
      const avgFee = this.avgFee;
      const f_id = this.f_id;
      this.ruleForm.d_money = avgFee;
      this.ruleForm.f_id = f_id;
      console.log( this.ruleForm.d_money);
    },

     // 添加成功@click事件
     open1() {
      this.$notify({
        title: '添加成果',
        message: '编号与时间已自动生成于数据库',
        type: 'success'
      })
    },

    refresh(){
      location.reload();
    },


  },
  mounted() {
    // 获取全部班级信息
    getGradeAll().then(res => {
      this.gradeInfoList = res
      this.ruleForm.g_id = this.gradeInfoList[0].g_id
    })

    
    getStuAll().then(res =>{
      this.selectData = res
    })
   

  },

  created(){
    this.calcAvgFee()
  },
  watch: {

  },
  computed: {
    f_id(){
      return this.$store.state.f_id
    },

    f_title(){
      return this.$store.state.f_title
    },

    //全体班费
    f_gross(){
      return this.$store.state.f_gross 
    },

    //班级人数
    stuCount(){
      return this.$store.state.stuCount
    },
    
    //计算出平均缴费
    avgFee(){
      return this.f_gross / this.stuCount
    }

    
  },
  filters: {

  },
  
}
</script>

<style scoped lang='scss'></style>