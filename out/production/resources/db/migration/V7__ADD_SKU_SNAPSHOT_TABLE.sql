CREATE TABLE `jx_sku_snapshot` (
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `ORDER_ITEM_ID` BIGINT,
  `price`  DOUBLE  NOT NULL,
  `sku_Id` BIGINT,
  `created_date` TIMESTAMP DEFAULT NOW()
);