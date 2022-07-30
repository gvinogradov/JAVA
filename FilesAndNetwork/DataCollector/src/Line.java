import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Line {
    private String name;
    private String number;

    public String toString() {
        return number + " - " + name;
    }
}
