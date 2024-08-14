create table orderservice(
id bigint(20) Not null AUTO_INCREMENT,
ordernumber varchar(255) default null,
skucode varchar(255),
price decimal(19,2),
quantity int(11),
primary key(id)
)