package com.efimchick;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.exists;
import static java.nio.file.Files.isDirectory;

@SpringBootApplication
@RestController
@ImportResource("file:config/settings.xml")
@PropertySource("file:config/application.properties")
public class SpringBootGalleryApplication {

	@Autowired(required = true)
	String root;

	private static ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(SpringBootGalleryApplication.class, args);
	}

	@RequestMapping("/hierarchy")
	ImageDirectoryDesc hierarchy() {
		Path rootPath = Paths.get(root);
		if (root != null && exists(rootPath) && isDirectory(rootPath)) {
			ImageDirectory dir = applicationContext.getBean(ImageDirectory.class, rootPath);
			return dir.getDesc();
		}
		return null;
	}

	@RequestMapping(value = "/i", method = RequestMethod.GET, produces = "image/jpg")
	ResponseEntity<byte[]> image(@RequestParam(required = true) String image) {
		Path path = getPathUnderRoot(image);
		InputStreamResource inputStream = new InputStreamResource(applicationContext.getBean(ImageSeeker.class, path).getStreamOfImage());
		return new ResponseEntity(inputStream, HttpStatus.OK);
	}

	private Path getPathUnderRoot(String image) {
		Path path;
		try {
			path = Paths.get(image);
			if (!path.startsWith(root)){
				throw new ResourceNotFoundException("Forbidden access to files that are not under root dir");
			}
		} catch (InvalidPathException e) {
			throw new ResourceNotFoundException(e);
		}
		return path;
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public static class ResourceNotFoundException extends RuntimeException {
		public ResourceNotFoundException() {
		}

		public ResourceNotFoundException(String message) {
			super(message);
		}

		public ResourceNotFoundException(String message, Throwable cause) {
			super(message, cause);
		}

		public ResourceNotFoundException(Throwable cause) {
			super(cause);
		}

		public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}
	}


	@Bean
	@Scope("prototype")
	public ImageDirectory imageDirectory(Path path) {
		return new ImageDirectory(path);
	}

	@Bean
	@Scope("prototype")
	public ImageSeeker imageSeeker(Path path) {
		return new ImageSeeker(path);
	}
}
