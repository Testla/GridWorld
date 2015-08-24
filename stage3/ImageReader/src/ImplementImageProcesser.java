package imagereader;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;

public class ImplementImageProcesser implements IImageProcessor {
    
    public static final int RedBitmask = 0xffff0000,
                            GreenBitmask = 0xff00ff00,
                            BlueBitmask = 0xff0000ff;
            
    private class BitmaskFilter extends RGBImageFilter {
        private int bitmask;
        public BitmaskFilter(int bitmask) {
            // The filter's operation does not depend on the
            // pixel's location, so IndexColorModels can be
            // filtered directly.
            canFilterIndexColorModel = true;
            this.bitmask = bitmask;
        }

        @Override
        public int filterRGB(int x, int y, int rgb) {
            return rgb & bitmask;
        }
    }

    
    private class GrayFilter extends RGBImageFilter {
        private int bitmask;
        public GrayFilter() {
            // The filter's operation does not depend on the
            // pixel's location, so IndexColorModels can be
            // filtered directly.
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int x, int y, int rgb) {
            int I = (int)(((rgb >> 16) & 0xff) * 0.299
                        + ((rgb >> 8) & 0xff) * 0.587
                        + ((rgb >> 0) & 0xff) * 0.114);
            return ((rgb & 0xff000000) | (I << 16) | (I << 8) | I);
        }
    }

    @Override
    public Image showChanelB(Image image) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        image.getSource(),
                        new BitmaskFilter(BlueBitmask)));
    }

    @Override
    public Image showChanelG(Image image) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        image.getSource(),
                        new BitmaskFilter(GreenBitmask)));
    }

    @Override
    public Image showChanelR(Image image) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        image.getSource(),
                        new BitmaskFilter(RedBitmask)));
    }

    @Override
    public Image showGray(Image image) {
//        return javax.swing.GrayFilter.createDisabledImage(image);
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        image.getSource(),
                        new GrayFilter()));
    }

}
