package com.dbmsProject.repositories;

import com.dbmsProject.domain.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepository extends JpaRepository<UserImage,Long> {
}
