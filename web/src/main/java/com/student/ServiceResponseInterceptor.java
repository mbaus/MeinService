package com.student;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ServiceResponseInterceptor extends AbstractPhaseInterceptor {

  public ServiceResponseInterceptor() {
    super(Phase.RECEIVE);
  }

  public void handleMessage(Message message) {
    System.out.println("message "+message);
    message.put(Message.ENCODING, "UTF-8");
    InputStream is = message.getContent(InputStream.class);

    if(is!=null){
      CachedOutputStream bos = new CachedOutputStream();
      try{
        IOUtils.copy(is,bos);
        String soapMessage = new String(bos.getBytes());
        System.out.println("-------------------------------------------");
        System.out.println("incoming message is " + soapMessage);
        System.out.println("-------------------------------------------");
        bos.flush();
        message.setContent(InputStream.class, is);

        is.close();
        InputStream inputStream = new ByteArrayInputStream(changeName(soapMessage).getBytes());
        message.setContent(InputStream.class, inputStream);
        bos.close();
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }

  private String changeName(String soapMessage) {
    soapMessage = soapMessage.replaceAll("Rockey", "Ramu");
    System.out.println("After change message is " + soapMessage);
    return soapMessage;
  }
}