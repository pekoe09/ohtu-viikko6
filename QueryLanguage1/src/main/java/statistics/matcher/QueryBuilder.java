package statistics.matcher;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    
    private List<Matcher> matchers;
    
    public QueryBuilder() {
        matchers = new ArrayList<Matcher>();
    }
    
    public Matcher build() {   
        Matcher[] matcherArray = new Matcher[matchers.size()];
        matcherArray = matchers.toArray(matcherArray);
        And and = new And(matcherArray);
        return (Matcher)and;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matchers.add(new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matchers.add(new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder playsIn(String team) {
        this.matchers.add(new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.matchers.add(new Or(matchers));
        return this;
    }
    
    public QueryBuilder not(Matcher matcher) {
        this.matchers.add(new Not(matcher));
        return this;
    }
}
