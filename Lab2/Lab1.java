public class Lab1 {
	public static void main(String[] args) {
		double[] coords; 
		coords = new double[args.length];
		for (int i = 0; i < args.length; i++) { 
			coords[i] = Double.parseDouble(args[i]);
		}
			
		Point3d pointA = new Point3d(coords[0],coords[1],coords[2]);
		Point3d pointB = new Point3d(coords[3],coords[4],coords[5]);
		Point3d pointC = new Point3d(coords[6],coords[7],coords[8]);
		
		if (pointA.compareValues(pointB) || pointB.compareValues(pointC) || pointC.compareValues(pointA)) {
			System.out.println("Некоторые точки совпадают");
		}else {
			System.out.println("Площадь треугольника: "+computeArea(pointA, pointB, pointC));
		}
	}
	
	public static double computeArea(Point3d pointA, Point3d pointB, Point3d pointC) { 
		double ab = pointA.distanceTo(pointB); 
		double bc = pointB.distanceTo(pointC); 
		double ac = pointC.distanceTo(pointA); 
		double p = 0.5*(ab+bc+ac); 
		return (Math.sqrt(p*(p-ab)*(p-bc)*(p-ac))); 
	}
}