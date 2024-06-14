UPDATE products
SET images = '{
  "1": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718219490/pottery/gumycjnjba1ylxzhg6ru.jpg",
  "2": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218123/pottery/rgh5kupestdhzcuvk9zg.jpg",
  "3": "https://res.cloudinary.com/dpnzbwijt/image/upload/v1718218122/pottery/hk8m5m28oobp4ojqiii2.jpg"
}'
WHERE id = 1;

UPDATE properties
SET default_price = 120.00,
    discount_price = 100.00
WHERE id = 1;
