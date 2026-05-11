## element-ui实现表头可拖拽换位置的Demo

### 1、先按照依赖包 Sortable.js

```sh
npm i sortablejs
```

### 2、实现的demo代码（实现的是element-ui的，不是element-plus的)

```vue
<template>
  <div style="padding: 20px;">
    <!-- 关键点 1：给 table 加 key，强制重新渲染 -->
    <el-table
      :key="tableKey"
      ref="dragTable"
      :data="tableData"
      border
      stripe
    >
      <el-table-column
        v-for="column in tableColumns"
        :key="column.prop"
        :prop="column.prop"
        :label="column.label"
        :width="column.width"
      />
    </el-table>
  </div>
</template>

<script>
import Sortable from 'sortablejs'

export default {
  data() {
    return {
      tableData: [
        { id: 1, name: '张三', age: 20, city: '北京' },
        { id: 2, name: '李四', age: 25, city: '上海' },
        { id: 3, name: '王五', age: 30, city: '广州' },
      ],
      tableColumns: [
        { prop: 'id', label: 'ID', width: '80' },
        { prop: 'name', label: '姓名', width: '120' },
        { prop: 'age', label: '年龄', width: '100' },
        { prop: 'city', label: '城市' , width: '100'},
      ],
      tableKey: 0, // 强制刷新用
      sortable: null,
    }
  },
  mounted() {
    this.initHeaderDrag()
  },
  methods: {
    initHeaderDrag() {
      const headerTr = this.$refs.dragTable.$el.querySelector('.el-table__header-wrapper tr')
      
      this.sortable = Sortable.create(headerTr, {
        animation: 150,
        onEnd: (evt) => {
          const oldIndex = evt.oldIndex
          const newIndex = evt.newIndex

          if (oldIndex !== newIndex) {
            // 1. 更新列顺序
            const moveCol = this.tableColumns.splice(oldIndex, 1)[0]
            this.tableColumns.splice(newIndex, 0, moveCol)

            // 关键点 2：强制表格重新渲染（解决数据不动的核心！）
            this.tableKey++
          }

          // 关键点 3：重新绑定拖拽（防止失效）
          this.resetDrag()
        },
      })
    },
    resetDrag() {
      if (this.sortable) this.sortable.destroy()
      this.$nextTick(() => {
        this.initHeaderDrag()
      })
    },
  },
  beforeDestroy() {
    if (this.sortable) this.sortable.destroy()
  },
}
</script>
```

