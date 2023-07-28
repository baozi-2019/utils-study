package com.baozi.minio;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.http.Method;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 作者：鲍庆洋
 * 时间：2023/7/27 11:26
 * 描述：
 */
public class MinIOStudy {
    public static void main(String[] args) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("192.168.0.155", 9000, false)
                        .credentials("fq6YW0SbdwEv6Q7jiAv6", "VP9AhOCulwDakknRroYD5HUoFdBYyhI84HiWUqKv")
                        .region("dalian-rack-1")
                        .build();
        minioClient.setTimeout(5000, 60000, 60000);
        boolean test = minioClient.bucketExists(BucketExistsArgs.builder().bucket("test").build());
        System.out.println(test);
        Map<String, String> map = new HashMap<>();
        map.put("Content-Disposition", "attachment; filename=111.png");
        System.out.println(minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .object("120.png").bucket("test").expiry(10, TimeUnit.MINUTES)
                .extraHeaders(map).method(Method.GET).build()));

    }
}
