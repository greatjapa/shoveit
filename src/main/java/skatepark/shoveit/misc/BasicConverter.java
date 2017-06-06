package skatepark.shoveit.misc;

import java.util.Objects;

public class BasicConverter {

    private static final String TABLE[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public int hex2Dec(String hexadecimal) {
        Objects.requireNonNull(hexadecimal, "hexadecimal should not be null.");

        int result = 0;
        char characters[] = hexadecimal.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int value = Character.getNumericValue(characters[i]);
            result += value * (Math.pow(16, characters.length - 1 - i));
        }
        return result;
    }

    public String dec2Hex(int decimal) {
        if (decimal < 16) {
            return TABLE[decimal];
        }
        int value = decimal / 16;
        int reminder = decimal % 16;
        return dec2Hex(value) + TABLE[reminder];
    }
}
