-- Inserting colors
INSERT INTO colors (id, name)
VALUES (1, 'Gold'),
       (2, 'Blue'),
       (3, 'Pearl'),
       (4, 'Yellow'),
       (5, 'Black'),
       (6, 'White'),
       (7, 'Red'),
       (8, 'Grey'),
       (9, 'Pink'),
       (10, 'Metallic');

-- Inserting materials
INSERT INTO materials (id, name)
VALUES (1, 'Metal'),
       (2, 'Glass'),
       (3, 'Ceramic');

-- Inserting categories
INSERT INTO categories (id, name)
VALUES (1, 'Mugs'),
       (2, 'Plates'),
       (3, 'Bowls'),
       (4, 'Vases'),
       (5, 'Cups'),
       (6, 'Cutlery'),
       (7, 'Pots'),
       (8, 'Pans'),
       (9, 'Glasses');

-- Inserting collections
INSERT INTO collections (id, name, description)
VALUES (1, 'Home collection',
        'Discover the artistry of our handmade vase collection. Embracing diverse styles and textures, our vases are designed to add a touch of elegance and warmth to your living spaces. Explore the timeless beauty of handcrafted pottery as you bring a unique blend of art and functionality into your home with our exquisite vase collection.'),
       (2, 'Dining collection',
        'Discover the artistry of our handmade Dining Collection. Each piece in our Handmade Collection is a testament to the passion and skill of our artisans, crafted with meticulous attention to detail. ');

-- Inserting products
INSERT INTO products (id, images, category_id, catalog_price, discount_catalog_price, name, description, care_guide)
VALUES (1, '{}', 4, 100.00, null, 'Black night Vase', 'Description 1', 'Care Guide 1'),
       (2, '{}', 4, 100.00, null, 'Bloom Vase', 'Description 2', 'Care Guide 2'),
       (3, '{}', 4, 100.00, null, 'Cobalt & White Vase', 'Description 2', 'Care Guide 2'),
       (4, '{}', 4, 100.00, null, 'Crystal Vase', 'Description 2', 'Care Guide 2'),
       (5, '{}', 4, 88.00, null, 'Cylinder Vase', 'Description 2', 'Care Guide 2'),
       (6, '{}', 4, 100.00, null, 'Electric Blue Vase', 'Description 2', 'Care Guide 2'),
       (7, '{}', 4, 100.00, null, 'Electric Vase', 'Description 2', 'Care Guide 2'),
       (8, '{}', 5, 100.00, null, 'Elegance Cup', 'Description 2', 'Care Guide 2'),
       (9, '{}', 5, 100.00, null, 'Frosty Cup', 'Description 2', 'Care Guide 2'),
       (10, '{}', 9, 100.00, null, 'Garnet Jar', 'Description 2', 'Care Guide 2'),
       (11, '{}', 4, 200.00, null, 'Golden night Vase', 'Description 2', 'Care Guide 2'),
       (12, '{}', 4, 120.00, 100.00, 'Midnight Vase', 'Description 2', 'Care Guide 2'),
       (13, '{}', 3, 100.00, null, 'Pearl Dish', 'Description 2', 'Care Guide 2'),
       (14, '{}', 4, 100.00, null, 'Red Vase', 'Description 2', 'Care Guide 2'),
       (15, '{}', 5, 100.00, null, 'Teacup', 'Description 2', 'Care Guide 2'),
       (16, '{}', 5, 100.00, null, 'Teacups set', 'Description 2', 'Care Guide 2'),
       (17, '{}', 7, 100.00, null, 'Teapot jan', 'Description 2', 'Care Guide 2'),
       (18, '{}', 9, 100.00, null, 'Terra jar', 'Description 2', 'Care Guide 2'),
       (19, '{}', 9, 100.00, null, 'Terra set', 'Description 2', 'Care Guide 2'),
       (20, '{}', 4, 100.00, null, 'Vase 2 set', 'Description 2', 'Care Guide 2');

--inserting images for products
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218123/pottery/rgh5kupestdhzcuvk9zg.jpg"
}'
WHERE id = 1;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218125/pottery/vua4ikwzwmwvmbkndvkv.jpg"
}'
WHERE id = 2;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218124/pottery/r9eug5kdwrsskpdpiyyf.jpg"
}'
WHERE id = 3;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218125/pottery/wgjl0llsg1crqxravsks.jpg"
}'
WHERE id = 4;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218123/pottery/nl2jneafu7ssf4xqmlse.jpg"
}'
WHERE id = 5;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218124/pottery/q0yvlgshbsdnz0drqxdy.jpg"
}'
WHERE id = 6;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218124/pottery/qv49ieuiqwmoastzsskm.jpg"
}'
WHERE id = 7;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218124/pottery/gnh7nfy0sgi6emyp5lxq.jpg"
}'
WHERE id = 8;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218125/pottery/ba0w8ocphsqhtdfvr9z6.png"
}'
WHERE id = 9;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218125/pottery/c6ku7ph7qupavgb4zufj.jpg"
}'
WHERE id = 10;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218125/pottery/ybbgixr4anldtlhuv6hh.jpg"
}'
WHERE id = 11;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218125/pottery/d1kuq32a6sfngxqxwzfs.jpg"
}'
WHERE id = 12;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218126/pottery/skohtw1a2qqlm9jwrp5v.jpg"
}'
WHERE id = 13;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218126/pottery/o5r3pxm9jmdkv8l0jp3z.jpg"
}'
WHERE id = 14;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218126/pottery/scwqezniom8ke987oah9.png"
}'
WHERE id = 15;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218122/pottery/jbe3khywwy2uqljdnfre.png"
}'
WHERE id = 16;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218123/pottery/fawmyyjwmt3ukou1b7mr.jpg"
}'
WHERE id = 17;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218122/pottery/hk8m5m28oobp4ojqiii2.jpg"
}'
WHERE id = 18;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218122/pottery/pkv3bthr9wkdodqsdvbd.jpg"
}'
WHERE id = 19;
UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718219490/pottery/gumycjnjba1ylxzhg6ru.jpg"
}'
WHERE id = 20;

-- Inserting products_collections
INSERT INTO products_collections (collections_id, product_id)
VALUES (1, 4),
       (1, 5),
       (1, 12),
       (1, 13),
       (1, 14),
       (1, 15),
       (1, 3),
       (1, 10),
       (2, 17),
       (2, 20),
       (2, 4);

-- Inserting properties
INSERT INTO properties (id, material_id, color_id, default_price, discount_price, quantity, sold, product_id,
                        dimensions)
VALUES (1, 2, 5, 100.00, null, 10, 0, 1, '10x10x10'),
       (2, 3, 2, 100.00, null, 20, 0, 2, '20x20x20'),
       (3, 3, 6, 100.00, null, 20, 0, 3, '20x20x20'),
       (4, 3, 6, 100.00, null, 20, 0, 4, '20x20x20'),
       (5, 1, 6, 88.00, null, 20, 0, 5, '20x20x20'),
       (6, 1, 5, 100.00, null, 20, 0, 5, '20x20x20'),
       (7, 3, 2, 100.00, null, 20, 0, 6, '20x20x20'),
       (8, 1, 10, 100.00, null, 20, 0, 7, '20x20x20'),
       (9, 3, 2, 100.00, null, 20, 0, 8, '20x20x20'),
       (10, 3, 4, 100.00, null, 20, 0, 8, '20x20x20'),
       (11, 3, 6, 100.00, null, 20, 0, 9, '20x20x20'),
       (12, 3, 6, 100.00, null, 20, 0, 10, '20x20x20'),
       (13, 3, 5, 200.00, null, 20, 0, 11, '20x20x20'),
       (14, 3, 1, 200.00, null, 20, 0, 11, '20x20x20'),
       (15, 3, 6, 120.00, 100.00, 20, 0, 12, '20x20x20'),
       (16, 3, 3, 100.00, null, 20, 0, 13, '20x20x20'),
       (17, 3, 7, 100.00, null, 20, 0, 14, '20x20x20'),
       (18, 3, 8, 100.00, null, 20, 0, 15, '20x20x20'),
       (19, 3, 7, 100.00, null, 20, 0, 16, '20x20x20'),
       (20, 3, 6, 100.00, null, 20, 0, 17, '20x20x20'),
       (21, 3, 9, 100.00, null, 20, 0, 18, '20x20x20'),
       (22, 3, 9, 150.00, null, 20, 0, 19, '20x20x20'),
       (23, 3, 6, 100.00, null, 20, 0, 20, '20x20x20');

