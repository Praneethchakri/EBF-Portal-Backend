--DROP TABLE employee;
DROP TABLE IF EXISTS employee cascade;
--commit;
DROP TABLE IF EXISTS company cascade;
--commit;
CREATE TABLE IF NOT EXISTS `employee` (
   `emp_id` bigint NOT NULL AUTO_INCREMENT,
   `address` varchar(255) NOT NULL,
   `email` varchar(255) NOT NULL,
   `name` varchar(255) NOT NULL,
   `salary` double NOT NULL,
   `surname` varchar(255) NOT NULL,
   PRIMARY KEY (`emp_id`)
 ); 
 
 
 CREATE TABLE IF NOT EXISTS `company` (
   `company_id` bigint NOT NULL AUTO_INCREMENT,
   `company_name` varchar(255) NOT NULL,
   `emp_id` bigint DEFAULT NULL,
   PRIMARY KEY (`company_id`)
--   ,
--   KEY (`emp_id`),
--   CONSTRAINT FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`)
 );