package ru.diasoft.micro.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.micro.domain.*;
import ru.diasoft.micro.repository.SmsVerificationRepository;
import ru.diasoft.micro.smsverificationcreated.publish.SmsVerificationCreatedPublishGateway;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsVerificationServiceTest {

    @Mock
    private SmsVerificationRepository repository;

    @Mock
    private SmsVerificationCreatedPublishGateway messagingGateway;

    private SmsVerificationPrimaryService service;

    private final String PHONE_NUMBER = "+9379992";
    private final String VALID_SECRET_CODE = "valid code";
    private final String INVALID_SECRET_CODE = "invalid code";
    private final String GUID = UUID.randomUUID().toString();
    private final String STATUS = "OK";

    @Before
    public void init() {
        service = new SmsVerificationPrimaryService(repository, messagingGateway);

        SmsVerification smsVerification = SmsVerification.builder()
                .processGuid(GUID)
                .phoneNumber(PHONE_NUMBER)
                .secretCode(VALID_SECRET_CODE)
                .status(STATUS)
                .build();
        Mockito.when(repository.findBySecretCodeAndProcessGuidAndStatus(VALID_SECRET_CODE, GUID, STATUS))
                .thenReturn(Optional.of(smsVerification));
        Mockito.when(repository.findBySecretCodeAndProcessGuidAndStatus(INVALID_SECRET_CODE, GUID, STATUS))
                .thenReturn(Optional.empty());
    }

    @Test
    public void dsSmsVerificationCheckValid() {
        SmsVerificationCheckResponse valid = service.dsSmsVerificationCheck(new SmsVerificationCheckRequest(GUID, VALID_SECRET_CODE));
        assertNotNull(valid);
        assertTrue(valid.getCheckResult());
    }

    @Test
    public void dsSmsVerificationCheckInvalid() {
        SmsVerificationCheckResponse inValid = service.dsSmsVerificationCheck(new SmsVerificationCheckRequest(GUID, INVALID_SECRET_CODE));
        assertNotNull(inValid);
        assertFalse(inValid.getCheckResult());
    }

    @Test
    public void dsSmsVerificationCreate() {
        SmsVerificationPostRequest request = new SmsVerificationPostRequest(PHONE_NUMBER);
        SmsVerificationPostResponse response = service.dsSmsVerificationCreate(request);
        assertNotNull(response);
        assertNotNull(response.getProcessGUID());
    }
}