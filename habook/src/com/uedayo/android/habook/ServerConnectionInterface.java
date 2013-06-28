
package com.uedayo.android.habook;

public interface ServerConnectionInterface {

    String ServerHost = "";

    String Lend = ServerHost + "/books/lend/";
    String Return = ServerHost + "/books/return/";
    String Search = ServerHost + "/books/";
    String User = ServerHost + "/users/";
}
