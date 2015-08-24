import imagereader.IImageProcessor;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class ImplementImageProcesser implements IImageProcessor {
    
    public static final int RED_BITMASK = 0xffff0000,
                            GREEN_BITMASK = 0xff00ff00,
                            BLUE_BITMASK = 0xff0000ff;
            
    private class BitmaskFilter extends RGBImageFilter {
        private int bitmask;
        public BitmaskFilter(int bitmask) {
            // The filter's operation does not depend on the
            // pixel's location, so IndexColorModels can be
            // filtered directly.
            this.bitmask = bitmask;
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int x, int y, int rgb) {
            return rgb & bitmask;
        }
    }

    
    private class GrayFilter extends RGBImageFilter {
        public GrayFilter() {
            // The filter's operation does not depend on the
            // pixel's location, so IndexColorModels can be
            // filtered directly.
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int x, int y, int rgb) {
            int gray = (int)(((rgb >> 16) & 0xff) * 0.299
                        + ((rgb >> 8) & 0xff) * 0.587
                        + ((rgb >> 0) & 0xff) * 0.114);
            return ((rgb & 0xff000000) | (gray << 16) | (gray << 8) | gray);
        }
    }

    @Override
    public Image showChanelB(Image image) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        image.getSource(),
                        new BitmaskFilter(BLUE_BITMASK)));
    }

    @Override
    public Image showChanelG(Image image) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        image.getSource(),
                        new BitmaskFilter(GREEN_BITMASK)));
    }

    @Override
    public Image showChanelR(Image image) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        image.getSource(),
                        new BitmaskFilter(RED_BITMASK)));
    }

    @Override
    public Image showGray(Image image) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        image.getSource(),
                        new GrayFilter()));
    }

}
