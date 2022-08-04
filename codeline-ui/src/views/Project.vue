<template>
    
</template>

<style scoped>

</style>

<script setup lang="ts">
import Base from '@/lib/ts/Base'
import { computed, ref } from "vue";

let refText = ref("未修改");
let reactiveText = ref({text: "未修改"});
let api1Text = ref({text: "未修改"});
let api2Text = ref({text: "未修改"});
let api3Text = ref({text: "未修改"});

let elPageSize = ref(100);
let elCPage = ref(1);

 
// Base.NetBase.create({}) 等同于 new Base.NetBase({})
// 网络请求示例 1
const api1 = Base.NetBase.create({
    baseUrl: "./"
})

api1.get("api.json", {
    _success: (res: {text: string}) => {
        let timer2 = setTimeout(() => {
            api1Text.value.text = res.text;
            clearTimeout(timer2);
        }, 5000);
    }
})

// 网络请求示例 2
const api2 = Base.NetBase.create({
    baseUrl: "./"
})

api2.get<{text: string}>("api.json")
    .then(res => {
        let timer2 = setTimeout(() => {
            api2Text.value.text = res.text;
            clearTimeout(timer2);
        }, 5000);
    })

// 网络请求示例 3
Base.NetBase.sget<{text: string}>("./api.json")
    .then(res => {
        let timer3 = setTimeout(() => {
            api3Text.value.text = res.text;
            clearTimeout(timer3);
        }, 5000);
    })
</script>
