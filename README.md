What is this?
-------------

It's a command line application that scrapes NFL team rank data, looks at matchups for whatever week of football specified, and
then compares opponents to find what I call a "differential" between them.

It does so by looking at the following rankings from ESPN:

* Offensive Passing Ranking
* Defensive Passing Ranking
* Offensive Rushing Ranking
* Defensive Rushing Ranking

Let's say that the Denver Broncos are facing off against the Oakland Raiders this week. And let's say the rankings for each
team are as follows:

* ```Broncos Offensive Passing Ranking (OPR): 1st out of 32 teams.```
* ```Broncos Defensive Passing Ranking (DPR): 5th out of 32 teams.```
* ```Broncos Offensive Rushing Ranking (ORR): 10th out of 32 teams.```
* ```Broncos Defensive Rushing Ranking (DRR): 20th out of 32 teams.```

* ```Raiders Offensive Passing Ranking (OPR): 31st out of 32 teams.```
* ```Raiders Defensive Passing Ranking (DPR): 3rd out of 32 teams.```
* ```Raiders Offensive Rushing Ranking (ORR): 4th out of 32 teams.```
* ```Raiders Defensive Rushing Ranking (DRR): 19th out of 32 teams.```

Based on these rankings, a differential can be produced:

* ```Broncos OPR vs Raiders DPR = (Raiders) 3  - (Denver)  1  = 2   (differential)```
* ```Broncos ORR vs Raiders DRR = (Raiders) 19 - (Denver)  10 = 9   (differential)```
* ```Raiders OPR vs Broncos DPR = (Broncos) 5  - (Raiders) 31 = -26 (differential)```
* ```Raiders ORR vs Broncos DRR = (Broncos) 20 - (Raiders) 4  = 16  (differential)```

The differential value will vary from -31 (worst matchup) to +31 (best matchup). 0 means a very even matchup. So using this information we can conclude
the following:

* The Broncos Offensive Passing game vs. the Raiders Defensive Passing game is a fairly even matchup at a differential of 2.
* The Broncos Offensive Rushing game vs. the Raiders Defensive Rushing game favors the Broncos pretty heavily at 19.
* The Raiders Offensive Passing game vs. the Broncos Defensive Passing game is a fairly even matchup at a differential of 5.
* The Raiders Offensive Rushing game vs. the Broncos Defensive Rushing game favors the Broncos at 20.

What is it good for?
-------------

It's good for finding favorable NFL matchups for a given week, solely based on rankings statistics. It stands to reason that if you have a #1 ranked offensive passing team vs. a #32 ranked defensive passing team, the offense has a substantial advantage. So it can be used as a good starting point for picking fantasy football players. It is intended only to compliment any research you may be doing. It is NOT, by any means, a reliable source of data on its own.

It stopped working.
-------------

I fully anticipate this will happen as soon as ESPN updates or changes their website. This is working ast of Oct 17th, 2017. It is, however, susceptible to breaking because it is looking for specific HTML elements within the ESPN website. So if and when the web developers over at ESPN change things, this app will most likely break. It should be easy to fix though.


How do I run it?
-------------

To run this (from a command line), Java is required.

The command to run from within the project directory is:

```./gradlew run``` - Produce matchup differentials for the current week of NFL football.

If you want to specify the week, do so using the week number as follows. For example, to get the matchup differentials for week 5 it would be:

```./gradlew run -PappArgs="'-w=5'"```

Example Output
-------------

```
Parallel execution with configuration on demand is an incubating feature.
:compileJava
warning: [options] bootstrap class path not set in conjunction with -source 1.5
warning: [options] source value 1.5 is obsolete and will be removed in a future release
warning: [options] target value 1.5 is obsolete and will be removed in a future release
warning: [options] To suppress warnings about obsolete options, use -Xlint:-options.
4 warnings
:processResources UP-TO-DATE
:classes
:run


Date: Sat Oct 08 15:22:27 MDT 2016

Loading schedule: http://www.espn.com/nfl/schedule/_/week/

Games for week: (current)

ARI at SF
 NE at CLE
PHI at DET
CHI at IND
TEN at MIA
WSH at BAL
HOU at MIN
NYJ at PIT
ATL at DEN
CIN at DAL
BUF at LA
 SD at OAK
NYG at GB
 TB at CAR

Offensive Passing Rankings

Team:   1, ATL
Team:   2, ARI
Team:   3, NO
Team:   4, CIN
Team:   5, NYG
Team:   6, WSH
Team:   7, DET
Team:   8, SD
Team:   9, OAK
Team:  10, CAR
Team:  11, SEA
Team:  12, PIT
Team:  13, IND
Team:  14, TB
Team:  15, CHI
Team:  16, MIA
Team:  17, BAL
Team:  18, KC
Team:  19, NYJ
Team:  20, DAL
Team:  21, JAX
Team:  22, DEN
Team:  23, MIN
Team:  24, CLE
Team:  25, HOU
Team:  26, TEN
Team:  27, SF
Team:  28, NE
Team:  29, LA
Team:  30, PHI
Team:  31, BUF
Team:  32, GB

Defensive Passing Rankings

Team:   1, PHI
Team:   2, HOU
Team:   3, DEN
Team:   4, BAL
Team:   5, SEA
Team:   6, JAX
Team:   7, CHI
Team:   8, MIN
Team:   9, CIN
Team:  10, GB
Team:  11, ARI
Team:  12, KC
Team:  13, TEN
Team:  14, CAR
Team:  15, TB
Team:  16, CLE
Team:  17, BUF
Team:  18, NYG
Team:  19, DAL
Team:  20, NE
Team:  21, DET
Team:  22, MIA
Team:  23, LA
Team:  24, IND
Team:  25, SF
Team:  26, WSH
Team:  27, NYJ
Team:  28, SD
Team:  29, NO
Team:  30, PIT
Team:  31, ATL
Team:  32, OAK

Offensive Rushing Rankings

Team:   1, SF
Team:   2, CLE
Team:   3, DAL
Team:   4, ARI
Team:   5, NE
Team:   6, TEN
Team:   7, OAK
Team:   8, ATL
Team:   9, BUF
Team:  10, CAR
Team:  11, NYJ
Team:  12, HOU
Team:  13, PIT
Team:  14, DEN
Team:  15, SD
Team:  16, BAL
Team:  17, NYG
Team:  18, SEA
Team:  19, WSH
Team:  20, DET
Team:  21, KC
Team:  22, PHI
Team:  23, IND
Team:  24, TB
Team:  25, CHI
Team:  26, NO
Team:  27, CIN
Team:  28, MIA
Team:  29, LA
Team:  30, GB
Team:  31, JAX
Team:  32, MIN

Defensive Rushing Rankings

Team:   1, GB
Team:   2, PHI
Team:   3, NYJ
Team:   4, PIT
Team:   5, BAL
Team:   6, SEA
Team:   7, SD
Team:   8, MIN
Team:   9, NYG
Team:  10, CAR
Team:  11, DAL
Team:  12, TB
Team:  13, BUF
Team:  14, CIN
Team:  15, NE
Team:  16, ATL
Team:  17, LA
Team:  18, IND
Team:  19, JAX
Team:  20, TEN
Team:  21, DEN
Team:  22, DET
Team:  23, CLE
Team:  24, NO
Team:  25, CHI
Team:  26, HOU
Team:  27, KC
Team:  28, MIA
Team:  29, WSH
Team:  30, OAK
Team:  31, ARI
Team:  32, SF

******************************
Best Offensive Passing Scenarios
******************************

NO appears to be on a bye.
SEA appears to be on a bye.
KC appears to be on a bye.
JAX appears to be on a bye.

  1. Differential:  24 ->  SD (playing OAK)
  2. Differential:  23 -> ARI (playing SF)
  3. Differential:  19 -> OAK (playing SD)
  4. Differential:  15 -> CIN (playing DAL)
  5. Differential:  15 -> PIT (playing NYJ)
  6. Differential:  11 -> NYJ (playing PIT)
  7. Differential:   9 -> CHI (playing IND)
  8. Differential:   9 -> BAL (playing WSH)
  9. Differential:   9 -> DEN (playing ATL)
 10. Differential:   5 -> NYG (playing GB)
 11. Differential:   5 -> CAR (playing TB)
 12. Differential:   2 -> ATL (playing DEN)
 13. Differential:   0 ->  TB (playing CAR)
 14. Differential:  -2 -> WSH (playing BAL)
 15. Differential:  -3 -> MIA (playing TEN)
 16. Differential:  -4 -> CLE (playing NE)
 17. Differential:  -4 -> TEN (playing MIA)
 18. Differential:  -6 -> DET (playing PHI)
 19. Differential:  -6 -> IND (playing CHI)
 20. Differential:  -8 -> BUF (playing LA)
 21. Differential:  -9 -> PHI (playing DET)
 22. Differential: -11 -> DAL (playing CIN)
 23. Differential: -12 ->  NE (playing CLE)
 24. Differential: -12 ->  LA (playing BUF)
 25. Differential: -14 ->  GB (playing NYG)
 26. Differential: -16 ->  SF (playing ARI)
 27. Differential: -17 -> HOU (playing MIN)
 28. Differential: -21 -> MIN (playing HOU)

******************************
Best Offensive Rushing Scenarios
******************************

SEA appears to be on a bye.
KC appears to be on a bye.
NO appears to be on a bye.
JAX appears to be on a bye.

  1. Differential:  30 ->  SF (playing ARI)
  2. Differential:  28 -> ARI (playing SF)
  3. Differential:  22 -> TEN (playing MIA)
  4. Differential:  18 ->  NE (playing CLE)
  5. Differential:  15 ->  SD (playing OAK)
  6. Differential:  13 -> CLE (playing NE)
  7. Differential:  13 -> ATL (playing DEN)
  8. Differential:  13 -> BAL (playing WSH)
  9. Differential:  11 -> DAL (playing CIN)
 10. Differential:   8 -> BUF (playing LA)
 11. Differential:   2 -> CAR (playing TB)
 12. Differential:   2 -> DEN (playing ATL)
 13. Differential:   2 -> IND (playing CHI)
 14. Differential:   0 -> OAK (playing SD)
 15. Differential:   0 -> PHI (playing DET)
 16. Differential:  -4 -> HOU (playing MIN)
 17. Differential:  -6 -> MIN (playing HOU)
 18. Differential:  -7 -> NYJ (playing PIT)
 19. Differential:  -7 -> CHI (playing IND)
 20. Differential:  -8 -> MIA (playing TEN)
 21. Differential: -10 -> PIT (playing NYJ)
 22. Differential: -14 -> WSH (playing BAL)
 23. Differential: -14 ->  TB (playing CAR)
 24. Differential: -16 -> NYG (playing GB)
 25. Differential: -16 -> CIN (playing DAL)
 26. Differential: -16 ->  LA (playing BUF)
 27. Differential: -18 -> DET (playing PHI)
 28. Differential: -21 ->  GB (playing NYG)


BUILD SUCCESSFUL

Total time: 1.641 secs

```

