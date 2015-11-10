package gfx;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by ff on 3.11.2015 ã..
 */
public class SpriteSheet {
    private BufferedImage image;
    private int cropWidth;
    private int cropHeight;

    public SpriteSheet(BufferedImage image, int cropWidth, int cropHeight) {
        this.image = image;
        this.cropWidth = cropWidth;
        this.cropHeight = cropHeight;
    }

    public BufferedImage crop (int col, int row) {
        return this.image.getSubimage(col*cropWidth, row*cropHeight, cropWidth, cropHeight);
    }
}
