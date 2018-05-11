package br.com.neja.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.neja.services.exception.FileException;

@Service
public class ImageService {

	public BufferedImage getJpgFromFile(MultipartFile file) {

		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		if (!"png".equals(extension) && !"jpg".equals(extension)) {
			throw new FileException("Somente imagens PNG e JPG são permitidas");
		}
		try {
			BufferedImage img = ImageIO.read(file.getInputStream());
			if ("png".equalsIgnoreCase(extension)) {
				img = pngToJpg(img);

			}
			return img;
		} catch (IOException e) {

			throw new FileException("Erro na leitura do arquivo");
		}

	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgRetorno = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgRetorno.createGraphics().drawImage(img, 0, 0, Color.WHITE/* preenche o transparente do png com branco */,
				null);
		return jpgRetorno;
	}

	public InputStream getInputStream(BufferedImage img, String ext) {
		try {

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(img, ext, output);
			return new ByteArrayInputStream(output.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro na leitura do arquivo");
		}
	}

	public BufferedImage cropSquareImage(BufferedImage img) {
		int min = (img.getHeight() <= img.getWidth() ? img.getHeight() : img.getWidth());
		return Scalr.crop(img,
				(img.getWidth() / 2) - (min / 2),
				(img.getHeight() / 2) - (min / 2),
				min, min);
	}
	
	public BufferedImage resize(BufferedImage img,int size) {
		return Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, size);
		
	}

}
