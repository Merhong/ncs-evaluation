/* ExamPapers(시험지) */
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

/* ExamPaperMultipleQuestions(시험지 문제) */
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

/* ExamPaperMultipleQuestionAnswers(시험지 문제 답안) */
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

/* CoursesAbilityUnits(과정 + 단위 능력 중간 테이블) */
INSERT INTO courses_ability_units(course_id, abilityunit_id, createdate, updatedate)
VALUES (1, 1, now(), now());
INSERT INTO courses_ability_units(course_id, abilityunit_id, createdate, updatedate)
VALUES (1, 2, now(), now());
INSERT INTO courses_ability_units(course_id, abilityunit_id, createdate, updatedate)
VALUES (2, 3, now(), now());
INSERT INTO courses_ability_units(course_id, abilityunit_id, createdate, updatedate)
VALUES (2, 4, now(), now());
INSERT INTO courses_ability_units(course_id, abilityunit_id, createdate, updatedate)
VALUES (3, 5, now(), now());
INSERT INTO courses_ability_units(course_id, abilityunit_id, createdate, updatedate)
VALUES (3, 6, now(), now());
INSERT INTO courses_ability_units(course_id, abilityunit_id, createdate, updatedate)
VALUES (3, 7, now(), now());

/* Students(학생) */
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (1, '학생A', '010-1111-1111', 'ACTIVE', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (1, '학생B', '010-2222-1111', 'ACTIVE', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (1, '학생C', '010-3333-1111', 'DROP', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (1, '학생D', '010-4444-4444', 'ACTIVE', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (2, '학생E', '010-1111-1111', 'ACTIVE', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (2, '학생F', '010-2222-2222', 'ACTIVE', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (2, '학생G', '010-3333-3333', 'ACTIVE', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (2, '학생H', '010-4444-4444', 'DROP', now(), now());

/* Exam(시험) */
INSERT INTO exams(student_id, exampaper_id, createdate, updatedate)
VALUES (1, 1, now(), now());
INSERT INTO exams(student_id, exampaper_id, createdate, updatedate)
VALUES (2, 1, now(), now());
INSERT INTO exams(student_id, exampaper_id, createdate, updatedate)
VALUES (3, 1, now(), now());

/* ExamResults(평가 결과) */
-- 학생 1의 국어 시험 평가 결과(3문제 맞춤, OOO)
INSERT INTO exam_results(exam_id, totalpoint, grade, comment, status, createdate, updatedate)
VALUES (1, 60, 3, 'UI 설계에 대한 이해도와 구현 능력이 뛰어나며....', 'WAIT', now(), now());
-- 학생 2의 국어 시험 평가 결과(2문제 맞춤, OXO)
INSERT INTO exam_results(exam_id, totalpoint, grade, comment, status, createdate, updatedate)
VALUES (2, 40, 2, '.....', 'WAIT', now(), now());
-- 학생 3의 국어 시험 평과 결과(1문제 맞춤, XXO)
INSERT INTO exam_results(exam_id, totalpoint, grade, comment, status, createdate, updatedate)
VALUES (3, 20, 1, '미흡한 면이 있습니다.', 'WAIT', now(), now());

/* ExamResultMultipleItems(평가 결과 채점) */
-- 학생 1의 국어 시험 채점
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (1, 1, 1, 20, '1-1 문제(20점) A 정답', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (1, 2, 6, 20, '1-2 문제(20점) C 정답', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (1, 3, 7, 20, '1-3 문제(20점) A 정답', now(), now());
-- 학생 2의 국어 시험 채점
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (2, 1, 1, 20, '1-1 문제(20점) A 정답', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (2, 2, 4, 0, '1-2 문제(20점) A 오답', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (2, 3, 7, 20, '1-3 문제(20점) A 정답', now(), now());
-- 학생 3의 국어 시험 채점
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (3, 1, 2, 0, '1-1 문제(20점) B 오답', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (3, 2, 5, 0, '1-2 문제(20점) B 오답', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (3, 3, 7, 20, '1-3 문제(20점) A 오답', now(), now());