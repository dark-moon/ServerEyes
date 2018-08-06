package model;

import java.util.Date;

/**
 *
 * @author kashwaa
 */
public class RFID_Control {

    private final Area area;
    
    public RFID_Control(Area area){
        this.area = area;
    }
    
    public void handleSignal(RFID_Reader reader, User user){
        RFID_Reader insideReader;
        RFID_Reader outsideReader;
        
        if (reader.isOutside()) {
            outsideReader = reader;
            insideReader = RFID_Pair.getInside(reader);
            if (insideReader.isProppedBy(user)) {
                insideReader.addPropper(user);
                user.leaveArea(area, new Date());
                return;
            }
            outsideReader.addPropper(user);
        }else{
            insideReader = reader;
            outsideReader = RFID_Pair.getOutside(reader);
            if (outsideReader.isProppedBy(user)) {
                outsideReader.addPropper(user);
                user.accessArea(area, new Date());
                return;
            }
            insideReader.addPropper(user);
        }
    }
    
    
    
}
