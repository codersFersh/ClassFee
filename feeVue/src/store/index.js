import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: { // 存放数据 和data类似
        f_id: '',
        f_title: '',
        f_gross: '',
        stuCount: ''
    },
    mutations: { // 用来修改state和getters里面的数据
        setFId(state, f_id) {
            state.f_id = f_id
        },
        setFTitle(state, f_title) {
            state.f_title = f_title
        },
        setFGross(state, f_gross) {
            state.f_gross = f_gross
        },
        setStuCount(state, stuCount) {
            state.stuCount = stuCount
        }
    },
    getters: { // 相当于计算属性
        getFId(state) {
            return state.f_id
        },
        getFTitle(state) {
            return state.f_title
        },
        getFGross(state) {
            return state.f_gross
        },
        getStuCount(state, ) {
            return state.stuCount
        }
    },
    actions: { // vuex中用于发起异步请求
    },
    modules: { // 拆分模块
    }
})