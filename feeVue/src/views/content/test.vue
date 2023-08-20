<!-- <template>
  <div>
    <el-select v-model="selectedId" placeholder="请选择">
      <template v-for="item in options">
        <el-option :key="item.s_id" :label="item.s_id + ' - ' + item.s_name" :value="item.s_id"></el-option>
      </template>
    </el-select>
  </div>
</template>

<script>
export default {

  data() {
    return {
      options: [
        { s_id: '001', s_name: '张三' },
        { s_id: '002', s_name: '李四' },
        { s_id: '003', s_name: '王五' },
      ],
      selectedId: '',
    }
  },
  methods: {
   
  },
  mounted() {

  },
  watch: {

  },
  computed: {

  },
  filters: {

  }
}
</script>

<style scoped lang='scss'>
.flip-container {
  perspective: 1000px;
  /* 设置3D场景的视距，可以根据需要调整 */
}

.flipper {
  position: relative;
  width: 200px;
  height: 200px;
  transform-style: preserve-3d;
  /* 设置子元素继承父元素的3D属性 */
  transition: transform 0.5s ease;
  /* 添加翻转动画 */
}

.front,
.back {
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
  /* 隐藏背面，避免出现闪烁问题 */
}

.front {
  background-color: #f00;
  /* 设置正面的颜色 */
}

.back {
  background-color: #00f;
  /* 设置反面的颜色 */
  transform: rotateY(180deg);
  /* 默认隐藏反面 */
}

button {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.flip {
  transform: rotateY(180deg);
  /* 点击按钮后翻转到反面 */
}
</style> -->