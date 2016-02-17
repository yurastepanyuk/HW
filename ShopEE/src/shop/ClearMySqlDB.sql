

DELETE FROM dbo_autoparts.sales_line;
truncate table dbo_autoparts.sales_line;
DELETE FROM dbo_autoparts.shopping_line;
truncate table dbo_autoparts.shopping_line;
DELETE FROM dbo_autoparts.docs_shopping;
ALTER TABLE dbo_autoparts.docs_shopping AUTO_INCREMENT=0;
DELETE FROM dbo_autoparts.docs_sales;
ALTER TABLE dbo_autoparts.docs_sales AUTO_INCREMENT=0;
DELETE FROM dbo_autoparts.prices;
truncate table dbo_autoparts.prices;
DELETE FROM dbo_autoparts.clients;
alter table dbo_autoparts.clients auto_increment=0;
DELETE FROM dbo_autoparts.balance_auto_parts;
truncate table dbo_autoparts.balance_auto_parts;
DELETE FROM dbo_autoparts.autoparts;
alter table dbo_autoparts.autoparts auto_increment=0;



##DELETE FROM dbo_autoparts.autoparts