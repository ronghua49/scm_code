import session from './storage'
const scmtoken = 'token'

export function getToken(){
    return session.get(scmtoken)
}

export function setToken(token){
    return session.set(scmtoken,token)
}

export function removeToken(){
    return session.remove(scmtoken)
}