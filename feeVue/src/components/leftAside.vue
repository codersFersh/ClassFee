<template>
  <div class="left-aside">
    <div id="menu-play">
      <h3 slot="title">操作导航</h3>
    </div>
    <el-menu 
      :default-active="$route.path"
      class="el-menu-vertical-demo" 
      @open="handleOpen" 
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      router 
      >
      <el-menu-item index="/Home/studentContent">
        <i class="el-icon-pie-chart" ></i>
         
        <span slot="title">学生管理</span>
      </el-menu-item>
      <el-menu-item index="/Home/classFeeContent">
        <i class="el-icon-s-custom"></i>
        <span slot="title">班费管理</span>
      </el-menu-item>
      <el-menu-item index="/Home/detailsContent">
        <i class="el-icon-s-home"></i>
        <span slot="title">学生缴费管理</span>
      </el-menu-item>
      <el-menu-item index="/Home/expendContent">
        <i class="el-icon-s-home"></i>
        <span slot="title">班费支出管理</span>
      </el-menu-item>
      <el-menu-item index="/Home/countMoney">
        <i class="el-icon-setting"></i>
        <span slot="title">收支统计</span>
      </el-menu-item>
    </el-menu>
  </div>

</template>

<script>
export default {
  name: 'leftAside',
  data() {
    return {
      handleOpen: '1',
    }
  },
  methods: {
    
  }
}
</script>

<style lang="less" scoped>

/deep/.el-dropdown-menu__item,
.el-menu-item {
    font-size: 18px;
    background-color: rgb(84, 92, 100) !important;
}

#menu-play{
  text-align: center;
  color: aliceblue;
}
.el-menu-vertical-demo{
  position: fixed;
  left: 0;
  widows: 100%;
}
.el-menu {
        border: 0 !important; //垂直时，去除右侧白边
}



</style>