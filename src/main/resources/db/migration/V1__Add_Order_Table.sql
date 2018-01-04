CREATE TABLE `jx_order` (
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `created_date` TIMESTAMP DEFAULT NOW(),
  `CONTACT_ID` BIGINT
);