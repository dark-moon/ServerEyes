package model.incident;

import java.util.Date;
import model.User;
import model.UserState;

/**
 *
 * @author kashwaa
 */
public class IncidentDetailsStateTransition extends IncidentDetails{
    
    private final String originalState;
    private final String attemptedState;
    private final String attemptedAction;

    public IncidentDetailsStateTransition(String originalState, 
            String attemptedState, String attemptedAction, 
            Date time, String description, 
            IncidentReporter reporter) {
        super(time, description, reporter);
        this.originalState = originalState;
        this.attemptedState = attemptedState;
        this.attemptedAction = attemptedAction;
    }

    
    public String getOriginalState() {
        return originalState;
    }

    public String getAttemptedState() {
        return attemptedState;
    }
    
    public String getAttemptedAction() {
        return attemptedAction;
    }
    
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(this.description)
                .append("\nDetails:\n")
                .append(this.incidentTime)
                .append(": abnormal state transition prevented for ")
                .append(((User)this.reporter).getUserName())
                .append("'s state \n\tfrom: ")
                .append(this.originalState)
                .append("\n\tto: ")
                .append(this.attemptedState)
                .append("\n\tattempted Action: ")
                .append(this.attemptedAction);
        return result.toString();
    }

    
}
