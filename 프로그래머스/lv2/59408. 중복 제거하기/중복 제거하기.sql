-- 코드를 입력하세요
SELECT COUNT(DISTINCT NAME) count
FROM ANIMAL_INS
WHERE NOT NAME IS NULL;