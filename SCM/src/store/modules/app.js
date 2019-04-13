export default {
    state: {
        isCollapse: false,  // 菜单栏折叠
        token: '',//
        isaddRouter: false,
        cght:'',
        cghtDeialId:'',
        tabObj:[],
    },
    mutations: {
        changeIsCollapse(state){
            state.isCollapse = !state.isCollapse;
        },
        setToken(state, data){
            state.token = data
        },
        changeAddRouterState(state){
            state.isaddRouter = !state.isaddRouter
        }
    },
    actions: {
    }
}