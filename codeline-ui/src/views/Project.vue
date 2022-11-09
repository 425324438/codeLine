<template>
    <!-- <ProjectHeader/> -->
  <a-button type="primary" @click="showDrawer">
    <template #icon><PlusOutlined /></template>
    新增项目
  </a-button>
  <a-pagination 
    @change="changePage" 
    :total="store.state.project.pagination.total" 
    :current="store.state.project.pagination.current"
    :pageSize="store.state.project.pagination.pageSize"
  />
  <a-table  
    :pagination="false"
    :columns="projectList"
    :data-source="store.state.project.list" 
    :bordered="true"
  ></a-table>

  <a-drawer
      title="新增项目"
      :width="720"
      :visible="visible"
      :body-style="{ paddingBottom: '80px' }"
      :footer-style="{ textAlign: 'right' }"
      @close="onClose"
      @submit="onSubmit"
    >
    <a-form :model="store.state.project.form"  layout="vertical">
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="GitUrl" name="url">
            <a-input :value="store.state.project.form.url" @change="fromChangeUrl"  style="width: 100%" placeholder="非.git结尾"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="项目名称(自动解析Url)" name="name">
            <a-input :value="store.state.project.form.name" @change="fromChangeName" disabled="true" placeholder="请输入项目名称" />
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
    <template #extra>
      <a-space>
        <a-button @click="onClose">取消</a-button>
        <a-button type="primary" @click="onSubmit">提交</a-button>
      </a-space>
    </template>
  </a-drawer>
</template>

<style scoped>

</style>

<script lang="ts" setup >
import Base from '@/lib/ts/Base'
import { defineComponent, ref, onMounted,  } from 'vue';
import ProjectHeader from '@/components/ProjectHeader.vue';
import type { TableColumnsType } from 'ant-design-vue';
import type { TableProps } from 'ant-design-vue';
import { PlusOutlined } from '@ant-design/icons-vue';
import store from '../store'

const projectList: TableColumnsType = [
  { title: 'id', width: 5, dataIndex: 'key', key: 'key', fixed: 'left' },
  { title: '项目', width: 30, dataIndex: 'name', key: 'name', fixed: 'left' },
  { title: '仓库地址', dataIndex: 'gitUrl', key: 'gitUrl', width: 200 },
  {
    title: 'Action',
    key: 'operation',
    fixed: 'right',
    width: 20,
  }
];

onMounted(() => {
  store.dispatch('project/getData', {})
})

const changePage = (page) => {
  console.log(page)
  // 更改查询条件
  store.commit('project/setCondition', {
    ...store.state.project.condition,
    pageNum: page ,
  });
  // 查询
  store.dispatch('project/getData', {});
}



// 新增项目
const visible = ref<boolean>(false);


const fromChangeName = (values: any) =>{
  console.info('fromChange',values)
  //表单内容变更
  store.commit('project/setFormName', {
    ...store.state.project.form,
    name: values.data
  });
}
const fromChangeUrl = (values: any) =>{
  console.info('fromChange',values.data)
  //表单内容变更
  store.commit('project/setFormUrl', {
    ...store.state.project.form,
    url: values.data
  });
}
const showDrawer = () =>{
  visible.value = true;
}
const onClose = () => {
  visible.value = false;
  clearForm();
};

const onSubmit = (values: any) =>{
  // store.state.project.form
  console.info('表单提交',store.state.project.form);
  visible.value = false;

  clearForm();
}

const clearForm = () =>{
  console.info('clearForm执行')
  store.commit('project/setFormName', {
    ...store.state.project.form,
    name: null
  });
  store.commit('project/setFormUrl', {
    ...store.state.project.form,
    url: null
  });
}

</script>
