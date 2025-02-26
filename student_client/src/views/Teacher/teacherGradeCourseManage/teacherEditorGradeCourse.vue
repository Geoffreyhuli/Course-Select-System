<template>
  <div>
    <el-card>
      <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="ruleForm.cname" :value="ruleForm.cname" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="教师名" prop="teacherName">
          <el-input v-model="ruleForm.tname" :value="ruleForm.tname" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="学生名" prop="studentName">
          <el-input v-model="ruleForm.sname" :value="ruleForm.sname" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="平时分" prop="normalScore">
          <el-input v-model.number="ruleForm.normalScore" :value="ruleForm.normalScore"></el-input>
        </el-form-item>
        <el-form-item label="考试分数" prop="testScore">
          <el-input v-model.number="ruleForm.testScore" :value="ruleForm.testScore"></el-input>
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
    var checkGrade = (rule, value, callback) => {
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
        cname: null,
        grade: null,
        studenstaffId: null,
        sname: null,
        staffId: null,
        tname: null,
      },
      rules: {
        grade: [
          { required: true, message: '请输入学分', trigger: 'change'},
          { type: 'number', message: '请输入数字', trigger: 'change'},
          { validator: checkGrade, trigger: 'blur'}
        ],
      }
    };
  },
  created() {
    const that = this
    this.ruleForm.courseId = this.$route.query.courseId
    this.ruleForm.staffId = this.$route.query.staffId
    this.ruleForm.studenstaffId = this.$route.query.studenstaffId
    this.ruleForm.semester = this.$route.query.semester
    axios.get('http://localhost:10086/SCT/findById/' +
        this.ruleForm.studenstaffId + '/' +
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
          const normalScore=that.ruleForm.normalScore
          const testScore=that.ruleForm.testScore
          axios.get("http://localhost:10086/SCT/updateById/" + studentId + '/' + courseId + '/' + staffId + '/' + semester + '/' + normalScore+'/'+testScore).then(function (resp) {
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
            that.$router.push("/queryGradeCourse")
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