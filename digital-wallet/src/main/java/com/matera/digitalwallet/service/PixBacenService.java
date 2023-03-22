package com.matera.digitalwallet.service;

import com.matera.digitalwallet.model.dto.PixBacenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PixBacenService {
    private static final String ACCOUNTS_BACEN_URL = "http://localhost:8088/contas";
    private final static RestTemplate restTamplate = new RestTemplate();

    public void registerPixCentralBank(PixBacenDto pixBacenDTO) {
        HttpEntity<PixBacenDto> request = new HttpEntity<>(pixBacenDTO);
        PixBacenDto pixBacenDto = restTamplate.postForObject(
                ACCOUNTS_BACEN_URL,
                request,
                PixBacenDto.class);
        log.info("Conta cadastrada com sucesso no Banco Central: {}", pixBacenDto);
    }

    public PixBacenDto findAccountCentralBank(String pixKey) {
        String url = String.format("%s/%s", ACCOUNTS_BACEN_URL, pixKey);
        PixBacenDto pixBacenDto = restTamplate.getForObject(url, PixBacenDto.class);
        log.info("Conta encontrada com sucesso no Banco Central: {}", pixBacenDto);
        return pixBacenDto;
    }
}
