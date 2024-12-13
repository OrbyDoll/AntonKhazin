SELECT * FROM post WHERE post_id IN (
	SELECT post_id FROM comment GROUP BY post_id HAVING count(*) <= 1
)
ORDER BY post_id LIMIT 10

-- RESULT
1	"post1"	"a"	1	"2024-12-13 20:40:58.330692+03"
3	"post3"	"aaa"	3	"2024-12-13 20:40:58.330692+03"
5	"post5"	"aaaaa"	5	"2024-12-13 20:40:58.330692+03"
7	"post7"	"aaaaaaa"	7	"2024-12-13 20:40:58.330692+03"
9	"post9"	"aaaaaaaaa"	9	"2024-12-13 20:40:58.330692+03"
11	"post11"	"aaaaaaaaaaa"	11	"2024-12-13 20:40:58.330692+03"
13	"post13"	"aaaaaaaaaaaaa"	13	"2024-12-13 20:40:58.330692+03"
15	"post15"	"aaaaaaaaaaaaaaa"	15	"2024-12-13 20:40:58.330692+03"
17	"post17"	"aaaaaaaaaaaaaaaaa"	17	"2024-12-13 20:40:58.330692+03"
19	"post19"	"aaaaaaaaaaaaaaaaaaa"	19	"2024-12-13 20:40:58.330692+03"