
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
import java.net.URL;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public interface REOConnectionProvider
{
    REOResponseData sendRequest(String url,String requestBody,HashMap< String,String> httpHeaders, REORequestResultHandler handler) throws java.lang.Exception;
}

class REOHttpConnectionProvider implements REOConnectionProvider{

    public void prepareRequest(HttpURLConnection url,String requestBody, REORequestResultHandler handler) throws java.lang.Exception {
        OutputStreamWriter wr = new OutputStreamWriter(url.getOutputStream());
        wr.write(requestBody);
        wr.flush();
    }

    @Override
    public REOResponseData sendRequest(String url,String requestBody, HashMap< String, String> httpHeaders,REORequestResultHandler handler ) throws java.lang.Exception
    {
        URL urlObject=new URL(url);
        HttpURLConnection connection=(HttpURLConnection)urlObject.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        for(Map.Entry<String, String> entry : httpHeaders.entrySet())
        {
            connection.setRequestProperty(entry.getKey(),entry.getValue());
        }

        prepareRequest(connection,requestBody,handler);


        REOResponseData response=new REOResponseData();
        for(Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet())
        {
            if(entry.getKey()!=null)
            {
                String values = "";
                for (String value : entry.getValue()) {
                    values = values + value;
                }
                response.getHeaders().put(entry.getKey(),values);
            }
        }
        
        try(InputStream dataStream = getResponseStream(connection,response,handler))
        {
            response.setBody(REOHelper.streamToString(dataStream));
        }
        
        return response;
    }

    protected InputStream getResponseStream(
        HttpURLConnection url,
        REOResponseData response,
        REORequestResultHandler handler
    ) throws java.lang.Exception
    {
        try{
            return url.getInputStream();
        }
        catch (IOException ex)
        {
            return url.getErrorStream();
        }
    }
}