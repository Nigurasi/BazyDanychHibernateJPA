import javax.persistence.*;
import java.util.List;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String companyName;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

    public Supplier(String companyName, Address address, List<Product> products) {
        this.companyName = companyName;
        this.address = address;
        this.products = products;
    }
    public Supplier() {
    }

    @Override
    public String toString(){
        return "Company Name: " + this.companyName +
                "; " + this.address.toString();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
