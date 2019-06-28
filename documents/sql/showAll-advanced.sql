--ユーザ情報取得
select name, email, password, zipcode, address, telephone ,birthday from users;
--カテゴリ取得
select id ,name from categorys;
--アイテム取得
select id,name,description,price_m,price_l,image_path,deleted,item_category from items;
--トッピング情報
select id,name,price_m,price_l from toppings;
--セット情報
select id,name,description,price,image_path,deleted,pizza_L_price from sets;
--オーダー情報
SELECT id ,user_id , status , total_price , order_date , destination_name , destination_email , destination_zipcode , destination_address,destination_tel , delivery_time , payment_method FROM orders;
--オーダーアイテム情報
SELECT id , item_id , order_id , quantity , size FROM order_items;
--オーダートッピング情報
SELECT id , topping_id , order_item_id FROM order_toppings;
--コメント情報
select id,error_date,error_page,comment from comments;

select
oi.item_id,
i.name,
i.item_category,
sum(oi.quantity) as quantity
from order_items oi
inner JOIN
items i
on
i.id=oi.item_id

GROUP BY oi.item_id,i.name,i.item_category
order by i.item_category,quantity desc;

select
order_date as sales_date,
sum(total_price) as sales
from orders
where status=2 or status=3
group by order_date
order by order_date desc;

select current_date;
