<template>
    <ProjectHeader/>

    <a-table class="project-table" :columns="projectList" :data-source="data" :bordered="true" >
    <template #bodyCell="{ column }">
      <template v-if="column.key === 'operation'">
        <a>action</a>
      </template>
    </template>
  </a-table>
</template>

<style scoped>

</style>

<script lang="ts">
import Base from '@/lib/ts/Base'
import { defineComponent, ref } from 'vue';
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

const api2 = Base.NetBase.create({
    baseUrl: "./"
})

interface DataItem {
  key: number;
  name: string;
  gitUrl: string;
}

const data: DataItem[] = [];
for (let i = 1; i < 100; i++) {
  data.push({
    key: i,
    name: `codeLine ${i}`,
    gitUrl: `https://github.com/425324438/codeLine.git`,
  });
}

export default defineComponent({
  components: {
    ProjectHeader,
  },
  setup() {
    const current = ref<string[]>(['mail']);
    return {
      current,
      data,
      projectList,
    };
  },
});
</script>
