export default [
	{
    path: 'cwgl/FinacialManagement',
    name: '财务管理',
    component: (resolve) => require(['../../views/finance/FinacialManagement'], resolve)
	},
	{
    path: 'cwgl/InvoiceManagement',
    name: '销售发票管理',
    component: (resolve) => require(['../../views/finance/InvoiceManagement'], resolve)
	},
	{
    path: 'cwgl/remittanceApply',
    name: '回款勾兑申请',
    component: (resolve) => require(['../../views/finance/remittanceApply'], resolve)
	},
	{
    path: 'cwgl/AccountsManagement',
    name: '应付账记账管理',
    component: (resolve) => require(['../../views/finance/AccountsManagement'], resolve)
	},
	{
    path: 'cwgl/TicketManagement',
    name: '票折池管理',
    component: (resolve) => require(['../../views/finance/TicketManagement'], resolve)
	},
	{
    path: 'cwgl/DeliveryManagement',
    name: '发货单开票管理',
    component: (resolve) => require(['../../views/finance/DeliveryManagement'], resolve)
	}
]