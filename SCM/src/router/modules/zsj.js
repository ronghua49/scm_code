// 主数据菜单路由配置
export default [
    {
        path: 'zsj/peoductLine',
        name: '商品系列管理',
        component: (resolve) => require(['../../views/zsjManagement/ProductLine.vue'], resolve)
    },
    {
        path: 'zsj/businesses',
        name: '商业管理',
        component: (resolve) => require(['../../views/zsjManagement/Businesses.vue'], resolve)
    },
    {
        path: 'zsj/supplier',
        name: '供应商管理',
        component: (resolve) => require(['../../views/zsjManagement/Supplier.vue'], resolve)
    },
    {
        path: 'zsj/commerceFirst',
        name: '商业首营管理',
        component: (resolve) => require(['../../views/zsjManagement/CommerceFirst.vue'], resolve)
    },
    {
        path: 'zsj/supplierFirst',
        name: '供应商首营管理',
        component: (resolve) => require(['../../views/zsjManagement/SupplierFirst.vue'], resolve)
    },
    {
        path: 'zsj/goods',
        name: '商品管理',
        component: (resolve) => require(['../../views/zsjManagement/goods.vue'], resolve)
    },
    {
        path: 'zsj/goodsfirst',
        name: '商品首营管理',
        component: (resolve) => require(['../../views/zsjManagement/goodsfirst.vue'], resolve),
    },
    {
        path: 'zsj/goodsfirstAdd',
        name: '商品首营管理',
        component: (resolve) => require(['../../views/zsjManagement/goodsfirstAdd.vue'], resolve),
	},
	{
        path: 'zsj/txmessage',
        name: '天祥信息设置',
        component: (resolve) => require(['../../views/zsjManagement/txmessage.vue'], resolve),
	}
]