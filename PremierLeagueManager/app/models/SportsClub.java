package models;

import java.io.Serializable;
import java.util.Objects;

public class SportsClub implements Serializable {

    private String clubCode;    //Code Of The Club
    private String clubName;    //Name Of The Club
    private String location;    //Location Of The Club

    public SportsClub(String clubCode, String clubName, String location) {
        this.clubCode = clubCode;
        this.clubName = clubName;
        this.location = location;
    }

    public String getClubCode() {
        return clubCode;
    }

    public void setClubCode(String clubCode) {
        this.clubCode = clubCode;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub that = (SportsClub) o;
        return Objects.equals(clubCode, that.clubCode) &&
                Objects.equals(clubName, that.clubName) &&
                Objects.equals(location, that.location);

    }

    @Override
    public int hashCode() {
        return Objects.hash(clubName, location);
    }
}