import java.util.HashMap;

//Class to store the upcoming MatchUp
public class MatchUp {
    //attributes
    private Team teamA = null, teamB = null;
    private String time = "";

    //constructor
    public MatchUp(String _teamA, String _teamB, String _time){
        teamA = new Team(_teamA);
        teamB = new Team(_teamB);
        time = _time;
    }
    //getters
    public Team getTeamA(){
        return teamA;
    }
    public Team getTeamB(){
        return teamB;
    }
    public String getTime(){
        return time;
    }
    public String getAll(){
        return getTeamA() + " " + getTeamB() + " " + getTime();
    }
    public String getAllString(){
        return getTeamA().getTeamName() + " " + getTeamB().getTeamName() + " " + getTime();
    }
    //setters
    public void setTeamA(String _teamA){
        teamA = new Team(_teamA);
    }
    public void setTeamB(String _teamB){
        teamB = new Team(_teamB);
    }
    public void setTime(String _time){
        time = _time;
    }
}
