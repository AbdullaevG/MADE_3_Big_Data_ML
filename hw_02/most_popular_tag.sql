WITH total_tags AS (
  SELECT trim(raw) as tag
  FROM artists
  LATERAL VIEW explode(split(tags_lastfm, ';')) tagTable AS raw
),
top_1_tag AS (
  SELECT tag, COUNT(tag) as tag_count
  FROM total_tags
  WHERE tag != ''
  GROUP BY tag
  ORDER BY tag_count DESC
  LIMIT 1
)

SELECT tag FROM top_1_tag;
