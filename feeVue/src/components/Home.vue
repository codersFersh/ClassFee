<template>
  <div class="body">
    <el-container>
      <el-header><top-header /></el-header>
      <el-container>
        <el-aside class="aside" width="180px">
          <left-Aside />
        </el-aside>
        <el-main><router-view></router-view></el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import topHeader from './topHeader.vue'
import leftAside from './leftAside.vue';
export default {
  components: { topHeader, leftAside },

  name: 'Home',

}
</script>
<style >
/* .body {
  width: 100%;
  height: 100%;
} */

aside {
  /* background-color:lightgray; */
  /* border-radius: 30px; */
  /* margin:20px 0px 0px 20px; */

  border-width: 2px;
  border-style: solid;
 
  background-color: rgb(84, 92, 100);
  margin-top: -4px;
  /* border-radius: 5px; */
  border-color: rgb(84, 92, 100);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5), 0 0px 12px rgba(0, 0, 0, 0.1);
}

.el-header {
        position: relative;
        width: 100%;
        height: 60px;      
}

.el-aside {
  display: block;
  position: absolute;
  left: 0;
  top: 60px;
  bottom: 0;
  }

  .el-main {
  position: absolute;
  left: 200px;
  right: 0;
  top: 60px;
  bottom: 0;
  overflow-y: scroll;
  }
</style>
