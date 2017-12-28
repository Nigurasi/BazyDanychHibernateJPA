import javax.persistence.*;
import java.util.List;

@Entity
@SecondaryTable(name="AddressNew")
public class SupplierNew {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String companyName;

    @Column(table = "AddressNew")
    private String street;
    @Column(table = "AddressNew")
    private String city;
    @Column(table = "AddressNew")
    private String country;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

    public SupplierNew(String companyName, String street,
                       String city, String country, List<Product> products) {
        this.companyName = companyName;
        this.street = street;
        this. city = city;
        this.country = country;
        this.products = products;
    }
    public SupplierNew() {
    }

    @Override
    public String toString(){
        return "Company Name: " + this.companyName +
                "; ";
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
