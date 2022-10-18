WITH top_500_artists AS (
  SELECT * FROM artists
  WHERE country_lastfm != ''
  ORDER BY listeners_lastfm DESC
  LIMIT 500
)

SELECT country_lastfm, COUNT(country_lastfm) AS country_count
FROM top_500_artists

GROUP BY country_lastfm
ORDER BY country_count DESC;
