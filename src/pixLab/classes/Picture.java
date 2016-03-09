package pixLab.classes;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture
{
	// /////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture()
	{
		/*
		 * not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName)
	{
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture(int height, int width)
	{
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture(Picture copyPicture)
	{
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image)
	{
		super(image);
	}

	// //////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName,
	 *         height and width.
	 */
	public String toString()
	{
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
		return output;

	}

	/** Method to set the blue to 0 */
	public void zeroRed()
	{
		topPixel[][] pixels = this.getPixels2D();
		for (topPixel[] rowArray : pixels)
		{
			for (topPixel pixelObj : rowArray)
			{
				pixelObj.setRed(0);
			}
		}
	}

	public void zeroGreen()
	{
		topPixel[][] pixels = this.getPixels2D();
		for (topPixel[] rowArray : pixels)
		{
			for (topPixel pixelObj : rowArray)
			{
				pixelObj.setGreen(0);
			}

		}
	}

	/**
	 * Method that mirrors the picture around a vertical mirror in the center of
	 * the picture from left to right
	 */
	public void mirrorVertical()
	{
		topPixel[][] pixels = this.getPixels2D();
		topPixel leftPixel = null;
		topPixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++)
		{
			for (int col = 0; col < width / 2; col++)
			{
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(rightPixel.getColor());
			}
		}
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple()
	{
		int mirrorPoint = 276;
		topPixel leftPixel = null;
		topPixel rightPixel = null;
		topPixel[][] pixels = this.getPixels2D();
		int width = pixels[0].length;

		// loop through the rows
		for (int row = 27; row < 97; row++)
		{
			// loop from 13 to just before the mirror point
			for (int col = 0; col < width / 2; col++)
			{

				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	public void mirrorArms()
	{
		int mirrorPoint = 250;
		topPixel topPixel = null;
		topPixel bottomPixel = null;
		topPixel[][] pixels = this.getPixels2D();
		int width = pixels[0].length;
		
		//loop through the rows
		for (int row = 153; row < 200; row++)
		{
			//loop from 13 just before the mirror point
			for (int col = 92; col < 290; col++)
			{
				
				topPixel = pixels[row][col];
				bottomPixel = pixels[row+50][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}
	}
	
	public void mirrorHorizontal()
	{
		topPixel[][] pixels = this.getPixels2D();
		topPixel leftPixel = null;
		topPixel rightPixel = null;
		int height = pixels.length;
		for (int row = 0; row < height / 2; row++)
		{
			for (int col = 0; col < pixels[0].length; col++)
			{
				leftPixel = pixels[row][col];
				rightPixel = pixels[height - 1 - row][col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	public void randomColor()
	{
		topPixel[][] pixels = this.getPixels2D();
		for (topPixel[] row : pixels)
		{

		}
	}

	public void mirrorVerticalRightToLeft()
	{
		topPixel[][] pixels = this.getPixels2D();
		topPixel leftPixel = null;
		topPixel rightPixel = null;
		int pictureWidth = pixels[0].length;
		for (int row = 0; row < pixels.length; row++)
		{
			for (int col = pixels[0].length - 1; col > pictureWidth / 2; col--)
			{
				rightPixel = pixels[row][col];
				leftPixel = pixels[row][(pictureWidth / 2) - (col - pictureWidth / 2)];
			}
		}
	}

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in
	 * the current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol)
	{
		topPixel fromPixel = null;
		topPixel toPixel = null;
		topPixel[][] toPixels = this.getPixels2D();
		topPixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length && toRow < toPixels.length; fromRow++, toRow++)
		{
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length && toCol < toPixels[0].length; fromCol++, toCol++)
			{
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}
	
	public void mirrorHorizontalBottomToTop()
	{
		topPixel[][] pixels = this.getPixels2D();
		topPixel topPixel = null;
		topPixel bottomPixel = null;
		int height = pixels.length;
		for (int row = 0; row < height / 2; row++)
		{
			for (int col = 0; col < pixels[0].length; col++)
			{
				topPixel = pixels[row][col];
				bottomPixel = pixels[height - 1 - row][col];
				topPixel.setColor(bottomPixel.getColor());
			}
		}
	}

	/** Method to create a collage of several pictures */
	public void createCollage()
	{
		Picture flower1 = new Picture("cancerlv.jpg");
		Picture flower2 = new Picture("cancercw.jpg");
		Picture newPicture = new Picture("beach.jpg");
		this.copy(newPicture, 250, 600);
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		flower2.zeroGreen();
		this.copy(flower1, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroRed();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}

	public void keepOnlyBlue()
	{
		topPixel[][] pixels = this.getPixels2D();
		for (topPixel[] rowArray : pixels)
		{
			for (topPixel pixelObj : rowArray)
			{
				pixelObj.setGreen(0);
				pixelObj.setRed(0);
			}

		}
	}

	public void keepOnlyRed()
	{
		topPixel[][] pixels = this.getPixels2D();
		for (topPixel[] rowArray : pixels)
		{
			for (topPixel pixelObj : rowArray)
			{
				pixelObj.setGreen(0);
				pixelObj.setBlue(0);
			}
		}
	}

	public void keepOnlyGreen()
	{
		topPixel[][] pixels = this.getPixels2D();
		for (topPixel[] rowArray : pixels)
		{
			for (topPixel pixelObj : rowArray)
			{
				pixelObj.setRed(0);
				pixelObj.setBlue(0);
			}
		}
	}

	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist
	 *            the distance for finding edges
	 */
	public void edgeDetection(int edgeDist)
	{
		topPixel leftPixel = null;
		topPixel rightPixel = null;
		topPixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length; row++)
		{
			for (int col = 0; col < pixels[0].length - 1; col++)
			{
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}
	}

	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args)
	{
		Picture beach = new Picture("cancerlv.jpg");
		beach.explore();
		beach.mirrorHorizontal();
		
		beach.zeroGreen();

		beach.explore();
		
		beach = new Picture("cancerlv.jpg");
		beach.keepOnlyBlue();
		beach.explore();
		
		beach = new Picture("cancerlv.jpg");
		beach.keepOnlyGreen();
		beach.explore();
		
		beach = new Picture("cancerlv.jpg");
		beach.keepOnlyRed();
		beach.explore();
		
		beach = new Picture("cancerlv.jpg");
		beach.mirrorVerticalRightToLeft();
		beach.explore();
		
		beach = new Picture("cancerlv.jpg");
		beach.mirrorHorizontalBottomToTop();
		beach.explore();
		
		beach = new Picture("temple.jpg");
		beach.mirrorTemple();
		beach.explore();
		
	    beach = new Picture("snowman.jpg");
		beach.mirrorArms();
		beach.explore();
		
	}

} // this } is the end of class Picture, put all new methods before this
