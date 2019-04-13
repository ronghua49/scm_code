// 主数据菜单路由配置
export default [
    {
        path: 'approval/commerceFirst',
        name: '商业首营审批',
        component: (resolve) => require(['../../views/approvalManagement/CommerceFirst.vue'], resolve)
    },
    {
        path: 'approval/goodsfirstApple',
        name: '商品首营审批',
        component: (resolve) => require(['../../views/approvalManagement/goodsfirstApple.vue'], resolve)
    },
    {
        path: 'approval/ContractProductApple',
        name: '采购合同审批',
        component: (resolve) => require(['../../views/approvalManagement/ContractProductApple.vue'], resolve)
    },
    {
        path: 'approval/ContractProductAppleSp',
        name: '采购合同审批详情',
        component: (resolve) => require(['../../views/approvalManagement/ContractProductAppleSp.vue'], resolve)
    },
    {
        path: 'approval/CgProOrderProductApple',
        name: '采购订单审批',
        component: (resolve) => require(['../../views/approvalManagement/CgProOrderProductApple.vue'], resolve)
    },
    {
        path: 'approval/CgProRepProductApple',
        name: '采购补单审批',
        component: (resolve) => require(['../../views/approvalManagement/CgProRepProductApple.vue'], resolve)
    },
    {
        path: 'approval/ZsjSupplierApple',
        name: '供应商审批',
        component: (resolve) => require(['../../views/approvalManagement/ZsjSupplierApple.vue'], resolve)
    },
    {
        path: 'approval/BlackManagementApple',
        name: '商业黑名单审批',
        component: (resolve) => require(['../../views/approvalManagement/BlackManagementApple.vue'], resolve)
    },
    {
       path: 'approval/BusinessCreditApprove',
       name: '商业资信审批',
       component: (resolve) => require(['../../views/approvalManagement/BusinessCreditApprove.vue'], resolve)
    },
    {
        path: 'approval/BusinessAcceptApprove',
        name: '商业认可状态审批',
        component: (resolve) => require(['../../views/approvalManagement/BusinessAcceptApprove.vue'], resolve)
    },
    {
        path: 'approval/DistributorAgreementApprove',
        name: '分销商协议合作条款审批',
        component: (resolve) => require(['../../views/approvalManagement/DistributorAgreementApprove.vue'], resolve)
    },
    {
        path: 'approval/BisAgreeNameApprove',
        name: '商业协议合作名单审批',
        component: (resolve) => require(['../../views/approvalManagement/BisAgreeNameApprove.vue'], resolve)
    },
    {
        path: 'approval/DealerAgreement',
        name: '经销商协议合作条款审批',
        component: (resolve) => require(['../../views/approvalManagement/DealerAgreement.vue'], resolve)
    },
    {
        path: 'approval/XsBigContractApplyApproval',
        name: '商业大合同审批',
        component: (resolve) => require(['../../views/approvalManagement/XsBigContractApplyApproval.vue'], resolve)
    },
    {
        path: 'approval/XsBigAllotApproval',
        name: '商业大合同分配审批',
        component: (resolve) => require(['../../views/approvalManagement/XsBigContractAllotApproval.vue'], resolve)
    },
    {
        path: 'approval/EveryDayContractApproval',
        name: '日常合同审批',
        component: (resolve) => require(['../../views/approvalManagement/EveryDayContractApproval.vue'], resolve)
    },
    {
        path: 'approval/FocusSendApproval',
        name: '集中发货审批',
        component: (resolve) => require(['../../views/approvalManagement/FocusSendApproval.vue'], resolve)
    },
    {
        path: 'approval/ShippingInsApproval',
        name: '发货指令审批',
        component: (resolve) => require(['../../views/approvalManagement/ShippingInsApproval.vue'], resolve)
    },
]
