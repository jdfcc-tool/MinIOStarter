package com.jdfcc.miniostarter.config;

import com.jdfcc.miniostarter.entity.MinIOConfigProperties;
import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.FileStorageService;

/**
 * @author Jdfcc
 * @Description MinIoConfig
 * @DateTime 2023/7/18 16:01
 */

@Data
@Configuration
@EnableConfigurationProperties({MinIOConfigProperties.class})
//当引入FileStorageService接口时
@ConditionalOnClass(FileStorageService.class)
public class MinIoConfig {

    @Autowired
    private MinIOConfigProperties minIOConfigProperties;

    @Bean
    public MinioClient buildMinioClient() {
        return MinioClient
                .builder()
                .credentials(minIOConfigProperties.getAccessKey(), minIOConfigProperties.getSecretKey())
                .endpoint(minIOConfigProperties.getEndpoint())
                .build();
    }
}
