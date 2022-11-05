import { Module } from "vuex";
import Base from '@/lib/ts/Base'
// interface DataItem {
//     key: number;
//     name: string;
//     gitUrl: string;
//   }

const store: Module<any, unknown> = {
    namespaced: true,
    state() {
        return {
            condition: {
                "currentPage": 0,
                "pageNum": 0,
                "pageSize": 20,
                "search": {}
                // 查询条件
            },
            list: [], // 页面渲染的数据
            pagination: {
                /*
                    current
                    pageSize
                    total
                */
            }
        }
    },
    // 更改 state, 但只能同步更改
    mutations: {
        setList(state, payload) {
            state.list = payload
        },
        setPagination(state, payload) {
            state.pagination = payload
        },
        setText(state: StoreUser, payload: AnyObject) {
            state.text = payload.text;
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
                    key: i+1,
                    name: item.name,
                    gitUrl: item.gitUrl,
                }))
                commit('setPagination', {
                    current: state.condition.currentPage,
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