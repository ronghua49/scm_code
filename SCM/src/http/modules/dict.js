import service from '../../utils/service'

let serviceScm = service.scmService

export const getDict = data => {
    return serviceScm.post(data.url,data.data).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const addDict = data => {
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

export const delDictPl = data => {
    return serviceScm.delete(data.url).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}
