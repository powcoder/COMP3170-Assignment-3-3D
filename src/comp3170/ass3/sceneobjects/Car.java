https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package comp3170.ass3.sceneobjects;

import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;

import java.io.FileNotFoundException;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import comp3170.GLBuffers;
import comp3170.SceneObject;
import comp3170.Shader;
import comp3170.ShaderLibrary;
import comp3170.ass3.models.Model;
import comp3170.ass3.models.Model.Mesh;

public class Car extends SceneObject {

	private static final String OBJ_FILE = "src/comp3170/ass3/models/car.obj";
	static final private String VERTEX_SHADER = "simpleVertex.glsl";
	static final private String FRAGMENT_SHADER = "simpleFragment.glsl";

	private Vector4f colour = new Vector4f(1, 1, 1, 1);

	private int vertexBuffer;
	private int indexBuffer;
	private int nIndices;

	private Shader shader;

	public Car() {
		shader = ShaderLibrary.instance.compileShader(VERTEX_SHADER, FRAGMENT_SHADER);
		shader.setStrict(false); 	// disable error checking for setAttribute and setUniform;
		
		// Load the car model from the OBJ file

		Model model = null;

		try {
			model = new Model(OBJ_FILE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Get the Body submesh

		Mesh mesh = model.getMesh("Body");
		vertexBuffer = GLBuffers.createBuffer(mesh.vertices);
		indexBuffer = GLBuffers.createIndexBuffer(mesh.indices);
		nIndices = mesh.indices.length;
	}

	@Override
	protected void drawSelf(Matrix4f mvpMatrix, int pass) {
		shader.enable();

		shader.setUniform("u_mvpMatrix", mvpMatrix);
		shader.setAttribute("a_position", vertexBuffer);
		shader.setUniform("u_colour", colour);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		glDrawElements(GL_TRIANGLES, nIndices, GL_UNSIGNED_INT, 0);
	}

}
