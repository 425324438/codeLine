<template>
   
  <a-row type="flex" class="sprint_detail">
    
    <a-col :span="6">
      迭代id:
    </a-col>
    <a-col :span="6">
      迭代名称:
    </a-col>
    <a-col :span="6">
      阶段:
    </a-col>
    <a-col :span="6">
      迭代类型:
    </a-col>

    <a-col :span="6">
      版本号:
    </a-col>
    <a-col :span="6">
      创建人:
    </a-col>
    <a-col :span="6">
      开始时间:
    </a-col>
    <a-col :span="6">
      预计发布时间:
    </a-col>
    <a-col :span="6">
      最后更新时间:
    </a-col>
  </a-row>

  <a-row type="flex" class="exe_log">
    <a-col :span="24">
      执行日志 日志级
    </a-col>
  </a-row>

  <a-row type="flex" justify="space-around" align="middle" wrap="true">
    阶段信息
    <!-- <a-col :span="6">col-6</a-col>
    <a-col :span="6">col-6</a-col>
    <a-col :span="6">col-6</a-col>
    <a-col :span="6">col-6</a-col> -->
  </a-row>
  <a-row type="flex">
    项目列表
  </a-row>
</template>

<style>
/* .site-layout .site-layout-background {
    background: #fff;
} */
.sprint_detail {
  background: forestgreen;
}
.exe_log {
  background-color: aquamarine;
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
 

/**
 * 页面已挂载
 */
onMounted(() => {
  
})
 
  </script>
 
  