//商业信息菜单路由配置
export default [
    {
        path: 'syxx/BusinessAcceptStatusManage',
        name: '商业认可状态',
        // 异步组件和 webpack 的 code-splitting 功能一起配合使用
        // 这个特殊的 `require` 语法将会告诉 webpack
        // 自动将你的构建代码切割成多个包，这些包
        // 会通过 Ajax 请求加载 
        component: (resolve) => require(['../../views/syxxManagement/BusinessAcceptStatusManage.vue'], resolve)
    }
]