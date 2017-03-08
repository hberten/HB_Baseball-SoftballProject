package com.example.hunter.myapplication2;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Hunter on 3/8/2017.
 */

public class Player {
    private UUID id;
    private String lastName;
    private String firstName;
    private String number; // Jersey number.
    private Date lastUpdate;
    private boolean isPitcher;
    private boolean isCatcher;
    private boolean isInfield;
    private boolean isOutfield;
    private String positions;

    public Player() {
        setId();
        setLastUpdate();
        setLastName("");
        setFirstName("");
        setNumber(99);
        setPitcher(false);
        setCatcher(false);
        setInfield(false);
        setOutfield(true);
        setPositions();
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        id = UUID.randomUUID();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = String.valueOf(number);
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate() {
        lastUpdate = new Date();
    }

    public boolean isPitcher() {
        return isPitcher;
    }

    public void setPitcher(boolean pitcher) {
        isPitcher = pitcher;
        setPositions();
    }

    public boolean isCatcher() {
        return isCatcher;
    }

    public void setCatcher(boolean catcher) {
        isCatcher = catcher;
        setPositions();
    }

    public boolean isInfield() {
        return isInfield;
    }

    public void setInfield(boolean infield) {
        isInfield = infield;
        setPositions();
    }

    public boolean isOutfield() {
        return isOutfield;
    }

    public void setOutfield(boolean outfield) {
        isOutfield = outfield;
        setPositions();
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions() {
        positions = "";
        if (isPitcher) positions += R.string.symbol_pitcher;
        if (isCatcher) positions += R.string.symbol_catcher;
        if (isInfield) positions += R.string.symbol_infield;
        if (isOutfield) positions += R.string.symbol_outfield;
    }


    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", number='" + number + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", positions='" + positions + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (isPitcher != player.isPitcher) return false;
        if (isCatcher != player.isCatcher) return false;
        if (isInfield != player.isInfield) return false;
        if (isOutfield != player.isOutfield) return false;
        if (!id.equals(player.id)) return false;
        if (lastName != null ? !lastName.equals(player.lastName) : player.lastName != null)
            return false;
        if (firstName != null ? !firstName.equals(player.firstName) : player.firstName != null)
            return false;
        if (number != null ? !number.equals(player.number) : player.number != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(player.lastUpdate) : player.lastUpdate != null)
            return false;
        return positions != null ? positions.equals(player.positions) : player.positions == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (isPitcher ? 1 : 0);
        result = 31 * result + (isCatcher ? 1 : 0);
        result = 31 * result + (isInfield ? 1 : 0);
        result = 31 * result + (isOutfield ? 1 : 0);
        result = 31 * result + (positions != null ? positions.hashCode() : 0);
        return result;
    }
}
