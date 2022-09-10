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

create table t_rental_order (
    `rental_order_id` int not null auto_increment comment 'primary key ID',
    `user_id` int not null comment 'user of order',
    `car_id` int not null comment 'car of order',
    `rental_start_time` bigint not null comment 'the time of using car',
    `rental_end_time` bigint not null comment 'the time of return car',
    `create_time` DATETIME not null DEFAULT CURRENT_TIMESTAMP comment 'create time',
    `update_time` DATETIME not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment 'update time',
    primary key (`rental_order_id`)
)
COMMENT='order info'
ENGINE=InnoDB;