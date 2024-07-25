package com.example.msjira.mapper;

import com.example.msjira.dao.entity.TeleSalesEntity;
import com.example.msjira.model.teleSales.TeleSaleDto;
import com.example.msjira.model.teleSales.TeleSaleReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TeleSalesMapper {

    List<TeleSaleDto> listToDto(List<TeleSalesEntity> teleSalesEntities);

    TeleSaleDto mapToDto(TeleSalesEntity teleSalesEntity);
    @Mapping(target = "image", source = "teleSaleReqDto", qualifiedByName = "fileToString")
    TeleSalesEntity mapToEntity(TeleSaleReqDto teleSaleReqDto);

    @Named("fileToString")
    default String fileToString(TeleSaleReqDto teleSaleReqDto) throws IOException {
        if (teleSaleReqDto.getImage() != null && !teleSaleReqDto.getImage().isEmpty()) {

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
            String uploadDir =  System.getProperty("user.dir")+"/uploads";
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);

            String originalFileName = teleSaleReqDto.getImage().getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileName = originalFileName.substring(0,originalFileName.lastIndexOf(".")) +"_" + timestamp + fileExtension;
            Path filePath = uploadPath.resolve(fileName);

            Files.write(filePath, teleSaleReqDto.getImage().getBytes());

            return filePath.toString();
        }
        return null;
    }

}
