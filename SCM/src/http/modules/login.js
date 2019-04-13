
import service from '../../utils/service'

let serviceLogin = service.loginService
export const login = data => {
    return serviceLogin.post('/auth',data).then((res) => {
        return res.data
    }).catch((error) => {
        return error.response.data
    })
}

