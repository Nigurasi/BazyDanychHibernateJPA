import javax.persistence.*;

@Entity
public class CustomerC extends CompanyC{
    private double discount;

    public CustomerC(String companyName, String street,
                     String city, String country,
                     double discount) {
        super(companyName, street, city, country);
        this.discount = discount;
    }

    public CustomerC(){}

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
