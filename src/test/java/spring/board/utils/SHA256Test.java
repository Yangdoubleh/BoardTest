package spring.board.utils;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@Slf4j
class SHA256Test {

    @Test
    void encrypt() throws Exception{
        SHA256 sha256 = new SHA256();
        String password = "testpassword";

        String encrypt = sha256.encrypt(password);
        log.info("encrypt = {}", encrypt);

        Assertions.assertThat(encrypt).isEqualTo(sha256.encrypt(password));
    }
}