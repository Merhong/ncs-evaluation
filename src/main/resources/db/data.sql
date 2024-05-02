-- ExamPapers(시험지)
Insert INTO exam_papers(abilityunit_id, user_id, name, examtype, createdate, updatedate)
VALUES (1, 1, '시험지1', 'MULTIPLE_CHOICE', now(), now());
Insert INTO exam_papers(abilityunit_id, user_id, name, examtype, createdate, updatedate)
VALUES (2, 2, '시험지2', 'MULTIPLE_CHOICE', now(), now());
Insert INTO exam_papers(abilityunit_id, user_id, name, examtype, createdate, updatedate)
VALUES (3, 3, '시험지3', 'MULTIPLE_CHOICE', now(), now());
Insert INTO exam_papers(abilityunit_id, user_id, name, examtype, createdate, updatedate)
VALUES (4, 4, '시험지4', 'MULTIPLE_CHOICE', now(), now());
Insert INTO exam_papers(abilityunit_id, user_id, name, examtype, createdate, updatedate)
VALUES (5, 2, '시험지5', 'MULTIPLE_CHOICE', now(), now());
Insert INTO exam_papers(abilityunit_id, user_id, name, examtype, createdate, updatedate)
VALUES (6, 2, '시험지6', 'MULTIPLE_CHOICE', now(), now());
Insert INTO exam_papers(abilityunit_id, user_id, name, examtype, createdate, updatedate)
VALUES (7, 2, '시험지7', 'MULTIPLE_CHOICE', now(), now());
Insert INTO exam_papers(abilityunit_id, user_id, name, examtype, createdate, updatedate)
VALUES (8, 2, '시험지8', 'MULTIPLE_CHOICE', now(), now());

-- ExamPaperMultipleQuestions(시험지 문제)
-- 시험지1 1~3번 문제
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, createdate, updatedate)
VALUES (1, 1, 4, '1-1번 문제', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, createdate, updatedate)
VALUES (1, 2, 4, '1-2번 문제', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, createdate, updatedate)
VALUES (1, 3, 4, '1-3번 문제', now(), now());
-- 시험지2 1~3번 문제
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, createdate, updatedate)
VALUES (2, 1, 4, '2-1번 문제', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, createdate, updatedate)
VALUES (2, 2, 4, '2-2번 문제', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, createdate, updatedate)
VALUES (2, 3, 4, '2-3번 문제', now(), now());
-- 시험지3 1~3번 문제
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, createdate, updatedate)
VALUES (3, 1, 4, '3-1번 문제', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, createdate, updatedate)
VALUES (3, 2, 4, '3-2번 문제', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, createdate, updatedate)
VALUES (3, 3, 4, '3-3번 문제', now(), now());
-- 시험지4 1~3번 문제
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, createdate, updatedate)
VALUES (4, 1, 4, '4-1번 문제', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, createdate, updatedate)
VALUES (4, 2, 4, '4-2번 문제', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, createdate, updatedate)
VALUES (4, 3, 4, '4-3번 문제', now(), now());

-- ExamPaperMultipleQuestionAnswers(시험지 문제 답안)
-- 시험지1 1번 문제 답안 A,B,C
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (1, 1, 'A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (1, 2, 'B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (1, 3, 'C', false);
-- 시험지1 2번 문제 답안 A,B,C
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (2, 1, 'A', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (2, 2, 'B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (2, 3, 'C', true);
-- 시험지1 3번 문제 답안 A,B,C
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (3, 1, 'A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (3, 2, 'B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (3, 3, 'C', false);
-- 시험지2 1번 문제 답안 A,B,C
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (4, 1, 'A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (4, 2, 'B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (4, 3, 'C', false);
-- 시험지2 2번 문제 답안 A,B,C
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (5, 1, 'A', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (5, 2, 'B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (5, 3, 'C', true);
-- 시험지2 3번 문제 답안 A,B,C
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (6, 1, 'A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (6, 2, 'B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (6, 3, 'C', false);
-- 시험지3 1번 문제 답안 A,B,C
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (7, 1, 'A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (7, 2, 'B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (7, 3, 'C', false);
-- 시험지3 2번 문제 답안 A,B,C
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (8, 1, 'A', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (8, 2, 'B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (8, 3, 'C', true);
-- 시험지3 3번 문제 답안 A,B,C
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (9, 1, 'A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (9, 2, 'B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (9, 3, 'C', false);
-- 시험지4 1번 문제 답안 A,B,C
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (10, 1, 'A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (10, 2, 'B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (10, 3, 'C', false);
-- 시험지4 2번 문제 답안 A,B,C
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (11, 1, 'A', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (11, 2, 'B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (11, 3, 'C', true);
-- 시험지4 3번 문제 답안 A,B,C
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (12, 1, 'A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (12, 2, 'B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (12, 3, 'C', false);