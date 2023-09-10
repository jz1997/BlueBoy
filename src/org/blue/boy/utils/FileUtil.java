package org.blue.boy.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Logger;

public class FileUtil {
    private static final Logger LOG = Logger.getLogger("FileUtil");

    public BufferedImage loadImage(String relativeFilePath) {
        LOG.info(() -> String.format("Loading image '%s'", relativeFilePath));
        InputStream inputStream = getClass().getResourceAsStream(relativeFilePath);
        try {
            return ImageIO.read(Objects.requireNonNull(inputStream));
        } catch (IOException e) {
            LOG.severe(() -> String.format("Loading image '%s' error '%s'.", relativeFilePath, e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    public InputStream loadFile(String relativeFilePath) {
        LOG.info(() -> String.format("Loading file '%s'", relativeFilePath));
        InputStream inputStream = getClass().getResourceAsStream(relativeFilePath);
        if (inputStream == null) {
            LOG.severe(() -> String.format("Loading file '%s' error '%s'.", relativeFilePath, "NULL inputStream"));
            throw new RuntimeException(String.format("Cannot open file '%s'", relativeFilePath));
        }
        return inputStream;
    }

    public URL loadSoundURL(String relativeFilePath) {
        LOG.info(() -> String.format("Loading sound '%s'", relativeFilePath));
        URL url = getClass().getResource(relativeFilePath);
        if (url == null) {
            LOG.severe(() -> String.format("Loading sound '%s' error '%s'.", relativeFilePath, "NULL URL"));
            throw new RuntimeException(String.format("Cannot open file '%s'", relativeFilePath));
        }
        return url;
    }

    public BufferedImage scaleImage(BufferedImage originalImage, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = scaledImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return scaledImage;
    }
}
