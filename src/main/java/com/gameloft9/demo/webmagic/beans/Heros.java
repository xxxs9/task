package com.gameloft9.demo.webmagic.beans;

import com.gameloft9.demo.dataaccess.model.user.HeroBase;
import lombok.Data;

import java.util.List;

@Data
public class Heros {

    private List<HeroBase> heroList;
}
