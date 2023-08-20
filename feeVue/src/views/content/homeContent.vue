<!-- <template>
  <div class="home">
    <div class="elTable">
      <el-tabs type="border-card">
        <el-tab-pane label="班费管理">
          <el-table :data="pageData" 
            style="width: 100%;
              margin: 0 auto;
              border-radius: 2px;             
              border-bottom: 0px;" max-height="100%">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column fixed="left" type="index" width="50" label="序号">
            </el-table-column>
            <el-table-column prop="s_id" label="学号" width="120">
            </el-table-column>
            <el-table-column prop="s_name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="s_gender" label="性别" width="120">
            </el-table-column>
            <el-table-column prop="s_phone" label="手机号" width="120">
            </el-table-column>
            <!-- <el-table-column prop="s_password" label="密码" width="120">
            </el-table-column> -->
            <el-table-column prop="g_id" label="班级" width="120">
            </el-table-column>
            <el-table-column prop="r_id" label="角色" width="120">
            </el-table-column>

            <!-- 搜索、编辑、删除 -->
            <el-table-column fixed="right" width="195">
              <template slot="header" slot-scope="scope">
                <el-date-picker type="datetimerange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"
                  align="right" size="mini" class="date_picker" style="width: 180px;">
                </el-date-picker>
                <el-input v-model="search" size="mini" class="search_input" placeholder="输入关键字搜索">
                  <template slot="append">
                    <el-button type="primary" size="mini" class="search_button" plain>搜索</el-button>
                  </template>
                </el-input>
              </template>
              <template slot-scope="scope">
                <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>

          <!-- 分页 -->


          <el-row :gutter="20">
            <el-col :span="4">
              <div class="grid-content bg-purple" style="padding-left:5px;padding-top: 8px;"> <el-button type="danger" size="mini">全部删除</el-button></div>
            </el-col>
            <el-col :span="16">
              <div class="grid-content bg-purple">
                <el-pagination 
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="currentPage"
                  :page-sizes="[10, 20, 50, 100]" 
                  :page-size="pageSize"
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="pageTotal" class="pagination">
                </el-pagination>
              </div>
            </el-col>
            <el-col :span="4">
              <div class="grid-content bg-purple" >
              </div>
            </el-col>
          </el-row>

        </el-tab-pane>


        <!-- 新增功能 -->
        <el-tab-pane label="新增操作">
        </el-tab-pane>
      </el-tabs>
      <div>
        <el-dialog title="编辑学生" :visible.sync="postedit">
          <el-form :model="formedit" :rules="rulesForm" ref="refedit">
            <el-row>
              <el-col :span="12">
                <el-form-item lable="日期" :label-width="formLabelWidth" prop="date">
                  <el-date-picker v-model="formedit.date" type="date" placeholder="选择日期" style="width: 150px">
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
                  <el-input v-model="formedit.name" placeholder="请输入姓名" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="地址" :label-width="formLabelWidth" prop="address">
                  <el-input v-model="formedit.address" placeholder="请输入地址" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="邮编" :label-width="formLabelWidth" prop="zip">
                  <el-input v-model="formedit.zip" placeholder="请输入邮编" clearable></el-input>
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
import axios from 'axios';
import { getStudentAll } from '@/network/ssmurl';
export default {
  data() {
    return {
      activeIndex: "1",
      activeIndex2: "1",
      postedit: false,
      formedit: {
        date: "",
        name: "",
        address: "",
        zip: "",
      },
      rulesForm: {
        //表单验证规则
        //姓名
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],

        //地址
        address: [{ required: true, message: "请填写地址", trigger: "blur" }],
        //邮编
        zip: [{ required: true, message: "请填写邮编", trigger: "blur" }],
        //时间
        date: [
          {
            type: "date",
            required: true,
            message: "请选择报名时间",
            trigger: "change",
          },
        ],
      },
      formLabelWidth: "120px",
      search: '',

      // 总的数据
      tableData: [],
      // 分页的数据
      pageData: [],
      // 总数据量 默认为 0
      pageTotal: 0,
      // 当前页 默认是第一页
      currentPage: 1,
      // 每页大小 默认每页10条数据
      pageSize: 7


    };
  },

  created(){
    this.fetchData()
  },

  mounted(){
  },

  methods: {
    

     // 改变每页大小的回调
     handleSizeChange (val) {
      this.pageSize = val

      this.pageData = this.queryByPage()
    },
    // 改变当前页的回调
    handleCurrentChange (val) {
      this.currentPage = val

      this.pageData = this.queryByPage()
    },
    // 获取全部数据
    async fetchData () {
      // pubic/table_data.json ：默认请求后端数据
      // const  data  = await getStudentAll()
      this.tableData = data
      this.pageTotal = data.length

      this.pageData  = this.queryByPage()
    },
    // 实现分页的方法
    queryByPage () {
      // 起始位置 = (当前页 - 1) x 每页的大小
      const start = (this.currentPage - 1) * this.pageSize
      // 结束位置 = 当前页 x 每页的大小
      const end = this.currentPage * this.pageSize

      return this.tableData.slice(start, end)
    }
  },


    // // 分页方法
    // handleSizeChange(val) {
    //   console.log(`每页 ${val} 条`);
    // },
    // handleCurrentChange(val) {
    //   console.log(`当前页: ${val}`);
    // },

    // //删除
    // handleDelete(row) {
    //   console.log(row);
    //   this.$alert("这是删除", "这是删除弹窗", {
    //     confirmButtonText: "确定",
    //     callback: action => {
    //       this.$message({
    //         type: "info",
    //         message: `action: ${action}`
    //       });
    //     }
    //   });
    // },
    // goBack() {
    //   console.log("go back");
    // },
    // handleEdit() {
    //   this.$alert("这是编辑", "这是编辑弹窗", {
    //     confirmButtonText: "确定",
    //     callback: action => {
    //       this.$message({
    //         type: "info",
    //         message: `action: ${action}`
    //       });
    //     }
    //   });
    // },
    // //编辑摁钮
    // handleEdit(data) {
    //   this.postedit = true;
    //   console.log(data);
    //   for (const key in data) {
    //     this.formedit[key] = data[key];
    //   }
    // },
    // async edit() {
    //   this.$refs.refedit.validate((val) => {
    //     if (val) {
    //       postCoachEdit(this.formedit).then((res) => {
    //         if (res.data.operateStatus) {
    //           this.$message({
    //             message: "编辑成功",
    //             type: "success",
    //           });
    //         } else {
    //           this.$message({
    //             message: "编辑失败",
    //             type: "error",
    //           });
    //         }
    //         (this.postedit = false), this.GetCoachAll();
    //       });
    //     }
    //   });
    // },
    // delete() {
    // }
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

</style> -->