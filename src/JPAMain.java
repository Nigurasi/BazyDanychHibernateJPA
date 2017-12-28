import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JPAMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("myDatabaseConfig");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();

        Category c1 = new Category("Technology");
        em.persist(c1);
        Category c2 = new Category("Food");
        em.persist(c2);

        Product p1 = new Product("Komputer", 20, c1);
        em.persist(p1);
        Product p2 = new Product("Konsola", 14, c1);
        em.persist(p2);
        Product p3 = new Product("Laptop", 8, c1);
        em.persist(p3);
        Product p4 = new Product("Jabłko", 34, c2);
        em.persist(p4);
        Product p5 = new Product("Jogurt", 6, c2);
        em.persist(p5);
        etx.commit();

        etx.begin();
        List<Product> foodP = new ArrayList<>();
        foodP.add(p4);
        foodP.add(p5);
        //Supplier s1 = new Supplier("Apple", "Polna 22",
                //"Warszawa", foodP);
        Address a1 = new Address("Polna 22", "Warszawa", "Polska");
        Supplier s1 = new Supplier("Apple", a1, foodP);
        em.persist(s1);
        p4.setSupplier(s1);
        p5.setSupplier(s1);

        List<Product> techP = new ArrayList<>();
        techP.add(p1);
        techP.add(p2);
        techP.add(p3);
        //Supplier s2 = new Supplier("Intel", "Mickiewicza 34",
                //"Gdańsk", techP);
        Address a2 = new Address("Mickiewicza 34", "Gdańsk", "Polska");
        Supplier s2 = new Supplier("Intel", a2, techP);
        em.persist(s2);
        p1.setSupplier(s2);
        p2.setSupplier(s2);
        p3.setSupplier(s2);
        etx.commit();

        etx.begin();
        List<Product> techPN = new ArrayList<>();
        techPN.add(p1);
        techPN.add(p2);
        techPN.add(p3);
        SupplierNew sn1 = new SupplierNew("S1", "Wesoła 12",
                "Lublin", "Polska", techPN);
        em.persist(sn1);
        etx.commit();

        etx.begin();
        List<Object[]> allProducts = em.createQuery("from Supplier as s " +
                "join s.products as p where s.companyName=:name")
                .setParameter("name", "Apple")
                .getResultList();
        System.out.println(allProducts.size());
        for(Object[] p: allProducts){
            System.out.println(p[0].toString() + " ------> " + p[1].toString());
        }

        List<Object[]> exactSupplier = em.createQuery("from Product as p " +
                "join p.supplier as s where p.productName=:name")
                .setParameter("name", "Laptop")
                .getResultList();
        System.out.println(exactSupplier.size());
        for(Object[] p: exactSupplier){
            System.out.println(p[0].toString() + " ------> " + p[1].toString());
        }
        etx.commit();

        etx.begin();
        Product p6 = new Product("Chleb", 25, c2);
        List<Product> listP = new ArrayList<>();
        listP.add(p4);
        listP.add(p6);
        ThisTransaction t1 = new ThisTransaction(3, listP);
        em.persist(t1);
        etx.commit();

        etx.begin();
        Product p7 = new Product("Klawiatura", 20, c1);
        ThisTransaction t2 = new ThisTransaction(2, new ArrayList<>());
        p7.addToTransactions(t2);
        p2.addToTransactions(t2);
        em.persist(p7);
        etx.commit();

        //Dziedziczenie
        //1 tabela na cala hierarchie
        etx.begin();
        Customer c121 = new Customer("Kupiec1", "Mickiewicza 34",
                "Gdańsk", "Polska", 0.2);

        SupplierXIIa s121 = new SupplierXIIa("Sprzedawca1",
                "Polna 22", "Warszawa", "Polska", "FWAWDA234ASAA");

        em.persist(c121);
        em.persist(s121);

        List<Object> company1 = em.createQuery("from Company").getResultList();
        System.out.println(company1.size());
        for(Object p: company1){
            System.out.println(p.toString());
        }
        etx.commit();

        //Tabele laczone
        etx.begin();
        CustomerB c122 = new CustomerB("Kupiec2", "Mickiewicza 34",
                "Gdańsk", "Polska", 0.2);

        SupplierXIIb s122 = new SupplierXIIb("Sprzedawca2",
                "Polna 22", "Warszawa", "Polska", "FWAWDA234ASAA");

        em.persist(c122);
        em.persist(s122);

        List<Object> company2 = em.createQuery("from SupplierXIIb").getResultList();
        System.out.println(company2.size());
        for(Object p: company2){
            System.out.println(p.toString());
        }
        etx.commit();

        //Jedna tabela na konkretną klasę
        etx.begin();
        CustomerC c123 = new CustomerC("Kupiec3", "Mickiewicza 34",
                "Gdańsk", "Polska", 0.2);

        SupplierXIIc s123 = new SupplierXIIc("Sprzedawca3",
                "Polna 22", "Warszawa", "Polska", "FWAWDA234ASAA");

        em.persist(c123);
        em.persist(s123);

        List<Object> company3 = em.createQuery("from SupplierXIIc").getResultList();
        System.out.println(company3.size());
        for(Object p: company3){
            System.out.println(p.toString());
        }
        etx.commit();


        em.close();
    }
}
