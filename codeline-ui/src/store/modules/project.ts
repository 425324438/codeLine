import { Module } from "vuex";
import Base from '@/lib/ts/Base'
import { defineComponent, ref } from 'vue';



const store: Module<any, unknown> = {
    namespaced: true,
    state() {
        return {
            condition: {
                "pageNum": 1,
                "pageSize": 20,
                "search": {}
                // 查询条件
            },
            list: [], // 页面渲染的数据
            pagination: {
                current: 1,
                pageSize: 20,
                total: 0
            },
            form: {
                name: "",
                url: ""
            },
            projectDo: {
                gitUrl: "",
                name: "",
                gitStorageType: "",
                group: ""
            }
        }
    },
    // 更改 state, 但只能同步更改
    mutations: {
        setCondition(state, payload) {
            state.condition = payload;
        },
        setList(state, payload) {
            state.list = payload
        },
        setPagination(state, payload) {
            state.pagination = payload
        },
        setText(state: StoreUser, payload: AnyObject) {
            state.text = payload.text;
        },
        loadForm(state, payload) {
            state.form = payload;
        },
        setFormName(state,payload){
            if(payload.name == null || payload.name  == ''){
                state.form.name = '';
            } else {
                state.form.name += payload.name;
            }
        },
        setFormUrl(state,payload){
            if(payload.url == null || payload.url  == ''){
                state.form.url = '';
            } else {
                state.form.url += payload.url;
            }
            if(state.form.url.startsWith('https://')){
                state.projectDo.gitUrl = state.form.url;
                state.projectDo.gitStorageType = state.form.url.split('https://')[1].split('.com')[0];
                state.projectDo.group = state.form.url.split('https://')[1].split('.com')[1].split('/')[1];
                state.projectDo.name = state.form.url.split('https://')[1].split('.com')[1].split('/')[2];
                state.form.name = state.projectDo.name;
            }
        }
    },
    // 更改 state, 可以执行异步任务
    actions: {
        async getData({commit, state}) {
            // 1. 查询
            // 2. 保存数据
            try {
                let res = await Base.NetBase.post<any>("/project/page", state.condition)
                console.log('getData', res);
                
                let list = res.data.map((item,i) => ({
                    key: item.id,
                    name: item.name,
                    gitUrl: item.gitUrl,
                }))
                commit('setPagination', {
                    current: parseInt(state.condition.pageNum),
                    pageSize: res.pageSize,
                    total: res.pageTotal,
                })
                commit('setList', list)
            } catch (error) {
                console.error(error)
            }
        },
        setText(context, payload: AnyObject) {
            context.commit("setText", payload);
        }
    },
    getters: {
        getText(state: StoreUser) {
            return state.text
        }
    }
}

export default store