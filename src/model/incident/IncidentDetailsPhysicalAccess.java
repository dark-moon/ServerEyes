package model.incident;

import java.util.Date;
import model.Area;
import model.User;

/**
 *
 * @author kashwaa
 */
public class IncidentDetailsPhysicalAccess extends IncidentDetails {

    private final Area reportedArea;
    private final String actionAttempted;

    public IncidentDetailsPhysicalAccess(Date time,
            String description, IncidentReporter reporter, Area reportedArea, boolean IsEntering) {
        super(time, description, reporter);
        this.reportedArea = reportedArea;
        this.actionAttempted = IsEntering? "enter" : "leave";
    }

    public Area getReportedArea() {
        return reportedArea;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.description)
                .append("\nDetails:\n")
                .append(this.getIncidentTime().toString())
                .append(": ")
                .append(((User) this.getReporter()).getUserName())
                .append(" tried to ")
                .append(actionAttempted)
                .append(" area: ")
                .append(reportedArea.getAreaName())
                .append(" in an unautherized way\n");
        return result.toString();
    }
}
