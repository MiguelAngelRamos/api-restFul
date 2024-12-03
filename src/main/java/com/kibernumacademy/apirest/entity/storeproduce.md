```sql
DELIMITER $$
CREATE PROCEDURE GetUserActivitySummary(
    IN p_user_id BIGINT,
    OUT total_posts INT,
    OUT last_post_title VARCHAR(255)
)
BEGIN
    -- Calcular el número total de posts del usuario
    SELECT COUNT(*)
    INTO total_posts
    FROM Post
    WHERE user_id = p_user_id;

    -- Obtener el título del último post del usuario
    SELECT title
    INTO last_post_title
    FROM Post
    WHERE user_id = p_user_id
    ORDER BY id DESC
    LIMIT 1;
END$$
DELIMITER ;
```