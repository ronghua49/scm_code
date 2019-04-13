// 销售管理菜单路由配置
export default [
    {
        path: 'fhgl/BusinessCreditManage',
        name: '商业资信管理',
        component: (resolve) => require(['../../views/fhglManagement/BusinessCreditManage.vue'], resolve)
    },
    {
        // 路由传参 -- 在目标页面通过$route获取
        path: 'fhgl/BusinessCreditApply/:id',
        name: '商业资信申请',
        component: (resolve) => require(['../../views/fhglManagement/BusinessCreditApply.vue'], resolve),
        props: true 
    }
    
]