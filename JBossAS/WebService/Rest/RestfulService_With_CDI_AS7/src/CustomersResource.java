package test;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.inject.Inject; 

@Produces("application/xml")
@Path("customers")
public class CustomersResource {

        @Inject
        private beans.TestBean testBean;

	private TreeMap<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();

	public CustomersResource() {
                System.out.println("[CustomerResource] Instantiated  "+this);
		Customer customer1 = new Customer();
		customer1.setId(1111);
		customer1.setName("CustomerOne");
		customer1.setAddress("Bombay, India");

		Customer customer2 = new Customer();
		customer2.setId(2222);
		customer2.setName("CustomerTwo");
		customer2.setAddress("Pune, India");

		Customer customer3 = new Customer();
		customer3.setId(3333);
		customer3.setName("CustomerThree");
		customer3.setAddress("Bangalore, India");

                customerMap.put(customer1.getId(),customer1);
                customerMap.put(customer2.getId(),customer2);
                customerMap.put(customer3.getId(),customer3);

                if(testBean!=null)
                    {
                       System.out.println("[CustomersResource] testBean NOT NULL "+testBean);
                       testBean.setCustomerMap(customerMap);
                    }
                else
                    {
                       System.out.println("[CustomersResource] testBean IS NULL");
                    }
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)  
	@Path("all")
	public Customers getCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		customers.addAll(testBean.getCustomerMap().values());
                Customers allCustomers=new Customers();
                allCustomers.setCustomer(customers);
                return allCustomers;
	}


	@GET
	@Path("{id}")
	public Customer getCustomer(@PathParam("id") int customerId) {
		return customerMap.get(customerId);
	}

	public String addDefaultCustomer(Customer customer) {
		customerMap.put(customer.getId(), customer);
		return "Customer ID: " +  customer.getId() +" Name: "+ customer.getName()+" Address: "+customer.getAddress();
	}


	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_XML)  
	@Consumes("application/x-www-form-urlencoded")
	public Customer addCustomer(@FormParam("custId")int id,@FormParam("custName")String name,@FormParam("custAddress")String address) {
		Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setAddress(address);
                customerMap.put(customer.getId(),customer);
                testBean.setCustomerMap(customerMap);
		return customer;
	}

}
