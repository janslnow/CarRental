## Car Rental Reverse Api Service

### Summary
This service is a simple rental car booking API service for customers 
to reserve a car for a period of time.

Everyone can reserve car, but user only can choose a period that someone else didn't reserve in a same car.
When use choose a car for a period that conflict with reserved period, it will tip 'reserve fail'

### Demo
Note: this web demo don't implement login action, so everyone who use this demo will use a same test account.

### Technology stack

* Agent: Nginx
* Language: Java
* Framework: SpringMVC, SpringBoot, Mybatis
* Database: MySQL

### Api Document

this service include three api implement;

#### 1. get all car info;

##### URI
GET /api/cars/v1

##### purpose
Client get all car basic info and reserved period of this car;

#### 2. get all reserve order info;

##### URI
GET /api/reserved-orders/v1

##### purpose
Client query reserved order entry by user id;

#### 3. reserve car
POST /api/reserved-orders/v1

##### purpose
User reserve a car for a few days.


### Database table
```
use `car-rental`;

drop table if exists `t_car`;
create table t_car(
    `car_id` int not null auto_increment comment 'primary key ID',
    `car_model` varchar(64) not null comment 'car model',
    `create_time` DATETIME not null DEFAULT CURRENT_TIMESTAMP comment 'create time',
    `update_time` DATETIME not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment 'update time',
    primary key (`car_id`)
)
COMMENT='car info'
ENGINE=InnoDB;

insert into t_car(`car_model`) values ('Toyota Camry'), ('Toyota Camry'), ('BMW 650'), ('BMW 650');

drop table if exists `t_rental_order`;
create table t_rental_order (
    `rental_order_id` int not null auto_increment comment 'primary key ID',
    `user_id` int not null comment 'user of order',
    `car_id` int not null comment 'car of order',
    `rental_start_date` DATE not null comment 'the day of using car',
    `rental_end_date` DATE not null comment 'the day of return car',
    `create_time` DATETIME not null DEFAULT CURRENT_TIMESTAMP comment 'create time',
    `update_time` DATETIME not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment 'update time',
    primary key (`rental_order_id`),
    index `index_date` (`rental_start_date`, `rental_end_date`)
)
COMMENT='order info'
ENGINE=InnoDB;
```