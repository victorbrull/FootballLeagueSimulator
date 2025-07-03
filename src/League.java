import java.util.List;

public class League {
    
    private List<String> teams;
    private Fixtures fixtures;

    public League(List<String> teamList) {
        this.teams = teamList;
        this.fixtures = new Fixtures(teams);
    }
}
