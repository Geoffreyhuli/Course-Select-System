<template>
  <div>
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%">
      <el-table-column
          fixed
          prop="courseId"
          label="课程号"
          width="150">
      </el-table-column>
      <el-table-column
          prop="courseName"
          label="课程名"
          width="150">
      </el-table-column>
      <el-table-column
          fixed
          prop="studentId"
          label="学号"
          width="100">
      </el-table-column>
      <el-table-column
          prop="studentName"
          label="学生名"
          width="100">
      </el-table-column>
      <el-table-column
          prop="totalScore"
          label="成绩"
          width="100">
      </el-table-column>
      <el-table-column
          prop="semester"
          label="学期"
          width="100">
      </el-table-column>
      <el-table-column
          label="操作"
          width="100">
        <template slot-scope="scope">
          <el-button @click="editor(scope.row)" type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <p>
      平均成绩：{{ avg }}
    </p>
    <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="pageSize"
        @current-change="changePage"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  methods: {
    select(row) {
      console.log(row)
    },
    changePage(page) {
      page = page - 1
      const that = this
      let start = page * that.pageSize, end = that.pageSize * (page + 1)
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    },
    editor(row) {
      this.$router.push({
        path: '/editorGradeCourse',
        query: {
          courseId: row.courseId,
          staffId: row.staffId,
          studentId: row.studentId,
          semester: row.semester
        }
      })
    }
  },
  data() {
    return {
      tableData: null,
      pageSize: 10,
      total: null,
      tmpList: null,
      avg: 0,
    }
  },
  props: {
    ruleForm: Object,
  },
  watch: {
    ruleForm: {
      handler(newRuleForm, oldRuleForm) {
        console.log("组件监听 form")
        console.log(newRuleForm)
        const that = this
        that.tmpList = null
        that.total = null
        that.tableData = null
        axios.post("http://localhost:10086/SCT/findBySearch", newRuleForm).then(function (resp) {
          console.log("查询结果:");
          console.log(resp)
          that.tmpList = resp.data
          that.total = resp.data.length
          let start = 0, end = that.pageSize
          let length = that.tmpList.length
          let ans = (end < length) ? end : length
          that.tableData = that.tmpList.slice(start, ans)

          for (let i = 0; i < that.tmpList.length; i++) {
            that.avg += that.tmpList[i].totalScore
          }
          that.avg /= that.total
          console.log('avg', that.avg)
        })
      },
      deep: true,
      immediate: true
    }
  },
}
</script>