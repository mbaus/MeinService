package com.student;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Student")
public class Student {
  private String name;
  private String studentIP;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStudentIP() {
    return studentIP;
  }

  public void setStudentIP(String studentIP) {
    this.studentIP = studentIP;
  }

}