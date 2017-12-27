package edu.swarthmore.cs71.starfruit.classes.requirements;

import edu.swarthmore.cs71.starfruit.classes.History;

public class PE implements GradRequirement {
    private int taken;
    private boolean complete;
    private History history;

    public PE(History history) {
        this.complete = false;
        this.history = history;
        this.taken = history.getPe();
    }

    public boolean isCompleted() {
        return complete;
    }

    @Override
    public void updateHistory(History history) {
        this.history = history;
        this.taken = this.history.getPe();
        if(this.taken >=4){
            this.complete = true;
        }
        //maybe there was some change to a previous history and we need to re-update
        else{
            this.complete = false;
        }
    }

    public Distribution getType() {
        return Distribution.PE;
    }

    public int getTotalNeeded() {
        if(this.taken>=4){
            return 0;
        }
        return 4-this.taken;
    }
}
