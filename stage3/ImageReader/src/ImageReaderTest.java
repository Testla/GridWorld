import static org.junit.Assert.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageReaderTest {
    private ImplementImageProcesser implementImageProcesser = new ImplementImageProcesser();
    ImplementImageIO implementImageIO = new ImplementImageIO();
    
    @Test
    public void testImplementImageIO() {
        try {
            String[] numbers = new String[]{ "1", "2"};
            for (String number : numbers) {
                assertTrue(imageDiff(
                        ImageIO.read(new FileInputStream("goal/" + number + "_red_goal.bmp")),
                        implementImageProcesser.showChanelR(implementImageIO.myRead(number + ".bmp"))
                ));
                assertTrue(imageDiff(
                        ImageIO.read(new FileInputStream("goal/" + number + "_green_goal.bmp")),
                        implementImageProcesser.showChanelG(implementImageIO.myRead(number + ".bmp"))
                ));
                assertTrue(imageDiff(
                        ImageIO.read(new FileInputStream("goal/" + number + "_blue_goal.bmp")),
                        implementImageProcesser.showChanelB(implementImageIO.myRead(number + ".bmp"))
                ));
            }
        } catch (IOException e) {
            fail("Exception thrown when testing!");
            return;
        }
    }
    
    static boolean imageDiff(Image a, Image b) {
        BufferedImage bImageA = ImplementImageIO.toBufferedImage(a),
                                    bImageB = ImplementImageIO.toBufferedImage(b);
        if (bImageA.getWidth(null) != bImageB.getWidth(null)) {
            return false;
        }
        if (bImageA.getHeight(null) != bImageB.getHeight(null)) {
            return false;
        }
        int width = bImageA.getWidth(null), height = bImageA.getHeight(null);
        for (int r = 0; r < height; ++r) {
            for (int c = 0; c < width; ++c) {
                if (bImageA.getRGB(c, r) != bImageB.getRGB(c, r)) {
                    return false;
                }
            }
        }
        return true;
    }
}
