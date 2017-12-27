package edu.swarthmore.cs71.starfruit.classes.requirements;

import edu.swarthmore.cs71.starfruit.classes.History;

public class Humanity implements GradRequirement{
    private int taken;
    private boolean complete;
    private History history;

    public Humanity(History history) {
        this.complete = false;
        this.history = history;
        this.taken = history.getHU();
    }

    @Override
    public void updateHistory(History history) {
        this.history = history;
        this.taken = this.history.getHU();
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
        return Distribution.HU;
    }

    public int getTotalNeeded() {
        if(this.taken>=3){
            return 0;
        }
        return 3-this.taken;
    }
}

