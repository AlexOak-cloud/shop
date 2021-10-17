package shop.app;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import shop.app.entity.Product;

public class Test {

    public static final Session session = getSession();

    public static Session getSession(){
        Configuration configuration = new Configuration();
        configuration.configure("application.properties");
        configuration.addAnnotatedClass(Product.class);
        return configuration.buildSessionFactory().openSession();
    }


    public static boolean add(Product product){
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(product);
            transaction.commit();
            return true;
        }catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        Product product = new Product("qwe",123);
        System.out.println(add(product));
    }


}
