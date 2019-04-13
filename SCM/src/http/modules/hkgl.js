import service from '../../utils/service'
let serviceScm = service.scmService

//get请求
export const get = data => {
    return serviceScm.get(data.url,{
        params: data.data
    }).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}
//post 请求
export const post = data => {
    return serviceScm.post(data.url,data.data).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}
export const delDict = data => {
    return serviceScm.delete(data.url).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}