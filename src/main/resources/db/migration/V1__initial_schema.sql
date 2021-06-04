create table loan_information
(
    id                bigserial not null
        constraint loan_information_pk
            primary key,
    loan_amount       numeric   not null,
    interest_rate     numeric   not null,
    loan_term         bigint    not null,
    payment_frequency varchar
);

alter table loan_information
    owner to danicanovakovic;

create table public.payment_information
(
    id                  bigserial not null
        constraint payment_information_pk
            primary key,
    total_payment       numeric   not null,
    total_interest      numeric   not null,
    loan_information_id bigint    not null
        constraint fk_payment_loan_information
            references public.loan_information
);

alter table payment_information
    owner to danicanovakovic;

create table public.amortization_schedule
(
    id                     bigserial not null
        constraint amortization_schedule_pk
            primary key,
    payment_amount         numeric   not null,
    principal_amount       numeric   not null,
    interest_amount        numeric   not null,
    balance_owed           numeric   not null,
    payment_information_id bigint    not null
        constraint fk_amortization_schedule_payment_information
            references public.payment_information,
    month                  integer   not null
);

alter table amortization_schedule
    owner to danicanovakovic;

