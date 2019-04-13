import service from '../../utils/service'

let serviceScm = service.scmService
//获取列表
export const getFhgl = data => {
    return serviceScm.post(data.url,data.data).then((res) => {
        if (res.state === false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
        } else {
            return res.data
        }
    }).catch((error) => {
        
        return error.response.data
    })
}
// 保存
export const saveFhgl = data => {
    return serviceScm.post(data.url,data.data).then((res) => {
        if (res.state === false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
        } else {
            return res.data
        }
    }).catch((error) => {
        return error.response.data
    })
}
// 新增
export const addFhgl = data => {
    return serviceScm.post(data.url,data.data).then((res) => {
        if (res.state === false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
        } else {
            return res.data
        }
    }).catch((error) => {
        return error.response.data
    })
}

export const get = data => {
    return serviceScm.get(data.url,{
        params: data.data
    }).then((res) => {
        // console.log(res);
        if (res.state === false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
        } else {
            return res.data
        }
    }).catch((error) => {
        // console.log(error);
        return error.response.data
    })
}

// 商务分区,省区二级联动下拉框
export const getZdBusinessDivision = data => {
    //serviceScm.defaults.headers.common['Authorization'] = "Bearer " + sessionStorage.getItem('token')
    return serviceScm.post('/scm/scmZdBusinessDivision/v1/list', {
        "pageBean": {
          "page": 0,
          "pageSize": 0,
          "showTotal": true
        },
        "params": {},
        "querys": [],
        "sorter": []
      }).then((res) => {
        if (res.state === false) {
            this.$message({
              message: res.shortMessage,
              type: "error",
              duration: 800
            });
        } else {
            return res.data
        }
    }).catch((error) => {
        return error.response.data
    })
}