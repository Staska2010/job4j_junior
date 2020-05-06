
-- 1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT product.name, type_id.name FROM product, type_id
        WHERE product.type_id = type_id.id AND type_id.name = 'Сыр';

-- 2. Написать запрос получения всех продуктов,
-- у кого в имени есть слово "мороженное"
SELECT * FROM product WHERE name ILIKE '%мороженое%';


-- 3. Написать запрос, который выводит все продукты,
-- срок годности которых заканчивается в следующем месяце.
SELECT * FROM product WHERE EXTRACT(MONTH FROM expired_date)=EXTRACT(MONTH FROM now()) + 1;

-- 4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product WHERE price = (SELECT MAX(price) FROM product);

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT type_id.name, COUNT(product.type_id) FROM product, type_id
    WHERE product.type_id = type_id.id GROUP BY type_id.name;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT product.name, product.price, product.expired_date, type_id.name FROM
       product, type_id WHERE
       product.type_id = type_id.id AND type_id.name IN ('Сыр', 'Молоко');

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT type_id.name, COUNT(product.name) FROM product, type_id
    WHERE product.type_id = type_id.id GROUP BY type_id.name HAVING COUNT(product.name) < 10;

-- 8. Вывести все продукты и их тип.
SELECT product.name, type_id.name FROM product, type_id WHERE product.type_id = type_id.id;

--Вариант с INNER JOIN
SELECT product.name, type_id.name FROM product INNER JOIN type_id ON (product.type_id = type_id.id);

