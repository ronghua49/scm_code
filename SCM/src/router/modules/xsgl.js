//销售管理菜单路由配置
export default [
    {
        path: 'fhgl/syxyhzmdManagement',
        name: '商业协议合作名单管理',
        component: (resolve) => require(['../../views/SalesManagement/syxyhzmdManagement.vue'], resolve)
    },
     {
        path: 'fhgl/syxydetails',
        name: '商业协议合作名单管理详情',
        component: (resolve) => require(['../../views/SalesManagement/syxydetails.vue'], resolve)
    },
    {
        path: 'xsgl/xsDealerClause',
        name: '经销商协议合作条款管理',
        component: (resolve) => require(['../../views/SalesManagement/xsDealerClause.vue'], resolve)
    },
    {
        path: 'xsgl/xsDealerClauseDetail',
        name: '经销商协议合作条款详情',
        component: (resolve) => require(['../../views/SalesManagement/xsDealerClauseDetail.vue'], resolve)
    },
    {
        path: 'xsgl/DistributorAgreementManage',
        name: '分销商协议合作条款管理',
        component: (resolve) => require(['../../views/xsglManagement/DistributorAgreementManage.vue'], resolve)
    },
    {
        path: 'xsgl/BusinessCreditManage',
        name: '商业资信管理',
        component: (resolve) => require(['../../views/xsglManagement/BusinessCreditManage.vue'], resolve)
    },
    {
        path: 'xsgl/xsBigContract',
        name: '商业大合同管理',
        component: (resolve) => require(['../../views/xsglManagement/XsBigContract.vue'], resolve)
    },
    {
        path: 'xsgl/xsBigContractAllot',
        name: '商业大合同分配管理',
        component: (resolve) => require(['../../views/xsglManagement/XsBigContractAllot.vue'], resolve)
    },
    {
        // 路由传参 -- 在目标页面通过$route获取
        // path: 'xsgl/BusinessCreditApply/:id',
        path: 'xsgl/BusinessCreditApply',
        name: '商业资信申请',
        component: (resolve) => require(['../../views/xsglManagement/BusinessCreditApply.vue'], resolve),
        // props: true 
    },
    {
        path: 'xsgl/EveryDayContract',
        name: '商业日常管理',
        component: (resolve) => require(['../../views/xsglManagement/EveryDayContract.vue'], resolve)
    },
    {
        path: 'xsgl/xsFocusSend',
        name: '集中发货合同管理',
        component: (resolve) => require(['../../views/xsglManagement/FocusSend.vue'], resolve)
    }
]
