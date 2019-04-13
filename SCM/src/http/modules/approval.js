import service from '../../utils/service'

let serviceAm = service.scmServiceApproval;
let serviceScm = service.scmService

export const post = data => {
    return serviceAm.post(data.url,data.data).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const get = data => {
    return serviceAm.get(data.url,{
        params: data.data
    }).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const postscm = data => {
    return serviceScm.post(data.url,data.data).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const getscm = data => {
    return serviceScm.get(data.url,{
        params: data.data
    }).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}


export const delscm = data => {
    return serviceScm.delete(data.url).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}