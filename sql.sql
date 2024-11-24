CREATE TABLE person( 
    person_id VARCHAR (10)
    , name VARCHAR (50) not null
    , age INTEGER not null
    , birth DATE not null
    , sex char (1) not null
    , salary NUMERIC (10, 2)
    , description TEXT
    , ref_timestamp_zone TIMESTAMP WITH TIME ZONE
    , create_user VARCHAR (30)
    , create_date TIMESTAMP (6)
    , update_user VARCHAR (30)
    , update_date TIMESTAMP (6)
    , is_active BOOLEAN
    , is_delete BOOLEAN
    , PRIMARY KEY (person_id, name)
); 

COMMENT ON TABLE person IS '個人情報';
COMMENT ON COLUMN person.person_id IS '個人ID';
COMMENT ON COLUMN person.name IS '姓名';
COMMENT ON COLUMN person.age IS '年齢';
COMMENT ON COLUMN person.sex IS '性別';
COMMENT ON COLUMN person.birth IS '生年月日';
COMMENT ON COLUMN person.salary IS '給料';
COMMENT ON COLUMN person.description IS '説明';
COMMENT ON COLUMN person.ref_timestamp_zone IS '任意日時（タイムゾーン）';
COMMENT ON COLUMN person.create_user IS '作成者';
COMMENT ON COLUMN person.create_date IS '作成日時';
COMMENT ON COLUMN person.update_user IS '更新者';
COMMENT ON COLUMN person.update_date IS '更新日時';
COMMENT ON COLUMN person.is_active IS '有効';
COMMENT ON COLUMN person.is_delete IS '削除';

drop table person

CREATE TABLE school( 
    person_id VARCHAR (10)
    , name VARCHAR (50) not null
    , grade smallint not null
    , classmate smallint not null
    , description TEXT
    , create_user VARCHAR (30)
    , create_date TIMESTAMP (6)
    , update_user VARCHAR (30)
    , update_date TIMESTAMP (6)
    , is_active BOOLEAN
    , is_delete BOOLEAN
    , PRIMARY KEY (person_id)
); 

COMMENT ON TABLE school IS '学校';
COMMENT ON COLUMN school.person_id IS '個人ID';
COMMENT ON COLUMN school.name IS '学校名';
COMMENT ON COLUMN school.grade IS '学年';
COMMENT ON COLUMN school.classmate IS 'クラスメート';
COMMENT ON COLUMN school.description IS '説明';
COMMENT ON COLUMN school.create_user IS '作成者';
COMMENT ON COLUMN school.create_date IS '作成日時';
COMMENT ON COLUMN school.update_user IS '更新者';
COMMENT ON COLUMN school.update_date IS '更新日時';
COMMENT ON COLUMN school.is_active IS '有効';
COMMENT ON COLUMN school.is_delete IS '削除';

select
    * 
from
    person t1
    , school t2 
where
    t1.person_id = t2.person_id

