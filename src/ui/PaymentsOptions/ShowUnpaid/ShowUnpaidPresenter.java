package ui.PaymentsOptions.ShowUnpaid;

import javax.swing.JOptionPane;

import domain.MonthlySubscription;
import memService.MemoryService;
import ui.MainFrame;

public class ShowUnpaidPresenter {
	private ShowUnpaidView view;
	
	public ShowUnpaidPresenter(ShowUnpaidView v){
		this.view = v;
	}
	
	public void start() {
        view.setPresenter(this);
        view.open();
    }
	
    public void cancel() {
        view.close();
    }
    
    public void goBack(MemoryService data) {
    	MainFrame m = new MainFrame(data);
    	m.open();
    	view.close();
    }
    
    public void showSubDetails(int index){
    	if(index < 0){
    		view.showError("Error: List index doesn't exists");
    	} else {
    		MonthlySubscription ms = view.findSub(index);
    		view.showSubInfo(ms.toString());
    	}
    }
}
