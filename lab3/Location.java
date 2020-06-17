import java.util.Objects;
public class Location
{
    public int xCoord;
    public int yCoord;

    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    public Location()
    {
        this(0, 0);
    }
    
    public boolean equals(Object obj) {
    	if (obj == this) {
    		return true;
    	}
		if (obj == null) {
			return false;
		}
    	Location a = (Location) obj;
    	return this.xCoord == a.xCoord && this.yCoord == a.yCoord;
    }
    
    public int hashCode()
    {
    	return Objects.hash(xCoord, yCoord);
    }
}