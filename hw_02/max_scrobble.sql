WITH top_1_scrobbles AS (
SELECT  artist_lastfm, scrobbles_lastfm
FROM artists
SORT BY scrobbles_lastfm DESC
LIMIT 1
)

SELECT artist_lastfm FROM top_1_scrobbles;
