<template>
  <div>
    <el-button type="info" @click="flip()">返回</el-button>
    <el-button type="success" @click="drawer = true, drawer1()" v-bind:disabled="isSubmitting">新增缴费</el-button>
    <div>
      <el-drawer title="新增缴费" :visible.sync="drawer" :with-header="false">
        <addDet :tableData="tableData"></addDet>
      </el-drawer>

    </div>
    <el-table :data="pageData" style="width: 100%;
              margin: 0 auto;
              border-radius: 2px;             
              border-bottom: 0px;" :header-cell-style="{ 'text-align': 'center' }"
      :cell-style="{ 'text-align': 'center' }" border>
      <el-table-column type="index" width="50" label="序号">
      </el-table-column>
      <el-table-column prop="f_id" label="班费编号" width="100">
      </el-table-column>
      <el-table-column prop="classFee.f_title" label="备注标签" width="100">
      </el-table-column>
      <el-table-column prop="d_id" label="缴费编号" width="120">
      </el-table-column>
      <el-table-column prop="d_date" label="缴费日期" width="180">
      </el-table-column>
      <el-table-column prop="s_id" label="学号" width="120">
      </el-table-column>
      <el-table-column prop="student.s_name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="grade.g_classname" label="班级" width="120">
      </el-table-column>
      <el-table-column prop="d_money" label="缴费金额" width="150">
      </el-table-column>

      <!-- 搜索、编辑、删除 -->
      <el-table-column fixed="right" width="195">
        <!-- <template slot="header" slot-scope="scope">
          <el-input v-model="f_title" type="warning" size="mini" class="search_input" placeholder="输入标签搜索"
            shadow="always">
            <template slot="append">
              <el-button type="primary" size="mini" class="search_button" @click="search" plain>搜索</el-button>
            </template>
          </el-input>
        </template> -->
        <template slot-scope="scope">
          <el-button size="mini" type="warning" @click="handleEdit(scope.row)"
            v-bind:disabled="isSubmitting">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)"
            v-bind:disabled="isSubmitting">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row>
      <el-col :span="24">
        <div class="grid-content">
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="pageTotal" class="pagination">
          </el-pagination>
        </div>
      </el-col>
    </el-row>
    <div>
      <el-dialog title="编辑学生缴费" :visible.sync="postedit" width="45%">
        <el-form :model="formedit" :rules="rules" ref="formedit" style="width: 100%;">
          <el-row>
            <el-col :span="12">
              <el-form-item label="编号：" :label-width="formLabelWidth" prop="f_id">
                <el-input v-model="formedit.f_id" placeholder="请输入班费备注" clearable disabled
                  style="width: 220px;"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="备注标签：" :label-width="formLabelWidth" prop="f_title">
                <el-input v-model="formedit.f_title" placeholder="请输入班费备注" clearable disabled
                  style="width: 220px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="班级：" style="padding-left:70px">
                <el-select v-model="formedit.g_id" placeholder="请选择班级" disabled>
                  <el-option v-for="item in gradeInfoList" :key="item.g_id" :label="item.g_classname" :value="item.g_id">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="缴费金额：" :label-width="formLabelWidth" prop="d_money">
                <el-input v-model="formedit.d_money" placeholder="请输入有效金额" clearable disabled
                  style="width: 220px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="学号：" :label-width="formLabelWidth" prop="s_id">
                <el-select v-model="formedit.s_id" placeholder="请选择学号" @change="handleSelectChange('s_id')">
                  <el-option v-for="item in selectData" :key="item.s_id" :label="item.s_id"
                    :value="item.s_id"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="姓名：" :label-width="formLabelWidth" prop="s_name">
                <el-select v-model="formedit.s_name" placeholder="请选择姓名" @change="handleSelectChange('s_name')">
                  <el-option v-for="item in selectData" :key="item.s_name" :label="item.s_name"
                    :value="item.s_name"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="postedit = false">取 消</el-button>
          <el-button native-type="submit" @click="edit()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { getDetAll } from '@/network/ssmurl';
import { getGradeAll } from '@/network/ssmurl';
import { PostDetEdit } from '@/network/ssmurl';
import { getStuAll } from '@/network/ssmurl';
import { PostDetDel } from '@/network/ssmurl';
import { getSumFIdMoney } from '@/network/ssmurl';
import store from '@/store';
import addDet from './addDet.vue';
export default {
  components: { addDet },
  data() {
    return {
      pageData: '',
      search: '',
      // f_title: '',
      postedit: false,
      selectData: [],
      drawer: false,
      formLabelWidth: "120px",
      gradeInfoList: '',
      //按钮禁用状态
      isSubmitting: false,

      //分页 
      // 总的数据
      tableData: [],
      // 分页的数据
      pageData: [],
      // 总数据量 默认为 0
      pageTotal: 0,
      // 当前页 默认是第一页
      currentPage: 1,
      // 每页大小 默认每页10条数据
      pageSize: 10,



      formedit: {
        g_id: "",
        d_money: "",
        f_id: "",
        s_id: "",
        s_name: "",
        f_title: ""
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
          pattern: /^(([1-9]{1}\d*)|(0{1}))(\.\d{1,2})?$/,
          message: "请输入合法的金额，输入整数",
          trigger: "change"
        }],
      },

    }
  },
  methods: {
    //按钮禁用
    async submitForm() {     
      let GR = this.f_gross
      let f_id = this.f_id
      console.log("测试");
      let SFM = await getSumFIdMoney({f_id:f_id})
      console.log(SFM);     
      if (SFM === GR){
        this.isSubmitting = true
      // submit form
      }     
    },



    //编辑摁钮
    handleEdit(data) {
      console.log(data.classFee.f_title);
      this.postedit = true;
      for (const key in data) {
        this.formedit[key] = data[key];
      }
      this.formedit.f_title = data.classFee.f_title;
      this.formedit.s_name = data.student.s_name;

    },

    async edit() {
      this.$refs.formedit.validate(async (valid) => {
        console.log(valid);
        if (valid) { // 如果验证通过
          const { f_id, s_id } = this.formedit; // 从formedit中获取f_id和s_id的值
          const isExist = this.tableData.some((item) => item.f_id === f_id && item.s_id === s_id);
          if (isExist) {
            this.$message({
              message: '该记录已存在，不允许编辑',
              type: 'warning',
            });
            return; // 直接退出方法
          }
          try {
            const res = await PostDetEdit(this.formedit);
            if (res) { // 如果操作成功
              this.$message({
                message: '编辑班费信息成功',
                type: 'success',
              });
              this.postedit = false; // 关闭编辑弹出框
              this.fetchData(); // 刷新页面
            } else {
              this.$message({
                message: '编辑班费信息失败',
                type: 'error',
              });
            }
          } catch (error) {

          }
        }
      });
    },


    //删除按钮
    async handleDelete(d_id) {
      PostDetDel(d_id).then(res => {
        if (!res) {
          this.$message({
            message: "删除失败",
            type: "error",
          });
        } else {
          this.refresh();
          this.$message({
            message: "删除成功",
            type: "success",
          });
          this.refresh();//刷新页面
        }
      })
    },

    //下拉列表双向绑定
    handleSelectChange(fieldName) {
      const s_id = this.formedit.s_id
      const s_name = this.formedit.s_name
      if (fieldName === 's_id' && s_id) {
        // 根据学号获取姓名
        const student = this.selectData.find(item => item.s_id === s_id)
        if (student) {
          this.formedit.s_name = student.s_name
        } else {
          this.formedit.s_name = ''
        }
      } else if (fieldName === 's_name' && s_name) {
        // 根据姓名获取学号
        const student = this.selectData.find(item => item.s_name === s_name)
        if (student) {
          this.formedit.s_id = student.s_id
        } else {
          this.formedit.s_id = ''
        }
      }

    },


    flip() {
      this.$router.push({ path: "/Home/detailsContent" })
    },

    drawer1() {
      this.$notify.warning({
        title: '注意！！！',
        message: '缴费时间，缴费编号可数据库自增，无需手动添加',
        showClose: false,
        //出现位置
        position: 'top-left'
      });

    },

    refresh() {
      location.reload();
    },

    // 改变每页大小的回调
    handleSizeChange(val) {
      this.pageSize = val;
      this.pageData = this.queryByPage();
    },
    // 改变当前页的回调
    handleCurrentChange(val) {
      this.currentPage = val;
      this.pageData = this.queryByPage();
    },
    // 获取全部数据+
    async fetchData() {
      // 请求后端数据
      let f_id = this.f_id;
      const data = await getDetAll({ f_id });//执行接口获取数据
      this.tableData = data;
      this.pageTotal = data.length;
      this.pageData = this.queryByPage();
    },
    // 实现分页的方法
    queryByPage() {
      // 起始位置 = (当前页 - 1) x 每页的大小
      const start = (this.currentPage - 1) * this.pageSize;
      // 结束位置 = 当前页 x 每页的大小
      const end = this.currentPage * this.pageSize;
      return this.tableData.slice(start, end);
    },


  },
  mounted() {
    //获取班级
    getGradeAll().then(res => {
      this.gradeInfoList = res
      this.formedit.g_id = this.gradeInfoList[0].g_id
    })
    getStuAll().then(res => {
      this.selectData = res
    })




  },
  created() {
    // 分页
    this.fetchData();

    this.submitForm();

  },
  activated() {
    // 在页面被激活时执行的操作，例如重新获取数据
    this.fetchData();

  },
  watch: {

  },
  computed: {
    //班费编号
    f_id() {
      return this.$store.state.f_id
    },

    //全体班费
    f_gross() {
      return this.$store.state.f_gross
    },



    // f_title(){
    //   return this.$store.state.f_title
    // },

  },
  filters: {

  }
}
</script>

<style scoped lang='scss'>
//解决table设置fixed缩小显示不全问题

/deep/.el-table__header-wrapper,
.el-table__body-wrapper,
.el-table__footer-wrapper {
  overflow: visible;
}

/deep/.el-table__body-wrapper {
  overflow-x: visible !important;
}

/* 这个是为了解决前面样式覆盖之后伪类带出来的竖线 */
/deep/.el-table::after {
  position: relative;
}

/deep/.el-table__fixed-right {
  height: 100% !important;
}

el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
}</style>