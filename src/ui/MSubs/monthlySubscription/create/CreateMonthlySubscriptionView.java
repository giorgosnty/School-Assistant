package ui.MSubs.monthlySubscription.create;

import domain.Slot;
import ui.View;

public interface CreateMonthlySubscriptionView extends View {


	void setCreateMonthlyPresenter(CreateMonthlyPresenter createMonthlyPresenter);

	Slot getSelectedSlot(int selectedIndex);

}
