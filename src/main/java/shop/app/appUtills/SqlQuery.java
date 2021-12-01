package shop.app.appUtills;

public interface SqlQuery {

    String getAllProductByUser = "   SELECT products.* FROM products INNER JOIN usr where products.user_id = %d;";
//    String getAllMessageByUser = ""



}
