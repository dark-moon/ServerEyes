package model.incident;

/**
 *
 * @author kashwaa
 */
public interface IncidentResponse {
    public <T extends IncidentDetails> void respond(T details);
}
