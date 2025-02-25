<template>
  <div>
    <el-container>
      <el-main>
        <el-card>
          <el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
            <el-form-item label="工号" prop="staffId">
              <el-input v-model.number="ruleForm.staffId"></el-input>
            </el-form-item>
            <el-form-item label="教师名" prop="name">
              <el-input v-model.number="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="教师模糊查询">
              <el-switch v-model="ruleForm.tFuzzy"></el-switch>
            </el-form-item>
            <el-form-item label="课程号" prop="courseId">
              <el-input v-model.number="ruleForm.courseId"></el-input>
            </el-form-item>
            <el-form-item label="课程名" prop="courseName">
              <el-input v-model.number="ruleForm.courseName"></el-input>
            </el-form-item>
            <el-form-item label="课程模糊查询">
              <el-switch v-model="ruleForm.cFuzzy"></el-switch>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
        <el-card style="margin-top: 10px">
          <select-course-list :rule-form="ruleForm"></select-course-list>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import SelectCourseList from "@/views/Student/selectCourse/selectCourseList";
export default {
  components: {SelectCourseList},
  data() {
    return {
      ruleForm: {
        staffId: null,
        courseId: null,
        courseName: null,
        name: null,
        tFuzzy: true,
        cFuzzy: true
      },
      rules: {
        staffId: [
          { type: 'number', message: '必须是数字类型' }
        ],
        courseId: [
          { type: 'number', message: '必须是数字类型' }
        ],
      }
    };
  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>