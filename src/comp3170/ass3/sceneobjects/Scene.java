https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package comp3170.ass3.sceneobjects;

import comp3170.SceneObject;

public class Scene extends SceneObject {	
	
	public static Scene instance;
	
	public Scene() {		
		instance = this;

		Axes3D axes = new Axes3D();
		axes.setParent(this);
		
		Car car = new Car();
		car.setParent(this);
	}

}
