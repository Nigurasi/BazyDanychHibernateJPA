import javax.persistence.*;
import java.util.List;

@Entity
public class ThisTransaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int transactionNumber;
    private int quantity;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Product> products;

    public ThisTransaction(int quantity, List<Product> products) {
        this.quantity = quantity;
        this.products = products;
    }

    public ThisTransaction(){}

    @Override
    public String toString(){
        return "TransactionNumber: " + this.transactionNumber +
                "; Quantity: " + this.quantity;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
