package services;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.PropertiesUtil;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {


    private final String BASE_PATH = PropertiesUtil.get("db.baseImagePath");
    private static final ImageService INSTANCE = new ImageService();


    @SneakyThrows
    public void upload(String pathToImage, InputStream inputStream) {
        Path path = Path.of(BASE_PATH, pathToImage);

        try(inputStream) {

            Files.createDirectories(path.getParent());
            Files.write(path,inputStream.readAllBytes());
        }
    }




    @SneakyThrows
    public Optional<InputStream> getInputStream(String path) {
        Path fullPath = Path.of(BASE_PATH, path);

        return Files.exists(fullPath) ? Optional.of(Files.newInputStream(fullPath)) : Optional.empty();
    }

    public static ImageService getInstance() {
        return INSTANCE;
    }

}
