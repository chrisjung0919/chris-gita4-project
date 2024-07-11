import javax.swing.SwingUtilities;

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