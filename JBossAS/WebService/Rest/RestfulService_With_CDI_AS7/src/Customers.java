package test;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;
@XmlRootElement
public class Customers {

	private List<Customer> customer;

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
}
