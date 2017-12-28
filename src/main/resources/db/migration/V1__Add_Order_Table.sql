CREATE TABLE `jx_order` (
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `Customer_Contact_ID`         BIGINT  NOT NULL,

  `price`     BIGINT  NOT NULL,
  `amount`     BIGINT  NOT NULL,
  `description`     VARCHAR(60)  NOT NULL,
  `brand`     VARCHAR(20)  NOT NULL,
  `created_date` TIMESTAMP DEFAULT NOW()
);