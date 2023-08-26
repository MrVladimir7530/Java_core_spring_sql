package com.example.springsql.service;

import com.example.springsql.entities.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {

    Avatar uploadAvatar(Long studentId, MultipartFile file) throws IOException;

    Avatar getAvatarById(Long avatarId);
}
