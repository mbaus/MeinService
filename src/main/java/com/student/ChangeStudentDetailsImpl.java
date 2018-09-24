package com.student;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.w3c.dom.Element;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.student.ChangeStudentDetails")
public class ChangeStudentDetailsImpl implements ChangeStudentDetails {
  public Student changeName(Student student) throws ServiceException {

    Message message = PhaseInterceptorChain.getCurrentMessage();

    SoapMessage soapMessage = (SoapMessage) message;
    List<Header> list = soapMessage.getHeaders();
    for (Header header : list) {
      System.out.println("Country: " + ((Element) header.getObject()).getTextContent());
    }


    //student.setName("Hello "+student.getName());
    //return student;

    if (student.getName().equals("Rockey")) {
      student.setName((new StringBuilder("HELLO ")).append(student.getName()).toString());
      return student;
    } else {
      ServiceExceptionDetails ServiceExceptionDetailsArray[] = new ServiceExceptionDetails[1];
      ServiceExceptionDetails serviceExceptionDetails = new ServiceExceptionDetails();
      serviceExceptionDetails.setFaultCode("100");
      serviceExceptionDetails.setFaultMessage("Student Name is not correct");
      ServiceExceptionDetailsArray[0] = serviceExceptionDetails;
      throw new ServiceException("Fault Message", ServiceExceptionDetailsArray);

    }
  }
}