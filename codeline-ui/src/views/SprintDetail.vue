<template>
  <div class="components-page-header-demo-responsive" style="border: 1px solid rgb(235, 237, 240)">
    <a-page-header title="迭代版本号" sub-title="版本说明" @back="() => $router.go(-1)">
      <template #extra>
        <a-button key="3">操作1</a-button>
        <a-button key="2">操作2</a-button>
        <a-button key="1" type="primary">操作3</a-button>
      </template>
      <div class="content">
        <div class="main">
          <a-descriptions size="small" :column="2">
            <a-descriptions-item label="迭代id">123123123</a-descriptions-item>
            <a-descriptions-item label="迭代名称">421421</a-descriptions-item>
            <a-descriptions-item label="版本号">V23214</a-descriptions-item>
            <a-descriptions-item label="创建人">阿斯顿</a-descriptions-item>
            <a-descriptions-item label="开始时间">2017-10-10</a-descriptions-item>
            <a-descriptions-item label="预计发布时间">2017-10-10</a-descriptions-item>
            <a-descriptions-item label="最后事件触发更新时间">2017-10-10</a-descriptions-item>
            <a-descriptions-item label="迭代类型">常规迭代/紧急迭代</a-descriptions-item>
            <a-descriptions-item label="备注">
              这里是备注，这里是备注，这里是备注，这里是备注，这里是备注，这里是备注，
            </a-descriptions-item>
          </a-descriptions>
        </div>
        <div class="extra">
          <div
            :style="{
              display: 'flex',
              width: 'max-content',
              justifyContent: 'flex-end',
            }"
          >
            <a-statistic
              title="当前迭代阶段"
              value="Pending"
              :style="{
                marginRight: '32px',
              }"
            />
            <a-statistic title="仓库个数"  :value="8" />
          </div>
        </div>
      </div>
    </a-page-header>
  </div>
  <div>
    <h4>执行日志</h4>
    <!-- <a-list item-layout="horizontal" :data-source="store.state.sprintDetail.logDate">
      <template #renderItem="{ item }">
        <a-list-item>
          <a-list-item-meta
            description="Ant Design, a design language for background applications, is refined by Ant UED Team"
          >
            <template #title>
              <a href="https://www.antdv.com/">{{ item.title }}</a>
            </template>
            <template #avatar>
              <a-avatar src="https://joeschmoe.io/api/v1/random" />
            </template>
          </a-list-item-meta>
        </a-list-item>
      </template>
    </a-list> -->
  </div>
</template>

<style>
.components-page-header-demo-responsive {
  padding-bottom: 20px;
}
.components-page-header-demo-responsive tr:last-child td {
  padding-bottom: 0;
}
#components-page-header-demo-responsive .content {
  display: flex;
}
#components-page-header-demo-responsive .ant-statistic-content {
  font-size: 20px;
  line-height: 28px;
}
@media (max-width: 576px) {
  #components-page-header-demo-responsive .content {
    display: block;
  }

  #components-page-header-demo-responsive .main {
    width: 100%;
    margin-bottom: 12px;
  }

  #components-page-header-demo-responsive .extra {
    width: 100%;
    margin-left: 0;
    text-align: left;
  }
}
</style>

<script lang="ts" setup>
  import { computed, defineComponent,ref, onMounted } from 'vue';
  import type { TableColumnsType } from 'ant-design-vue';
  import store from '../store'
 

// onMounted(() => {
  
// })
 
 
  </script>
 
  