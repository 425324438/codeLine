<template>
  <a-layout has-sider>
    <a-layout-sider collapsible
      :style="{ overflow: 'auto', height: '100vh', position: 'fixed', left: 0, top: 0, bottom: 0 }"
    >
      <div class="logo" >logo</div>
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item key="1">
          <project-outlined />
          <span class="nav-text">项目</span>
          <router-link to="/project"/>
        </a-menu-item>
        <a-menu-item key="2">
          <calendar-outlined />
          <span class="nav-text">迭代列表</span>
          <router-link to="/sprint"/>
        </a-menu-item>
        <a-menu-item key="3">
          <setting-outlined />
          <span class="nav-text">系统设置</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout :style="{ marginLeft: '200px' }">
      <a-layout-content :style="{ margin: '24px 16px 0', overflow: 'initial' }">
        <router-view />
      </a-layout-content>
      <a-layout-footer :style="{ textAlign: 'center' }">
        CodeLine ©2022 Created by Ant UED
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>
<script lang="ts">
import {
  UserOutlined,
  VideoCameraOutlined,
  UploadOutlined,
  BarChartOutlined,
  CloudOutlined,
  AppstoreOutlined,
  TeamOutlined,
  ShopOutlined,
  SettingOutlined,
  ProjectOutlined,
  CalendarOutlined,
} from '@ant-design/icons-vue';
import { defineComponent, ref } from 'vue';
export default defineComponent({
  components: {
    UserOutlined,
    VideoCameraOutlined,
    UploadOutlined,
    BarChartOutlined,
    CloudOutlined,
    AppstoreOutlined,
    TeamOutlined,
    ShopOutlined,
    SettingOutlined,
    ProjectOutlined,
    CalendarOutlined,
  },
  setup() {
    return {
		//默认选中的页面
      selectedKeys: ref<string[]>(['1']),
    };
  },
});
</script>
<style>
.logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}
.site-layout .site-layout-background {
  background: #fff;
}

[data-theme='dark'] .site-layout .site-layout-background {
  background: #141414;
}
</style>
