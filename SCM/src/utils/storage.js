const sessionStorage = window.sessionStorage

function get(key){
    const value = sessionStorage.getItem(key) || ''

    try {
        return JSON.parse(value)
    } catch (e){
        return value
    }
}

function set(key, value){
    if(typeof value === 'object'){
        value = JSON.stringify(value)
    }
    sessionStorage.setItem(key, value)
}

function remove(key){
    sessionStorage.removeItem(key)
}

export default {
    set,
    get,
    remove
}