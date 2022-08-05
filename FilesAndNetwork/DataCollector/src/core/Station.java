package core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class Station {
    private final String name;
    private final String lineNumber;
    private final boolean hasConnection;
    private Double depth;
    private Date date;

    public String toString() {
        String result = "(" + lineNumber + ")" + name;
        if (depth != null) {
            result += " depth: " + depth;
        }
        if (date != null) {
            result += " date: " + (new SimpleDateFormat("dd.MM.yyyy")).format(date);
        }
        return result;
    }
}
