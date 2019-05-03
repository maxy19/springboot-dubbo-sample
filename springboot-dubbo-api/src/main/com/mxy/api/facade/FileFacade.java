package com.mxy.api.facade;

import org.springframework.web.multipart.MultipartFile;

public interface FileFacade {

    Boolean transfer(MultipartFile file);

}