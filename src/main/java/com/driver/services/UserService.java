package com.driver.services;


import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.model.WebSeries;
import com.driver.repository.UserRepository;
import com.driver.repository.WebSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WebSeriesRepository webSeriesRepository;


    public Integer addUser(User user){

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setAge(user.getAge());
        newUser.setMobNo(user.getMobNo());
        newUser = userRepository.save(newUser);
        return newUser.getId();

        //Just simply add the user to the Db and return the userId returned by the repository
    }

    public Integer getAvailableCountOfWebSeriesViewable(Integer userId){

        //Return the count of all webSeries that a user can watch based on his ageLimit and subscriptionType
        //Hint: Take out all the Webseries from the WebRepository

        List<WebSeries> webSeriesList = webSeriesRepository.findAll();

        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent())
            return 0;

        User user = userOptional.get();

        int countSeries = 0;

        for(WebSeries webSeries : webSeriesList)
        {
            if (webSeries.getSubscriptionType().equals(user.getSubscription().getSubscriptionType()) && webSeries.getAgeLimit() <= user.getAge())
            {
                countSeries+=1;
            }
        }

        return countSeries;
    }


}
