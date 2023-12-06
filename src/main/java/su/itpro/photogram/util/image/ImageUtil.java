package su.itpro.photogram.util.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import su.itpro.photogram.util.PropertiesUtil;
import su.itpro.photogram.util.image.exception.ImageConvertException;

public class ImageUtil {

  private static final String IMAGE_OUTPUT_FORMAT
      = PropertiesUtil.getProperty("image.output.format");
  private static final double IMAGE_OUTPUT_QUALITY
      = PropertiesUtil.getDouble("image.output.quality", 1.0);

  private ImageUtil() {
  }

  public static String convertToBase64(byte[] data) {
    return Base64.getEncoder().encodeToString(data);
  }

  public static byte[] checkAndResize(byte[] image, int width, int height) {
    try {
      BufferedImage original = ImageIO.read(new ByteArrayInputStream(image));
      if (original.getWidth() == width && original.getHeight() == height) {
        return image;
      }
      return resize(original, width, height);
    } catch (IOException e) {
      throw new ImageConvertException(e.getMessage());
    }
  }

  private static byte[] resize(BufferedImage original, int width, int height) {
    double targetAspectRatio = (double) width / height;
    double currentAspectRatio = (double) original.getWidth() / original.getHeight();

    if (currentAspectRatio > targetAspectRatio) {
      byte[] resizedByHeight = resizeByHeight(original, height);
      return cropByWidth(resizedByHeight, width, height);
    } else {
      byte[] resizedByWidth = resizeByWidth(original, width);
      return cropByHeight(resizedByWidth, width, height);
    }
  }

  private static byte[] resizeByWidth(BufferedImage image, int width) {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      Thumbnails.of(image)
          .width(width)
          .outputFormat(IMAGE_OUTPUT_FORMAT)
          .outputQuality(IMAGE_OUTPUT_QUALITY)
          .toOutputStream(outputStream);
      return outputStream.toByteArray();
    } catch (IOException e) {
      throw new ImageConvertException(e.getMessage());
    }
  }

  private static byte[] resizeByHeight(BufferedImage image, int height) {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      Thumbnails.of(image)
          .height(height)
          .outputFormat(IMAGE_OUTPUT_FORMAT)
          .outputQuality(IMAGE_OUTPUT_QUALITY)
          .toOutputStream(outputStream);
      return outputStream.toByteArray();
    } catch (IOException e) {
      throw new ImageConvertException(e.getMessage());
    }
  }

  private static byte[] cropByWidth(byte[] image, int width, int height) {
    BufferedImage bufferedImage = toBufferedImage(image);
    int x = (bufferedImage.getWidth() - width) / 2;
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      Thumbnails.of(bufferedImage)
          .scale(1.0)
          .sourceRegion(x, 0, width, height)
          .outputFormat(IMAGE_OUTPUT_FORMAT)
          .outputQuality(IMAGE_OUTPUT_QUALITY)
          .toOutputStream(output);
      return output.toByteArray();
    } catch (IOException e) {
      throw new ImageConvertException(e.getMessage());
    }
  }

  private static byte[] cropByHeight(byte[] image, int width, int height) {
    BufferedImage bufferedImage = toBufferedImage(image);
    int y = (bufferedImage.getHeight() - height) / 2;
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      Thumbnails.of(bufferedImage)
          .scale(1.0)
          .sourceRegion(0, y, width, height)
          .outputFormat(IMAGE_OUTPUT_FORMAT)
          .outputQuality(IMAGE_OUTPUT_QUALITY)
          .toOutputStream(output);
      return output.toByteArray();
    } catch (IOException e) {
      throw new ImageConvertException(e.getMessage());
    }
  }

  private static BufferedImage toBufferedImage(byte[] image) {
    try {
      return ImageIO.read(new ByteArrayInputStream(image));
    } catch (IOException e) {
      throw new ImageConvertException(e.getMessage());
    }
  }

}
