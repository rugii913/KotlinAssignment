package kotlinassignment.week10.domain.image

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@RequestMapping("/images")
@RestController
class ImageController(
    @Value("\${AWS_S3_BUCKET_NAME}") private val bucketName: String,
    private val amazonS3: AmazonS3,
) {

    @PostMapping
    fun uploadImageToS3(
        @RequestPart(value = "image") image: MultipartFile
    ): String {
        val originalFilename = image.originalFilename ?: throw IllegalArgumentException("파일 이름 없음")
        val ext = originalFilename.substring(originalFilename.lastIndexOf('.'))
        val uuidFilename = assignUUIDFilename(originalFilename)
        val metadata = ObjectMetadata().also { it.contentType = "image/$ext" }

        val putObjectRequest = PutObjectRequest(bucketName, uuidFilename, image.inputStream, metadata)
            .withCannedAcl(CannedAccessControlList.PublicRead)

        try {
            val putObjectResult = amazonS3.putObject(putObjectRequest)
        } catch (e: Exception) {
            throw IllegalStateException("S3 업로드 중 오류", e)
        }

        return amazonS3.getUrl(bucketName, uuidFilename).toString()
    }

    private fun assignUUIDFilename(originalFileName: String): String {
        return UUID.randomUUID().toString() + originalFileName
    }

    /*
    * 참고 링크
    * https://growth-coder.tistory.com/116, https://growth-coder.tistory.com/104
    * https://leveloper.tistory.com/46
    * ***** The bucket does not allow ACLs 에러 해결 및 권한 관련 생각해볼 것 - https://velog.io/@devty/AWS-S3-Bucket
    * */
    
    /*
    * S3 호출 및 권한 관련 더 생각해볼 것
    * - AWS S3 이해하기: 기본 개념, 사용 방법, 그리고 사례 https://bellugadev.tistory.com/60
    * - AWS
    *   - Example: Browser-Based Upload using HTTP POST (Using AWS Signature Version 4) https://docs.aws.amazon.com/ko_kr/AmazonS3/latest/API/sigv4-post-example.html
    *   - Amazon S3 시작하기 https://aws.amazon.com/ko/s3/getting-started/?nc=sn&loc=6&dn=1
    *   - Amazon Simple Storage Service 사용 설명서 - https://docs.aws.amazon.com/ko_kr/AmazonS3/latest/userguide/Welcome.html
    *   - AWS SDK for Java 설명서 https://docs.aws.amazon.com/ko_kr/sdk-for-java/
    *     - https://github.com/aws/aws-sdk-java, https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/services/s3/package-summary.html
    *     - Java용 AWS SDK https://aws.amazon.com/ko/sdk-for-java/
    *   - AWS API 호출하기 (1) – 개론편 https://aws.amazon.com/ko/blogs/korea/aws-api-call-1/
    *
    * */
}