SELECT id,name, email, password, zipcode, address, telephone FROM users;
SELECT id,name,description , price_m , price_l , image_path , deleted FROM items;
SELECT id,name,price_m,price_l FROM toppings;
SELECT id ,user_id , status , total_price , order_date , destination_name , destination_email , destination_zipcode , destination_address,destination_tel , delivery_time , payment_method FROM orders;
SELECT id , item_id , order_id , quantity , size FROM order_items;
SELECT id , topping_id , order_item_id FROM order_toppings ;
