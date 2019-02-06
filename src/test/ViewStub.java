package test;

import javax.swing.JOptionPane;

public class ViewStub {
	private  boolean opened;
	private int errorCount = 0;
	private int infoCount = 0;

	
	public void open() {
        opened = true;
        
    }


    public void close() {
      opened=false;
    }
    
    public boolean isOpened(){
    	return opened;
    }


    public void showError(String message) {
        errorCount++;
    }


    public void showInfo(String message) {
        infoCount++;
    }
    
    public int getErrorCount(){
    	return errorCount;
    }
    
    public int getInfoCount(){
    	return infoCount;
    }
}
