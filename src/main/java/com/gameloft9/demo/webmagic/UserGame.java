package com.gameloft9.demo.webmagic;

import com.gameloft9.demo.dataaccess.model.user.ReptileUserBase;
import com.gameloft9.demo.dataaccess.model.user.ReptileUserContentStatistics;
import lombok.Data;

import java.util.List;

@Data
public class UserGame {

    private ReptileUserBase rub;

    private List<ReptileUserContentStatistics> rList;
}
