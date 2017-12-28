CREATE TABLE `jx_order_item` (
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `ORDER_ID` BIGINT,
  `created_date` TIMESTAMP DEFAULT NOW()
);