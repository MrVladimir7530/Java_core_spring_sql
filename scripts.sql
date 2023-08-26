SELECT * FROM faculty;

SELECT * FROM student
Where age BETWEEN 10 and 20;

SELECT name FROM student
ORDER By name;

SELECT * FROM student
WHERE name  LIKE '%o%'
ORDER By name;

SELECT * FROM student
WHERE age > 20
ORDER By name;

SELECT * FROM student
ORDER By age;

SELECT s.name, s.age, f.name FROM student s
    INNER JOIN faculty f on s.id = f.student_id;