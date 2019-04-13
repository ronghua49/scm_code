import service from '../../utils/service'

let serviceScm = service.scmService

export const getZsj = data => {
    return serviceScm.post(data.url,data.data).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const saveZsj = data => {
    return serviceScm.post(data.url,data.data).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const addZsj = data => {
    return serviceScm.post(data.url,data.data).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}


export const post = data => {
    return serviceScm.post(data.url,data.data).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const deleteA = data => {
    return serviceScm.delete(data.url).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const changeState = data => {
    return serviceScm.get(data.url,{
        params: data.data
    }).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const get = data => {
    return serviceScm.get(data.url,{
        params: data.data
    }).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}