INSERT INTO `buildings` (`address`) VALUES ('Line 1, 60');
INSERT INTO `buildings` (`address`) VALUES ('Line 1, 61');

INSERT INTO `flats` (`number`, `square`, `level`, `building_id`) VALUES ('101', '42.7', '1', '1');
INSERT INTO `flats` (`number`, `square`, `level`, `building_id`) VALUES ('102', '57.6', '1', '1');
INSERT INTO `flats` (`number`, `square`, `level`, `building_id`) VALUES ('103', '24.9', '1', '1');
INSERT INTO `flats` (`number`, `square`, `level`, `building_id`) VALUES ('104', '31.3', '1', '1');

INSERT INTO `members` (`names`) VALUES ('Український Павло');
INSERT INTO `members` (`names`) VALUES ('Пеьренко Назар');

INSERT INTO `members_role` (`member_id`) VALUES ('1');
INSERT INTO `members_role` (`member_id`) VALUES ('2');

INSERT INTO `properties` (`member_id`, `flat_id`) VALUES ('1', '1');
INSERT INTO `properties` (`member_id`, `flat_id`) VALUES ('1', '2');
INSERT INTO `properties` (`member_id`, `flat_id`) VALUES ('2', '3');
INSERT INTO `properties` (`member_id`, `flat_id`) VALUES ('2', '4');

INSERT INTO `tanents` (`names`, `auto_allow`, `flat_id`) VALUES ('Сєргєєва Олеса', '1', '1');
INSERT INTO `tanents` (`names`, `member_id`, `flat_id`) VALUES ('Український Павло', '1', '4');
INSERT INTO `tanents` (`names`, `flat_id`) VALUES ('Загадковий Микола', '2');
INSERT INTO `tanents` (`names`, `flat_id`) VALUES ('Тимчак Павлина', '3');
INSERT INTO `tanents` (`names`, `auto_allow`, `member_id`, `flat_id`) VALUES ('Пеьренко Назар', '1', '2', '4');
