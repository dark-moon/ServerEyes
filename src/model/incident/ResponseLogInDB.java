package model.incident;

/**
 *
 * @author kashwaa
 */
public class ResponseLogInDB implements IncidentResponse {

    @Override
    public <T extends IncidentDetails> void respond(T details) {
        //TODO:Code to log the incident details in the database.
        System.out.println(details);
    }
    
}
