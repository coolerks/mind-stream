package top.integer.blog.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import top.integer.blog.exception.DataException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class ImageUpload {
    private final FileManager fileManager;

    public void uploadCompressedImage(String path, ByteArrayOutputStream byteArrayOutputStream) {
        System.out.println("压缩完成");
        ByteArrayOutputStream compressed = compressPictures(byteArrayOutputStream);
        try (ByteArrayInputStream compressedInput = new ByteArrayInputStream(compressed.toByteArray())) {
            if (!fileManager.upload(path, compressedInput)) {
                throw new DataException("上传失败");
            }
        } catch (IOException e) {
            throw new DataException("上传失败");
        }
    }

    private ByteArrayOutputStream compressPictures(ByteArrayOutputStream byteArrayOutputStream) {
        log.info("开始压缩图片");
        long start = System.currentTimeMillis();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ByteArrayOutputStream compressed = new ByteArrayOutputStream();

        try (byteArrayInputStream) {
            Thumbnails.of(byteArrayInputStream)
                    .scale(0.5f)
                    .outputQuality(0.5f)
                    .toOutputStream(compressed);
            compressed.flush();
        } catch (Throwable e) {
            throw new DataException("图片压缩失败，请上传图片。");
        }
        log.info("图片压缩成功，耗时：{} ms", System.currentTimeMillis() - start);
        return compressed;
    }

    public void uploadOriginalImage(String path, ByteArrayOutputStream byteArrayOutputStream) {
        log.info("开始上传原图");
        long start = System.currentTimeMillis();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        try (byteArrayInputStream) {
            if (!fileManager.upload(path, byteArrayInputStream)) {
                throw new DataException("上传失败");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("原图上传成功，耗时：{} ms", System.currentTimeMillis() - start);
    }
}
