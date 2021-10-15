package com.telefonica.msappdetailplaninformation.local.model.service;

import com.telefonica.msappdetailplaninformation.local.model.dto.SubscriberInformationDTO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriberService {


    private List<SubscriberInformationDTO> lst = new ArrayList<>();

    @PostConstruct
    public void initDB() {
        lst.add(new SubscriberInformationDTO(1, "Test 1", 01));
        lst.add(new SubscriberInformationDTO(2, "Test 2", 02));
        lst.add(new SubscriberInformationDTO(3, "Test 3", 03));
    }

    public SubscriberInformationDTO addSubscriber(SubscriberInformationDTO information) {
        lst.add(information);
        return information;
    }

    public List<SubscriberInformationDTO> subscriberAll() {
        return lst;
    }
}
