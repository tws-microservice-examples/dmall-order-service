CREATE TABLE `jx_order_event` (
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `ORDER_ID` BIGINT,
  `TICKET_ID`  BIGINT,
  `name` VARCHAR(20)  NOT NULL,
  `created_date` TIMESTAMP DEFAULT NOW()
);