import javax.persistence.*;

@Entity
public class SupplierXIIb extends CompanyB{
    private String bankAccountNumber;

    public SupplierXIIb(String companyName, String street,
                        String city, String country,
                        String bankAccountNumber) {
        super(companyName, street, city, country);
        this.bankAccountNumber = bankAccountNumber;
    }
    public SupplierXIIb() {
    }

    @Override
    public String toString(){
        return "Company Name: " + super.getCompanyName() +
                "; Bank Account" + this.bankAccountNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}

