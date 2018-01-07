CREATE TABLE `jx_customer_contact` (
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `ORDER_ID` BIGINT,
   `name`         VARCHAR(20)  NOT NULL,
  `province`     VARCHAR(20)  NOT NULL,
  `city`     VARCHAR(20)  NOT NULL,
  `area`     VARCHAR(20)  NOT NULL,
  `street`     VARCHAR(30)  NOT NULL,
  `more_details`     VARCHAR(150)  NOT NULL,
  `created_date` TIMESTAMP DEFAULT NOW()
);
