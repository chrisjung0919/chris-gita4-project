class PalCheckerUI extends JFrame {
    private JTextArea textArea;
    private JButton checkButton;

    public PalCheckerUI() {
        // Set up the JFrame
        setTitle("Palindrome Checker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create JTextArea for user input
        textArea = new JTextArea();
        textArea.setBounds(20, 20, 300, 100);
        add(textArea);

        // Create JButton to check for palindrome
        checkButton = new JButton("Check Palindrome");
        checkButton.setBounds(20, 130, 150, 30);
        add(checkButton);

        // Add ActionListener to the button
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get text from JTextArea
                String userInput = textArea.getText();

                // Check if the entered text is a palindrome
                boolean isPalindrome = PalindromeChecker.isPalindrome(userInput);

                // Display the result
                displayResult(isPalindrome);
            }
        });

        // Set frame size and visibility
        setSize(350, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void displayResult(boolean isPalindrome) {
        if (isPalindrome) {
            JOptionPane.showMessageDialog(this, "The text is a palindrome!");
        } else {
            JOptionPane.showMessageDialog(this, "The text is not a palindrome.");
        }
    }
}