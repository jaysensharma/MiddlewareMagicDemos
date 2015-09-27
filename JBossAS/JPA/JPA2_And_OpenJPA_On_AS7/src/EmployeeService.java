package jpa;
import java.lang.Integer;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.EntityTransaction;
import javax.ejb.*;

@Stateless
public class EmployeeService implements EmployeeServiceRemote {
  @PersistenceContext(unitName="EmployeeService")
  EntityManager em;

  public EmployeeService() {
	  System.out.println("\n\tEmployeeService  Object Created. this: "+this);
  }

  @TransactionAttribute (TransactionAttributeType.REQUIRED)
  public Employee createEmployee(Integer id, String name) {
    Employee emp = new Employee();
    emp.setEmpno(id);
    emp.setEname(name);
    System.out.println("\n\t[EmployeeService] Before em.persist(emp)");

    try{ 
         
         //em.getTransaction().begin();
         em.persist(emp);
         //em.flush();
         //em.getTransaction().commit();
       } 
    catch(Exception e)
       {
          e.printStackTrace();
          try{   em.getTransaction().rollback();   } catch(Exception ee)  {  ee.printStackTrace();  }
       }

     System.out.println("\n\t[EmployeeService] Before findEmployee(id)");
     emp = findEmployee(id);
     System.out.println("\n\t[EmployeeService] Inside createEmployee() findEmployee result: "+emp);
     return emp;
  }

  public void removeEmployee(Integer id) {
	System.out.println("\n\t Inside removeEmployee("+id+")");
    Employee emp = findEmployee(id);
    if (emp != null) {
       try{ 
            //em.getTransaction().begin();
            em.remove(emp);
            //em.getTransaction().commit();
          } 
       catch(Exception e)
          {
             e.printStackTrace();
             try{   em.getTransaction().rollback();   } catch(Exception ee)  {  ee.printStackTrace();  }
          }
    }
  }

  public Employee findEmployee(Integer id) {
	System.out.println("\n\t Inside findEmployee("+id+")");
    return em.find(Employee.class, id);
  }

  @SuppressWarnings("unchecked")
  public Collection<Employee> findAllEmployees() 
     {
	System.out.println("\n\t Inside findAllEmployees()");
        Query query = em.createQuery("SELECT e FROM Employee e");
        return (Collection<Employee>) query.getResultList();
     }

  public void doAction(){
       System.out.println("\n\t Hello World...");
  }
}

