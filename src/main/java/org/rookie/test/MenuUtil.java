package org.rookie.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 42784 on 2016/10/17.
 */
public class MenuUtil {

    public static void redirectUri()
    {
        String appid="wx8951557abc6a6697";
        String host="es.saif.sjtu.edu.cn";
        String pageCode="account";
        String redirect_uri = "";
        try {
            redirect_uri = URLEncoder.encode("http://"+host+"/weixinPage?p="+pageCode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";

        System.out.println("result = " + url);
    }


    public static void redirectUriNew()
    {
        String appid="wx969b93ced39adf83";
        String host="bb613523.ngrok.io";
        String pageCode="/weixin/app/application/application.html,v2";
        String redirect_uri = "";
        try {
            redirect_uri = URLEncoder.encode("http://"+host+"/weixinPage?p="+pageCode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";

        System.out.println("result = " + url);
    }

    public static void main(String[] arg){
        //redirectUri();
        redirectUriNew();
    }
}
