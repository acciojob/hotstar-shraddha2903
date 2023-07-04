package com.driver.services;


import com.driver.EntryDto.SubscriptionEntryDto;
import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.repository.SubscriptionRepository;
import com.driver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserRepository userRepository;

    public Integer buySubscription(SubscriptionEntryDto subscriptionEntryDto){

        //Save The subscription Object into the Db and return the total Amount that user has to pay

        //code
        //getting user by id
        User user = userRepository.findById(subscriptionEntryDto.getUserId()).get();
        if (user==null)return 0;

        Subscription userSubscription = new Subscription();
        userSubscription.setSubscriptionType(subscriptionEntryDto.getSubscriptionType());
//        userSubscription.setStartSubscriptionDate(new Date());
        userSubscription.setNoOfScreensSubscribed(subscriptionEntryDto.getNoOfScreensRequired());

        int amountPaid = 0;

        int screenSubscribed = subscriptionEntryDto.getNoOfScreensRequired();

        //Basic : 500 + 200 * noOfScreensSubscribed
        if(subscriptionEntryDto.getSubscriptionType().equals("BASIC"))
        {
            amountPaid = 500 + (200 * screenSubscribed);
        }
        //Pro : 800 + 250*noOfScreensSubscribed
        else if(subscriptionEntryDto.getSubscriptionType().equals("PRO"))
        {
            amountPaid = 800 + (250 * screenSubscribed);
        }
        //ELITE Plan : 1000 + 350*noOfScreensSubscribed
        else if(subscriptionEntryDto.getSubscriptionType().equals("ELITE"))
        {
            amountPaid = 1000 + (350 * screenSubscribed);
        }
        else
            return 0;

        userSubscription.setTotalAmountPaid(amountPaid);

        //mapping user->subscription
        user.setSubscription(userSubscription);

        subscriptionRepository.save(userSubscription);

        userRepository.save(user);

        return amountPaid;
    }

    public Integer upgradeSubscription(Integer userId)throws Exception{

        //If you are already at an ElITE subscription : then throw Exception ("Already the best Subscription")
        //In all other cases just try to upgrade the subscription and tell the difference of price that user has to pay
        //update the subscription in the repository

        return null;
    }

    public Integer calculateTotalRevenueOfHotstar(){

        //We need to find out total Revenue of hotstar : from all the subscriptions combined
        //Hint is to use findAll function from the SubscriptionDb

        return null;
    }

}
