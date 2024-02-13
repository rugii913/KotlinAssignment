package kotlinassignment.week10.domain.image

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ImageConfig(
    @Value("\${AWS_ACCESS_KEY}") private val accessKey: String,
    @Value("\${AWS_SECRET_ACCESS_KEY}") private val secretAccessKey: String,
    @Value("\${AWS_REGION}") private val region: String,
) {

    @Bean
    fun amazonS3(): AmazonS3 {
        val awsCredentials: AWSCredentials = BasicAWSCredentials(accessKey, secretAccessKey)

        return AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .withRegion(region).build()
    }
}
