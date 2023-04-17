package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import services.ImageService;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/images/*")
public class ImagesServlet extends HttpServlet {

    private final ImageService imageService = ImageService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        var uri = req.getRequestURI();

        var path = uri.replaceAll("/images", "");

        imageService.getInputStream(path).ifPresentOrElse(
                inputStream -> {
                    resp.setContentType("application/octet-stream");
                    writeImage(inputStream, resp);
                },
                () -> resp.setStatus(404)

        );


    }

    @SneakyThrows

    private void writeImage(InputStream inputStream, HttpServletResponse resp) {

        try(inputStream; var writer = resp.getOutputStream();) {


            int currentByte;
            while ((currentByte = inputStream.read()) != -1){
                writer.write(currentByte);
            }
        }
    }
}
