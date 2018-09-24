
package de.dacomb.webservice.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "de.dacomb.webservice.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

