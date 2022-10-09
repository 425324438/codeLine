<template>
    <ProjectHeader/>

    <a-table  
    :pagination="pagination"
    :loading="loading"
    :columns="projectList" 
    :data-source="data" :bordered="true"
    class="project-table" 
    >
    <template #bodyCell="{ column }">
      <template v-if="column.key === 'operation'">
        <a>操作</a>
      </template>
    </template>
  </a-table>
</template>

<style scoped>

</style>

<script lang="ts" >
import Base from '@/lib/ts/Base'
import { defineComponent, ref, onMounted,  } from 'vue';
import ProjectHeader from '@/components/ProjectHeader.vue';
import type { TableColumnsType } from 'ant-design-vue';
import type { TableProps } from 'ant-design-vue';


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

const data: DataItem[] = [];

export default {
  components: {
    ProjectHeader,
  },
  data() {
    return {
      data,
      projectList,
    }
  },]
  
  created() {
    this.loadProjectPage()
  },
  methods:{
    loadProjectPage(){
      Base.NetBase.post<any>("/project/page", {
      "currentPage": 0,
      "pageNum": 0,
      "pageSize": 20,
      "search": { 
      }
    }).then(res => {
          for (let i = 0; i < res.data.length; i++) {
            var projectVo = res.data[i];
            this.data.push({
              key: i+1,
              name: projectVo.name,
              gitUrl: projectVo.gitUrl,
            });
          }
    })
    },
  }
}
</script>
