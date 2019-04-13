/**
 * 全局常量、方法封装模块
 * 通过原型挂载到Vue属性
 * 通过 this.Global 调用
 */
let ip = 'http://114.116.86.0'
let scmip = 'http://114.116.86.0'

//172.18.1.213:8088 
// let szamip = 'http://scmdevuc.winwayworld.com'
// //172.18.1.213:8086 
// let szamip2 = 'http://scmdevbpmr.winwayworld.com'
// //172.18.1.213:8087 
// let szamip3 = 'http://scmdevbpmm.winwayworld.com'
// //172.18.1.213:8082 
// let szamip4 = 'http://scmdevform.winwayworld.com'
// //172.18.1.213:8084 
// let szamip5 = 'http://scmdevportal.winwayworld.com'
// //172.18.1.213:8091 
// let szamip6 = 'http://scmdevmaster.winwayworld.com'
// //172.18.1.213:8081 
// let szamip7 = 'http://scmdevsearch.winwayworld.com'

 // 后台管理系统服务器地址
export const loginUrl = scmip+':8088'//登录
export const amUrl = ip+':8088'//登录
export const menuUrl = ip+':8084'//获取菜单栏
export const scmUrl = scmip + ':8999'//scm字典 主数据
export const scmUrlApproval = scmip + ':8086'//scm审批

// export const loginUrl = szamip//登录
// export const amUrl = szamip//登录
// export const menuUrl = szamip5//获取菜单栏
// export const scmUrl = scmip + ':8999'//scm字典 主数据
// export const scmUrlApproval = szamip2//scm审批

 // 系统数据备份还原服务器地址

export default {
    amUrl,
    loginUrl,
    menuUrl,
    scmUrl,
    scmUrlApproval
}