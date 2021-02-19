package com.auth.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.auth.models.FileInfo;
import com.auth.models.Gymclass;
import com.auth.models.Video;
import com.auth.repositories.FilesStorageService;
import com.auth.repositories.GymclassRepository;
import com.auth.repositories.VideoRepository;
import com.auth.request.FilenameRequest;
import com.auth.response.ResponseMessage;


@RestController
@RequestMapping("/api/files")
@CrossOrigin("*")
public class FilesController {

  @Autowired
  FilesStorageService storageService;
  
  @Autowired
  VideoRepository vidrepo;
  @Autowired
  GymclassRepository gymrepo;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("gymid") String gymid)
  {
    String message = "";
    try {
    	
	Gymclass gym = gymrepo.findById(gymid).get();
	  
	String newname = gym.getName()+"/"+file.getOriginalFilename();
	storageService.save(file,gym.getName());
	  
	Video vid = new Video(file.getOriginalFilename(),"uploads/"+newname, gym.getId());
	vidrepo.save(vid);
	  
	gym.getVideoids().add(vid.getId());
		
	gymrepo.save(gym);
	
	message = "video : " + newname + " added to gym : " + gym.getName();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() +"\n" + e.getMessage() +"!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/download")
  public ResponseEntity<List<FileInfo>> getListFiles()
  {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @PostMapping("/download/file/")
  @ResponseBody
  public ResponseEntity<Resource> getFile(@RequestBody FilenameRequest request) {
    Resource file = storageService.load(request.getFilename());
    System.out.println("file found " + file);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}
