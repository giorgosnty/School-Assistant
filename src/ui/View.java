package ui;

public interface View {
    void open();
    
    void close();
    
    void showError(String message);

    void showInfo(String message);
}
