ALTER TABLE `members_role`
ADD COLUMN `id` INT NOT NULL AUTO_INCREMENT AFTER `role`,
ADD PRIMARY KEY (`id`);
;

ALTER TABLE `properties`
ADD COLUMN `id` INT NOT NULL AUTO_INCREMENT AFTER `flat_id`,
ADD PRIMARY KEY (`id`);
;