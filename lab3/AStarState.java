import java.util.HashMap;

public class AStarState
{
	private HashMap<Location, Waypoint> openedWaypointsSet = new HashMap<Location, Waypoint>();
	private HashMap<Location, Waypoint> closedWaypointsSet = new HashMap<Location, Waypoint>();
	
    private Map2D map;

    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    public Map2D getMap()
    {
        return map;
    }

    public Waypoint getMinOpenWaypoint() /*Возвращает ссылку на вершину с минимальной 
    стоимостью из набора открытых вершин или null если вершин в наборе нет*/
    {
    	Waypoint minWaypoint = null;
    	float minWaypointValue = Float.MAX_VALUE;
    	
    	if (numOpenWaypoints() == 0) {
    		return null;
    	}
    	
    	for (Waypoint key : openedWaypointsSet.values()) {
    		if (key.getTotalCost() < minWaypointValue) {
    			minWaypointValue = key.getTotalCost();
    			minWaypoint = key;
    		}
    	}
    	return minWaypoint;
    }
    
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Waypoint wp = openedWaypointsSet.get(newWP.loc);

        if (wp == null) { /*Добавляем вершину если ее не существует в этой локации*/
        	openedWaypointsSet.put(newWP.loc, newWP);
            return true;
        } else if (newWP.getPreviousCost() < wp.getPreviousCost()) {
        	/*Заменяем вершину при условии, что "стоимость" новой вершины меньше*/
        	openedWaypointsSet.put(newWP.loc, newWP); 
            return true;
        } else {
        	return false;
        }
    }

    public int numOpenWaypoints() /*Возвращает кол-во точек в наборе открытых вершин*/
    {
        return openedWaypointsSet.size();
    }

    public void closeWaypoint(Location loc)
    {
        Waypoint wp = openedWaypointsSet.remove(loc); /*Удаление вершины из набора открытых вершин*/
        closedWaypointsSet.put(loc, wp); /*Добавление вершины в набор закрытых вершин*/
    }

    public boolean isLocationClosed(Location loc) /*Проверка на существование местороложения loc в 
    наборе закрытых вершин*/
    {
    	return closedWaypointsSet.containsKey(loc);
    }
}