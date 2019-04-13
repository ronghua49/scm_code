function NewError(options) {
// code: 500
// message: "Required String parameter 'ownerId' is not present"
// moreInfoUrl: "http://www.hotent.com/"
// shortMessage: "系统错误"
// state: false
// status: 500

// error: "Not Found"
// message: "No message available"
// path: "/scm/scmXsDistributorClause/v1/commerceAndAcceptState"
// status: 404
// timestamp: 1550889694772
    this.state = false;
    this.message = options.message; // 必传
    this.code = options.code || 700;
    this.moreInfoUrl = options.moreInfoUrl || "http://www.baidu.com/";
    this.shortMessage = options.shortMessage; // 必传
    this.status = options.code || 700;
}

export default {
    NewError
}