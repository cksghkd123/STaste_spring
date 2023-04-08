package com.hwang.staste.algorithm;

import com.hwang.staste.model.entity.FoodAbility;
import com.hwang.staste.model.entity.Sticker;
import com.hwang.staste.model.entity.UserAbility;
import com.hwang.staste.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewAlgo {

    private UserAbility userAbility;
    private FoodAbility foodAbility;
    private List<Sticker> stickerList;
    private int reviewCount;

    private ArrayList<Long> hackLevelSum;
    private ArrayList<Long> tokLevelSum;
    private ArrayList<Long> maraLevelSum;
    private ArrayList<Long> sweetLevelSum;
    private ArrayList<Long> satisfyLevelSum;


    private void calculateStickers(List<Sticker> stickerList){

        for (Sticker sticker : stickerList) {
            hackLevelSum.add(sticker.getHackLevel());
            tokLevelSum.add(sticker.getTokLevel());
            maraLevelSum.add(sticker.getMaraLevel());
            sweetLevelSum.add(sticker.getSweetLevel());
            satisfyLevelSum.add(sticker.getSatisfyLevel());
        }
    }

    private Double calculateAverage(ArrayList<Long> sumList) {
        if(sumList.isEmpty()){
            return null;
        }
        long sum = 0;
        int count = 0;

        for (Long value : sumList) {
            if (value != null) {
                sum += value;
                count++;
            }
        }

        if (count == 0) {
            return null;
        }

        return (double) sum / count;
    }



    //@@@@@@@@@ 업데이트 메소드 @@@@@@@@@@@@@//
    public void updateAbility(UserAbility userAbility, FoodAbility foodAbility, List<Sticker> stickerList, int reviewCount) {
        hackLevelSum = new ArrayList<>();
        tokLevelSum = new ArrayList<>();
        maraLevelSum = new ArrayList<>();
        sweetLevelSum = new ArrayList<>();
        satisfyLevelSum = new ArrayList<>();
        this.reviewCount = reviewCount;
        this.userAbility = userAbility;
        this.foodAbility = foodAbility;

        calculateStickers(stickerList);

        Double hackLevelAvg = calculateAverage(hackLevelSum);
        Double tokLevelAvg = calculateAverage(tokLevelSum);
        Double maraLevelAvg = calculateAverage(maraLevelSum);
        Double sweetLevelAvg = calculateAverage(sweetLevelSum);
        Double satisfyLevelAvg = calculateAverage(satisfyLevelSum);


        updateAbilityLevel(userAbility.getHackLevel(), foodAbility.getHackLevel(), hackLevelAvg, "hack");
        updateAbilityLevel(userAbility.getTokLevel(), foodAbility.getTokLevel(), tokLevelAvg,"tok");
        updateAbilityLevel(userAbility.getMaraLevel(), foodAbility.getMaraLevel(), maraLevelAvg,"mara");
        updateAbilityLevel(userAbility.getSweetLevel(), foodAbility.getSweetLevel(), sweetLevelAvg,"sweet");
        updateAbilityLevel(userAbility.getSatisfyLevel(), foodAbility.getSatisfyLevel(), satisfyLevelAvg,"satisfy");

    }

    private void updateAbilityLevel(Double userAbilityLevel, Double foodAbilityLevel, Double reviewLevel, String abilityType) {
        switch (abilityType) {
            case "hack":
                userAbilityLevel = userAbility.getHackLevel();
                foodAbilityLevel = foodAbility.getHackLevel();
                break;
            case "tok":
                userAbilityLevel = userAbility.getTokLevel();
                foodAbilityLevel = foodAbility.getTokLevel();
                break;
            case "mara":
                userAbilityLevel = userAbility.getMaraLevel();
                foodAbilityLevel = foodAbility.getMaraLevel();
                break;
            case "sweet":
                userAbilityLevel = userAbility.getSweetLevel();
                foodAbilityLevel = foodAbility.getSweetLevel();
                break;
            case "satisfy":
                userAbilityLevel = userAbility.getSatisfyLevel();
                foodAbilityLevel = foodAbility.getSatisfyLevel();
                break;
            default:
                return;
        }
        if (userAbilityLevel == null || foodAbilityLevel == null || reviewLevel == null) {
            return;
        }

        Double actualDifference = foodAbilityLevel - userAbilityLevel;
        Double weight;
        if(reviewCount < 5) {
            weight = 0.5; // 조절 상수
        } else if (5<= reviewCount && reviewCount<10) {
            weight = 0.3;
        } else {
            weight = 0.1;
        }

        if (reviewLevel < actualDifference) {
            userAbilityLevel += weight;
            foodAbilityLevel -= weight;
        } else if (reviewLevel > actualDifference) {
            userAbilityLevel -= weight;
            foodAbilityLevel += weight;
        }

        switch (abilityType) {
            case "hack":
                userAbility.setHackLevel(userAbilityLevel);
                foodAbility.setHackLevel(foodAbilityLevel);
                break;
            case "tok":
                userAbility.setTokLevel(userAbilityLevel);
                foodAbility.setTokLevel(foodAbilityLevel);
                break;
            case "mara":
                userAbility.setMaraLevel(userAbilityLevel);
                foodAbility.setMaraLevel(foodAbilityLevel);
                break;
            case "sweet":
                userAbility.setSweetLevel(userAbilityLevel);
                foodAbility.setSweetLevel(foodAbilityLevel);
                break;
            case "satisfy":
                userAbility.setSatisfyLevel(userAbilityLevel);
                foodAbility.setSatisfyLevel(foodAbilityLevel);
                break;
            default:
                return;
        }
    }
}
