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
            分页数据
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
  import { defineComponent, ref } from 'vue';
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
        
      };
    },
        created() {
        
    },
    methods:{
        
    }
  });
  </script>
 
  