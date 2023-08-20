import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/register/Login.vue'
import Home from '@/components/Home.vue'
import homeContent from '@/views/content/homeContent.vue'
import test from '@/views/content/test.vue'
import signin from '@/views/register/signin.vue'
import studentContent from '@/views/stuMGT/studentContent.vue'
import addStu from '@/views/stuMGT/addStu.vue'
import ClassFeeContent from '@/views/feeMGT/classFeeContent.vue'
import addCFee from '@/views/feeMGT/addCFee.vue'
import detailsContent from '@/views/detMGT/detailsContent.vue'
import detailsAll from '@/views/detMGT/detailsAll.vue'
import addDet from '@/views/detMGT/addDet.vue'
import expendContent from '@/views/expMGT/expendContent.vue'
import addExpend from "@/views/expMGT/addExpend.vue";
import countMoney from "@/views/SUM/countMoney.vue"

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'Login',
        component: Login
    },
    {
        path: '/Home',
        name: 'Home',
        component: Home,
        meta: { authRequired: true },
        children: [{
                path: 'test',
                component: () =>
                    import ('@/views/content/test.vue')
                    // component: homeContent,
            },
            {
                path: 'studentContent',
                component: () =>
                    import ('@/views/stuMGT/studentContent.vue')
            },
            {
                path: 'ClassFeeContent',
                component: () =>
                    import ('@/views/feeMGT/classFeeContent.vue')
            }, {
                path: 'detailsContent',
                component: () =>
                    import ('@/views/detMGT/detailsContent.vue')
            },
            {
                path: 'detailsAll',
                component: () =>
                    import ('@/views/detMGT/detailsAll.vue')
            },
            {
                path: 'expendContent',
                component: () =>
                    import ('@/views/expMGT/expendContent.vue')
            },
            {
                path: 'countMoney',
                component: () =>
                    import ('@/views/SUM/countMoney.vue')
            }

        ]
    },
    {
        path: '/homeContent',
        name: 'homeContent',
        component: homeContent
    },
    {
        path: '/signin',
        name: 'signin',
        component: signin
    },
    {
        path: '/studnetContent',
        name: 'studentContent',
        component: studentContent
    },
    {
        path: '/addStu',
        name: 'addStu',
        component: addStu
    },
    {
        path: '/classFeeContent',
        name: 'ClassFeeContent',
        component: ClassFeeContent
    },
    {
        path: '/addCFee',
        name: 'addCFee',
        component: addCFee
    },
    {
        path: '/detailsContent',
        name: 'detailsContent',
        component: detailsContent
    },
    {
        path: '/test',
        name: 'test',
        component: test
    },
    {
        path: '/detailsAll',
        name: 'detailsAll',
        component: detailsAll
    },
    {
        path: '/addDet',
        name: 'addDet',
        component: addDet
    },
    {
        path: '/expendContent',
        name: 'expendContent',
        component: expendContent
    },
    {
        path: '/addExpend',
        name: 'addExpend',
        component: addExpend
    },
    {
        path: '/countMoney',
        name: 'countMoney',
        component: countMoney
    }
]

const router = new VueRouter({
    routes
})

//全局前置守卫
//每次跳转页面执行该函数
//to,相当于this.$rotue,要跳到某个页面的路由信息
//from,代表当前页面的信息
router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('token')
    if (to.name !== 'Login' && !token) next({ name: 'Login' })
        // 如果用户未能验证身份，则 `next` 会被调用两次
    next()
})

export default router