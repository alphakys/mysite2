--테이블 생성
create table board(

        no  number  primary key,
        name varchar2(20) not null,
        title varchar2(100) not null,
        content varchar2(3000) not null,
        reg_date date
        );

--시퀀스 생성
create sequence seq_board_no
increment by 1
start with 1
nocache;

insert into board
values(seq_board_no.nextval, '강용수', '즐코', '즐코합시다', sysdate);

select *
from board;

