WITH total_tags AS (
    SELECT artist_lastfm, listeners_lastfm, tag FROM artists
    LATERAL VIEW explode(split(tags_lastfm, ";")) tag_table AS tag
    WHERE tag != ""
),

clean_tags AS (
SELECT artist_lastfm, listeners_lastfm, trim(tag) as tag FROM total_tags
),

tags_counts AS (
    SELECT tag, count(tag) as tag_count FROM clean_tags
    GROUP BY tag
    ORDER BY tag_count DESC
    LIMIT 10
),
popular_artists AS (
    SELECT DISTINCT artist_lastfm, listeners_lastfm FROM clean_tags
    WHERE tag in (SELECT tag FROM tags_counts)
    ORDER BY listeners_lastfm DESC
    LIMIT 10
)
SELECT artist_lastfm FROM popular_artists
