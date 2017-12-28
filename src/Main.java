import org.hibernate.*;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static SessionFactory sessionFactory = null;
    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory =
                    configuration.configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void main(final String[] args) throws Exception {
        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        Category newCategory = new Category("Rozrywka");
        Category anotherNewCategory = new Category("Jedzenie");
        session.save(newCategory);
        session.save(anotherNewCategory);
        String productName[] = {"Książka", "Film", "Gra PC"};
        String anotherProductName[] = {"Sok", "Pączek", "Chleb"};
        int quantity[] = {12, 20, 5};

        for (int i = 0; i < productName.length; i++) {
            Product newProduct = new Product(productName[i], quantity[i], newCategory);
            session.save(newProduct);
            newCategory.addToCategory(newProduct);

            newProduct = new Product(anotherProductName[i], quantity[i], anotherNewCategory);
            session.save(newProduct);
            anotherNewCategory.addToCategory(newProduct);
        }
        tx.commit();

        tx = session.beginTransaction();
        Product product1 = session.find(Product.class, 3);
        Product product2 = session.find(Product.class, 4);
        Product product3 = session.find(Product.class, 5);
        Product product4 = session.find(Product.class, 6);
        Product product5 = session.find(Product.class, 7);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);


        //Supplier supplier = new Supplier("Znak",
                //"Kościuszki 40", "Kraków", products);
        Address a2 = new Address("Kościuszki 40", "Kraków", "Polska");
        Supplier supplier = new Supplier("Znak", a2, products);

        session.save(supplier);
        product1.setSupplier(supplier);
        product2.setSupplier(supplier);
        product3.setSupplier(supplier);
        tx.commit();


        List<Object[]> allProducts = session.createQuery("from Supplier as s " +
                "join s.products as p where s.companyName=:name")
                .setParameter("name", "Znak")
                .getResultList();
        System.out.println(allProducts.size());
        for(Object[] p: allProducts){
            System.out.println(p[0].toString() + " ------> " + p[1].toString());
        }

        List<Object[]> exactSupplier = session.createQuery("from Product as p " +
                "join p.supplier as s where p.productName=:name")
                .setParameter("name", "Sok")
                .getResultList();
        System.out.println(exactSupplier.size());
        for(Object[] p: exactSupplier){
            System.out.println(p[0].toString() + " ------> " + p[1].toString());
        }

        //Creating transactions
        tx = session.beginTransaction();
        List <Product> productsT1 = new ArrayList<>();
        productsT1.add(product3);
        productsT1.add(product2);
        ThisTransaction thisTransaction = new ThisTransaction(2, productsT1);
        session.save(thisTransaction);

        productsT1 = new ArrayList<>();
        productsT1.add(product4);
        productsT1.add(product1);
        thisTransaction = new ThisTransaction(6, productsT1);
        session.save(thisTransaction);

        productsT1 = new ArrayList<>();
        productsT1.add(product5);
        productsT1.add(product2);
        productsT1.add(product3);
        thisTransaction = new ThisTransaction(5, productsT1);
        session.save(thisTransaction);
        tx.commit();

        //query transactions
        List<Object[]> allProductsInTransaction = session.createQuery("from ThisTransaction as t " +
                "join t.products as p where t.transactionNumber=:number")
                .setParameter("number", 11)
                .getResultList();
        System.out.println(allProductsInTransaction.size());
        for(Object[] p: allProductsInTransaction){
            System.out.println(p[0].toString() + " ------> " + p[1].toString());
        }

        List<Object[]> allTransactionsForProduct = session.createQuery("from Product as p " +
                "join p.transactions as s where p.productName=:name")
                .setParameter("name", "Film")
                .getResultList();
        System.out.println(allTransactionsForProduct.size());
        for(Object[] p: allTransactionsForProduct){
            System.out.println(p[0].toString() + " ------> " + p[1].toString());
        }
        session.close();
    }
}
