<template>
    <a-layout style="min-height: 100vh">
        <a-layout>
        <a-layout-content style="margin: 0 16px">
            <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>
                <b>迭代列表</b>
                <router-link to="/project"/>
            </a-breadcrumb-item>
            <a-breadcrumb-item>迭代详情</a-breadcrumb-item>
            </a-breadcrumb>
            <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
                <a-table
                    :columns="columns"
                    :row-key="record => record.id"
                    :data-source="dataSource"
                    :pagination="pagination"
                    :loading="loading"
                    @change="handleTableChange"
                >
                    <template #bodyCell="{ column, text }">
                    <template v-if="column.dataIndex === 'name'">{{ text.first }} {{ text.last }}</template>
                    </template>
                </a-table>
            </div>
        </a-layout-content>
        <a-layout-footer style="text-align: center">
            Ant Design ©2018 Created by Ant UED
        </a-layout-footer>
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
  <script lang="ts">
  import {
    PieChartOutlined,
    DesktopOutlined,
    UserOutlined,
    TeamOutlined,
    FileOutlined,
  } from '@ant-design/icons-vue';
  import type { TableProps } from 'ant-design-vue';
  import { computed, defineComponent,ref } from 'vue';
  
  const columns = [
    {
        title: '序号',
        dataIndex: 'index',
        sorter: true,
        width: '20%',
    },
    {
        title: '项目',
        dataIndex: 'name',
        filters: [
        { text: 'Male', value: 'male' },
        { text: 'Female', value: 'female' },
        ],
        width: '20%',
    },
    {
        title: '仓库地址',
        dataIndex: 'gitUrl',
    },
  ];

  type APIParams = {
    results: number;
    page?: number;
    sortField?: string;
    sortOrder?: number;
    [key: string]: any;
  };
  type APIResult = {
    results: {
      gender: 'female' | 'male';
      name: string;
      email: string;
    }[];
  };

  const queryData = (params: APIParams) => {
    // return axios.get<APIResult>('https://randomuser.me/api?noinfo', { params });
  };
  export default defineComponent({
    components: {
      PieChartOutlined,
      DesktopOutlined,
      UserOutlined,
      TeamOutlined,
      FileOutlined,
    },
    data() {
      return {
        // dataSource,
        // pagination,
        // loading,
        columns,
      };
    },
    created() {
        
    },
    methods:{
        handleTableChange(){
          TableProps['onChange'] = (
            pag: { pageSize: number; current: number },
            filters: any,
            sorter: any,
            ) => { run({
                results: pag.pageSize!,
                page: pag?.current,
                sortField: sorter.field,
                sortOrder: sorter.order,
                ...filters,
            });
          };
        }
    }
  });
  </script>
 
  