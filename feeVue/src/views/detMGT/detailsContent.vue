<template>
  <div class="flip-container">
    <div class="flipper">
      <div class="front">
        <el-tabs type="border-card" @tab-click="handleTabClick">
          <el-tab-pane label="学生缴费管理" mame="tab1">
            <el-table :data="pageData" style="width: 100%;
              margin: 0 auto;
              border-radius: 2px;             
              border-bottom: 0px;"
               max-height="550px"
               :header-cell-style="{'text-align':'center'}"
              :cell-style="{'text-align':'center'}"
                @row-click="test">
              <el-table-column type="index" width="50" label="序号">
              </el-table-column>
              <el-table-column prop="f_id" label="编号" width="120">
              </el-table-column>
              <el-table-column prop="f_date" label="日期" width="250">
              </el-table-column>
              <el-table-column prop="f_title" label="备注标签" width="220">
              </el-table-column>
              <el-table-column prop="grade.g_classname" label="班级" width="150">
              </el-table-column>
              <el-table-column prop="f_gross" label="总金额" width="150">                
              </el-table-column>
              <el-table-column prop="f_status" label="状态" width="100">
            </el-table-column>

              <!-- 搜索、编辑、删除 -->
              <el-table-column fixed="right" width="195">
                <template slot="header" slot-scope="scope">
                  <el-input v-model="f_title" size="mini" class="search_input" placeholder="输入标签搜索" shadow="always">
                    <template slot="append">
                      <el-button type="primary" size="mini" class="search_button" @click="search1" plain>搜索</el-button>
                    </template>
                  </el-input>
                </template>
                <template slot-scope="scope">
                  <el-button type="success" @click="flip()">查看此次学生缴费</el-button>
                </template>
              </el-table-column>
            </el-table>

            <!-- 分页 -->
            <el-row>
              <el-col :span="24">
                <div class="grid-content">
                  <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                    :current-page="currentPage" :page-sizes="[10, 20, 50, 100]" :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper" :total="pageTotal" class="pagination">
                  </el-pagination>
                </div>
              </el-col>
            </el-row>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
  </div>
</template>
<script>
import { Message } from 'element-ui';
import { getCfAll } from '@/network/ssmurl';
import store from '@/store';
import { getCfSearch } from '@/network/ssmurl';
import { getstuCount } from '@/network/ssmurl';
export default {

  data() {
    return {
      activeTab: 'tab1',
      //宽度设置
      formLabelWidth: "120px",
      //搜索框
      f_title: "",
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


    };

  },
  created() {
    //分页
    this.fetchData();
  },
  mounted() {

  },
  methods: {

    //获取table中的f_id值

    test(row) {
      this.row = row;
    },

    //将f_id储存到Vuex后访问detailsAll页面进行
    // 单次班费记录的学生缴费管理
  async flip() {
      if(this.row && this.row.f_id &&this.row.f_title){
        let f_id = this.row.f_id;
        let f_title = this.row.f_title;
        let f_gross = this.row.f_gross; 
        let stuCount =  await getstuCount();
      store.commit('setFId',f_id)
      store.commit('setFTitle',f_title)
      store.commit('setFGross',f_gross)
      store.commit('setStuCount',stuCount)
      this.$router.push({ path: '/Home/detailsAll'})
      // console.log(f_id);
      // console.log(stuCount);
      }
    },


    //信息提示
    handleTabClick(tab) {
      Message({
        message: `您切换到了${tab.label}`,
        type: 'success'
      });

      this.$notify.warning({
        title: '注意！！！',
        message: '收取班费时间和编号在数据库自动生成无需新增',
        showClose: false
      });

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
    // 获取全部数据
    async fetchData() {
      // 请求后端数据
      const data = await getCfAll();
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

    //模糊查询
    search1() {
      let params = { f_title: this.f_title };
      getCfSearch(params).then(res => {
        this.pageData = res;
        this.pageTotal = res.length;
      });
    },
  },
}




</script>

<style lang="less" scoped>
.flip-container {
  // display: flex;
  border-radius: 5px;
  background-color: lightgray;
  // perspective: 1000px;
  // /* 设置3D场景的视距，可以根据需要调整 */

}

.flipper {
  width: 99%;
  margin: 0 auto;
  position: relative;

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
  padding-top: 10px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
}
</style>