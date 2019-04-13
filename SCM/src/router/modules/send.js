// 发货管理菜单路由配置
export default [
    {
        path: 'send/DeliverControlManage',
        name: '发货控制管理',
        component: (resolve) => require(['../../views/ShippingManagement/DeliverControlManage.vue'], resolve),
        props: true 
    },
    {
        path: 'send/LogisticsInfoManage',
        name: '物流发运信息管理',
        component: (resolve) => require(['../../views/ShippingManagement/LogisticsInfoManage.vue'], resolve),
        props: true 
    },
    {
        path: 'send/DailyDeliverDetailQuery',
        name: '日发货明细查询',
        component: (resolve) => require(['../../views/ShippingManagement/DailyDeliverDetailQuery.vue'], resolve),
        props: true 
    },
    {
        path: 'send/DeliverGoodsManage',
        name: '发货业务管理',
        component: (resolve) => require(['../../views/ShippingManagement/DeliverGoodsManage.vue'], resolve),
        props: true 
    },
    {
        path: 'send/cysManagement',
        name: '承运商管理',
        component: (resolve) => require(['../../views/ShippingManagement/cysManagement.vue'], resolve)
    },
    {
        path: 'send/wlAgreement',
        name: '物流协议管理',
        component: (resolve) => require(['../../views/ShippingManagement/wlAgreement.vue'], resolve),
        props: true 
    },
    {
        path: 'send/CentralizedDeliverSetting',
        name: '集中发货设置',
        component: (resolve) => require(['../../views/ShippingManagement/CentralizedDeliverSetting.vue'], resolve),
        props: true 
    },
      {
        path: 'send/ApplicationforDeliveryOrder',
        name: '发货指令申请',
        component: (resolve) => require(['../../views/ShippingManagement/ApplicationforDeliveryOrder.vue'], resolve),
        props: true  
       }
    
]