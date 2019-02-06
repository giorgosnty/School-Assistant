package ui.MSubs.monthlySubscription;

import java.util.ArrayList;
import java.util.List;

import domain.Customer;
import ui.View;

public interface MonthlySubscriptionView extends View{

	void setMonthlyPresenter(MonthlyPresenter presenter);
	
	List<Customer> findCustomerByLastNameAndTelephone(String lastname, String telephone);

	List<Customer> findCustomerByLastName(String lastname);

	List<Customer> findCustomerByTelephone(String telephone);

	void showMessage(String string);

	void showCustomersListData(List<Customer> cust);
}
