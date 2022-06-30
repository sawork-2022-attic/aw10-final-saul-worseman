create table orders
(
    order_id    bigint auto_increment
        primary key,
    cart_id     bigint       null,
    status      varchar(255) null,
    total_price double       not null
);

create table products
(
    id    bigint auto_increment
        primary key,
    image varchar(255) null,
    name  varchar(255) null,
    price double       not null
);

create table carts
(
    cart_id bigint auto_increment
        primary key
);


