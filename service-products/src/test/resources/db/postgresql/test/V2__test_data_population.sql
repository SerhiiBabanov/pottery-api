-- Inserting colors
INSERT INTO colors (id, name)
VALUES (1, 'Gold'),
       (2, 'Blue'),
       (3, 'Grey');


-- Inserting materials
INSERT INTO materials (id, name)
VALUES (1, 'Metal'),
       (2, 'Glass'),
       (3, 'Ceramic');

-- Inserting categories
INSERT INTO categories (id, name)
VALUES (1, 'Vases'),
       (2, 'Cups'),
       (3, 'Dishes');

-- Inserting collections
INSERT INTO collections (id, name, description)
VALUES (1, 'Home collection',
        'Discover the artistry of our handmade vase collection. Embracing diverse styles and textures, our vases are designed to add a touch of elegance and warmth to your living spaces. Explore the timeless beauty of handcrafted pottery as you bring a unique blend of art and functionality into your home with our exquisite vase collection.'),
       (2, 'Dining collection',
        'Discover the artistry of our handmade Dining Collection. Each piece in our Handmade Collection is a testament to the passion and skill of our artisans, crafted with meticulous attention to detail. ');

-- Inserting products
INSERT INTO products (id, images, category_id, catalog_price, discount_catalog_price, name, description, care_guide)
VALUES
--     5 vases
(1,  '{"1": "imageUrl"}', 1, 60.00,  null,   'A Gold Metal Vase', 'Description', 'Care Guide'),
(2,  '{"1": "imageUrl"}', 1, 70.00,  null,   'B Gold Metal Vase', 'Description', 'Care Guide'),
(3,  '{"1": "imageUrl"}', 1, 80.00,  null,   'C Gold Metal Vase', 'Description', 'Care Guide'),
(4,  '{"1": "imageUrl"}', 1, 90.00,  null,   'D Gold Metal Vase', 'Description', 'Care Guide'),
(5,  '{"1": "imageUrl"}', 1, 110.00, 100.00, 'E Gold Metal Sale Vase', 'Description', 'Care Guide'),
--     5 cups
(6,  '{"1": "imageUrl"}', 2, 110.00, null,   'A Blue Glass Cup',  'Description', 'Care Guide'),
(7,  '{"1": "imageUrl"}', 2, 120.00, null,   'B Blue Glass Cup',  'Description', 'Care Guide'),
(8,  '{"1": "imageUrl"}', 2, 130.00, null,   'C Blue Glass Cup',  'Description', 'Care Guide'),
(9,  '{"1": "imageUrl"}', 2, 140.00, null,   'D Blue Glass Cup',  'Description', 'Care Guide'),
(10, '{"1": "imageUrl"}', 2, 150.00, null,   'E Blue Glass Cup',  'Description', 'Care Guide'),
--     5 dishes
(11, '{"1": "imageUrl"}', 3, 200.00, null,   'A Grey Ceramic Dish', 'Description', 'Care Guide'),
(12, '{"1": "imageUrl"}', 3, 190.00, null,   'B Grey Ceramic Dish', 'Description', 'Care Guide'),
(13, '{"1": "imageUrl"}', 3, 190.00, 180.00, 'C Grey Ceramic Sale Dish', 'Description', 'Care Guide'),
(14, '{"1": "imageUrl"}', 3, 170.00, null,   'D Grey Ceramic Dish', 'Description', 'Care Guide'),
(15, '{"1": "imageUrl"}', 3, 160.00, null,   'E Grey Ceramic Not Available Dish', 'Description', 'Care Guide');

-- Inserting products_collections
INSERT INTO products_collections (collections_id, product_id)
VALUES
-- 1 collection
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
-- 2 collection
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10);

-- Inserting properties
INSERT INTO properties (id, material_id, color_id, default_price, discount_price, quantity, sold, product_id,
                        dimensions)
VALUES (1,  1, 1, 60.00,  null,   10, 0, 1,  '10x10x10'),
       (2,  1, 1, 70.00,  null,   20, 0, 2,  '20x20x20'),
       (3,  1, 1, 80.00,  null,   20, 0, 3,  '20x20x20'),
       (4,  1, 1, 90.00,  null,   20, 0, 4,  '20x20x20'),
       (5,  1, 1, 110.00, 100.00, 20, 0, 5,  '20x20x20'),
       (6,  2, 2, 110.00, null,   20, 0, 6,  '20x20x20'),
       (7,  2, 2, 120.00, null,   20, 0, 7,  '20x20x20'),
       (8,  2, 2, 130.00, null,   20, 0, 8,  '20x20x20'),
       (9,  2, 2, 140.00, null,   20, 0, 9,  '20x20x20'),
       (10, 2, 2, 150.00, null,   20, 0, 10, '20x20x20'),
       (11, 3, 3, 200.00, null,   20, 0, 11, '20x20x20'),
       (12, 3, 3, 190.00, null,   20, 0, 12, '20x20x20'),
       (13, 3, 3, 190.00, 180.00, 20, 0, 13, '20x20x20'),
       (14, 3, 3, 170.00, null,   20, 0, 14, '20x20x20'),
       (15, 3, 3, 160.00, null,   0,  0, 15, '20x20x20');

