import Vue from 'vue'
import Router from 'vue-router'
import dict from './modules/dict'
import examine from './modules/examine'
import zsj from './modules/zsj'
import cgdd from './modules/cgdd'
import send from './modules/send'
import xsgl from './modules/xsgl'
import syxx from './modules/syxx'
import approval from './modules/approval'
import bim from "./modules/bim"
import storage from '../utils/storage'
import store from '../store'
import cwgl from  './modules/cwgl'

Vue.use(Router)

let router = new Router({
    routes: [
        {
            path: '/login',
            name: '登录',
            component: (resolve) => require(['../views/login/Login.vue'], resolve),
        },

        {
            path: '/',
            name: '首页',
            component: (resolve) => require(['../views/layout/Layout'], resolve),
            children: [
                ...examine,
                ...dict,
                ...zsj,
                ...approval,
                ...cgdd,
                ...xsgl,
                ...syxx,
                ...send,
                ...bim,
				...cwgl
            ]
        },
        {
            path: '/404',
            name: '404',
            component: (resolve) => require(['../views/errorPage/404.vue'], resolve),
        },

    ]
})

router.beforeEach((to, from, next) => {
    // if(to.matched.length === 0){
    //   next({path:'/404'})
    // }
    // 登录界面登录成功之后，会把用户信息保存在会话
    // 存在时间为会话生命周期，页面关闭即失效。


    let userName = sessionStorage.getItem('userName')
    if (to.path === '/login') {
        // 如果是访问登录界面，如果用户会话信息存在，代表已登录过，跳转到主页
        if (!userName) {
            next()
        } else {
            next({path: '/'})
        }
    } else {
        if (!userName) {//如果没有登录 访问任何页面都跳转到登录页面
            next({
                path: '/login'
            })
        } else {
            // if(!store.state.app.isaddRouter){//如果已经加载过动态路由 就不需要再次加载
            //   let routerList = storage.get('menulist')
            //   let asyncRouter = dynamicRouter(routerList)
            //   router.options.routes[1].children.push(...asyncRouter)
            //   router.addRoutes(router.options.routes)
            //   next({ ...to, replace: true })//解决动态路由刷新失效问题
            // }
            next()
        }
    }
})


let dynamicRouterList = [];
let dynamicRouter = list => {
    list.reduce((finallist, item, index, arr) => {
        buildRouter(item.children)
    }, [])
    store.commit('changeAddRouterState')
    return dynamicRouterList
}

let buildRouter = listChildren => {
    listChildren.reduce((finalList, item, index, arr) => {
        dynamicRouterList.push({
            path: item.menuUrl,
            name: item.name,
            // component: (resolve) => require([`../views/${item.templateUrl}`], resolve)
            // component: r => require.ensure([], () => r(require(`../views/${item.templateUrl}`), `${item.templateUrl.split('/')[0].split('.')[1]}`)) ,
            component: () => import(`../views/${item.templateUrl}`),

        });
        if (item.children.length > 0) {
            buildRouter(item.children)
        }
        return finalList;
    }, [])
};

export default router
