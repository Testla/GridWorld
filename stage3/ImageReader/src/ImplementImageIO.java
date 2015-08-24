package imagereader;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

public class ImplementImageIO implements IImageIO {

    @Override
    public Image myRead(String filePath) throws IOException {
        final int maxLength = 10 * 1024 * 1024;

        // read from file
        FileInputStream inputFile = new FileInputStream(filePath);
        byte[] fileData = new byte[maxLength]
                , imageData = new byte[maxLength];
        int fileLength;
        fileLength = inputFile.read(fileData);
        inputFile.close();
        ByteBuffer byteBuffer = ByteBuffer.wrap(
                fileData,
                0,
                fileLength
        ).order(ByteOrder.LITTLE_ENDIAN);

        // Create image
        int imageDataOffset = byteBuffer.getInt(10),
                width = byteBuffer.getInt(18),
                height = byteBuffer.getInt(22);
        // discard header
        byteBuffer.get(imageData, 0, imageDataOffset);
        byteBuffer.get(imageData, 0, fileLength - imageDataOffset);

        BufferedImage image = new BufferedImage(
                width, 
                height, 
                BufferedImage.TYPE_INT_RGB
        );
        image.setRGB(0, 0, width, height, byteArrayToRgbArray(imageData, width, height), 0, width);
//        Image img = Toolkit.getDefaultToolkit().createImage(
//                new MemoryImageSource(
//                        width, height,
//                        byteArrayToRgbArray(imageData, width, height),
//                        0, width
//                ));
        return image;
    }

    @Override
    public Image myWrite(Image image, String filePath) throws IOException {
        // TODO Auto-generated method stub
        ImageIO.write(
                toBufferedImage(image),
                "BMP",
                ImageIO.createImageOutputStream(new File(filePath))
        );
        return ImageIO.read(new File(filePath));
    }

    /**
     * Converts a given Image into a BufferedImage
     * 
     * @param img
     *            The Image to be converted
     * @return The converted BufferedImage
     */
    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null),
                img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    private static int[] byteArrayToRgbArray(byte byteArray[], int width, int height) {
        int result[] = new int[width * height]
            , strideLength = (width * 3 + 4 - 1) / 4 * 4;
        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {
                int offset = (height - 1 - row) * strideLength + col * 3;
                result[row * width + col] = byteArray[offset]
                                            + ((int)byteArray[offset + 1] << 8)
                                            + ((int)byteArray[offset + 2] << 16);
            }
        }
        return result;
    }
}
