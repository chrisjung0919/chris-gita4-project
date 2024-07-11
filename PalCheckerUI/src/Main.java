package PalCheckerUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Launch the UI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PalCheckerUI();
            }
        });
    }
}