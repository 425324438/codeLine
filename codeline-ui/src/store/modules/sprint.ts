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
                "search": {
                    "likeName": '',
                    "likeVersion": ''
                }
                // 查询条件
            },
            list: [], // 页面渲染的数据
            pagination: {
                current: 1,
                pageSize: 20,
                total: 0
            },
        }
    },
    // 更改 state, 但只能同步更改
    mutations: {
        // setCondition(state, payload) {
        //     state.condition = payload;
        // }
        setPagination(state, payload) {
            state.pagination = payload
        },
        setList(state, payload) {
            state.list = payload
        },
    },
    // 更改 state, 可以执行异步任务
    actions: {
        async getData({commit, state}) {
            // 1. 查询
            // 2. 保存数据
            try {
                let res = await Base.NetBase.post<any>("/sprint/page", state.condition)
                
                let list = res.data.map((item) => ({
                    id: item.id,
                    name: item.name,
                    sprintEnvStatus: item.sprintEnvStatus,

                    sprintTemplateId: item.sprintTemplateId,
                    sprintType: item.sprintType,
                    version: item.version,

                    hasError: item.hasError,
                    createdTime: item.createdTime,
                    creator: item.creator,
                    modifiedTime: item.modifiedTime
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
        }
    },
    getters: {
        // getText(state: StoreUser) {
        //     return state.text
        // }
    }
}

export default store