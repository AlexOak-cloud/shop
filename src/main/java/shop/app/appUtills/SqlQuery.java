package shop.app.appUtills;

public interface SqlQuery {

    String getAllProductByUser = "SELECT  products.* FROM products JOIN usr WHERE" +
            " products.user_id = usr.id AND usr.id = %d;";
    String getAllCategory = "SELECT * FROM products WHERE category LIKE '%s';";
    String getAllProductsByName="select * from products where name like '%s';";
    String getChatByRecipient = "SELECT t_msg.* FROM t_msg JOIN usr WHERE t_msg.sender LIKE " +
            "usr.id AND sender LIKE %d  AND recepient LIKE %d;";



}
