/*
  Created by Davide Cossu (gjkf), 6/10/2016
 */
package com.gjkf.seriousEngine.core.gui;

import com.gjkf.seriousEngine.core.controls.MouseInput;
import com.gjkf.seriousEngine.core.render.Colors;
import com.gjkf.seriousEngine.core.render.Renderer;
import org.lwjgl.glfw.GLFW;

/**
 * 	Abstract class defining a {@code GuiWidget}.
 * 	It provides methods for drawing, updating and managing mouse inputs.
 *
 * 	@see GuiScreenWidget
 */

public abstract class GuiWidget{

    /**
     * Coordinates and dimensions
     */
	public float x, y, width, height;
    /**
     * The {@link MouseListener} that is called when the widget is pressed
     */
	public MouseListener listener;
    /**
     * If set to true will display a green grid to see the positioning
     */
    public boolean debug = false;
    /**
     * The parent screen
     */
    public GuiScreenWidget parent;

    /**
     * The widget constructor
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param width The width
     * @param height The height
     * @param mouseListener The mouse listener
     */

	public GuiWidget(float x, float y, float width, float height, MouseListener mouseListener){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.listener = mouseListener;
	}

	/**
	 * 	Checks if the given coordinates are inside the GuiWidget
	 *
	 * 	@param px The x coordinate of the point to check
	 * 	@param py The y coordinate of the point to check
	 *
	 * 	@return TRUE if the point is inside, FALSE otherwise
	 */

	public boolean pointInside(double px, double py) {
		return px >= x && px < x + width && py >= y && py < y + height;
	}

	/**
	 * 	Draw method for the widget
	 */

	public void draw(){
	    if(debug){
            for(int i = 0; i < width; i += width / 10){
                Renderer.drawLine(i, 0, i, height, Colors.GREEN.color);
            }
            for(int i = 0; i < width; i += height / 10){
                Renderer.drawLine(0, i, width, i, Colors.GREEN.color);
            }
        }
    }

	/**
	 * 	Update method for the widget
	 */

	public void update(){
		this.draw();
	}

	public void mouseClicked(){
		if(MouseInput.isPressed(GLFW.GLFW_MOUSE_BUTTON_LEFT) && pointInside(MouseInput.getMouseX(), MouseInput.getMouseY())){
			if(listener != null){
				listener.mouseClicked();
			}
		}
	}

    /**
     * Returns the parent screen
     *
     * @return The parent screen
     */

    public GuiScreenWidget getParent(){
        return parent;
    }

    /**
     * Sets the parent of this widget
     *
     * @param parent The new parent
     */

    public void setParent(GuiScreenWidget parent){
        this.parent = parent;
    }
}
