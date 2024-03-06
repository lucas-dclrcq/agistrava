CREATE TABLE strava_users
(
    id        bigint not null,
    createdAt timestamp(6) with time zone,
    firstname varchar(255),
    lastname  varchar(255),
    access_token varchar(4000),
    refresh_token varchar(4000),
    primary key (id)
);
