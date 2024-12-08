package demo.api;

import demo.api.utils.CustomEncryptionUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomDataSourceConfig {
  @Value("${spring.datasource.password}")
  private String encryptedPassword;
  private String decryptedPassword;

  @PostConstruct
  public void init() throws Exception {
    // 假设加密后的密码以"ENC:"开头
    if (encryptedPassword.startsWith("ENC:")) {
      String encryptedPart = encryptedPassword.substring(4);
      decryptedPassword = CustomEncryptionUtil.decrypt(encryptedPart);
    } else {
      decryptedPassword = encryptedPassword;
    }
  }

  // 可以在这里使用decryptedPassword来配置数据源
  // 例如，创建一个DataSource Bean等操作
}
