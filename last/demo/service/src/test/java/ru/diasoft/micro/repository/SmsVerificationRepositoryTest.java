package ru.diasoft.micro.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.micro.domain.SmsVerification;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsVerificationRepositoryTest {

    @Autowired
    private SmsVerificationRepository repository;

    @Test
    public void smsVerificationCreateTest() {
        SmsVerification smsVerification = SmsVerification.builder()
                .processGuid(UUID.randomUUID().toString())
                .phoneNumber("12345678")
                .secretCode("jopa")
                .status("WAITING")
                .build();
        SmsVerification created = repository.save(smsVerification);
        assertNotNull(created.getVerificationId());
        assertEquals(smsVerification, created);
    }

    @Test
    public void smsVerificationCheckTest() {
        String guid = UUID.randomUUID().toString();
        String status = "WAITING";
        String secretCode = "jopa";
        SmsVerification smsVerification = SmsVerification.builder()
                .processGuid(guid)
                .phoneNumber("12345678")
                .secretCode(secretCode)
                .status(status)
                .build();
        SmsVerification created = repository.save(smsVerification);
        assertNotNull(repository.findBySecretCodeAndProcessGuidAndStatus(secretCode, guid, status).orElse(SmsVerification.builder().build()));
        assertEquals(created, smsVerification);
        assertNull(repository.findBySecretCodeAndProcessGuidAndStatus("sc", "juid", "stat").orElse(null));
    }

    @Test
    public void updateStatusByProcessGuidTest() {
        String guid = UUID.randomUUID().toString();
        String status = "WAITING";
        String secretCode = "jopa";

        SmsVerification smsVerification = SmsVerification.builder()
                .processGuid(guid)
                .phoneNumber("12345678")
                .secretCode(secretCode)
                .status(status)
                .build();
        SmsVerification created = repository.save(smsVerification);

        repository.updateStatusByProcessGuid("OK", guid);
        assertEquals(repository.findById(created.getVerificationId())
                .orElse(SmsVerification.builder().build()).getStatus(), "OK");
    }
}