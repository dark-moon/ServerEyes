package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kashwaa
 */
public class RFID_Pair {

    private static final List<RFID_Reader> outReaders = new ArrayList<>();
    private static final List<RFID_Reader> inReaders = new ArrayList<>();
    
    private RFID_Reader outsideReader;
    private RFID_Reader insideReader;

    public RFID_Pair() {
        
    }
    
    public RFID_Reader getOutsideReader(){
        return outsideReader;
    }
    
    public RFID_Reader getInsideReader(){
        return insideReader;
    }
    
    public static RFID_Reader getInside(RFID_Reader outsideReader){
        return inReaders.get(outReaders.indexOf(outsideReader));
    }
    
    public static RFID_Reader getOutside(RFID_Reader insideReader){
        return outReaders.get(inReaders.indexOf(insideReader));
    }
    
    public static int addPair(RFID_Pair pair){
        return addPair(pair.getOutsideReader(), pair.getInsideReader());
    }
    
    public static int addPair(RFID_Reader outsideReader, RFID_Reader insideReader){
        if (!outsideReader.isOutside() && insideReader.isOutside()) {
            return -1;
        }
        inReaders.add(insideReader);
        int index = inReaders.indexOf(insideReader);
        outReaders.add(index, outsideReader);
        if(outReaders.indexOf(outsideReader) == -1){
            inReaders.remove(index);
            return -1;
        }
        return index;
    }
    
    public static boolean removePair(RFID_Reader outsideReader, RFID_Reader insideReader){
        if (outReaders.indexOf(outsideReader) == inReaders.indexOf(insideReader)) {
            if(outReaders.remove(outsideReader)){
                if(!inReaders.remove(insideReader)){
                    outReaders.add(inReaders.indexOf(insideReader), outsideReader);
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
