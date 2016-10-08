package com.ericschlenz.nflscrape;

import org.apache.commons.cli.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Eric on 10/2/16.
 */
public class Main {

    private static final String SCHEDULE = "http://www.espn.com/nfl/schedule/_/week/%1$s";
    private static final String OFF_PASS_RANKS = "http://www.espn.com/nfl/statistics/team/_/stat/passing";
    private static final String OFF_RUSH_RANKS = "http://www.espn.com/nfl/statistics/team/_/stat/rushing";
    private static final String DEF_PASS_RANKS = "http://www.espn.com/nfl/statistics/team/_/stat/passing/position/defense";
    private static final String DEF_RUSH_RANKS = "http://www.espn.com/nfl/statistics/team/_/stat/rushing/position/defense";

    private static final String ESPN_BYE_WEEK = ".byeweek";
    private static final String ESPN_TEAM_NAME = ".team-name";
    private static final String ESPN_ABBR = "abbr";
    private static final String ESPN_EVEN_ODD_ELEMENTS = "tr.oddrow, tr.evenrow";
    private static final String ESPN_ALIGN_LEFT = "[align=left]";
    private static final String ESPN_A = "a";

    private final String scheduleUrl;
    private Map<String, String> schedule = new LinkedHashMap<String, String>();
    private Map<String, Integer> offPassRanks = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> defPassRanks = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> offRushRanks = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> defRushRanks = new LinkedHashMap<String, Integer>();
    private List<Differential> offPassDiff = new ArrayList<Differential>();
    private List<Differential> offRushDiff = new ArrayList<Differential>();

    public Main(String scheduleUrl) {
        this.scheduleUrl = scheduleUrl;
    }

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("w", true, "The NFL week");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse parameters.", e);
        }

        String week;
        if (cmd.hasOption("w")) {
            week = cmd.getOptionValue("w");
        } else {
            week = "";
        }

        Main main = new Main(String.format(SCHEDULE, week));
        main.load();
        System.exit(0);
    }

    private void load() {
        loadSchedule();
        loadOffensivePassingRanks();
        loadDefensivePassingRanks();
        loadOffensiveRushingRanks();
        loadDefensiveRushingRanks();

        findBestOffensivePassingScenarios();
        findBestOffensiveRushingScenarios();
    }

    private void loadSchedule() {
        System.out.println("Loading schedule: " + scheduleUrl + "\n");
        System.out.println("Games: \n");

        // Get rid of bye week teams.
        Document doc = loadDocument(scheduleUrl);
        doc.select(ESPN_BYE_WEEK).remove();

        Elements teams = doc.select(ESPN_TEAM_NAME).select(ESPN_ABBR);

        String awayTeam = null;
        String homeTeam = null;

        for (int i=0; i < teams.size(); i++) {
            String team = teams.get(i).html();
            if ((i % 2) == 0) {
                awayTeam = team;
                System.out.print(team);
            } else {
                homeTeam = team;
                schedule.put(awayTeam, homeTeam);
                schedule.put(homeTeam, awayTeam);

                System.out.print(" at ");
                System.out.println(homeTeam);
            }
        }

        System.out.println();
    }

    private void loadOffensivePassingRanks() {
        Document doc = loadDocument(OFF_PASS_RANKS);
        loadMapWithEspnRanks(doc, offPassRanks, "Offensive Passing Rankings");
    }

    private void loadDefensivePassingRanks() {
        Document doc = loadDocument(DEF_PASS_RANKS);
        loadMapWithEspnRanks(doc, defPassRanks, "Defensive Passing Rankings");
    }

    private void loadOffensiveRushingRanks() {
        Document doc = loadDocument(OFF_RUSH_RANKS);
        loadMapWithEspnRanks(doc, offRushRanks, "Offensive Rushing Rankings");
    }

    private void loadDefensiveRushingRanks() {
        Document doc = loadDocument(DEF_RUSH_RANKS);
        loadMapWithEspnRanks(doc, defRushRanks, "Defensive Rushing Rankings");
    }

    private void loadMapWithEspnRanks(Document doc, Map<String, Integer> ranks, String name) {
        Elements allRows = doc.select(ESPN_EVEN_ODD_ELEMENTS);
        Elements teams = allRows.select(ESPN_ALIGN_LEFT).select(ESPN_A);
        Pattern pattern = Pattern.compile(".*nfl/team/\\_/name/([a-zA-Z]+).*");

        for (int i=0; i < teams.size(); i++) {
            String teamUrl = teams.get(i).attr("href");
            Matcher matcher = null;

            if ((matcher = pattern.matcher(teamUrl)).matches()) {
                String team = matcher.group(1).toUpperCase();
                ranks.put(team, i + 1);
            } else {
                throw new RuntimeException("Failed to get team name from: " + teamUrl);
            }

        }

        System.out.println(name);
        System.out.println();

        for (Map.Entry<String, Integer> entry : ranks.entrySet()) {
            System.out.println(String.format("Team: %1$ 3d, %2$s", entry.getValue(), entry.getKey()));
        }

        System.out.println();
    }

    private void findBestOffensivePassingScenarios() {
        System.out.println("******************************");
        System.out.println("Best Offensive Passing Scenarios");
        System.out.println("******************************\n");

        findBestOffensiveScenarios(offPassRanks, defPassRanks, offPassDiff);

        System.out.println();
    }

    private void findBestOffensiveRushingScenarios() {
        System.out.println("******************************");
        System.out.println("Best Offensive Rushing Scenarios");
        System.out.println("******************************\n");

        findBestOffensiveScenarios(offRushRanks, defRushRanks, offRushDiff);

        System.out.println();
    }

    private void findBestOffensiveScenarios(Map<String, Integer> offRanks, Map<String, Integer> defRanks, List<Differential> offDiff) {
        for (Map.Entry<String, Integer> entry : offRanks.entrySet()) {
            String opponent = schedule.get(entry.getKey());
            if (opponent == null) {
                System.out.println(String.format("%1$s must be on a bye.", entry.getKey()));
                continue;
            }

            int offRank = entry.getValue();
            int defRank = defRanks.get(opponent);

            int differential = defRank - offRank;
            offDiff.add(new Differential(entry.getKey(), differential));
        }

        Collections.sort(offDiff);

        System.out.println();
        int i=1;
        for (Differential diff : offDiff) {
            int diffVal = diff.getDifferential();
            String favoredTeam = diff.getTeamName();
            String opponentTeam = schedule.get(favoredTeam);

            System.out.println(String.format(
                "%1$ 3d. Differential: %2$ 3d -> %3$3s (playing %4$s)",
                i,
                diff.getDifferential(),
                diff.getTeamName(),
                opponentTeam));
            i++;
        }
    }

    private Document loadDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load defensive passing ranks.", e);
        }

        return doc;
    }

    private static class Differential implements Comparable<Differential> {
        private final String teamName;
        private final int differential;

        public Differential(String teamName, int differential) {
            this.teamName = teamName;
            this.differential = differential;
        }

        public String getTeamName() {
            return teamName;
        }

        public int getDifferential() {
            return differential;
        }

        public int compareTo(Differential o) {
            return Integer.valueOf(differential).compareTo(o.getDifferential()) * -1;
        }
    }

}
