package com.hwang.staste.algorithm;

import com.hwang.staste.model.entity.FoodAbility;
import com.hwang.staste.model.entity.Sticker;
import com.hwang.staste.model.entity.UserAbility;
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
    public void updateAbility(UserAbility userAbility, FoodAbility foodAbility, List<Sticker> stickerList) {
        hackLevelSum = new ArrayList<>();
        tokLevelSum = new ArrayList<>();
        maraLevelSum = new ArrayList<>();
        sweetLevelSum = new ArrayList<>();
        satisfyLevelSum = new ArrayList<>();

        calculateStickers(stickerList);

        Double hackLevelAvg = calculateAverage(hackLevelSum);
        Double tokLevelAvg = calculateAverage(tokLevelSum);
        Double maraLevelAvg = calculateAverage(maraLevelSum);
        Double sweetLevelAvg = calculateAverage(sweetLevelSum);
        Double satisfyLevelAvg = calculateAverage(satisfyLevelSum);

        updateAbilityLevel(userAbility.getHackLevel(), foodAbility.getHackLevel(), hackLevelAvg);
        updateAbilityLevel(userAbility.getTokLevel(), foodAbility.getTokLevel(), tokLevelAvg);
        updateAbilityLevel(userAbility.getMaraLevel(), foodAbility.getMaraLevel(), maraLevelAvg);
        updateAbilityLevel(userAbility.getSweetLevel(), foodAbility.getSweetLevel(), sweetLevelAvg);
        updateAbilityLevel(userAbility.getSatisfyLevel(), foodAbility.getSatisfyLevel(), satisfyLevelAvg);

    }

    private void updateAbilityLevel(Double userAbilityLevel, Double foodAbilityLevel, Double reviewLevel) {
        if (userAbilityLevel == null || foodAbilityLevel == null || reviewLevel == null) {
            return;
        }

        Double actualDifference = foodAbilityLevel - userAbilityLevel;
        Double weight = 0.1; // 조절 상수

        if (reviewLevel < actualDifference) {
            userAbilityLevel += weight;
            foodAbilityLevel -= weight;
        } else if (reviewLevel > actualDifference) {
            userAbilityLevel -= weight;
            foodAbilityLevel += weight;
        }

    }
}
