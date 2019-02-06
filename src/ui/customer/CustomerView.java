package ui.customer;
import java.util.ArrayList;
import java.util.List;

import domain.Customer;
import ui.View;


public interface CustomerView extends View {
       
  
    void setCustomerPresenter(CustomerPresenter presenter);

	List<Customer> findCustomerByLastNameAndTelephone(String lastname, String telephone);

	List<Customer> findCustomerByLastName(String lastname);

	List<Customer> findCustomerByTelephone(String telephone);

	void showCustomersListData(List<Customer> customers);
}
