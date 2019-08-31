/**
 * ZBF_INTERFACECallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package com.winway.scm.persistence.manager.impl;


/**
 *  ZBF_INTERFACECallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class ZBF_INTERFACECallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public ZBF_INTERFACECallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public ZBF_INTERFACECallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for zbfInterface method
     * override this method for handling normal response from zbfInterface operation
     */
    public void receiveResultzbfInterface(
        com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.ZbfInterfaceResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from zbfInterface operation
     */
    public void receiveErrorzbfInterface(java.lang.Exception e) {
    }
}
