import javax.persistence.*;
import java.util.List;

@Entity
public class SupplierXIIa extends Company{
    private String bankAccountNumber;

    public SupplierXIIa(String companyName, String street,
                        String city, String country,
                        String bankAccountNumber) {
        super(companyName, street, city, country);
        this.bankAccountNumber = bankAccountNumber;
    }
    public SupplierXIIa() {
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}

