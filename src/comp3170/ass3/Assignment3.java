https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package comp3170.ass3;


import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;

import java.io.File;

import org.joml.Matrix4f;

import comp3170.IWindowListener;
import comp3170.InputManager;
import comp3170.OpenGLException;
import comp3170.ShaderLibrary;
import comp3170.Window;
import comp3170.ass3.sceneobjects.Scene;

public class Assignment3 implements IWindowListener {

	private static final File SHADER_DIRS = new File("src/comp3170/ass3/shaders");

	private Window window;
	private int screenWidth = 1000;
	private int screenHeight = 1000;
	private Scene scene;
	private InputManager input;
	private long oldTime;

	public Assignment3() throws OpenGLException {
		window = new Window("Assignment 3", screenWidth, screenHeight, this);
		window.run();
	}
	
	@Override
	public void init() {
		new ShaderLibrary(SHADER_DIRS);

		// set up scene
		scene = new Scene();

		// initialise oldTime
		input = new InputManager(window);
		oldTime = System.currentTimeMillis();
	}

	private void update() {
		long time = System.currentTimeMillis();
		float deltaTime = (time - oldTime) / 1000f;
		oldTime = time;

		// TODO update the scene
		
		// Clear keysPressed flags at end of frame
		input.clear();
	}

	private Matrix4f viewMatrix = new Matrix4f();
	private Matrix4f projectionMatrix = new Matrix4f();
	private Matrix4f mvpMatrix = new Matrix4f();

	@Override
	public void draw() {
		update();
		
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT);

		// TODO: Replace this with a proper projection matrix
		projectionMatrix.identity().scale(0.5f, 0.5f, 0.5f);

		mvpMatrix.set(projectionMatrix).mul(viewMatrix);
		scene.draw(mvpMatrix);	// Opaque
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub		
	}
	
	public static void main(String[] args) throws OpenGLException {
		new Assignment3();
	}
}
