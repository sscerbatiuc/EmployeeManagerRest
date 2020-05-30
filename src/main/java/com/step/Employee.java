package com.step;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L; 
    private int id;
    private String name;
    private String address;
    private String phoneno;

    public Employee() {
    }

    public Employee(String name, String address, String phoneno) {
        this.name = name;
        this.address = address;
        this.phoneno = phoneno;
    }

    public Employee(int id, String name, String address, String phoneno) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneno = phoneno;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneno() {
        return phoneno;
    }

    @XmlElement
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public int getId() {
        return id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", address=" + address + ", phoneno=" + phoneno + "]";
    }

}
