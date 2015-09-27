package jpa;
import java.lang.Integer;
import java.util.Collection;
import javax.ejb.Remote;
@Remote

public interface EmployeeServiceRemote{
  public void doAction();
  public Employee createEmployee(Integer id, String name);
  public void removeEmployee(Integer id);
  public Employee findEmployee(Integer id);
  public Collection<Employee> findAllEmployees() ;

}

