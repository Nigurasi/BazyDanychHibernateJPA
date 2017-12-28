import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class CompanyB {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String companyName;
    private String street;
    private String city;
    private String country;

    public CompanyB(String companyName, String street, String city, String country) {
        this.companyName = companyName;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public CompanyB(){}

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
}
