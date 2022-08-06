<template>
  <a-menu class="project-operation" v-model:selectedKeys="current" mode="horizontal">
    <a-menu-item key="list">
      <template #icon>
        <appstore-outlined />
      </template>
      <router-link to="/project"/>
      项目列表
    </a-menu-item>
    <a-menu-item key="create" >
      <template #icon>
        <edit-outlined />
        </template>
      <router-link to="/createProject"/>
      新增项目
    </a-menu-item>
  </a-menu>
</template>

<style scoped>
.project-operation{
  margin-bottom: 50px;
}
</style>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { MailOutlined, AppstoreOutlined, SettingOutlined, EditOutlined } from '@ant-design/icons-vue';
export default defineComponent({
  components: {
    MailOutlined,
    AppstoreOutlined,
    SettingOutlined,
    EditOutlined,
  },
  setup() {
    const current = ref<string[]>(['list']);
    return {
      current,
    };
  },
});
</script>

