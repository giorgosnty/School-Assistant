package ui.PaymentsOptions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import memService.MemoryService;
import ui.MainFrame;
import ui.PaymentsOptions.PaySub.PaySubFrame;
import ui.PaymentsOptions.PaySub.PaySubPresenter;
import ui.PaymentsOptions.ShowUnpaid.ShowUnpaidFrame;
import ui.PaymentsOptions.ShowUnpaid.ShowUnpaidPresenter;

public class PaymentsOptionsPresenter {
	
	PaymentsOptionsView view;
	
	public PaymentsOptionsPresenter(PaymentsOptionsView view){
		this.view = view;
	}
	
	public void start() {
        view.setPresenter(this);
        view.open();
    }
	
    public void cancel() {
        view.close();
    }
    
    public void goBack(MemoryService data){
    	MainFrame m  = new MainFrame(data);
    	m.open();
    	view.close();
    }
    
    public void showUnpaid(MemoryService data) {
    	ShowUnpaidFrame pframe = new ShowUnpaidFrame(data);
    	ShowUnpaidPresenter pres = new ShowUnpaidPresenter(pframe);
		pres.start();
		view.close();
    }
    
    public void paySub(MemoryService data) {
    	PaySubFrame pframe = new PaySubFrame(data);
    	PaySubPresenter pres = new PaySubPresenter(pframe);
		pres.start();
		view.close();
    }
}
