package shop.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.app.repository.DialogRepository;

@Service
public class DialogService {

    @Autowired
    private DialogRepository dialogRepository;



}
