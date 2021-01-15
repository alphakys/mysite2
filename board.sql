--테이블 생성
create table board(

        no  number  primary key,
        title varchar2(500) not null,
        content varchar2(4000),
        hit number,
        reg_date date   not null,
        user_no  number not null,
        
        constraint no_foreign_key foreign key(user_no)
            references users(no) on delete cascade
        
        );

--시퀀스 생성
create sequence seq_board_no
increment by 1
start with 1
nocache;



select *
from board;

--<테이블 수정>--
alter table board modify hit default 0;
alter table board modify hit default null;

--<<삭제관련>>--
drop table board;

drop sequence seq_board_no;



