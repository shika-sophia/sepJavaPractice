package javaPractice.chapter05;

public class Practice5_4 {

	public static void main(String[] args) {
		//---- 三角形 ----
		double height = 10.0;
		double bottom = 5.0;

		double valueTri = calcTriangleArea(height, bottom);
		System.out.println("三角形の面積: " + valueTri);

		//---- 円の面積 ----
		double radius = 5.0;
	}//main()

	private static double calcTriangleArea(double height, double bottom) {

		return height * bottom / 2;
	}

}//class
