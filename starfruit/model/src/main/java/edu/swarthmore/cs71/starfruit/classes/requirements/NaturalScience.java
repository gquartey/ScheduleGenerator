package edu.swarthmore.cs71.starfruit.classes.requirements;

import edu.swarthmore.cs71.starfruit.classes.History;

public class NaturalScience implements GradRequirement{
    private int taken;
    private boolean complete;
    private History history;

    public NaturalScience(History history) {
        this.complete = false;
        this.history = history;
        this.taken = history.getNS();
    }

    @Override
    public void updateHistory(History history) {
        this.history = history;
        this.taken = this.history.getNS();
        if(this.taken >=3){
            this.complete = true;
        }
        //maybe there was some change to a previous history
        else{
            this.complete = false;
        }
    }

    public boolean isCompleted() {
        return complete;
    }

    public Distribution getType() {
        return Distribution.NS;
    }

    public int getTotalNeeded() {
        if(this.taken>=3){
            return 0;
        }
        return 3-this.taken;
    }
}

