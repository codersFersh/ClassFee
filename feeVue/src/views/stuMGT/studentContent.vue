<template>
  <div class="home">
    <div class="elTable">
      <el-tabs type="border-card" @tab-click="handleTabClick">
        <el-tab-pane label="学生管理" mame="tab1">
          <el-table :data="pageData" style="width: 100%;
              margin: 0 auto;
              border-radius: 2px;             
              border-bottom: 0px;" :header-cell-style="{ 'text-align': 'center' }"
            :cell-style="{ 'text-align': 'center' }" max-height="550px">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column fixed type="index" width="50" label="序号">
            </el-table-column>
            <el-table-column prop="s_id" label="学号" width="120">
            </el-table-column>
            <el-table-column prop="s_name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="s_gender" label="性别" width="120">
            </el-table-column>
            <el-table-column prop="s_phone" label="手机号" width="150">
            </el-table-column>
            <el-table-column prop="grade.g_classname" label="班级" width="150">
            </el-table-column>
            <el-table-column prop="role.r_name" label="角色" width="150">
            </el-table-column>

            <!-- 搜索、编辑、删除 -->
            <el-table-column fixed="right" width="195">
              <template slot="header" slot-scope="scope">
                <el-input v-model="s_name" size="mini" class="search_input" placeholder="输学生姓名搜索" shadow="always">
                  <template slot="append">
                    <el-button type="primary" size="mini" class="search_button" @click="search1" plain>搜索</el-button>
                  </template>
                </el-input>
              </template>
              <template slot-scope="scope">
                <el-button size="mini" type="warning" @click="handleEdit(scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>

          <!-- 分页 -->
          <el-row :gutter="20">
            <el-col :span="4">
              <div class="grid-content bg-purple" style="padding-left:5px;padding-top: 8px;"> <el-button type="danger"
                  size="mini">批量删除</el-button></div>
            </el-col>
            <el-col :span="16">
              <div class="grid-content bg-purple">
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                  :current-page="currentPage" :page-sizes="[10, 20, 50, 100]" :page-size="pageSize"
                  layout="total, sizes, prev, pager, next, jumper" :total="pageTotal" class="pagination">
                </el-pagination>
              </div>
            </el-col>
            <el-col :span="4">
              <div class="grid-content bg-purple">
              </div>
            </el-col>
          </el-row>

        </el-tab-pane>


        <!-- 新增功能 -->
        <el-tab-pane label="新增操作" name="tab2">

          <addStu></addStu>

        </el-tab-pane>
      </el-tabs>
      <div>
        <el-dialog title="编辑学生" :visible.sync="postedit" width="50%">
          <el-form :model="formedit" :rules="rules" ref="formedit" style="width: 100%;">
            <el-row>
              <el-col :span="12">
                <el-form-item label="学号" :label-width="formLabelWidth" prop="s_id" style="padding-left: 35px;">
                  <el-input v-model="formedit.s_id" placeholder="请输入姓名" clearable disabled
                    style="width: 220px;"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="姓名" :label-width="formLabelWidth" prop="s_name">
                  <el-input v-model="formedit.s_name" placeholder="请输入姓名" clearable style="width: 220px;"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12" id="selectItem">
                <el-form-item label="角色：" style="padding-left:0px;">
                  <el-select v-model="formedit.r_id" placeholder="请选择角色" disabled>
                    <el-option v-for="item in roleInfoList" :key="item.r_id" :label="item.r_name" :value="item.r_id">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="性别" style="padding-left: 50px;" prop="s_gender">
                  <el-radio-group v-model="formedit.s_gender">
                    <el-radio-button label="男" border></el-radio-button>
                    <el-radio-button label="女" border></el-radio-button>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="班级：" style="padding-left:80px;">
                  <el-select v-model="formedit.g_id" placeholder="请选择班级" disabled>
                    <el-option v-for="item in gradeInfoList" :key="item.g_id" :label="item.g_classname"
                      :value="item.g_id">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="手机号" :label-width="formLabelWidth" prop="s_phone">
                  <el-input v-model="formedit.s_phone" placeholder="请输入手机号" clearable style="width: 220px;"></el-input>
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
  </div>
</template>


<script>
import { Message } from 'element-ui';
import { getStuAll } from '@/network/ssmurl';
import { getStuName } from '@/network/ssmurl';
import addStu from '@/views/stuMGT/addStu.vue';
import { postStuEdit } from '@/network/ssmurl';
import { postStuDel } from '@/network/ssmurl';
import { getGradeAll } from '@/network/ssmurl';
import { getRoleAll} from '@/network/ssmurl';
import { getDetSid} from '@/network/ssmurl';
export default {
  components: { addStu },
  data() {
    return {
      activeTab: 'tab1',
      //编辑弹窗
      postedit: false,
      //下拉列表获取班级，角色
      roleInfoList: '',
      gradeInfoList: '',
      //宽度设置
      formLabelWidth: "100px",
      //搜索框
      s_name: "",

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

      //编辑表对应数据
      formedit: {
        s_name: "",
        s_gender: "",
        s_phone: "",
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
            message: "姓名格式错误请检查,2到10个字符",
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
      },
    };

  },
  created() {
    //分页
    this.fetchData();
  },
  mounted() {
    //获取班级
    getGradeAll().then(res => {
      this.gradeInfoList = res
      this.formedit.g_id = this.gradeInfoList[0].g_id
    })
    //获取全部角色信息

    getRoleAll().then(res => {
      this.roleInfoList = res
      this.formedit.r_id = this.roleInfoList[0].r_id
    })
  },
  methods: {

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
    // 获取全部数据
    async fetchData() {
      // 请求后端数据
      const data = await getStuAll();
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


    //信息提示
    handleTabClick(tab) {
      Message({
        message: `您切换到了${tab.label}`,
        type: 'success'
      });

      this.$notify.warning({
        title: '注意！！！',
        message: '学号可数据库自增，无需手动添加',
        showClose: false
      });

    },

    //模糊查询
    search1() {
      let params = { s_name: this.s_name };
      getStuName(params).then(res => {
        this.pageData = res;
        this.pageTotal = res.length;
      });
    },

    //编辑摁钮
    handleEdit(data) {
      this.postedit = true;
      console.log(data);
      for (const key in data) {
        this.formedit[key] = data[key];
      }
    },
    //编辑学生
    async edit() {
      this.$refs.formedit.validate((val) => {
        if (val) {
          postStuEdit(this.formedit).then(res => {
            console.log(res)
            if (res) {
              this.$message({
                message: "编辑成功",
                type: "success",
              });
            }
            else {
              this.$message({
                message: "编辑失败",
                type: "error",
              });
            }
            (this.postedit = false), this.fetchData();
          });
        }
      });
    },

    refresh() {
      location.reload();//刷新页面
    },
    //删除学生
    //删除按钮
    async handleDelete(row) {
      let Sid = await getDetSid({ s_id: row.s_id })
      console.log(Sid)
      if (Sid === 0) {
        postStuDel(row).then(res => {
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
            // this.refresh();//刷新页面
          }
        })
      } else {
        this.$message({
          message: "该学生有缴费记录，不能删除",
          type: "warning",
        });
      }
    }
  },
}




</script>

<style lang="less" scoped>
.home {
  // display: flex;
  border-radius: 5px;
  background-color: lightgray;

  // position: absolute;
  // top: 10px;
  // left: 0px;
  // bottom: 10px;
  // width: auto;
  // right: 10px;
  // overflow-y: auto;
  // height:calc(100vh - 13rem);
  // box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5), 0 0px 6px rgba(0, 0, 0, 0.1);
}

.elTable {
  width: 99%;
  margin: 0 auto;
}

.line {
  top: 0px;
}

.search_input {
  font-weight: bold;
  border-width: 2px;
  border-style: solid;
  border-color: antiquewhite;
  background-size: auto;
  left: 0;
}



.search_button {
  background-color: #409EFF;
}


/deep/.el-tabs {
  .el-tabs__item {
    font-size: 18px;
  }
}


// 时间选择器
.date_picker {
  // width: 50px !important;
  font-weight: bold;
  border-width: 2px;
  border-style: solid;
  border-color: antiquewhite;
  background-size: auto;
  left: 0;
}

//分页样式
.pagination {
  text-align: center;
  padding-top: 7px;
}

// .el-table {
//   /deep/ .el-table__body-wrapper {
//     overflow-x: scroll !important; //直接设置横轴内容滚动条 即可撑高高度 还不影响使用
//   }
// }

// /deep/.el-table__row {
//   left: 200px;
//   right: 0;
//   top: 60px;
//   bottom: 0;
//   // 去掉table横向滚动条
//   // overflow:-Scroll;
//   // overflow-x:hidden
// }

// /deep/.el-table {
//   overflow: auto;
// }


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
}
</style>