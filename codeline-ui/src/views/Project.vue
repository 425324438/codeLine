<template>
    <ProjectHeader/>

    <a-table  
    :pagination="pagination"
    :loading="loading"
    :columns="projectList" 
    :data-source="data" :bordered="true"
    class="project-table" 
    @change="handleTableChange"  >
    <template #bodyCell="{ column }">
      <template v-if="column.key === 'operation'">
        <a>操作</a>
      </template>
    </template>
  </a-table>
</template>

<style scoped>

</style>

<script lang="ts">
import Base from '@/lib/ts/Base'
import { defineComponent, ref, onMounted,  } from 'vue';
import ProjectHeader from '@/components/ProjectHeader.vue';
import type { TableColumnsType } from 'ant-design-vue';

const projectList: TableColumnsType = [
  { title: '序号', width: 5, dataIndex: 'key', key: 'key', fixed: 'left' },
  { title: '项目', width: 30, dataIndex: 'name', key: 'name', fixed: 'left' },
  { title: '仓库地址', dataIndex: 'gitUrl', key: 'gitUrl', width: 200 },
  {
    title: 'Action',
    key: 'operation',
    fixed: 'right',
    width: 20,
  },
];

interface DataItem {
  key: number;
  name: string;
  gitUrl: string;
}

const data: DataItem[] = [{'key':1,'name':'test','gitUrl':'test'}];

function init(){
  debugger
  Base.NetBase.post<any>("/project/page", {
      "currentPage": 0,
      "pageNum": 0,
      "pageSize": 20,
      "search": { 
      }
    }).then(res => {
          console.log(res.data)
          for (let i = 1; i < res.data.length; i++) {
            var projectVo = res.data[i];
            data.push({
              key: i,
              name: projectVo.name,
              gitUrl: projectVo.gitUrl,
            });
          }
    })
}

export default {
  components: {
    ProjectHeader,
  },
  data() {
    debugger
    const current = ref<string[]>(['mail']);
    init()
    return {
      current,
      data,
      projectList,
    }
  },
  created() {
    
  }
}
</script>
