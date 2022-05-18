create table if not exists post (
id serial primary key,
title text,
link varchar(200) unique,
description text,
created timestamp
);