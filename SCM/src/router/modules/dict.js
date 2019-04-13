// 字典菜单路由配置
export default  [
    // {
    //     path: 'dict/busiDicManage',
    //     name: '商务分区字典管理',
    //     component: (resolve) => require(['../../views/dicManagement/BusiDicManagement.vue'], resolve)
    // },
    // {
    //     path: 'dict/provinDicManage',
    //     name: '省区字典管理',
    //     component: (resolve) => require(['../../views/dicManagement/ProvinDicManagement.vue'], resolve)
    // },
    {
        path: 'dict/ZdManageMentScope',
        name: '生产经营范围字典管理',
        component: (resolve) => require(['../../views/dicManagement/ManageMentScope.vue'], resolve)
    },
    {
        path: 'dict/GMPCertiflcationScope',
        name: 'GMP证书认证范围字典管理',
        component: (resolve) => require(['../../views/dicManagement/GMPCertiflcationScope.vue'], resolve)
    },
    {
        path: 'dict/MedicineType',
        name: '药品类型字典管理',
        component: (resolve) => require(['../../views/dicManagement/MedicineType.vue'], resolve)
    },
    {
        path: 'dict/MedicineClassify',
        name: '药品分类字典管理',
        component: (resolve) => require(['../../views/dicManagement/MedicineClassify.vue'], resolve)
    },
    {
        path: 'dict/ZdAgenttype',
        name: '剂型字典管理',
        component: (resolve) => require(['../../views/dicManagement/Agenttype.vue'], resolve)
    },
    // {
    //     path: 'dict/ZdAgenttype',
    //     name: '剂型字典管理测试',
    //     component: (resolve) => require(['../../views/dicManagement/TestAgenttypefor.vue'], resolve)
    // },
    {
        path: 'dict/commerceLevel',
        name: '商业级别字典管理',
        component: (resolve) => require(['../../views/dicManagement/CommerceLevel.vue'], resolve)
    },
    {
        path: 'dict/functionType',
        name: '职能类型字典管理',
        component: (resolve) => require(['../../views/dicManagement/FunctionType.vue'], resolve)
    },
    {
        path: 'dict/keepingCondition',
        name: '贮存条件字典管理',
        component: (resolve) => require(['../../views/dicManagement/KeepingCondition.vue'], resolve)
    },
    {
        path: 'dict/manuFacturer',
        name: '生产厂家字典管理',
        component: (resolve) => require(['../../views/dicManagement/Manufacturer.vue'], resolve)
    },
    {
        path: 'dict/getTicket',
        name: '收票方字典管理',
        component: (resolve) => require(['../../views/dicManagement/Getticket.vue'], resolve)
    },
    {
        path: 'dict/getMoneywareHouse',
        name: '收货仓库字典管理',
        component: (resolve) => require(['../../views/dicManagement/GetMoneyWarehouse.vue'], resolve)
    },
    {
        path: 'dict/returnRequestCause',
        name: '退货申请原因字典管理',
        component: (resolve) => require(['../../views/dicManagement/ReturnRequestCause.vue'], resolve)
    },
    {
        path: 'dict/DutyAffirm',
        name: '责任认定字典管理',
        component: (resolve) => require(['../../views/dicManagement/DutyAffirm.vue'], resolve)
    },
    {
        path: 'dict/DutyOffice',
        name: '责任办事处字典管理',
        component: (resolve) => require(['../../views/dicManagement/DutyOffice.vue'], resolve)
    },
    {
        path: 'dict/PromiseBook',
        name: '承诺书管理',
        component: (resolve) => require(['../../views/dicManagement/PromiseBook.vue'], resolve)
    },
]