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

Broncos Offensive Passing Ranking (OPR): 1st out of 32 teams.
Broncos Defensive Passing Ranking (DPR): 5th out of 32 teams.
Broncos Offensive Rushing Ranking (ORR): 10th out of 32 teams.
Broncos Defensive Rushing Ranking (DRR): 20th out of 32 teams.

Raiders Offensive Passing Ranking (OPR): 31st out of 32 teams.
Raiders Defensive Passing Ranking (DPR): 3rd out of 32 teams.
Raiders Offensive Rushing Ranking (ORR): 4th out of 32 teams.
Raiders Defensive Rushing Ranking (DRR): 19th out of 32 teams.

Based on these rankings, a differential can be produced:

Broncos OPR vs Raiders DPR = (Raiders) 3  - (Denver)  1  = 2   (differential)
Broncos ORR vs Raiders DRR = (Raiders) 19 - (Denver)  10 = 9   (differential)
Raiders OPR vs Broncos DPR = (Broncos) 5  - (Raiders) 31 = -26 (differential)
Raiders ORR vs Broncos DRR = (Broncos) 20 - (Raiders) 4  = 16  (differential)

The differential value will vary from -31 (worst matchup) to +31 (best matchup). 0 means a very even matchup. So using this information we can conclude
the following:

* The Broncos Offensive Passing game vs. the Raiders Defensive Passing game is a fairly even matchup at a differential of 2.
* The Broncos Offensive Rushing game vs. the Raiders Defensive Rushing game favors the Broncos pretty heavily at 19.
* The Raiders Offensive Passing game vs. the Broncos Defensive Passing game is a fairly even matchup at a differential of 5.
* The Raiders Offensive Rushing game vs. the Broncos Defensive Rushing game favors the Broncos at 20.

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
Starting a new Gradle Daemon for this build (subsequent builds will be faster).
Parallel execution with configuration on demand is an incubating feature.
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:run
Loading schedule: http://www.espn.com/nfl/schedule/_/week/

Games: 

MIA at CIN
IND at JAX
TEN at HOU
CLE at WSH
SEA at NYJ
BUF at NE
CAR at ATL
OAK at BAL
DET at CHI
DEN at TB
LA at ARI
NO at SD
DAL at SF
KC at PIT
NYG at MIN

Offensive Passing Rankings

Team: 01, ATL
Team: 02, NO
Team: 03, CIN
Team: 04, WSH
Team: 05, DET
Team: 06, ARI
Team: 07, SD
Team: 08, OAK
Team: 09, CAR
Team: 10, SEA
Team: 11, PIT
Team: 12, IND
Team: 13, TB
Team: 14, CHI
Team: 15, MIA
Team: 16, BAL
Team: 17, KC
Team: 18, NYJ
Team: 19, DAL
Team: 20, JAX
Team: 21, DEN
Team: 22, NYG
Team: 23, CLE
Team: 24, HOU
Team: 25, TEN
Team: 26, NE
Team: 27, LA
Team: 28, PHI
Team: 29, BUF
Team: 30, SF
Team: 31, MIN
Team: 32, GB

Defensive Passing Rankings

Team: 01, PHI
Team: 02, MIN
Team: 03, HOU
Team: 04, DEN
Team: 05, BAL
Team: 06, SEA
Team: 07, NYG
Team: 08, JAX
Team: 09, ARI
Team: 10, CHI
Team: 11, CIN
Team: 12, GB
Team: 13, KC
Team: 14, TEN
Team: 15, SF
Team: 16, CAR
Team: 17, TB
Team: 18, CLE
Team: 19, BUF
Team: 20, DAL
Team: 21, NE
Team: 22, DET
Team: 23, MIA
Team: 24, LA
Team: 25, IND
Team: 26, WSH
Team: 27, NYJ
Team: 28, SD
Team: 29, NO
Team: 30, PIT
Team: 31, ATL
Team: 32, OAK

Offensive Rushing Rankings

Team: 01, CLE
Team: 02, DAL
Team: 03, NE
Team: 04, TEN
Team: 05, OAK
Team: 06, ATL
Team: 07, BUF
Team: 08, CAR
Team: 09, SF
Team: 10, NYJ
Team: 11, HOU
Team: 12, PIT
Team: 13, DEN
Team: 14, ARI
Team: 15, SD
Team: 16, BAL
Team: 17, SEA
Team: 18, WSH
Team: 19, DET
Team: 20, KC
Team: 21, PHI
Team: 22, IND
Team: 23, TB
Team: 24, CHI
Team: 25, NO
Team: 26, CIN
Team: 27, MIA
Team: 28, LA
Team: 29, GB
Team: 30, JAX
Team: 31, NYG
Team: 32, MIN

Defensive Rushing Rankings

Team: 01, GB
Team: 02, PHI
Team: 03, NYG
Team: 04, MIN
Team: 05, NYJ
Team: 06, PIT
Team: 07, BAL
Team: 08, SEA
Team: 09, SD
Team: 10, CAR
Team: 11, DAL
Team: 12, TB
Team: 13, BUF
Team: 14, CIN
Team: 15, NE
Team: 16, ATL
Team: 17, LA
Team: 18, IND
Team: 19, JAX
Team: 20, TEN
Team: 21, ARI
Team: 22, DEN
Team: 23, DET
Team: 24, CLE
Team: 25, NO
Team: 26, CHI
Team: 27, HOU
Team: 28, KC
Team: 29, MIA
Team: 30, WSH
Team: 31, OAK
Team: 32, SF

******************************
Best Offensive Passing Scenarios
******************************

PHI must be on a bye.
GB must be on a bye.

Differential: 26, NO
Differential: 22, SD
Differential: 22, CAR
Differential: 20, CIN
Differential: 18, ARI
Differential: 17, SEA
Differential: 16, BAL
Differential: 15, ATL
Differential: 14, WSH
Differential: 13, KC
Differential: 8, CHI
Differential: 5, DET
Differential: 5, JAX
Differential: 3, CLE
Differential: 2, PIT
Differential: -3, OAK
Differential: -4, IND
Differential: -4, MIA
Differential: -4, DAL
Differential: -4, DEN
Differential: -7, NE
Differential: -8, BUF
Differential: -9, TB
Differential: -10, HOU
Differential: -10, SF
Differential: -12, NYJ
Differential: -18, LA
Differential: -20, NYG
Differential: -22, TEN
Differential: -24, MIN

******************************
Best Offensive Rushing Scenarios
******************************

PHI must be on a bye.
GB must be on a bye.

Differential: 30, DAL
Differential: 29, CLE
Differential: 23, TEN
Differential: 16, PIT
Differential: 15, BAL
Differential: 10, NE
Differential: 10, SD
Differential: 9, HOU
Differential: 8, BUF
Differential: 8, CAR
Differential: 7, DET
Differential: 6, WSH
Differential: 4, ATL
Differential: 3, ARI
Differential: 3, CIN
Differential: 2, OAK
Differential: 2, SF
Differential: -1, DEN
Differential: -1, TB
Differential: -1, CHI
Differential: -2, NYJ
Differential: -3, IND
Differential: -7, LA
Differential: -12, SEA
Differential: -12, JAX
Differential: -13, MIA
Differential: -14, KC
Differential: -16, NO
Differential: -27, NYG
Differential: -29, MIN


BUILD SUCCESSFUL

Total time: 5.463 secs
```

