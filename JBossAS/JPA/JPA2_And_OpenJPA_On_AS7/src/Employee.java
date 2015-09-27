package jpa;
import java.io.Serializable;
import javax.persistence.*;
import java.lang.Integer;

@Entity
@Table(name="Employee")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer empno;

	private String ename;

    public Employee() {
    	System.out.println("Employee Object Created. this: "+this);
    }

	public Integer getEmpno() {
		return this.empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

}
