package br.com.neja.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucket;

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	public void uploadFile(String caminho) {
		try {
			LOG.info(" \n\n iniciando upload \n\n ");
			File file = new File(caminho);

			s3Client.putObject(new PutObjectRequest(bucket, "teste", file));
			LOG.info(" \n\n upload finalizado \n\n ");
		} catch (AmazonServiceException e) {
			LOG.info(e.getServiceName() + " | AmazonServiceException:  " + e.getErrorMessage());
			LOG.info(e.getServiceName() + ".code.status  :  " + e.getErrorCode());
		} catch (AmazonClientException e) {
			LOG.info("AmazonClientException :  " + e.getMessage());
			LOG.info( "code.status  :  " + e.getLocalizedMessage());
		}

	}

}
