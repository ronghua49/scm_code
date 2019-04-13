// 采购订单菜单路由配置
export default [
    {
        path: 'cgdd/cgBuyOrder',
        name: '采购订单管理',
        component: (resolve) => require(['../../views/cgddManagement/cgBuyOrder.vue'], resolve)
    },
    {
        path: 'cgdd/cgBuyOrderDetail',
        name: '采购订单详情',
        component: (resolve) => require(['../../views/cgddManagement/cgBuyOrderDetail.vue'], resolve)
    },
    {
        path: 'cgdd/cgContract',
        name: '采购合同管理',
        component: (resolve) => require(['../../views/cgddManagement/cgContract.vue'], resolve)
     },
    {
        path: 'cgdd/cgContractDeial',
        name: '采购合同管理详情',
        component: (resolve) => require(['../../views/cgddManagement/cgContractDeial.vue'], resolve)
     },
    {
        path: 'cgdd/cgBuyOrderRe',
        name: '采购补单申请',
        component: (resolve) => require(['../../views/cgddManagement/cgBuyOrderRe.vue'], resolve)
     },
    {
        path: 'cgdd/cgProcurementPlan',
        name: '采购计划管理',
        component: (resolve) => require(['../../views/cgddManagement/cgProcurementPlan.vue'], resolve)
    },
]
