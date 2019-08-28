package co.grandcircus.bepositive.dto;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Singleton;
import com.cloudinary.StoredFile;

public class PhotoUpload extends StoredFile {

	private MultipartFile file;

	public String getUrl() {
		if (version != null && format != null && publicId != null) {
			return Singleton.getCloudinary().url().resourceType(resourceType).type(type).format(format).version(version)
					.generate(publicId);
		} else
			return null;
	}

	public String getComputedSignature() {
		return getComputedSignature(Singleton.getCloudinary());
	}

	public boolean validSignature() {
		return getComputedSignature().equals(signature);
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
