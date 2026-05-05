import javax.swing.*;

public class InputValidator {

    // ================= TEXT (GENERAL SAFE INPUT) =================
    public static String getText(JFrame frame, String message) {

        String input = JOptionPane.showInputDialog(frame, message);

        if (input == null) return null;

        input = input.trim();

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "❌ Input cannot be empty");
            return null;
        }

        return input;
    }

    // ================= NAME ONLY (STRICT VALIDATION) =================
    public static String getNameOnly(JFrame frame, String message) {

        String input = JOptionPane.showInputDialog(frame, message);

        if (input == null) return null;

        input = input.trim();

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "❌ Name cannot be empty");
            return null;
        }

        // ✔ Letters only (Arabic + English) + spaces
        // ❌ blocks numbers, symbols, :, _, etc.
        if (!input.matches("[\\p{L} ]+")) {
            JOptionPane.showMessageDialog(frame,
                    "❌ Invalid Name\nOnly letters are allowed (no numbers or symbols)");
            return null;
        }

        return input;
    }

    // ================= INTEGER VALIDATION =================
    public static Integer getInt(JFrame frame, String message) {

        String input = JOptionPane.showInputDialog(frame, message);

        try {
            if (input == null || input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "❌ Invalid Input");
                return null;
            }

            return Integer.parseInt(input.trim());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "❌ Please enter a valid number");
            return null;
        }
    }

    // ================= DOUBLE VALIDATION =================
    public static Double getDouble(JFrame frame, String message) {

        String input = JOptionPane.showInputDialog(frame, message);

        try {
            if (input == null || input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "❌ Invalid Input");
                return null;
            }

            return Double.parseDouble(input.trim());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "❌ Please enter a valid number");
            return null;
        }
    }
}