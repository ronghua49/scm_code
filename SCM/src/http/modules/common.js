import service from '../../utils/service'
import storage from '@/utils/storage'

let serviceAm = service.amService
let serviceScm = service.scmService

export const getDivision = data => {//获取登录人商务分区和省区
    serviceAm.defaults.headers.common['Authorization'] = "Bearer "+sessionStorage.getItem('token')
    return serviceAm.post('/api/scmuc/v1/getBusinessDivisionInfo').then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const getLoginId = data => {//获取登录人公司ID
    serviceAm.defaults.headers.common['Authorization'] = "Bearer "+sessionStorage.getItem('token')
    return serviceAm.post('/api/scmuc/v1/getCurrentCompanyId').then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const getcommercelevellist = data => {//获取商业级别和职能类型
    return serviceScm.post(
        '/scm/scmZdCommerceLevel/v1/downBoxList',
        {
            params: {},
            querys: [],
            sorter: []
          }
    ).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}


export const getAgentType = data => {//获取剂型数据
    return serviceScm.post(
        '/scm/scmZdAgenttype/v1/list',
        {
            params: {},
            querys: [],
            sorter: []
          }
    ).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const getManageMentScope = data => {//获取生产经营范围数据
    return serviceScm.post(
        '/scm/scmZdManageMentScope/v1/list',
        {
            params: {},
            querys: [],
            sorter: []
          }
    ).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const getPromiseBook = data => {//获取剂型数据
    return serviceScm.post(
        '/scm/scmZdPromiseBook/v1/list',
        {
            params: {},
            querys: [],
            sorter: []
          }
    ).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}


export const getcommercefirstList = data => {//获取商业首营下拉列表
    return serviceScm.get(
        '/scm/scmZsjCommerce/v1/downBox/'+storage.get('ownerId')+'',
    ).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

export const getsupplierfirstList = data => {//获取商业首营下拉列表
    return serviceScm.get(
        '/scm/scmZsjSupplier/v1/downBox/'+storage.get('ownerId')+'',
    ).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}



