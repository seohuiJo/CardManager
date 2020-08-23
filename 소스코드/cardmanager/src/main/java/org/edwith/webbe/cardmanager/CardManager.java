package org.edwith.webbe.cardmanager;

import org.edwith.webbe.cardmanager.dao.BusinessCardManagerDao;
import org.edwith.webbe.cardmanager.dto.BusinessCard;
import org.edwith.webbe.cardmanager.ui.CardManagerUI;

import java.util.List;

public class CardManager {
    public static void main(String[] args){
        CardManagerUI cardManagerUI = CardManagerUI.getInstance();
        BusinessCardManagerDao businessCardManagerDao = new BusinessCardManagerDao();

        while_loop:
        while(true) {
            cardManagerUI.printMainMenu();
            int menuNumber = cardManagerUI.getMenuNumber();
            switch(menuNumber){
                case 1 :    // 명함 입력
                    BusinessCard businessCard = cardManagerUI.inputBusinessCard();
                    businessCardManagerDao.addBusinessCard(businessCard);
                    break;
                case 2 :    // 명함 검색
                    String searchKeyword = cardManagerUI.getSearchKeyword();
                    List<BusinessCard> businessCardList = businessCardManagerDao.searchBusinessCard(searchKeyword);
                    cardManagerUI.printBusinessCards(businessCardList);
                    break;
                case 3 :    // 종료
                    cardManagerUI.printExitMessage();
                    break while_loop;
                default:
                    cardManagerUI.printErrorMessage();
                    break;
            }
        }
    }
}
