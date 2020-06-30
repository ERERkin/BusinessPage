package kg.ItAcademy.BusinessPage.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping
public class ImageController {
    @PostMapping("/img")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        final String var = "cloudinary://195177273174982:32Ghbev-X3B100OzYzfpI6b9Zrg@dxyxfqijk/";
        //Image image = new Image();
        File f = Files.createTempFile(Objects.requireNonNull(
                file.getOriginalFilename().substring(0,
                        file.getOriginalFilename().indexOf("."))),
                file.getOriginalFilename().substring(
                        file.getOriginalFilename().indexOf(".")
                )
        ).toFile();
        file.transferTo(f);
        Cloudinary cloudinary = new Cloudinary(var);
        Map result = cloudinary.uploader().upload(f, ObjectUtils.emptyMap());
        System.out.println(result.get("url"));
        return result.get("url").toString();
    }
}
