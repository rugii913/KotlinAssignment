insert into to_do_card(title, description, user_name, created_date_time)
values ('제목1', '할 일 상세1', '유저1', CURRENT_TIMESTAMP),
       ('두번째 글 제목', '상세한 할 일 2', '이름2', CURRENT_TIMESTAMP),
       ('3제목', '일2 상세 내용', '세번째 user_name', CURRENT_TIMESTAMP);
insert into comment(content, user_name, password, created_date_time, to_do_card_id)
values ('댓글이야', '이건 이름1', 'test!', CURRENT_TIMESTAMP, 1),
       ('2댓글', 'user_name2', 'test!', CURRENT_TIMESTAMP, 1),
       ('댓글 내용입니다', 'abcd3', 'test!', CURRENT_TIMESTAMP, 2),
       ('아무 댓글', '4이름', 'test!', CURRENT_TIMESTAMP, 2);
