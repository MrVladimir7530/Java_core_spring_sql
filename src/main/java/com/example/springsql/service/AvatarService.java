package com.example.springsql.service;

import com.example.springsql.entities.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface AvatarService {

    Avatar uploadAvatar(Long studentId, MultipartFile file) throws IOException;

    Avatar getAvatarById(Long avatarId);

    Collection<Avatar> getAllAvatars(Integer page, Integer size);
}
