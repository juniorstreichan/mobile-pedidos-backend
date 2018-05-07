package br.com.neja.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucket;

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	public URI uploadFile(MultipartFile multipartFile) {
		try {

			String fileName = multipartFile.getOriginalFilename();
			InputStream input = multipartFile.getInputStream();

			String contentType = multipartFile.getContentType();

			return uploadFile(input, fileName, contentType);

		} catch (IOException e) {
			throw new RuntimeException("Erro IO: " + e.getMessage());
		}
	}

	public URI uploadFile(InputStream input, String fileName, String contentType) {
		try {

			LOG.info(" \n\n iniciando upload \n\n ");
			ObjectMetadata meta = new ObjectMetadata();
			s3Client.putObject(bucket, fileName, input, meta);
			LOG.info(" \n\n upload finalizado \n\n ");
			return s3Client.getUrl(bucket, fileName).toURI();

		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter url para uri");
		}

	}

}
