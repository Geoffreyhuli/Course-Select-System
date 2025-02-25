<template>
  <div>
    <el-container>
      <el-main>
        <h1>学生主页</h1>
        <info-card></info-card>
        <el-card style="width: 40%">
          <p><i class="el-icon-s-home" style="margin-right: 18px"></i>同学你好</p>

        </el-card>
        <logout></logout>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Logout from "@/components/logout";
import InfoCard from "@/components/infoCard";
export default {
  name: "home",
  components: {InfoCard, Logout},
}
</script>

<style scoped>

</style>
