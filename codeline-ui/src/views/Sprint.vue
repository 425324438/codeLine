<template>
    <a-layout style="min-height: 100vh">
        <a-layout>
          <a-layout-content style="margin: 0 16px">
              <a-breadcrumb style="margin: 16px 0">
              </a-breadcrumb>
                <a-table
                    :columns="sprintColumns"
                    :row-key="record => record.id"
                    :data-source="store.state.sprint.list" 
                    :pagination="store.state.sprint.pagination"
                    :bordered="true"
                    
                >
                </a-table>
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
  import type { TableColumnsType } from 'ant-design-vue';
  import store from '../store'

  const sprintColumns: TableColumnsType  = [
  { title: 'id',        width: 5,    dataIndex: 'id'  },
  { title: '迭代名称',   width: 90,   dataIndex: 'name'               },
  { title: '阶段',      width: 30,   dataIndex: 'sprintEnvStatus'    },
  { title: '迭代类型',   width: 30,   dataIndex: 'sprintType'         },
  { title: '版本号',     width: 50,  dataIndex: 'version'             },
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
 
  