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