<template>
  <div>
    <el-card>
      <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="ruleForm.courseName" :value="ruleForm.courseName" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="教师名" prop="teacherName">
          <el-input v-model="ruleForm.teacherName" :value="ruleForm.teacherName" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="学生名" prop="studentName">
          <el-input v-model="ruleForm.studentName" :value="ruleForm.studentName" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="平时分数" prop="normalScore">
          <el-input v-model.number="ruleForm.normalScore" :value="ruleForm.normalScore"></el-input>
        </el-form-item>
        <el-form-item label="考试分数" prop="testScore">
          <el-input v-model.number="ruleForm.testScore" :value="ruleForm.testScore"></el-input>
        </el-form-item>
        <el-form-item label="总分数" prop="totalScore">
          <el-input v-model.number="ruleForm.totalScore" :value="ruleForm.totalScore"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
          <el-button @click="test">test</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
export default {
  data() {
    var checktotalScore = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('成绩不能为空'));
      }
      if (!Number.isInteger(value)) {
        callback(new Error('请输入数字值'));
      } else {
        if (value > 100 || value < 0) {
          callback(new Error('成绩范围 [0 - 100]'));
        } else {
          callback();
        }
      }
    };
    return {
      ruleForm: {
        courseId: null,
        courseName: null,
        totalScore: null,
        normalScore: null,
        testScore: null,
        studentId: null,
        studentName: null,
        staffId: null,
        name: null,
      },
      rules: {
        totalScore: [
          { required: true, message: '请输入学分', trigger: 'change'},
          { type: 'number', message: '请输入数字', trigger: 'change'},
          { validator: checktotalScore, trigger: 'blur'}
        ],
      }
    };
  },
  created() {
    const that = this
    this.ruleForm.courseId = this.$route.query.courseId
    this.ruleForm.staffId = this.$route.query.staffId
    this.ruleForm.studentId = this.$route.query.studentId
    this.ruleForm.semester = this.$route.query.semester
    axios.get('http://localhost:10086/SCT/findById/' +
        this.ruleForm.studentId + '/' +
        this.ruleForm.courseId + '/' +
        this.ruleForm.staffId + '/' +
        this.ruleForm.semester).then(function (resp) {
      that.ruleForm = resp.data
    })
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          const that = this
          const studentId = that.ruleForm.studentId
          const courseId = that.ruleForm.courseId
          const staffId = that.ruleForm.staffId
          const semester = that.ruleForm.semester
          const totalScore = that.ruleForm.totalScore
          const testScore = that.ruleForm.testScore
          const normalScore = that.ruleForm.normalScore
          axios.get("http://localhost:10086/SCT/updateById/" + studentId + '/' + courseId + '/' + staffId + '/' + semester + '/' + normalScore+ '/' + testScore+ '/' + totalScore).then(function (resp) {
            if (resp.data === true) {
              that.$message({
                showClose: true,
                message: '编辑成功',
                type: 'success'
              });
            }
            else {
              that.$message.error('编辑失败，请检查数据库');
            }
            if (sessionStorage.getItem('type') === 'admin') {
              that.$router.push("/queryGradeCourse")
            } else {
              that.$router.push("/teacherQuerytotalScoreCourseManage")
            }
          })
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    test() {
      console.log(this.ruleForm)
    }
  }
}
</script>