 create table Role (
        id bigint generated by default as identity unique,
        name varchar(255),
        primary key (id)
    );
    
    create table Skill (
        id bigint generated by default as identity unique,
        name varchar(255),
         ownRating integer,
          rating integer,
        primary key (id)
    );

        create table Rating (
        id bigint generated by default as identity unique,
        description varchar(255),
         val integer,
          rater_id bigint,
        primary key (id)
    );

        create table Point (
        id bigint generated by default as identity unique,
          sum integer,
          datum timestamp,
        primary key (id)
    );

   create table User (
        id bigint generated by default as identity unique,
        city varchar(255),
        other varchar(255),
        zipcode integer,
        addressCardNumber varchar(255),
        addressCardPicture binary(255),
        certified bit,
        idCardNumber varchar(255),
        idCardPicture binary(255),
        enabled bit,
        password varchar(255),
        accountName varchar(255),
        companyEmployer bit ,
        email varchar(255),
        rating integer,
        employee bit ,
        employer bit ,
        facebookValidatedId varchar(255),
        firstName varchar(255),
        introduction varchar(255),
        lastName varchar(255),
        personalEmployer bit ,
        phoneNumber varchar(255),
        profileImagePath varchar(255),
        providerId varchar(255),
        userName varchar(255) unique,
        primary key (id)
    );
    
     create table JobCategory (
        id bigint generated by default as identity unique,
        main bit not null,
        name varchar(255),
        main_id bigint,
        primary key (id)
    );

    
    create table User_Skills (
        user_id bigint not null,
        skill_id bigint not null,
        primary key (user_id, skill_id)
     
    );
    
     create table User_Points (
        user_id bigint not null,
        point_id bigint not null,
        primary key (user_id, point_id)
     
    );

    create table User_Roles (
        user_id bigint not null,
        role_id bigint not null,
        primary key (user_id, role_id)
     
    );
    
     create table User_Ratings (
        user_id bigint not null,
        rating_id bigint not null,
        primary key (user_id, rating_id)
     
    );

    create table ValueSet (
        id bigint generated by default as identity unique,
        name varchar(255) not null,
        primary key (id)
    );

    create table ValueSetEntry (
        id bigint generated by default as identity unique,
        key varchar(255) not null,
        valueSet_id bigint not null,
        primary key (id)
    );


    create table ValueStore (
        id bigint generated by default as identity unique,
        name varchar(255),
        value varchar(255),
        primary key (id)
    );

    create table settlement (
        id bigint generated by default as identity unique,
        settlement varchar(255),
        zip integer,
        primary key (id)
    );

     create table Advertisement (
        id bigint generated by default as identity unique,
        city varchar(255),
        other varchar(255),
        zipcode integer,
        description varchar(2000),
        imagepaths varchar(4000),
        expiration timestamp,
        jobTime timestamp,
        category_id bigint,
        remuneration varchar(255),
        specifiedJobTime boolean not null,
        user_id bigint,
        homework boolean,
        maxOffer integer,
        primary key (id)
    );
