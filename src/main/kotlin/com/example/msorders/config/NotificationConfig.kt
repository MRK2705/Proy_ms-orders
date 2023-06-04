package com.example.msorders.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.sns.AmazonSNSClient
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class NotificationConfig {

    @Primary
    @Bean

    fun amazonSNSClient(): AmazonSNSClient {
        return AmazonSNSClientBuilder.standard()
            .withRegion(Regions.US_EAST_1)
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials("AKIA5VXCFD7YSGH34YS7", "jcH/91mpmZ2qY5FewgjWv0kd2QuKUlemL9PTt6CR")))
            .build() as AmazonSNSClient
    }

}