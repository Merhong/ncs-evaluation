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
-- 시험지1 1~5번 문제
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (1, 1, 20, '1-1번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (1, 2, 20, '1-2번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (1, 3, 20, '1-3번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (1, 4, 20, '1-4번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (1, 5, 20, '1-5번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
-- 시험지2 1~5번 문제
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (2, 1, 20, '2-1번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (2, 2, 20, '2-2번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (2, 3, 20, '2-3번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (2, 4, 20, '2-4번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (2, 5, 20, '2-5번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
-- 시험지3 1~5번 문제
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (3, 1, 20, '3-1번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (3, 2, 20, '3-2번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (3, 3, 20, '3-3번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (3, 4, 20, '3-4번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());
Insert INTO exam_paper_multiple_questions(exampaper_id, no, point, content, comment, createdate, updatedate)
VALUES (3, 5, 20, '3-5번 문제', '설계된 화면과 폼의 흐름을 확인할 수 있다.', now(), now());


/* ExamPaperMultipleQuestionAnswers(시험지 문제 답안) */
-- 시험지1 1번 문제 답안
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (1, 1, '답안 A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (1, 2, '답안 B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (1, 3, '답안 C', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (1, 4, '답안 D', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (1, 5, '답안 E', false);
-- 시험지1 2번 문제 답안
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (2, 1, '답안 A', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (2, 2, '답안 B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (2, 3, '답안 C', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (2, 4, '답안 D', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (2, 5, '답안 E', false);
-- 시험지1 3번 문제 답안
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (3, 1, '답안 A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (3, 2, '답안 B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (3, 3, '답안 C', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (3, 4, '답안 D', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (3, 5, '답안 E', false);
-- 시험지1 4번 문제 답안
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (4, 1, '답안 A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (4, 2, '답안 B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (4, 3, '답안 C', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (4, 4, '답안 D', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (4, 5, '답안 E', false);
-- 시험지1 5번 문제 답안
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (5, 1, '답안 A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (5, 2, '답안 B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (5, 3, '답안 C', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (5, 4, '답안 D', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (5, 5, '답안 E', false);
-- 시험지2 1번 문제 답안
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (6, 1, '답안 A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (6, 2, '답안 B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (6, 3, '답안 C', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (6, 4, '답안 D', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (6, 5, '답안 E', false);
-- 시험지2 2번 문제 답안
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (7, 1, '답안 A', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (7, 2, '답안 B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (7, 3, '답안 C', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (7, 4, '답안 D', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (7, 5, '답안 E', false);
-- 시험지2 3번 문제 답안
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (8, 1, '답안 A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (8, 2, '답안 B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (8, 3, '답안 C', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (8, 4, '답안 D', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (8, 5, '답안 E', false);
-- 시험지2 4번 문제 답안
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (9, 1, '답안 A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (9, 2, '답안 B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (9, 3, '답안 C', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (9, 4, '답안 D', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (9, 5, '답안 E', false);
-- 시험지2 5번 문제 답안
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (10, 1, '답안 A', true);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (10, 2, '답안 B', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (10, 3, '답안 C', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (10, 4, '답안 D', false);
Insert INTO exam_paper_multiple_question_answers(exampapermultiplequestion_id, no, content, iscorrect)
VALUES (10, 5, '답안 E', false);




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
VALUES (1, '학생F', '010-1111-1111', 'ACTIVE', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (1, '학생G', '010-2222-1111', 'ACTIVE', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (1, '학생H', '010-3333-1111', 'DROP', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (1, '학생I', '010-4444-4444', 'ACTIVE', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (2, '학생J', '010-1111-1111', 'ACTIVE', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (2, '학생K', '010-2222-2222', 'ACTIVE', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (2, '학생L', '010-3333-3333', 'ACTIVE', now(), now());
INSERT INTO students(course_id, name, tel, status, createdate, updatedate)
VALUES (2, '학생M', '010-4444-4444', 'DROP', now(), now());

/* Exam(시험) */
INSERT INTO exams(student_id, exampaper_id, createdate, updatedate)
VALUES (1, 1, now(), now());
INSERT INTO exams(student_id, exampaper_id, createdate, updatedate)
VALUES (2, 1, now(), now());
INSERT INTO exams(student_id, exampaper_id, createdate, updatedate)
VALUES (3, 1, now(), now());

/* ExamResults(평가 결과) */
-- 학생 1의 국어 시험 평가 결과(5문제 맞춤, OOOOO)
INSERT INTO exam_results(exam_id, totalpoint, grade, comment, status, createdate, updatedate)
VALUES (1, 100, 5, 'UI 설계에 대한 이해도와 구현 능력이 뛰어나며....', 'WAIT', now(), now());
-- 학생 2의 국어 시험 평가 결과(4문제 맞춤, OXOOO)
INSERT INTO exam_results(exam_id, totalpoint, grade, comment, status, createdate, updatedate)
VALUES (2, 80, 4, '.....', 'WAIT', now(), now());
-- 학생 3의 국어 시험 평과 결과(3문제 맞춤, XXOOO)
INSERT INTO exam_results(exam_id, totalpoint, grade, comment, status, createdate, updatedate)
VALUES (3, 60, 3, '미흡한 면이 있습니다.', 'WAIT', now(), now());

/* ExamResultMultipleItems(평가 결과 채점) */
-- 학생 1의 국어 시험 채점 OOOOO
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (1, 1, 1, 20, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (1, 2, 8, 20, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (1, 3, 11, 20, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (1, 4, 16, 20, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (1, 5, 21, 20, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());

-- 학생 2의 국어 시험 채점
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (2, 1, 1, 20, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (2, 2, 9, 0, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (2, 3, 11, 20, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (2, 3, 16, 20, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (2, 3, 21, 20, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
-- 학생 3의 국어 시험 채점
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (3, 1, 2, 0, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (3, 2, 10, 0, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (3, 3, 11, 20, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (3, 3, 16, 20, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());
INSERT INTO exam_result_multiple_items(examresult_id, exampaperquestion_id, exampapermultiplequestionanswers_id, point,
                                       comment, createdate, updatedate)
VALUES (3, 3, 21, 20, '설계된 화면과 폼의 흐름을 확인하고... 확인할 수 있다.', now(), now());