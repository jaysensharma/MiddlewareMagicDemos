package ws;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employee", propOrder = {
    "empName",
    "empNo",
    "empSalary"
})

public class Employee
  {
    public String empName;
    public Long empNo;
    public Long empSalary;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String value) {
        this.empName = value;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long value) {
        this.empNo = value;
    }

    public Long getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(Long value) {
        this.empSalary = value;
    }

  }
