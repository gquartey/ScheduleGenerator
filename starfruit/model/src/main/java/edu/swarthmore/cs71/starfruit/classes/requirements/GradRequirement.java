package edu.swarthmore.cs71.starfruit.classes.requirements;

import edu.swarthmore.cs71.starfruit.classes.History;

public interface GradRequirement {

    void updateHistory(History history);

    boolean isCompleted();

    Distribution getType();

    int getTotalNeeded();
}
