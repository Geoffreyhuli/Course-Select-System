<template>
  <div>
    <el-container>
      <el-main>
        <h1>当前学期开设的课程</h1>
        <el-card>
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
                prop="credit"
                label="学分"
                width="150">
            </el-table-column>

            <!-- 新增操作列，包含删除按钮 -->
            <el-table-column label="操作" width="100">
              <template slot-scope="scope">
                <el-popconfirm
                    confirm-button-text="删除"
                    cancel-button-text="取消"
                    icon="el-icon-warning"
                    icon-color="red"
                    title="确定要删除吗?"
                    @confirm="deleteCourse(scope.row)">
                  <el-button slot="reference" type="text" size="small">删除</el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>

          <el-pagination
              background
              layout="prev, pager, next"
              :total="total"
              :page-size="pageSize"
              @current-change="changePage">
          </el-pagination>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  methods: {
    // 删除课程的方法
    deleteCourse(row) {
      const that = this;
      axios.delete('http://localhost:10086/course/deleteById/' + row.courseId)
          .then(function (resp) {
            if (resp.data === true) {
              that.$message({
                showClose: true,
                message: '删除成功',
                type: 'success'
              });
              // 删除成功后刷新数据
              that.fetchData();
            } else {
              that.$message({
                showClose: true,
                message: '删除出错，请查询数据库连接',
                type: 'error'
              });
            }
          })
          .catch(function (error) {
            that.$message({
              showClose: true,
              message: '删除请求失败，请稍后再试',
              type: 'error'
            });
          });
    },

    // 获取课程数据
    fetchData() {
      const that = this;
      axios.get('http://localhost:10086/courseTeacher/findMyCourse/' + this.staffId + '/' + this.semester)
          .then(function (resp) {
            that.tmpList = resp.data;
            that.total = resp.data.length;
            that.tableData = that.tmpList.slice(0, that.pageSize);
          })
          .catch(function (error) {
            console.error('获取数据失败', error);
          });
    },

    // 分页处理
    changePage(page) {
      page = page - 1;
      const that = this;
      let start = page * that.pageSize, end = that.pageSize * (page + 1);
      let length = that.tmpList.length;
      let ans = (end < length) ? end : length;
      that.tableData = that.tmpList.slice(start, ans);
    },
  },

  data() {
    return {
      tableData: null,
      pageSize: 10,
      total: null,
      tmpList: null,
      staffId: null,
      semester: null
    };
  },

  created() {
    // 获取存储在 sessionStorage 中的 staffId 和 semester 信息
    this.staffId = sessionStorage.getItem('staffId');
    this.semester = sessionStorage.getItem('currentTerm');

    // 加载课程数据
    this.fetchData();
  }
};
</script>
