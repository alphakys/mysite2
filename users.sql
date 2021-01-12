--<<생성 관련>>


--테이블 생성
create table users(
        no number,
        id varchar2(20) unique not null,
        password varchar2(20) not null,
        name varchar2(20),
        gender varchar2(10),
        primary key(no)
);

--시퀀스 생성
create sequence seq_users_no
increment by 1
start with 1
nocache;

--insert
insert into users
values(seq_users_no.nextval, 'alphakys', '1234', '강용수', '남');

select *
from users
where password = 1234 and id = '정우성';



--<<삭제 관련>>



--시퀀스 삭제
drop sequence seq_user_no;

select *
from users;

--테이블 데이터 삭제
delete users;

