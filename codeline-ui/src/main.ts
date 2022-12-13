import { createApp } from 'vue'
import App from './App.vue'
import router from './router';
import store from "./store";
import Antd from 'ant-design-vue';

import ElementPlus from "element-plus"
// import "element-plus/dist/index.css"
import 'element-plus/theme-chalk/index.css'
import 'ant-design-vue/dist/antd.css'
import "./assets/css/setting.css"
import "./assets/css/global.css"

import { i18n } from './i18n';

const app = createApp(App);

app.use(router);
app.use(store);
app.use(Antd);
app.use(ElementPlus, { size: "mini" });
app.use(i18n);

app.mount('#app');

// app.use(router).use(store).use(Antd).use(ElementPlus, { size: "mini" }).use(i18n).mount('#app')

export default app;
