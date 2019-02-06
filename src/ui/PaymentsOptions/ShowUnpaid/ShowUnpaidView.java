package ui.PaymentsOptions.ShowUnpaid;

import domain.MonthlySubscription;
import ui.View;

public interface ShowUnpaidView extends View {
	public void setPresenter(ShowUnpaidPresenter p);
	
	public MonthlySubscription findSub(int index);
	
	public void showSubInfo(String info);
}
