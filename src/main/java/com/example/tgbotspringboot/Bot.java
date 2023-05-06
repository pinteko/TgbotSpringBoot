package com.example.tgbotspringboot;

import com.example.tgbotspringboot.Builder.FilterBuilder;
import com.example.tgbotspringboot.Builder.MyFilterBuilder;
import com.example.tgbotspringboot.Entity.Filter;
import com.example.tgbotspringboot.Entity.Vacancy;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class Bot {
//    private HhApi hhApi;
    private MyFilterBuilder filterBuilder;
    private GeneralLogic logic;
    private Filter filter;

    @Autowired
    public Bot(GeneralLogic logic, MyFilterBuilder filterBuilder){
        this.filterBuilder = filterBuilder;
        this.logic = logic;
        TelegramBot bot = new TelegramBot("1587428127:AAH-Kt63sdaOC_ZO14vpN9m3AD1pYMaYxCE");
        bot.setUpdatesListener(element -> {
            System.out.println(element);
            element.forEach(it->{

                String[] massive = it.message().text().split(" ");
                if (massive[0].equals("/go") || massive.length < 2){
                    bot.execute(new SendMessage(it.message().chat().id(), "Приветствую тебя кожанный холоп" +
                            "\nВведи название вакансии(одним словом), город поиска, опыт в виде:" + "\nВакансия Город" + "\nЕсли город поиска не уникальный введи в формате:" +
                            "\nВакансия Город(Область)" + "\nЕсли нужна дополнительная информация, то введи в вормате:" +
                            "\nВакансия Город(Область) Опыт Зарплата"));
                } else {
                    if (massive.length < 4)
                        massive = overRide(massive);
                    else {
                        massive = Arrays.copyOf(massive, 5);
                        massive[4] = String.valueOf(it.message().messageId()).concat(String.valueOf(it.message().from().id()));
                        ;
                    }
                    filterBuilder.setNameVacancy(massive[0]);
                    filterBuilder.setNameRegion(massive[1]);
                    filterBuilder.setExperience(massive[2]);
                    int i;
                    try {
                        i = Integer.parseInt(massive[3]);
                    } catch (NumberFormatException e) {
                        i = -1;
                    }
                    filterBuilder.setSalary(i);
                    filterBuilder.setRequestId(massive[4]);

                    filter = filterBuilder.getFilter();

                    List<Vacancy> list = logic.getVacancies(filter);

                    if (list.size() != 0){
                        list.forEach(vacancy -> {
                            bot.execute(new SendMessage(it.message().chat().id(), "Вакансия: " + vacancy.getName() + "\nСсылка: http://hh.ru/vacancy/" + vacancy.getId()));
                            System.out.println(vacancy.getId() + " " + vacancy.getName());
                        });
                    } else {
                        bot.execute(new SendMessage(it.message().chat().id(), "Данных вакансий в городе " + massive[1] +" не найдено"));
                    }
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }


    public String[] overRide(String[] s){
        String[] reg = Arrays.copyOf(s, 5);
        for (int i = s.length; i < 5; i++){
            reg[i] = null;
        }
        reg[3] = "543";
        reg[4] = "id23434534543";

        return reg;
    }
}
