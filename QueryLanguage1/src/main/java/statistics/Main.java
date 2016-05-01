package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
          
        Matcher m = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        
        System.out.println("***** 30 maalia tai NYI *****");
        
        m = new Or( new HasAtLeast(30, "goals"),
                             new PlaysIn("NYI")
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        
       System.out.println("***** 30 maalia eikä NYI tai PHI *****");
        
        m = new And( new HasAtLeast(30, "goals"),
                            new And(new Not(new PlaysIn("NYI")), new Not(new PlaysIn("PHI")))
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        
        System.out.println("***** Alle 2 maalia *****");
        
        m = new HasFewerThan(2, "goals");
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        
        System.out.println("***** Alle 2 maalia - ketjutettu *****");
        
        QueryBuilder query = new QueryBuilder();
        
        m = query.hasFewerThan(2, "goals").build();
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        
        System.out.println("***** 30 maalia tai NYI - ketjutettu *****");
        
        query = new QueryBuilder();
        Matcher m1 = query.hasAtLeast(30, "goals").build();
        query = new QueryBuilder();
        Matcher m2 = query.playsIn("NYI").build();
        query = new QueryBuilder();
        m = query.oneOf(m1, m2).build();
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
    }
}
