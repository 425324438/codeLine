<template>
    <a-layout style="min-height: 100vh">
        <a-layout>
          <a-layout-content style="margin: 0 16px">
              <a-breadcrumb style="margin: 16px 0">
              </a-breadcrumb>
              <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
                  <a-table
                      :columns="sprintColumns"
                      :row-key="record => record.id"
                      :data-source="store.state.sprint.list" 
                      :pagination="store.state.sprint.pagination"
                      :bordered="true"
                      
                  >
                  <!-- @change="handleTableChange" -->
                      <template #bodyCell="{ column, text }">
                      <template v-if="column.dataIndex === 'name'">{{ text.first }} {{ text.last }}</template>
                      </template>
                  </a-table>
              </div>
          </a-layout-content>
        </a-layout>
    </a-layout>
</template>

<style>
#components-layout-demo-side .logo {
    height: 32px;
    margin: 16px;
    background: rgba(255, 255, 255, 0.3);
}
.site-layout .site-layout-background {
    background: #fff;
}
[data-theme='dark'] .site-layout .site-layout-background {
    background: #141414;
}
</style>
  <script lang="ts" setup>
  import {
    PieChartOutlined,
    DesktopOutlined,
    UserOutlined,
    TeamOutlined,
    FileOutlined,
  } from '@ant-design/icons-vue';
  import { computed, defineComponent,ref, onMounted } from 'vue';
  import store from '../store'

  const sprintColumns = [
  { title: 'id',        width: 5,    dataIndex: 'id', fixed: 'left'  },
  { title: '迭代名称',   width: 60,   dataIndex: 'name'               },
  { title: '阶段',      width: 30,   dataIndex: 'sprintEnvStatus'    },
  { title: '迭代类型',   width: 30,   dataIndex: 'sprintType'         },
  { title: '版本号',     width: 90,  dataIndex: 'version'             },
  { title: '创建人',     width: 30,   dataIndex: 'creator'            },
  { title: '创建时间',   width: 30,   dataIndex: 'createdTime'        },
  { title: '最后更新时间',width: 30,   dataIndex: 'modifiedTime'       },
  // {
  //   title: 'Action',
  //   key: 'operation',
  //   fixed: 'right',
  //   width: 20,
  // }
];

onMounted(() => {
  store.dispatch('sprint/getData', {})
})
 
  </script>
 
  