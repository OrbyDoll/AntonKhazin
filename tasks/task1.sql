SELECT count(*) FROM profile WHERE profile_id NOT IN (SELECT profile_id FROM post)

-- RESULT: 5