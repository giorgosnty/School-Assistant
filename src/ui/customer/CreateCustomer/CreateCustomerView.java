package ui.customer.CreateCustomer;

import ui.View;
import domain.*;
import java.util.ArrayList;
import java.util.List;

public interface CreateCustomerView extends View{

	void setCreatePresenter(CreatePresenter presenter);

	void updateList(List<Customer> arr);
	
	public void returnToCustomerFrame();
}
