-- 1. Вывести список всех машин и все привязанные к ним детали.
SELECT c.id, c.model, bw.type AS bodywork, en.type AS engine, gb.type AS gearbox
    FROM cars AS c,  bodywork AS bw, engine AS en, gearbox AS gb
    WHERE c.gearbox = gb.serial_number AND c.engine = en.serial_number AND c.bodywork = bw.serial_number;

-- 2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
--Вывести неиспользуемые коробки передач
SELECT gb.serial_number, gb.type FROM gearbox AS gb
    LEFT OUTER JOIN cars ON gb.serial_number = cars.gearbox
    WHERE cars.id IS NULL;

-- Вывести неипользуемые двигатели
SELECT en.serial_number, en.power, en.type FROM engine AS en
    LEFT OUTER JOIN cars ON cars.engine = en.serial_number
    WHERE cars.id IS NULL;

-- Вывести неиспользуемые кузова
SELECT bw.serial_number, bw.type FROM bodywork AS bw
    LEFT OUTER JOIN cars ON bw.serial_number = cars.bodywork
    WHERE cars.id IS NULL;





