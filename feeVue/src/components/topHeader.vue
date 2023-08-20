<template>
  <div class="topHeader">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#545c64"
      text-color="#fff" active-text-color="#ffd04b">
      <el-col :span="8" style="height: 56px;">
        <img id="img-fee" src="@/assets/fee.png" />
        <h2 id="img-h2">班 费 管 理 系 统</h2>
      </el-col>
      <el-col :span="4">
        <el-menu-item index="2" id="menu_user" title="个人中心">
          <a href="" target="_blank"><img src="@/assets/logo.png" alt="" id="user_logo"></a>
          Admin
        </el-menu-item>
        <el-menu-item index="3" id="menu_end" title="退出登录">
          <a @click="logout" target="_blank"><img src="@/assets/end.png" /></a>
        </el-menu-item>
      </el-col>
    </el-menu>
  </div>
</template>
<script>
import { PostUserlogout } from "@/network/ssmurl";
export default {
  name: 'topHeader',
  data() {
    return {
      activeIndex: '1',
    }
  },
  methods: {
    logout() {
  // 从本地存储中获取token
  const token = window.localStorage.getItem("token");
  // 调用接口
  PostUserlogout({}, {
    headers: {
      Authorization: token,
    },
  })
  .then(() => {
    // 删除本地存储中的token
    window.localStorage.removeItem("token");
    // 跳转到登录页面
    this.$router.push("/");
  })
  .catch((error) => {
    console.error(error);
  });
}


  }
}
</script>
<style>
.el-header {
  padding: 0 0px !important;
}

/* .el-menu.el-menu--horizontal {
    border-bottom: solid 1px rgb(84, 92, 100) !important;
} */

.topHeader {
  padding: 0 auto;
  z-index: 999;
  position: fixed;
  width: 100%;
  top:0;
  border: -0.727;
  /*一直将导航栏置于最顶层 */
  /* display: flex;
  flex-direction: column;
  height: 100%; */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5), 0 0px 12px rgba(0, 0, 0, 0.1);
}

#menu_user {
  right: 60px;
  position: absolute;
  background-color: rgb(84, 92, 100) !important;
}

#user_logo {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: #eee;
  box-shadow: 0 0 10px #eee;
}

#menu_end {
  right: 0;
  position: absolute;
  background-color: rgb(84, 92, 100) !important;
}

#img-h2 {
  top: -6px;
  left: 50px;
  vertical-align: middle;
  text-align: center;
  position: absolute;
  color: #eee;
}

#img-fee {
  margin: 2px 0px 0px 2px;
}
</style>