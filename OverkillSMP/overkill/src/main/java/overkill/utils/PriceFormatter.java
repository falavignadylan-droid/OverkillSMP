package overkill.utils;

public class PriceFormatter {

    public static String format(double value) {
        if (value >= 1_000_000_000) return String.format("%.2fB", value / 1_000_000_000);
        if (value >= 1_000_000) return String.format("%.2fM", value / 1_000_000);
        if (value >= 1_000) return String.format("%.2fK", value / 1_000);
        return String.format("%.0f", value);
    }

    public static double parse(String s) {
        s = s.toUpperCase();

        if (s.endsWith("B")) return Double.parseDouble(s.replace("B", "")) * 1_000_000_000;
        if (s.endsWith("M")) return Double.parseDouble(s.replace("M", "")) * 1_000_000;
        if (s.endsWith("K")) return Double.parseDouble(s.replace("K", "")) * 1_000;

        return Double.parseDouble(s);
    }
}
