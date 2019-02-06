package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.Customer;
import memService.MemoryService;
import ui.customer.CustomerPresenter;
import ui.customer.CreateCustomer.CreatePresenter;
import test.CustomerViewStub;
import test.ViewStub;


public class CustomerPresenterTest {

	private CustomerViewStub customerViewStub;
	private CustomerPresenter pres;
	private CreatePresenter cr_pres;
	private MemoryService data;
	
	
	
	@Before
	public void setUp(){
		data= new MemoryService();
		customerViewStub=new CustomerViewStub(data);
		pres=new CustomerPresenter(customerViewStub);
		customerViewStub.setCustomerPresenter(pres);
		
		
		
	}
	
	@Test
	public void connection() {
		pres.start();
		Assert.assertTrue(customerViewStub.isOpened());
		Assert.assertEquals(4,data.getCustomers().size());
	}
	
	@Test
	public void cancelation(){
		pres.cancel();
		Assert.assertFalse(customerViewStub.isOpened());
	}
	
	@Test
	public void SearchWithNoInput(){
		pres.searchCustomer("","");
		Assert.assertEquals(2,customerViewStub.getErrorCount());
		Assert.assertEquals(0,customerViewStub.getInfoCount());
	}
	
	@Test 
	public void customerPhoneFound(){
		pres.searchCustomer("","6972746952");
		Assert.assertEquals(0,customerViewStub.getErrorCount());
		Assert.assertEquals(1,customerViewStub.getInfoCount());
	
	}
	
	@Test 
	public void customerNameFound(){
		pres.searchCustomer("H","");
		Assert.assertEquals(0,customerViewStub.getErrorCount());
		Assert.assertEquals(1,customerViewStub.getInfoCount());
	}
	
	@Test 
	public void customerNameAndPhoneFound(){
		pres.searchCustomer("H","6972746952");
		Assert.assertEquals(0,customerViewStub.getErrorCount());
		Assert.assertEquals(1,customerViewStub.getInfoCount());
	}
	
	@Test
	public void goBack(){
		pres.goBack(data);
		Assert.assertFalse(customerViewStub.isOpened());
	}
	
	
	
	public void deleteCustomer(){
		Customer c = new Customer("Triple","H","6972746952","Valaoritou 12","mymail@gmail.com");
		pres.deleteCustomer(data, c);
		Assert.assertTrue(customerViewStub.isOpened());
		Assert.assertEquals(1, customerViewStub.getInfoCount());
	}

}
