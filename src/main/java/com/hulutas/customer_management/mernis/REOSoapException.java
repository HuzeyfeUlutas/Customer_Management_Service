

package com.hulutas.customer_management.mernis;
//------------------------------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 9.0.1.0
//
// Created by Quasar Development 
//
// This class has been generated for test purposes only and cannot be used in any commercial project.
// To use it in commercial project, you need to generate this class again with Premium account.
// Check https://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account.
//
// Licence: 6014FC0604699FF8992147336EC24F44C8CF0689BD3E7F550442133BA8D3941AD2A782C2E04B6CBF51A5C0805714781AE6CDE09131A34E33B9C52B5358460D35
//------------------------------------------------------------------------

public class REOSoapException extends java.lang.Exception
{
    private org.w3c.dom.Element _details=null;
    private Object _fault = null;
    
    public REOSoapException(String message,org.w3c.dom.Element details)
    {
        super(message);
        _details=details;
    }

    public REOSoapException(Object fault)
    {
        _fault=fault;
    }

    public Object getFault() {
        return _fault;
    }


    public org.w3c.dom.Element getDetails() {
        return _details;
    }
}