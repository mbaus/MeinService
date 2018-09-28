package com.student.client;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.student.ChangeStudentDetails;
import com.student.Student;

// CXF JAX-WS Client / Consuming Web Services With CXF

public final class StudentClient {

  public static void main(String args[]) throws Exception {

    //For tracing the SOAP message

    JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

    factory.setServiceClass(ChangeStudentDetails.class);
    factory.setAddress("http://localhost:9080/ws/ChangeStudent?wsdl");
    factory.getInInterceptors().add(new LoggingInInterceptor());
    factory.getOutInterceptors().add(new LoggingOutInterceptor());
    ChangeStudentDetails client = (ChangeStudentDetails) factory.create();
    Student student = new Student();
    student.setName("Rockey");
    Student changeName = client.changeName(student);
    System.out.println("Server said: " + changeName.getName());
    System.exit(0);
  }
}