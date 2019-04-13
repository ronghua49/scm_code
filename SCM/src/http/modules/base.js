import service from '../../utils/service'

let serviceAm = service.scmServiceApproval;
let serviceScm = service.scmService//8999


export const postScm = data => {
    return serviceScm.post(data.url,data.data).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const getScm = data => {
    return serviceScm.get(data.url,{
        params: data.data
    }).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}


export const delScm = data => {
    return serviceScm.delete(data.url).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}