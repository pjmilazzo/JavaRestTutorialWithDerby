DROP TABLE PC_PARTS;

CREATE TABLE PC_PARTS
(
pc_parts_pk INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
pc_parts_title VARCHAR(30) NOT NULL,
pc_parts_code INTEGER NOT NULL,
pc_parts_maker VARCHAR(30) NOT NULL,
pc_parts_avail INTEGER NOT NULL,
pc_parts_desc VARCHAR(100) NOT NULL,
CONSTRAINT primary_key PRIMARY KEY (pc_parts_pk)
) ;

INSERT INTO PC_PARTS(pc_parts_title, pc_parts_code, pc_parts_maker, pc_parts_avail, pc_parts_desc) values('Intel MB', 123456789, 'Intel', 5, 'Intel motherboard');
INSERT INTO PC_PARTS(pc_parts_title, pc_parts_code, pc_parts_maker, pc_parts_avail, pc_parts_desc) values('AMD MB', 987654321, 'AMD', 3, 'AMD motherboard');
INSERT INTO PC_PARTS(pc_parts_title, pc_parts_code, pc_parts_maker, pc_parts_avail, pc_parts_desc) values('HP Laptop', 123456, 'HP', 5, 'HP Laptop with 15 inch screen');
INSERT INTO PC_PARTS(pc_parts_title, pc_parts_code, pc_parts_maker, pc_parts_avail, pc_parts_desc) values('IBM Desktop', 123457, 'IBM', 10, 'IBM Desktop');
INSERT INTO PC_PARTS(pc_parts_title, pc_parts_code, pc_parts_maker, pc_parts_avail, pc_parts_desc) values('Dell Desktop', 123458, 'DELL', 14, 'Dell Desktop');
INSERT INTO PC_PARTS(pc_parts_title, pc_parts_code, pc_parts_maker, pc_parts_avail, pc_parts_desc) values('HP Desktop', 123459, 'HP', 3, 'HP Desktop');



