// 审批菜单路由配置
export default [
    {
        path: 'examine/customerExamine',
        name: '客户首营审批',
        component: (resolve) => require(['../../views/examineManagement/CustomerExamine.vue'], resolve)
    },
    {
        path: 'examine/customerChangeExamine',
        name: '客户信息修改审批',
        component: (resolve) => require(['../../views/examineManagement/CustomerChangeExamine.vue'], resolve)
    },
    {
        path: 'examine/goodsChangeExamine',
        name: '商品信息修改审批',
        component: (resolve) => require(['../../views/examineManagement/GoodsChangeExamine.vue'], resolve)
    },
]