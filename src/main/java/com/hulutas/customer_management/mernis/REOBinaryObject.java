

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

public class REOBinaryObject implements java.io.Serializable
{
    private byte[] _bytes = null;    
    private transient java.io.InputStream _stream = null;

    private String _contentType = null;

    public REOBinaryObject(byte[] bytes) {
        _bytes = bytes;
    }

    public REOBinaryObject(java.io.InputStream stream) {
        _stream = stream;
    }
    
    public Object getUnderlayingObject()
    {
        if (_bytes != null)
            return _bytes;
        else if (_stream != null)
            return _stream;
        else 
            return null;
    }
    
    public java.io.InputStream getStream()
    {
        return _stream;
    }
    
    public byte[] getBytes() {
        return _bytes;
    }

    public void setBytes(byte[] bytes) {
        this._bytes = bytes;
    }

    public String getContentType() {
        return _contentType;
    }

    public void setContentType(String contentType) {
        this._contentType = contentType;
    }
}