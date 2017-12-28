import javax.persistence.*;

@Entity
public class CustomerB extends CompanyB{
    private double discount;

    public CustomerB(String companyName, String street,
                    String city, String country,
                    double discount) {
        super(companyName, street, city, country);
        this.discount = discount;
    }

    public CustomerB(){}

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
