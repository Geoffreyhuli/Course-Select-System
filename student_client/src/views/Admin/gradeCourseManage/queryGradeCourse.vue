<template>
  <div>
    <el-container>
      <el-main>
        <el-card>
          <el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
            <el-form-item label="学号" prop="studentId">
              <el-input v-model.number="ruleForm.studentId"></el-input>
            </el-form-item>
            <el-form-item label="学生名" prop="studentName">
              <el-input v-model="ruleForm.studentName"></el-input>
            </el-form-item>
            <el-form-item label="模糊查询" prop="sFuzzy">
              <el-switch v-model="ruleForm.sFuzzy"></el-switch>
            </el-form-item>
            <el-form-item label="工号" prop="staffId">
              <el-input v-model.number="ruleForm.staffId"></el-input>
            </el-form-item>
            <el-form-item label="教师名" prop="teacherName">
              <el-input v-model="ruleForm.teacherName"></el-input>
            </el-form-item>
            <el-form-item label="模糊查询" prop="tFuzzy">
              <el-switch v-model="ruleForm.tFuzzy"></el-switch>
            </el-form-item>
            <el-form-item label="课程号" prop="courseId">
              <el-input v-model.number="ruleForm.courseId"></el-input>
            </el-form-item>
            <el-form-item label="课程名" prop="courseName">
              <el-input v-model="ruleForm.cname"></el-input>
            </el-form-item>
            <el-form-item label="模糊查询" prop="cFuzzy">
              <el-switch v-model="ruleForm.cFuzzy"></el-switch>
            </el-form-item>
            <el-form-item label="成绩下限" prop="lowBound">
              <el-input v-model.number="ruleForm.lowBound"></el-input>
            </el-form-item>
            <el-form-item label="成绩上限" prop="highBound">
              <el-input v-model.number="ruleForm.highBound"></el-input>
            </el-form-item>
            <el-form-item label="选择学期">
              <el-select v-model="ruleForm.term" placeholder="请选择学期">
                <el-option v-for="(item, index) in termList" :key="index" :label="item" :value="item"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
        <el-card style="margin-top: 10px">
          <grade-course-list :rule-form="ruleForm"></grade-course-list>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import GradeCourseList from "@/views/Admin/gradeCourseManage/gradeCourseList";
export default {
  components: {GradeCourseList},
  data() {
    return {
      termList: null,
      ruleForm: {
        studentId: null,
        studentName: null,
        sFuzzy: true,
        staffId: null,
        teacherName: null,
        tFuzzy: true,
        courseId: null,
        courseName: null,
        cFuzzy: true,
        lowBound: null,
        highBound: null,
        semester: sessionStorage.getItem('currentTerm')
      },
      rules: {
        courseId: [
          { type: 'number', message: '必须是数字类型' }
        ],
        staffId: [
          { type: 'number', message: '必须是数字类型' }
        ],
        studentId: [
          { type: 'number', message: '必须是数字类型' }
        ],
        cname: [
        ],
        lowBound: [
          { type: 'number', message: '必须是数字类型' }
        ],
        highBound: [
          { type: 'number', message: '必须是数字类型' }
        ],
      }
    };
  },
  created() {
    const that = this
    axios.get('http://localhost:10086/SCT/findAllTerm').then(function (resp) {
      that.termList = resp.data
    })
  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>