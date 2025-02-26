<template>
  <div>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="学生姓名" prop="name">
        <el-input v-model="ruleForm.name" :value="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="学生ID" prop="studentId">
        <el-input v-model="ruleForm.studentId" :value="ruleForm.studentId"></el-input>
      </el-form-item>
      <el-form-item label="初始密码" prop="password">
        <el-input v-model="ruleForm.password" :value="ruleForm.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>

      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        studentId: null, // 学生 ID
        name: null,      // 学生姓名
        password: null   // 学生密码
      },
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' }
        ],
      }
    };
  },
  created() {
    const that = this;
    // 从路由参数中获取学生 ID
    const studentId = this.$route.query.studentId;
    if (studentId) {
      // 调用后端接口查询学生信息
      axios.get('http://localhost:10086/student/findById/' + studentId).then(function (resp) {
        if (resp.data) {
          // 将查询到的学生信息填充到表单中
          that.ruleForm = resp.data;
        } else {
          that.$message.error('未找到学生信息');
        }
      }).catch(function (error) {
        that.$message.error('查询学生信息失败：' + error.message);
      });
    } else {
      that.$message.error('学生 ID 未提供');
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          const that = this;
          console.log(this.ruleForm);
          // 调用后端接口更新学生信息
          axios.post("http://localhost:10086/student/updateStudent", this.ruleForm).then(function (resp) {
            if (resp.data === true) {
              that.$message({
                showClose: true,
                message: '编辑成功',
                type: 'success'
              });
            } else {
              that.$message.error('编辑失败，请检查数据库');
            }
            // 跳转到学生列表页面
            that.$router.push("/studentList");
          }).catch(function (error) {
            that.$message.error('更新学生信息失败：' + error.message);
          });
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    test() {
      console.log(this.ruleForm);
    }
  }
}
</script>