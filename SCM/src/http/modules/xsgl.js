import service from '../../utils/service';
import errorWegit from '../../utils/error';
let serviceScm = service.scmService;
let serviceAm = service.amService;
let serviceApproval = service.scmServiceApproval;
function rejectFunc(error, vueInstance) {
    vueInstance.loading = false;
    console.log(error);
    let msg = 'Error! Request is failed!';
    if(error.response) {
        msg = error.response.data.shortMessage;
    }
    vueInstance.$message({
        message: msg,
        type: "error",
        duration: 2000
    });
}

// get 请求
export const get = (data, resolve) => {
    return serviceScm.get(data.url,{ params: data.data }).then((res) => {
        if (res.state === false) {
            throw new errorWegit.NewError(res)
        } else {
            if(resolve && typeof resolve === 'function') {
                resolve(res.data);
            } else {
                return res.data;
            }
        }
    }).catch((error) => {
        rejectFunc(error, data.vueInstance);
    });
}
// post 请求
export const post = (data, resolve) => {
    return serviceScm.post(data.url, data.data).then((res) => {
        // console.log(res);
        if (res.state === false) {
            throw new errorWegit.NewError(res)
        } else {
            if(resolve && typeof resolve === 'function') {
                resolve(res.data);
            } else {
                return res.data;
            }
            
        }
    }).catch((error) => {
        rejectFunc(error, data.vueInstance);
    });
}
// post 请求
export const deletes = (data, resolve) => {
    return serviceScm.delete(data.url).then((res) => {
        // console.log(res);
        if (res.state === false) {
            throw new errorWegit.NewError(res)
        } else {
            if(resolve && typeof resolve === 'function') {
                resolve(res.data);
            } else {
                return res.data;
            }
            
        }
    }).catch((error) => {
        rejectFunc(error, data.vueInstance);
    });
}
// getApproval 请求
export const getAproval = (data, resolve) => {
    return serviceApproval.get(data.url,{ params: data.data }).then((res) => {
        console.log(res.status !== 200 || res.state === false);
        if (res.state === false) {
            throw new errorWegit.NewError(res)
        } else {
            resolve(res.data);
        }
    }).catch((error) => {
        rejectFunc(error, data.vueInstance);
    });
}
// postApproval 请求
export const postApproval = (data, resolve) => {
    return serviceApproval.post(data.url, data.data).then((res) => {
        console.log(res);
        if (res.state === false) {
            throw new errorWegit.NewError(res)
        } else {
            resolve(res.data);
        }
    }).catch((error) => {
        rejectFunc(error, data.vueInstance);
    });
}


// 商务分区,省区二级联动下拉框
export const getZdBusinessDivision = (data, resolve) => {
    serviceAm.defaults.headers.common['Authorization'] = "Bearer "+sessionStorage.getItem('token');
    return serviceAm.post('/api/scmuc/v1/getBusinessDivisionInfo').then((res) => {
        console.log(res);
        if (res.state === false) {
            throw new errorWegit.NewError(res);
        } else {
            resolve(res.data);
        }
    }).catch((error) => {
        rejectFunc(error, data.vueInstance);
    });
}