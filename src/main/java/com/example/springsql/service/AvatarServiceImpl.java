package com.example.springsql.service;

import com.example.springsql.entities.Avatar;
import com.example.springsql.entities.Student;
import com.example.springsql.exceptions.AvatarNotFoundExceptions;
import com.example.springsql.repositorries.AvatarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Service
public class AvatarServiceImpl implements AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentService studentService;

    Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);
    @Value("${path.to.avatars.folder}$")
    private String avatarDir;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    @Override
    public Avatar uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.debug("starting upload avatar's method");
        Student student = studentService.findStudent(studentId);
        Path pathFile = Path.of(avatarDir, UUID.randomUUID() + "." + getExtension(avatarFile.getOriginalFilename()));

        Files.createDirectories(pathFile.getParent());
        Files.deleteIfExists(pathFile);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(pathFile, StandardOpenOption.CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setStudent(student);
        avatar.setPath(pathFile.toString());
        avatar.setSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        logger.debug("saving avatar");
        return avatarRepository.save(avatar);
    }

    private byte[] generateDataForDB(Path filePath) throws IOException {
        try (
                InputStream is = Files.newInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics2D = preview.createGraphics();
            graphics2D.drawImage(image, 0, 0, 100, height, null);
            graphics2D.dispose();
            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            logger.debug("reading file");
            return baos.toByteArray();
        }
    }

    @Override
    public Avatar getAvatarById(Long studentId) {
        logger.debug("got avatar by {}", studentId);
        return avatarRepository.findByStudentId(studentId).orElseThrow(()->new AvatarNotFoundExceptions("Avatar not found"));
    }

    @Override
    public Collection<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        logger.debug("got all avatars");
        return avatarRepository.findAll(pageable).getContent();
    }


    private String getExtension(String fileName) {
        logger.debug("got extension");
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }
}
