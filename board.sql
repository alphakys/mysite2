--<select>--

select b.no,
       title,
       u.name,
       hit,
       reg_date,
       user_no
from board b left outer join users u
on   b.user_no = u.no 
order by no asc;      

select name,
        hit,
        reg_date,
        title
from board b, users u
where b.no = '3' and b.user_no = u.no;

select name,
        hit,
        reg_date,
        title
from board b left outer join users u
on   b.user_no = u.no
where b.no = '3';


--이름으로 검색하기
select b.no,
       title,
       u.name,
       hit,
       reg_date,
       user_no
from board b left outer join users u
on   b.user_no = u.no 
where u.name like '정우'
order by no asc;





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

delete board 
where no = '1';

select *
from board;

--<테이블 수정>--
alter table board modify hit default 0;
alter table board modify hit default null;


--<<update>>--

update  board
set   hit = '11'
where no = '3';



--<<삭제관련>>--
drop table board;

drop sequence seq_board_no;

update board
set    title= '22',
       content = 'not null please'
where no = '2';       

