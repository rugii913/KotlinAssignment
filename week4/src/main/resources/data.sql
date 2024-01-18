insert into to_do_card(title, description, member_id, created_date_time)
values ('제목1', '할 일 상세1', 1, CURRENT_TIMESTAMP - 25),
       ('두번째 글 제목', '상세한 할 일 2', 1, CURRENT_TIMESTAMP - 20),
       ('3제목', '일2 상세 내용', 1, CURRENT_TIMESTAMP);

insert into comment(content, user_name, password, created_date_time, to_do_card_id)
values ('댓글이야', '이건 이름1', 'test!', CURRENT_TIMESTAMP - 20, 1),
       ('2댓글', 'user_name2', 'test!', CURRENT_TIMESTAMP - 15, 1),
       ('댓글 내용입니다', 'abcd3', 'test!', CURRENT_TIMESTAMP - 10, 2),
       ('아무 댓글', '4이름', 'test!', CURRENT_TIMESTAMP - 5, 2);
