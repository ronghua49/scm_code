import global from './global'
import axios from 'axios'
import store from '../store'
import { getToken } from './auth'
// let token = sessionStorage.getItem('token')

// let header = {
//     "Authorization": "Bearer " + token,
// }

export const loginService = axios.create({
    baseURL: global.loginUrl,
})
export const amService = axios.create({
    baseURL: global.amUrl,
})

export const scmService = axios.create({
    baseURL: global.scmUrl,
})

export const scmServiceApproval = axios.create({
    baseURL: global.scmUrlApproval,
})

// request拦截器 添加token信息
scmService.interceptors.request.use(
    config => {
        if(getToken()){
            config.headers['Authorization'] = "Bearer " + getToken()
        }
        return config
    },
    error => {
        return Promise.reject(error)
    }
)
scmService.interceptors.response.use(function(response){
    return response;
}, function(error){
    return Promise.reject(error)
})



// request拦截器 添加token信息
scmServiceApproval.interceptors.request.use(
    config => {
        if(getToken()){
            config.headers['Authorization'] = "Bearer " + getToken()
        }
        return config
    },
    error => {
        return Promise.reject(error)
    }
)


export default {
    scmService,
    loginService,
    amService,
    scmServiceApproval
}