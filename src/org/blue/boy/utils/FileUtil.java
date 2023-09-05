package org.blue.boy.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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
}
