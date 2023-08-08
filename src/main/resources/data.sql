-- housesテーブルfat
INSERT IGNORE INTO foods (id, name, dish_type, energy, protein, fat, carbohydrate, fiber, nacl) VALUES (1, '白米', '主食', 168, 2.5, 0.3, 37.1, 0.3, 0);
INSERT IGNORE INTO foods (id, name, dish_type, energy, protein, fat, carbohydrate, fiber, nacl) VALUES (2, '食パン', '主食', 264, 9.3, 4.4, 46.7, 2.3, 1.3);
INSERT IGNORE INTO foods (id, name, dish_type, energy, protein, fat, carbohydrate, fiber, nacl) VALUES (3, '豚肉', '主菜', 328, 26.7, 22.7, 0.3, 0, 0.1);
INSERT IGNORE INTO foods (id, name, dish_type, energy, protein, fat, carbohydrate, fiber, nacl) VALUES (4, 'べにしゃけ', '主菜', 177, 28.5, 6.0, 0.1, 0, 0.2);
INSERT IGNORE INTO foods (id, name, dish_type, energy, protein, fat, carbohydrate, fiber, nacl) VALUES (5, 'トマト', '副菜', 19, 0.7, 0.1, 4.7, 1.0, 0);
INSERT IGNORE INTO foods (id, name, dish_type, energy, protein, fat, carbohydrate, fiber, nacl) VALUES (6, 'きゅうり', '副菜', 14, 1.0, 0.1, 3.0, 1.1, 0);
INSERT IGNORE INTO foods (id, name, dish_type, energy, protein, fat, carbohydrate, fiber, nacl) VALUES (7, '牛乳', 'そのほか', 64, 3.3, 3.8, 4.8, 0, 0.1);

--rolesテーブル
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');
