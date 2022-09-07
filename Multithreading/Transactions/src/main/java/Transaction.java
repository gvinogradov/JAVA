import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private String srcAcc;
    private String dstAcc;
    private long amount;
    private Boolean success;
}
