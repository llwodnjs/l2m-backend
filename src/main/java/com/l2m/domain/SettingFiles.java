package com.l2m.domain;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.l2m.domain.base.enums.DomainPrefix;
import com.l2m.domain.global.BaseEntity;
import com.l2m.domain.global.BaseFunction;
import com.l2m.exception.base.NoDataException;
import com.l2m.model.MySettingDto;
import com.l2m.util.global.BusinessKeyUtil;
import com.l2m.util.global.IsNullUtil;
import com.l2m.util.global.SessionUtil;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 나의 세팅 저장시 파일 저장용 Entity
 * 
 * by jaewon
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettingFiles extends BaseEntity implements BaseFunction<SettingFiles> {
  @Id
  @GeneratedValue
  @Column(name = "settingFilesId")
  private Long id;

  // 비즈니스 키
  @Column
  private String businessKey;

  // root 따로
  @Column
  private String root;

  // filePath
  @Column
  private String filePath;

  // 실제 fullPath
  @Column
  private String fullPath;

  // file명 + extension
  @Column
  private String fileName;

  // 실제 파일명
  @Column
  private String originalFileName;

  // 저장된 파일명
  @Column
  private String storedFileName;

  // 파일 사이즈
  @Column
  private Long fileSize;

  // 파일 url link
  @Column
  private String resourceLink;

  /**
   * AllArgsConstructor
   * 
   * @param createUserKey
   * @param createDateTime
   * @param updateUserKey
   * @param updateDateTime
   * @param id
   * @param businessKey
   * @param root
   * @param filePath
   * @param fullPath
   * @param fileName
   * @param originalFileName
   * @param storedFileName
   * @param fileSize
   * @param resourceLink
   */
  protected SettingFiles(String createUserKey, LocalDateTime createDateTime, String updateUserKey,
      LocalDateTime updateDateTime, Long id, String businessKey, String root, String filePath, String fullPath,
      String fileName,
      String originalFileName, String storedFileName, Long fileSize, String resourceLink) {
    super(createUserKey, createDateTime, updateUserKey, updateDateTime);
    this.id = id;
    this.businessKey = businessKey;
    this.root = root;
    this.filePath = filePath;
    this.fullPath = fullPath;
    this.fileName = fileName;
    this.originalFileName = originalFileName;
    this.storedFileName = storedFileName;
    this.fileSize = fileSize;
    this.resourceLink = resourceLink;
  }

  /**
   * clone용 생성자
   * 
   * @param settingFiles
   */
  protected SettingFiles(SettingFiles settingFiles) {
    super(settingFiles.getCreateUserKey(), settingFiles.getCreateDateTime(), settingFiles.getUpdateUserKey(),
        settingFiles.getUpdateDateTime());
    this.businessKey = settingFiles.getBusinessKey();
    this.root = settingFiles.getRoot();
    this.filePath = settingFiles.getFilePath();
    this.fullPath = settingFiles.getFullPath();
    this.fileName = settingFiles.getFileName();
    this.originalFileName = settingFiles.getOriginalFileName();
    this.storedFileName = settingFiles.getStoredFileName();
    this.fileSize = settingFiles.getFileSize();
    this.resourceLink = settingFiles.getResourceLink();
  }

  @Override
  public Supplier<SettingFiles> identity() {
    return () -> new SettingFiles();
  }

  @Override
  public SettingFiles clone(SettingFiles e) {
    return new SettingFiles(e);
  }

  @Override
  public SettingFiles destroy(SettingFiles e) {
    // TODO Auto-generated method stub
    return null;
  }

  public Path getLocation(String rootPath) {
    if (IsNullUtil.check(rootPath)) {
      throw new NoDataException("파일 저장 불가");
    }

    final Path rootLocation = Paths.get(rootPath);

    try {
      Files.createDirectories(rootLocation);
      return rootLocation;
    } catch (Exception e) {
      // TODO: handle exception
      throw new NoDataException("실패");
    }
  }

  /**
   * 파일 등록 supplier
   * 
   * @param file
   * @param contentDir
   * @param dir
   * @param path
   * @return
   */
  public static Supplier<SettingFiles> createFile(MultipartFile file, String contentDir, String root, String path) {
    return () -> new SettingFiles(file, contentDir, root, path);
  }

  /**
   * real file save
   * @param file
   * @param rootPath
   * @return
   */
  protected MySettingDto.settingFile storeFile(MultipartFile file, String rootPath) {
    final Path root = this.getLocation(rootPath);

    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    // StringBuilder 

    try {
      if (file.isEmpty()) {
        throw new NoDataException("빈 파일을 저장할 수 없습니다.");
      }

      try (InputStream inputStream = file.getInputStream()) {
        String storedFileName = UUID.randomUUID().toString().replaceAll("-", ""),
              originalFileName = FilenameUtils.getBaseName(fileName),
              fileExt = FilenameUtils.getExtension(fileName);

        Files.copy(inputStream, root.resolve(storedFileName + "." + fileExt), StandardCopyOption.REPLACE_EXISTING);

        return new MySettingDto.settingFile(originalFileName, storedFileName, fileExt, root.toAbsolutePath().toString(), file.getSize());
      }
    } catch (Exception e) {
      throw new NoDataException("파일 저장 실패");
    }
  }

  /**
   * 파일 등록
   * 
   * @param file
   * @param contentDir
   * @param dir
   * @param path
   */
  protected SettingFiles(MultipartFile file, String contentDir, String root, String path) {
    // 실제 파일 저장
    MySettingDto.settingFile settingFile = this.storeFile(file, root + contentDir + path);
    this.createUserKey = SessionUtil.getSession().getBusinessKey();
    this.updateUserKey = SessionUtil.getSession().getBusinessKey();

    this.businessKey = BusinessKeyUtil.create(DomainPrefix.FILE);
    this.root = root + contentDir;
    this.filePath = path;
    this.fileName = file.getName();
    this.originalFileName = settingFile.getOriginalFileName();
    this.storedFileName = settingFile.getReplacedFileName();
    this.fileSize = settingFile.getSize();
    this.fullPath = new StringBuilder(settingFile.getPath())
                            .append("/")
                            .append(this.storedFileName)
                            .append(".")
                            .append(settingFile.getExtension())
                            .toString();
    // this.resourceLink = resourceLink;

  }
}
