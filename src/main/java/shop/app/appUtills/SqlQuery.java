package shop.app.appUtills;

public interface SqlQuery {

    String getAllProductByUser = "SELECT  products.* FROM products JOIN usr WHERE" +
            " products.user_id = usr.id AND usr.id = %d;";
    String getAllCategory = "SELECT * FROM products WHERE category LIKE '%s';";
    String getAllProductsByName="select * from products where name like '%s';";
    String getChatByRecipient = "SELECT t_msg.* FROM t_msg JOIN usr WHERE t_msg. " +
            "sender_user_id like usr.id AND t_msg.recipient_user_id LIKE %d or " +
            "t_msg.sender_user_id LIKE usr.id AND t_msg.recipient_user_id LIKE %d;";



}
