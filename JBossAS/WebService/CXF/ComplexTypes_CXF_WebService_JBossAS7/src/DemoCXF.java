package ws;
import javax.jws.WebParam;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "DemoCXF", serviceName ="DemoCXFService", portName ="DemoCXFPort", targetNamespace="http://test.org")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE) 

public class DemoCXF
{
  @WebMethod()
  @WebResult(targetNamespace="http://test.org",name="updatedEmployee")
  public Employee processEmployeeSalary(@WebParam(partName = "employee", name = "employee", targetNamespace = "http://test.org")
        Employee emp, @WebParam(partName = "incrementAmount", name = "incrementAmount", targetNamespace = "http://test.org") Long incrementAmount)
  {
    System.out.println("[DemoCXF] Method Invoked....processEmployeeSalary");
    System.out.println("[DemoCXF] Before processing: "+emp);
    long incrementedSalary=emp.getEmpSalary()+incrementAmount;
    emp.setEmpSalary(incrementedSalary);
    System.out.println("[DemoCXF] After processing: "+emp);

    // Some Business Logic to Store the Employee's Updated Details in Database or Messaging System.

    return emp;
  }
}
