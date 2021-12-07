package shop.app.appUtills;

public interface SqlQuery {

    String getAllProductByUser = "SELECT  products.* FROM products JOIN usr WHERE products.user_id = usr.id AND usr.id = %d;";
    String getAllCategory = "SELECT * FROM products WHERE category LIKE '%s';";
    String getAllProductsByName="select * from products where name like '%s';";



}
