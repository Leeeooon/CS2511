package framework.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class manages statistics for solver objects.
 * @author tcolburn
 */
public class Statistics {

    /**
     * Creates a new statistics object.
     * Statistics are integers stored on a hash map keyed by strings 
     * representing the statistics names.
     */
    public Statistics() {
        keyList = new ArrayList<>();
        statMap = new HashMap<>();
    }

    /**
     * Sets the header for this set of statistics.
     * The header will be used as a title for the statistics display string.
     * @param header a header for this set of statistics
     */
    public void setHeader(String header) {
        this.header = header;
    }
    
    /**
     * Adds a new statistic and initializes it to zero.
     * @param key the name of the statistic
     */
    public void initStat(String key) { 
        keyList.add(key);
        statMap.put(key, 0);        
    }
    
    /**
     * Increments a statistic by one.
     * @param key the name of the statistic
     * @throws RuntimeException if the name is not found
     */
    public void incrStat(String key) {
        int v = getStat(key); 
        statMap.put(key, v+1);               
    }
    
    /**
     * Sets a statistic to a given value.
     * @param key the name of the statistic
     * @param v the statistic's value
     */
    public void putStat(String key, Integer v) {
        statMap.put(key, v);
    }
    
    /**
     * Gets a statistic's value.
     * @param key the name of the statistic
     * @return the statistic's value
     * @throws RuntimeException if the name is not found
     */
    public Integer getStat(String key) {
        Object o = statMap.get(key);  
        if (o == null) {
            throw new RuntimeException("Key not found in getStat: " + key);
        }
        return (Integer)o;
    }
    
    /**
     * Sets all of the statistics to zero.
     */
    public void reset() {
        keyList.stream().forEach((key) -> {
            statMap.put(key, 0);
        });
    }
    
    /**
     * Produces a list of the statistics' names and values, with the
     * header used for a title.
     * @return a string representing all the statistics' names and values
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(header);
        builder.append("\n");
        keyList.stream().forEach(k -> { 
            builder.append(k + "   " + statMap.get(k) + "\n");
        });
        return builder.toString();
    }
    
    private final List<String> keyList;
    private final HashMap<String, Integer> statMap;
    
    private String header;
}
