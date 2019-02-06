package ui.PaymentsOptions.PaySub;

import java.util.List;

import domain.Customer;
import domain.MonthlySubscription;
import ui.View;

public interface PaySubView extends View{
	
	public void setPresenter(PaySubPresenter p);
	
	public List<Customer> findCustomerByLastName(String lastname);
	
	public List<Customer> findCustomerByTelephone(String telephone);
	
	public List<Customer> findCustomerByLastNameAndTelephone(String lastname, String telephone);
	
	public void showUnpaidSubscriptionList(List<MonthlySubscription> m_subs);
	
	public void refreshAmount();
	
	public List<MonthlySubscription> getUnpaidSubs();
	
	public List<MonthlySubscription> getUnpaidCurrentList();
}
