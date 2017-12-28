import javax.persistence.*;
import java.util.List;

@Entity
public class Customer extends Company{
    private double discount;

    public Customer(String companyName, String street,
                    String city, String country,
                    double discount) {
        super(companyName, street, city, country);
        this.discount = discount;
    }

    public Customer(){}

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
