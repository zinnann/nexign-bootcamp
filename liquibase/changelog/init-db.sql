create table clients
(
    balance      numeric(38, 2),
    id           bigserial
        constraint clients_pkey
            primary key,
    phone_number varchar(255),
    tariff       varchar(255)
        constraint clients_tariff_check
            check ((tariff)::text = ANY
                   ((ARRAY ['PER_MINUTE'::character varying, 'MONTHLY'::character varying])::text[]))
);

alter table clients
    owner to postgres;


alter sequence clients_id_seq owner to postgres;
alter sequence clients_id_seq owned by clients.id;

insert into public.clients (balance, phone_number, tariff)
values (1500.00, '79940402705', 'PER_MINUTE');
insert into public.clients (balance, phone_number, tariff)
values (1000.50, '79079765785', 'MONTHLY');