<template>
  <ProjectHeader/>
   <div id="components-pagination-demo-mini">
    <a-pagination size="small" :total="50" show-size-changer show-quick-jumper />
  </div>
 
</template>

<style scoped>
#components-pagination-demo-mini .ant-pagination:not(:last-child) {
  margin-bottom: 24px;
}
</style>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import ProjectHeader from '@/components/ProjectHeader.vue';
export default defineComponent({
  components: {
    ProjectHeader,
  },
  setup() {
    const current = ref<string[]>(['mail']);
    return {
      current,
    };
  },
});
</script>
