-- ユーザー
drop table if exists users cascade;

create table users (
 id serial primary key
 , name varchar(100) not null
 , email varchar(100) not null unique
 , password text not null
 , zipcode varchar(7) not null
 , address varchar(200) not null
 , telephone varchar(15) not null
) ;


--ユーザー登録(pass:morimori)
insert into users(name, email, password, zipcode, address, telephone) values('テストユーザ', 'test@test.co.jp', 'a232fe3fd81a175aa15541dc2051fe2cb003cdae810371104fbfc100eb34cd6f','1111111', 'テスト住所', 'テスト電話番号');

create table categorys(
  id integer primary key,
  name varchar(100) not null
);

insert into categorys(id,name)values(1,'ピザ');
insert into categorys(id,name)values(2,'サイドメニュー');
insert into categorys(id,name)values(3,'ドリンク');
-- 商品
drop table if exists items cascade;
create table items (
  id integer primary key
  , name text not null
  , description text not null
  , price_m integer not null
  , price_l integer not null
  , image_path text not null
  , deleted boolean default false not null
  , item_category integer not null
) ;

insert into items values(1, 'じゃがバターベーコン', 'ホクホクのポテトと旨味が凝縮されたベーコンを特製マヨソースで味わって頂く商品です。バター風味豊かなキューブチーズが食材の味を一層引き立てます。', 1490, 2570, '1.jpg',false,1);
insert into items values(2, 'アスパラ・ミート', 'グリーンアスパラと相性の良いベーコンにいろどりのフレッシュトマトをトッピングし特製マヨソースでまとめた商品です', 1490, 2570, '2.jpg',false,1);
insert into items values(3, '熟成ベーコンとマッシュルーム', 'マッシュルームと熟成ベーコンにブラックペッパーをトッピングしたシンプルなピザ！', 1490, 2570, '3.jpg',false,1);
insert into items values(4, 'カレーじゃがバター', 'マイルドな味付けのカレーに大きくカットしたポテトをのせた、バターとチーズの風味が食欲をそそるお子様でも楽しめる商品です', 1900, 2980, '4.jpg',false,1);
insert into items values(5, '明太バターチーズ', '大きくカットしたポテトにコーンとベーコンをトッピングして、明太クリームソース、バター、チーズを合わせた、家族で楽しめるピザです', 1900, 2980, '5.jpg',false,1);
insert into items values(6, '濃厚Gorgeous4', '「厚切イベリコ」、「贅沢フォルマッジ」「ラクラクピザ・シュプリーム」「アボカドシュリンプ」4種類の濃厚な味わいが一つで楽しめるピザです', 2700, 4050, '6.jpg',false,1);
insert into items values(7, 'ピザベスト4', 'ラクラクピザの人気ピザ"ベスト4"（「アイダホ風ほっくりポテマヨ」、「フレッシュモッツァレラのマルゲリータ」、「特うまプルコギ」', 2570, 3780, '7.jpg',false,1);
insert into items values(8, 'Charity4', '「デラックス」、「ミート・シュプリーム」、「ツナマイルド」、「ガーリック・トマト」の組み合わせ。「チャリティー4」1枚のご注文につき、世界の飢餓救済に', 2160, 3380, '8.jpg',false,1);
insert into items values(9, '特うまプルコギ', 'ミートナンバー１！甘辛ダレの焼肉がクセになる！食べると思わず元気が出るラクラクピザの自信作', 2700, 4050, '9.jpg',false,1);
insert into items values(10, 'フレッシュモッツァレラ', 'ピザの王道！トマトとフレッシュモッツァレラが絶妙です', 2160, 3380, '10.jpg',false,1);
insert into items values(11, 'Specialミート4', 'お肉好きの方必見！ラクラクピザ人気のミートシリーズが1枚のピザになって新登場！「厚切イベリコ」「ワイルド・ガーリック」「特うまプルコギ」', 2700, 4050, '11.jpg',false,1);
insert into items values(12, 'バラエティー4', '「めちゃマヨ・ミート」「ガーリック・トマト」「えびマヨコーン」、「フレッシュモッツァレラのマルゲリータ」が一つになった4種のピザ', 2160, 3380, '12.jpg',false,1);
insert into items values(13, 'めちゃマヨミート', 'あらびきスライスソーセージとイタリアンソーセージの2種類のソーセージを、トマトソースと特製マヨソースの2種類のソースで召し上がって頂く商品です', 2160, 3380, '13.jpg',false,1);
insert into items values(14, 'とろけるビーフシチュー', 'デミグラスソースでじっくり煮込んだ旨味たっぷりのビーフシチューのピザ', 2980, 4460, '14.jpg',false,1);
insert into items values(15, 'シーフードミックス', 'シーフードナンバー１！魚介の旨みたっぷり！人気の海の幸と、野菜のリッチなおいしさ', 2700, 4050, '15.jpg',false,1);
insert into items values(16, 'Family4', 'ラクラクピザ自慢「特うまプルコギ」定番「デラックス」お子様に人気「ツナマイルド」女性に好評「チーズ＆チーズ」の４種のおいしさを贅沢に組み合わせました', 2440, 3650, '16.jpg',false,1);
insert into items values(17, 'アイダホ風ほっくりポテマヨ', 'みんな大好き！ポテトと特製マヨソースの組み合わせ！定番のおいしさを味わえます', 2440, 3650, '17.jpg',false,1);
insert into items values(18, '贅沢フォルマッジ', '濃厚なカマンベールソース＆カマンベールと香りとコクのパルメザンチーズをトッピング', 2700, 4050, '18.jpg',false,1);
INSERT INTO items values(19,'ポテト','ポテトです',300,300,'side_1.png',false,2);
INSERT INTO items values(20,'ナゲット','ナゲットです',400,400,'side_2.png',false,2);
INSERT INTO items values(21,'シーザサラダ','シーザサラダです',500,500,'side_3.png',false,2);
INSERT INTO items values(22,'コーンスープ','コーンスープです',300,300,'side_4.png',false,2);
INSERT INTO items values(23,'コーラ','コーラです',200,300,'drink_1.png',false,3);
INSERT INTO items values(24,'ファンタグレープ','ファンタグレープです',200,300,'drink_1.png',false,3);
INSERT INTO items values(25,'爽健美茶','爽健美茶です',200,300,'drink_1.png',false,3);
INSERT INTO items values(26,'オレンジ','コーラです',200,300,'drink_1.png',false,3);
INSERT INTO items values(27,'ビール','です',300,500,'drink_1.png',false,3);

-- トッピング
drop table if exists toppings cascade;

create table toppings (
  id integer primary key
  , name text not null
  , price_m integer not null
  , price_l integer not null
) ;

insert into toppings values(1, 'オニオン', 200, 300);
insert into toppings values(2, 'ツナマヨ', 200, 300);
insert into toppings values(3, 'イタリアントマト', 200, 300);
insert into toppings values(4, 'イカ', 200, 300);
insert into toppings values(5, 'プルコギ', 200, 300);
insert into toppings values(6, 'アンチョビ', 200, 300);
insert into toppings values(7, 'エビ', 200, 300);
insert into toppings values(8, 'コーン', 200, 300);
insert into toppings values(9, 'ピーマン', 200, 300);
insert into toppings values(10, 'フレッシュスライストマト', 200, 300);
insert into toppings values(11, 'ベーコン', 200, 300);
insert into toppings values(12, 'ペパロニ･サラミ', 200, 300);
insert into toppings values(13, '熟成ベーコン', 200, 300);
insert into toppings values(14, '特製マヨソース', 200, 300);
insert into toppings values(15, 'カマンベールチーズ', 200, 300);
insert into toppings values(16, 'フレッシュモッツァレラチーズ', 200, 300);
insert into toppings values(17, 'イタリアンソーセージ', 200, 300);
insert into toppings values(18, 'ガーリックスライス', 200, 300);
insert into toppings values(19, 'あらびきスライスソｰセｰジ', 200, 300);
insert into toppings values(20, 'ブロッコリー', 200, 300);
insert into toppings values(21, 'グリーンアスパラ', 200, 300);
insert into toppings values(22, 'パルメザンチーズ', 200, 300);
insert into toppings values(23, 'パイナップル', 200, 300);
insert into toppings values(24, 'ハラペーニョ', 200, 300);
insert into toppings values(25, 'もち', 200, 300);
insert into toppings values(26, 'ポテト', 200, 300);
insert into toppings values(27, 'ブラックオリーブ', 200, 300);
insert into toppings values(28, 'チーズ増量', 200, 300);

-- 注文
drop table if exists orders cascade;

create table orders (
  id serial primary key
  , user_id integer not null
  , status integer not null
  , total_price integer not null
  , order_date date
  , destination_name varchar(100)
  , destination_email varchar(100)
  , destination_zipcode varchar(7)
  , destination_address varchar(200)
  , destination_tel varchar(15)
  , delivery_time timestamp
  , payment_method integer
  ) ;

-- 注文商品
drop table if exists order_items cascade;

create table order_items (
  id serial primary key
  , item_id integer not null
  , order_id integer not null
  , quantity integer not null
  , size varchar(1)
) ;

-- 注文トッピング
drop table if exists order_toppings cascade;

create table order_toppings (
  id serial primary key
  , topping_id integer not null
  , order_item_id integer not null
) ;
