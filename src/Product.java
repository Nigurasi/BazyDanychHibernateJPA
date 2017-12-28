import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String productName;
    private int unitsOnStock;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Supplier_ID")
    private Supplier supplier;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST)
    private List<ThisTransaction> transactions;

    public Product(){}

    public Product(String productName, int unitsOnStock, Category category){
        this.productName = productName;
        this.unitsOnStock = unitsOnStock;
        this.category = category;
        this.transactions = new ArrayList<>();
    }

    @Override
    public String toString(){
        return "Product Name: " + this.productName +
                "; Category: " + this.category +
                "; Units: " + this.unitsOnStock;
    }

    public void addToTransactions(ThisTransaction transaction){
        this.transactions.add(transaction);
        transaction.getProducts().add(this);
    }

    public List<ThisTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<ThisTransaction> transactions) {
        this.transactions = transactions;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return this.supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProductName() {
        return this.productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getUnitsOnStock() {
        return this.unitsOnStock;
    }
    public void setUnitsOnStock(int unitsOnStock) {
        this.unitsOnStock = unitsOnStock;
    }
}
