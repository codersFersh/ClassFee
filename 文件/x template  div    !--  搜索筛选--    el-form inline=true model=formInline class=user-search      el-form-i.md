## 模糊查询

```vue


<template>
  <div>
    <!--  搜索筛选-->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="formInline.bname" placeholder="输入文章标题"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()">添加</el-button>
      </el-form-item>
    </el-form>
 
    <!--列表-->
    <el-table size="small" :data="listData" highlight-current-row style="width: 100%;">
      <el-table-column align="center" type="selection" min-width="60">
      </el-table-column>
      <el-table-column sortable prop="bid" label="文章ID" min-width="300">
      </el-table-column>
      <el-table-column sortable prop="bname" label="文章标题" min-width="300">
      </el-table-column>
      <el-table-column align="center" label="操作" min-width="300">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="deleteUser(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
 
    <!-- 分页条 -->
    <el-pagination style="margin-top: 20px;" @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page="formInline.page" :page-sizes="[10, 20, 30, 50]" :page-size="100"
      layout="total, sizes, prev, pager, next, jumper" :total="formInline.total">
    </el-pagination>
 
    <!-- 编辑界面 -->
    <el-dialog :title="title" :visible.sync="editFormVisible" width="30%" @before-close="closeDialog">
      <el-form label-width="120px" :model="editForm" :rules="rules" ref="editForm">
        <el-form-item label="文章内容" prop="bname">
          <el-input size="small" v-model="editForm.bname" auto-complete="off" placeholder="请输入文章内容"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">取消</el-button>
        <el-button size="small" type="primary" class="title" @click="submitForm('editForm')">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>
 
<script>
  export default {
    name: 'index',
    data() {
      return {
        title: '',
        editFormVisible: false,
        editForm: {
          title: '',
          bname: '',
          bid: ''
        },
        rules: {
          bid: [{
              required: true,
              message: '请输入编号',
              trigger: 'blur'
            },
            {
              min: 5,
              max: 10,
              message: '长度须在 5 到 10 个字符之间',
              trigger: 'blur'
            }
          ],
          bname: [{
            required: true,
            message: '请输入文章内容',
            trigger: 'blur'
          }],
 
        },
 
        listData: [],
        formInline: {
          bname: '',
          page: 1,
          total: 10
        }
      }
    },
    methods: {
      handleSizeChange(rows) {
        // console.log("页面发生改变")
        this.formInline.page = 1;
        this.formInline.rows = rows;
        this.search();
      },
      handleCurrentChange(page) {
        // console.log("当前页码发生改变")
        this.formInline.page = page;
        this.search();
      },
      //是为了代码复用
      doSearch(param) {
        // 获取树形节点的数据
        let url = this.axios.urls.SYSTEM_ARTICLE_list;
 
        //this指的是vue实例
        this.axios.post(url, param)
          .then(resp => { //代表成功 箭头函数 jdk8的语法
            console.log(resp.data);
            this.listData = resp.data.result;
            this.formInline.total = resp.data.pageBean.total;
 
          }).catch(function() { //代表失败
 
          });
      },
      search() {
        //  按照条件进行查询
        this.doSearch(this.formInline);
      },
      closeDialog() {
        //  关闭窗体
        this.clearData();
      },
      clearData() {
        //  清楚编辑窗体的缓存数据
        this.editForm.bname = '';
        this.editForm.bid = '';
        this.editForm.bname = '';
        this.title = '';
        this.editFormVisible = false;
      },
      handleEdit(index, row) {
        this.clearData();
        // 展示新增文章的表单
        this.editFormVisible = true;
        if (row) {
          //  编辑
          this.title = '编辑窗体';
          this.editForm.bid = row.bid;
          this.editForm.bname = row.bname;
        } else {
          //新增
          this.title = '新增窗体';
        }
 
      },
      deleteUser(index, row) {
        this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let url = this.axios.urls.SYSTEM_ARTICLE_del;
 
          this.axios.post(url, {
            bid: row.bid
          }).then(r => {
            //  新增成功之后，1、关闭窗体 ，清空数据  2、重新查询
            this.$message({
              message: '操作成功',
              type: 'success'
            });
            this.closeDialog();
            this.search();
          }).catch(e => {
 
          })
 
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let url;
            if (this.editForm.bid == '') {
              //新增
              url = this.axios.urls.SYSTEM_ARTICLE_add;
            } else {
              url = this.axios.urls.SYSTEM_ARTICLE_edit;
            }
            this.axios.post(url, this.editForm).then(r => {
              //  新增成功之后，1、关闭窗体 ，清空数据  2、重新查询
              this.$message({
                message: '操作成功',
                type: 'success'
              });
              this.closeDialog();
              this.search();
            }).catch(e => {
 
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      }
    },
    created() {
      this.doSearch({});
    }
  }
</script>
 
<style >
 
</style>
```



## 模糊查询

```java
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/search")
    public List<User> searchUser(@RequestParam("keyword") String keyword) {
        return userService.searchUser(keyword);
    }
    
}


@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    public List<User> searchUser(String keyword) {
        return userMapper.searchUser(keyword);
    }
    
}


<select id="searchUser" parameterType="string" resultType="User">
    SELECT * FROM user WHERE name LIKE CONCAT('%',#{keyword},'%')
</select>
```



```vue
<template>
  <div>
    <el-input v-model="keyword" placeholder="请输入查询关键字"></el-input>
    <el-button type="primary" @click="search">查询</el-button>
    <el-table :data="searchResult" border>
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
    </el-table>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  data() {
    return {
      keyword: '',
      searchResult: []
    }
  },
  methods: {
    search() {
      axios.get('/user/search', {
        params: {
          keyword: this.keyword
        }
      }).then(res => {
        this.searchResult = res.data
      })
    }
 
```

```java





```

```java

```

