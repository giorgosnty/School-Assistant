package ui.customer.EditCustomer;

import java.util.ArrayList;

import domain.Customer;
import ui.View;

public interface EditCustomerView extends View{


	void setEditPresenter(EditPresenter editPresenter);

	void setLastName(String last);
	void setFirstName(String first);
	void setAddress(String address);
	void setEmail(String email);
	void setPhone(String phone);
	public void returnToCustomerFrame();
}
