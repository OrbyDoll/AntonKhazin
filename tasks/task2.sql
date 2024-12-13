SELECT * FROM post WHERE post_id IN (
	SELECT post_id FROM comment GROUP BY post_id HAVING count(*) = 2
) AND length(content) > 20 AND LEFT(title,1) IN ('0','1','2','3','4','5','6','7','8','9')
ORDER BY post_id

-- RESULT
22	"22post"	"aaaaaaaaaaaaaaaaaaaaaa"	22	"2024-12-13 20:40:58.330692+03"
24	"24post"	"aaaaaaaaaaaaaaaaaaaaaaaa"	24	"2024-12-13 20:40:58.330692+03"
26	"26post"	"aaaaaaaaaaaaaaaaaaaaaaaaaa"	26	"2024-12-13 20:40:58.330692+03"
28	"28post"	"aaaaaaaaaaaaaaaaaaaaaaaaaaaa"	28	"2024-12-13 20:40:58.330692+03"
32	"32post"	"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"	32	"2024-12-13 20:40:58.330692+03"
34	"34post"	"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"	34	"2024-12-13 20:40:58.330692+03"
36	"36post"	"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"	36	"2024-12-13 20:40:58.330692+03"
38	"38post"	"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"	38	"2024-12-13 20:40:58.330692+03"
42	"42post"	"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"	42	"2024-12-13 20:40:58.330692+03"
44	"44post"	"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"	44	"2024-12-13 20:40:58.330692+03"
