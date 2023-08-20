```java
/*修改学生*/
@RequestMapping(value = "/stuEdit/{id}", method = RequestMethod.GET)
public ModelAndView getEditStudentPage(@PathVariable("id") int id) {
  ModelAndView modelAndView = new ModelAndView("editStudent");
  Student student = studentService.getStudentById(id);
  modelAndView.addObject("student", student);
  return modelAndView;
}

@RequestMapping(value = "/stuEdit/{id}", method = RequestMethod.POST)
@ResponseBody
public boolean updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
  student.setId(id);
  boolean result = studentService.updateStudent(student);
  return result;
}
```





```java
@RequestMapping(value = "/stuEdit/{id}", method = RequestMethod.PUT)
@ResponseBody public boolean updateStudent(@PathVariable("id") int id, @RequestBody Student student) { 
    Student student1 = studentService.getStudentById(id);
    if (student1 == null) {
        return false; 
    } 
    student1.setName(student.getName());
    student1.setAge(student.getAge()); 
    student1.setGender(student.getGender());
    boolean s = studentService.updateStudent(student1); return s; 
} 

```



```vue
<el-menu :default-active="$route.path"
         class="el-menu-vertical-demo" 
         @open="handleOpen"
         @close="handleClose"
         :collapse="isCollapse"
         router>
    <template  v-for="(item,index) in menulist" >
        <!--没有子菜单-->
        <el-menu-item :key="index"
                      v-if="item.menuChilds.length==0" 
                      :index="item.menuUrl" >
            <i v-if="item.menuImgClass" :class="item.menuImgClass"></i>
            <span slot="title">pw_item.menuName</span>
        </el-menu-item>
```

```vue
<template>
  <el-form :model="formData" ref="form">
    <el-form-item label="Name">
      <el-input v-model="formData.name"></el-input>
    </el-form-item>
    <el-form-item label="Age">
      <el-input v-model="formData.age"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm">Submit</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      formData: {
        name: '',
        age: ''
      }
    };
  },
  methods: {
    submitForm() {
      axios.post('/api/user/add', this.formData)
        .then(response => {
          console.log(response.data);
          this.$message.success('Add user success');
        })
        .catch(error => {
          console.log(error);
          this.$message.error('Add user failed');
        });
    }
  }
};
</script>
```

```vue
clickAdd() { this.refs.form.validate((valid) =&gt; { if (valid) { postStuAdd(this.ruleForm).then(res =&gt; { if (res.code === 200) { this.message.success('添加成功'); this.resetForm('ruleForm'); } else { this.$message.error('添加失败'); } }) } else { return false; } }); }
```

```vue
// 刷新数据
this.showB = false;
this.$nextTick(() => {
  this.showB = true;
  this.activeTab = 'b';
});


<el-tabs v-model="activeTab" type="border-card">
  <el-tab-pane name="a">
    <!-- a Tab 组件内容 -->
  </el-tab-pane>
  <el-tab-pane name="b" v-if="showB">
    <!-- b Tab 组件内容 -->
  </el-tab-pane>
</el-tabs>

```

