package com.efimchick;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.isDirectory;

@SpringBootApplication
@RestController
@ImportResource("file:config/settings.xml")
@PropertySource("file:config/application.properties")
public class SpringBootGalleryApplication {

	@Autowired(required = true)
	String root;

	@Resource
	List<String> imgExtensions;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGalleryApplication.class, args);
	}

	@RequestMapping("/d")
	ImageDirectoryDesc directory(@RequestParam(required = false) String path) {
		Path actualPath = Paths.get(path == null || path.isEmpty() ? root : path);
		if (!actualPath.startsWith(root)){
			throw new IllegalArgumentException("Forbidden access to files tha are not under root dir");
		}
		if (Files.exists(actualPath)) {
			ImageDirectory dir = new ImageDirectory(actualPath, (p -> isImage(p)));
			return dir.getDesc();
		}
		return null;
	}

	@RequestMapping(value = "/i", method = RequestMethod.GET,produces = "image/jpg")
	ResponseEntity<byte[]> image(@RequestParam String path) {
		InputStreamResource inputStream = new InputStreamResource(ImageSeeker.getStreamOfImage(path, (p -> isImage(p))));
		return new ResponseEntity(inputStream, HttpStatus.OK);
	}

	@RequestMapping("/root")
	String getRootName() {
		return root;
	}


	public boolean isImage(Path path){
		Objects.requireNonNull(path);
		for (String extension : imgExtensions) {
			if (!isDirectory(path) && path.toString().toLowerCase().endsWith(extension.toLowerCase())){
				return true;
			}
		}
		return false;
	}
}
