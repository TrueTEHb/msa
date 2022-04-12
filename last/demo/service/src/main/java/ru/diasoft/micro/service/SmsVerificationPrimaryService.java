package ru.diasoft.micro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.diasoft.micro.domain.*;
import ru.diasoft.micro.model.SmsVerificationMessage;
import ru.diasoft.micro.repository.SmsVerificationRepository;
import ru.diasoft.micro.smsverificationcreated.publish.SmsVerificationCreatedPublishGateway;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Primary
public class SmsVerificationPrimaryService implements SmsVerificationService {

    private final SmsVerificationRepository repository;
    private final SmsVerificationCreatedPublishGateway messagingGateway;

    @Override
    public SmsVerificationCheckResponse dsSmsVerificationCheck(SmsVerificationCheckRequest smsVerificationCheckRequest) {
        String code = smsVerificationCheckRequest.getCode();
        String guid = smsVerificationCheckRequest.getProcessGUID();
        Optional<SmsVerification> smsVerification = repository.findBySecretCodeAndProcessGuidAndStatus(code, guid, "OK");
        return new SmsVerificationCheckResponse(smsVerification.isPresent());
    }

    @Override
    public SmsVerificationPostResponse dsSmsVerificationCreate(SmsVerificationPostRequest smsVerificationPostRequest) {
        String GUID = UUID.randomUUID().toString();
        String secretCode = String.format("%04d", new Random().nextInt(10000));
        String phoneNumber = smsVerificationPostRequest.getPhoneNumber();
        SmsVerification smsVerification = SmsVerification
                .builder()
                .phoneNumber(phoneNumber)
                .processGuid(GUID)
                .secretCode(secretCode)
                .status("WAITING")
                .build();
        repository.save(smsVerification);

        messagingGateway.smsVerificationCreated(
                SmsVerificationMessage.builder().guid(GUID).code(secretCode).phoneNumber(phoneNumber).build());
        return new SmsVerificationPostResponse(GUID);
    }
}
