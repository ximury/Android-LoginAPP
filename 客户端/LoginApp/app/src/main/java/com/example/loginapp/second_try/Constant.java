package com.example.loginapp.second_try;

/**
 * 使用localhost会报错
 * 原来模拟器默认把127.0.0.1和localhost当做本身了，
 * 在androidStudio模拟器上可以用10.0.2.2代替127.0.0.1和localhost；
 * 另外如果是在局域网环境（手机wifi连接电脑热点），
 * 可以用 192.168.0.x或者192.168.1.x(根据具体配置)连接本机,这样应该就不会报错了
 */
public class Constant {
    //保证和服务端的访问地址一致，同时，服务端要一直开着，即那边要一直处于运行状态
    public static String URL = "http://127.0.0.1:8089/Servlet/"; // IP地址、端口号要注意

    public static String URL_Register = URL + "RegisterServlet";
    public static String URL_Login = URL + "LoginServlet";
}