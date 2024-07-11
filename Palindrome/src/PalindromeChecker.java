class PalindromeChecker {
    public static boolean isPalindrome(String text) {
        // Remove spaces and convert to lowercase
        String processedText = text.replaceAll("\\s", "").toLowerCase();

        // Check if the processed text is a palindrome
        int length = processedText.length();
        for (int i = 0; i < length / 2; i++) {
            if (processedText.charAt(i) != processedText.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
