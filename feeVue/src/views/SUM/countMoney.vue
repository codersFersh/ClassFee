<template>
  <div>
    <div style="margin-left: 250px; margin-top: 50px;">
      <div>
        <h2 style="font-weight:100px;font-size: 50px; font-family:KaiTi">班费收支明细</h2>
      </div>
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="sizeCss">
            <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value1" :title="title1">
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color: blue"></i>
              </template>
            </el-statistic>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="sizeCss">
            <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value2" :title="title2">
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color: red"></i>
              </template>
            </el-statistic>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="sizeCss">
            <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value3" :title="title3">
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color:chartreuse"></i>
              </template>
            </el-statistic>
          </div>
        </el-col>

      </el-row>
    </div>

    <div style="margin-left: 250px; margin-top: 50px;">
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="sizeCss">
            <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value4" :title="title4">
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color: blue"></i>
              </template>
            </el-statistic>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="sizeCss">
            <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value5" :title="title5">
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color: red"></i>
              </template>
            </el-statistic>
          </div>
        </el-col>
        <!-- <el-col :span="6">
        <div class="sizeCss">
          <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value3" :title="title3">
            <template slot="prefix">
              <i class="el-icon-s-flag" style="color:chartreuse"></i>
            </template>
          </el-statistic>
        </div>
      </el-col> -->

      </el-row>
    </div>
  </div>
</template>

<script>

import { getSumMoney } from '@/network/ssmurl';
import { getSumGross } from '@/network/ssmurl';
import { getSumFee } from '@/network/ssmurl';
export default {
  data() {
    return {
      like: true,
      value1: "",
      value2: "",
      value3: "",
      value4: "",
      value5: "",
      title1: '班费总收入',
      title2: '班费总支出',
      title3: '班费余额',
      title4: '预收班费',
      title5: '未缴费金额'
    }
  },
  methods: {

  },
  mounted() {
    // getSumMoney().then(res =>{
    //   this.tableData.amount1 = res
    // })


  },
  watch: {

  },
  created() {
    // this.tableData()
    getSumMoney().then(res => {
      this.value1 = res
      getSumGross().then(res => {
        this.value3 = this.value1 - res
      })
    })
    getSumGross().then(res => {
      this.value2 = res
    })

    getSumFee().then(res => {
      this.value4 = res
      getSumMoney().then(res => {
        this.value5 = this.value4 - res
      })
    })

    // let SM = getSumMoney()
    // let GM = getSumGross()

    // this.value3 = SM - GM
    // console.log(this.value3);

    // this.tableData.amount1 =  getSumMoney

  },
  computed: {

  },
  filters: {

  }
}
</script>

<style scoped lang='scss'>
/deep/.el-statistic .head {
  margin-bottom: 4px;
  color: #606266;
  font-size: 40px !important;
}

/deep/.el-statistic .con .number {
  font-size: 40px !important;
  padding: 4px;
}

.sizeCss {
  font-size: 30px;
  background-color: cornsilk;
}
</style>