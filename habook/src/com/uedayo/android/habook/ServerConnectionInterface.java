
package com.uedayo.android.habook;

public interface ServerConnectionInterface {

	// for basic auth
    String Host = "";
    String Realm = Host;
    String UserName = "";
    String Password = "";
    
    String ServerHost = "http://" + Host;

    String Lend = ServerHost + "/books/lend/";
    String Return = ServerHost + "/books/return/";
    String Search = ServerHost + "/books/";
    String User = ServerHost + "/users/";
}
